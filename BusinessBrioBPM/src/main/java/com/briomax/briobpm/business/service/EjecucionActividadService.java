/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados. Este software contiene informacion
 * confidencial propiedad de Briomax Consulting por lo cual no puede ser reproducido, distribuido o alterado sin el
 * consentimiento previo de Briomax Consulting.
 */

package com.briomax.briobpm.business.service;

import java.math.BigDecimal;
import java.text.ParseException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.briomax.briobpm.business.helpers.base.INodoHelper;
import com.briomax.briobpm.business.service.core.IEjecucionActividadService;
import com.briomax.briobpm.business.service.core.IEmailsService;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.persistence.dao.base.IEjecucionActividadRepository;
import com.briomax.briobpm.transferobjects.DataOccurrenceTO;
import com.briomax.briobpm.transferobjects.DataSectionTO;
import com.briomax.briobpm.transferobjects.SaveSectionTO;
import com.briomax.briobpm.transferobjects.SectionVariablesTO;
import com.briomax.briobpm.transferobjects.emun.TipoContenidoEnum;
import com.briomax.briobpm.transferobjects.emun.TipoPresentacionEnum;
import com.briomax.briobpm.transferobjects.in.ActividadTO;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;
import com.briomax.briobpm.transferobjects.in.NodoTO;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Class EjecucionActividadService.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Mar 5, 2020 1:03:55 PM Modificaciones:
 * @since JDK 1.8
 */
@Service
@Transactional
@Slf4j
public class EjecucionActividadService implements IEjecucionActividadService {

//	/** La Constante NUMERAL. */
//	private static final String NUMERAL = "#";

	/** La Constante PIPE. */
	private static final String PIPE = "|";

	/** La Constante VACIO. */
	private static final String VACIO = "";

	/** La Constante OK. */
	private static final String OK = "OK";

	/** La Constante ERROR. */
	private static final String ERROR = "ERROR";

	/** El atributo o variable ejecucion actividad repository. */
	@Autowired
	private IEjecucionActividadRepository repository;

	/** El atributo o variable email service. */
	@Autowired
	private IEmailsService emailService;
	
	/** El atributo o variable nodo Helper. */
	@Autowired
	private INodoHelper iNodoHelper;


	/** 
	 * {@inheritDoc}
	 * @throws ParseException 
	 * @see com.briomax.briobpm.business.service.core.IEjecucionActividadService#guardarActividad(com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO, com.briomax.briobpm.transferobjects.SaveSectionTO)
	 */
	@Override
	public RetMsg guardarActividad(DatosAutenticacionTO session, SaveSectionTO dataSections) throws BrioBPMException, ParseException {
		log.info("\t----- GUARDAR ACTIVIDAD {}", session);
		log.info("\t   {}", dataSections);
		RetMsg response = saveActivity(session, dataSections);
		log.debug("\t----- {}", response);
		return response;
	}

