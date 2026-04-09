/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */


package com.briomax.briobpm.business.service;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.briomax.briobpm.business.helpers.base.ICorreosHelper;
import com.briomax.briobpm.business.helpers.base.INodoHelper;
import com.briomax.briobpm.business.service.catalogs.core.IMessagesService;
import com.briomax.briobpm.business.service.core.IBPMMailSender;
import com.briomax.briobpm.business.service.core.IEmailsService;
import com.briomax.briobpm.common.core.Constants;
import com.briomax.briobpm.common.core.DAORet;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.persistence.dao.base.IEmailsDAO;
import com.briomax.briobpm.persistence.entity.CrDefinicionPeriocidad;
import com.briomax.briobpm.persistence.entity.CrDestinatarioCorreo;
import com.briomax.briobpm.persistence.entity.CrNotificacion;
import com.briomax.briobpm.persistence.entity.CrProgramacionProceso;
import com.briomax.briobpm.persistence.entity.HusoHorario;
import com.briomax.briobpm.persistence.entity.InVariableProceso;
import com.briomax.briobpm.persistence.entity.LocalidadEntidad;
import com.briomax.briobpm.persistence.entity.LocalidadEntidadPK;
import com.briomax.briobpm.persistence.entity.Notificacion;
import com.briomax.briobpm.persistence.entity.ParametroGeneral;
import com.briomax.briobpm.persistence.entity.Usuario;
import com.briomax.briobpm.persistence.entity.UsuarioPK;
import com.briomax.briobpm.persistence.entity.namedquery.LeeCorreosPorEnviar;
import com.briomax.briobpm.persistence.repository.ICrDefinicionPeriocidadRepository;
import com.briomax.briobpm.persistence.repository.ICrDestinatarioCorreoRepository;
import com.briomax.briobpm.persistence.repository.ICrNotificacionRepository;
import com.briomax.briobpm.persistence.repository.ICrProgramacionProcesoRepository;
import com.briomax.briobpm.persistence.repository.IInVariableProcesoRepository;
import com.briomax.briobpm.persistence.repository.ILocalidadEntidadRepository;
import com.briomax.briobpm.persistence.repository.INotificacionRepository;
import com.briomax.briobpm.persistence.repository.IParametroGeneralRepository;
import com.briomax.briobpm.persistence.repository.IUsuarioRepository;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;
import com.briomax.briobpm.transferobjects.in.DestinatarioTO;
import com.briomax.briobpm.transferobjects.in.EstatusVariablesTO;
import com.briomax.briobpm.transferobjects.in.NodoTO;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Class EmailsService.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Apr 13, 2020 10:30:45 AM Modificaciones:
 * @since JDK 1.8
 */
@Service
@Transactional
@Slf4j
public class EmailsService implements IEmailsService {

	/** El atributo o variable emails dao. */
	@Autowired
	private IEmailsDAO emailsDao;

	/** El atributo o variable emails sender. */
	@Autowired
	private IBPMMailSender emailsSender;
	
	/** El atributo o variable  corro helper. */
	@Autowired
    private ICorreosHelper correoHelper;
     
	/** El atributo o variable  nodo helper. */
	@Autowired
    private INodoHelper nodoHelper;
	
	/** El atributo o variable Parametro General Repository. */
	@Autowired
    private IParametroGeneralRepository parametroRepository;

    @Autowired
    private ICrProgramacionProcesoRepository programacionProcesoRepository; // Repositorio para operaciones con la entidad CRProgramacionProceso

    @Autowired
    private ICrNotificacionRepository notificacionRepository; // Repositorio para operaciones con la entidad CRNotificacion

    @Autowired
    private ICrDestinatarioCorreoRepository destinatarioCorreoRepository; // Repositorio para operaciones con la entidad CRDestinatarioCorreo

    @Autowired
    private IUsuarioRepository usuarioRepository; // Repositorio para operaciones con la entidad Usuario
    
    @Autowired
    private IMessagesService messagesService;
    
    @Autowired
    private IBPMMailSender bPMMailSender;
    
    @Autowired
    private ILocalidadEntidadRepository localidadRepository;
    
	/** El atributo o variable Notificacio. */
	@Autowired
	private  INotificacionRepository notificacionUsuarioRepository;

	/** El atributo o variable definicion repository. */
	@Autowired
	private ICrDefinicionPeriocidadRepository definicionRepository;
	
	@Autowired
	private IInVariableProcesoRepository variableProcesoRepository;
	
	/** El atributo o variable in nodo helper. */
	@Autowired
	private INodoHelper iNodoHelper;
    
	
	/**
	 * Crear una nueva instancia del objeto emails service.
	 */
	public EmailsService() {
	}

