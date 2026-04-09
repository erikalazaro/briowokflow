package com.briomax.briobpm.rest.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briomax.briobpm.business.helpers.base.IDashboardRepseHelper;
import com.briomax.briobpm.business.service.catadaptaciones.CrAccesoUsuarioService;
import com.briomax.briobpm.business.service.catadaptaciones.CrConsultaRepseService;
import com.briomax.briobpm.business.service.catadaptaciones.core.ICrConsultaRepseService;
import com.briomax.briobpm.business.service.catalogs.CrDefinicionPeriodicaService;
import com.briomax.briobpm.business.service.catalogs.CrProcesoPeriodicoService;
import com.briomax.briobpm.business.service.catalogs.core.IInVariableProcesoService;
import com.briomax.briobpm.business.service.catalogs.core.IMessagesService;
import com.briomax.briobpm.business.service.core.IEliminarInformacionService;
import com.briomax.briobpm.common.core.Constants;
import com.briomax.briobpm.common.core.DAORet;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.transferobjects.CargaGridTO;
import com.briomax.briobpm.transferobjects.ComboBoxTO;
import com.briomax.briobpm.transferobjects.PdfGridTO;
import com.briomax.briobpm.transferobjects.app.Datos;
import com.briomax.briobpm.transferobjects.app.GraficaTO;
import com.briomax.briobpm.transferobjects.app.Grafico;
import com.briomax.briobpm.transferobjects.app.Seccion;
import com.briomax.briobpm.transferobjects.in.CrConsultaRepseTO;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;
import com.briomax.briobpm.transferobjects.in.DatosContratista;
import com.briomax.briobpm.transferobjects.in.DatosContrato;
import com.briomax.briobpm.transferobjects.repse.CorreoTO;
import com.briomax.briobpm.transferobjects.repse.CrUsuarioTO;
import com.briomax.briobpm.transferobjects.repse.FiltroAdaptacionesTO;
import com.briomax.briobpm.transferobjects.repse.FiltroValidaRepseTO;
import com.briomax.briobpm.transferobjects.repse.GuardaCorreoTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Class RepseController.java es ...
 * 
 * @author Sara Ventura
 * @version 1.0 Fecha de creacion May 15, 2024 6:12:03 PM Modificaciones:
 * @since JDK 1.8
 */
@Tag(name = "Reportes Operativos", description = "Reportes.")
@RestController
@RequestMapping(RepseController.ROOT_URL)
@Slf4j
public class RepseController extends AbstractBbpmController {

	/** Constante para la url del servicio. */
	public static final String ROOT_URL = "/services/repse/";

	/** El atributo o variable reporte helper. */
	@Autowired
	private CrConsultaRepseService repseService;

	/** El atributo o variable messages service. */
	@Autowired
	private IMessagesService messagesService;
	
	@Autowired
	private IInVariableProcesoService inProcesoService;
	
	@Autowired
	private IDashboardRepseHelper dashboardRepseHelper;
	
	@Autowired
	private CrAccesoUsuarioService crAccesoUsuarioService;
	
	@Autowired
	private CrProcesoPeriodicoService crProcesoPeriodicoService;
	
	/** El atributo o variable messages service. */
	@Autowired
	private ICrConsultaRepseService crConsultaRepseService;
	
	@Autowired
	private CrDefinicionPeriodicaService definicionPeriodicaService;
	
