/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */
package com.briomax.briobpm.business.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.briomax.briobpm.business.helpers.base.IFormularioPantalla;
import com.briomax.briobpm.business.helpers.base.INodoHelper;
import com.briomax.briobpm.business.helpers.base.IReglaActividadHelper;
import com.briomax.briobpm.business.helpers.base.ITraductorHelper;
import com.briomax.briobpm.business.service.catalogs.core.IMessagesService;
import com.briomax.briobpm.business.service.core.IFormularioDinamicoService;
import com.briomax.briobpm.common.core.Constants;
import com.briomax.briobpm.common.core.DAORet;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.persistence.dao.base.IEjecucionActividadRepository;
import com.briomax.briobpm.persistence.entity.StAccionSeccionNodo;
import com.briomax.briobpm.persistence.entity.StNodoProceso;
import com.briomax.briobpm.persistence.entity.StNodoProcesoPK;
import com.briomax.briobpm.persistence.entity.namedquery.LeeEtiquetasTabla;
import com.briomax.briobpm.persistence.entity.namedquery.LeeMensajesReglas;
import com.briomax.briobpm.persistence.entity.namedquery.LeeReglasActividad;
import com.briomax.briobpm.persistence.repository.IStAccionSeccionNodoRepository;
import com.briomax.briobpm.persistence.repository.IStNodoProcesoRepository;
import com.briomax.briobpm.transferobjects.dynamicForm.Buttons;
import com.briomax.briobpm.transferobjects.dynamicForm.ButtonsRow;
import com.briomax.briobpm.transferobjects.dynamicForm.ColumnGrid;
import com.briomax.briobpm.transferobjects.dynamicForm.ColumnSection;
import com.briomax.briobpm.transferobjects.dynamicForm.Config;
import com.briomax.briobpm.transferobjects.dynamicForm.Control;
import com.briomax.briobpm.transferobjects.dynamicForm.DataGrid;
import com.briomax.briobpm.transferobjects.dynamicForm.Filter;
import com.briomax.briobpm.transferobjects.dynamicForm.Format;
import com.briomax.briobpm.transferobjects.dynamicForm.FormularioDinamico;
import com.briomax.briobpm.transferobjects.dynamicForm.Hehavior;
import com.briomax.briobpm.transferobjects.dynamicForm.Properties;
import com.briomax.briobpm.transferobjects.dynamicForm.Row;
import com.briomax.briobpm.transferobjects.dynamicForm.Section;
import com.briomax.briobpm.transferobjects.dynamicForm.SectionButtons;
import com.briomax.briobpm.transferobjects.dynamicForm.SectionProperties;
import com.briomax.briobpm.transferobjects.dynamicForm.Sequential;
import com.briomax.briobpm.transferobjects.emun.TipoContenidoEnum;
import com.briomax.briobpm.transferobjects.emun.TipoListaEnum;
import com.briomax.briobpm.transferobjects.emun.TipoPresentacionEnum;
import com.briomax.briobpm.transferobjects.in.ActividadTO;
import com.briomax.briobpm.transferobjects.in.CatalogoEtiquetaTO;
import com.briomax.briobpm.transferobjects.in.ColumasGridEjecucionTO;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;
import com.briomax.briobpm.transferobjects.in.EstatusTO;
import com.briomax.briobpm.transferobjects.in.ListaTO;
import com.briomax.briobpm.transferobjects.in.NodoTO;
import com.briomax.briobpm.transferobjects.in.RsOcurrenciaTO;
import com.briomax.briobpm.transferobjects.in.RsOcurrenciaTipoTO;
import com.briomax.briobpm.transferobjects.in.StSeccionNodoTO;
import com.briomax.briobpm.transferobjects.in.TareaNodoTO;

import lombok.extern.slf4j.Slf4j;


/**
 * El objetivo de la Class EjecucionActividadService.java es ...
 * @author RIGOBERTO OLVERA
 * @version 1.0 Fecha de creacion 29/01/2020 12:24:33 PM Modificaciones:
 * @since JDK 1.8
 */
@Service
@Transactional
@Slf4j
public class FormularioDinamicoService implements IFormularioDinamicoService {

	/** El atributo o variable repository. */
	@Autowired
	private IEjecucionActividadRepository repository;
	
	/** El atributo o variable Formulario Dinamico Helper. */
	@Autowired
	private IFormularioPantalla formularioPantalla;

	/** El atributo o variable Nodo Helper. */
	@Autowired
	private INodoHelper nodoHelper;
	
	/** El atributo o variable Accion Seccion Nodo. */
	@Autowired
	private IStAccionSeccionNodoRepository stAccionSeccionNodoRepository;
	
	/** El atributo o variable St Nodo Proceso Repository. */
	@Autowired
	private IStNodoProcesoRepository stNodoProcesoRepository;
	
	
	/** El atributo o variable St Nodo Proceso Repository. */
	@Autowired
	private ITraductorHelper traductorHelper;
	
	@Autowired
	private IMessagesService message;
	
	/** El atributo o variable usarios helper. */
	@Autowired
	private IReglaActividadHelper reglaActividadHelper;
	
	/** 
	 * {@inheritDoc}
	 * @throws ParseException 
	 * @see com.briomax.briobpm.business.service.core.IFormularioDinamicoService#obtenerFormularioDinamico(com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO, com.briomax.briobpm.transferobjects.in.ActividadTO)
	 */
	@Override
	@Transactional(readOnly = true)
	/**
	 * Método para obtener un formulario dinámico con base en la actividad y los datos de autenticación.
	 * Genera las secciones del formulario utilizando la información del nodo y añade botones a cada sección.
	 * Finalmente, construye el objeto `FormularioDinamico` con las secciones generadas y retorna el resultado junto con un mensaje de éxito.
	 *
	 * @param session El objeto que contiene los datos de autenticación de la sesión.
	 * @param actividad El objeto que representa la actividad actual.
	 * @return Un objeto `DAORet` que contiene el formulario dinámico generado y un mensaje de retorno.
	 * @throws BrioBPMException Si ocurre algún problema durante la obtención o construcción del formulario dinámico.
	 */
	public DAORet<FormularioDinamico, RetMsg> obtenerFormularioDinamico(DatosAutenticacionTO session,
			ActividadTO actividad) throws BrioBPMException, ParseException {

		// Mensajes informativos de inicio y detalles de la actividad.
		log.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~obtenerFormularioDinamico~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		log.info("-------------> actividad: " + actividad.toString());
		log.info("-------------> session: " + session.toString());
		
		// Inicializa la lista que contendrá las secciones del formulario.
		List<Section> formSections = new ArrayList<Section>();
		
		// Objeto para almacenar las secciones obtenidas desde la base de datos o servicio.
		DAORet<List<StSeccionNodoTO>, RetMsg> seccionesEntity;

		// Log informativo mostrando el inicio de la generación del formulario dinámico.
		log.info("\t\t ===== # FORMULARIO DINAMICO: {} =====");

		// Muestra los datos clave de la sesión y la actividad.
		log.info("'{}', '{}', '{}', '{}', '{}', '{}', '{}', 'NO' ", session.getCveUsuario(),
				session.getCveEntidad(), session.getCveLocalidad(), session.getCveIdioma(),
				actividad.getCveProceso(), actividad.getVersion(), actividad.getCveNodo());

		// Comentado: código para leer las secciones de nodo directamente desde el repositorio.
//			seccionesEntity = repository.leeSeccionesNodo(session.getCveUsuario(), session.getCveEntidad(),
//				session.getCveLocalidad(), session.getCveIdioma(), actividad.getCveProceso(),
//				new BigDecimal(actividad.getVersion()), actividad.getCveNodo(), actividad.getIdNodo(), "NO");

		// Construye el objeto NodoTO utilizando los valores de la actividad.
		NodoTO nodo = NodoTO.builder()
					.cveProceso(actividad.getCveProceso())
					.cveNodo(actividad.getCveNodo())
					.idNodo(actividad.getIdNodo())
					.version(new BigDecimal(actividad.getVersion()))
					.usoSeccion(actividad.getUsoSeccion())
					.build();
		
		// Log mostrando la información del nodo construido.
		log.info("-------------> nodo: " + nodo.toString());

		// Lee las secciones del nodo utilizando el nodoHelper y los datos de sesión.
		seccionesEntity = nodoHelper.leeSeccionesNodo(session, nodo, Constants.NO, null);
		
		log.info("TERMINA LEE SECCIONES NODO");
		
		// Log de depuración que muestra el número de secciones obtenidas.
		log.debug("\t\t ===== # SECCIONES \t Size[{}] =====", seccionesEntity.getContent().size());

		log.info("-------------> seccionesEntity AQUI: {} {} " + seccionesEntity.getContent().size(), seccionesEntity.getContent().toString());
		
		
		// Log detallado con los datos clave de la sesión y actividad (repetición para asegurar que se loguea en cada paso).
		log.debug("'{}', '{}', '{}', '{}', '{}', '{}', '{}', 'NO' ", session.getCveUsuario(), //DEPURAR
				session.getCveEntidad(), session.getCveLocalidad(), session.getCveIdioma(),
				actividad.getCveProceso(), actividad.getVersion(), actividad.getCveNodo());

		log.info("-------------> seccionesEntity aqui: " + seccionesEntity.getContent().size());
		
		// Itera sobre cada sección obtenida y genera la representación de la sección.
		for (StSeccionNodoTO seccion : seccionesEntity.getContent()) {
			// Log informativo para cada sección generada.
			log.info(">>>>>>>>>>>>>>>>>>>>>>>>>> genera seccion {}", seccion);

			// Llama al método addSection para crear la sección correspondiente.
			Section to = addSection(session, actividad, seccion);
			
			// Añade un botón a la sección generada.
			to.setButton(addButton(session, actividad, seccion));
			log.info(">>>>>>>>>>>>>>>>>>>>>>>>>> svm genera botones seccion {}", seccion);
			
			//Añade registroSelecconables SVM ajuste Registro Selecconables
			to.setRegistroSelecconables(seccion.getRegistroSelecconables());
			
			// Agrega la sección al formulario.
			formSections.add(to);
		}

		//svm se integran botones del proceso
		StNodoProcesoPK idStNodoProceso = StNodoProcesoPK.builder()
				.cveEntidad(session.getCveEntidad())
				.cveProceso(actividad.getCveProceso())
				.version(new BigDecimal(actividad.getVersion()))
				.idNodo(actividad.getIdNodo())
				.cveNodo( actividad.getCveNodo())
				.build();
		// Busco el nodo del proceso por su ID
		Optional<StNodoProceso> stNodoProcesoEntidad = stNodoProcesoRepository.findById(idStNodoProceso);
		String etiquetaGuardar = "";
		String botonGuardar = "NO";
		String etiquetaTerminar = "";
		String botonTerminar = "NO";
		if(stNodoProcesoEntidad.isPresent()) {
			StNodoProceso to = stNodoProcesoEntidad.get();
			if (to.getBotonGuardar() != null && !to.getBotonGuardar().equals("")) {
				etiquetaGuardar = traductorHelper.getTraducce(session, to.getEtiquetaBotonGuardar());
				botonGuardar = to.getBotonGuardar();
			}
			
			if (to.getBotonTerminar() != null && !to.getBotonTerminar().equals("")) {
				etiquetaTerminar = traductorHelper.getTraducce(session, to.getEtiquetaBotonTerminar());
				botonTerminar = to.getBotonTerminar();
			}
		}
		
		log.info("------------> formSections:: " + formSections.toString());		

		// Construye el objeto FormularioDinamico con las secciones generadas.
		FormularioDinamico formularioDinamico = FormularioDinamico.builder()
					.sections(formSections)
					.etiquetaGuardar(etiquetaGuardar)
					.etiquetaTerminar(etiquetaTerminar)
					.habilitarGuardar(botonGuardar)
					.habilitarTerminar(botonTerminar)
					.build();

		// Llama al método builderSpecification para añadir especificaciones adicionales al formulario.
		builderSpecification(session, actividad, formularioDinamico);
		
		// Log de depuración indicando que se ha terminado de obtener el formulario dinámico.
		log.debug("------------> formularioDinamico:: " + formularioDinamico.toString());		
		log.debug("\t\t ===== # FIN obtenerFormularioDinamico \t =====");

		// Retorna el formulario dinámico junto con un mensaje de estado OK.
		return new DAORet<FormularioDinamico, RetMsg>(formularioDinamico,
				RetMsg.builder().status("OK").message("").build());
	}


