/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.business.helpers;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.briomax.briobpm.business.convertes.IStNodoInicioProcesoConverter;
import com.briomax.briobpm.business.convertes.InStMensajeEnvioConverter;
import com.briomax.briobpm.business.helpers.base.IFechaHelper;
import com.briomax.briobpm.business.helpers.base.INodoHelper;
import com.briomax.briobpm.business.helpers.base.IProcesoHelper;
import com.briomax.briobpm.business.service.catalogs.core.IMessagesService;
import com.briomax.briobpm.common.core.Constants;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.persistence.dao.base.IAreaTrabajoDAO;
import com.briomax.briobpm.persistence.entity.BitacoraBatch;
import com.briomax.briobpm.persistence.entity.BitacoraBatchPK;
import com.briomax.briobpm.persistence.entity.Entidad;
import com.briomax.briobpm.persistence.entity.InFolioProceso;
import com.briomax.briobpm.persistence.entity.InFolioProcesoPK;
import com.briomax.briobpm.persistence.entity.InImagenProceso;
import com.briomax.briobpm.persistence.entity.InImagenProcesoPK;
import com.briomax.briobpm.persistence.entity.InMensajeEnvioPK;
import com.briomax.briobpm.persistence.entity.InNodoProceso;
import com.briomax.briobpm.persistence.entity.InProceso;
import com.briomax.briobpm.persistence.entity.InProcesoPK;
import com.briomax.briobpm.persistence.entity.InVariableProceso;
import com.briomax.briobpm.persistence.entity.InVariableProcesoPK;
import com.briomax.briobpm.persistence.entity.StNodoInicioProceso;
import com.briomax.briobpm.persistence.entity.StProceso;
import com.briomax.briobpm.persistence.entity.StProcesoPK;
import com.briomax.briobpm.persistence.entity.StValorInicialVariable;
import com.briomax.briobpm.persistence.entity.TipoNodo;
import com.briomax.briobpm.persistence.repository.IBitacoraBatchRepository;
import com.briomax.briobpm.persistence.repository.IEntidadRepository;
import com.briomax.briobpm.persistence.repository.IInFolioProcesoRepository;
import com.briomax.briobpm.persistence.repository.IInImagenProcesoRepository;
import com.briomax.briobpm.persistence.repository.IInMensajeEnvioRepository;
import com.briomax.briobpm.persistence.repository.IInNodoProcesoRepository;
import com.briomax.briobpm.persistence.repository.IInProcesoRepository;
import com.briomax.briobpm.persistence.repository.IInVariableProcesoRepository;
import com.briomax.briobpm.persistence.repository.IStNodoInicioProcesoRepository;
import com.briomax.briobpm.persistence.repository.IStProcesoRepository;
import com.briomax.briobpm.persistence.repository.IStValorInicialVariableRepository;
import com.briomax.briobpm.persistence.repository.ITipoNodoRepository;
import com.briomax.briobpm.transferobjects.DataOccurrenceTO;
import com.briomax.briobpm.transferobjects.DataSectionTO;
import com.briomax.briobpm.transferobjects.NodoInicioProcesoTO;
import com.briomax.briobpm.transferobjects.NodoInicioTO;
import com.briomax.briobpm.transferobjects.SaveSectionTO;
import com.briomax.briobpm.transferobjects.SectionVariablesTO;
import com.briomax.briobpm.transferobjects.in.ActividadTO;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;
import com.briomax.briobpm.transferobjects.in.ElementoCadenaTO;
import com.briomax.briobpm.transferobjects.in.EstatusCreaInstanciaTO;
import com.briomax.briobpm.transferobjects.in.EstatusFolioTO;
import com.briomax.briobpm.transferobjects.in.EstatusTO;
import com.briomax.briobpm.transferobjects.in.EstatusVariablesTO;
import com.briomax.briobpm.transferobjects.in.NodoTO;
import com.briomax.briobpm.transferobjects.in.ProcesoInicialTO;
import com.briomax.briobpm.transferobjects.in.StInMensajeTO;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la clase ProcesoHelper.java es proporcionar métodos de ayuda relacionados con los procesos.
 *
 * @author Alexis Zamora
 * @ver 1.0
 * @fecha Feb 14, 2023 4:12:01 PM
 * @since JDK 1.8
 */
@Slf4j
@Service
@Transactional
public class ProceoHelper implements IProcesoHelper {
	
	/** La Constante DATA_ERROR. */
	private static final String ID_PROCESO = "CREA_INSTANCIA_PROCESO";
	
	/** El atributo o variable folio Proceso. */
	@Autowired
    private IInFolioProcesoRepository folioProcesoRepository;

	/** El atributo o variable messages service. */
	@Autowired
	private IMessagesService messagesService;
	
	/** El atributo o variable St_Proceso repository. */
	@Autowired
	private IStProcesoRepository stProcesoRepository;

	
	/** El atributo o variable In_Proceso repository. */
	@Autowired
	private IInProcesoRepository inProcesoRepository;
	
	/** El atributo o variable Tipo_Nodo repository. */
	@Autowired
	private ITipoNodoRepository tipoNodoRepository;
	
	/** El atributo o variable Valor Inicial Variable repository. */
	@Autowired
	private  IStValorInicialVariableRepository stValorInicialVariableRepository;
	
	/** El atributo o variable Valorin Variable Proceso repository. */
	@Autowired
	private  IInVariableProcesoRepository inVariableProcesoRepository;
	
	/** El atributo o variable Valorin Variable Proceso repository. */
	@Autowired
	private  IInImagenProcesoRepository inImagenProcesoRepository;
	
	/** El atributo o variable In Mensaje Recepcion Repository. */
	@Autowired
	IBitacoraBatchRepository bitacoraBatchRepository;
	
	/** El atributo o variable st Nodo Inicio ProcesoRepository. */
	@Autowired
	IStNodoInicioProcesoRepository stNodoInicioProcesoRepository;
	
	/** El atributo o variable in Nod oProceso Repository. */
	@Autowired
	IInNodoProcesoRepository inNodoProcesoRepository;
	
	/** El atributo o variable in nodo helper. */
	@Autowired
	INodoHelper iNodoHelper;
		
	/** El atributo o variable iMensajeEnvioRepository. */
	@Autowired
	IInMensajeEnvioRepository iMensajeEnvioRepository;
	
	@Autowired
	private IEntidadRepository entidadRepository;
	
	/** El atributo o variable para ejecutar querys. */
	@Autowired  
	IAreaTrabajoDAO areaTrabajoDAO;
	
	@Autowired
	IFechaHelper fechaHelper;
	
	/**
	 * Crear una nueva instancia del objeto entidad helper.
	 */
	public ProceoHelper() {
	}

