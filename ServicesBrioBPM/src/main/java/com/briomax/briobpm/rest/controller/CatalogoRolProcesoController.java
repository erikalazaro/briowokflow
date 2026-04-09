/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */
package com.briomax.briobpm.rest.controller;

import java.util.List;

import com.briomax.briobpm.business.service.catalogs.core.IMessagesService;
import com.briomax.briobpm.business.service.catalogs.core.IRolProcesoService;
import com.briomax.briobpm.business.service.catalogs.core.ISemiFijosService;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.transferobjects.ComboBoxTO;
import com.briomax.briobpm.transferobjects.catalogs.RolProcesoTO;
import com.briomax.briobpm.transferobjects.catalogs.UsuarioRol;
import com.briomax.briobpm.transferobjects.catalogs.UsuariosRol;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
 * El objetivo de la Class CatalogoRolProcesoController.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Jun 29, 2020 4:30:44 PM Modificaciones:
 * @since JDK 1.8
 */
@Tag(name = "CatalogoRolProceso", description = "Catalogo de Roles de Procesos.")
@RestController
@RequestMapping(CatalogoRolProcesoController.ROOT_URL)
@Slf4j
public class CatalogoRolProcesoController  extends AbstractBbpmController {

	/** Constante para la url del servicio. */
	public static final String ROOT_URL = "/services/catalogos/rolprocesos";

	/** El atributo o variable messages service. */
	@Autowired
	private IMessagesService messagesService;

	/** El atributo o variable service. */
	@Autowired
	private ISemiFijosService semiFijosService;

	/** El atributo o variable proceso service. */
	@Autowired
	private IRolProcesoService rolProcesoService;

	/**
	 * Crear una nueva instancia del objeto catalogo rol proceso controller.
	 */
	public CatalogoRolProcesoController() {
	}