	/**
	 * Agrega una sección basada en el tipo de presentación de la sección proporcionada.
	 * Dependiendo del tipo de presentación (secuencial o en cuadrícula), construye la sección correspondiente.
	 *
	 * @param session El objeto que contiene los datos de autenticación de la sesión.
	 * @param actividad El objeto que representa la actividad actual.
	 * @param seccion El objeto que contiene la información de la sección que se va a agregar.
	 * @return Un objeto Section que representa la sección construida.
	 * @throws BrioBPMException Si ocurre algún problema durante la creación de la sección.
	 */
	private Section addSection(DatosAutenticacionTO session, ActividadTO actividad, StSeccionNodoTO seccion) throws BrioBPMException, ParseException {
	    
	    // Inicializa el objeto Section en null.
	    Section section = null;
	    
	    // Dependiendo del tipo de presentación de la sección (determinado por un enum), se construye la sección de forma diferente.
	    switch (TipoPresentacionEnum.getTipoPresentacionEnum(seccion.getTipoPresentacion())) {
	        
	        // Si el tipo de presentación es SECUENCIAL, se llama al método builderSectionSequential.
	        case SECUENCIAL:
	            section = builderSectionSequential(session, actividad, seccion);
	            break;
	        
	        // Si el tipo de presentación es GRID (cuadrícula), se llama al método builderSectionGrid.
	        case GRID:
	            section = builderSectionGrid(session, actividad, seccion);
	            break;
	        
	        // En caso de que el tipo de presentación no coincida con ninguno de los casos, no se hace nada.
	        default:
	            break;
	    }
	    
	    // Se escribe un mensaje de depuración indicando que se ha finalizado la adición de la sección.
	    log.info("\t\t ===== # FIN addSection \t =====");

	    // Devuelve el objeto Section que fue construido, o null si no se pudo construir.
	    return section;
	}


	/**
	 * Construye una sección secuencial basada en la información proporcionada.
	 * Este método utiliza varios objetos y métodos auxiliares para construir una
	 * instancia de `Section` que contiene la información de la sección y las columnas.
	 *
	 * @param session    Información de autenticación del usuario.
	 * @param actividad  La actividad en la que se está trabajando.
	 * @param seccion    La sección del nodo en la que se está trabajando.
	 * @return Una instancia de `Section` que representa la sección construida.
	 * @throws BrioBPMException Si ocurre un error durante la construcción de la sección.
	 * @throws ParseException 
	 */
	private Section builderSectionSequential(DatosAutenticacionTO session, ActividadTO actividad,
	        StSeccionNodoTO seccion) throws BrioBPMException, ParseException {
	    
	    // Inicio del método, se registra la entrada.
	    log.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~builderSectionSequential~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	    
	    // Inicializa la ocurrencia de la sección como 1.
	    Integer ocurrencia = 1;
	    
	    // Mapa para almacenar la información de la sección.
	    Map<String, List<List<String>>> infoSeccion = new HashMap<>();
	    
	    // Se obtiene un mapa temporal con información de las columnas de la sección.
	    Map<Integer, Map<String, List<List<String>>>> tmp = getInfoColumnasSeccion(session, actividad, seccion);
	    
	    // Itera sobre la entrada del mapa temporal para obtener la ocurrencia y la información de la sección.
	    for (Entry<Integer, Map<String, List<List<String>>>> item : tmp.entrySet()) {
	        ocurrencia = item.getKey(); // Establece la ocurrencia de la sección.
	        infoSeccion = item.getValue(); // Establece la información de la sección.
	    }
	    
	    // Se obtienen las columnas de la sección como una lista de objetos ColumasGridEjecucionTO.
	    List<ColumasGridEjecucionTO> columsEntity = getColumnsSecction(session, actividad, seccion);
	    
	    // Se crea una lista para almacenar las columnas de la sección.
	    List<ColumnSection> columnsSection = new ArrayList<ColumnSection>();
	    
	    // Itera sobre cada columna de la sección obtenida.
	    for (ColumasGridEjecucionTO columnaSeccion : columsEntity) {
	        
	        // Registra información sobre la columna de la sección actual.
	        log.debug("-------------> COLUMNA SECCION EN builderSectionSequential: ");
	        log.debug("\t{}", columnaSeccion);
	        
	        // Se obtienen las opciones de control para la columna actual.
	        List<List<String>> opcsControl = getOptionsControl(session, actividad, seccion, columnaSeccion, true);
	        log.debug("---->> opcsControl en builderSectionSequential::: " + opcsControl.toString());
	        
	        // Se construye un objeto Properties para la columna con la información relevante.
	        Properties properties = Properties.builder()
	                .cveVariable(columnaSeccion.getCveVariable())
	                .etiqueta(columnaSeccion.getEtiqueta())
	                .width(columnaSeccion.getAnchoColumna())	                
	                .tipoDato(columnaSeccion.getTipoDato())
	                .visible(columnaSeccion.getVisible())
	                .envioGrabar(columnaSeccion.getEnvioGrabar())
	                .numeroColumna(columnaSeccion.getNumeroColumna())
	                .renglon(columnaSeccion.getRenglon())
	                .totalColumnas(columnaSeccion.getNumColumnas())	                
	                .build();
	        
	        // Se construye un objeto Format para la columna, inicializando la longitud y el formato de fecha.
	        Format format = Format.builder()
	                .longitud(columnaSeccion.getLongitud())
	                .formatoFecha(columnaSeccion.getFormatoFecha())
	                .colorDato(null)
	                .build();
	        
	        // Si hay información sobre enteros, se establece en el formato.
	        if (columnaSeccion.getEnteros() != null) {
	            format.setEnteros(columnaSeccion.getEnteros().intValue());
	        }
	        
	        // Si hay información sobre decimales, se establece en el formato.
	        if (columnaSeccion.getDecimales() != null) {
	            format.setDecimales(columnaSeccion.getEnteros().intValue());
	        }
	        
	        // Se construye un objeto Control para la columna con las opciones de control obtenidas.
	        Control control = Control.builder()
	                .tipoControl(columnaSeccion.getTipoControl())
	                .options(opcsControl)
	                .tipoInteraccion(columnaSeccion.getTipoInteraccion())
	                .build();
	        
	        // Se invoca un método auxiliar para construir opciones adicionales basadas en la sección y la columna.
	        builderOptions(seccion, infoSeccion, columnaSeccion, control);
	        
	        // Se construye un objeto ColumnSection con las propiedades, formato y control definidos anteriormente.
	        ColumnSection columnSection = ColumnSection.builder()
	                .secuencia(columnaSeccion.getSecuencia())
	                .properties(properties)
	                .format(format)
	                .control(control)
	                .build();
	        
	        // Se agrega la columna construida a la lista de columnas de la sección.
	        columnsSection.add(columnSection);
	    }
	    
	    // Se construye un objeto Sequential que representa la secuencia de la sección con la ocurrencia y las columnas.
	    Sequential sequential = Sequential.builder()
	            .ocurrencia(ocurrencia)
	            .columns(columnsSection)
	            .build();
	    
	    // Se registra la finalización del método.
	    log.debug("\t\t ===== # FIN builderSectionSequential \t =====");
	    
	    // Se retorna una nueva instancia de Section con las propiedades de la sección y la secuencia construida.
	    return Section.builder()
	            .properties(getSectionProperties(seccion))
	            .orden(seccion.getOrden())
	            .sequential(sequential)
	            .build();
	}


