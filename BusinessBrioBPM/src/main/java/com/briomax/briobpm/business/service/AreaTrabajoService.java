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
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.briomax.briobpm.business.convertes.IAreaTrabajoConverter;
import com.briomax.briobpm.business.helpers.base.IAreaTrabajoHelper;
import com.briomax.briobpm.business.helpers.base.IEntidadHelper;
import com.briomax.briobpm.business.helpers.base.INodoHelper;
import com.briomax.briobpm.business.helpers.base.IProcesoHelper;
import com.briomax.briobpm.business.service.core.IAreaTrabajoService;
import com.briomax.briobpm.business.service.core.IEjecucionActividadService;
import com.briomax.briobpm.business.service.core.IEmailsService;
import com.briomax.briobpm.common.core.Constants;
import com.briomax.briobpm.common.core.DAORet;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.persistence.dao.base.IAreaTrabajoDAO;
import com.briomax.briobpm.persistence.entity.AreaTrabajo;
import com.briomax.briobpm.persistence.entity.DatoAreaTrabajo;
import com.briomax.briobpm.persistence.entity.IconoEstadoAT;
import com.briomax.briobpm.persistence.entity.InNodoProceso;
import com.briomax.briobpm.persistence.entity.StNodoProceso;
import com.briomax.briobpm.persistence.entity.StNodoProcesoPK;
import com.briomax.briobpm.persistence.entity.StNodoSiguiente;
import com.briomax.briobpm.persistence.entity.TarjetaAreaTrabajo;
import com.briomax.briobpm.persistence.entity.namedquery.LeeMenuEstatico;
import com.briomax.briobpm.persistence.entity.namedquery.MenuAreaTrabajo;
import com.briomax.briobpm.persistence.repository.IAreaTrabajoRepository;
import com.briomax.briobpm.persistence.repository.IDatoAreaTrabajoRepository;
import com.briomax.briobpm.persistence.repository.IDatoProcesoNodoRepository;
import com.briomax.briobpm.persistence.repository.IIconoEstadoATRepository;
import com.briomax.briobpm.persistence.repository.IInNodoProcesoRepository;
import com.briomax.briobpm.persistence.repository.IStNodoProcesoRepository;
import com.briomax.briobpm.persistence.repository.IStNodoSiguienteRepository;
import com.briomax.briobpm.persistence.repository.IUsuarioRolRepository;
import com.briomax.briobpm.persistence.repository.TarjetaAreaTrabajoTRepository;
import com.briomax.briobpm.transferobjects.BitacoraTO;
import com.briomax.briobpm.transferobjects.InformacionAreaTrabajoTO;
import com.briomax.briobpm.transferobjects.MenuAreaTrabajoTO;
import com.briomax.briobpm.transferobjects.SaveSectionTO;
import com.briomax.briobpm.transferobjects.SectionVariablesTO;
import com.briomax.briobpm.transferobjects.catalogs.BitacoraNodo;
import com.briomax.briobpm.transferobjects.dynamicForm.Action;
import com.briomax.briobpm.transferobjects.dynamicForm.ActionCveInstancia;
import com.briomax.briobpm.transferobjects.dynamicForm.Buttons;
import com.briomax.briobpm.transferobjects.dynamicForm.ColumnGrid;
import com.briomax.briobpm.transferobjects.dynamicForm.Config;
import com.briomax.briobpm.transferobjects.dynamicForm.Control;
import com.briomax.briobpm.transferobjects.dynamicForm.DataGrid;
import com.briomax.briobpm.transferobjects.dynamicForm.DatoAreaTrabajoT;
import com.briomax.briobpm.transferobjects.dynamicForm.Filter;
import com.briomax.briobpm.transferobjects.dynamicForm.Format;
import com.briomax.briobpm.transferobjects.dynamicForm.Grafico;
import com.briomax.briobpm.transferobjects.dynamicForm.IconoAtTO;
import com.briomax.briobpm.transferobjects.dynamicForm.Properties;
import com.briomax.briobpm.transferobjects.dynamicForm.Row;
import com.briomax.briobpm.transferobjects.dynamicForm.StNodoProcesoT;
import com.briomax.briobpm.transferobjects.dynamicForm.Style;
import com.briomax.briobpm.transferobjects.dynamicForm.TarjetaDato;
import com.briomax.briobpm.transferobjects.in.ActividadTO;
import com.briomax.briobpm.transferobjects.in.ColumnasAreaTrabajoTO;
import com.briomax.briobpm.transferobjects.in.DatosAreaTrabajoTO;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;
import com.briomax.briobpm.transferobjects.in.EstatusAreaTrabajoTO;
import com.briomax.briobpm.transferobjects.in.EstatusAtributosDatoTO;
import com.briomax.briobpm.transferobjects.in.EstatusCondicionTO;
import com.briomax.briobpm.transferobjects.in.EstatusCreaInstanciaTO;
import com.briomax.briobpm.transferobjects.in.NodoTO;
import com.briomax.briobpm.transferobjects.in.ProcesoInicialTO;
import com.briomax.briobpm.transferobjects.in.ProcesoSeguroTO;
import com.briomax.briobpm.transferobjects.in.ProcesoTO;

import lombok.extern.slf4j.Slf4j;


/**
 * El objetivo de la Class AreaTrabajoService.java es ...
 * @author RIGOBERTO OLVERA
 * @version 1.0 Fecha de creacion 29/01/2020 12:32:32 PM Modificaciones:
 * @since JDK 1.8
 */
@Service
@Transactional
@Slf4j
public class AreaTrabajoService implements IAreaTrabajoService {

	private static final String SALIDA = "SALIDA";

	/** El atributo o variable area trabajo DAO. */
	@Autowired
	private IAreaTrabajoDAO areaTrabajoDAO;

	/** El atributo o variable email service. */
	@Autowired
	private IEmailsService emailService;

	/** El atributo o variable Proceso Helper service. */
	@Autowired
	private IProcesoHelper procesoHelper;
	
	/** El atributo o variable Entidad Helper service. */
	@Autowired
	private IEntidadHelper entidadHelper;
	
	/** El atributo o variable Entidad Helper service. */
	@Autowired
	private INodoHelper nodoHelper;
	
	/** El atributo o variable Area Trabajo Helper. */
	@Autowired 
	private IAreaTrabajoHelper areaTrabajoHelper;
	
	/** El atributo o variable Area Trabajo Repository. */
	@Autowired 
	private  IAreaTrabajoRepository areaTrabajoRepository;
	
	/** El atributo o variable Tarjeta Area Trabajo Repository. */
	@Autowired 
	private  TarjetaAreaTrabajoTRepository tarjetaAreaTrabajoTRepository;
	
	/** El atributo o variable Tarjeta Area Trabajo Repository. */
	@Autowired 
	private  IInNodoProcesoRepository inNodoProcesoRepository;
	
	/** El atributo o variable St Nodo Proceso Repository. */
	@Autowired 
	private IStNodoProcesoRepository stNodoProcesoRepository;
	
	/** El atributo o variable Dato Area Trabajo Repository. */
	@Autowired 
	private IDatoAreaTrabajoRepository datoAreaTrabajoRepository;
	
	/** El atributo o variable Dato Proceso Repository. */
	@Autowired
	private IDatoProcesoNodoRepository iDatoProcesoNodoRepository;
	
	/** El atributo o variable Usuario Rol Repository. */
	@Autowired
	private IUsuarioRolRepository usuarioRolRepository;
	
	/** El atributo o variable Icono Estado AT Repository. */
	@Autowired
	IIconoEstadoATRepository iconoEstadoATRepository;
	
	/** El atributo o variable Icono Estado AT Repository. */
	@Autowired 
	IStNodoSiguienteRepository stNodoSiguienteRepository;
	
	/** El atributo o variable ejecucion actividad service. */
	@Autowired
	private IEjecucionActividadService ejecucionActividadService;
	
	/**
	 * Crear una nueva instancia del objeto area trabajo service.
	 */
	public AreaTrabajoService() {
		log.debug("Constructor Inicializando AreaTrabajoHelper ");
	}

	/** 
	 * {@inheritDoc}
	 * @see com.briomax.briobpm.business.service.core.IAreaTrabajoService#obtenerMenuAreaTrabajo(com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO)
	 */
	@Override
	public DAORet<List<MenuAreaTrabajoTO>, RetMsg> obtenerMenuAreaTrabajo(DatosAutenticacionTO datosAutenticacion) throws BrioBPMException {
		List<MenuAreaTrabajoTO> lista = new LinkedList<MenuAreaTrabajoTO>();
		log.debug("\t----- Obtener Menu Dinamico para {}.", datosAutenticacion.getCveUsuario());
		DAORet<List<MenuAreaTrabajo>, RetMsg> menuDinamico = areaTrabajoDAO.leeMenuAreaTrabajo(
			datosAutenticacion.getCveUsuario(), datosAutenticacion.getCveEntidad(),
			datosAutenticacion.getCveLocalidad(), datosAutenticacion.getCveIdioma());
		if (menuDinamico.getMeta().getStatus().equalsIgnoreCase(Constants.OK)) {
			log.debug("Converte Entity (MenuAreaTrabajo) -> Dto (MenuAreaTrabajoTO) ");
			lista.addAll(menuDinamico.getContent().stream().map(IAreaTrabajoConverter.converterDynamicMenu)
				.collect(Collectors.toList()));
		}
		
		DAORet<List<LeeMenuEstatico>, RetMsg> menuEstatico = areaTrabajoDAO.leeMenuEstatico(
			datosAutenticacion.getCveUsuario(), datosAutenticacion.getCveEntidad(),
			datosAutenticacion.getCveLocalidad(), datosAutenticacion.getCveIdioma());
		log.debug("Obtener Menu Estatico para {} ", datosAutenticacion.getCveUsuario());
		if (menuEstatico.getMeta().getStatus().equalsIgnoreCase(Constants.OK)) {
			log.debug("Converte Entity (LeeMenuEstatico) -> Dto (MenuAreaTrabajoTO) ");
			lista.addAll(menuEstatico.getContent().stream().map(IAreaTrabajoConverter.converterFixedMenu)
				.collect(Collectors.toList()));
		}

		assemblerMenu(lista);
		log.trace("\t      {}", lista);
		log.debug("\t----- {}", menuDinamico.getMeta());
		return new DAORet<List<MenuAreaTrabajoTO>, RetMsg>(lista, menuDinamico.getMeta());
	}