	// SP_VAL_DATOS_ST_PROCESO
	/** 
	 * {@inheritDoc}
	 * @see com.briomax.briobpm.business.helpers.base.IProcesoHelper#getValidaDatos(com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO)
	 */
	@Override
	public EstatusTO getValidaDatos(DatosAutenticacionTO session, String claveProceso, BigDecimal version) throws BrioBPMException {
		EstatusTO result = EstatusTO.builder()
				.tipoExcepcion(Constants.OK)
				.mensaje("")
				.build();
		StProcesoPK id = StProcesoPK.builder()
				.cveEntidad(session.getCveEntidad())
				.cveProceso(claveProceso)
				.version(version)
				.build();
		Optional<StProceso> proceso = stProcesoRepository.findById(id);
		if (proceso.isPresent()) {
			result.setTipoExcepcion(Constants.OK);
		}else {
			String variablesValores = Constants.CVE_PROCESO + claveProceso + "|" + Constants.VERSION + version;
			String mensaje = messagesService.getMessage(session, ID_PROCESO, "PROCESO_VERSION_NO_EXISTEN", variablesValores);
			result.setTipoExcepcion(Constants.ERROR);
			result.setMensaje(mensaje);
		}
        log.info("############### RETURN SP_VAL_DATOS_ST_PROCESO: " + result.toString());
		return result;
	}

	// SP_INS_IN_PROCESO
	/** 
	 * {@inheritDoc}
	 * @see com.briomax.briobpm.business.helpers.base.IProcesoHelper#setInsetInProceso(com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO, 
	 * 		com.briomax.briobpm.transferobjects.in.ProcesoInicialTO)
	 */
	@Override
	public EstatusTO setInsInProceso(DatosAutenticacionTO session, ProcesoInicialTO procesoInicial) throws BrioBPMException {
		
		log.debug("############### INICIA SP_INS_IN_PROCESO");
		EstatusTO result = EstatusTO.builder()
				.tipoExcepcion(Constants.OK)	
				.mensaje("")
				.build();
		
		InProcesoPK id = InProcesoPK.builder()
				.cveEntidad(session.getCveEntidad())
				.cveProceso(procesoInicial.getCveProceso())
				.version(procesoInicial.getVersion())
				.cveInstancia(procesoInicial.getCveInstancia())
				.build();
		
		TipoNodo nodoIni = tipoNodoRepository.findById(procesoInicial.getCveNodoInicial()).get();
		TipoNodo nodoFin = tipoNodoRepository.findById(procesoInicial.getCveNodoFinal()).get();
		
		InProceso entity = InProceso.builder()
				.id(id)
				.concepto(procesoInicial.getConcepto())
				.fechaCreacion(procesoInicial.getFecCreacion())
				.origen(procesoInicial.getOrigen())
				.cveEntidadCreadora(session.getCveEntidad())
				.cveLocalidaCreadora(session.getCveLocalidad())
				.rolCreador(procesoInicial.getCveRol())
				.usuarioCreador(session.getCveUsuario())
				.situacion(procesoInicial.getSituacion())
				.cveNodoInicio(nodoIni)
				.idNodoInicio(procesoInicial.getIdNodoInicial())
				.cveNodoFin(nodoFin)
				.idNodoFin(procesoInicial.getIdNodoFinal())
				.build();
		
		inProcesoRepository.saveAndFlush(entity);


		Optional<InProceso> proceso = inProcesoRepository.findById(id);
		if (proceso.isPresent()) {
			result.setTipoExcepcion(Constants.OK);
		}else {
			String variablesValores = Constants.CVE_PROCESO + procesoInicial.getCveProceso() + "|" +
				    Constants.VERSION + procesoInicial.getVersion() +  "|" +
				    Constants.CVE_INSTANCIA + procesoInicial.getCveInstancia() + "|" +
				    Constants.CONCEPTO + procesoInicial.getConcepto() + "|" +
				    Constants.FECHA_CREACION + procesoInicial.getFecCreacion() + "|" +
				    Constants.ORIGEN + procesoInicial.getOrigen() + "|" +
				    Constants.ROL_CREADOR + procesoInicial.getCveRol() + "|" +
				    Constants.USUARIO_CREADOR + session.getCveUsuario() + "|" +
				    Constants.SITUACION + procesoInicial.getSituacion() + "|" +
				    Constants.CVE_NODO_INICIO + procesoInicial.getCveNodoInicial() + "|" +
				    Constants.ID_NODO_INICIO + procesoInicial.getIdNodoInicial() + "|" +
				    Constants.CVE_NODO_FIN + procesoInicial.getCveNodoFinal() + "|" +
				    Constants.ID_NODO_FIN + procesoInicial.getIdNodoFinal() + "|";

			String mensaje = messagesService.getMessage(session, ID_PROCESO, "ERR-INS-TAB-IN_PROCESO", variablesValores);
			result.setTipoExcepcion(Constants.ERROR);
			result.setMensaje(mensaje);
		}
        log.info("############### RETURN SP_INS_IN_PROCESO: " + result.toString());
		return result;
	}

