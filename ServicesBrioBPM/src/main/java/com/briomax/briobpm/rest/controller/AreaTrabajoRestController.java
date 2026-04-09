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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briomax.briobpm.business.service.core.IAreaTrabajoService;
import com.briomax.briobpm.common.core.DAORet;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.transferobjects.BitacoraTO;
import com.briomax.briobpm.transferobjects.SaveSectionTO;
import com.briomax.briobpm.transferobjects.dynamicForm.DataGrid;
import com.briomax.briobpm.transferobjects.in.ActividadTO;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;
import com.briomax.briobpm.transferobjects.in.ProcesoSeguroTO;
import com.briomax.briobpm.transferobjects.in.ProcesoTO;

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
 * El objetivo de la Class AreaTrabajoRestController.java es ...
 * @author RIGOBERTO OLVERA
 * @version 1.0 Fecha de creacion 29/09/2017 01:00:55 PM Modificaciones:
 * @since JDK 1.8
 */
@Tag(name = "AreaTrabajo", description = "Area de Trabajo.")
@RestController
@RequestMapping(AreaTrabajoRestController.ROOT_URL)
@Slf4j
public class AreaTrabajoRestController extends AbstractBbpmController {

	/** Constante para la url del servicio. */
	public static final String ROOT_URL = "/services/areatrabajo/";

	/** El atributo o variable area trabajo service. */
	@Autowired
	private IAreaTrabajoService areaTrabajoService;