	/**
	 * Construye las opciones del control basadas en el tipo de lista y el valor seleccionado.
	 *
	 * @param seccion El objeto que contiene la clave de la sección.
	 * @param infoSeccion Un mapa que contiene la información de la sección con listas anidadas de Strings.
	 * @param columnaSeccion El objeto que contiene información de la columna, incluyendo la clave de la variable y el tipo de lista.
	 * @param control El objeto Control que se utilizará para gestionar las opciones que se mostrarán.
	 */
	private void builderOptions(StSeccionNodoTO seccion, Map<String, List<List<String>>> infoSeccion,
				ColumasGridEjecucionTO columnaSeccion, Control control) {
	    
	    // Obtiene el tipo de lista según la enumeración TipoListaEnum usando el valor del campo 'tipoLista' de columnaSeccion.
	    TipoListaEnum tipoLista = TipoListaEnum.getTipoListaEnum(columnaSeccion.getTipoLista());
	    
	    // Obtiene la lista de valores seleccionados para la sección y la clave de variable especificada.
	    List<List<String>> valorSeleccionado = getValorSeleccionado(infoSeccion, seccion.getCveSeccion(),
				columnaSeccion.getCveVariable());

	    // Loguea el tipo de lista y los valores seleccionados.
	    log.debug("BuilderOptions {}", tipoLista);
	    log.debug("VALOR SELECCIONADO:: {}", valorSeleccionado);
	    
	    log.debug("---->> control en builderOptions::: " + control.toString());
	    
	    // Dependiendo del tipo de lista, se ejecuta una lógica diferente.
	    switch (tipoLista) {
	        // En los casos CATALOGO_ETIQUETA y TABLA, se busca la opción seleccionada en las opciones del control.
	        case CATALOGO_ETIQUETA:
	        case TABLA:
	            String key = "";

	            //log.info("---->> control en builderOptions::: {} | {}  ", control.getOptions().size()); 
	            
	            // Itera sobre las opciones del control.
	            for (List<String> options: control.getOptions()) {
	                // Obtiene el primer elemento de la opción como clave.
	                key = options.get(0);
	            	//log.info("Key1: " + key);
	                
	                // Itera sobre los valores selheccionados para comparar con la clave.
	                for (List<String> item: valorSeleccionado) {
	                	//log.info("VALOR SELECCIONADO: " + valorSeleccionado);
	                	//log.info("Key2: " + key);
	                	//log.info("Item: " + item.get(0));
	                	
	                	// Si la clave coincide con el primer elemento del valor seleccionado, se marca como seleccionada.
	                    if(key.equals(item.get(0))) {
	                    	//log.info("Key3: " + key);
	                        // Cambia el valor en el tercer elemento de la opción a "SI".
	                        options.set(2, "SI");
	                        break; // Sale del bucle interno una vez que encuentra una coincidencia.
	                    }
	                }
	            }
	            break;
	        
	        // Si no es un tipo de lista CATALOGO_ETIQUETA o TABLA, se eliminan los primeros elementos y se configuran las opciones.
	        default:
	            // Elimina el primer elemento de cada valor seleccionado.
	            for (List<String> item: valorSeleccionado) {
	                item.remove(0);
	            }
	            // Establece el valor seleccionado como las opciones del control.
	            control.setOptions(valorSeleccionado);
	            break;
	    }
	}

	/**
	 * Obtiene el valor de la variable seleccionada a partir del mapa infoSeccion.
	 * Si no se encuentra un valor para la clave dada, se crea y devuelve una lista vacía por defecto.
	 *
	 * @param infoSeccion Un mapa que contiene la información de la sección con listas anidadas de Strings.
	 * @param cveSeccion La clave de la sección utilizada para buscar el valor en el mapa.
	 * @param cveVariable La clave de la variable utilizada para buscar el valor en el mapa.
	 * @return Una lista de listas de Strings que representa el valor seleccionado. Si no se encuentra, devuelve una lista por defecto.
	 */
	private List<List<String>> getValorSeleccionado(Map<String, List<List<String>>> infoSeccion, String cveSeccion,
			String cveVariable) {
		
		
		// quitar esto
	    // Imprimir todo el contenido del map infoSeccion
	    log.info("Contenido de infoSeccion:");

	    if (infoSeccion != null && !infoSeccion.isEmpty()) {
	        for (Map.Entry<String, List<List<String>>> entry : infoSeccion.entrySet()) {
	            String clave = entry.getKey();
	            List<List<String>> valor = entry.getValue();
	            
	            // Imprimir la clave
	            log.debug("Clave: " + clave);

	            // Imprimir el valor (lista de listas)
	            for (List<String> subLista : valor) {
	                log.debug("SubLista: " + subLista);
	                for (String elemento : subLista) {
	                    log.debug("Elemento: '" + elemento + "'");
	                }
	            }
	        }
	    } else {
	        log.info("El Map infoSeccion está vacío o es nulo.");
	    }
	    
	    
		
	    // Inicializa una variable para almacenar el valor seleccionado.
	    List<List<String>> valorSeleccionado;
	    
	    // Busca en el mapa infoSeccion el valor correspondiente a la combinación de cveSeccion y cveVariable.
	    // Se usa String.format para formatear las claves como "cveSeccion|cveVariable".
	    log.debug("---> getValorSeleccionado: ");
	    log.debug("Valor de cveSeccion: " + cveSeccion);
	    log.debug("Valor de cveVariable: " + cveVariable);

	    String claveGenerada = String.format("%S|%S", cveSeccion, cveVariable);
	    log.debug("Clave generada: " + claveGenerada);

	    /*if (!infoSeccion.containsKey(claveGenerada)) {
	        log.info("La clave " + claveGenerada + " no existe en infoSeccion.");
	    }*/

	    
	    valorSeleccionado = infoSeccion.get(claveGenerada);
	    
	    log.info("----->> valorSeleccionado: " + valorSeleccionado);
	    
	    // Si no se encuentra ningún valor (es decir, valorSeleccionado es null), se crea una nueva lista vacía.
	    if (valorSeleccionado == null) {
	        valorSeleccionado = new ArrayList<List<String>>();
	        
	        // Crea una lista de Strings vacía y la añade a valorSeleccionado.
	        List<String> valores = new ArrayList<String>();
	        
	        // Agrega un String vacío a la lista "valores".
	        valores.add("");  // Inicialmente, se puede agregar más valores si es necesario.
	        
	        // Añade la lista de valores vacía a valorSeleccionado.
	        valorSeleccionado.add(valores);
	    }
	    
	    // Devuelve la lista de valores seleccionados (ya sea la que se encontró o la lista vacía por defecto).
	    return valorSeleccionado;
	}
	
