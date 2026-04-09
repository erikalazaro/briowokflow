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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.briomax.briobpm.business.helpers.base.IEntidadHelper;
import com.briomax.briobpm.business.helpers.base.IInitHelper;
import com.briomax.briobpm.business.service.catalogs.core.IMessagesService;
import com.briomax.briobpm.business.service.core.IAreaTrabajoService;
import com.briomax.briobpm.business.service.core.IMenuService;
import com.briomax.briobpm.common.core.DAORet;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.transferobjects.CargaGridTO;
import com.briomax.briobpm.transferobjects.ComboBoxTO;
import com.briomax.briobpm.transferobjects.LocalidadTO;
import com.briomax.briobpm.transferobjects.MenuAreaTrabajoTO;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Class InitializationRestController.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion May 15, 2020 12:02:20 PM Modificaciones:
 * @since JDK 1.8
 */
@Tag(name = "Initialization", description = "Inicialización.")
@RestController
@RequestMapping(InitializationRestController.ROOT_URL)
@Slf4j
public class InitializationRestController extends AbstractBbpmController {

	/** Constante para la url del servicio. */
	public static final String ROOT_URL = "/services/init/";

	/** El atributo o variable init helper. */
	@Autowired
	private IInitHelper initHelper;

	/** El atributo o variable entidad helper. */
	@Autowired
	private IEntidadHelper entidadService;

	/** El atributo o variable area trabajo helper. */
	@Autowired
	private IAreaTrabajoService areaTrabajoService;

	/** El atributo o variable messages service. */
	@Autowired
	private IMessagesService messagesService;
	
	/** El atributo o variable menu service. */
	@Autowired
	private IMenuService menuService;