	// SP_CREA_VARIABLES_PROCESO
	/** 
	 * {@inheritDoc}
	 * @see com.briomax.briobpm.business.helpers.base.IProcesoHelper#setCreaVariables(com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO)
	 */
	@Override
	public EstatusTO setCreaVariables(DatosAutenticacionTO session, String claveInstancia, String claveProceso, 
			BigDecimal version, String referencia) throws BrioBPMException {
		
		log.debug("############### INICIA SP_CREA_VARIABLES_PROCESO");
		EstatusTO result = EstatusTO.builder()
				.tipoExcepcion(Constants.OK)	
				.mensaje("")
				.build();
		List<StValorInicialVariable> listConImagen = new ArrayList<StValorInicialVariable>();
		List<StValorInicialVariable> listSinImagen = new ArrayList<StValorInicialVariable>();
		
		listConImagen = stValorInicialVariableRepository.findValorInicialVariableCIByParam(session.getCveEntidad(), claveProceso, version);
		listSinImagen = stValorInicialVariableRepository.findValorInicialVariableSIByParam(session.getCveEntidad(), claveProceso, version);
			
		log.debug("----->LISTA DE VARIABLES: {}, LISTA DE IMAGENES {}", listSinImagen.size(), listConImagen.size());
		
		log.debug("-----> ITERAR LISTA DE VARIABLES");
		if (listSinImagen.size() > 0) {
			for (StValorInicialVariable ite : listSinImagen) {
				log.info("-------> CVE_VARIABLE: {}", ite.getId().getCveVariable());
						
				InVariableProcesoPK id = InVariableProcesoPK.builder()
						.cveEntidad(session.getCveEntidad())
						.cveProceso(claveProceso)
						.version(version)
						.cveInstancia(claveInstancia)
						.cveVariable(ite.getId().getCveVariable())
						.ocurrencia(ite.getId().getOcurrencia())
						.secuenciaValor(ite.getId().getSecuenciaValor())
						.build();
						
						
				InVariableProceso entity = InVariableProceso.builder()
						.id(id)
						.valorAlfanumerico(ite.getValorAlfanumerico())
						.valorDecimal(ite.getValorDecimal())
						.valorEntero(ite.getValorEntero())
						.valorFecha(ite.getValorFecha())
						.build();
				
				log.debug("-------> VALOR ALFANUMERICO a guardar: {}", ite.getValorAlfanumerico());
				inVariableProcesoRepository.saveAndFlush(entity);
				
				Optional<InVariableProceso> proceso = inVariableProcesoRepository.findById(id);
				if (proceso.isPresent()) {
					result.setTipoExcepcion(Constants.OK);
				}else {
					String variablesValores = Constants.CVE_PROCESO + claveProceso + "|" +
							Constants.VERSION + version + "|" +
							Constants.CVE_INSTANCIA + claveInstancia + "|" +
							Constants.CVE_VARIABLE + ite.getId().getCveVariable();
					
					String mensaje = messagesService.getMessage(session, ID_PROCESO, "ERR_INS_TAB_IN_VARIABLE_PROCESO", variablesValores);
					result.setTipoExcepcion(Constants.ERROR);
					result.setMensaje(mensaje);
					break;
				}
			}
			
			if (listConImagen.size() > 0) {
				for (StValorInicialVariable ite : listConImagen) {
					log.info("-------> CVE_VARIABLE: {}", ite.getId().getCveVariable());
					
					InImagenProcesoPK id = InImagenProcesoPK.builder()
							.cveEntidad(session.getCveEntidad())
							.cveProceso(claveProceso)
							.version(version)
							.cveInstancia(claveInstancia)
							.cveVariable(ite.getId().getCveVariable())
							.ocurrencia(ite.getId().getOcurrencia())
							.secuenciaValor(ite.getId().getSecuenciaValor())
							.build();
							
							
					InImagenProceso entity = InImagenProceso.builder()
							.id(id)
							.valorImagen(ite.getValorImagen())
							.build();
					
					//log.debug("-------> VALOR ALFANUMERICO a guardar: {}", ite.getValorAlfanumerico());
					inImagenProcesoRepository.saveAndFlush(entity);
					
					Optional<InImagenProceso> proceso = inImagenProcesoRepository.findById(id);
					if (proceso.isPresent()) {
						result.setTipoExcepcion(Constants.OK);
					}else {
						String variablesValores = Constants.CVE_PROCESO + claveProceso + "|" +
								Constants.VERSION + version + "|" +
								Constants.CVE_INSTANCIA + claveInstancia + "|" +
								Constants.CVE_VARIABLE + ite.getId().getCveVariable();
						
						String mensaje = messagesService.getMessage(session, ID_PROCESO, "ERR_INS_TAB_IN_VARIABLE_PROCESO", variablesValores);
						result.setTipoExcepcion(Constants.ERROR);
						result.setMensaje(mensaje);
						break;
					}
					
				}
			}
			
		}
		
		return result;
	}
	
	
	// SP_CREA_VARIABLES_PROCESO
	/** 
	 * {@inheritDoc}
	 * @see com.briomax.briobpm.business.helpers.base.IProcesoHelper#setCreaVariables(com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO)
	 */
	@Override
	public EstatusTO setCreaVariablesServicio(DatosAutenticacionTO session, String claveInstancia, String claveProceso, 
			BigDecimal version, String referencia) throws BrioBPMException {
		
		log.debug("############### INICIA SP_CREA_VARIABLES_PROCESO");
		EstatusTO result = EstatusTO.builder()
				.tipoExcepcion(Constants.OK)	
				.mensaje("")
				.build();
		
		List<SectionVariablesTO> listVariables=  separarCadena(referencia);
					
		//log.debug("----->LISTA DE VARIABLES: {}, LISTA DE IMAGENES {}", listSinImagen.size(), listConImagen.size());
		
		log.debug("-----> ITERAR LISTA DE VARIABLES");
	
			if (listVariables.size() > 0) {
				for (SectionVariablesTO ite : listVariables) {
					log.info("-------> CVE_VARIABLE: {}", ite.getCveVariable());
							
					InVariableProcesoPK id = InVariableProcesoPK.builder()
							.cveEntidad(session.getCveEntidad())
							.cveProceso(claveProceso)
							.version(version)
							.cveInstancia(claveInstancia)
							.cveVariable(ite.getCveVariable())
							.ocurrencia(1)
							.secuenciaValor(1)
							.build();
					
					String tipo = detectarTipo( ite.getCveVariable());
					String valorDecimal  = null;
					String valorEntero = null;
					Date valorFecha = null;
					String valorAlfanumerico = null;
					
					switch (tipo) {
					case "ALFANUMERICO":
						valorAlfanumerico = ite.getValues().get(0);
						break;
					case "ENTERO":
						valorEntero = ite.getValues().get(0);
						break;
					case "DECIMAL":
						valorDecimal = ite.getValues().get(0);
						break;
					case "FECHA":
						String formatoDeFecha = "dd/MM/yyyy";
				        try {
				            // 1. Crea un objeto SimpleDateFormat con el formato de la cadena
				            SimpleDateFormat formatter = new SimpleDateFormat(formatoDeFecha);
				            // 2. Usa el método parse() para convertir el String a Date
				            Date fechaConvertida = formatter.parse(ite.getValues().get(0));
				            valorFecha = fechaConvertida;
				        } catch (ParseException e) {
				            System.err.println("Error al parsear la fecha: " + e.getMessage());
				        }
						
						break;
					}
							
							
					InVariableProceso entity = InVariableProceso.builder()
							.id(id)
							.valorAlfanumerico(valorAlfanumerico != null ? valorAlfanumerico : null)
							.valorDecimal(valorDecimal != null ? new BigDecimal(valorDecimal) : null)
							.valorEntero(valorEntero != null ? Integer.valueOf(valorEntero) : null)
							.valorFecha(valorFecha != null ? valorFecha : null)
							.build();
					
					// log.debug("-------> VALOR ALFANUMERICO a guardar: {}", ite.getValorAlfanumerico());
					inVariableProcesoRepository.saveAndFlush(entity);
					
					Optional<InVariableProceso> proceso = inVariableProcesoRepository.findById(id);
					if (proceso.isPresent()) {
						result.setTipoExcepcion(Constants.OK);
					}else {
						String variablesValores = Constants.CVE_PROCESO + claveProceso + "|" +
								Constants.VERSION + version + "|" +
								Constants.CVE_INSTANCIA + claveInstancia + "|" +
								Constants.CVE_VARIABLE + ite.getCveVariable();
						
						String mensaje = messagesService.getMessage(session, ID_PROCESO, "ERR_INS_TAB_IN_VARIABLE_PROCESO", variablesValores);
						result.setTipoExcepcion(Constants.ERROR);
						result.setMensaje(mensaje);
						break;
					}
				}
			}

		
		return result;
	}
	
