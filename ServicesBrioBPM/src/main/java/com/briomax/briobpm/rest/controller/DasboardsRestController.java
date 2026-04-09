/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.rest.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
import com.briomax.briobpm.business.service.core.IDashboardsService;
import com.briomax.briobpm.common.core.DAORet;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.transferobjects.app.Datos;
import com.briomax.briobpm.transferobjects.app.GraficaTO;
import com.briomax.briobpm.transferobjects.app.Grafico;
import com.briomax.briobpm.transferobjects.app.Seccion;
import com.briomax.briobpm.transferobjects.dashboards.Dashboard;
import com.briomax.briobpm.transferobjects.dashboards.OperativoGerenteIn;
import com.briomax.briobpm.transferobjects.dashboards.OperativoIn;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;
import com.briomax.briobpm.transferobjects.in.DatosContrato;

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
@Tag(name = "Dashboards", description = "Dashboards.")
@RestController
@RequestMapping(DasboardsRestController.ROOT_URL)
@Slf4j
public class DasboardsRestController extends AbstractBbpmController {

	/** Constante para la url del servicio. */
	public static final String ROOT_URL = "/services/dashboards/";

	/** El atributo o variable dashboards service. */
	@Autowired
	private IDashboardsService dashboardsService;
	
	@Autowired
	private IDashboardHelper dashboardHelper;
	
	/**
	 * Operativo.
	 * @param filtros el filtros.
	 * @return el response entity.
	 */
	@Operation(summary = "Dashboard Operativo", description = "Dashboard Operativo.")
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
	@Parameter(name = "filtros", description = "Filtros.", required = true,
			schema = @Schema(implementation = OperativoIn.class))
	@SneakyThrows(BrioBPMException.class)
	@PostMapping(value = "operativo", produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> operativo(@RequestBody OperativoIn filtros) {
		ResponseEntity<?> response = null;
		DatosAutenticacionTO userSession = getUserSessionBpm();
		log.trace(">>>>>> REQUEST Dashboard Operativo ### {}{}operativo \nSession Data:\n{} \nFilters:\n{}",
			contextPath, ROOT_URL, converterJsonAsString(userSession), converterJsonAsString(filtros));

		DAORet<Dashboard, RetMsg> dashboard = dashboardsService.operativo(userSession, filtros);
		//Remove dashboards response = getResponse(dashboard);
		response = new ResponseEntity<>(HttpStatus.NO_CONTENT); //Remove dashboards 

		log.trace("<<<<<< RESPONSE Dashboard Operativo ### {}{}operativo \n{}", contextPath, ROOT_URL,
			response);
		return response;
	}

	/**
	 * Operativo gerente.
	 * @param filtros el filtros.
	 * @return el response entity.
	 */
	@Operation(summary = "Dashboard Operativo Gerente", description = "Dashboard Operativo Gerente.")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "OK - Successful operation.", content = {@Content(
				mediaType = MediaType.APPLICATION_JSON_VALUE,
				schema = @Schema(description = "Dashboard Operativo Gerente", title = "Dashboard Operativo Gerente.",
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
	@Parameter(name = "filtros", description = "Filtros.", required = true,
	schema = @Schema(implementation = OperativoGerenteIn.class))
	@SneakyThrows(BrioBPMException.class)
	@PostMapping(value = "operativogerente", produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> operativoGerente(@RequestBody OperativoGerenteIn filtros) {
		ResponseEntity<?> response = null;
		DatosAutenticacionTO userSession = getUserSessionBpm();
		log.trace(
			">>>>>> REQUEST Dashboard Operativo Gerente ### {}{}operativogerente \nSession Data:\n{} \nFilters:\n{}",
			contextPath, ROOT_URL, converterJsonAsString(userSession), converterJsonAsString(filtros));
		Object object = new Object();
		//Remove dashboards  // dashboardsService.operativoGerente(userSession, filtros);
		
		response = new ResponseEntity<>(HttpStatus.NO_CONTENT); //Remove dashboards 

		log.trace("<<<<<< RESPONSE Dashboard Operativo Gerente ### {}{}operativogerente \n{}", contextPath, ROOT_URL,
			response);
		return response;
	}

	/**
	 * Obtener el valor de response.
	 * @param dashboard el dashboard.
	 * @return el response.
	 */
	private ResponseEntity<?> getResponse(DAORet<Dashboard, RetMsg> dashboard) {
		ResponseEntity<?> response = null;
		if (dashboard.getMeta().getStatus().equalsIgnoreCase("OK")) {
			if (!dashboard.getContent().getSections().isEmpty()) {
				response = new ResponseEntity<>(dashboard.getContent(), HttpStatus.OK);
			}
			else {
				response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		}
		else {
			response = new ResponseEntity<>(dashboard.getMeta().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
		

	
	@Operation(summary = "Lee Lista Dashboard", description = "Obtiene la lista de datos del dashboard.")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "OK - Successful operation.",
	            content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
	                    schema = @Schema(description = "Lista de Datos del Dashboard", title = "Lista Dashboard",
	                            implementation = DAORet.class))}),
	    @ApiResponse(responseCode = "204", description = "No Content - The server successfully processed the request, and is not returning any content.",
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
	@GetMapping(value = "leeListaDashboard", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> leeListaDashboard(HttpServletRequest request) {
	    ResponseEntity<?> response = null;

	    log.trace(">>>>>> REQUEST Lee Lista Dashboard ### {}{}leeListaDashboard \nSession ",
	            contextPath, ROOT_URL);

	    String destino = request.getParameter("destino");
	    String ubicacionLista = request.getParameter("ubicacionLista");
	    
	    log.debug("\tDESTINO: {}", destino);
	    log.debug("\tUBICACION LISTA: {}", ubicacionLista);
	    log.debug("\tENTIDAD: {}", request.getParameter("cveEntidad"));
	    log.debug("\tLOCALIDAD: {}", request.getParameter("cveLocalidad"));
	    log.debug("\tIDIOMA: {}", request.getParameter("cveIdioma"));
	    log.debug("\tUSUARIO: {}", request.getParameter("username"));

	    // Crear datos de autenticación
	    DatosAutenticacionTO session = DatosAutenticacionTO.builder()
	    		.cveEntidad(request.getParameter("cveEntidad"))
	    		.cveLocalidad(request.getParameter("cveLocalidad"))
	    		.cveIdioma(request.getParameter("cveIdioma"))
	    		.cveUsuario(request.getParameter("username"))
	    		.build();

	    // Llamar al método leeListaDashboard
	    DAORet<HashMap<String, String>, RetMsg> result = dashboardHelper.leeListaDashboard(session, destino, ubicacionLista);

	    response = new ResponseEntity<>(result, HttpStatus.OK);

	    log.trace("<<<<<< RESPONSE Lee Lista Dashboard ### {}{}leeListaDashboard \n{}", contextPath, ROOT_URL, response);
	    return response;
	}
	
}