	/**
	 * Menu dinamico.
	 * @return el response entity.
	 */
	@Operation(operationId = "menudinamico", summary = "Menu Dinamico.", description = "Menu Dinamico.")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "OK - Successful operation.",
				content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
						array = @ArraySchema(schema = @Schema(implementation = MenuAreaTrabajoTO.class)))}),
		@ApiResponse(responseCode = "204",
				description = "No Content - The server successfully processed the request," +
					" and is not returning any content.",
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
	@GetMapping(value = "menudinamico", produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> menuDinamico() {
		ResponseEntity<?> response = null;
		RetMsg meta = null;
			DatosAutenticacionTO userSession = getUserSessionBpm();
			log.info(">>>>>> REQUEST Menu Dinamico ### {}{}menudinamico \nSession Data:\n{}",
			contextPath, ROOT_URL, converterJsonAsString(userSession));
			DAORet<List<MenuAreaTrabajoTO>, RetMsg> res = menuService.obtenerMenuAreaTrabajo(userSession);
			List<MenuAreaTrabajoTO> menu = res.getContent();
			log.info("menu: " + menu.size());
			meta = res.getMeta();
			if (meta.getStatus().equalsIgnoreCase("OK")) {
				if (!menu.isEmpty()) {
					response = new ResponseEntity<>(menu, HttpStatus.OK);
					printerJson("JSON Menu Dinamico", menu);
				}
				else {
					response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
				}
			}
			else {
				response = new ResponseEntity<>(meta.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		log.info("<<<<<< RESPONSE Menu Dinamico ### {}{}menudinamico \n{}", contextPath, ROOT_URL,
			response.getStatusCode());
		return response;
	}

	/**
	 * Menu Dashboard.
	 * @return el response entity.
	 */
	@Operation(operationId = "menudashboard", summary = "Menu Dashboard.", description = "Menu Dashboard.")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "OK - Successful operation.",
				content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
						array = @ArraySchema(schema = @Schema(implementation = MenuAreaTrabajoTO.class)))}),
		@ApiResponse(responseCode = "204",
				description = "No Content - The server successfully processed the request," +
					" and is not returning any content.",
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
	@GetMapping(value = "menudashboard", produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> menudashboard() {
		ResponseEntity<?> response = null;
		RetMsg meta = null;
			DatosAutenticacionTO userSession = getUserSessionBpm();
			log.trace(">>>>>> REQUEST Menu Dashboard ### {}{}menudinamico \nSession Data:\n{}",
			contextPath, ROOT_URL, converterJsonAsString(userSession));
			log.info(">>>>>> REQUEST Menu Dashboard ### {}{}menudinamico \nSession Data:\n{}");
			DAORet<List<MenuAreaTrabajoTO>, RetMsg> res = menuService.obtenerMenuDashboard(userSession);
			//DAORet<List<MenuAreaTrabajoTO>, RetMsg> res = areaTrabajoService.obtenerMenuDashboard(userSession);
			List<MenuAreaTrabajoTO> menu = res.getContent();
			meta = res.getMeta();
			if (meta.getStatus().equalsIgnoreCase("OK")) {
				if (!menu.isEmpty()) {
					response = new ResponseEntity<>(menu, HttpStatus.OK);
					printerJson("JSON Menu Dashboard", menu);
				}
				else {
					response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
				}
			}
			else {
				response = new ResponseEntity<>(meta.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		log.info("<<<<<< RESPONSE Menu Dashboard ### {}{}menudinamico \n{}", contextPath, ROOT_URL,
			response.getStatusCode());
		return response;
	}

	@Operation(operationId = "horalocalidad", summary = "Hora de acuerdo a la Localidad del Usuario.", description = "Hora de acuerdo a la Localidad del Usuario.")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "OK - Successful operation.",
				content = {@Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
						array = @ArraySchema(schema = @Schema(example = "11:45", implementation = String.class)))}),
		@ApiResponse(responseCode = "204",
				description = "No Content - The server successfully processed the request," +
					" and is not returning any content.",
				content = {@Content(schema = @Schema(example = "11:45", implementation = String.class))}),
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
	@GetMapping(value = "horalocalidad", produces = MediaType.TEXT_PLAIN_VALUE,
			consumes = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<?> horalocalidad() {
		ResponseEntity<?> response = null;
		DatosAutenticacionTO userSession = getUserSessionBpm();
		log.trace(">>>>>> REQUEST Hora ### {}{}hora \nSession Data:\n{}", contextPath, ROOT_URL,
			converterJsonAsString(userSession));
		DAORet<String, RetMsg> res = entidadService.getHora(userSession);
		if ("OK".equalsIgnoreCase(res.getMeta().getStatus())) {
			response = new ResponseEntity<>(res.getContent(), HttpStatus.OK);
		}
		else {
			response = new ResponseEntity<>(res.getMeta().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		log.trace("<<<<<< RESPONSE Hora ### {}{}hora \n{}", contextPath, ROOT_URL, response);
		return response;
	}

	/**
	 * Obtener el valor de localidad.
	 * @return el localidad.
	 */
	@Operation(operationId = "localidad", summary = "Datos de la Localidad.", description = "Datos de la Localidad del Usuario.")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "OK - Successful operation.",
			content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
				schema = @Schema(implementation = LocalidadTO.class))}),
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
	@GetMapping(value = "localidad", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> localidad() {
		ResponseEntity<?> response = null;
		DatosAutenticacionTO userSession = getUserSessionBpm();
		log.trace(">>>>>> REQUEST Datos Localidad ### {}{}localidad \nSession Data:\n{}", contextPath, ROOT_URL,
			converterJsonAsString(userSession));
		response = obtenerLocalidad(userSession.getCveUsuario(), userSession.getCveEntidad(),
			userSession.getCveLocalidad(), userSession.getCveIdioma());
		log.trace("<<<<<< RESPONSE Datos Localidad ### {}{}datoslocalidad \n{}", contextPath, ROOT_URL, response);
		return response;
	}

	/**
	 * Obtener el valor de entidad.
	 * @param cveUsuario el cve usuario.
	 * @param cveEntidad el cve entidad.
	 * @param cveLocalidad el cve localidad.
	 * @param cveIdioma el cve idioma.
	 * @return el entidad.
	 */
	@Operation(operationId = "datoslocalidad", summary = "Datos de una Localidad.", description = "Informacion de una Localidad.")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "OK - Successful operation.",
			content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
				schema = @Schema(implementation = LocalidadTO.class))}),
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
	@Parameters(value = {
		@Parameter(in = ParameterIn.QUERY, name = "cveUsuario", description = "Clave del Usuario.", required = true,
				example = "BrioBpm", schema = @Schema(implementation = String.class)),
		@Parameter(in = ParameterIn.QUERY, name = "cveEntidad", description = "Clave de la Entidad.", required = true,
				example = "BRIOMAX", schema = @Schema(implementation = String.class)),
		@Parameter(in = ParameterIn.QUERY, name = "cveLocalidad", description = "Clave de la Localidad.",
				required = true, example = "BRIOMAX-CENTRAL", schema = @Schema(implementation = String.class)),
		@Parameter(in = ParameterIn.QUERY, name = "cveIdioma", description = "Clave del Idioma.", required = true,
				example = "ES-MX", schema = @Schema(implementation = String.class))
	})
	@SneakyThrows(BrioBPMException.class)
	@PostMapping(value = "datoslocalidad", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> datoslocalidad(@RequestParam("cveUsuario") String cveUsuario,
		@RequestParam("cveEntidad") String cveEntidad,
		@RequestParam("cveLocalidad") String cveLocalidad,
		@RequestParam("cveIdioma") String cveIdioma) {
		ResponseEntity<?> response = null;
		log.trace(">>>>>> REQUEST Datos Localidad ### {}{}datoslocalidad [ cveUsuario:{} , cveEntidad:{} , cveLocalidad:{} , cveIdioma:{} ]",
			contextPath, ROOT_URL, cveUsuario, cveEntidad, cveLocalidad, cveIdioma);
			response = obtenerLocalidad(cveUsuario, cveEntidad, cveLocalidad, cveIdioma);
		log.trace("<<<<<< RESPONSE Datos Localidad ### {}{}datoslocalidad \n{}", contextPath, ROOT_URL, response);
		return response;
	}

	/**
	 * Obtener localidad.
	 * @param cveUsuario el cve usuario.
	 * @param cveEntidad el cve entidad.
	 * @param cveLocalidad el cve localidad.
	 * @param cveIdioma el cve idioma.
	 * @return el response entity.
	 */
	private ResponseEntity<?> obtenerLocalidad(String cveUsuario, String cveEntidad, String cveLocalidad,
		String cveIdioma) {
		ResponseEntity<?> response;
		DatosAutenticacionTO userSession = DatosAutenticacionTO.builder()
				.cveUsuario(cveUsuario)
				.cveEntidad(cveEntidad)
				.cveLocalidad(cveLocalidad)
				.cveIdioma(cveIdioma)
				.build();
		DAORet<LocalidadTO, RetMsg> res =
			entidadService.getDatosLocalidad(userSession.getCveUsuario(), userSession.getCveEntidad(),
				userSession.getCveLocalidad(), userSession.getCveIdioma());
		LocalidadTO localidad = res.getContent();
		RetMsg meta = res.getMeta();
		if ("OK".equalsIgnoreCase(meta.getStatus())) {
			if (localidad != null) {
				response = new ResponseEntity<>(localidad, HttpStatus.OK);
			}
			else {
				response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		}
		else {
			response = new ResponseEntity<>(meta.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

	/**
	 * Obtener el valor de idiomas.
	 * @return el idiomas.
	 */
	@Operation(operationId = "getidiomas", summary = "Idiomas.", description = "Idiomas.")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "OK - Successful operation.",
				content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
						array = @ArraySchema(schema = @Schema(implementation = ComboBoxTO.class)))}),
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
			@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RetMsg.class))})
	})
	@SneakyThrows(BrioBPMException.class)
	@GetMapping(value = "getidiomas", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getidiomas() {
		DatosAutenticacionTO userSession = getUserSessionBpm();
		log.trace(">>>>>> REQUEST Idiomas ### {}{}getidiomas \nSession Data:\n{}", contextPath, ROOT_URL,
			converterJsonAsString(userSession));
		ResponseEntity<?> response = null;
		DAORet<List<ComboBoxTO>, RetMsg>  metaData = initHelper.getIdiomas(userSession);
		log.debug("RESPONSE Idiomas: {}", metaData);
		if (metaData.getMeta().getStatus().toUpperCase().equals("OK")) {
			if (!metaData.getContent().isEmpty()) {
				response = new ResponseEntity<>(metaData.getContent(), HttpStatus.OK);
			}
			else {
				response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		}
		else {
			response = new ResponseEntity<>(metaData.getMeta().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		log.trace("<<<<<< RESPONSE Idiomas ### {}{}getidiomas \n{}", contextPath, ROOT_URL, response);
		return response;
	}

	/**
	 * Obtener el valor de localidades.
	 * @return el localidades.
	 */
	@Operation(operationId = "localidades", summary = "Localidades.", description = "Localidades de la Entidad en Sesion.")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "OK - Successful operation.",
				content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
						array = @ArraySchema(schema = @Schema(implementation = ComboBoxTO.class)))}),
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
			@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RetMsg.class))})
	})
	@SneakyThrows(BrioBPMException.class)
	@GetMapping(value = {"localidades"}, produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> getLocalidades() {
		ResponseEntity<?> response = null;
		DatosAutenticacionTO userSession = getUserSessionBpm();
		log.trace(">>>>>> REQUEST Localidades de la Entidad en Sesion ### {}{}localidades \nSession Data:\n{}",
			contextPath, ROOT_URL, converterJsonAsString(userSession));
			List<ComboBoxTO> list = entidadService.getLocalidades(getUserSessionBpm().getCveEntidad());
			if (list.isEmpty()) {
				response = new ResponseEntity<>("", HttpStatus.NO_CONTENT);
			}
			else {
				response = new ResponseEntity<>(list, HttpStatus.OK);
			}
		log.trace("<<<<<< RESPONSE Localidades de la Entidad en Sesion ### {}{}localidades \n{}", contextPath, ROOT_URL,
			response);
		return response;
	}

	/**
	 * Obtener el valor de message.
	 * @param cveProceso el cve proceso.
	 * @param codMensaje el cod mensaje.
	 * @param variablesValores el variables valores.
	 * @return el message.
	 */
	@Operation(operationId = "message", summary = "Mensaje.", description = "Obtener la descripcion de un codigo.")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "OK - Successful operation.",
				content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
						schema = @Schema(implementation = String.class))}),
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
			@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RetMsg.class))})
	})
	@Parameters(value = {
		@Parameter(in = ParameterIn.QUERY, name = "cveProceso", description = "Clave del Proceso.", required = true,
				example = "DUMMY", schema = @Schema(implementation = String.class)),
		@Parameter(in = ParameterIn.QUERY, name = "codMensaje", description = "Codigo del Mensaje.", required = true,
				example = "ACTIVIDAD_NO_EXISTE", schema = @Schema(implementation = String.class)),
		@Parameter(in = ParameterIn.QUERY, name = "variablesValores",
				description = "Cadena de Variables & Valores, " + "separados por el operador 1", required = true,
				example = "VARIABLE|VALOR|", schema = @Schema(implementation = String.class)),
	})
	@SneakyThrows(BrioBPMException.class)
	@PostMapping(value = "message", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getMessage(@RequestParam("cveProceso") String cveProceso,
		@RequestParam("codMensaje") String codMensaje, @RequestParam("variablesValores") String variablesValores) {
		log.trace(">>>>>> REQUEST Mensaje x Codigo ### {}{}message [ cveProceso:{} , codMensaje:{} , variablesValores:{} ]",
			contextPath, ROOT_URL, cveProceso, codMensaje, variablesValores);
		ResponseEntity<?> response = null;
		String message = messagesService.getMessage(getUserSessionBpm(), cveProceso == null ? "" : cveProceso,
			codMensaje, variablesValores == null ? "" : variablesValores);
		log.debug("\t Message: {}", message);
		response = new ResponseEntity<>(message, HttpStatus.OK);
		log.trace("<<<<<< RESPONSE Mensaje x Codigo ### {}{}message \n{}", contextPath, ROOT_URL, response);
		return response;
	}

	/**
	 * Obtener el valor de monedas.
	 * @return el monedas.
	 */
	@Operation(operationId = "getmonedas", summary = "Monedas.", description = "Monedas.")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "OK - Successful operation.",
				content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
						array = @ArraySchema(schema = @Schema(implementation = ComboBoxTO.class)))}),
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
			@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RetMsg.class))})
	})
	@SneakyThrows(BrioBPMException.class)
	@GetMapping(value = "monedas", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getMonedas() {
		DatosAutenticacionTO userSession = getUserSessionBpm();
		log.trace(">>>>>> REQUEST Monedas ### {}{}monedas \nSession Data:\n{}", contextPath, ROOT_URL,
			converterJsonAsString(userSession));
		ResponseEntity<?> response = null;
		DAORet<List<ComboBoxTO>, RetMsg>  metaData = initHelper.getMonedas();
		
		if (metaData.getMeta().getStatus().equalsIgnoreCase("OK")) {
			if (!metaData.getContent().isEmpty()) {
				response = new ResponseEntity<>(metaData.getContent(), HttpStatus.OK);
			}
			else {
				response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		}
		else {
			response = new ResponseEntity<>(metaData.getMeta().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		log.trace("<<<<<< RESPONSE Monedas ### {}{}monedas \n{}", contextPath, ROOT_URL, response);
		return response;
	}

	/**
	 * Obtener el valor de entidades.
	 * @return el entidades.
	 */
	@Operation(operationId = "getentidades", summary = "Entidades.", description = "Entidades.")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "OK - Successful operation.",
				content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
						array = @ArraySchema(schema = @Schema(implementation = ComboBoxTO.class)))}),
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
			@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RetMsg.class))})
	})
	@SneakyThrows(BrioBPMException.class)
	@GetMapping(value = "getentidades", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getEntidades() {
		DatosAutenticacionTO userSession = getUserSessionBpm();
		log.trace(">>>>>> REQUEST Entidades ### {}{}getentidades \nSession Data:\n{}", contextPath, ROOT_URL,
			converterJsonAsString(userSession));
		ResponseEntity<?> response = null;
		DAORet<List<ComboBoxTO>, RetMsg>  metaData = entidadService.getEntidades(userSession);
		log.debug("RESPONSE Entidades: {}", metaData.getMeta());
		if ("OK".equalsIgnoreCase(metaData.getMeta().getStatus())) {
			if (!metaData.getContent().isEmpty()) {
				response = new ResponseEntity<>(metaData.getContent(), HttpStatus.OK);
			}
			else {
				response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		}
		else {
			response = new ResponseEntity<>(metaData.getMeta().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		log.trace("<<<<<< RESPONSE Entidades ### {}{}getentidades \n{}", contextPath, ROOT_URL, response);
		return response;
	}

}
