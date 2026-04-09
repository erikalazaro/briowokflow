/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.rest.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briomax.briobpm.business.helpers.base.IDashboardHelper;
import com.briomax.briobpm.business.service.catalogs.core.IActividadService;
import com.briomax.briobpm.business.service.catalogs.core.IProcesoService;
import com.briomax.briobpm.business.service.core.IAppMovilService;
import com.briomax.briobpm.business.service.core.IAreaTrabajoService;
import com.briomax.briobpm.business.service.core.IDashboardsService;
import com.briomax.briobpm.business.service.core.IFormularioDinamicoService;
import com.briomax.briobpm.common.core.Constants;
import com.briomax.briobpm.common.core.DAORet;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.transferobjects.app.ActividadAutorizarTO;
import com.briomax.briobpm.transferobjects.app.ActividadTO;
import com.briomax.briobpm.transferobjects.app.Datos;
import com.briomax.briobpm.transferobjects.app.GraficaTO;
import com.briomax.briobpm.transferobjects.app.Grafico;
import com.briomax.briobpm.transferobjects.app.GuardaTO;
import com.briomax.briobpm.transferobjects.app.ProcesoTO;
import com.briomax.briobpm.transferobjects.app.RespuestaFormularioTO;
import com.briomax.briobpm.transferobjects.app.Seccion;
import com.briomax.briobpm.transferobjects.dashboards.Dashboard;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;
import com.briomax.briobpm.transferobjects.in.DocumentoAppTO;
import com.briomax.briobpm.transferobjects.in.EstatusCreaInstanciaTO;
import com.briomax.briobpm.transferobjects.in.EstatusTO;
import com.briomax.briobpm.transferobjects.in.NodoTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Class DasboardsRestController.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion May 12, 2021 4:38:01 PM Modificaciones:
 * @since JDK 1.8
 */
@Tag(name = "SolicitudesAppController", description = "DashboardsApp.")
@RestController
@RequestMapping(SolicitudesAppController.ROOT_URL)
@Slf4j
public class SolicitudesAppController extends AbstractBbpmController {

	/** Constante para la url del servicio. */
	public static final String ROOT_URL = "/services/app/";

	/** El atributo o variable dashboards service. */
	@Autowired
	private IDashboardsService dashboardsService;
	
	/** El atributo o variable Proceso service. */
	@Autowired
	private IProcesoService procesoService;
	
	/** El atributo o variable Actividad service. */
	@Autowired
	private IActividadService actividadService;
	
	/** El atributo o variable Formulario Dinamico Service. */
	@Autowired
	private IFormularioDinamicoService formDinamicoService;
	
	/** El atributo o variable area trabajo service. */
	@Autowired
	private IAreaTrabajoService areaTrabajoService;
	
	@Autowired
	private IDashboardHelper dashboardHelper;

	@Autowired
	private IAppMovilService appService;
	