	/** 
	 * {@inheritDoc}
	 * @see com.briomax.briobpm.business.service.core.IAreaTrabajoService#obtenerMenuDashboard(com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO)
	 */
	@Override
	public DAORet<List<MenuAreaTrabajoTO>, RetMsg> obtenerMenuDashboard(DatosAutenticacionTO datosAutenticacion)
		throws BrioBPMException {
		List<MenuAreaTrabajoTO> lista = new LinkedList<MenuAreaTrabajoTO>();
		log.debug("Obtener Menu Dashboard para {} ", datosAutenticacion.getCveUsuario());
		DAORet<List<LeeMenuEstatico>, RetMsg> menuDashboard = areaTrabajoDAO.leeMenuDashboard(
			datosAutenticacion.getCveUsuario(), datosAutenticacion.getCveEntidad(),
			datosAutenticacion.getCveLocalidad(), datosAutenticacion.getCveIdioma());
		if (menuDashboard.getMeta().getStatus().equalsIgnoreCase(Constants.OK)) {
			log.debug("Converte Entity (LeeMenuEstatico) -> Dto (MenuAreaTrabajoTO). ");
			lista.addAll(menuDashboard.getContent().stream().map(IAreaTrabajoConverter.converterFixedMenu)
				.collect(Collectors.toList()));
		}

		assemblerMenu(lista);
		log.trace("\t      {}", lista);
		log.debug("\t----- {}", menuDashboard.getMeta());
		return new DAORet<List<MenuAreaTrabajoTO>, RetMsg>(lista, menuDashboard.getMeta());
	}

	/**
	 * Assembler menu.
	 * @param lista el lista.
	 */
	private void assemblerMenu(List<MenuAreaTrabajoTO> lista) {
		if (!lista.isEmpty()) {
			int ordenamiento = 1;
			for (MenuAreaTrabajoTO item : lista) {
				item.setOrdenamiento(ordenamiento);
				if(item.getTipo().equalsIgnoreCase("ESTATICO")) {
					item.setParametros(new JSONObject().toString());
				}
				ordenamiento++;
			}
		}
	}