	/**
	 * Builder section grid.
	 * @param session el session.
	 * @param actividad el actividad.
	 * @param seccion el seccion.
	 * @return el section.
	 * @throws BrioBPMException la brio BPM exception.
	 * @throws ParseException 
	 */
	// cambiar a private
	public Section builderSectionGrid(DatosAutenticacionTO session, ActividadTO actividad, StSeccionNodoTO seccion)
		throws BrioBPMException, ParseException {
		log.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~builderSectionGrid~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

		log.info("-------------> actividad: " + actividad.toString());
		log.info("-------------> seccion: " + seccion.toString());
		
		// Informacion de la seccion
		DataGrid dataGrid = getInfoDataGrid(session, actividad, seccion);
		log.info("-------- termina getInfoDataGrid, continua en  builderSectionGrid");
		dataGrid.setType("FORM");
		// Columnas
		// Obtiene las columnas de la seccion
		List<ColumasGridEjecucionTO> columsEntity = getColumnsSecction(session, actividad, seccion);
		List<ColumnGrid> columns = new ArrayList<ColumnGrid>();
		log.info("-----tamano de columsEntity {}: {}" + columsEntity.size(), columsEntity.toString());
		for (ColumasGridEjecucionTO columnaSeccion : columsEntity) {
			log.info("\t{} |-| {}",columnaSeccion.getCveVariable() ,columnaSeccion);
			List<List<String>> opcsControl = getOptionsControl(session, actividad, seccion, columnaSeccion, false);
			log.info("---->>{} | opcsControl en builderSectionGrid::: {}", opcsControl.size(), opcsControl.toString());
			
			ColumnGrid column = ColumnGrid.builder()
					.secuencia(columnaSeccion.getSecuencia())
					.properties(Properties.builder()
						.cveVariable(columnaSeccion.getCveVariable())
						.etiqueta(columnaSeccion.getEtiqueta())
						.width(columnaSeccion.getAnchoColumna())
						.tipoDato(columnaSeccion.getTipoDato())
						.visible(columnaSeccion.getVisible())
						.envioGrabar(columnaSeccion.getEnvioGrabar())
						.build())
					.format(Format.builder()
						.longitud(columnaSeccion.getLongitud())
						.formatoFecha(columnaSeccion.getFormatoFecha())
						.colorDato(null)
						.build())
					.control(Control.builder()
						.tipoControl(columnaSeccion.getTipoControl())
						.options(opcsControl)
						.tipoInteraccion(columnaSeccion.getTipoInteraccion())
						.build())
					.filter(Filter.builder().filtro("NO").build())
					.build();
			
			if(columnaSeccion.getEnteros() != null) {
				log.info("---->>{} | Enteros: ", columnaSeccion.getEnteros());
				column.getFormat().setEnteros(columnaSeccion.getEnteros().intValue());
			}
			if(columnaSeccion.getDecimales() != null) {
				log.info("---->>{} | Decimales: ", columnaSeccion.getDecimales());
				column.getFormat().setDecimales(columnaSeccion.getDecimales().intValue());
			}
			columns.add(column);
		}
		
		log.info("-----tamano de columns {} : {}", columns.size(), columns.toString());
		
		dataGrid.setConfig(Config.builder().columns(columns).buttons(
			Buttons.builder()
				.addRow(seccion.getBotonCrearRenglon()).addDescription(seccion.getEtiquetaCrearRenglon())
				.delRow(seccion.getBotonEliminarRenglon()).delDescription(seccion.getEtiquetaEliminarRenglon())
				.build()
				)
			.build());
		if (TipoContenidoEnum.getTipoContenidoEnum(seccion.getContenido()) == TipoContenidoEnum.VARIABLES) {
			cleanInfoDataGrid(dataGrid);
		}
		Section section = Section.builder()
				.properties(getSectionProperties(seccion))
				.orden(seccion.getOrden())
				.grid(dataGrid)
				.build();
		log.debug("\t\t ===== # FIN builderSectionGrid \t =====");
		return section;
	}

	/**
	 * Clean info data grid.
	 * @param dataGrid el data grid.
	 */
	private void cleanInfoDataGrid(DataGrid dataGrid) {
		List<Integer> secuencias = new ArrayList<Integer>();
		List<Boolean> simple = new ArrayList<Boolean>();
		for (ColumnGrid config : dataGrid.getConfig().getColumns()) {
			if (config.getControl() != null && config.getControl().getOptions().isEmpty()) {
				secuencias.add(config.getSecuencia() - 1);
				simple.add(true);
			} else {
				simple.add(false);
			}
		}
		for (Row row : dataGrid.getTail()) {
			for (int index = 0; index < row.getCells().size(); index++) {
				List<List<String>> cell = row.getCells().get(index);
				if (simple.get(index)) {
					for (List<String> value : cell) {
						value.remove(0);
					}
				}
				else {
					for (List<String> value : cell) {
						value.remove(1);
					}
				}
			}
//			for (Integer index : secuencias) {
//				List<List<String>> cell = row.getCells().get(index);
//				for (List<String> value : cell) {
//					value.remove(0);
//				}
//			}
		}
	}