	/**
	 * Método encargado de guardar la actividad para la sesión de autenticación dada
	 * y las secciones de datos proporcionadas.
	 *
	 * @param session      El objeto de sesión que contiene la autenticación del usuario.
	 * @param dataSections Las secciones de datos que se van a procesar y guardar.
	 * @return Un objeto RetMsg que contiene el estado y el mensaje del resultado de la operación.
	 * @throws BrioBPMException En caso de que ocurra una excepción específica de BPM.
	 * @throws ParseException   En caso de que ocurra un error al parsear los datos.
	 */
	private RetMsg saveActivity(DatosAutenticacionTO session, SaveSectionTO dataSections) throws BrioBPMException, ParseException {
	    log.info("{} ### saveActivity {}", session, dataSections);
	    
	    // Inicializa la respuesta con un estado de error por defecto.
	    RetMsg response = RetMsg.builder().status(ERROR).message(ERROR).build();

	    // Verifica que la sesión y las secciones de datos no sean nulas, y que las secciones contengan datos.
	    if (session != null && dataSections != null && dataSections.getData() != null) {
	        
	        // Verifica que la lista de datos de las secciones no esté vacía.
	        if (dataSections.getData() != null && !dataSections.getData().isEmpty()) {
	            
	            // Itera sobre cada sección de datos.
	            for (DataSectionTO section : dataSections.getData()) {
	                log.info("-----.-.-. SECTION: " + section.toString());
	                
	                // Verifica si la sección tiene ocurrencias de datos.
	                if (section.getDataOccurrence() != null && !section.getDataOccurrence().isEmpty()) {
	                    
	                    // Llama al método para guardar la actividad de forma desacoplada.
	                    response = saveActivity(session, dataSections.getActivity(), section);
	                    
	                    // Si se detecta un error en la respuesta, se interrumpe el bucle.
	                    if (response.getStatus().equalsIgnoreCase(ERROR)) {
	                        break;
	                    }
	                }
	                else {
	                    // Si la sección no tiene ocurrencias, se registra una advertencia y se responde con éxito.
	                    log.warn("SECCION - Sin Ocurrencias");
	                    response = RetMsg.builder().status(OK).message(OK).build();
	                }
	            }
	        }
	    }
	    //Ajuste de reglas al aplicar el guardado de variables
	    NodoTO nodo = NodoTO.builder()
	    		.cveProceso(dataSections.getActivity().getCveProceso())
	    		.version(new BigDecimal(dataSections.getActivity().getVersion()))
	    		.cveNodo(dataSections.getActivity().getCveNodo())
	    		.idNodo(dataSections.getActivity().getIdNodo())
	    		.cveInstancia(dataSections.getActivity().getCveInstancia())
	    		.usoSeccion("WEB")
	    		.build();
		RetMsg respons = iNodoHelper.evaluaReglasProcesoTerminar(session, nodo , "GUARDAR");
	    
	    if (respons.getStatus().equals(ERROR)) {
	    	response.setStatus(ERROR);
	    	response.setMessage(respons.getMessage());
	    }
	    // Retorna la respuesta generada.
	    return response;
	}


	/**
	 * Guarda la actividad en función del tipo de contenido de la sección proporcionada.
	 *
	 * Este método recibe la sesión de autenticación, una actividad y una sección de datos, y luego
	 * determina cómo guardar la información en función del tipo de contenido de la sección.
	 * Dependiendo de si la sección contiene variables, documentos o tareas, el método delega
	 * la operación de guardado a métodos específicos para cada caso.
	 *
	 * @param session Objeto `DatosAutenticacionTO` que contiene la información de autenticación del usuario.
	 * @param activity Objeto `ActividadTO` que representa la actividad que se va a guardar.
	 * @param section Objeto `DataSectionTO` que contiene los datos de la sección a guardar.
	 * @return Un objeto `RetMsg` que contiene el estado y el mensaje del resultado de la operación.
	 * @throws BrioBPMException Si ocurre algún error en el proceso de guardado.
	 * @throws ParseException Si ocurre un error al analizar datos.
	 */
	private RetMsg saveActivity(DatosAutenticacionTO session, ActividadTO activity, DataSectionTO section)
	        throws BrioBPMException, ParseException {
		
		log.info("---------->saveActivity ");
		log.info("---------->SECTION: " + section.toString());
		
	    // Inicializa la respuesta con un estado y mensaje de error por defecto.
	    RetMsg response = RetMsg.builder().status(ERROR).message(ERROR).build();

	    // Determina el tipo de contenido de la sección y ejecuta la lógica correspondiente.
	    switch (TipoContenidoEnum.getTipoContenidoEnum(section.getContent())) {
	        
	        // Caso para el contenido de tipo VARIABLES o DOCUMENTOS.
	        case VARIABLES:
	        case DOCUMENTOS:
	            log.info("\tGuardar Seccion Secuencial/Grid con Variables/Documentos");
	            // Llama al método saveVariables para guardar las variables o documentos.
	            response = saveVariables(session, activity, section);
	            break;
	        
	        // Caso para el contenido de tipo TAREAS.
	        case TAREAS:
	            log.debug("\tGuardar Seccion Tareas");
	            // Llama al método saveTasks para guardar las tareas.
	            response = saveTasks(session, activity, section);
	            break;
	        
	        // Caso por defecto si el tipo de contenido no coincide con ninguno de los casos anteriores.
	        default:
	            break;
	    }

	    // Retorna la respuesta final que contiene el estado y el mensaje del resultado de la operación.
	    return response;
	}