	/** 
	 * {@inheritDoc}
	 * @see com.briomax.briobpm.business.service.core.IAreaTrabajoService#getAreaTrabajo(com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO, com.briomax.briobpm.transferobjects.in.ProcesoTO)
	 */
	@Override
	public DAORet<DataGrid, RetMsg> getAreaTrabajo(DatosAutenticacionTO session, ProcesoTO datosProceso)
			throws BrioBPMException {
		
		//log.info("\t-----AREA DE TRABAJO SERVICE ------- {}{}");
		
		// Inicializo las variables para el proceso
		String cveAreaTrabajoTarjeta;
		String cveDato;
		String cveEntidad = session.getCveEntidad();
		String origenDato;
		DataGrid dataGrid = DataGrid.builder().type("GRID").build();
		BigDecimal version = new BigDecimal(datosProceso.getVersion());
		String cveProceso = datosProceso.getCveProceso();
		String cveNodo = datosProceso.getCveNodo();
		Integer idNodo = datosProceso.getIdNodo();
		
	    // Inicializo el mensaje de retorno con estado de error por defecto
		RetMsg msg = RetMsg.builder().status("ERROR").message("").build();
		
	    // Construyo el objeto nodo con los datos del proceso
		NodoTO nodo = NodoTO.builder()
				.cveProceso(datosProceso.getCveProceso())
				.version(new BigDecimal(datosProceso.getVersion()))
				.cveNodo(datosProceso.getCveNodo())
				.idNodo(datosProceso.getIdNodo())
				.build();
		
		//log.info("\t-----AREA DE TRABAJO SERVICE {}{}", session, datosProceso);
		//log.debug("\t\t====ICONO_ESTADO_AT =======");
				

	    // Proceso para leer las columnas del área de trabajo
		DAORet<List<ColumnasAreaTrabajoTO>, RetMsg>  resColumns = areaTrabajoHelper.leeColumnaAreaTrabajo(session, nodo, datosProceso.getCveAreaTrabajo());
				
		//log.info("\t\tRESPONSE DATA COLUMMS: {}", resColumns);
		
	    // Verifico si el estado del resultado es OK
		if (resColumns.getMeta().getStatus().equalsIgnoreCase(Constants.OK)) {
			List<ColumnGrid> columnsGrid = new ArrayList<ColumnGrid>();
			// Columnas dinamicas
			int sequence = 0;
			//log.info("\tCOLUMNAS DINAMICAS");
			//log.debug("\t# COLUMNS: {}", resColumns.getContent().size());
			
			// Itero por cada columna dinámica obtenida
			for (ColumnasAreaTrabajoTO entityColumn : resColumns.getContent()) {
				columnsGrid.add(builderColumn(datosProceso, entityColumn));
				sequence = entityColumn.getSecuencia();
			}
			
			// Columnas fijas
			//log.debug("\tCOLUMNS FIJAS");
	        columnsGrid.addAll(getColumsFix(sequence)); // Agrego columnas fijas
			
	        // Actualizo el mensaje a estado OK
			msg.setStatus(Constants.OK);
			msg.setMessage(resColumns.getMeta().getMessage());
			
	        // Configuración de las columnas y botones del grid
			dataGrid.setConfig(Config.builder()
				.columns(columnsGrid)
				.buttons(Buttons.builder().addRow("NO").delRow("NO").build())
				.build());
			
	        // Informacion del área de trabajo
			//log.info("\t\t====================INFORMACION DEL AREA DE TRABAJO====================");
			StNodoProcesoPK idStNodoProceso = StNodoProcesoPK.builder()
					.cveEntidad(cveEntidad)
					.cveProceso(cveProceso)
					.version(version)
					.idNodo(idNodo)
					.cveNodo(cveNodo)
					.build();
			// Busco el nodo del proceso por su ID
			Optional<StNodoProceso> stNodoProcesoEntidad = stNodoProcesoRepository.findById(idStNodoProceso);
			if(stNodoProcesoEntidad.isPresent()) {
				
				// Si se encuentra el nodo del proceso, obtengo la clave del área de trabajo tarjeta
				cveAreaTrabajoTarjeta = stNodoProcesoEntidad.get().getCveAreaTrabajoTarjeta();
			} else {
				cveAreaTrabajoTarjeta = "NA";
			}
			
			DAORet<EstatusAreaTrabajoTO, RetMsg> resInfo = areaTrabajoHelper.leeInfAreaTrabajo(session, nodo, datosProceso.getCveAreaTrabajo(), cveAreaTrabajoTarjeta);
			/*DAORet<List<InformacionAreaTrabajo>, RetMsg> resInfo = 	areaTrabajoDAO.leeInformacionAreaTrabajo(session.getCveUsuario(), session.getCveEntidad(),
				session.getCveLocalidad(), session.getCveIdioma(), datosProceso.getCveRol(),
				datosProceso.getCveProceso(), new BigDecimal(datosProceso.getVersion()), datosProceso.getCveNodo(),
				datosProceso.getIdNodo(), datosProceso.getCveAreaTrabajo());*/
			
			//log.info("----- termina leeInfAreaTrabajo y regresa a getAreaTrabajo");
			//log.info("------ HASH MAP LLAMADO EN GET AREA TRABAJO: " + resInfo.getContent().getMapDatos().size() + " " + resInfo.getContent().getMapDatos().toString());
			
			//log.info("\t\tRESPONSE DATA INFO: {}", resInfo);
					
			// Creo map con cveInstancia de llave y contenido la lista de acciones
			Map<String, List<Action>>  menuRenglon = new HashMap<>();

	        // Si la información del área de trabajo es exitosa
			if (resInfo.getMeta().getStatus().equalsIgnoreCase(Constants.OK)) {
				List<Row> rows = new ArrayList<Row>();
				
				//log.info("\t\t -----> INFORMACION {} ", resInfo.getContent().getInfAreaTrabajo().size());

	            // Itero por cada fila de información del área de trabajo
				for (InformacionAreaTrabajoTO entityInfo : resInfo.getContent().getInfAreaTrabajo()) {
					//log.debug("USUARIO BLOQUEA***: " + entityInfo.getUsuarioBloquea());					
	                // Genero una fila con la información del área de trabajo
					Row row = getRow(entityInfo, session.getCveUsuario());
					rows.add(row);
				
	                // Agrego acciones al menú por cada instancia
					menuRenglon.put(entityInfo.getCveInstancia(), row.getAcciones());					
				}
				
				//log.info("\t\tROWS: {}", rows.size());
				
	            // Agrego los datos al grid
				dataGrid.setTail(rows);
				msg.setStatus(Constants.OK);
				msg.setMessage(resInfo.getMeta().getMessage());
			}
			else {
				msg.setStatus(resInfo.getMeta().getStatus());
				msg.setMessage(resInfo.getMeta().getMessage());
				log.info("\tINFORMACION", msg);
			}
			
			
			
			
			// TARJETAS
			// DEFINO SI ES TIPO GRID O TIPO TARJETA
			
			Optional<AreaTrabajo> areaTrabajo = areaTrabajoRepository.obtieneAreasTrabajo(cveEntidad, cveProceso, version, datosProceso.getCveAreaTrabajo());
			if(areaTrabajo.isPresent()) {
				String tipoArea = areaTrabajo.get().getTipoArea();
				dataGrid.setGrafico(Grafico.builder()
							.tipoArea(tipoArea)
							.build());
			}
			
			// Inicializo la lista de nodos del proceso
			List<StNodoProcesoT> stNodosProcesoTO = new ArrayList<>();
//			StNodoProcesoPK idStNodoProceso = StNodoProcesoPK.builder()
//					.cveEntidad(cveEntidad)
//					.cveProceso(cveProceso)
//					.version(version)
//					.idNodo(idNodo)
//					.cveNodo(cveNodo)
//					.build();
//			// Busco el nodo del proceso por su ID
//			/Optional<StNodoProceso> stNodoProcesoEn = stNodoProcesoRepository.findById(idStNodoProceso);
			if(stNodoProcesoEntidad.isPresent()) {
				
				// Si se encuentra el nodo del proceso, obtengo la clave del área de trabajo tarjeta
				cveAreaTrabajoTarjeta = stNodoProcesoEntidad.get().getCveAreaTrabajoTarjeta();
				
				if(cveAreaTrabajoTarjeta != null && !cveAreaTrabajoTarjeta.isEmpty()){
						
				
					// Llamo todas las tarjetas del área de trabajo
					//tarjetas = tarjetaAreaTrabajoTRepository.obtieneTarjetasAreaTrabajo(cveEntidad, "REBATES AND REWARDS EJECUCIÓN", version, "AREA_TRABAJO_TAJETA R&R");
					List<TarjetaAreaTrabajo> tarjetas = tarjetaAreaTrabajoTRepository.obtieneTarjetasAreaTrabajo(cveEntidad, cveProceso, version, cveAreaTrabajoTarjeta);
					log.info("tarjetas: " + tarjetas.size());
					
					// Inicializo el objeto para condiciones evaluadas
					EstatusCondicionTO condicionEvaluada = new EstatusCondicionTO();			
					log.info("infoAreaTrabajoTO: " + resInfo.getContent().getInfAreaTrabajo().size());
			
					// Obtengo las instancias del nodo y sus datos relacionados
					List<Object[]> renglonesTarjeta = stNodoProcesoRepository.obtieneDatosTarjetaCompuesta(cveEntidad, cveProceso, version, cveNodo, idNodo, cveAreaTrabajoTarjeta); //estas lineas las comente por hacer un MOCK
					log.info("renglonesTarjeta: " + renglonesTarjeta.size() + renglonesTarjeta.toString()); // Depuración
	
					//List<Object[]> datosTarjetaDesdeNodo = stNodoProcesoRepository.obtieneDatosTarjetaCompuesta(cveEntidad, "ATEN_INCI_TICK", version, cveNodo, 1, "AREA_TRABAJO_TARJETA ATEN_INCI_TICK");
	
					// Mapeo de datos por tarjeta y listas auxiliares
					Map<Integer, List<DatoAreaTrabajoT>> mapListaDatosPorTarjeta =  new HashMap<Integer, List<DatoAreaTrabajoT>>();
					List<DatoAreaTrabajoT> datoPorTarjeta = new ArrayList<DatoAreaTrabajoT>();
					String cveOrigen = "";
					int secuenciaTarjeta = 0;
					
					String valorPantalla = "";
					Integer secuenciaDato;
					String cveDatoProcesoNodo ="";
					
					// Recorro los renglones de datos de la tarjeta
					for(Object[] renglonTarjeta : renglonesTarjeta) {
						cveDato = (String)renglonTarjeta[9];
						cveOrigen = (String)renglonTarjeta[7];
						secuenciaDato = ((BigDecimal)renglonTarjeta[6]).intValue();
						cveDatoProcesoNodo = (String)renglonTarjeta[9];
						EstatusAtributosDatoTO atributos = EstatusAtributosDatoTO.builder()
								.tipoExcepcion("OK")
								.mensaje("")
								.build();
						
					    //log.info("secuenciaTarjeta: " + secuenciaTarjeta);
						//log.info("renglonTarjeta: " + ((BigDecimal) renglonTarjeta[5]).intValue());
	
					    // Si secuenciaTarjeta es diferente del valor en renglonTarjeta[5]
					    if(secuenciaTarjeta != ((BigDecimal) renglonTarjeta[5]).intValue()) {
					        // Si la lista datoPorTarjeta tiene elementos
					        if(datoPorTarjeta.size() > 0) {
					            // Agrega la lista datoPorTarjeta al mapa con la clave secuenciaTarjeta
					            mapListaDatosPorTarjeta.put(secuenciaTarjeta, datoPorTarjeta);
					            // Registra el tamaño y contenido del mapa para depuración
					            //log.info(" Agrega la lista datoPorTarjeta mapListaDatosPorTarjeta: " + mapListaDatosPorTarjeta.size() + mapListaDatosPorTarjeta.toString());
					            // Limpia la lista datoPorTarjeta
					            datoPorTarjeta = new ArrayList<DatoAreaTrabajoT>();
					        }
					        // Actualiza secuenciaTarjeta con el valor de renglonTarjeta[5]
					        secuenciaTarjeta = ((BigDecimal) renglonTarjeta[5]).intValue();
					        // Registra el tamaño y contenido del mapa para depuración
					        //log.info("mapListaDatosPorTarjeta: " + mapListaDatosPorTarjeta.size() + mapListaDatosPorTarjeta.toString());
					    }
					   
					    List<DatosAreaTrabajoTO> datosAreaTrabajo = resInfo.getContent().getDatosAreaTrabajo();
	
	//			        // Asigna el valor de valorPantalla correspondiente al cveDato
	//			         valorPantalla = datosAreaTrabajo.stream()
	//			                .filter(dato -> dato.getCveDato().equals(cveDato))
	//			                .map(DatosAreaTrabajoTO::getValorPantalla)
	//			                .findFirst()
	//			                .orElse("");
					    int i = 0;
					    
					    // for con tarjetas para llenar
					    for(DatosAreaTrabajoTO dato : datosAreaTrabajo) {
					    	if(cveDatoProcesoNodo != null && cveDatoProcesoNodo.equals(dato.getCveDatoProcesoNodo())
					    			&& dato.getSecuenciaDato().equals(secuenciaDato)) {
					    		valorPantalla = dato.getValorPantalla();
					    		log.info(i + " valorPantalla: " + valorPantalla);
					    		i++;
					    		break;
					    	}
					    }
	
					    // Creo un nuevo objeto DatoAreaTrabajoT con los datos del rengló
					    DatoAreaTrabajoT datoAreaTrabajoTO = DatoAreaTrabajoT.builder()
					    	.valorPantalla("") // FALTA 
					        .secuenciaDato(((BigDecimal) renglonTarjeta[6]).intValue()) 
					        .origenDato((String) renglonTarjeta[7])  
					        .etiqueta((String) renglonTarjeta[8]) 
					        .cveDatoProcesoNodo((String) renglonTarjeta[9])  
					        .cveVariable((String) renglonTarjeta[10])
					        .visible((String) renglonTarjeta[11])
					        .tituloTarjeta((String) renglonTarjeta[4])
					        .build();
					    
					    // Agrego el nuevo dato a la lista
					    datoPorTarjeta.add(datoAreaTrabajoTO);
					}
	
					 // Aseguro que la última secuencia de datos también se guarde en el mapa
					if(datoPorTarjeta.size() > 0) {
					    mapListaDatosPorTarjeta.put(secuenciaTarjeta, datoPorTarjeta);
					    log.info("mapListaDatosPorTarjeta (final): " + mapListaDatosPorTarjeta.size() + mapListaDatosPorTarjeta.toString());
					}
					
					// Variables auxiliares para construir las tarjetas
					String claveOrigenDato="";
					DatoAreaTrabajoT nuevaDato = null;	
					List<DatoAreaTrabajoT> datoAreasTrabajoTO = new ArrayList<DatoAreaTrabajoT>();
					List<TarjetaDato> tarjetasTO = new ArrayList<TarjetaDato>();
					//for(InformacionAreaTrabajoTO infoAreaTrabajoTO: resInfo.getContent().getInfAreaTrabajo()) { // recorrer el hash map
					String cveInstancia = "";
					
					
					log.info("*** mapDatos {} : {}" + resInfo.getContent().getMapDatos().toString() + " " + resInfo.getContent().getMapDatos().size());
					
					// Recorro las instancias y sus datos del área de trabajo
					for(Map.Entry<String, List<DatosAreaTrabajoTO>> mapDatos: resInfo.getContent().getMapDatos().entrySet()) {
					    //log.info("Clave del mapa: " + mapDatos.getKey()); // Depuración
					    cveInstancia = mapDatos.getKey();
						List<DatosAreaTrabajoTO> datosList = mapDatos.getValue();
					    //log.info("datosList: " + datosList.size() + " " + datosList.toString()); // Depuración
						tarjetasTO = new ArrayList<TarjetaDato>();
						
						  // Recorro el mapa de datos por tarjeta para construir las tarjetas			
						for(Map.Entry<Integer, List<DatoAreaTrabajoT>> entry: mapListaDatosPorTarjeta.entrySet()) { // iterar el map que creamos mapListaDatosPorTarjeta
	
							//log.info("FOR mapListaDatosPorTarjeta: " +  mapListaDatosPorTarjeta.size() + mapListaDatosPorTarjeta.toString()); // Depuración
						    List<DatoAreaTrabajoT> datostarjetas = entry.getValue(); // Se obtiene la lista de datos asociada a la clave de la tarjeta actual en el mapa
	
						    String tituloT = ""; // Variable para almacenar el título de la tarjeta
						    datoAreasTrabajoTO = new ArrayList<>(); // Reiniciamos la lista de datos para cada nueva tarjeta
						    
						    // Recorremos la lista de datos asociados a la tarjeta actual
							    for (DatoAreaTrabajoT datostarjeta : datostarjetas) {
							        tituloT = datostarjeta.getTituloTarjeta(); // Obtenemos el título de la tarjeta
							        
							     // Aquí creamos una nueva instancia en lugar de reutilizar 'nuevaDato'
							        nuevaDato = DatoAreaTrabajoT.builder()
							        		.secuenciaDato(datostarjeta.getSecuenciaDato())
							        		.origenDato(datostarjeta.getOrigenDato())
							        		.etiqueta(datostarjeta.getEtiqueta())
							        		.cveVariable(datostarjeta.getCveVariable())
							        		.cveDatoProcesoNodo(datostarjeta.getCveDatoProcesoNodo())
							        		.visible(datostarjeta.getVisible())
							        		.valorPantalla(datostarjeta.getValorPantalla())
							        		.tituloTarjeta(datostarjeta.getTituloTarjeta())
							        		.build(); // Crear una nueva instancia vacía
							        
							        claveOrigenDato = datostarjeta.getCveDatoProcesoNodo(); // Obtenemos la clave del dato del nodo de proceso
							       
							        // Comparamos el dato actual con los datos en la lista 'datosList'
				                    for (DatosAreaTrabajoTO dato : datosList) {
				                        valorPantalla = dato.getValorPantalla(); // Obtenemos el valor en pantalla del dato
	
				                        // Si el origen del dato es una variable del proceso, ajustamos la clave del origen del dato             
				                        if (datostarjeta.getOrigenDato().equals("VARIABLE_PROCESO")) {
				                            claveOrigenDato = datostarjeta.getCveVariable(); // Asignamos la clave de la variable al origen del dato
				                        }
				                        cveDato = dato.getCveDatoProcesoNodo(); // Asignamos la clave del dato del proceso del nodo
				                        cveDato = cveDato != null ? cveDato : dato.getCveDato();
				                        
				                        // Si las claves coinciden, actualizamos el valor en pantalla del dato
				                        if (cveDato.equals(claveOrigenDato)) {
				                            nuevaDato.setValorPantalla(dato.getValorPantalla()); // Asignamos el valor en pantalla al dato correspondiente
				                            break; // Salimos del bucle una vez que encontramos la coincidencia
				                        }
				                    }
				                    datoAreasTrabajoTO.add(nuevaDato); // Añadimos el dato actualizado a la lista de datos de la tarjeta
				                }
	
								
								log.debug("ICONO_ESTADO_AT " + datoAreasTrabajoTO.size() + " " +datoAreasTrabajoTO.toString());						
								
								List<IconoEstadoAT> iconosAT = iconoEstadoATRepository.obtieneIconos(cveEntidad, cveProceso, version, cveAreaTrabajoTarjeta, entry.getKey());
								List<IconoAtTO> iconosAtTO = new ArrayList<IconoAtTO>();
								
							    // Creamos un objeto NodoTO con los datos del nodo de proceso
								NodoTO nodo3 = NodoTO.builder()
										.version(version)
										.cveProceso(cveProceso)
										.cveInstancia(cveInstancia)						
										.build();
								
							    // Recorremos los iconos asociados al estado para evaluar su condición
								for(IconoEstadoAT iconoTO : iconosAT) {
									log.debug("iconoTO: " + iconoTO.getCondicionIconoEstado());
									
									condicionEvaluada = nodoHelper.evaluarCondicion(session, nodo3, iconoTO.getCondicionIconoEstado());
									if(condicionEvaluada.getResultado().equals(Constants.VERDADERO)) {
										IconoAtTO iconoAtTO = IconoAtTO.builder()
												.iconoEstado(iconoTO.getIconoEstado())
												.build();
										iconosAtTO.add(iconoAtTO);
										
									}
								}
								
								// Construimos el objeto TarjetaDato con los datos y los iconos de estado, y lo añadimos a la lista de tarjetas
							    TarjetaDato tarjetaTO = TarjetaDato.builder()
							            .datosPorTarjeta(datoAreasTrabajoTO) // Asignamos los datos de la tarjeta
							            .iconosEstado(iconosAtTO) // Asignamos los iconos de estado
							            .titulo_tarjeta(tituloT) // Asignamos el título de la tarjeta
							            .build();
							    tarjetasTO.add(tarjetaTO); // Añadimos la tarjeta a la lista de tarjetas
						}
						// buscar con la clave en el map nuevo el menu y agregarlo como una tarjeta nueva
						List<Action> accionesParaTarjeta = menuRenglon.get(cveInstancia);
						ActionCveInstancia accionConClave = ActionCveInstancia.builder()
								.acciones(accionesParaTarjeta)
								.cveInstancia(cveInstancia)
								.build();
						
						StNodoProcesoT stNodoProcesoTO = StNodoProcesoT.builder()
								.tarjeta(tarjetasTO)
								.cve_proceso(cveProceso)
								.version(version)
								.descripcion(stNodoProcesoEntidad.get().getDescripcion())
								.cve_nodo(datosProceso.getCveNodo())
								.id_nodo(datosProceso.getIdNodo())
								.cve_area_trabajo_tarjeta(datosProceso.getCveAreaTrabajo())
								.accionesCveInstancia(accionConClave)
								.build();
						stNodosProcesoTO.add(stNodoProcesoTO);
					}
				}

			}
			
			log.info("-----> SE CREO UN RENGLON: " + stNodosProcesoTO.size());
//			for(StNodoProcesoT to: stNodosProcesoTO) {
//				for(TarjetaDato tarjeta :to.getTarjeta()) {
//					for(DatoAreaTrabajoT dato : tarjeta.getDatosPorTarjeta()) {
//						log.info("DATO:::: " + dato.getValorPantalla());					
//						
//					}
//				}
//					log.info("******************");
//			} 
			dataGrid.setRenglonConTarjeta(stNodosProcesoTO);
				

		} else {
			msg.setStatus(resColumns.getMeta().getStatus());
			msg.setMessage(resColumns.getMeta().getMessage());
			log.info("\tCOLUMNAS", msg);
		}
		log.trace("\t      {}", dataGrid);
		log.info("----- YA NO SE MUESTRA DATAGRID");
		log.info("\t----- {}", msg);
		return new DAORet<DataGrid, RetMsg>(dataGrid, msg);
	}