	/**
	 * Obtener el valor de options control.
	 * Este método obtiene una lista de opciones (List<List<String>>) basada en un tipo de lista, que puede
	 * ser de tipo CATALOGO_ETIQUETA o TABLA. Dependiendo de esto, el método consulta y procesa etiquetas
	 * de catálogo o etiquetas de tabla para construir las opciones de control que serán devueltas.
	 * 
	 * @param session la sesión del usuario que contiene los datos de autenticación.
	 * @param actividad los detalles de la actividad que se está ejecutando.
	 * @param seccion los detalles de la sección del nodo que se está procesando.
	 * @param columnaSeccion los detalles de la columna de la sección.
	 * @param secuencial indica si las opciones deben añadirse de manera secuencial o no.
	 * @return una lista de opciones de control (List<List<String>>).
	 * @throws BrioBPMException si ocurre algún error durante la consulta o procesamiento de datos.
	 * @throws ParseException 
	 */
	private List<List<String>> getOptionsControl(DatosAutenticacionTO session, ActividadTO actividad, StSeccionNodoTO seccion,
	        ColumasGridEjecucionTO columnaSeccion, boolean secuencial) throws BrioBPMException, ParseException {
	    //log.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~getOptionsControl~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	    //log.info("------- tipoLista: {}", columnaSeccion.getTipoLista());

	    // Definimos dos variables para almacenar los resultados de las consultas de etiquetas.
	    DAORet<List<CatalogoEtiquetaTO>, RetMsg> etiquetasCatalogoEntity; // Resultado de la consulta para CATALOGO_ETIQUETA.
	    DAORet<List<LeeEtiquetasTabla>, RetMsg> etiquetasTablaEntity;     // Resultado de la consulta para TABLA.
	    
	    // Inicializamos una lista que almacenará las opciones de control, que son listas de Strings.
	    List<List<String>> opcsControl = new ArrayList<List<String>>();
	    
	    // Determinamos el tipo de lista basado en el valor almacenado en columnaSeccion.
	    TipoListaEnum tipoLista = TipoListaEnum.getTipoListaEnum(columnaSeccion.getTipoLista());
	    log.info("------- tipoLista: {}", tipoLista);
	    		
	    // Ejecutamos el código en función del tipo de lista.
	    switch (tipoLista) {
	        case CATALOGO_ETIQUETA:
	            // Construimos un objeto NodoTO con los valores relevantes de la actividad actual.
//					etiquetasCatalogoEntity = repository.leeEtiquetasCatalogo(session.getCveUsuario(),
//						session.getCveEntidad(), session.getCveLocalidad(), session.getCveIdioma(),
//						actividad.getCveProceso(), new BigDecimal(actividad.getVersion()), actividad.getCveInstancia(),
//						actividad.getCveNodo(), actividad.getIdNodo(), actividad.getSecNodo(),
//						columnaSeccion.getId().getCveVariable() == null ? "" : columnaSeccion.getId().getCveVariable());
	            NodoTO nodo = NodoTO.builder()
	                .cveInstancia(actividad.getCveInstancia())   // Clave de la instancia.
	                .cveNodo(actividad.getCveNodo())             // Clave del nodo.
	                .cveProceso(actividad.getCveProceso())       // Clave del proceso.
	                .idNodo(actividad.getIdNodo())               // ID del nodo.
	                .secuenciaNodo(actividad.getSecNodo())       // Secuencia del nodo.
	                .version(new BigDecimal(actividad.getVersion())) // Versión como BigDecimal.
	                .build();

//					etiquetasTablaEntity = repository.leeEtiquetasTabla(session.getCveUsuario(),
//						session.getCveEntidad(), session.getCveLocalidad(), session.getCveIdioma(),
//						actividad.getCveProceso(), new BigDecimal(actividad.getVersion()), actividad.getCveInstancia(),
//						actividad.getCveNodo(), actividad.getIdNodo(), actividad.getSecNodo(),
//						columnaSeccion.getCveVariable() == null ? "" : columnaSeccion.getCveVariable(),
//						columnaSeccion.getValorLista() == null ? "" : columnaSeccion.getValorLista(),
//						columnaSeccion.getDescripcionLista() == null ? "" : columnaSeccion.getDescripcionLista(),
//						columnaSeccion.getTablaLista() == null ? "" : columnaSeccion.getTablaLista(),
//						columnaSeccion.getWhereLista() == null ? "" : columnaSeccion.getWhereLista());
	            
	            // Obtenemos las etiquetas de catálogo basadas en la sesión, nodo y variable de columna.
	            etiquetasCatalogoEntity = formularioPantalla.leeEtiquetasCatalogo(session, nodo,
	                columnaSeccion.getCveVariable() == null ? " " : columnaSeccion.getCveVariable());

	            // Imprimimos la cantidad de etiquetas obtenidas.
	            log.debug("\t\t ===== # ETIQUETAS CATALOGOS: {} =====", etiquetasCatalogoEntity.getContent().size());

	            // Imprimimos los valores clave de la sesión y actividad para debugging.
	            log.info("'{}', '{}', '{}', '{}', '{}', '{}', '{}', '{}', '{}', '{}', '{}' ",
	                session.getCveUsuario(), session.getCveEntidad(), session.getCveLocalidad(),
	                session.getCveIdioma(), actividad.getCveProceso(), actividad.getVersion(),
	                actividad.getCveInstancia(), actividad.getCveNodo(), actividad.getIdNodo(),
	                actividad.getSecNodo(), columnaSeccion.getCveVariable());
	            
	            log.debug("---->> etiquetasCatalogoEntity {} : {} ", etiquetasCatalogoEntity.getContent().size() ,etiquetasCatalogoEntity.getContent().toString());

	            // Iteramos sobre las etiquetas obtenidas y construimos las opciones de control.
	            for (CatalogoEtiquetaTO etiquetaCatalogo : etiquetasCatalogoEntity.getContent()) {
	                //log.info("\t\t\t {}", etiquetaCatalogo);

	                // Creamos una lista para almacenar el valor seleccionado y la etiqueta.
	                List<String> valSel = new ArrayList<String>();
	                //log.debug("---:::> etiquetaCatalogo.getValorAlfanumerico(): " + etiquetaCatalogo.getValorAlfanumerico());
	                valSel.add(etiquetaCatalogo.getValorAlfanumerico());  // Añadimos el valor alfanumérico.
	                valSel.add(etiquetaCatalogo.getEtiqueta());           // Añadimos la etiqueta.
	                
	                //log.info("---->> secuencial: {}", secuencial);
	                // Si secuencial es verdadero, añadimos "NO" como tercer valor.
	                if (secuencial) {
	                    valSel.add("NO");
	                }
	                
	                // Añadimos esta lista de valores a la lista principal de opciones de control.
	                opcsControl.add(valSel);
	            }
	            break;

	        case TABLA:
	            // Construimos un objeto NodoTO similar al caso anterior.
	            NodoTO nodoTabla = NodoTO.builder()
	                .cveInstancia(actividad.getCveInstancia())
	                .cveNodo(actividad.getCveNodo())
	                .cveProceso(actividad.getCveProceso())
	                .idNodo(actividad.getIdNodo())
	                .secuenciaNodo(actividad.getSecNodo())
	                .version(new BigDecimal(actividad.getVersion()))
	                .build();

	            // Creamos un objeto ListaTO con los valores de la columna.
	            ListaTO listaTO = ListaTO.builder()
	                .descripcionLista(columnaSeccion.getDescripcionLista() == null ? "" : columnaSeccion.getDescripcionLista())  // Descripción de la lista.
	                .tablaLista(columnaSeccion.getTablaLista() == null ? "" : columnaSeccion.getTablaLista())  // Nombre de la tabla.
	                .valorLista(columnaSeccion.getValorLista() == null ? "" : columnaSeccion.getValorLista())  // Valor de la lista.
	                .whereLista(columnaSeccion.getWhereLista() == null ? "" : columnaSeccion.getWhereLista())  // Condición WHERE.
	                .build();

	            // Asignamos un valor de clave de dato o una cadena vacía si es nulo.
	            String cveDato = columnaSeccion.getCveVariable() == null ? "" : columnaSeccion.getCveVariable();

	            // Obtenemos las etiquetas de tabla basadas en la sesión, nodo y lista.
	            etiquetasTablaEntity = formularioPantalla.leeEtiquetasTabla(session, nodoTabla, cveDato, listaTO);
	            log.info(">>>>>>>>>>>>>>>>>>>evl \t\t\t etiquetasTablaEntity: {}", etiquetasTablaEntity);
	            // Imprimimos la cantidad de etiquetas obtenidas.

	            // Imprimimos valores clave de la actividad y columna para debugging.
	            log.info(
	                "'getCveProceso:{}', 'getVersion:{}', 'getCveInstancia:{}', 'getCveNodo:{}', 'getIdNodo:{}', 'getSecNodo:{}',"
	                + " 'getCveVariable:{}', 'getValorLista:{}', 'getDescripcionLista:{}', 'getTablaLista:{}', 'getWhereLista:{}' ",
	                actividad.getCveProceso(), actividad.getVersion(),
	                actividad.getCveInstancia(), actividad.getCveNodo(), actividad.getIdNodo(),
	                actividad.getSecNodo(), columnaSeccion.getCveVariable(), columnaSeccion.getValorLista(),
	                columnaSeccion.getDescripcionLista(), columnaSeccion.getTablaLista(),
	                columnaSeccion.getWhereLista());

	            // Iteramos sobre las etiquetas obtenidas de la tabla y construimos las opciones de control.
	            try {
	                log.info("\t\t ===== # ETIQUETAS TABLA: {} =====", etiquetasTablaEntity.getContent().size());	            
		            for (LeeEtiquetasTabla etiquetaTabla : etiquetasTablaEntity.getContent()) {
		                log.debug(">>>>>>>>>>>>>>>>>>>svm \t\t\t etiquetaTabla: {}", etiquetaTabla);
	
		                // Verificamos si la etiqueta no es nula.
		                if (etiquetaTabla != null) {
		                    List<String> valSel = new ArrayList<String>();
		                    log.debug("---> etiquetaTabla.getId().getValorAlfanumerico(): " + etiquetaTabla.getId().getValorAlfanumerico());
		                    valSel.add(etiquetaTabla.getId().getValorAlfanumerico() != " "  ? etiquetaTabla.getId().getValorAlfanumerico() : "");  // Añadimos el valor alfanumérico.
		                    valSel.add(etiquetaTabla.getEtiqueta());                  // Añadimos la etiqueta.
		                    
		                    // Si secuencial es verdadero, añadimos "NO" como tercer valor.
		                    if (secuencial) {
		                        valSel.add("NO");
		                    }
		                    
		                    // Añadimos esta lista de valores a la lista principal de opciones de control.
		                    opcsControl.add(valSel);
		                }
		            }
	            } catch (Exception e) {
	                log.error("Error al obtener el tamaño de etiquetasTablaEntity: {}", e.getMessage());
	            	
	            }
	            break;

	        default:
	            break;
	    }

	    // Devolvemos la lista de opciones de control.
	    log.info(">>>>>>>>>>>>>>>>>>>svm \t\t\t evl opcsControl: {}", opcsControl);
	    return opcsControl;
	}

	
	
	
	/**
	 * Obtener el valor de columns secction.
	 * @param session el session.
	 * @param actividad el actividad.
	 * @param seccion el seccion.
	 * @return el columns secction.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	private List<ColumasGridEjecucionTO> getColumnsSecction(DatosAutenticacionTO session, ActividadTO actividad,
			StSeccionNodoTO seccion) throws BrioBPMException {
//		DAORet<List<LeeColumnasSeccionOcu>, RetMsg> columnasSeccionEntity = repository
//			.leeColumnasSeccionOcu(session.getCveUsuario(), session.getCveEntidad(), session.getCveLocalidad(),
//				session.getCveIdioma(), actividad.getCveRol(), actividad.getCveProceso(),
//				new BigDecimal(actividad.getVersion()), actividad.getCveNodo(), actividad.getIdNodo(),
//				seccion.getCveSeccion());
		
		NodoTO nodo = NodoTO.builder()
				.cveNodo(actividad.getCveNodo())
				.cveProceso(actividad.getCveProceso())
				.idNodo(actividad.getIdNodo())
				.version(new BigDecimal(actividad.getVersion()))
				.build();
		
		DAORet<List<ColumasGridEjecucionTO>, RetMsg> columnasSeccionEntity = formularioPantalla.leeColumnasSeccionOCU(session, nodo, seccion.getCveSeccion());
		log.debug("\t\t ===== # COLUMNAS: {} =====", columnasSeccionEntity.getContent().size());
		log.debug("'{}', '{}', '{}', '{}', '{}', '{}', '{}', '{}', '{}', '{}' ", session.getCveUsuario(),
			session.getCveEntidad(), session.getCveLocalidad(), session.getCveIdioma(), actividad.getCveRol(),
			actividad.getCveProceso(), actividad.getVersion(), actividad.getCveNodo(),
			actividad.getIdNodo(), seccion.getCveSeccion());
		log.debug("\t\t ===== # FIN getColumnsSecction \t =====");
		return columnasSeccionEntity.getContent();
	}

	/**
	 * Obtener el valor de section properties.
	 * @param seccion el seccion.
	 * @return el section properties.
	 */
	private SectionProperties getSectionProperties(StSeccionNodoTO seccion) {
		SectionProperties propSection = SectionProperties.builder()
				.cveSeccion(seccion.getCveSeccion())
				.etiqueta(seccion.getEtiquetaSeccion())
				.contenido(seccion.getContenido())
				.build();
		return propSection;
	}

	/**
	 * Obtener el valor de info data grid.
	 * @param session el session.
	 * @param actividad el actividad.
	 * @param seccion el seccion.
	 * @return el info data grid.
	 */
	private DataGrid getInfoDataGrid(DatosAutenticacionTO session, ActividadTO actividad, StSeccionNodoTO seccion) {
		
		log.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~getInfoDataGrid~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		DataGrid dataGrid = DataGrid.builder().build();
		switch (TipoContenidoEnum.getTipoContenidoEnum(seccion.getContenido())) {
			case VARIABLES:
			case DOCUMENTOS:
				dataGrid = getInfoTypeGrid(session, actividad, seccion);
				break;
			case TAREAS:
				dataGrid = getInfoTypeTask(session, actividad, seccion);
				break;
//			case DOCUMENTOS:
//				dataGrid = getInfoTypeDoc(session, actividad, seccion);
//				break;
			default:
				break;
		}
		return dataGrid;
	}