	/**
	 * Guarda las variables de la sección proporcionada.
	 *
	 * Este método se encarga de guardar las variables asociadas a una sección específica en la actividad.
	 * Primero verifica si la sección tiene ocurrencias de datos; si no las tiene, retorna una respuesta de éxito
	 * sin realizar ninguna operación adicional. Luego, itera sobre las ocurrencias de la sección, 
	 * verifica si contienen variables y, en caso afirmativo, guarda estas variables llamando a otro método.
	 *
	 * @param session Objeto `DatosAutenticacionTO` que contiene la información de autenticación del usuario.
	 * @param activity Objeto `ActividadTO` que representa la actividad en la que se guardarán las variables.
	 * @param section Objeto `DataSectionTO` que contiene los datos de la sección, incluidas las ocurrencias y variables.
	 * @return Un objeto `RetMsg` que contiene el estado y el mensaje del resultado de la operación.
	 * @throws BrioBPMException Si ocurre algún error durante el proceso de guardado.
	 * @throws ParseException Si ocurre un error al analizar datos.
	 */
	private RetMsg saveVariables(DatosAutenticacionTO session, ActividadTO activity, DataSectionTO section)
	        throws BrioBPMException, ParseException {

	    // Log de depuración con detalles de la sección y actividad.
	    log.info("\t\t VARIABLES  saveVariables {} ### {} ### {}", section, activity, section);

	    // Inicializa la respuesta con un estado y mensaje de éxito por defecto.
	    RetMsg response = RetMsg.builder().status(OK).message(OK).build();

	    // Verifica si la sección tiene ocurrencias de datos; si no tiene, retorna la respuesta.
	    if (section.getDataOccurrence() == null || section.getDataOccurrence().size() == 0) {
	        log.warn("VARIABLES - Sin Ocurrencias");
	        return response;
	    }

	    // Variables auxiliares para manejar la primera ocurrencia y almacenar datos.
	    String datosOcurrencia = VACIO;
	    String primerOcurrencia = "SI";

	    // Si el tipo de presentación de la sección es secuencial, se marca que no es la primera ocurrencia.
	    if (section.getType().equalsIgnoreCase(TipoPresentacionEnum.SECUENCIAL.getValue())) {
	        primerOcurrencia = "NO";
	    }

	    // Itera sobre cada ocurrencia de datos en la sección.
	    for (DataOccurrenceTO ocurrence : section.getDataOccurrence()) {
	        log.info("\t\t\t OCURRENCE desde seccion {} ### {} ", ocurrence.getOcurrencia(), ocurrence);

	        // Si la ocurrencia no tiene variables de sección, continúa con la siguiente.
	        if (ocurrence.getSectionVariables().isEmpty()) {
	            continue;
	        }

	        // Obtiene los datos de la ocurrencia actual.
	        datosOcurrencia = getDatosOcurrencia(ocurrence);

	        // Si los datos de la ocurrencia no están vacíos, intenta guardarlos.
	        if(StringUtils.isNotBlank(datosOcurrencia)) {    
	            response =
	                saveSectionVariables(session, activity, section, ocurrence.getOcurrencia(), primerOcurrencia,
	                    ocurrence.isNueva() ? "SI" : "NO", datosOcurrencia);

	            // Si ocurre un error al guardar las variables, rompe el bucle.
	            if (response.getStatus().equalsIgnoreCase(ERROR)) {
	                break;
	            }
	        }

	        // Marca que ya no es la primera ocurrencia después de la primera iteración.
	        primerOcurrencia = "NO";
	    }

	    // Retorna la respuesta final que contiene el estado y el mensaje del resultado de la operación.
	    return response;
	}