	/**
	 * Obtiene la fila (Row) a partir de la información proporcionada.
	 * @param entityInfo la información de la entidad, que contiene los datos de la actividad.
	 * @param userSession la sesión del usuario.
	 * @return la fila (Row) con los datos procesados.
	 */
	private Row getRow(InformacionAreaTrabajoTO entityInfo, String userSession) {
	    // Inicializa una lista de listas de listas de strings para almacenar las celdas de la fila.
	    List<List<List<String>>> cells = new ArrayList<List<List<String>>>();

	    // Inicializa una lista de listas de strings que contendrá los valores seleccionados para cada fila.
	    List<List<String>> valorSeleccionado = new ArrayList<List<String>>();

	    // Inicializa una lista de strings que contendrá los valores individuales dentro de cada celda.
	    List<String> valSel = new ArrayList<String>();

	    // ### Procesamiento de datos dinámicos
	    // Muestra en el log los datos de la actividad de la entidad.
	    log.debug("\tOCURRENCIA DATOS ACTIVIDAD {}", entityInfo.getDatosActividad());

	    // Divide la cadena 'DatosActividad' de la entidad utilizando '|' como delimitador.
	    String[] split = entityInfo.getDatosActividad().split("\\|");

	    // Muestra en el log el tamaño del array resultante después de la división.
	    log.trace("\tSplit Size: {}", split.length);

	    // Recorre el array 'split' en grupos de 3 elementos.
	    for (int i = 0; i < split.length;) {
	        // Inicializa una nueva lista de listas de strings para almacenar los valores seleccionados.
	        valorSeleccionado = new ArrayList<List<String>>();

	        // Inicializa una nueva lista de strings para almacenar los valores de cada columna.
	        valSel = new ArrayList<String>();

	        try {
	            // Muestra en el log el primer valor (i).
	            log.trace("\t\t{}", split[i]);

	            // Muestra en el log el segundo valor (i + 1).
	            log.trace("\t\t\t{}", split[i + 1]);

	            // Muestra en el log el tercer valor (i + 2) y lo agrega a 'valSel'.
	            log.trace("\t\t\t{}", split[i + 2]);
	            valSel.add(split[i + 2]);
	        }
	        catch (ArrayIndexOutOfBoundsException exArr) {
	            // Si ocurre un error de índice fuera de rango, agrega una cadena vacía y registra el error en el log.
	            valSel.add("");
	            log.error("\t{}", exArr.getMessage());
	        }

	        // Agrega la lista 'valSel' (que contiene el valor procesado) a 'valorSeleccionado'.
	        valorSeleccionado.add(valSel);

	        // Agrega 'valorSeleccionado' (que es una lista de listas) a 'cells', que contiene todas las celdas de la fila.
	        cells.add(valorSeleccionado);

	        // Incrementa el índice en 3 para procesar el siguiente grupo de valores.
	        i += 3;
	    }

	    // ### Procesamiento de datos fijos
	    // Muestra en el log que comienza el procesamiento de los datos fijos de la actividad.
	    log.debug("\tDATOS FIJOS ACTIVIDAD ");

	    // Agrega a 'cells' los datos de la clave de la instancia.
	    getValorSeleccionado(entityInfo.getCveInstancia(), cells);

	    // Agrega a 'cells' la secuencia del nodo.
	    getValorSeleccionado(String.valueOf(entityInfo.getSecuenciaNodo()), cells);

	    // Agrega a 'cells' la situación.
	    getValorSeleccionado(entityInfo.getSituacion(), cells);

	    // Agrega a 'cells' el usuario responsable, procesando la información del usuario bloqueador.
	    getValorSeleccionado(userOwner(entityInfo.getUsuarioBloquea(), userSession), cells);

	    // Crea una nueva instancia de 'Row' with las celdas procesadas.
	    Row row = Row.builder()
	            .cells(cells)
	            .build();

	    // Agrega estilos personalizados a la fila.
	    addEstilos(row, entityInfo);

	    // Agrega acciones o funcionalidades adicionales a la fila.
	    addAcciones(row, entityInfo);

	    // Devuelve la fila construida.
	    return row;
	}