	/**
	 * Método que obtiene la información de una sección en formato de `DataGrid`, basada en las ocurrencias de la sección.
	 * @param session el session.
	 * @param actividad el actividad.
	 * @param seccion el seccion.
	 * @return el info type grid.
	 */
	private DataGrid getInfoTypeGrid(DatosAutenticacionTO session, ActividadTO actividad, StSeccionNodoTO seccion) {
	    /*log.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~getInfoTypeGrid~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	    log.info("----Session: " + session.toString());
	    log.info("----Actividad: " + actividad.toString());
	    log.info("----Seccion: " + seccion.toString());*/
	    
	    String tipo = " no se lleno";
	    DataGrid dataGrid = null; // Inicialización del objeto DataGrid a retornar
	    List<Row> rows = new ArrayList<Row>(); // Lista para almacenar las filas (rows) del DataGrid
	    
//	    if (seccion.getTipoPresentacion().toUpperCase().equals("GRID")) { 

	        List<List<String>> valorSeleccionado = null;
	        
	        // Obtiene la información de la sección como una lista de entidades RsOcurrenciaTO
	        RsOcurrenciaTipoTO infSeccionEntity = getInfoSeccion(session, actividad, seccion);
	        tipo = infSeccionEntity.getTipo();       
	        log.info("termino de ejecutar getInfoSeccion y regresa a getInfoTypeGrid");
	        log.info("----------> getInfoTypeGrid / infSeccionEntity: " + infSeccionEntity.getRsOcurrencias().size() + " " + infSeccionEntity);
	        
	        Integer numValores = 0; // Variable para almacenar el número de valores que se procesarán

	        // Itera sobre cada ocurrencia de la sección obtenida
	        for (RsOcurrenciaTO infSeccion : infSeccionEntity.getRsOcurrencias()) {
	            log.debug("----->  infSeccion: ");
	            log.debug("\t {}", infSeccion);

	            // Divide los datos de la ocurrencia en partes utilizando el separador "#"
	            String[] datosOcurrencia = infSeccion.getDatosOcurrencia().split("#");

	            // Utiliza Arrays.toString() para imprimir los elementos del array
	            log.debug("--------> datosOcurrencia: " + datosOcurrencia.length + " " + Arrays.toString(datosOcurrencia));
	            
	            List<List<List<String>>> cells = new ArrayList<List<List<String>>>(); // Lista para almacenar las celdas de la fila

	            // Itera sobre cada conjunto de datos de la ocurrencia
	            for (String ocurrencia : datosOcurrencia) {
	                log.debug("----->  ocurrencia: ");
	                log.debug("\t\t {}", ocurrencia);
	                
	                // Divide la ocurrencia en valores separados por "|"
	                String[] valores = ocurrencia.split("\\|");
	                log.debug("----------> valores: " + valores.length);
	                
	                valorSeleccionado = new ArrayList<List<String>>(); // Inicializa la lista de valores seleccionados

	                if(valores.length > 0 ) {
	                    // El segundo valor indica el número de valores que hay que procesar
	                    numValores = Integer.parseInt(valores[1]);
	                    log.debug("----->  num valores: " + numValores);
	                    
	                    int index = 2;
	                    
	                   log.debug("******* TIPO : "  + tipo);
	                   
	                   if("DOCUMENTO".equals(tipo)) {
	                	   getInfTypeGridDocumento(valorSeleccionado, numValores, valores, index);

	                   }else if("VARIABLE".equals(tipo)) {
	                	   
	                	   getInfTypeGridVariable(valorSeleccionado, numValores, valores, index);
	                   }	                   
	                    	
	                    log.debug("-----valorSeleccionado *: " + valorSeleccionado.toString());
	                    
	                }
	                // Agrega la lista de valores seleccionados a la lista de listas de celdas
	                cells.add(valorSeleccionado);
	            }
	            
	            // Crea un objeto Row y lo agrega a la lista de filas
	            //SVM para la imagen
	            rows.add(Row.builder().ocurrencia(infSeccion.getOcurrencia()).cells(cells).imagenes(infSeccion.getImagenes()).tipoDato(infSeccion.getTipoDato()).build());
	        }

	        // Construye el objeto DataGrid con las filas creadas
	        dataGrid = DataGrid.builder()
	                .tail(rows)
	                .build();
//	    }

	    // Retorna el objeto DataGrid
	    return dataGrid;
	}

	private void getInfTypeGridVariable(List<List<String>> valorSeleccionado, Integer numValores, String[] valores,
			int index) {
		
		//log.info("-----------------getInfTypeGridVariable--------------");
		List<String> valSel;
		// Itera sobre la cantidad de valores a procesar
		for (int i = 0; i < numValores; i++) {
		    //log.debug("\t\t\t\t {} {} {}", getValue(valores, index), valores[index+1], valores[index+2]);
		    
			log.debug("-----------Iteracion: " + i);
			
			// Crea una lista para almacenar los valores correspondientes a la columna actual
		    valSel = new ArrayList<String>();
		    
		    // Agrega a la lista los valores relevantes, omitiendo el primer valor (index)
		    //valSel.add(getValue(valores, index)); // Este código está comentado y no se ejecuta
		    
		    valSel.add(valores[index + 1]); // Segundo valor
		    log.debug("-----------valSel.add(valores[index + 1]): " + i);
		    valSel.add(valores[index + 2]); // Tercer valor
		    log.debug("\t\t\t\t {} ", valSel);
		    log.debug("-----------valSel.add(valores[index + 2]) ***: " + i);
		    
		    // Agrega la lista de valores seleccionados a la lista de celdas
		    valorSeleccionado.add(valSel);
		    log.debug("----------- valorSeleccionado.add(valSel): " + i);
		    
		    // Incrementa el índice para pasar al siguiente grupo de valores
		    index = index + 3;
		    log.debug("-----------index: " + i);
		}
	}
	
	
	private void getInfTypeGridDocumento(List<List<String>> valorSeleccionado, Integer numValores, String[] valores,
			int index) {
		List<String> valSel;
		
		log.info("-----------------getInfTypeGridDocumento--------------");
		log.info("---numValores : " + numValores);
		 // Para imprimir todos los valores del array 'valores'
	    log.info("---valores : ");
	    for (String valor : valores) {
	    	if(" ".equals(valor)) {
	    		valor = "";
	    	}
	        log.info("----Valor: " + valor);
	    }
		log.info("---index : " + index);
		
		// Itera sobre la cantidad de valores a procesar
		for (int i = 0; i < numValores; i++) {
		    //log.debug("\t\t\t\t {} {} {}", getValue(valores, index), valores[index+1], valores[index+2]);
		    
		    // Crea una lista para almacenar los valores correspondientes a la columna actual
		    valSel = new ArrayList<String>();
		    
		    // Agrega a la lista los valores relevantes, omitiendo el primer valor (index)
		    //valSel.add(getValue(valores, index)); // Este código está comentado y no se ejecuta
		    valSel.add(valores[index + 1]); // Segundo valor
		    valSel.add(valores[index + 2]); // Tercer valor
		    log.debug("\t\t\t\t {} ", valSel);
		    
		    // Agrega la lista de valores seleccionados a la lista de celdas
		    valorSeleccionado.add(valSel);
		    
		    // Incrementa el índice para pasar al siguiente grupo de valores
		    index = index + 3;
		}
	}

