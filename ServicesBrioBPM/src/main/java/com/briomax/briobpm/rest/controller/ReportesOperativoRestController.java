/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.rest.controller;

import java.util.List;

import com.briomax.briobpm.business.service.core.IReportesOperativosService;
import com.briomax.briobpm.common.core.DAORet;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.transferobjects.ReporteActividadesTO;
import com.briomax.briobpm.transferobjects.ReporteProcesosTO;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
 * El objetivo de la Class ReportesRestController.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Oct 6, 2020 6:12:03 PM Modificaciones:
 * @since JDK 1.8
 */
@Tag(name = "Reportes Operativos", description = "Reportes.")
@RestController
@RequestMapping(ReportesOperativoRestController.ROOT_URL)
@Slf4j
public class ReportesOperativoRestController extends AbstractBbpmController {

	/** Constante para la url del servicio. */
	public static final String ROOT_URL = "/services/reportes/operativos/";

	/** El atributo o variable reporte service. */
	@Autowired
	private IReportesOperativosService reporteService;

	/**
	 * Procesos.
	 * @return el response entity.
	 */
	@Operation(summary = "Reporte por Procesos", description = "Reporte por Procesos.")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "OK - Successful operation.",
				content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
						array = @ArraySchema(schema = @Schema(description = "Reporte Procesos",
								title = "Reporte Procesos.", implementation = ReporteProcesosTO.class)))
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
	@SneakyThrows(BrioBPMException.class)
	@GetMapping(value = "procesos", produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> procesos() {
		ResponseEntity<?> response = null;
		RetMsg meta = null;
			DatosAutenticacionTO userSession = getUserSessionBpm();
		log.trace(">>>>>> REQUEST Reporte Operativo por Procesos ### {}{}procesos \nSession Data:\n{}", contextPath, ROOT_URL,
			converterJsonAsString(userSession));
			DAORet<List<ReporteProcesosTO>, RetMsg> metaData = reporteService.obtenerReporteProcesos(userSession);
			List<ReporteProcesosTO> reporte = metaData.getContent();
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
		log.trace("<<<<<< RESPONSE Reporte Operativo por Procesos ### {}{}procesos \n{}", contextPath, ROOT_URL,
			response);
		return response;
	}

	/**
	 * Actividad.
	 * @return el response entity.
	 */
	@Operation(summary = "Reporte por Actividad", description = "Reporte por Actividad.")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "OK - Successful operation.",
				content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
						array = @ArraySchema(schema = @Schema(description = "Reporte Procesos",
								title = "Reporte Procesos.", implementation = ReporteActividadesTO.class)))
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
	@SneakyThrows(BrioBPMException.class)
	@GetMapping(value = "actividades", produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> actividades() {
		ResponseEntity<?> response = null;
		RetMsg meta = null;
		DatosAutenticacionTO userSession = getUserSessionBpm();
		log.trace(">>>>>> REQUEST Reporte Operativo por Actividades ### {}{}actividades \nSession Data:\n{}",
			contextPath, ROOT_URL, converterJsonAsString(userSession));
			DAORet<List<ReporteActividadesTO>, RetMsg> metaData = reporteService.obtenerReporteActividades(userSession);
			List<ReporteActividadesTO> reporte = metaData.getContent();
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
		log.trace("<<<<<< RESPONSE Reporte Operativo por Actividades ### {}{}actividades \n{}", contextPath, ROOT_URL,
			response);
		return response;
	}

}