	/**
	 * Save section variables.
	 * Este método se encarga de guardar las variables de una sección específica
	 * en una actividad. Para ello, construye un objeto con la información de la 
	 * actividad y realiza la llamada a la capa de persistencia.
	 * Se manejan posibles excepciones y se retorna un mensaje con el estado del proceso.
	 * 
	 * Save section variables.
	 * @param session el session.
	 * @param activity el activity.
	 * @param section el section.
	 * @param numOcurrencia el num ocurrencia.
	 * @param priOcurrencia el pri ocurrencia.
	 * @param datosOcurrencia el datos ocurrencia.
	 * @return el ret msg.
	 * @throws ParseException 
	 * @throws BrioBPMException 
	 */
	private RetMsg saveSectionVariables(DatosAutenticacionTO session, ActividadTO activity, DataSectionTO section,
		Integer numOcurrencia, String priOcurrencia, String nueva, String datosOcurrencia) throws BrioBPMException, ParseException {
		RetMsg response = null;
		try {
			log.info("\t\t\t\t REQUEST  SP_GUARDA_VARIABLES_SECCION '{}', '{}', '{}', '{}', '{}', '{}', '{}'," +
				" '{}', '{}', '{}', '{}', '{}', '{}', '{}'  ",
				session.getCveUsuario(), session.getCveEntidad(), session.getCveLocalidad(), session.getCveIdioma(),
				activity.getCveRol(), activity.getCveProceso(), activity.getVersion(), activity.getCveInstancia(),
				activity.getCveNodo(), activity.getIdNodo(), section.getCveSection(), numOcurrencia, nueva,
				datosOcurrencia);
			
			// Creación de un objeto NodoTO con la información de la actividad, que se utiliza para guardar variables de la sección.
			NodoTO nodoGuardaVariable = NodoTO.builder()
					.rol(activity.getCveRol())
					.cveProceso(activity.getCveProceso() != null ? activity.getCveProceso() : VACIO)
					.version(activity.getVersion() != null ? new BigDecimal(activity.getVersion()) : BigDecimal.ZERO)
					.cveInstancia(activity.getCveInstancia() != null ? activity.getCveInstancia() : VACIO)
					.cveNodo(activity.getCveNodo() != null ? activity.getCveNodo() : VACIO)
					.idNodo(activity.getIdNodo() != null ? activity.getIdNodo() : 0)
					.ocurrencia(numOcurrencia)
					.build();
			
			log.info("DESAOPLE SP_GUARDA_VARIABLES_SECCION");
			
			log.info("-----:: numOcurrencia: " + numOcurrencia);
			
			// Si la clave de la sección es nula, se establece un valor vacío.
			String cveSeccion = section.getCveSection() != null ? section.getCveSection() : VACIO;
			
			// DESACOPLE SP_GUARDA_VARIABLES_SECCION
			// Llamada al método `guardaVariablesSeccion` de `iNodoHelper` para guardar las variables de la sección, pasando los parámetros necesarios.
			response = iNodoHelper.guardaVariablesSeccion(session, nodoGuardaVariable, cveSeccion, priOcurrencia, nueva, datosOcurrencia);
			
//			response = repository.guardaVariablesSeccion(session.getCveUsuario(), session.getCveEntidad(),
//					session.getCveLocalidad(), session.getCveIdioma(), activity.getCveRol(), 
//					activity.getCveProceso() != null ? activity.getCveProceso() : VACIO,
//					activity.getVersion() != null ? new BigDecimal(activity.getVersion()) : BigDecimal.ZERO,
//					activity.getCveInstancia() != null ? activity.getCveInstancia() : VACIO,
//					activity.getCveNodo() != null ? activity.getCveNodo() : VACIO,
//					activity.getIdNodo() != null ? activity.getIdNodo() : 0,
//					section.getCveSection() != null ? section.getCveSection() : VACIO,
//					numOcurrencia, priOcurrencia, nueva, datosOcurrencia);
//			log.debug("\t\t\t\t RESPONSE SP_GUARDA_VARIABLES_SECCION {} \n", response);
		
		
		} catch (DataAccessException dataEx) {
			// Retorno de la respuesta con el resultado del guardado.
			response =RetMsg.builder().status(ERROR).message("Error saving section.").build();
			log.error(dataEx.getMessage(), dataEx);
		}
		return response;
	}