	/**
	 * User owner.
	 * @param usuarioBloquea el usuario bloquea.
	 * @param userSession el user session.
	 * @return el string.
	 */
	private String userOwner(String usuarioBloquea, String userSession) {
		//log.info("usuarioBloquea:  {} |  userSession: {}", usuarioBloquea, userSession);
		
		String owner = "";
		if (StringUtils.isNotBlank(userSession) && StringUtils.isNotBlank(usuarioBloquea)) {
			if (userSession.equals(usuarioBloquea)) {
				owner = "SI";
			}
			else {
				owner = "NO";
			}
		}
		
//log.info("OWENER: " + owner);
		return owner;
	}

	/**
	 * Agrega un valor seleccionado a la estructura de celdas (cells).
	 * @param value el valor que se agregará a las celdas.
	 * @param cells la lista de celdas donde se agregará el valor.
	 */
	private void getValorSeleccionado(String value, List<List<List<String>>> cells) {
	    // Crea una nueva lista de listas de strings para almacenar el valor seleccionado.
	    List<List<String>> valorSeleccionado;

	    // Inicializa 'valorSeleccionado' como una nueva lista vacía de listas de strings.
	    valorSeleccionado = new ArrayList<List<String>>();

	    // Crea una nueva lista de strings que contendrá el valor individual seleccionado.
	    List<String> valSel = new ArrayList<String>();

	    //log.info("VALUE:  " + value);
	    // Agrega el valor proporcionado a la lista 'valSel'.
	    valSel.add(value);

	    // Agrega la lista 'valSel' (que contiene el valor) a 'valorSeleccionado'.
	    valorSeleccionado.add(valSel);

	    // Muestra en el log el valor que se va a agregar a las celdas.
	    log.debug("\t\t{}", valorSeleccionado);

	    // Agrega 'valorSeleccionado' a 'cells', que contiene todas las celdas procesadas de la fila.
	    cells.add(valorSeleccionado);
	}



	/**
	 * Añade los estilos a la fila (row) proporcionada según la información de la entidad.
	 *
	 * @param row        La fila a la que se le añadirán los estilos.
	 * @param entityInfo La información del área de trabajo que contiene los datos del estilo.
	 */
	private void addEstilos(Row row, InformacionAreaTrabajoTO entityInfo) {
	    // Traza para indicar que se está añadiendo estilos.
	    log.trace("\t\t ADD STYLES ");

	    // Traza para mostrar la secuencia y el estilo que se van a procesar.
	    log.trace("\t\t [STYLE] {} {}", entityInfo.getSecuenciaDatoEstilo(), entityInfo.getEstilo());

	    // Lista que contendrá los estilos a añadir.
	    List<Style> estilos = new ArrayList<>();

	    // Verifica que los valores de secuencia y estilo no estén en blanco.
	    if (StringUtils.isNotBlank(entityInfo.getSecuenciaDatoEstilo()) &&
	        StringUtils.isNotBlank(entityInfo.getEstilo())) {
	        
	        // Verifica si la secuencia de datos de estilo no es "NO APLICA".
	        if (StringUtils.isNotBlank(entityInfo.getSecuenciaDatoEstilo()) &&
	            !entityInfo.getSecuenciaDatoEstilo().equalsIgnoreCase("NO APLICA")) {

	            // Traza para indicar que se está añadiendo un estilo válido.
	            log.trace("\t\t\t ADD {} {} ", entityInfo.getSecuenciaDatoEstilo(), entityInfo.getEstilo());

	            // Construye el estilo y lo añade a la lista.
	            estilos.add(Style.builder()
	                .column(entityInfo.getSecuenciaDatoEstilo())  // Asigna la secuencia de columna.
	                .estyle(entityInfo.getEstilo())              // Asigna el estilo.
	                .build());
	        }
	    }

	    // Establece la lista de estilos en la fila (row).
	    row.setEstilos(estilos);
	}


	/**
	 * Agrega las acciones disponibles a la fila.
	 * @param row el objeto Row donde se agregarán las acciones.
	 * @param entityInfo contiene la información de la entidad que determina las acciones habilitadas.
	 */
	private void addAcciones(Row row, InformacionAreaTrabajoTO entityInfo) {
	    // Registra en el log que se está añadiendo acciones a la fila.
	    log.trace("\t\t ADD ACTIONS ");
	    
	    // Crea una lista para almacenar las acciones que se agregarán a la fila.
	    List<Action> acciones = new ArrayList<Action>();

	    // Agrega la acción "CONSULTAR" a la lista de acciones, utilizando la etiqueta y el estado de habilitación de la entidad.
	    addElemtAction(acciones, "CONSULTAR", entityInfo.getEtiquetaConsultar(), entityInfo.getHabilitarConsultar());

	    // Agrega la acción "OBTENER" a la lista de acciones.
	    addElemtAction(acciones, "OBTENER", entityInfo.getEtiquetaTomar(), entityInfo.getHabilitarTomar());

	    // Agrega la acción "LIBERAR" a la lista de acciones.
	    addElemtAction(acciones, "LIBERAR", entityInfo.getEtiquetaLiberar(), entityInfo.getHabilitarLiberar());

	    // Agrega la acción "EJECUTAR" a la lista de acciones.
	    addElemtAction(acciones, "EJECUTAR", entityInfo.getEtiquetaEjecutar(), entityInfo.getHabilitarEjecutar());

	    // Agrega la acción "TERMINAR" a la lista de acciones.
	    addElemtAction(acciones, "TERMINAR", entityInfo.getEtiquetaTerminar(), entityInfo.getHabilitarTerminar());

	    // Agrega la acción "CANCELAR" a la lista de acciones.
	    addElemtAction(acciones, "CANCELAR", entityInfo.getEtiquetaCancelar(), entityInfo.getHabilitarCancelar());

	    // Agrega la acción "BITACORA" a la lista de acciones.
	    addElemtAction(acciones, "BITACORA", entityInfo.getEtiquetaBitacora(), entityInfo.getHabilitarBitacora());

	    // Agrega la acción "GUARDAR" a la lista de acciones.
	    addElemtAction(acciones, "GUARDAR", entityInfo.getEtiquetaGuardar(), entityInfo.getEtiquetaGuardar());
	    
	    // Establece la lista de acciones en la fila actual.
	    row.setAcciones(acciones);
	}


	/**
	 * Añade una acción específica a la lista de acciones si está habilitada.
	 * @param acciones la lista donde se añadirá la acción.
	 * @param accion el nombre de la acción (ej. "CONSULTAR", "EJECUTAR").
	 * @param etiqueta la etiqueta asociada a la acción (texto que se muestra).
	 * @param habilitar indica si la acción está habilitada ("SI" si está habilitada).
	 */
	private void addElemtAction(List<Action> acciones, String accion, String etiqueta, String habilitar) {
	    // Registra en el log la acción, su estado de habilitación y la etiqueta.
	    log.trace("\t\t [ACTION] {} {} {}", habilitar, accion, etiqueta);

	    // Verifica si el campo 'habilitar' no está en blanco y si es igual a "SI" (ignora mayúsculas/minúsculas).
	    if (StringUtils.isNotBlank(habilitar) && "SI".equalsIgnoreCase(habilitar)) {
	        // Si se cumplen las condiciones, se registra en el log que la acción se va a añadir.
	        log.trace("\t\t\t ADD {} {} ", accion, etiqueta);

	        // Añade la acción a la lista de acciones, usando el patrón builder para construir un objeto Action.
	        acciones.add(Action.builder()
	            .action(accion)    // Establece el nombre de la acción.
	            .label(etiqueta)   // Establece la etiqueta visible asociada a la acción.
	            .build());         // Construye el objeto Action.
	    }
	}