	/**
	 * Método que obtiene la información de las columnas de una sección en particular dentro de un nodo de actividad.
	 * @param session el session.
	 * @param actividad el actividad.
	 * @param seccion el seccion.
	 * @return el info columnas seccion.
	 */
	private Map<Integer, Map<String, List<List<String>>>> getInfoColumnasSeccion(DatosAutenticacionTO session, ActividadTO actividad,
			StSeccionNodoTO seccion) {
   
	log.debug("-------------> getInfoColumnasSeccion: ");
        
    // Inicialización de las estructuras de datos que se van a devolver.
    Map<Integer, Map<String, List<List<String>>>> info = new HashMap<>();
    Map<String, List<List<String>>> infoSeccion = new HashMap<>();
    
    // Verificación del tipo de presentación de la sección. Si es "SECUENCIAL", se procede a obtener la información.
    if (seccion.getTipoPresentacion().toUpperCase().equals(TipoPresentacionEnum.SECUENCIAL.getValue())) {
        // Obtiene la información de la sección como una lista de entidades RsOcurrenciaTO.
    	RsOcurrenciaTipoTO infSeccionEntity = getInfoSeccion(session, actividad, seccion);
        log.debug("----------> getInfoColumnasSeccion / infSeccionEntity: " + infSeccionEntity.getRsOcurrencias().size() + " " + infSeccionEntity);

        // Variables para almacenar temporalmente los valores extraídos.
        String key = "";
        Integer numValores = 0;
        List<List<String>> valorSeleccionado = new ArrayList<>();
        List<String> valSel = new ArrayList<>();
        log.debug("\t SIZE INFO SEQUENCIAL = {}", infSeccionEntity.getRsOcurrencias().size());

        // Itera sobre cada ocurrencia de la sección obtenida.
        for (RsOcurrenciaTO infSeccion : infSeccionEntity.getRsOcurrencias()) {
            log.debug("\t INFO SECCION {}", infSeccion);
            
            // Divide los datos de la ocurrencia en partes utilizando el separador "#".
            String[] datosOcurrencia = infSeccion.getDatosOcurrencia().split("(?<!\\\\)#");
            
            // Itera sobre cada conjunto de datos de la ocurrencia.
            for (String ocurrencia : datosOcurrencia) {
                log.debug("\t\t OCURRENCIA {}", ocurrencia);
                
                // Divide la ocurrencia en valores separados por "|".
                String[] valores = ocurrencia.split("\\|");
                              
                // Inicializa la lista de valores seleccionados.
                valorSeleccionado = new ArrayList<>();
                
                if(valores.length > 0) {
                    // Crea la clave utilizando la clave de la sección y el primer valor de la ocurrencia.
                    key = String.format("%S|%S", seccion.getCveSeccion(), valores[0]);
                    
                    // El segundo valor indica el número de valores que hay que procesar.
                    numValores = Integer.parseInt(valores[1]);
                    log.debug("----> LLAVE Y NUMVALORES ");
                    log.debug("\t\t\t datos de llave y numValores {} {}", key, numValores);
                    
                    int index = 2;
       
                    // Itera sobre la cantidad de valores a procesar.
                    for (int i = 0; i < numValores; i++) {
                    	
                        log.debug("\t\t\t\t {} {} {} valores", getValue(valores, index), getValue(valores, index + 1),
                            getValue(valores, index + 2));
                                 
                        // Crea una lista para almacenar los valores correspondientes a la columna actual.
                        valSel = new ArrayList<>();
                        
                        // Agrega a la lista los valores relevantes, omitiendo el primer valor (index).
                        log.debug("getValue(valores, index + 1): " + getValue(valores, index + 1) + "-");
                        log.debug("getValue(valores, index + 2): " + getValue(valores, index + 2) + "-");
                        
                        String segundoValor = getValue(valores, index + 1);
                        segundoValor = segundoValor.replace("\\#", "#");
	                     // Comprueba si 'segundoValor' es nulo, vacío o contiene solo espacios.
	                     segundoValor = (segundoValor != null && !segundoValor.trim().isEmpty()) ? segundoValor : "";
	                     log.debug("segundoValor: " + segundoValor);
	
	                     String tercerValor = getValue(valores, index + 2);
	                     // Comprueba si 'tercerValor' es nulo, vacío o contiene solo espacios.
	                     tercerValor = (tercerValor != null && !tercerValor.trim().isEmpty()) ? tercerValor : "";
	                     tercerValor = tercerValor.replace("\\#", "#");
	                     log.debug("tercerValor: " + tercerValor);
	
	                     valSel.add(segundoValor);  // Agrega el segundo valor.
	                     valSel.add(tercerValor);   // Agrega el tercer valor.
	                     log.debug("\t\t\t\t {} ", valSel);
                        
                        // Agrega la lista de valores seleccionados a la lista de columnas.
                        valorSeleccionado.add(valSel);
                        
                        // Incrementa el índice para pasar al siguiente grupo de valores.
                        index = index + 3;
                    }
                }
                // Almacena la información de la sección en el mapa utilizando la clave generada.
                log.debug("KEY: " + key);
                log.debug("valorSeleccionado: " + valorSeleccionado);
                infoSeccion.put(key, valorSeleccionado);
                log.debug("infoSeccion: "+ infoSeccion.toString());          }
            // Almacena el mapa de información de la sección en el mapa principal con la ocurrencia como clave.
            info.put(infSeccion.getOcurrencia(), infoSeccion);
            log.debug("info: "+ info.toString());     
        }
    }
    log.debug("\t\t ===== # FIN getInfoColumnasSeccion \t =====");
    
    // Retorna la información procesada.
    return info;
}

	/**
	 * Este método obtiene un valor de un arreglo de cadenas (`valores`) dado un índice específico (`index`).
	 * Si el índice está fuera del rango del arreglo, el método captura la excepción y retorna `null`.
	 * 
	 * @param valores Un arreglo de cadenas del que se obtendrá el valor.
	 * @param index El índice del arreglo desde el cual se desea obtener el valor.
	 * @return El valor correspondiente al índice especificado, o `null` si el índice está fuera del rango del arreglo.
	 */
	private String getValue(String[] valores, int index) {
		log.debug("----> getValue inicio");
		String value = null;
	    try {
	        // Intenta obtener el valor en la posición especificada por 'index'
	        value = valores[index];
	    } 
	    catch (ArrayIndexOutOfBoundsException indexEx) {
	        // Si el índice está fuera del rango del arreglo, captura la excepción y asigna null a 'value'
	        value = null;
	    }
	    	    
	    return value;
	}

	/**
	 * Método que obtiene la información de una sección en particular.
	 * @param session el session.
	 * @param actividad el actividad.
	 * @param seccion el seccion.
	 * @return el info seccion.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	private RsOcurrenciaTipoTO getInfoSeccion(DatosAutenticacionTO session, ActividadTO actividad,
			StSeccionNodoTO seccion) throws BrioBPMException {
		RsOcurrenciaTipoTO result = new RsOcurrenciaTipoTO();
		List<RsOcurrenciaTO> info = new ArrayList<RsOcurrenciaTO>();
		log.debug("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~getInfoSeccion~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		log.info("\t\t ===== # INFORMACION SECCION: {} =====", seccion.getCveSeccion());
		log.info("'{}', '{}', '{}', '{}', '{}', '{}', '{}', '{}', '{}', '{}', '{}' ", session.getCveUsuario(),
			session.getCveEntidad(), session.getCveLocalidad(), session.getCveIdioma(), actividad.getCveRol(),
			actividad.getCveProceso(), actividad.getVersion(), actividad.getCveInstancia(), actividad.getCveNodo(),
			actividad.getIdNodo(), seccion.getCveSeccion());
//		DAORet<List<LeeInfSeccionOcu>, RetMsg> infSeccionEntity = repository.leeInfSeccionOcu(session.getCveUsuario(),
//			session.getCveEntidad(), session.getCveLocalidad(), session.getCveIdioma(), actividad.getCveRol(),
//			actividad.getCveProceso(), new BigDecimal(actividad.getVersion()), actividad.getCveInstancia(),
//			actividad.getCveNodo(), actividad.getIdNodo(),
//			seccion.getCveSeccion() == null ? "" : seccion.getCveSeccion());
		
		// Crea un objeto NodoTO con la información relevante de la actividad
	    NodoTO nodo = NodoTO.builder()
	            .rol(actividad.getCveRol())
	            .cveProceso(actividad.getCveProceso())
	            .version(new BigDecimal(actividad.getVersion()))
	            .cveInstancia(actividad.getCveInstancia())
	            .cveNodo(actividad.getCveNodo())
	            .idNodo(actividad.getIdNodo())
	            .secuenciaNodo(actividad.getSecNodo())
	            .build();
	    
	    // Llama al método 'leeInfSeccionOcu' para obtener la información de la sección desde la base de datos
	    DAORet<List<RsOcurrenciaTO>, RetMsg> infSeccionEntity = formularioPantalla.leeInfSeccionOcu(session, nodo,
	            seccion.getCveSeccion() == null ? "" : seccion.getCveSeccion()); // Se usa una cadena vacía si la clave de la sección es nula
	    
	    String tipo = infSeccionEntity.getMeta().getMessage();
	    log.debug("$$$$ TIPO: " + tipo);
	    log.debug("termina leeInfSeccionOcu y regresa a getInfoSeccion");   
	    // Log de la cantidad de ocurrencias obtenidas y su contenido
	    log.info("---------> infSeccionEntity dentro de getInfoSeccion para la seccion *" + seccion.getCveSeccion() + "* " + infSeccionEntity.getContent().size() + " " + infSeccionEntity.getContent().toString());

	    // Verifica si el estado de la respuesta es "OK" y si es así, asigna el contenido a la lista 'info'
	    if (infSeccionEntity.getMeta().getStatus().toUpperCase().equals("OK")) {
	        info = infSeccionEntity.getContent();
	    }
	    
	    
	    log.info("\t\t ===== # FIN getInfoSeccion \t ====="); // Log que indica el fin del método
	    result.setTipo(tipo);
	    result.setRsOcurrencias(info);
	    
	    return result; // Retorna la lista con la información de la sección
	}

	/**
	 * Obtener el valor de info task.
	 * @param session el session.
	 * @param actividad el actividad.
	 * @param seccion el seccion.
	 * @return el info task.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	private List<TareaNodoTO> getInfoTask(DatosAutenticacionTO session, ActividadTO actividad,
            StSeccionNodoTO seccion) throws BrioBPMException {
		log.info("\t\t ===== # INFORMACION TAREAS: {} =====", "NO");
		log.info("'{}', '{}', '{}', '{}', '{}', '{}', '{}', '{}', '{}', '{}', '{}'",
		session.getCveUsuario(), session.getCveEntidad(), session.getCveLocalidad(), 
		session.getCveIdioma(), actividad.getCveProceso(), actividad.getVersion(),
		actividad.getCveInstancia(), actividad.getCveNodo(), actividad.getIdNodo(), 
		seccion.getCveSeccion(), "NO");
		
		List<TareaNodoTO> tareaNodoFinal = new ArrayList<>();

		
		List<TareaNodoTO> tareaNodo = new ArrayList<>();
		
		NodoTO nodo = NodoTO.builder()
		.cveNodo(actividad.getCveNodo())
		.cveProceso(actividad.getCveProceso())
		.idNodo(actividad.getIdNodo())
		.version(new BigDecimal(actividad.getVersion()))
		.cveInstancia(actividad.getCveInstancia())
		.secuenciaNodo(actividad.getSecNodo())
		.build();
		
		DAORet<List<TareaNodoTO>, EstatusTO> infTaskEntity = nodoHelper.leeTareasNodo(
		session, nodo, seccion.getCveSeccion(), Constants.NO, tareaNodo
		);
		
		tareaNodoFinal = infTaskEntity.getContent();
		
		// Log de la cantidad de tareas obtenidas y su contenido
	    log.info("termina leeTareasNodo y regresa a getInfoTask");	
	    log.info("Tareas obtenidas: " + tareaNodoFinal.size());		
	    // Verifica si el estado de la respuesta es "OK" y si es así, asigna el contenido a la lista 'info'
	    return tareaNodoFinal;
	}


	/**
	 * Obtener el valor de info type task.
	 * @param session el session.
	 * @param actividad el actividad.
	 * @param seccion el seccion.
	 * @return el info type task.
	 */
	private DataGrid getInfoTypeTask(DatosAutenticacionTO session, ActividadTO actividad, StSeccionNodoTO seccion) {
		DataGrid dataGrid = null;
		List<Row> rows = new ArrayList<Row>();
		List<List<List<String>>> cells = null;
		List<TareaNodoTO> infTaskEntity = getInfoTask(session, actividad, seccion);
		log.debug("\t\t ===== # TAREAS: {} =====", infTaskEntity.size());
		for (TareaNodoTO infoTask : infTaskEntity) {
			log.debug("\t INFO TAREA ### {}", infoTask);
			cells = new ArrayList<List<List<String>>>();
			// >>>>>>>>>>
			cells.add(getValorSeleccionado(infoTask.getSecuenciaTarea(), String.valueOf(infoTask.getSecuenciaTarea())
				, false));
			// <<<<<<<<<<
			// >>>>>>>>>>
			cells.add(getValorSeleccionado(infoTask.getSecuenciaTarea(), infoTask.getDescripcionTarea(), false));
			// <<<<<<<<<<
			// >>>>>>>>>>
			cells.add(getValorSeleccionado(infoTask.getSecuenciaTarea(), infoTask.getCopletada(), true));
			// <<<<<<<<<<
			// ##################################
			rows.add(Row.builder().ocurrencia(infoTask.getSecuenciaTarea()).cells(cells).build());
		}
		dataGrid = DataGrid.builder()
				.tail(rows)
				.build();
		return dataGrid;
	}