	/**
	 * Obtener el valor de datos ocurrencia.
	 * @param ocurrence el ocurrence.
	 * @return el datos ocurrencia.
	 */
	private String getDatosOcurrencia(DataOccurrenceTO ocurrence) {
	    String datosOcurrencia = VACIO; // Inicializa la variable para almacenar los datos de ocurrencia como una cadena vacía.
	    
	    // Verifica si la lista de variables de sección no es nula.
	    if (ocurrence.getSectionVariables() != null) {
	        // Itera sobre cada variable de sección en la lista.
	        for (SectionVariablesTO sectionVariables : ocurrence.getSectionVariables()) {
	            log.trace("\t\t\t\t\t {}", sectionVariables); // Registra información de la variable de sección en modo de traza.
	            datosOcurrencia += sectionVariables.getCveVariable() + PIPE; // Agrega la clave de la variable y un separador.
	            
	            // Verifica si la lista de valores no es nula ni está vacía.
	            if (sectionVariables.getValues() != null && !sectionVariables.getValues().isEmpty()) {
	                datosOcurrencia += sectionVariables.getValues().size() + PIPE; // Agrega el número de valores y un separador.
	                int secuencia = 1; // Inicializa un contador para la secuencia de los valores.
	                
	                // Itera sobre cada valor en la lista de valores.
	                for (String value : sectionVariables.getValues()) {
	                    log.trace("\t\t\t\t\t\t {}", value); // Registra cada valor en modo de traza.
	                    // Agrega la secuencia y el valor (o vacío si es nulo) al String de datos de ocurrencia.
	                    datosOcurrencia += String.valueOf(secuencia) + PIPE + (value != null ? value : "") + PIPE;
	                    secuencia++; // Incrementa el contador de secuencia.
	                }
	            } else {
	                // Si no hay valores, agrega una entrada por defecto indicando un solo valor nulo.
	                // Numero Valores | Secuencia | Valor |
	                datosOcurrencia += "1|1||"; // Agrega un marcador por defecto con un valor vacío.
	            }
//	            datosOcurrencia += NUMERAL; // Comentado: podría ser usado para separar secciones en el futuro.
	        }
	    }

//	    // Elimina el último carácter de separación si se añadió un numeral al final.
//	    if (datosOcurrencia.endsWith(NUMERAL)) {
//	        datosOcurrencia = datosOcurrencia.substring(0, datosOcurrencia.length() - 1); // Elimina el último numeral si existe.
//	    }
	    
	    // Elimina el último separador '|' si está presente al final del String de datos de ocurrencia.
	    if (datosOcurrencia.endsWith(PIPE)) {
	        datosOcurrencia = datosOcurrencia.substring(0, datosOcurrencia.length() - 1); // Elimina el último PIPE.
	    }
	    
	    // Registra el resultado final de los datos de ocurrencia en modo de depuración.
	    log.debug("\t\t\t\t Datos Ocurrencia ### '{}' ", datosOcurrencia);
	    return datosOcurrencia; // Retorna la cadena de datos de ocurrencia formateada.
	}