	// SP_CREA_FOLIO_IN_PROCESO
    @Override
    public EstatusFolioTO creaFolio(DatosAutenticacionTO session, String cveProceso,
    		BigDecimal version) throws BrioBPMException {
    	
    	String cveInstancia;
    	String mesStr;
    	String folio;
    	Integer longitud;
    	
    	// INICIALIZA VARIABLES
    	String cveEntidad = session.getCveEntidad();
    	EstatusFolioTO result = EstatusFolioTO.builder()
        		.tipoExcepcion(Constants.OK)
        		.mensaje("")
        		.build();
        
        // INICIALIZA LA CLAVE DE LA INSTANCIA
    	cveInstancia = "";
    	result.setCveInstancia(cveInstancia);
    	
    	// OBTIENE LA FECHA DEL SISTEMA
        InFolioProceso folioProceso = null;
        Optional<InFolioProceso> folioProcesoOptional = null;

        //log.info("----->FOLIO PROCESO: ");
        LocalDate fechaActual = LocalDate.now();
        //log.info("----->FECHA ACTUAL: " + fechaActual);
        Integer anio = fechaActual.getYear();
        Integer mes = fechaActual.getMonthValue();
        mesStr = mes.toString();
        
        if(mes < 10) {
        	mesStr = "0" + mes.toString().trim();
        }
        
        // CONCATENA LOS PARÁMETROS Y SUS VALORES PARA DESPLEGARLOS EN CASO DE ERROR
        String variablesValores = Constants.CVE_PROCESO + cveProceso + "|" +
        						  Constants.VERSION + version +
        						  Constants.ANIO + anio + "|" +
        						  Constants.MES + mes ;
        
        // BUSCA UN FOLIO EN LA TABLA IN_FOLIO_PROCESO
        //log.info("----->BUSCA UN FOLIO EN LA TABLA IN_FOLIO_PROCESO ");
        InFolioProcesoPK id = InFolioProcesoPK.builder()
        				.cveEntidad(cveEntidad)
        				.cveProceso(cveProceso)
        				.version(version)
        				.anio(anio)
        				.mes(mes)
        				.build();
        
        Integer folioActual = 0;
        folioProcesoOptional = folioProcesoRepository.findById(id);
        if (!folioProcesoOptional.isPresent()) {
        	folioActual = 1;
        	folioProceso = new InFolioProceso();
        	folioProceso.setId(id);
        	folioProceso.setFolio(folioActual);
            folioProcesoRepository.saveAndFlush(folioProceso);
            folioProcesoOptional = folioProcesoRepository.findById(id);
            if(!folioProcesoOptional.isPresent()) {
            	result.setTipoExcepcion(Constants.ERROR);
    			String mensaje = messagesService.getMessage(
    					session, "CREA_FOLIO_IN_PROCESO",
    					 "ERR-INS-TAB-IN_FOLIO_PROCESO", variablesValores);
    			result.setMensaje(mensaje);
            }
            folioProceso = folioProcesoOptional.get();
            if (folioProceso.getFolio() == folioActual) {
            	result.setTipoExcepcion(Constants.OK);
            }
        } else {
        	folioProceso = folioProcesoOptional.get();
        	folioActual = folioProceso.getFolio();
        	folioActual = folioActual + 1;
            folioProceso.setFolio(folioActual);
            folioProcesoRepository.saveAndFlush(folioProceso);
            folioProcesoOptional = folioProcesoRepository.findById(id);
            
            if (folioProcesoOptional.isPresent()) {
            	result.setTipoExcepcion(Constants.OK);
            } else {
            	result.setTipoExcepcion(Constants.ERROR);
    			String mensaje = messagesService.getMessage(
    					session, "CREA_FOLIO_IN_PROCESO",
    					 "ERR-UPD-TAB-IN_FOLIO_PROCESO", variablesValores);
    			result.setMensaje(mensaje);
            }
        
        }	
        folio = folioActual.toString();
        longitud = folio.trim().length();
        cveInstancia = anio + mesStr + "-" + String.format("%0" + (6 - longitud) + "d%s", 0, folio.trim());
        log.info("-------> CLAVE INSTANCIA: " + cveInstancia);
        result.setCveInstancia(cveInstancia);
        log.info("############### RETURN SP_CREA_FOLIO_IN_PROCESO: " + result.toString());
        return result;
    }

    
	//SP_LEE_NODO_INICIO_PROCESO
	@Override
	public NodoInicioTO leeNodoInicioProceso(DatosAutenticacionTO session, NodoInicioProcesoTO nodoProceso,
			String origen, String valoresReferenciaEnvio) throws BrioBPMException, ParseException {
		
		String cveEntidad = session.getCveEntidad();
		String cveProceso = nodoProceso.getCveProceso();
		BigDecimal version = nodoProceso.getVersion();
		String idProceso;
		String idProcesoMensajes;
		String variablesReferenciaRec;
		String valoresReferenciaRec;
		String existeNodoInicio;
		String cveNodoInicio;
		Integer idNodoInicio;
		String mensaje;

		// INICIALIZA CÓDIGO DE ERROR, MENSAJE
		NodoInicioTO result = NodoInicioTO.builder()
					.tipoExcepcion(Constants.OK)
					.mensaje("")
					.build();
		
		idProceso = "LEE_NODO_INICIO_PROCESO";
		idProcesoMensajes = "PROCESA_MENSAJES";
		
		// CONSTANTES
		String variableValor = 
				Constants.CVE_ENTIDAD + cveEntidad + "|" +
				Constants.CVE_LOCALIDAD + session.getCveLocalidad()+ "|" +
				Constants.CVE_PROCESO + cveProceso + "|" +
				Constants.VERSION + version + "|" +
				Constants.ORIGEN + origen;  
		String origenMensaje = Constants.EVENTO_INICIO;
		if(Constants.ORIGEN_MENSAJE_SERVICIO.equals(origen)) {
			origenMensaje = Constants.EVENTO_INICIO_MENSAJE;
		}
		// LISTA DE NODOS DE INICIO DE UN PROCESO
		List<NodoInicioProcesoTO> listDto = new ArrayList<NodoInicioProcesoTO>();
		List<StNodoInicioProceso> listEntity =  stNodoInicioProcesoRepository.listaNodosInicio(
				cveEntidad,
				cveProceso,
				version,
				origenMensaje);
		log.info("----->TERMINA LISTA");
		listDto.addAll(listEntity.stream().map(IStNodoInicioProcesoConverter.converterEntityToDTO).collect(Collectors.toList()));

		// DETERMINA EL NODO DE INICIO QUE DEBE APLICARSE
		existeNodoInicio = Constants.NO;

		// SP_REEMPLAZA_VARIABLES
		// declaro aqui esto para que no se ejecute dentro del bucle
		BitacoraBatchPK id = BitacoraBatchPK.builder()
				.cveProcresoBatch(idProcesoMensajes)
				.cveSubProcresoBatch(idProceso)
				.fechaMensaje(new Date())
				.build();
		NodoTO nodo = NodoTO.builder()
				.idProceso(idProceso)
				.cveProceso(cveProceso)
				.version(version)
				.cveInstancia(null)
				.cveNodo(null)
				.idNodo(null)
				.secuenciaNodo(null)
				.ocurrencia(1)
				.build();
		for(NodoInicioProcesoTO to: listDto) {
			cveNodoInicio = to.getCveNodo();
			idNodoInicio = to.getIdNodo();
			variablesReferenciaRec = to.getVariablesReferenciaRec();
			if(Constants.ORIGEN_MENSAJE_SERVICIO.equals(origen )) {
				variablesReferenciaRec = valoresReferenciaEnvio;
			}
			result.setCveNodo(cveNodoInicio);
			result.setIdNodo(idNodoInicio);
			log.info("-------> REEMPLAZA VARIABLES EN LEE NODO INCIO PROCESO");
			EstatusVariablesTO variableTo = iNodoHelper.reemplazaVariables(
					session, nodo, null, variablesReferenciaRec);
			valoresReferenciaRec = variableTo.getValoresReferenciaRec();
			log.info("-------> REEMPLAZA VARIABLES EN LEE NODO INCIO PROCESO {} \n -------> valoresReferenciaRec {}", variableTo.toString(), valoresReferenciaRec);
			
			if(Constants.ERROR.equals(variableTo.getTipoExcepcion())) {
				if(origen.equals(Constants.ORIGEN_MENSAJE)) {
					//INSERT INTO		BITACORA_BATCH
					BitacoraBatch batch = BitacoraBatch.builder()
							.id(id)
							.tipoMensaje(Constants.ERROR)
							.mensajePrincipal(variableTo.getMensaje())
							.mensajeAuxiliar(null)
							.cveEntidad(cveEntidad)
							.cveProceso(cveProceso)
							.version(version)
							.cveInstancia(null)
							.cveNodo(null)
							.idNodo(null)
							.secuenciaNodo(null)
							.build();
					bitacoraBatchRepository.saveAndFlush(batch);
				}
			} 

			// si todo es null o todo tiene valor
			if (((valoresReferenciaEnvio == null || valoresReferenciaEnvio.trim().isEmpty()) &&
				     (valoresReferenciaRec == null || valoresReferenciaRec.trim().isEmpty())) ||
				    ((valoresReferenciaEnvio != null && !valoresReferenciaEnvio.trim().isEmpty()) &&
				     (valoresReferenciaRec != null && !valoresReferenciaRec.trim().isEmpty()) &&
				     valoresReferenciaEnvio.trim().equals(valoresReferenciaRec.trim()))||
				    (Constants.ORIGEN_MENSAJE_SERVICIO.equals(origen))) {
				
				existeNodoInicio = Constants.SI;
			}
			if(Constants.SI.equals(existeNodoInicio)) {
				break;
			}
		}
		if(existeNodoInicio.equals(Constants.NO)) {
			cveNodoInicio = null;
			idNodoInicio = null;
			result.setCveNodo(cveNodoInicio);
			result.setIdNodo(idNodoInicio);
			mensaje = messagesService.getMessage(
					session,
					idProceso,
					"NO-EXISTE-NODO-INICIO",
					variableValor);
			
			if(origen.equals(mensaje)) {
				BitacoraBatch batch2 = BitacoraBatch.builder()
						.id(id)
						.tipoMensaje(Constants.ERROR)
						.mensajePrincipal(mensaje)
						.mensajeAuxiliar(null)
						.cveEntidad(cveEntidad)
						.cveProceso(cveProceso)
						.version(version)
						.cveInstancia(null)
						.cveNodo(null)
						.idNodo(null)
						.secuenciaNodo(null)
						.build();
				bitacoraBatchRepository.saveAndFlush(batch2);
				
				result.setTipoExcepcion(Constants.ERROR);
				result.setMensaje(mensaje);
			}
		}
        log.info("############### RETURN SP_LEE_NODO_INICIO_PROCESO: " + result.toString());
		return result;
	}
    
    
    // SP_CREA_INSTANCIA_PROCESO
	@Override
	public EstatusCreaInstanciaTO creaInstancia(DatosAutenticacionTO session, ProcesoInicialTO instanciaNodo,
			String valoresReferenciaEnvio, Integer folioMensajeEnvio) throws BrioBPMException, ParseException {

		log.debug("############### INICIA SP_CREA_INSTANCIA_PROCESO");
		String cveEntidad = session.getCveEntidad();
		String cveLocalidad = session.getCveLocalidad();
		String cveProceso = instanciaNodo.getCveProceso();
		BigDecimal version = instanciaNodo.getVersion();
		String concepto = instanciaNodo.getConcepto();
		String origen = instanciaNodo.getOrigen();
		String rolCreador = instanciaNodo.getCveRol();
		String cveNodoInicio = "";
		Integer idNodoInicio  = null;
		String cveNodoFin = "";
		Integer idNodoFin = null;
		Date fechaCreacion;
		String variableValor;
		String idProceso;
		String mensaje;
		String cveInstancia;
		
		// INICIALIZA CÓDIGO DE ERROR, MENSAJE Y SUGERENCIA
		EstatusCreaInstanciaTO result = EstatusCreaInstanciaTO.builder()
				.tipoExcepcion(Constants.OK)
				.mensaje("")
				.build();
		idProceso = "CREA_INSTANCIA_PROCESO";
		
		// INICIALIZA CLAVE DE LA INSTANCIA
		result.setCveInstancia("");
		
		// VALIDA EXISTENCIA DEL PROCESO
		EstatusTO valDSP = getValidaDatos(session, cveProceso, version);
		result.setMensaje(valDSP.getMensaje());
		result.setTipoExcepcion(valDSP.getTipoExcepcion());
		if(valDSP.getTipoExcepcion().equals(Constants.ERROR)) {
			return result;
		}
		
		variableValor = Constants.CVE_PROCESO + cveProceso + "|" +
						Constants.VERSION + version; 
		
		// VALIDA QUE LA SITUACIÓN DEL PROCESO ESTÁTICO SEA "HABILITADO" Y OBTIENE EL NODO FINAL
		/*cveNodoInicio = ""; se inilina para inicuialializarlas al inicio
		idNodoInicio = null;
		cveNodoFin = "";
		idNodoFin = null;*/
		
		Optional<StProceso> stP = stProcesoRepository.obtenerDatos(
				cveEntidad, cveProceso, version, Constants.HABILITADO);
		if(!stP.isPresent()) {
			mensaje = messagesService.getMessage(
					session,
					idProceso,
					"PROCESO_NO_HABILITADO",
					variableValor);
			result.setTipoExcepcion(Constants.ERROR);
			result.setMensaje(mensaje);
			return result;
		}
		
		StProceso stProceso = stP.get();
		cveNodoFin = stProceso.getCveNodoFin().getCveNodo();
		idNodoFin = stProceso.getIdNodoFin();
		
		// OBTIENE EL NODO INICIAL DEL ACUERDO AL ORIGEN
		NodoInicioProcesoTO nodoProceso = NodoInicioProcesoTO.builder()
				.cveProceso(cveProceso)
				.version(version)
				.build();
		
		log.debug("-------> LEE NODO INICIO PROCESO");
		NodoInicioTO leeNIP = leeNodoInicioProceso(session, nodoProceso, origen, valoresReferenciaEnvio);
		result.setTipoExcepcion(leeNIP.getTipoExcepcion());
		result.setMensaje(leeNIP.getMensaje());
		if(leeNIP.getTipoExcepcion().equals(Constants.ERROR)) {
			return result;
		}
		cveNodoInicio = leeNIP.getCveNodo();
		idNodoInicio = leeNIP.getIdNodo();
		
		// CREA EL FOLIO DE UNA INSTANCIA
		EstatusFolioTO creaFIP = creaFolio(session, cveProceso, version);
		result.setTipoExcepcion(creaFIP.getTipoExcepcion());
		result.setMensaje(creaFIP.getMensaje());
		if(creaFIP.getTipoExcepcion().equals(Constants.ERROR)) {
			return result; // rollback
		}
		cveInstancia = creaFIP.getCveInstancia();
		result.setCveInstancia(cveInstancia);
		
		// CREA LA INSTANCIA DEL PROCESO
		fechaCreacion = fechaHelper.obtenerFechaCreacion(cveEntidad, cveLocalidad);
		ProcesoInicialTO procesoInicial = ProcesoInicialTO.builder()
				.idProceso(idProceso)
				.cveProceso(cveProceso)
				.version(version)
				.cveInstancia(cveInstancia)
				.concepto(concepto)
				.fecCreacion(fechaCreacion)
				.origen(origen)
				.cveRol(rolCreador)
				.situacion(Constants.REGISTRADO)
				.cveNodoInicial(cveNodoInicio)
				.idNodoInicial(idNodoInicio)
				.cveNodoFinal(cveNodoFin)
				.idNodoFinal(idNodoFin)
				.build();
		EstatusTO insIP = setInsInProceso(session, procesoInicial);
		result.setTipoExcepcion(insIP.getTipoExcepcion());
		result.setMensaje(insIP.getMensaje());
		if(insIP.getTipoExcepcion().equals(Constants.ERROR)) {
			return result; //rollback
		}
		
		// CREA EL NODO INICIAL
		NodoTO instanciaNodoInicial = NodoTO.builder()
				.cveProceso(cveProceso)
				.version(version)
				.cveInstancia(cveInstancia)
				.cveNodo(cveNodoInicio)
				.idNodo(idNodoInicio)
				.secuenciaNodo(0)
				.tipoGeneracion(Constants.ACTUAL)
				.tipoNodoSiguiente(Constants.NORMAL)
				.cveNodoInicio(cveNodoInicio)
				.idNodoInicio(idNodoInicio)
				.rol(rolCreador)
				.folioMensajeEnvio(folioMensajeEnvio)
				.origen(origen)
				.build();

		log.info("*********************CREA EL NODO INICIAL***************************");
		log.info("clave Rol Creador: " + rolCreador);
		EstatusTO creaIN = iNodoHelper.creaInstancia(session, instanciaNodoInicial);
		
		result.setTipoExcepcion(creaIN.getTipoExcepcion());
		result.setMensaje(creaIN.getMensaje());
		if(creaIN.getTipoExcepcion().equals(Constants.ERROR)) {
			return result;// rollback
		}
		
		// INICIALIZA LAS VARIABLES A NIVEL DE PROCESO QUE ASÍ FUERON CONFIGURADAS
		log.debug("-------> CREA VARIABLES");

		//log.info("-------> REEMPLAZA VARIABLES EN EL PROCESO Y USUARIO REFERENCIA");
		
		//SVM ajuste de imagen y variables se comenta ya que se pasa a variables proceso formulario
		String referencia = "";
		/*String query = stProceso.getCondicionInicializacion();
		log.info("xxxxxxxxxxxxxxxxxxxxxxxx> condi variables query: {}", query);
		if (query != null && !query.isEmpty() && cveProceso != "EVENTO-INICIO" && cveProceso != "EVENTO-INICIO-MENSAJE" ) {
			EstatusVariablesTO reemplzav = iNodoHelper.reemplazaVariables(
					session, instanciaNodoInicial, null, query);
			log.error("xxxxxxxxxxxxxxxxxxxxxxxx> condi variables reemplzav: {}", reemplzav);
			referencia = areaTrabajoDAO.obtenerUsuarioAsignado(reemplzav.getCadenaSalida());
			log.error("xxxxxxxxxxxxxxxxxxxxxxxx> condi variables referencia: {}", referencia);
		}*/
		EstatusTO creaVP = null;
		creaVP = setCreaVariables(session, cveInstancia, cveProceso, version, referencia);
		if("MENSAJE-SERVICIO".equals(origen)) {			
			creaVP = setCreaVariablesServicio(session, cveInstancia, cveProceso, version, valoresReferenciaEnvio);
		}
		result.setTipoExcepcion(creaVP.getTipoExcepcion());
		result.setMensaje(creaVP.getMensaje());
		if(creaVP.getTipoExcepcion().equals(Constants.ERROR)) {
			return result;// rollback
		}
		
		log.info("*********************CREA EL NODO SIGUIENTE AL DE INICIO************************");
		// CREA EL NODO SIGUIENTE AL DE INICIO
		NodoTO instanciaNodoSiguiente= NodoTO.builder()
				.cveProceso(cveProceso)
				.version(version)
				.cveInstancia(cveInstancia)
				.cveNodo(cveNodoInicio)
				.idNodo(idNodoInicio)
				.secuenciaNodo(1)
				.tipoGeneracion(Constants.SIGUIENTE)
				.tipoNodoSiguiente(Constants.NORMAL)
				.cveNodoInicio(cveNodoInicio)
				.idNodoInicio(idNodoInicio)
				.rol(rolCreador)
				.folioMensajeEnvio(folioMensajeEnvio)
				.origen(origen)
				.build();
		
		log.info("CREA EL NODO SIGUIENTE: " + instanciaNodoInicial.toString());
		log.info("rol: " + rolCreador);
		EstatusTO creaSIN = iNodoHelper.creaInstancia(session,instanciaNodoSiguiente);
		//log.info("-------> CREA INSTANCIA DE NODO SIGUIENTE: " + creaSIN.toString());
		
		result.setTipoExcepcion(creaSIN.getTipoExcepcion());
		result.setMensaje(creaSIN.getMensaje());
		if(Constants.ERROR.equals(creaIN.getTipoExcepcion())) {
			return result;
		}
		
		// RECUPERA LOS DATOS DEL PRIMER NODO, POSTERIOR AL NODO INICIAL PARA REGRESAR ESOS DATOS
		// LISTA DE LOS NODOS QUE CORRESPONDEN A ACTIVIDADES DE USUARIO
        List<String> actividades = Arrays.asList(Constants.ACTIVIDAD_USUARIO, Constants.ACTIVIDAD_USUARIO_TEMPORIZACION);

		List<InNodoProceso> listaNodo = inNodoProcesoRepository.encuentraNodosActividades(
				cveEntidad, cveProceso, version, cveInstancia, actividades);

		result.setCveNodoActividad(null);
		result.setIdNodoActividad(null);
		result.setSecuenciaNodoActividad(null);
		
		result.setCveNodoActividad("");
		result.setIdNodoActividad(0);
		result.setSecuenciaNodoActividad(0);
		
		for (InNodoProceso inNP: listaNodo) {
			result.setCveNodoActividad(inNP.getId().getCveNodo());
			result.setIdNodoActividad(inNP.getId().getIdNodo());
			result.setSecuenciaNodoActividad(inNP.getId().getSecuenciaNodo());
		}
		
        log.info("############### RETURN SP_CREA_INSTANCIA_PROCESO: " + result.toString());
		return result;
	}

