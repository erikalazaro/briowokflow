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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briomax.briobpm.business.helpers.base.IEntidadHelper;
import com.briomax.briobpm.business.service.catalogs.core.IFacultadService;
import com.briomax.briobpm.business.service.catalogs.core.IMessagesService;
import com.briomax.briobpm.business.service.catalogs.core.ISemiFijosService;
import com.briomax.briobpm.business.service.catalogs.core.IUsuarioService;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.transferobjects.ComboBoxTO;
import com.briomax.briobpm.transferobjects.catalogs.UsuarioResetTO;
import com.briomax.briobpm.transferobjects.catalogs.UsuarioTO;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;

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
 * El objetivo de la Class CatalogoUsuarioController.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Jul 27, 2020 5:37:00 PM Modificaciones:
 * @since JDK 1.8
 */
@Tag(name = "CatalogoUsuario", description = "Catalogo de Usuarios.")
@RestController
@RequestMapping(CatalogoUsuarioController.ROOT_URL)
@Slf4j
public class CatalogoUsuarioController  extends AbstractBbpmController {

	/** Constante para la url del servicio. */
	public static final String ROOT_URL = "/services/catalogos/usuarios";

	/** El atributo o variable messages service. */
	@Autowired
	private IMessagesService messagesService;

	/** El atributo o variable service. */
	@Autowired
	private ISemiFijosService semiFijosService;

	/** El atributo o variable usuarios service. */
	@Autowired
	private IUsuarioService usuariosService;
	
	/** El atributo o variable entidad helper. */
	@Autowired
	private IEntidadHelper entidadService;
	
	/** El atributo o variable facultad service. */
	@Autowired
	private IFacultadService facultadService;

	/**
	 * Crear una nueva instancia del objeto catalogo usuario controller.
	 */
	public CatalogoUsuarioController() {
	}