	/**
	 * Save tasks.
	 * @param session el session.
	 * @param activity el activity.
	 * @param section el section.
	 * @return el ret msg.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	private RetMsg saveTasks(DatosAutenticacionTO session, ActividadTO activity, DataSectionTO section)
		throws BrioBPMException {
		log.debug("\t\t TASK {} ### {} ### {}", section, activity, section);
		RetMsg response = RetMsg.builder().status(OK).message(OK).build();
		if (section.getDataOccurrence() == null || section.getDataOccurrence().size() == 0) {
			log.warn("TASK - Sin Ocurrencias");
			return response;
		}
		String secuenciaCompletada = VACIO;
		boolean save = false;
		if (section.getDataOccurrence() != null && !section.getDataOccurrence().isEmpty()) {
			for (DataOccurrenceTO dataSection : section.getDataOccurrence()) {
				if (dataSection.getSectionVariables() != null && !dataSection.getSectionVariables().isEmpty()) {
					for (SectionVariablesTO sectionVariable : dataSection.getSectionVariables()) {
						if (sectionVariable.getValues() != null && !sectionVariable.getValues().isEmpty()) {
							for (String values : sectionVariable.getValues()) {
								if (values != null) {
									secuenciaCompletada += values + PIPE;
									save = true;
								}
							}
						} else {
							secuenciaCompletada += "NO" + PIPE;
						}
					}
				}
			}
		}
		log.debug("SECUENCIA TAREA [{}]: {}", save, secuenciaCompletada);
		if (save) {
			if (secuenciaCompletada.endsWith(PIPE)) {
				secuenciaCompletada = secuenciaCompletada.substring(0, secuenciaCompletada.length() - 1);
			}
			log.debug("\t\t\t Secuencia Completada: {}", secuenciaCompletada);
			if (StringUtils.isNotBlank(secuenciaCompletada)) {
				response = saveTaskVariables(session, activity, secuenciaCompletada);
			}
		}
		return response;
	}

	/**
	 * Save task variables.
	 * @param session el session.
	 * @param activity el activity.
	 * @param secuenciaCompletada el secuencia completada.
	 * @return el ret msg.
	 */
	private RetMsg saveTaskVariables(DatosAutenticacionTO session, ActividadTO activity, String secuenciaCompletada) {
		RetMsg response;
		try {
			log.info("\t\t\t REQUEST  SP_GUARDA_TAREAS_NODO '{}', '{}', '{}', '{}', '{}', '{}', '{}', '{}', '{}', '{}', '{}' ",
				session.getCveUsuario(), session.getCveEntidad(), session.getCveLocalidad(), session.getCveIdioma(),
				activity.getCveProceso(), activity.getVersion(), activity.getCveInstancia(), activity.getCveNodo(),
				activity.getIdNodo(), activity.getSecNodo(), secuenciaCompletada);
			
			NodoTO nodoGuardaTarea = NodoTO.builder()
					.cveProceso(activity.getCveProceso() != null ? activity.getCveProceso() : VACIO)
					.version(activity.getVersion() != null ? new BigDecimal(activity.getVersion()) : BigDecimal.ZERO)
					.cveInstancia(activity.getCveInstancia() != null ? activity.getCveInstancia() : VACIO)
					.cveNodo(activity.getCveNodo() != null ? activity.getCveNodo() : VACIO)
					.idNodo(activity.getIdNodo() != null ? activity.getIdNodo() : 0)
					.secuenciaNodo(activity.getSecNodo() != null ? activity.getSecNodo() : 0)
					.build();
			log.info("DESAOPLE SP_GUARDA_TAREAS_NODO");
			response = iNodoHelper.guardaTareasNodo(session, nodoGuardaTarea, secuenciaCompletada);
			
//			response = repository.guardaTareasSeccion(session.getCveUsuario(), session.getCveEntidad(),
//				session.getCveLocalidad(), session.getCveIdioma(), 
//				activity.getCveProceso() != null ? activity.getCveProceso() : VACIO,
//				activity.getVersion() != null ? new BigDecimal(activity.getVersion()) : BigDecimal.ZERO,
//				activity.getCveInstancia() != null ? activity.getCveInstancia() : VACIO,
//				activity.getCveNodo() != null ? activity.getCveNodo() : VACIO,
//				activity.getIdNodo() != null ? activity.getIdNodo() : 0,
//				activity.getSecNodo() != null ? activity.getSecNodo() : 0,
//				secuenciaCompletada);
			
			log.debug("\t\t\t RESPONSE SP_GUARDA_TAREAS_NODO: {}", response);
		} catch (DataAccessException dataEx) {
			response =RetMsg.builder().status(ERROR).message("Error saving section.").build();
			log.error(dataEx.getMessage(), dataEx);
		}
		return response;
	}