	//SP_CARGA_LISTA_EN_TABLA
	@Override
	public RetMsg cargaListaEnTabla(DatosAutenticacionTO session, NodoInicioProcesoTO nodoProceso, String cadenaEntrada, String separador,
			List<String> listaValor) throws BrioBPMException, ParseException {

		String encontrado;
		int longitudCadena;
		int posicionInicial;
		int posicionFinal;
		String elemento;
		
		// Inicializa código de error, mensaje y sugerencia 
        RetMsg msg = RetMsg.builder().status("OK").message("").build(); 
				
		 
		// Asumimos que listaValor contiene los valores iniciales separados por el delimitador 
		longitudCadena = cadenaEntrada.length();
		 log.info("############### IMPRIME LONGITUD CADENA: " + longitudCadena);
		 
		posicionInicial = 0;
		log.info("############### IMPRIME POSICION INICIAL: " + posicionInicial);
		boolean finLista = false;
		
		// Recorrer la cadena de entrada
		while (!finLista) {
			
			ElementoCadenaTO result  = iNodoHelper.leeElementoCadena(cadenaEntrada, longitudCadena, posicionInicial, separador);	
			
			
			encontrado = result.getEncontro();
			 log.info("############### IMPRIME ENCONTRADO: " + encontrado);
			
			posicionFinal = result.getPosicionFinal();	
			 log.info("############### IMPRIME POSICION FINAL: " + posicionFinal);
			 
			 elemento = result.getElemento();
			 log.info("############### IMPRIME ELEMENTO: " + elemento);
			
			
			if(Constants.SI.equals(encontrado)) {
				listaValor.add(elemento);			
				
				posicionInicial = posicionFinal + 2;
				
			 if(posicionInicial >= longitudCadena) {
				
				finLista = true;				
			 }
			}else {
				
				finLista = true;	
	
				}
			 }
		
		return msg;
	}

	
	@Override
	public RetMsg procesaMensajes(DatosAutenticacionTO session) throws BrioBPMException, ParseException {
		List<Object> procesos = iMensajeEnvioRepository.obtenerMensajesEnvio(Constants.AMBITO_WFBD_ACTUAL, 
				Constants.SITUACION_PENDIENTE, Constants.ERROR);
		log.info("tamaño de los mensajes entidad {} ", procesos.size());
		List<StInMensajeTO> result = new ArrayList<StInMensajeTO>();			
		result.addAll(procesos.stream().map(InStMensajeEnvioConverter.converterEntityToDTO).collect(Collectors.toList()));	
		String aplicar = Constants.NO;
		BitacoraBatchPK idB = BitacoraBatchPK.builder()
				.cveProcresoBatch(Constants.PROCESA_MENSAJES)
				.fechaMensaje(new Date())
				.build();
		// Inicializa código de error, mensaje y sugerencia 
        RetMsg msg = RetMsg.builder().status("OK").message("EXITO").build(); 
		for(StInMensajeTO stTo: result) {
			log.info("stTo: {} ", stTo);
			Optional<Entidad> entidad = entidadRepository.findById(stTo.getCveEntidad());
			log.info("entidad: ", entidad);
			String idioma = entidad.get().getIdioma().getCveIdioma();
			if (Constants.SI.equals(stTo.getInicioProcesoDestino()) &&
					null != stTo.getCveEntidadDestino() &&
					null != stTo.getCveProcesodDestino()&&
					null != stTo.getVersionDestino() ) {
				DatosAutenticacionTO sessionNewInstance = DatosAutenticacionTO.builder()
						.cveUsuario(session.getCveUsuario())
						.cveEntidad(stTo.getCveEntidadDestino())	
						.cveIdioma(idioma)	
						.cveLocalidad(session.getCveLocalidad())
						.build();
				ProcesoInicialTO instanciaNodo = ProcesoInicialTO.builder()
						.cveInstancia(stTo.getCveInstancia())
						.cveProceso(stTo.getCveProceso())
						.cveNodoFinal(stTo.getCveProcesodDestino())
						.cveNodoInicial(stTo.getCveNodo())
						.version(new BigDecimal(stTo.getVersion()))
						.concepto(Constants.ACTIVACION_POR_MENSAJE)
						.origen(Constants.MENSAJE)
						.cveRol(Constants.ROL_WORKFLOW)
						.build();
				log.info("sessionNewInstance: {} ", sessionNewInstance);

				// SI SE TRATA DE UN MENSAJE DE INICIO DE PROCESO, EJECUTA LA CREACIÓN DE INSTANCIA DE PROCESO
				EstatusCreaInstanciaTO estatus = creaInstancia( sessionNewInstance,  
						instanciaNodo,
						stTo.getValoresReferenciaEnvio(),  stTo.getFolioMensaje());
				log.info("estatus.getTipoExcepcion(): {} ", estatus.getTipoExcepcion());
				if(Constants.OK.equals(estatus.getTipoExcepcion())){
					// Update IN_MENSAJE_ENVIO
					log.info("EstatusCreaInstanciaTO estatus: {} ", estatus);
					try {
					iMensajeEnvioRepository.updateSituacion(Constants.ENVIADO,
							stTo.getCveEntidad(),
							stTo.getCveProceso(),
							new BigDecimal(stTo.getVersion()),
							stTo.getCveInstancia(),
							stTo.getFolioMensaje());
					log.info("EstatusCreaInstanciaTO rows enviado {} ");
					} catch (Exception e) {
						e.printStackTrace();
						e.getMessage();
					}
					log.info("EstatusCreaInstanciaTO estatus enviado {} ", estatus);
				} else {
					//ROLLBACK
					msg.setMessage(Constants.ERROR);
					BitacoraBatch batch = BitacoraBatch.builder()
							.id(idB)
							.tipoMensaje(Constants.ERROR)
							.mensajePrincipal(estatus.getMensaje())
							.mensajeAuxiliar(null)
							.cveEntidad(stTo.getCveEntidadDestino())
							.cveProceso(stTo.getCveProcesodDestino())
							.version(stTo.getVersionDestino())
							.cveInstancia(null)
							.cveNodo(null)
							.idNodo(null)
							.secuenciaNodo(null)
							.build();
					
					bitacoraBatchRepository.saveAndFlush(batch);
				
				}
				
			} else {
				// CR_MENSAJES_RECEPCION
				
				iMensajeEnvioRepository.obtenerMensajesRecepcion(stTo.getValoresReferenciaEnvio(), " ");
				List<InMensajeEnvioPK> procesosRecepcion = iMensajeEnvioRepository.obtenerMensajesRecepcion( 
						stTo.getValoresReferenciaEnvio(), Constants.VACIO);
				log.info("procesosRecepcion: {} ",procesosRecepcion.size());
				//SI SE PROPORCIONÓ LA ESPECIFICACIÓN DE UN DESTINO EN EL ENVÍO, SE VERIFICA QUE EXISTA CORRESPONDENCIA CON
				//		LA RECEPCIÓN

				
				for(InMensajeEnvioPK id: procesosRecepcion) {
					aplicar = Constants.NO;
					if (stTo.getCveEntidadDestino() != null && 
						stTo.getCveEntidadDestino() !=  Constants.VACIO	&&
						stTo.getCveProcesodDestino() != null && 
						stTo.getCveProcesodDestino() !=  Constants.VACIO	&& 
						stTo.getVersionDestino() != null && 
						stTo.getVersionDestino() != new BigDecimal("0")	&& 
						stTo.getCveNodoDestino() != null && 
						stTo.getCveNodoDestino() !=  Constants.VACIO	&& 
						stTo.getIdNodoDestino()!= null && 
						stTo.getIdNodoDestino() != new BigDecimal("0")) {
						
							if(id.getCveEntidad().equals(stTo.getCveEntidadDestino()) &&
							id.getCveProceso().equals(stTo.getCveProcesodDestino()) &&
							id.getVersion().equals(stTo.getVersionDestino()) &&
							id.getCveNodo().equals(stTo.getCveNodoDestino()) &&	
							id.getIdNodo().equals(stTo.getIdNodoDestino())){
								aplicar = Constants.SI;							
							
						}
					} else {
						aplicar = Constants.SI;		
					}
					if (Constants.SI.equals(aplicar)) {
						DatosAutenticacionTO sessionNewInstance = DatosAutenticacionTO.builder()
								.cveUsuario(session.getCveUsuario())
								.cveEntidad(id.getCveEntidad())	
								.cveLocalidad(session.getCveLocalidad())
								.cveIdioma(idioma)	
								.build();
						ProcesoInicialTO instanciaNodo = ProcesoInicialTO.builder()
								.cveInstancia(id.getCveInstancia())
								.cveProceso(id.getCveProceso())
								.cveNodoFinal(id.getCveNodo())
								.cveNodoInicial(stTo.getCveNodo())
								.version(id.getVersion())
								.secuenciaNodo(id.getSecuenciaNodo())
								.concepto(Constants.ACTIVACION_POR_MENSAJE)
								.origen(Constants.MENSAJE)
								.cveRol(Constants.ROL_WORKFLOW)
								.build();
						
						EstatusCreaInstanciaTO estatus = creaInstancia( sessionNewInstance,  
								instanciaNodo,
								stTo.getValoresReferenciaEnvio(),  stTo.getFolioMensaje());
						if(!Constants.OK.equals(estatus.getTipoExcepcion())){
							//ROLLBACK
							msg.setMessage(Constants.ERROR);
							BitacoraBatch batch = BitacoraBatch.builder()
									.id(idB)									
									.tipoMensaje(Constants.ERROR)
									.mensajePrincipal(estatus.getMensaje())
									.mensajeAuxiliar(null)
									.cveEntidad(stTo.getCveEntidadDestino())
									.cveProceso(stTo.getCveProcesodDestino())
									.version(stTo.getVersionDestino())
									.cveInstancia(null)
									.cveNodo(null)
									.idNodo(null)
									.secuenciaNodo(null)
									.build();
							bitacoraBatchRepository.saveAndFlush(batch);
							
						}
						}
					}	
				}
		}
		log.info("msg: {}",msg.getMessage());
		return msg;
	}