	/**
	 * Builder column.
	 * @param datosProceso the datos proceso
	 * @param entityColumn the entity
	 * @return the com.briomax.briobpm.transferobjects.dynamicForm 2 . column
	 */
	private ColumnGrid builderColumn(ProcesoTO datosProceso,
		ColumnasAreaTrabajoTO entityColumn) {
		List<List<String>> options = null;
		// Los filtros son a nivel tabla.
		/*
		TipoListaEnum tipoLista = TipoListaEnum.getTipoListaEnum(entity.getTipoLista());
		switch (tipoLista) {
		case CATALOGO_ETIQUETA:
			// SP_LEE_ETIQUETAS_CATALOGO
			options = new ArrayList<List<String>>();
			break;
		case TABLA:
			// SP_LEE_ETIQUETAS_TABLA
			options = new ArrayList<List<String>>();
			break;
		default:
			break;
		}*/
		ColumnGrid columnGrid = ColumnGrid.builder()
				.secuencia(entityColumn.getSecuencia())
				.properties(Properties.builder()
					.cveVariable(entityColumn.getCveVariable())
					.etiqueta(entityColumn.getEtiqueta())
					.width(entityColumn.getAnchoColumna())
					.tipoDato(entityColumn.getTipoDato())
					.visible(entityColumn.getVisible())
					.envioGrabar("NO")
					.build())
				.format(Format.builder()
					.longitud(entityColumn.getLongitud())
					.enteros(entityColumn.getEnteros())
					.decimales(entityColumn.getDecimales())
					.formatoFecha(entityColumn.getFormatoFecha())
					.colorDato(entityColumn.getColorDato())
					.build())
				.control(Control.builder()
					.tipoControl(entityColumn.getTipoControl())
					.tipoInteraccion(SALIDA)
					.options(options)
					.build())
				.filter(Filter.builder()
					.filtro(entityColumn.getFiltro())
					.multiSeleccion(entityColumn.getMultiseleccion())
					.opcionTodos(entityColumn.getOpcionTodos())
					.etiquetaTodos(entityColumn.getEtiquetaTodos())
					.build())
				.build();
		log.debug("\t\tDynamic {}", columnGrid);
		return columnGrid;
	}

	/**
	 * Gets the colums fix.
	 * @return the colums fix
	 */
	private List<ColumnGrid> getColumsFix(int sequence) {
		List<ColumnGrid> columnsGrid = 
				new ArrayList<ColumnGrid>();
		columnsGrid.add(ColumnGrid.builder()
			.secuencia(sequence + 1)
			.properties(Properties.builder()
				.cveVariable("CVE_INSTANCIA")
				.etiqueta("CVE_INSTANCIA")
				.width(0)
				.tipoDato("ALFANUMERICO")
				.visible("NO")
				.envioGrabar("NO")
				.build())
			.format(Format.builder()
				.longitud(null)
				.enteros(null)
				.decimales(null)
				.formatoFecha(null)
				.colorDato("NO")
				.build())
			.control(Control.builder()
				.tipoControl("LABEL")
				.tipoInteraccion(SALIDA)
				.options(null)
				.build())
			.filter(Filter.builder()
				.filtro("NO")
				.multiSeleccion(null)
				.opcionTodos(null)
				.etiquetaTodos(null)
				.build())
			.build());
		columnsGrid.add(ColumnGrid.builder()
			.secuencia(sequence + 2)
			.properties(Properties.builder()
				.cveVariable("SECUENCIA_NODO")
				.etiqueta("SECUENCIA_NODO")
				.width(0)
				.tipoDato("NUMERICO")
				.visible("NO")
				.envioGrabar("NO")
				.build())
			.format(Format.builder()
				.longitud(5)
				.enteros(5)
				.decimales(0)
				.formatoFecha(null)
				.colorDato("NO")
				.build())
			.control(Control.builder()
				.tipoControl("LABEL")
				.tipoInteraccion(SALIDA)
				.options(null)
				.build())
			.filter(Filter.builder()
				.filtro("NO")
				.multiSeleccion(null)
				.opcionTodos(null)
				.etiquetaTodos(null)
				.build())
			.build());
		columnsGrid.add(ColumnGrid.builder()
			.secuencia(sequence + 3)
			.properties(Properties.builder()
				.cveVariable("SITUACION")
				.etiqueta("SITUACION")
				.width(0)
				.tipoDato("ALFANUMERICO")
				.visible("NO")
				.envioGrabar("NO")
				.build())
			.format(Format.builder()
				.longitud(null)
				.enteros(null)
				.decimales(null)
				.formatoFecha(null)
				.colorDato("NO")
				.build())
			.control(Control.builder()
				.tipoControl("LABEL")
				.tipoInteraccion(SALIDA)
				.options(null)
				.build())
			.filter(Filter.builder()
				.filtro("NO")
				.multiSeleccion(null)
				.opcionTodos(null)
				.etiquetaTodos(null)
				.build())
			.build());
		columnsGrid.add(ColumnGrid.builder()
			.secuencia(sequence + 4)
			.properties(Properties.builder()
				.cveVariable("OWNER")
				.etiqueta("OWNER")
				.width(0)
				.tipoDato("ALFANUMERICO")
				.visible("NO")
				.envioGrabar("NO")
				.build())
			.format(Format.builder()
				.longitud(null)
				.enteros(null)
				.decimales(null)
				.formatoFecha(null)
				.colorDato("NO")
				.build())
			.control(Control.builder()
				.tipoControl("LABEL")
				.tipoInteraccion(SALIDA)
				.options(null)
				.build())
			.filter(Filter.builder()
				.filtro("NO")
				.multiSeleccion(null)
				.opcionTodos(null)
				.etiquetaTodos(null)
				.build())
			.build());
		log.debug("\t\tStatics {}", columnsGrid);
		return columnsGrid;
	}