	/** 
	 * {@inheritDoc}
	 * @throws ParseException 
	 * @see com.briomax.briobpm.business.service.core.IEjecucionActividadService#guardarTerminarActividad(com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO, com.briomax.briobpm.transferobjects.SaveSectionTO)
	 */
	@Override
	public RetMsg guardarTerminarActividad(DatosAutenticacionTO session, SaveSectionTO dataSections)
		throws BrioBPMException, ParseException {
		log.debug("\t----- GUARDAR & TERMINAR ACTIVIDAD {}", session);
		log.trace("\t   {}", dataSections);
		// DESACOPLADO
		RetMsg response = saveActivity(session, dataSections);

	    // Retorna la respuesta generada.
		
		
		if (response.getStatus().equalsIgnoreCase(OK)) {
			log.trace("\t\t TERMINAR ACTIVIDAD ");
			
			log.info("DESACOPLE SP_TERMINA_ACTIVIDAD");
			
			NodoTO nodoTerminaActividad = NodoTO.builder()
					.cveProceso(dataSections.getActivity().getCveProceso() != null ? dataSections.getActivity().getCveProceso() : VACIO)
					.version(dataSections.getActivity().getVersion() != null ? new BigDecimal(dataSections.getActivity().getVersion()) : BigDecimal.ZERO)
					.cveInstancia(dataSections.getActivity().getCveInstancia() != null ? dataSections.getActivity().getCveInstancia() : VACIO)
					.cveNodo(dataSections.getActivity().getCveNodo() != null ? dataSections.getActivity().getCveNodo() : VACIO)
					.idNodo(dataSections.getActivity().getIdNodo() != null ? dataSections.getActivity().getIdNodo() : 0)
					.secuenciaNodo(dataSections.getActivity().getSecNodo() != null ? dataSections.getActivity().getSecNodo() : 0)
					.origen(dataSections.getActivity().getOrigen() != null ? dataSections.getActivity().getOrigen() : "USUARIO")
					.build();
			response = iNodoHelper.terminaActividad(session, nodoTerminaActividad);
			
//			response = repository.terminaActividad(session.getCveUsuario(), session.getCveEntidad(),
//				session.getCveLocalidad(), session.getCveIdioma(),
//				dataSections.getActivity().getCveProceso() != null ? dataSections.getActivity().getCveProceso() : VACIO,
//				dataSections.getActivity().getVersion() != null ? new BigDecimal(dataSections.getActivity().getVersion()) : BigDecimal.ZERO,
//				dataSections.getActivity().getCveInstancia() != null ? dataSections.getActivity().getCveInstancia() : VACIO,
//				dataSections.getActivity().getCveNodo() != null ? dataSections.getActivity().getCveNodo() : VACIO,
//				dataSections.getActivity().getIdNodo() != null ? dataSections.getActivity().getIdNodo() : 0,
//				dataSections.getActivity().getSecNodo() != null ? dataSections.getActivity().getSecNodo() : 0);
			
			// DESCOMENTAR, IMPORTANTE (CUANDO FUNCIONE)
			
			
			
			if (response.getStatus().equalsIgnoreCase(OK)) {
				
			    //Ajuste de reglas al aplicar el guardado de variables
			    NodoTO nodo = NodoTO.builder()
			    		.cveEntidad(session.getCveEntidad())
			    		.cveProceso(dataSections.getActivity().getCveProceso())
			    		.version(new BigDecimal(dataSections.getActivity().getVersion()))
			    		.cveNodo(dataSections.getActivity().getCveNodo())
			    		.idNodo(dataSections.getActivity().getIdNodo())
			    		.cveInstancia(dataSections.getActivity().getCveInstancia())
			    		.usoSeccion("WEB")			    		
			    		.build();
				RetMsg respons = iNodoHelper.evaluaReglasProcesoTerminar(session, nodo , "TERMINAR");
			    
			    if (respons.getStatus().equals(ERROR)) {
			    	response.setStatus(ERROR);
			    	response.setMessage(respons.getMessage());
			    }
			    
				log.trace("\t\t\t SEND EMAILS ");
				log.info("---------> SE PREPARA PARA ENVIAR UN CORREO DE NOTIFICACION DEL TERMINO DEL PROCESO");
				
				// envia correo de notificaion al terminar un proceso
				emailService.sendEmails(session.getCveUsuario(), session.getCveEntidad(), session.getCveLocalidad(),
					session.getCveIdioma());
				
				//iNodoHelper.consultarNodoRepse(session, nodo, dataSections);
				
				log.info("---------> Se envio el correo de manera exitosa");

			}
		}
		
		log.debug("\t----- {}", response);
		return response;
	}

	/** 
	 * {@inheritDoc}
	 * @see com.briomax.briobpm.business.service.core.IEjecucionActividadService#obtenerActividad(com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO, com.briomax.briobpm.transferobjects.in.ActividadTO)
	 */
	@Override
	public RetMsg obtenerActividad(DatosAutenticacionTO session, ActividadTO activity) throws BrioBPMException {
		log.info("\t----- OBTENER ACTIVIDAD {}\t{}", session, activity);
		RetMsg response;
		// desacople SP_CAMBIA_SITUACION_NODO
		response = iNodoHelper.cambiaSituacionNodo(session, activity, "OBTENER");
//		response = repository.cambiaSituacionActividad(session.getCveUsuario(), session.getCveEntidad(),
//			session.getCveLocalidad(), session.getCveIdioma(), activity.getCveProceso(),
//			activity.getVersion() != null ? new BigDecimal(activity.getVersion()) : BigDecimal.ZERO,
//			activity.getCveInstancia(), activity.getCveNodo(), activity.getIdNodo(), activity.getSecNodo(), "OBTENER");
		log.debug("\t----- {}", response);
		return response;
	}

