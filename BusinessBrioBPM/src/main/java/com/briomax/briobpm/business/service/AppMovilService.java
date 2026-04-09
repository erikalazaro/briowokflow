package com.briomax.briobpm.business.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briomax.briobpm.business.helpers.base.IAppMovilHelper;
import com.briomax.briobpm.business.helpers.base.INodoHelper;
import com.briomax.briobpm.business.service.core.IAppMovilService;
import com.briomax.briobpm.business.service.core.IFormularioDinamicoService;
import com.briomax.briobpm.common.core.Constants;
import com.briomax.briobpm.common.core.DAORet;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.persistence.entity.InDocumentoProcesoOc;
import com.briomax.briobpm.persistence.entity.InDocumentoProcesoOcPK;
import com.briomax.briobpm.persistence.entity.InVariableProceso;
import com.briomax.briobpm.persistence.entity.InVariableProcesoPK;
import com.briomax.briobpm.persistence.entity.StDocumentoSeccion;
import com.briomax.briobpm.persistence.entity.StDocumentoSeccionPK;
import com.briomax.briobpm.persistence.entity.StNodoProceso;
import com.briomax.briobpm.persistence.entity.StNodoProcesoPK;
import com.briomax.briobpm.persistence.entity.StSeccionNodo;
import com.briomax.briobpm.persistence.entity.StSeccionNodoPK;
import com.briomax.briobpm.persistence.entity.StValorVariable;
import com.briomax.briobpm.persistence.entity.StVariableProceso;
import com.briomax.briobpm.persistence.entity.StVariableProcesoPK;
import com.briomax.briobpm.persistence.entity.StVariableSeccion;
import com.briomax.briobpm.persistence.entity.StVariableSeccionPK;
import com.briomax.briobpm.persistence.repository.IInDocumentoProcesoOcRepository;
import com.briomax.briobpm.persistence.repository.IInVariableProcesoRepository;
import com.briomax.briobpm.persistence.repository.IStBotonNodoRepository;
import com.briomax.briobpm.persistence.repository.IStDocumentoSeccionRepository;
import com.briomax.briobpm.persistence.repository.IStNodoProcesoRepository;
import com.briomax.briobpm.persistence.repository.IStSeccionNodoRepository;
import com.briomax.briobpm.persistence.repository.IStSeccionProcesoRepository;
import com.briomax.briobpm.persistence.repository.IStValorVariableRepository;
import com.briomax.briobpm.persistence.repository.IStVariableProcesoRepository;
import com.briomax.briobpm.persistence.repository.IStVariableSeccionRepository;
import com.briomax.briobpm.transferobjects.app.ActividadTO;
import com.briomax.briobpm.transferobjects.app.DatoValor;
import com.briomax.briobpm.transferobjects.app.DetDatoActividad;
import com.briomax.briobpm.transferobjects.app.DocumentoIinfoTO;
import com.briomax.briobpm.transferobjects.app.RespuestaFormularioTO;
import com.briomax.briobpm.transferobjects.dynamicForm.ColumnSection;
import com.briomax.briobpm.transferobjects.dynamicForm.FormularioDinamico;
import com.briomax.briobpm.transferobjects.dynamicForm.Section;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;
import com.briomax.briobpm.transferobjects.in.DocumentoAppTO;
import com.briomax.briobpm.transferobjects.in.EstatusTO;
import com.briomax.briobpm.transferobjects.in.NodoTO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AppMovilService implements IAppMovilService{

	@Autowired
	private IFormularioDinamicoService formularioService;
	
	@Autowired 
	private IAppMovilHelper appMovilHelper;
	
	@Autowired 
	private IStVariableSeccionRepository variableSeccionRepository;
	
	@Autowired 
	private IStNodoProcesoRepository nodoProcesoRepository;
	
	@Autowired 
	private IStBotonNodoRepository stBotonNodoRepository;
	
	/** El atributo o variable Nodo Helper. */
	@Autowired
	private INodoHelper nodoHelper;
	
	@Autowired
	private IStVariableSeccionRepository stVariableSeccionRepository;
	
	@Autowired
	private IStVariableProcesoRepository stVariableProcesoRepository;
	
	@Autowired
	private IInVariableProcesoRepository inVariableProcesoRepository;
	
	@Autowired
	private IStSeccionProcesoRepository stSeccionProcesoRepository;
	
	@Autowired
	private IStDocumentoSeccionRepository stDocumentoSeccionRepository;
	
	@Autowired
	private IInDocumentoProcesoOcRepository inDocumentoProcesoOCRepository;
	
	@Autowired
	private IStValorVariableRepository stValorVariableRepository;
	
	@Autowired
	private IStSeccionNodoRepository stSeccionNodoRepository;
	
	@Override
	public ActividadTO obtenerFormularioDinamicoMovil01(DatosAutenticacionTO session, NodoTO nodo) throws BrioBPMException, ParseException {

	    // Llamamos al servicio para obtener el formulario dinámico completo
	    DAORet<FormularioDinamico, RetMsg> formularioCompleto = formularioService.obtenerFormularioDinamicoMovil(session, nodo);
	    
	    // Obtenemos el contenido del formulario dinámico
	    FormularioDinamico formulario = formularioCompleto.getContent();
	    log.info("FORMULARIO DINAMICO: " + formulario.toString());  // Logueamos el formulario completo para debug
	    	    
	    // Obtenemos las secciones del formulario
	    List<Section> secciones = formulario.getSections();
	    
	    log.info("****** SECCIONES {}: {}",secciones.size(), secciones.toString());  // Logueamos las secciones para debug"
	    	    
	    // Creamos una lista para almacenar todos los datos (objetos DetDatoActividad)
	    List<DetDatoActividad> list = new ArrayList<>();
	    
	    // Iteramos a través de las secciones del formulario
	    for (Section seccion : secciones) {
	    	
	    	log.info("----->>> seccion: " + seccion.toString());
	        
	        // Verificamos si la sección es de tipo "Sequential" (secuencia de columnas)
	        if (seccion.getSequential() != null) {
	            
	            // Iteramos sobre las columnas de la sección secuencial
	            for (ColumnSection columna : seccion.getSequential().getColumns()) {
	                
	                // Creamos una nueva lista de valores (opciones) para cada columna
	                List<DatoValor> info = new ArrayList<>();
	                
	                // Verificamos si la columna tiene opciones configuradas
	                if (columna.getControl().getOptions() != null) {
	                    
	                	// Iteramos sobre las listas de opciones de la columna
	                	for (List<String> opciones : columna.getControl().getOptions()) {

	                	    // Creamos un objeto DatoValor vacío para inicializar
	                	    DatoValor.DatoValorBuilder builder = DatoValor.builder();

	                	    // Verificamos si la lista tiene al menos un elemento (valor)
	                	    if (opciones.size() > 0) {
	                	        builder.valor(opciones.get(0)); // Guardamos el valor (primer elemento)
	                	    }

	                	    // Verificamos si la lista tiene al menos dos elementos (id)
	                	    if (opciones.size() > 1) {
	                	    	builder.id(opciones.get(0));
	                	        builder.valor(opciones.get(1)); // Guardamos la clave (segundo elemento)
	                	    }

	                	    // Verificamos si la lista tiene al menos cuatro elementos (seleccionado)
	                	    if (opciones.size() > 2) {
	                	        builder.seleccionado("SI".equalsIgnoreCase(opciones.get(2))); // Seleccionado si es "SI"
	                	    } 

	                	    // Construimos el objeto DatoValor
	                	    DatoValor to = builder.build();

	                	    // Agregamos la opción a la lista de valores
	                	    info.add(to);
	                	}

	                }

	                // Construimos un objeto DetDatoActividad con la información de la columna
	                DetDatoActividad ite = null;
	                		//construirDetDatoActividad(columna, seccion, session, nodo, info);
	                
	                // Agregamos el objeto DetDatoActividad a la lista final
	                list.add(ite);
	            }
	        }
	    }
	    
	    
	    // Finalmente, construimos el objeto ActividadTO con todos los datos recopilados
	    ActividadTO to2 = construirActividadTO(list, nodo, session);
	    		
	    
	    // Devolvemos el objeto ActividadTO que será usado en la interfaz móvil
	    return to2;
	}
	
	
	// Método principal para obtener el formulario dinámico móvil y construir la actividad
	@Override
	public ActividadTO obtenerFormularioDinamicoMovil(DatosAutenticacionTO session, NodoTO nodo) throws BrioBPMException, ParseException {
		
		log.info("----------- obtenerFormularioDinamicoMovil -----------");
		log.info("session: " + session.toString());
		log.info("nodo: " + nodo.toString());
	    
	    // Llamamos al servicio para obtener el formulario dinámico completo
	    DAORet<FormularioDinamico, RetMsg> formularioCompleto = formularioService.obtenerFormularioDinamicoMovil(session, nodo);
	    
	    // Obtenemos el contenido del formulario dinámico
	    FormularioDinamico formulario = formularioCompleto.getContent();
	    
	    // Procesamos las secciones del formulario para construir la lista de DetDatoActividad
	    List<DetDatoActividad> datos = procesarSecciones(formulario, session, nodo);
	    
	    // Construimos y devolvemos el objeto ActividadTO con la información recopilada
	    return construirActividadTO(datos, nodo, session);
	}

	// Método para procesar las secciones del formulario y construir la lista de DetDatoActividad
	private List<DetDatoActividad> procesarSecciones(FormularioDinamico formulario, DatosAutenticacionTO session, NodoTO nodo) {
	    
		log.info("----------- procesarSecciones -----------");
		log.info("formulario: " + formulario.toString());
		log.info("session: " + session.toString());
		log.info("nodo: " + nodo.toString());
			
	    // Creamos una lista para almacenar todos los datos (objetos DetDatoActividad)
	    List<DetDatoActividad> list = new ArrayList<>();
	    
	    // Iteramos a través de las secciones del formulario
	    for (Section seccion : formulario.getSections()) {
	    	
	    	log.info("*** seccion: " + seccion.toString());
	    	log.info("*** seccion.getSequential(): " + seccion.getSequential());
	        	    	
	    	String tipo = seccion.getProperties().getContenido();
	        log.info("TIPO DE SECCION: " + tipo);
	        
	    	// Verificamos si la sección es de tipo "Sequential" (secuencia de columnas)
	    	if("VARIABLES".equals(tipo)) {
	            
	            // Procesamos las columnas de la sección y las agregamos a la lista de DetDatoActividad
	            list.addAll(procesarColumnas(seccion, session, nodo, tipo));
	            
	        } else if("DOCUMENTOS".equals(tipo)) {
	        	
            	// Procesamos las columnas de la sección y las agregamos a la lista de DetDatoActividad
                list.addAll(procesarColumnas(seccion, session, nodo, tipo));
	        	
	        }
	    }
	    
	    int numeroDato = 1;
	    log.info("Colocando numeros de dato");
	    for (DetDatoActividad renglon : list) {
	    	renglon.setNumeroDato(numeroDato);
	    	numeroDato++;
	    }
	    
	    
	    
	    // Devolvemos la lista final de DetDatoActividad
	    return list;
	}

	// Método para procesar las columnas dentro de una sección y construir los objetos DetDatoActividad
	private List<DetDatoActividad> procesarColumnas(Section seccion, DatosAutenticacionTO session, NodoTO nodo, String tipo) {
	    
		log.info("----------- procesarColumnas -----------");
		log.info("seccion: " + seccion.toString());
		log.info("session: " + session.toString());
		log.info("nodo: " + nodo.toString());
		
	    // Creamos una lista para almacenar los objetos DetDatoActividad
	    List<DetDatoActividad> list = new ArrayList<>();
	    
	    log.info("TIPO: " + tipo);
	    
	    switch(tipo) {
	        case "DOCUMENTOS":
        		list.addAll(procesarColumnasDocumento(seccion, session, nodo));
        		break;
        	case "VARIABLES":
        		list.addAll(procesarColumnasSequential(seccion, session, nodo));
        		break;
        	default:
        		break;
        
	    }
	    
	    // Devolvemos la lista de DetDatoActividad de esta sección
	    return list;
	}
	
	private List<DetDatoActividad> procesarColumnasSequential(Section seccion, DatosAutenticacionTO session, NodoTO nodo){
		
		List<DetDatoActividad> list = new ArrayList<>();
		
		// Iteramos sobre las columnas de la sección secuencial
	    for (ColumnSection columna : seccion.getSequential().getColumns()) {
	    	
	    	log.info("-----> columna: " + columna.toString());
	        
	        // Creamos una nueva lista de valores (opciones) para cada columna
	        List<DatoValor> info = obtenerValoresColumna(columna, seccion);
	        
	        // Castamos los datos de la columna usando el helper y construimos el DetDatoActividad
	        DetDatoActividad ite = construirDetDatoActividadSec(columna, seccion, session, nodo, info);
	        
	        log.info("ite: " + ite.toString());
	        
	        // Agregamos el objeto DetDatoActividad a la lista
	        list.add(ite);
	        
	    }
	    
		return list;
	}
	
	
	private List<DetDatoActividad> procesarColumnasDocumento(Section seccion, DatosAutenticacionTO session, NodoTO nodo){
		
		log.info("----------- procesarColumnasDocumento -----------");
		log.info("seccion {}: {}", seccion.toString());
		
		List<DetDatoActividad> list = new ArrayList<>();
		
		

		String  seccionProceso = stSeccionProcesoRepository.encontrar(
				session.getCveEntidad()
				,nodo.getCveProceso()
				,nodo.getVersion()	
				,seccion.getProperties().getCveSeccion()
				);
		
		log.info("*** SECCION PROCESO: {}", seccionProceso);
		
		
		String etiqueta = seccion.getProperties().getEtiqueta();
		
		if (seccionProceso != null) {
			
			
			log.info("StDocumentoSeccion ----> CVE ENTIDAD: {} | CVEPROCESO: {} | VERSION: {} | CVE INSTANCIA: {} | SECCION PROCESO: {} | DOCUMENTOS: {}",
					session.getCveEntidad(), nodo.getCveProceso(), nodo.getVersion(), nodo.getCveInstancia(), seccionProceso);
			List<StDocumentoSeccion> stDocumentos = stDocumentoSeccionRepository
					.encuentraStDocumentoSeccion(
							session.getCveEntidad(),
							nodo.getCveProceso(),
							nodo.getVersion(),
							nodo.getCveNodo(),
							nodo.getIdNodo(),
							seccionProceso);
			
			log.info("ST DOCUMENTOS {} : {}", stDocumentos.size());
			DetDatoActividad ite = new DetDatoActividad();
			for (StDocumentoSeccion stDocumento : stDocumentos) {
								
				// Castamos los datos de la columna usando el helper y construimos el DetDatoActividad
		        ite = construirDetDatoActividadDoc(null, seccion, session, nodo, etiqueta, null, stDocumento);

		        
				if (ite != null) {
					list.add(ite);
				}
			}
		}
   
		log.info("---->>>> LISTA: " + list.size());
	      
		return list;
	}


	private DetDatoActividad construirDetDatoActividadDoc(ColumnSection columna, Section seccion,
			DatosAutenticacionTO session, NodoTO nodo, String etiqueta, InDocumentoProcesoOc documento, StDocumentoSeccion stDocumento) {
		
		log.info("----------- construirDetDatoActividadDoc -----------");
		log.info("seccion: " + seccion.toString());
		log.info("session: " + session.toString());
		log.info("nodo: " + nodo.toString());
		log.info("etiqueta: " + etiqueta);
		
		
		Integer secuenciaDocumento = stDocumento.getId().getSecuenciaDocumento();
		log.info("SEC DOCUMENTO: " + secuenciaDocumento);
		
		Integer ocurrencia = 1;
		if (documento != null) {
            ocurrencia = documento.getId().getOcurrencia() == null ? 1 : documento.getId().getOcurrencia();
            
        }
		
		log.info("OCURRENCIA: " + ocurrencia);
		
		// REVISAR EL TEMA DE LA OCURRENCIA OCURRENCIA = NULL
		String cveVariable = null;
		String cveSeccion = seccion.getProperties().getCveSeccion();
		log.info("CVE SECCION: {} | CVE VARIABLE: {}", cveSeccion, cveVariable);
		log.info("ID NODO: {} | CVE NODO: {} | SECUENCIA DOCUMENTO: {}", nodo.getIdNodo(), nodo.getCveNodo(), secuenciaDocumento);
		
		
	    // Construimos la clave primaria para buscar la información de la columna
		StDocumentoSeccionPK id = StDocumentoSeccionPK.builder()
				.cveEntidad(session.getCveEntidad())
				.cveProceso(nodo.getCveProceso())
				.version(nodo.getVersion())
				.cveNodo(nodo.getCveNodo())
				.idNodo(nodo.getIdNodo())
				.cveSeccion(cveSeccion)
				.secuenciaDocumento(secuenciaDocumento)
				.build();
		

	    String requerida;
	    Optional<StDocumentoSeccion> entidad = stDocumentoSeccionRepository.findById(id);
	    if (entidad.isPresent()) {
	    	log.info(" DOC PRESENTE");
	        requerida = entidad.get().getRequerida();
	
	        
	        log.info("REQUERIDA: " + requerida);
	
		String tipoInteraccion = "S";
	    // Castamos los datos de la columna usando el helper
	    String datos = appMovilHelper.casteaDatos(
	    		"DOCUMENTO",
	            requerida,
	            "DOCUMENTO",
	            "ENTRADA");

	    log.info("DATOS: " + datos);
	    
				
	   // byte[] documentoValor = documento.getArchivoBinario(); // Separamos los datos obtenidos del helper por "|" para asignarlos a los campos correspondientes
	    String[] dato = datos.split("\\|");
	    
	    //String cveVarible = "DOCUMENTO" + "|" + secuenciaDocumento;
	    
        String variableConSeccion = seccion.getProperties().getCveSeccion() +  "|" + ocurrencia + "|" + secuenciaDocumento;

        String interaccion = dato[4];
        String tipoControl = dato[3];
		
	    // Construimos y devolvemos el objeto DetDatoActividad con la información de la columna
	    return DetDatoActividad.builder()
	            .tipoDato(dato[0])  // Tipo de dato
	            .alineacion(dato[1])  // Alineación
	            .requerido(dato[2])  // Requerido (SI o NO)
	            .tipoControl(tipoControl)  // Tipo de control (ej. textbox, checkbox, etc.)
	            .interaccion(interaccion)  // Tipo de interacción (ej. salida, entrada, etc.)
	            .etiqueta(etiqueta)  // Etiqueta que describe la columna
	            .listaValores(null)// Asignamos la lista de opciones procesadas (info)
        		//.documentoValor(documentoValor)
	            .seccionVariable(variableConSeccion)
	            .build();
	    }
	    
	    
	    log.info("DOC NO PRESENTE");
	    return null;
		
	}



	/*
	 * Método para construir un objeto ActividadTO a partir de una lista de DetDatoActividad.
	 * @param datos Lista de objetos DetDatoActividad con la información de las columnas.
	 * @param nodo Nodo en el contexto del cual se procesan los datos.
	 * 
	 * @return Objeto ActividadTO construido con los datos de las columnas.
	 */
	// Método que obtiene los valores de una columna en una sección
	private List<DatoValor> obtenerValoresColumna(ColumnSection columna, Section seccion) {
	    log.info("----------- obtenerValoresColumna -----------");

	    // Lista para almacenar los valores obtenidos
	    List<DatoValor> info = new ArrayList<>();

	    // Si la columna, su control o las opciones son nulos, agrega un valor por defecto "ARCHIVO" y retorna
	    if (columna == null || columna.getControl() == null || columna.getControl().getOptions() == null) {
	        info.add(DatoValor.builder().valor("ARCHIVO").build());
	        return info;
	    }

	    // Se obtiene el tipo de interacción de la columna
	    String tipoInteraccion = columna.getControl().getTipoInteraccion();
	    // Se obtiene la lista de opciones disponibles para la columna
	    List<List<String>> opcionesList = columna.getControl().getOptions();

	    log.info("-.-. columna: " + columna.toString());

	    // Listas para almacenar los valores seleccionados y todos los valores
	    List<DatoValor> valoresSeleccionados = new ArrayList<>();
	    List<DatoValor> todosLosValores = new ArrayList<>();

	    // Itera sobre las opciones de la columna
	    for (List<String> opciones : opcionesList) {
	        // Si la opción está vacía, se omite
	        if (opciones.isEmpty()) continue;

	        // Crea un builder para un nuevo objeto DatoValor
	        DatoValor.DatoValorBuilder builder = DatoValor.builder();
	        // El primer valor de la opción se asigna como el valor de DatoValor
	        builder.valor(opciones.get(0));

	        // Si hay más de un valor en la opción, se asigna el primer valor como id y el segundo como valor
	        if (opciones.size() > 1) {
	            builder.id(opciones.get(0));
	            builder.valor(opciones.get(1));
	        }

	        // Si hay un tercer valor y es "SI", se marca como seleccionado
	        boolean seleccionado = opciones.size() > 2 && "SI".equalsIgnoreCase(opciones.get(2));
	        builder.seleccionado(seleccionado);

	        // Se construye el objeto DatoValor
	        DatoValor to = builder.build();
	        log.info("*****DATO VALOR: " + to.toString());

	        // Agrega el valor a la lista de todos los valores
	        todosLosValores.add(to);
	        // Si está seleccionado, se agrega a la lista de valores seleccionados
	        if (seleccionado) {
	            valoresSeleccionados.add(to);
	        }
	    }

	    // Si la interacción es "ENTRADA" y no hay valores seleccionados, se retornan todos los valores
	    if ("ENTRADA".equals(tipoInteraccion) || valoresSeleccionados.isEmpty()) {
	        return todosLosValores;
	    }

	    // Si la interacción es "SALIDA" y no hay valores seleccionados, se retorna la lista vacía
	    if ("SALIDA".equals(tipoInteraccion) && valoresSeleccionados.isEmpty()) {
	        return todosLosValores;
	    }

	    log.info("termina obtenerValoresColumna");
	    // Retorna los valores seleccionados
	    return valoresSeleccionados;
	}


	/**
	 * Construye un objeto DetDatoActividad a partir de la información de una columna en una sección.
	 *
	 * @param columna        Columna que contiene las propiedades y control para el dato.
	 * @param seccion        Sección a la que pertenece la columna.
	 * @param session        Datos de autenticación del usuario.
	 * @param nodo           Nodo en el contexto del cual se procesa el dato.
	 * @param info           Lista de valores procesados para el dato.
	 * @return               Objeto DetDatoActividad construido con los datos de la columna.
	 */
	private DetDatoActividad construirDetDatoActividadSec(ColumnSection columna, Section seccion, DatosAutenticacionTO session, NodoTO nodo, List<DatoValor> info) {
	    
		log.info("----------- construirDetDatoActividad -----------");
		log.info("columna: " + columna.toString());
		log.info("seccion: " + seccion.toString());
		log.info("session: " + session.toString());
		log.info("nodo: " + nodo.toString());
		log.info("info: " + info.toString());
		String cveVariable = columna.getProperties().getCveVariable();
		String cveSeccion = seccion.getProperties().getCveSeccion();
		
		log.info("CVE SECCION: {} | CVE VARIABLE: {}", cveSeccion, cveVariable);
		
		//Integer numeroDato = columna.getSecuencia();
		
	    // Construimos la clave primaria para buscar la información de la columna
	    StVariableSeccionPK id = StVariableSeccionPK.builder()
	            .cveEntidad(session.getCveEntidad())
	            .cveProceso(nodo.getCveProceso())
	            .version(nodo.getVersion())
	            .cveNodo(nodo.getCveNodo())
	            .idNodo(new BigDecimal(nodo.getIdNodo()))
	            .cveSeccion(cveSeccion)
	            .cveVariable(cveVariable)
	            .build();

	    String requerida;
	    
	    // Buscamos la entidad correspondiente en el repositorio
	    Optional<StVariableSeccion> entidad = variableSeccionRepository.findById(id);
	    if (entidad.isPresent()) {
	        requerida = entidad.get().getRequerida();
	    } else {
	        // Manejo de caso cuando la entidad no está presente
	        requerida = null;
	    }
	    
	    log.info("REQUERIDA: " + requerida);
	    log.info("TIPO DE DATO: " + columna.getProperties().getTipoDato());
	    log.info("TIPO DE CONTROL: " + columna.getControl().getTipoControl());
	    log.info("TIPO DE INTERACCION: " + columna.getControl().getTipoInteraccion());

	    // Obtenemos el tipo de interacción de la columna
	    String tipoInteraccion = columna.getControl().getTipoInteraccion();
	    // Castamos los datos de la columna usando el helper
	    String datos = appMovilHelper.casteaDatos(
	            columna.getProperties().getTipoDato(),
	            requerida,
	            columna.getControl().getTipoControl(),
	            tipoInteraccion);

	    log.info("DATOS: " + datos);
	    
	    // Separamos los datos obtenidos del helper por "|" para asignarlos a los campos correspondientes
	    String[] dato = datos.split("\\|");
	    
        String variableConSeccion = seccion.getProperties().getCveSeccion() +  "|" + columna.getProperties().getCveVariable();
        
        String tipoControl = dato[3];
        String interaccion = dato[4];
        
        // si david no puede hacer el cambio, descomentar esta parte
//		if ("CB".equals(tipoControl) && "S".equals(interaccion)) {
//			tipoControl = "TB";
//		}
        
	    // Construimos y devolvemos el objeto DetDatoActividad con la información de la columna
	    return DetDatoActividad.builder()
	            .tipoDato(dato[0])  // Tipo de dato
	            .alineacion(dato[1])  // Alineación
	            .requerido(dato[2])  // Requerido (SI o NO)
	            .tipoControl(tipoControl)  // Tipo de control (ej. textbox, checkbox, etc.)
	            .interaccion(interaccion)  // Tipo de interacción (ej. salida, entrada, etc.)
	            .etiqueta(columna.getProperties().getEtiqueta())  // Etiqueta que describe la columna
	            .listaValores(info)  // Asignamos la lista de opciones procesadas (info)
        		.seccionVariable(variableConSeccion)
	            .build();
	}

	
	/*
	 * Método para construir un objeto ActividadTO con los datos y la descripción.
	 * @param columna Columna que contiene las propiedades y control para el dato.
	 * @param seccion Sección a la que pertenece la columna.
	 * @param session Datos de autenticación del usuario.
	 * @param nodo Nodo en el contexto del cual se procesa el dato.
	 * 
	 */
	// Método para construir el objeto ActividadTO con los datos y la descripción
	private ActividadTO construirActividadTO(List<DetDatoActividad> datos, NodoTO nodo, DatosAutenticacionTO session) {
	    
		log.info("----------- construirActividadTO -----------");
		log.info("datos: " + datos.toString());
		log.info("nodo: " + nodo.toString());
		log.info("session: " + session.toString());
		
		
//		for (DetDatoActividad detDatoActividad : datos) {
//			if (detDatoActividad.getTipoControl().equals("D")) {
//				log.info("*****DOCUMENTO");
//				detDatoActividad.setRequerido("O");
//			}
//		}
		
	    // Construimos la clave primaria para buscar el nodo en la base de datos
	    StNodoProcesoPK id2 = StNodoProcesoPK.builder()
	            .cveEntidad(session.getCveEntidad())
	            .cveProceso(nodo.getCveProceso())
	            .version(nodo.getVersion())
	            .cveNodo(nodo.getCveNodo())
	            .idNodo(nodo.getIdNodo())
	            .build();
	    
	    // Buscamos la entidad correspondiente en el repositorio
	    Optional<StNodoProceso> entidad2 = nodoProcesoRepository.findById(id2);
	    
	    // Obtenemos la descripción del nodo si está presente
	    String descripcion = entidad2.isPresent() ? entidad2.get().getDescripcion() : "";
	    
	    // Llamamos a la funcion para obtener los botones 
	    String[] botones = generaBotones(session.getCveEntidad(), session.getCveUsuario(), nodo);
	    
	    // Asignamos datos a los botones según el número de botones en el array
	    int numBotones = Integer.parseInt(botones[0]);
	    
		 // Construimos el objeto ActividadTO con el número de botones
		 ActividadTO result = ActividadTO.builder()
		         .desActividad(descripcion)  // Descripción de la actividad
		         .datos(datos)  // Asignamos la lista de DetDatoActividad
		         .numBotones(numBotones)  // Número de botones en la actividad
		         .build();	
		 
		 // Asignamos las etiquetas de los botones según el número de botones en el array
		 // SOLO SE PUEDEN CONFIGURAR UN MAXIMO DE 3 BOTOTNES PARA APP
	
		 if (numBotones >= 1) {
		     // Si hay al menos 1 botón
		     result.setBtnEtiquetaUno(botones[1]);  // Asignar el dato del primer botón
		 }
		 if (numBotones >= 2) {
		     // Si hay al menos 2 botones
		     result.setBtnEtiquetaDos(botones[2]);  // Asignar el dato del segundo botón
		 }
		 if (numBotones >= 3) {
		     // Si hay 3 botones
		     result.setBtnEtiquetaTres(botones[3]);  // Asignar el dato del tercer botón
		 }
		    
	    return result;
	}

	
	/*
	 * Método para generar los botones de una actividad.
	 * @param cveEntidad Clave de la entidad a la que pertenece el usuario.
	 * @param cveUsuario Clave del usuario que realiza la actividad.
	 * @param nodo Nodo actual en el que se encuentra el usuario.
	 * @return Array de Strings con los botones generados.
	 */
	public String[] generaBotones(String cveEntidad, String cveUsuario, NodoTO nodo) {
		
		log.info("------------------generaBotones-------------------");
		log.info("cveEntidad: " + cveEntidad);
		log.info("cveUsuario: " + cveUsuario);
		log.info("nodo: " + nodo.toString());
		
	    // Suponiendo que encuentras datos y los almacenas en la lista
	    List<Object[]> datosBotones = stBotonNodoRepository.obtenerDatosBotones(
	    		cveEntidad, nodo.getCveProceso(), nodo.getVersion(),
	    		nodo.getCveNodo(), nodo.getIdNodo(), cveUsuario);
	    
	    log.info("DATOS BOTONES: " + datosBotones.size());
	    
	    String[] botones = null;
	    
	    // Obtener el tamaño de la lista o establecer un maximo de 3
	    // Si hay más de 3 botones, se toman solo los primeros 3
	    Integer sizeBoton = datosBotones != null ? datosBotones.size() : 0;
	    // Limitar el tamaño máximo a 3
	    sizeBoton = sizeBoton > 3 ? 3 : sizeBoton;
	    
	    log.info("TAMAÑO BOTON: " + sizeBoton);

	    // Inicializar el array de botones con el tamaño calculado + 1 (en la primera posicion va el numero de botones)
	    botones = new String[sizeBoton + 1];

	    // El primer botón contiene el tamaño
	    botones[0] = sizeBoton.toString();

	    // Iterar sobre los datos de los botones
	    for (int i = 1; i <= sizeBoton; i++) {
	    	
	        StringBuilder datoBoton = new StringBuilder();  // Usamos StringBuilder para concatenar las cadenas

	        // 
	        if (datosBotones != null && i - 1 < datosBotones.size()) {
	            Object[] filaDatos = datosBotones.get(i - 1);  // Obtener el objeto en la posición i-1

	            // Iterar a través de los campos que necesitamos (posiciones 1 a 6)
	            for (int j = 1; j <= 6; j++) {
	                // Si el valor en la posición `j` es null, se asigna un string vacío, de lo contrario se concatena su valor
	                String valorCampo = filaDatos[j] != null ? filaDatos[j].toString() : "";
	                switch(valorCampo) {
	                
	                case "NO":
	                	valorCampo = "N";
	                	break;
	                case "SI":
	                	valorCampo = "S";
	                	break;
	                default:
	                	break;
	                }
	                
	                datoBoton.append(valorCampo);  // Concatenar el valor

	                // Agregar el separador "|" entre los campos, excepto después del último
	                if (j < 6) {
	                    datoBoton.append("|");
	                }
	            }
	        }
	        
	        botones[i] = datoBoton.toString();  // Asignar la cadena concatenada al array de botones
	    }

	    return botones; // Retornar el array de botones generado
	}
	
	@Override
	/**
	 * Registra las respuestas del usuario en el proceso y guarda los valores de acuerdo al tipo de control.
	 *
	 * @param userSession la sesión del usuario que contiene el usuario y entidad
	 * @param nodo el nodo actual que contiene la versión y proceso
	 * @param respuestasFormulario lista de respuestas a registrar, incluyendo el tipo de control y valores
	 * @return un objeto EstatusTO que indica el resultado de la operación
	 */
	public EstatusTO registraRespuestaUsuarioN(DatosAutenticacionTO userSession, NodoTO nodo, List<RespuestaFormularioTO> respuestasFormulario) throws ParseException {
	    
	    log.info("--------------------registraRespuestaUsuario---------------------");
	    log.info("userSession: {}", userSession);
	    log.info("nodo: {}", nodo);


	    // Crear un objeto EstatusTO con el resultado de la operación
	    EstatusTO result = EstatusTO.builder()
	            .mensaje("")
	            .tipoExcepcion(Constants.OK)
	            .build();

	    log.info("respuestasFormulario: {} : {}", respuestasFormulario.size(), respuestasFormulario);

	    // Iterar sobre las respuestas del formulario
	    for (RespuestaFormularioTO respuesta : respuestasFormulario) {
	    	
	    	// Procesar y guardar la respuesta en la base de datos
	        procesarYGuardarRespuesta(respuesta, userSession, nodo);
	    }

	    
	    //AJUSTE APLICA REGLAS EN VARIABLES
	    RetMsg estatus = nodoHelper.evaluaReglasProcesoTerminar (userSession, nodo, "GUARDAR");
	    
	    if (estatus.getStatus().equals(Constants.ERROR)) {
	    	result.setTipoExcepcion(Constants.ERROR);
	    	result.setMensaje(estatus.getMessage());
	    }
	    
	    return result;
	}

	/**
	 * Procesa la respuesta y guarda el valor en la base de datos si es necesario.
	 */
	private void procesarYGuardarRespuesta(RespuestaFormularioTO respuesta, DatosAutenticacionTO userSession,  NodoTO nodo) throws ParseException {
	    
		log.info("PROCESAR Y GUARDAR RESPUESTA");
		log.info("RESPUESTA: " + respuesta.toString());
		log.info("USER SESSION: " + userSession.toString());
		log.info("NODO: " + nodo.toString());
		
		String seccionVariable = respuesta.getSecccionVariable();
	    String cveEntidad = userSession.getCveEntidad();
	    String tipoControl = respuesta.getTipoControl();
	    String cveProceso = nodo.getCveProceso();
	    BigDecimal version = nodo.getVersion();
	    String cveNodo = nodo.getCveNodo();
	    Integer idNodo = nodo.getIdNodo();
	    		
	    log.info("seccionVariable: {}", seccionVariable);

	    // Separar la sección y variable de la cadena seccionVariable
	    String[] seccionConVariable = seccionVariable != null ? seccionVariable.split("\\|") : null;
	    
	    // Verificar que la sección y variable no sean nulas o tengan menos de 2 elementos
	    if (seccionConVariable == null || seccionConVariable.length < 2) {
	        return;
	    }

	    String cveSeccion = seccionConVariable[0];
	    String cveVariable = seccionConVariable[1];
	    log.info("cveVariable: {}, cveSeccion: {}", cveVariable, cveSeccion);

	    // Verificar si la variable es DOCUMENTO y no procesarla
	    if (cveVariable.equals("DOCUMENTO")) return;

	    // Construir la clave primaria para buscar la información de la sección y nodo
	    StSeccionNodoPK idStSeccionNodo = StSeccionNodoPK.builder()
	            .cveEntidad(cveEntidad)
	            .cveProceso(cveProceso)
	            .version(version)
	            .cveNodo(cveNodo)
	            .idNodo(idNodo)
	            .cveSeccion(cveSeccion)
	            .build();
	    
	    // Buscar la sección y nodo en la base de datos
	    Optional<StSeccionNodo> stSeccionNodo = stSeccionNodoRepository.findById(idStSeccionNodo);

	    // Verificar si la sección y nodo están presentes
	    if (stSeccionNodo.isPresent() ) {
	    	
	    	// Verificar si la sección y nodo tienen registros seleccionables
	        if (stSeccionNodo.get().getRegsitrosSeleccionables() == null) {
	        	log.info("NO REGISTROS SELECCIONABLES");
	        	// Guardar el valor de la variable en la base de datos según el tipo de control
	            guardarValorVariable(respuesta, userSession, nodo, tipoControl);
	        } else {
	        	log.info("REGISTROS SELECCIONABLES");
	        	// Gestionar los registros seleccionables y almacenar los valores en la base de datos
	            gestionarRegistrosSeleccionables(userSession, nodo, cveVariable);
	        }
	    }
    
	}

	/**
	 * Guarda un valor de variable en la base de datos según el tipo de control.
	 */
	private void guardarValorVariable(RespuestaFormularioTO respuesta, DatosAutenticacionTO userSession, NodoTO nodo, String tipoControl) throws ParseException {
	   
		log.info("GUARDAR VALOR VARIABLE");
		log.info("TIPO CONTROL: " + tipoControl);
		log.info("RESPUESTA: " + respuesta.toString());
		log.info("USER SESSION: " + userSession.toString());
		log.info("NODO: " + nodo.toString());
		
		String cveEntidad = userSession.getCveEntidad();
	    String cveProceso = nodo.getCveProceso();
	    BigDecimal version = nodo.getVersion();
	    String cveInstancia = nodo.getCveInstancia();
	    String seccionVariable = respuesta.getSecccionVariable();
	    String cveVariable = seccionVariable.split("\\|")[1];
		
	    // Buscar la variable de proceso en la base de datos
		Optional<StVariableProceso> variableProcesoOpt = stVariableProcesoRepository.findById(new StVariableProcesoPK(cveEntidad, cveProceso, version, cveVariable));
	    
		// Verificar si la variable de proceso
		if (!variableProcesoOpt.isPresent()) return;

	    String tipo = variableProcesoOpt.get().getTipo();
	    String valor = respuesta.getValue().toString().replace("value=", "");
	    log.info("valor: {}", valor);

	    InVariableProcesoPK id = InVariableProcesoPK.builder()
	            .cveEntidad(cveEntidad)
	            .cveInstancia(cveInstancia)
	            .cveProceso(cveProceso)
	            .version(version)
	            .cveVariable(cveVariable)
	            .ocurrencia(1)
	            .secuenciaValor(1)
	            .build();
	    
	    // Guardar el valor en la base de datos según el tipo de control
	    log.info("TIPO DE CONTROL: " + tipoControl);
	    
	    switch(tipoControl) {
    	case "CB": // COMBOBOX		            
        case "LB": // LISTBOX		        	 
        case "RB": // RADIOBUTTON
        	
			//tipoControl = "RADIOBUTTON";
			log.info("valor: " + respuesta.getValue().toString());
			valor = respuesta.getValue().toString();
	        valor = valor.replace("value=", "");
	        
	        // Buscar el valor de la variable en la
	        Optional<StValorVariable> variableProcesoRB = stValorVariableRepository.encuentraValor(
	        		cveEntidad, cveProceso, version, cveVariable, valor);
	        
			if (variableProcesoRB.isPresent()) {
				valor = variableProcesoRB.get().getValorAlfanumerico();
				log.info("--- variableProcesoRB: " + valor);
			}
			break;
        case "CA": // CALENDAR
			tipoControl = "CALENDAR";
			log.info("valor: " + respuesta.getValue().toString());
			valor = respuesta.getValue().toString();
	        valor = valor.replace("value=", "");
			break;
        case "TA": // TEXTAREA
			tipoControl = "TEXTAREA";
			valor = respuesta.getValue().toString();
	        valor = valor.replace("value=", "");
	            
	            log.info("valor: " + valor);
			break;
        case "TB": // TEXTBOX
        	tipoControl = "TEXTBOX";
        	valor = respuesta.getValue().toString();
            valor = valor.replace("value=", "");
              
            log.info("valor: " + valor);

            break;
        case "CX": // CHECKBOX
            break;
        default:
            break;
    	}

	    InVariableProceso entidad = InVariableProceso.builder().id(id).build();

	    switch (tipo) {
	        case Constants.ALFANUMERICO:
	            entidad.setValorAlfanumerico(valor);
	            break;
	        case Constants.ENTERO:
	            entidad.setValorEntero(Integer.parseInt(valor));
	            break;
	        case Constants.DECIMAL:
	            entidad.setValorDecimal(new BigDecimal(valor));
	            break;
	        case Constants.FECHA:
	            Instant instant = Instant.parse(valor);  // Parsea la fecha en UTC
	            Date fecha = Date.from(instant); // Convierte Instant a Date
	            entidad.setValorFecha(fecha);
	            break;
	    }

	    inVariableProcesoRepository.saveAndFlush(entidad);
	}

	/**
	 * Gestiona los registros seleccionables y almacena los valores en la base de datos.
	 */
	private void gestionarRegistrosSeleccionables(DatosAutenticacionTO userSession, NodoTO nodo, String cveVariable) {
	    
		log.info("GESTIONAR REGISTROS SELECCIONABLES");
		
		String cveEntidad = userSession.getCveEntidad();
		String cveProceso = nodo.getCveProceso();
		BigDecimal version = nodo.getVersion();
		String cveInstancia = nodo.getCveInstancia();
		
		List<InVariableProceso> lista = inVariableProcesoRepository.encuentraLista(cveEntidad, cveProceso, version, cveVariable);
	    log.info("LISTA: {}", lista.size());

	    int contador = 1;
	    for (InVariableProceso inVariableProceso : lista) {
	        InVariableProcesoPK inVariableProcesID = InVariableProcesoPK.builder()
	                .cveEntidad(cveEntidad)
	                .cveInstancia(cveInstancia)
	                .cveProceso(cveProceso)
	                .version(version)
	                .cveVariable(cveVariable)
	                .ocurrencia(contador)
	                .secuenciaValor(1)
	                .build();

	        InVariableProceso entidad = InVariableProceso.builder()
	                .id(inVariableProcesID)
	                .valorAlfanumerico(contador == inVariableProceso.getId().getOcurrencia() ? Constants.SI : Constants.NO)
	                .build();

	        inVariableProcesoRepository.saveAndFlush(entidad);
	        contador++;
	    }
	}


	/**
	 * Selecciona y ejecuta una acción específica en función de la clave del botón proporcionada (cveBoton).
	 * 
	 * @param userSession Datos de autenticación del usuario.
	 * @param nodo Nodo sobre el cual se realizará la acción.
	 * @param listaRespuestas Lista de objetos DatoGuardar que contienen las respuestas del usuario.
	 * @param cveBoton Clave del botón que indica la acción a ejecutar ("GUARDAR", "TERMINAR", etc.).
	 * @return EstatusTO que indica el estado y resultado de la acción ejecutada.
	 * @throws BrioBPMException En caso de error durante la ejecución de la acción.
	 * @throws ParseException En caso de error de formato de datos.
	 */
	@Override
	public EstatusTO seleccionaAccion(DatosAutenticacionTO userSession, NodoTO nodo, String cveBoton, 
			List<RespuestaFormularioTO> respuestasFormulario) throws BrioBPMException, ParseException {
	    
	    // Registro en el log indicando el inicio del método.
	    log.info("-----------------seleccionaAccion----------------------");
	    log.info("cveBoton: " + cveBoton);
	    log.info("userSession: " + userSession.toString());
	    log.info("nodo: " + nodo.toString());
	    
	    //log.info("listaRespuestas: " + listaRespuestas != null ? listaRespuestas.toString() : "LISTA NULA");
	    
	    // Creación de un objeto EstatusTO con mensaje vacío. Este será el objeto de respuesta que devolverá el método.
	    EstatusTO result = EstatusTO.builder()
	    		.tipoExcepcion(Constants.OK)
	            .mensaje("")
	            .build();
	    
	    //cveBoton = "Guardar";  -- se comenta
	    //ajuste para validar el terminar
	    nodo.setUsoSeccion("APP");
	    // Evaluación de la acción según el valor de cveBoton.
	    switch (cveBoton) {
	    case "Guardar":
	    	log.info("ENTRA A GUARDAR");
	        // Si la clave del botón es "GUARDAR", se registra la respuesta del usuario.
	        result = registraRespuestaUsuarioN(userSession, nodo, respuestasFormulario);
	        break;
	        
	    case "Finalizar":
	    	log.info("ENTRA A TERMINAR");
	        // Si la clave del botón es "TERMINAR", registra la respuesta del usuario y finaliza la actividad.
	        result = registraRespuestaUsuarioN(userSession, nodo, respuestasFormulario);
	        // Termina la actividad en el nodo y recibe un mensaje de resultado.
	        RetMsg resultado = nodoHelper.terminaActividad(userSession, nodo);
	        
		    //AJUSTE APLICA REGLAS EN VARIABLES
	        if (resultado.getStatus().equals(Constants.OK)) { //Se ajusta para poner reglas al terminar
	        	RetMsg estatus = nodoHelper.evaluaReglasProcesoTerminar (userSession, nodo, "TERMINAR");
	        	
	        	if (estatus.getStatus().equals(Constants.ERROR)) {
	    	        result.setMensaje(resultado.getMessage());
	    	        result.setTipoExcepcion(resultado.getStatus());
	        	}
	        }
		    	        
	        // Asigna el mensaje y el tipo de excepción al objeto result en base al resultado de la actividad.
	        result.setMensaje(resultado.getMessage());
	        result.setTipoExcepcion(resultado.getStatus());
	        break;

	    default:
	        // Para cualquier otro valor de cveBoton, configura el tipo de excepción como un error genérico.
	        result.setTipoExcepcion(Constants.ERROR);
	        break;
	    }
	    
	    // Retorna el objeto result con el estado y mensaje configurados según la acción seleccionada.
	    log.info("RESULTADO TERMINADO APP: " + result.toString());
	    return result;
	}
	
	
	/**
	 * Carga la información de los documentos de la base de datos y los devuelve en
	 * un objeto DocumentoAppTO.
	 * 
	 * @param session      Datos de autenticación del usuario.
	 * @param documentoAPP Objeto DocumentoAppTO que contiene la información de los
	 *                     documentos a cargar.
	 * @return Objeto DocumentoAppTO con los documentos cargados.
	 */
	@Override
	public EstatusTO uploadImageService(DatosAutenticacionTO session, DocumentoAppTO documentoAPP) {
	    
		log.info("----------- uploadImageService -----------");
		
		// Crea ResultadoTO con valores iniciales
		EstatusTO result = EstatusTO
	            .builder()
	            .tipoExcepcion(Constants.OK)
	            .mensaje("")
	            .build();
	    
	    String cveEntidad = session.getCveEntidad();
	    String cveProceso = documentoAPP.getCveProceso();
	    BigDecimal version = documentoAPP.getVersion();
	    String cveInstancia = documentoAPP.getCveInstancia();
	    log.info("CVE ENTIDAD: {} | CVE PROCESO: {} | VERSION: {} | CVE INSTANCIA: {}", cveEntidad, cveProceso, version, cveInstancia);
	    
	    Integer secuenciaDocumento = 1;
	    Integer secuenciaArchivo = 1;
	    Integer ocurrencia = 1;
	    String seccionVariable = "";

	    log.info("DOCUMENTOS: " + documentoAPP.getDocumentos().size());
	    
	    // Iterar sobre cada documento en la lista de documentos
	    for (DocumentoIinfoTO docInfo : documentoAPP.getDocumentos()) {
	        
	    	if (docInfo.getSeccionVariable() != null && !docInfo.getSeccionVariable().isEmpty()) {
	    		String[] info = docInfo.getSeccionVariable().split("\\|");
	    		ocurrencia = Integer.valueOf(info[1]);
	    		secuenciaDocumento = Integer.valueOf(info[2]);
	    		
	    		if (!seccionVariable.equals(docInfo.getSeccionVariable())) {
	    	    	// Eliminar el documento si ya existe
	    	    	inDocumentoProcesoOCRepository.deleteMulDocumentoProceso(
	    	    			cveEntidad,
	    	    			cveProceso,
	    	    			version,
	    	    			cveInstancia,
	    	    			secuenciaDocumento,
	    	    			ocurrencia);    	    	
	    	    	//reinicia la variable para otro grupo
	    	    	seccionVariable = docInfo.getSeccionVariable();
	    	    	// Obtener la secuencia del documento
	    	    	secuenciaArchivo =  1;   	    	
	    	    	log.error("ELIMINAR DOCUMENTOS DE LA SECCION: " + seccionVariable);
	    		}
	    	} else {
	    		if (seccionVariable.isEmpty()) {
	    	    	// Eliminar el documento si ya existe
	    	    	inDocumentoProcesoOCRepository.deleteMulDocumentoProceso(
	    	    			cveEntidad,
	    	    			cveProceso,
	    	    			version,
	    	    			cveInstancia,
	    	    			secuenciaDocumento,
	    	    			ocurrencia);    	    	
	    	    	//reinicia la variable para otro grupo
	    	    	seccionVariable = "NA";
	    	    	// Obtener la secuencia del documento
	    	    	secuenciaArchivo =  1;   	    	
	    	    	log.error("ELIMINAR DOCUMENTOS DE LA SECCION: UNO");
	    		}
	    	}
	    	
	    	log.info("SEC DOCUMENTO: " + secuenciaDocumento);
	    	log.info("OCURRENCIA: " + ocurrencia);
	    	log.info("SEC ARCHIVO: " + secuenciaArchivo);
	    	log.info("NOMBRE ARCHIVO: " + docInfo.getNomArchivo());
	    			
	    	// Crear una nueva instancia de InDocumentoProcesoOcPK en cada iteración
	        InDocumentoProcesoOcPK documentoPK = InDocumentoProcesoOcPK.builder()
	                .cveEntidad(cveEntidad)
	                .cveProceso(cveProceso)
	                .version(version)
	                .cveInstancia(cveInstancia)
	                .secuenciaDocumento(secuenciaDocumento)
	                .ocurrencia(ocurrencia)
	                .secuenciaArchivo(secuenciaArchivo)
	                .build();

	        // Crear un nuevo documento con la PK creada
	        InDocumentoProcesoOc documento = InDocumentoProcesoOc.builder()
	                .id(documentoPK)
	                .nombreArchivo(docInfo.getNomArchivo())
	                .archivoBinario(docInfo.getData())
	                .contentType(docInfo.getContentType())
	                .build();

	        log.info("GUARDAR DOCUMENTO");
	        // Guardar el documento en la base de datos
	        inDocumentoProcesoOCRepository.saveAndFlush(documento);
	        
	        // Verificar si el documento fue guardado correctamente
	        Optional<InDocumentoProcesoOc> doc = inDocumentoProcesoOCRepository.findById(documentoPK);
			
	        if (!doc.isPresent()) {
				log.info("Documento no encontrado");
				result.setTipoExcepcion(Constants.ERROR);
				break;
			}
	        secuenciaArchivo++;
	    }

	    log.info("RESULTADO uploadImageService: " + result.toString());
	    
	    // Retornar el resultado de la operación
	    return result;
	}


}
