package com.briomax.briobpm.business.helpers;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.rowset.serial.SerialBlob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.briomax.briobpm.business.helpers.base.IEjecutaFuncionHelper;
import com.briomax.briobpm.business.helpers.base.IFechaHelper;
import com.briomax.briobpm.business.helpers.base.INodoHelper;
import com.briomax.briobpm.business.helpers.base.ITemporizadorHelper;
import com.briomax.briobpm.business.service.catadaptaciones.CrAccesoUsuarioService;
import com.briomax.briobpm.business.service.catalogs.core.IMessagesService;
import com.briomax.briobpm.common.core.Constants;
import com.briomax.briobpm.common.core.DAORet;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.persistence.dao.VwDatoActividadDAO;
import com.briomax.briobpm.persistence.dao.base.IAreaTrabajoDAO;
import com.briomax.briobpm.persistence.entity.ComposicionCorreo;
import com.briomax.briobpm.persistence.entity.ComposicionCorreoPK;
import com.briomax.briobpm.persistence.entity.CorreoProceso;
import com.briomax.briobpm.persistence.entity.CrConsultaRepsePK;
import com.briomax.briobpm.persistence.entity.CrProgramacionProceso;
import com.briomax.briobpm.persistence.entity.CrProgramacionProcesoPK;
import com.briomax.briobpm.persistence.entity.DatoProcesoNodo;
import com.briomax.briobpm.persistence.entity.Entidad;
import com.briomax.briobpm.persistence.entity.FoliadorMensaje;
import com.briomax.briobpm.persistence.entity.FolioMensajeProceso;
import com.briomax.briobpm.persistence.entity.InBitacoraNodo;
import com.briomax.briobpm.persistence.entity.InBitacoraNodoPK;
import com.briomax.briobpm.persistence.entity.InDocumentoProceso;
import com.briomax.briobpm.persistence.entity.InDocumentoProcesoOc;
import com.briomax.briobpm.persistence.entity.InDocumentoProcesoOcPK;
import com.briomax.briobpm.persistence.entity.InDocumentoProcesoPK;
import com.briomax.briobpm.persistence.entity.InFolioNodo;
import com.briomax.briobpm.persistence.entity.InFolioNodoPK;
import com.briomax.briobpm.persistence.entity.InImagenProceso;
import com.briomax.briobpm.persistence.entity.InImagenProcesoPK;
import com.briomax.briobpm.persistence.entity.InMensajeEnvio;
import com.briomax.briobpm.persistence.entity.InMensajeEnvioPK;
import com.briomax.briobpm.persistence.entity.InMensajeRecepcion;
import com.briomax.briobpm.persistence.entity.InMensajeRecepcionPK;
import com.briomax.briobpm.persistence.entity.InNodoProceso;
import com.briomax.briobpm.persistence.entity.InNodoProcesoPK;
import com.briomax.briobpm.persistence.entity.InNodoProcesoUsuario;
import com.briomax.briobpm.persistence.entity.InNodoProcesoUsuarioPK;
import com.briomax.briobpm.persistence.entity.InProceso;
import com.briomax.briobpm.persistence.entity.InProcesoPK;
import com.briomax.briobpm.persistence.entity.InTareaProceso;
import com.briomax.briobpm.persistence.entity.InTareaProcesoPK;
import com.briomax.briobpm.persistence.entity.InVariableEnvio;
import com.briomax.briobpm.persistence.entity.InVariableEnvioPK;
import com.briomax.briobpm.persistence.entity.InVariableProceso;
import com.briomax.briobpm.persistence.entity.InVariableProcesoPK;
import com.briomax.briobpm.persistence.entity.ReglaBotonActividad;
import com.briomax.briobpm.persistence.entity.StCompuertaInicio;
import com.briomax.briobpm.persistence.entity.StMensajeEnvio;
import com.briomax.briobpm.persistence.entity.StMensajeEnvioPK;
import com.briomax.briobpm.persistence.entity.StMensajeRecepcion;
import com.briomax.briobpm.persistence.entity.StMensajeRecepcionPK;
import com.briomax.briobpm.persistence.entity.StNodoProceso;
import com.briomax.briobpm.persistence.entity.StNodoProcesoPK;
import com.briomax.briobpm.persistence.entity.StNodoSiguiente;
import com.briomax.briobpm.persistence.entity.StValorInicialVariable;
import com.briomax.briobpm.persistence.entity.StVariableEnvio;
import com.briomax.briobpm.persistence.entity.StVariableProceso;
import com.briomax.briobpm.persistence.entity.StVariableProcesoPK;
import com.briomax.briobpm.persistence.entity.Usuario;
import com.briomax.briobpm.persistence.entity.UsuarioPK;
import com.briomax.briobpm.persistence.entity.VariableEntidad;
import com.briomax.briobpm.persistence.entity.VariableEntidadPK;
import com.briomax.briobpm.persistence.entity.VariableLocalidad;
import com.briomax.briobpm.persistence.entity.VariableLocalidadPK;
import com.briomax.briobpm.persistence.entity.VariableSistema;
import com.briomax.briobpm.persistence.jdto.Documento;
import com.briomax.briobpm.persistence.repository.IBitacoraBatchRepository;
import com.briomax.briobpm.persistence.repository.IComposicionCorreoRepository;
import com.briomax.briobpm.persistence.repository.ICorreoProcesoRepository;
import com.briomax.briobpm.persistence.repository.ICrProgramacionProcesoRepository;
import com.briomax.briobpm.persistence.repository.IDatoProcesoNodoRepository;
import com.briomax.briobpm.persistence.repository.IDestinarioCorreoRepository;
import com.briomax.briobpm.persistence.repository.IEntidadRepository;
import com.briomax.briobpm.persistence.repository.IFoliadorMensaje;
import com.briomax.briobpm.persistence.repository.IInBitacoraNodoRepository;
import com.briomax.briobpm.persistence.repository.IInDocumentoProcesoOcRepository;
import com.briomax.briobpm.persistence.repository.IInDocumentoProcesoRepository;
import com.briomax.briobpm.persistence.repository.IInFolioNodoRepository;
import com.briomax.briobpm.persistence.repository.IInImagenProcesoRepository;
import com.briomax.briobpm.persistence.repository.IInMensajeEnvioRepository;
import com.briomax.briobpm.persistence.repository.IInMensajeRecepcionRepository;
import com.briomax.briobpm.persistence.repository.IInNodoProcesoRepository;
import com.briomax.briobpm.persistence.repository.IInNodoProcesoUsuarioRepository;
import com.briomax.briobpm.persistence.repository.IInProcesoRepository;
import com.briomax.briobpm.persistence.repository.IInTareaProcesoRepository;
import com.briomax.briobpm.persistence.repository.IInVariableEnvioRepository;
import com.briomax.briobpm.persistence.repository.IInVariableProcesoRepository;
import com.briomax.briobpm.persistence.repository.ILocalidadEntidadRepository;
import com.briomax.briobpm.persistence.repository.IParametroGeneralRepository;
import com.briomax.briobpm.persistence.repository.IReglaBotonActividadRepository;
import com.briomax.briobpm.persistence.repository.IStCompuertaInicioRepository;
import com.briomax.briobpm.persistence.repository.IStDocumentoProcesoRepository;
import com.briomax.briobpm.persistence.repository.IStDocumentoSeccionRepository;
import com.briomax.briobpm.persistence.repository.IStMensajeEnvioRepository;
import com.briomax.briobpm.persistence.repository.IStMensajeRecepcionRepository;
import com.briomax.briobpm.persistence.repository.IStNodoInicioProcesoRepository;
import com.briomax.briobpm.persistence.repository.IStNodoProcesoRepository;
import com.briomax.briobpm.persistence.repository.IStNodoSiguienteRepository;
import com.briomax.briobpm.persistence.repository.IStSeccionNodoRepository;
import com.briomax.briobpm.persistence.repository.IStTareaSeccionRepository;
import com.briomax.briobpm.persistence.repository.IStValorInicialVariableRepository;
import com.briomax.briobpm.persistence.repository.IStVariableEnvioRepository;
import com.briomax.briobpm.persistence.repository.IStVariableProcesoRepository;
import com.briomax.briobpm.persistence.repository.IStVariableSeccionRepository;
import com.briomax.briobpm.persistence.repository.ITraduccionRepository;
import com.briomax.briobpm.persistence.repository.IUsuarioRepository;
import com.briomax.briobpm.persistence.repository.IUsuarioRolRepository;
import com.briomax.briobpm.persistence.repository.IVariableEntidadRepository;
import com.briomax.briobpm.persistence.repository.IVariableLocalidadRepository;
import com.briomax.briobpm.persistence.repository.IVariableSistemaRepository;
import com.briomax.briobpm.transferobjects.BitacoraTO;
import com.briomax.briobpm.transferobjects.SaveSectionTO;
import com.briomax.briobpm.transferobjects.VariableCadenaTO;
import com.briomax.briobpm.transferobjects.catalogs.BitacoraNodo;
import com.briomax.briobpm.transferobjects.emun.MagicNumber;
import com.briomax.briobpm.transferobjects.in.ActividadTO;
import com.briomax.briobpm.transferobjects.in.CalculoFechaLimiteTO;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;
import com.briomax.briobpm.transferobjects.in.DocumentoNodoTO;
import com.briomax.briobpm.transferobjects.in.DocumentoTO;
import com.briomax.briobpm.transferobjects.in.ElementoCadenaTO;
import com.briomax.briobpm.transferobjects.in.EstatusCompuertaTO;
import com.briomax.briobpm.transferobjects.in.EstatusCondicionTO;
import com.briomax.briobpm.transferobjects.in.EstatusConfiguracionEnvioTO;
import com.briomax.briobpm.transferobjects.in.EstatusFechaTO;
import com.briomax.briobpm.transferobjects.in.EstatusTO;
import com.briomax.briobpm.transferobjects.in.EstatusVariableCadenaTO;
import com.briomax.briobpm.transferobjects.in.EstatusVariablesTO;
import com.briomax.briobpm.transferobjects.in.NodoProcesoTO;
import com.briomax.briobpm.transferobjects.in.NodoSuperiorTO;
import com.briomax.briobpm.transferobjects.in.NodoTO;
import com.briomax.briobpm.transferobjects.in.SituacionTareaTO;
import com.briomax.briobpm.transferobjects.in.StSeccionNodoTO;
import com.briomax.briobpm.transferobjects.in.TareaNodoTO;
import com.briomax.briobpm.transferobjects.in.ValorVariableTO;
import com.briomax.briobpm.transferobjects.repse.CrUsuarioTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la clase NodoHelper.java es proporcionar métodos de ayuda relacionados con los nodos.
 *
 * @author Alexis Zamora
 * @ver 1.0
 * @fecha Feb 14, 2024 4:12:01 PM
 * @since JDK 1.8
 */

@Service
@Transactional
@Slf4j
public class NodoHelper implements INodoHelper {
	
	/** El atributo o variable messages service. */
	@Autowired
	private IMessagesService messagesService;

	/** El atributo o variable folio Nodo Repository. */
	@Autowired
	private IInFolioNodoRepository folioNodoRepository;

	/** El atributo o variable folio Nodo Repository. */
	@Autowired
	private IInNodoProcesoRepository inNodoProcesoRepository;

	/** El atributo o variable in Variable Proceso Repository. */
	@Autowired
	IInVariableProcesoRepository inVariableProcesoRepository;
	
	/** El atributo o variable in Tarea Proceso Repository. */
	@Autowired
	IInTareaProcesoRepository inTareaProcesoRepository;

	/** El atributo o variable In Documento Proceso Repository. */
	@Autowired
	IInDocumentoProcesoRepository iInDocumentoProcesoRepository;
	
	/** El atributo o variable In Documento ProcesoOc Repository. */
	@Autowired
	IInDocumentoProcesoOcRepository iInDocumentoProcesoOcRepository;
	
	/** El atributo o variable st Variable Seccion Repository. */
	@Autowired
	IStVariableSeccionRepository iSstVariableSeccionRepository;
	
	/** El atributo o variable St Tarea Seccion Repository. */
	@Autowired
	IStTareaSeccionRepository iStTareaSeccionRepository;
	
	/** El atributo o variable St Documento Seccion Repository. */
	@Autowired
	IStDocumentoSeccionRepository iStDocumentoSeccionRepository;
	
	/** El atributo o variable St Compuerta Inicio Repository. */
	@Autowired
	IStCompuertaInicioRepository iStCompuertaInicioRepository;
	
	/** El atributo o variable In Folio Nodo Repository. */
	@Autowired
	IInFolioNodoRepository iInFolioNodoRepository;
	
	/** El atributo o variable In Nodo Proceso Repository. */
	@Autowired
	IInNodoProcesoRepository iInNodoProcesoRepository;
	
	/** El atributo o variable In Nodo Siguiente Repository. */
	@Autowired
	IStNodoSiguienteRepository iStNodoSiguienteRepository; 
	
	/** El atributo o variable In Mensaje Envio Repository. */
	@Autowired
	IInMensajeEnvioRepository 	iInMensajeEnvioRepository;
	
	/** El atributo o variable In Variable Envio Repository. */
	@Autowired
	IInVariableEnvioRepository iInVariableEnvioRepository;
	
	/** El atributo o variable In Bitacora Nodo Repository. */
	@Autowired
	IInBitacoraNodoRepository iInBitacoraNodoRepository;
	
	/** El atributo o variable Dato Proceso Repository. */
	@Autowired
	IDatoProcesoNodoRepository iDatoProcesoRepository;
	
	/** El atributo o Variable Sistema Repository. */
	@Autowired
	IVariableSistemaRepository iVariableSistemaRepository;
	
	/** El atributo o Variable Entidad Repository. */
	@Autowired
	IVariableEntidadRepository iVariableEntidadRepository;
	
	/** El atributo o Variable St Variable Proceso Repository. */
	@Autowired
	IStVariableProcesoRepository iStVariableProcesoRepository;
	
	/** El atributo o Variable Variable Localidad Repository. */
	@Autowired
	IVariableLocalidadRepository iVariableLocalidadRepository;
	
	/** El atributo o variable St Variable Envio repository. */
	@Autowired
	IStVariableEnvioRepository iStVariableEnvioRepository;
	
	/** El atributo o variable Foliador Mensaje repository. */
	@Autowired
	IFoliadorMensaje iFoliadorMensajeRepository;
	
	/** El atributo o variable St Mensaje Recepcion repository. */
	@Autowired
	IStMensajeRecepcionRepository iStMensajeRecepcionRepository;
	
	/** El atributo o variable St Mensaje Envio repository. */
	@Autowired
	IStMensajeEnvioRepository iStMensajeEnvioRepository;
	
	/** El atributo o variable Valor Variable Proceso repository. */
	@Autowired
	ICorreoProcesoRepository iCorreoProcesoRepository;
	
	/** El atributo o variable Valor Documento Proceso repository. */
	@Autowired
	IStDocumentoProcesoRepository iStDocumentoProcesoRepository;
	
	/** El atributo o variable Valor Destinario Correo Repository. */
	@Autowired
	IDestinarioCorreoRepository iDestinarioCorreoRepository;
	
	/** El atributo o variable Valor Composicion Correo Repository. */
	@Autowired
	IComposicionCorreoRepository iComposicionCorreoRepository;
	
	/** El atributo o variable Usuario Repository. */
	@Autowired
	IUsuarioRepository iUsuarioRepository;
	
	/** El atributo o variable Usuario Rol Repository. */
	@Autowired
	IUsuarioRolRepository iUsuarioRolRepository;
	
	/** El atributo o variable st Nodo Inicio ProcesoRepository. */
	@Autowired
	IStNodoInicioProcesoRepository stNodoInicioProcesoRepository;
	
	/** El atributo o variable In Mensaje Recepcion Repository. */
	@Autowired
	IBitacoraBatchRepository bitacoraBatchRepository;
	
	/** El atributo o variable In Proceso Repository. */
	@Autowired
	IInProcesoRepository iInProcesoRepository;
	
	/** El atributo o variable Parametro General Repository Repository. */
	@Autowired
	IParametroGeneralRepository iParametroGeneralRepository;
	
	/** El atributo o variable In Mensaje Recepcion Repository. */
	@Autowired
	IInMensajeRecepcionRepository iInMensajeRecepcionRepository;
	
	/** El atributo o variable St Documento Seccion Repository. */
	@Autowired
	IStDocumentoSeccionRepository stDocumentoSeccionRepository;
	
	/** El atributo o variable St Seccion Nodo Repository. */
	@Autowired
	IStSeccionNodoRepository stSeccionNodoRepository;
	
	@Autowired
	ICrProgramacionProcesoRepository crProgramacionProcesoRepository;
	
	/** El atributo o variable Fecha Helper. */
	@Autowired
	IFechaHelper fechaHelper;
	
	/** El atributo o variable para ejecutar querys. */
	@Autowired  
	IAreaTrabajoDAO areaTrabajoDAO;
	
	/** El atributo o variable para ejecutar querys. */
	@Autowired
	IReglaBotonActividadRepository iReglaProcesoTerminarRepository;
	
	@Autowired
	ILocalidadEntidadRepository iLocalidadEntidadRepository;
	
	
	/** El atributo o variable localidad repository. */
	@Autowired
	private ITraduccionRepository traduccionRepository;
	
	/** El atributo o variable entidad repository. */
	@Autowired
	private IEntidadRepository entidadRepository;
	
	/** El atributo o variable dashboar. */
	@Autowired
	private DashboardHelper dashboardHelper;	

	/** El atributo o variable Valor Inicial Variable repository. */
	@Autowired
	private  IStValorInicialVariableRepository stValorInicialVariableRepository;
	
	/** El atributo o variable Valorin Variable Proceso repository. */
	@Autowired
	private  IInImagenProcesoRepository inImagenProcesoRepository;
	
	/** El atributo o variable St Nodo Proceso repository. */
	@Autowired
	private IStNodoProcesoRepository stNodoProcesoRepository;
	
	/** El atributo o variable InNodoProcesoUsuario repository. */
	@Autowired
	private IInNodoProcesoUsuarioRepository inNodoProcesoUsuarioRepository;

	/** El atributo o variable IEjecutaFuncionHelper repository. */
	@Autowired
	IEjecutaFuncionHelper ejecutaFuncionHelper;
	
	/** El atributo o variable Vista Dato Actividad DAO. */
	@Autowired
	VwDatoActividadDAO vwDatoActividadDAO;
	
	/** El atributo o variable Vista Temporizador Helper. */
	@Autowired
	ITemporizadorHelper temporizadorHelper;
	

	
	
    @PersistenceContext
    EntityManager entityManager;
		
	// SP_LEE_VARIABLES_CADENA
	@Override
	public EstatusVariableCadenaTO leeVariablesCadena(String cadenaEntrada, List<VariableCadenaTO> variableCadenaList)
			throws BrioBPMException {
		
		boolean finBusqueda;
		Integer inicio;
		Integer fin;
		Integer longitud;
		String variable;
		
		
		//log.info("----------> cadenaEntrada: " + cadenaEntrada);
		// INICIALIZA CÓDIGO DE ERROR Y MENSAJE
		EstatusVariableCadenaTO result = EstatusVariableCadenaTO.builder()
				.tipoExcepcion(Constants.OK)
				.mensaje("")
				.build();
		
		//INICIALIZA VARIABLES
		inicio = 0;
		finBusqueda = false;
		
		//RECORRE LA CADENA DE ENTRADA PARA IDENTIFICAR LAS VARIABLES
		while (!finBusqueda) {
			
			// Log para mostrar la cadena de entrada actual
	        //log.debug("-------> CADENA ENTRADA: " + cadenaEntrada);
			
	        // Busca el inicio de la siguiente variable
	        inicio = cadenaEntrada.indexOf("@", inicio);
	        //log.debug("-------> INICIO: " + inicio);
	        
	        // Si no se encuentra más '@', termina la búsqueda
			if(inicio == -1) {
				finBusqueda = true;
				break;
			}
			
			// Busca el final de la variable actual
			fin = cadenaEntrada.indexOf("@", inicio + 1);
			//log.debug("-------> FIN: " + fin);
			if(fin == -1) {
				finBusqueda = true;
				break;
			}
			
			// Calcula la longitud de la variable
			longitud = fin - (inicio + 1);
			//log.debug("-------> LONGITUD: " + longitud);
			
			// Extrae el nombre de la variable
			//variable = cadenaEntrada.substring(inicio + 1 , longitud);
			variable = cadenaEntrada.substring(inicio + 1, inicio + 1 + longitud);
			//log.debug("-------> VARIABLE: " + variable);
			inicio = fin + 1;
			//log.debug("-------> INICIO: " + inicio);
			
			//INSERTA LA VARIABLE EN LA TABLA TEMPORAL
			VariableCadenaTO variableCadena = VariableCadenaTO.builder()
					.cveVariable(variable)
					.build();
			variableCadenaList.add(variableCadena);
			
			// Actualiza la lista de variables en el resultado
			result.setValoresLista(variableCadenaList);
		}
		return result;
	}
	
	
	// SP_CREA_FOLIO_IN_NODO
	@Override
	public EstatusVariablesTO creaFolio(DatosAutenticacionTO session, NodoTO nodo) throws BrioBPMException {	
		log.info("#################################################################################" );
		log.info("################### SP_CREA_FOLIO_IN_NODO_TEST ########################" );
		String cveProceso = nodo.getCveProceso();
		BigDecimal version = nodo.getVersion();
		String cveInstancia = nodo.getCveInstancia();
		String cveNodo = nodo.getCveNodo();
		Integer idNodo = nodo.getIdNodo();
		
		String cveEntidad = session.getCveEntidad();
		Integer folioActual;
		InFolioNodo folioNodo;
		Integer secuenciaNodo;
	
		// INICIALIZA CÓDIGO DE ERROR, MENSAJE Y SUGERENCIA
		EstatusVariablesTO result = EstatusVariablesTO.builder()
				.tipoExcepcion(Constants.OK)
				.mensaje("")
				.build();
		String idProceso = "CREA_FOLIO_IN_NODO";

		// CONCATENA LOS PARÁMETROS Y SUS VALORES PARA DESPLEGARLOS EN CASO DE ERROR
		String variablesValores = Constants.CVE_PROCESO + cveProceso + "|" +
		Constants.VERSION + version + "|" +
		Constants.CVE_INSTANCIA + cveInstancia + "|" +
		Constants.CVE_NODO + cveNodo + "|" +
		Constants.ID_NODO + idNodo;
		
		log.debug("----->I_SECUENCIA_NODO DE ENTRADA EN SP_CREA_FOLIO_IN_NODO_TEST, ESTA ES UNA SALIDA: ");
		// INICIALIZA LA CLAVE DE LA INSTANCIA
		secuenciaNodo = 0;
		folioActual = 0;
		log.info("----->I_SECUENCIA_NODO DE ENTRADA EN SP_CREA_FOLIO_IN_NODO_TES: " + secuenciaNodo);

		// BUSCA UN FOLIO EN LA TABLA IN_FOLIO_NODO
	    // Crea un objeto InFolioNodoPK con las claves necesarias para buscar un registro en la tabla
		InFolioNodoPK id = InFolioNodoPK.builder()
				.cveEntidad(cveEntidad)
				.cveProceso(cveProceso)
				.version(version)
				.cveInstancia(cveInstancia)
				.cveNodo(cveNodo)
				.idNodo(idNodo)
				.build();
		log.info("----->id: " + id.toString());
		
		// Busca el folio correspondiente en el repositorio
		Optional<InFolioNodo> folioNodoOptional = folioNodoRepository.findById(id);
		
		if (!folioNodoOptional.isPresent()) {
			// Si no existe un folio, inicializa uno nuevo
			folioActual = 1;
			folioNodo = InFolioNodo.builder()
					.id(id)
					.folio(folioActual)
					.build();
			
			// Guarda el nuevo folio en la base de datos
			log.info("PREPARA EN FOLIO NODO EN NODO HELPER PRESENTE - creaFolio");
			folioNodoRepository.saveAndFlush(folioNodo);
			log.info("GUARDADO EN FOLIO NODO EN NODO HELPER PRESENTE - creaFolio");
			
			// Verifica si el nuevo folio se ha guardado correctamente
			folioNodoOptional = folioNodoRepository.findById(id);
	
			if (folioNodoOptional.isPresent()) {
				 // Si el folio se ha creado exitosamente, establece la secuencia del nodo
				secuenciaNodo = 1;
				log.debug("----->I_SECUENCIA_NODO IGUAL A 1 SI  @CH_TIPO_EXCEPCION = ''OK''': " + secuenciaNodo);
				result.setSecuenciaNodo(secuenciaNodo);
				return result;
			} else {
				 // Si ocurre un error al crear el folio, genera un mensaje de error
				String mensaje = messagesService.getMessage(session,
						idProceso,
						"ERR-INS-TAB-IN_FOLIO_NODO",
						variablesValores);
				result.setTipoExcepcion(Constants.ERROR);
				result.setMensaje(mensaje);
			}
		} else {
			 // Si el folio ya existe, actualiza el folio actual
			log.debug("----->folioNodoOptional presente " );
			folioNodo = folioNodoOptional.get();
			folioActual = folioNodo.getFolio() + 1;
			folioNodo.setFolio(folioActual);
			
			// Guarda los cambios en el folio actual
			log.info("PREPARA EN FOLIO NODO EN NODO HELPER NUEVO - creaFolio");
			folioNodoRepository.saveAndFlush(folioNodo);
			log.info("GUARDADO EN FOLIO NODO EN NODO HELPER NUEVO - creaFolio");
			
			if (folioNodo.getFolio() == folioActual) {
				// Si la actualización es exitosa, establece la secuencia del nodo
				secuenciaNodo = folioActual;
				result.setSecuenciaNodo(secuenciaNodo);
				log.debug("----->I_SECUENCIA_NODO IGUAL A I_FOLIO_ACTUAL SI  @CH_TIPO_EXCEPCION = ''OK'': " + secuenciaNodo);

				return result;
			} else {
				// Si ocurre un error al actualizar el folio, genera un mensaje de error
				log.debug("----->ERROR " );
				result.setTipoExcepcion(Constants.ERROR);
				String mensaje = messagesService.getMessage(
						session,
						idProceso,
						"ERR-UPD-TAB-IN_FOLIO_NODO", variablesValores);
				result.setTipoExcepcion(Constants.ERROR);
				result.setMensaje(mensaje);
			}
		}

		 // Registra en el log la secuencia del nodo creada
		log.debug("crea folio in Nodo SECUENCIA NODO: " + result.getSecuenciaNodo());
		return result;
	}
	
	
	// SP_INS_IN_NODO
	@Override
	public EstatusTO insertaInNodo(DatosAutenticacionTO session,  NodoTO nodo, NodoProcesoTO nodoProceso, List<String> usuarioAsignado )
			throws BrioBPMException {
		
		log.info("#################################################################################" );
		log.info("################### SP_INS_IN_NODO_TEST ########################" );
		
		// Obtiene la clave de la entidad, localidad y usuario de la sesión de autenticación
	    String cveEntidad = session.getCveEntidad();
	    String cveLocalidad = session.getCveLocalidad();
	    String usuario = session.getCveUsuario();

	    // Obtiene información del nodo
	    String idProceso = nodo.getIdProceso();
	    String cveProceso = nodo.getCveProceso();
	    BigDecimal version = nodo.getVersion();
	    String cveInstancia = nodo.getCveInstancia();
	    String cveNodo = nodo.getCveNodo();
	    Integer idNodo = nodo.getIdNodo();
	    Integer secuenciaNodo = nodo.getSecuenciaNodo();

	    // Obtiene información del proceso del nodo
		String estado = nodoProceso.getEstado();
		String origen = nodoProceso.getOrigen();
		String cveProcesoOrigen = nodoProceso.getCveProcesoOrigen();
		String cveInstanciaOrigen = nodoProceso.getCveInstanciaOrigen();
		String cveNodoOrigen = nodoProceso.getCveNodoOrigen();
		String rolCreador = nodoProceso.getRolCreador();
		String rolBloquea = nodoProceso.getRolBloquea();
		String usuarioBloquea = nodoProceso.getUsuarioBloquea();
		String comentario = nodoProceso.getComentario();
		Date fechaCreacion = nodoProceso.getFechaCreacion();
		Date fechaBloquea = nodoProceso.getFechaBloquea();
		Date fechaLimite = nodoProceso.getFechaLimite();
		Date fechaEstadoActual = nodoProceso.getFechaEstadoActual();
		Date fechaFinEspera = nodoProceso.getFechaFinEspera();
		BigDecimal versionOrigen = nodoProceso.getVersionOrigen();
		Integer idNodoOrigen = nodoProceso.getIdNodoOrigen();
		Integer secuenciaNodoOrigen = nodoProceso.getSecuenciaNodoOrigen();
	
		// INICIALIZA CÓDIGO DE ERROR, MENSAJE Y SUGERENCIA
		EstatusTO result = EstatusTO.builder()
				.tipoExcepcion(Constants.OK)
				.mensaje("")
				.build();
		
		// Concatena variables y valores relevantes en un solo String para la generación de mensajes
		String variablesValores = Constants.CVE_PROCESO + cveProceso + "|" +
				Constants.VERSION + version + "|" +
				Constants.CVE_INSTANCIA + cveInstancia + "|" +
				Constants.CVE_NODO + cveNodo + "|" +
				Constants.ID_NODO + idNodo + "|" +
				Constants.SECUENCIA_NODO + secuenciaNodo + "|" +
				Constants.ESTADO + estado + "|" +
				Constants.FECHA_CREACION + fechaCreacion + "|" +
				Constants.ORIGEN + origen + "|" +
				Constants.CVE_PROCESO_ORIGEN + cveProcesoOrigen + "|" +
				Constants.VERSION_ORIGEN + versionOrigen + "|" +
				Constants.CVE_INSTANCIA_ORIGEN + cveInstanciaOrigen + "|" +
				Constants.CVE_NODO_ORIGEN + cveNodoOrigen + "|" +
				Constants.ID_NODO_ORIGEN + idNodoOrigen + "|" +
				Constants.SECUENCIA_NODO_ORIGEN + secuenciaNodoOrigen + "|" +
				Constants.ROL_CREADOR + rolCreador + "|" +
				Constants.USUARIO_CREADOR + usuario + "|" +
				Constants.ROL_BLOQUEA + rolBloquea + "|" +
				Constants.USUARIO_BLOQUEA + usuarioBloquea + "|" +
				Constants.FECHA_BLOQUEA + fechaBloquea + "|" +
				Constants.FECHA_LIMITE + fechaLimite + "|" +
				Constants.FECHA_ESTADO_ACTUAL + fechaEstadoActual + "|" +
				Constants.FECHA_FIN_ESPERA + fechaFinEspera + "|" +
				Constants.COMENTARIO + comentario;

		// SVM ajuste para integrar el parametro USUARIO_ASIGNADO
		// Construye una clave primaria compuesta para el nuevo nodo de proceso
		InNodoProcesoPK id = InNodoProcesoPK.builder()
				.cveEntidad(cveEntidad)
				.cveProceso(cveProceso)
				.version(version)
				.cveInstancia(cveInstancia)
				.cveNodo(cveNodo)
				.idNodo(idNodo)
				.secuenciaNodo(secuenciaNodo)
				.build();
		
		// Construye la entidad InNodoProceso a insertar en la base de datos
		InNodoProceso entity = InNodoProceso.builder()
				.id(id)
				.estado(estado)
				.fechaCreacion(fechaCreacion)
				.origen(origen)
				.cveProcesoOrigen(cveProcesoOrigen)
				.versionOrigen(versionOrigen)
				.cveInstanciaOrigen(cveInstanciaOrigen)
				.cveNodoOrigen(cveNodoOrigen)
				.idNodoOrigen(idNodoOrigen)
				.secuenciaNodoOrigen(secuenciaNodoOrigen)
				.rolCreador(rolCreador)
				.cveEntidadCreadora(cveEntidad)
				.cveLocalidadCreadora(cveLocalidad)
				.usuarioCreador(usuario)
				.rolBloquea(rolBloquea)
				.usuarioBloquea(usuarioBloquea)
				.fechaBloquea(fechaBloquea)
				.fechaLimite(fechaLimite)
				.fechaFinEspera(fechaFinEspera)
				.fechaEstadoActual(fechaEstadoActual)
				.comentario(comentario)
				.prioridad(null) // Campo prioridad inicializado como null
				.build();

		// Guarda la entidad en la base de datos y asegura que se escriban los cambios inmediatamente
		inNodoProcesoRepository.saveAndFlush(entity);
	    
	    // Busca la entidad recién insertada para confirmar su existencia
		Optional<InNodoProceso> nodoP = inNodoProcesoRepository.findById(id);
		if (nodoP.isPresent()) {
			 // Si la entidad está presente, establece el tipo de excepción como OK
			result.setTipoExcepcion(Constants.OK);	
			
			//svm ajuste para asignar varios usuarios a un proceso inNodoProceso
			if (usuarioAsignado != null) {
				for (String dato : usuarioAsignado) {
					InNodoProcesoUsuarioPK pk = InNodoProcesoUsuarioPK.builder()
							.cveEntidad(cveEntidad)
							.cveProceso(cveProceso)
							.version(version)
							.cveInstancia(cveInstancia)
							.cveNodo(cveNodo)
							.idNodo(idNodo)
							.secuenciaNodo(secuenciaNodo)
							.cveUsuario(dato)
							.build();
					
					InNodoProcesoUsuario inp = InNodoProcesoUsuario.builder()
							.id(pk)
							.build();
					inNodoProcesoUsuarioRepository.saveAndFlush(inp);
				}
			}			
			
		} else {
			 // Si la entidad no está presente, genera un mensaje de error utilizando el servicio de mensajes
			String mensaje = messagesService.getMessage(
					session,
					idProceso,
					"ERR-INS-TAB-IN_NODO_PROCESO", // Código de error para la inserción fallida
	                variablesValores); // Variables y valores usados para el mensaje de error
	        
	        // Establece el tipo de excepción como ERROR y asigna el mensaje de error generado
	        result.setTipoExcepcion(Constants.ERROR);
	        result.setMensaje(mensaje);
			
			// Retorna el resultado indicando la falla
	        return result;
	    }
	    
	    // Retorna el resultado indicando éxito
		return result;
	}
	
	// SP_LEE_CONFIGURACION_ENVIO
	@Override
	public EstatusConfiguracionEnvioTO leeConfiguracionEnvio(DatosAutenticacionTO session, 
			NodoTO nodo) throws BrioBPMException {
		
		String cveUsuario = session.getCveUsuario();
		String cveEntidad = session.getCveEntidad();
		String cveLocalidad = session.getCveLocalidad();
		String idProceso = nodo.getIdProceso();
		String cveProceso = nodo.getCveProceso();
		BigDecimal version = nodo.getVersion();
		String cveInstancia = nodo.getCveInstancia();
		String cveNodo = nodo.getCveNodo();
		Integer idNodo = nodo.getIdNodo();
		Integer secuenciaNodo = nodo.getSecuenciaNodo();
		
		// INICIALIZA CÓDIGO DE ERROR, MENSAJE Y SUGERENCIA
		EstatusConfiguracionEnvioTO result = EstatusConfiguracionEnvioTO.builder()
					.tipoExcepcion(Constants.OK)
					.mensaje("")
					.build();
		
		// Se concatenan los valores de las variables para formar un String de parámetros
		// que se utilizará en mensajes de configuración.
		String variablesValores = Constants.CVE_USUARIO + cveUsuario + "|" +
				Constants.CVE_ENTIDAD + cveEntidad + "|" +
				Constants.CVE_LOCALIDAD + cveLocalidad + "|" +
				Constants.CVE_PROCESO + cveProceso + "|" +
				Constants.VERSION + version + "|" + 
				Constants.CVE_INSTANCIA + cveInstancia + "|" +
				Constants.CVE_NODO + cveNodo + "|" +
				Constants.ID_NODO + idNodo + "|" +
				Constants.SECUENCIA_NODO + secuenciaNodo;
		
		// LEE CONFIGURACIÓN DEL MENSAJE
		// Crea una clave primaria compuesta para identificar el mensaje de configuración
		// que se va a buscar en la base de datos.
		StMensajeEnvioPK id = StMensajeEnvioPK.builder()
				.cveEntidad(cveEntidad)
				.cveProceso(cveProceso)
				.version(version)
				.cveNodo(cveNodo)
				.idNodo(idNodo)
				.build();
		
		// Se intenta obtener el mensaje de configuración de envío desde el repositorio
		// utilizando el ID compuesto.
		Optional<StMensajeEnvio> mensajeEnvio = iStMensajeEnvioRepository.findById(id);
		
		// Si el mensaje de configuración no está presente, se crea un mensaje de error
		// y se devuelve el objeto result con el código de error y el mensaje configurado.
		if(!mensajeEnvio.isPresent()) {
			String mensaje = messagesService.getMessage(
					session,
					idProceso,
					"SIN_CONF_MENSAJE_ENVIO",
					variablesValores);
			
			 // Se establece el tipo de excepción como ERROR y se asigna el mensaje obtenido.
		    result.setTipoExcepcion(Constants.ERROR);
		    result.setMensaje(mensaje);
		    return result;  // Devuelve el resultado con el error.
		} else {
		    // Si el mensaje de configuración está presente, se obtiene el objeto StMensajeEnvio.
		    StMensajeEnvio mensajeEnv = mensajeEnvio.get();

		    // Se actualizan los atributos del objeto result con la información del mensaje obtenido.
		    result.setAmbitoEnvio(mensajeEnv.getAmbitoEnvio());  // Ámbito de envío del mensaje.
		    result.setIniciarProcesoDestino(mensajeEnv.getIniciarProcesoDestino());  // Proceso de destino a iniciar.
		    result.setCveEntidadDestino(mensajeEnv.getCveEntidadDestino());  // Clave de entidad de destino.
		    result.setCveProcesoDestino(mensajeEnv.getCveProcesoDestino());  // Clave de proceso de destino.
		    result.setVersionDestino(mensajeEnv.getVersionDestino());  // Versión de destino.
		    result.setCveNodoDestino(mensajeEnv.getCveNodoDestino());  // Clave de nodo de destino.
		    result.setIdNodoDestino(mensajeEnv.getIdNodoDestino());  // Identificador de nodo de destino.
		    result.setVariablesReferenciaEnv(mensajeEnv.getVariablesReferenciaEnvio());  // Variables de referencia para el envío.
		}

		// Devuelve el resultado final del proceso de configuración de envío.
		return result;
	}
	
	// SP_LEE_CONFIGURACION_RECEPCION
	@Override
	public EstatusVariablesTO leeConfiguracionRecepcion(DatosAutenticacionTO session, NodoTO nodo)
			throws BrioBPMException {
		
	// Se extraen los datos del nodo actual para su uso en la configuración y verificación de mensajes.
	String idProceso = nodo.getIdProceso();     // Identificador del proceso actual.
	String cveProceso = nodo.getCveProceso();   // Clave del proceso actual.
	BigDecimal version = nodo.getVersion();     // Versión del proceso.
	String cveInstancia = nodo.getCveInstancia(); // Clave de la instancia actual.
	String cveNodo = nodo.getCveNodo();         // Clave del nodo actual.
	Integer idNodo = nodo.getIdNodo();          // Identificador del nodo.
	Integer secuenciaNodo = nodo.getSecuenciaNodo(); // Secuencia del nodo.

	// Se extraen los datos del usuario y la entidad desde el objeto de sesión.
	String cveUsuario = session.getCveUsuario(); // Clave del usuario actual.
	String cveEntidad = session.getCveEntidad(); // Clave de la entidad.
	String cveLocalidad = session.getCveLocalidad(); // Clave de la localidad.

	// Inicializa un objeto EstatusVariablesTO para almacenar el estado del proceso.
	// Se configura inicialmente como exitoso (OK) con un mensaje vacío.
	EstatusVariablesTO result = EstatusVariablesTO.builder()
	        .tipoExcepcion(Constants.OK) // Código de éxito inicial.
	        .mensaje("") // Mensaje vacío por defecto.
	        .build();

	// Construye una cadena de texto con valores de variables importantes,
	// separadas por "|", para utilizarlas en los mensajes de configuración.
	String variablesValores = Constants.CVE_USUARIO + cveUsuario + "|" +
	        Constants.CVE_ENTIDAD + cveEntidad + "|" +
	        Constants.CVE_LOCALIDAD + cveLocalidad + "|" +
	        Constants.CVE_PROCESO + cveProceso + "|" +
	        Constants.VERSION + version + "|" +
	        Constants.CVE_INSTANCIA + cveInstancia + "|" +
	        Constants.CVE_NODO + cveNodo + "|" +
	        Constants.ID_NODO + idNodo + "|" +
	        Constants.SECUENCIA_NODO + secuenciaNodo;

	// Crea una clave primaria compuesta (PK) para identificar el mensaje de recepción
	// que se va a buscar en el repositorio.
	StMensajeRecepcionPK id = StMensajeRecepcionPK.builder()
	        .cveEntidad(cveEntidad) // Clave de la entidad del mensaje.
	        .cveProceso(cveProceso) // Clave del proceso del mensaje.
	        .version(version) // Versión del mensaje.
	        .cveNodo(cveNodo) // Clave del nodo del mensaje.
	        .idNodo(idNodo) // Identificador del nodo del mensaje.
	        .build();

	// Intenta obtener el mensaje de recepción desde el repositorio utilizando la clave primaria.
	Optional<StMensajeRecepcion> mensajeRecepcion = iStMensajeRecepcionRepository.findById(id);

	// Verifica si el mensaje de recepción no está presente.
	if (!mensajeRecepcion.isPresent()) {
	    // Obtiene un mensaje de error utilizando el servicio de mensajes,
	    // pasando la sesión, el id del proceso, un código de error,
	    // y la cadena de valores de variables.
	    String mensaje = messagesService.getMessage(
	            session, idProceso,
	            "SIN_CONF_MENSAJE_RECEPCION", variablesValores);

	    // Establece el tipo de excepción como ERROR y asigna el mensaje obtenido.
	    result.setTipoExcepcion(Constants.ERROR);
	    result.setMensaje(mensaje);

	    // Registra un mensaje informativo en los logs indicando que el mensaje de recepción no está presente.
	    log.info("Mensaje Recepcion NO presente ");
	    return result; // Devuelve el resultado con el error.
	}

	// Actualiza el resultado con las variables de referencia de recepción obtenidas del mensaje.
	result.setVariablesReferenciaRec(mensajeRecepcion.get().getVariablesReferenciaRec());

	// Devuelve el resultado final, que contiene la configuración del mensaje de recepción.
	return result;
	}

	//SP_CREA_VARIABLES_ENVIO
	@Override
	public EstatusTO creaVariablesEnvio(DatosAutenticacionTO session, NodoTO nodo)
			throws BrioBPMException, ParseException {
		
		String cveEntidad = session.getCveEntidad();
		String cveLocalidad = session.getCveLocalidad();
		String idProceso = nodo.getIdProceso();
		String cveProceso = nodo.getCveProceso();
		BigDecimal version = nodo.getVersion();
		String cveInstancia = nodo.getCveInstancia();
		String cveNodo = nodo.getCveNodo();
		Integer idNodo = nodo.getIdNodo();
		Integer secuenciaNodo = nodo.getSecuenciaNodo();
		
		EstatusTO result = EstatusTO.builder()
				.tipoExcepcion(Constants.OK)
				.mensaje("")
				.build();
		
		EstatusVariablesTO resultVariables = EstatusVariablesTO.builder()
				.tipoExcepcion(Constants.OK)
				.mensaje("")
				.build();
		
		String variablesValores = Constants.CVE_USUARIO + session.getCveUsuario() + "|" +
				Constants.CVE_ENTIDAD + cveEntidad + "|" +
				Constants.CVE_LOCALIDAD + cveLocalidad + "|" +
				Constants.CVE_PROCESO + cveProceso + "|" +
				Constants.VERSION + version + "|" +
				Constants.CVE_INSTANCIA + cveInstancia + "|" +
				Constants.CVE_NODO + cveNodo + "|" +
				Constants.ID_NODO + idNodo + "|" +
				Constants.SECUENCIA_NODO + secuenciaNodo;
	
		Integer existeVariable = iStVariableEnvioRepository.verificaExisteVariables(
				cveEntidad,
				cveProceso,
				version,
				cveNodo,
				idNodo);
		
		if(existeVariable == null) {
			result.setTipoExcepcion(Constants.ERROR);
			return result;
		}
		
		List<StVariableEnvio> listaVariables = iStVariableEnvioRepository.variablesParaAplicarReemplazo(
				cveEntidad,
				cveProceso,
				version,
				cveNodo,
				idNodo);
		
		for(StVariableEnvio stVariableEnv : listaVariables) {
			if (stVariableEnv.getCveVaribleDestino().substring(0, 4).equals(Constants.VPRO)) {
				// SP_LEE_VALOR_VPRO-SP_CREA_VARIABLES_ENVIO
				nodo.setCveVariable(stVariableEnv.getId().getCveVariable());
				resultVariables = leerValorVpro(
						session, nodo, // agregacion,
						"", null);
			} else if (stVariableEnv.getCveVaribleDestino().substring(0, 4).equals(Constants.VLOC)) {
				// SP_LEE_VALOR_VLOC
				resultVariables = leerValorVeloc(
						session, cveProceso, version, stVariableEnv.getId().getCveVariable());
			} else if (stVariableEnv.getCveVaribleDestino().substring(0, 4).equals(Constants.VENT)) {
				// SP_LEE_VALOR_VENT
				resultVariables = leerValorVent(
						session, cveProceso, version, stVariableEnv.getId().getCveVariable());
			} else if (stVariableEnv.getCveVaribleDestino().substring(0, 4).equals(Constants.VSIS)) {
				// SP_LEE_VALOR_VSIS
				resultVariables = leerValorVsis(
						session, cveProceso, version, stVariableEnv.getId().getCveVariable());
			} else if (stVariableEnv.getCveVaribleDestino().substring(0, 4).equals(Constants.DA)
					&& cveNodo != null && !cveNodo.isEmpty() && idNodo != null && idNodo != 0
					&& secuenciaNodo != null && secuenciaNodo != 0) {
				// SP_LEE_VALOR_VPN
				resultVariables = leerValorVpn(
						session, nodo, stVariableEnv.getId().getCveVariable());
			}
			
			if(resultVariables.getTipoExcepcion().equals(Constants.ERROR)) {
				return result;
			}
			
			InVariableEnvioPK id = InVariableEnvioPK.builder()
					.cveEntidad(cveEntidad)
					.cveProceso(cveProceso)
					.version(version)
					.cveNodo(cveNodo)
					.idNodo(idNodo)
					.secuenciaNodo(secuenciaNodo)
					.cveVariable(stVariableEnv.getId().getCveVariable())
					.build();
			InVariableEnvio entity = InVariableEnvio.builder()
					.id(id)
					.cveVariableDestino(stVariableEnv.getCveVaribleDestino())
					.valorAlfanumerico(Normalizer.normalize(resultVariables.getValorAlfanumerico(), Normalizer.Form.NFC))
					.valorEntero(resultVariables.getValorEntero())
					.valorDecimal(resultVariables.getValorDecimal())
					.valorFecha(resultVariables.getValorFecha())
					.build();
			
			iInVariableEnvioRepository.save(entity);
			Optional<InVariableEnvio> entidadGuardad = iInVariableEnvioRepository.findById(id);
			
			if(!entidadGuardad.isPresent()) {
				String mensaje = messagesService.getMessage(
						session,
						idProceso,
						"ERR-INS-TAB-IN_VARIABLE_MENSAJE",
						variablesValores);
				result.setTipoExcepcion(Constants.ERROR);
				result.setMensaje(mensaje);
			}
		}
		
		return result;
	}
	
	// SP_LEE_VALOR_VPRO
	@Override
	public EstatusVariablesTO leerValorVpro(DatosAutenticacionTO session, NodoTO nodo, String agregacion,
			Integer secuenciaDocumento) throws BrioBPMException{
	
		//log.info("---------- leerValorVpro ----------");
		//log.info("---------- session: " + session);
		//log.info("---------- nodo: " + nodo);
		//log.info("---------- agregacion: " + agregacion);
		//log.info("---------- secuenciaDocumento: " + secuenciaDocumento);
		
		String cveProceso = nodo.getCveProceso();
		BigDecimal version = nodo.getVersion();
		String cveInstancia = nodo.getCveInstancia();
		String cveVariable = nodo.getCveVariable();
		Integer ocurrencia = nodo.getOcurrencia();
		String cveEntidad = session.getCveEntidad();

		// INICIALIZA CÓDIGO DE ERROR, MENSAJE Y SUGERENCIA
		EstatusVariablesTO result = EstatusVariablesTO.builder()
				.tipoExcepcion(Constants.OK)
				.mensaje("")
				.build();
		
		// INICIALIZA LAS VARIABLES DE SALIDA
		result.setTipo(null);
		result.setDecimales(null);
		result.setFormatoFecha(null);
		result.setValorAlfanumerico(null);
		result.setValorEntero(null);
		result.setValorDecimal(null);
		result.setValorFecha(null);
		
		// OBTIENE EL VALOR DE LA VARIABLE DEL PROCESO
		if (agregacion != null && (agregacion.equals(Constants.MAXIMO) || agregacion.equals(Constants.MINIMO) ||
			    agregacion.equals(Constants.SUMA) || agregacion.equals(Constants.PROMEDIO))) {
			
		Integer valorEntero = null;
		BigDecimal valorDecimal = null;
		Date valorFecha = null;
				
		switch(agregacion) {		
		case Constants.MAXIMO:
			valorEntero = inVariableProcesoRepository.encuentraMaxEntero(
					cveEntidad,
					cveProceso,
					version,
					cveVariable,
					cveInstancia,
					ocurrencia);
			
			valorDecimal = inVariableProcesoRepository.encuentraMaxDecimal(
					cveEntidad,
					cveProceso,
					version,
					cveVariable,
					cveInstancia,
					ocurrencia);
			
			valorFecha = inVariableProcesoRepository.encuentraMaxFecha(
					cveEntidad,
					cveProceso,
					version,
					cveVariable,
					cveInstancia,
					ocurrencia);
			break;
		case Constants.MINIMO:
			valorEntero = inVariableProcesoRepository.encuentraMinEntero(
					cveEntidad,
					cveProceso,
					version,
					cveVariable,
					cveInstancia,
					ocurrencia);
			
			valorDecimal = inVariableProcesoRepository.encuentraMinDecimal(
					cveEntidad,
					cveProceso,
					version,
					cveVariable,
					cveInstancia,
					ocurrencia);
			valorFecha = inVariableProcesoRepository.encuentraMinFecha(
					cveEntidad,
					cveProceso,
					version,
					cveVariable,
					cveInstancia,
					ocurrencia);
			break;
		case Constants.SUMA:
			valorDecimal = inVariableProcesoRepository.encuentraSumaDecimal(
					cveEntidad,
					cveProceso,
					version,
					cveVariable,
					cveInstancia,
					ocurrencia);

			valorEntero = inVariableProcesoRepository.encuentraSumaEntero(
					cveEntidad,
					cveProceso,
					version,
					cveVariable,
					cveInstancia,
					ocurrencia);
			break;
		case Constants.PROMEDIO:
			valorDecimal = inVariableProcesoRepository.encuentraPromedioDecimal(
					cveEntidad,
					cveProceso,
					version,
					cveVariable,
					cveInstancia,
					ocurrencia);
			
			valorEntero = inVariableProcesoRepository.encuentraPromedioEntero(
					cveEntidad,
					cveProceso,
					version,
					cveVariable,
					cveInstancia,
					ocurrencia);
			break;
				
		}	
		valorEntero = (valorEntero == null) ? 0 : valorEntero;
		valorDecimal = (valorDecimal == null) ? BigDecimal.ZERO : valorDecimal;

		result.setValorEntero(valorEntero);
		result.setValorDecimal(valorDecimal);
		result.setValorFecha(valorFecha);
		
		} else {
			if(cveVariable != Constants.VPRO_01_FECHA_VIGENCIA_DOCUMENTO) {
				List<InVariableProceso> inVariablePros = inVariableProcesoRepository.obtenerVariableProceso(
						cveEntidad,
						cveProceso,
						version,
						cveInstancia,
						cveVariable,
						ocurrencia);
				
				if(inVariablePros.isEmpty()) {
					inVariablePros = inVariableProcesoRepository.obtenerVariableProcesoSinOcurrencia(
							cveEntidad,
							cveProceso,
							version,
							cveInstancia,
							cveVariable);
					if(inVariablePros.isEmpty()) {
						result.setValorAlfanumerico(null);
						result.setValorEntero(null);
						result.setValorDecimal(null);
						result.setValorFecha(null);
					} else {
						InVariableProceso inVariablePro = inVariablePros.get(0);
						result.setValorAlfanumerico(inVariablePro.getValorAlfanumerico());
						result.setValorEntero(inVariablePro.getValorEntero());
						result.setValorDecimal(inVariablePro.getValorDecimal());
						result.setValorFecha(inVariablePro.getValorFecha());					
					}
				} else {
					InVariableProceso inVariablePro = inVariablePros.get(0);
					result.setValorAlfanumerico(inVariablePro.getValorAlfanumerico());
					result.setValorEntero(inVariablePro.getValorEntero());
					result.setValorDecimal(inVariablePro.getValorDecimal());
					result.setValorFecha(inVariablePro.getValorFecha());
				}
			
			}else {
				List<InVariableProceso> inVariablePros = inVariableProcesoRepository.obtenerVariableProceso(
						cveEntidad,
						cveProceso,
						version,
						cveInstancia,
						cveVariable,
						secuenciaDocumento);
				
				if(inVariablePros.isEmpty()) {
					inVariablePros = inVariableProcesoRepository.obtenerVariableProcesoSinOcurrencia(
							cveEntidad,
							cveProceso,
							version,
							cveInstancia,
							cveVariable);
					if(inVariablePros.isEmpty()) {
						result.setValorAlfanumerico(null);
						result.setValorEntero(null);
						result.setValorDecimal(null);
						result.setValorFecha(null);
					} else {
						InVariableProceso inVariablePro = inVariablePros.get(0);
						result.setValorAlfanumerico(inVariablePro.getValorAlfanumerico());
						result.setValorEntero(inVariablePro.getValorEntero());
						result.setValorDecimal(inVariablePro.getValorDecimal());
						result.setValorFecha(inVariablePro.getValorFecha());					
					}

				} else {
					InVariableProceso inVariablePro = inVariablePros.get(0);
					result.setValorAlfanumerico(inVariablePro.getValorAlfanumerico());
					result.setValorEntero(inVariablePro.getValorEntero());
					result.setValorDecimal(inVariablePro.getValorDecimal());
					result.setValorFecha(inVariablePro.getValorFecha());
				}
			}
		}
		
		StVariableProcesoPK idS = StVariableProcesoPK.builder()
				.cveEntidad(cveEntidad)
				.cveProceso(cveProceso)
				.version(version)
				.cveVariable(cveVariable)
				.build();
		/*if(cveVariable == null) {
			log.info("estatus: cveVariable {} ", cveVariable);
		
		}*/
		Optional<StVariableProceso> variableProceso = iStVariableProcesoRepository.findById(idS);
		if(!variableProceso.isPresent()) {
			result.setTipo(null);
			result.setDecimales(null);
			result.setFormatoFecha(null);
		} else {
			result.setTipo(variableProceso.get().getTipo());
			result.setDecimales(variableProceso.get().getDecimales());
			result.setFormatoFecha(variableProceso.get().getFormatoFecha());
		}
		
		return result;
	}

	
	// SP_LEE_VALOR_VLOC
	@Override
	public EstatusVariablesTO leerValorVeloc(DatosAutenticacionTO session, String cveProceso, BigDecimal version, String cveVariable) throws BrioBPMException {
			
			String cveEntidad = session.getCveEntidad();
			String cveLocalidad = session.getCveLocalidad();
			
			// INICIALIZA CÓDIGO DE ERROR, MENSAJE Y SUGERENCIA
			EstatusVariablesTO result = EstatusVariablesTO.builder()
					.tipoExcepcion(Constants.OK)
					.mensaje("")
					.build();
		
			// INICIALIZA LAS VARIABLES DE SALIDA
			result.setTipo(null);
			result.setDecimales(null);
			result.setValorAlfanumerico(null);
			result.setValorEntero(null);
			result.setValorDecimal(null);
			result.setValorFecha(null);
			
			VariableLocalidadPK id = VariableLocalidadPK.builder()
					.cveEntidad(cveEntidad)
					.cveLocalidad(cveLocalidad)
					.cveVariable(cveVariable)
					.build();
			//OBTIENE EL VALOR DE LA VARIABLE A NIVEL DE LOCALIDAD
			Optional<VariableLocalidad> variableNivelLocalidad = iVariableLocalidadRepository.findById(id);
			
			result.setTipo(variableNivelLocalidad.get().getTipo());
			result.setDecimales(variableNivelLocalidad.get().getDecimales());
			result.setValorAlfanumerico(variableNivelLocalidad.get().getValorAlfanumerico());
			result.setValorEntero(variableNivelLocalidad.get().getValorEntero());
			result.setValorDecimal(variableNivelLocalidad.get().getValorDecimal());
			result.setValorFecha(variableNivelLocalidad.get().getValorFecha());
			
			if(!variableNivelLocalidad.isPresent()) {
				result.setTipo(null);
				result.setDecimales(null);
				result.setFormatoFecha(null);
				result.setValorAlfanumerico(null);
				result.setValorEntero(null);
				result.setValorDecimal(null);
				result.setValorFecha(null);
			}
			return result;
		}
	
	// SP_LEE_VALOR_VENT
	@Override
	public EstatusVariablesTO leerValorVent(DatosAutenticacionTO session, String cveProceso,
			BigDecimal version, String cveVariable) throws BrioBPMException {
		
			String cveEntidad = session.getCveEntidad();
			
			// INICIALIZA CÓDIGO DE ERROR, MENSAJE Y SUGERENCIA
			EstatusVariablesTO result = EstatusVariablesTO.builder()
					.tipoExcepcion(Constants.OK)
					.mensaje("")
					.build();
			
			// INICIALIZA LAS VARIABLES DE SALIDA
			result.setValorAlfanumerico(null);
			result.setValorEntero(null);
			result.setValorDecimal(null);
			result.setValorFecha(null);
			
			//OBTIENE EL VALOR DE LA VARIABLE A NIVEL DE LOCALIDAD
			VariableEntidadPK id = VariableEntidadPK.builder()
					.cveEntidad(cveEntidad)
					.cveVariable(cveVariable)
					.build();

			Optional<VariableEntidad> variableNivelLocalidad = iVariableEntidadRepository.findById(id);
			
			result.setTipoDato(variableNivelLocalidad.get().getTipo());
			result.setDecimales(variableNivelLocalidad.get().getDecimales());
			result.setFormatoFecha(variableNivelLocalidad.get().getFormatoFecha());
			result.setValorAlfanumerico(variableNivelLocalidad.get().getValorAlfanumerico());
			result.setValorEntero(variableNivelLocalidad.get().getValorEntero());
			result.setValorDecimal(variableNivelLocalidad.get().getValorDecimal());
			result.setValorFecha(variableNivelLocalidad.get().getValorFecha());
			
			if(!variableNivelLocalidad.isPresent()) {
				result.setTipo(null);
				result.setDecimales(null);
				result.setValorAlfanumerico(null);
				result.setValorEntero(null);
				result.setValorDecimal(null);
				result.setValorFecha(null);
			}
			return result;
		}
	
	// SP_LEE_VALOR_VSIS
	@Override
	public EstatusVariablesTO leerValorVsis(DatosAutenticacionTO session,
			String cveProceso, BigDecimal version, String cveVariable) throws BrioBPMException {

		String tipo;
		String funcionVariable;
		
		// INICIALIZA CÓDIGO DE ERROR, MENSAJE Y SUGERENCIA
		EstatusVariablesTO result = EstatusVariablesTO.builder()
				.tipoExcepcion(Constants.OK)
				.mensaje("")
				.build();
		
		//INICIALIZA LAS VARIABLES DE SALIDA
		result.setTipo(null);
		result.setValorAlfanumerico(null);
		result.setValorEntero(null);
		result.setValorDecimal(null);
		result.setValorFecha(null);
		
		//OBTIENE EL VALOR DE LA VARIABLE A NIVEL DE LOCALIDAD
		Optional<VariableSistema> variableSistema = iVariableSistemaRepository.findById(cveVariable);
		if(!variableSistema.isPresent()) {
			result.setTipoExcepcion(Constants.ERROR);
			return result;
		}
				
		result.setTipo(variableSistema.get().getTipo());
		result.setDecimales(variableSistema.get().getDecimales());
		result.setFormatoFecha(variableSistema.get().getValorAlfanumerico());
		result.setValorEntero(variableSistema.get().getValorEntero());
		result.setValorDecimal(variableSistema.get().getValorDecimal());
		result.setValorFecha(variableSistema.get().getValorFecha());
		
		tipo = variableSistema.get().getTipo();		
		funcionVariable = variableSistema.get().getFuncionVariable();
		
		// EN CASO DE QUE SE CUENTE CON UNA FUNCIÓN, LA INTERPRETA Y LA ASIGNA AL VARON CORRESPONDIENTE DE LA VARIABLE
		if(funcionVariable != null) {			
			switch (tipo) {
	            case Constants.ALFANUMERICO:
	            	String valorAlfanumerico = variableSistema.get().getValorAlfanumerico();
	            	result.setValorAlfanumerico(valorAlfanumerico);
	                break;
	            case Constants.ENTERO:
	               Integer valorEntero = variableSistema.get().getValorEntero();
	               result.setValorEntero(valorEntero);
	                break;
	            case Constants.DECIMAL:
	               BigDecimal valorDecimal = variableSistema.get().getValorDecimal();
	               result.setValorDecimal(valorDecimal);
	                break;
	            case Constants.FECHA:
	            	if ( funcionVariable.toUpperCase().contains("DATE")){
		            	Date valorFecha = new Date();
		            	result.setValorFecha(valorFecha);
	            	}
	                break;
			}
		}
		
		return result;
	}
	
	
	// SP_LEE_VALOR_VPN
	@Override
	public EstatusVariablesTO leerValorVpn(DatosAutenticacionTO session, NodoTO nodo, String cveVariable)
					throws BrioBPMException{
		
		/*log.info("-------> INICIA leerValorVpn");
		log.info("-------> nodo: {}", nodo);
		log.info("-------> cveVariable: {}", cveVariable);*/
		
		String cveProceso = nodo.getCveProceso();
		BigDecimal version = nodo.getVersion();
		String cveInstancia = nodo.getCveInstancia();
		String cveNodo = nodo.getCveNodo();
		Integer idNodo = nodo.getIdNodo();
		Integer secuenciaNodo = nodo.getSecuenciaNodo();
		String cveEntidad = session.getCveEntidad();
		String cveLocalidad = session.getCveLocalidad();
		
	    
	    // INICIALIZA CÓDIGO DE ERROR, MENSAJE
	    EstatusVariablesTO result = EstatusVariablesTO.builder()
				.tipoExcepcion(Constants.OK)
				.mensaje("")
				.build();
		String idProceso = "LEE_VALOR_VPN";

		String variablesValores = Constants.CVE_ENTIDAD + cveEntidad + "|" +
				Constants.CVE_LOCALIDAD + cveLocalidad + "|" +
				Constants.CVE_PROCESO + cveProceso + "|" +
				Constants.VERSION + version + "|" +
				Constants.CVE_INSTANCIA + cveInstancia + "|" +
				Constants.CVE_NODO + cveNodo + "|" +
				Constants.ID_NODO + idNodo + "|" +
				Constants.SECUENCIA_NODO + secuenciaNodo + "|" +
				Constants.CVE_VARIABLE + cveVariable;
		
		// INICIALIZA LAS VARIABLES DE SALIDA
		result.setValorAlfanumerico(null);
		result.setValorEntero(null);
		result.setValorDecimal(null);
		result.setValorFecha(null);
		
		String valorAlfanumerico = null;
		String valor = null;
		
		// OBTIENE EL TIPO DE DATO DE LA VARIABLE
		Integer existe = iDatoProcesoRepository.verificaDato(cveVariable);
		if(existe == null) {
			String mensaje = messagesService.getMessage(
					session,
					idProceso,
					"NO-EXISTE-VARIABLE-NATIVA",
					variablesValores);
			result.setTipoExcepcion(Constants.ERROR);
			result.setMensaje(mensaje);
			return result;
		}
		
		Optional<DatoProcesoNodo> datoProcesoNodo = iDatoProcesoRepository.encuentraDato(cveVariable);
		String tipo = datoProcesoNodo.get().getTipo();

		result.setTipo(tipo);
		result.setDecimales(datoProcesoNodo.get().getDecimales());
		result.setFormatoFecha(datoProcesoNodo.get().getFormatoFecha());
		switch (tipo) {
	    case Constants.ALFANUMERICO:
	    	if(cveNodo != null && idNodo != null && secuenciaNodo != null) {
	    		
	    		valorAlfanumerico = vwDatoActividadDAO.obtenerMaxCompleto(
	    				cveVariable,
	    				cveEntidad,
		    			cveProceso,
		    			version,
		    			cveInstancia,
		    			cveNodo,
			    		idNodo,
			    		secuenciaNodo);

	    	} else {
	    		valorAlfanumerico = vwDatoActividadDAO.obtenerMax(
		    			cveVariable,
		    			cveEntidad,
		    			cveProceso,
		    			version,
		    			cveInstancia);

	    	}
	    	result.setValorAlfanumerico(valorAlfanumerico);
	        break;
	    case Constants.ENTERO:
	    	if(cveNodo != null && idNodo != null && secuenciaNodo != null) {
	    		valor = vwDatoActividadDAO.obtenerMaxCompleto(
	    				cveVariable,
	    				cveEntidad,
	    				cveProceso,
	    				version,
	    				cveInstancia,
	    				cveNodo,
	    				idNodo,
	    				secuenciaNodo);
	    			    		
	    	} else {
	    		valor = vwDatoActividadDAO.obtenerMax(
	    			cveVariable,
	    			cveEntidad,
	    			cveProceso,
	    			version,
	    			cveInstancia);	 

	    	}
   		 Integer valorEntero = Integer.parseInt(valor);

   		 result.setValorEntero(valorEntero);
	        break;
	    case Constants.DECIMAL:
	    	if(cveNodo != null && idNodo != null && secuenciaNodo != null) {
    		valor = vwDatoActividadDAO.obtenerMaxCompleto(
    				cveVariable,
	    			cveEntidad,
	    			cveProceso,
	    			version,
	    			cveInstancia,
		    		cveNodo,
		    		idNodo,
		    		secuenciaNodo);
	    	} else {
	    		valor = vwDatoActividadDAO.obtenerMax(
	    			cveVariable,
	    			cveEntidad,
	    			cveProceso,
	    			version,
	    			cveInstancia);
	    	}
	    	result.setValorDecimal(new BigDecimal(valor));

	        break;
	    case Constants.FECHA:
	    	if(cveNodo != null && idNodo != null && secuenciaNodo != null) {
	    		valor = vwDatoActividadDAO.obtenerMaxCompleto(
	    				cveVariable,
	    				cveEntidad,
	    				cveProceso,
	    				version,
	    				cveInstancia,
	    				cveNodo,
	    				idNodo,
	    				secuenciaNodo);

	    	} else {
	    		valor = vwDatoActividadDAO.obtenerMax(
	    			cveVariable,
	    			cveEntidad,
	    			cveProceso,
	    			version,
	    			cveInstancia);

	    	}

	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	         Date fecha = null;
	    	 if(valor != null && !valor.equals("NA")) {
	        	 try {
	        		if (valor.length() > 19) {
	        			valor = valor.substring(0, 19);
	        		}
					fecha = sdf.parse(valor);
				} catch (ParseException e) {
					throw new BrioBPMException(e);
				}
	         }
	    	 
	    	 result.setValorFecha(fecha);
	        break;
	    default:

	    	String mensaje = messagesService.getMessage(
					session,
					idProceso,
					"ERR-LEER-VALOR-DATO_PROCESO_NODO",
					variablesValores);
			result.setTipoExcepcion(Constants.ERROR);
			result.setMensaje(mensaje);
			return result;
		}        
		//log.info("############### RETURN SP_LEE_VALOR_VPN: " + result.toString());
		return result;
	}

	// SP_LEE_VALOR_VDOC.
	@Override
	public EstatusVariablesTO leerValorVdoc(DatosAutenticacionTO session, NodoTO nodo)
			throws BrioBPMException {
		
		String cveProceso = nodo.getCveProceso();
		BigDecimal version = nodo.getVersion();
		String cveInstancia = nodo.getCveInstancia();
		String cveVariable = nodo.getCveVariable();
		Integer ocurrencia = nodo.getOcurrencia();
		String cveEntidad = session.getCveEntidad();
		String tipoAlfanumerico;
		String vdocDescripcionDocumento;
		String descripcionDocumento = null;
		String nombreArchivo = null;
		
		//INICIALIZANDO CONSTANTES
		tipoAlfanumerico = "ALFANUMERICO";
		vdocDescripcionDocumento = "VDOC_DESCRIPCION_DOCUMENTO";
		
		//INICIALIZA CÓDIGO DE ERROR, MENSAJE Y SUGERENCIA
		EstatusVariablesTO result = EstatusVariablesTO.builder()
				.tipoExcepcion(Constants.OK)
				.mensaje("")
				.build();
		
		// INICIALIZA LAS VARIABLES DE SALIDA
		result.setTipo(null);
		result.setDecimales(null);
		result.setFormatoFecha(null);
		result.setValorAlfanumerico(null);
		result.setValorEntero(null);
		result.setValorDecimal(null);
		result.setValorFecha(null);
		
		//OBTIENE LAS VARIABLES DISPONIBLES DE DOCUMENTOS
		List<Object[]>obtieneVaribles = iStDocumentoProcesoRepository.obtenerDocumentoProceso(
				cveEntidad,
				cveProceso,
				version,
				ocurrencia,
				cveInstancia);
		descripcionDocumento = (String) obtieneVaribles.get(0)[0];
		nombreArchivo = (String) obtieneVaribles.get(0)[1];
		
		if(obtieneVaribles.isEmpty()) {
			descripcionDocumento = null;
			nombreArchivo = null;
		}
		
		result.setTipo(tipoAlfanumerico);
		if(cveVariable.equals(vdocDescripcionDocumento)) {
			result.setValorAlfanumerico((descripcionDocumento != null) ? descripcionDocumento : "");
		} else {
			result.setValorAlfanumerico((nombreArchivo != null) ? nombreArchivo : "");
		}
		
		return result;
		
	}
	 // SP_CREA_VARIABLES_SECCION
	@Override
 	public EstatusTO creaVariablesSeccion(DatosAutenticacionTO session, NodoTO nodo, String tipoSeccion,
			Integer secuenciaDocumento) throws BrioBPMException {
		
		log.info("############### INICIO SP_CREA_VARIABLES_SECCION");

		String cveEntidad = session.getCveEntidad();
		String cveProceso = nodo.getCveProceso();
		BigDecimal version = nodo.getVersion();
		String cveInstancia = nodo.getCveInstancia();
		String cveNodo = nodo.getCveNodo();
		Integer idNodo = nodo.getIdNodo();
		Integer secuenciaNodo = nodo.getSecuenciaNodo();
		String mensaje;
		Integer secuenciaValor;
		Integer ocurrencia;
		Integer resultado;
		
		// INICIALIZA CÓDIGO DE ERROR, MENSAJE Y SUGERENCIA
		EstatusTO result = EstatusTO.builder()
				.tipoExcepcion(Constants.OK)
				.mensaje("")
				.build();
		String idProceso = "CREA_VARIABLES_SECCION";
		String tipoSeccionDocumentos = "DOCUMENTOS";
		
		// CONCATENA LA LISTA DE LOS PARÁMETROS PARA DESPLEGARLOS EN CASO DE ERROR
		String variablesValores = Constants.CVE_PROCESO + cveProceso + "|" +
				Constants.VERSION + version + "|" +
				Constants.CVE_INSTANCIA + cveInstancia + "|" +
				Constants.CVE_NODO + cveNodo + "|" +
				Constants.ID_NODO + idNodo + "|" +
				Constants.SECUENCIA_NODO + secuenciaNodo;
		
		// CONSTANTES
		List<String> vpro = Arrays.asList(
				Constants.VPRO_01_SECUENCIA_DOCUMENTO,
				Constants.VPRO_01_DESCRIPCION_DOCUMENTO,
				Constants.VPRO_01_ARCHIVO_DOCUMENTO,
				Constants.VPRO_01_REQUERIDO_DOCUMENTO);

		
		// VALIDA QUE EL TIPO DE NODO SEA 'ACTIVIDAD-USUARIO', ES DECIR UNA ACTIVIDAD DE USUARIO
		if (!Constants.ACTIVIDAD_USUARIO.equals(cveNodo) &&
				!Constants.ACTIVIDAD_USUARIO_TEMPORIZACION.equals(cveNodo)) {
			
			mensaje = messagesService.getMessage(
					session,
					idProceso,
					"VARIABLES_EXCLUSIVAS_ACT_USU",
					variablesValores);
			result.setTipoExcepcion(Constants.ERROR);
			result.setMensaje(mensaje);
			return result;
		} 
		
                
		// CURSOR DE VARIABLES PARA EL NODO. LAS VARIABLES DE UNA SECCION DE TIPO GRID
		// SE CREAN DURANTE LA EJECUCIÓN DE LA ACTIVIDAD.
		List<String> variables = iSstVariableSeccionRepository.encuentraVariablesDetalles(
				cveEntidad, cveProceso, version, cveNodo, idNodo,
				tipoSeccionDocumentos, vpro);
		
		
		// RECORRE LAS VARIABLES PARA GENERARLAS EN LA INSTANCIA DE VARIABLES DEL NODO
		for (String cveVariable : variables) {
			resultado = inVariableProcesoRepository.verificaVariable(
					cveEntidad,
					cveProceso,
					version,
					cveInstancia,
					cveVariable);
			
			
			if (resultado == null) {
				secuenciaValor = 1;
				ocurrencia = 1;
				
				if(tipoSeccion.equals(tipoSeccionDocumentos)) {
					ocurrencia = secuenciaDocumento;
				}
				
				InVariableProcesoPK id = InVariableProcesoPK.builder()
						.cveEntidad(cveEntidad)
						.cveProceso(cveProceso)
						.version(version)
						.cveInstancia(cveInstancia)
						.ocurrencia(ocurrencia)
						.cveVariable(cveVariable)
						.secuenciaValor(secuenciaValor)
						.build();
				InVariableProceso entidad = InVariableProceso.builder()
						.id(id)
						.build();
				
				// NO RELACIONADO CON GRID
				inVariableProcesoRepository.saveAndFlush(entidad);
				
				Optional<InVariableProceso> variableProceso = inVariableProcesoRepository.findById(id);
				if (!variableProceso.isPresent()) {
					mensaje = messagesService.getMessage(session,
							idProceso,
							"ERR_INS_TAB_IN_VARIABLE_PROCESO",
							variablesValores);
					result.setTipoExcepcion(Constants.ERROR);
					result.setMensaje(mensaje);
					return result;
				}
			}
		}
        log.info("############### RETURN SP_CREA_VARIABLES_SECCION: " + result.toString());
		return result;
	}

	// SP_CREA_TAREAS_NODO
	@Override
	public EstatusTO creaTareas(DatosAutenticacionTO session, NodoTO nodo)
					throws BrioBPMException {	
		
		log.info("############### INICIO SP_CREA_TAREAS_NODO");
		
		String cveProceso = nodo.getCveProceso();
		BigDecimal version = nodo.getVersion();
		String cveInstancia = nodo.getCveInstancia();
		String cveNodo = nodo.getCveNodo();
		Integer idNodo = nodo.getIdNodo();
		Integer secuenciaNodo = nodo.getSecuenciaNodo();
		
		String cveEntidad = session.getCveEntidad();
		String mensaje;
		
		// INICIALIZA CÓDIGO DE ERROR, MENSAJE Y SUGERENCIA
		String idProceso = "CREA_TAREAS_NODO";
		EstatusTO result = EstatusTO.builder()
				.tipoExcepcion(Constants.OK)
				.mensaje("")
				.build();
		// CONCATENA LA LISTA DE LOS PARÁMETROS PARA DESPLEGARLOS EN CASO DE ERROR
		String variablesValores = Constants.CVE_PROCESO + cveProceso + "|" +
				Constants.VERSION + version + "|" +
				Constants.CVE_INSTANCIA + cveInstancia + "|" +
				Constants.CVE_NODO + cveNodo + "|" +
				Constants.ID_NODO + idNodo + "|" +
				Constants.SECUENCIA_NODO + secuenciaNodo;
		
		// VALIDA QUE EL TIPO DE NODO SEA 'EVE_ACT', ES DECIR UNA ACTIVIDAD DE USUARIO
		if (!Constants.ACTIVIDAD_USUARIO.equals(cveNodo) && !Constants.ACTIVIDAD_USUARIO_TEMPORIZACION.equals(cveNodo)) {
			mensaje = messagesService.getMessage(session,
					idProceso,
					"TAREAS_EXCLUSIVAS_ACT_USU",
					variablesValores);
			result.setTipoExcepcion(Constants.ERROR);
			result.setMensaje(mensaje);
			return result;			
		}
		
		// CURSOR DE TAREAS PARA EL NODO
		List<Integer> tareas = iStTareaSeccionRepository.encuentraSecuenciaTarea(
				cveEntidad,
				cveProceso,
				version,
				cveNodo,
				idNodo);
		
		log.info("creaTareas - Tareas: " + tareas.size());
		
		for(Integer secuenciaTarea : tareas) {
			
			InTareaProcesoPK id = InTareaProcesoPK.builder()
					.cveEntidad(cveEntidad)
					.cveProceso(cveProceso)
					.version(version)
					.cveInstancia(cveInstancia)
					.secuenciaTarea(secuenciaTarea)
					.build();
			
			Integer resultado = inTareaProcesoRepository.existeInTareaProceso(
					cveEntidad,
					cveProceso,
					version,
					cveInstancia,
					secuenciaTarea);
			
			if(resultado == null) {			
				InTareaProceso entity = InTareaProceso.builder()
						.id(id)
						.completada(Constants.NO)
						.build();
				
				log.info("creaTareas ");
				inTareaProcesoRepository.saveAndFlush(entity);

				
				Optional<InTareaProceso> inTarea = inTareaProcesoRepository.findById(id);
				if (inTarea.isPresent()) {
					result.setTipoExcepcion(Constants.OK);
				} else {
					mensaje = messagesService.getMessage(
							session,
							idProceso,
							"ERR_INS_TAB_IN_TAREA_NODO",
							variablesValores);
					result.setTipoExcepcion(Constants.ERROR);
					result.setMensaje(mensaje);
				}
			}
		}
        log.info("############### RETURN SP_CREA_TAREAS_NODO: " + result.toString());
		return result;
	}
	
	// SP_CREA_DOCUMENTOS_NODO
	@Override
public EstatusTO creaDocumentos(DatosAutenticacionTO session, NodoTO nodo) throws BrioBPMException {
		
		 // Se obtienen datos importantes del objeto de sesión y del nodo para su uso posterior.
	    String cveEntidad = session.getCveEntidad(); // Clave de la entidad actual.
	    String cveProceso = nodo.getCveProceso(); // Clave del proceso actual.
	    BigDecimal version = nodo.getVersion(); // Versión del proceso.
	    String cveInstancia = nodo.getCveInstancia(); // Clave de la instancia del proceso.
	    String cveNodo = nodo.getCveNodo(); // Clave del nodo actual.
	    Integer idNodo = nodo.getIdNodo(); // Identificador del nodo.
	    Integer secuenciaNodo = nodo.getSecuenciaNodo(); // Secuencia del nodo.
	    
	    // Se registra un mensaje informativo en los logs para indicar el inicio del proceso.
		log.info("-------INICIA creaDocumentos");
		
		 // Construye una cadena de texto con los valores de las variables relevantes
	    // separadas por "|" para utilizar en los mensajes de configuración.
		String variablesValores = Constants.CVE_PROCESO + cveProceso + "|" +
				Constants.VERSION + version + "|" +
				Constants.CVE_INSTANCIA + cveInstancia + "|" +
				Constants.CVE_NODO + cveNodo + "|" +
				Constants.ID_NODO + idNodo + "|" +
				Constants.SECUENCIA_NODO + secuenciaNodo;
		
		 // Definición de identificador de proceso y tipo de sección para documentos.
	    String idProceso = "CREA_DOCUMENTOS_NODO"; // Identificador del proceso de creación de documentos.
	    String tipoSeccionDocumentos = "DOCUMENTOS"; // Tipo de sección asociado a documentos.

	    // Se inicializa el objeto EstatusTO que almacenará el estado del proceso.
	    // Se establece inicialmente como exitoso (OK) con un mensaje vacío.
	    EstatusTO result = EstatusTO.builder()
	            .tipoExcepcion(Constants.OK) // Código de éxito inicial.
	            .mensaje("") // Mensaje vacío por defecto.
	            .build();
		
		// VALIDA QUE EL TIPO DE NODO SEA 'ACTIVIDAD-USUARIO'
	    // Se verifica que el nodo sea del tipo 'ACTIVIDAD-USUARIO' o 'ACTIVIDAD-USUARIO-TEMPORIZACION'.
	    if (!Constants.ACTIVIDAD_USUARIO.equals(cveNodo) && !Constants.ACTIVIDAD_USUARIO_TEMPORIZACION.equals(cveNodo)) {

	        // Obtiene un mensaje de error utilizando el servicio de mensajes,
	        // pasando la sesión, el id del proceso, un código de error,
	        // y la cadena de valores de variables.
	        String mensaje = messagesService.getMessage(session,
	                idProceso,
	                "DOCUMENTOS_EXCLUSIVOS_ACT_USU",
	                variablesValores);

	        // Establece el tipo de excepción como ERROR y asigna el mensaje obtenido.
	        result.setTipoExcepcion(Constants.ERROR);
	        result.setMensaje(mensaje);

	        // Devuelve el resultado con el error.
	        return result;
		} 


	    // Se obtiene una lista de secuencias de documentos asociados al nodo actual.
	    List<Integer> documentos = iStDocumentoSeccionRepository.encuentraSecuenciaDocumento(
	            cveEntidad,
	            cveProceso,
	            version,
	            cveNodo,
	            idNodo);

	    // RECORRE LOS DOCUMENTOS PARA GENERARLOS EN LA INSTANCIA DEL NODO
	    // Se itera sobre cada documento en la lista de secuencias obtenida.
		for(Integer secuenciaDocumentos : documentos) {
			
			// CREA EL DOCUMENTO
			  // Se construye una clave primaria compuesta (PK) para identificar el documento del proceso.
	        InDocumentoProcesoPK id = InDocumentoProcesoPK.builder()
	                .cveEntidad(cveEntidad) // Clave de la entidad del documento.
	                .cveProceso(cveProceso) // Clave del proceso del documento.
	                .version(version) // Versión del documento.
	                .cveInstancia(cveInstancia) // Clave de la instancia del documento.
	                .secuenciaDocumento(secuenciaDocumentos) // Secuencia del documento.
	                .build();

	        // Verifica si ya existe un documento en la base de datos con la clave primaria generada.
	        Integer resultado = iInDocumentoProcesoRepository.existeInDocumentoProceso(
	                cveEntidad,
	                cveProceso,
	                version,
	                cveInstancia,
	                secuenciaDocumentos);

	        // Si no existe un documento con esa clave primaria (resultado == 0), se procede a crearlo.
	        if (resultado == 0) {

	            // Se crea una nueva entidad InDocumentoProceso con la clave primaria generada.
	            InDocumentoProceso entity = InDocumentoProceso.builder()
	                    .id(id)
	                    .build();

	            // Se guarda y actualiza la entidad en la base de datos.
	            iInDocumentoProcesoRepository.saveAndFlush(entity);

	            // Se verifica si el documento se guardó correctamente recuperándolo por su clave primaria.
	            Optional<InDocumentoProceso> inDocumento = iInDocumentoProcesoRepository.findById(id);

	            // Si el documento está presente, se considera que la operación fue exitosa.
	            if (inDocumento.isPresent()) {
	                result.setTipoExcepcion(Constants.OK);
	            } else {
	                // Si el documento no se pudo guardar, se elimina cualquier rastro del documento creado y se genera un mensaje de error.
	                iInDocumentoProcesoRepository.deleteDocumentoProceso(cveEntidad,
	                        cveProceso,
	                        version,
	                        cveInstancia);

	                // Obtiene un mensaje de error utilizando el servicio de mensajes,
	                // pasando la sesión, el id del proceso, un código de error,
	                // y la cadena de valores de variables.
	                String mensaje = messagesService.getMessage(
	                        session,
	                        idProceso,
	                        "ERR_INS_TAB_IN_DOCUMENTO_PROCESO",
	                        variablesValores);

	                // Establece el tipo de excepción como ERROR y asigna el mensaje obtenido.
	                result.setTipoExcepcion(Constants.ERROR);
	                result.setMensaje(mensaje);

	                // Devuelve el resultado con el error.
	                return result;
	            }
	        }

	        // CREA LAS VARIABLES DE DOCUMENTOS
	        // Se crean las variables asociadas al documento de la sección utilizando un método auxiliar.
	        result = creaVariablesSeccion(
	                session,
	                nodo,
	                tipoSeccionDocumentos,
	                secuenciaDocumentos);

	        // Si el resultado es un error, se termina el proceso.
	        if (result.getTipoExcepcion().equals(Constants.ERROR)) {
	            break; // Sale del bucle de documentos si ocurre un error.
	        }
	    }

	    // Devuelve el resultado final, que contiene el estado del proceso de creación de documentos.
	    return result;
	}
	
	// SP_CREA_FOLIO_MENSAJE
	@Override
	public EstatusVariablesTO creaFolioMensaje(DatosAutenticacionTO session, NodoTO nodo)
					throws BrioBPMException{
		
		String cveProceso = nodo.getCveProceso();
		BigDecimal version = nodo.getVersion();
		String cveInstancia = nodo.getCveInstancia();
		String cveNodo = nodo.getCveNodo();
		Integer idNodo = nodo.getIdNodo();
		Integer secuenciaNodo = nodo.getSecuenciaNodo();
		String cveEntidad = session.getCveEntidad();
		
		//inicializa variables
		String idProceso = "CREA_FOLIO_MENSAJE";
		EstatusVariablesTO result = EstatusVariablesTO.builder()
				.tipoExcepcion(Constants.OK)
				.mensaje("").build();
		
		String variablesValores = Constants.CVE_ENTIDAD + cveEntidad + "|" +
				Constants.CVE_PROCESO + cveProceso + "|" +
				Constants.VERSION + version + "|" +
				Constants.CVE_INSTANCIA + cveInstancia + "|" +
				Constants.CVE_NODO + cveNodo + "|" +
				Constants.ID_NODO + idNodo + "|" +
				Constants.SECUENCIA_NODO + secuenciaNodo;
		
		result.setFolioMensaje(null);
		Date fechaActual = new Date();
		
        // obtiene el ultimo folio generado
        Optional<Integer> folioGenerado = iFoliadorMensajeRepository.findFolioMensaje();
        if(!folioGenerado.isPresent()) {
        	String mensaje = messagesService.getMessage(session,
					idProceso,
					"ERR-LEE-TAB-FOLIADOR_MENSAJE",
					variablesValores);
			result.setTipoExcepcion(Constants.ERROR);
			result.setMensaje(mensaje);
			result.setFolioMensaje(null);
			return result;
        }
        
        //incrementa el folio
        Integer folioMensaje = folioGenerado.get() + 1;
        result.setFolioMensaje(folioMensaje);
        
        //actualiza el folio
        FoliadorMensaje entity = FoliadorMensaje.builder()
        		.folioMensaje(folioMensaje)
        		.build();
        
        iFoliadorMensajeRepository.saveAndFlush(entity);
        Optional<FoliadorMensaje> folioMensajeActualizado = iFoliadorMensajeRepository.findById(entity.getFolioMensaje());
        
        if(!folioMensajeActualizado.isPresent()) {
        	String mensaje = messagesService.getMessage(session,
					idProceso,
					"ERR-UPD-TAB-FOLIADOR_MENSAJE",
					variablesValores);
			result.setTipoExcepcion(Constants.ERROR);
			result.setMensaje(mensaje);
			result.setFolioMensaje(null);
			return result;
        }
        
        // crea detalle en FOLIO_MENSAJE_PROCESO
        FolioMensajeProceso entityFolio = FolioMensajeProceso.builder()
        		.folioMensajeEnvio(folioMensaje)
        		.cveEntidad(cveEntidad)
        		.cveProceso(cveProceso)
        		.version(version)
        		.cveInstancia(cveInstancia)
        		.cveNodo(cveNodo)
        		.idNodo(idNodo)
        		.secuenciaNodo(secuenciaNodo)
        		.fechaCreacion(fechaActual)
        		.build();
        
        Optional<FoliadorMensaje> folioMensajeCreado = iFoliadorMensajeRepository.findById(entityFolio.getFolioMensajeEnvio());
        if(!folioMensajeCreado.isPresent()) {
        	String mensaje = messagesService.getMessage(session,
					idProceso,
					"ERR-INS-TAB-FOLIO_MENSAJE_PROCESO",
					variablesValores);
			result.setTipoExcepcion(Constants.ERROR);
			result.setMensaje(mensaje);
			result.setFolioMensaje(null);
			return result;
        }
        return result;
		
	}
	
	// SP_RECIBE_VARIABLES_ENVIO
	@Override
	public EstatusTO recibeValoresEnvio(DatosAutenticacionTO session, String idProceso,	Integer folioMensajeEnvio,
			String cveEntidadDestino, String cveProcesoDestino, BigDecimal versionDestino,
			String cveInstanciaDestino) throws BrioBPMException {
		
		log.info("############### INICIO SP_RECIBE_VARIABLES_ENVIO");
		
		// NICIALIZA CÓDIGO DE ERROR, MENSAJE Y SUGERENCIA
		EstatusTO result = EstatusTO.builder()
				.tipoExcepcion(Constants.OK)
				.mensaje("").build();
		
		// CONSTANTES 
		final String error = Constants.ERROR;
		
		// LISTA DE VARIABLES PARA MANEJO DE MENSAJES DE ERROR
		String variablesValores = Constants.CVE_USUARIO + session.getCveUsuario() + "|" +
				Constants.FOLIO_MENSAJE_ENVIO + folioMensajeEnvio;
		
		// obtiene datos del proceso origen
		Optional<InMensajeEnvio> procesoOrigen = iInMensajeEnvioRepository.obtenerDatosProcesoOrigen(folioMensajeEnvio);
		if(!procesoOrigen.isPresent()) {
			String mensaje = messagesService.getMessage(
					session,
					idProceso,
					"FOLIO-MSG-ENVIO-NO-EXISTE",
					variablesValores);
			result.setTipoExcepcion(error);
			result.setMensaje(mensaje);
			return result;
		}
		
		InMensajeEnvioPK pOrigen = procesoOrigen.get().getId();
		String cveEntidadOrigen = pOrigen.getCveEntidad();
		String cveProcesoOrigen = pOrigen.getCveProceso();
		BigDecimal versionOrigen = pOrigen.getVersion();
		String cveInstanciaOrigen = pOrigen.getCveInstancia();
		String cveNodoOrigen = pOrigen.getCveNodo();
		Integer idNodoOrigen = pOrigen.getIdNodo();
		Integer secuenciaNodoOrigen = pOrigen.getSecuenciaNodo();
		
		variablesValores = variablesValores + Constants.CVE_ENTIDAD_ORIGEN + cveEntidadOrigen + "|" +
                Constants.CVE_PROCESO_ORIGEN + cveProcesoOrigen + "|" +
                Constants.VERSION_ORIGEN + versionOrigen + "|" +
                Constants.CVE_NODO_ORIGEN + cveNodoOrigen + "|" +
                Constants.ID_NODO_ORIGEN + idNodoOrigen + "|" +
                Constants.CVE_ENTIDAD_DESTINO + cveEntidadDestino + "|" +
                Constants.CVE_PROCESO_DESTINO + cveProcesoDestino + "|" +
                Constants.VERSION_DESTINO + versionDestino + "|";
		
		// LISTA DE VARIABLES DEL PROCESO QUE ENVÍA
		List<InVariableEnvio> variablesProcesoEnvia = iInVariableEnvioRepository.obtenerDatosOrigen(
				cveEntidadOrigen,
				cveProcesoOrigen,
				versionOrigen,
				cveInstanciaOrigen,
				cveNodoOrigen,
				idNodoOrigen,
				secuenciaNodoOrigen);
		
		// SE ASUME QUE LAS VARIABLES PERTENECEN A LA OCURRENCIA 1 Y SECUENCIA VALOR 1
		Integer ocurrencia = 1;
		Integer secuenciaValor = 1;
		
		log.info("------> variablesProcesoEnvia: {} {} ", variablesProcesoEnvia.size(), variablesProcesoEnvia.toString());
		
		for(InVariableEnvio inVariable : variablesProcesoEnvia) {
			Integer existeVariableProceso = inVariableProcesoRepository.verificaVariable(
					cveEntidadDestino,
					cveProcesoDestino,
					versionDestino,
					cveInstanciaDestino,
					inVariable.getCveVariableDestino());

			InVariableProcesoPK id = InVariableProcesoPK.builder()
					.cveEntidad(cveEntidadDestino)
					.cveProceso(cveProcesoDestino)
					.version(versionDestino)
					.cveInstancia(cveInstanciaDestino)
					.ocurrencia(ocurrencia)
					.cveVariable(inVariable.getCveVariableDestino())
					.secuenciaValor(secuenciaValor)
					.build();
			
			InVariableProceso entity = InVariableProceso.builder()
					.valorAlfanumerico(inVariable.getValorAlfanumerico())
					.valorEntero(inVariable.getValorEntero())
					.valorDecimal(inVariable.getValorDecimal())
					.valorFecha(inVariable.getValorFecha())
					.build();
			
			if (existeVariableProceso != 1) {
				inVariableProcesoRepository.saveAndFlush(entity);
				Optional<InVariableProceso> existeInVariable = inVariableProcesoRepository.findById(id);
				String variableValorAux = variablesValores +
						"@CVE_VARIABLE_DESTINO@|" + inVariable.getCveVariableDestino();
				
				if(!existeInVariable.isPresent()) {
					String mensaje = messagesService.getMessage(session,
							idProceso,
							"ERR-CREAR-VARIABLE-DESTINO",
							variableValorAux);
					result.setTipoExcepcion(error);
					result.setMensaje(mensaje);
					return result;
				}
			} else {

				entity.setValorAlfanumerico(inVariable.getValorAlfanumerico());
				entity.setValorEntero(inVariable.getValorEntero());
				entity.setValorDecimal(inVariable.getValorDecimal());
				entity.setValorFecha(inVariable.getValorFecha());
				inVariableProcesoRepository.saveAndFlush(entity);
				
				String variableValorAux = variablesValores +
						"@CVE_VARIABLE_DESTINO@|" + inVariable.getCveVariableDestino();
				
				Optional<InVariableProceso> actualizaVariable = inVariableProcesoRepository.findById(id);
				
				if (!actualizaVariable.isPresent()) {
					String mensaje = messagesService.getMessage(
							session,
							idProceso,
							"ERR-ACTUALIZAR-VARIABLE-DESTINO",
							variableValorAux);
					result.setTipoExcepcion(error);
					result.setMensaje(mensaje);
					return result;
				}
			}
		}
		
		return result;
	}
	
	// SP_REEMPLAZA_VARIABLES
	@Override
	public EstatusVariablesTO reemplazaVariables(DatosAutenticacionTO session, NodoTO nodo, Integer secuenciaDocumento,
			String cadenaEntrada)  throws BrioBPMException{
		
		
		// Extrae información relevante del objeto nodo
		//log.info("-------->reemplazaVariables<-------");
		
		String cveProceso = null;
		BigDecimal version = null;
		String cveInstancia = null;
		Integer ocurrencia = null;
		String cveEntidad = null;
		String pathRepse = null;
		
		if(nodo != null){
			cveProceso = nodo.getCveProceso();
			version = nodo.getVersion();
			cveInstancia = nodo.getCveInstancia();
			ocurrencia = nodo.getOcurrencia();
			cveEntidad = session.getCveEntidad();
			pathRepse = nodo.getPathImage();
		}

		String formatoFecha = "YYYY-MM-DD";
		String agregacion = null; // revisar
		String valorResultante = null;
		//log.info("------>> reemplazaVariables");
		
		//log.info("-------> CLAVE INSTANCIA EN REMPLAZA VAR: " + cveInstancia);
		//log.info("-------> SECUENCIA DOCUMENTO: " + secuenciaDocumento);
		//log.info("-------> CADENA ENTRADA: " + cadenaEntrada);

		//INICIALIZA CÓDIGO DE ERROR Y MENSAJE
		EstatusVariablesTO result = EstatusVariablesTO.builder()
				.tipoExcepcion(Constants.OK)
				.mensaje("")
				.build();
		result.setCadenaSalida(cadenaEntrada);
	
	    String variableReemplazar = null;
	    String cadenaSalida = cadenaEntrada;
	    
	    //log.debug("----> CADENA DE ENTRADA antes if: " + cadenaEntrada);
	    // SI LA CADENA DE ENTRADA ES NULA O VACÍA, TERMINA EL PROCESO
	    if(cadenaEntrada == null || cadenaEntrada.isEmpty()) {
	        return result;
	    }
	    
	    // TABLA TEMPORAL PARA ALMACENAR LISTA DE VARIABLES CONTENIDAS EN LA CADENA DE ENTR
	    // CREA TABLA TEMPORAL PARA ALMACENAR LISTA DE VARIABLES CONTENIDAS EN LA CADENA DE ENTRADA
		// revisar
	    List<VariableCadenaTO> variableCadenaList = new ArrayList<VariableCadenaTO>();
	    
		// EXTRAE VARIABLES DE LA CADENA DE ENTRADA
	    // Extrae las variables contenidas en la cadena de entrada usando el método leeVariablesCadena
        EstatusVariableCadenaTO leeVariableC = leeVariablesCadena(cadenaEntrada, variableCadenaList);
        result.setMensaje(leeVariableC.getMensaje());
	    result.setTipoExcepcion(leeVariableC.getTipoExcepcion());
	    //log.info("----> TABLA TEMPORAL PARA ALMACENAR LISTA DE VARIABLES: " + leeVariableC.toString());
        EstatusVariablesTO leeV = null;
        
        //LISTA DE VARIABLES PARA APLICAR EL REEMPLAZO
        // Obtiene la lista de variables que se deben reemplazar en la cadena
        variableCadenaList = leeVariableC.getValoresLista();
        //log.info("----> LISTA : " + variableCadenaList);
        
       //SVM ajuste se integra una validacion si no tiene variables
        if (variableCadenaList == null || variableCadenaList.size() == 0) {
        	result.setCadenaSalida(cadenaSalida);
        	//log.info("---------------------> variableCadenaList.size(): " + result);
            return result;
        }
        
	    Integer contador = variableCadenaList.size();
	    //log.info("----> LISTA TAMAÑO: " + contador);
	    
	    // Itera sobre cada variable en la lista para reemplazarla en la cadena de salida
	    for(int x = 0 ; x < contador ; x++) {
	    	
	    	boolean continuarFlujo= true;
	    	
	    	variableReemplazar = variableCadenaList.get(x).getCveVariable();
	    	//log.info("-----> variableReemplazar: " + variableReemplazar);
	    	
	    	//ESTABLECE EL TIPO DE VARIABLE QUE SE LEE
	    	// Determina el tipo de variable y obtiene su valor correspondiente
	    	if (variableReemplazar.startsWith(Constants.VPRO)) {
	    		// variable de proceso
	    		//log.info("----> VPRO: " + Constants.VPRO);
	    	    nodo.setCveVariable(variableReemplazar);
		    	EstatusVariablesTO leeVPRO = leerValorVpro(session, nodo, agregacion, secuenciaDocumento);
		    	leeV = leeVPRO;
		    	
		    } else if (variableReemplazar.startsWith(Constants.VLOC)) {
		    	// variable de localidad
		    	//log.info("----> VLOC: " + Constants.VLOC);
		    	EstatusVariablesTO leeVLOC = leerValorVeloc(session, cveProceso, version, variableReemplazar);
		    	leeV = leeVLOC;
		    	
		    } else if (variableReemplazar.startsWith(Constants.VENT)) {
		    	// variable de entidad
		    	//log.info("----> VENT: " + Constants.VENT);
		    	EstatusVariablesTO leeVENT = leerValorVent(session, cveProceso, version, variableReemplazar);
		    	leeV = leeVENT;
		    	
		    } else if (variableReemplazar.startsWith(Constants.VSIS)) {
		    	// variable de sistema
		    	//log.info("----> VSIS: " + Constants.VSIS);
		    	EstatusVariablesTO leeVSIS = leerValorVsis(session, cveProceso, version, variableReemplazar);
		    	leeV = leeVSIS;
		    	
		    } else if (variableReemplazar.startsWith(Constants.DA) && 
		    		cveEntidad != null && cveProceso != null &&
		    		version != null && cveInstancia != null) {
		    	//log.info("----> VPN: " + Constants.DA);
		    	if (variableReemplazar.equals("DA.ID_NODO_ACTUAL")) {
		    		
		    	}
		    	EstatusVariablesTO leeVPN = leerValorVpn(session, nodo, variableReemplazar);
		    	leeV = leeVPN;
		    	//log.info("----> VPN leeV: " + leeV);
		    	
		    } else if (variableReemplazar.startsWith(Constants.VDOC) &&
		    		ocurrencia != null && ocurrencia != 0) {
		    	// variable de documento
		    	//log.info("----> VDOC: " + Constants.VDOC);
		    	EstatusVariablesTO leeVDOC = leerValorVdoc(session, nodo);
		    	leeV = leeVDOC;
		    } else {
		    	// varible especial
		    	//log.info("llama a SP_LEE_VALOR_VARIABLE_ESPECIAL: {} ", variableReemplazar);
		    	EstatusVariablesTO leeVVE;
				try {
					leeVVE = dashboardHelper.leeValorVariableEspecial(session, variableReemplazar);
				} catch (ParseException e) {
					throw new BrioBPMException(e);
				}
		    	leeV = leeVVE;
		    }
	    	
	    	 // Si el flujo de procesamiento continúa, actualiza la lista de variables y realiza el reemplazo en la cadena
	    	if (continuarFlujo) {
	    		// ACTUALIZANDO EL VALOR DE LA VARIABLE EN LA TABLA TEMPORAL
		    	variableCadenaList.get(x).setTipo(leeV.getTipo());
		    	variableCadenaList.get(x).setDecimales(leeV.getDecimales());
		    	variableCadenaList.get(x).setFormatoFecha(leeV.getFormatoFecha());
		    	variableCadenaList.get(x).setValorAlfanumerico(leeV.getValorAlfanumerico());
		    	variableCadenaList.get(x).setValorEntero(leeV.getValorEntero());
		    	variableCadenaList.get(x).setValorDecimal(leeV.getValorDecimal());
		    	variableCadenaList.get(x).setValorFecha(leeV.getValorFecha());
		    	
		    	Date valorFecha = variableCadenaList.get(x).getValorFecha();
		    	
		    	// REEMPLAZANDO LOS VALORES DE LAS VARIABLES EN LA CADENA DE SALIDA
		    	//log.info("--TIPO VARIABLE :" +variableCadenaList.get(x).getTipo());
		    	if (variableCadenaList.get(x).getTipo() != null) {
		    		valorResultante = "";
		    		
			    	switch(variableCadenaList.get(x).getTipo()) {
		    		case Constants.ALFANUMERICO:
		    			//log.info("-- ALFANUMERICO ");
		    			valorResultante = variableCadenaList.get(x).getValorAlfanumerico();
		    			//log.info("--valorResultante1 :" + valorResultante);
		    			break;
		    		case Constants.ENTERO:
		    			//log.info("-- ENTERO ");
		    			valorResultante = variableCadenaList.get(x).getValorEntero().toString();
		    			//log.info("----> VSIS valorResultante2: " + valorResultante);
		    			break;
		    		case Constants.DECIMAL:
		    			//log.info("-- DECIMAL ");
		    			valorResultante = truncarDecimales(
		    					variableCadenaList.get(x).getValorDecimal(),
		    					variableCadenaList.get(x).getValorEntero());
		    			//log.info("--valorResultante3 :" + valorResultante);
		    			break;
		    		case Constants.FECHA:
		    			//log.info("-- FECHA ");
		    			String formatoFechaAMS = "yyyyMMdd";

		    			if(nodo == null & secuenciaDocumento == null) {
		    				//log.info("valorFecha ANTES : {} {}" , variableReemplazar, valorFecha);
							valorResultante = formatFecha(valorFecha, formatoFechaAMS);
							log.info("valorFecha DESPUES : {} {}" , variableReemplazar, valorFecha);
		    			} else {
		    				valorResultante = formatFecha(
			    					variableCadenaList.get(x).getValorFecha(),
			    					variableCadenaList.get(x).getFormatoFecha());
			    			log.info("--valorResultante4 :" + valorResultante);
		    			}
		    		
		    			break;
		    		default:
		    			log.info("-- NINGUNO ");
		    			valorResultante = "";
		    			break;
		    		
			    	}
		    	
		    	}
		    	
		    	
		    	// Reemplaza en la cadena de salida el valor de la variable correspondiente
		    	variableReemplazar = "@" + variableReemplazar + "@";
		    	//log.info("--variableReemplazar :" + variableReemplazar);
		    	//log.info("--*valorResultante :" + valorResultante);
		    	
		    	// Reemplaza en la cadena de salida el valor de la variable correspondiente
		    	cadenaSalida = cadenaSalida.replace(variableReemplazar, (valorResultante != null) ? valorResultante : "");
		    	//log.info("--cadena Salida :" + cadenaSalida);
		    	
		    	
		    	
		    	// ACTUALIZA LA CADENA DE SALIDA
	            result.setCadenaSalida(cadenaSalida);
	            //log.info("----> VSIS result: " + result);
	    	}	    	
	    }
	    // TODO EVL ELIMINAR
	    /*
	    if (!"".equals(pathRepse) && pathRepse != null) {
	    	cadenaSalida = cadenaSalida.replaceAll("@RUTA_IMAGEN_REPSE@", pathRepse);
	    	result.setCadenaSalida(cadenaSalida);
	    }*/
	    	
	    // Registra el resultado final antes de retornar
        log.debug("############### RETURN SP_REEMPLAZA_VARIABLES: " + result.toString());
		return result;
	}
	
	// SP_EVALUA_CONDICION
	@Override
	public EstatusCondicionTO evaluarCondicion(DatosAutenticacionTO session, NodoTO nodo,
			String condicion) throws BrioBPMException {
		
		//log.info("****************************************************");
		//log.info("*****************evaluarCondicion*******************");
		//log.info("NODO : " + nodo.toString());
		//log.info("CONDICION: " + condicion);
		
		String idProceso = nodo.getIdProceso();
		String cveProceso = nodo.getCveProceso();
		BigDecimal version = nodo.getVersion();
		String cveInstancia = nodo.getCveInstancia();
		String cveEntidad = session.getCveEntidad();
		String cveLocalidad = session.getCveLocalidad();
		String condicionSalida;
		Integer inicio;
		Integer fin;
		boolean finBusqueda;
		String valorAlfanumerico = null;
		BigDecimal valorDecimal = null;
		Integer valorEntero = null;
		Date valorFecha = null;
		String valorReemplazar;
		String tipo = null;
		String resultado;
		
		// INICIALIZA CÓDIGO DE ERROR Y MENSAJE
		EstatusCondicionTO result = EstatusCondicionTO.builder()
				.tipoExcepcion(Constants.OK)
				.mensaje("")
				.build();
		
		String variablesValores = Constants.CONDICION + condicion + "|" +
				Constants.SENTENCIA  + result.getResultado();

		// INICIALIZA VARIABLES
		finBusqueda = false;
		inicio = 0;
		condicionSalida = condicion;
		
		// RECORRE CONDICIÓN PARA REEMPLAZAR VARIABLES
		while(!finBusqueda) {
			
			// Busca el inicio de una variable (carácter '@')
			inicio = condicion.indexOf("@", inicio);
			
			if(inicio == -1) {
				finBusqueda = true; // No se encuentran más variables
			} else {
				// Busca el fin de la variable (siguiente carácter '@')
				fin = condicion.indexOf("@", inicio + 1);
				
				if(inicio == -1) {
					finBusqueda = true; // No se encuentra el cierre de variabl
				} else {
					Integer longitud = fin - (inicio + 1);
					String variable = condicion.substring(inicio + 1, fin);
					String variableSwitch = variable.substring(0,4);
					
					inicio = fin + 1;
					boolean variableEncontrada = false;
					
					// BUSCA LA VARIABLE OBTENIDA EN LA LISTA DE VARIABLES DE LA INSTANCIA DEL PROCESO
					
					
					
					switch(variableSwitch){		
					case Constants.VPRO:
					    Object inVariableProceso = inVariableProcesoRepository.findByIdCompleto(
					        cveEntidad,
					        cveProceso,
					        version,
					        cveInstancia,
					        variable);

					    //log.info("----------> inVariableProceso EXISTE");
					    if (inVariableProceso != null && ((Object[]) inVariableProceso).length > 0) {
					        //log.info("----------> inVariableProceso PRESENTE " + condicion);

					        variableEncontrada = true;

					        valorAlfanumerico = (String) ((Object[]) inVariableProceso)[0];
					        
					        //log.info("-------------valorAlfanumerico : " + valorAlfanumerico);

					        valorEntero = (Integer) ((Object[]) inVariableProceso)[1];
					        
					        //log.info("-------------valorEntero : " + valorEntero);
					        
					        valorDecimal = (BigDecimal) ((Object[]) inVariableProceso)[2];
					        
					        //log.info("-------------valorDecimal : " + valorDecimal);
					        
					        valorFecha = (Date) ((Object[]) inVariableProceso)[3];
					        
					        //log.info("-------------valorFecha : " + valorFecha);
					        
					        tipo = (String) ((Object[]) inVariableProceso)[4];
					        
					        //log.info("-------------tipo : " + tipo);
					     }
						
						break;
					case Constants.VENT:
						VariableEntidadPK idVE = VariableEntidadPK.builder()
						.cveEntidad(cveEntidad)
						.cveVariable(variable)
						.build();
						Optional<VariableEntidad> variableEntidad = iVariableEntidadRepository.findById(idVE);
						if(variableEntidad.isPresent()) {
							variableEncontrada = true;
							
							VariableEntidad ent = variableEntidad.get();
							valorAlfanumerico = ent.getValorAlfanumerico();
							valorEntero = ent.getValorEntero();
							valorDecimal = ent.getValorDecimal();
							valorFecha = ent.getValorFecha();
							tipo = ent.getTipo();
						}
						break;
					case Constants.VLOC:
						VariableLocalidadPK idVL = VariableLocalidadPK.builder()
						.cveEntidad(cveEntidad)
						.cveLocalidad(cveLocalidad)
						.cveVariable(variable)
						.build();
						Optional<VariableLocalidad> variableLocalidad = iVariableLocalidadRepository.findById(idVL);
						if(variableLocalidad.isPresent()) {
							variableEncontrada = true;
							
							VariableLocalidad ent = variableLocalidad.get();
							valorAlfanumerico = ent.getValorAlfanumerico();
							valorEntero = ent.getValorEntero();
							valorDecimal = ent.getValorDecimal();
							valorFecha = ent.getValorFecha();
							tipo = ent.getTipo();
						}
						break;
					case Constants.VSIS:
						String idVS = variable;
						Optional<VariableSistema> variableSistema = iVariableSistemaRepository.findById(idVS);
						if(variableSistema.isPresent()) {
							variableEncontrada = true;

							VariableSistema ent = variableSistema.get();
							valorAlfanumerico = ent.getValorAlfanumerico();
							valorEntero = ent.getValorEntero();
							valorDecimal = ent.getValorDecimal();
							valorFecha = ent.getValorFecha();
							tipo = ent.getTipo();
						}
						break;

				}
					
					
					
				if (variableEncontrada) {
					
					//log.info("----------> TIPO: " + tipo);
					switch(tipo) {
					case Constants.ALFANUMERICO:
						// Eliminar espacios en blanco al principio y al final
						valorAlfanumerico = valorAlfanumerico != null ? valorAlfanumerico : "";
				        String trimmedValue = valorAlfanumerico.trim();
				        //log.info("----------> trimmedValue: " + trimmedValue);
				        // Reemplazar comillas simples con dos comillas simples
				        String replacedValue = trimmedValue.replace("'", "''");
				        //log.info("----------> replacedValue: " + replacedValue);
				        // Agregar comillas simples alrededor del valor final
				        //valorReemplazar = "'" + replacedValue + "'";
				        valorReemplazar = "'" + replacedValue + "'";
				        //valorReemplazar = "'SI'".equals(valorReemplazar) ? "SI" : valorReemplazar;
				        //log.info("----------> valorReemplazar: " + replacedValue);
						break;
					case Constants.ENTERO:
						valorReemplazar = valorEntero.toString();
						break;
					case Constants.DECIMAL:
						valorReemplazar = valorDecimal.toString();
						break;
					default:
						valorReemplazar = "'" + new SimpleDateFormat("yyyyMMdd").format(valorFecha) + "'";
						//log.info("----------> valorReemplazar: " + valorReemplazar);
						break;
					}
					
					//log.info("----------> valorReemplazar: " + valorReemplazar);
					// Realiza el reemplazo en la cadena
					condicionSalida = condicionSalida.replace("@" + variable + "@", valorReemplazar);
					condicionSalida = condicionSalida.replace("''SI''", "'SI'");
					condicionSalida = condicionSalida.replace("''NO''", "'NO'");
					//log.info("----------> CONDICION SALIDA: " + condicionSalida);
					
				}
			}
		}
	}

		EstatusVariablesTO reemplazaVar = reemplazaVariables(session, nodo, null, condicionSalida);
		if (reemplazaVar.getTipoExcepcion().equals(Constants.OK)) {
			condicionSalida = reemplazaVar.getCadenaSalida();
		}
		
		try {
		
		resultado = Constants.FALSO;
		// EVL quito consulta a base de datos porque no es necesaria
		// Consulta SQL dinámica
		// String sql = "SELECT 'V' WHERE " + condicionSalida;
		// 1. Separar por AND
        // Paso 1: normalizar espacios
        condicionSalida = condicionSalida.trim();

        resultado = evaluarCondicion(condicionSalida);
		
		// Verificar si hay resultados
		/*if (!results.isEmpty()) {
		    Object queryResult = results.get(0);
		    if (queryResult != null) {
		        resultado = Constants.VERDADERO;
		        log.info("----------> resultado con queryResult diferente de null: " + resultado);
		    }
		}*/
		
		//log.info("----------> RESULTADO FINAL: " + resultado);
		
	} catch (BrioBPMException b){
		String mensaje = messagesService.getMessage(
				session,
				idProceso,
				"ERR-EVALUACION-CONDICION",
				variablesValores);
		result.setTipoExcepcion(Constants.ERROR);
		result.setMensaje(mensaje);
		return result;
	}
		result.setResultado(resultado);
	//log.info("RETURN RESULT evaluarCondicion: " + result.toString());
		return result;
	}
	
    private String evaluarCondicion(String condicionSalida) {
        if (condicionSalida == null || condicionSalida.trim().isEmpty()) {
            return Constants.FALSO;
        }

        condicionSalida = condicionSalida.trim();

        // Dividir por OR (menor prioridad)
        String[] partesOr = condicionSalida.split("(?i)\\s+OR\\s+");
        boolean resultadoOr = false;

        for (String parteOr : partesOr) {
            // Dividir por AND (mayor prioridad)
            String[] partesAnd = parteOr.split("(?i)\\s+AND\\s+");
            boolean resultadoAnd = true;

            for (String condicion : partesAnd) {
                condicion = condicion.trim();

                boolean resultadoCondicion = false;
                if (condicion.contains("<>")) {
                    String[] parts = condicion.split("<>");
                    if (parts.length == 2) {
                        String izquierda = parts[0].trim().replace("'", "");
                        String derecha   = parts[1].trim().replace("'", "");
                        resultadoCondicion = !izquierda.equals(derecha);
                    }
                } else if (condicion.contains("=")) {
                    String[] parts = condicion.split("=");
                    if (parts.length == 2) {
                        String izquierda = parts[0].trim().replace("'", "");
                        String derecha   = parts[1].trim().replace("'", "");
                        resultadoCondicion = izquierda.equals(derecha);
                    }
                }

                if (!resultadoCondicion) {
                    resultadoAnd = false;
                    break;
                }
            }

            if (resultadoAnd) {
                resultadoOr = true;
                break;
            }
        }

        return resultadoOr ? Constants.VERDADERO : Constants.FALSO;
    }

	
	// SP_GENERA_EVENTO_BITACORA
	@Override
	public EstatusTO generaEventoBitacora(DatosAutenticacionTO session, NodoTO nodo, String accion)throws BrioBPMException {
		
		String cveProceso = nodo.getCveProceso();
		BigDecimal version = nodo.getVersion();
		String cveInstancia = nodo.getCveInstancia();
		String cveNodo = nodo.getCveNodo();
		Integer idNodo = nodo.getIdNodo();
		Integer secuenciaNodo = nodo.getSecuenciaNodo();
		String cveUsuario = session.getCveUsuario();
		String cveEntidad = session.getCveEntidad();
		
		//log.info("-**********SP_GENERA_EVENTO_BITACORA : " + accion);
		// INICIALIZA CÓDIGO DE ERROR, MENSAJE Y ID_PROCESO
		String idProceso = "GENERA_EVENTO_BITACORA";
		EstatusTO result = EstatusTO.builder()
				.tipoExcepcion(Constants.OK)
				.mensaje("")
				.build();
		
		// CONCATENA LA LISTA DE LOS PARÁMETROS PARA DESPLEGARLOS ENACCION CASO DE ERROR
		String variablesValores = Constants.CVE_PROCESO + cveProceso + "|" +
				Constants.VERSION + version + "|" +
				Constants.CVE_INSTANCIA + cveInstancia + "|" +
				Constants.CVE_NODO + cveNodo + "|" +
				Constants.ID_NODO + idNodo + "|" +
				Constants.SECUENCIA_NODO + secuenciaNodo + "|" +
				Constants.CVE_USUARIO + cveUsuario + "|" +
				Constants.ACCION + accion;
		
		// VALIDA QUE EL NODO ESPECIFICADO EXISTA
		NodoTO datosNodo = NodoTO.builder()
				.idProceso(idProceso)
				.cveProceso(cveProceso)
				.version(version)
				.cveInstancia(cveInstancia)
				.cveNodo(cveNodo)
				.idNodo(idNodo)
				.secuenciaNodo(secuenciaNodo)
				.build();

		EstatusTO valDatos = valDatosIn(session, datosNodo);
		result.setTipoExcepcion(valDatos.getTipoExcepcion());
		result.setMensaje(valDatos.getMensaje());
		//log.info("--->VALIDA DATOS IN NODO: " + result.toString());
		
		if(result.getTipoExcepcion().equals(Constants.ERROR)) {
			log.info("--->ERROR");
			return result;
		}
		
		// REGISTRA LA ACCIÓN EN LA BITÁCORA CON LA FECHA DEL SISTEMA
		InBitacoraNodoPK id = InBitacoraNodoPK.builder()
				.cveEntidad(cveEntidad)
				.cveProceso(cveProceso)
				.version(version)
				.cveInstancia(cveInstancia)
				.cveNodo(cveNodo)
				.idNodo(idNodo)
				.secuenciaNodo(secuenciaNodo)
				.fechaEvento(new Date())
				.build();
		
		InBitacoraNodo entity = InBitacoraNodo.builder()
				.id(id)
				.descripcionEvento(accion)
				.cveUsuarioEvento(cveUsuario)
				.build();
		
		//log.info("PREPARA EN IN BITACORA NODO EN NODO HELPER - generaEventoBitacora");
		iInBitacoraNodoRepository.saveAndFlush(entity);
		//log.info("GUARDADO EN IN BITACORA NODO EN NODO HELPER - generaEventoBitacora");
		
		Optional<InBitacoraNodo> entidadGuardada = iInBitacoraNodoRepository.findById(id);
		//log.info("--->BUSCA POR ID");
		
		if(!entidadGuardada.isPresent()) {
			log.info("--->ERROR AL GUARDAR");
			String mensaje = messagesService.getMessage(
					session,
					idProceso,
					"ERR_CREAR_BITACORA_NODO",
					variablesValores);
			result.setTipoExcepcion(Constants.ERROR);
			result.setMensaje(mensaje);
		}
		
		//log.info("-----> generaEventoBitacora  result: " + result.toString());
		return result;
	}
	
	
	// SP_VAL_DATOS_IN_NODO
	@Override
	public EstatusTO valDatosIn(DatosAutenticacionTO session, NodoTO datosNodo) throws BrioBPMException {
		
		String cveEntidad = session.getCveEntidad();
		String idProceso = datosNodo.getIdProceso();
		String cveProceso = datosNodo.getCveProceso();
		BigDecimal version = datosNodo.getVersion();
		String cveInstancia = datosNodo.getCveInstancia();
		String cveNodo = datosNodo.getCveNodo();
		Integer secuenciaNodo = datosNodo.getSecuenciaNodo();
		Integer idNodo = datosNodo.getIdNodo();
		
		// INICIALIZA CÓDIGO DE ERROR, MENSAJE Y SUGERENCIA
		EstatusTO result = EstatusTO.builder()
				.tipoExcepcion(Constants.OK)
				.mensaje("")
				.build();
		
		// VALIDA QUE EXISTA LA INFORMACIÓN DEL NODO
		//log.info("**********SP_VAL_DATOS_IN_NODO");
		String variablesValores = Constants.CVE_PROCESO + cveProceso + "|" +
				Constants.VERSION + version + "|" +
				Constants.CVE_INSTANCIA + cveInstancia + "|" +
				Constants.CVE_NODO + cveNodo + "|" +
				Constants.SECUENCIA_NODO + secuenciaNodo;
		
		InNodoProcesoPK id = InNodoProcesoPK.builder()
				.cveEntidad(cveEntidad)
				.cveProceso(cveProceso)
				.version(version)
				.cveInstancia(cveInstancia)
				.cveNodo(cveNodo)
				.idNodo(idNodo)
				.secuenciaNodo(secuenciaNodo)
				.build();
		
		Optional<InNodoProceso> existe = iInNodoProcesoRepository.findById(id);
		if(!existe.isPresent()) {
			////log.info("---->ENTRA AL IF existe");
			String mensaje = messagesService.getMessage(
					session, idProceso,
					Constants.ACTIVIDAD_NO_EXISTE,
					variablesValores);
			result.setTipoExcepcion(Constants.ERROR);
			result.setMensaje(mensaje);
		}
		
		//log.info("----> EXISTE valDatosIn: " + result.toString());
		return result;
		
	}
		
	// SP_TERMINA_COMPUERTA_CIERRE
	@Override
	public EstatusCompuertaTO terminaCompuertaCierre(DatosAutenticacionTO session, NodoTO nodo, String cveNodoCompuerta,
			Integer idNodoCompuerta) throws BrioBPMException {		
		
		log.info("**********SP_TERMINA_COMPUERTA_CIERRE");
		
		String cveEntidad = session.getCveEntidad();
		String cveLocalidad = session.getCveLocalidad();
		String cveIdioma = session.getCveIdioma();
		String cveProceso = nodo.getCveProceso();
		BigDecimal version = nodo.getVersion();
		String cveInstancia = nodo.getCveInstancia();
		String cveNodo = nodo.getCveNodo();
		Integer idNodo = nodo.getIdNodo();
		Integer secuenciaNodo = nodo.getSecuenciaNodo();
		String idProceso;	
		String cveNodoInicio;
		Integer idNodoInicio;
		Integer secuenciaNodoCompuerta = null;
		Integer numNodosPrevios;
		Integer numNodosTerminados;
		String actividadUsuario;
		String actividadUsuarioTemp;
		String temporizador;
		String estadoRegistro;
		Integer secuenciaNodoInicio;
		String estadoCompuerta;
		
		// INICIALIZA CÓDIGO DE ERROR, MENSAJE Y SUGERENCIA
		EstatusCompuertaTO result = EstatusCompuertaTO.builder()
				.tipoExcepcion(Constants.OK)
				.mensaje("")
				.build();
		idProceso = "TERMINA_COMPUERTA_CIERRE";
		
		String variablesValores = Constants.CVE_ENTIDAD + cveEntidad + "|" +
				Constants.CVE_LOCALIDAD + cveLocalidad + "|" +
				Constants.CVE_IDIOMA + cveIdioma + "|" +
				Constants.CVE_PROCESO + cveProceso + "|" +
				Constants.VERSION + version + "|" +
				Constants.CVE_INSTANCIA + cveInstancia + "|" +
				Constants.CVE_NODO + cveNodo + "|" +
				Constants.ID_NODO + idNodo + "|" +
				Constants.SECUENCIA_NODO + secuenciaNodo + "|" +
				Constants.CVE_NODO_COMPUERTA + cveNodoCompuerta + "|" +
				Constants.ID_NODO_COMPUERTA + idNodoCompuerta + "|" +
				Constants.SECUENCIA_NODO_COMPUERTA + secuenciaNodoCompuerta;
		
		// TODO EVL INICIALIZA EL ESTADO DE LA COMPUERTA
		estadoCompuerta = "REGISTRO";
		result.setEstadoCompuerta(estadoCompuerta);
		
		// CONSTANTES
		actividadUsuario = Constants.ACTIVIDAD_USUARIO;
		actividadUsuarioTemp = Constants.ACTIVIDAD_USUARIO_TEMPORIZACION;
		temporizador = Constants.TEMPORIZADOR;
		estadoRegistro = "REGISTRO";
		
		//OBTIENE LOS DATOS DE LA COMPUERTA DE INICIO EN LA CONFIGURACIÓN
		cveNodoInicio = "";
		idNodoInicio = null;
		
		Integer resultado = iStCompuertaInicioRepository.existeStCompuertaInicio(
				cveEntidad,
				cveProceso,
				version,
				cveNodoCompuerta,
				idNodoCompuerta);
			
		if(resultado == null) {
			log.info("-------IF existeStCompuertaInicio");
			String mensaje = messagesService.getMessage(
					session, idProceso,
					"ERR-OBTENER-COMPUERTA-INICIO", variablesValores);
			result.setTipoExcepcion(Constants.ERROR);
			result.setMensaje(mensaje);
			return result;
		}
		
		secuenciaNodoCompuerta = null;
		secuenciaNodoCompuerta = iInNodoProcesoRepository.encontrarMaxSecuenciaNodo(
				cveEntidad,
				cveProceso, //revisar
				version,
				cveInstancia,
				cveNodoCompuerta,
				idNodoCompuerta,
				estadoRegistro);
		
		if(secuenciaNodoCompuerta == null) {
			String mensaje = messagesService.getMessage(
					session, idProceso,
					"ERR-OBTENER-COMPUERTA_CIERRE", variablesValores);
			result.setTipoExcepcion(Constants.ERROR);
			result.setMensaje(mensaje);
			return result;
		}
		
		// IDENTIFICA LA COMPUERTA DE INICIO PARA, EN CASO NECESARIO CERRARLA
		Optional<StCompuertaInicio> compuertaInicio = iStCompuertaInicioRepository.identificaCompuertaInicio(
				cveEntidad,
				cveProceso,
				version,
				cveNodoCompuerta, 
				idNodoCompuerta);	
		cveNodoInicio = compuertaInicio.get().getId().getCveNodoInicio();
		idNodoInicio = compuertaInicio.get().getId().getIdNodoInicio();
		log.info("----------cveNodoInicio: " + cveNodoInicio);
		log.info("----------idNodoInicio: " + idNodoInicio);

		secuenciaNodoInicio = iInNodoProcesoRepository.encontrarMaxSecuenciaNodo(
				cveEntidad,
				cveProceso,
				version,
				cveInstancia,
				cveNodoInicio,
				idNodoInicio,
				estadoRegistro);
		log.info("----------secuenciaNodoInicio: " + secuenciaNodoInicio);

		// CUENTA EL NÚMERO DE NODOS REGISTRADOS O TERMINADOS PREVIOS A LA COMPUERTA DE CIERRE
		List<String> nodos = Arrays.asList(actividadUsuario, actividadUsuarioTemp, temporizador);
		numNodosPrevios = 0;
		numNodosPrevios = iStNodoSiguienteRepository.contarNodosPrevios(
				cveEntidad,
				cveProceso,
				version,
				cveNodoCompuerta,
				idNodoCompuerta,
				cveInstancia,
				nodos);
		numNodosPrevios = (numNodosPrevios != null) ? numNodosPrevios : 0;
		log.info("----------numNodosPrevios: " + numNodosPrevios);
		
		// CUENTA EL NÚMERO DE NODOS TERMINADOS PREVIOS A LA COMPUERTA DE CIERRE
		numNodosTerminados = 0;
		numNodosTerminados = iStNodoSiguienteRepository.contarNodosTerminados(
				cveEntidad,
				cveProceso,
				version,
				cveNodoCompuerta,
				idNodoCompuerta,
				cveInstancia,
				nodos);
		log.info("----------numNodosTerminados: " + numNodosTerminados);
		
		InNodoProcesoPK id1 = InNodoProcesoPK.builder()
				.cveEntidad(cveEntidad)
				.cveProceso(cveProceso)
				.version(version)
				.cveInstancia(cveInstancia)
				.cveNodo(cveNodoCompuerta)
				.idNodo(idNodoCompuerta)
				.secuenciaNodo(secuenciaNodoCompuerta)
				.build();

		InNodoProcesoPK id2 = InNodoProcesoPK.builder()
				.cveEntidad(cveEntidad)
				.cveProceso(cveProceso)
				.version(version)
				.cveInstancia(cveInstancia)
				.cveNodo(cveNodoInicio)
				.idNodo(idNodoInicio)
				.secuenciaNodo(secuenciaNodoInicio)
				.build();
		
		Optional<InNodoProceso> entidad1 = iInNodoProcesoRepository.findById(id1);
		Optional<InNodoProceso> entidad2 = iInNodoProcesoRepository.findById(id2);

		// SI COINCIDEN EL NÚMERO DE NODOS GENERADOS EN LA COMPUERTA DE INICIO CON EL
		// NÚMERO DE NODOS TERMINADOS ANTES DE LA COMPUERTA DE CIERRE,
		// SE TERMINA LA COMPURETA DE CIERRE
		if ((((numNodosPrevios == numNodosTerminados && secuenciaNodoCompuerta != null
				&& cveNodoCompuerta.equals(Constants.COMPUERTA_PARALELA_CIERRE))
				|| (!cveNodoCompuerta.equals(Constants.COMPUERTA_PARALELA_CIERRE))))
				/*&& (entidad1.isPresent() && entidad2.isPresent())*/) {
			
			estadoCompuerta = "TERMINADA";
			result.setEstadoCompuerta(estadoCompuerta);
			
			entidad1.get().setEstado(estadoCompuerta);
			log.info("Se ROMPE AQUI YYYYY");
			iInNodoProcesoRepository.saveAndFlush(entidad1.get());
			entidad1.get().setEstado(estadoCompuerta);
			log.info("Se ROMPE AQUI ZZZZZ");
			iInNodoProcesoRepository.saveAndFlush(entidad2.get());
			
			if(entidad1.get().getEstado().equals(estadoCompuerta)
					&& entidad2.get().getEstado().equals(estadoCompuerta) ) {
				String mensaje = messagesService.getMessage(
						session, idProceso,
						"ERR-UPD-TAB-IN_NODO_PROCESO", variablesValores);
				result.setTipoExcepcion(Constants.ERROR);
				result.setMensaje(mensaje);
				return result;
			}
			
			
			String evento = "";
			if (cveNodoCompuerta.equals(Constants.COMPUERTA_PARALELA_CIERRE)) {
				evento = "COMPUERTA_PARALELA_CIERRE";
				
	    		//CREA EL MENSAJE DE CORREO PARA EL EVENTO DE TERMINADO
	    		//SVM 23-04-2025
	    		int ocurrencia = 1;
			    NodoTO nodoCorreo = NodoTO.builder()
			    		.cveProceso(cveProceso)
			    		.version(version)
			    		.cveEventoCorreo(evento)
			    		.cveUsuarioDestinatario(null)
			    		.cveRolDestinatario(null)
			    		.cveInstancia(cveInstancia)
			    		.cveNodo(cveNodoInicio)
			    		.idNodo(idNodoInicio)
			    		.secuenciaNodo(secuenciaNodoInicio)
			    		.ocurrencia(ocurrencia)
			    		.build();
			    
				EstatusTO creaCP = creaCorreoProceso(session, nodoCorreo, null, session.getCveUsuario());
			    log.info("----------> CREA CORREO PROCESO: " + creaCP.toString());
			    result.setTipoExcepcion(creaCP.getTipoExcepcion());
				result.setMensaje(creaCP.getTipoExcepcion());
			    
			    if(creaCP.getTipoExcepcion().equals(Constants.ERROR)) {
			    	return result;
			    }
			} 
		    


		} 
		return result;
	}
	
	
	// SP_CREA_CORREO_PROCESO
	@Override
	public EstatusTO creaCorreoProceso(DatosAutenticacionTO session, NodoTO nodo, Integer secuenciaDocumento,
			String cveUsuarioCreador)  throws BrioBPMException {
		
		String cveUsuario = session.getCveUsuario();
    	String cveEntidad = session.getCveEntidad();
    	String cveProceso = nodo.getCveProceso();
    	BigDecimal version = nodo.getVersion();
    	String cveEventoCorreo = nodo.getCveEventoCorreo();
    	String cveUsuarioDestinatario = nodo.getCveUsuarioDestinatario();
    	String cveRolDestinatario = nodo.getCveRolDestinatario();
    	String cveNodo = nodo.getCveNodo();
    	Integer idNodo = nodo.getIdNodo();
    	Integer ocurrencia = nodo.getOcurrencia();
    	//String usuarioAsignado = nodo.getCveUsuarioAsignado();
    	String cveRol = null;
    	String direccionCorreo;
    	String grupoCorreo;
    	String grupoAnterior;
    	String tipoDestinatario;
    	String tipoDestinatarioPara;
    	String tipoDestinatarioCcp;
    	Integer secuenciaCorreo;
    	String usoCveUsuarioCreador;
    	String enviarCorreo;
    	String condicionCorreo;
    	String asunto;
    	String asuntoEntrada;
    	String cuerpo;
    	String cuerpoEntrada;
    	String para;
    	String conCopiaPara;
    	Integer numeroCorreo;
    	String creado;
    	
    	
    	//INICIALIZA TIPO DE EXCEPCIÓN, MENSAJE E ID_PROCESO
    	EstatusTO result = EstatusTO.builder()
				.tipoExcepcion(Constants.OK)
				.mensaje("")
				.build();
    	
		// OTRAS CONSTANTES
		creado = "CREADO";
		tipoDestinatarioPara = "PARA";
		tipoDestinatarioCcp = "CCP";
		
		/*log.info("-----> creaCorreoProces <------ ");
		log.info("-----> cveEntidad: " + cveEntidad);
		log.info("-----> cveProceso: " + cveProceso);
		log.info("-----> version: " + version);
		log.info("-----> cveEventoCorreo: " + cveEventoCorreo);
		log.info("-----> cveUsuarioDestinatario: " + cveUsuarioDestinatario);
		log.info("-----> cveRolUsuarioDestinatario: " + cveRolDestinatario);
		log.info("-----> cveInstancia: " + nodo.getCveInstancia());
		log.info("-----> cveNodo: " + cveNodo);
		log.info("-----> idNodo: " + idNodo);
		log.info("-----> secuenciaNodo: " + nodo.getSecuenciaNodo());
		log.info("-----> ocurrencia: " + ocurrencia);
		log.info("-----> secuenciaDocumento: " + secuenciaDocumento);
		log.info("-----> cveUsuarioCreador: " + cveUsuarioCreador);
		log.info("-----> cveInstancia: " + nodo.getCveInstancia());
		log.info("-----> secuenciaNodo: " + nodo.getSecuenciaNodo());
		log.info("-----> TipoConsulta: " + nodo.getTipoConsulta());*/
		
		//SVM ajuste de query para el manejo de correos
		// CURSOR QUE CONTIENE LA LISTA DE CORREOS CONFIGURADOS
	    // Obtiene la lista de correos configurados para el proceso y evento proporcionados.
		List<CorreoProceso> listaCorreos = iCorreoProcesoRepository.obtenerCorreos(
				cveEntidad,
				cveProceso,
				version,
				cveEventoCorreo,
				cveNodo,
				idNodo);
		log.info("----> LISTA CORREOS: " + listaCorreos.size());
		
		// RECORRE LA LISTA DE CORREOS DEL PROCESO PARA EL EVENTO PROPORCIONADO
		// Recorre la lista de correos configurados para procesarlos.
		for(CorreoProceso correoPro : listaCorreos) {
			secuenciaCorreo = correoPro.getId().getSecuenciaCorreo();
//			cveNodo = correoPro.getCveNodo();
//			idNodo = correoPro.getIdNodo();
			asunto = correoPro.getAsunto();			
			cuerpo = correoPro.getCuerpo();
			condicionCorreo = correoPro.getCondicion();
			
			enviarCorreo = Constants.SI;
			
			// Si hay una condición para enviar el correo, se evalúa.
			if(condicionCorreo != null && !condicionCorreo.trim().isEmpty()) {
				log.info("CONDICION CORREO: " + condicionCorreo);
				EstatusCondicionTO evaluaCon = evaluarCondicion(session, nodo, condicionCorreo);
				log.info("RESULT DESPUES DE EVALUAR CONDICION: " + evaluaCon.getTipoExcepcion() + " " + evaluaCon.getMensaje());
				
	            // Si la evaluación da un error, se devuelve ese error en el resultado.
				if(evaluaCon.getMensaje().equals(Constants.ERROR)) {
					log.info("ERROR DESPUES DE EVALUAR CONDICION");
					result.setTipoExcepcion(evaluaCon.getTipoExcepcion());
					result.setMensaje(evaluaCon.getMensaje());
					return result;
				}
				
	            // Si la condición no se cumple, no se enviará el correo.
				if (evaluaCon.getResultado().equals(Constants.FALSO)) {
					enviarCorreo = Constants.NO;
				}
			}
			
			// ARMA LA LISTA DE DESTINATARIOS
	        // Si se debe enviar el correo, se preparan los detalles.
			if(enviarCorreo.equals(Constants.SI)) {
				
				//ARMA EL ASUNTO Y EL CUERPO
				//REEMPLAZA VARIABLES EN ASUNTO Y EN CUERPO DEL CORREO
				asuntoEntrada = asunto;
				//log.info("-------> REEMPLAZA VARIABLES EN CREAR CORREO PROCESO");
				//log.info("------Nodo que entra a reemplazaVariables: " + nodo.toString());
				EstatusVariablesTO reemplazaVar;
				
				reemplazaVar = reemplazaVariables(session, nodo, secuenciaDocumento, asuntoEntrada);
				
				asunto = reemplazaVar.getCadenaSalida();
				result.setTipoExcepcion(reemplazaVar.getTipoExcepcion());
				result.setMensaje(reemplazaVar.getMensaje());
				log.info("RESULT DESPUES DE REEMPLAZAR VARIABLES 1: " + result.toString());
				
		//log.info("------->-> REEMPLAZA VARIABLES EN CREAR CORREO PROCESO 2");
				cuerpoEntrada = cuerpo;
				EstatusVariablesTO reemplazaVar2 = reemplazaVariables(session, nodo, secuenciaDocumento, cuerpoEntrada);
				
				cuerpo = reemplazaVar2.getCadenaSalida();
				result.setTipoExcepcion(reemplazaVar2.getTipoExcepcion());
				result.setMensaje(reemplazaVar2.getMensaje());
				log.info("RESULT DESPUES DE REEMPLAZAR VARIABLES 2: " + result.toString());
				
				
			//} svm no esta este cierre en el sp
			
   
	  			log.info("----> cveEntidad:-" + cveEntidad + "-");
				log.info("----> cveProceso: " + cveProceso);
				log.info("----> version: " + version);
				log.info("----> cveEventoCorreo: " + cveEventoCorreo);
				log.info("----> secuenciaCorreo: " + secuenciaCorreo);
				log.info("----> tipoDestinatarioPara " + tipoDestinatarioPara);
				log.info("----> cveRolDestinatario: " + cveRolDestinatario);
				log.info("----> cveUsuarioDestinatario: " + cveUsuarioDestinatario);
			 
				//SVM ajuste de query para el manejo de destinatarios
				//CURSOR QUE CONTIENE LA LISTA DE DESTINATARIOS
		        // Obtiene la lista de destinatarios del correo para la secuencia actual.
				List<Object[]> destinatarios = iDestinarioCorreoRepository.obtenerDestinatariosCorreo(
						cveEntidad,
						cveProceso,
						version,
						cveEventoCorreo,
						secuenciaCorreo,
						tipoDestinatarioPara,
						cveRolDestinatario,
						cveUsuarioDestinatario);
				//Se
				
				grupoAnterior = "";
				para = "";
				conCopiaPara = "";
				String correoPendiente  = "";
				
				//SVM ajuste para integrar al suario asignado 
				if (nodo.getCveUsuarioAsignado() != null) {

					for (String cveUsuAsig : nodo.getCveUsuarioAsignado()) {
						direccionCorreo = fnLeeDireccionCorreo(
								cveEntidad,
								cveProceso,
								version,
								cveUsuAsig,
								cveRolDestinatario);
		                para = para.isEmpty() ? direccionCorreo : para + "|" + direccionCorreo;
					}
				} else {
					
									
			        // Recorre la lista de destinatarios para configurar los correos a enviar.
					for (Object[] ite : destinatarios) {
						/*grupoCorreo = destinatario.getId().getGrupoCorreo();
						tipoDestinatario = destinatario.getTipoDestinatario();
						usoCveUsuarioCreador = destinatario.getUsoCveUsuarioCreador();
						cveRolDestinatario = destinatario.getCveRol();
						cveUsuarioDestinatario = destinatario.getCveUsuario();*/
						grupoCorreo = (String) ite[0];
						BigDecimal secCorreo = (BigDecimal) ite[1];
						secuenciaCorreo = secCorreo.intValue();
						tipoDestinatario = (String) ite[2];
						usoCveUsuarioCreador = (String) ite[3];
						cveRolDestinatario = (String) ite[4];
						cveUsuarioDestinatario = (String) ite[5];
						
						correoPendiente  = "SI";
						
						log.info("---->grupoCorreo {}", grupoCorreo);
						log.info("---->tipoDestinatario {}", tipoDestinatario);
						log.info("---->usoCveUsuarioCreador {}", usoCveUsuarioCreador);
						log.info("---->cveRolDestinatario {}", cveRolDestinatario);
						log.info("---->cveUsuarioDestinatario {}", cveUsuarioDestinatario);
						log.info("----> DestinarioCorreo grupoCorreo = {},  grupoAnterior = {}", grupoCorreo, grupoAnterior);
						
			            // Si cambia el grupo de correo, se crea un nuevo mensaje.
						if(!grupoCorreo.equals(grupoAnterior) && !grupoAnterior.isEmpty()) {
							//CREA EL MENSAJE					
							Integer comCorreo = iComposicionCorreoRepository.existeComposicionCorreo(cveProceso, version);
							if(comCorreo !=  null) {
								numeroCorreo = iComposicionCorreoRepository.obtieneNumeroCorreo(cveProceso, version);
								numeroCorreo = ((numeroCorreo != null) ? numeroCorreo : 0) + 1;
							} else {
								numeroCorreo = 1;
							}
							
			                // Se guarda la composición del correo en la base de datos.
							ComposicionCorreoPK id = ComposicionCorreoPK.builder()
									.cveEntidad(cveEntidad)
									.cveProceso(cveProceso)
									.version(version)
									.numeroCorreo(numeroCorreo)
									.build();
							ComposicionCorreo entidad = ComposicionCorreo.builder()
									.id(id)
									.fechaComposicion(new Date())
									.referencia(cveEventoCorreo)
									.situacionCorreo(creado)
									.fechaEnvio(null)
									.para(para)
									.conCopiaPara(conCopiaPara)
									.asunto(asunto)
									.mensaje(cuerpo)
									.cveNodo(cveNodo)
									.idNodo(idNodo)
									.secuenciaNodo(nodo.getSecuenciaNodo())
									.build();
						
							log.info("PREPARA EN COMPOSICION CORREO EN NODO HELPER - creaCorreoProceso");
							iComposicionCorreoRepository.saveAndFlush(entidad);
							log.info("GUARDADO EN COMPOSICION CORREO EN NODO HELPER - creaCorreoProceso");

							para = "";
							conCopiaPara = "";					
						}
						grupoAnterior = grupoCorreo;
						
						log.info("----> DestinarioCorreo destinatario.getTipoDestinatario() = {},  tipoDestinatarioPara = {}", tipoDestinatario, tipoDestinatarioPara);
						// ARMA LAS DIRECCIONES DE LOS DESTINATARIOS
			            // Configura las direcciones de correo de los destinatarios.
						if(tipoDestinatario.equals(tipoDestinatarioPara)) {
							//AR Se quita condicion para que todos los procesos entren a para
							//if(usoCveUsuarioCreador != null && usoCveUsuarioCreador.equals(Constants.SI)) {
								cveUsuarioDestinatario = cveUsuarioCreador;
								direccionCorreo = fnLeeDireccionCorreo(
										cveEntidad,
										cveProceso,
										version,
										cveUsuarioDestinatario,
										cveRolDestinatario);
				                para = para.isEmpty() ? direccionCorreo : para + "|" + direccionCorreo;
							//}
							


						} else {
							if(tipoDestinatario.equals(tipoDestinatarioCcp)) {
								direccionCorreo = fnLeeDireccionCorreo(
										cveEntidad,
										cveProceso,
										version,
										cveUsuario,
										cveRol);
								
			                    conCopiaPara = conCopiaPara.isEmpty() ? direccionCorreo : conCopiaPara + "|" + direccionCorreo;

							}
						}				
						//	cveUsuarioDestinatario = destinatario.getCveUsuario(); svm se comenta
					}					
				}
				//cveRolDestinatario,
				//cveUsuarioDestinatario

		    Integer composicionCorreo = iComposicionCorreoRepository.existeComposicionCorreo(cveProceso, version);
			numeroCorreo = 1;
			if(composicionCorreo != null) {
				numeroCorreo = iComposicionCorreoRepository.obtieneNumeroCorreo(cveProceso, version);
				numeroCorreo = (numeroCorreo != null) ? numeroCorreo : 0;
				numeroCorreo += 1;
			} else {
				numeroCorreo = 1;
			}
			
			if (para != null && !para.isEmpty()) {
				ComposicionCorreoPK id = ComposicionCorreoPK.builder()
						.cveEntidad(cveEntidad)
						.cveProceso(cveProceso)
						.version(version)
						.numeroCorreo(numeroCorreo)
						.build();
				ComposicionCorreo entidad2 = ComposicionCorreo.builder()
						.id(id)
						.fechaComposicion(new Date())
						.referencia(cveEventoCorreo)
						.situacionCorreo(creado)
						.fechaEnvio(null)
						.para(para)
						.conCopiaPara(conCopiaPara)
						.asunto(asunto)
						.mensaje(cuerpo)
						.cveNodo(cveNodo)
						.idNodo(idNodo)
						.secuenciaNodo(nodo.getSecuenciaNodo())
						.build();				

				log.info("PREPARA EN COMPOSICION CORREO EN NODO HELPER 2 - creaCorreoProceso");
				iComposicionCorreoRepository.saveAndFlush(entidad2);
				log.info("GUARDADO EN COMPOSICION CORREO EN NODO HELPER 2 - creaCorreoProceso");
				}
				
	
			}
			
			log.info("Sali del for CR_CURSOR_CORREOS");
		}
        log.info("############### RETURN SP_CREA_CORREO_PROCESO: " + result.toString());
    	return result;
	}
    
	// FN_LEE_DIRECCION_CORREO
    @Override
	public String fnLeeDireccionCorreo(String cveEntidad, String cveProceso, BigDecimal version,
			String cveUsuario, String cveRol) throws BrioBPMException{
		
		String direccionCorreo;
		String correoElectronicoRol;
		
		direccionCorreo = "";
		if(cveUsuario != null && !cveUsuario.isEmpty()) {
			
			UsuarioPK id = UsuarioPK.builder()
					.cveEntidad(cveEntidad)
					.cveUsuario(cveUsuario)
					.build();
			
			Optional<Usuario> usuarioE = iUsuarioRepository.findById(id);
			if (usuarioE.isPresent()) {
				direccionCorreo = usuarioE.get().getCorreoElectronico();	
			}
		} else {
			if(cveRol != null && !cveRol.isEmpty()) {
				
				List<String> correosRol = iUsuarioRolRepository.obtieneDireccionesCorreoRol(
						cveEntidad,
						cveProceso,
						version,
						cveRol
						);
				for(String usuarioRolFor : correosRol) {
					correoElectronicoRol = usuarioRolFor;
					if(direccionCorreo == "") {
						direccionCorreo = correoElectronicoRol;
						//log.info("-------> DIRECCION CORREO IF : " + direccionCorreo);
					} else {
						direccionCorreo = direccionCorreo + "|" + correoElectronicoRol;
						//log.info("-------> DIRECCION CORREO ELSE : " + direccionCorreo);
					}
				}
			}
		}
        log.info("############### RETURN FN_LEE_DIRECCION_CORREO: " + direccionCorreo);
		return direccionCorreo;
		
	}
	
	
	// FN_TRUNCA_DECIMALES
	@Override
		public String truncarDecimales(BigDecimal valorDecimal, Integer numeroDecimales)throws BrioBPMException{
		
		log.info("----truncarDecimaleS---");
		String valorDecimalAlf;
		String punto;
		Integer posicionPunto;
		Integer longitud;
		Integer numeroCaracteres;
		String enteroAlfanumerico;
		String decimalAlfanumerico;
		String resultado;
		
		//INICIALIZANDO VARIABLES
		punto = ".";
		
		if(valorDecimal == null) {
			return null;
		}
		//OBTIENE LA PARTE ENTERA
		valorDecimalAlf = valorDecimal.toString();
		posicionPunto = valorDecimalAlf.indexOf(punto);
		numeroDecimales = numeroDecimales != null ? numeroDecimales : 0; //  verificar de mas atras
		if(posicionPunto == -1) {
			resultado = String.format("%." + numeroDecimales + "f", Double.parseDouble(valorDecimalAlf.trim()));

		} else {
			enteroAlfanumerico = valorDecimalAlf.substring(0, posicionPunto);
			longitud = valorDecimalAlf.length();
			
			if (posicionPunto + numeroDecimales > longitud) {
                numeroCaracteres = longitud - posicionPunto;
                decimalAlfanumerico = valorDecimalAlf.substring(posicionPunto + 1, numeroCaracteres);
            } else {
                decimalAlfanumerico = valorDecimalAlf.substring(posicionPunto + 1, posicionPunto + 1 + numeroDecimales);
            }
			resultado = enteroAlfanumerico;
			if(numeroDecimales > 0) {
	            resultado = enteroAlfanumerico + "." + decimalAlfanumerico;
			}
		}
			return resultado;
	}
	
	//FN_FORMATEA_FECHA
	// Este método se encarga de formatear una fecha (Date) en un formato específico que se le pasa como parámetro.
	// Los formatos que maneja son los siguientes:
	//  - "dd/MM/yyyy HH:mm:ss": Fecha en formato día/mes/año con horas, minutos y segundos.
	//  - "dd/MM/yyyy": Fecha en formato día/mes/año.
	//  - "yyyy-MM-dd HH:mm:ss": Fecha en formato año-mes-día con horas, minutos y segundos.
	//  - "yyyy-MM-dd'T'HH:mm:ss": Fecha en formato año-mes-día con una "T" separando la fecha y la hora.
	//  - "yyyy-MM-dd": Fecha en formato año-mes-día.
	//
	// El método utiliza `SimpleDateFormat` para formatear la fecha en los diferentes formatos de salida.

	@Override
	public String formatFecha(Date fecha, String formato) {
	    String ddMMyyyyHHmmss = "dd/MM/yyyy HH:mm:ss";
	    String ddMMyyyy = "dd/MM/yyyy";
	    String yyyyMMddHHmmss = "yyyy-MM-dd HH:mm:ss";
	    String yyyyMMddTHHmmss = "yyyy-MM-dd'T'HH:mm:ss";
	    String yyyy_MM_dd = "yyyy-MM-dd";
	    String ddMMMMyyyyES = "dd MMMM yyyy"; // Formato con mes en español
	    String ddMMMMyyyyEN = "MMMM dd, yyyy"; // Formato con mes en inglés
	    String yyyyMMdd = "yyyyMMdd";
	    String fechaFinal;

	    SimpleDateFormat sdfFecha = new SimpleDateFormat("dd/MM/yyyy");
	    SimpleDateFormat sdfHora = new SimpleDateFormat("HH:mm:ss");

	    log.info("------> FECHA: " + fecha);
	    log.info("------> FORMATO: " + formato);
	    fechaFinal = "";

	    if (fecha != null) {
	        // Convertimos la fecha a String para poder manipularla fácilmente
	        String fechaStr = fecha.toString();
	        log.info("------> FECHA STRING: " + fechaStr);
	        String horaStr = sdfHora.format(fecha);
	        log.info("------> HORA STRING: " + horaStr);

	        // Verificamos el formato solicitado y formateamos la fecha en consecuencia
	        if (ddMMyyyy.equals(formato)) {
	            log.info("------> ddMMyyyy ");
	            // Ejemplo de salida: 15/08/2024
	            fechaFinal = sdfFecha.format(fecha);
	        } else if (ddMMyyyyHHmmss.equals(formato)) {
	            log.info("------> ddMMyyyyHHmmss ");
	            // Ejemplo de salida: 15/08/2024 18:12:57
	            fechaFinal = sdfFecha.format(fecha) + " " + horaStr;
	        } else if (yyyyMMddHHmmss.equals(formato)) {
	            log.info("------> yyyyMMddHHmmss ");
	            // Ejemplo de salida: 2024-08-15 18:12:57
	            fechaFinal = fechaStr.substring(6, 10) + "-" + fechaStr.substring(3, 5) + "-" + fechaStr.substring(0, 2)
	                + " " + horaStr;
	        } else if (yyyyMMddTHHmmss.equals(formato)) {
	            log.info("------> yyyyMMddTHHmmss ");
	            // Ejemplo de salida: 2024-08-15T18:12:57
	            fechaFinal = fechaStr.substring(6, 10) + "-" + fechaStr.substring(3, 5) + "-" + fechaStr.substring(0, 2)
	                + "T" + horaStr;
	        } else if (yyyy_MM_dd.equals(formato)) {
	            log.info("------> yyyyMMdd ");
	            // Ejemplo de salida: 2024-08-15
	            fechaFinal = fechaStr.substring(0, 10);
	        } else if (ddMMMMyyyyEN.equals(formato)) {
	            log.info("------> ddMMMMyyyyEN ");
	            // Ejemplo de salida: August 15, 2024
	            SimpleDateFormat sdfFechaConMesEscritoEn = new SimpleDateFormat("MMMM dd, yyyy", Locale.ENGLISH);
	            fechaFinal = sdfFechaConMesEscritoEn.format(fecha);
	        }  else if (ddMMMMyyyyES.equals(formato)) {
	            log.info("------> ddMMMMyyyyES ");
	            // Ejemplo de salida: 15 agosto 2024
	            SimpleDateFormat sdfFechaConMesEscrito = new SimpleDateFormat("dd MMMM yyyy", new Locale("es"));
	            fechaFinal = sdfFechaConMesEscrito.format(fecha);
	        }  else if (yyyyMMdd.equals(formato)) {
	            log.info("------> yyyyMMdd ");
	            // Ejemplo de salida: 2024-10-31
	            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	            fechaFinal = sdf.format(fecha);
	        } else {
	            // Si el formato no coincide con ninguno de los esperados, se retorna una cadena vacía.
	            fechaFinal = "";
	        }
	    }
	    log.info("------> FECHA FINAL: " + fechaFinal);
	    return fechaFinal;
	}

	
	// SP_CREA_MENSAJE_NODO
	@Override
	public EstatusTO creaMensaje(DatosAutenticacionTO session, NodoTO nodo) throws BrioBPMException, ParseException{
		
		String idProceso = nodo.getIdProceso();
		String cveProceso = nodo.getCveProceso();
		BigDecimal version = nodo.getVersion();
		String cveInstancia = nodo.getCveInstancia();
		String cveNodo = nodo.getCveNodo();
		Integer idNodo = nodo.getIdNodo();
		Integer secuenciaNodo = nodo.getSecuenciaNodo();
		String cveEntidad = session.getCveEntidad();
		String cveLocalidad = session.getCveLocalidad();
		String mensaje;
		String pendiente;
		String referenciaVariables;
		Integer folioMensajeEnvio;
		String variableReferenciaEnvio = null;
		String variableReferenciaRec = null;
		String valoresReferenciaEnvio = null;
		String valoresReferenciaRec = null;
		Integer ocurrencia;
		
		// INICIALIZA CÓDIGO DE ERROR, MENSAJE Y SUGERENCIA
		//Optional<ParametroGeneral> parametroG = iParametroGeneralRepository.findById("DESPLEGAR_RASTREO");
		EstatusTO result = EstatusTO.builder()
				.tipoExcepcion(Constants.OK)
				.mensaje("")
				.build();
		String variablesValores = Constants.CVE_USUARIO + session.getCveUsuario() + "|" +
				Constants.CVE_ENTIDAD + cveEntidad + "|" +
				Constants.CVE_LOCALIDAD + cveLocalidad + "|" +
				Constants.CVE_PROCESO + cveProceso + "|" +
				Constants.VERSION + version + "|" +
				Constants.CVE_INSTANCIA + cveInstancia + "|" +
				Constants.CVE_NODO + cveNodo + "|" +
				Constants.SECUENCIA_NODO + secuenciaNodo;
		pendiente = "PENDIENTE";
		
		// LEE CONFIGURACIÓN DEL MENSAJE
		if(cveNodo.equals(Constants.EVENTO_INTERMEDIO_ENVIO)) {
			EstatusConfiguracionEnvioTO leeConfigE = 
					leeConfiguracionEnvio(session, nodo);
			variableReferenciaEnvio = leeConfigE.getVariablesReferenciaEnv();
			if(leeConfigE.getTipoExcepcion().equals(Constants.ERROR)) {
				return result;
			}
			result.setMensaje(leeConfigE.getMensaje());
			result.setTipoExcepcion(leeConfigE.getTipoExcepcion());
			
		} else {
			EstatusVariablesTO leeConfigR =
					leeConfiguracionRecepcion(session, nodo);
			if(leeConfigR.getTipoExcepcion().equals(Constants.ERROR)) {
				return result;
			}
			result.setMensaje(leeConfigR.getMensaje());
			result.setTipoExcepcion(leeConfigR.getTipoExcepcion());
			valoresReferenciaRec = leeConfigR.getVariablesReferenciaRec();
		
		}
			
		// ARMA LA REFERENCIA
		if(cveNodo.equals(Constants.EVENTO_INTERMEDIO_ENVIO)) {
			referenciaVariables = variableReferenciaEnvio;
		} else {
			referenciaVariables = variableReferenciaRec;
		}
		if(referenciaVariables != null) {
			// LA OCURRENCIA DE LAS VARIABLES DE LOS MENSAJES NECESARIAMENTE ES LA 1
			ocurrencia = 1;
			nodo.setOcurrencia(ocurrencia);
			nodo.setSecuenciaNodo(null);
			//log.info("-------> REEMPLAZA VARIABLES EN CREAR MENSAJE");
			EstatusVariablesTO reemplzav = 
					reemplazaVariables(session, nodo, null, referenciaVariables);// revisar stored
			if(reemplzav.getTipoExcepcion().equals(Constants.ERROR)) {
				return result;
			}
			result.setMensaje(reemplzav.getMensaje());
			result.setTipoExcepcion(reemplzav.getTipoExcepcion());
			
		}
	
		// CREA FOLIO DEL MENSAJE
		EstatusVariablesTO creaFolioM = creaFolioMensaje(session, nodo);
		result.setMensaje(creaFolioM.getMensaje());
		result.setTipoExcepcion(creaFolioM.getTipoExcepcion());
		folioMensajeEnvio = creaFolioM.getFolioMensaje();
		if(creaFolioM.getTipoExcepcion().equals(Constants.ERROR)) {
			return result;
		}
		
		// CREA INSTANCIA DEL MENSAJE
		if(cveNodo.equals(Constants.EVENTO_INTERMEDIO_ENVIO)) {
			InMensajeEnvioPK idME = InMensajeEnvioPK.builder()
					.cveEntidad(cveEntidad)
					.cveProceso(cveProceso)
					.version(version)
					.cveInstancia(cveInstancia)
					.cveNodo(cveNodo)
					.idNodo(idNodo)
					.secuenciaNodo(secuenciaNodo)
					.build();
			InMensajeEnvio entidadME = InMensajeEnvio.builder()
					.id(idME)
					.folioMensajeEnvio(folioMensajeEnvio)
					.valoresReferenciaEnvio(valoresReferenciaEnvio)
					.fechaCreacion(new Date())
					.fechaEnvio(null)
					.situacionEnvio(pendiente)
					.build();
			log.info("PREPARA EN IN MENSAJE ENVIO NODO HELPER - EVENTO_INTERMEDIO_ENVIO - creaMensaje");
			iInMensajeEnvioRepository.saveAndFlush(entidadME);
			log.info("GUARDADO EN IN MENSAJE ENVIO EN NODO HELPER - creaMensaje");

			Optional<InMensajeEnvio> mensajeEnvioGuardado = iInMensajeEnvioRepository.findById(idME);
			
			if(!mensajeEnvioGuardado.isPresent()) {
				mensaje = messagesService.getMessage(
						session,
						idProceso,
						"ERR-INS-TAB-IN_MENSAJE_ENVIO",
						variablesValores);
				result.setTipoExcepcion(Constants.ERROR);
				result.setMensaje(mensaje);
				return result;
			}
		}
		
		if(cveNodo.equals(Constants.EVENTO_INTERMEDIO_RECEPCION)) {
			InMensajeRecepcionPK idMR = InMensajeRecepcionPK.builder()
					.cveEntidad(cveEntidad)
					.cveProceso(cveProceso)
					.version(version)
					.cveInstancia(cveInstancia)
					.cveNodo(cveNodo)
					.idNodo(idNodo)
					.secuenciaNodo(secuenciaNodo)
					.build();
			InMensajeRecepcion entidadMR = InMensajeRecepcion.builder()
					.valoresReferenciaRec(valoresReferenciaRec)
					.fechaCreacion(new Date())
					.fechaEnvio(null)
					.situacionRecepcion(pendiente)
					.build();
			log.info("PREPARA EN IN MENSAJE ENVIO NODO HELPER - EVENTO_INTERMEDIO_RECEPCION - creaMensaje");
			iInMensajeRecepcionRepository.saveAndFlush(entidadMR);
			log.info("GUARDADO EN IN MENSAJE ENVIO EN NODO HELPER - creaMensaje");
			
			Optional<InMensajeRecepcion> mensajeRecepcionGuardado = iInMensajeRecepcionRepository.findById(idMR);
			
			if(!mensajeRecepcionGuardado.isPresent()) {
				mensaje = messagesService.getMessage(
						session,
						idProceso,
						"ERR-INS-TAB-IN_MENSAJE_RECEPCIION",
						variablesValores);
				result.setTipoExcepcion(Constants.ERROR);
				result.setMensaje(mensaje);
				return result;
			}
		}
		
		// CREA LAS VARIABLES PARA EL EVENTO DE ENVIO EN CASO DE EXISTIR
		if(cveNodo.equals(Constants.EVENTO_INTERMEDIO_ENVIO)) {
			EstatusTO creaVE = creaVariablesEnvio(session, nodo);
			result.setMensaje(creaVE.getMensaje());
			result.setTipoExcepcion(creaVE.getTipoExcepcion());
			if(creaVE.getTipoExcepcion().equals(Constants.ERROR)) {
				return result;
			}
		}
        log.info("############### RETURN SP_CREA_MENSAJE_NODO: " + result.toString());
		return result;
	}
	
	
	// SP_CREA_INSTANCIA_NODO
	@Override
	public EstatusTO creaInstancia(DatosAutenticacionTO session, NodoTO nodo)
			throws BrioBPMException, ParseException{
		
		log.info("---------------creaInstancia----------------------");
		
		String cveEntidad = session.getCveEntidad();
		String cveProceso = nodo.getCveProceso();
		BigDecimal version = nodo.getVersion();
		String cveInstancia = nodo.getCveInstancia();
		String cveNodo = nodo.getCveNodo();
		Integer idNodo = nodo.getIdNodo();
		Integer secuenciaNodo = nodo.getSecuenciaNodo();
		String tipoGeneracion = nodo.getTipoGeneracion();
		String tipoNodoSiguiente = nodo.getTipoNodoSiguiente();
		String cveNodoActual  = nodo.getCveNodoInicio();
		Integer idNodoActual = nodo.getIdNodoInicio();
		String rol = nodo.getRol(); 
		String origen = nodo.getOrigen();
		Integer folioMensajeEnvio = nodo.getFolioMensajeEnvio();
		String mensaje;
		String estadoRegistro;
		String cveNodoOrigen = null;
		Integer idNodoOrigen = null;
		Integer secuenciaNodoOrigen = null;
		Integer consecutivo;
		String idProceso;
        String cveNodoProceso;
        Integer idNodoProceso;
        Integer numNodosCreados;
		String cveNodoSiguiente;
        Integer idNodoSiguiente;
        String condicion;
        String existeNodo;
        String agregarNodoPila;
        String terminarCompuerta;
        Integer secuenciaNodoSiguiente;
        String crearNodo;
        Integer  secuenciaNodoCreado;
        String estadoCompuerta;
        
        // INICIALIZA CÓDIGO DE ERROR, MENSAJE Y SUGERENCIA
		EstatusTO result = EstatusTO.builder()
				.tipoExcepcion(Constants.OK)
				.mensaje("")
				.build();
		idProceso = "CREA_INSTANCIA_NODO";
		
		// Construye una cadena con los valores de las variables para el registro
		String variablesValores = Constants.CVE_PROCESO + cveProceso + "|" +
				Constants.VERSION + version + "|" +
				Constants.CVE_INSTANCIA + cveInstancia + "|" +
				Constants.CVE_NODO + cveNodo + "|" +
				Constants.ID_NODO + idNodo + "|" +
				Constants.SECUENCIA_NODO + secuenciaNodo + "|" +
				Constants.TIPO_GENERACION + tipoGeneracion;
		
		log.info("#################################################################################" );
		log.info("################### SP_CREA_INSTANCIA_NODO_TEST ########################" );
		log.info("-------- I_SECUENCIA_NODO DE ENTRADA EN SP_CREA_INSTANCIA_NODO_TEST: " + secuenciaNodo);
		
		// CONSTANTES
		estadoRegistro = "REGISTRO";
		
//		Optional<ParametroGeneral> parametroG = 
//				iParametroGeneralRepository.findById("DESPLEGAR_RASTREO");
//		
		// VALIDA QUE EL TIPO DE GENERACIÓN SEA ACT-ACTUAL O SIG-SIGUIENTE
		if(!Arrays.asList(Constants.ACTUAL, Constants.SIGUIENTE).contains(tipoGeneracion)){
			 mensaje = messagesService.getMessage(
					session,
					idProceso,
					"TIPO_GEN_NODO_INCORRECTO",
					variablesValores);
			result.setTipoExcepcion(Constants.ERROR);
			result.setMensaje(mensaje);
			return result;
		}
		
		// CREA INSTANCIA DEL NODO ACTUAL
		if(tipoGeneracion.equals(Constants.ACTUAL)) { // INSTANCIA DEL NODO ACTUAL
	        // Verifica si ya existe una instancia del nodo actual
			Integer instanciaNodo = inNodoProcesoRepository.encontrarRegistroExistente(
					cveEntidad,
					cveProceso,
					version,
					cveInstancia,
					cveNodoActual,
					idNodoActual);
			
			// VALIDA QUE NO SE HAYA CREADO ANTES UNA INSTANCIA DEL MISMO NODO
			if(instanciaNodo > 0) {
				log.info("YA EXISTE ERROR DE INSTNACIA NODO");
				mensaje = messagesService.getMessage(
						session,
						idProceso,
						"YA_EXISTE_INSTANCIA_NODO",
						variablesValores);
				result.setTipoExcepcion(Constants.ERROR);
				result.setMensaje(mensaje);
				return result;
			}
		
			// CREA EL NODO SOLICITADO
			// PARA EL CASO DE UN EVENTO-INICIO O EVENTO-INICIO-MENSAJE, INICIALIZA EL NODO ORIGEN EN NULO
			if(cveNodoActual.equals(Constants.EVENTO_INICIO) || cveNodoActual.equals(Constants.EVENTO_INICIO_MENSAJE)) {
				cveNodoOrigen = null;
				idNodoOrigen = null;
				secuenciaNodoOrigen = null;	
				
			} else {
				cveNodoOrigen = cveNodo;
				idNodoOrigen = idNodo;
				secuenciaNodoOrigen = secuenciaNodo;
				
			}
			
			log.info("-------- I_SECUENCIA_NODO_ORIGEN DESPUES DE LA EVALUACION \n            DE SI ES O NO EVENTO-INICIO O EVENTO-FIN-MENSAJE: " + secuenciaNodoOrigen);
			
			// CREA EL NODO SOLICITADO
			NodoTO nodoSolicitado = NodoTO.builder()
					.cveProceso(cveProceso)
					.version(version)
					.cveInstancia(cveInstancia)
					.cveNodo(cveNodoOrigen)
					.idNodo(idNodoOrigen)
					.origen(origen)					
					.secuenciaNodo(secuenciaNodoOrigen)
					.rol(rol)
					.build();
			
			// Llama al método para crear el nodo y obtiene la secuencia del nodo creado
			EstatusVariablesTO creaN = creaNodo(session, nodoSolicitado, cveNodoActual, idNodoActual, null, Constants.SI, folioMensajeEnvio);
			
			secuenciaNodoCreado = creaN.getSecuenciaNodo();
			log.info("-------- I_SECUENCIA_NODO_CREADO DESPUES DE EJECUTAR SP_CREA_NODO_TEST  \n            ANTES DEL WHILE: " + secuenciaNodoCreado);
			result.setTipoExcepcion(creaN.getTipoExcepcion());
			result.setMensaje(creaN.getMensaje());
			
			if(creaN.getTipoExcepcion().equals(Constants.ERROR)) {
				result.setTipoExcepcion(Constants.ERROR);
				return result;
			}
			return result;
		}
		
		// CREA INSTANCIA DE NODOS SIGUIENTES
		//	VERIFICA QUE EXISTA CONFIGURACIÓN DE NODOS SIGUIENTES.
		//	EN ESTE CASO COMO SE TRATA DE LA TERMINACION POR LA ACCIÓN DEL USUARIO SE BUSCAN NODOS SIGUIENTES
		//	DEL TIPO 'NORMAL', EN OPOSICIÓN A 'TEMPORIZACION-CON-INTERRUPCION' Y 'TEMPORIZACION-SIN-INTERRUPCION'
		Integer existeStNodoS = iStNodoSiguienteRepository.encontrarRegistroExistente(
				cveEntidad,
				cveProceso,
				version,
				cveNodo,
				idNodo,
				tipoNodoSiguiente);
		
		if(existeStNodoS == 0) {
			mensaje = messagesService.getMessage(
					session,
					idProceso,
					"NODOS_SIGUIENTES_INDEFINIDOS",
					variablesValores);
			result.setTipoExcepcion(Constants.ERROR);
			result.setMensaje(mensaje);
			return result;
		}
		
		// CREA TABLA AUXILIAR (PILA) PARA RECORRER EL 'ÁRBOL' DE NODOS SIGUIENTES
        List<NodoSuperiorTO> nodoSuperior = new ArrayList<NodoSuperiorTO>();
        
        // PROCESA NODOS SIGUIENTES
        // AGREGA NODO ACTUAL A LA PILA
        consecutivo = 1;
        
        log.info("ANODO: " + cveNodo);
        NodoSuperiorTO nodoTO = NodoSuperiorTO.builder()
        		.consecutivo(consecutivo)
        		.cveNodo(cveNodo)
        		.idNodo(idNodo)
        		.build();
        
        nodoSuperior.add(nodoTO);
        log.info("tamaño nodoSuperior: " + nodoSuperior.size() + " " + nodoSuperior.toString());
        
        // PROCESA LA PILA DE NODOS MIENTRAS ÉSTA CONTENGA DATOS
        String pilaVacia = Constants.NO;
        while(Constants.NO.equals(pilaVacia)) {
        	
        	log.info("-------ITERA PILA-------");
        	// LEE ELEMENTO DE LA PILA
        	// Encontrar el elemento con el consecutivo mínimo
            NodoSuperiorTO nodoMinimo = nodoSuperior.stream()
                    .min(Comparator.comparingInt(NodoSuperiorTO::getConsecutivo))
                    .orElse(null);
            cveNodoProceso = nodoMinimo.getCveNodo();
            idNodoProceso = nodoMinimo.getIdNodo();          
            log.info("nodoMinimo: " + nodoMinimo.toString());
            
            log.info("@I_CONSECUTIVO_PROCESO: " + nodoMinimo.getConsecutivo());
            log.info("@CH_CVE_NODO_PROCESO: " + cveNodoProceso);
            log.info("@I_ID_NODO_PROCESO: " + idNodoProceso);
            
        	// OBTIENE LA LISTA DE NODOS SIGUIENTES DEL NODO RECUPERADO DE LA PILA
            List<StNodoSiguiente> nodoSiguiente = iStNodoSiguienteRepository.obtieneListaNodosSiguiente(cveEntidad,
																					            		cveProceso,
																					            		version,
																					            		cveNodoProceso,
																					            		idNodoProceso 
																					            		,tipoNodoSiguiente);
            //log.info("------->TAMAÑO LISTA DE LOS NODOS SIGUIENTES ST_NODO_SIGUIENTE: " + nodoSiguiente.size()); 
            numNodosCreados = 0;
            
            for(StNodoSiguiente stNodoSF : nodoSiguiente) {
            	cveNodoSiguiente = stNodoSF.getCveNodoSiguiente();
            	idNodoSiguiente = stNodoSF.getIdNodoSiguiente();
            	condicion = stNodoSF.getCondicion();
            	
            	//SVM ajuste para asignar del usuario
            	String tipoConsulta = stNodoSF.getTipoConsulta();
            	String sentenciaUsuarioAsignado = stNodoSF.getSentenciaUsuarioAsignado();
                log.info("xxxxxxxxxxxxxxxxxxxxxxx QUERY tipoConsulta: {}", tipoConsulta);
                log.debug("xxxxxxxxxxxxxxxxxxxxxxx QUERY sentenciaUsuarioAsignado: {}" + sentenciaUsuarioAsignado);           	
            	
            
    		    // INICIALIZA VARIABLES PARA CONTROLAR LAS ACCIONES A REALIZAR
    		    existeNodo = Constants.NO;
    		    crearNodo = Constants.NO;
    		    agregarNodoPila = Constants.NO;
    		    terminarCompuerta = Constants.NO;

    		    // VERIFICA SI YA EXISTE UN NODO CREADO
    		    secuenciaNodoSiguiente = 0;

		    	Integer nodoSecuencia = iInNodoProcesoRepository.encontrarSecuenciaNodoSiguiente(
		    			cveEntidad,
		    			cveProceso,
		    			version,
		    			cveInstancia,
		    			cveNodoSiguiente,
		    			idNodoSiguiente,
		    			estadoRegistro);
		    	// nodoSecuencia = nodoSecuencia == null ? 0 : nodoSecuencia;
            	
		    	if(nodoSecuencia != null) {
		    		existeNodo = Constants.SI;
		    		secuenciaNodoSiguiente = nodoSecuencia;
		    	}
    		    
    		    // VALIDA SI SE DEBE CREAR EL Constants.SIDO
    		    List<String> nodosPermitidos = Arrays.asList(
    		        Constants.ACTIVIDAD_USUARIO,
    		        Constants.ACTIVIDAD_USUARIO_TEMPORIZACION,
    		        Constants.COMPUERTA_EXCLUSIVA_INICIO,
    		        Constants.COMPUERTA_EXCLUSIVA_CIERRE,
    		        Constants.COMPUERTA_INCLUSIVA_CIERRE,
    		        Constants.COMPUERTA_INCLUSIVA_INICIO,
    		        Constants.COMPUERTA_PARALELA_CIERRE,
    		        Constants.COMPUERTA_PARALELA_INICIO,
    		        Constants.EVENTO_FIN,
    		        Constants.EVENTO_FIN_MENSAJE,
    		        Constants.EVENTO_INICIO_MENSAJE,
    		        Constants.EVENTO_INTERMEDIO_ENVIO,
    		        Constants.EVENTO_INTERMEDIO_RECEPCION,
    		        Constants.TEMPORIZADOR
    		    );

    		    if (nodosPermitidos.contains(cveNodoSiguiente) && existeNodo.equals(Constants.NO)) {
    		    	crearNodo = Constants.SI;
    		    } else {
    		    	if(cveNodoSiguiente.equals(Constants.COMPUERTA_EXCLUSIVA_INICIO) &&
    		    			numNodosCreados == 0) {
    		    		crearNodo = Constants.SI;   		    		
    		    	}
    		    }
    		    List<String> nodosCierre = Arrays.asList(
    		    		Constants.COMPUERTA_INCLUSIVA_CIERRE,
    		    		Constants.COMPUERTA_PARALELA_CIERRE,
    		    		Constants.COMPUERTA_EXCLUSIVA_CIERRE);
    		    
    		    // VALIDA Constants.SI EL NODO SIGUIENTE ES UNA COMPUERTA DE CIERRE
		    	if (nodosCierre.contains(cveNodoSiguiente)) {
		    	   terminarCompuerta = Constants.SI;	    	   
		    	}
		    	
    		    // EJECUTA LAS ACCIONES DE ACUERDO A LAS VARIABLES DE CONTROL
    		    secuenciaNodoCreado = 0;
    		    log.info("-------- INICIALIZA EN 0 LA I_SECUENCIA_NODO_CREADO \n , AQUI EMPIEZA LA EJECUCION DE LAS ACCIONES DE ACUIERDO A LAS VARIABLES DE CONTROL: " + secuenciaNodoCreado);
    	        log.info("---------> CREAR NODO: " + crearNodo);
    	        log.info("---------> TERMINAR COMPUERTA: " + terminarCompuerta);
    		    
    	       //SVM ajuste para asignar del usuario
    	        // CREA EL NODO SOLICITADO
    	        if(crearNodo.equals(Constants.SI)) {
    	        	
    	        	NodoTO nodoNuevo = NodoTO.builder()
    	        			.cveProceso(cveProceso)
    	        			.version(version)
    	        			.cveInstancia(cveInstancia)
    	        			.cveNodo(cveNodo)
    	        			.idNodo(idNodo)
    	        			.secuenciaNodo(secuenciaNodo)
    	        			.rol(rol)
    	        			.origen(origen)
    	        			.tipoConsulta(tipoConsulta)
    	        			.sentenciaUsuarioAsignado(sentenciaUsuarioAsignado)
    	        			.build();
    	        	
    	        	EstatusVariablesTO creaNo = creaNodo(session, nodoNuevo, cveNodoSiguiente, idNodoSiguiente, condicion, Constants.SI, folioMensajeEnvio);
    	        	log.info("xxxxxxxxxxxxxxxx CREO NODO: " + creaNo.toString());
    	        	
    	        	secuenciaNodoCreado = creaNo.getSecuenciaNodo();

    	        	result.setMensaje(creaNo.getMensaje());
    	        	result.setTipoExcepcion(creaNo.getTipoExcepcion());
    	        	if(creaNo.getTipoExcepcion().equals(Constants.ERROR)) {
    	        		return result;
    	        	}
    	        	
    	        	if(secuenciaNodoCreado != null) {
        	        	log.info("I_SECUENCIA_NODO_CREADO ES DIFERNTE DE NULL: " + secuenciaNodoCreado);

	    	    	   if(secuenciaNodoCreado > 0) {
	    	    		   log.info("I_SECUENCIA_NODO_ES MAYOR QUE 0': " + secuenciaNodoCreado);
	    	    		   numNodosCreados = numNodosCreados + 1;
	    	    		   log.info(" NUMERO NODOS CREADOS: " + numNodosCreados);
	    	    	   }
    	        	}
    	       }
    	        
    	       estadoCompuerta = "REGISTRO";
    	       if(terminarCompuerta.equals(Constants.SI)) {
    	    	   log.info("LE ENTREGO I_SECUENCIA_NODO A SP_TERMINA_COMPUERTA_CIERRE: " + secuenciaNodo);
    	    	   NodoTO nodoCompuerta = NodoTO.builder()
    	    			   .cveProceso(cveProceso)
    	    			   .version(version)
    	    			   .cveInstancia(cveInstancia)
    	    			   .cveNodo(cveNodo)
    	    			   .idNodo(idNodo)
    	    			   .rol(rol)
    	    			   .build();
    	    	   EstatusCompuertaTO cierreCompuerta = terminaCompuertaCierre(session, nodoCompuerta, cveNodoSiguiente, idNodoSiguiente);
	    		   log.info("---------> CIERRE COMPUERTA: " + cierreCompuerta.toString());
    	    	   estadoCompuerta = cierreCompuerta.getEstadoCompuerta();
    	    	   result.setMensaje(cierreCompuerta.getMensaje());
    	    	   result.setTipoExcepcion(cierreCompuerta.getTipoExcepcion());
    	    	   if(cierreCompuerta.getTipoExcepcion().equals(Constants.ERROR)) {
    	    		   return result;
    	    	   }
    	       }
    	       
    	       log.info("@CH_CVE_NODO_SIGUIENTE: " + cveNodoSiguiente);
    	       
    	       // VERIFICA SI EL NODO FUE CREADO Y SI SE DEBE AGREGAR A LA PILA
    	       List<String> nodosInicio = Arrays.asList(
    	    		   Constants.COMPUERTA_EXCLUSIVA_INICIO,
    	    		   Constants.COMPUERTA_INCLUSIVA_INICIO,
    	    		   Constants.COMPUERTA_PARALELA_INICIO,
    	    		   Constants.EVENTO_INTERMEDIO_ENVIO,
    	    		   Constants.EVENTO_INICIO_MENSAJE
    	    		);

	    		if (nodosInicio.contains(cveNodoSiguiente) && (secuenciaNodoCreado != null && secuenciaNodoCreado != 0)) {
	    			agregarNodoPila = Constants.SI;
		    		log.info("CH_AGREGAR_NODO_A_PILA: " + agregarNodoPila);
	    		} else if (nodosCierre.contains(cveNodoSiguiente) && estadoCompuerta.equals("TERMINADA")) {
	    			agregarNodoPila = Constants.SI;
 		    		log.info("@CH_AGREGAR_NODO_A_PILA TERMINADA: " + agregarNodoPila);
    			}
	    		
    	    	// AGREGA NODO A LA PILA
	    		log.info("@CH_AGREGAR_NODO_A_PILA: " + agregarNodoPila);
	    		// Agrega el nodo a la pila si el flag es 'SI' y termina la compuerta si el flag es 'SI'
	            if(agregarNodoPila.equals(Constants.SI)) {
    	    		consecutivo ++;
    	    		
    	    		log.info("@I_CONSECUTIVO: " + consecutivo);
    	    		log.info("@CH_CVE_NODO_SIGUIENTE: " + cveNodoSiguiente);
    	    		log.info("@I_ID_NODO_SIGUIENTE: " + idNodoSiguiente);
    	    		
    	    		 NodoSuperiorTO nodoTOPila = NodoSuperiorTO.builder()
    	    	        		.consecutivo(consecutivo)
    	    	        		.cveNodo(cveNodoSiguiente)
    	    	        		.idNodo(idNodoSiguiente)
    	    	        		.build();
    	    	        
    	    	        nodoSuperior.add(nodoTOPila);
 		    		   log.info("---------> NODO PILA AGREGADO: " + nodoTOPila.toString());

    	    	}
    	    	
    	    	if(cveNodoProceso.equals(Constants.COMPUERTA_EXCLUSIVA_INICIO) && numNodosCreados >= 1) {
    	    		break;
    	    	}
            }
            // BORRA ELEMENTO DE LA PILA
            // Remueve el nodo mínimo procesado
            nodoSuperior.remove(nodoMinimo);
  		   	log.info("---------> nodoSuperior despues de borrar: " + nodoSuperior.size());
  		   	
  		   	// Verifica si la pila está vacía
  		   	if(nodoSuperior.isEmpty()) {
  		   		pilaVacia = Constants.SI;
  		   	}

        }
        log.info("############### RETURN SP_CREA_INSTANCIA_NODO: " + result.toString());
        return result;
	}
	
	// SP_CREA_NODO
	@Override
	public EstatusVariablesTO creaNodo(DatosAutenticacionTO session, NodoTO nodo, String cveNodoCrear,
			Integer idNodoCrear, String condicionNodoCrear, String aplicarTerminacion, Integer folioMensajeEnvio)
			throws BrioBPMException, ParseException {

		log.info("---------------creaNodo-----------------------");
		String cveEntidad = session.getCveEntidad();
		String cveLocalidad = session.getCveLocalidad();
		String cveProceso = nodo.getCveProceso();
		BigDecimal version = nodo.getVersion();
		String cveInstancia = nodo.getCveInstancia();
		String cveNodo = nodo.getCveNodo();
		Integer idNodo = nodo.getIdNodo();
		Integer secuenciaNodo = nodo.getSecuenciaNodo();
		String rol = nodo.getRol();
		String origen = nodo.getOrigen();
	    String idProceso;
	    String resultado;
	    Date fechaCreacion;
	    Date fechaEspera = null;
	    Date fechaLimite;
	    String crearNodo;
	    String estado;
	    String asignacionActividad;
	    Integer ocurrencia;
	    String estadoTerminada;
	    String mensaje;
	    List<String> usuarioAsignado = null;
	    
	    Integer secuenciaNodoCreado;
	    
		log.info("-----cveInstancia: " + cveInstancia);
	    
	    //INICIALIZA CÓDIGO DE ERROR, MENSAJE Y SUGERENCIA
	    idProceso = "CREA_NODO";
	    EstatusVariablesTO result = EstatusVariablesTO.builder()
				.tipoExcepcion(Constants.OK)
				.mensaje("")
				.build();
	    
	    // Construye una cadena con los valores de las variables para el registro
		String variablesValores = Constants.CVE_PROCESO + cveProceso + "|" +
				Constants.VERSION + version + "|" +
				Constants.CVE_INSTANCIA + cveInstancia + "|" +
				Constants.CVE_NODO + cveNodo + "|" +
				Constants.ID_NODO + idNodo + "|" +
				Constants.SECUENCIA_NODO + secuenciaNodo + "|" +
				Constants.CVE_NODO_CREAR + cveNodoCrear + "|" +
				Constants.ID_NODO_CREAR + idNodoCrear;  
	    
		asignacionActividad = "ASIGNACION_ACTIVIDAD";
	    estadoTerminada = "TERMINADA";
	    
	    // INICIALIZA LA SECUENCIA "DEL NODO A CREAR"
	    secuenciaNodoCreado = 0;
	    
	    // Constants.SI SE TRATA DE UNA ACTIVIDAD DE USUARIO, EVALUA CONDICIÓN
	    crearNodo = Constants.SI;
	    resultado = Constants.VERDADERO;
	    if(condicionNodoCrear != null && !condicionNodoCrear.trim().isEmpty()) {	
	    	EstatusCondicionTO evaluaC = evaluarCondicion(session, nodo, condicionNodoCrear);
	    	if(evaluaC.getTipoExcepcion().equals(Constants.ERROR)) {
	    		result.setTipoExcepcion(evaluaC.getTipoExcepcion());
	    		result.setMensaje(evaluaC.getMensaje());
	    		return result;	    		
	    	}
	    	
	    	result.setMensaje(evaluaC.getMensaje());
	    	resultado = evaluaC.getResultado();
	    	if(resultado.equals(Constants.FALSO)) {
	    		crearNodo = Constants.NO;
	    	}
	    }
	    
	    // VERIFICA SI ES NECESARIO CREAR EL NODO
	    if(crearNodo.equals(Constants.NO)) {
	    	return result;
	    }
	    
	    // ASIGNA LA FECHA DE CREACIÓN Y LA FECHA LÍMITE
	    fechaCreacion = fechaHelper.obtenerFechaCreacion(cveEntidad, cveLocalidad);
		
	    fechaLimite = null;
	    
	    // DETERMINA LA FECHA LÍMITE EN CASO DE QUE EXISTA NIVEL DE SERVICIO EN LA ACTIVIDAD A CREAR
	    CalculoFechaLimiteTO datosFecha = CalculoFechaLimiteTO.builder()
    			.cveProceso(cveProceso)
    			.version(version)
    			.cveInstancia(cveInstancia)
    			.cveNodo(cveNodoCrear)
    			.idNodo(idNodoCrear)
    			.fechaCreacion(fechaCreacion)
    			.build();

	    if(cveNodoCrear.equals(Constants.ACTIVIDAD_USUARIO) ||
	    		cveNodoCrear.equals(Constants.ACTIVIDAD_USUARIO_TEMPORIZACION)	) {
	    	
	    	EstatusFechaTO calculaFechaL = fechaHelper.calculaFechaLimite(session, datosFecha);	    	
	    	result.setMensaje(calculaFechaL.getMensaje());
	    	result.setTipoExcepcion(calculaFechaL.getTipoExcepcion());
	    	fechaLimite = calculaFechaL.getFechaLimite();
	    	
	    	if(calculaFechaL.getTipoExcepcion().equals(Constants.ERROR)) {
	    		return result;
	    	}
	    	
	    	//SVM ajuste de usuario asignado
	    	if (nodo.getTipoConsulta() != null && nodo.getTipoConsulta().equals("QUERY")) {
	    		//log.info("xxxxxxxxxxxxxxxxxxxxxxx Entre en  QUERY");
				EstatusVariablesTO reemplzav = reemplazaVariables(session, nodo, null, nodo.getSentenciaUsuarioAsignado());
				
				//log.info("xxxxxxxxxxxxxxxxxxxxxxx Entre en  QUERY reemplzav: {}", reemplzav);
				if(reemplzav.getTipoExcepcion().equals(Constants.ERROR)) {
					result.setMensaje(reemplzav.getMensaje());
					result.setTipoExcepcion(reemplzav.getTipoExcepcion());
					return result;
				}
				
	    		usuarioAsignado = areaTrabajoDAO.obtenerUsuarioAsignado(reemplzav.getCadenaSalida());
	    		
	    		//log.info("xxxxxxxxxxxxxxxxxxxxxxx Entre en  QUERY usuarioAsignado: {}", usuarioAsignado.toString());	    		
	    		nodo.setCveUsuarioAsignado(usuarioAsignado);
	    		
	    		if (usuarioAsignado == null || usuarioAsignado.size() == 0) {
	    			result.setTipoExcepcion(Constants.ERROR);
	    			result.setMensaje(messagesService.getMessage("SIN_USUARIO_ASIGNADO", session.getCveIdioma()));
	    			return result;
	    		}
	    	}
	    	
	    } 
	    
	    // PARA EL CASO DE NODO TEMPORIZADOR, SE CALCULA LA FECHA Y HORA DE FINALIZACIÓN
	    fechaCreacion = fechaHelper.obtenerFechaCreacion(cveEntidad, cveLocalidad);
	    
	    if (cveNodoCrear.equals(Constants.EVENTO_INTERMEDIO_TEMPORAL) ||
	    		cveNodoCrear.equals(Constants.ACTIVIDAD_USUARIO_TEMPORIZACION) ||
	    		cveNodoCrear.equals(Constants.TEMPORIZADOR)) {
	    
	    	EstatusFechaTO fechaTemporizador = fechaHelper.calculaFechaTemporizador(session, datosFecha);
	    	result.setMensaje(fechaTemporizador.getMensaje());
	    	result.setTipoExcepcion(fechaTemporizador.getTipoExcepcion());
	    	if(fechaTemporizador.getTipoExcepcion().equals(Constants.ERROR)) {
	    		return result;
	    	}
	    	fechaEspera = fechaTemporizador.getFechaLimite();
	    }
	    
	    // DETERMINA LA SITUACIÓN DEL NODO
	    estado = "REGISTRO";
	    if(aplicarTerminacion.equals(Constants.SI)) {
	    	if (cveNodoCrear.equals(Constants.EVENTO_FIN) /*||
	    			cveNodoCrear.equals(Constants.COMPUERTA_EXCLUSIVA_CIERRE)*/) {
	    		estado = "TERMINADA";

	    	}
	    	
	    	if (cveNodoCrear.equals(Constants.EVENTO_FIN) || 
				cveNodoCrear.equals(Constants.COMPUERTA_EXCLUSIVA_INICIO) ||
				cveNodoCrear.equals(Constants.COMPUERTA_EXCLUSIVA_CIERRE) || 
				cveNodoCrear.equals(Constants.COMPUERTA_INCLUSIVA_CIERRE) ||
				cveNodoCrear.equals(Constants.COMPUERTA_INCLUSIVA_INICIO) || 
				cveNodoCrear.equals(Constants.EVENTO_FIN_MENSAJE) ||
				cveNodoCrear.equals(Constants.EVENTO_INICIO_MENSAJE) ) {
		
				String evento = "EVENTO_FIN_MENSAJE";
				if (cveNodoCrear.equals(Constants.EVENTO_FIN)) {
					evento = "EVENTO_FIN";
				} else if (cveNodoCrear.equals(Constants.COMPUERTA_EXCLUSIVA_INICIO)) {
					evento = "COMPUERTA_EXCLUSIVA_INICIO";
				} else if (cveNodoCrear.equals(Constants.COMPUERTA_EXCLUSIVA_CIERRE)) {
					evento = "COMPUERTA_EXCLUSIVA_CIERRE";
				} else if (cveNodoCrear.equals(Constants.COMPUERTA_INCLUSIVA_CIERRE)) {
					evento = "COMPUERTA_INCLUSIVA_CIERRE";
				} else if (cveNodoCrear.equals(Constants.COMPUERTA_INCLUSIVA_INICIO)) {
					evento = "COMPUERTA_INCLUSIVA_INICIO";
				} else if (cveNodoCrear.equals(Constants.EVENTO_INICIO_MENSAJE)) {
					evento = "EVENTO_INICIO_MENSAJE";
				}
			    
		    	//SVM ajuste de usuario asignado
		    	if (nodo.getTipoConsulta() != null && nodo.getTipoConsulta().equals("QUERY")) {
		    		log.info("xxxxxxxxxxxxxxxxxxxxxxx Entre en  QUERY");
					EstatusVariablesTO reemplzav = reemplazaVariables(session, nodo, null, nodo.getSentenciaUsuarioAsignado());
					
					log.info("xxxxxxxxxxxxxxxxxxxxxxx Entre en  QUERY reemplzav: {}", reemplzav);
					if(reemplzav.getTipoExcepcion().equals(Constants.ERROR)) {
						result.setMensaje(reemplzav.getMensaje());
						result.setTipoExcepcion(reemplzav.getTipoExcepcion());
						return result;
					}
					
		    		usuarioAsignado = areaTrabajoDAO.obtenerUsuarioAsignado(reemplzav.getCadenaSalida());
		    		log.info("xxxxxxxxxxxxxxxxxxxxxxx Entre en  QUERY usuarioAsignado: {}", usuarioAsignado.toString());	    		
		    		nodo.setCveUsuarioAsignado(usuarioAsignado);
		    		
		    		if (usuarioAsignado == null || usuarioAsignado.size() == 0) {
		    			result.setTipoExcepcion(Constants.ERROR);
		    			result.setMensaje(messagesService.getMessage("SIN_USUARIO_ASIGNADO", session.getCveIdioma()));
		    			return result;
		    		}
		    	}
				
				//validar si tiene usuarios asignados
				
	    		//CREA EL MENSAJE DE CORREO PARA EL EVENTO DE TERMINADO
	    		//SVM 23-04-2025
		    	
	    		ocurrencia = 1;
			    NodoTO nodoCorreo = NodoTO.builder()
			    		.cveProceso(cveProceso)
			    		.version(version)
			    		.cveEventoCorreo(evento)
			    		.cveUsuarioDestinatario(null)
			    		.cveRolDestinatario(null)
			    		.cveInstancia(cveInstancia)
			    		.cveNodo(cveNodoCrear)
			    		.idNodo(idNodoCrear)
			    		.secuenciaNodo(secuenciaNodoCreado)
			    		.ocurrencia(ocurrencia)
			    		.origen(origen)
			    		.cveUsuarioAsignado(usuarioAsignado)
			    		.build();
			 
				EstatusTO creaCP = creaCorreoProceso(session, nodoCorreo, null, null);
			    log.info("----------> CREA CORREO PROCESO: " + creaCP.toString());
			    result.setTipoExcepcion(creaCP.getTipoExcepcion());
				result.setMensaje(creaCP.getTipoExcepcion());
			    
			    if(creaCP.getTipoExcepcion().equals(Constants.ERROR)) {
			    	return result;
			    }
	    	}
	    }
	    
	    // CUANDO NO EXISTE ESPECIFICACIÓN DE NODO SIGUIENTE PARA UNA COMPUERTA EXCLUSIVA DE INICIO
	    // ASIGNA EL ESTADO DE TERMINADA A DICHA COMPUERTA
	    if(cveNodoCrear.equals(Constants.COMPUERTA_EXCLUSIVA_INICIO)){
	    	Integer stCompuertaInicio = iStCompuertaInicioRepository.encontrarRegistroExistente(
	    			cveEntidad,
	    			cveProceso,
	    			version,
	    			cveNodoCrear,
	    			idNodoCrear);
	    	if(stCompuertaInicio == null) {
	    		estado = estadoTerminada;
	    	}
	    }
	    
	    // CREA EL FOLIO DEL NODO
	    NodoTO creaFolio = NodoTO.builder()
	    		.cveProceso(cveProceso)
	    		.version(version)
	    		.cveInstancia(cveInstancia)
	    		.cveNodo(cveNodoCrear)
	    		.idNodo(idNodoCrear)
	    		.origen(origen)
	    		.build();
	    EstatusVariablesTO folioNodo = creaFolio(session, creaFolio);
    	result.setMensaje(folioNodo.getMensaje());
    	result.setTipoExcepcion(folioNodo.getTipoExcepcion());
    	
	    if(folioNodo.getTipoExcepcion().equals(Constants.ERROR)) {
	    	return result;
	    }
	    secuenciaNodoCreado = folioNodo.getSecuenciaNodo();
	    result.setSecuenciaNodo(secuenciaNodoCreado);
	    log.info("I_SECUENCIA_NODO_CREADO RESULTADO DE EJECUTAR SP_CREA_FOLIO_IN_NODO_TEST: " + secuenciaNodoCreado);
	    
	    // INSERTA EL NODO
	    
	    log.info("cveNodoCrear: " + cveNodoCrear);
	    log.info("cveNodoOrigen: " + cveNodo);
	    
	    log.info("PREPARA I_SECUENCIA_NODO_CREADO COMO ENTRADA EN SP_INS_IN_NODO_TEST: " + secuenciaNodoCreado);
	    log.info("PREPARA I_SECUENCIA_NODO ORIGEN PARA COMO ENTRADA EN SP_INS_IN_NODO_TEST: " + secuenciaNodo);
	    
	    //SVM ajuste para crear el usuario_asignado
	    
	    
	    NodoTO nodoP = NodoTO.builder()
	    		.idProceso(idProceso)
	    		.cveProceso(cveProceso)
	    		.version(version)
	    		.cveInstancia(cveInstancia)
	    		.cveNodo(cveNodoCrear)
	    		.idNodo(idNodoCrear)
	    		.secuenciaNodo(secuenciaNodoCreado)
	    		.origen(origen)
	    		.build();
	    NodoProcesoTO nodoProceso = NodoProcesoTO.builder()
	    	    .estado(estado)
	    	    .fechaCreacion(fechaCreacion)
	    	    .origen(origen)
	      	    .cveProcesoOrigen(cveProceso)
	    	    .versionOrigen(version)
	    	    .cveInstanciaOrigen(cveInstancia)
	    	    .cveNodoOrigen(cveNodo) // revisar
	    	    .idNodoOrigen(idNodo)
	    	    .secuenciaNodoOrigen(secuenciaNodo) // revisar
	    	    .rolCreador(rol)
	    	    .rolBloquea(null)
	    	    .usuarioBloquea(null)
	    	    .fechaBloquea(null)
	    	    .fechaLimite(fechaLimite)
	    	    .fechaEstadoActual(fechaCreacion)
	    	    .fechaFinEspera(fechaEspera)
	    	    .comentario(null)
	    	    .build();
	    
	    EstatusTO insInNodo = insertaInNodo( session, nodoP, nodoProceso, usuarioAsignado);
	    result.setTipoExcepcion(insInNodo.getTipoExcepcion());
	    result.setMensaje(insInNodo.getMensaje());
	    
	    if(insInNodo.getTipoExcepcion().equals(Constants.ERROR)) {
	    	return result;
	    }
	    
	    // VERIFICA SI SE DEBE TERMINAR EL PROCESO
	    if(aplicarTerminacion.equals(Constants.SI)) {
	    	if(cveNodoCrear.equals(Constants.EVENTO_FIN)) {
	    		InProcesoPK idIP = InProcesoPK.builder()
	    				.cveEntidad(cveEntidad)
	    				.cveProceso(cveProceso)
	    				.version(version)
	    				.cveInstancia(cveInstancia)
	    				.build();
	    		
	    		Optional<InProceso> inProcesoUpdate = iInProcesoRepository.findById(idIP);
	    		inProcesoUpdate.get().setSituacion("TERMINADO");
	    		if(!inProcesoUpdate.isPresent() && !inProcesoUpdate.get().equals("TERMINADO")) {
	    			mensaje = messagesService.getMessage(
	    					session,
	    					idProceso,
	    					"ERR-UPD-TAB-IN_PROCESO",
	    					variablesValores);
	    			result.setMensaje(mensaje);
	    			return result;
	    		}
	    	}
	    }
	    
    	
	    // SI SE TRATA DE EVENTOS INTERMEDIO DE MENSAJE DE ENVIO O RECEPCIÓN, CREA LOS MENSAJES RESPECTIVOS
	    if(cveNodoCrear.equals(Constants.EVENTO_INTERMEDIO_ENVIO) ||
	    		cveNodoCrear.equals(Constants.EVENTO_INTERMEDIO_RECEPCION)	) {
	    	
	    	NodoTO nodoMensaje = NodoTO.builder()
	    			.idProceso(idProceso)
	    			.cveProceso(cveProceso)
	    			.version(version)
	    			.cveInstancia(cveInstancia)
	    			.cveNodo(cveNodoCrear)
	    			.idNodo(idNodoCrear)
	    			.build();
	    	//CREA EL MENSAJE DEL NODO DE ENVÍO O RECEPCIÓN
	    	EstatusTO creaMN = creaMensaje(session, nodoMensaje);
	    	log.info("-----------> CREA MENSAJE: " + creaMN.toString());
	    	result.setTipoExcepcion(creaMN.getTipoExcepcion());
	    	result.setMensaje(creaMN.getMensaje());
	    }
	    
	    // CREA TAREAS DEL NODO, EN CASO QUE EXISTAN
    	if(cveNodoCrear.equals(Constants.ACTIVIDAD_USUARIO) ||
	    		cveNodoCrear.equals(Constants.ACTIVIDAD_USUARIO_TEMPORIZACION)	) {
	    	
    		// CREA LAS VARIABLES DEL PROCESO PARA EL NODO EN CASO QUE NO EXISTAN
    		NodoTO nodoVariablesSecciones = NodoTO.builder()
    				.cveProceso(cveProceso)
    				.version(version)
    				.cveInstancia(cveInstancia)
    				.cveNodo(cveNodoCrear)
    				.idNodo(idNodoCrear)
    				.origen(origen)
    				.secuenciaNodo(secuenciaNodoCreado)
    				.build();
    		EstatusTO creaVS = creaVariablesSeccion(session, nodoVariablesSecciones, "VARIABLES", null);
    		log.info("-----------> CREA VARIABLES SECCION: " + creaVS.toString());
	    	result.setTipoExcepcion(creaVS.getTipoExcepcion());
	    	result.setMensaje(creaVS.getMensaje());
	    	
		    if(creaVS.getTipoExcepcion().equals(Constants.ERROR)) {
		    	return result;
		    }
		    
		    //SVM crea variables iniciales con refenecia en el nodo
		    EstatusTO creaVariables = setCreaVariablesRefencia(session, nodoVariablesSecciones);
		    
		    // CREA LAS TAREAS DEL PROCESO PARA EL NODO EN CASO DE QUE NO EXISTAN
		    NodoTO nodoCreaTareas = NodoTO.builder()
		    		.cveProceso(cveProceso)
		    		.version(version)
		    		.cveInstancia(cveInstancia)
		    		.cveNodo(cveNodoCrear)
		    		.idNodo(idNodoCrear)
		    		.origen(origen)
		    		.secuenciaNodo(secuenciaNodoCreado)
		    		.build();
		    EstatusTO creaTN = creaTareas(session, nodoCreaTareas);
    		log.info("-----------> CREA TAREAS: " + creaTN.toString());

		    result.setTipoExcepcion(creaTN.getTipoExcepcion());
	    	result.setMensaje(creaTN.getMensaje());
		    
		    if(creaTN.getTipoExcepcion().equals(Constants.ERROR)) {
		    	return result;
		    }
		    
		   // CREA LOS DOCUMENTOS DEL PROCESO PARA EL NODO EN CASO DE QUE NO EXISTAN
		    NodoTO nodoCreaDocumentos = NodoTO.builder()
		    		.cveProceso(cveProceso)
		    		.version(version)
		    		.cveInstancia(cveInstancia)
		    		.cveNodo(cveNodoCrear)
		    		.idNodo(idNodoCrear)
		    		.origen(origen)
		    		.secuenciaNodo(secuenciaNodoCreado)
		    		.build();
		    //EstatusTO creaDN = creaDocumentos(session, nodoCreaDocumentos);
		    EstatusTO creaDNM = creaDocumentosMultiples(session, nodoCreaDocumentos); // ajsute a multiple
		    result.setTipoExcepcion(creaDNM.getTipoExcepcion());
			result.setMensaje(creaDNM.getTipoExcepcion());
			
		    if(creaDNM.getTipoExcepcion().equals(Constants.ERROR)) {
		    	result.setMensaje(creaDNM.getMensaje());
		    	result.setTipoExcepcion(Constants.ERROR);
		    	return result;
		    }
		    
		    // RECIBE LAS VARIABLES DEL MENSAJE DE ENVÍO
		    if(folioMensajeEnvio != null && folioMensajeEnvio != 0) {
		    	EstatusTO recibeVE = recibeValoresEnvio(
		    			session,
		    			idProceso,
		    			folioMensajeEnvio,
		    			cveEntidad,
		    			cveProceso,
		    			version,
		    			cveInstancia);
		    	result.setTipoExcepcion(recibeVE.getTipoExcepcion());
				result.setMensaje(recibeVE.getTipoExcepcion());
		    	
		        if(recibeVE.getTipoExcepcion().equals(Constants.ERROR)) {
			    	return result;
			    }
		    }
		    
		    //CREA EL MENSAJE DE CORREO PARA EL EVENTO DE ASIGNACIÓN DE ACTIVIDAD
		    ocurrencia = 1;
		    NodoTO nodoCorreo = NodoTO.builder()
		    		.cveProceso(cveProceso)
		    		.version(version)
		    		.cveEventoCorreo(asignacionActividad)
		    		.cveUsuarioDestinatario(null)
		    		.cveRolDestinatario(null)
		    		.cveInstancia(cveInstancia)
		    		.cveNodo(cveNodoCrear)
		    		.idNodo(idNodoCrear)
		    		.secuenciaNodo(secuenciaNodoCreado)
		    		.ocurrencia(ocurrencia)
		    		.origen(origen)
		    		.cveUsuarioAsignado(usuarioAsignado)
		    		.build();
		    
		    log.info("------ CLAVE INSTANCIA EN CREAR NODO: " + cveInstancia);
		    log.info("------ NODO PARA CREAR CORREO PROCESO: " + nodoCorreo.toString());
			EstatusTO creaCP =  creaCorreoProceso(session, nodoCorreo, null, session.getCveUsuario());
		    log.info("----------> CREA CORREO PROCESO: " + creaCP.toString());
		    result.setTipoExcepcion(creaCP.getTipoExcepcion());
			result.setMensaje(creaCP.getTipoExcepcion());
		    
		    if(creaCP.getTipoExcepcion().equals(Constants.ERROR)) {
		    	return result;
		    }
		 // Ajuste para crear correos para consulta REPSE
		 //   consultarNodoRepse(session, nodo);
		    
		    // GUARDANDO ENTRADA DE LA BITÁCORA
		    NodoTO nodoGeneraBitacora = NodoTO.builder()
		    		.cveProceso(cveProceso)
		    		.version(version)
		    		.cveInstancia(cveInstancia)
		    		.cveNodo(cveNodoCrear) 
		    		.idNodo(idNodoCrear)
		    		.secuenciaNodo(secuenciaNodoCreado)
		    		.origen(origen)
		    		.build();
		    EstatusTO generaEB = generaEventoBitacora(session, nodoGeneraBitacora, "Creación");
			result.setTipoExcepcion(generaEB.getTipoExcepcion());
			result.setMensaje(generaEB.getTipoExcepcion());
	    }
    	
    	result.setSecuenciaNodo(secuenciaNodoCreado);
        log.info("############### RETURN SP_CREA_NODO: " + result.toString());
	   return result;
	}


	// SP_LEE_BITACORA_NODO
	@Override
	public BitacoraNodo leeBitacoraNodo(DatosAutenticacionTO session, ActividadTO actividad)
			throws BrioBPMException {
		String mensaje = null;
		List<Object> bitacoraNodo = iInBitacoraNodoRepository.leeBitacoraNodo(session.getCveEntidad(), actividad.getCveProceso(), 
				actividad.getVersion() != null ? new BigDecimal(actividad.getVersion()) : BigDecimal.ZERO,
				actividad.getCveInstancia(),  actividad.getCveNodo(), new BigInteger(actividad.getIdNodo().toString()), 
				new BigInteger(actividad.getSecNodo().toString()));
		
		List<BitacoraTO> bitacoraTOlist = new ArrayList<BitacoraTO>();
		if (!bitacoraNodo.isEmpty()) {
			mensaje = Constants.OK;
			for (int i = 0; i < bitacoraNodo.size(); i++) {
				Object[] rowDetail = (Object[]) bitacoraNodo.get(i);
				BitacoraTO bitacoraTO = BitacoraTO.builder()
						.fecha(formatFecha((Date) Arrays.asList(rowDetail).get(0),"dd/MM/yyyy HH:mm:ss"))
						.accion((String) Arrays.asList(rowDetail).get(1))
						.usuario((String) Arrays.asList(rowDetail).get(2))
						.build();
			bitacoraTOlist.add(bitacoraTO);
			}
		}

		BitacoraNodo bitacora =  new BitacoraNodo();
		bitacora.setMensaje(mensaje);
		bitacora.setListaBitacoraNodo(bitacoraTOlist);		
		return bitacora;
	}
	
	
	@Override
	public String traducirEtiqueta(DatosAutenticacionTO session, String etiqueta) throws BrioBPMException {
		//log.info("-------------> ENTRA A TRADUCIR ETIQUETA <---------------");
		String cveIdiomaEntidad;
		String etiquetaTraducida = etiqueta;
		//log.debug("----> ETIQUETA RECIBIDA : " + etiqueta);		
		// OBTIENE EL IDIOMA DE LA ENTIDAD
		Optional<Entidad> entidad = entidadRepository.findById(session.getCveEntidad())	;	//	DETERMINA SI SE REQUIERE TRADUCCIÓN
		cveIdiomaEntidad = entidad.get().getIdioma().getCveIdioma();
		
		//DETERMINA SI SE REQUIERE TRADUCCIÓN
		//log.debug("------> ANTES IF ETIQUETA TRADUCIDA: " + etiquetaTraducida);
		if (etiqueta != null && !session.getCveIdioma().equals(cveIdiomaEntidad)) {
			log.debug("------> ENTRA IF ETIQUETA TRADUCIDA: " + etiquetaTraducida);
			etiquetaTraducida = traduccionRepository.traducirEtiqueta(session.getCveIdioma(), etiqueta);
			log.debug("------ > ETIQUETA TRADUCIDA: " + etiquetaTraducida);
			etiquetaTraducida = etiquetaTraducida != null ? "*" + etiquetaTraducida : etiqueta.trim();
		}
//		etiquetaTraducida = etiquetaTraducida != null ? "*" + etiquetaTraducida : null;
//		log.info("########## RETORNA TRADUCIDA: " + etiquetaTraducida);
		return etiquetaTraducida;
	}
	
	// SP_LEE_SECCIONES_NODO
	@Override
	public 	DAORet<List<StSeccionNodoTO>, RetMsg>  leeSeccionesNodo(DatosAutenticacionTO session, NodoTO nodo, String generaTablaTemporal, List<StSeccionNodoTO> seccionNodo)
			throws BrioBPMException {
		
		//log.info("---------------leeSeccionesNodo---------------------");
		//log.debug("-----sesion: " + session.toString());
		//log.debug("-----nodo: " + nodo.toString());
		//log.debug("-----generaTablaTemporal: " + generaTablaTemporal);
		
		String cveEntidad = session.getCveEntidad();
		String cveNodo = nodo.getCveNodo();
		String cveProceso = nodo.getCveProceso();
		Integer idNodo = nodo.getIdNodo();
		BigDecimal version = nodo.getVersion();
		String usoSeccion = nodo.getUsoSeccion();
		String idProceso;
		String mensaje;
		String variableValor;
		
		//INICIALIZA CÓDIGO DE ERROR, MENSAJE Y SUGERENCIA
		RetMsg msg = RetMsg.builder().status("OK").message("").build();
		List<StSeccionNodoTO> seccionesNodo = new ArrayList<StSeccionNodoTO>();

		idProceso =  "LEE_SECCIONES_NODO";
		// CONCATENA LA LISTA DE LOS PARÁMETROS PARA DESPLEGARLOS EN CASO DE ERROR
		variableValor = Constants.CVE_PROCESO + cveProceso + "|" +
				Constants.VERSION + version + "|" +
				Constants.CVE_NODO + cveNodo + "|" +
				Constants.ID_NODO + idNodo;
		
		// VALIDA QUE EXISTAN SECCIONES PARA EL NODO PROPORCIONADO
		Integer nodoP = stSeccionNodoRepository.validaSecciones(cveEntidad, cveProceso, version, cveNodo, idNodo);
		if(nodoP == null) {
			mensaje = messagesService.getMessage(
					session,
					idProceso,
					Constants.NO_EXISTEN_SECCIONES_NODO,
					variableValor);
			msg.setStatus(Constants.ERROR);
			msg.setMessage(mensaje);
			return new DAORet<List<	StSeccionNodoTO>, RetMsg>(null, msg);
		}
		
		usoSeccion = usoSeccion != null ? usoSeccion : "WEB";
		
		// RECUPERA LAS SECCIONES DE UN NODO
		// SVM AJUSTE DE SELECCION
		if(generaTablaTemporal.equals(Constants.NO)) {
			seccionesNodo = stSeccionNodoRepository.recuperaSeccionesNodo(cveEntidad, cveProceso, version, cveNodo, idNodo, usoSeccion)
					.stream() 
					.map(item -> {
					        Object[] row = (Object[]) item;
					        return StSeccionNodoTO.builder()
					        	   .orden(((BigDecimal) Arrays.asList(row).get(MagicNumber.ZERO.getValue())).intValue())
					               .cveSeccion((String) Arrays.asList(row).get(MagicNumber.ONE.getValue()))					               
					               .etiquetaSeccion((String) Arrays.asList(row).get(MagicNumber.TWO.getValue()))
					               .tipoPresentacion((String) Arrays.asList(row).get(MagicNumber.THREE.getValue()))
					               .botonCrearRenglon((String) Arrays.asList(row).get(MagicNumber.FOUR.getValue()))
					               .etiquetaCrearRenglon((String) Arrays.asList(row).get(MagicNumber.FIVE.getValue()))
					               .botonEliminarRenglon((String) Arrays.asList(row).get(MagicNumber.SIX.getValue()))
					               .etiquetaEliminarRenglon((String) Arrays.asList(row).get(MagicNumber.SEVEN.getValue()))
					               .contenido((String) Arrays.asList(row).get(MagicNumber.EIGHT.getValue()))
					               .registroSelecconables((BigDecimal) Arrays.asList(row).get(MagicNumber.NINE.getValue()))
					               .build();
					    })
					    .peek(to -> to.setEtiquetaSeccion(traducirEtiqueta(session, to.getEtiquetaSeccion())))
					    .peek(to -> to.setEtiquetaCrearRenglon(traducirEtiqueta(session, to.getEtiquetaCrearRenglon())))
					    .peek(to -> to.setEtiquetaEliminarRenglon(traducirEtiqueta(session, to.getEtiquetaEliminarRenglon())))
					    .collect(Collectors.toList());
		} else {
		    List<StSeccionNodoTO> tempSeccionesNodo = stSeccionNodoRepository.recuperaSeccionesNodo(cveEntidad, cveProceso, version, cveNodo, idNodo, usoSeccion)
		            .stream()
		            .map(item -> {
		                Object[] row = (Object[]) item;
		                return StSeccionNodoTO.builder()
		                    .orden(((BigDecimal) Arrays.asList(row).get(MagicNumber.ZERO.getValue())).intValue())
		                    .cveSeccion((String) Arrays.asList(row).get(MagicNumber.ONE.getValue()))
		                    .etiquetaSeccion((String) Arrays.asList(row).get(MagicNumber.TWO.getValue()))
		                    .tipoPresentacion((String) Arrays.asList(row).get(MagicNumber.THREE.getValue()))
		                    .botonCrearRenglon((String) Arrays.asList(row).get(MagicNumber.FOUR.getValue()))
		                    .etiquetaCrearRenglon((String) Arrays.asList(row).get(MagicNumber.FIVE.getValue()))
		                    .botonEliminarRenglon((String) Arrays.asList(row).get(MagicNumber.SIX.getValue()))
		                    .etiquetaEliminarRenglon((String) Arrays.asList(row).get(MagicNumber.SEVEN.getValue()))
		                    .contenido((String) Arrays.asList(row).get(MagicNumber.EIGHT.getValue()))
		                    .registroSelecconables(row[MagicNumber.NINE.getValue()] == null ? BigDecimal.ZERO : (BigDecimal) Arrays.asList(row).get(MagicNumber.NINE.getValue()))
		                    .build();
		            })
		            .peek(to -> to.setEtiquetaSeccion(traducirEtiqueta(session, to.getEtiquetaSeccion())))
		            .peek(to -> to.setEtiquetaCrearRenglon(traducirEtiqueta(session, to.getEtiquetaCrearRenglon())))
		            .peek(to -> to.setEtiquetaEliminarRenglon(traducirEtiqueta(session, to.getEtiquetaEliminarRenglon())))
		            .collect(Collectors.toList());

		    // Agregar los elementos a la lista pasada como parámetro
		    seccionNodo.addAll(tempSeccionesNodo);
		    //log.info("--------------> seccionNodo aqui::: " + seccionNodo.toString());
		}
		
		log.info("--------------> seccioneNodo::: " + seccionesNodo.toString());
		return new DAORet<List<StSeccionNodoTO>, RetMsg>(seccionesNodo, msg);
	}

	// SP_LEE_DOCUMENTOS_NODO
	@Override
	public 	DAORet<List<DocumentoNodoTO>, RetMsg> leeDocumentosNodo(DatosAutenticacionTO session, NodoTO nodo, String cveSeccion, String generaTablaTemporal, List<DocumentoNodoTO> documentoNodo) throws BrioBPMException {
		
		log.info("---------------leeDocumentosNodo---------------------");
		log.info("-----sesion: " + session.toString());
		log.info("-----nodo: " + nodo.toString());
		log.info("-----cveSeccion: " + cveSeccion);
		log.info("-----generaTablaTemporal: " + generaTablaTemporal);
		log.info("-----documentoNodo: " + documentoNodo.toString());
		String cveEntidad = session.getCveEntidad();
		String cveInstancia = nodo.getCveInstancia();
		String cveNodo = nodo.getCveNodo();
		String cveProceso = nodo.getCveProceso();
		Integer idNodo = nodo.getIdNodo();
		Integer secuenciaNodo = nodo.getSecuenciaNodo();
		BigDecimal version = nodo.getVersion();
		String idProceso;
		String mensaje;
		String variableValor;
		
		//INICIALIZA CÓDIGO DE ERROR, MENSAJE Y SUGERENCIA
		RetMsg msg = RetMsg.builder().status(Constants.OK).message("").build();
		idProceso =  Constants.LEE_COLUMNAS_SECCION_OCU;
		
		// CONCATENA LA LISTA DE LOS PARÁMETROS PARA DESPLEGARLOS EN CASO DE ERROR
		variableValor = Constants.CVE_PROCESO + cveProceso + "|" +
				Constants.VERSION + version + "|" +
				Constants.CVE_INSTANCIA + cveInstancia + "|" +
				Constants.CVE_NODO + cveNodo + "|" +
				Constants.ID_NODO + idNodo + "|" +
				Constants.SECUENCIA_NODO + secuenciaNodo + "|" +
				Constants.CVE_SECCION + cveSeccion ;
		
		List<DocumentoNodoTO> resultados = new ArrayList<DocumentoNodoTO>();
		if(Constants.NO.equals(generaTablaTemporal)) {
			
			// REVISAR PARA MULTIPLE
			//List<Object> documento = stDocumentoSeccionRepository.obtenerDocumentos(cveEntidad, cveProceso, version, cveInstancia, cveNodo, idNodo, cveSeccion);

			log.info("DOC 1");
			List<Object> documento = stDocumentoSeccionRepository.obtenerDocumentosMultiple(cveEntidad, cveProceso, version, cveNodo, idNodo, cveSeccion);
	        log.info("------LISTA: " + documento.toString());
			if (documento.isEmpty()) {
				mensaje = messagesService.getMessage(
						session,
						idProceso,
						"ERR_LEE_DOCUMENTOS_NODO",
						variableValor);
				msg.setStatus(Constants.ERROR);
				msg.setMessage(mensaje);
				return new DAORet<List<DocumentoNodoTO>, RetMsg>(null, msg);
			}
			
			documento.forEach(item -> {
		        Object[] row = (Object[]) item;
		        String descripcionTraducida =  traducirEtiqueta(session, (String) row[MagicNumber.ONE.getValue()]);
		        Integer secDoc = (Integer) row[MagicNumber.ZERO.getValue()];
		        
		        List<String> nombres = stDocumentoSeccionRepository.obtenerNombresArchivo(cveEntidad, cveProceso, version, cveInstancia, secDoc);
		        
		        String nomArc = "";
		        	
	        	// Verificar si la lista no está vacía
	        	if (!nombres.isEmpty()) {
	        	    // Concatenar los nombres con un asterisco
	        		nomArc = String.join(" * ", nombres);
	        	}
	        	
		        DocumentoNodoTO itemSelected = DocumentoNodoTO.builder()
		                .secuencia(secDoc)
		                .descripcionDocumento(descripcionTraducida)
		                .requerido((String) row[MagicNumber.TWO.getValue()] )
		                .nombreArchivo((nomArc != null) ? nomArc : "")
		                .build();
		        log.info("------SECUENCIA     : " + itemSelected.getSecuencia());
		        log.info("------DESCRIPCION   : " + itemSelected.getDescripcionDocumento());
		        log.info("------REQUERIDO     : " + itemSelected.getRequerido());
		        log.info("------NOMBRE ARCHIVO: " + itemSelected.getNombreArchivo());
		        resultados.add(itemSelected);
		    });
			return new DAORet<List<DocumentoNodoTO>, RetMsg>(resultados, msg);

		} else {
			
			//List<Object> documento = stDocumentoSeccionRepository.obtenerDocumentos(cveEntidad, cveProceso, version, cveInstancia, cveNodo, idNodo, cveSeccion);
			log.info("DOC 2");
			List<Object> documento = stDocumentoSeccionRepository.obtenerDocumentosMultiple(cveEntidad, cveProceso, version,cveNodo, idNodo, cveSeccion);
	        log.info("------LISTA: " + documento.toString());
			if (documento.isEmpty()) {
				mensaje = messagesService.getMessage(
						session,
						idProceso,
						Constants.ERR_LEE_DOCUMENTOS_NODO,
						variableValor);
				msg.setStatus(Constants.ERROR);
				msg.setMessage(mensaje);
				return new DAORet<List<DocumentoNodoTO>, RetMsg>(null, msg);
			}
			
			
			documento.forEach(item -> {
		        Object[] row = (Object[]) item;
		        Integer secDoc = row[MagicNumber.ZERO.getValue()] != null ? ((BigDecimal) row[MagicNumber.ZERO.getValue()]).intValue() : null;
		        String descripcionTraducida =  traducirEtiqueta(session, (String) row[MagicNumber.ONE.getValue()]);
		        List<String> nombres = stDocumentoSeccionRepository.obtenerNombresArchivo(cveEntidad, cveProceso, version, cveInstancia, secDoc);

		        log.info("------LISTA NOMBRES 3: " + nombres.toString());
		        
		        String nomArc = null;

			    if (nombres != null && !nombres.isEmpty()) {
			        // Verificar si la lista contiene solo un elemento y ese elemento es `null`
			        if (!(nombres.size() == 1 && nombres.get(0) == null)) {
			            // Concatenar solo si tiene valores no nulos
			            nomArc = String.join("*", nombres);
			        }
			    }
	        	
	        	log.info("---- NOMBRE ARCHIVO: " + nomArc);
	        	
		        DocumentoNodoTO itemSelected = DocumentoNodoTO.builder()
		        	    .secuencia(secDoc)
		        	    .descripcionDocumento(descripcionTraducida)
		        	    .requerido((String) row[MagicNumber.TWO.getValue()])
		        	    .nombreArchivo(nomArc)
		        	    .build();

		        log.debug("------SECUENCIA     : " + itemSelected.getSecuencia());
		        log.debug("------DESCRIPCION   : " + itemSelected.getDescripcionDocumento());
		        log.debug("------REQUERIDO     : " + itemSelected.getRequerido());
		        log.debug("------NOMBRE ARCHIVO: " + itemSelected.getNombreArchivo());
		        documentoNodo.add(itemSelected);
		    });
			return new DAORet<List<DocumentoNodoTO>, RetMsg>(documentoNodo, msg);
		}
	}


	// SP_LEE_ELEMENTO_CADENA
	/**
	 * Método que extrae un elemento de una cadena a partir de una posición de búsqueda específica,
	 * utilizando un separador definido para delimitar el elemento. 
	 * Si el separador no se encuentra, se toma el resto de la cadena como el elemento.
	 * 
	 * @param cadena La cadena completa de la cual se extraerá el elemento.
	 * @param longitudCadena La longitud total de la cadena.
	 * @param posicionBusqueda La posición inicial desde donde se comenzará a buscar el separador.
	 * @param separador El separador que delimita el final del elemento.
	 * @return Un objeto `ElementoCadenaTO` que contiene el elemento extraído, la posición final en la cadena,
	 *         y una indicación de si se encontró o no el separador.
	 * @throws BrioBPMException Si ocurre un error durante la ejecución.
	 */
	@Override
	public ElementoCadenaTO leeElementoCadena(String cadena, Integer longitudCadena, Integer posicionBusqueda,
	        String separador) throws BrioBPMException {
		
		log.debug("---------------leeElementoCadena---------------------");
		log.info("CADENA: " + cadena);
		log.info("LONGITUD CADENA: " + longitudCadena);
		log.info("POSICIÓN BÚSQUEDA: " + posicionBusqueda);
		log.info("SEPARADOR: " + separador);

	    // Inicializa la posición final y el objeto de resultado
	    Integer posicionFinal;
	    ElementoCadenaTO result = new ElementoCadenaTO();

	    // VALIDA LA POSICIÓN DE BÚSQUEDA
	    // Si la posición de búsqueda está fuera de los límites de la cadena, retorna un objeto indicando que no se encontró el elemento
	    if (posicionBusqueda < 0 || posicionBusqueda > longitudCadena) {
	        result.setEncontro(Constants.NO);
	        posicionFinal = 0;
	        result.setPosicionFinal(posicionFinal);
	        result.setElemento("");
	        return result;
	    }

	    // BUSCA EL SEPARADOR O EL FINAL DE LA CADENA CUANDO NO EXISTE SEPARADOR
	    // Busca el separador a partir de la posición de búsqueda
	    posicionFinal = cadena.indexOf(separador, posicionBusqueda);

	    // Si no se encuentra el separador, toma el resto de la cadena como el elemento
	    if (posicionFinal == -1) {
	        posicionFinal = longitudCadena - 1; // Establece la posición final al final de la cadena
	    } else {
	        posicionFinal = posicionFinal - 1; // Ajusta la posición final para excluir el separador
	    }

	    // Ajusta el resultado con la posición final, el elemento encontrado y una indicación de éxito
	    result.setPosicionFinal(posicionFinal);
	    result.setElemento(cadena.substring(posicionBusqueda, posicionFinal + 1)); // Extrae el elemento
	    result.setEncontro(Constants.SI);
	    
	    // Registro de información para depuración
	    log.info("ELEMENTO*: " + cadena.substring(posicionBusqueda, posicionFinal + 1));
	    
	    return result;
	}


	// SP_EXTRAE_DATOS_OCURRENCIA
	/**
	 * Método que extrae datos de una cadena de ocurrencia proporcionada, 
	 * interpreta cada elemento en función de su tipo de variable (entero, decimal, fecha, etc.), 
	 * y almacena los resultados en una lista de objetos `ValorVariableTO`.
	 * 
	 * @param session Objeto `DatosAutenticacionTO` que contiene la sesión del usuario.
	 * @param nodo Objeto `NodoTO` que contiene información del nodo actual.
	 * @param datosOcurrencia Cadena que contiene los datos de ocurrencia en un formato delimitado.
	 * @param valorVariableList Lista en la que se almacenarán los valores de las variables extraídas.
	 * @return Objeto `EstatusTO` que contiene el estado final de la operación.
	 * @throws BrioBPMException Si ocurre algún error durante la ejecución.
	 * @throws ParseException Si ocurre un error al parsear las fechas.
	 */
	@Override
	public EstatusTO extraeDatosOcurrencia(DatosAutenticacionTO session, NodoTO nodo, String datosOcurrencia,
			List<ValorVariableTO> valorVariableList) throws BrioBPMException, ParseException{
		
		log.debug("---------------extraeDatosOcurrencia---------------------");
		
		// Declaración de variables locales
		String cveVariable;
		Integer decimales;
		String encontrado;
		Integer enteros;
		Boolean finBusqueda;
		String formatoFecha;
		Integer longitudCadena;
		Integer longitud;
		Integer longitudCveMoneda;
		Integer numValores;
		Integer numValoresLeidos;
		Integer posicionBusqueda;
		Integer posicionFinal;
		Integer secuencia;
		String tipo;
		String valorAlfanumerico;
		Integer valorEntero;
		BigDecimal valorDecimal;
		Date valorFecha;
		String valorVariable;
		
		// INICIALIZA CÓDIGO DE ERROR, MENSAJE Y SUGERENCIA
		EstatusTO result = EstatusTO.builder()
				.tipoExcepcion(Constants.OK)
				.mensaje("")
				.build();
		// INICIALIZA OTRAS VARIABLES
		longitudCveMoneda = 3;
		// OBTIENE LAS VARIABLES Y SUS VALORES PARA GRABARLOS EN LA TABLA TEMPORAL
		
		// INICIALIZA VARIABLES
		finBusqueda = false;  // Flag que indica si se ha terminado la búsqueda en la cadena
	    posicionBusqueda = 0;  // Posición inicial para la búsqueda en la cadena
	    longitudCadena = datosOcurrencia != null ? datosOcurrencia.length() : 0;  // Longitud de la cadena de ocurrencia
	    
	    // Si la cadena de ocurrencia está vacía, termina la búsqueda
		if(longitudCadena == 0) {
			finBusqueda = true;
		}

		// RECORRE LA LISTA DE VARIABLE_VALOR PARA SEPARAR LOS DATOS
		// Recorre la lista de variables y valores en la cadena para separar los datos
		while (!finBusqueda) {
			
			// OBTIENE LA VARIABLE
			// Obtiene la variable actual de la cadena de ocurrencia
			ElementoCadenaTO elementoCadena = leeElementoCadena(datosOcurrencia, longitudCadena, posicionBusqueda, "|");
	        encontrado = elementoCadena.getEncontro();  // Verifica si se encontró la variable
			if(encontrado.contentEquals(Constants.NO)) {
	            finBusqueda = true;  // Termina la búsqueda si no se encontró la variable
				break;
			}
			
			// OBTIENE EL NÚMERO DE VALORES
			posicionFinal = elementoCadena.getPosicionFinal();
			cveVariable = elementoCadena.getElemento(); // Clave de la variable
			
		    posicionBusqueda = posicionFinal + 2;  // Avanza la posición de búsqueda
	        elementoCadena = leeElementoCadena(datosOcurrencia, longitudCadena, posicionBusqueda, "|");
	        encontrado = elementoCadena.getEncontro();  // Verifica si se encontró el número de valores
	        if(encontrado.contentEquals(Constants.NO)) {
	            finBusqueda = true;  // Termina la búsqueda si no se encontró el número de valores
	            break;
	        }
	        
			posicionFinal = elementoCadena.getPosicionFinal();
			numValores = elementoCadena.getElemento() != null ? Integer.parseInt(elementoCadena.getElemento()) : 0;
			
			// OBTIENE LA SECUENCIA Y EL VALOR DE LA VARIABLE, TANTAS VECES COMO LO INDICA numValores
	        // Bucle para obtener la secuencia y el valor de la variable tantas veces como lo indica numValores
			numValoresLeidos = 0;
			while((numValoresLeidos < numValores) && !finBusqueda) {
				
				// OBTIENE LA SECUENCIA DEL VALOR
				posicionBusqueda = posicionFinal + 2;
	            elementoCadena = leeElementoCadena(datosOcurrencia, longitudCadena, posicionBusqueda, "|");
	            encontrado = elementoCadena.getEncontro();  // Verifica si se encontró la secuencia
	            if(encontrado.contentEquals(Constants.NO)) {
	                finBusqueda = true;  // Termina la búsqueda si no se encontró la secuencia
	                break;
	            }
	            posicionFinal = elementoCadena.getPosicionFinal();
	            secuencia = Integer.parseInt(elementoCadena.getElemento());  // Secuencia del valor de la variable
	            
				
				// OBTIENE EL VALOR DE LA VARIABLE
				posicionBusqueda = posicionFinal + 2;
				log.debug("entrada leeElementoCadena {} * {} * {} * {} ", datosOcurrencia, longitudCadena, posicionBusqueda);
				elementoCadena = leeElementoCadena(datosOcurrencia, longitudCadena, posicionBusqueda, "|");
				log.debug("elementoCadena***: " + elementoCadena.toString());
				posicionFinal = elementoCadena.getPosicionFinal();
				if(posicionFinal == 0) {
	                finBusqueda = true;  // Termina la búsqueda si no se encontró el valor
					break;
				}
				
				encontrado = elementoCadena.getEncontro();  // Verifica si se encontró el valor
		        valorVariable = elementoCadena.getElemento();  // Valor de la variable
				
				// INICIALIZA LOS VALORES ENTERO, DECIMAL Y FECHA
				valorAlfanumerico = null;
				valorEntero = null;
				valorDecimal = null;
				valorFecha = null;
				
				// BUSCA EL TIPO DE DATO DE LA VARIABLE
				tipo = null;
				longitud = null;
				enteros = null;
				decimales = null;
				formatoFecha = null;
				
				if(cveVariable.indexOf(Constants.MON) == -1 ) {
	                // Consulta en la base de datos para obtener las propiedades de la variable
					StVariableProcesoPK id = StVariableProcesoPK.builder()
							.cveEntidad(session.getCveEntidad())
							.cveProceso(nodo.getCveProceso())
							.version(nodo.getVersion())
							.cveVariable(cveVariable)
							.build();
					
					Optional<StVariableProceso> stVariableProceso = iStVariableProcesoRepository.findById(id);
					if(stVariableProceso.isPresent()) {
	                    tipo = stVariableProceso.get().getTipo();  // Tipo de la variable
	                    longitud = stVariableProceso.get().getLongitud();  // Longitud de la variable
	                    enteros = stVariableProceso.get().getEnteros();  // Número de dígitos enteros
	                    decimales = stVariableProceso.get().getDecimales();  // Número de dígitos decimales
	                    formatoFecha = stVariableProceso.get().getFormatoFecha();  // Formato de fecha
	                }
				} else {
					tipo = Constants.ALFANUMERICO;
					longitud = longitudCveMoneda;
				}
				
			 // Asigna el valor a la variable según su tipo
			 if(valorVariable != null && !valorVariable.isEmpty() && 
	                    !valorVariable.equals(" ") && posicionFinal > 0) {
	                if(tipo.equals(Constants.ENTERO)) {
	                	
	                	log.info("TIPO **: " +  tipo);
	                	log.info("VALOR VARIBALE * : " +  valorVariable);
	                	
	                	valorEntero = Integer.parseInt(valorVariable);  // Valor entero
	                } else if(tipo.equals(Constants.DECIMAL) || tipo.equals(Constants.MONEDA)) {
	                    valorDecimal = new BigDecimal(valorVariable);  // Valor decimal
	                    valorAlfanumerico = null;
	                } else if(tipo.equals(Constants.FECHA)) {
	                    valorVariable = valorVariable.replace("/", "-");
	                    valorFecha = new SimpleDateFormat("yyyy-MM-dd").parse(valorVariable);  // Valor de fecha
	                    valorAlfanumerico = null;
	                } else if(tipo.equals(Constants.ALFANUMERICO)) {
	                    valorAlfanumerico = valorVariable;  // Valor alfanumérico
	                }
	            }
			
	            // Crea un objeto ValorVariableTO y lo añade a la lista de valores
				ValorVariableTO valorVariableTO = ValorVariableTO.builder()
						.secuencia(secuencia)
						.cveVariable(cveVariable)
						.tipo(tipo)
						.longitud(longitud)
						.enteros(enteros)
						.decimales(decimales)
						.formatoFecha(formatoFecha)
						.valorAlfanumerico(valorAlfanumerico)
						.valorEntero(valorEntero)
						.valorDecimal(valorDecimal)
						.valorFecha(valorFecha)
						.build();
				valorVariableList.add(valorVariableTO);
	            
				// Incrementa el contador de valores leídos y actualiza la posición de búsqueda
				numValoresLeidos = numValoresLeidos + 1;
				posicionBusqueda = posicionFinal + 2;
				
	            // Si la posición de búsqueda supera la longitud de la cadena, termina la búsqueda
				if(posicionBusqueda > longitudCadena) {
					finBusqueda = true;
					break;
				}
			}
		}
		
	    // Registra el resultado y lo retorna
		log.info("extraeDatosOcurrencia: " + result.toString());
		return result;
	}


	// SP_GUARDA_VARIABLES_SECCION
	/**
	 * Este método guarda las variables de una sección en un proceso. Recibe la sesión de autenticación,
	 * la información del nodo, la clave de la sección, si es la primera ocurrencia, si es una nueva
	 * ocurrencia y los datos de la ocurrencia. Este método realiza varias tareas, incluyendo la
	 * creación de una tabla temporal para almacenar valores, validación de información, actualización
	 * de valores y eliminación de registros existentes en caso necesario.
	 *
	 * @param session           Datos de autenticación del usuario.
	 * @param nodo              Información del nodo del proceso.
	 * @param cveSeccion        Clave de la sección.
	 * @param primeraOcurrencia Indica si es la primera ocurrencia de la sección.
	 * @param nuevaOcurrencia   Indica si es una nueva ocurrencia de la sección.
	 * @param datosOcurrencia   Datos de la ocurrencia que deben ser guardados.
	 * @return RetMsg           Mensaje con el resultado de la operación.
	 * @throws BrioBPMException Si ocurre algún error relacionado con BPM.
	 * @throws ParseException   Si ocurre algún error de parseo.
	 */
	@Override
	public RetMsg guardaVariablesSeccion(DatosAutenticacionTO session, NodoTO nodo, String cveSeccion, String primeraOcurrencia,
	        String nuevaOcurrencia, String datosOcurrencia) throws BrioBPMException, ParseException {
	    
		log.info("-------------guardaVariablesSeccion----------------");
		log.info("PRIMERA OCURRENCIA DE ENTRADA: " + primeraOcurrencia);
		log.info("NUEVA OCURRENCIA DE ENTRADA: " + nuevaOcurrencia);
		
	    // Se registra el evento de solicitud con los detalles de la sesión y del nodo.
	    log.info("\t\t\t\t REQUEST  SP_GUARDA_VARIABLES_SECCION guardaVariablesSeccion'{}', '{}', '{}', '{}', '{}', '{}', '{}'," +
	            " '{}', '{}', '{}', '{}', '{}', '{}', {}  ",
	            session.getCveUsuario(), session.getCveEntidad(), session.getCveLocalidad(), session.getCveIdioma(),
	            nodo.getRol(), nodo.getCveProceso(), nodo.getVersion(), nodo.getCveInstancia(),
	            nodo.getCveNodo(), nodo.getIdNodo(), cveSeccion);
	    
	    log.info("\t\t\t REQUEST  SP_GUARDA_VARIABLES_SECCION primeraOcurrencia: '{}', nuevaOcurrencia: '{}', datosOcurrencia: '{}'",
	    primeraOcurrencia, nuevaOcurrencia,
	            datosOcurrencia);

	    // Variables principales del proceso se inicializan.
	    String cveEntidad = session.getCveEntidad();
	    String cveInstancia = nodo.getCveInstancia();
	    String cveProceso = nodo.getCveProceso();
	    String cveVariable = nodo.getCveVariable();
	    Integer ocurrencia = nodo.getOcurrencia();
	    BigDecimal version = nodo.getVersion();
	    
	    log.info("----------> OCURRENCIA DE NODO: " + ocurrencia);

	    // Variables auxiliares para la lógica del proceso.
	    String cveVariableAnterior;
	    boolean existeInformacion;
	    String idProceso;
	    Integer ocurrenciaGrabar;
	    Integer secuenciaValor;
	    String valorAlfanumerico;
	    Integer valorEntero;
	    BigDecimal valorDecimal;
	    Date valorFecha;
	    String variableValor;

	    // Inicializa el mensaje de retorno con valores por defecto.
	    RetMsg msg = RetMsg.builder()
	            .status("OK")
	            .message("")
	            .build();

	    // Construye la variable de proceso para el mensaje de error en caso necesario.
	    idProceso = Constants.GUARDA_VARIABLES_SECCION;
	    variableValor = Constants.CVE_PROCESO + nodo.getCveProceso() + "|" +
	            Constants.VERSION + nodo.getVersion() + "|" + 
	            Constants.CVE_NODO + nodo.getCveNodo() + "|" +
	            Constants.ID_NODO + nodo.getIdNodo() + "|" +
	            Constants.CVE_SECCION + cveSeccion;
	    
	    // Crea una lista para almacenar los valores de las variables de la sección.
	    List<ValorVariableTO> valorVariable = new ArrayList<ValorVariableTO>();
	    
	    // Extrae los datos de la ocurrencia y los almacena en la lista de valores de variables.
	    EstatusTO result = extraeDatosOcurrencia(session, nodo, datosOcurrencia, valorVariable);
	    msg.setMessage(result.getMensaje());
	    msg.setStatus(result.getTipoExcepcion());
	    log.info("-------VALOR VARIABLE: " + valorVariable.size() + " " + valorVariable.toString());
	    
	    // Valida si hay información en la lista de valores de variables.
	    existeInformacion = false;
	    if(valorVariable != null && valorVariable.size() > 0) {
	        existeInformacion = true;
	    }
	    
	    // Si existe información en la lista, procede con la actualización de los valores.
	    if(existeInformacion) {
	        
	        // Si existen variables de tipo moneda, se actualizan con la clave de moneda y se eliminan las variables auxiliares.
	        long count = (valorVariable != null) 
	            ? valorVariable.stream().filter(v -> Constants.MONEDA.equals(v.getTipo())).count() 
	            : 0;
	                
	        if (count > 0) {
	            final List<ValorVariableTO> finalValorVariable = valorVariable;
	            finalValorVariable.stream()
	                .filter(v -> Constants.MONEDA.equals(v.getTipo()))
	                .forEach(v -> {
	                    Optional<ValorVariableTO> matchingElement = finalValorVariable.stream()
	                        .filter(innerV -> (v.getCveVariable() + Constants.MON).equals(innerV.getCveVariable())
	                                          && v.getSecuencia() == innerV.getSecuencia())
	                        .findFirst();
	                    matchingElement.ifPresent(innerV -> v.setValorAlfanumerico(innerV.getValorAlfanumerico()));
	                });
	            valorVariable = finalValorVariable.stream()
	                .filter(v -> !Constants.MONEDA.equals(v.getTipo()))
	                .collect(Collectors.toList());
	        }
	        
	        // Si es la primera ocurrencia, depura las variables de todas las ocurrencias de la sección.
	        try {
	            if(primeraOcurrencia.equals(Constants.SI)) {
	                Integer existeVariableProceso = inVariableProcesoRepository.findRecord(cveEntidad, cveProceso, version, cveInstancia, cveSeccion);
	                if(existeVariableProceso != null) {
	                    List<InVariableProceso> inVariableProcesoList = inVariableProcesoRepository.findRecords(cveEntidad, cveProceso, version, cveInstancia, cveSeccion);
	                    for (InVariableProceso inVariableProceso : inVariableProcesoList) {
	                        entityManager.remove(inVariableProceso);
	                    }
	                }
	            }
	        
	        } catch(BrioBPMException b) {
	            // Si ocurre un error al eliminar las variables de la sección, se maneja la excepción.
	            log.info("-----> ERROR ");
	            String mensaje = messagesService.getMessage(
	                    session,
	                    idProceso,
	                    "ERR_DEL_IN_VARIABLE_PROCESO_SECCION",
	                    variableValor);
	            msg.setMessage(mensaje);
	            msg.setStatus(Constants.ERROR);
	            return msg;
	        }

	        // Si es una nueva ocurrencia, determina el número de la misma.
	        ocurrenciaGrabar = 0;
	        if(nuevaOcurrencia.equals(Constants.SI)) {
	            ocurrenciaGrabar = inVariableProcesoRepository.findMaxOcurrencia(cveEntidad, cveProceso, version, cveInstancia, cveSeccion);
	            ocurrenciaGrabar = (ocurrenciaGrabar != null ? ocurrenciaGrabar : 0) +1;
	        } else {
	            ocurrenciaGrabar = ocurrencia;
	        }
	        
	        // Recorre la lista de valores de variables y actualiza o inserta los registros en la base de datos.
	        cveVariableAnterior = " ";
	        
	        log.info("-----> VALOR VARIABLE: {} {}" , valorVariable.size(), valorVariable.toString());
	        for(ValorVariableTO valor : valorVariable) {
	            cveVariable = valor.getCveVariable();
	           
	            secuenciaValor = valor.getSecuencia();
	            valorAlfanumerico =  valor.getValorAlfanumerico() != null ? Normalizer.normalize(valor.getValorAlfanumerico() , Normalizer.Form.NFC)  : null;

	            valorEntero = valor.getValorEntero();
	            valorDecimal = valor.getValorDecimal();
	            valorFecha = valor.getValorFecha();
	            
	            // Si la variable es nueva, elimina los valores existentes antes de insertar los nuevos.
	            if(cveVariable != cveVariableAnterior) {
	            	
	            	// Si la variable ya existe, elimina los valores anteriores.
	                Integer existeStVariableProceso = iStVariableProcesoRepository.findRecord(cveEntidad, cveProceso, version, cveVariable);
	                log.info("-----> existeStVariableProceso: " + existeStVariableProceso);
	                if(existeStVariableProceso != null) {
	                    List<InVariableProceso> borrarInvariable = inVariableProcesoRepository.findVariablesProceso(cveEntidad, cveProceso, version, cveInstancia, ocurrencia, cveVariable);
	                    for (InVariableProceso inVariableProceso : borrarInvariable) {
	                        inVariableProcesoRepository.delete(inVariableProceso);
	                    }
	                }
	                cveVariableAnterior = cveVariable;
	            }
	            
	            // Crea el identificador primario para la tabla de variables y verifica si ya existe el registro.
	            log.info("--------> OCURRENCIA DECLARADA EN EL ID PREVIO A INSERTAR: " + ocurrencia);
	            InVariableProcesoPK id = InVariableProcesoPK.builder()
	                    .cveEntidad(cveEntidad)
	                    .cveProceso(cveProceso)
	                    .version(version)
	                    .cveInstancia(cveInstancia)
	                    .ocurrencia(ocurrencia)
	                    .cveVariable(cveVariable)
	                    .secuenciaValor(secuenciaValor)
	                    .build();
	            Optional<InVariableProceso> existeInVariableProceso = inVariableProcesoRepository.findById(id);
	            if(existeInVariableProceso.isPresent()) {
	                // Si el registro ya existe, lo actualiza.
	                log.info("--------> actualizara un registro ya existende de IN_VARIABLE_PROCESO");
	                existeInVariableProceso.get().setValorAlfanumerico(valorAlfanumerico);
	                existeInVariableProceso.get().setValorEntero(valorEntero);
	                existeInVariableProceso.get().setValorDecimal(valorDecimal);
	                existeInVariableProceso.get().setValorFecha(valorFecha);
	                inVariableProcesoRepository.saveAndFlush(existeInVariableProceso.get());
	            } else {
	                // Si el registro no existe, inserta un nuevo registro.
	                log.info("--------> insertara un registro nuevo en IN_VARIABLE_PROCESO");
	                id.setOcurrencia(ocurrenciaGrabar);
	                
	                InVariableProceso inVariableProceso = InVariableProceso.builder()
	                        .id(id)
	                        .valorAlfanumerico(valorAlfanumerico)
	                        .valorEntero(valorEntero)
	                        .valorDecimal(valorDecimal)
	                        .valorFecha(valorFecha)
	                        .build();
	                inVariableProcesoRepository.saveAndFlush(inVariableProceso);
	            }
	        }
	    } else {
	        // Si no existe información en la lista, muestra un mensaje de error.
	        log.info("NO HAY INFORMACIÓN");
	        String mensaje = messagesService.getMessage(session, idProceso, "ERR_NO_VARIABLES", variableValor);
	        msg.setMessage(mensaje);
	        msg.setStatus(Constants.ERROR);
	        return msg;
	    }
	    
	    // Retorna el mensaje de resultado con la información del estado de la operación.
	    log.info("antes de retornar msg de guardaVariablesSeccion  "+msg.getMessage());
	    return msg;
	}

	
	// SP_EXTRAE_TAREAS_CADENA
	@Override
	public EstatusTO extraeTareasCadena(DatosAutenticacionTO session, NodoTO nodo,
			String secuenciaCompletada, List<SituacionTareaTO> situacionTarea) throws BrioBPMException {

		String cveEntidad = session.getCveEntidad();
		String cveInstancia = nodo.getCveInstancia();
		String cveProceso = nodo.getCveProceso();
		BigDecimal version = nodo.getVersion();
		
		String completada;
		boolean finBusqueda;
		String idProceso;
		Integer longitudCadena;
		String mensaje;
		Integer posicionBusqueda;
		Integer posicionFinal;
		Integer secuenciaTarea;
		String secuenciaTareaStr;
		String variableValor;
		
		// INICIALIZA CÓDIGO DE ERROR, MENSAJE Y SUGERENCIA
		EstatusTO result = EstatusTO.builder()
				.tipoExcepcion(Constants.OK)
				.mensaje("")
				.build();
		idProceso = Constants.EXTRAE_TAREAS_NODO;
		
		// CONCATENA LA LISTA DE LOS PARÁMETROS PARA DESPLEGARLOS EN CASO DE ERROR
		variableValor = Constants.CVE_ENTIDAD + cveEntidad + "|" +
				Constants.CVE_LOCALIDAD + session.getCveLocalidad() + "|" +
				Constants.CVE_PROCESO + cveProceso + "|" +
				Constants.VERSION + version + "|" + 
				Constants.CVE_INSTANCIA + cveInstancia + "|" +
				Constants.CVE_NODO + nodo.getCveNodo() + "|" +
				Constants.ID_NODO + nodo.getIdNodo() + "|" +
				Constants.SECUENCIA_NODO + nodo.getSecuenciaNodo();
		
		// OBTIENE LAS TAREAS Y SUS SITUACIONES
		// INICIALIZA VARIABLES
		
		finBusqueda = false;
		posicionBusqueda = 0;
		longitudCadena = secuenciaCompletada != null ? secuenciaCompletada.length() : 0;
		
		if(longitudCadena == 0) {
			finBusqueda = false;
		}
		
		// RECORRE LA LISTA DE TAREA_VALOR PARA SEPARAR LOS DATOS
		while(!finBusqueda) {
			
			// OBTIENE LA SECUENCIA DE LA TAREA
			ElementoCadenaTO elementoCadena = leeElementoCadena(secuenciaCompletada, longitudCadena, posicionBusqueda, "|");
			if(elementoCadena.getEncontro().equals(Constants.NO)) {
				finBusqueda = true;
				break;
			}
			posicionFinal = elementoCadena.getPosicionFinal();
			secuenciaTareaStr = elementoCadena.getElemento();
			
			// OBTIENE LA SITUACIÓN DE LA TAREA
			posicionBusqueda = posicionFinal + 2;
			elementoCadena = leeElementoCadena(secuenciaCompletada, longitudCadena, posicionBusqueda, "|");
			if(elementoCadena.getEncontro().equals(Constants.NO)) {
				finBusqueda = true;
				break;
			}
			posicionFinal = elementoCadena.getPosicionFinal();
			completada = elementoCadena.getElemento();
			log.info("completada: " + completada);
			
			// VALIDA QUE LA SECUENCIA DE LA TAREA SEA NUMÉRICA
			if (!secuenciaTareaStr.matches("\\d+")) {
				// ERROR
				variableValor = variableValor + "|" + Constants.SECUENCIA_TAREA + secuenciaTareaStr;
				mensaje = messagesService.getMessage(
						session,
						idProceso,
						Constants.SEC_TAREA_NO_NUMERICA,
						variableValor);
				result.setTipoExcepcion(Constants.ERROR);
				result.setMensaje(mensaje);
				return result;
			}

			secuenciaTarea = Integer.parseInt(secuenciaTareaStr);
			
			// VALIDA QUE LA SECUENCIA DE LA TAREA EXISTA
			InTareaProcesoPK id = InTareaProcesoPK.builder()
					.cveEntidad(cveEntidad)
					.cveProceso(cveProceso)
					.version(version)
					.cveInstancia(cveInstancia)
					.secuenciaTarea(secuenciaTarea)
					.build();
			Optional<InTareaProceso> inTareaProceso = inTareaProcesoRepository.findById(id);
			
			if(!inTareaProceso.isPresent()) {
				// ERROR
				variableValor = variableValor + "|" + Constants.SECUENCIA_TAREA + secuenciaTareaStr;
				mensaje = messagesService.getMessage(
						session,
						idProceso,
						Constants.TAREA_NODO_NO_EXISTE,
						variableValor);
				result.setTipoExcepcion(Constants.ERROR);
				result.setMensaje(mensaje);
				return result;
			}
			
			// VALIDA QUE EL ESTADO SEA CORRECTO (SI/NO)
			
			if (!completada.equals(Constants.SI) && !completada.equals(Constants.NO)) {
				// ERROR
				variableValor = variableValor + "|" + Constants.SECUENCIA_TAREA + secuenciaTareaStr + "|" +
						Constants.COMPLETADA + completada;
				mensaje = messagesService.getMessage(
						session,
						idProceso,
						"ESTADO_INVALIDO_TAREA",
						variableValor);
				result.setTipoExcepcion(Constants.ERROR);
				result.setMensaje(mensaje);
				return result;
			}
			
			// GRABA LA SECUENCIA DE LA TAREA Y SU ESTADO DE COMPLETADA
			situacionTarea.add(SituacionTareaTO.builder()
					.secuenciaTarea(secuenciaTarea)
					.completada(completada)
					.build());
			
			//INICIALIZA LA VARIABLE DE INICIO DE LA SECUENCIA DE LA TAREA
			posicionBusqueda = posicionFinal + 2;
			if(posicionBusqueda > longitudCadena) {
				finBusqueda = true;
			}
		}
		
		log.info("----> SITUACION TAREA: " + situacionTarea.size());
				
		return result;
	}

	// SP_GUARDA_TAREAS_NODO
	@Override
	public RetMsg guardaTareasNodo(DatosAutenticacionTO session, NodoTO nodo,
			String secuenciaCompletada) throws BrioBPMException {
		
		log.trace("\t\t\t\t REQUEST  SP_GUARDA_TAREAS_NODO '{}', '{}', '{}', '{}', '{}', '{}', '{}'," +
				" '{}', '{}', '{}', '{}', '{}', '{}', {}  ",
				session.getCveUsuario(), session.getCveEntidad(), session.getCveLocalidad(), session.getCveIdioma(),
				nodo.getCveProceso(), nodo.getVersion(), nodo.getCveInstancia(),
				nodo.getCveNodo(), nodo.getIdNodo(), nodo.getSecuenciaNodo(), secuenciaCompletada);
		
		String completada;
		String idProceso;
		String mensaje;
		Integer secuenciaTarea;
		String variableValor;
		
		// INICIALIZA CÓDIGO DE ERROR, MENSAJE Y SUGERENCIA
		RetMsg msg = RetMsg.builder().status("OK").message("").build();

		// Define el identificador del proceso
		idProceso = Constants.GUARDA_TAREAS_NODO;
		
		// CONCATENA LA LISTA DE LOS PARÁMETROS PARA DESPLEGARLOS EN CASO DE ERROR
		variableValor = Constants.CVE_ENTIDAD + session.getCveEntidad() + "|" +
				Constants.CVE_LOCALIDAD + session.getCveLocalidad() + "|" +
				Constants.CVE_PROCESO + nodo.getCveProceso() + "|" +
				Constants.VERSION + nodo.getVersion() + "|" + 
				Constants.CVE_INSTANCIA + nodo.getCveInstancia() + "|" +
				Constants.CVE_NODO + nodo.getCveNodo() + "|" +
				Constants.ID_NODO + nodo.getIdNodo() + "|" +
				Constants.SECUENCIA_NODO + nodo.getSecuenciaNodo();
		
		// CREA UNA TABLA TEMPORAL PARA ALMACENAR LA SITUACIÓN DE LAS TAREAS
		List<SituacionTareaTO> situacionTarea = new ArrayList<SituacionTareaTO>();
		
		// OBTIENE LAS TAREAS Y SUS SITUACIONES PARA GRABARLAS EN UNA TABLA TEMPORAL
		EstatusTO result = extraeTareasCadena(session, nodo, secuenciaCompletada, situacionTarea);
		log.info("----> SITUACION TAREA: " + situacionTarea.size());		

	    // Maneja el caso en que hay un error en la extracción de tareas
		if(result.getTipoExcepcion().equals(Constants.ERROR)) {
			msg.setStatus(Constants.ERROR);
			msg.setMessage(result.getMensaje());
			return msg;
		}
		 
		// VALIDA QUE EXISTA INFORMACIÓN EN LA TABLA TEMPORAL
		if(situacionTarea.size() <= 0) {
			// Si no existen tareas, se establece un mensaje de error
			mensaje = messagesService.getMessage(
					session,
					idProceso,
					Constants.NO_EXISTEN_TAREAS,
					variableValor);
			msg.setStatus(Constants.ERROR);
			msg.setMessage(mensaje);
			return msg;
		}
		
		// DECLARA EL CURSOR QUE CONTIENE LA LISTA DE VARIABLES A ACTUALIZAR
		 // PROCESA CADA TAREA EN LA TABLA TEMPORAL
		for(SituacionTareaTO estado : situacionTarea) {
			secuenciaTarea = estado.getSecuenciaTarea();
			completada = estado.getCompletada();
			
			// Construye el identificador primario para la tarea
			InTareaProcesoPK id = InTareaProcesoPK.builder()
					.cveEntidad(session.getCveEntidad())
					.cveProceso(nodo.getCveProceso())
					.version(nodo.getVersion())
					.cveInstancia(nodo.getCveInstancia())
					.secuenciaTarea(secuenciaTarea)
					.build();
			
	        // Busca la tarea en el repositorio usando el identificador
			Optional<InTareaProceso> inTareaProceso = inTareaProcesoRepository.findById(id);
			
			// Verifica si la tarea está presente
			if(inTareaProceso.isPresent()) {
				String completadaOriginal = inTareaProceso.get().getCompletada();
				
				// Actualiza el estado de la tarea
				inTareaProceso.get().setCompletada(completada);
				
				log.info("guardaTareasNodo");

				inTareaProcesoRepository.saveAndFlush(inTareaProceso.get());
				
				//confirma que existe el cambio, de lo contrario retorna el valor anterior
				if(!inTareaProceso.get().getCompletada().equals(completada)) {
					
					// Si el cambio no se guardó, revierte el cambio y reporta un error
					inTareaProceso.get().setCompletada(completadaOriginal);
					
					log.info("guardaTareasNodo 2");
					
					inTareaProcesoRepository.saveAndFlush(inTareaProceso.get());
					variableValor = variableValor + "|" + Constants.SECUENCIA_TAREA + 
							secuenciaTarea.toString() + "|" + Constants.COMPLETADA + completada;
					// ERROR
					mensaje = messagesService.getMessage(
							session,
							idProceso,
							Constants.ERR_UPD_TAREA_NODO,
							variableValor);
					msg.setStatus(Constants.ERROR);
					msg.setMessage(mensaje);
					return msg;
				}
			}
		}

	    // Retorna el mensaje de éxito si todo se procesó correctamente
		return msg;
	}


	// SP_GUARDA_DOCUMENTO_BINARIO_NODO
	@Override
	public RetMsg guardaDocumentoBinarioNodo(DatosAutenticacionTO session, DocumentoTO documento) throws BrioBPMException {

		String idProceso;
		String mensaje;
		String variableValor;
		
		byte[] archivoBinario = documento.getData();
		String contenType = documento.getContentType();
		String cveInstancia = documento.getCveInstancia();
		String cveProceso = documento.getCveProceso();
		String cveNodo = documento.getCveNodo();
		String nombreArchivo = documento.getNomArchivo();
		Integer idNodo = documento.getIdNodo();
		Integer secuenciaNodo = documento.getSecNodo();
		Integer secuenciaDocumento = documento.getSecDocumento();
		BigDecimal version = new BigDecimal(documento.getVersion());
		
		// INICIALIZA CÓDIGO DE ERROR, MENSAJE Y SUGERENCIA
		RetMsg msg = RetMsg.builder().status("OK").message("").build();

		//CONCATENA LA LISTA DE LOS PARÁMETROS PARA DESPLEGARLOS EN CASO DE ERROR
		variableValor = Constants.CVE_PROCESO + cveProceso + "|" +
				Constants.VERSION + version + "|" +
				Constants.CVE_INSTANCIA + cveInstancia + "|" +
				Constants.CVE_NODO + cveNodo + "|" +
				Constants.ID_NODO + idNodo + "|" +
				Constants.SECUENCIA_NODO + secuenciaNodo + "|" +
				Constants.SECUENCIA_DOCUMENTO + secuenciaDocumento + "|" +
				Constants.NOMBRE_ARCHIVO + nombreArchivo;
		
		idProceso = Constants.GUARDA_DOCUMENTO_BINARIO_NODO;
		
		InDocumentoProcesoPK id = InDocumentoProcesoPK.builder()
				.cveEntidad(session.getCveEntidad())
				.cveProceso(cveProceso)
				.version(version)
				.cveInstancia(cveInstancia)
				.secuenciaDocumento(secuenciaDocumento)
				.build();
		Optional<InDocumentoProceso> inDocumentoProceso = iInDocumentoProcesoRepository.findById(id);
		if(!inDocumentoProceso.isPresent()) {
			//ERROR
			mensaje = messagesService.getMessage(
					session,
					idProceso,
					Constants.ERR_LEE_DOCUMENTO_BINARIO_PROCESO,
					variableValor);
			msg.setStatus(Constants.ERROR);
			msg.setMessage(mensaje);
			return msg;
		}
		
		// update
		InDocumentoProceso documentoProceso = inDocumentoProceso.get();
		documentoProceso.setNombreArchivo(nombreArchivo);
		documentoProceso.setArchivoBinario(archivoBinario);
		documentoProceso.setContentType(contenType);
		iInDocumentoProcesoRepository.saveAndFlush(documentoProceso);
		Optional<InDocumentoProceso> documentoEditado = iInDocumentoProcesoRepository.findById(id);
		
		// verifica si guardo cambios 
		if(!documentoEditado.isPresent() && !documentoEditado.get().getNombreArchivo().equals(nombreArchivo) &&
				!documentoEditado.get().getArchivoBinario().equals(archivoBinario) && !documentoEditado.get().getContentType().equals(contenType)) {
			//ERROR
			mensaje = messagesService.getMessage(
					session,
					idProceso,
					Constants.ERR_UPD_TAREA_NODO,
					variableValor);
			msg.setStatus(Constants.ERROR);
			msg.setMessage(mensaje);
			return msg;
		}
		
		return msg;
		
	}


	// SP_VALIDA_VARIABLES_REQUERIDAS
	private EstatusTO validaVariablesRequeridas(DatosAutenticacionTO session, NodoTO nodo, List<StSeccionNodoTO> seccionNodo)
			throws BrioBPMException {
		
		String cveSeccion;
		String idProceso;
		String mensaje;
		String mensajeVariable;
		String variableValor;
		
		log.info("-----> validaVariablesRequeridas: " + nodo.toString() + " " + seccionNodo.size() + " " + seccionNodo.toString());
		
		// INICIALIZA CÓDIGO DE ERROR, MENSAJE Y SUGERENCIA
		EstatusTO result = EstatusTO.builder()
				.tipoExcepcion(Constants.OK)
				.mensaje("")
				.build();
		idProceso = Constants.VALIDA_VARIABLES_REQUERIDAS;
		
		// CONCATENA LA LISTA DE LOS PARÁMETROS PARA DESPLEGARLOS EN CASO DE ERROR
		variableValor = Constants.CVE_PROCESO + "|" + nodo.getCveProceso() + "|" +
				Constants.VERSION + "|" + nodo.getVersion() + "|" + 
				Constants.CVE_INSTANCIA + "|" + nodo.getCveInstancia() + "|" +
				Constants.CVE_NODO + "|" + nodo.getCveNodo() + "|" +
				Constants.ID_NODO + "|" + nodo.getIdNodo() + "|" +
				Constants.SECUENCIA_NODO + "|" + nodo.getSecuenciaNodo();
		
		// CURSOR PARA LEER LAS SECCIONES DEL NODO QUE CONTIENEN VARIABLES
		List<StSeccionNodoTO> cursorSeccion = seccionNodo.stream()
                .filter(nodo1 -> "VARIABLES".equals(nodo1.getContenido()))
                .sorted((n1, n2) -> n1.getOrden().compareTo(n2.getOrden()))
                .collect(Collectors.toList());
		
		// TABLA QUE CONTIENE LAS VARIABLES DE LAS SECCIONES DEL NODO
		//List<VariableSeccionTO> variableSeccion = new ArrayList<VariableSeccionTO>();
		
		// RECORRE LAS SECCIONES PARA BUSCAR LAS VARIABLES DE CADA UNA DE ELLAS
		// Y VALIDAR QUE LOS DATOS DE CAPTURA ESTÉN COMPLETADOS
		
		// RECUPERA LAS VARIABLES DE UNA SECCION
		List<String> cveSecciones = new ArrayList<String>();
		
		for(StSeccionNodoTO seccion : cursorSeccion) {
			cveSeccion = seccion.getCveSeccion();
			log.info("----->  cveSeccion: {}", cveSeccion);
			cveSecciones.add(cveSeccion);
		}
		
	    // RECUPERA LAS VARIABLES DE UNA SECCIÓN

        List<String> etiquetas = inVariableProcesoRepository.findVariableSeccionRecordsRequeridos(
	                session.getCveEntidad(), nodo.getCveProceso(), nodo.getVersion(), 
	                nodo.getCveInstancia(), nodo.getCveNodo(), nodo.getIdNodo(), cveSecciones);
        
        if (etiquetas != null || etiquetas.size() > 0) {
        	
        	mensajeVariable = " ";
        	for (String etiqueta : etiquetas) {
        		if (mensajeVariable.equals(" ")) {
        			mensajeVariable = etiqueta;
        		} else {
        			mensajeVariable = mensajeVariable + ", " + etiqueta;
        		}
			}
        	
        	
    		if(!mensajeVariable.equals(" ")) {
    			variableValor = "@" + Constants.MENSAJE_VARIABLES + "@|" + mensajeVariable;
    			// ERROR
    			mensaje = messagesService.getMessage(
    					session,
    					idProceso,
    					Constants.VARIABLES_REQUERIDAS_SIN_VALOR,
    					variableValor);
    			result.setTipoExcepcion(Constants.ERROR);
    			result.setMensaje(mensaje);
    			return result;
    		}
        }
			
		return result;
	}


	// SP_TERMINA_ACTIVIDAD
	@Override
	public RetMsg terminaActividad(DatosAutenticacionTO session, NodoTO nodo)
			throws BrioBPMException, ParseException {
	
		String estadoActividad;
		String idProceso;
		String mensaje;
		String tipoNodoSiguiente;
		String variableValor;
		
		log.info("--------------> terminaActividad +");
		log.info("nodo:  " + nodo.toString());
		
		// INICIALIZA CÓDIGO DE ERROR, MENSAJE Y SUGERENCIA
		RetMsg msg = RetMsg.builder().status(Constants.OK).message("").build();

		// Asigna el identificador de proceso para la operación de terminar actividad
		idProceso = Constants.TERMINA_ACTIVIDAD;
		
		// CONCATENA LA LISTA DE LOS PARÁMETROS PARA DESPLEGARLOS EN CASO DE ERROR
		variableValor = Constants.CVE_PROCESO + nodo.getCveProceso() + "|" +
				Constants.VERSION + nodo.getVersion() + "|" + 
				Constants.CVE_INSTANCIA + nodo.getCveInstancia() + "|" +
				Constants.CVE_NODO + nodo.getCveNodo() + "|" +
				Constants.ID_NODO + nodo.getIdNodo() + "|" +
				Constants.SECUENCIA_NODO + nodo.getSecuenciaNodo();
		
		// VALIDA QUE LA SITUACIÓN DE LA ACTIVIDAD SEA 'REGISTRO'
		estadoActividad = " ";
		
		// Crea una clave primaria compuesta para buscar la entidad InNodoProceso
		InNodoProcesoPK idInNodoProceso = InNodoProcesoPK.builder()
				.cveEntidad(session.getCveEntidad())
				.cveInstancia(nodo.getCveInstancia())
				.cveProceso(nodo.getCveProceso())
				.version(nodo.getVersion())
				.cveNodo(nodo.getCveNodo())
				.idNodo(nodo.getIdNodo())
				.secuenciaNodo(nodo.getSecuenciaNodo())
				.build();

		 // Busca el InNodoProceso correspondiente en el repositorio
		//log.info("------>>* BUSCA POR LLAVE EN InNodoProceso ");
	    Optional<InNodoProceso> entidadInProceso = inNodoProcesoRepository.findById(idInNodoProceso);
	    if (entidadInProceso.isPresent()) {
	        // Si se encuentra, obtiene el estado de la actividad
			estadoActividad = entidadInProceso.get().getEstado();
			//log.info("------>>* SE ENCONTRO UN ESTADO ACTIVIDAD: " + estadoActividad);
		}
		
		estadoActividad = estadoActividad != null ? estadoActividad : " ";
		
		log.info("------>>* Estado actividad: " + estadoActividad);
		
	    // Verifica que el estado de la actividad sea 'REGISTRO', de lo contrario lanza error VENCIDA_POR_TIEMPO
		// SI LA SITUACIÓN NO ES 'REGISTRO' SE TRATA DE UN ERROR
		if(!(Constants.REGISTRO.equals(estadoActividad) || Constants.VENCIDA_POR_TIEMPO.equals(estadoActividad))) {
			
			//log.info("--------------ESTADO_INVALIDO_PARA_TERMINAR---------------");
			
			// ERROR
			mensaje = messagesService.getMessage(
					session,
					idProceso,
					Constants.ESTADO_INVALIDO_PARA_TERMINAR,
					variableValor);
			msg.setStatus(Constants.ERROR);
			msg.setMessage(mensaje);
			return msg;
		}
		
		// CREA TABLA TEMPORAL DE LAS SECCIONES DEL NODO
		List<StSeccionNodoTO> seccionNodo = new ArrayList<StSeccionNodoTO>();
				
		// EJECUTA PROCESO QUE RECUPERA LAS SECCIONES DE UN NODO
		if(!Constants.TEMPORIZADOR.equals(nodo.getCveNodo())) {
			
			//log.info("Antes de la llamada: " + seccionNodo.size());
			DAORet<List<StSeccionNodoTO>, RetMsg> leeSeccionNodo = leeSeccionesNodo(session, nodo, Constants.SI, seccionNodo);
			//log.info("Después de la llamada: " + seccionNodo.size());
			
			if(Constants.ERROR.equals(leeSeccionNodo.getMeta().getStatus())) {
				// Si ocurre un error al recuperar las secciones, retorna con error
				msg.setStatus(Constants.ERROR);
				msg.setMessage(leeSeccionNodo.getMeta().getMessage());
				return msg;
			}
			
		}
		
		// VALIDA LAS VARIABLES REQUERIDAS DE LA ACTIVIDAD
		// Valida que las variables requeridas para la actividad estén presentes
		EstatusTO result = validaVariablesRequeridas(session, nodo, seccionNodo);
		if(Constants.ERROR.equals(result.getTipoExcepcion())) {
			msg.setStatus(Constants.ERROR);
			msg.setMessage(result.getMensaje());
			return msg;
		}
		
		// VALIDA LAS TAREAS REQUERIDAS NO COMPLETADAS
		result = validaTareasRequerida(session, nodo, seccionNodo);
		if(Constants.ERROR.equals(result.getTipoExcepcion())) {
			msg.setStatus(Constants.ERROR);
			msg.setMessage(result.getMensaje());
			return msg;
		}
		
		// VALIDA QUE LOS DOCUMENTOS REQUERIDOS HAYAN SIDO CARGADOS
		result = validaDocumentoRequetidos(session, nodo, seccionNodo);
		if(Constants.ERROR.equals(result.getTipoExcepcion())) {
			msg.setStatus(Constants.ERROR);
			msg.setMessage(result.getMensaje());
			return msg;
		}
		
		// ACTUALIZA LA ACTIVIDAD
		// BUSCA EL ROL DEL USUARIO
		List<String> cveRoles = iUsuarioRolRepository.buscarRoles(session.getCveEntidad(),
												session.getCveUsuario(),
												nodo.getCveProceso(),
												nodo.getVersion());
		
		int sizeRoles = cveRoles != null ? cveRoles.size() : 0;
		int i = 0;
		do {
			String cveRol = cveRoles != null && !cveRoles.isEmpty() ? cveRoles.get(i) : " ";
			// Asegura que el rol no sea nulo, si lo es, asigna un espacio vacío
			// cveRol = cveRol != null ? cveRol : " ";
			
			// CAMBIA LA SITUACIÓN DEL NODO ACTUAL
			if(entidadInProceso.isPresent()) {
				entidadInProceso.get().setEstado(Constants.TERMINADA);
				entidadInProceso.get().setFechaBloquea(null);
				entidadInProceso.get().setUsuarioBloquea(null);
				entidadInProceso.get().setRolBloquea(null);
				entidadInProceso.get().setFechaEstadoActual(new Date());
			}
			
			// ASIGNA EL TIPO DE NODO SIGUIENTE "NORMAL"
			tipoNodoSiguiente = Constants.NORMAL;
			
			// SELECT 'FRA 02'
			// CREA LOS NODOS SIGUIENTES
			 // Crea los nodos siguientes utilizando el constructor de NodoTO
	        NodoTO nodoSiguiente = NodoTO.builder()
	                .cveProceso(nodo.getCveProceso())
	                .version(nodo.getVersion())
	                .cveInstancia(nodo.getCveInstancia())
	                .cveNodo(nodo.getCveNodo())
	                .idNodo(nodo.getIdNodo())
	                .secuenciaNodo(nodo.getSecuenciaNodo())
	                .tipoGeneracion("SIG") // Indica que el nodo siguiente es generado automáticamente
	                .tipoNodoSiguiente(tipoNodoSiguiente) // Establece el tipo del nodo siguiente
	                .cveNodoInicio(nodo.getCveNodo()) // Nodo de inicio para el siguiente nodo
	                .idNodoInicio(nodo.getIdNodo()) // Identificador del nodo de inicio
	                .rol(cveRol) // Rol del usuario asignado al nodo siguiente
	                .folioMensajeEnvio(null) // Sin folio de mensaje de envío
	            	.origen(nodo.getOrigen())//AR Se ajusta parametro para no crear error al crear una instancia 
	                .build();
	        
	        // Crea una nueva instancia del nodo siguiente
			result = creaInstancia(session, nodoSiguiente);
			if(Constants.ERROR.equals(result.getTipoExcepcion())) {
				msg.setStatus(Constants.ERROR);
				msg.setMessage(result.getMensaje());
				return msg;
			}
			i++;
		} while(i < sizeRoles);

		
		//APLICA EL AJUSTE DE LAS VARIABLES POR CONDICIONES	
		//SVM ajuse de una mejora
		/*RetMsg reglas =  evaluaReglasProcesoTerminar(session, nodo, "TERMINAR");
		if (reglas.getStatus().equals(Constants.ERROR)) {
			return reglas;
		}
		
		// GUARDANDO ENTRADA DE LA BITÁCORA
		result = generaEventoBitacora(session, nodo, "Terminar");
		if(!Constants.OK.equals(result.getTipoExcepcion())) {
			msg.setStatus(Constants.ERROR);
			msg.setMessage(result.getMensaje());
			return msg;
		}*/
		
		/*ACTUALIZA PROGRAMACION PROCESO*/
		if ("FACT_SERV_CONT".equals(nodo.getCveProceso())){
			//log.info("-----> ACTUALIZA PROGRAMACION PROCESO");
			result = actualizaProgramacionProceso(session, nodo);
			if(!Constants.OK.equals(result.getTipoExcepcion())) {
				msg.setStatus(Constants.ERROR);
				msg.setMessage(result.getMensaje());
				return msg;
			}
		}
		// Retorna el mensaje de resultado con estado y mensaje actualizado
		return msg;
	}

	private EstatusTO actualizaProgramacionProceso(DatosAutenticacionTO session, NodoTO nodo) {
		EstatusTO estatus = EstatusTO.builder()
				.tipoExcepcion(Constants.OK)
				.mensaje("")
				.build();	
		List<InVariableProceso> inContrato = inVariableProcesoRepository.obtenerVariableProceso(session.getCveEntidad(), nodo.getCveProceso() , nodo.getVersion(),
				nodo.getCveInstancia(),"VPRO_01_NUM_CONTRATO",nodo.getOcurrencia() == null ? 1 : nodo.getOcurrencia());
	
		List<Object> inRFC = inVariableProcesoRepository.RfcByContrato(session.getCveEntidad(), inContrato.get(0).getValorAlfanumerico());
		Object[] datos = (Object[]) inRFC.get(0);
		String rfc = (String) datos[2];
		// InVariableProceso rfc = inRFC.get(0) != null ? (InVariableProceso) inRFC.get(0) : null;	

		
		List<CrProgramacionProceso> programacionProceso =
		 crProgramacionProcesoRepository.obtenerInfoContratoRfcProcesoFactura(session.getCveEntidad(),session.getCveLocalidad(),
				session.getCveIdioma(), rfc, inContrato.get(0).getValorAlfanumerico());
		
		for(CrProgramacionProceso proceso : programacionProceso) {
			int tiene = inVariableProcesoRepository.cuentaDocsCargados( session.getCveEntidad(),  nodo.getCveProceso(), 
					nodo.getCveInstancia() ,proceso.getCrProcesoPeriodico().getId().getCveProcesoPeriodico());
		    if (tiene > 0) {
		    	proceso.setSituacionEjecucion("EJECUTADO");
		    	crProgramacionProcesoRepository.saveAndFlush(proceso);
		    }
		}
		 
	
		return estatus;
	}


	// SP_LEE_TAREAS_NODO
	@Override
	public DAORet<List<TareaNodoTO>, EstatusTO> leeTareasNodo(DatosAutenticacionTO session, NodoTO nodo, String cveSeccion,
			String generaTablaTemporal, List<TareaNodoTO> tareaNodo) throws BrioBPMException {

		String idProceso;
		String variableValor = null;
		String mensaje;
		//String mensajeVariable = null;
		
		// INICIALIZA CÓDIGO DE ERROR, MENSAJE Y SUGERENCIA
		EstatusTO estatus = EstatusTO.builder()
				.tipoExcepcion(Constants.OK)
				.mensaje("")
				.build();
		List<TareaNodoTO> result = new ArrayList<TareaNodoTO>();
		
		idProceso = Constants.LEE_TAREAS_NODO;
		
		// CONCATENA LA LISTA DE LOS PARÁMETROS PARA DESPLEGARLOS EN CASO DE ERROR
		variableValor = Constants.CVE_PROCESO + nodo.getCveProceso() + "|" +
				Constants.VERSION + nodo.getVersion() + "|" + 
				Constants.CVE_INSTANCIA + nodo.getCveInstancia() + "|" +
				Constants.CVE_NODO + nodo.getCveNodo() + "|" +
				Constants.ID_NODO + nodo.getIdNodo() + "|" +
				Constants.SECUENCIA_NODO + nodo.getSecuenciaNodo();
		
		// RECUPERA LAS TAREAS DE UN NODO Y SU SITUACIÓN
		if(Constants.NO.equals(generaTablaTemporal)) {
			List<Object[]> tareas = iStTareaSeccionRepository.obtenerDetallesTareas(session.getCveEntidad(), nodo.getCveProceso(),
					nodo.getVersion(), nodo.getCveNodo(), nodo.getIdNodo(), cveSeccion, nodo.getCveInstancia());
			
			if(tareas == null) {
				// ERROR
				mensaje = messagesService.getMessage(
						session,
						idProceso,
						Constants.ERR_LEE_TAREAS_SECCION,
						variableValor);
				estatus.setTipoExcepcion(Constants.ERROR);
				estatus.setMensaje(mensaje);
				return new DAORet<List<TareaNodoTO>, EstatusTO>(null, estatus);
			}
			
			for(Object[] tarea : tareas) {
							
				TareaNodoTO tareaNodoTO = TareaNodoTO.builder()
						.secuenciaTarea(tarea[0] != null ? ((BigDecimal) tarea[0]).intValue() : 0)
						.descripcionTarea((String)tarea[1])
						.requerida((String)tarea[2])
						.copletada((String)tarea[3])
						.build();
				
				result.add(tareaNodoTO);
				}
			
			
		
		} else {
			List<Object[]> tareas = iStTareaSeccionRepository.obtenerDetallesTareas(session.getCveEntidad(), nodo.getCveProceso(),
					nodo.getVersion(), nodo.getCveNodo(), nodo.getIdNodo(), cveSeccion, nodo.getCveInstancia());
			for(Object[] tarea : tareas) {
				
				TareaNodoTO tareaNodoTO = TareaNodoTO.builder()
						.secuenciaTarea(tarea[0] != null ? ((BigDecimal) tarea[0]).intValue() : 0)
						.descripcionTarea((String)tarea[1])
						.requerida((String)tarea[2])
						.copletada((String)tarea[3])
						.build();
				
				tareaNodo.add(tareaNodoTO);
				
				 // Verificar que se guardó en la lista
		        if (!tareaNodo.contains(tareaNodoTO)) {
		        	// ERROR
					mensaje = messagesService.getMessage(
							session,
							idProceso,
							Constants.ERR_INS_TAREAS_SECCION,
							variableValor);
					
					estatus.setTipoExcepcion(Constants.ERROR);
					estatus.setMensaje(mensaje);
					return new DAORet<List<TareaNodoTO>, EstatusTO>(null, estatus);
		        }
			}
		}
		
		
		return new DAORet<List<TareaNodoTO>, EstatusTO>(result, estatus);

	}

	// SP_LEE_DOCUMENTO_BINARIO_NODO
	@Override
	public Documento leeDocumentoBinarioNodo(DatosAutenticacionTO session, NodoTO nodo,
	        Integer secuenciaDocumento) throws BrioBPMException {

	    // Variables locales que almacenan información clave para el procesamiento del documento
	    String cveEntidad = session.getCveEntidad();
	    String cveInstancia = nodo.getCveInstancia();
	    String cveProceso = nodo.getCveProceso();
	    BigDecimal version = nodo.getVersion();

	    // Variables para almacenar el contenido del archivo y sus metadatos
	    byte[] archivoBinario;
	    Blob archivoBinarioBlob = null;
	    String contentType;
	    String idProceso;
	    boolean existeDocumento;
	    String mensaje;
	    String nombreArchivo;
	    String variableValor;

	    // INICIALIZA CÓDIGO DE ERROR, MENSAJE Y SUGERENCIA
	    // Se crea un objeto Documento para devolver el resultado del proceso
	    Documento result = Documento.builder()
	            .status(Constants.OK) // Inicializa el estado como OK
	            .message("") // Inicializa el mensaje como vacío
	            .build();
	    idProceso = Constants.LEE_DOCUMENTO_BINARIO_NODO;
	    existeDocumento = false; // Inicializa el indicador de existencia del documento como falso

	    // CONCATENA LA LISTA DE LOS PARÁMETROS PARA DESPLEGARLOS EN CASO DE ERROR
	    // Se construye una cadena que representa la combinación de parámetros clave
	    variableValor = Constants.CVE_PROCESO + nodo.getCveProceso() + "|" +
	            Constants.VERSION + nodo.getVersion() + "|" +
	            Constants.CVE_INSTANCIA + nodo.getCveInstancia() + "|" +
	            Constants.CVE_NODO + nodo.getCveNodo() + "|" +
	            Constants.ID_NODO + nodo.getIdNodo() + "|" +
	            Constants.SECUENCIA_NODO + nodo.getSecuenciaNodo() + "|" +
	            Constants.SECUENCIA_DOCUMENTO + secuenciaDocumento;

	    // Inicializa las variables de contenido del documento
	    archivoBinario = null;
	    nombreArchivo = null;
	    contentType = null;

	    // Busca documento Proceso
	    // Se construye la clave primaria para buscar el documento en la base de datos
	    InDocumentoProcesoPK idDocumentoProceso = InDocumentoProcesoPK.builder()
	            .cveEntidad(cveEntidad)
	            .cveProceso(cveProceso)
	            .version(version)
	            .cveInstancia(cveInstancia)
	            .secuenciaDocumento(secuenciaDocumento)
	            .build();

	    // Se busca el documento en el repositorio utilizando la clave primaria
	    Optional<InDocumentoProceso> inDocumentoProceso = iInDocumentoProcesoRepository.findById(idDocumentoProceso);

	    if (!inDocumentoProceso.isPresent()) {
	        // Maneja el caso en que el documento no existe
	        // Si el documento no está presente, se devuelve un mensaje de error
	        mensaje = messagesService.getMessage(session, idProceso, Constants.NO_EXISTE_DOCUMENTO_PARA_NODO, variableValor);
	        result.setStatus(Constants.ERROR);
	        result.setMessage(mensaje);
	        return result;
	    }

	    // Obtiene el documento si está presente
	    InDocumentoProceso documentoProceso = inDocumentoProceso.get();
	    if (documentoProceso == null || documentoProceso.getArchivoBinario() == null) {
	        // Maneja el caso en que el documento es nulo o no tiene contenido
	        // Si el documento es nulo o no tiene contenido, se devuelve un mensaje de error
	        mensaje = messagesService.getMessage(session, idProceso, Constants.NO_EXISTE_DOCUMENTO_PARA_NODO, variableValor);
	        result.setStatus(Constants.ERROR);
	        result.setMessage(mensaje);
	        return result;
	    }

	    // Si existe, obtiene sus datos
	    // Asigna los datos del documento a las variables correspondientes
	    archivoBinario = documentoProceso.getArchivoBinario();
	    nombreArchivo = documentoProceso.getNombreArchivo();
	    contentType = documentoProceso.getContentType();

	    try {
	        // Convierte el byte array a Blob para facilitar el manejo del contenido binario
	        archivoBinarioBlob = new SerialBlob(archivoBinario);
	    } catch (SQLException e) {
	        // Manejo de la excepción en caso de error al convertir
	        throw new BrioBPMException("Error al convertir byte[] a Blob", e);
	    }

	    // Asigna los valores al resultado
	    // Establece el contenido y metadatos en el objeto result
	    result.setContenido(archivoBinarioBlob);
	    result.setContentType(contentType);
	    result.setNombre(nombreArchivo);

	    // Devuelve el resultado final con los datos del documento
	    return result;
	}


	// SP_BORRA_DOCUMENTO_BINARIO_NODO
	@Override
	public RetMsg borraDocumentoBinarioNodo(DatosAutenticacionTO session, NodoTO nodo, 
			Integer secuenciaDocumento) throws BrioBPMException {
		
		String cveEntidad = session.getCveEntidad();
		String cveInstancia = nodo.getCveInstancia();
		String cveProceso = nodo.getCveProceso();
		BigDecimal version = nodo.getVersion();
		
		byte[] archivoBinario;
		String idProceso;
		boolean existeDocumento;
		String mensaje;
		String variableValor;
		
		// INICIALIZA CÓDIGO DE ERROR, MENSAJE Y SUGERENCIA
		RetMsg msg = RetMsg.builder().status("OK").message("").build();

		idProceso = Constants.BORRA_DOCUMENTO_BINARIO_NODO;
		existeDocumento = false;
		
		// CONCATENA LA LISTA DE LOS PARÁMETROS PARA DESPLEGARLOS EN CASO DE ERROR
		variableValor = Constants.CVE_PROCESO + cveProceso + "|" +
				Constants.VERSION + version + "|" + 
				Constants.CVE_INSTANCIA + cveInstancia + "|" +
				Constants.CVE_NODO + nodo.getCveNodo() + "|" +
				Constants.ID_NODO + nodo.getIdNodo() + "|" +
				Constants.SECUENCIA_NODO + nodo.getSecuenciaNodo() + "|" +
				Constants.SECUENCIA_DOCUMENTO + secuenciaDocumento;
		
		archivoBinario = null;
		
		// verifica si existe documento Proceso
		InDocumentoProcesoPK idDocumentoProceso = InDocumentoProcesoPK.builder()
				.cveEntidad(cveEntidad)
				.cveProceso(cveProceso)
				.version(version)
				.cveInstancia(cveInstancia)
				.secuenciaDocumento(secuenciaDocumento)
				.build();
		Optional<InDocumentoProceso> inDocumentoProceso = iInDocumentoProcesoRepository.findById(idDocumentoProceso);
		if(inDocumentoProceso.isPresent()) {
			
			archivoBinario = inDocumentoProceso.get().getArchivoBinario();
		
			if(archivoBinario == null) {
				existeDocumento = false;
			} else {
				existeDocumento = true;
			}
			
			if(!archivoBinario.equals(inDocumentoProceso.get().getArchivoBinario())) {
				
				// ERROR
				mensaje = messagesService.getMessage(
						session,
						idProceso,
						Constants.ERR_LEE_DOCUMENTO_BINARIO_PROCESO,
						variableValor);
				msg.setStatus(Constants.ERROR);
				msg.setMessage(mensaje);
				return msg;
			}
			
		} else {
			// ExisteD 
			existeDocumento = false;
		}
		if(!existeDocumento) {
			// ERROR
			mensaje = messagesService.getMessage(
					session,
					idProceso,
					Constants.NO_EXISTE_DOCUMENTO_PARA_NODO,
					variableValor);
			msg.setStatus(Constants.ERROR);
			msg.setMessage(mensaje);
			return msg;
			
		} else {
			
			// update
			inDocumentoProceso.get().setNombreArchivo(null);
			inDocumentoProceso.get().setArchivoBinario(null);
			
			if(inDocumentoProceso.get().getArchivoBinario() != null || inDocumentoProceso.get().getNombreArchivo() != null) {
				// ERROR
				mensaje = messagesService.getMessage(
						session,
						idProceso,
						Constants.ERROR_BORRADO_DOCUMENTO_NODO,
						variableValor);
				msg.setStatus(Constants.ERROR);
				msg.setMessage(mensaje);
				return msg;
			}
		}
		
		return msg;
	}

	// SP_VALIDA_TAREAS_REQUERIDAS
	@Override
	public EstatusTO validaTareasRequerida(DatosAutenticacionTO session, NodoTO nodo, List<StSeccionNodoTO> seccionNodo) throws BrioBPMException {
		String cveSeccion;
		String etiquetaSeccion;
		String idProceso;
		String mensaje;
		String mensajeTareas;
		Integer ordenSeccion;
		String variableValor;
		
		// INICIALIZA CÓDIGO DE ERROR, MENSAJE Y SUGERENCIA
		EstatusTO result = EstatusTO.builder()
				.tipoExcepcion(Constants.OK)
				.mensaje("")
				.build();
		idProceso = Constants.VALIDA_TAREAS_REQUERIDAS;
		
		// CONCATENA LA LISTA DE LOS PARÁMETROS PARA DESPLEGARLOS EN CASO DE ERROR
		variableValor = Constants.CVE_PROCESO + nodo.getCveProceso() + "|" +
				Constants.VERSION + nodo.getVersion() + "|" + 
				Constants.CVE_INSTANCIA + nodo.getCveInstancia() + "|" +
				Constants.CVE_NODO + nodo.getCveNodo() + "|" +
				Constants.ID_NODO + nodo.getIdNodo() + "|" +
				Constants.SECUENCIA_NODO + nodo.getSecuenciaNodo();
		
		// VALIDA QUE LAS TAREAS REQUERIDAS ESTÉN COMPLETADAS
		// CURSOR PARA LEER LAS SECCIONES DEL NODO QUE CONTIENEN TAREAS
		  // Filtrar, ordenar y mapear los resultados
        List<StSeccionNodoTO> seccionesTarea = seccionNodo.stream()
                .filter(seccion -> "TAREAS".equals(seccion.getContenido()))
                .sorted(Comparator.comparingInt(StSeccionNodoTO::getOrden))
                .collect(Collectors.toList());
        
		// CREA TABLA TEMPORAL PARA LAS TAREAS DEL NODO
		List<TareaNodoTO> tareaNodo = new ArrayList<TareaNodoTO>();
				
		// RECORRE LAS SECCIONES PARA BUSCAR LAS TAREAS DE CADA UNA DE ELLAS
		//	Y VALIDAR, EN CASO DE SER REQUERIDAS, QUE ESTÉN COMPLETADAS
		
		for(StSeccionNodoTO seccionTarea : seccionesTarea) {
			
			ordenSeccion = seccionTarea.getOrden();
			cveSeccion = seccionTarea.getCveSeccion();
			etiquetaSeccion = seccionTarea.getEtiquetaSeccion();
			
			// EJECUTA EL PROCESO QUE RECUPERA LAS TAREAS DEL NODO
			result = leeTareasNodo(session, nodo, cveSeccion, Constants.SI, tareaNodo).getMeta();
			if(Constants.ERROR.equals(result.getTipoExcepcion())) {
				return result;
			}
		}
		
		// CURSOR PARA LEER LAS TAREAS REQUERIDAS Y NO COMPLETADAS
		List<String> descripcionesTareas = tareaNodo.stream()
                .filter(t -> Constants.REQUERIDA.equals(t.getRequerida()) && Constants.NO.equals(t.getCopletada()))
                .sorted(Comparator.comparingInt(TareaNodoTO::getSecuenciaTarea))
                .map(TareaNodoTO::getDescripcionTarea)
                .collect(Collectors.toList());
		
		mensajeTareas = " ";
		// RECORRE TAREAS REQUERIDAS SIN VALOR ASIGNADO
		for(String descripcionTarea : descripcionesTareas) {
			if(" ".equals(mensajeTareas)) {
				mensajeTareas = descripcionTarea;
			} else {
				mensajeTareas = mensajeTareas + ", " + descripcionTarea;
			}
		}
		// SI HAY TAREAS REQUERIDAS NO COMPLETADAS, ENTONCES REGRESA EL MENSAJE CORRESPONDIENTE
		if(!" ".equals(mensajeTareas)) {
			// ERROR
			variableValor = variableValor + "|" + Constants.LISTA_TAREAS + mensajeTareas;
			mensaje = messagesService.getMessage(
					session,
					idProceso,
					Constants.TAREAS_REQUERIDAS_SIN_REALIZAR,
					variableValor);
			result.setTipoExcepcion(Constants.ERROR);
			result.setMensaje(mensaje);
			return result;
		}
		return result;
	}

	// SP_VALIDA_DOCUMENTO_REQUERIDOS
	@Override
	public EstatusTO validaDocumentoRequetidos(DatosAutenticacionTO session, NodoTO nodo, List<StSeccionNodoTO> seccionNodo) throws BrioBPMException {
		log.info("---------------validaDocumentoRequetidos---------------------");
		//log.info("-----sesion: " + session.toString());
		//log.info("-----nodo: " + nodo.toString());
		////log.info("-----seccionNodo: " + seccionNodo.toString());
		
		String cveSeccion;
		String etiquetaSeccion;
		String idProceso;
		String mensaje;
		String mensajeDocumentos;
		Integer ordenSeccion;
		String variableValor;
		
		// INICIALIZA CÓDIGO DE ERROR, MENSAJE Y SUGERENCIA
		EstatusTO result = EstatusTO.builder()
				.tipoExcepcion(Constants.OK)
				.mensaje("")
				.build();
		idProceso = Constants.VALIDA_DOCUMENTO_REQUERIDOS;
		
		// CONCATENA LA LISTA DE LOS PARÁMETROS PARA DESPLEGARLOS EN CASO DE ERROR
		variableValor = Constants.CVE_PROCESO + nodo.getCveProceso() + "|" +
				Constants.VERSION + nodo.getVersion() + "|" + 
				Constants.CVE_INSTANCIA + nodo.getCveInstancia() + "|" +
				Constants.CVE_NODO + nodo.getCveNodo() + "|" +
				Constants.ID_NODO + nodo.getIdNodo() + "|" +
				Constants.SECUENCIA_NODO + nodo.getSecuenciaNodo();
		
		// VALIDA QUE LOS DOCUMENTOS REQUERIDOS ESTÉN CARGADOS
		// CURSOR PARA LEER LAS SECCIONES DEL NODO QUE CONTIENEN DOCUMENTOS
		// Filtrar, ordenar y mapear los resultados
        List<StSeccionNodoTO> seccionesDocumentos = seccionNodo.stream()
                .filter(seccion -> Constants.DOCUMENTOS.equals(seccion.getContenido()))
                .sorted(Comparator.comparingInt(StSeccionNodoTO::getOrden))
                .collect(Collectors.toList());
		
		// CREA TABLA TEMPORAL PARA LOS DOCUMENTOS DEL NODO
		List<DocumentoNodoTO> documentoNodo = new ArrayList<DocumentoNodoTO>();
		
		// RECORRE LAS SECCIONES PARA BUSCAR LOS DOCUMENTOS DE CADA UNA DE ELLAS
		//	Y VALIDAR QUE LOS DOCUMENTOS REQUERIDOS HAYAN SIDO CARGADOS
		// RECORRE SECCIONES
		for(StSeccionNodoTO seccion : seccionesDocumentos) {
			ordenSeccion = seccion.getOrden();
			cveSeccion = seccion.getCveSeccion();
			etiquetaSeccion = seccion.getEtiquetaSeccion();
			// EJECUTA EL PROCESO QUE RECUPERA LOS DOCUMENTOS DEL NODO
			DAORet<List<DocumentoNodoTO>, RetMsg> leeDocNodo = leeDocumentosNodo(session, nodo, cveSeccion, Constants.SI, documentoNodo);
			if(Constants.ERROR.equals(leeDocNodo.getMeta().getStatus())) {
				result.setTipoExcepcion(Constants.ERROR);
				result.setMensaje(leeDocNodo.getMeta().getMessage());
				return result;
			}
		}
		
		//log.info("----->> documentoNodo {} : {}" + documentoNodo.size(), documentoNodo.toString());

		// CURSOR PARA LEER LOS DOCUMENTOS REQUERIDOS Y NO CARGADOS
		// Filtrar, ordenar y mapear los resultados
		List<String> documentos = documentoNodo.stream()
		    .filter(documento -> {
		        String nombreArchivo = documento.getNombreArchivo();
		        // log.info("----->> nombreArchivo: '" + nombreArchivo + "'");

		        // Verificar si nombreArchivo es realmente null o vacío
		        boolean esNombreVacio = nombreArchivo == null || nombreArchivo.trim().isEmpty();
		        
		        // Depuración extra
		        /*if (nombreArchivo == null) {
		            log.info("nombreArchivo es null");
		        } else if (nombreArchivo.trim().isEmpty()) {
		        	log.info("nombreArchivo está vacío (con o sin espacios)");
					} else if ("null".equals(nombreArchivo)) {
						log.info("nombreArchivo es 'null' (como cadena)");
					} else {
						log.info("nombreArchivo tiene contenido: '" + nombreArchivo + "'");
					}*/

		        log.info("----->> esNombreVacio: " + esNombreVacio);

		        boolean esRequerido = Constants.REQUERIDA.equals(documento.getRequerido());
		        //log.info("----->> esRequerido: " + esRequerido);

		        // Depuración: Imprimir valores para ver qué está pasando
		        System.out.println("Documento: " + documento.getDescripcionDocumento() +
		                           " | Requerido: " + documento.getRequerido() +
		                           " | Nombre Archivo: '" + nombreArchivo + "'" +
		                           " | Filtra: " + (esRequerido && esNombreVacio) +
		                           " | Secuencia: " + documento.getSecuencia());

		        // Filtrar solo los documentos requeridos y con nombre de archivo vacío o nulo
		        return esRequerido && esNombreVacio;
		    })
		    //.sorted(Comparator.comparingInt(DocumentoNodoTO::getSecuencia))
		    .map(DocumentoNodoTO::getDescripcionDocumento)
		    .collect(Collectors.toList());

		//log.info("-----documentos: " + documentos.size());

        
		// RECORRE DOCUMENTOS REQUERIDOS SIN NOMBRE DE ARCHIVO
        mensajeDocumentos = " ";
		for(String descripcionDocumento : documentos) {
			
			//log.info("----->> descripcionDocumento: " + descripcionDocumento);
			
			if(" ".equals(mensajeDocumentos)) {
				mensajeDocumentos = descripcionDocumento;
			} else {
				mensajeDocumentos = mensajeDocumentos + ", " + descripcionDocumento;
			}
		}
		
		
		// SI HAY DOCUMENTOS REQUERIDOS NO COMPLETADOS, ENTONCES REGRESA EL MENSAJE CORRESPONDIENTE
		if(!" ".equals(mensajeDocumentos)) {
			
			//log.info("----->> mensajeDocumentos: " + mensajeDocumentos);
			// ERROR
			variableValor = variableValor + "|" + Constants.LISTA_DOCUMENTOS + "|" + mensajeDocumentos;
			//log.info("----->> variableValor: " + variableValor);
			//log.info("----->> idProceso: " + idProceso);
			mensaje = messagesService.getMessage(
					session,
					idProceso,
					Constants.DOCUMENTOS_REQUERIDOS_SIN_ANEXAR,
					variableValor);
			log.info("----->> mensaje: " + mensaje);
			result.setTipoExcepcion(Constants.ERROR);
			result.setMensaje(mensaje);
			return result;
		}
		
		return result;
	}
	
	
	// SP_LEE_DOCUMENTO_BINARIO_NODO (MULTIPLE)
	@Override
	public List<Documento> leeDocumentoBinarioNodoMultiple(DatosAutenticacionTO session, NodoTO nodo,
	        Integer secuenciaDocumento) throws BrioBPMException {

		log.info("------------------leeDocumentoBinarioNodoMultiple-------------------------");
		//log.info("SESSION :" + session.toString());
		//log.info("NODO :" + nodo.toString());
		//log.info("SECUENCIA DOCUMENTO :" + secuenciaDocumento);
		
		// Variables locales que almacenan información clave para el procesamiento del documento
	    String cveEntidad = session.getCveEntidad();
	    String cveInstancia = nodo.getCveInstancia();
	    String cveProceso = nodo.getCveProceso();
	    BigDecimal version = nodo.getVersion();

	    // Variables para almacenar el contenido del archivo y sus metadatos
	    String idProceso;
	    boolean existeDocumento;
	    String mensaje;
	    String variableValor;

	   
	    idProceso = Constants.LEE_DOCUMENTO_BINARIO_NODO;
	    existeDocumento = false; // Inicializa el indicador de existencia del documento como falso

	    // CONCATENA LA LISTA DE LOS PARÁMETROS PARA DESPLEGARLOS EN CASO DE ERROR
	    // Se construye una cadena que representa la combinación de parámetros clave
	    variableValor = Constants.CVE_PROCESO + nodo.getCveProceso() + "|" +
	            Constants.VERSION + nodo.getVersion() + "|" +
	            Constants.CVE_INSTANCIA + nodo.getCveInstancia() + "|" +
	            Constants.CVE_NODO + nodo.getCveNodo() + "|" +
	            Constants.ID_NODO + nodo.getIdNodo() + "|" +
	            Constants.SECUENCIA_NODO + nodo.getSecuenciaNodo() + "|" +
	            Constants.SECUENCIA_DOCUMENTO + secuenciaDocumento;


	    // Busca todos los documentos que coincidan con los criterios dados
	    List<InDocumentoProcesoOc> documentosProcesoOc = iInDocumentoProcesoOcRepository.encuentraDocumentos(
	    		cveEntidad, cveProceso, version, cveInstancia, secuenciaDocumento);
	    // Lista para almacenar los resultados
	    List<Documento> resultados = new ArrayList<>();
	    
	    //log.info("---------documentosProcesoOc------ " + documentosProcesoOc.size());
	    if (documentosProcesoOc.isEmpty()) {
	    	
	    	//log.info("---------documentosProcesoOc VACIA------ ");
	    	// Maneja el caso en que el documento no existe
	    	// Si el documento no está presente, se devuelve un mensaje de error
	    	mensaje = messagesService.getMessage(session, idProceso, Constants.NO_EXISTE_DOCUMENTO_PARA_NODO, variableValor);
	    	resultados.clear();
	    	resultados.add(Documento.builder()
	    			.message(mensaje)
	    			.status(Constants.ERROR)
	    			.build());
	    	return resultados;
	    }

	    for (InDocumentoProcesoOc documentoProcesoOc : documentosProcesoOc) {
	        // Si el documento es nulo o no tiene contenido, se ignora
	        if (documentoProcesoOc == null || documentoProcesoOc.getArchivoBinario() == null) {
	            continue;
	        }
	        
	        byte[] archivoBinario = documentoProcesoOc.getArchivoBinario();
	        String nombreArchivo = documentoProcesoOc.getNombreArchivo();
	        String contentType = documentoProcesoOc.getContentType();
	        Blob archivoBinarioBlob;

	        try {
	            archivoBinarioBlob = new SerialBlob(archivoBinario);
	        } catch (SQLException e) {
	            throw new BrioBPMException("Error al convertir byte[] a Blob", e);
	        }

	        // Crea un objeto Documento y lo añade a la lista de resultados
	        Documento result = Documento.builder()
	                .status(Constants.OK)
	                .message("")
	                .contenido(archivoBinarioBlob)
	                .contentType(contentType)
	                .nombre(nombreArchivo)
	                .build();
	        //log.info("--- Agrega nuevo documento a la lista");
	        resultados.add(result);
	    }

	    // Si no se encontraron documentos, devuelve un error
	    if (resultados.isEmpty()) {
	        variableValor = Constants.CVE_PROCESO + nodo.getCveProceso() + "|" +
	                Constants.VERSION + nodo.getVersion() + "|" +
	                Constants.CVE_INSTANCIA + nodo.getCveInstancia() + "|" +
	                Constants.CVE_NODO + nodo.getCveNodo() + "|" +
	                Constants.ID_NODO + nodo.getIdNodo() + "|" +
	                Constants.SECUENCIA_NODO + nodo.getSecuenciaNodo() + "|" +
	                Constants.SECUENCIA_DOCUMENTO + secuenciaDocumento;

	        mensaje = messagesService.getMessage(session, Constants.LEE_DOCUMENTO_BINARIO_NODO,
	                Constants.NO_EXISTE_DOCUMENTO_PARA_NODO, variableValor);
	        
	        resultados.clear();
	        resultados.add(Documento.builder()
	    			.message(mensaje)
	    			.status(Constants.ERROR)
	    			.build());
	    	}

	    return resultados;
	}

		
	// SP_GUARDA_DOCUMENTO_BINARIO_NODO (MULTIPLE)
	/**
	 * Este método se encarga de guardar múltiples documentos binarios en un nodo específico 
	 * dentro de un proceso, administrando las secuencias de archivo y asegurando la persistencia 
	 * adecuada de los datos en la base de datos.
	 * 
	 * - Primero, se inicializa un mensaje de retorno con el estado "OK".
	 * - Luego, se itera sobre una lista de documentos para procesar cada uno.
	 * - Para el primer documento de la lista, se eliminan los documentos previos del nodo que 
	 *   tengan una secuencia mayor y se establece la secuencia del nuevo archivo en 1.
	 * - Para documentos posteriores, se calcula la siguiente secuencia de archivo disponible.
	 * - Se crea una clave primaria (PK) única para cada documento y se guarda en la base de datos.
	 * - Finalmente, el método retorna un mensaje de estado.
	 *
	 * @param session Información de autenticación de la sesión del usuario.
	 * @param documentos Lista de objetos DocumentoTO que contienen los datos binarios a ser guardados.
	 * @return RetMsg Mensaje de retorno con el estado de la operación.
	 * @throws BrioBPMException Si ocurre algún error durante el proceso.
	 */
	@Override //OCURRENCIA
	public RetMsg guardaDocumentoBinarioNodoMultiple(DatosAutenticacionTO session, List<DocumentoTO> documentos) throws BrioBPMException {

		//log.info("-----------------guardaDocumentoBinarioNodoMultiple-------------------");

	    // Inicializa el mensaje de retorno con un estado "OK" por defecto.
	    RetMsg msg = RetMsg.builder().status("OK").message("").build();

	    // Recupera información de la sesión.
	    String cveEntidad = session.getCveEntidad();

	    Integer ocurrenciaNueva = null;
	    Integer secuenciaArchivoNuevo = 1;

	    int contador = 0;

	    for (DocumentoTO documento : documentos) {
	    	//log.info("---------documento:" + documento.toString());

	        // Recupera información del documento.
	        String cveInstancia = documento.getCveInstancia();
	        String cveProceso = documento.getCveProceso();
	        byte[] archivoBinario = documento.getData();
	        String contenType = documento.getContentType();
	        String nombreArchivo = documento.getNomArchivo();
	        Integer secuenciaDocumento = documento.getSecDocumento();
	        BigDecimal version = new BigDecimal(documento.getVersion());
	        Integer ocurrencia = documento.getOcurrencia();

	        if (contador == 0) {
	            // Primer caso: crear un nuevo renglón.
	            if (ocurrencia == null || ocurrencia == 0) {
	                // Buscar la ocurrencia máxima existente y asignar la nueva ocurrencia.
	                ocurrenciaNueva = iInDocumentoProcesoOcRepository.encuentraMaxOcurrenciaNueva(cveEntidad, cveProceso, version, cveInstancia, secuenciaDocumento);
	                ocurrenciaNueva = ocurrenciaNueva != null ? ocurrenciaNueva + 1 : 1;

	            } else {
	                // Segundo caso: agregar archivos a un renglón existente.
	                // Borra los documentos con secuencias mayores.
	                iInDocumentoProcesoOcRepository.deleteMulDocumentoProceso(cveEntidad, cveProceso, version, cveInstancia, secuenciaDocumento, ocurrencia);
	                ocurrenciaNueva = ocurrencia; // Mantener la ocurrencia existente.
	            }

	            secuenciaArchivoNuevo = 1; // Inicializar secuencia de archivo para el nuevo renglón.
	            contador++;

	            secuenciaArchivoNuevo = 1;
	        } else {
	            // Casos adicionales al primer documento en la lista.
	            secuenciaArchivoNuevo++; // Incrementa la secuencia para cada archivo adicional.
	        }


	        log.info("---------secuenciaArchivoNuevo:" + secuenciaArchivoNuevo);

	        // Crea una nueva clave primaria (PK) para el nuevo registro de documento.
	        InDocumentoProcesoOcPK id = InDocumentoProcesoOcPK.builder()
	                .cveEntidad(cveEntidad)	                
	                .cveProceso(cveProceso)
	                .version(version)
	                .cveInstancia(cveInstancia)
	                .ocurrencia(ocurrenciaNueva)
	                .secuenciaDocumento(secuenciaDocumento)
	                .secuenciaArchivo(secuenciaArchivoNuevo)
	                .build();
	        
	        log.info("NOMBRE ARCHIVO: " + nombreArchivo);

	        // Remover el carácter específico "#"
	        nombreArchivo = nombreArchivo.replace("#", "");

	        // Eliminar otros caracteres especiales, dejando solo letras, números, espacios y puntos
	        nombreArchivo = nombreArchivo.replaceAll("[^a-zA-Z0-9 .]", "");

	        log.info("NOMBRE ARCHIVO CASTEADO: " + nombreArchivo);
	        
	        // Crea un nuevo objeto documento con la información recopilada y el archivo binario.
	        InDocumentoProcesoOc documentoProcesoOc = InDocumentoProcesoOc.builder()
	                .id(id)
	                .nombreArchivo(nombreArchivo)
	                .contentType(contenType)
	                .archivoBinario(archivoBinario)
	                .build();

	        // Guarda y confirma el nuevo documento en la base de datos.
	        iInDocumentoProcesoOcRepository.saveAndFlush(documentoProcesoOc);
	    }

	    // Retorna el mensaje con el estado y cualquier información adicional.
	    return msg;
	}



		
	// SP_CREA_DOCUMENTOS_NODO (MULTIPLE)
	@Override
	public EstatusTO creaDocumentosMultiples(DatosAutenticacionTO session, NodoTO nodo) throws BrioBPMException {
			
		 // Se obtienen datos importantes del objeto de sesión y del nodo para su uso posterior.
	    String cveEntidad = session.getCveEntidad(); // Clave de la entidad actual.
	    String cveProceso = nodo.getCveProceso(); // Clave del proceso actual.
	    BigDecimal version = nodo.getVersion(); // Versión del proceso.
	    String cveInstancia = nodo.getCveInstancia(); // Clave de la instancia del proceso.
	    String cveNodo = nodo.getCveNodo(); // Clave del nodo actual.
	    Integer idNodo = nodo.getIdNodo(); // Identificador del nodo.
	    Integer secuenciaNodo = nodo.getSecuenciaNodo(); // Secuencia del nodo.
	    
	    // Se registra un mensaje informativo en los logs para indicar el inicio del proceso.
		log.info("-------INICIA creaDocumentos");
		
		 // Construye una cadena de texto con los valores de las variables relevantes
	    // separadas por "|" para utilizar en los mensajes de configuración.
		String variablesValores = Constants.CVE_PROCESO + cveProceso + "|" +
				Constants.VERSION + version + "|" +
				Constants.CVE_INSTANCIA + cveInstancia + "|" +
				Constants.CVE_NODO + cveNodo + "|" +
				Constants.ID_NODO + idNodo + "|" +
				Constants.SECUENCIA_NODO + secuenciaNodo;
		
		 // Definición de identificador de proceso y tipo de sección para documentos.
	    String idProceso = "CREA_DOCUMENTOS_NODO"; // Identificador del proceso de creación de documentos.
	    String tipoSeccionDocumentos = "DOCUMENTOS"; // Tipo de sección asociado a documentos.

	    // Se inicializa el objeto EstatusTO que almacenará el estado del proceso.
	    // Se establece inicialmente como exitoso (OK) con un mensaje vacío.
	    EstatusTO result = EstatusTO.builder()
	            .tipoExcepcion(Constants.OK) // Código de éxito inicial.
	            .mensaje("") // Mensaje vacío por defecto.
	            .build();
		
		// VALIDA QUE EL TIPO DE NODO SEA 'ACTIVIDAD-USUARIO'
	    // Se verifica que el nodo sea del tipo 'ACTIVIDAD-USUARIO' o 'ACTIVIDAD-USUARIO-TEMPORIZACION'.
	    if (!Constants.ACTIVIDAD_USUARIO.equals(cveNodo) && !Constants.ACTIVIDAD_USUARIO_TEMPORIZACION.equals(cveNodo)) {
	        // Si el nodo no es de tipo usuario, se registra un mensaje informativo en los logs.
	    	//log.info("-------IF ACTIVIDAD-USUARIO");

	        // Obtiene un mensaje de error utilizando el servicio de mensajes,
	        // pasando la sesión, el id del proceso, un código de error,
	        // y la cadena de valores de variables.
	        String mensaje = messagesService.getMessage(session,
	                idProceso,
	                "DOCUMENTOS_EXCLUSIVOS_ACT_USU",
	                variablesValores);

	        // Establece el tipo de excepción como ERROR y asigna el mensaje obtenido.
	        result.setTipoExcepcion(Constants.ERROR);
	        result.setMensaje(mensaje);

	        // Devuelve el resultado con el error.
	        return result;
		} 

	    // Si el nodo es del tipo correcto, se continúa el proceso
	    //log.info("-------BEFORE LISTA encuentraSecuenciaDocumento");

	    // Se obtiene una lista de secuencias de documentos asociados al nodo actual.
	    List<Integer> documentos = iStDocumentoSeccionRepository.encuentraSecuenciaDocumento(
	            cveEntidad,
	            cveProceso,
	            version,
	            cveNodo,
	            idNodo);

	    //log.info("-------AFTER LISTA encuentraSecuenciaDocumento");

//	    // ecncontrar ocurrencia MAX 
//	    Integer maxOcurrencia = iInDocumentoProcesoOcRepository.encuentraMaxOcurrencia(cveEntidad, 
//	    		cveProceso, version, cveInstancia);
//	    maxOcurrencia = maxOcurrencia != null ? maxOcurrencia : 1;
	    
	    // RECORRE LOS DOCUMENTOS PARA GENERARLOS EN LA INSTANCIA DEL NODO
	    // Se itera sobre cada documento en la lista de secuencias obtenida.
		for(Integer secuenciaDocumentos : documentos) {
			
			// CREA EL DOCUMENTO
			  // Se construye una clave primaria compuesta (PK) para identificar el documento del proceso.
	        InDocumentoProcesoOcPK id = InDocumentoProcesoOcPK.builder()
	                .cveEntidad(cveEntidad) // Clave de la entidad del documento.
	                .cveProceso(cveProceso) // Clave del proceso del documento.
	                .version(version) // Versión del documento.
	                .cveInstancia(cveInstancia) // Clave de la instancia del documento.
	                .secuenciaDocumento(secuenciaDocumentos) // Secuencia del documento.
	                .ocurrencia(1)
	                .secuenciaArchivo(1)
	                .build();

	        // Verifica si ya existe un documento en la base de datos con la clave primaria generada.
	        Integer resultado = iInDocumentoProcesoOcRepository.existeInDocumentoProcesoOc(
	                cveEntidad,
	                cveProceso,
	                version,
	                cveInstancia,
	                secuenciaDocumentos,
	                1);
	        		

	        log.info("-------IF existeInDocumentoProcesoOc");

	        // Si no existe un documento con esa clave primaria (resultado == 0), se procede a crearlo.
	        if (resultado == 0) {
	            log.info("-------IF RESULTADO: " + resultado);

	            // Se crea una nueva entidad InDocumentoProceso con la clave primaria generada.
	            InDocumentoProcesoOc entity = InDocumentoProcesoOc.builder()
	                    .id(id)
	                    .build();

	            // Se guarda y actualiza la entidad en la base de datos.
	            //log.info("PREPARA EN IN DOCUMENTO PROCESO EN NODO HELPER - creaDocumentosMultiples");
	            iInDocumentoProcesoOcRepository.saveAndFlush(entity);
	            //log.info("GUARDADO EN IN DOCUMENTO PROCESO EN NODO HELPER - creaDocumentosMultiples");


	            //log.info("-------SAVE AND FLUSH");

	            // Se verifica si el documento se guardó correctamente recuperándolo por su clave primaria.
	            Optional<InDocumentoProcesoOc> inDocumento = iInDocumentoProcesoOcRepository.findById(id);

	            // Si el documento está presente, se considera que la operación fue exitosa.
	            if (inDocumento.isPresent()) {
	                result.setTipoExcepcion(Constants.OK);
	            } else {
	                // Si el documento no se pudo guardar, se elimina cualquier rastro del documento creado y se genera un mensaje de error.
	                iInDocumentoProcesoOcRepository.deleteDocumentoProceso(cveEntidad,
	                        cveProceso,
	                        version,
	                        cveInstancia,
	                        1);

	                // Obtiene un mensaje de error utilizando el servicio de mensajes,
	                // pasando la sesión, el id del proceso, un código de error,
	                // y la cadena de valores de variables.
	                String mensaje = messagesService.getMessage(
	                        session,
	                        idProceso,
	                        "ERR_INS_TAB_IN_DOCUMENTO_PROCESO",
	                        variablesValores);

	                // Establece el tipo de excepción como ERROR y asigna el mensaje obtenido.
	                result.setTipoExcepcion(Constants.ERROR);
	                result.setMensaje(mensaje);

	                // Devuelve el resultado con el error.
	                return result;
	            }
	        }

	        // CREA LAS VARIABLES DE DOCUMENTOS
	        // Se crean las variables asociadas al documento de la sección utilizando un método auxiliar.
	        result = creaVariablesSeccion(
	                session,
	                nodo,
	                tipoSeccionDocumentos,
	                secuenciaDocumentos);

	        // Si el resultado es un error, se termina el proceso.
	        if (result.getTipoExcepcion().equals(Constants.ERROR)) {
	            break; // Sale del bucle de documentos si ocurre un error.
	        }
	    }

	    // Devuelve el resultado final, que contiene el estado del proceso de creación de documentos.
	    return result;
	}


	// SP_LEE_VALOR_VARIABLE
	@Override
	
	public EstatusVariablesTO leeValorVariable(DatosAutenticacionTO session, NodoTO nodo, String cveVariable,
			Integer secuenciaDocumento) throws BrioBPMException, ParseException{
		
		
		//log.info("---------------leeValorVariable---------------------");
		log.info("----nodo: " + nodo.toString());
		// INICIALIZA PARÁMETROS DE SALIDA
		String cveEntidad = session.getCveEntidad();
		String cveInstancia = nodo.getCveInstancia();
		String cveProceso = nodo.getCveProceso();
		Integer ocurrencia = nodo.getOcurrencia();
		BigDecimal version = nodo.getVersion();
		String agregacion = null;
		
		//INICIALIZA CÓDIGO DE ERROR, MENSAJE Y SUGERENCIA	    
	    EstatusVariablesTO leeVariable = EstatusVariablesTO.builder()
				.tipoExcepcion(Constants.OK)
				.mensaje("")
				.build();
	    
	    // LEE EL VALOR DE LA VARIABLE DE ACUERDO A SU TIPO
	    log.info("-----> cveVariable: " + cveVariable);
	    
    	// LEE EL VALOR DE LA VARIABLE DE ACUERDO A SU TIPO
    	if (cveVariable.startsWith(Constants.VPRO)) {
    		// Variables de proceso
    	    log.info("----> VPRO: " + Constants.VPRO);
	    	EstatusVariablesTO leeVPRO = leerValorVpro(session, nodo, agregacion, secuenciaDocumento);
	    	leeVariable = leeVPRO;
	    	
	    } else if (cveVariable.startsWith(Constants.VLOC)) {
	    	 // Variables locales
	    	log.info("----> VLOC: " + Constants.VLOC);
	    	EstatusVariablesTO leeVLOC = leerValorVeloc(session, cveProceso, version, cveVariable);
	    	leeVariable = leeVLOC;
	    	
	    } else if (cveVariable.startsWith(Constants.VENT)) {
	    	// Variables de entorno
	    	log.info("----> VENT: " + Constants.VENT);
	    	EstatusVariablesTO leeVENT = leerValorVent(session, cveProceso, version, cveVariable);
	    	leeVariable = leeVENT;
	    	
	    } else if (cveVariable.startsWith(Constants.VSIS)) {
	    	// Variables del sistema
	    	log.info("----> VSIS: " + Constants.VSIS);
	    	EstatusVariablesTO leeVSIS = leerValorVsis(session, cveProceso, version, cveVariable);
	    	leeVariable = leeVSIS;
	    	
	    } else if (cveVariable.startsWith(Constants.DA) && 
	    		cveEntidad != null && cveProceso != null &&
	    		version != null && cveInstancia != null) {
	    	// Variables de datos de proceso
	    	log.info("----> VPN: " + Constants.DA);
	    	EstatusVariablesTO leeVPN = leerValorVpn(session, nodo, cveVariable);
	    	leeVariable = leeVPN;
	    	log.info("----> VPN leeV: " + leeVariable.toString());
	    	
	    } else if (cveVariable.startsWith(Constants.VDOC) &&
	    		ocurrencia != null && ocurrencia != 0) {
	    	// Variables de documentos
	    	log.info("----> VDOC: " + Constants.VDOC);
	    	EstatusVariablesTO leeVDOC = leerValorVdoc(session, nodo);
	    	leeVariable = leeVDOC;
	    }
    	
    	//log.info("--------> leeVariable fecha: " + leeVariable.toString()); // Registra el resultado final de la lectura de la variable
		return leeVariable; // Devuelve el resultado
	}


	// SP_CAMBIA_SITUACION_NODO
	// Este método cambia la situación de un nodo en base a una acción proporcionada ("OBTENER" o "LIBERAR").
	// Valida la acción, verifica el estado del nodo y realiza las actualizaciones correspondientes en la base de datos,
	// devolviendo un mensaje con el estado de la operación.
	@Override
	public RetMsg cambiaSituacionNodo(DatosAutenticacionTO session, ActividadTO actividad, String accion) throws BrioBPMException {

	    // Se obtienen las variables clave de la entidad, usuario, instancia, nodo y proceso a partir de los objetos de sesión y actividad.
	    String cveEntidad = session.getCveEntidad();
	    String cveUsuario = session.getCveUsuario();
	    String cveInstancia = actividad.getCveInstancia();
	    String cveNodo = actividad.getCveNodo();
	    String cveProceso = actividad.getCveProceso();
	    Integer idNodo = actividad.getIdNodo();
	    String idProceso = "CAMBIA_SITUACION_NODO";
	    Date fechaEstadoActual;
	    Integer secuenciaNodo = actividad.getSecNodo();
	    String cveUsuarioBloquea = null;
	    String estado = "";
	    //String estadoActual;
	    String mensaje;
	    
	    log.info("**********SP_CAMBIA_SITUACION_NODO");
	    
	    // Se obtiene la versión de la actividad, si existe, o se asigna 0 por defecto.
	    BigDecimal version = actividad.getVersion() != null ? new BigDecimal(actividad.getVersion()) : BigDecimal.ZERO;

	    // Inicializa el mensaje de retorno con un estado "OK" por defecto.
	    RetMsg msg = RetMsg.builder().status("OK").message("").build();

	    // Se construye la cadena de variables y valores que se utilizarán en los mensajes.
	    String variableValor = Constants.CVE_PROCESO + cveProceso + "|" +
	            Constants.VERSION + version + "|" +
	            Constants.CVE_INSTANCIA + cveInstancia + "|" +
	            Constants.CVE_NODO + cveNodo + "|" +
	            Constants.ID_NODO + idNodo + "|" +
	            Constants.SECUENCIA_NODO + secuenciaNodo + "|" +
	            Constants.CVE_USUARIO + idNodo + "|" +
	            Constants.ACCION + accion;

	    // Valida la acción. Si no es "OBTENER" o "LIBERAR", retorna un mensaje de error.
	    if(!"OBTENER".equals(accion) && !"LIBERAR".equals(accion)) {
	        log.info("Indica error de ACCION_INVALIDA_SOBRE_NODO evaluando en obtener y liberar");
	        mensaje = messagesService.getMessage(session,
	                idProceso,
	                "ACCION_INVALIDA_SOBRE_NODO",
	                variableValor);
	        msg.setStatus(Constants.ERROR);
	        msg.setMessage(mensaje);
	        return msg;
	    }
	    
	    // Valida que el nodo especificado exista en el sistema.
	    NodoTO nodoValDatosIn = NodoTO.builder()
	            .idProceso(idProceso)
	            .cveProceso(cveProceso)  		
	            .version(version)
	            .cveInstancia(cveInstancia)
	            .cveNodo(cveNodo)
	            .idNodo(idNodo)
	            .secuenciaNodo(secuenciaNodo)
	            .build();
	    EstatusTO estatus = valDatosIn(session, nodoValDatosIn);
	    log.info("**********TERMINA SP_VAL_DATOS_IN_NODO");
	    if(Constants.ERROR.equals(estatus.getTipoExcepcion())) {
	        msg.setMessage(estatus.getMensaje());
	        msg.setStatus(estatus.getTipoExcepcion());
	        return msg;
	    }
	    
	    // Obtiene el estado de la actividad y, si aplica, el usuario que bloquea el nodo.
	    InNodoProcesoPK idNodoProceso = InNodoProcesoPK.builder()
	            .cveEntidad(cveEntidad)
	            .cveProceso(cveProceso)
	            .version(version)
	            .cveInstancia(cveInstancia)
	            .cveNodo(cveNodo)
	            .idNodo(idNodo)
	            .secuenciaNodo(secuenciaNodo)
	            .build();
	    Optional<InNodoProceso> nodoProceso = inNodoProcesoRepository.findById(idNodoProceso);
	    if(nodoProceso.isPresent()) {
	        cveUsuarioBloquea = nodoProceso.get().getUsuarioBloquea();
	    }
	    // Actualiza la lista variable-valor para incluir el usuario que bloquea, si existe.
	    variableValor = variableValor + "|" + "@CVE_USUARIO_BLOQUEA@|" + (cveUsuarioBloquea != null ? cveUsuarioBloquea.trim() : "NA");
	    
	    // Valida el estado actual del nodo y la acción. 
	    // Para "OBTENER", el estado debe ser "REGISTRO"; para "LIBERAR", la actividad debe estar bloqueada.
	    log.info("@CH_ACCION : " + accion);
	    log.info("@CH_ESTADO : " + estado);
	    log.info("@CH_CVE_USUARIO_BLOQUEA : " + cveUsuarioBloquea);
	    
	    if (("OBTENER".equals(accion) && !estado.isEmpty() && !"REGISTRO".equals(estado)) || ("LIBERAR".equals(accion) && cveUsuarioBloquea == null)) {
	        mensaje = messagesService.getMessage(session,
	                idProceso,
	                "ACCION_INVALIDA_SOBRE_NODO",
	                variableValor);
	        msg.setStatus(Constants.ERROR);
	        msg.setMessage(mensaje);
	        return msg; 
	    }
	    
	    // Verifica que el nodo actual no esté bloqueado por otro usuario antes de permitir la acción.
	    log.info("VALIDA QUE EL NODO ACTUAL NO SE ENCUENTRE YA BLOQUEADO @CH_ACCION:  : " + accion);
	    log.info("@CH_CVE_USUARIO : " + cveUsuario);
	    log.info("@CH_CVE_USUARIO_BLOQUEA : " + cveUsuarioBloquea);
	    
	    switch(accion) {
	    case "OBTENER":
	    	//log.info("-----> cveUsuarioBloquea: " + cveUsuarioBloquea);
	        if(cveUsuarioBloquea != null && !cveUsuarioBloquea.equals(cveUsuario)) {
	            mensaje = messagesService.getMessage(session,
	                    idProceso,
	                    "NODO_ACTUAL_YA_ESTA_BLOQUEADO",
	                    variableValor);
	            msg.setStatus(Constants.ERROR);
	            msg.setMessage(mensaje);
	            return msg;
	        }

	        // Si el nodo está bloqueado por el usuario actual, termina el proceso sin cambios.
	        cveUsuarioBloquea = cveUsuarioBloquea != null ? cveUsuarioBloquea : "";
	        log.info("@CH_CVE_USUARIO : " + cveUsuario);
	        log.info("@CH_CVE_USUARIO_BLOQUEA : " + cveUsuarioBloquea);
	        
	        if((cveUsuarioBloquea).equals(cveUsuario)) {
	            log.info("-----> MSG MITAD: " + msg.toString());
	            return msg;
	        }
	        
	        log.info("CONTINUA ");
	        
	        // Actualiza la situación del nodo en la base de datos si la acción es válida.
	        fechaEstadoActual = new Date();
	        List<String> usuariosObtener = iUsuarioRolRepository.buscarRoles(cveEntidad, cveUsuario, cveProceso, version);
	        
	        if(nodoProceso.isPresent()) {
	            InNodoProceso nodoEntidad = nodoProceso.get();
	            // Actualiza los campos del nodo con la nueva información de bloqueo.
	            nodoEntidad.setCveEntidadBloquea(cveEntidad); 
	            nodoEntidad.setFechaEstadoActual(fechaEstadoActual);
	            nodoEntidad.setFechaBloquea(fechaEstadoActual);
	            nodoEntidad.setUsuarioBloquea(cveUsuario);
	            
	            if(usuariosObtener.size() == 1) {
	            	String rolBloquea = usuariosObtener.get(0);
	            	log.info("--- ROL BLOQUEA" + rolBloquea);
	                nodoEntidad.setRolBloquea(rolBloquea);
	                
	                // Verifica si los cambios se han realizado correctamente.
	                if (!nodoEntidad.getCveEntidadBloquea().equals(cveEntidad) ||
	                        !nodoEntidad.getFechaEstadoActual().equals(fechaEstadoActual) ||
	                        !nodoEntidad.getFechaBloquea().equals(fechaEstadoActual) ||
	                        !nodoEntidad.getUsuarioBloquea().equals(cveUsuario) ||
	                        !nodoEntidad.getRolBloquea().equals(rolBloquea)) {
	                    
	                    mensaje = messagesService.getMessage(session,
	                            idProceso,
	                            "ERR-UPD-TAB-IN_NODO_PROCESO",
	                            variableValor);
	                    msg.setStatus(Constants.ERROR);
	                    msg.setMessage(mensaje);
	                    return msg;
	                } else {
	                    // Guarda el nodo actualizado en la base de datos y genera un evento en la bitácora.
	                    log.info("----> ACTUALIZACION Y GENERA BITACORA OBTENER");
	                    iInNodoProcesoRepository.saveAndFlush(nodoEntidad);
	                    estatus = generaEventoBitacora(session, nodoValDatosIn, "Obtener");
	                    log.info("**** TERMINA SP_GENERA_EVENTO_BITACORA");
	                }
	                
	            } else {
	            	 // Verifica si los cambios se han realizado correctamente.
	                if (!nodoEntidad.getCveEntidadBloquea().equals(cveEntidad) ||
	                        !nodoEntidad.getFechaEstadoActual().equals(fechaEstadoActual) ||
	                        !nodoEntidad.getFechaBloquea().equals(fechaEstadoActual) ||
	                        !nodoEntidad.getUsuarioBloquea().equals(cveUsuario)) {
	                    
	                    mensaje = messagesService.getMessage(session,
	                            idProceso,
	                            "ERR-UPD-TAB-IN_NODO_PROCESO",
	                            variableValor);
	                    msg.setStatus(Constants.ERROR);
	                    msg.setMessage(mensaje);
	                    return msg;
	                } else {
	                    // Guarda el nodo actualizado en la base de datos y genera un evento en la bitácora.
	                    log.info("----> ACTUALIZACION Y GENERA BITACORA OBTENER");
	                    iInNodoProcesoRepository.saveAndFlush(nodoEntidad);
	                    estatus = generaEventoBitacora(session, nodoValDatosIn, "Obtener");
	                    log.info("**** TERMINA SP_GENERA_EVENTO_BITACORA");
	                }
	            }
	              
	        }
	    	break;
	    case "LIBERAR":
	    	// Si la acción es "LIBERAR", valida que el nodo esté bloqueado por el usuario actual.
	        if(!cveUsuarioBloquea.equals(cveUsuario)) {
	            mensaje = messagesService.getMessage(session,
	                    idProceso,
	                    "USUARIO_DIFERENTE_A_USUARIO_BLOQUEO",
	                    variableValor);
	            msg.setStatus(Constants.ERROR);
	            msg.setMessage(mensaje);
	            return msg;
	        } else {
	        	
	        	fechaEstadoActual = new Date();
		        
		        if(nodoProceso.isPresent()) {
		            InNodoProceso nodoEntidad = nodoProceso.get();
		            
		            nodoEntidad.setCveEntidadBloquea(null); 
		            nodoEntidad.setFechaEstadoActual(fechaEstadoActual);
		            nodoEntidad.setFechaBloquea(null);
		            nodoEntidad.setUsuarioBloquea(null);
		            nodoEntidad.setRolBloquea(null);
		            
	                if (nodoEntidad.getCveEntidadBloquea() != null ||
	                        !nodoEntidad.getFechaEstadoActual().equals(fechaEstadoActual) ||
	                        nodoEntidad.getFechaBloquea() != null ||
	                        nodoEntidad.getUsuarioBloquea() != null ||
	                        nodoEntidad.getRolBloquea() != null) {
	                    
	                    mensaje = messagesService.getMessage(session,
	                            idProceso,
	                            "ERR-UPD-TAB-IN_NODO_PROCESO",
	                            variableValor);
	                    msg.setStatus(Constants.ERROR);
	                    msg.setMessage(mensaje);
	                    return msg;
	                } else {
	                    // Guarda el nodo actualizado en la base de datos y genera un evento en la bitácora.
	                    log.info("----> ACTUALIZACION Y GENERA BITACORA LIBERAR");
	                    iInNodoProcesoRepository.saveAndFlush(nodoEntidad);
	                    estatus = generaEventoBitacora(session, nodoValDatosIn, "Liberar");
	                    log.info("**** TERMINA SP_GENERA_EVENTO_BITACORA");
	                }
		        }
	        }
	    	break;
	        
	    }
	    return msg;
	}

	
	
	// REGLA_PROCESO_TERMINAR
	@Override
	public RetMsg evaluaReglasProcesoTerminar (DatosAutenticacionTO session, NodoTO nodo, String tipoBoton) {
		RetMsg msg = RetMsg.builder()
				.status(Constants.OK)
				.message("")
				.build();
		String resultado = "";
		String origen = nodo.getUsoSeccion();
		origen = origen != null ? origen : "WEB";
		
		List<ReglaBotonActividad> reglas = iReglaProcesoTerminarRepository
				.encuentraReglasTerminar(session.getCveEntidad(), nodo.getCveProceso(),
						nodo.getVersion(), nodo.getCveNodo(), nodo.getIdNodo(), origen, tipoBoton);
		boolean conError = false;
		String mgsError = "";
		if (reglas.size() > 0) {
			for (ReglaBotonActividad regla : reglas) {
				String expresion = regla.getExpresion();
				String query = "";
				log.info("Expresión: " + expresion);
				
				if (regla.getTipoExpresion().equals("QUERY")) {

					EstatusVariablesTO reemplzav;
					try {
						
						String cadenaSalida = expresion;
						if ( reglas.size() > 1) {
					    	String variableReemplazar = "@resultado@";
					    	
					    	// Reemplaza en la cadena de salida el valor de la variable correspondiente
					    	cadenaSalida = expresion.replace(variableReemplazar, (resultado != null) ? resultado : "");
						}

						log.info("cadenaSalida: " + cadenaSalida);
						
						reemplzav = reemplazaVariables(session, nodo, null, cadenaSalida);
						query = reemplzav.getCadenaSalida();
					} catch (BrioBPMException e) {
						msg.setStatus(Constants.ERROR);
						msg.setMessage("Error al obtener la informacion del nodo");
						return msg;
					}
					
					
					if (regla.getTipoQuery().equals("NA")) {
						resultado = areaTrabajoDAO.ejecutaQuery(query);
						
						if (!regla.getResultadoLogico().equals("NA")) {
							if (!regla.getResultadoLogico().equals(resultado)) {
								if (!regla.getCodMjsError().equals(null)) {
									conError = true;
									mgsError = mgsError + " " + messagesService.getMessage(regla.getCodMjsError(), session.getCveIdioma().toUpperCase());									
								}
							}
						}

						
					} else {

						int filasAfectadas = entityManager.createNativeQuery(query).executeUpdate();
						
						if (filasAfectadas == 0) {	
							if (!regla.getCodMjsError().equals(null)) {
								conError = true;
								mgsError = mgsError + " " + messagesService.getMessage(regla.getCodMjsError(), session.getCveIdioma().toUpperCase());									
							} else {
								conError = true;
								mgsError = mgsError + " " + messagesService.getMessage("AJUSTE_ERROR_QUERY_" + regla.getTipoQuery(), session.getCveIdioma().toUpperCase());								
							}
						}
					}
					
				}
				
				if (regla.getTipoExpresion().equals("FUNCION")) {

					nodo.setCveEntidad(session.getCveEntidad());
		            Method metodo;
					try {
						
						//metodo = ReflectionUtils.findMethod(EjecutaFuncionHelper.class, "validaEstatusREPSE", NodoTO.class);
						//ReflectionUtils.makeAccessible(metodo);
						//ReflectionUtils.invokeMethod(metodo, ejecutaFuncionHelper, nodo);
						
						// Activa el metodo
						metodo = ejecutaFuncionHelper.getClass().getMethod(expresion, NodoTO.class);
			            //Invocar el método en la instancia de ejecutaFuncionHelper
						metodo.invoke(ejecutaFuncionHelper, nodo);
			            
					} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						log.error(e.getMessage());
						conError = true;
						mgsError = mgsError + " " + messagesService.getMessage(regla.getCodMjsError(), session.getCveIdioma().toUpperCase());									
					} 
					//					} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {

				}
			    
			}	
			
			if (conError) {
				msg.setStatus(Constants.ERROR);
				msg.setMessage(mgsError);				
			}

		}

		return msg;		
	}
	
	//Medo para crear variables inciales con un valor inicial en el nodo
	private EstatusTO setCreaVariablesRefencia(DatosAutenticacionTO session, NodoTO nodo) throws BrioBPMException, ParseException {
		
		log.debug("############### INICIA_CREA_VARIABLES_PROCESO_REFERENCIA_NODO_PROCESO");
		EstatusTO result = EstatusTO.builder()
				.tipoExcepcion(Constants.OK)	
				.mensaje("")
				.build();
		String entidad = session.getCveEntidad();
		String claveProceso = nodo.getCveProceso();
		BigDecimal version = nodo.getVersion();
		String claveInstancia = nodo.getCveInstancia();
		
		//SVM asigna la variables niciales para el nodo
		String query = null;
		String aplicaInicio = null;
		String referencia = null;
		
		StNodoProcesoPK id = StNodoProcesoPK.builder()
				.cveEntidad(entidad)
				.cveProceso(claveProceso)
				.idNodo(nodo.getIdNodo())
				.cveNodo(nodo.getCveNodo())
				.version(version)
				.build();
		
		Optional<StNodoProceso> stNodoProceso = stNodoProcesoRepository.findById(id);
		
		if (stNodoProceso.isPresent() && referencia == null) {
			StNodoProceso to = stNodoProceso.get();
			aplicaInicio =  to.getInicializarVariables();
			query = to.getCondicionInicializacionVar();
		
			if (query != null && !query.isEmpty() && aplicaInicio.equals("SI") ) {
				EstatusVariablesTO reemplzav = reemplazaVariables(session, nodo, null, query);
				log.debug("xxxxxxxxxxxxxxxxxxxxxxxx> condi variables reemplzav: {}", reemplzav);
				referencia = areaTrabajoDAO.ejecutaQuerySimple(reemplzav.getCadenaSalida());
				log.debug("xxxxxxxxxxxxxxxxxxxxxxxx> condi variables referencia: {}", referencia);
				
			} else {
				return result;
			}
		}
		
		List<StValorInicialVariable> listConImagen = new ArrayList<StValorInicialVariable>();
		List<StValorInicialVariable> listSinImagen = new ArrayList<StValorInicialVariable>();
		
		listConImagen = stValorInicialVariableRepository.findValIniVariableCIByParam(entidad, claveProceso, version, referencia, nodo.getCveInstancia());
		listSinImagen = stValorInicialVariableRepository.findValIniVariableSIByParam(entidad, claveProceso, version, referencia, nodo.getCveInstancia());

		log.debug("----->LISTA DE VARIABLES: {}, LISTA DE IMAGENES {}", listSinImagen.size(), listConImagen.size());
		
		
		log.debug("-----> ITERAR LISTA DE VARIABLES");
		if (listSinImagen.size() > 0) {
			for (StValorInicialVariable ite : listSinImagen) {
				//log.info("-------> CVE_VARIABLE: {}", ite.getId().getCveVariable());
						
				InVariableProcesoPK idVar = InVariableProcesoPK.builder()
						.cveEntidad(entidad)
						.cveProceso(claveProceso)
						.version(version)
						.cveInstancia(claveInstancia)
						.cveVariable(ite.getId().getCveVariable())
						.ocurrencia(ite.getId().getOcurrencia())
						.secuenciaValor(ite.getId().getSecuenciaValor())
						.build();
						
						
				InVariableProceso entity = InVariableProceso.builder()
						.id(idVar)
						.valorAlfanumerico(ite.getValorAlfanumerico())
						.valorDecimal(ite.getValorDecimal())
						.valorEntero(ite.getValorEntero())
						.valorFecha(ite.getValorFecha())
						.build();
				
				log.debug("-------> VALOR ALFANUMERICO a guardar: {}", ite.getValorAlfanumerico());
				inVariableProcesoRepository.saveAndFlush(entity);
				
			}
			
			if (listConImagen.size() > 0) {
				for (StValorInicialVariable ite : listConImagen) {
					//log.info("-------> CVE_VARIABLE: {}", ite.getId().getCveVariable());
					
					InImagenProcesoPK idImg = InImagenProcesoPK.builder()
							.cveEntidad(entidad)
							.cveProceso(claveProceso)
							.version(version)
							.cveInstancia(claveInstancia)
							.cveVariable(ite.getId().getCveVariable())
							.ocurrencia(ite.getId().getOcurrencia())
							.secuenciaValor(ite.getId().getSecuenciaValor())
							.build();
							
							
					InImagenProceso entity = InImagenProceso.builder()
							.id(idImg)
							.valorImagen(ite.getValorImagen())
							.build();
					
					log.debug("-------> VALOR ALFANUMERICO a guardar: {}", ite.getValorAlfanumerico());
					inImagenProcesoRepository.saveAndFlush(entity);
										
				}
			}
			
		}
		
		return result;
	}
	
	@Override
	public void consultarNodoRepse(DatosAutenticacionTO session, NodoTO nodo, SaveSectionTO dataSections) {
	    // Constructor vacío
		// valida si se consulta el servicio de REPSE
		String cveNodo = "ACTIVIDAD-USUARIO";
		String cveProceso = "FACT_SERV_CONT";
		String razonSocial = "";
		String rfcContratista = "";
		int idNodo = 1;
		
	    log.info("**********INICIA consultarNodoRepse");
	    if(cveNodo.equals(nodo.getCveNodo()) && 
	    		cveProceso.equals(nodo.getCveProceso()) && 
	    		idNodo == nodo.getIdNodo()) {
	    	
	    	//String numContrato = dataSections.getData().get(0).getCveSection();
			String numContrato = dataSections.getData().stream() // Lista<DataSectionTO>
				    .flatMap(dataSection -> dataSection.getDataOccurrence().stream()) // Lista<DataOccurrenceTO>
				    .flatMap(dataOccurrence -> dataOccurrence.getSectionVariables().stream()) // Lista<SectionVariablesTO>
				    .filter(sectionVar -> "VPRO_01_NUM_CONTRATO".equals(sectionVar.getCveVariable())) // Filtrar por variable
				    .findFirst() // Tomar el primero que coincida
				    .map(sectionVar -> sectionVar.getValues().get(0)) // Obtener el primer valor de la lista
				    .orElse(null); // Si no existe, devuelve null

	    	
	    	List<Object[]> datosUsuario = inVariableProcesoRepository.getRfcAndNombreByProces(
	    			session.getCveEntidad(),
	    			session.getCveUsuario());
	    			if (datosUsuario.size() > 0) {
	    				razonSocial = datosUsuario.get(0)[1] != null ? datosUsuario.get(0)[1].toString() : "";
	    				rfcContratista = datosUsuario.get(0)[0] != null ? datosUsuario.get(0)[0].toString() : "";
	    			} else {
	    				return;
	    			}
	        log.info("**********ES NODO REPSE, SE CONSULTA SERVICIO");
	        try {
	            // Llamada al servicio externo para obtener la información del REPSE
	        	String jsonResponse = temporizadorHelper.consultaRepsePorCurl(razonSocial, Boolean.TRUE);
	        	ObjectMapper mapper = new ObjectMapper();
	        	JsonNode root = mapper.readTree(jsonResponse);
	        	boolean error = !root.path("success").asBoolean();                
                String mensajeError = root.path("mensaje").asText("");
                log.info("**********PRIMER RESPUESTA SERVICIO REPSE - ERROR: " + error + " MENSAJE: " + mensajeError);	
	        	// 2. Parse JSON response (only if error == false)
                if (error) {                	
                	jsonResponse = temporizadorHelper.consultaRepsePorCurl(razonSocial, Boolean.TRUE);
                	mapper = new ObjectMapper();
         	        root = mapper.readTree(jsonResponse);
         	        error = !root.path("success").asBoolean();                
         	        mensajeError = root.path("mensaje").asText("");
                    log.info("**********SEGUNDA RESPUESTA SERVICIO REPSE - ERROR: " + error + " MENSAJE: " + mensajeError);	
	        	
         	       /* if (error) {
         	        	CrConsultaRepsePK pk = CrConsultaRepsePK.builder()
         		                   .cveEntidad(nodo.getCveEntidad())
         		                   .cveProceso(nodo.getCveProceso())
         		                   .rfcContratista(rfcContratista)
         		                   .numContrato(numContrato)
         		                   .fechaConsulta(new Date())	                    
         		                   .build();
         	        	return;
         	        } */    				
				}
                CrConsultaRepsePK pk = CrConsultaRepsePK.builder()
	                    .cveEntidad(nodo.getCveEntidad())
	                    .cveProceso(nodo.getCveProceso())
	                    .rfcContratista(rfcContratista)
	                    .numContrato(numContrato)
	                    .fechaConsulta(new Date())	                    
	                    .build();
           	
	        	temporizadorHelper.parseAndSaveRepseJson(session, pk, root, nodo.getCveNodo(), nodo.getCveInstancia());
	        } catch (Exception e) {
	            log.error("**********ERROR AL CONSULTAR EL SERVICIO REPSE: " + e.getMessage());
	        }
	    } else {
	        log.info("**********NO ES NODO REPSE, NO SE CONSULTA SERVICIO");
	    }
	}

}