	@Autowired
	private IEliminarInformacionService eliminarInformacionService;
	/**
	 * Procesos.
	 * 
	 * @return el response entity.
	 */
	@Operation(summary = "Repse", description = "Obtiene registros repse.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK - Successful operation.", content = {
			@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(description = "Procesos Consulta Repse", title = "Cr Consulta Repse.", implementation = CrConsultaRepseTO.class))) }),
			@ApiResponse(responseCode = "204", description = "No Content - The server successfully processed the request, and is not returning any content.", content = {
					@Content(schema = @Schema(implementation = String.class)) }),
			@ApiResponse(responseCode = "400", ref = "BadRequestError"),
			@ApiResponse(responseCode = "401", ref = "UnauthorizedError"),
			@ApiResponse(responseCode = "403", ref = "ForbiddenError"),
			@ApiResponse(responseCode = "404", ref = "NotFoundError"),
			@ApiResponse(responseCode = "405", ref = "MethodNotAllowedError"),
			@ApiResponse(responseCode = "408", ref = "RequestTimeoutError"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error - Processing error.", content = {
					@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = String.class)) }) })
	@GetMapping(value = "getAll", produces = MediaType.APPLICATION_JSON_VALUE, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> obtenerTodos() {
		ResponseEntity<?> response = null;
		DatosAutenticacionTO userSession = getUserSessionBpm();
		log.trace(">>>>>> REQUEST Obtener Procesos x versiones ### {}{}/versiones \nSession Data:\n{} ", contextPath,
				ROOT_URL, converterJsonAsString(userSession));
		List<CrConsultaRepseTO> list = repseService.consultaTodosRegistros();
		if (list.isEmpty()) {
			response = new ResponseEntity<>(messagesService.getMessage(userSession, "CATALOGO_REPSE",
					"MANTENIMIENTO_PROCESO_CONSULTAR_VACIA", ""), HttpStatus.NO_CONTENT); // CREAR EN BASE
		} else {
			response = new ResponseEntity<>(list, HttpStatus.OK);
		}
		log.trace("<<<<<< RESPONSE Obtener Procesos x versiones ### {}{}/versiones \n{}", contextPath, ROOT_URL,
				response);
		return response;
	}	
	
	// MOCK 1
	@Operation(operationId = "usuariosall", summary = "Usuarios.", description = "Obtener el catalogo de Usuarios de la Entidad en Sesion.")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "OK - Successful operation..",
			content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
				schema = @Schema(implementation = GraficaTO.class))
		}),
		@ApiResponse(responseCode = "204",
			description = "No Content - The server successfully processed the request, and is not returning any content.",
					content = {@Content(schema = @Schema(implementation = String.class))}),
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
	@SneakyThrows(BrioBPMException.class)
	@GetMapping(value = {"dashboardMonitorInicialMock"}, produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> dashboardMonitorInicialMock() {	//, @RequestBody SolicitudAppTO filtros
		ResponseEntity<?> response = null;
		//DatosAutenticacionTO userSession = getUserSessionBpm();
		log.trace(">>>>>> REQUEST Dashboard Operativo ### {}{}operativo \nSession ",
			contextPath, ROOT_URL );// converterJsonAsString(filtros)
		
        
        // Crear datos simulados
        List<Datos> datos1 = List.of(
                Datos.builder().id("1").x("Total Contratistas").y(40).build(),
                Datos.builder().id("2").x("Contratistas a Tiempo").y(4).build()
        );
        
        // Crear datos simulados
        
        List<Datos> datos2 = List.of(
                Datos.builder().id("1").x("Vencimiento mayor a 1 mes").y(3).build()
        );

        List<Datos> datos3 = List.of(
                Datos.builder().id("1").x("Vencimiento de 15 a 29 días").y(3).build()
        );

        List<Datos> datos4 = List.of(
                Datos.builder().id("1").x("Vencimiento de 1 a 14 días").y(3).build()
        );

        List<Datos> datos5 = List.of(
                Datos.builder().id("1").x("Vencidos").y(3).build()
        );

        List<Datos> datos6 = List.of(
                Datos.builder().id("1").x("Total registros REPSE").y(2).build(),
                Datos.builder().id("2").x("Registros REPSE Vigentes").y(1).build()
        );
        
        // Crear gráficos simulados
        List<Grafico> graficos1 = List.of(
        		Grafico.builder().id("1|1").tipo("CIRCULAR").labels(List.of("Cumplimiento de Contratistas").toArray(new String[0])).data(datos1).build()
        );
        
        List<Grafico> graficos2 = List.of(
                Grafico.builder().id("2|1").tipo("CIRCULAR").labels(List.of("Vencimiento mayor a 1 Mes").toArray(new String[0])).data(datos2).build()
        );
        
        List<Grafico> graficos3 = List.of(
                Grafico.builder().id("2|2").tipo("CIRCULAR").labels(List.of("Vencimiento de 15 días y menos de 1 Mes").toArray(new String[0])).data(datos3).build()
        );

        List<Grafico> graficos4 = List.of(
               
                Grafico.builder().id("2|3").tipo("CIRCULAR").labels(List.of("Vencimiento de 1 a 14 días").toArray(new String[0])).data(datos4).build()
        );

        List<Grafico> graficos5 = List.of(
                Grafico.builder().id("2|4").tipo("CIRCULAR").labels(List.of("Vencidos").toArray(new String[0])).data(datos5).build()
        );

        List<Grafico> graficos6 = List.of(
                Grafico.builder().id("3|1").tipo("BARRAS").labels(List.of("Registros REPSE").toArray(new String[0])).data(datos6).build()
        );

        // Crear sección simulada
        Seccion seccion1 = Seccion.builder()
                .title("Cumplimiento Global")
                .subTitle("Périodo anterior de Cumplimiento")
                .order(1)
                .tieneEscala("NO")
                .rightScale(null)
                .leftScale(null)
                .graphics(graficos1)
                .build();
       
        Seccion seccion2 = Seccion.builder()
                .title("Cumplimiento Global")
                .subTitle("Périodo actual de Cumplimiento")
                .order(2)
                .tieneEscala("NO")
                .rightScale(null)
                .leftScale(null)
                .graphics(graficos2)
                .build();
        
        Seccion seccion3 = Seccion.builder()
                .title("Cumplimiento Global")
                .subTitle("Périodo actual de Cumplimiento")
                .order(2)
                .tieneEscala("NO")
                .rightScale(null)
                .leftScale(null)
                .graphics(graficos3)
                .build();
        
        Seccion seccion4 = Seccion.builder()
                .title("Cumplimiento Global")
                .subTitle("Périodo actual de Cumplimiento")
                .order(2)
                .tieneEscala("NO")
                .rightScale(null)
                .leftScale(null)
                .graphics(graficos4)
                .build();
        
        Seccion seccion5 = Seccion.builder()
                .title("Cumplimiento Global")
                .subTitle("Périodo anterior de Cumplimiento")
                .order(2)
                .tieneEscala("NO")
                .rightScale(null)
                .leftScale(null)
                .graphics(graficos5)
                .build();
        
        Seccion seccion6 = Seccion.builder()
                .title("Cumplimiento Global")
                .subTitle("Vigencia REPSE")
                .order(3)
                .tieneEscala("NO")
                .rightScale(null)
                .leftScale(null)
                .graphics(graficos6)
                .build();

        // Crear lista de secciones
        List<Seccion> secciones = new ArrayList<Seccion>();
        secciones.add(seccion1);
        secciones.add(seccion2);
        secciones.add(seccion3);
        secciones.add(seccion4);
        secciones.add(seccion5);
        secciones.add(seccion6);
        
        // Crear objeto GraficoTO
        GraficaTO result = GraficaTO.builder()
                .sections(secciones)
                .build();

		response = new ResponseEntity<>(result, HttpStatus.OK);

		log.trace("<<<<<< RESPONSE Dashboard Operativo ### {}{}operativo \n{}", contextPath, ROOT_URL,
			response);
		return response;
	}
	
	// MOCK 2
	@Operation(summary = "Dashboard Cumplimiento por Contratista", description = "Dashboard Cumplimiento por Contratista.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK - Successful operation.", content = {
			@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(description = "Lista", title = "Lista de Contratos por RFC.", implementation = DatosContratista.class))) }),
			@ApiResponse(responseCode = "204", description = "No Content - The server successfully processed the request, and is not returning any content.", content = {
					@Content(schema = @Schema(implementation = String.class)) }),
			@ApiResponse(responseCode = "400", ref = "BadRequestError"),
			@ApiResponse(responseCode = "401", ref = "UnauthorizedError"),
			@ApiResponse(responseCode = "403", ref = "ForbiddenError"),
			@ApiResponse(responseCode = "404", ref = "NotFoundError"),
			@ApiResponse(responseCode = "405", ref = "MethodNotAllowedError"),
			@ApiResponse(responseCode = "408", ref = "RequestTimeoutError"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error - Processing error.", content = {
					@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = String.class)) }) })

	@GetMapping(value = "dashboardCumplimientoPorContratistaMock", produces = MediaType.APPLICATION_JSON_VALUE,
	        consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> dashboardCumplimientoPorContratistaMock() {
	    log.trace(">>>>>> REQUEST Dashboard Cumplimiento por Contratista ### {}{}cumplimientoPorContratista \nSession ",
	            contextPath, ROOT_URL);

	    ResponseEntity<?> response = null;
	    
	    // Crear datos simulados
        List<Datos> datos1 = List.of(
                Datos.builder().id("1").x("Total Contratistas").y(40).build(),
                Datos.builder().id("2").x("Contratistas a Tiempo").y(4).build()
        );
        
        // Crear datos simulados
        
        List<Datos> datos2 = List.of(
                Datos.builder().id("1").x("Vencimiento mayor a 1 mes").y(3).build()
        );

        List<Datos> datos3 = List.of(
                Datos.builder().id("1").x("Vencimiento de 15 a 29 días").y(3).build()
        );

        List<Datos> datos4 = List.of(
                Datos.builder().id("1").x("Vencimiento de 1 a 14 días").y(3).build()
        );

        List<Datos> datos5 = List.of(
                Datos.builder().id("1").x("Vencidos").y(3).build()
        );

        List<Datos> datos6 = List.of(
                Datos.builder().id("1").x("Total registros REPSE").y(2).build(),
                Datos.builder().id("2").x("Registros REPSE Vigentes").y(1).build()
        );
        
        List<Grafico> graficos1 = List.of(
        		Grafico.builder().id("1|1").tipo("CIRCULAR").labels(List.of("Cumplimiento de Contratistas").toArray(new String[0])).data(datos1).build()
        );

	    List<Grafico> graficos2 = List.of(
	            Grafico.builder().id("2|1").tipo("CIRCULAR").labels(List.of("Vencimiento mayor a 1 Mes").toArray(new String[0])).data(datos2).build()
	    );

	    List<Grafico> graficos3 = List.of(
	            Grafico.builder().id("2|2").tipo("CIRCULAR").labels(List.of("Vencimiento de 15 días y menos de 1 Mes").toArray(new String[0])).data(datos3).build()
	    );

	    List<Grafico> graficos4 = List.of(
	            Grafico.builder().id("2|3").tipo("CIRCULAR").labels(List.of("Vencimiento de 1 a 14 días").toArray(new String[0])).data(datos4).build()
	    );

	    List<Grafico> graficos5 = List.of(
	            Grafico.builder().id("2|4").tipo("CIRCULAR").labels(List.of("Vencidos").toArray(new String[0])).data(datos5).build()
	    );

	    List<Grafico> graficos6 = List.of(
	            Grafico.builder().id("3|1").tipo("BARRAS").labels(List.of("Registros REPSE").toArray(new String[0])).data(datos6).build()
	    );

        // Crear sección simulada
        Seccion seccion1 = Seccion.builder()
                .title("Cumplimiento Global")
                .subTitle("Périodo anterior de Cumplimiento")
                .order(1)
                .tieneEscala("NO")
                .rightScale(null)
                .leftScale(null)
                .graphics(graficos1)
                .build();
       
        Seccion seccion2 = Seccion.builder()
                .title("Cumplimiento Global")
                .subTitle("Périodo actual de Cumplimiento")
                .order(2)
                .tieneEscala("NO")
                .rightScale(null)
                .leftScale(null)
                .graphics(graficos2)
                .build();
        
        Seccion seccion3 = Seccion.builder()
                .title("Cumplimiento Global")
                .subTitle("Périodo actual de Cumplimiento")
                .order(2)
                .tieneEscala("NO")
                .rightScale(null)
                .leftScale(null)
                .graphics(graficos3)
                .build();
        
        Seccion seccion4 = Seccion.builder()
                .title("Cumplimiento Global")
                .subTitle("Périodo actual de Cumplimiento")
                .order(2)
                .tieneEscala("NO")
                .rightScale(null)
                .leftScale(null)
                .graphics(graficos4)
                .build();
        
        Seccion seccion5 = Seccion.builder()
                .title("Cumplimiento Global")
                .subTitle("Périodo anterior de Cumplimiento")
                .order(2)
                .tieneEscala("NO")
                .rightScale(null)
                .leftScale(null)
                .graphics(graficos5)
                .build();
        
        Seccion seccion6 = Seccion.builder()
                .title("Cumplimiento Global")
                .subTitle("Vigencia REPSE")
                .order(3)
                .tieneEscala("NO")
                .rightScale(null)
                .leftScale(null)
                .graphics(graficos6)
                .build();

        // Crear lista de secciones
        List<Seccion> secciones = new ArrayList<Seccion>();
        secciones.add(seccion1);
        secciones.add(seccion2);
        secciones.add(seccion3);
        secciones.add(seccion4);
        secciones.add(seccion5);
        secciones.add(seccion6);

	    DatosContratista item = DatosContratista.builder()
	    		.razonSocial("Contratista 1 svm")
	    		.sections(secciones)
	    		.build();

	    DatosContratista item2 = DatosContratista.builder()
	    		.razonSocial("Contratista 2 svm")
	    		.sections(secciones)
	    		.build();
	    

	    List<DatosContratista> result = new ArrayList<>();
	    
	    result.add(item);
	    result.add(item2);

	    // Crear respuesta con tipo específico
	    response = new ResponseEntity<>(result, HttpStatus.OK);

	    log.trace("<<<<<< RESPONSE Dashboard Cumplimiento por Contratista ### {}{}cumplimientoPorContratista \n{}", contextPath, ROOT_URL, response);

	    return response;
	}

	// MOCK 3
	@Operation(operationId = "dashboard Por Contrato", summary = "dashboardPorContrato.", description = "dashboard Por Contrato.")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "OK - Successful operation..",
			content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
				schema = @Schema(implementation = GraficaTO.class))
		}),
		@ApiResponse(responseCode = "204",
			description = "No Content - The server successfully processed the request, and is not returning any content.",
					content = {@Content(schema = @Schema(implementation = String.class))}),
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
	@SneakyThrows(BrioBPMException.class)
	@PostMapping(value = {"dashboardPorContratoMock"}, produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> dashboardPorContratoMock() {	//, @RequestBody SolicitudAppTO filtros
		ResponseEntity<?> response = null;
		//DatosAutenticacionTO userSession = getUserSessionBpm();
		log.trace(">>>>>> REQUEST Dashboard Operativo ### {}{}operativo \nSession ",
			contextPath, ROOT_URL );// converterJsonAsString(filtros)
		
		
		DatosAutenticacionTO session = getUserSessionBpm();
		
		        
        // Crear datos simulados
        List<Datos> datos1 = List.of(
                Datos.builder().id("1").x("Total Contratistas").y(40).build(),
                Datos.builder().id("2").x("Contratistas a Tiempo").y(4).build()
        );
        
        // Crear datos simulados
        
        List<Datos> datos2 = List.of(
                Datos.builder().id("1").x("Vencimiento mayor a 1 mes").y(3).build()
        );

        List<Datos> datos3 = List.of(
                Datos.builder().id("1").x("Vencimiento de 15 a 29 días").y(3).build()
        );

        List<Datos> datos4 = List.of(
                Datos.builder().id("1").x("Vencimiento de 1 a 14 días").y(3).build()
        );

        List<Datos> datos5 = List.of(
                Datos.builder().id("1").x("Vencidos").y(3).build()
        );

        List<Datos> datos6 = List.of(
                Datos.builder().id("1").x("Total registros REPSE").y(2).build(),
                Datos.builder().id("2").x("Registros REPSE Vigentes").y(1).build()
        );
        
        // Crear gráficos simulados
        List<Grafico> graficos1 = List.of(
        		Grafico.builder().id("1|1").tipo("CIRCULAR").labels(List.of("Cumplimiento de Contratistas").toArray(new String[0])).data(datos1).build()
        );
        
        List<Grafico> graficos2 = List.of(
                Grafico.builder().id("2|1").tipo("CIRCULAR").labels(List.of("Vencimiento mayor a 1 Mes").toArray(new String[0])).data(datos2).build()
        );
        
        List<Grafico> graficos3 = List.of(
                Grafico.builder().id("2|2").tipo("CIRCULAR").labels(List.of("Vencimiento de 15 días y menos de 1 Mes").toArray(new String[0])).data(datos3).build()
        );

        List<Grafico> graficos4 = List.of(
               
                Grafico.builder().id("2|3").tipo("CIRCULAR").labels(List.of("Vencimiento de 1 a 14 días").toArray(new String[0])).data(datos4).build()
        );

        List<Grafico> graficos5 = List.of(
                Grafico.builder().id("2|4").tipo("CIRCULAR").labels(List.of("Vencidos").toArray(new String[0])).data(datos5).build()
        );

       /* List<Grafico> graficos6 = List.of(
                Grafico.builder().id("3|1").tipo("BARRAS").labels(List.of("Registros REPSE").toArray(new String[0])).data(datos6).build()
        );*/

        // Crear sección simulada
        Seccion seccion1 = Seccion.builder()
                .title("Cumplimiento Global")
                .subTitle("Périodo anterior de Cumplimiento")
                .order(1)
                .tieneEscala("NO")
                .rightScale(null)
                .leftScale(null)
                .graphics(graficos1)
                .build();
       
        Seccion seccion2 = Seccion.builder()
                .title("Cumplimiento Global")
                .subTitle("Périodo actual de Cumplimiento")
                .order(2)
                .tieneEscala("NO")
                .rightScale(null)
                .leftScale(null)
                .graphics(graficos2)
                .build();
        
        Seccion seccion3 = Seccion.builder()
                .title("Cumplimiento Global")
                .subTitle("Périodo actual de Cumplimiento")
                .order(2)
                .tieneEscala("NO")
                .rightScale(null)
                .leftScale(null)
                .graphics(graficos3)
                .build();
        
        Seccion seccion4 = Seccion.builder()
                .title("Cumplimiento Global")
                .subTitle("Périodo actual de Cumplimiento")
                .order(2)
                .tieneEscala("NO")
                .rightScale(null)
                .leftScale(null)
                .graphics(graficos4)
                .build();
        
        Seccion seccion5 = Seccion.builder()
                .title("Cumplimiento Global")
                .subTitle("Périodo anterior de Cumplimiento")
                .order(2)
                .tieneEscala("NO")
                .rightScale(null)
                .leftScale(null)
                .graphics(graficos5)
                .build();
        
        /*Seccion seccion6 = Seccion.builder()
                .title("Cumplimiento Global")
                .subTitle("Vigencia REPSE")
                .order(3)
                .tieneEscala("NO")
                .rightScale(null)
                .leftScale(null)
                .graphics(graficos6)
                .build();*/

        // Crear lista de secciones
        List<Seccion> secciones = new ArrayList<Seccion>();
        secciones.add(seccion1);
        secciones.add(seccion2);
        secciones.add(seccion3);
        secciones.add(seccion4);
        secciones.add(seccion5);
        //secciones.add(seccion6);
        
        // Crear objeto GraficoTO
        GraficaTO result = GraficaTO.builder()
                .sections(secciones)
                .build();

		response = new ResponseEntity<>(result, HttpStatus.OK);

		log.trace("<<<<<< RESPONSE Dashboard Operativo ### {}{}operativo \n{}", contextPath, ROOT_URL,
			response);
		return response;
	}


	
	@Operation(summary = "Obtiene los contratos de cada RFC", description = "Obtiene los contratos por RFC.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK - Successful operation.", content = {
			@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(description = "Lista", title = "Lista de Contratos por RFC.", implementation = DatosContrato.class))) }),
			@ApiResponse(responseCode = "204", description = "No Content - The server successfully processed the request, and is not returning any content.", content = {
					@Content(schema = @Schema(implementation = String.class)) }),
			@ApiResponse(responseCode = "400", ref = "BadRequestError"),
			@ApiResponse(responseCode = "401", ref = "UnauthorizedError"),
			@ApiResponse(responseCode = "403", ref = "ForbiddenError"),
			@ApiResponse(responseCode = "404", ref = "NotFoundError"),
			@ApiResponse(responseCode = "405", ref = "MethodNotAllowedError"),
			@ApiResponse(responseCode = "408", ref = "RequestTimeoutError"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error - Processing error.", content = {
					@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = String.class)) }) })
	@GetMapping(value = "getContratosByRFC", produces = MediaType.APPLICATION_JSON_VALUE, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> obtieneDatosContratos() throws ParseException {	//, @RequestBody SolicitudAppTO filtros
		ResponseEntity<?> response = null;
		//DatosAutenticacionTO userSession = getUserSessionBpm();
		log.trace(">>>>>> REQUEST Dashboard Operativo ### {}{}operativo \nSession ",
			contextPath, ROOT_URL );// converterJsonAsString(filtros)
		
		DatosAutenticacionTO session = getUserSessionBpm();

			
		
		List<DatosContrato> result = new ArrayList<DatosContrato>();
				//dashboardRepseHelper.obtieneContratosPorRFC(session);
		
		for(DatosContrato grafica : result) {
			
			
			 // Crear datos simulados
	        List<Datos> datos1 = List.of(
	                Datos.builder().id("1").x("Total Contratistas").y(40).build(),
	                Datos.builder().id("2").x("Contratistas a Tiempo").y(4).build()
	        );
	        
	        // Crear datos simulados
	        
	        List<Datos> datos2 = List.of(
	                Datos.builder().id("1").x("Vencimiento mayor a 1 mes").y(3).build()
	        );

	        List<Datos> datos3 = List.of(
	                Datos.builder().id("1").x("Vencimiento de 15 a 29 días").y(3).build()
	        );

	        List<Datos> datos4 = List.of(
	                Datos.builder().id("1").x("Vencimiento de 1 a 14 días").y(3).build()
	        );

	        List<Datos> datos5 = List.of(
	                Datos.builder().id("1").x("Vencidos").y(3).build()
	        );

	        List<Datos> datos6 = List.of(
	                Datos.builder().id("1").x("Total registros REPSE").y(2).build(),
	                Datos.builder().id("2").x("Registros REPSE Vigentes").y(1).build()
	        );
	        
	        // Crear gráficos simulados
	        List<Grafico> graficos1 = List.of(
	        		Grafico.builder().id("1|1").tipo("CIRCULAR").labels(List.of("Cumplimiento de Contratistas").toArray(new String[0])).data(datos1).build()
	        );
	        
	        List<Grafico> graficos2 = List.of(
	                Grafico.builder().id("2|1").tipo("CIRCULAR").labels(List.of("Vencimiento mayor a 1 Mes").toArray(new String[0])).data(datos2).build()
	        );
	        
	        List<Grafico> graficos3 = List.of(
	                Grafico.builder().id("2|2").tipo("CIRCULAR").labels(List.of("Vencimiento de 15 días y menos de 1 Mes").toArray(new String[0])).data(datos3).build()
	        );

	        List<Grafico> graficos4 = List.of(
	               
	                Grafico.builder().id("2|3").tipo("CIRCULAR").labels(List.of("Vencimiento de 1 a 14 días").toArray(new String[0])).data(datos4).build()
	        );

	        List<Grafico> graficos5 = List.of(
	                Grafico.builder().id("2|4").tipo("CIRCULAR").labels(List.of("Vencidos").toArray(new String[0])).data(datos5).build()
	        );

	        List<Grafico> graficos6 = List.of(
	                Grafico.builder().id("3|1").tipo("BARRAS").labels(List.of("Registros REPSE").toArray(new String[0])).data(datos6).build()
	        );

	        // Crear sección simulada
	        Seccion seccion1 = Seccion.builder()
	                .title("Cumplimiento Global")
	                .subTitle("Périodo anterior de Cumplimiento")
	                .order(1)
	                .tieneEscala("NO")
	                .rightScale(null)
	                .leftScale(null)
	                .graphics(graficos1)
	                .build();
	       
	        Seccion seccion2 = Seccion.builder()
	                .title("Cumplimiento Global")
	                .subTitle("Périodo actual de Cumplimiento")
	                .order(2)
	                .tieneEscala("NO")
	                .rightScale(null)
	                .leftScale(null)
	                .graphics(graficos2)
	                .build();
	        
	        Seccion seccion3 = Seccion.builder()
	                .title("Cumplimiento Global")
	                .subTitle("Périodo actual de Cumplimiento")
	                .order(2)
	                .tieneEscala("NO")
	                .rightScale(null)
	                .leftScale(null)
	                .graphics(graficos3)
	                .build();
	        
	        Seccion seccion4 = Seccion.builder()
	                .title("Cumplimiento Global")
	                .subTitle("Périodo actual de Cumplimiento")
	                .order(2)
	                .tieneEscala("NO")
	                .rightScale(null)
	                .leftScale(null)
	                .graphics(graficos4)
	                .build();
	        
	        Seccion seccion5 = Seccion.builder()
	                .title("Cumplimiento Global")
	                .subTitle("Périodo anterior de Cumplimiento")
	                .order(2)
	                .tieneEscala("NO")
	                .rightScale(null)
	                .leftScale(null)
	                .graphics(graficos5)
	                .build();
	        
	        Seccion seccion6 = Seccion.builder()
	                .title("Cumplimiento Global")
	                .subTitle("Vigencia REPSE")
	                .order(3)
	                .tieneEscala("NO")
	                .rightScale(null)
	                .leftScale(null)
	                .graphics(graficos6)
	                .build();

	        // Crear lista de secciones
	        List<Seccion> secciones = new ArrayList<Seccion>();
	        secciones.add(seccion1);
	        secciones.add(seccion2);
	        secciones.add(seccion3);
	        secciones.add(seccion4);
	        secciones.add(seccion5);
	        secciones.add(seccion6);
	        
	        // Crear objeto GraficoTO
	        GraficaTO grafic = GraficaTO.builder()
	                .sections(secciones)
	                .build();
	        
	        grafica.setGraficas(grafic);	        
		}
		
		
		response = new ResponseEntity<>(result, HttpStatus.OK);

		log.trace("<<<<<< RESPONSE Dashboard ### {}{}operativo \n{}", contextPath, ROOT_URL,
			response);
		return response;
	}
	
	
	@Operation(operationId = "obtiene monitor", summary = "obtiene monitor.", description = "Obtener la estructura del monitor.")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "OK - Successful operation..",
			content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
				schema = @Schema(implementation = GraficaTO.class))
		}),
		@ApiResponse(responseCode = "204",
			description = "No Content - The server successfully processed the request, and is not returning any content.",
					content = {@Content(schema = @Schema(implementation = String.class))}),
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
	@SneakyThrows(BrioBPMException.class)
	@GetMapping(value = {"dashboardMonitorInicial"}, produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> dashboardMonitorInicial() throws ParseException {	//, @RequestBody SolicitudAppTO filtros
		ResponseEntity<?> response = null;
		//DatosAutenticacionTO userSession = getUserSessionBpm();
		log.trace(">>>>>> REQUEST Dashboard Operativo ### {}{}operativo \nSession ",
			contextPath, ROOT_URL );// converterJsonAsString(filtros)
		
        String destino = "WEB";
        String cveDashboard = "MONITOR-COMPLIANCE-REPSE";
        
        // Crear objeto GraficoTO
        DAORet<GraficaTO, RetMsg> result = dashboardRepseHelper.generaGrafico(getUserSessionBpm(), destino, cveDashboard, null, true, 1);  
		response = new ResponseEntity<>(result.getContent(), HttpStatus.OK);

		log.trace("<<<<<< RESPONSE Dashboard Operativo ### {}{}operativo \n{}", contextPath, ROOT_URL,
			response);
		return response;
	}
	
	
	
	
	// MOCK 2
	@Operation(summary = "Dashboard Cumplimiento por Contratista", description = "Dashboard Cumplimiento por Contratista.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK - Successful operation.", content = {
			@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(description = "Lista", title = "Lista de Contratos por RFC.", implementation = DatosContratista.class))) }),
			@ApiResponse(responseCode = "204", description = "No Content - The server successfully processed the request, and is not returning any content.", content = {
					@Content(schema = @Schema(implementation = String.class)) }),
			@ApiResponse(responseCode = "400", ref = "BadRequestError"),
			@ApiResponse(responseCode = "401", ref = "UnauthorizedError"),
			@ApiResponse(responseCode = "403", ref = "ForbiddenError"),
			@ApiResponse(responseCode = "404", ref = "NotFoundError"),
			@ApiResponse(responseCode = "405", ref = "MethodNotAllowedError"),
			@ApiResponse(responseCode = "408", ref = "RequestTimeoutError"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error - Processing error.", content = {
					@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = String.class)) }) })

	@GetMapping(value = "dashboardCumplimientoPorContratista", produces = MediaType.APPLICATION_JSON_VALUE,
	        consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> dashboardCumplimientoPorContratista() throws ParseException {
	    log.trace(">>>>>> REQUEST Dashboard Cumplimiento por Contratista ### {}{}cumplimientoPorContratista \nSession ",
	            contextPath, ROOT_URL);

	    ResponseEntity<?> response = null;
	   	    

	    List<DatosContratista> result = dashboardRepseHelper.obtieneContratosPorRFC(getUserSessionBpm(), "WEB", "MONITOR-COMPLIANCE-REPSE", true, 2);

	    // Crear respuesta con tipo específico
	    response = new ResponseEntity<>(result, HttpStatus.OK);

	    log.trace("<<<<<< RESPONSE Dashboard Cumplimiento por Contratista ### {}{}cumplimientoPorContratista \n{}", contextPath, ROOT_URL, response);

	    return response;
	}
	
	
	
	// MOCK 3
	@Operation(summary = "Obtiene los contratos de cada RFC", description = "Obtiene los contratos por RFC.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK - Successful operation.", content = {
			@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(description = "Lista", title = "Lista de Contratos por RFC.", implementation = DatosContrato.class))) }),
			@ApiResponse(responseCode = "204", description = "No Content - The server successfully processed the request, and is not returning any content.", content = {
					@Content(schema = @Schema(implementation = String.class)) }),
			@ApiResponse(responseCode = "400", ref = "BadRequestError"),
			@ApiResponse(responseCode = "401", ref = "UnauthorizedError"),
			@ApiResponse(responseCode = "403", ref = "ForbiddenError"),
			@ApiResponse(responseCode = "404", ref = "NotFoundError"),
			@ApiResponse(responseCode = "405", ref = "MethodNotAllowedError"),
			@ApiResponse(responseCode = "408", ref = "RequestTimeoutError"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error - Processing error.", content = {
					@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = String.class)) }) })
	@GetMapping(value = "dashboardPorContrato", produces = MediaType.APPLICATION_JSON_VALUE, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> dashboardPorContrato() throws ParseException {	
		ResponseEntity<?> response = null;
		//DatosAutenticacionTO userSession = getUserSessionBpm();
		log.trace(">>>>>> REQUEST Dashboard Por  Contrato ### {}{} \nSession ",
			contextPath, ROOT_URL );// converterJsonAsString(filtros)
		
		
		List<DatosContrato> result =  dashboardRepseHelper.obtieneContratosPorRFCyNUM(getUserSessionBpm(), "WEB", "MONITOR-COMPLIANCE-REPSE", true, 3);
		

		response = new ResponseEntity<>(result, HttpStatus.OK);

		log.trace("<<<<<< RESPONSE Dashboard Operativo ### {}{}operativo \n{}", contextPath, ROOT_URL,
			response);
		return response;
	}

	
	@Operation(operationId = "dashboardPorRFCyNumContrato", summary = "dashboardPorRFCyNumContrato.", description = "dashboard Por Contrato.")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "OK - Successful operation..",
			content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
				schema = @Schema(implementation = GraficaTO.class))}),
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
	@Parameter(name = "datos", required = true, schema = @Schema(implementation = DatosContrato.class))
	@SneakyThrows(BrioBPMException.class)
	@PostMapping(value = "dashboardPorRFCyNumContrato", produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> dashboardPorRFCyNumContrato(@RequestBody DatosContrato datos) {
		    ResponseEntity<?> response = null;

		    log.trace(">>>>>> REQUEST Dashboard por RFC y Número de Contrato ### {}{} \nRFC: {} | Número de Contrato: {}", contextPath, ROOT_URL, datos.getRfc(), datos.getNombre());

		    // Llamamos al método `obtieneGraficoPorRFCyNUM`
		    DatosAutenticacionTO session = getUserSessionBpm(); // Método para obtener la sesión del usuario
		    GraficaTO resultado = null;
			try {
				resultado = dashboardRepseHelper.obtieneGraficoPorRFCyNUM(
				    session,
				    "WEB",
				    "MONITOR-COMPLIANCE-REPSE",
				    datos.getRfc(),
				    datos.getNombre(),
				    true,
				    3
				);
			} catch (ParseException e) {
				resultado = null;
			}

		    // Si no hay resultado, devolvemos un 204 No Content
		    if (resultado == null) {
		        response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		    } else {
		        // Si hay resultado, devolvemos el gráfico con un 200 OK
		        response = new ResponseEntity<>(resultado, HttpStatus.OK);
		    }

		    log.trace("<<<<<< RESPONSE Dashboard por RFC y Número de Contrato ### {}{} \n{}", contextPath, ROOT_URL, response);
		    return response;
		}

	
	/**
	 * Obtener RFC para la consulta de trabajadores.
	 * 
	 * @return el response entity.
	 */
	@Operation(summary = "Repse RFC", description = "Obtiene los RFC.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK - Successful operation.", content = {
			@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(description = "Lista", title = "Lista de RFC.", implementation = ComboBoxTO.class))) }),
			@ApiResponse(responseCode = "204", description = "No Content - The server successfully processed the request, and is not returning any content.", content = {
					@Content(schema = @Schema(implementation = String.class)) }),
			@ApiResponse(responseCode = "400", ref = "BadRequestError"),
			@ApiResponse(responseCode = "401", ref = "UnauthorizedError"),
			@ApiResponse(responseCode = "403", ref = "ForbiddenError"),
			@ApiResponse(responseCode = "404", ref = "NotFoundError"),
			@ApiResponse(responseCode = "405", ref = "MethodNotAllowedError"),
			@ApiResponse(responseCode = "408", ref = "RequestTimeoutError"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error - Processing error.", content = {
					@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = String.class)) }) })
	@GetMapping(value = "getAllRfc", produces = MediaType.APPLICATION_JSON_VALUE, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> getAllRfc() {
		ResponseEntity<?> response = null;
		DatosAutenticacionTO userSession = getUserSessionBpm();
		log.trace(">>>>>> REQUEST Obtener los datos del RFC <<<<<<<<< ");

		List<ComboBoxTO> list = inProcesoService.getAllNombres(userSession);
		if (list.isEmpty()) {
			response = new ResponseEntity<>(messagesService.getMessage(userSession, "ADAPTACION_TRABAJADORES",
					"ADAPTACION_TRABAJADORES_CONSULTAR_VACIA_RFC_VACIA", ""), HttpStatus.NO_CONTENT); 
		} else {
			response = new ResponseEntity<>(list, HttpStatus.OK);
		}
		log.trace("<<<<<< RESPONSE Obtener Procesos x versiones ### {}{}/versiones \n{}", contextPath, ROOT_URL,
				response);
		return response;
	}
	
	/**
	 * Obtener Contratos para la consulta de trabajadores considreando el proceso y rfc.
	 * 
	 * @return el response entity.
	 */
	@Operation(summary = "Consulta", description = "Obtiene los contratos de un RFC.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK - Successful operation.", content = {
			@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(description = "Lista de contartos", title = "Lista de contartos.", implementation = ComboBoxTO.class))) }),
			@ApiResponse(responseCode = "204", description = "No Content - The server successfully processed the request, and is not returning any content.", content = {
					@Content(schema = @Schema(implementation = String.class)) }),
			@ApiResponse(responseCode = "400", ref = "BadRequestError"),
			@ApiResponse(responseCode = "401", ref = "UnauthorizedError"),
			@ApiResponse(responseCode = "403", ref = "ForbiddenError"),
			@ApiResponse(responseCode = "404", ref = "NotFoundError"),
			@ApiResponse(responseCode = "405", ref = "MethodNotAllowedError"),
			@ApiResponse(responseCode = "408", ref = "RequestTimeoutError"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error - Processing error.", content = {
					@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = String.class)) }) })
	
	@Parameter(name = "datos", description = "Filtro Trabajadores.", required = true, schema = @Schema(title = "FiltroAdaptacionesTO.",description = "Filtro Trabajadores.", implementation = FiltroAdaptacionesTO.class))
	@SneakyThrows(BrioBPMException.class)

	@PostMapping(value = "getContratoByRfcProceso", produces = MediaType.APPLICATION_JSON_VALUE, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> getContratoByRfcProceso(@RequestBody FiltroAdaptacionesTO datos) {
		ResponseEntity<?> response = null;
		DatosAutenticacionTO userSession = getUserSessionBpm();
		log.trace(">>>>>> REQUEST getTrabajadorByFilter");
		List<ComboBoxTO> list = new ArrayList<ComboBoxTO>();	

		list = inProcesoService.getContratosByRfcProcesos(userSession, datos.getRfc());
		
		if (list.isEmpty()) {
			response = new ResponseEntity<>(messagesService.getMessage(userSession, "ADAPTACION_TRABAJADORES",
					"ADAPTACION_TRABAJADORES_CONSULTAR_VACIA_CONTRATOS_VACIA", ""), HttpStatus.NO_CONTENT); // CREAR EN BASE
		} else {
			response = new ResponseEntity<>(list, HttpStatus.OK);
		}
		log.trace("<<<<<< RESPONSE Obtener Procesos x versiones ### {}{}/versiones \n{}", contextPath, ROOT_URL,
				response);
		return response;
	}
	
	/**
	 * Obtener Contratos para la consulta de trabajadores considreando el proceso y rfc.
	 * 
	 * @return el response entity.
	 */
	@Operation(summary = "Consulta", description = "Valida REPSE en el sistema de gobierno.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK - Successful operation.", content = {
			@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(description = "Lista de contartos", title = "Lista de contartos.", implementation = ComboBoxTO.class))) }),
			@ApiResponse(responseCode = "204", description = "No Content - The server successfully processed the request, and is not returning any content.", content = {
					@Content(schema = @Schema(implementation = String.class)) }),
			@ApiResponse(responseCode = "400", ref = "BadRequestError"),
			@ApiResponse(responseCode = "401", ref = "UnauthorizedError"),
			@ApiResponse(responseCode = "403", ref = "ForbiddenError"),
			@ApiResponse(responseCode = "404", ref = "NotFoundError"),
			@ApiResponse(responseCode = "405", ref = "MethodNotAllowedError"),
			@ApiResponse(responseCode = "408", ref = "RequestTimeoutError"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error - Processing error.", content = {
					@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = String.class)) }) })
	
	@Parameter(name = "datos", description = "Filtro Trabajadores.", required = true, schema = @Schema(title = "FiltroValidaRepseTO.",description = "Filtro.", implementation = FiltroValidaRepseTO.class))
	@SneakyThrows(BrioBPMException.class)

	@PostMapping(value = "validaRepse", produces = MediaType.APPLICATION_JSON_VALUE, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> validaRepse(@RequestBody FiltroValidaRepseTO datos) {
		ResponseEntity<?> response = null;
		DatosAutenticacionTO userSession = getUserSessionBpm();
		log.error (">>>>>> REQUEST FiltroValidaRepseTO: " + datos.toString());
		RetMsg result = RetMsg.builder()
				.status("OK")
				.message("La Razon Social " + datos.getRazonSocial() + " es vigente." )
				.build();	
		
		try {
			result = crConsultaRepseService.validaRepseLinea(userSession, datos);
		} catch (IOException | InterruptedException e) {
			result = RetMsg.builder()
					.status("ERROR")
					.message("La Razon Social " + datos.getRazonSocial() + " tiene problemas al obtener la información." )
					.build();
		}
		response = new ResponseEntity<>(result, HttpStatus.OK);
		
		log.error("<<<<<< RESPONSE Obtener Procesos x versiones ### {}{}/versiones \n{}", contextPath, ROOT_URL,
				response);
		return response;
	}
	
	/**
	 * Consulta de usuarios de Repse
	 * @return
	 */
	@Operation(summary = "Repse_usuarios", description = "Obtiene usuarios de repse.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK - Successful operation.", content = {
			@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(description = "Procesos Consulta Repse", title = "Cr Acceso Usuario", implementation = CrConsultaRepseTO.class))) }),
			@ApiResponse(responseCode = "204", description = "No Content - The server successfully processed the request, and is not returning any content.", content = {
					@Content(schema = @Schema(implementation = String.class)) }),
			@ApiResponse(responseCode = "400", ref = "BadRequestError"),
			@ApiResponse(responseCode = "401", ref = "UnauthorizedError"),
			@ApiResponse(responseCode = "403", ref = "ForbiddenError"),
			@ApiResponse(responseCode = "404", ref = "NotFoundError"),
			@ApiResponse(responseCode = "405", ref = "MethodNotAllowedError"),
			@ApiResponse(responseCode = "408", ref = "RequestTimeoutError"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error - Processing error.", content = {
					@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = String.class)) }) })
	@GetMapping(value = "getUsuariosByContratante", produces = MediaType.APPLICATION_JSON_VALUE, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> getUsuariosByContratante() {
		ResponseEntity<?> response = null;
		DatosAutenticacionTO userSession = getUserSessionBpm();
		log.trace(">>>>>> REQUEST Obtener Procesos x versiones ### {}{}/versiones \nSession Data:\n{} ", contextPath,
				ROOT_URL, converterJsonAsString(userSession));
		List<CrUsuarioTO> list = crAccesoUsuarioService.getAll(userSession);
		if (list.isEmpty()) {
			response = new ResponseEntity<>(messagesService.getMessage(userSession, "CATALOGO_REPSE",
					"MANTENIMIENTO_PROCESO_CONSULTAR_VACIA", ""), HttpStatus.NO_CONTENT); // CREAR EN BASE
		} else {
			response = new ResponseEntity<>(list, HttpStatus.OK);
		}
		log.trace("<<<<<< RESPONSE Obtener Procesos x versiones ### {}{}/versiones \n{}", contextPath, ROOT_URL,
				response);
		return response;
	}	

	/**
	 * Registrar usuarios de Repse.
	 * @param datos el datos.
	 * @return el response entity.
	 */
	@Operation(operationId = "usuariosregistrar", summary = "Registrar.", description = "Registrar un Usuario.")
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
	@Parameter(name = "datos", required = true, schema = @Schema(implementation = CrUsuarioTO.class))
	@SneakyThrows(BrioBPMException.class)
	@PostMapping(value = "registrarUsuario", produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> registrarUsuario(@RequestBody CrUsuarioTO datos) {
		ResponseEntity<?> response = null;
		DatosAutenticacionTO userSession = getUserSessionBpm();
		log.trace(">>>>>> REQUEST Registrar Usuario ### {}{}/registrar \nSession Data:\n{} \nData Request:\n{}",
			contextPath, ROOT_URL, converterJsonAsString(userSession), converterJsonAsString(datos));
		RetMsg retMsg = crAccesoUsuarioService.insert(userSession, datos);
		response = new ResponseEntity<>(retMsg, HttpStatus.CREATED);
		log.trace("<<<<<< RESPONSE Registrar Usuario ### {}{}/registrar \n{}", contextPath, ROOT_URL, response);
		return response;
	}

	/**
	 * Actualizar usuarios de Repse.
	 * @param datos el datos.
	 * @return el response entity.
	 */
	@Operation(operationId = "usuariosactualizar", summary = "Actualizar.", description = "Actualizar un Usuario.")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "OK - Successful operation..",
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
	@Parameter(name = "datos", required = true, schema = @Schema(implementation = CrUsuarioTO.class))
	@SneakyThrows(BrioBPMException.class)
	@PostMapping(value = "actualizarUsuario", produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> actualizarUsuario(@RequestBody CrUsuarioTO datos) {
		ResponseEntity<?> response = null;
		DatosAutenticacionTO userSession = getUserSessionBpm();
		log.trace(">>>>>> REQUEST Registrar Usuario ### {}{}/registrar \nSession Data:\n{} \nData Request:\n{}",
			contextPath, ROOT_URL, converterJsonAsString(userSession), converterJsonAsString(datos));
		RetMsg retMsg = crAccesoUsuarioService.update(userSession, datos);
		response = new ResponseEntity<>(retMsg, HttpStatus.OK);
		log.trace("<<<<<< RESPONSE Actualizar Usuario ### {}{}/actualizar \n{}", contextPath, ROOT_URL, response);
		return response;
	}
	
		
	
	/**
	 * Consulta de procesos de Repse
	 * @return
	 */
	@Operation(summary = "Repse_usuarios", description = "Obtiene usuarios de repse.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK - Successful operation.", content = {
			@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(description = "Lista de Trabajadores", 
					title = "Lista.", implementation = CargaGridTO.class)) }),
			@ApiResponse(responseCode = "400", ref = "BadRequestError"),
			@ApiResponse(responseCode = "401", ref = "UnauthorizedError"),
			@ApiResponse(responseCode = "403", ref = "ForbiddenError"),
			@ApiResponse(responseCode = "404", ref = "NotFoundError"),
			@ApiResponse(responseCode = "405", ref = "MethodNotAllowedError"),
			@ApiResponse(responseCode = "408", ref = "RequestTimeoutError"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error - Processing error.", content = {
					@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = String.class)) }) })
	@GetMapping(value = "getListProcesos", produces = MediaType.APPLICATION_JSON_VALUE, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> getListProcesos() {
		ResponseEntity<?> response = null;
		DatosAutenticacionTO userSession = getUserSessionBpm();
		log.trace(">>>>>> REQUEST Obtener Procesos x versiones ### {}{}/versiones \nSession Data:\n{} ", contextPath,
				ROOT_URL, converterJsonAsString(userSession));
		PdfGridTO list = crProcesoPeriodicoService.getTabProcesos(userSession);
		response = new ResponseEntity<>(list, HttpStatus.OK);
		log.trace("<<<<<< RESPONSE Obtener Procesos con su detalle ### {}{}/versiones \n{}", contextPath, ROOT_URL,
				response);
		return response;
	}	
	
		
	/**
	 * Actualizar usuarios de Repse.
	 * @param datos el datos.
	 * @return el response entity.
	 */
	@Operation(operationId = "guardaCorreoProceso", summary = "Actualizar.", description = "Actualizar un Correo.")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "OK - Successful operation..",
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
	@Parameter(name = "datos", required = true, schema = @Schema(implementation = GuardaCorreoTO.class))
	@SneakyThrows(BrioBPMException.class)
	@PostMapping(value = "guardaCorreoProceso", produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> guardaCorreoProceso(@RequestBody GuardaCorreoTO datos) {
		ResponseEntity<?> response = null;
		DatosAutenticacionTO userSession = getUserSessionBpm();
		log.trace(">>>>>> REQUEST Guada correo ### {}{}/registrar \nSession Data:\n{} \nData Request:\n{}",
			contextPath, ROOT_URL, converterJsonAsString(userSession), converterJsonAsString(datos));
		RetMsg retMsg = definicionPeriodicaService.guardaCorreo(userSession, datos);
		response = new ResponseEntity<>(retMsg, HttpStatus.OK);
		log.trace("<<<<<< RESPONSE Guada correo ### {}{}/actualizar \n{}", contextPath, ROOT_URL, response);
		return response;
	}


	/**
	 * Consulta de procesos de Repse detalle
	 * @return
	 */
	@Operation(summary = "Repse_usuarios", description = "Obtiene usuarios de repse.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK - Successful operation.", content = {
			@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(description = "Lista de Trabajadores",
					title = "Lista.", implementation = CargaGridTO.class)) }),
			@ApiResponse(responseCode = "400", ref = "BadRequestError"),
			@ApiResponse(responseCode = "401", ref = "UnauthorizedError"),
			@ApiResponse(responseCode = "403", ref = "ForbiddenError"),
			@ApiResponse(responseCode = "404", ref = "NotFoundError"),
			@ApiResponse(responseCode = "405", ref = "MethodNotAllowedError"),
			@ApiResponse(responseCode = "408", ref = "RequestTimeoutError"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error - Processing error.", content = {
					@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = String.class)) }) })
	@Parameter(name = "datos", required = true, schema = @Schema(implementation = CorreoTO.class))
	@SneakyThrows(BrioBPMException.class)
	@PostMapping(value = "getCorreoProcesos", produces = MediaType.APPLICATION_JSON_VALUE, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> getCorreoProcesos(@RequestBody CorreoTO datos) {
		ResponseEntity<?> response = null;
		DatosAutenticacionTO userSession = getUserSessionBpm();
		log.trace(">>>>>> REQUEST Obtener Procesos x versiones ### {}{}/versiones \nSession Data:\n{} ", contextPath,
				ROOT_URL, converterJsonAsString(userSession));
		PdfGridTO list = definicionPeriodicaService.getTabDetProcesos(userSession, datos);
		response = new ResponseEntity<>(list, HttpStatus.OK);
		log.trace("<<<<<< RESPONSE Obtener Procesos con su detalle ### {}{}/versiones \n{}", contextPath, ROOT_URL,
				response);
		return response;
	}
	
	@Operation(summary = "Repse", description = "Elimina informacion compliance repse.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK - Successful operation.", content = {
			@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(description = "Procesos Consulta Repse", title = "Cr Consulta Repse.", implementation = CrConsultaRepseTO.class))) }),
			@ApiResponse(responseCode = "204", description = "No Content - The server successfully processed the request, and is not returning any content.", content = {
					@Content(schema = @Schema(implementation = String.class)) }),
			@ApiResponse(responseCode = "400", ref = "BadRequestError"),
			@ApiResponse(responseCode = "401", ref = "UnauthorizedError"),
			@ApiResponse(responseCode = "403", ref = "ForbiddenError"),
			@ApiResponse(responseCode = "404", ref = "NotFoundError"),
			@ApiResponse(responseCode = "405", ref = "MethodNotAllowedError"),
			@ApiResponse(responseCode = "408", ref = "RequestTimeoutError"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error - Processing error.", content = {
					@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = String.class)) }) })
	@PostMapping(
		    value = "eliminarProcesoIn",
		    consumes = MediaType.TEXT_PLAIN_VALUE,
		    produces = MediaType.APPLICATION_JSON_VALUE
		)
	public ResponseEntity<?> eliminarProcesoIn(@RequestBody String cveProceso) {
		ResponseEntity<?> response = null;
		DatosAutenticacionTO userSession = getUserSessionBpm();
		log.info(">>>>>> REQUEST Eliminar Compliance Repse:\n{} ", contextPath,
				ROOT_URL, converterJsonAsString(userSession));
		List<String> procesos = Arrays.asList(cveProceso.split(","));
		List<String> procesosRepse = eliminarInformacionService.getParametroGeneralRepository(Constants.REPSE_ELIMINAR);
		RetMsg msg = null;
		if (procesosRepse.contains(cveProceso)) {
			msg = eliminarInformacionService.eliminarProcesosPeriodicos();
		} 
		msg = eliminarInformacionService.eliminarProcesoByCve(procesos);
		if (msg.getStatus().equalsIgnoreCase("OK")) {
			response = new ResponseEntity<>(msg, HttpStatus.OK);
		} else {
			response = new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		log.info("<<<<<< Eliminar Compliance Repse {}{}/versiones \n{}", contextPath, ROOT_URL,
				response);
		return response;
	}
	
	@Operation(summary = "Repse", description = "Elimina informacion compliance repse.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK - Successful operation.", content = {
			@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(description = "Procesos Consulta Repse", title = "Cr Consulta Repse.", implementation = CrConsultaRepseTO.class))) }),
			@ApiResponse(responseCode = "204", description = "No Content - The server successfully processed the request, and is not returning any content.", content = {
					@Content(schema = @Schema(implementation = String.class)) }),
			@ApiResponse(responseCode = "400", ref = "BadRequestError"),
			@ApiResponse(responseCode = "401", ref = "UnauthorizedError"),
			@ApiResponse(responseCode = "403", ref = "ForbiddenError"),
			@ApiResponse(responseCode = "404", ref = "NotFoundError"),
			@ApiResponse(responseCode = "405", ref = "MethodNotAllowedError"),
			@ApiResponse(responseCode = "408", ref = "RequestTimeoutError"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error - Processing error.", content = {
					@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = String.class)) }) })
	@GetMapping(value = "eliminarProcesosPeriodicos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> eliminarProcesosPeriodicos() {
		ResponseEntity<?> response = null;
		DatosAutenticacionTO userSession = getUserSessionBpm();
		log.info(">>>>>> REQUEST Eliminar procesos periodicos:\nSession Data:\n{} ", contextPath,
				ROOT_URL, converterJsonAsString(userSession));
		RetMsg msg = eliminarInformacionService.eliminarProcesosPeriodicos();
		if (msg.getStatus().equalsIgnoreCase("OK")) {
			response = new ResponseEntity<>(msg, HttpStatus.OK);
		} else {
			response = new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		log.info("<<<<<< Eliminar procesos periodicos {}{} \n{}", contextPath, ROOT_URL,
				response);
		return response;
	}	

}