	/** 
	 * {@inheritDoc}
	 * @see com.briomax.briobpm.business.service.core.IEjecucionActividadService#liberarActividad(com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO, com.briomax.briobpm.transferobjects.in.ActividadTO)
	 */
	@Override
	public RetMsg liberarActividad(DatosAutenticacionTO session, ActividadTO activity) throws BrioBPMException {
		log.info("\t----- LIBERAR ACTIVIDAD {}\t{}", session, activity);
		RetMsg response;
		// desacople SP_CAMBIA_SITUACION_NODO
		response = iNodoHelper.cambiaSituacionNodo(session, activity, "LIBERAR");
//		response = repository.cambiaSituacionActividad(session.getCveUsuario(), session.getCveEntidad(),
//			session.getCveLocalidad(), session.getCveIdioma(), activity.getCveProceso(),
//			activity.getVersion() != null ? new BigDecimal(activity.getVersion()) : BigDecimal.ZERO,
//			activity.getCveInstancia(), activity.getCveNodo(), activity.getIdNodo(), activity.getSecNodo(), "LIBERAR");
		log.debug("\t----- {}", response);
		return response;
	}

	/** 
	 * {@inheritDoc}
	 * @see com.briomax.briobpm.business.service.core.IEjecucionActividadService#cancelarActividad(com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO, com.briomax.briobpm.transferobjects.in.ActividadTO)
	 */
	@Override
	public RetMsg cancelarActividad(DatosAutenticacionTO session, ActividadTO activity) throws BrioBPMException {
		log.debug("\t----- CANCELAR ACTIVIDAD {}\t{}", session, activity);
		RetMsg response;
		
		// desacople SP_CAMBIA_SITUACION_NODO
		response = iNodoHelper.cambiaSituacionNodo(session, activity, "CANCELAR");
//		response = repository.cambiaSituacionActividad(session.getCveUsuario(), session.getCveEntidad(),
//			session.getCveLocalidad(), session.getCveIdioma(), activity.getCveProceso(),
//			activity.getVersion() != null ? new BigDecimal(activity.getVersion()) : BigDecimal.ZERO,
//			activity.getCveInstancia(), activity.getCveNodo(), activity.getIdNodo(), activity.getSecNodo(), "CANCELAR");
		log.debug("\t----- {}", response);
		return response;
	}

	/** 
	 * {@inheritDoc}
	 * @throws ParseException 
	 * @see com.briomax.briobpm.business.service.core.IEjecucionActividadService#terminarActividad(com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO, com.briomax.briobpm.transferobjects.in.ActividadTO)
	 */
	@Override
	public RetMsg terminarActividad(DatosAutenticacionTO session, ActividadTO activity) throws BrioBPMException, ParseException {
		log.info("\t----- TERMINAR ACTIVIDAD {}\t{}", session, activity);
		RetMsg response;
//		response = repository.terminaActividad(session.getCveUsuario(), session.getCveEntidad(),
//			session.getCveLocalidad(), session.getCveIdioma(), activity.getCveProceso(),
//			activity.getVersion() != null ? new BigDecimal(activity.getVersion()) : BigDecimal.ZERO,
//			activity.getCveInstancia(), activity.getCveNodo(), activity.getIdNodo(), activity.getSecNodo());
		
		// desacople SP_TERMINA_ACTIVIDAD
		NodoTO nodoTerminarActividad = NodoTO.builder()
				.cveProceso(activity.getCveProceso())
				.version(activity.getVersion() != null ? new BigDecimal(activity.getVersion()) : BigDecimal.ZERO)
				.cveInstancia(activity.getCveInstancia())
				.idNodo(activity.getIdNodo())
				.cveNodo(activity.getCveNodo())
				.secuenciaNodo(activity.getSecNodo())
				.origen("USUARIO")
				.build();
		response = iNodoHelper.terminaActividad(session, nodoTerminarActividad);
		
		log.debug("\t----- {}", response);
		return response;
	}

}