	/**
	 * Area trabajo.
	 * @param datos el datos.
	 * @return el response entity.
	 */
	@Operation(summary = "Area de Trabajo", description = "Area de Trabajo.")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "OK - Successful operation.",
				content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
					array = @ArraySchema(schema =  @Schema(description = "Area de Trabajo", title = "Area de Trabajo.",
						implementation = DataGrid.class)))
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
		@ApiResponse(responseCode = "500", description = "Internal Server Error - Processing error.", content = {
			@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = String.class))})
		}
	)
	@Parameter(name = "datos", description = "Datos del Proceso.", required = true, schema = @Schema(title = "ProcesoTO.",description = "Datos del Proceso.", implementation = ProcesoTO.class))
	@SneakyThrows(BrioBPMException.class)
	@PostMapping(value = "grid", produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> areaTrabajo(@RequestBody ProcesoTO datos) {
		ResponseEntity<?> response = null;
		RetMsg meta = new RetMsg();
		DataGrid areaTrabajo = null;
			DatosAutenticacionTO userSession = getUserSessionBpm();
			log.info("----> AREA TRABAJO ");
		log.trace(">>>>>> REQUEST Area Trabajo (Config/Data) ### {}{}grid \nSession Data:\n{} \nData Request:\n{}",
			contextPath, ROOT_URL, converterJsonAsString(userSession), converterJsonAsString(datos));
			DAORet<DataGrid, RetMsg> metaData = areaTrabajoService.getAreaTrabajo(userSession, datos);
			log.info("---- TERMINA getAreaTrabajo");
			areaTrabajo = metaData.getContent();
			log.info("----> AREA TRABAJO: " + areaTrabajo);

			meta = metaData.getMeta();
			log.info("----> META: " + meta);
			if (meta.getStatus().equalsIgnoreCase("OK")) {
				log.info("----> META OK");
				response = new ResponseEntity<>(areaTrabajo, HttpStatus.OK);
				printerJson("JSON Area Trabajo (Config/Data)", areaTrabajo);
				/*if (areaTrabajo.getTail() != null && !areaTrabajo.getTail().isEmpty()) {
					response = new ResponseEntity<>(areaTrabajo, HttpStatus.OK);
					printerJson(areaTrabajo);
				}
				else {
					response = new ResponseEntity<>(areaTrabajo, HttpStatus.NO_CONTENT);
				}*/
			} else {
				response = new ResponseEntity<>(meta.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		log.trace("<<<<<< RESPONSE Area Trabajo (Config/Data) ### {}{}grid \n{}", contextPath, ROOT_URL,
			response.getStatusCode());
		return response;
	}

	/**
	 * Inicio proceso.
	 * @param datos el datos.
	 * @return el response entity.
	 */
	@Operation(summary = "Inicio de Proceso", description = "Inicio de Proceso.")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "OK - Successful operation.",
				content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
						schema = @Schema(implementation = ActividadTO.class))}),
		@ApiResponse(responseCode = "400", ref = "BadRequestError"),
		@ApiResponse(responseCode = "401", ref = "UnauthorizedError"),
		@ApiResponse(responseCode = "403", ref = "ForbiddenError"),
		@ApiResponse(responseCode = "404", ref = "NotFoundError"),
		@ApiResponse(responseCode = "405", ref = "MethodNotAllowedError"),
		@ApiResponse(responseCode = "408", ref = "RequestTimeoutError"),
		@ApiResponse(responseCode = "500", description = "Internal Server Error - Processing error.", content = {
			@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = String.class))}),
		}
	)
	@Parameter(name = "datos", description = "Datos del Proceso.", required = true, schema = @Schema(implementation = ProcesoTO.class))
	@SneakyThrows(BrioBPMException.class)
	@PostMapping(value = "inicioproceso", produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> inicioProceso(@RequestBody ProcesoTO datos) {
		ResponseEntity<?> response = null;
		RetMsg meta = null;
		log.info("----> AREA TRABAJO INICIO PROCESO");
			DatosAutenticacionTO userSession = getUserSessionBpm();
			log.trace(">>>>>> REQUEST Inicio Proceso ### {}{}inicioproceso \nSession Data:\n{} \nData Request:\n{}",
				contextPath, ROOT_URL, converterJsonAsString(userSession), converterJsonAsString(datos));
			DAORet<ActividadTO, RetMsg> metaData = areaTrabajoService.inicioProceso(getUserSessionBpm(), datos);
			ActividadTO activity = metaData.getContent();
			meta = metaData.getMeta();
			if (meta.getStatus().equalsIgnoreCase("OK")) {
				response = new ResponseEntity<>(activity, HttpStatus.OK);
			}
			else {
				response = new ResponseEntity<>(meta.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		log.info("<<<<<< RESPONSE Inicio Proceso ### {}{}inicioproceso \n{}", contextPath, ROOT_URL, response);
		return response;
	}

	/**
	 * Procesos.
	 * @return el response entity.
	 */
	@Operation(summary = "Bitacora", description = "Bitacora.")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "OK - Successful operation.",
				content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
						array = @ArraySchema(schema = @Schema(description = "Bitacora",
								title = "Bitacora.", implementation = BitacoraTO.class)))
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
		@ApiResponse(responseCode = "500", description = "Internal Server Error - Processing error.", content = {
			@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = String.class))})
		}
	)
	@Parameter(name = "actividad", description = "Datos de la Actividad.", required = true,
			schema = @Schema(implementation = ActividadTO.class))
	@SneakyThrows(BrioBPMException.class)
	@PostMapping(value = "bitacora", produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> bitacora(@RequestBody ActividadTO actividad) {
		ResponseEntity<?> response = null;
		RetMsg meta = null;
			DatosAutenticacionTO userSession = getUserSessionBpm();
			log.trace(">>>>>> REQUEST bitacora ### {}{}bitacora \nSession Data:\n{} \nRequest Data:\n{}",
				contextPath, ROOT_URL, converterJsonAsString(userSession), converterJsonAsString(actividad));
			DAORet<List<BitacoraTO>, RetMsg> metaData =
				areaTrabajoService.leeBitacoraNodo(getUserSessionBpm(), actividad);
			List<BitacoraTO> reporte = metaData.getContent();
			meta = metaData.getMeta();
			if (meta.getStatus().equalsIgnoreCase("OK")) {
				if (!reporte.isEmpty()) {
					response = new ResponseEntity<>(reporte, HttpStatus.OK);
				}
				else {
					response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
				}
			}
			else {
				response = new ResponseEntity<>(meta.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		log.trace("<<<<<< RESPONSE Bitacora ### {}{}bitacora \n{}", contextPath, ROOT_URL, response);
		return response;
	}
	
	/**
	 * Inicio proceso seguro con autenticación.
	 * @param datos el datos con información de autenticación y proceso.
	 * @return el response entity.
	 */
	@Operation(summary = "Inicio de Proceso", description = "Inicio de Proceso.")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "OK - Successful operation.",
				content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
						schema = @Schema(implementation = ActividadTO.class))}),
		@ApiResponse(responseCode = "400", ref = "BadRequestError"),
		@ApiResponse(responseCode = "401", ref = "UnauthorizedError"),
		@ApiResponse(responseCode = "403", ref = "ForbiddenError"),
		@ApiResponse(responseCode = "404", ref = "NotFoundError"),
		@ApiResponse(responseCode = "405", ref = "MethodNotAllowedError"),
		@ApiResponse(responseCode = "408", ref = "RequestTimeoutError"),
		@ApiResponse(responseCode = "500", description = "Internal Server Error - Processing error.", content = {
			@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = String.class))}),
		}
	)
	@Parameter(name = "datos", description = "Datos del Proceso.", required = true, schema = @Schema(implementation = ProcesoSeguroTO.class))
	@SneakyThrows(BrioBPMException.class)
	@PostMapping(value = "inicioprocesoseguro", produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = {MediaType.APPLICATION_JSON_VALUE})	
	public ResponseEntity<?> inicioProcesoSeguro(@RequestBody ProcesoSeguroTO datos) {
		ResponseEntity<?> response = null;
		RetMsg meta = null;
		log.info("----> INICIO PROCESO SEGURO");
		
		try {
			log.info(">>>>>> REQUEST Inicio Proceso Seguro ### {}{}inicioprocesoseguro \nData Request:\n{}",
				contextPath, ROOT_URL, converterJsonAsString(datos));
			

			
			// Primero autenticar al usuario
			
			DAORet<DatosAutenticacionTO, RetMsg> autenticacionResult = areaTrabajoService.autenticarUsuarioPass(datos.getCveUsuario(), datos.getPassword());
			
			if (!autenticacionResult.getMeta().getStatus().equalsIgnoreCase("OK")) {
				log.warn("Autenticación fallida para usuario: {}", datos.getCveUsuario());
				return new ResponseEntity<>(autenticacionResult.getMeta().getMessage(), HttpStatus.UNAUTHORIZED);
		}
			
			log.info("Usuario autenticado exitosamente: {}", datos.getCveUsuario());

			DAORet<ActividadTO, RetMsg> metaData = areaTrabajoService.inicioProcesoSeguro(autenticacionResult.getContent(), datos);
			ActividadTO activity = metaData.getContent();
			meta = metaData.getMeta();
			SaveSectionTO dataSections = new SaveSectionTO();
			dataSections.setActivity(activity);
			


			
			
			if (meta.getStatus().equalsIgnoreCase("OK")) {
				log.info("Proceso iniciado exitosamente para usuario: {} - Proceso: {}", 
					datos.getCveUsuario(), datos.getCveProceso());
				response = new ResponseEntity<>(activity.getValoresReferenciaEnvio(), HttpStatus.OK);
			} else {
				log.error("Error al iniciar proceso: {}", meta.getMessage());
				response = new ResponseEntity<>(meta.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
		} catch (Exception e) {
			log.error("Error inesperado en inicio proceso seguro: ", e);
			response = new ResponseEntity<>("Error interno del servidor", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		log.info("<<<<<< RESPONSE Inicio Proceso Seguro ### {}{}status \n{} mensaje: {} \\n", 
			contextPath, ROOT_URL, response.getStatusCode(), response.getBody());
		response = new ResponseEntity<>( response.getBody(), HttpStatus.OK);
		return response;
	}
}
