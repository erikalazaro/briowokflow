package com.briomax.briobpm.business.helpers;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TimeZone;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.briomax.briobpm.business.helpers.base.INodoHelper;
import com.briomax.briobpm.business.helpers.base.ITemporizadorHelper;
import com.briomax.briobpm.business.service.catalogs.core.IMessagesService;
import com.briomax.briobpm.business.service.core.IBPMMailSender;
import com.briomax.briobpm.common.core.Constants;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.persistence.entity.BitacoraBatch;
import com.briomax.briobpm.persistence.entity.BitacoraBatchPK;
import com.briomax.briobpm.persistence.entity.ComposicionCorreo;
import com.briomax.briobpm.persistence.entity.ComposicionCorreoPK;
import com.briomax.briobpm.persistence.entity.CrConsultaRepse;
import com.briomax.briobpm.persistence.entity.CrConsultaRepsePK;
import com.briomax.briobpm.persistence.entity.CrPdfFiles;
import com.briomax.briobpm.persistence.entity.InNodoProceso;
import com.briomax.briobpm.persistence.entity.InNodoProcesoPK;
import com.briomax.briobpm.persistence.entity.ParametroGeneral;
import com.briomax.briobpm.persistence.entity.namedquery.LeeCorreosPorEnviar;
import com.briomax.briobpm.persistence.entity.namedquery.LeeCorreosPorEnviarPK;
import com.briomax.briobpm.persistence.repository.IBitacoraBatchRepository;
import com.briomax.briobpm.persistence.repository.IComposicionCorreoRepository;
import com.briomax.briobpm.persistence.repository.ICorreosEnviarRepository;
import com.briomax.briobpm.persistence.repository.ICrConsultaRepseRepository;
import com.briomax.briobpm.persistence.repository.ICrDefinicionPeriocidadRepository;
import com.briomax.briobpm.persistence.repository.ICrPdfFilesRepository;
import com.briomax.briobpm.persistence.repository.ICrProgramacionProcesoRepository;
import com.briomax.briobpm.persistence.repository.IInNodoProcesoRepository;
import com.briomax.briobpm.persistence.repository.IInVariableProcesoRepository;
import com.briomax.briobpm.persistence.repository.IParametroGeneralRepository;
import com.briomax.briobpm.transferobjects.emun.MagicNumber;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;
import com.briomax.briobpm.transferobjects.in.EstatusTO;
import com.briomax.briobpm.transferobjects.in.NodoTO;
import com.briomax.briobpm.transferobjects.repse.RepseConsultaVariablesDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class TemporizadorHelper implements ITemporizadorHelper {


	/** El atributo o variable Entidad service. */
	@Autowired
	private IInNodoProcesoRepository inNodoProcesoRepository;
	
	/** El atributo Parametro General Repository service. */
	@Autowired
	private IParametroGeneralRepository parametroGeneralRepository;
	

	/** El atributo o variable bitacora BatchRepository Repository. */
	@Autowired
	IBitacoraBatchRepository bitacoraBatchRepository;
	
	/** El atributo Cr Pdf FilesRepository service. */
	@Autowired
	private ICrPdfFilesRepository crPdfFilesRepository;
	
	/** El atributo o variable Entidad service. */
	@Autowired
	private INodoHelper nodoHelper;
	
	/** El atributo o variable messages service. */
	@Autowired
	private IMessagesService messagesService;
	
	/** El atributo o variable in Variable Proceso Repository. */
	@Autowired
	IInVariableProcesoRepository inVariableProcesoRepository;
	
	/** El atributo o variable Programacion Proceso Repository. */
	@Autowired
	ICrProgramacionProcesoRepository crProgramacionProcesoRepository;
	
	/** El atributo o variable Programacion Proceso Repository. */
	@Autowired
	ICrDefinicionPeriocidadRepository crDefinicionPeriocidadRepository;
	
    @Autowired
    ICrConsultaRepseRepository crConsultaRepseRepository;
    
    @Autowired
	ICorreosEnviarRepository correosenviar;
    
    @Autowired
    IBPMMailSender bpmMailSender;
    
    @Autowired
	private IComposicionCorreoRepository composicionCorreoRepository;

	/** El atributo o variable path de la informacion. */
	@Value("${path.repse.data}")
    private String dataPath;
	
	/** El atributo o variable path de la imagen. */
	@Value("${path.repse.image}")
    private String imagePath;
	
	/** El atributo o url del servicio */
	@Value("${url.servicio.repse}")
    private String urlServicioRepse;

	// SP_TEMPORIZADOR_ACTIVIDADES
	/**
	 * Método que procesa la temporización de actividades en un flujo de trabajo.
	 * 
	 * El método recorre una lista de nodos que tienen una temporización activa y, 
	 * según su configuración y estado actual, los actualiza y termina. 
	 * Si un nodo cumple ciertas condiciones, el método también dispara eventos 
	 * y notificaciones por correo electrónico.
	 * 
	 * @return RetMsg Contiene el estado y mensaje de la operación, indicando éxito o error.
	 * @throws BrioBPMException Excepción personalizada para errores del flujo de trabajo.
	 * @throws ParseException Excepción lanzada al fallar el parseo de fechas.
	 */
	@Override
	public RetMsg temporizadorActivades() throws BrioBPMException, ParseException {
		
		log.info("---------------  temporizadorActivades  ------------------");
		
		// INICIALIZA CÓDIGO DE ERROR, MENSAJE Y SUGERENCIA
		RetMsg msg = RetMsg.builder().status("OK").message("").build();
		
		// DECLARO CONSTANTES PARA EL USO EN EL TEMPORIZADORs
		String cveUsuarioBriowf = Constants.CVE_USUARIO_BRIOWF;
		String cveUsuario = cveUsuarioBriowf;
		String idProceso = Constants.TEMPORIZADOR_ACTIVIDADES;
		String actividadUsuTem = Constants.ACTIVIDAD_USUARIO_TEM;
		String temporizador = Constants.TEMPORIZADOR;
		String tipoTemConInt = Constants.TIPO_TEM_CON_INT;
		String tipoNodoSigConInt = Constants.TIPO_NODO_SIG_CON_INT;
		String tipoNodoSigSinInt = Constants.TIPO_NODO_SIG_SIN_INT;
		String interrumpida = Constants.INTERRUMPIDA;
		String vencidaPorTiempo = Constants.VENCIDA_POR_TIEMPO;
		String origen = "AUTO";

		String eventoCorreo;
		String estadoActividad;
		String tipoNodoSiguiente;
		String tipoTemporizacion;
		Date fechaActual = new Date();
		String cveEntidad;
		String cveProceso;
		BigDecimal version;
		String cveInstancia;
		String cveNodo;
		BigDecimal idNodo;
		BigDecimal secuenciaNodo;
		String cveIdioma;
		String cveRol;
		String mensaje;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("America/Mexico_City"));
		
		log.error("fechaActual: {}", fechaActual);
		
		
		// DECLARA EL CURSOR QUE CONTIENE LA LISTA DE NODOS CON TEMPORIZACIÓN
		List<Object[]> nodosTemporizados = inNodoProcesoRepository.encuentraNodosTemporizados(actividadUsuTem, temporizador, fechaActual);

		log.error("NODOS TEMPORIZADOS: {}", nodosTemporizados.size());
		
		// RETORNA MENSAJE EN CASO DE NO HABER NODOS TEMPORIZADOS
		if(nodosTemporizados.isEmpty()){
			log.info("NO SE EJECUTO");
			return msg;
		}
		
		// RECORRE LA LISTA DE NODOS TEMPORIZADOS
		for(Object[] fila : nodosTemporizados) {
			
			log.info("ENTRA AL FOR");
			// OBTIENE LOS DATOS DEL NODO
			cveEntidad = (String) fila [0];
			cveProceso = (String) fila [1];
			version = (BigDecimal) fila [2];
			cveInstancia = (String) fila [3];
			cveNodo = (String) fila [4];
			idNodo = (BigDecimal) fila [5];
			secuenciaNodo = (BigDecimal) fila [6];
			cveRol = (String) fila [7];
			tipoTemporizacion = (String) fila [8];
			cveIdioma = (String) fila [9];
			
			log.info("Datos del nodo: cveEntidad={}, cveProceso={}, version={}, cveInstancia={}, cveNodo={}, idNodo={}, secuenciaNodo={}, cveRol={}, tipoTemporizacion={}, cveIdioma={}",
			        cveEntidad, cveProceso, version, cveInstancia, cveNodo, idNodo, secuenciaNodo, cveRol, tipoTemporizacion, cveIdioma);
						
			DatosAutenticacionTO session = DatosAutenticacionTO.builder()
					.cveUsuario(cveUsuario)
					.cveEntidad(cveEntidad)
					.cveLocalidad(null)
					.cveIdioma(cveIdioma)
					.build();
			
			log.info(" ACTIVIDAD USUARIO TEMPORIZADA: {} | CVE NODO: {}", actividadUsuTem, cveNodo);			
			// PROCESA TEMPORIZACIÓN DE ACTIVIDADES DE USUARIO
			if(actividadUsuTem.equals(cveNodo)) {
				
				log.error("---------  Tipo de temporización: {}", tipoTemporizacion);
				
				// DETERMIA LA SITUACIÓN A ACTUALIZAR
				if(tipoTemConInt.equals(tipoTemporizacion)) {
					// ACTUALIZA LA ACTIVIDAD A INTERRUMPIDA
					estadoActividad = interrumpida;
				} else {
					// ACTUALIZA LA ACTIVIDAD A VENCIDA POR TIEMPO
					estadoActividad = vencidaPorTiempo;
				}
				
				log.info("Estado actividad: {}", estadoActividad);

				// CAMBIA LA SITUACIÓN DEL NODO ACTUAL
				InNodoProcesoPK id = InNodoProcesoPK.builder()
						.cveEntidad(cveEntidad)
						.cveProceso(cveProceso)
						.version(version)
						.cveInstancia(cveInstancia)
						.cveNodo(cveNodo)
						.idNodo(idNodo.intValue())
						.secuenciaNodo(secuenciaNodo.intValue())
						
						.build();				
				log.info("Buscando inNodoProceso con ID: {}", id);

				log.info("--- inNodoProcesoRepository.findById(id) temporizadorActivades");
				// BUSCA LA INSTANCIA DEL NODO A ACTUAL
				Optional<InNodoProceso> inNodoProceso = inNodoProcesoRepository.findById(id);
				log.info("TERMINO DE BUSCAR POR ID LA InNodoProceso");
				// FECHA DE ACTUALIZACIÓN
				Date fechaAct = new Date();
				log.info("fechaAct = {}", fechaAct);
				// RETORNA MENSAJE EN CASO DE NO ENCONTRAR EL NODO
				if (!inNodoProceso.isPresent()) {					
					log.info("YA EXISTE ERROR DE INSTANCIA NODO");
					mensaje = messagesService.getMessage(
							session,
							idProceso,
							"YA_EXISTE_INSTANCIA_NODO",
							null);
					msg.setMessage(mensaje);
					msg.setStatus(Constants.ERROR);
					return msg;
				}
				
				// ACTUALIZA EL NODO CON LA SITUACIÓN DETERMINADA
				log.info("Actualizando nodo: estado={}, fechaBloquea={}, usuarioBloquea={}", estadoActividad, fechaAct, "Brio.Workflow");
				inNodoProceso.get().setEstado(estadoActividad);
				inNodoProceso.get().setFechaBloquea(fechaAct);
				inNodoProceso.get().setUsuarioBloquea("Brio.Workflow");
				inNodoProceso.get().setRolBloquea(null);
				inNodoProceso.get().setFechaEstadoActual(fechaAct);
				//inNodoProceso.get().setRolCreador("ROL-WORKFLOW");
				log.info("PREPARA EN IN NODO PROCESO EN JOB TEMPORIZADOR");
				
				// GUARDA EL NODO ACTUALIZADO
				inNodoProcesoRepository.saveAndFlush(inNodoProceso.get());
				log.info("GUARDADO EN IN NODO PROCESO EN JOB TEMPORIZADOR");
				log.info("Nodo guardado exitosamente en inNodoProceso");

				// RETORNA MENSAJE EN CASO DE ERROR AL GUARDAR EL NODO
				if (!estadoActividad.equals(inNodoProceso.get().getEstado())
						&& !fechaAct.equals(inNodoProceso.get().getFechaBloquea())
						&& !"Brio.Workflow".equals(inNodoProceso.get().getUsuarioBloquea())
						&& inNodoProceso.get().getUsuarioBloquea() != null
						&& !fechaAct.equals(inNodoProceso.get().getFechaEstadoActual())) {
					
					log.info("YA EXISTE ERROR DE INSTANCIA NODO");
					mensaje = messagesService.getMessage(
							session,
							idProceso,
							"YA_EXISTE_INSTANCIA_NODO",
							null);
					msg.setMessage(mensaje);
					msg.setStatus(Constants.ERROR);
					return msg;
				}
				
				// GUARDANDO ENTRADA DE LA BITÁCORA
				NodoTO nodoBitacora = NodoTO.builder()
						.cveProceso(cveProceso)
						.version(version)
						.cveInstancia(cveInstancia)
						.cveNodo(cveNodo)
						.idNodo(idNodo.intValue())
						.secuenciaNodo(secuenciaNodo.intValue())
						.build();
				
				// GENERA EVENTO BITÁCORA
				EstatusTO estatus = nodoHelper.generaEventoBitacora(session, nodoBitacora, "Interrumpir");
				
				msg.setMessage(estatus.getMensaje());
				msg.setStatus(estatus.getTipoExcepcion());

				
				log.info("Tipo de temporización: {}", tipoTemporizacion);
				// CREA LOS NODOS SIGUIENTES
				if(tipoTemConInt.equals(tipoTemporizacion)) {
					
					// PROCESA TEMPORIZADOS INCRUSTADOS
					tipoNodoSiguiente = tipoNodoSigConInt;
					eventoCorreo = "INTERRUPCION_ACTIVIDAD";
				} else {					
					// PROCESA TEMPORIZADOS SIN INTERRUPCIÓN
					tipoNodoSiguiente = tipoNodoSigSinInt;
					eventoCorreo = "TEMPORIZACION_SIN_INTERRUPCION";
				}
				
				log.info("Tipo de nodo siguiente: {}", tipoNodoSiguiente);
				log.info("Evento de correo: {}", eventoCorreo);
				log.info("---- ---- creaInstancia {} ---- ---- ", cveInstancia);
				log.info("---- ---- estadoActividad {} ---- ---- ", estadoActividad);
				
				NodoTO nodoInstancia = NodoTO.builder()
						.cveProceso(cveProceso)
						.version(version)
						.cveInstancia(cveInstancia)
						.cveNodo(cveNodo)
						.idNodo(idNodo.intValue())
						.secuenciaNodo(secuenciaNodo.intValue())
						.tipoGeneracion("SIG")
					    .tipoNodoSiguiente(tipoNodoSiguiente)
					    .cveNodoInicio(cveNodo) // clave del nodo actual
					    .idNodoInicio(idNodo.intValue()) // id del nodo actual
					    .rol(cveRol)
					    .origen("AUTO")
					    .folioMensajeEnvio(null)
					    .build();
				
				// CREA LA INSTANCIA DEL NODO SIGUIENTE
				if ("VENCIDA POR TIEMPO".equals(estadoActividad)) {
					log.info("---- La actividad fue vencida por tiempo ----");				
				} else {		
					log.info("---- temporizador ----");
					estatus = nodoHelper.creaInstancia(session, nodoInstancia);
					
					if(estatus.getTipoExcepcion().equals(Constants.ERROR)) {
						log.info("Error al crear instancia para el nodo: {} --- {} ", nodoInstancia, estatus.getMensaje());
						msg.setMessage(estatus.getMensaje());
						msg.setStatus(estatus.getTipoExcepcion());
						continue;
						
					}
				}
				
				// EN CASO DE HABER NOTIFICACIÓN POR CORREO ELECTRÓNICO, ÉSTA ES EJECUTADA
				// CREA EL MENSAJE DE CORREO PARA EL EVENTO 
				NodoTO nodoCorreo = NodoTO.builder()
						.cveProceso(cveProceso)
						.version(version)
						.cveEventoCorreo(eventoCorreo)
						.cveInstancia(cveInstancia)
						.cveNodo(cveNodo)
						.cveUsuarioDestinatario(null)
			    		.cveRolDestinatario(null)
						.idNodo(idNodo.intValue())
						.secuenciaNodo(secuenciaNodo.intValue())
					    .rol(cveRol)
					    .build();
				
				// CREA EL CORREO PARA EL EVENT
				//AR el parametro de cveUsuarioCreador se manda a null para que respete la configuracion que tiene el proceso
				estatus = nodoHelper.creaCorreoProceso(session, nodoCorreo, null, null);
				if(estatus.getTipoExcepcion().equals(Constants.ERROR)) {
					msg.setMessage(estatus.getMensaje());
					msg.setStatus(estatus.getTipoExcepcion());
					log.info("Error al crear correo para el evento: {} --- {} ", eventoCorreo, estatus.getMensaje());
					return msg;
					
				}
				
			// PROCESA TEMPORIZACIÓN DE ACTIVIDADES TEMPORIZADAS
			} else {
				
				// PROCESA TEMPORIZADOS NO INCRUSTADOS
				// TERMINA LA ACTIVIDAD TEMPORIZADA NO INCRUSTADA
				NodoTO nodoTeminar = NodoTO.builder()
						.cveProceso(cveProceso)
						.version(version)
						.cveInstancia(cveInstancia)
						.cveNodo(cveNodo)
						.idNodo(idNodo.intValue())
						.origen("AUTO")
						.secuenciaNodo(secuenciaNodo.intValue())
						.rol(cveRol)
						.build();
				log.info("Preparando para terminar actividad en nodo: {}", nodoTeminar);
				
				// TERMINA LA ACTIVIDAD TEMPORIZADA NO INCRUSTADA
				 msg = nodoHelper.terminaActividad(session, nodoTeminar);
				
				// RETORNA MENSAJE EN CASO DE ERROR AL TERMINAR EL NODO
				if(msg.getStatus().equals(Constants.ERROR)) {
					log.info("Error al terminar actividad en nodo: {} --- {} ", nodoTeminar, msg.getMessage());
					return msg;
					
					
				}
				log.info("Actividad terminada exitosamente en nodo: {}", nodoTeminar);
			}

		}
		
		// RETORNA EL MENSAJE FINAL INDICANDO ÉXITO O ERROR
		log.info("Finaliza temporizadorActivades con estado: {}", msg.getStatus());

		// RETORNA EL MENSAJE FINAL INDICANDO ÉXITO O ERROR
		return msg;
	}


	@Override
	public void depurarDocumentos() throws BrioBPMException {
		log.info(">>>>>>>>>>> INICIA PROCESO DEPURAR DOCUMENTOS <<<<<<<<<<<");
		String periodo = "PERIODO_DEPURAR_DOCUMENTOS";
		String tiempo = "TIEMPO_DEPURAR_DOCUMENTOS";
		String subproceso = "CR_PDF_FILES";

		
		// RECUPERA PARÁMETRO DE DÍAS PARA DEPURAR ELEMENTOS
		Optional<ParametroGeneral> parametroGeneralPeriodo = parametroGeneralRepository.findById(periodo);
		Optional<String> periodoOp = parametroGeneralPeriodo.map(ParametroGeneral::getValorAlfanumerico);
		
		Optional<ParametroGeneral> parametroGeneralTiempo = parametroGeneralRepository.findById(tiempo);
		
		Optional<Integer> tiempoOp = parametroGeneralTiempo.map(ParametroGeneral::getValorEntero); // Extrae el valor entero si
		LocalDate fechaAEliminar = 	sumaFechas(periodoOp.get(), tiempoOp.get());
		Date date = Date.from(fechaAEliminar.atStartOfDay(ZoneId.systemDefault()).toInstant());
		log.info(">>>>>>>>>>> Convertido a Date: {} <<<<<<<<<<<" , date);
		//RECUPERA LOS DOCUMENTOS QUE CUMPLEN CON LA FECHA DE DEPURACION
		List<CrPdfFiles> listFiles = crPdfFilesRepository.findByFechaCargaBeforeOrEqual(date);
		try {
			for (CrPdfFiles file: listFiles) {
				crPdfFilesRepository.deleteById(file.getId());
			}
		} catch(Exception e) {
			bitacora(periodo,subproceso,e.getMessage(), Constants.ERROR);
			log.error(subproceso);
		}
		
		bitacora(periodo,subproceso,"Documentos eliminados correctamente",Constants.INFORMACION);
	
	}


	private LocalDate sumaFechas(String periodo, int tiempo) {
		LocalDate hoy = LocalDate.now();
		LocalDate fecha = null;
		switch (periodo.toUpperCase()) {
        case "DAY":
            fecha = hoy.plusDays(tiempo);
            break;
        case "MONTH":
        	fecha =  hoy.plusMonths(tiempo); // Maneja correctamente meses con diferente cantidad de días
        	break;
        case "YEAR":
        	fecha =  hoy.plusYears(tiempo); // Maneja correctamente años bisiestos
        	break;
        default:
        	fecha = hoy;
    }
		return fecha;
		
	}
	
	private void bitacora(String idProcesoMensajes, String idProceso, String mensaje, String tipo ) {
		BitacoraBatchPK id = BitacoraBatchPK.builder()
				.cveProcresoBatch(idProcesoMensajes)
				.cveSubProcresoBatch(idProceso)
				.fechaMensaje(new Date())
				.build();
		
		BitacoraBatch batch = BitacoraBatch.builder()
				.id(id)
				.tipoMensaje(tipo)
				.mensajePrincipal(mensaje)
				.mensajeAuxiliar(null)
				.cveEntidad(null)
				.cveProceso(null)
				.version(null)
				.cveInstancia(null)
				.cveNodo(null)
				.idNodo(null)
				.secuenciaNodo(null)
				.build();
		
		bitacoraBatchRepository.saveAndFlush(batch);
		
	}

	
	/** 
	 * {@inheritDoc}
	 * @see com.briomax.briobpm.business.jobs.core.ITemporizador#consultaRepse(java.lang.String)
	 */
	@Override
	public void consultaRepse() throws BrioBPMException {
        // Step 1: Query variables for REPSE
        String lastRazonSocial = null;
        JsonNode lastRoot = null;
        byte[] lastImagenBytes = null;
        String lastArchivoGuardadoPath = null;
        String lastScreenshotPath = null;
        String lastNombreRazonSocial = null;
        String lastEntidad = null;
        String lastMunicipio = null;
        String lastNumeroAviso = null;
        String lastFechaAviso = null;
        String lastRegistroLocalizadoFolio = null;
        String lastVigenciaRegistro = null;
        String lastResultadoConsulta = null;
        String lastSituacionCarga = null;
        String lastServiciosOfrecidos = null;
        String eventoCorreo = "REPSE_NO_VALIDO"; 		
        List<Object[]> rawResults = inVariableProcesoRepository.findRepseConsultaVariablesRaw();
        if (rawResults == null || rawResults.isEmpty()) return;

        int i = 0; // Nodo no utilizado en este contexto
        DatosAutenticacionTO session = null;
        RepseConsultaVariablesDTO repseDTO = null;
        for (Object[] row : rawResults) {
        	 
            repseDTO = new RepseConsultaVariablesDTO(
                (String) row[0], // cveEntidad
                "FACT_SERV_CONT", // cveProceso
                (String) row[2], // cveInstancia
                (String) row[3], // rfcContratista
                (String) row[4], // numeroContrato
                (String) row[5], // razonSocialContratista
                (String) row[6]  // cveU
            );
            session = DatosAutenticacionTO.builder()
                .cveUsuario(repseDTO.getCveUsuario())
                .cveEntidad(repseDTO.getCveEntidad())
                .cveLocalidad(null)
                .cveIdioma(null)
                .build();

            String resultadoErrorConsulta = "Validación de capcha incorrecta.";
            CrConsultaRepsePK pk = new CrConsultaRepsePK();
            pk.setCveEntidad(repseDTO.getCveEntidad());
            pk.setCveProceso(repseDTO.getCveProceso());
            pk.setRfcContratista(repseDTO.getRfc());
            pk.setNumContrato(repseDTO.getNumeroContrato());
            pk.setFechaConsulta(new java.util.Date());
            CrConsultaRepse repse = CrConsultaRepse.builder()
                .id(pk)
                .razonSocial(repseDTO.getRazonSocial())
                .folio("")
                .entidadMunicipio("")
                .descripcionServicio("")
                .build();
            log.info(">>>>>>>>>>>>>>>  repseDTO razon a original {}",repseDTO.getRazonSocial()); 
            try {
                // Validar si la última consulta corresponde a la misma razón social y fue exitosa
                if (lastRazonSocial != null && lastRazonSocial.equals(repseDTO.getRazonSocial()) &&
                    "ENCONTRADO".equals(lastResultadoConsulta) && "CARGA EXITOSA".equals(lastSituacionCarga)) {
                    // Guardar los datos obtenidos en la consulta previa
                    repse = CrConsultaRepse.builder()
                        .id(pk)
                        .razonSocial(lastNombreRazonSocial)
                        .folio(lastRegistroLocalizadoFolio)
                        .entidadMunicipio((lastEntidad != null ? lastEntidad : "") + "/" + (lastMunicipio != null ? lastMunicipio : ""))
                        .avisoFechaRegistro(lastFechaAviso)
                        .descripcionServicio(lastServiciosOfrecidos)
                        .vigenciaRegistro(lastVigenciaRegistro)
                        .imagenConsulta(lastImagenBytes)
                        .resultadoConsulta(lastResultadoConsulta)
                        .situacionCarga(lastSituacionCarga)
                        .build();
                    crConsultaRepseRepository.saveAndFlush(repse);
                    continue;
                }
                // Limpiar razonSocial: quitar puntos y comas, y eliminar el último legal ending
                String razonSocialClean = repseDTO.getRazonSocial();
                // Remove all commas
                razonSocialClean = razonSocialClean.replace(",", "");
                // Remove legal ending at the end (case-insensitive)
                razonSocialClean = razonSocialClean.replaceFirst("(?i),?\\s*(S\\. DE R\\.L\\. DE C\\.V\\.|S\\.A\\. DE C\\.V\\.|S\\. DE R\\.L\\.|S\\.C\\.)\\s*$", "");
                // Remove trailing spaces
                // razonSocialClean = razonSocialClean.replaceFirst("\\s+$", "");
                log.info("@@@@@@@@@ 1 razonSocialClean a enviar @@@@@@@@@ {}", razonSocialClean);
                
                //Thread.sleep(12000);
                log.info("--------------  Esperando 12 seg para enviar a consulta Repse Por RazonSocial -------------- ");
                String jsonResponse = consultaRepsePorCurl(razonSocialClean, Boolean.TRUE); // headless = false
                log.info(">>>>>>>>>>>>>>> Respuesta del servicio REPSE: >>>>>>>>>>>>>>>{}", jsonResponse);
                /*if (jsonResponse != null && jsonResponse.contains(resultadoErrorConsulta)) {
                    log.error(">>>>>>>>>>>>>>> Error en consulta REPSE para razonSocial {}: {}", repseDTO.getRazonSocial(), resultadoErrorConsulta);
                    continue;
                }*/
                ObjectMapper mapper = new ObjectMapper();
                JsonNode root = mapper.readTree(jsonResponse);
                boolean error = !root.path("success").asBoolean();
                String mensajeError = root.path("mensaje").asText("");
                log.info(">>>>>>>>>>>>>>> Valor de error: {} y mensajeError: {}", error, mensajeError);
                if (error && "No hay datos.".equals(mensajeError)) {
                    log.info(">>>>>>>>>>>>>>> Error en respuesta REPSE: {}", mensajeError);
                    log.info(">>>>>>>>>>>>>>> No hay datos para la razón social: {}. No se repite la consulta.", repseDTO.getRazonSocial());
                    repse.setResultadoConsulta("NO ENCONTRADO");
                    repse.setSituacionCarga("NOTIFICACION");
                    crConsultaRepseRepository.save(repse);      
                    // Insertar en notificaciones de usuario (pendiente)
                    //EstatusTO estatus = this.creaCorreo(session, repseDTO.getCveProceso(), "ACTIVIDAD-USUARIO", repseDTO.getCveInstancia() , eventoCorreo);
                    continue;
                } else if (error) {                                      
                    int length = razonSocialClean.length();
                    int twoThirdsLength = (int) Math.ceil((2.0 * length) / 3.0);
                    String twoThirds = razonSocialClean.substring(0, twoThirdsLength);
                    log.info("@@@@@@@@@  2 Reintentando consulta REPSE para razonSocialClean: {} @@@@@@@@@", twoThirds);
                    Thread.sleep(20000); // Esperar 60 segundos antes de reintentar 
                    log.info("--------------  Esperando 60 seg para enviar a consulta Repse Por RazonSocial -------------- ");
                    jsonResponse = consultaRepsePorCurl(twoThirds, Boolean.TRUE); // headless = false
                    log.info(">>>>>>>>>>>>>>> Respuesta del servicio REPSE (segunda consulta): {}", jsonResponse);
                    root = mapper.readTree(jsonResponse);
                    error = !root.path("success").asBoolean();
                    mensajeError = root.path("mensaje").asText("");
                }
                if (error) {
                    log.error(">>>>>>>>>>>>>>> Error en respuesta REPSE (segunda consulta): {}", mensajeError);
                    repse.setResultadoConsulta("NO ENCONTRADO");
                    repse.setSituacionCarga("CARGA NO EXITOSA");
                    crConsultaRepseRepository.saveAndFlush(repse);    
                    this.creaCorreo(session, repseDTO.getCveProceso(), "ACTIVIDAD-USUARIO", repseDTO.getCveInstancia() , eventoCorreo, "");
                    continue;
                }
                // 2. Parse JSON response (only if error == false)
                
                parseAndSaveRepseJson(session, pk, root,"ACTIVIDAD-USUARIO", repseDTO.getCveInstancia() );
                lastRazonSocial = repseDTO.getRazonSocial();
                lastRoot = root;
                lastImagenBytes = null;
                //lastArchivoGuardadoPath = archivoGuardadoPath;
                lastScreenshotPath = null;
                lastNombreRazonSocial = null;
                lastEntidad = null;
                lastMunicipio = null;
                lastNumeroAviso = null;
                lastFechaAviso = null;
                lastRegistroLocalizadoFolio = null;
                lastVigenciaRegistro = null;
                lastServiciosOfrecidos = null;
                lastResultadoConsulta = "ENCONTRADO";
                lastSituacionCarga = "CARGA EXITOSA";
                 // Esperar 5 segundos antes de reintentar
            } catch (Exception e) {
                log.error(">>>>>>>>>>>>>>> Error en consultaRepse para razonSocial {}: {}",  repseDTO.getRazonSocial(), e.getMessage());
                continue;
            }
        }
    }


	/**
     * Realiza una consulta HTTP POST al servicio REPSE con el parámetro razonSocial y retorna la respuesta JSON como String.
     * @param razonSocial La razón social a consultar.
     * @return La respuesta JSON del servicio REPSE como String.
     */
    public String consultaRepsePorRazonSocial(String razonSocial) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);
            RestTemplate restTemplate = new RestTemplate();
            Map<String, Object> requestBody = new HashMap<>();
            String razonSocialClean = razonSocial.replace(".", "").replace(",", "");
            requestBody.put("razonSocial", razonSocialClean);
            Map<String, Object> opciones = new HashMap<>();
            opciones.put("headless", Boolean.TRUE); // Cambia a Boolean.FALSE si lo necesitas
            opciones.put("navegacionExtra", Boolean.TRUE); // Cambia a Boolean.FALSE si lo necesitas
            requestBody.put("opciones", opciones);
            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);
            log.info("--------------  URL Repse Por RazonSocial: "+ getUrlServicioRepse());
            ResponseEntity<String> response = restTemplate.postForEntity(getUrlServicioRepse(), entity, String.class);
            return response.getBody();
        } catch (Exception e) {
            log.error("Error en consultaRepsePorRazonSocial para razonSocial {}: {}", razonSocial, e.getMessage());
            return null;
        }
    }


    /**
     * Consulta el servicio REPSE usando un comando CURL con payload en archivo temporal para evitar problemas de escape.
     * @param razonSocial La razón social a consultar.
     * @param headless Valor booleano para la opción headless.
     * @return La respuesta JSON del servicio REPSE como String, o null si hay error.
     */
    @Override
    public String consultaRepsePorCurl(String razonSocial, boolean headless) {
        // Use the correct endpoint for REPSE
        String url = getUrlServicioRepse();
        log.info("-------------- EVL URL Repse Por CURL: --------  " + url);
        String payload = String.format("{\"razonSocial\":\"%s\",\"options\":{\"headless\":%s}}", razonSocial.replace("\"", "\\\""), headless);
        java.io.File tempFile = null;
        try {
            tempFile = java.io.File.createTempFile("repse_payload", ".json");
            java.nio.file.Files.write(tempFile.toPath(), payload.getBytes(java.nio.charset.StandardCharsets.UTF_8));
            String os = System.getProperty("os.name").toLowerCase();
            String command;
            if (os.contains("win")) {
                command = String.format("curl -X POST \"%s\" -H \"Content-Type: application/json\" -d @\"%s\"", url, tempFile.getAbsolutePath());
            } else {
                command = String.format("curl -X POST '%s' -H 'Content-Type: application/json' -d @%s", url, tempFile.getAbsolutePath());
            }
            
            ProcessBuilder pb;
            if (os.contains("win")) {
                String[] cmd = {"cmd.exe", "/c", command};
                pb = new ProcessBuilder(cmd);
                log.info("-------------- Comando WIN CURL: --------  " + command);
            } else {
                String[] cmd = {"/bin/sh", "-c", command};
                pb = new ProcessBuilder(cmd);
                log.info("-------------- Comando LINUX CURL: --------  " + command);
            }
            pb.redirectErrorStream(true);
            Process process = pb.start();
            java.io.InputStream is = process.getInputStream();
            java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
            String output = s.hasNext() ? s.next() : "";
            log.info("-------------- Comando output: --------  " + output);
            s.close();
            int exitCode = process.waitFor();
            if (exitCode != 0) {
                log.error("Error ejecutando CURL. Código de salida: {}. Output: {}", exitCode, output);
                return null;
            }
            // Robust JSON extraction: find first '{' and last '}'
            int jsonStart = output.indexOf('{');
            int jsonEnd = output.lastIndexOf('}');
            if (jsonStart != -1 && jsonEnd != -1 && jsonEnd > jsonStart) {
                String json = output.substring(jsonStart, jsonEnd + 1);
                log.info("-------------- Servicio respuesta json: --------  " + json);
                return json;
            } else {
                log.error("No se encontró JSON válido en la respuesta del servicio REPSE. Output: {}", output);
                return null;
            }
        } catch (Exception e) {
            log.error("Error ejecutando consultaRepsePorCurl: {}", e.getMessage());
            return null;
        } finally {
            if (tempFile != null && tempFile.exists()) {
                tempFile.delete();
            }
        }
    }
    
    public EstatusTO creaCorreo(DatosAutenticacionTO session, String cveProceso, 
    		String cveNodo, String cveInstancia,String eventoCorreo,
    		String pathimage) throws BrioBPMException {
    	
    	NodoTO nodo = NodoTO.builder()
				.cveProceso(cveProceso)
				.version(new BigDecimal(1.0))
				.cveEventoCorreo(eventoCorreo)
				.cveUsuarioDestinatario(session.getCveUsuario())
				.cveRolDestinatario(null)
				.cveNodo(cveNodo)
				.idNodo(2)
				.ocurrencia(1)
				.cveInstancia(cveInstancia)
				.secuenciaNodo(1)
				.ocurrencia(1)	
				.pathImage(pathimage)
			    .rol(null)
				.build();
    	EstatusTO estatus = null;
		try {
			estatus = nodoHelper.creaCorreoProceso(session, nodo, null, session.getCveUsuario());

		} catch (BrioBPMException e) {
			log.error("Error al crear correo: {}", e.getMessage());
		} catch (ParseException e) {
			log.error("Error de parseo al crear correo: {}", e.getMessage());
		}
    	
		log.info("Mensaje: {}", estatus.getMensaje());
		log.info("TipoExcepcion: {}", estatus.getTipoExcepcion());
	
    	return estatus;							
    }
    
    /**
     * Parsea el JSON de respuesta REPSE y guarda el resultado en la base de datos.
     * @param pk Clave primaria para CrConsultaRepse
     * @param root Nodo raíz del JSON de respuesta
     */
    @Override
	public void parseAndSaveRepseJson(DatosAutenticacionTO session, CrConsultaRepsePK pk, JsonNode root, String cveNodo, String cveInstancia) {
        // Mapear los campos correctamente según la estructura del JSON
        String nombreRazonSocial = "";
        String folio = "";
        String entidadMunicipio = "";
        String avisoRegistro = "";
        String vigenciaRegistro = "";
        String serviciosOfrecidos = "";
        String fechaConsulta = "";
        String error = "";
    	try {
            boolean success = root.path("success").asBoolean();
            log.error(">>>>>>>>>>>>>>>  entra en parseAndSaveRepseJson con success: {}", success);
            String eventoCorreo = "REPSE_NO_VALIDO";
            JsonNode dataNode = root.path("data");
            // Obtener la URL del screenshot
            String screenshotUrl = root.path("screenshotUrl").asText("");
            String timestamp = root.path("timestamp").asText("");
            
            log.error(">>>>>>>>>>>>>>>  Screenshot URL: {}", screenshotUrl);
            
            EstatusTO estatus = null;
            byte[] imagenBytes = null;
            if (screenshotUrl != null && !screenshotUrl.isEmpty() && !"null".equals(screenshotUrl)) {
                try {
                    java.nio.file.Path imagePathObj = java.nio.file.Paths.get(screenshotUrl);
                    if (java.nio.file.Files.exists(imagePathObj)) {
                        imagenBytes = java.nio.file.Files.readAllBytes(imagePathObj);
                    } else {
                        log.error(">>>>>>>>>>>>>>> No se encontró la imagen en la ruta: {}", screenshotUrl);
                    }
                } catch (Exception ex) {
                    log.error(">>>>>>>>>>>>>>>  Error al leer la imagen en {}: {}", screenshotUrl, ex.getMessage());
                }
            }
            if (success) {
                eventoCorreo = "REPSE_VALIDO";
             // Acceder al nodo "data" que contiene la información principal
                
                // Mapear los campos correctamente según la estructura del JSON
                 nombreRazonSocial = dataNode.path("razonSocial").asText("");
                 folio = dataNode.path("folio").asText("");
                 entidadMunicipio = dataNode.path("entidadMunicipio").asText("");
                 avisoRegistro = dataNode.path("numeroRegistro").asText("") + " / " + dataNode.path("fechaRegistro").asText("");
                 vigenciaRegistro = dataNode.path("vigenciaRegistro").asText("");
                 serviciosOfrecidos = dataNode.path("serviciosEspecializados").asText("");
                 fechaConsulta = dataNode.path("fechaConsulta").asText("");
                log.error(">>>>>>>>>>>>>>>  Fecha de consulta REPSE: {}", fechaConsulta);
 
                CrConsultaRepse repse = CrConsultaRepse.builder()
                    .id(pk)
                    .razonSocial(nombreRazonSocial)
                    .folio(folio)
                    .entidadMunicipio(entidadMunicipio)
                    .avisoFechaRegistro(avisoRegistro)
                    .descripcionServicio(serviciosOfrecidos)
                    .vigenciaRegistro(vigenciaRegistro)
                    .imagenConsulta(imagenBytes)
                    .resultadoConsulta("ENCONTRADO")
                    .situacionCarga("CARGA EXITOSA")
                    .build(); 
                if (!"".equals(nombreRazonSocial) || !nombreRazonSocial.isEmpty()) {
                	crConsultaRepseRepository.saveAndFlush(repse);                	
                	log.error("Mensaje: {}", estatus.getTipoExcepcion());               	
                
                log.error(">>>>>>>>>>>>>>>  REPSE EXITOSO: {}", repse);
                }
            }  
    		/*if ("".equals(fechaConsulta) || fechaConsulta.isEmpty()) {
    			fechaConsulta = timestamp.substring(0, 10); // Asigna fecha del timestamp si fechaConsulta está vacía
    			log.error(">>>>>>>>>>>>>>>  Fecha de consulta REPSE asignada por timestamp: {}", fechaConsulta);
    		}*/
            estatus = creaCorreo(session, pk.getCveProceso(), cveNodo, cveInstancia, eventoCorreo, screenshotUrl);
        	
        	List<LeeCorreosPorEnviar> datos = leeCorreosRepse();
        	datos.forEach(cor -> {
				try {	
					boolean successffully  = bpmMailSender.enviarCorreoConImagen(						
							cor.getPara(),								
							cor.getAsunto(),
							cor.getMensaje(),
							screenshotUrl 
							);
					ComposicionCorreo updateComposicion = new ComposicionCorreo();
					ComposicionCorreoPK composicionCorreoPK = new ComposicionCorreoPK();
					composicionCorreoPK.setCveProceso(cor.getId().getCveProceso());
					    composicionCorreoPK.setCveEntidad(pk.getCveEntidad());
					    composicionCorreoPK.setCveProceso(pk.getCveProceso());    						    
						composicionCorreoPK.setVersion(cor.getId().getVersion());
						composicionCorreoPK.setNumeroCorreo(cor.getId().getNumCorreo().intValue());
						updateComposicion.setId(composicionCorreoPK);
						if(successffully) {
							Optional<ComposicionCorreo> compcorreo = composicionCorreoRepository.findById(composicionCorreoPK);
							updateComposicion = compcorreo.get();
							updateComposicion.setSituacionCorreo("ENVIADO");
							composicionCorreoRepository.saveAndFlush(updateComposicion);
						}
				} catch (BrioBPMException | MessagingException e) {
					log.error("Error al enviar correo REPSE: {}", e.getMessage());	
				}});
        } catch (Exception e) {
            log.error("Error al parsear y guardar REPSE JSON: {}", e.getMessage());
           // throw e;
        }
    }


	public String getDataPath() {
		return dataPath;
	}


	public void setDataPath(String dataPath) {
		this.dataPath = dataPath;
	}


	public String getUrlServicioRepse() {
		return urlServicioRepse;
	}


	public void setUrlServicioRepse(String urlServicioRepse) {
		this.urlServicioRepse = urlServicioRepse;
	}


	public String getImagePath() {
		return imagePath;
	}


	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	public List<LeeCorreosPorEnviar> leeCorreosRepse() throws BrioBPMException {
		List<LeeCorreosPorEnviar> datosCorreo = new ArrayList<LeeCorreosPorEnviar>();
		//llama SP_LEE_CORREOS_POR_ENVIAR
		List<Object > listaDAT =correosenviar.regresaEstatusRepse();

	   if(!listaDAT.isEmpty()) {
		   
	   
			listaDAT.forEach(item -> {
				Date fecha = new Date();			
				Object[] row = (Object[]) item;
				LeeCorreosPorEnviar itemSelected = new LeeCorreosPorEnviar();
				LeeCorreosPorEnviarPK pk = new LeeCorreosPorEnviarPK();
				pk.setCveProceso(((String) Arrays.asList(row).get(MagicNumber.ZERO.getValue())));
				pk.setVersion ((BigDecimal) Arrays.asList(row).get(MagicNumber.ONE.getValue())) ;
				pk.setNumCorreo((((BigDecimal) Arrays.asList(row).get(MagicNumber.TWO.getValue())).longValue()));
				itemSelected.setId(pk);
				itemSelected.setPara(((String) Arrays.asList(row).get(MagicNumber.THREE.getValue())));
				itemSelected.setConCopiaPara(((String) Arrays.asList(row).get(MagicNumber.FOUR.getValue())));
				itemSelected.setAsunto(((String) Arrays.asList(row).get(MagicNumber.FIVE.getValue())));
				itemSelected.setMensaje(((String) Arrays.asList(row).get(MagicNumber.SIX.getValue())));
				fecha = ((Date) Arrays.asList(row).get(MagicNumber.SEVEN.getValue()));
				/*if(fecha.after(new Date(fechaConsulta))) {
					datosCorreo.add(itemSelected);
				}*/
				
				}
			);	
	   }
			return  datosCorreo;
		
	}


	@Override
	public RetMsg activadesPendientes() throws BrioBPMException , ParseException {
		
		log.info("---------------  temporizadorActivades  ------------------");
		
		// INICIALIZA CÓDIGO DE ERROR, MENSAJE Y SUGERENCIA
		RetMsg msg = RetMsg.builder().status("OK").message("").build();
		
		// DECLARO CONSTANTES PARA EL USO EN EL TEMPORIZADORs
		String cveUsuarioBriowf = Constants.CVE_USUARIO_BRIOWF;
		String cveUsuario = cveUsuarioBriowf;
		String actividadUsuTem = Constants.ACTIVIDAD_USUARIO_TEM;


		String eventoCorreo = Constants.ACTIVIDAD_PENDIENTE;

		Date fechaActual = new Date();
		String cveEntidad;
		String cveProceso;
		BigDecimal version;
		String cveInstancia;
		String cveNodo;
		BigDecimal idNodo;
		BigDecimal secuenciaNodo;
		String cveIdioma;
		String cveRol;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("America/Mexico_City"));
		
		log.error("fechaActual: {}", fechaActual);
		
		
		// DECLARA EL CURSOR QUE CONTIENE LA LISTA DE NODOS CON TEMPORIZACIÓN
		List<Object[]> nodosActividadPendiente = inNodoProcesoRepository.encuentraActividadPendiente(fechaActual);
				

		log.error("ACTIVIDAD PENDIENTE: {}", nodosActividadPendiente.size());
		
		// RETORNA MENSAJE EN CASO DE NO HABER NODOS TEMPORIZADOS
		if(nodosActividadPendiente.isEmpty()){
			log.info("NO SE EJECUTO");
			return msg;
		}
		
		// RECORRE LA LISTA DE NODOS TEMPORIZADOS
		for(Object[] fila : nodosActividadPendiente)
			try {
				
					log.info("ENTRA AL FOR");
					// OBTIENE LOS DATOS DEL NODO
					cveEntidad = (String) fila [0];
					cveProceso = (String) fila [1];
					version = (BigDecimal) fila [2];
					cveInstancia = (String) fila [3];
					cveNodo = (String) fila [4];
					idNodo = (BigDecimal) fila [5];
					secuenciaNodo = (BigDecimal) fila [6];
					cveRol = (String) fila [7];
					String tipoTemporizacion = (String) fila [8];
					cveIdioma = (String) fila [9];
					
					log.info("Datos del nodo: cveEntidad={}, cveProceso={}, version={}, cveInstancia={}, cveNodo={}, idNodo={}, secuenciaNodo={}, cveRol={}, tipoTemporizacion={}, cveIdioma={}",
					        cveEntidad, cveProceso, version, cveInstancia, cveNodo, idNodo, secuenciaNodo, cveRol, tipoTemporizacion, cveIdioma);
								
					DatosAutenticacionTO session = DatosAutenticacionTO.builder()
							.cveUsuario(cveUsuario)
							.cveEntidad(cveEntidad)
							.cveLocalidad(null)
							.cveIdioma(cveIdioma)
							.build();
					
					log.info(" ACTIVIDAD USUARIO : {} | CVE NODO: {}", actividadUsuTem, cveNodo);
					// EN CASO DE HABER NOTIFICACIÓN POR CORREO ELECTRÓNICO, ÉSTA ES EJECUTADA
					// CREA EL MENSAJE DE CORREO PARA EL EVENTO 
					NodoTO nodoCorreo = NodoTO.builder()
							.cveProceso(cveProceso)
							.version(version)
							.cveEventoCorreo(eventoCorreo)
							.cveInstancia(cveInstancia)
							.cveNodo(cveNodo)
							.cveUsuarioDestinatario(null)
				    		.cveRolDestinatario(null)
							.idNodo(idNodo.intValue())
							.secuenciaNodo(secuenciaNodo.intValue())
						    .rol(cveRol)
						    .build();
					
					// CREA EL CORREO PARA EL EVENT
					//AR el parametro de cveUsuarioCreador se manda a null para que respete la configuracion que tiene el proceso
					EstatusTO estatus = nodoHelper.creaCorreoProceso(session, nodoCorreo, null, null);
					if(estatus.getTipoExcepcion().equals(Constants.ERROR)) {
						msg.setMessage(estatus.getMensaje());
						msg.setStatus(estatus.getTipoExcepcion());
						log.info("Error al crear correo para el evento: {} --- {} ", eventoCorreo, estatus.getMensaje());
						return msg;
						
					}

				
			} catch (BrioBPMException | ParseException e) {
				throw new BrioBPMException("Error al procesar nodo con idNodo: " + fila[5] + " - " + e.getMessage(), e);
			}
		
		return msg;
	}


}