	/**
	 * Obtener el valor de valor seleccionado documento.
	 * @param secuencia el secuencia.
	 * @param valor el valor.
	 * @param select el select.
	 * @return el valor seleccionado documento.
	 */
	private List<List<String>> getValorSeleccionado(Integer secuencia, String valor, boolean select) {
		List<List<String>> valorSeleccionado = new ArrayList<List<String>>();
		valorSeleccionado = new ArrayList<List<String>>();
		List<String> valSel = null;
		valSel = new ArrayList<String>();
		//valSel.add(String.valueOf(secuencia));

		//valSel.add(valor);
		if (!select) {
			valSel.add(valor);
		}
		else {
			if (StringUtils.isNotBlank(valor)) {
				if (valor.equalsIgnoreCase("SI")) {
					valSel.add(valor);
				}
			}
		}

		//valSel.add(valor);
		log.debug("\t\t\t\t {} ", valSel);
		valorSeleccionado.add(valSel);
		return valorSeleccionado;
	}

	/**
	 * Builder specification.
	 * @param session el session.
	 * @param actividad el actividad.
	 * @param formularioDinamico el formulario dinamico.
	 */
	private void builderSpecification(DatosAutenticacionTO session,
		ActividadTO actividad, FormularioDinamico formularioDinamico) {
		List<String> messages = new ArrayList<String>();
		log.info("-----builderSpecification----");
		/*DAORet<List<LeeMensajesReglas>, RetMsg> mensajesReglas =
			repository.leeMensajesReglas(session.getCveUsuario(), session.getCveEntidad(), session.getCveLocalidad(),
				session.getCveIdioma(), actividad.getCveRol(), actividad.getCveProceso(),
				new BigDecimal(actividad.getVersion()), actividad.getCveNodo(), actividad.getIdNodo());*/
		DAORet<List<LeeMensajesReglas>, RetMsg>
		mensajesReglas = message.leeMensajesReglas(session, actividad);
		log.info("############ mensajesReglas resultado:  {} ", mensajesReglas.getMeta().toString());
		if ("OK".equalsIgnoreCase(mensajesReglas.getMeta().getStatus())) {
			mensajesReglas.getContent().forEach(row -> messages.add(row.getMensaje()));
		}
		try {
			formularioDinamico.setBehavior(Hehavior.builder()
				.specification(getReglas(session, actividad))
				.messages(messages)
				.build()
			);
		} catch (BrioBPMException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Obtener el valor de reglas.
	 * @param session el session.
	 * @param actividad el actividad.
	 * @return el reglas.
	 * @throws ParseException 
	 * @throws BrioBPMException 
	 */
	//TODO DESACOPLAR
	private String getReglas(DatosAutenticacionTO session, ActividadTO actividad) throws BrioBPMException, ParseException {
		
		log.debug(">>>>>>>>>>>>> getReglas");
		log.info("-----getReglas---- {}", actividad.toString());
		StringBuilder rules = new StringBuilder("");
		/*DAORet<List<LeeReglasActividad>, RetMsg> mensajesReglas =
			repository.leeReglasActividad(session.getCveUsuario(), session.getCveEntidad(), session.getCveLocalidad(),
				session.getCveIdioma(), actividad.getCveRol(), actividad.getCveProceso(),
				new BigDecimal(actividad.getVersion()), actividad.getCveInstancia(), actividad.getCveNodo(),
				actividad.getIdNodo(), actividad.getSecNodo());*/
		NodoTO nodo = NodoTO.builder()
				 .cveNodo(actividad.getCveNodo())
				 .cveInstancia(actividad.getCveInstancia())
				 .cveProceso(actividad.getCveProceso())
				 .version(new BigDecimal( actividad.getVersion()))
				 .idNodo(actividad.getIdNodo())
				 .secuenciaNodo(actividad.getSecNodo())
				 .ocurrencia(1)
				 .build();
		DAORet<List<LeeReglasActividad>, RetMsg> mensajesReglas = reglaActividadHelper.leeReglasActividad(session, nodo);
		log.info("mensaje: " + mensajesReglas.getContent().size());
		for(LeeReglasActividad reglas:mensajesReglas.getContent()) {
			log.info("polaca: " + reglas.getNotacionPolaca());
			
		if ("OK".equalsIgnoreCase(mensajesReglas.getMeta().getStatus())) {
			mensajesReglas.getContent().forEach(row -> {
				rules.append("{");
				rules.append(row.getNotacionPolaca());
				rules.append("} ");
			});
		}
		}
		return rules.toString();
	}
	
	private SectionButtons addButton(DatosAutenticacionTO session, ActividadTO actividad, StSeccionNodoTO seccion) throws BrioBPMException {
		SectionButtons button = null;
		
		log.debug(">>>>>>>>>>>>> inicia lectura de botones");

		List<StAccionSeccionNodo> list = stAccionSeccionNodoRepository.obtieneBotones(session.getCveEntidad(), actividad.getCveProceso(), new BigDecimal(actividad.getVersion()),
				actividad.getCveNodo(), actividad.getIdNodo(), seccion.getCveSeccion());
		List<ButtonsRow> tail = new ArrayList<ButtonsRow>();
		
		if (list.size() > 0) {
						
			for (StAccionSeccionNodo ite : list) {
				ButtonsRow btn = ButtonsRow.builder()
						.orden(ite.getOrdenAccion())
						.etiqueta(ite.getEtiquetaAccion())
						.urlAccion(ite.getUrlAccion())
						.funcionAccion(ite.getFuncionAccion())
						.atributos(ite.getAtributos())
						.variables(ite.getVariables())
						.build();
				tail.add(btn);
			}
			 			
			button = SectionButtons.builder()
					.numButtons(list.size())
					.tail(tail)
					.build();
			
		}
		
		log.debug(">>>>>>>>>>>>> Fin lectura de botones: "+  tail.size());

		return button;
	}


	@Override
	public DAORet<FormularioDinamico, RetMsg> obtenerFormularioDinamicoMovil(DatosAutenticacionTO session,
			NodoTO nodo) throws BrioBPMException, ParseException {
		
		ActividadTO actividad = new ActividadTO();
		actividad.setCveInstancia(nodo.getCveInstancia());
		actividad.setCveNodo(nodo.getCveNodo());
		actividad.setCveProceso(nodo.getCveProceso());
		actividad.setVersion(nodo.getVersion().toString());
		actividad.setSecNodo(nodo.getSecuenciaNodo());
		actividad.setIdNodo(nodo.getIdNodo());
		actividad.setCveAreaTrabajo(null);
		actividad.setUsoSeccion(nodo.getUsoSeccion());	
		log.info("ACTIVIDAD: " + actividad.toString());
		
		DAORet<FormularioDinamico, RetMsg> metaData = obtenerFormularioDinamico(session, actividad);
		
		log.info("META DATA: " + metaData.getContent().toString());
		return metaData;
	}


}
