/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.rest.controller;

import com.briomax.briobpm.transferobjects.DatosUsuarioTO;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Class SessionDataRestController.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Feb 26, 2020 12:39:49 PM Modificaciones:
 * @since JDK 1.8
 */
@Tag(name = "SessionData", description = "Datos de la Sesión.")
@RestController
@RequestMapping(SessionDataRestController.ROOT_URL)
@Slf4j
public class SessionDataRestController extends AbstractBbpmController {

	/** Constante para la url del servicio. */
	public static final String ROOT_URL = "/services/session/";

	/**
	 * Obtener el valor de session data.
	 * @return el session data.
	 */
	@Operation(summary = "Datos de la Sesión.", description = "Datos de la Sesión.")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "OK - Successful operation.",
				content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
						schema = @Schema(name = "data", description = "Datos de Autenticacion",
								title = "Datos de Autenticacion.", implementation = DatosAutenticacionTO.class))}),
		@ApiResponse(responseCode = "400", ref = "BadRequestError"),
		@ApiResponse(responseCode = "401", ref = "UnauthorizedError"),
		@ApiResponse(responseCode = "403", ref = "ForbiddenError"),
		@ApiResponse(responseCode = "404", ref = "NotFoundError"),
		@ApiResponse(responseCode = "405", ref = "MethodNotAllowedError"),
		@ApiResponse(responseCode = "408", ref = "RequestTimeoutError"),
		@ApiResponse(responseCode = "500", ref = "InternalServerError")
	})
	@GetMapping(value = "getsessiondata", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public DatosAutenticacionTO getSessionData() {
		log.trace(">>>>>> REQUEST Datos de la Sesion ### {}{}getsessiondata", contextPath, ROOT_URL);
		DatosAutenticacionTO data = getUserSessionBpm();
		log.trace("<<<<<< RESPONSE Datos de la Sesion ### {}{}getsessiondata \n{}", contextPath, ROOT_URL,
			converterJsonAsString(data));
		return data;
	}

	/**
	 * Obtener el valor de user data.
	 * @return el user data.
	 */
	@Operation(summary = "Datos del Usuario en Sesión.", description = "Datos del Usuario en Sesión.")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "OK - Successful operation.",
				content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
						schema = @Schema(name = "data", description = "Datos del Usuario", title = "Datos del Usuario.",
								implementation = DatosUsuarioTO.class))}),
		@ApiResponse(responseCode = "400", ref = "BadRequestError"),
		@ApiResponse(responseCode = "401", ref = "UnauthorizedError"),
		@ApiResponse(responseCode = "403", ref = "ForbiddenError"),
		@ApiResponse(responseCode = "404", ref = "NotFoundError"),
		@ApiResponse(responseCode = "405", ref = "MethodNotAllowedError"),
		@ApiResponse(responseCode = "408", ref = "RequestTimeoutError"),
		@ApiResponse(responseCode = "500", ref = "InternalServerError")
	})
	@GetMapping(value = "getuserdata", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public DatosUsuarioTO getUserData() {
		log.trace(">>>>>> REQUEST Datos del Usuario en Sesion ### {}{}getuserdata", contextPath, ROOT_URL);
		DatosUsuarioTO data = getUserSessionData();
		log.trace("<<<<<< RESPONSE Datos del Usuario en Sesion ### {}{}getuserdata \n{}", contextPath, ROOT_URL,
			converterJsonAsString(data));
		return data;
	}

	/**
	 * Sets the idioma.
	 * @param cveIdioma el cve idioma.
	 * @return el response entity.
	 */
	@Operation(summary = "Modificar Idioma.", description = "Modificar el Idioma en Sesion.")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "OK - Successful operation.", content = {
			@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = String.class))}),
		@ApiResponse(responseCode = "400", ref = "BadRequestError"),
		@ApiResponse(responseCode = "401", ref = "UnauthorizedError"),
		@ApiResponse(responseCode = "403", ref = "ForbiddenError"),
		@ApiResponse(responseCode = "404", ref = "NotFoundError"),
		@ApiResponse(responseCode = "405", ref = "MethodNotAllowedError"),
		@ApiResponse(responseCode = "408", ref = "RequestTimeoutError"),
		@ApiResponse(responseCode = "500", description = "Internal Server Error - Processing error.", content = {
			@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = String.class))})
	})
	@Parameters(value = {
		@Parameter(in = ParameterIn.QUERY, name = "cveIdioma", description = "Clave del Idioma.", required = true,
				example = "ES-MX", schema = @Schema(implementation = String.class))
	})
	@PutMapping(value = "idioma", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> setIdioma(@RequestParam(name = "cveIdioma", required = true) String cveIdioma) {
		ResponseEntity<?> response = null;
		log.trace(">>>>>> REQUEST Modificar el Idioma en Sesion ### {}{}idioma CVE_IDIOMA=", contextPath, ROOT_URL, cveIdioma);
		if (modifySession("IDIOMA", cveIdioma)) {
			response = new ResponseEntity<>("Successful", HttpStatus.OK);
		}
		else {
			response = new ResponseEntity<>("Error al modificar el Idioma", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		log.trace("<<<<<< RESPONSE Modificar el Idioma en Sesion ### {}{}idioma \n{}", contextPath, ROOT_URL,
			response);
		return response;
	}

	/**
	 * Sets the moneda.
	 * @param cveMoneda el cve moneda.
	 * @return el response entity.
	 */
	@Operation(summary = "Modificar Moneda.", description = "Modificar la clave de la Moneda en Sesion.")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "OK - Successful operation.", content = {
			@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = String.class))}),
		@ApiResponse(responseCode = "400", ref = "BadRequestError"),
		@ApiResponse(responseCode = "401", ref = "UnauthorizedError"),
		@ApiResponse(responseCode = "403", ref = "ForbiddenError"),
		@ApiResponse(responseCode = "404", ref = "NotFoundError"),
		@ApiResponse(responseCode = "405", ref = "MethodNotAllowedError"),
		@ApiResponse(responseCode = "408", ref = "RequestTimeoutError"),
		@ApiResponse(responseCode = "500", description = "Internal Server Error - Processing error.", content = {
			@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = String.class))})
	})
	@Parameters(value = {
		@Parameter(in = ParameterIn.QUERY, name = "cveMoneda", description = "Clave de la Moneda.", required = true,
				example = "MXN", schema = @Schema(implementation = String.class))
	})
	@PutMapping(value = "moneda", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> setMoneda(@RequestParam(name = "cveMoneda", required = true) String cveMoneda) {
		ResponseEntity<?> response = null;
		log.trace(">>>>>> REQUEST Modificar la Moneda en Sesion ### {}{}moneda CVE_MONEDA=", contextPath, ROOT_URL, cveMoneda);
		if (modifySession("MONEDA", cveMoneda)) {
			response = new ResponseEntity<>("Successful", HttpStatus.OK);
		}
		else {
			response = new ResponseEntity<>("Error al modificar la Moneda.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		log.trace("<<<<<< RESPONSE Modificar la Moneda en Sesion ### {}{}moneda \n{}", contextPath, ROOT_URL,
			response);
		return response;
	}

}