	/**
	 * Obtener el valor de all.
	 * @param cveEntidad el cve entidad.
	 * @return el all.
	 */
	@Operation(operationId = "usuariosall", summary = "Usuarios.", description = "Obtener el catalogo de Usuarios de la Entidad en Sesion.")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "OK - Successful operation..",
			content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
				array = @ArraySchema(schema = @Schema(implementation = UsuarioTO.class)))
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
	@GetMapping(value = {"/all"}, produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> getAll() {
		ResponseEntity<?> response = null;
		DatosAutenticacionTO userSession = getUserSessionBpm();
		log.trace(">>>>>> REQUEST Obtener Usuarios ### {}{}/ \nSession Data:\n{} ", contextPath, ROOT_URL,
			converterJsonAsString(userSession));
		List<UsuarioTO> list = usuariosService.getAll(userSession);
		if (list.isEmpty()) {
			response = new ResponseEntity<>(
				messagesService.getMessage(userSession, "USUARIO", "MANTENIMIENTO_USUARIO_CONSULTAR_VACIA", ""),
				HttpStatus.NO_CONTENT);
		}
		else {
			response = new ResponseEntity<>(list, HttpStatus.OK);
		}
		log.trace("<<<<<< RESPONSE Obtener Usuarios ### {}{}/ \n{}", contextPath, ROOT_URL, response);
		return response;
	}

	/**
	 * Registrar.
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
	@Parameter(name = "datos", required = true, schema = @Schema(implementation = UsuarioTO.class))
	@SneakyThrows(BrioBPMException.class)
	@PostMapping(value = "/registrar", produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> registrar(@RequestBody UsuarioTO datos) {
		ResponseEntity<?> response = null;
		DatosAutenticacionTO userSession = getUserSessionBpm();
		log.trace(">>>>>> REQUEST Registrar Usuario ### {}{}/registrar \nSession Data:\n{} \nData Request:\n{}",
			contextPath, ROOT_URL, converterJsonAsString(userSession), converterJsonAsString(datos));
		RetMsg retMsg = usuariosService.insert(userSession, datos);
		response = new ResponseEntity<>(retMsg, HttpStatus.CREATED);
		log.trace("<<<<<< RESPONSE Registrar Usuario ### {}{}/registrar \n{}", contextPath, ROOT_URL, response);
		return response;
	}

	/**
	 * Actualizar.
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
	@Parameter(name = "datos", required = true, schema = @Schema(implementation = UsuarioTO.class))
	@SneakyThrows(BrioBPMException.class)
	@PostMapping(value = "/actualizar", produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> actualizar(@RequestBody UsuarioTO datos) {
		ResponseEntity<?> response = null;
		DatosAutenticacionTO userSession = getUserSessionBpm();
		log.trace(">>>>>> REQUEST Registrar Usuario ### {}{}/registrar \nSession Data:\n{} \nData Request:\n{}",
			contextPath, ROOT_URL, converterJsonAsString(userSession), converterJsonAsString(datos));
		RetMsg retMsg = usuariosService.update(userSession, datos);
		response = new ResponseEntity<>(retMsg, HttpStatus.OK);
		log.trace("<<<<<< RESPONSE Actualizar Usuario ### {}{}/actualizar \n{}", contextPath, ROOT_URL, response);
		return response;
	}

	/**
	 * Cmb situacion.
	 * @return el response entity.
	 */
	@Operation(operationId = "usuarioscmbsituacion", summary = "Situacion.", description = "Obtener las Situaciones de Usuarios.")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "OK - Successful operation..",
			content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
				array = @ArraySchema(schema = @Schema(implementation = ComboBoxTO.class)))
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
	@GetMapping(value = "/cmbsituacion", produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> cmbSituacion() {
		ResponseEntity<?> response = null;
		DatosAutenticacionTO userSession = getUserSessionBpm();
		log.trace(">>>>>> REQUEST Combo Situacion ### {}{}/cmbsituacion \nSession Data:\n{} ", contextPath, ROOT_URL,
			converterJsonAsString(userSession));
		List<ComboBoxTO> list = semiFijosService.getCmbSituacionUsuario(userSession);
		if (list.isEmpty()) {
			response = new ResponseEntity<>(messagesService.getMessage(userSession, "USUARIO",
				"MANTENIMIENTO_USUARIO_CMBSITUACION_VACIA", ""), HttpStatus.NO_CONTENT);
		}
		else {
			response = new ResponseEntity<>(list, HttpStatus.OK);
		}
		
		/*List<ComboBoxTO> list =  new ArrayList<ComboBoxTO>();
		ComboBoxTO to = ComboBoxTO.builder().descripcion("Registrado").id("REGISTRADO").build();
		list.add(to);
		to = ComboBoxTO.builder().descripcion("Habilitado").id("HABILITADO").build();
		list.add(to);
		to = ComboBoxTO.builder().descripcion("Desactivado").id("DESACTIVADO").build();
		list.add(to);		
		response = new ResponseEntity<>(list, HttpStatus.OK);*/
		log.trace("<<<<<< RESPONSE Combo Situacion ### {}{}/cmbsituacion \n{}", contextPath, ROOT_URL, response);
		return response;
	}

	/**
	 * Cmb tipo.
	 * @return el response entity.
	 */
	@Operation(operationId = "usuarioscmbtipo", summary = "Tipo.", description = "Obtener las Tipos de Usuarios.")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "OK - Successful operation..",
			content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
				array = @ArraySchema(schema = @Schema(implementation = ComboBoxTO.class)))
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
	@GetMapping(value = "/cmbtipo", produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> cmbTipo() {
		ResponseEntity<?> response = null;
		DatosAutenticacionTO userSession = getUserSessionBpm();
		log.trace(">>>>>> REQUEST Combo Tipo ### {}{}/cmbtipo \nSession Data:\n{} ", contextPath, ROOT_URL,
			converterJsonAsString(userSession));
		List<ComboBoxTO> list = semiFijosService.getCmbTipoUsuario(userSession);
		if (list.isEmpty()) {
			response = new ResponseEntity<>(
				messagesService.getMessage(userSession, "USUARIO", "MANTENIMIENTO_USUARIO_CMBTIPO_VACIA", ""),
				HttpStatus.NO_CONTENT);
		}
		else {
			response = new ResponseEntity<>(list, HttpStatus.OK);
		}
		/*List<ComboBoxTO> list =  new ArrayList<ComboBoxTO>();
		ComboBoxTO to = ComboBoxTO.builder().descripcion("Interno").id("INTERNO").build();
		list.add(to);
		to = ComboBoxTO.builder().descripcion("Externo").id("EXTERNO").build();
		list.add(to);		
		response = new ResponseEntity<>(list, HttpStatus.OK);*/
		
		log.trace("<<<<<< RESPONSE Combo Tipo ### {}{}/cmbtipo \n{}", contextPath, ROOT_URL, response);
		return response;
	}

	
	/**
	 * Obtener las localidades por entidad.
	 * @param cveEntidad el cve entidad.
	 * @return el entidad.
	 */
	@Operation(operationId = "Localidad", summary = "Localidades de una enditdad.", description = "Informacion de una Localidad.")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "OK - Successful operation.",
			content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
				schema = @Schema(implementation = ComboBoxTO.class))}),
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
	@Parameter(name = "datos", required = true, schema = @Schema(implementation = String.class))	@SneakyThrows(BrioBPMException.class)
	@PostMapping(value = "getLocalidadesByEntidad", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getLocalidadesByEntidad(@RequestBody String datos) { 
		ResponseEntity<?> response = null;
		log.trace(">>>>>> REQUEST Localidades de la Entidad en Sesion ### {}{}localidades \nSession Data:\n{}",
				contextPath, ROOT_URL, datos);
		List<ComboBoxTO> list = entidadService.getLocalidades(datos);
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
	 * Actualizar.
	 * @param datos el datos.
	 * @return el response entity.
	 */
	@Operation(operationId = "actualizaSituacion", summary = "Actualizar.", description = "Actualizar un Usuario.")
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
	@Parameter(name = "datos", required = true, schema = @Schema(implementation = UsuarioTO.class))
	@SneakyThrows(BrioBPMException.class)
	@PostMapping(value = "/actualizaSituacion", produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> actualizarSituacion(@RequestBody UsuarioTO datos) {
		ResponseEntity<?> response = null;
		DatosAutenticacionTO userSession = getUserSessionBpm();
		log.trace(">>>>>> REQUEST Registrar Usuario ### {}{}/registrar \nSession Data:\n{} \nData Request:\n{}",
			contextPath, ROOT_URL, converterJsonAsString(userSession), converterJsonAsString(datos));
		RetMsg retMsg = usuariosService.actualizaSituacion(userSession, datos);
		response = new ResponseEntity<>(retMsg, HttpStatus.OK);
		log.trace("<<<<<< RESPONSE Actualizar Usuario ### {}{}/actualizar \n{}", contextPath, ROOT_URL, response);
		return response;
	}
	
	/**
	 * Actualizar el password.
	 * @param datos el datos.
	 * @return el response entity.
	 */
	@Operation(operationId = "actualizaPassword", summary = "Actualizar.", description = "Actualizar un Password.")
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
	@Parameter(name = "datos", required = true, schema = @Schema(implementation = UsuarioTO.class))
	@SneakyThrows(BrioBPMException.class)
	@PostMapping(value = "/actualizarContrasena", produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> actualizarPasswor(@RequestBody UsuarioTO datos) {
		ResponseEntity<?> response = null;
		DatosAutenticacionTO userSession = getUserSessionBpm();
		log.trace(">>>>>> REQUEST Registrar Usuario ### {}{}/registrar \nSession Data:\n{} \nData Request:\n{}",
			contextPath, ROOT_URL, converterJsonAsString(userSession), converterJsonAsString(datos));
		RetMsg retMsg = usuariosService.actualizaPassword(userSession, datos);
		response = new ResponseEntity<>(retMsg, HttpStatus.OK);
		log.trace("<<<<<< RESPONSE Actualizar Usuario ### {}{}/actualizar \n{}", contextPath, ROOT_URL, response);
		return response;
	}
	

	/**
	 * Cmb facultades.
	 * @return el response entity.
	 */
	@Operation(operationId = "cmbFacultad", summary = "Facultad.", description = "Obtener las Facultad.")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "OK - Successful operation..",
			content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
				array = @ArraySchema(schema = @Schema(implementation = ComboBoxTO.class)))
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
	@GetMapping(value = "/cmbfacultad", produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> cmbFacultad() {
		ResponseEntity<?> response = null;
		DatosAutenticacionTO userSession = getUserSessionBpm();
		log.trace(">>>>>> REQUEST Combo Tipo ### {}{}/cmbtipo \nSession Data:\n{} ", contextPath, ROOT_URL,
			converterJsonAsString(userSession));
		List<ComboBoxTO> list = facultadService.getCmbFacultad(userSession);
		if (list.isEmpty()) {
			response = new ResponseEntity<>(
				messagesService.getMessage(userSession, "USUARIO", "MANTENIMIENTO_FACULTAD_COMBO_VACIA", ""),
				HttpStatus.NO_CONTENT);
		}
		else {
			response = new ResponseEntity<>(list, HttpStatus.OK);
		}
		
		log.trace("<<<<<< RESPONSE Combo Tipo ### {}{}/cmbtipo \n{}", contextPath, ROOT_URL, response);
		return response;
	}

	/**
	 * Reset Pasword.
	 * @param datos el datos.
	 * @return el response entity.
	 */
	@Operation(operationId = "reset", summary = "Reset Pasword.", description = "Reset de Pasword.")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "201",
			description = "Created - Crea el proceso de reset del password.",
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
	@Parameter(name = "datos", required = true, schema = @Schema(implementation = UsuarioResetTO.class))
	@SneakyThrows(BrioBPMException.class)
	@PostMapping(value = "/generaResetPassword", produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> generaResetPassword(@RequestBody UsuarioResetTO datos) {
		ResponseEntity<?> response = null;
		log.trace(">>>>>> REQUEST Reset pasword ### {}{}/registrar \nData Request:\n{}",
			contextPath, ROOT_URL, converterJsonAsString(datos));
		//RetMsg retMsg = RetMsg.builder().status("OK").build();
		RetMsg retMsg = usuariosService.resetPassword(datos);
		response = new ResponseEntity<>(retMsg, HttpStatus.OK);
		log.trace("<<<<<< RESPONSE Registrar Usuario ### {}{}/registrar \n{}", contextPath, ROOT_URL, response);
		return response;
	}
	
	/**
	 * Reset Pasword.
	 * @param datos el datos.
	 * @return el response entity.
	 */
	@Operation(operationId = "reset", summary = "Reset Pasword.", description = "Reset de Pasword.")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "201",
			description = "Created - Crea el proceso de reset del password.",
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
	@Parameter(name = "datos", required = true, schema = @Schema(implementation = UsuarioResetTO.class))
	@SneakyThrows(BrioBPMException.class)
	@PostMapping(value = "/restauraPassword", produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> restauraPassword(@RequestBody UsuarioResetTO datos) {
		ResponseEntity<?> response = null;
		log.trace(">>>>>> REQUEST Reset pasword ### {}{}/registrar \nData Request:\n{}",
			contextPath, ROOT_URL, converterJsonAsString(datos));
		RetMsg retMsg = usuariosService.restauraPassword(datos);
		response = new ResponseEntity<>(retMsg, HttpStatus.OK);
		log.trace("<<<<<< RESPONSE Registrar Usuario ### {}{}/registrar \n{}", contextPath, ROOT_URL, response);
		return response;
	}
}