	/** 
	 * {@inheritDoc}
	 * @see com.briomax.briobpm.business.service.core.IEmailsService#sendEmails(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void sendEmails(String cveUsuario, String cveEntidad, String cveLocalidad, String cveIdioma)
		throws BrioBPMException {
		log.info("\t\tSEND EMAILS SERVICE {} {} {} {}", cveUsuario, cveEntidad, cveLocalidad, cveIdioma);
		final DAORet<List<LeeCorreosPorEnviar>, RetMsg> metadata = leeCorreosEnviar();
		log.info("-----------------------> LEE CORREOS POR ENVIAR");
		log.trace("\t\t\t{}", metadata);
		
		// Evalua si el resultado de la lectura de corres al enviar fue exitosa
		if (metadata.getMeta().getStatus().equalsIgnoreCase("OK")) {
			
			// Itera el contenido de la lista de correos por enviar
			for (LeeCorreosPorEnviar item : metadata.getContent()) {
				String[] to = null;
				
				if (StringUtils.isNotBlank(StringUtils.deleteWhitespace(item.getPara()))) {
					to = item.getPara().split("\\|");
				}
				if (to != null && to.length > 0) {
					log.debug("\t\t\tSEND EMAIL : to:{} cc:{} subject:{} message:{}", item.getPara(),
						item.getConCopiaPara(), item.getAsunto(), item.getMensaje());
					String[] cc = null;
					if (StringUtils.isNotBlank(StringUtils.deleteWhitespace(item.getConCopiaPara()))) {
						cc = item.getConCopiaPara().split("\\|");	
					}
					try {
						log.debug("\t\t\t Enviando ...");
						boolean successfully =
								emailsSender.sendHtmlMailMessage(to, cc, item.getAsunto(), item.getMensaje());
							log.debug("\t\t\t Enviado ... {}", successfully);
							if (successfully) {
								log.trace("\t\t\t\tACTUALIZAR SITUACION CORREO");
								
								// desacople SP_ACTUALIZA_SITUACION_CORREO
//								RetMsg msg = emailsDao.actualizaSituacionCorreo(cveUsuario, cveEntidad, cveLocalidad, cveIdioma,
//									item.getId().getCveProceso(), item.getId().getVersion(), item.getId().getNumCorreo(),
//									"ENVIADO");
								DatosAutenticacionTO session = DatosAutenticacionTO.builder()
										.cveUsuario(cveUsuario)
										.cveEntidad(cveEntidad)
										.cveLocalidad(cveLocalidad)
										.cveIdioma(cveIdioma)
										.build();
								RetMsg msg = messagesService.actualizaSItuacionCorreo(session,
										item.getId().getCveProceso(),
										item.getId().getVersion(),
										item.getId().getNumCorreo().intValue(),
										"ENVIADO");
								log.trace("\t\t\t\tSITUACION CORREO{}", msg);
							}
					} catch (Exception e) {
						log.error("\t\t\t ERROR AL ENVIAR CORREO: \\n\\n\\n", e);
					}
					
				} else {
					log.warn("\t\t\tSIN DESTINATARIO(S) # to:{} cc:{} subject:{} message:{}", item.getPara(),
						item.getConCopiaPara(), item.getAsunto(), item.getMensaje());
				}
			}
		}
	}
	
	
	//SP_LEE_CORREOS_POR_ENVIAR
	@Override
	public DAORet<List<LeeCorreosPorEnviar>, RetMsg> leeCorreosEnviar() throws BrioBPMException {
		
		// Crea un objeto de retorno
		RetMsg retMsg = new RetMsg ();
		retMsg.setStatus("OK");
		
		// Llama al helper para leer correos
		List<LeeCorreosPorEnviar> listaCorreos = correoHelper.leeCorreos();
		
		// Verifica si la lista de correos es nula o vacía
		return new DAORet<List<LeeCorreosPorEnviar>, RetMsg>(listaCorreos,retMsg);
	}

	
	// Método principal que ejecuta el proceso de notificación
	@Override
	public void notifiacionUsuarios(String cveEntidad, String cveLocalidad, String cveIdioma) {
	    log.debug("NOTIFICACION USUARIOS");

	    // Obtener la zona horaria según la entidad y localidad proporcionadas
	    Integer horas = obtenerZonaHoraria(cveEntidad, cveLocalidad);

	    // Calcular la fecha actual en la zona horaria correspondiente
	    ZoneId zoneIdDesplazamiento = ZoneId.ofOffset(Constants.UTC, ZoneOffset.ofHours(horas));
	    
	    // Obtener la fecha actual en la zona horaria correspondiente
	    ZonedDateTime fechaActualConDesplazamiento = ZonedDateTime.now(zoneIdDesplazamiento);
	    
	    // Obtener la fecha actual en formato LocalDate
	    LocalDate fechaActual = fechaActualConDesplazamiento.toLocalDate();
    
	    // Recuperar procesos programados que cumplen con los criterios
	    //SVM ajustar
	    List<CrProgramacionProceso> procesosAntes = programacionProcesoRepository.encuentraFechaAntesNotificacion(
	    		cveEntidad, cveLocalidad, cveIdioma, Constants.PROGRAMADO, fechaActual, "NORMAL");

	    log.debug("PROCESOS ANTES DE LA FECHA TAMAÑO: " + procesosAntes.size());

	    String nomProcesoPeriodico = "";
	    int numPeriodo = 0;
	    String tipoPeriodo = "";
	    // Procesar cada proceso recuperado
	    for (CrProgramacionProceso proceso : procesosAntes) {
	    	
	    	nomProcesoPeriodico = proceso.getCrProcesoPeriodico().getDescripcion();
	    	numPeriodo = proceso.getCrPeriodicidad().getCadaNPeriodos();
	    	tipoPeriodo = proceso.getCrPeriodicidad().getPeriodoTiempo();
	    	
		    List<CrNotificacion> notificaciones = notificacionRepository.encuentraNotificaciones(
		            proceso.getId().getCveEntidad(),
		            proceso.getId().getCveLocalidad(),
		            proceso.getId().getCveIdioma(),
		            proceso.getId().getCveProcesoPeriodico(),
		            proceso.getCrDefinicionPeriocidad().getSecDefinicionAntes(),
		            proceso.getId().getRfc(),
		            proceso.getId().getContrato());

		    log.debug("notificaciones TAMAÑO: " + notificaciones.size());

		    // Si existen notificaciones, procesarlas
		    if (!notificaciones.isEmpty()) {
		        log.debug("EXISTEN NOTIFICACIONES ");
		        // Iterar sobre cada notificación
		        for (CrNotificacion notificacion : notificaciones) {
		        	// Procesar cada notificación
		            procesarNotificacion(notificacion, proceso, horas, nomProcesoPeriodico, numPeriodo, tipoPeriodo, "NORMAL");
		        }
		    }

	    }
	    
	    //proceso programados actuales
	    List<CrProgramacionProceso> procesosActuales = programacionProcesoRepository.encuentraFechaNotificacion(
	    		cveEntidad, cveLocalidad, cveIdioma, Constants.PROGRAMADO, fechaActual, "NORMAL");

	    log.debug("PROCESOS ANTES DE LA FECHA TAMAÑO: " + procesosAntes.size());

	    // Procesar cada proceso recuperado
	    for (CrProgramacionProceso proceso : procesosActuales) {
	    	nomProcesoPeriodico = proceso.getCrProcesoPeriodico().getDescripcion();
	    	numPeriodo = proceso.getCrPeriodicidad().getCadaNPeriodos();
	    	tipoPeriodo = proceso.getCrPeriodicidad().getPeriodoTiempo();
	    	
		    List<CrNotificacion> notificaciones = notificacionRepository.encuentraNotificaciones(
		            proceso.getId().getCveEntidad(),
		            proceso.getId().getCveLocalidad(),
		            proceso.getId().getCveIdioma(),
		            proceso.getId().getCveProcesoPeriodico(),
		            proceso.getCrDefinicionPeriocidad().getSecDefinicionDia(),
		            proceso.getId().getRfc(),
		            proceso.getId().getContrato());

		    log.debug("notificaciones TAMAÑO: " + notificaciones.size());
		    // Si existen notificaciones, procesarlas
		    if (!notificaciones.isEmpty()) {
		        log.debug("EXISTEN NOTIFICACIONES ");
		        
		        // Iterar sobre cada notificación
		        for (CrNotificacion notificacion : notificaciones) {
		        	// Procesar cada notificación
		            procesarNotificacion(notificacion, proceso, horas, nomProcesoPeriodico, numPeriodo, tipoPeriodo, "NORMAL");
		        }
		    }

	    } 
	    
	    //proceso programados vencidos
	    List<CrProgramacionProceso> procesosVencidos = programacionProcesoRepository.encuentraProcesoVencido(
	    		cveEntidad, cveLocalidad, cveIdioma, Constants.PROGRAMADO, fechaActual, "NORMAL");

	    log.debug("PROCESOS ANTES DE LA FECHA TAMAÑO: " + procesosVencidos.size());

	    // Procesar cada proceso recuperado
	    for (CrProgramacionProceso proceso : procesosVencidos) {
	    	nomProcesoPeriodico = proceso.getCrProcesoPeriodico().getDescripcion();
	    	numPeriodo = proceso.getCrPeriodicidad().getCadaNPeriodos();
	    	tipoPeriodo = proceso.getCrPeriodicidad().getPeriodoTiempo();
	    	
		    List<CrNotificacion> notificaciones = notificacionRepository.encuentraNotificaciones(
		            proceso.getId().getCveEntidad(),
		            proceso.getId().getCveLocalidad(),
		            proceso.getId().getCveIdioma(),
		            proceso.getId().getCveProcesoPeriodico(),
		            proceso.getCrDefinicionPeriocidad().getSecDefinicionDespues(),
		            proceso.getId().getRfc(),
		            proceso.getId().getContrato());

		    log.debug("notificaciones TAMAÑO: " + notificaciones.size());
		    // Si existen notificaciones, procesarlas
		    if (!notificaciones.isEmpty()) {
		        log.debug("EXISTEN NOTIFICACIONES ");
		        
		        // Iterar sobre cada notificación
		        for (CrNotificacion notificacion : notificaciones) {
		        	// Procesar cada notificación
		            procesarNotificacion(notificacion, proceso, horas, nomProcesoPeriodico, numPeriodo, tipoPeriodo, "NORMAL");
		        }
		    }

	    } 	    
	
	}

	
	// Método principal que ejecuta el proceso de notificación
	@Override
	public void notifiacionUsuariosRepse(String cveEntidad, String cveLocalidad, String cveIdioma) {
	    log.debug("NOTIFICACION USUARIOS");

	    // Obtener la zona horaria según la entidad y localidad proporcionadas
	    Integer horas = obtenerZonaHoraria(cveEntidad, cveLocalidad);

	    // Calcular la fecha actual en la zona horaria correspondiente
	    ZoneId zoneIdDesplazamiento = ZoneId.ofOffset(Constants.UTC, ZoneOffset.ofHours(horas));
	    
	    // Obtener la fecha actual en la zona horaria correspondiente
	    ZonedDateTime fechaActualConDesplazamiento = ZonedDateTime.now(zoneIdDesplazamiento);
	    
	    // Obtener la fecha actual en formato LocalDate
	    LocalDate fechaActual = fechaActualConDesplazamiento.toLocalDate();
    
	    // Recuperar procesos programados que cumplen con los criterios
	    //SVM ajustar
	    List<CrProgramacionProceso> procesosAntes = programacionProcesoRepository.encuentraFechaAntesNotificacion(
	    		cveEntidad, cveLocalidad, cveIdioma, Constants.PROGRAMADO, fechaActual, "REPSE");

	    log.debug("PROCESOS ANTES DE LA FECHA TAMAÑO: " + procesosAntes.size());

	    String nomProcesoPeriodico = "";
	    int numPeriodo = 0;
	    String tipoPeriodo = "";
	    // Procesar cada proceso recuperado
	    for (CrProgramacionProceso proceso : procesosAntes) {
	    	if (proceso.getCrProcesoPeriodico().getRequerido().equals("SI")) {
		    	int docObligatorio = parametroRepository.documetosObligatoriosCorreo(proceso.getId().getCveProcesoPeriodico() + "%");
		    	
		    	nomProcesoPeriodico = proceso.getCrProcesoPeriodico().getDescripcion();
		    	numPeriodo = proceso.getCrPeriodicidad().getCadaNPeriodos();
		    	tipoPeriodo = proceso.getCrPeriodicidad().getPeriodoTiempo();
		    	
		    	if (docObligatorio > 0) {
				    List<CrNotificacion> notificaciones = notificacionRepository.encuentraNotificaciones(
				            proceso.getId().getCveEntidad(),
				            proceso.getId().getCveLocalidad(),
				            proceso.getId().getCveIdioma(),
				            proceso.getId().getCveProcesoPeriodico(),
				            proceso.getCrDefinicionPeriocidad().getSecDefinicionAntes(),
				            proceso.getId().getRfc(),
				            proceso.getId().getContrato());

				    log.debug("notificaciones TAMAÑO: " + notificaciones.size());

				    // Si existen notificaciones, procesarlas
				    if (!notificaciones.isEmpty()) {
				        log.debug("EXISTEN NOTIFICACIONES ");
				        // Iterar sobre cada notificación
				        for (CrNotificacion notificacion : notificaciones) {
				        	// Procesar cada notificación
				            procesarNotificacion(notificacion, proceso, horas, nomProcesoPeriodico, numPeriodo, tipoPeriodo, "REPSE");
				        }
				    }	    		
		    	}	    		
	    	}	    
	    }
	    
	    //proceso programados actuales
	    List<CrProgramacionProceso> procesosActuales = programacionProcesoRepository.encuentraFechaNotificacion(
	    		cveEntidad, cveLocalidad, cveIdioma, Constants.PROGRAMADO, fechaActual, "REPSE");

	    log.debug("PROCESOS ANTES DE LA FECHA TAMAÑO: " + procesosAntes.size());

	    // Procesar cada proceso recuperado
	    for (CrProgramacionProceso proceso : procesosActuales) {
	    	if (proceso.getCrProcesoPeriodico().getRequerido().equals("SI")) {
		    	nomProcesoPeriodico = proceso.getCrProcesoPeriodico().getDescripcion();
		    	numPeriodo = proceso.getCrPeriodicidad().getCadaNPeriodos();
		    	tipoPeriodo = proceso.getCrPeriodicidad().getPeriodoTiempo();
		    	int docObligatorio = parametroRepository.documetosObligatoriosCorreo(proceso.getId().getCveProcesoPeriodico() + "%");
		    	
		    	if (docObligatorio > 0) {
				    List<CrNotificacion> notificaciones = notificacionRepository.encuentraNotificaciones(
				            proceso.getId().getCveEntidad(),
				            proceso.getId().getCveLocalidad(),
				            proceso.getId().getCveIdioma(),
				            proceso.getId().getCveProcesoPeriodico(),
				            proceso.getCrDefinicionPeriocidad().getSecDefinicionDia(),
				            proceso.getId().getRfc(),
				            proceso.getId().getContrato());

				    log.debug("notificaciones TAMAÑO: " + notificaciones.size());
				    // Si existen notificaciones, procesarlas
				    if (!notificaciones.isEmpty()) {
				        log.debug("EXISTEN NOTIFICACIONES ");
				        
				        // Iterar sobre cada notificación
				        for (CrNotificacion notificacion : notificaciones) {
				        	// Procesar cada notificación
				            procesarNotificacion(notificacion, proceso, horas, nomProcesoPeriodico, numPeriodo, tipoPeriodo, "REPSE");
				        }
				    }
		    	}	    		
	    	}
	    } 
	    
	    //proceso programados vencidos
	    List<CrProgramacionProceso> procesosVencidos = programacionProcesoRepository.encuentraProcesoVencido(
	    		cveEntidad, cveLocalidad, cveIdioma, Constants.PROGRAMADO, fechaActual, "REPSE");

	    log.debug("PROCESOS ANTES DE LA FECHA TAMAÑO: " + procesosVencidos.size());

	    // Procesar cada proceso recuperado
	    for (CrProgramacionProceso proceso : procesosVencidos) {
	    	if (proceso.getCrProcesoPeriodico().getRequerido().equals("SI")) {
		    	nomProcesoPeriodico = proceso.getCrProcesoPeriodico().getDescripcion();
		    	numPeriodo = proceso.getCrPeriodicidad().getCadaNPeriodos();
		    	tipoPeriodo = proceso.getCrPeriodicidad().getPeriodoTiempo();
		    	int docObligatorio = parametroRepository.documetosObligatoriosCorreo(proceso.getId().getCveProcesoPeriodico() + "%");
		    	
		    	if (docObligatorio > 0) {
				    List<CrNotificacion> notificaciones = notificacionRepository.encuentraNotificaciones(
				            proceso.getId().getCveEntidad(),
				            proceso.getId().getCveLocalidad(),
				            proceso.getId().getCveIdioma(),
				            proceso.getId().getCveProcesoPeriodico(),
				            proceso.getCrDefinicionPeriocidad().getSecDefinicionDespues(),
				            proceso.getId().getRfc(),
				            proceso.getId().getContrato());

				    log.debug("notificaciones TAMAÑO: " + notificaciones.size());
				    // Si existen notificaciones, procesarlas
				    if (!notificaciones.isEmpty()) {
				        log.debug("EXISTEN NOTIFICACIONES ");
				        
				        // Iterar sobre cada notificación
				        for (CrNotificacion notificacion : notificaciones) {
				        	// Procesar cada notificación
				            procesarNotificacion(notificacion, proceso, horas, nomProcesoPeriodico, numPeriodo, tipoPeriodo, "REPSE");
				        }
				    }
		    	}	    		
	    	}
	    } 	    
	
	}
	
	// Obtiene la zona horaria basada en la entidad y localidad
	private Integer obtenerZonaHoraria(String cveEntidad, String cveLocalidad) {
	    // Buscar la localidad y su huso horario en el repositorio
	    Optional<LocalidadEntidad> localidadEntidad = localidadRepository.findById(LocalidadEntidadPK.builder()
	            .cveEntidad(cveEntidad)
	            .cveLocalidad(cveLocalidad)
	            .build());

	    // Verificar si la localidad fue encontrada
	    if (localidadEntidad.isPresent()) {
	        // Retornar la clave del huso horario si está presente
	        HusoHorario usoHorario = localidadEntidad.get().getHusoHorario();
	        return Integer.valueOf(usoHorario.getCveHusoHorario());
	    }

	    // Retornar 0 si no se encuentra la localidad
	    return 0;
	}


	// Obtiene los días límite para notificación
	private Integer obtenerDiasLimite(CrDefinicionPeriocidad definicion) {
	    // Obtener el valor de días para notificar de la definición
	    Integer diasLimite = definicion.getDiasParaNotificar();

	    // Verificar si el valor de días para notificar está definido
	    if (diasLimite == null) {
	        // Si no está definido, usar el valor por defecto
	        diasLimite = 5;
	        
	        // Buscar el parámetro general de días límite
	        Optional<ParametroGeneral> parametroGral = parametroRepository.findById(Constants.FECHA_LIMITE_DIAS);
	       
	        // Verificar si el parámetro general está
	        if (parametroGral.isPresent()) {
	            // Usar el valor configurado en parámetros generales si está presente
	            diasLimite = parametroGral.get().getValorEntero();
	        }
	    }

	    // Retornar el valor de días límite
	    return diasLimite;
	}

	// Procesa cada notificación
	private void procesarNotificacion(CrNotificacion notificacion, CrProgramacionProceso proceso, Integer horas, 
			String nomProcesoPeriodico, int nPerodos, String tipoPeriodo, String tipoProceso ) {
	    // Mostrar detalles de la notificación
	    log.debug("ASUNTO : " + notificacion.getAsunto());
	    log.debug("CUERPO : " + notificacion.getCuerpo());
	    
	    DestinatarioTO destinatarios = null;

	    if (tipoProceso.equals("NORMAL")) {
		    // Obtener los destinatarios de la notificación
		    destinatarios = obtenerDestinatarios(
		            notificacion.getId().getCveEntidad(),
		            notificacion.getId().getCveLocalidad(),
		            notificacion.getId().getCveIdioma(),
		            notificacion.getId().getCveProcesoPeriodico(),
		            notificacion.getId().getSecuenciaCorreo());	    	
	    } else {
	  
	    	destinatarios = obtenerDestinatariosRepse(proceso,  notificacion.getId().getSecuenciaCorreo());
	    }

	    log.debug("-----------> EMAILS: " + destinatarios.getEmails().length + " " + Arrays.toString(destinatarios.getEmails()));
	    log.debug("-----------> CC: " + destinatarios.getTipo().length + " " + Arrays.toString(destinatarios.getTipo()));

	    // Enviar correo si existen destinatarios
	    if (destinatarios.getEmails() != null && destinatarios.getEmails().length != 0) {
	    	
	    	InVariableProceso ivp = variableProcesoRepository.obternerDatosProceso(proceso.getId().getCveEntidad(),
	    			proceso.getId().getContrato(), proceso.getId().getRfc());
	    	
	    	String asunto = notificacion.getAsunto();
	    	String cuerpoConfigurado = notificacion.getCuerpo();
	    		    	
	    	// Formatear la fecha de programación
	    	String fechaProgramacion = nodoHelper.formatFecha(proceso.getId().getFechaProgramacion(), "dd MMMM yyyy");
	        log.debug("fechaProgramacion" + fechaProgramacion);nodoHelper.formatFecha(proceso.getId().getFechaProgramacion(), "dd MMMM yyyy");

	        // Configurar el cuerpo del mensaje con la fecha programada
	        cuerpoConfigurado = cuerpoConfigurado.replace(Constants.FECHA_PROGRAMACION, fechaProgramacion);
	        log.debug("cuerpoConfigurado" + cuerpoConfigurado);
	        
	        // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
	         DateTimeFormatter[] formatters = new DateTimeFormatter[] {
	                DateTimeFormatter.ofPattern("dd MMMM yyyy", new Locale("es", "ES")),
	                DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.ENGLISH)
	            };
	        LocalDate fechaDocumentacion = null;
	        for (DateTimeFormatter formatter : formatters) {
	        	try {
	        		fechaDocumentacion = LocalDate.parse(fechaProgramacion, formatter);	                
	            } catch (DateTimeParseException ignored) {
	            	continue;
	            }
	        }
	    	
	        //Obtener periodo
	        
	        String strMesPeriodo = "";
	        
	        switch (tipoPeriodo) {
			case "MES":
				YearMonth mesActual = YearMonth.from(fechaDocumentacion);
				mesActual = mesActual.plusMonths(-1);
				if (nPerodos == 1 || nPerodos == 0) {
					strMesPeriodo = " mes de " + mesActual.getMonth().getDisplayName(TextStyle.FULL, new Locale("es", "ES")); 
				} 
			
				else {
					 int mes = mesActual.getMonthValue();
					 int periodo = (mes - 1) / nPerodos + 1;
					 strMesPeriodo = " periodo " + periodo + " del año " + mesActual.getYear();
				}
				break;
			case "SEMANA":
				WeekFields weekFields = WeekFields.of(Locale.getDefault());
				int semanaDelMes = fechaDocumentacion.get(weekFields.weekOfMonth());
				
				YearMonth semanaActual = YearMonth.from(fechaDocumentacion);
				strMesPeriodo = " la semana "  + semanaDelMes + " del mes de " + semanaActual.getMonth().getDisplayName(TextStyle.FULL, new Locale("es", "ES")); 
				break;

			default:
				strMesPeriodo = " al dia "  + fechaProgramacion; 
				break;
			}
	        
	        cuerpoConfigurado = cuerpoConfigurado.replace(Constants.MES_PROCESO, strMesPeriodo);
	        log.debug("cuerpoConfigurado" + cuerpoConfigurado);
	        
	        cuerpoConfigurado = cuerpoConfigurado.replace(Constants.CONTRATO, proceso.getId().getContrato());
	        log.debug("cuerpoConfigurado" + cuerpoConfigurado);
	        
	        asunto = asunto.replace(Constants.NOM_DOCUMENTO, nomProcesoPeriodico);
	        cuerpoConfigurado = cuerpoConfigurado.replace(Constants.NOM_DOCUMENTO, nomProcesoPeriodico);
	        log.debug("cuerpoConfigurado" + cuerpoConfigurado);
	        
	        if (ivp != null) {
		    	//Se integran las variables
				NodoTO nodo = NodoTO.builder()
						.idProceso(ivp.getId().getCveProceso())
						.cveProceso(ivp.getId().getCveProceso())
						.version(ivp.getId().getVersion())
						.cveInstancia(ivp.getId().getCveInstancia())
						.cveNodo(null)
						.idNodo(null)
						.secuenciaNodo(null)
						.ocurrencia(ivp.getId().getOcurrencia())
						.build();
			    
				DatosAutenticacionTO session =DatosAutenticacionTO.builder()
						.cveEntidad(proceso.getId().getCveEntidad())
						.cveIdioma(proceso.getId().getCveIdioma())
						.cveLocalidad(proceso.getId().getCveLocalidad())
						.build();
				
				EstatusVariablesTO variableTo;
				try {
					variableTo = iNodoHelper.reemplazaVariables(session, nodo, null, asunto);

					
					if (variableTo.getTipoExcepcion().equals(Constants.OK)) {
						asunto = variableTo.getCadenaSalida();
						variableTo =  iNodoHelper.reemplazaVariables(session, nodo, null, cuerpoConfigurado);
						cuerpoConfigurado = variableTo.getCadenaSalida();
					}
				
				} catch (BrioBPMException | ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	        
	        log.debug("asunto: " + asunto);
	        log.debug("cuerpoConfigurado: " + cuerpoConfigurado);
	        
	        // Enviar el correo
	        boolean envio = bPMMailSender.sendHtmlMailMessage(destinatarios.getEmails(), destinatarios.getTipo(), asunto, cuerpoConfigurado);
	        log.debug("ENVIO: " + envio);

	        // Actualizar la fecha de última notificación si el envío fue exitoso
	        if (envio) {
	            actualizarFechaUltimaNotificacion(proceso, horas);
	        }
	    }
	}

	
	// Método que obtiene los destinatarios de notificación para un proceso específico
	@Override
	public DestinatarioTO obtenerDestinatarios(String cveEntidad, String cveLocalidad, String cveIdioma, String cveProcesoPeriodico, Integer secuenciaCorreo) {
	   
		log.debug("OBTENER DESTINATARIOS");
		
		// Obtiene los destinatarios para el proceso
	    List<CrDestinatarioCorreo> destinatarios = destinatarioCorreoRepository.buscarDestinatario(
	            cveEntidad,
	            cveLocalidad,
	            cveIdioma,
	            cveProcesoPeriodico,
	            secuenciaCorreo);

	    // Inicializa listas para almacenar las direcciones de correo electrónico
	    List<String> emailsList = new ArrayList<>();
	    List<String> tiposList = new ArrayList<>();
	    DestinatarioTO destinatariosTO = new DestinatarioTO();

	    // Itera sobre cada destinatario encontrado
	    for (CrDestinatarioCorreo destinatario : destinatarios) {
	    	
	        // Si el destinatario es un usuario (es decir, tiene una clave de usuario no nula)
	        if (destinatario.getCveUsuario() != null) {
	            // Intenta obtener el usuario asociado a través del repositorio de usuarios
	            Optional<Usuario> usuario = usuarioRepository.findById(
	                UsuarioPK.builder()
	                    .cveEntidad(destinatario.getId().getCveEntidad()) // Asigna la clave de entidad del destinatario al ID del usuario
	                    .cveUsuario(destinatario.getCveUsuario()) // Asigna la clave de usuario del destinatario al ID del usuario
	                    .build() // Construye el objeto UsuarioPK con las claves asignadas
	            );

	            // Si el usuario está presente (es decir, fue encontrado)
	            if (usuario.isPresent()) {
	                // Obtiene el correo electrónico del usuario
	                String emailUsuario = usuario.get().getCorreoElectronico();

	                // Verifica que el email no sea nulo ni esté vacío
	                if (emailUsuario != null && !emailUsuario.isEmpty()) {
	                    // Busca la posición del carácter '<' en el email
	                    int start = emailUsuario.indexOf("<");

	                    // Busca la posición del carácter '>' en el email
	                    int end = emailUsuario.indexOf(">");

	                    // Verifica que ambos caracteres fueron encontrados y que '<' aparece antes que '>'
	                    if (start != -1 && end != -1 && start < end) {
	                        // Extrae el email que se encuentra entre '<' y '>'
	                        emailUsuario = emailUsuario.substring(start + 1, end);

	                        emailsList.add(emailUsuario);
	                        
	                    }
	                }
	            }
	        } 
	        
	        // Verifica si el destinatario es una dirección de correo electrónico
	        if (destinatario.getDireccionCorreo() != null) { // Si el destinatario es una dirección de correo electrónico directa (no asociada a un usuario)
	        	
	        	log.debug("EXISTE DIRECCION DE CORREO");
	        	
	        	// Obtiene la dirección de correo del destinatario
	        	String direccionCorreo = destinatario.getDireccionCorreo();

	        	// Verifica que la dirección de correo no sea nula ni esté vacía
	        	if (direccionCorreo != null && !direccionCorreo.isEmpty()) {
	        	    // Divide la cadena en correos separados por ';'
	        	    String[] correos = direccionCorreo.split(";");
	        	    
	        	    // Itera sobre cada dirección de correo
	        	    for (String correo : correos) {
	        	        // Elimina espacios en blanco antes y después de cada dirección de correo
	        	        correo = correo.trim();
	        	        
	        	        // Verifica que el correo no esté vacío después de la separación
	        	        if (!correo.isEmpty()) {
	        	            // Añade la dirección de correo a la lista correspondiente
	        	            if (Constants.CCP.equals(destinatario.getTipoDestinatario())) {
	        	                tiposList.add(correo);
	        	            } else if (Constants.PARA.equals(destinatario.getTipoDestinatario())) {
	        	                emailsList.add(correo);
	        	            }
	        	        }
	        	    }
	        	}

	        	log.debug("MANDO CORREO A DIRECCIONES EXTERNAS");
	        }
	    }

	    // Convierte las listas a arrays
	    String[] emails = emailsList.toArray(new String[0]);
	    String[] tipos = tiposList.toArray(new String[0]);

	    // Asigna los arrays de emails y tipos al objeto DestinatarioTO
	    destinatariosTO.setEmails(emails);
	    destinatariosTO.setTipo(tipos);

	    log.debug("EMAILS: " + Arrays.toString(emails));
	    log.debug("TIPOS: " + Arrays.toString(tipos));
	    
	    // Devuelve el objeto DestinatarioTO
	    return destinatariosTO;
	}


	@Override
	// Método para actualizar la fecha de la última notificación
	public void actualizarFechaUltimaNotificacion(CrProgramacionProceso proceso, Integer horas) {
		log.debug("ACTUALIZAR FECHA ULTIMA NOTIFICACION");
		
	    // Obtener la zona horaria del servidor
	    ZoneId serverZoneId = ZoneId.systemDefault();
	    ZonedDateTime nowInServerZone = ZonedDateTime.now(serverZoneId);
	    
	    // Calcular el desfase del servidor con respecto a UTC
	    int serverOffset = nowInServerZone.getOffset().getTotalSeconds() / 3600;

	    log.info("Server Offset: " + serverOffset + " horas respecto a UTC");

	    // Verificar si las horas (desfase proporcionado) son diferentes al desfase del servidor
	    if (serverOffset != horas) {
	        // Calcular la diferencia entre el desfase del servidor y las horas proporcionadas
	        int diferencia = horas - serverOffset;

	        // Ajustar el tiempo según la diferencia calculada
	        nowInServerZone = nowInServerZone.plusHours(diferencia); 
	        log.info("ZonedDateTime ajustado según diferencia de horas: " + nowInServerZone);
	    }
	    log.debug("HORAS proporcionadas: " + horas);

	    // Convertir a LocalDate para eliminar la parte de la hora
	    LocalDate localDate = nowInServerZone.toLocalDate();

	    // Convertir LocalDate a Date, estableciendo la hora a medianoche
	    Date fechaActualDate = Date.from(localDate.atStartOfDay(serverZoneId).toInstant());
	    log.debug("Fecha actual ajustada a medianoche: " + fechaActualDate);

	    // Actualizar la fecha de la última notificación
	    proceso.setFechaUltimaNotificacion(fechaActualDate);
	    
	    // Actualizar la situación de la última notificación
	    proceso.setSituacionUltimaNotificacion(Constants.NOTIFICADO);

	    // Guardar el proceso actualizado en la base de datos
	    programacionProcesoRepository.save(proceso);
	}

	// Método principal que ejecuta el proceso de notificación usuarios nuevos
	@Override
	public void notifiacionNewUsuarios() {
		
		// Obtener la lista de notificaciones de usuarios nuevos
		List<Notificacion> listas = notificacionUsuarioRepository.findAll();
	    List<String> emailsList = new ArrayList<>();
	    List<String> tiposList = new ArrayList<>();
	    
		// Iterar sobre cada notificación
		for (Notificacion notificacion : listas) {
			
			// Dividir las direcciones de correo electrónico en un array
			String[] correos = notificacion.getPara().split(";"); 
			String[] ccp = notificacion.getCcp().split(";");
			for (int j = 0; j < correos.length; j++) {
	    		String email = correos[j];
	            // Busca la posición del carácter '<' en el email
	            int start = email.indexOf("<");

	            // Busca la posición del carácter '>' en el email
	            int end = email.indexOf(">");

	            // Verifica que ambos caracteres fueron encontrados y que '<' aparece antes que '>'
	            if (start != -1 && end != -1 && start < end) {
	                // Extrae el email que se encuentra entre '<' y '>'
	            	email = email.substring(start + 1, end);
	                emailsList.add(email);	                
	            }  else {
	            	if (!email.isEmpty()) {
	            		emailsList.add(email);	
	            	}
	            }
			}

			for (int j = 0; j < ccp.length; j++) {
	    		String email = ccp[j];
	            // Busca la posición del carácter '<' en el email
	            int start = email.indexOf("<");

	            // Busca la posición del carácter '>' en el email
	            int end = email.indexOf(">");

	            // Verifica que ambos caracteres fueron encontrados y que '<' aparece antes que '>'
	            if (start != -1 && end != -1 && start < end) {
	                // Extrae el email que se encuentra entre '<' y '>'
	            	email = email.substring(start + 1, end);
	            	tiposList.add(email);	                
	            }  else {
	            	if (!email.isEmpty()) {
	            		tiposList.add(email);	
	            	}
	            }
			}
			
		    String[] emails = emailsList.toArray(new String[0]);
		    String[] cc_emails = tiposList.toArray(new String[0]);
		    
			// Enviar
	        boolean envio = bPMMailSender.sendHtmlMailMessage(emails, cc_emails, notificacion.getAsunto(), notificacion.getCuerpo());

	        // Eliminar la notificación si el envío fue exitoso
	        if(envio) {
	        	notificacionUsuarioRepository.deleteById(notificacion.getSecuenciaCorreo());
	    	}
		}
		 
	}

	
	
	private DestinatarioTO obtenerDestinatariosRepse(CrProgramacionProceso proceso, Integer secuenciaCorreo) {
	   
		log.debug("OBTENER DESTINATARIOS");
		log.info("rfc: {} contrato: {} proceso: {} ", proceso.getId().getRfc(), proceso.getId().getContrato(), proceso.getId().getCveProcesoPeriodico());
		
		 List<CrDestinatarioCorreo> usuarios = destinatarioCorreoRepository.buscarDestinatarioByContrato(proceso.getId().getCveEntidad(),
				 proceso.getId().getCveLocalidad(), proceso.getId().getCveIdioma(), proceso.getId().getCveProcesoPeriodico(), 
				secuenciaCorreo, proceso.getId().getRfc(), proceso.getId().getContrato());


	    // Inicializa listas para almacenar las direcciones de correo electrónico
	    List<String> emailsList = new ArrayList<>();
	    List<String> tiposList = new ArrayList<>();
	    DestinatarioTO destinatariosTO = new DestinatarioTO();

	    // Itera sobre cada destinatario encontrado
	    for (CrDestinatarioCorreo usuario : usuarios) {

            String emailUsuario = usuario.getDireccionCorreo();

            // Verifica que el email no sea nulo ni esté vacío
            if (emailUsuario != null && !emailUsuario.isEmpty()) {
            	
            	String[] correos = emailUsuario.split("\\;");
            	for (int i = 0; i < correos.length; i++) {
            		String email = correos[i];
                    // Busca la posición del carácter '<' en el email
                    int start = email.indexOf("<");

                    // Busca la posición del carácter '>' en el email
                    int end = email.indexOf(">");

                    // Verifica que ambos caracteres fueron encontrados y que '<' aparece antes que '>'
                    if (start != -1 && end != -1 && start < end) {
                        // Extrae el email que se encuentra entre '<' y '>'
                    	email = email.substring(start + 1, end);

                        emailsList.add(email);
                        
                    } 
            	}
            }
	    }

	    // Convierte las listas a arrays
	    String[] emails = emailsList.toArray(new String[0]);
	    String[] tipos = tiposList.toArray(new String[0]);

	    // Asigna los arrays de emails y tipos al objeto DestinatarioTO
	    destinatariosTO.setEmails(emails);
	    destinatariosTO.setTipo(tipos);

	    log.debug("EMAILS: " + Arrays.toString(emails));
	    log.debug("TIPOS: " + Arrays.toString(tipos));
	    
	    // Devuelve el objeto DestinatarioTO
	    return destinatariosTO;
	}

	
}