	/**
	 * Obtener el valor de all.
	 * @param cveProceso el cve proceso.
	 * @param version el version.
	 * @return el all.
	 */
	@Operation(operationId = "rolprocesos", summary = "Roles de un Proceso.", description = "Obtener los Roles de un Proceso.")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "OK - Successful operation..",
			content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
				array = @ArraySchema(schema = @Schema(implementation = RolProcesoTO.class)))
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
	@Parameters(value = {
		@Parameter(in = ParameterIn.QUERY, name = "cveProceso", description = "Clave del Proceso", required = true,
				example = "DUMMY", schema = @Schema(implementation = String.class)),
		@Parameter(in = ParameterIn.QUERY, name = "version", description = "Version del Proceso", required = true,
				example = "1.00", schema = @Schema(implementation = String.class))
	})
	@SneakyThrows(BrioBPMException.class)
	@PostMapping(value = {"/"}, produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> getAll(@RequestParam("cveProceso") String cveProceso,
		@RequestParam("version") String version) {
		ResponseEntity<?> response = null;
		DatosAutenticacionTO userSession = getUserSessionBpm();
		log.trace(">>>>>> REQUEST Obtener Rol-Procesos ### {}{}/ \nSession Data:\n{} \nData Request: {} {}",
			contextPath, ROOT_URL, converterJsonAsString(userSession), cveProceso, version);
		List<RolProcesoTO> list = rolProcesoService.getAll(userSession, cveProceso, version);
		if (list.isEmpty()) {
			response = new ResponseEntity<>(messagesService.getMessage(userSession, "ROL_PROCESO",
				"MANTENIMIENTO_ROLPROCESO_CONSULTAR_VACIA", ""), HttpStatus.NO_CONTENT);
		}
		else {
			response = new ResponseEntity<>(list, HttpStatus.OK);
		}
		log.trace("<<<<<< RESPONSE Obtener Rol-Procesos ### {}{}/ \n{}", contextPath, ROOT_URL, response);
		return response;
	}

	/**
	 * Registrar.
	 * @param datos el datos.
	 * @return el response entity.
	 */
	@Operation(operationId = "rolprocesosregistrar", summary = "Registrar.",
			description = "Registrar un Rol de un Proceso.")
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
	@Parameter(name = "datos", required = true, schema = @Schema(implementation = RolProcesoTO.class))
	@SneakyThrows(BrioBPMException.class)
	@PostMapping(value = "/registrar", produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> registrar(@RequestBody RolProcesoTO datos) {
		ResponseEntity<?> response = null;
		DatosAutenticacionTO userSession = getUserSessionBpm();
		log.trace(">>>>>> REQUEST Registrar Rol-Proceso ### {}{}/registrar \nSession Data:\n{} \nData Request:\n{}",
			contextPath, ROOT_URL, converterJsonAsString(userSession), converterJsonAsString(datos));
		RetMsg retMsg = rolProcesoService.insert(userSession, datos);
		response = new ResponseEntity<>(retMsg, HttpStatus.CREATED);
		log.trace("<<<<<< RESPONSE Registrar Rol-Proceso ### {}{}/registrar \n{}", contextPath, ROOT_URL, response);
		return response;
	}

	/**
	 * Actualizar.
	 * @param datos el datos.
	 * @return el response entity.
	 */
	@Operation(operationId = "rolprocesosactualizar", summary = "Actualizar.",
			description = "Actualizar un Rol de un Proceso.")
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
	@Parameter(name = "datos", required = true, schema = @Schema(implementation = RolProcesoTO.class))
	@SneakyThrows(BrioBPMException.class)
	@PostMapping(value = "/actualizar", produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> actualizar(@RequestBody RolProcesoTO datos) {
		ResponseEntity<?> response = null;
		DatosAutenticacionTO userSession = getUserSessionBpm();
		log.trace(">>>>>> REQUEST Actualizar Rol-Proceso ### {}{}/actualizar \nSession Data:\n{} \nData Request:\n{}",
			contextPath, ROOT_URL, converterJsonAsString(userSession), converterJsonAsString(datos));
		RetMsg retMsg = rolProcesoService.update(userSession, datos);
		response = new ResponseEntity<>(retMsg, HttpStatus.OK);
		log.trace("<<<<<< RESPONSE Actualizar Rol-Proceso ### {}{}/actualizar \n{}", contextPath, ROOT_URL, response);
		return response;
	}

	/**
	 * Cmb situacion.
	 * @return el response entity.
	 */
	@Operation(operationId = "rolprocesoscmbsituacion", summary = "Situacion.",
			description = "Obtener las situaciones de los Roles de un Proceso.")
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
		log.trace(">>>>>> REQUEST Combo Situacion ### {}{}/cmbsituacion \nSession Data:\n{} ", contextPath,
			ROOT_URL, converterJsonAsString(userSession));
		List<ComboBoxTO> list = semiFijosService.getCmbSituacionRolProceso(userSession);
		if (list.isEmpty()) {
			response = new ResponseEntity<>(messagesService.getMessage(userSession, "ROL_PROCESO",
				"MANTENIMIENTO_ROLPROCESO_CMBSITUACION_VACIA", ""), HttpStatus.NO_CONTENT);
		}
		else {
			response = new ResponseEntity<>(list, HttpStatus.OK);
		}
		log.trace("<<<<<< RESPONSE Combo Situacion ### {}{}/cmbsituacion \n{}", contextPath, ROOT_URL, response);
		return response;
	}

	/**
	 * Obtener el valor de usuarios rol.
	 * @param cveProceso el cve proceso.
	 * @param version el version.
	 * @param cveRol el cve rol.
	 * @return el usuarios rol.
	 */
	@Operation(operationId = "rolprocesosusuarios", summary = "Usuarios de un Rol.", description = "Obtener los Usuarios de un Rol.")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "OK - Successful operation..",
			content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
				array = @ArraySchema(schema = @Schema(implementation = UsuarioRol.class)))
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
	@Parameters(value = {
		@Parameter(in = ParameterIn.QUERY, name = "cveProceso", description = "Clave del Proceso", required = true,
				example = "DUMMY", schema = @Schema(implementation = String.class)),
		@Parameter(in = ParameterIn.QUERY, name = "version", description = "Version del Proceso", required = true,
				example = "1.00", schema = @Schema(implementation = String.class)),
		@Parameter(in = ParameterIn.QUERY, name = "cveRol", description = "Clave del Rol.", required = true,
				example = "ADMIN", schema = @Schema(implementation = String.class))
	})
	@SneakyThrows(BrioBPMException.class)
	@PostMapping(value = {"/usuarios"}, produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> getUsuariosRol(@RequestParam("cveProceso") String cveProceso,
		@RequestParam("version") String version, @RequestParam("cveRol") String cveRol) {
		ResponseEntity<?> response = null;
		DatosAutenticacionTO userSession = getUserSessionBpm();
		log.trace(">>>>>> REQUEST Obtener Usuarios Rol-Procesos ### {}{}/usuarios \nSession Data:\n{} \nData Request: {} {}",
			contextPath, ROOT_URL, converterJsonAsString(userSession), cveProceso, version);
		List<UsuarioRol> list = rolProcesoService.getUsuariosRolProcesos(userSession, cveProceso, version, cveRol);
		if (list.isEmpty()) {
			response = new ResponseEntity<>(messagesService.getMessage(userSession, "USUARIOS_ROL_PROCESO",
				"MANTENIMIENTO_ROLPROCESOUSUARIOS_CONSULTAR_VACIA", ""), HttpStatus.NO_CONTENT);
		}
		else {
			response = new ResponseEntity<>(list, HttpStatus.OK);
		}
		log.trace("<<<<<< RESPONSE Obtener Usuarios Rol-Procesos ### {}{}/usuarios \n{}", contextPath, ROOT_URL, response);
		return response;
	}

	/**
	 * Asignar usuarios rol.
	 * @param datos el datos.
	 * @return el response entity.
	 */
	@Operation(operationId = "rolprocesosusuariosasignar", summary = "Asignar Usuarios a un Rol Proceso.",
			description = "Registrar Usuarios a un Rol Proceso.")
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
	@Parameter(name = "datos", description = "Lista de Ids de Usuario-Rol", required = true,
			schema = @Schema(description = "Ids Usuarios Rol-Proceso", title = "Id de Usuario-Rol.",
					implementation = UsuariosRol.class))
	@SneakyThrows(BrioBPMException.class)
	@PostMapping(value = "/asignarusuarios", produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> asignarUsuariosRol(@RequestBody UsuariosRol datos) {
		ResponseEntity<?> response = null;
		DatosAutenticacionTO userSession = getUserSessionBpm();
		log.trace(">>>>>> REQUEST asignar Usuarios Rol-Proceso ### {}{}/asignarusuarios \nSession Data:\n{} \nData Request:\n{}",
			contextPath, ROOT_URL, converterJsonAsString(userSession), converterJsonAsString(datos));
		RetMsg retMsg = rolProcesoService.asignarUsariosRol(userSession, datos);
		response = new ResponseEntity<>(retMsg, HttpStatus.CREATED);
		log.trace("<<<<<< RESPONSE asignar Usuarios Rol-Proceso ### {}{}/asignarusuarios \n{}", contextPath, ROOT_URL, response);
		return response;
	}

}