	@Operation(summary = "Dashboard App", description = "Dashboard App.")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "OK - Successful operation.",
				content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
						schema = @Schema(description = "Dashboard Operativo", title = "Dashboard Operativo.",
								implementation = Dashboard.class))}),
		@ApiResponse(responseCode = "204",
		description = "No Content - The server successfully processed the request, and is not returning any content.",
		content = {@Content(schema = @Schema(implementation = String.class))}),
		@ApiResponse(responseCode = "400", ref = "BadRequestError"),
		@ApiResponse(responseCode = "401", ref = "UnauthorizedError"),
		@ApiResponse(responseCode = "403", ref = "ForbiddenError"),
		@ApiResponse(responseCode = "404", ref = "NotFoundError"),
		@ApiResponse(responseCode = "405", ref = "MethodNotAllowedError"),
		@ApiResponse(responseCode = "408", ref = "RequestTimeoutError"),
		@ApiResponse(responseCode = "500", description = "Internal Server Error - Processing error.", content = {
			@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = String.class))})
		}
	)
	//@Parameter(name = "datos", required = true, schema = @Schema(implementation = SolicitudAppTO.class))
	@SneakyThrows(BrioBPMException.class)
	@GetMapping(value = "dashboardincial", produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> dashboardIncial(HttpServletRequest request) throws ParseException {	//, @RequestBody SolicitudAppTO filtros
		ResponseEntity<?> response = null;
		//DatosAutenticacionTO userSession = getUserSessionBpm();
		log.trace(">>>>>> REQUEST Dashboard Operativo ### {}{}operativo \nSession ",
			contextPath, ROOT_URL );// converterJsonAsString(filtros)
		
		log.debug("\tENTIDAD: {}", request.getParameter("cveEntidad"));
		log.debug("\tLOCALIDAD: {}", request.getParameter("cveLocalidad"));
		log.debug("\tIDIOMA: {}", request.getParameter("cveIdioma"));
		log.debug("\tUSUARIO: {}", request.getParameter("username"));

		Datos to = Datos.builder().id("1").x("Eficiencia Global de Procesos").y(93).build(); 
		Datos to1 = Datos.builder().id("2").x("").y(7).build();
		
		List<Datos> ls = new ArrayList<Datos>();
		ls.add(to);
		ls.add(to1);
		
		Grafico g1 = Grafico.builder().id("1").tipo("PIE").data(ls).build();
		List<Grafico> gfs = new ArrayList<Grafico>();
		gfs.add(g1);
		
		Seccion sec = Seccion.builder()
				.title("Eficiencia Global")
				.order(1)
				.graphics(gfs)
				.tieneEscala("NO")
				.build();
		List<Seccion> secciones = new ArrayList<Seccion>();
		secciones.add(sec);
		
		to = Datos.builder().id("1").x("Registro de Contratistas").y(98).build(); 
		to1 = Datos.builder().id("2").x("Verificación Mensual de REPSE").y(90).build();
		Datos to2 = Datos.builder().id("3").x("Verificación de Recibos de Nómina").y(88).build();
		
		ls = new ArrayList<Datos>();
		ls.add(to);
		ls.add(to1);
		ls.add(to2);
		
		g1 = Grafico.builder().id("1").tipo("LINEAL").data(ls).build();
		gfs = new ArrayList<Grafico>();
		gfs.add(g1);
		
		to = Datos.builder().id("1").x("Registro de Contratistas").y(88).build(); 
		to1 = Datos.builder().id("2").x("Verificación Mensual de REPSE").y(80).build();
		to2 = Datos.builder().id("3").x("Verificación de Recibos de Nómina").y(78).build();
		
		ls = new ArrayList<Datos>();
		ls.add(to);
		ls.add(to1);
		ls.add(to2);
		
		g1 = Grafico.builder().id("2").tipo("LINEAL").data(ls).build();
		gfs.add(g1);
		
		sec = Seccion.builder()
				.title("Eficiencia Histórica de Procesos Últimos 6 meses")
				.order(2)
				.graphics(gfs)
				.tieneEscala("NO")
				.build();

		secciones.add(sec);		
		//----------------
		
		to = Datos.builder().id("1").x("27/02/2024").y(9).build(); 
		to1 = Datos.builder().id("2").x("28/02/2024").y(39).build();
		to2 = Datos.builder().id("3").x("29/02/2024").y(13).build();

		ls = new ArrayList<Datos>();
		ls.add(to);
		ls.add(to1);
		ls.add(to2);		
        
		to = Datos.builder().id("4").x( "01/03/2024").y(22).build(); 
		to1 = Datos.builder().id("5").x("04/03/2024").y(3).build();
		to2 = Datos.builder().id("6").x("12/03/2024").y(1).build();      
        
		ls.add(to);
		ls.add(to1);
		ls.add(to2);       
        
		to = Datos.builder().id("7").x("15/03/2024").y(14).build(); 
		to1 = Datos.builder().id("8").x("19/03/2024").y(3).build();
		to2 = Datos.builder().id("9").x("20/03/2024").y(2).build();      
        
		ls.add(to);
		ls.add(to1);
		ls.add(to2);        
        
		
		g1 = Grafico.builder().id("Inicial").tipo("BAR").data(ls).build();
		gfs = new ArrayList<Grafico>();
		gfs.add(g1);
		
		to = Datos.builder().id("1").x("27/02/2024").y(3).build(); 
		to1 = Datos.builder().id("2").x("28/02/2024").y(7).build();
		to2 = Datos.builder().id("3").x("29/02/2024").y(0).build();

		ls = new ArrayList<Datos>();
		ls.add(to);
		ls.add(to1);
		ls.add(to2);		
        
		to = Datos.builder().id("4").x( "01/03/2024").y(4).build(); 
		to1 = Datos.builder().id("5").x("04/03/2024").y(1).build();
		to2 = Datos.builder().id("6").x("12/03/2024").y(0).build();      
        
		ls.add(to);
		ls.add(to1);
		ls.add(to2);       
        
		to = Datos.builder().id("7").x("15/03/2024").y(4).build(); 
		to1 = Datos.builder().id("8").x("19/03/2024").y(0).build();
		to2 = Datos.builder().id("9").x("20/03/2024").y(0).build();      
        
		ls.add(to);
		ls.add(to1);
		ls.add(to2);  
		
		g1 = Grafico.builder().id("Atendidas").tipo("BAR").data(ls).build();
		gfs.add(g1);
		
		sec = Seccion.builder()
				.title("Rendimiento histórico")
				.order(3)
				.graphics(gfs)
				.tieneEscala("NO")
				.build();

		secciones.add(sec);		


		//----------------
		
		to = Datos.builder().id("1").x("27/02/2024").y(9).build(); 
		to1 = Datos.builder().id("2").x("28/02/2024").y(78).build();
		to2 = Datos.builder().id("3").x("29/02/2024").y(39).build();

		ls = new ArrayList<Datos>();
		ls.add(to);
		ls.add(to1);
		ls.add(to2);		
        
		to = Datos.builder().id("4").x( "01/03/2024").y(88).build(); 
		to1 = Datos.builder().id("5").x("04/03/2024").y(21).build();
		to2 = Datos.builder().id("6").x("12/03/2024").y(15).build();      
        
		ls.add(to);
		ls.add(to1);
		ls.add(to2);       
        
		to = Datos.builder().id("7").x("15/03/2024").y(252).build(); 
		to1 = Datos.builder().id("8").x("19/03/2024").y(66).build();
		to2 = Datos.builder().id("9").x("20/03/2024").y(46).build();      
        
		ls.add(to);
		ls.add(to1);
		ls.add(to2);        
        
		
		g1 = Grafico.builder().id("Horas Hombre").tipo("LINEAL").data(ls).build();
		gfs = new ArrayList<Grafico>();
		gfs.add(g1);
		
		to = Datos.builder().id("1").x("27/02/2024").y(2).build(); 
		to1 = Datos.builder().id("2").x("28/02/2024").y(25).build();
		to2 = Datos.builder().id("3").x("29/02/2024").y(12).build();

		ls = new ArrayList<Datos>();
		ls.add(to);
		ls.add(to1);
		ls.add(to2);		
        
		to = Datos.builder().id("4").x( "01/03/2024").y(30).build(); 
		to1 = Datos.builder().id("5").x("04/03/2024").y(3).build();
		to2 = Datos.builder().id("6").x("12/03/2024").y(80).build();      
        
		ls.add(to);
		ls.add(to1);
		ls.add(to2);       
        
		to = Datos.builder().id("7").x("15/03/2024").y(23).build(); 
		to1 = Datos.builder().id("8").x("19/03/2024").y(15).build();
		to2 = Datos.builder().id("9").x("20/03/2024").y(8).build();      
        
		ls.add(to);
		ls.add(to1);
		ls.add(to2);  
		
		g1 = Grafico.builder().id("Rendimiento").tipo("LINEAL").data(ls).build();
		gfs.add(g1);
		
		int[] leftScale = {0,50,100,150,200,250,300};
		int[] rightScale = {0,10,20,30,40,50,60,70,80,90,100};
		sec = Seccion.builder()
				.title("Rendimiento")
				.order(4)
				.graphics(gfs)
				.tieneEscala("SI")
				.leftScale(leftScale)
				.rightScale(rightScale)
				.build();

		secciones.add(sec);		
		
		
		
		GraficaTO result = GraficaTO.builder().sections(secciones).build();
		
        String destino = "APP";
        String cveDashboard = "MONITOR-COMPLIANCE-REPSE";
        
		DatosAutenticacionTO userSession = DatosAutenticacionTO.builder().cveEntidad(request.getParameter("cveEntidad"))
				.cveLocalidad(request.getParameter("cveLocalidad"))
				.cveIdioma(request.getParameter("cveIdioma"))
				.cveUsuario(request.getParameter("username"))
				.cveIdioma(request.getParameter("cveIdioma"))
				.build();
		
		  log.info("Generando grafico para dashboard {} de destino {}", cveDashboard, destino);
		    DAORet<GraficaTO, RetMsg> dashboard = dashboardHelper.generaGrafico(userSession, destino, cveDashboard, null, true, 1);
		    
		    GraficaTO grafico = dashboard.getContent();
		    if (grafico != null) {
		        log.info("MODFIYING GRAPHICS");
		        int id = 1;
		        for (Seccion seccion : grafico.getSections()) {
		        	dashboardHelper.procesarSeccion(seccion, id++);
		        }
		    }
        
        response = new ResponseEntity<>(grafico, HttpStatus.OK);
		
		log.info("Dashboard generado   --> : {}", grafico);

		
	
		log.trace("<<<<<< RESPONSE Dashboard Operativo ### {}{}operativo \n{}", contextPath, ROOT_URL,
			response);
		return response;
	}

	@Operation(summary = "ListaProceso", description = "Lista de Procesos.")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "OK - Successful operation.",
				content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
						schema = @Schema(description = "Dashboard Operativo", title = "Dashboard Operativo.",
								implementation = Dashboard.class))}),
		@ApiResponse(responseCode = "204",
		description = "No Content - The server successfully processed the request, and is not returning any content.",
		content = {@Content(schema = @Schema(implementation = String.class))}),
		@ApiResponse(responseCode = "400", ref = "BadRequestError"),
		@ApiResponse(responseCode = "401", ref = "UnauthorizedError"),
		@ApiResponse(responseCode = "403", ref = "ForbiddenError"),
		@ApiResponse(responseCode = "404", ref = "NotFoundError"),
		@ApiResponse(responseCode = "405", ref = "MethodNotAllowedError"),
		@ApiResponse(responseCode = "408", ref = "RequestTimeoutError"),
		@ApiResponse(responseCode = "500", description = "Internal Server Error - Processing error.", content = {
			@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = String.class))})
		}
	)
	@SneakyThrows(BrioBPMException.class)
	@GetMapping(value = "listaProcesos", produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> listProcesos(HttpServletRequest request) {	
		ResponseEntity<?> response = null;
		List <ProcesoTO> list = new ArrayList<ProcesoTO>();
		
		log.error(">>>>>> REQUEST APP listaProcesos ### {}{}",
			contextPath, ROOT_URL );// converterJsonAsString(filtros)
		
		log.error("\tENTIDAD: {}", request.getParameter("cveEntidad"));
		log.error("\tLOCALIDAD: {}", request.getParameter("cveLocalidad"));
		log.error("\tIDIOMA: {}", request.getParameter("cveIdioma"));
		log.error("\tUSUARIO: {}", request.getParameter("username"));
		
		String cveUsuario = request.getParameter("username");
		String cveEntidad = request.getParameter("cveEntidad");
		String cveIdioma = request.getParameter("cveIdioma");
		String cveLocalidad = request.getParameter("cveLocalidad");
		
		list = procesoService.getProcesosByUser(cveEntidad, cveUsuario, cveIdioma, cveLocalidad);
		response = new ResponseEntity<>(list, HttpStatus.OK);
		log.error("<<<<<< RESPONSE listaProcesos ### {}{}operativo \n{}", contextPath, ROOT_URL,
			response);
		return response;
	}

	

	@Operation(summary = "listaActividades", description = "Lista de actividades por Autorizar.")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "OK - Successful operation.",
				content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
						schema = @Schema(description = "Dashboard Operativo", title = "Dashboard Operativo.",
								implementation = Dashboard.class))}),
		@ApiResponse(responseCode = "204",
		description = "No Content - The server successfully processed the request, and is not returning any content.",
		content = {@Content(schema = @Schema(implementation = String.class))}),
		@ApiResponse(responseCode = "400", ref = "BadRequestError"),
		@ApiResponse(responseCode = "401", ref = "UnauthorizedError"),
		@ApiResponse(responseCode = "403", ref = "ForbiddenError"),
		@ApiResponse(responseCode = "404", ref = "NotFoundError"),
		@ApiResponse(responseCode = "405", ref = "MethodNotAllowedError"),
		@ApiResponse(responseCode = "408", ref = "RequestTimeoutError"),
		@ApiResponse(responseCode = "500", description = "Internal Server Error - Processing error.", content = {
			@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = String.class))})
		}
	)
	@SneakyThrows(BrioBPMException.class)
	@GetMapping(value = "listaActividades", produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> actividadPorAutorizar(HttpServletRequest request) {	
		ResponseEntity<?> response = null;
		List <ActividadAutorizarTO> list = new ArrayList<ActividadAutorizarTO>();
		
		log.error(">>>>>> REQUEST listaActividades ### {}{}",
			contextPath, ROOT_URL );// converterJsonAsString(filtros)
		
		log.error("\tENTIDAD: {}", request.getParameter("cveEntidad"));
		log.error("\tLOCALIDAD: {}", request.getParameter("cveLocalidad"));
		log.error("\tIDIOMA: {}", request.getParameter("cveIdioma"));
		log.error("\tUSUARIO: {}", request.getParameter("username"));
		log.error("\tCVEPROCESO: {}", request.getParameter("cveProceso"));
		log.error("\tVERSION: {}", request.getParameter("version"));
		
		String cveUsuario = request.getParameter("username");
		String cveEntidad = request.getParameter("cveEntidad");
		String cveIdioma = request.getParameter("cveIdioma");
		String cveLocalidad = request.getParameter("cveLocalidad");
		String cveProceso = request.getParameter("cveProceso");
		Double version = Double.valueOf(request.getParameter("version"));
		
		list = actividadService.getActividadByProcesos(cveEntidad, cveProceso, BigDecimal.valueOf(version), cveUsuario, cveIdioma);
				
		response = new ResponseEntity<>(list, HttpStatus.OK);
		log.error("<<<<<< RESPONSE listaActividades ### {}{} response\n{}", contextPath, ROOT_URL,
			response);
		return response;
	}

	
	@Parameter(name = "dato", description = "Datos del fomulario.", required = true, 
			schema = @Schema(implementation = GuardaTO.class))
	@PostMapping(value = "guardaFormulario")
	public ResponseEntity<?> guardaFormulario(@RequestBody GuardaTO dato, HttpServletRequest request) throws BrioBPMException, ParseException {
		
		log.error(">>>>>> REQUEST guardaFormulario ### {}{}", contextPath, ROOT_URL);// converterJsonAsString(filtros)
		
		/*ResponseEntity<?> response = null;
		
		log.trace(">>>>>> REQUEST actSitActividad ### {}{}operativo \nSession ",
			contextPath, ROOT_URL );// converterJsonAsString(filtros)
		
		log.debug("\tDatos de entrada: {}", dato.getListaRespuestas().size());
						
		response = new ResponseEntity<>("Este es un mensaje que se le mostrará al usuario como resultado del registro.", HttpStatus.OK);
		log.trace("<<<<<< RESPONSE Dashboard Operativo ### {}{}operativo \n{}", contextPath, ROOT_URL,
			response);*/
		
		log.error("**** GUARDA FORMULARIO");
		log.error("\tInformación : {} ", converterJsonAsString (dato.getListaRespuestas()));
		log.error("\tDatos de entrada: {}", dato.getListaRespuestas().size());
		
		ResponseEntity<?> response = null;
		RetMsg meta = new RetMsg(); // Se inicializa meta
		
		DatosAutenticacionTO userSession = DatosAutenticacionTO.builder()
				.cveEntidad(dato.getCveEntidad())
				.cveIdioma(dato.getCveIdioma())
				.cveLocalidad(dato.getCveLocalidad())
				.cveUsuario(dato.getUsername())
				.build();
		
		log.error("userSession: {}", userSession.toString());
		
		NodoTO nodo = NodoTO.builder()
				.cveInstancia(dato.getCveInstancia())
		        .cveProceso(dato.getCveProceso())
		        .version(new BigDecimal(dato.getVersion()))
		        .cveInstancia(dato.getCveInstancia())
		        .cveNodo(dato.getCveNodo())
		        .idNodo(dato.getIdNodo())
		        .secuenciaNodo(dato.getSecNodo())
		        .usoSeccion("APP")
		        .build();
		
		log.error("nodo: {}", nodo.toString());
		
		String cveBoton = dato.getCveBoton();
		
		log.error("cveBoton: {}", cveBoton);
		
		List<RespuestaFormularioTO> datos = new ArrayList<>();

		for (Object item : dato.getListaRespuestas()) {
		    if (item instanceof Map) {
		        Map<String, Object> mapItem = (Map<String, Object>) item;
		        
		        Integer numeroDato = (Integer) mapItem.get("numeroDato");
		        String seccionVariable = (String) mapItem.get("seccionVariable");
		        String tipoControl = (String) mapItem.get("tipoControl");
		        
		        log.info("numeroDato: {}", numeroDato);
		        log.info("seccionVariable: {}", seccionVariable);
		        log.info("tipoControl: {}", tipoControl);
		        

		        Object valorObj = mapItem.get("value"); // Obtener el objeto

		        String valorFinal = null;
		        if (valorObj instanceof Map) { 
		            Map<String, Object> valorMap = (Map<String, Object>) valorObj;
		            valorFinal = (String) valorMap.get("value"); // Extraer el valor real
		        } else if (valorObj instanceof String) {
		            valorFinal = (String) valorObj;
		        }
		        
		        // Construir el DTO
		        RespuestaFormularioTO res = RespuestaFormularioTO.builder()
		                .numeroDato(numeroDato)
		                .secccionVariable(seccionVariable)
		                .tipoControl(tipoControl)
		                .value(valorFinal)
		                .build();

		        datos.add(res);
		    }
		}

		log.error("ENTRARA A SELECT ACCION DONDE cveBoton:{}",cveBoton);
		
		EstatusTO to = appService.seleccionaAccion(userSession, nodo, cveBoton, datos);
		
		log.error("Respuesta: {}", to);
		
		meta.setStatus(to.getTipoExcepcion());
		meta.setMessage(to.getMensaje());	
		
		
		
		/*
		        
		List<Object> datosObjeto = dato.getListaRespuestas();
		log.debug("datosObjeto: {} {}", datosObjeto.size(), datosObjeto.toString());
		
//		 // Convierte cada elemento de `datos` a `DatoGuardar` usando el converter
//        List<DatoGuardar> datos = datosObjeto.stream()
//                .map(IDatoGuardarConverter::convertirObjetoADatoGuardar)
//                .collect(Collectors.toList());
		
		 List<DatoGuardar> datos = new ArrayList<DatoGuardar>();
		 for (Object obj : datosObjeto) {
			 
			 if (obj instanceof DatoGuardar) {
			        DatoGuardar datoCas = (DatoGuardar) obj;

			        // Crear un nuevo objeto DatoGuardar usando el builder
			        DatoGuardar input = DatoGuardar.builder()
			                .seccionVariable(datoCas.getSeccionVariable())
			                .numeroDato(datoCas.getNumeroDato())
			                .tipoControl(datoCas.getTipoControl())
			                .valor(datoCas.getValor()) // Asumiendo que 'valor' es compatible
			                .build();

			        datos.add(input);
				} else {
					log.info("El objeto no es una instancia de DatoGuardar: {}",
							obj != null ? obj.getClass().getName() : "null");
				}
		 }
        
        log.info("datos CONVERTIDOS: {} {}", datos.size() ,datos.toString());
		
		log.trace(">>>>>> REQUEST Formulario Dinamico ### {}{}formulariodinamico \nSession Data:\n{} \nData Request:\n{}",
			contextPath, ROOT_URL, converterJsonAsString(userSession), converterJsonAsString(nodo));
			
		EstatusTO to2 = appService.seleccionaAccion(userSession, nodo, datos, cveBoton);
		log.info("Respuesta: {}", to2);
		*/
		
		//Si la respuesta es OK
		if (meta.getStatus().equalsIgnoreCase(Constants.OK)) {
			response = new ResponseEntity<RetMsg>(meta, HttpStatus.OK);
			
		} else {
			response = new ResponseEntity<RetMsg>(meta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		log.error("<<<<<< RESPONSE End Activity ### {}{}terminaractividad \n{}", contextPath, ROOT_URL, response);
	
		return response;
	} 
	
	
	@Operation(summary = "uploadImage.", description = "Guardar un Documento.")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "201",
			description = "Created - La petición ha sido completada y ha resultado en la creación de un nuevo recurso.",
			content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
				schema = @Schema(implementation = RetMsg.class))}),
		@ApiResponse(responseCode = "400", ref = "BadRequestError"),
		@ApiResponse(responseCode = "401", ref = "UnauthorizedError"),
		@ApiResponse(responseCode = "403", ref = "ForbiddenError"),
		@ApiResponse(responseCode = "404", ref = "NotFoundError"),
		@ApiResponse(responseCode = "405", ref = "MethodNotAllowedError"),
		@ApiResponse(responseCode = "408", ref = "RequestTimeoutError"),
		@ApiResponse(responseCode = "500", description = "Internal Server Error - Processing error.",
			content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
				schema = @Schema(implementation = RetMsg.class))})
	})
	@Parameter(name = "document", description = "Datos del Documento.", required = true, 
		schema = @Schema(implementation = DocumentoAppTO.class))
	@SneakyThrows(BrioBPMException.class)
	@PostMapping(value = "uploadImage", produces = MediaType.APPLICATION_JSON_VALUE, consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> uploadImage(@RequestBody DocumentoAppTO document, HttpServletRequest request) {
		ResponseEntity<?> response = null;
		RetMsg meta = null;
		log.error(">>>>>> REQUEST Guardar Documento ### {}{}uploadImage \nRequest Data:\n{}",
				contextPath, ROOT_URL, document.getDocumentos().size());
			
		log.error("----> Guardando Documento: {}", converterJsonAsString(document));


		DatosAutenticacionTO session = DatosAutenticacionTO.builder()
					.cveEntidad(document.getCveEntidad())
					.cveLocalidad(document.getCveLocalidad())
					.cveIdioma(document.getCveIdioma())
					.cveUsuario(document.getUsername()).build();
		
		/*DatosAutenticacionTO session = DatosAutenticacionTO.builder()
				.cveEntidad("BRIOMAX")
				.cveLocalidad("BRIOMAX-CENTRAL")
				.cveIdioma("ES-MX")
				.cveUsuario("AIT.Emilio.Cardenas").build();*/
			
		EstatusTO image = appService.uploadImageService(session , document);
			
		log.error("                {}", image);
		log.error("<<<<<< RESPONSE Guardar Documento: {} ", image);
		return new ResponseEntity<>("Imagenes subidas exitosamente", HttpStatus.OK);
	}	
    
	

	
	
	@Operation(summary = "datosActividad", description = "Detalle del formulario de la actividad Dinamico.")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "OK - Successful operation.",
				content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
						schema = @Schema(description = "Dashboard Operativo", title = "Dashboard Operativo.",
								implementation = Dashboard.class))}),
		@ApiResponse(responseCode = "204",
		description = "No Content - The server successfully processed the request, and is not returning any content.",
		content = {@Content(schema = @Schema(implementation = String.class))}),
		@ApiResponse(responseCode = "400", ref = "BadRequestError"),
		@ApiResponse(responseCode = "401", ref = "UnauthorizedError"),
		@ApiResponse(responseCode = "403", ref = "ForbiddenError"),
		@ApiResponse(responseCode = "404", ref = "NotFoundError"),
		@ApiResponse(responseCode = "405", ref = "MethodNotAllowedError"),
		@ApiResponse(responseCode = "408", ref = "RequestTimeoutError"),
		@ApiResponse(responseCode = "500", description = "Internal Server Error - Processing error.", content = {
			@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = String.class))})
		}
	)
	@SneakyThrows(BrioBPMException.class)
	@GetMapping(value = "datosActividad", produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> detActividadDinamico(HttpServletRequest request) {	
		log.error(">>>>>> REQUEST detActividadDinamico ### {}{}", contextPath, ROOT_URL);

		log.info("----->datosActividad");
		
		log.error("\tENTIDAD: {}", request.getParameter("cveEntidad"));
		log.error("\tLOCALIDAD: {}", request.getParameter("cveLocalidad"));
		log.error("\tIDIOMA: {}", request.getParameter("cveIdioma"));
		log.error("\tUSUARIO: {}", request.getParameter("username"));
		
		log.error("\tcveProceso: {}", request.getParameter("cveProceso"));
		log.error("\tcveInstanciao: {}", request.getParameter("cveInstancia"));
		log.error("\tcveNodo: {}", request.getParameter("cveNodo"));
		log.error("\tidNodo: {}", request.getParameter("idNodo"));
		log.error("\tsecNodo: {}", request.getParameter("secNodo"));
		log.error("\tversion: {}", request.getParameter("verProceso"));

		String cveUsuario = request.getParameter("username");
		String cveEntidad = request.getParameter("cveEntidad");
		String cveIdioma = request.getParameter("cveIdioma");
		String cveLocalidad = request.getParameter("cveLocalidad");
		String cveProceso = request.getParameter("cveProceso");
		
		String cveNodo = request.getParameter("cveNodo");
		String idNodoS = request.getParameter("idNodo");
		String secNodoS = request.getParameter("secNodo");
		String versionS = request.getParameter("verProceso");
		if (versionS.equals(null) || versionS.isEmpty()) {
			versionS = "1.0";
		}
		Double version = Double.valueOf(versionS.strip());
		
		String cveInstancia = request.getParameter("cveInstancia");
		Integer idNodo = Integer.parseInt(idNodoS);
		Integer secNodo = Integer.parseInt(secNodoS);
		
		ResponseEntity<?> response = null;
		RetMsg meta = null;
		
		DatosAutenticacionTO userSession = DatosAutenticacionTO.builder()
				.cveEntidad(cveEntidad)
				.cveIdioma(cveIdioma)
				.cveLocalidad(cveLocalidad)
				.cveUsuario(cveUsuario.trim())
				.build();
		
		ActividadTO to2 = null;
		EstatusCreaInstanciaTO metaData = null;
		if ("0".equals(request.getParameter("cveInstancia"))) {

			metaData = areaTrabajoService.inicioProcesoApp(userSession, cveProceso,
					cveNodo, idNodo, secNodo, versionS);
			cveInstancia = metaData.getCveInstancia();
			secNodo = metaData.getSecuenciaNodoActividad();
			if (metaData.equals("ERROR")) {
				return response = new ResponseEntity<>(to2, HttpStatus.OK);
			}
		}		
		
		NodoTO nodo = NodoTO.builder()
				.cveEntidad(request.getParameter("cveEntidad"))
		        .cveProceso(cveProceso)
		        .version(BigDecimal.valueOf(version))
		        .cveInstancia(cveInstancia)
		        .idNodo(idNodo)
		        .secuenciaNodo(secNodo)
		        .cveNodo(cveNodo)
		        .usoSeccion("APP")
		        .build();

		log.error(">>>>>> REQUEST detActividadDinamico ### {}{}formulariodinamico \nSession Data:\n{} \nData Request:\n{}",
			contextPath, ROOT_URL, converterJsonAsString(userSession), converterJsonAsString(nodo));
	
		
		try {
			to2 = appService.obtenerFormularioDinamicoMovil(userSession, nodo);
		} catch (BrioBPMException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.error(">>>>>>ActividadTO ### formulariodinamico\n{}", converterJsonAsString(to2));
		response = new ResponseEntity<>(to2, HttpStatus.OK);
		log.error("<<<<<< RESPONSE detActividadDinamico ### {}{}operativo \n{}", contextPath, ROOT_URL,
			response);
		return response;
	}
	
	
	
}
