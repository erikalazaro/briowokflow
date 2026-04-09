/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.rest.controller;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briomax.briobpm.business.helpers.StVariableDependienteHelper;
import com.briomax.briobpm.business.service.core.IEjecucionActividadService;
import com.briomax.briobpm.business.service.core.IFormularioDinamicoService;
import com.briomax.briobpm.common.core.DAORet;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.transferobjects.SaveSectionTO;
import com.briomax.briobpm.transferobjects.dynamicForm.FormularioDinamico;
import com.briomax.briobpm.transferobjects.in.ActividadTO;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;
import com.briomax.briobpm.transferobjects.in.StVariableDependienteInTO;

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
 * El objetivo de la Class EjecucionActividadRestController.java es ...
 * @author Rigoberto Olvera C.
 * @version 1.0 Fecha de creacion 31/01/2020 01:15:39 PM Modificaciones:
 * @since JDK 1.8
 */
@Tag(name = "EjecucionActividad", description = "Ejecución de una Actividad.")
@RestController
@RequestMapping(EjecucionActividadRestController.ROOT_URL)
@Slf4j
public class EjecucionActividadRestController extends AbstractBbpmController {

	/** Constante para la url del servicio. */
	public static final String ROOT_URL = "/services/ejecucionactividad/";

	/** El atributo o variable Formulario Dinamico Service. */
	@Autowired
	private IFormularioDinamicoService formDinamicoService;

	/** El atributo o variable ejecucion actividad service. */
	@Autowired
	private IEjecucionActividadService ejecucionActividadService;
	
	@Autowired
	private StVariableDependienteHelper stVariableDependienteHelper;