	@Override
	public SaveSectionTO setCreaValoresReferenciaDesdeValores(
	        DatosAutenticacionTO session,
	        ActividadTO actividad,
	        String valoresReferencia) throws BrioBPMException {

	    List<DataSectionTO> dataSectionTOList = new ArrayList<>();
	    DataSectionTO data = new DataSectionTO();

	    data.setCveSection("CREA_FOLI");
	    data.setType("SECUENCIAL");
	    data.setContent("VARIABLES");

	    
	    DataOccurrenceTO dataOccurrence = DataOccurrenceTO.builder()
	            .ocurrencia(1)
	            .nueva(false)
	            .build();

	    List<SectionVariablesTO> sectionVariables = new ArrayList<>();

	    // Separar los pares clave-valor por coma
	    String[] pares = valoresReferencia.split(",");
	    for (String par : pares) {
	        // Separar la clave y valor por '|'
	        String[] partes = par.trim().split("\\|", 2);
	        if (partes.length == 2) {
	            String clave = partes[0].trim();
	            String valor = partes[1].trim();

	            SectionVariablesTO variable = new SectionVariablesTO();
	            variable.setCveVariable(clave);
	            variable.setValues(Arrays.asList(valor));

	            sectionVariables.add(variable);
	        }
	    }
	    List<DataOccurrenceTO> dataOccurrences = new ArrayList<>();
	    dataOccurrences.add(dataOccurrence);
	    dataOccurrence.setSectionVariables(sectionVariables);
	    data.setDataOccurrence(dataOccurrences);
	    dataSectionTOList.add(data);

	    return SaveSectionTO.builder()
	            .activity(actividad)
	            .data(dataSectionTOList)
	            .build();
	}

		
	
	
    private  String detectarTipo(String valor) {
        // Entero
        if (valor.matches("-?\\d+")) {
            return "ENTERO";
        }
        // Decimal
        else if (valor.matches("-?\\d*\\.\\d+")) {
            return "DECIMAL";
        }
        // Fecha (formatos comunes)
        else if (esFecha(valor)) {
            return "FECHA";
        }
        // Alfanumérico (letras y números mezclados o texto con espacios)
        else if (valor.matches("[\\p{L}\\d\\s\\-_,.]+")) {
            return "ALFANUMERICO";
        }
        // Cualquier otro tipo
        return "DESCONOCIDO";
    }

    private  boolean esFecha(String valor) {
        // Lista de formatos posibles
        String[] formatos = {"yyyy-MM-dd", "dd/MM/yyyy", "MM/dd/yyyy", "yyyy/MM/dd"};
        for (String formato : formatos) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(formato);
                sdf.setLenient(false);
                sdf.parse(valor);
                return true;
            } catch (ParseException e) {
                // Ignorar y probar el siguiente formato
            }
        }
        return false;
    }
    
    private List<SectionVariablesTO> separarCadena(String cadena) {
    	// Separar los pares clave-valor por coma
	    String[] pares = cadena.split(",");    
	    List<SectionVariablesTO> elementos = new ArrayList<>();
    	  for (String par : pares) {
  	        // Separar la clave y valor por '|'
  	        String[] partes = par.trim().split("\\|", 2);
  	        if (partes.length == 2) {
  	            String clave = partes[0].trim();
  	            String valor = partes[1].trim();
  	            SectionVariablesTO variable = new SectionVariablesTO();
  	            variable.setCveVariable(clave);
  	            variable.setValues(Arrays.asList(valor));
  	          elementos.add(variable);
  	        }
  	    }
		return elementos;
    }
	
}