	/** 
	 * {@inheritDoc}
	 * @see com.briomax.briobpm.business.service.core.IAreaTrabajoService#inicioProceso(com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO, com.briomax.briobpm.transferobjects.in.ProcesoTO)
	 */
	@Override
	public DAORet<ActividadTO, RetMsg> inicioProceso(DatosAutenticacionTO session, ProcesoTO datosProceso)
		throws BrioBPMException {
		log.info("\t-----INICIA PROCESO SERVICE {} {}", session, datosProceso);
		log.info("-- cve Rol: " + datosProceso.getCveRol());
		ProcesoInicialTO instanciaNodo = ProcesoInicialTO.builder()
				.cveRol(datosProceso.getCveRol())
				.cveProceso(datosProceso.getCveProceso())
				.version(new BigDecimal(datosProceso.getVersion()))
				.concepto("NUEVA INCIDENCIA")
				.origen("USUARIO")
				.build();
		
		
		EstatusCreaInstanciaTO response = null;
		try {
			response = procesoHelper.creaInstancia(session, instanciaNodo, datosProceso.getValoresReferenciaEnvio(), null);
    		log.info("-----------> CREA INSTANCIA: " + response.toString());

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RetMsg meta = RetMsg.builder().status(response.getTipoExcepcion()).message(response.getMensaje()).build();
//		InicioProceso response = areaTrabajoDAO.creaInstanciaProceso(session.getCveUsuario(), session.getCveEntidad(),
//			session.getCveLocalidad(), session.getCveIdioma(), datosProceso.getCveRol(), datosProceso.getCveProceso(),
//			new BigDecimal(datosProceso.getVersion()), "NUEVA INCIDENCIA", "USUARIO");
//		RetMsg meta = RetMsg.builder().status(response.getStatus()).message(response.getMessage()).build();
		
		log.trace("\t\tRESPONSE DATA: {}", meta);
		ActividadTO activity = ActividadTO.builderActividadTO()
				.cveAreaTrabajo(datosProceso.getCveAreaTrabajo())
				.cveNodo(datosProceso.getCveNodo())
				.cveProceso(datosProceso.getCveProceso())
				.idNodo(datosProceso.getIdNodo())
				.version(datosProceso.getVersion())
				.secNodo(response.getSecuenciaNodoActividad()) //.secNodo(response.getSecNodoActividad())
				.cveNodo(response.getCveNodoActividad())
				.idNodo(response.getIdNodoActividad())
				.cveRol(datosProceso.getCveRol())
				.origen("USUARIO")
				.build();
		if (meta.getStatus().equalsIgnoreCase(Constants.OK)) {
			activity.setCveInstancia(response.getCveInstancia());
			
			log.trace("\t\tSend Emails");
			emailService.sendEmails(session.getCveUsuario(), session.getCveEntidad(), session.getCveLocalidad(),
				session.getCveIdioma());
			log.info("----------> SEND EMAILS");
		}
		log.trace("\t     {} ", activity);
		log.debug("\t-----{} ", meta);
        log.info("############### RETURN inicioProceso ");
       
		return new DAORet<ActividadTO, RetMsg>(activity, meta);
	}

	/** 
	 * {@inheritDoc}
	 * @see com.briomax.briobpm.business.service.core.IAreaTrabajoService#leeBitacoraNodo(com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO, com.briomax.briobpm.transferobjects.in.ActividadTO)
	 */
	@Override
	public DAORet<List<BitacoraTO>, RetMsg> leeBitacoraNodo(DatosAutenticacionTO session, ActividadTO actividad)
		throws BrioBPMException {
		log.trace("Bitacora Nodo {} {} ", session, actividad);
		List<BitacoraTO> journal = new LinkedList<BitacoraTO>();

			
			// CONCATENA LA LISTA DE LOS PARÁMETROS PARA DESPLEGARLOS EN CASO DE ERROR
			// RTRIM (LTRIM (CONVERT (CHAR (10), @C_VERSION)))
			String chVariableValor = "@CVE_PROCESO@|" + actividad.getCveProceso().trim() + "|" +
					"@VERSION@|" + actividad.getVersion()+ "|" +
					"@CVE_INSTANCIA@|" +  actividad.getCveInstancia().trim() + "|" +
					"@CVE_NODO@|" + actividad.getCveNodo().trim() + "|" +
					"@ID_NODO@|" + actividad.getIdNodo() + "|" +
					"@SECUENCIA_NODO@|" + actividad.getSecNodo();
			
			// FN_TRADUCIR_ETIQUETA
			//String traducirEtiqueta(DatosAutenticacionTO session, String cveEntidad, String cveIdiomaLocalidad,
			//		String etiqueta);
			
		//entidadHelper.traducirEtiqueta(session, session.getCveEntidad(), session.getCveIdioma(), actividad.getCveAreaTrabajo().g)
			// Consulta Bitácora
			
		/*DAORet<List<LeeBitacoraNodo>, RetMsg> bitacora =
			areaTrabajoDAO.leeBitacoraNodo(session.getCveUsuario(), session.getCveEntidad(), session.getCveLocalidad(),
				session.getCveIdioma(), actividad.getCveProceso(),
				actividad.getVersion() != null ? new BigDecimal(actividad.getVersion()) : BigDecimal.ZERO,
				actividad.getCveInstancia(), actividad.getCveNodo(), actividad.getIdNodo(), actividad.getSecNodo());*/
		BitacoraNodo bitacoraNodo =
				nodoHelper.leeBitacoraNodo(session, actividad);
		
		log.trace("\t\tRESPONSE DATA LEE BITACORA NODO: {}", bitacoraNodo);
		if (Constants.OK.equalsIgnoreCase(bitacoraNodo.getMensaje())) {
			log.debug("Converte Entity (LeeInfReporteActividad) -> Dto (ReporteActividadTO) ");
			journal.addAll(bitacoraNodo.getListaBitacoraNodo());
		}
		RetMsg msg = RetMsg.builder()
				.status( bitacoraNodo.getMensaje())
				.build();
		return new DAORet<List<BitacoraTO>, RetMsg>(journal,msg);
	}

	@Override
	public List<StNodoProceso> getStNodosProceso (String cveEntidad, String cveProceso, BigDecimal version)
			throws BrioBPMException {
		return stNodoProcesoRepository.obtieneStNodosProcesos(cveEntidad, cveProceso, version);
		}
	
	@Override
	public List<AreaTrabajo> getAreasTrabajo(String cveEntidad, String cveProceso, BigDecimal version) throws BrioBPMException {
		return null;
	}
	@Override
	public List<TarjetaAreaTrabajo> getTarjetasAreaTrabajo(String cveEntidad, String cveProceso, BigDecimal version,
			String cveAreaTrabajo) throws BrioBPMException {
		return tarjetaAreaTrabajoTRepository.obtieneTarjetasAreaTrabajo(cveEntidad, cveProceso, version, cveAreaTrabajo);
	}

	@Override
	public List<InNodoProceso> getInNodosProceso(String cveEntidad, String cveProceso, BigDecimal version, String cveNodo, BigDecimal idNodo)
			throws BrioBPMException {
		
		return inNodoProcesoRepository.obtieneInNodoProceso(cveEntidad , cveProceso , version, cveNodo, idNodo);
	}
	
	@Override
	public List<DatoAreaTrabajo> getDatosAreaTrabajo(String cveEntidad, String cveProceso, BigDecimal version, String cveAreaTarjeta)
			throws BrioBPMException {
		
		return datoAreaTrabajoRepository.obtieneDatosAT(cveEntidad , cveProceso , version, cveAreaTarjeta);
	}

	@Override
	public DAORet<DatosAutenticacionTO, RetMsg> autenticarUsuario(DatosAutenticacionTO datosAutenticacion)
			throws BrioBPMException {
		log.info("----> AUTENTICAR USUARIO");
		log.trace(">>>>>> REQUEST Autenticar Usuario - Datos: {}", datosAutenticacion.toString());
		
		RetMsg meta = RetMsg.builder().build();
		DatosAutenticacionTO usuarioAutenticado = null;
		
		try {
			// Validar que los datos de autenticación no sean nulos
			if (datosAutenticacion == null) {
				meta.setStatus("ERROR");
				meta.setMessage("Los datos de autenticación son requeridos");
				return new DAORet<>(null, meta);
			}
			
			// Validar campos obligatorios
			if (datosAutenticacion.getCveUsuario() == null || datosAutenticacion.getCveUsuario().trim().isEmpty()) {
				meta.setStatus("ERROR");
				meta.setMessage("El usuario es requerido");
				return new DAORet<>(null, meta);
			}
			
			if (datosAutenticacion.getCveEntidad() == null || datosAutenticacion.getCveEntidad().trim().isEmpty()) {
				meta.setStatus("ERROR");
				meta.setMessage("La entidad es requerida");
				return new DAORet<>(null, meta);
			}
			
			// Aquí puedes agregar la lógica de validación real contra la base de datos
			// Por ejemplo, validar usuario y contraseña contra una tabla de usuarios
			// usuarioRepository.validarUsuario(datosAutenticacion.getCveUsuario(), password);
			
			// Por ahora, una validación básica de ejemplo
			// En una implementación real, aquí validarías contra la base de datos
			if (validarCredenciales(datosAutenticacion)) {
				usuarioAutenticado = DatosAutenticacionTO.builder()
					.cveUsuario(datosAutenticacion.getCveUsuario())
					.cveEntidad(datosAutenticacion.getCveEntidad())
					.cveLocalidad(datosAutenticacion.getCveLocalidad())
					.cveIdioma(datosAutenticacion.getCveIdioma())
					.cveMoneda(datosAutenticacion.getCveMoneda())
					.build();
				
				meta.setStatus("OK");
				meta.setMessage("Usuario autenticado correctamente");
				log.info("Usuario {} autenticado exitosamente", datosAutenticacion.getCveUsuario());
			} else {
				meta.setStatus("ERROR");
				meta.setMessage("Credenciales inválidas");
				log.warn("Intento de autenticación fallido para usuario: {}", datosAutenticacion.getCveUsuario());
			}
			
		} catch (Exception e) {
			log.error("Error al autenticar usuario: {}", e.getMessage(), e);
			meta.setStatus("ERROR");
			meta.setMessage("Error interno del servidor al procesar la autenticación");
		}
		
		log.info("<<<<<< RESPONSE Autenticar Usuario - Status: {}", meta.getStatus());
		return new DAORet<>(usuarioAutenticado, meta);
	}
	
	/**
	 * Valida las credenciales del usuario.
	 * En una implementación real, este método consultaría la base de datos.
	 * 
	 * @param datosAutenticacion los datos de autenticación
	 * @return true si las credenciales son válidas, false en caso contrario
	 */
	private boolean validarCredenciales(DatosAutenticacionTO datosAutenticacion) {
		// Implementación de ejemplo - en producción esto debe consultar la base de datos
		// y validar contraseñas encriptadas
		
		// Aquí puedes agregar la lógica real de validación:
		// 1. Consultar usuario en la base de datos
		// 2. Validar contraseña (hash)
		// 3. Verificar estado del usuario (activo/inactivo)
		// 4. Validar permisos y roles
		
		// Por ahora retorna true para usuarios válidos de ejemplo
		return datosAutenticacion.getCveUsuario() != null && 
		       !datosAutenticacion.getCveUsuario().trim().isEmpty() &&
		       datosAutenticacion.getCveEntidad() != null &&
		       !datosAutenticacion.getCveEntidad().trim().isEmpty();
	}

	@Override
	public DAORet<ActividadTO, RetMsg> inicioProcesoSeguro(DatosAutenticacionTO datosAuth, ProcesoSeguroTO datosProcesoSeguro)
			throws BrioBPMException {
		log.info("----> INICIO PROCESO SEGURO");
		log.trace(">>>>>> REQUEST Inicio Proceso Seguro - Datos: {}", datosProcesoSeguro.toString());
		
		RetMsg meta = RetMsg.builder().build();
		ActividadTO actividadCreada = null;
		
		try {

			
			// 2. Validar campos obligatorios del proceso
			if (datosProcesoSeguro.getCveProceso() == null || datosProcesoSeguro.getCveProceso().trim().isEmpty()) {
				meta.setStatus("ERROR");
				meta.setMessage("La clave del proceso es requerida");
				return new DAORet<>(null, meta);
			}
			
			// 3. Obtener datos proceso
			List<StNodoSiguiente> nodoSiguiente = stNodoSiguienteRepository.
					obtieneProcesoNodosSiguiente(datosAuth.getCveEntidad(), 
					datosProcesoSeguro.getCveProceso(), 	
					"EVENTO-INICIO-MENSAJE", 
					BigDecimal.ONE.intValue());
			if (nodoSiguiente == null || nodoSiguiente.isEmpty()) {
				meta.setStatus("ERROR");
				meta.setMessage("No se encontraron datos del proceso para la clave: " + datosProcesoSeguro.getCveProceso());
				return new DAORet<>(null, meta);
			}
			ProcesoInicialTO instanciaNodo = null;
			if (nodoSiguiente.size() >= 1) {
				 instanciaNodo = ProcesoInicialTO.builder()	
				.cveRol("CLIENTE") // Rol fijo para procesos seguros
				.cveProceso(datosProcesoSeguro.getCveProceso())
				.version(nodoSiguiente.get(0).getId().getVersion())		
				.concepto("MENSAJE SERVICIO")
				.origen("MENSAJE-SERVICIO")
				.build();
			}
			// 4. Construir datos del proceso para la creación de instancia
			
			
			log.info("Creando instancia de proceso: {}", instanciaNodo.getCveProceso());
			
			// 5. Crear la instancia del proceso
			
			EstatusCreaInstanciaTO response = EstatusCreaInstanciaTO.builder()
					.tipoExcepcion("OK")
					.mensaje("Instancia creada exitosamente")
					.build();
			log.info("Instancia creada exitosamente: TODO aquí va la creacion de la instancia {}");
			response.setTipoExcepcion("OK");
			response.setMensaje("Instancia DUMMY creada exitosamente");
			
					//getDataOccurrence().get(0)
					//.getDataSection().get(0).getDataVariables();
					
			try {
				response = procesoHelper.creaInstancia(datosAuth, instanciaNodo, 
						datosProcesoSeguro.getValoresReferenciaEnvio(), null);
				log.info("Instancia creada exitosamente: {}", response.getCveInstancia());
			} catch (ParseException e) {
				log.error("Error al crear instancia del proceso: {}", e.getMessage(), e);
				meta.setStatus("ERROR");
				meta.setMessage("Error al procesar los datos del proceso: " + e.getMessage());
				return new DAORet<>(null, meta);
			}
			
			// 6. Construir respuesta de actividad
			meta = RetMsg.builder()
					.status(response.getTipoExcepcion())
					.message(response.getMensaje())
					.build();
			
			if ("OK".equalsIgnoreCase(meta.getStatus())) {
				
				actividadCreada = ActividadTO.builderActividadTO()
						.cveAreaTrabajo("CRE_FOL")
						.cveNodo(instanciaNodo.getCveNodo())
						.cveProceso(instanciaNodo.getCveProceso())
						.idNodo(instanciaNodo.getIdNodo())
						.version(instanciaNodo.getVersion().toString())
						.secNodo(response.getSecuenciaNodoActividad()) //.secNodo(response.getSecNodoActividad())
						.cveNodo(response.getCveNodoActividad())
						.idNodo(response.getIdNodoActividad())
						.cveRol(instanciaNodo.getCveRol())
						.cveAreaTrabajo("CRE_FOL")
						.cveInstancia(response.getCveInstancia())
						.origen("MENSAJE-SERVICIO")
						.build();
				 String variables = datosProcesoSeguro.getValoresReferenciaEnvio().split("#")[1];
			     String salida = variablesSalida(actividadCreada, variables );
			     actividadCreada.setValoresReferenciaEnvio(salida);		     
					SaveSectionTO saveSection = procesoHelper.setCreaValoresReferenciaDesdeValores(
				    		 datosAuth,
					         actividadCreada,
					         datosProcesoSeguro.getValoresReferenciaEnvio().split("#")[0]);
					List<SectionVariablesTO> variablesTO = saveSection.getData().get(0).getDataOccurrence().get(0).getSectionVariables();
			     
			     meta = ejecucionActividadService.guardarTerminarActividad(datosAuth, saveSection);
				
				/* 7. Enviar emails si la configuración lo requiere
				try {
					log.trace("Enviando emails para el proceso creado");
					emailService.sendEmails(datosAuth.getCveUsuario(), datosAuth.getCveEntidad(), 
							datosAuth.getCveLocalidad(), datosAuth.getCveIdioma());
					log.info("Emails enviados exitosamente");
				} catch (Exception e) {
					log.warn("Error al enviar emails, pero el proceso se creó correctamente: {}", e.getMessage());
					// No fallar todo el proceso por error en emails
				}
				*/
				meta.setMessage("CVE_INSTANCIA| " + response.getCveInstancia());
				log.info("Proceso seguro completado exitosamente para usuario: {} - Instancia: {}", 
						datosAuth.getCveUsuario(), response.getCveInstancia());
			} else {
				log.error("Error al crear el proceso: {}", meta.getMessage());
			}
			
		} catch (BrioBPMException e) {
			log.error("Error BrioBPM en inicio proceso seguro: {}", e.getMessage(), e);
			meta.setStatus("ERROR");
			meta.setMessage("Error del sistema: " + e.getMessage());
		} catch (Exception e) {
			log.error("Error inesperado en inicio proceso seguro: {}", e.getMessage(), e);
			meta.setStatus("ERROR");
			meta.setMessage("Error interno del servidor");
		}
		
		log.info("<<<<<< RESPONSE Inicio Proceso Seguro - Status: {}, Instancia: {}", 
				meta.getStatus(), actividadCreada != null ? actividadCreada.getCveInstancia() : "N/A");
		
		return new  DAORet<ActividadTO, RetMsg>(actividadCreada, meta);
	}
	


	/**
	 * Valida las credenciales del usuario.
	 * En una implementación real, este método consultaría la base de datos.
	 * 
	 * @param datosAutenticacion los datos de autenticación
	 * @return true si las credenciales son válidas, false en caso contrario
	 */
	private String variablesSalida(ActividadTO activity, String variable) {
		String valores = "";
		String[] split = variable.split("\\|");
		
		List<SectionVariablesTO> listSection = new ArrayList<SectionVariablesTO>();
		for (String val : split) {
			SectionVariablesTO section = new SectionVariablesTO();
			log.info("VALOR:  " + val);
			if(val.equalsIgnoreCase("CVE_INSTANCIA")) {
				valores = valores + val+ "|" + activity.getCveInstancia() + ",";
			}
			else if(val.equalsIgnoreCase("CVE_NODO")) {
				valores = valores + val+ "|" + activity.getCveNodo() + ",";
			}
			else if(val.equalsIgnoreCase("ID_NODO")) {
				valores = valores + val+ "|" + activity.getIdNodo() + ",";
			}
			else if(val.equalsIgnoreCase("SECUENCIA_NODO")) {
				valores = valores + val+ "|" + activity.getSecNodo() + ",";
			}
			else if(val.equalsIgnoreCase("CVE_PROCESO")) {
				valores = valores + val+ "|" + activity.getCveProceso() + ",";
			}
			else if(val.equalsIgnoreCase("VERSION")) {
				valores = valores + val+ "|" + activity.getVersion() + ",";
			}
			else if(val.equalsIgnoreCase("CVE_ROL")) {
				valores = valores + val+ "|" + activity.getCveRol() + ",";
			}
			else {
				log.info("NO APLICA");
			}
		}
		valores = valores.substring(0, valores.length() - 1);
		return valores;
	}

	@Override
	public DAORet<DatosAutenticacionTO, RetMsg> autenticarUsuarioPass(String usuario, String password)
			throws BrioBPMException {
		RetMsg meta = RetMsg.builder().build();
		DatosAutenticacionTO usuarioAutenticado = null;
		
		// Por ahora, una validación básica de ejemplo
		// En una implementación real, aquí validarías contra la base de datos
		
			usuarioAutenticado = DatosAutenticacionTO.builder()
				.cveUsuario(usuario)
				.cveEntidad("BRIOMAX")
				.cveLocalidad("BRIOMAX-CENTRAL")
				.cveIdioma("ES-MX")
				.cveMoneda("MXN")
				.build();
			
			meta.setStatus("OK");
			meta.setMessage("Usuario autenticado correctamente");
			log.info("Usuario {} autenticado exitosamente", usuarioAutenticado.getCveUsuario());
		
		
		
		return new DAORet<>(usuarioAutenticado, meta);
	}

	@Override
	public EstatusCreaInstanciaTO inicioProcesoApp(DatosAutenticacionTO session, String cveProceso, String cveNodo,
			Integer idNodo, Integer secNodo, String version) throws BrioBPMException {
		
		EstatusCreaInstanciaTO response = null;
		List<String> lista = usuarioRolRepository.buscarRoles(session.getCveEntidad(), session.getCveUsuario(),
				cveProceso, new BigDecimal(version));

		if (lista.size() > 0) {
			ProcesoInicialTO instanciaNodo = ProcesoInicialTO.builder()
					.cveRol(lista.get(0))
					.cveProceso(cveProceso)
					.version(new BigDecimal(version))
					.concepto("NUEVA INCIDENCIA")
					.origen("USUARIO")
					.build();

			try {
				response = procesoHelper.creaInstancia(session, instanciaNodo, null, null);
			} catch (ParseException e) {
				throw(new BrioBPMException(e) );  
			}
	    	log.info("-----------> CREA INSTANCIA: " + response.toString());
		}
		return response;
	}
}
