/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.rest.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.briomax.briobpm.business.service.catalogs.core.IMessagesService;
import com.briomax.briobpm.business.service.core.IDocumentosService;
import com.briomax.briobpm.common.core.Constants;
import com.briomax.briobpm.common.core.DAORet;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;
import com.briomax.briobpm.transferobjects.in.DocumentoMultipleTO;
import com.briomax.briobpm.transferobjects.in.DocumentoTO;

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
 * El objetivo de la Class DocumentosRestController.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Mar 10, 2020 2:05:43 PM Modificaciones:
 * @since JDK 1.8
 */
@Tag(name = "Documentos", description = "Documentos de una Actividad.")
@RestController
@RequestMapping(DocumentosRestController.ROOT_URL)
@Slf4j
public class DocumentosRestController extends AbstractBbpmController {

	/** Constante para la url del servicio. */
	public static final String ROOT_URL = "/services/documentos/";

	/** El atributo o variable documentos service. */
	@Autowired
	private IDocumentosService documentosService;

	/** El atributo o variable messages service. */
	@Autowired
	private IMessagesService messagesService;
	
	/**
	 * Guardar documentos.
	 * @param documentos el objeto que contiene múltiples documentos.
	 * @param request la solicitud HTTP.
	 * @return la entidad de respuesta con el resultado de la operación.
	 */
	@Operation(summary = "Guardar.", description = "Guardar uno o más Documentos.")
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
	    @ApiResponse(responseCode = "500", description = "Internal Server Error - Error durante el procesamiento.",
	        content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
	            schema = @Schema(implementation = RetMsg.class))})
	})
	@Parameter(name = "documentos", description = "Datos de los Documentos.", required = true,
	    schema = @Schema(implementation = DocumentoMultipleTO.class))
	@SneakyThrows(BrioBPMException.class)
	@PostMapping(value = "guardar", produces = MediaType.APPLICATION_JSON_VALUE, consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> guardarDocumento(@RequestBody DocumentoMultipleTO documentoMultipleTO, HttpServletRequest request) {
	    ResponseEntity<?> response = null;
	    RetMsg meta = null;

	    if (documentoMultipleTO == null) {
            log.error("DocumentoMultipleTO es nulo");
            return ResponseEntity.badRequest().body("DocumentoMultipleTO es nulo");
        }
        
        List<DocumentoTO> documents = documentoMultipleTO.getDocumentos();
        if (documents == null) {
            log.error("La lista de documentos es nula");
            return ResponseEntity.badRequest().body("La lista de documentos es nula");
        }
        
	    // Obtiene la sesión de usuario BPM.
	    DatosAutenticacionTO userSession = getUserSessionBpm();


        // Log del request con la información de la sesión y los datos del documento.
        log.trace(">>>>>> REQUEST Guardar Documento ### {}{}guardar \nSession Data:\n{} \nRequest Data:\n{}",
            contextPath, ROOT_URL, converterJsonAsString(userSession), converterJsonAsString(documents));

        // Llama al servicio para guardar el documento binario asociado a un nodo.
        meta = documentosService.guardaDocumentoBinarioNodo(userSession, documents);

        // Log del response con la meta información resultante del guardado.
        log.debug("RESPONSE Guardar Documento: {}", meta);

        // Evalúa el estado del resultado y genera la respuesta HTTP adecuada.
        if (meta.getStatus().toUpperCase().equalsIgnoreCase("OK")) {
            response = new ResponseEntity<>(meta.getMessage(), HttpStatus.CREATED);
        } else {
            response = new ResponseEntity<>(meta.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
          
        }

	        // Log adicional del response.
	        log.trace("                {}", response);
	        log.debug("<<<<<< RESPONSE Guardar Documento: {} ", meta);

	    // Retorna la respuesta final.
	    return response;
	}


	/**
	 * Borrar documento.
	 * @param document el document.
	 * @return el response entity.
	 */
	@Operation(summary = "Eliminar.", description = "Eliminar un Documento.")
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
	@Parameter(name = "document", description = "Datos del Documento.", required = true, 
		schema = @Schema(implementation = DocumentoTO.class))
	@SneakyThrows(BrioBPMException.class)
	@PostMapping(value = "borrar", produces = MediaType.APPLICATION_JSON_VALUE, consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> borrarDocumento(@RequestBody DocumentoTO document) {
		ResponseEntity<?> response = null;
		RetMsg meta = null;
			DatosAutenticacionTO userSession = getUserSessionBpm();
			log.trace(">>>>>> REQUEST Borrar Documento ### {}{}guardar \nSession Data:\n{} \nRequest Data:\n{}",
				contextPath, ROOT_URL, converterJsonAsString(userSession), converterJsonAsString(document));
			meta = documentosService.borraDocumentoBinarioNodo(userSession, document);
			if (meta.getStatus().toUpperCase().equalsIgnoreCase("OK")) {
				response = new ResponseEntity<>(meta.getMessage(), HttpStatus.CREATED);
			} else {
				response = new ResponseEntity<>(meta.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			}

		log.trace("                {}", response);
		log.debug("<<<<<< RESPONSE Borrar Documento: {} ", meta);
		return response;
	}

//	/**
//	 * Leer documento.
//	 * @param document el document.
//	 * @param request el request.
//	 * @param response el response.
//	 * @return el response entity.
//	 */
//	@Operation(summary = "Descargar.", description = "Descargar un Documento.")
//	@ApiResponses(value = {
//		@ApiResponse(responseCode = "200", description = "OK - Successful operation..", content = {@Content}),
//		@ApiResponse(responseCode = "400", ref = "BadRequestError"),
//		@ApiResponse(responseCode = "401", ref = "UnauthorizedError"),
//		@ApiResponse(responseCode = "403", ref = "ForbiddenError"),
//		@ApiResponse(responseCode = "404", ref = "NotFoundError"),
//		@ApiResponse(responseCode = "405", ref = "MethodNotAllowedError"),
//		@ApiResponse(responseCode = "408", ref = "RequestTimeoutError"),
//		@ApiResponse(responseCode = "500", ref = "InternalServerError")
//	})
//	@Parameter(name = "document", description = "Datos del Documento.", required = true, 
//		schema = @Schema(implementation = DocumentoTO.class))
//	@PostMapping(value = "download", produces = MediaType.APPLICATION_JSON_VALUE, consumes = {MediaType.APPLICATION_JSON_VALUE})
//	@ResponseStatus(value=HttpStatus.OK)
//	@ResponseBody
//	public void leerDocumento(@RequestBody DocumentoTO document, HttpServletRequest request,
//		HttpServletResponse response) {
//		try {
//			DatosAutenticacionTO userSession = getUserSessionBpm();
//			log.trace(">>>>>> REQUEST Descargar Documento ### {}{}guardar \nSession Data:\n{} \nRequest Data:\n{}",
//				contextPath, ROOT_URL, converterJsonAsString(userSession), converterJsonAsString(document));
//			DAORet<DocumentoTO, RetMsg> metaData = documentosService.leeDocumentoBinarioNodo(userSession, document);
//			if (metaData.getMeta().getStatus().toUpperCase().equalsIgnoreCase("OK")) {
//				DocumentoTO attachment =  metaData.getContent();
//				if (attachment.getData() != null) {
//					log.debug("{} {} {}", attachment.getSecDocumento(), attachment.getContentType(),
//						attachment.getNomArchivo());
//					response.setContentType(attachment.getContentType());
//					response.setHeader("Content-Disposition", "attachment; filename=\"" + attachment.getNomArchivo() +
//							"\"");
//					FileCopyUtils.copy(attachment.getData(), response.getOutputStream());
//					response.flushBuffer();
//				}
//			} else {
//				log.error("{}", metaData.getMeta());
//			}
//		}
//		catch (BrioBPMException brioEx) {
//			log.error(brioEx.getMensaje(), brioEx);
//		}
//		catch (IOException ioEx) {
//			log.error(ioEx.getMessage(), ioEx);
//		}
//	}
	
	/**
	 * Crea Renglon de Nuevo Documento.
	 * @param documentos el objeto que contiene múltiples documentos.
	 * @param request la solicitud HTTP.
	 * @return la entidad de respuesta con el resultado de la operación.
	 */
	@Operation(summary = "Crear Renglón de Documento", description = "Guardar uno o más documentos y crear un nuevo renglón en la base de datos.")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "201",
	            description = "Created - La petición ha sido completada y ha creado un nuevo renglón para un nuevo documento.",
	        content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
	            schema = @Schema(implementation = RetMsg.class))}),
	    @ApiResponse(responseCode = "400", ref = "BadRequestError"),
	    @ApiResponse(responseCode = "401", ref = "UnauthorizedError"),
	    @ApiResponse(responseCode = "403", ref = "ForbiddenError"),
	    @ApiResponse(responseCode = "404", ref = "NotFoundError"),
	    @ApiResponse(responseCode = "405", ref = "MethodNotAllowedError"),
	    @ApiResponse(responseCode = "408", ref = "RequestTimeoutError"),
	    @ApiResponse(responseCode = "500", description = "Internal Server Error - Error durante el procesamiento.",
	        content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
	            schema = @Schema(implementation = RetMsg.class))})
	})
	@Parameter(name = "documentoTO", description = "Objeto que contiene los datos del documento para crear un nuevo renglón.", required = true,
    schema = @Schema(implementation = DocumentoTO.class))
	@SneakyThrows(BrioBPMException.class)
	@PostMapping(value = "crearNuevo", produces = MediaType.APPLICATION_JSON_VALUE, consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> crearNuevoRenglon(@RequestBody DocumentoTO documentoTO) {
	    ResponseEntity<?> response = null;
	    RetMsg meta = null;

	    // Verifica si el objeto documentoTO es nulo y retorna un error en caso de serlo.
	    if (documentoTO == null) {
            log.error("documentoTO es nulo");
            return ResponseEntity.badRequest().body("documentoTO es nulo");
        }
                
	    // Obtiene la sesión de usuario BPM para obtener información como la clave de la entidad.
	    DatosAutenticacionTO userSession = getUserSessionBpm();
	    String cveEntidad = userSession.getCveEntidad();

	    // Realiza un log detallado del request, incluyendo datos de la sesión y del documento.
        log.trace(">>>>>> REQUEST Guardar Documento ### {}{}guardar \nSession Data:\n{} \nRequest Data:\n{}",
            contextPath, ROOT_URL, converterJsonAsString(userSession), converterJsonAsString(documentoTO));

        // Llama al servicio para crear un nuevo renglón para el documento.
        meta = documentosService.creaNuevoRenglon(cveEntidad, documentoTO);

        // Realiza un log del response que incluye la meta información resultante del guardado.
        log.debug("RESPONSE Crear Renglón Documento: {}", meta);

        // Evalúa el estado del resultado y genera la respuesta HTTP adecuada según el estado.
        if (meta.getStatus().toUpperCase().equalsIgnoreCase("OK")) {
            response = new ResponseEntity<>(meta.getMessage(), HttpStatus.CREATED);
        } else {
            response = new ResponseEntity<>(meta.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
          
        }
	        // Log adicional del response.
	        log.trace("                {}", response);
	        log.debug("<<<<<< RESPONSE Crear Renglón Documento: {} ", meta);

	    // Retorna la respuesta final con el resultado de la operación.
	    return response;
	}
	
	
	/**
	 * Elimina Renglón de Documento.
	 * 
	 * @param documentoTO el objeto que contiene los datos del documento cuyo renglón se va a eliminar.
	 * @param request la solicitud HTTP.
	 * @return la entidad de respuesta con el resultado de la operación.
	 */
	@Operation(summary = "Eliminar Renglón de Documento", description = "Elimina uno o más renglones de documentos en la base de datos.")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "200",
	            description = "OK - La petición ha sido completada y se han eliminado los renglones de documentos.",
	        content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
	            schema = @Schema(implementation = RetMsg.class))}),
	    @ApiResponse(responseCode = "400", ref = "BadRequestError"),
	    @ApiResponse(responseCode = "401", ref = "UnauthorizedError"),
	    @ApiResponse(responseCode = "403", ref = "ForbiddenError"),
	    @ApiResponse(responseCode = "404", ref = "NotFoundError"),
	    @ApiResponse(responseCode = "405", ref = "MethodNotAllowedError"),
	    @ApiResponse(responseCode = "408", ref = "RequestTimeoutError"),
	    @ApiResponse(responseCode = "500", description = "Internal Server Error - Error durante el procesamiento.",
	        content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
	            schema = @Schema(implementation = RetMsg.class))})
	})
	@Parameter(name = "documentoTO", description = "Objeto que contiene los datos del documento para eliminar un renglón.", required = true,
	schema = @Schema(implementation = DocumentoTO.class))
	@SneakyThrows(BrioBPMException.class)
	@PostMapping(value = "eliminar", produces = MediaType.APPLICATION_JSON_VALUE, consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> eliminarRenglon(@RequestBody DocumentoTO documentoTO) {
	    ResponseEntity<?> response = null;
	    RetMsg meta = null;

	    // Verifica si el objeto documentoTO es nulo y retorna un error en caso de serlo.
	    if (documentoTO == null) {
	        log.error("documentoTO es nulo");
	        return ResponseEntity.badRequest().body("documentoTO es nulo");
	    }
	            
	    // Obtiene la sesión de usuario BPM para obtener información como la clave de la entidad.
	    DatosAutenticacionTO userSession = getUserSessionBpm();
	    String cveEntidad = userSession.getCveEntidad();

	    // Realiza un log detallado del request, incluyendo datos de la sesión y del documento.
	    log.trace(">>>>>> REQUEST Eliminar Documento ### {}{}eliminar \nSession Data:\n{} \nRequest Data:\n{}",
	        contextPath, ROOT_URL, converterJsonAsString(userSession), converterJsonAsString(documentoTO));

	    // Llama al servicio para eliminar el renglón del documento.
	    meta = documentosService.eliminarRenglon(cveEntidad, documentoTO);

	    // Realiza un log del response que incluye la meta información resultante de la eliminación.
	    log.debug("RESPONSE Eliminar Renglón Documento: {}", meta);

	    // Evalúa el estado del resultado y genera la respuesta HTTP adecuada según el estado.
	    if (meta.getStatus().equalsIgnoreCase("OK")) {
	        response = new ResponseEntity<>(meta.getMessage(), HttpStatus.OK);
	    } else {
	        String warningMessage = "ERROR, NO ES POSIBLE ELIMINAR ESTE RENGLÓN";
	        log.warn(warningMessage + ": {}", meta.getMessage());
	        response = new ResponseEntity<>(warningMessage, HttpStatus.INTERNAL_SERVER_ERROR);
	    }

	    // Log adicional del response.
	    log.trace("                {}", response);
	    log.debug("<<<<<< RESPONSE Eliminar Renglón Documento: {} ", meta);

	    // Retorna la respuesta final con el resultado de la operación.
	    return response;
	}

   	/**
 	 * Leer documento.
 	 * @param document el document.
 	 * @param request el request.
 	 * @param response el response.
 	 * @return el response entity.
 	 */
 	@Operation(summary = "Descargar.", description = "Descargar un Documento.")
 	@ApiResponses(value = {
 		@ApiResponse(responseCode = "200", description = "OK - Successful operation..", content = {@Content}),
 		@ApiResponse(responseCode = "400", ref = "BadRequestError"),
 		@ApiResponse(responseCode = "401", ref = "UnauthorizedError"),
 		@ApiResponse(responseCode = "403", ref = "ForbiddenError"),
 		@ApiResponse(responseCode = "404", ref = "NotFoundError"),
 		@ApiResponse(responseCode = "405", ref = "MethodNotAllowedError"),
 		@ApiResponse(responseCode = "408", ref = "RequestTimeoutError"),
 		@ApiResponse(responseCode = "500", ref = "InternalServerError")
 	})
 	@Parameter(name = "document", description = "Datos del Documento.", required = true, 
 		schema = @Schema(implementation = DocumentoTO.class))
 	@PostMapping(value = "download", produces = MediaType.APPLICATION_JSON_VALUE, consumes = {MediaType.APPLICATION_JSON_VALUE})
 	@ResponseStatus(value=HttpStatus.OK)
 	@ResponseBody
 	public void leerDocumento(@RequestBody DocumentoTO document, HttpServletRequest request,
 		HttpServletResponse response) {
 		try {
 			DatosAutenticacionTO userSession = getUserSessionBpm();
 			log.trace(">>>>>> REQUEST Descargar Documento ### {}{}guardar \nSession Data:\n{} \nRequest Data:\n{}",
 				contextPath, ROOT_URL, converterJsonAsString(userSession), converterJsonAsString(document));
 			DAORet<DocumentoTO, RetMsg> metaData = documentosService.leeDocumentoBinarioNodo(userSession, document);
 			if (metaData.getMeta().getStatus().toUpperCase().equalsIgnoreCase("OK")) {

				DocumentoTO attachment = metaData.getContent();
				log.debug("{} {} {}", attachment.getSecDocumento(), attachment.getContentType(), attachment.getNomArchivo());
				response.setContentType(attachment.getContentType());
				response.setHeader("Content-Disposition", "attachment; filename=\"" + attachment.getNomArchivo() + "\"");
				FileCopyUtils.copy(attachment.getData(), response.getOutputStream());
				response.flushBuffer();

 			} else {
 				log.error("{}", metaData.getMeta());
 			}
 		}
 		catch (BrioBPMException brioEx) {
 			log.error(brioEx.getMensaje(), brioEx);
 		}
 		catch (IOException ioEx) {
 			log.error(ioEx.getMessage(), ioEx);
 		}
 	}
	
	/**
	 * Leer documento.
	 * @param document el document.
	 * @param request el request.
	 * @param response el response.
	 * @return el response entity.
	 */
	@Operation(summary = "Descargar.", description = "Descargar un Documento.")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "OK - Successful operation..", content = {@Content}),
		@ApiResponse(responseCode = "400", ref = "BadRequestError"),
		@ApiResponse(responseCode = "401", ref = "UnauthorizedError"),
		@ApiResponse(responseCode = "403", ref = "ForbiddenError"),
		@ApiResponse(responseCode = "404", ref = "NotFoundError"),
		@ApiResponse(responseCode = "405", ref = "MethodNotAllowedError"),
		@ApiResponse(responseCode = "408", ref = "RequestTimeoutError"),
		@ApiResponse(responseCode = "500", ref = "InternalServerError")
	})
	@Parameter(name = "document", description = "Datos del Documento.", required = true, 
		schema = @Schema(implementation = DocumentoTO.class))
	@PostMapping(value = "listaNombreDocumento", produces = MediaType.APPLICATION_JSON_VALUE, consumes = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseStatus(value=HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<?> listaNombreDocumento(@RequestBody DocumentoTO document, HttpServletRequest request,
		HttpServletResponse response) {
		ResponseEntity<?> result = null;
		try {
						
			DatosAutenticacionTO userSession = getUserSessionBpm();
			log.trace(">>>>>> REQUEST Descargar Documento ### {}{}guardar \nSession Data:\n{} \nRequest Data:\n{}",
				contextPath, ROOT_URL, converterJsonAsString(userSession), converterJsonAsString(document));
			
			List<String> list = documentosService.leeNombreDocumentos(userSession, document);
			log.info("------list: " + list.size());

			
			if (list.isEmpty()) {
				result = new ResponseEntity<>(messagesService.getMessage(userSession,  Constants.LEE_DOCUMENTO_BINARIO_NODO,
		                Constants.NO_EXISTE_DOCUMENTO_PARA_NODO, ""), HttpStatus.NO_CONTENT); // CREAR EN BASE
			} else {
				result = new ResponseEntity<>(list, HttpStatus.OK);
			}
		}
		catch (BrioBPMException brioEx) {
			log.error(brioEx.getMensaje(), brioEx);
			result = new ResponseEntity<>(brioEx.getMensaje(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		log.info("------result: " + result.toString());
		return result;
	}
	
	

}