	/**
	 * Formulario dinamico.
	 * @param actividad el actividad.
	 * @return el response entity.
	 */
	@Operation(operationId = "formulariodinamico", summary = "Formulario Dinamico.", description = "Formulario de una Actividad.")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "OK - Successful operation.",
			content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
				array = @ArraySchema(schema = @Schema(description = "Formulario Dinamico", title = "Formulario Dinamico",
					implementation = FormularioDinamico.class))
			)}),
		@ApiResponse(responseCode = "204",
			description = "No Content - The server successfully processed the request, and is not returning any content.",
			content = {@Content(schema = @Schema(implementation = String.class))}),
		@ApiResponse(responseCode = "204", ref = "NoContent"),
		@ApiResponse(responseCode = "400", ref = "BadRequestError"),
		@ApiResponse(responseCode = "401", ref = "UnauthorizedError"),
		@ApiResponse(responseCode = "403", ref = "ForbiddenError"),
		@ApiResponse(responseCode = "404", ref = "NotFoundError"),
		@ApiResponse(responseCode = "405", ref = "MethodNotAllowedError"),
		@ApiResponse(responseCode = "408", ref = "RequestTimeoutError"),
		@ApiResponse(responseCode = "500", description = "Internal Server Error - Processing error.",
			content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
			schema = @Schema(implementation = String.class))})
	})
	@Parameter(name = "actividad", description = "Datos de la Actividad.", required = true,
			schema = @Schema(implementation = ActividadTO.class))
	@SneakyThrows(BrioBPMException.class)
	@PostMapping(value = "formulariodinamico", produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> formularioDinamico(@RequestBody ActividadTO actividad) {
		ResponseEntity<?> response = null;
		RetMsg meta = null;
			DatosAutenticacionTO userSession = getUserSessionBpm();
		log.trace(">>>>>> REQUEST Formulario Dinamico ### {}{}formulariodinamico \nSession Data:\n{} \nData Request:\n{}",
			contextPath, ROOT_URL, converterJsonAsString(userSession), converterJsonAsString(actividad));
			DAORet<FormularioDinamico, RetMsg> metaData = null;
			try {
				metaData = formDinamicoService.obtenerFormularioDinamico(userSession, actividad);
			} catch (BrioBPMException | ParseException e) {
				metaData = null;
			}
			meta = metaData.getMeta();
			log.info("Formulario Dinamico <<< {}", converterJsonAsString(meta));
			
			log.info("\t Dynamic Form final <<< {}",  converterJsonAsString(metaData.getContent()));
			if (meta.getStatus().equalsIgnoreCase("OK")) {
				FormularioDinamico formularioDinamico = metaData.getContent();
				if (formularioDinamico != null && !formularioDinamico.getSections().isEmpty()) {
					response = new ResponseEntity<FormularioDinamico>(formularioDinamico, HttpStatus.OK);
					printerJson("JSON Formulario Dinamico", formularioDinamico);
				}
				else {
					response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
				}
			}
			else {
				response = new ResponseEntity<RetMsg>(metaData.getMeta(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
			log.info("<<<<<< RESPONSE Dynamic Form ### {}{}formulariodinamico \n{}", contextPath, ROOT_URL,
			response.getStatusCode());
		return response;
	}

	/**
	 * Guardar Informacion de la Actividad.
	 * @param dataSections el data sections.
	 * @return el response entity.
	 * @throws ParseException 
	 */
	@Operation(summary = "Guardar Actividad.", description = "Guardar Actividad.")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "OK - Successful operation.",
				content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
						schema = @Schema(implementation = RetMsg.class))}),
		@ApiResponse(responseCode = "201",
				description = "Created - The petition has been completed and has resulted" +
					" in the creation of a new resource.",
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
	@Parameter(name = "dataSections", description = "Datos de las secciones del Formulario.", required = true,
		array = @ArraySchema(schema = @Schema(implementation = SaveSectionTO.class)))
	@SneakyThrows(BrioBPMException.class)
	@PostMapping(value = "guardaractividad", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE},
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<?> guardarActividad(@RequestBody SaveSectionTO dataSections) throws ParseException {
		ResponseEntity<?> response = null;
		RetMsg meta = null;
			DatosAutenticacionTO userSession = getUserSessionBpm();
			log.info(">>>>>> REQUEST Save Activity ### {}{}formulariodinamico \nSession Data:\n{} \nData Request:\n{}",
			contextPath, ROOT_URL, converterJsonAsString(userSession), converterJsonAsString(dataSections));
			meta = ejecucionActividadService.guardarActividad(userSession, dataSections);
			if (meta.getStatus().equalsIgnoreCase("OK")) {
				response = new ResponseEntity<RetMsg>(meta, HttpStatus.CREATED);
			} else {
				response = new ResponseEntity<RetMsg>(meta, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			log.info("<<<<<< RESPONSE Save Activity ### {}{}guardaractividad \n{}", contextPath, ROOT_URL, response);
		return response;
	}

	/**
	 * Guardar y Terminar Actividad.
	 * @param dataSections el data sections.
	 * @return el response entity.
	 * @throws ParseException 
	 */
	@Operation(summary = "Guardar & Teminar Actividad.", description = "Guardar & Terminar Actividad.")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "OK - Successful operation.",
				content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
						schema = @Schema(implementation = RetMsg.class))}),
		@ApiResponse(responseCode = "201",
				description = "Created - The petition has been completed and has resulted" +
					" in the creation of a new resource.",
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
	@Parameter(name = "dataSections", description = "Datos de las secciones del Formulario.", required = true,
	array = @ArraySchema(schema = @Schema(implementation = SaveSectionTO.class)))
	@SneakyThrows(BrioBPMException.class)
	@PostMapping(value = "guardarterminaractividad",
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE},
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<?> guardarTerminarActividad(@RequestBody SaveSectionTO dataSections) throws ParseException {
		ResponseEntity<?> response = null;
		RetMsg meta = null;
			DatosAutenticacionTO userSession = getUserSessionBpm();
			log.info(">>>>>> REQUEST Save & End Activity ### {}{}guardarterminaractividad \nSession Data:\n{} \nData Request:\n{}",
			contextPath, ROOT_URL, converterJsonAsString(userSession), converterJsonAsString(dataSections));
			meta = ejecucionActividadService.guardarTerminarActividad(userSession, dataSections);
			if (meta.getStatus().equalsIgnoreCase("OK")) {
				response = new ResponseEntity<RetMsg>(meta, HttpStatus.CREATED);
			} else {
				response = new ResponseEntity<RetMsg>(meta, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			log.info("<<<<<< RESPONSE Save & End Activity ### {}{}guardarterminaractividad \n{}", contextPath, ROOT_URL,
			response);
		return response;
	}

	/**
	 * Obtener actividad.
	 * @param actividad el actividad.
	 * @return el response entity.
	 */
	@Operation(summary = "Obtener Actividad.", description = "Obtener Actividad.")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "OK - Successful operation.",
			content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
			schema = @Schema(implementation = RetMsg.class))}),
		@ApiResponse(responseCode = "400", ref = "BadRequestError"),
		@ApiResponse(responseCode = "401", ref = "UnauthorizedError"),
		@ApiResponse(responseCode = "403", ref = "ForbiddenError"),
		@ApiResponse(responseCode = "404", ref = "NotFoundError"),
		@ApiResponse(responseCode = "405", ref = "MethodNotAllowedError"),
		@ApiResponse(responseCode = "408", ref = "RequestTimeoutError"),
		@ApiResponse(responseCode = "500", description = "Internal Server Error - Processing error.", content = {
			@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RetMsg.class))})
	})
	@Parameter(name = "actividad", description = "Datos de la Actividad.", required = true,
		schema = @Schema(implementation = ActividadTO.class))
	@SneakyThrows(BrioBPMException.class)
	@PostMapping(value = "obteneractividad", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE},
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<?> obtenerActividad(@RequestBody ActividadTO actividad) {
		ResponseEntity<?> response = null;
		RetMsg meta = null;
		DatosAutenticacionTO userSession = getUserSessionBpm();
		log.info(">>>>>> REQUEST Obtain Activity ### {}{}obteneractividad \nSession Data:\n{} \nData Request:\n{}",
			contextPath, ROOT_URL, converterJsonAsString(userSession), converterJsonAsString(actividad));

		meta = ejecucionActividadService.obtenerActividad(userSession, actividad);
		response = new ResponseEntity<RetMsg>(meta, getCodeHttpStatus(meta));

		log.info("<<<<<< RESPONSE Obtain Activity ### {}{}obteneractividad \n{}", contextPath, ROOT_URL, response);
		return response;		
	}

	/**
	 * Ceder actividad.
	 * @param actividad el actividad.
	 * @return el response entity.
	 */
	@Operation(summary = "Liberar Actividad.", description = "Liberar Actividad.")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "OK - Successful operation.",
				content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
						schema = @Schema(implementation = RetMsg.class))}),
		@ApiResponse(responseCode = "400", ref = "BadRequestError"),
		@ApiResponse(responseCode = "401", ref = "UnauthorizedError"),
		@ApiResponse(responseCode = "403", ref = "ForbiddenError"),
		@ApiResponse(responseCode = "404", ref = "NotFoundError"),
		@ApiResponse(responseCode = "405", ref = "MethodNotAllowedError"),
		@ApiResponse(responseCode = "408", ref = "RequestTimeoutError"),
		@ApiResponse(responseCode = "500", description = "Internal Server Error - Processing error.", content = {
			@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RetMsg.class))})
	})
	@Parameter(name = "actividad", description = "Datos de la Actividad.", required = true,
		schema = @Schema(implementation = ActividadTO.class))
	@SneakyThrows(BrioBPMException.class)
	@PostMapping(value = "liberaractividad", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE},
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<?> cederActividad(@RequestBody ActividadTO actividad) {
		ResponseEntity<?> response = null;
		RetMsg meta = null;
			DatosAutenticacionTO userSession = getUserSessionBpm();
			log.info(">>>>>> REQUEST Liberate Activity ### {}{}obteneractividad \nSession Data:\n{} \nData Request:\n{}",
				contextPath, ROOT_URL, converterJsonAsString(userSession), converterJsonAsString(actividad));
			meta = ejecucionActividadService.liberarActividad(userSession, actividad);
			if (meta.getStatus().equalsIgnoreCase("OK")) {
				response = new ResponseEntity<RetMsg>(meta, HttpStatus.OK);
			} else {
				response = new ResponseEntity<RetMsg>(meta, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			log.info("<<<<<< RESPONSE Liberate Activity ### {}{}liberaractividad \n{}", contextPath, ROOT_URL, response);
		return response;
	}

	/**
	 * Cancelar actividad.
	 * @param actividad el actividad.
	 * @return el response entity.
	 */
	@Operation(summary = "Cancelar Actividad.", description = "Cancelar Actividad.")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "OK - Successful operation.",
				content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
						schema = @Schema(implementation = RetMsg.class))}),
		@ApiResponse(responseCode = "400", ref = "BadRequestError"),
		@ApiResponse(responseCode = "401", ref = "UnauthorizedError"),
		@ApiResponse(responseCode = "403", ref = "ForbiddenError"),
		@ApiResponse(responseCode = "404", ref = "NotFoundError"),
		@ApiResponse(responseCode = "405", ref = "MethodNotAllowedError"),
		@ApiResponse(responseCode = "408", ref = "RequestTimeoutError"),
		@ApiResponse(responseCode = "500", description = "Internal Server Error - Processing error.", content = {
			@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RetMsg.class))})
	})
	@Parameter(name = "actividad", description = "Datos de la Actividad.", required = true,
		schema = @Schema(implementation = ActividadTO.class))
	@SneakyThrows(BrioBPMException.class)
	@PostMapping(value = "cancelaractividad", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE},
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<?> cancelarActividad(@RequestBody ActividadTO actividad) {
		ResponseEntity<?> response = null;
		RetMsg meta = null;
			DatosAutenticacionTO userSession = getUserSessionBpm();
			log.info(">>>>>> REQUEST Cancel Activity ### {}{}cancelaractividad \nSession Data:\n{} \nData Request:\n{}",
				contextPath, ROOT_URL, converterJsonAsString(userSession), converterJsonAsString(actividad));
			meta = ejecucionActividadService.cancelarActividad(userSession, actividad);
			if (meta.getStatus().equalsIgnoreCase("OK")) {
				response = new ResponseEntity<RetMsg>(meta, HttpStatus.OK);
			} else {
				response = new ResponseEntity<RetMsg>(meta, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			log.info("<<<<<< RESPONSE Cancel Activity ### {}{}cancelaractividad \n{}", contextPath, ROOT_URL, response);
		return response;		
	}

	/**
	 * Terminar Actividad.
	 * @param dataSections el data sections.
	 * @return el response entity.
	 * @throws ParseException 
	 */
	@Operation(summary = "Terminar Actividad.", description = "Terminar Actividad.")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "OK - Successful operation.",
				content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
						schema = @Schema(implementation = RetMsg.class))}),
		@ApiResponse(responseCode = "400", ref = "BadRequestError"),
		@ApiResponse(responseCode = "401", ref = "UnauthorizedError"),
		@ApiResponse(responseCode = "403", ref = "ForbiddenError"),
		@ApiResponse(responseCode = "404", ref = "NotFoundError"),
		@ApiResponse(responseCode = "405", ref = "MethodNotAllowedError"),
		@ApiResponse(responseCode = "408", ref = "RequestTimeoutError"),
		@ApiResponse(responseCode = "500", description = "Internal Server Error - Processing error.", content = {
			@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RetMsg.class))})
	})
	@Parameter(name = "actividad", description = "Datos de la Actividad.", required = true,
		schema = @Schema(implementation = ActividadTO.class))
	@SneakyThrows(BrioBPMException.class)
	@PostMapping(value = "terminaractividad", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE},
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<?> terminarActividad(@RequestBody ActividadTO actividad) throws ParseException {
		ResponseEntity<?> response = null;
		RetMsg meta = null;
			DatosAutenticacionTO userSession = getUserSessionBpm();
		log.trace(">>>>>> REQUEST End Activity ### {}{}cancelaractividad \nSession Data:\n{} \nData Request:\n{}",
				contextPath, ROOT_URL, converterJsonAsString(userSession), converterJsonAsString(actividad));
			meta = ejecucionActividadService.terminarActividad(userSession, actividad);
			if (meta.getStatus().equalsIgnoreCase("OK")) {
				response = new ResponseEntity<RetMsg>(meta, HttpStatus.OK);
			} else {
				response = new ResponseEntity<RetMsg>(meta, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		log.trace("<<<<<< RESPONSE End Activity ### {}{}terminaractividad \n{}", contextPath, ROOT_URL, response);
		return response;
	}


	/**
	   * Variable Dependiente.
	   * @param dataSections el data sections. variabledependiente
	   * @return el response entity.
	   */
	  @Operation(summary = "Variable Dependiente.", description = "Variable Dependiente.")
	  @ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "OK - Successful operation.",
	        content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
	            schema = @Schema(implementation = RetMsg.class))}),
	    @ApiResponse(responseCode = "400", ref = "BadRequestError"),
	    @ApiResponse(responseCode = "401", ref = "UnauthorizedError"),
	    @ApiResponse(responseCode = "403", ref = "ForbiddenError"),
	    @ApiResponse(responseCode = "404", ref = "NotFoundError"),
	    @ApiResponse(responseCode = "405", ref = "MethodNotAllowedError"),
	    @ApiResponse(responseCode = "408", ref = "RequestTimeoutError"),
	    @ApiResponse(responseCode = "500", description = "Internal Server Error - Processing error.", content = {
	      @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RetMsg.class))})
	  })
	  @Parameter(name = "actividad", description = "Datos de la Actividad.", required = true,
	    schema = @Schema(implementation = String.class))
	  @SneakyThrows(BrioBPMException.class)
	  @PostMapping(value = "variabledependiente", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE},
	      consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
	  public ResponseEntity<?> variableDependiente(@RequestBody StVariableDependienteInTO in) {
	    ResponseEntity<?> response = null;
	    List<List<String>> meta = null;   
	    log.info(">>>>>> REQUEST End Activity ### {}{}cancelaractividad \nSession Data:\n{} \nData Request:\n{}",
				contextPath, ROOT_URL,converterJsonAsString(in)); 
	      DatosAutenticacionTO userSession = getUserSessionBpm();
	      meta = stVariableDependienteHelper.obtenerVariable(userSession, in);
	      log.info("meta: {} ",meta);
	      if (meta.size() > 0) {
	    	  response = new ResponseEntity<>(meta, HttpStatus.OK);
	      } else {
	        response = new ResponseEntity<>("No hay datos", HttpStatus.INTERNAL_SERVER_ERROR);
	      }
	    log.trace("<<<<<< RESPONSE End Activity ### {}{}variabledependiente \n{}", contextPath, ROOT_URL, response);
	    return response;
	  }
	  
}
