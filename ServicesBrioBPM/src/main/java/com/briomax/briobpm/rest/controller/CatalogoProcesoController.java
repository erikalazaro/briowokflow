/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */
package com.briomax.briobpm.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briomax.briobpm.business.service.catalogs.core.IMessagesService;
import com.briomax.briobpm.business.service.catalogs.core.IProcesoService;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.transferobjects.catalogs.Proceso;
import com.briomax.briobpm.transferobjects.catalogs.ProcesosTO;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Class CatalogoProcesoController.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Jun 29, 2020 2:53:08 PM Modificaciones:
 * @since JDK 1.8
 */
@Tag(name = "CatalogoProceso", description = "Catalogo de Procesos.")
@RestController
@RequestMapping(CatalogoProcesoController.ROOT_URL)
@Slf4j
public class CatalogoProcesoController  extends AbstractBbpmController {

	/** Constante para la url del servicio. */
	public static final String ROOT_URL = "/services/catalogos/procesos";

	/** El atributo o variable messages service. */
	@Autowired
	private IMessagesService messagesService;
	
	/** El atributo o variable proceso service. */
	@Autowired
	private IProcesoService procesoService;

	/**
	 * Crear una nueva instancia del objeto catalogo proceso controller.
	 */
	public CatalogoProcesoController() {
	}

	@Operation(operationId = "procesosversiones", summary = "Procesos.", description = "Obtener los Procesos-Versiones Activos de la Entidad en Sesion.")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "OK - Successful operation..",
			content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
				array = @ArraySchema(schema = @Schema(implementation = Proceso.class)))
		}),
		@ApiResponse(responseCode = "204",
			description = "No Content - The server successfully processed the request, and is not returning any content.",
			content = {@Content}),
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
	@GetMapping(value = "/versiones", produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> getProcesos() {
		ResponseEntity<?> response = null;
		DatosAutenticacionTO userSession = getUserSessionBpm();
		log.trace(">>>>>> REQUEST Obtener Procesos x versiones ### {}{}/versiones \nSession Data:\n{} ", contextPath,
			ROOT_URL, converterJsonAsString(userSession));
		List<Proceso> list = procesoService.getProcesos(userSession);
		if (list.isEmpty()) {
			response = new ResponseEntity<>(
				messagesService.getMessage(userSession, "PROCESO", "MANTENIMIENTO_PROCESO_CONSULTAR_VACIA", ""),
				HttpStatus.NO_CONTENT);
		}
		else {
			response = new ResponseEntity<>(list, HttpStatus.OK);
		}
		log.trace("<<<<<< RESPONSE Obtener Procesos x versiones ### {}{}/versiones \n{}", contextPath, ROOT_URL,
			response);
		return response;
	}

	/**
	 * Obtener el valor de all.
	 * @return el all.
	 */
	@Operation(operationId = "procesosall", summary = "Procesos.", description = "Obtener los Procesos de la Entidad en Sesion.")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "OK - Successful operation..",
			content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
				array = @ArraySchema(schema = @Schema(implementation = ProcesosTO.class)))
		}),
		@ApiResponse(responseCode = "204",
			description = "No Content - The server successfully processed the request, and is not returning any content.",
			content = {@Content}),
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
	@GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> getAll() {
		ResponseEntity<?> response = null;
		DatosAutenticacionTO userSession = getUserSessionBpm();
		log.trace(">>>>>> REQUEST Obtener Procesos ### {}{}/all \nSession Data:\n{} ", contextPath,
			ROOT_URL, converterJsonAsString(userSession));
		List<ProcesosTO> list = procesoService.getAll(userSession);
		if (list.isEmpty()) {
			response = new ResponseEntity<>(
				messagesService.getMessage(userSession, "PROCESO", "MANTENIMIENTO_PROCESO_CONSULTAR_VACIA", ""),
				HttpStatus.NO_CONTENT);
		}
		else {
			response = new ResponseEntity<>(list, HttpStatus.OK);
		}
		log.trace("<<<<<< RESPONSE Obtener Procesos ### {}{}/all \n{}", contextPath, ROOT_URL, response);
		return response;
	}

}
