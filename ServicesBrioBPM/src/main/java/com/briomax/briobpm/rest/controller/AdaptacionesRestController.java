/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.rest.controller;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialBlob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.briomax.briobpm.business.helpers.base.ICargaPdfHelper;
import com.briomax.briobpm.business.helpers.base.IConsultaPdfHelper;
import com.briomax.briobpm.business.helpers.base.ITrabajadoresHelper;
import com.briomax.briobpm.business.service.catadaptaciones.CrConsultaRepseService;
import com.briomax.briobpm.business.service.catadaptaciones.core.ICrAvisoIcsoeService;
import com.briomax.briobpm.business.service.catadaptaciones.core.ICrAvisoSisubService;
import com.briomax.briobpm.business.service.catadaptaciones.core.ICrCedulaDetCuotasService;
import com.briomax.briobpm.business.service.catadaptaciones.core.ICrComprobanteCuotasOPService;
import com.briomax.briobpm.business.service.catadaptaciones.core.ICrConsultaRepseService;
import com.briomax.briobpm.business.service.catadaptaciones.core.ICrDeclaracionComplementarioService;
import com.briomax.briobpm.business.service.catadaptaciones.core.ICrDeclaracionProvisionalService;
import com.briomax.briobpm.business.service.catadaptaciones.core.ICrFormatoCuotasOPService;
import com.briomax.briobpm.business.service.catadaptaciones.core.ICrPagoBancarioService;
import com.briomax.briobpm.business.service.catadaptaciones.core.ICrReciboNominaService;
import com.briomax.briobpm.business.service.catadaptaciones.core.ICrRegistroObraService;
import com.briomax.briobpm.business.service.catalogs.CrProgamacionProcesoService;
import com.briomax.briobpm.business.service.catalogs.core.ICrProcesoPeriodicoService;
import com.briomax.briobpm.business.service.catalogs.core.IInVariableProcesoService;
import com.briomax.briobpm.business.service.catalogs.core.IMessagesService;
import com.briomax.briobpm.common.core.Constants;
import com.briomax.briobpm.common.core.DAORet;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.common.exception.ConverterExcepcion;
import com.briomax.briobpm.persistence.entity.CrPdfFiles;
import com.briomax.briobpm.persistence.entity.ParametroGeneral;
import com.briomax.briobpm.persistence.repository.IParametroGeneralRepository;
import com.briomax.briobpm.transferobjects.CargaGridTO;
import com.briomax.briobpm.transferobjects.ComboBoxTO;
import com.briomax.briobpm.transferobjects.PdfGridTO;
import com.briomax.briobpm.transferobjects.TrabajadoresTO;
import com.briomax.briobpm.transferobjects.dynamicForm.Properties;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;
import com.briomax.briobpm.transferobjects.in.EjecutaRepseTO;
import com.briomax.briobpm.transferobjects.repse.ComboBoxProcesosTO;
import com.briomax.briobpm.transferobjects.repse.ConsultaPdfTO;
import com.briomax.briobpm.transferobjects.repse.DocumentoTrabajadorTO;
import com.briomax.briobpm.transferobjects.repse.FiltroAdaptacionesTO;
import com.briomax.briobpm.transferobjects.repse.ListaDocumentosTrabajadorTO;

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
 * El objetivo de la Class AdaptacionesRestController.java es ...
 * @author Sara Ventura
 * @version 1.0 Fecha de creacion Mar 10, 2020 2:05:43 PM Modificaciones:
 * @since JDK 1.8
 */
@Tag(name = "AdaptacionesRestController", description = "Ser de adaptaciones.")
@RestController
@RequestMapping(AdaptacionesRestController.ROOT_URL)
@Slf4j
public class AdaptacionesRestController extends AbstractBbpmController {

	/** Constante para la url del servicio. */
	public static final String ROOT_URL = "/services/adaptaciones/";
	
	/** La Constante DATABASE. */


	private static final String ACUSE_SIROC = "REGISTRO_SIROC";
	private static final String REGISTRO_SIROC_BIM = "REGISTRO_SIROC_BIM";
	private static final String REGISTRO_SIROC_TERM_OBRA = "REGISTRO_SIROC_TERM_OBRA";
	private static final String INCIDENCIAS_SIROC = "INCIDENCIAS_SIROC";
	private static final String ACT_DATOS_SIROC = "ACT_DATOS_SIROC";
	private static final String DEC_IVA_CONTRATISTA = "DEC_IVA_CONTRATISTA";    
	private static final String PAGO_BANCARIO = "COMPROB_PAGO_IVA_E_ISR";
	private static final String ENVIO_RECIBOS_NOMINA = "ENVIO_RECIBOS_NOMINA";
	private static final String SISTEMA_UNICO_AUTODETERMINACION = "SUA_MENSUAL";
	private static final String SISTEMA_UNICO_AUTODETERMINACION_BIM = "SUA_BIMESTRAL";
	private static final String FORMATO_CUOTA_OBRERO_PATRONALES = "LINEA_CAP_SIPARE_COP";    	
	private static final String COMPROBANTE_CUOTAS_OBRERO_PATRONALES = "COMPROBANTE_PAGO_SIPARE";
	private static final String AVISO_ICSOE = "ACUSE_ICSOE_IMSS";  
	private static final String AVISO_SISUB = "ACUSE_SISUB_INFONAVIT";
	private static final String AVISO_REPSE = "AVISO_REPSE";
	private static final String DEC_IVA_CONTRATISTA_COMPLEMENTARIO = "DEC_IVA_CONTRATISTA_COMPLEMENTARIO";

	
	
	
	/** El atributo o variable messages service. */
	@Autowired
	private IMessagesService messagesService;
	
	/** El atributo o variable messages service. */
	@Autowired
	private ITrabajadoresHelper trabajadoresHelper;
	
	/** El atributo o variable repository. */
	@Autowired
	private IInVariableProcesoService repository;
	
	/** El atributo o variable messages service. */
	@Autowired
	private CrProgamacionProcesoService crProgamacionProcesoService;
	
	/** El atributo o variable messages service. */
	@Autowired
	private ICrProcesoPeriodicoService crProcesoPeriodicoService;

	/** El atributo o variable messages service. */
	@Autowired
	private CrConsultaRepseService repseService;
	
	/** El atributo o variable messages service. */
	@Autowired
	private ICargaPdfHelper cargaPdfHelper;
	
	@Autowired 
	private IConsultaPdfHelper consultaPDFHelper;
	
	@Autowired 
	private ICrReciboNominaService crReciboNominaService;
	
	@Autowired 
	private ICrDeclaracionProvisionalService crDeclaracionProvisionalService;
	
	@Autowired 
	private ICrPagoBancarioService crPagoBancarioService;
	
	@Autowired 
	private ICrComprobanteCuotasOPService crComprobanteCuotasOPService;
	
	@Autowired 
	private ICrRegistroObraService registroObraService;
	
	@Autowired 
	private ICrAvisoSisubService avisoSisubService;
	
	@Autowired 
	private ICrAvisoIcsoeService avisoIcsoeService;
	
	@Autowired 
	private ICrFormatoCuotasOPService formatoCuotasOPService;
	
	@Autowired 
	private ICrCedulaDetCuotasService cedulaDetCuotasService;
	
	@Autowired 
	private ICrConsultaRepseService consultaRepseService;
	
	@Autowired 
	private ICrDeclaracionComplementarioService declaracionComplementarioService;
	
	@Value("${path.python}")
    private String rutaPython;
	
	@Value("${path.slash}")
    private String slash;
	
 	@Autowired
	private IParametroGeneralRepository parametroGeneralRepository;	
	
	
	@Operation(summary = "Consulta", description = "Obtiene los contratos de un RFC.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK - Successful operation.", content = {
			@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(description = "Lista de contartos", title = "Lista de contartos.", implementation = ComboBoxTO.class))) }),
			@ApiResponse(responseCode = "204", description = "No Content - The server successfully processed the request, and is not returning any content.", content = {
					@Content(schema = @Schema(implementation = String.class)) }),
			@ApiResponse(responseCode = "400", ref = "BadRequestError"),
			@ApiResponse(responseCode = "401", ref = "UnauthorizedError"),
			@ApiResponse(responseCode = "403", ref = "ForbiddenError"),
			@ApiResponse(responseCode = "404", ref = "NotFoundError"),
			@ApiResponse(responseCode = "405", ref = "MethodNotAllowedError"),
			@ApiResponse(responseCode = "408", ref = "RequestTimeoutError"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error - Processing error.", content = {
					@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = String.class)) }) })
	
	@Parameter(name = "datos", description = "Filtro Trabajadores.", required = true, schema = @Schema(title = "FiltroAdaptacionesTO.",description = "Filtro Trabajadores.", implementation = FiltroAdaptacionesTO.class))
	@SneakyThrows(BrioBPMException.class)

	@PostMapping(value = "getContratoByRfc", produces = MediaType.APPLICATION_JSON_VALUE, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> getContratoByRfc(@RequestBody FiltroAdaptacionesTO datos) {
		ResponseEntity<?> response = null;
		DatosAutenticacionTO userSession = getUserSessionBpm();
		log.trace(">>>>>> REQUEST getTrabajadorByFilter");
		List<ComboBoxTO> list = new ArrayList<ComboBoxTO>();	

		list = repository.getContratosByRfc(userSession, datos.getRfc());
		
		if (list.isEmpty()) {
			response = new ResponseEntity<>(messagesService.getMessage(userSession, "IN_VARIABLE_PROCESO",
					"MANTENIMIENTO_TRABAJADORESACTIVOS_CONSULTAR_ERROR", ""), HttpStatus.NO_CONTENT); // CREAR EN BASE
		} else {
			response = new ResponseEntity<>(list, HttpStatus.OK);
		}
		log.trace("<<<<<< RESPONSE Obtener Procesos x versiones ### {}{}/versiones \n{}", contextPath, ROOT_URL,
				response);
		return response;
	}
	


	@Operation(summary = "Consulta", description = "Obtiene los contratos de un RFC.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK - Successful operation.", content = {
			@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(description = "Lista de contartos", title = "Lista de contartos.", implementation = ComboBoxTO.class))) }),
			@ApiResponse(responseCode = "204", description = "No Content - The server successfully processed the request, and is not returning any content.", content = {
					@Content(schema = @Schema(implementation = String.class)) }),
			@ApiResponse(responseCode = "400", ref = "BadRequestError"),
			@ApiResponse(responseCode = "401", ref = "UnauthorizedError"),
			@ApiResponse(responseCode = "403", ref = "ForbiddenError"),
			@ApiResponse(responseCode = "404", ref = "NotFoundError"),
			@ApiResponse(responseCode = "405", ref = "MethodNotAllowedError"),
			@ApiResponse(responseCode = "408", ref = "RequestTimeoutError"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error - Processing error.", content = {
					@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = String.class)) }) })
	
	@Parameter(name = "datos", description = "Filtro Trabajadores.", required = true, schema = @Schema(title = "FiltroAdaptacionesTO.",description = "Filtro Trabajadores.", implementation = FiltroAdaptacionesTO.class))
	@SneakyThrows(BrioBPMException.class)

	@PostMapping(value = "getFechasHistorico", produces = MediaType.APPLICATION_JSON_VALUE, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> getFechasHistorico(@RequestBody FiltroAdaptacionesTO datos) {
		ResponseEntity<?> response = null;
		DatosAutenticacionTO userSession = getUserSessionBpm();
		log.trace(">>>>>> REQUEST getTrabajadorByFilter");
		List<ComboBoxTO> list = new ArrayList<ComboBoxTO>();	

		list = trabajadoresHelper.getDiasHistorico(userSession, datos.getRfc(), datos.getContrato(), datos.getCveProceso());
		
		if (list.isEmpty()) {
			response = new ResponseEntity<>(messagesService.getMessage(userSession, "CR_TRABAJADOR_HISTORICO",
					"ADAPTACION_TRABAJADORES_CONSULTAR_FECHA_VACIA", ""), HttpStatus.NO_CONTENT); // CREAR EN BASE
		} else {
			response = new ResponseEntity<>(list, HttpStatus.OK);
		}
		log.trace("<<<<<< RESPONSE Obtener Procesos x versiones ### {}{}/versiones \n{}", contextPath, ROOT_URL,
				response);
		return response;
	}
	
	
	@Operation(summary = "Ejecuta REPSE", description = "Ejecuta el ejecutable de REPSE.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK - Successful operation.", content = {
			@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(description = "Lista de contartos", title = "Lista de contartos.", implementation = ComboBoxTO.class))) }),
			@ApiResponse(responseCode = "204", description = "No Content - The server successfully processed the request, and is not returning any content.", content = {
					@Content(schema = @Schema(implementation = String.class)) }),
			@ApiResponse(responseCode = "400", ref = "BadRequestError"),
			@ApiResponse(responseCode = "401", ref = "UnauthorizedError"),
			@ApiResponse(responseCode = "403", ref = "ForbiddenError"),
			@ApiResponse(responseCode = "404", ref = "NotFoundError"),
			@ApiResponse(responseCode = "405", ref = "MethodNotAllowedError"),
			@ApiResponse(responseCode = "408", ref = "RequestTimeoutError"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error - Processing error.", content = {
					@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = String.class)) }) })
	@Parameter(name = "datos", description = "Proceso Trabajadores.", required = true, schema = @Schema(title = "FiltroAdaptacionesTO.",description = "Filtro Proceso.", implementation = EjecutaRepseTO.class))
	@SneakyThrows(BrioBPMException.class)	

	@PostMapping(value = "ejecutaRepse", produces = MediaType.APPLICATION_JSON_VALUE, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> ejecutaRepse(@RequestBody EjecutaRepseTO datos) {
		ResponseEntity<?> response = null;

	    log.trace(">>>>>> REQUEST Ejecutar REPSE con data <<<<<<<<<");
	    RetMsg result = null;
	    log.trace(">>>>>> data: {} <<<<<<<<<", datos.toString());

	    try {
	 	   String primerAtributo = datos.getRazonSocial();
	 	   log.trace(">>>>>> REQUEST Ejecutar REPSE con data: {} <<<<<<<<<", primerAtributo);
	 	   
	 	   if (primerAtributo.length() > 3) {
				result = repseService.correEjecutablePython(primerAtributo);
				 if (result.getStatus().equals(Constants.OK)) {
				    result = RetMsg.builder().status(Constants.OK).message("La Razon Social esta activa.").build();  
				 }else {
				    result = RetMsg.builder().status(Constants.ERROR).message("El proceso no pudo concluir la validacion.").build();  
				 }
	 	   } else {
	 		  result = RetMsg.builder().status(Constants.OK).message("Verifique la Razon Social ya qie no es valida.").build(); 
	 	   }


			 response = new ResponseEntity<>(result, HttpStatus.OK);
		} catch (IOException | InterruptedException e) {
			response = new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		log.trace("<<<<<< RESPONSE Obtener Procesos x versiones ### {}{}/versiones \n{}", contextPath, ROOT_URL,
				response);
		return response;
	}
	

	
	
	
	@Operation(summary = "Consulta Procesos periodicos", description = "Obtiene los procesos periodicos correspondientes a la entidad, localidad e idioma.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK - Successful operation.", content = {
			@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(description = "Lista de contartos", title = "Lista de contartos.", implementation = ComboBoxTO.class))) }),
			@ApiResponse(responseCode = "204", description = "No Content - The server successfully processed the request, and is not returning any content.", content = {
					@Content(schema = @Schema(implementation = String.class)) }),
			@ApiResponse(responseCode = "400", ref = "BadRequestError"),
			@ApiResponse(responseCode = "401", ref = "UnauthorizedError"),
			@ApiResponse(responseCode = "403", ref = "ForbiddenError"),
			@ApiResponse(responseCode = "404", ref = "NotFoundError"),
			@ApiResponse(responseCode = "405", ref = "MethodNotAllowedError"),
			@ApiResponse(responseCode = "408", ref = "RequestTimeoutError"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error - Processing error.", content = {
					@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = String.class)) }) })
	
	@SneakyThrows(BrioBPMException.class)
	@GetMapping(value = "consultaProcesosPeriodicos", produces = MediaType.APPLICATION_JSON_VALUE, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> getProcesosPeriodicos() {
		ResponseEntity<?> response = null;
		DatosAutenticacionTO userSession = getUserSessionBpm();
		log.trace(">>>>>> REQUEST getProcesosPeriodicos");
		List<ComboBoxTO> list = new ArrayList<ComboBoxTO>();	

		list = crProcesoPeriodicoService.getProcesosPeriodicos(userSession);
		
		if (!list.isEmpty()) {
			response = new ResponseEntity<>(list, HttpStatus.OK);
		}
		log.trace("<<<<<< RESPONSE Obtener Procesos periodicos ### {}{}/versiones \n{}", contextPath, ROOT_URL,
				response);
		return response;
	}
	
	
	@Operation(operationId = "cargaPdfs", summary = "validar.", description = "Validar Trabajadores del Excel.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK - Successful operation.", content = {
			@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(description = "Lista de Trabajadores", title = "Lista.", implementation = CargaGridTO.class)) }),
			@ApiResponse(responseCode = "400", ref = "BadRequestError"),
			@ApiResponse(responseCode = "401", ref = "UnauthorizedError"),
			@ApiResponse(responseCode = "403", ref = "ForbiddenError"),
			@ApiResponse(responseCode = "404", ref = "NotFoundError"),
			@ApiResponse(responseCode = "405", ref = "MethodNotAllowedError"),
			@ApiResponse(responseCode = "408", ref = "RequestTimeoutError"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error - Processing error.", content = {
					@Content(mediaType = "", schema = @Schema(implementation = RetMsg.class)) }) })

	@SneakyThrows(BrioBPMException.class)
	@Parameter(name = "data", description = "Datos del Documento.", required = true, 
	schema = @Schema(implementation = ListaDocumentosTrabajadorTO.class))
	@PostMapping(value = "cargaPdfs", produces = MediaType.APPLICATION_JSON_VALUE, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> cargaPdfs(@RequestBody ListaDocumentosTrabajadorTO data, HttpServletRequest request) {
		log.info(">>>>>>> Entro al controller cargaPdfs <<<<<<");

		ResponseEntity<?> response = null;
		DatosAutenticacionTO userSession = getUserSessionBpm();
		PdfGridTO resultu = null;
		List<Properties> columns = new ArrayList<Properties>();
		Properties column = null;
		if(data!=null) {
			log.error(">>>>>>>>>>>>>>>>>>>> data: {} ", data.toString());
		} else {
			log.error(">>>>>>>>>>>>>>>>>>>> data is null");
		}
		List<DocumentoTrabajadorTO> documentos = data.getLista();
		if(documentos!=null) {
			log.error(">>>>>>>>>>>>>>>>>>>> Documentos: {} ", documentos.toString());
		} else {
			log.error(">>>>>>>>>>>>>>>>>>>> Documentos is null");
		}
		
		
		//log.info(">>>>>>>>>>>>>>>>>>>> multiples documentos.size(): {}", data);
		String rutaFile = "";
		//log.info(">>>>>>>>>>>>>>>>>>>> multiples documentos.size(): ");
		
		DocumentoTrabajadorTO document = documentos.get(0);
		String infoFecha = document.getFechaCarga();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date fechaCarga = null;
		try {
			fechaCarga = sdf.parse(infoFecha);
		} catch (ParseException e) {			
			log.error(">>>>>>>>>>>>>>>>>>>> error en SimpleDateFormat : {}", e.getMessage());
		}
		log.info(">>>>>>>>>>>>>>>>>>>> fechaCarga: {}", fechaCarga.toString());
		infoFecha = infoFecha.replace("/","-");
		log.info(">>>>>>>>>>>>>>>>>>>> fechaAjustada: {}", infoFecha);
		
		String file = "";
		String prefixDocumento = userSession.getCveEntidad() + "#" + document.getCveProcesoPeriodico() + "#" + document.getRfc() + "#" + document.getContrato() + "#" +  infoFecha ;
		
		log.error(">>>>>>>>>>>>>>>>>>>> prefixDocumento: {}", prefixDocumento.toString());
		String tipo = document.getCveProcesoPeriodico();
		String paramExtension = tipo + "_" +document.getNomArchivo().substring(document.getNomArchivo().lastIndexOf(".")+1).toUpperCase();
		Optional<ParametroGeneral> parametro = parametroGeneralRepository.findById(paramExtension);
	    ParametroGeneral parGeneral = null;
	    String nombreArchivo = "";
	    if (parametro.isPresent()) {
	      	parGeneral = parametro.get();
	       	nombreArchivo = parGeneral.getValorAlfanumerico();
	    }
	    log.info(">>>>>>>>>>>>>>>>>>>> tipo: {}", tipo);
	    log.info(">>>>>>>>>>>>>>>>>>>> rutaPython: {} ",rutaPython );
		try {		 

			 switch (tipo) {
			 	case ENVIO_RECIBOS_NOMINA:
			 		
			 		rutaFile = rutaPython + "Recibo_nomina";
			 		log.error(">>>>>>>>>>>>>>>>>>>> ENVIO_RECIBOS_NOMINA: ");
					for (DocumentoTrabajadorTO to : documentos) {
						 					 
				 		 file = rutaPython + "Recibo_nomina" + slash + 
				 				prefixDocumento  + "#" +  to.getNomArchivo();
						 		 
				 		 log.info(">>>>>>>>>>>>>>>>>>>> file: " +  file);
				 		 FileOutputStream fos = new FileOutputStream(file);			 		 
				 		 BufferedOutputStream bos = new BufferedOutputStream(fos);
				 		 bos.write(to.getData());
				 		 bos.close();
				         log.info("Archivo creado exitosamente en: " + rutaPython );	
					}
		            resultu = cargaPdfHelper.execEnvioRecibosNomina(userSession, document.getRfc(), document.getContrato(), tipo,  prefixDocumento, fechaCarga, nombreArchivo );
			       		 		 
		            break;
			 	case DEC_IVA_CONTRATISTA:
			 		 rutaFile = rutaPython + "Declaracion_provisional";
					for (DocumentoTrabajadorTO to : documentos) {
				 		 file = rutaPython + "Declaracion_provisional" + slash + 
				 				prefixDocumento  + "#" +  to.getNomArchivo();
	
				 		 FileOutputStream fos = new FileOutputStream(file);			 		 
				 		 BufferedOutputStream bos = new BufferedOutputStream(fos);
				 		 bos.write(to.getData());
			             log.info("Archivo creado exitosamente en: " + rutaPython );	
			             bos.close();
					}
		            resultu = cargaPdfHelper.execDeclaracionProvicionalContratista(userSession, document.getRfc(), document.getContrato(), tipo,  prefixDocumento, fechaCarga, nombreArchivo );
		             
	
		        break;
			 	case PAGO_BANCARIO: 
			 		rutaFile = rutaPython + "Comprobante_pagobancario";
					for (DocumentoTrabajadorTO to : documentos) {
						 file = rutaPython + "Comprobante_pagobancario" + slash + 
			 				prefixDocumento  + "#" +  to.getNomArchivo();

				 		 FileOutputStream fos = new FileOutputStream(file);			 		 
				 		 BufferedOutputStream bos = new BufferedOutputStream(fos);
				 		 bos.write(to.getData());
			             bos.close();
			             log.error("Archivo creado exitosamente en: " + rutaPython );
					}
		            resultu = cargaPdfHelper.execPagoBancario(userSession, document.getRfc(), document.getContrato(), tipo,  prefixDocumento, fechaCarga, nombreArchivo );
		             

             break;
			 	case ACUSE_SIROC: //REGISTRO_SIROC_MENSUAL
			 		log.info(">>>>>>>>>>>>>>>>>>>> ACUSE_SIROC: " );
			 		rutaFile = rutaPython +  "SIROC";
					for (DocumentoTrabajadorTO to : documentos) {
				 		 file = rutaPython + "SIROC" + slash + 
				 				prefixDocumento  + "#" +  to.getNomArchivo();
				 		 FileOutputStream fos = new FileOutputStream(file);			 		 
				 		 BufferedOutputStream bos = new BufferedOutputStream(fos);
				 		 bos.write(to.getData());		      
			             bos.close();
			             log.error("ACUSE_SIROC Archivo creado exitosamente en: " + rutaPython );
					}
		            resultu = cargaPdfHelper.execRegistroObra(userSession, document.getRfc(), document.getContrato(), tipo,  prefixDocumento, fechaCarga, nombreArchivo );
				break;
			 	case REGISTRO_SIROC_BIM: //REGISTRO_SIROC_BIMESTRAL
			 		log.info(">>>>>>>>>>>>>>>>>>>> REGISTRO_SIROC_BIM: " );
			 		 rutaFile = rutaPython +  "SIROC";
					for (DocumentoTrabajadorTO to : documentos) {
				 		 file = rutaPython + "SIROC" + slash + 
				 				prefixDocumento  + "#" +  to.getNomArchivo();
				 		 FileOutputStream fos = new FileOutputStream(file);			 		 
				 		 BufferedOutputStream bos = new BufferedOutputStream(fos);
				 		 bos.write(to.getData());		      
			             bos.close();
			             log.error("REGISTRO_SIROC_BIM Archivo creado exitosamente en: " + rutaPython );
					}
		            resultu = cargaPdfHelper.execRegistroObra(userSession, document.getRfc(), document.getContrato(), tipo,  prefixDocumento, fechaCarga , nombreArchivo);
				break;
			 	case REGISTRO_SIROC_TERM_OBRA: //REGISTRO_SIROC_TERMINO DE OBRA
			 		log.info(">>>>>>>>>>>>>>>>>>>> REGISTRO_SIROC_TERM_OBRA: " );
			 		 rutaFile = rutaPython +  "SIROC";
					for (DocumentoTrabajadorTO to : documentos) {
				 		 file = rutaPython + "SIROC" + slash + 
				 				prefixDocumento  + "#" +  to.getNomArchivo();
				 		 FileOutputStream fos = new FileOutputStream(file);			 		 
				 		 BufferedOutputStream bos = new BufferedOutputStream(fos);
				 		 bos.write(to.getData());		      
			             bos.close();
			             log.error("REGISTRO_SIROC_TERM_OBRA Archivo creado exitosamente en: " + rutaPython );
					}
		            resultu = cargaPdfHelper.execRegistroObra(userSession, document.getRfc(), document.getContrato(), tipo,  prefixDocumento, fechaCarga, nombreArchivo );
				break;
			 	case INCIDENCIAS_SIROC: // INCIDENCIAS SIROC 
			 		log.info(">>>>>>>>>>>>>>>>>>>> INCIDENCIAS_SIROC: " );
			 		 rutaFile = rutaPython +  "SIROC";
					for (DocumentoTrabajadorTO to : documentos) {
				 		 file = rutaPython + "SIROC" + slash + 
				 				prefixDocumento  + "#" +  to.getNomArchivo();
				 		 FileOutputStream fos = new FileOutputStream(file);			 		 
				 		 BufferedOutputStream bos = new BufferedOutputStream(fos);
				 		 bos.write(to.getData());		      
			             bos.close();
			             log.error("INCIDENCIAS_SIROC Archivo creado exitosamente en: " + rutaPython );
					}
		            resultu = cargaPdfHelper.execRegistroObra(userSession, document.getRfc(), document.getContrato(), tipo,  prefixDocumento, fechaCarga, nombreArchivo );
				break;
			 	case ACT_DATOS_SIROC: //ACTUALIZACION DATOS SIROC
			 		log.info(">>>>>>>>>>>>>>>>>>>> ACT_DATOS_SIROC: " );
			 		 rutaFile = rutaPython +  "SIROC";
					for (DocumentoTrabajadorTO to : documentos) {
				 		 file = rutaPython + "SIROC" + slash + 
				 				prefixDocumento  + "#" +  to.getNomArchivo();
				 		 FileOutputStream fos = new FileOutputStream(file);			 		 
				 		 BufferedOutputStream bos = new BufferedOutputStream(fos);
				 		 bos.write(to.getData());		      
			             bos.close();
			             log.error("ACT_DATOS_SIROC Archivo creado exitosamente en: " + rutaPython );
					}
		            resultu = cargaPdfHelper.execRegistroObra(userSession, document.getRfc(), document.getContrato(), tipo,  prefixDocumento, fechaCarga, nombreArchivo );
				break;				
				
			 	case COMPROBANTE_CUOTAS_OBRERO_PATRONALES:
			 		log.info(">>>>>>>>>>>>>>>>>>>> COMPROBANTE_CUOTAS_OBRERO_PATRONALES: " );
			 		rutaFile = rutaPython + "Comprobante_Cuota_Obrero_Patronales";
					for (DocumentoTrabajadorTO to : documentos) {
				 		file = rutaPython + "Comprobante_Cuota_Obrero_Patronales" + slash + 
				 				prefixDocumento  + "#" +  to.getNomArchivo();
				 		 FileOutputStream fos = new FileOutputStream(file);			 		 
				 		 BufferedOutputStream bos = new BufferedOutputStream(fos);
				 		 bos.write(to.getData());
			             log.error("Archivo creado exitosamente en: " + rutaPython );	
			             bos.close();
					}
		            resultu = cargaPdfHelper.execComprobanteCuotaOP(userSession, document.getRfc(), document.getContrato(), tipo,  prefixDocumento, fechaCarga, nombreArchivo );
		             
			 	break;
			 	case FORMATO_CUOTA_OBRERO_PATRONALES:
			 		log.info(">>>>>>>>>>>>>>>>>>>> FORMATO_CUOTA_OBRERO_PATRONALES: " );
			 		rutaFile = rutaPython + "Formato_Cuota_Obrero_Patronales" ;
					for (DocumentoTrabajadorTO to : documentos) {
				 		file = rutaPython + "Formato_Cuota_Obrero_Patronales" + slash + 
				 				prefixDocumento  + "#" +  to.getNomArchivo();
				 		 FileOutputStream fos = new FileOutputStream(file);			 		 
				 		 BufferedOutputStream bos = new BufferedOutputStream(fos);
				 		 bos.write(to.getData());
			             log.error("Archivo creado exitosamente en: " + rutaPython );
			             bos.close();
					}
		            resultu = cargaPdfHelper.execFormatoCuotaOP(userSession, document.getRfc(), document.getContrato(), tipo,  prefixDocumento, fechaCarga, nombreArchivo );
				break;
			 	case SISTEMA_UNICO_AUTODETERMINACION: // SUA mensual- CEdula de determinaciOn de cuotas
			 		log.info(">>>>>>>>>>>>>>>>>>>> SISTEMA_UNICO_AUTODETERMINACION: " );
			 		rutaFile = rutaPython +  "SUA";
					for (DocumentoTrabajadorTO to : documentos) {
				 		file = rutaPython + "SUA" + slash + 
				 				prefixDocumento  + "#" +  to.getNomArchivo();
				 		 FileOutputStream fos = new FileOutputStream(file);			 		 
				 		 BufferedOutputStream bos = new BufferedOutputStream(fos);
				 		 bos.write(to.getData());
			             log.error("Archivo creado exitosamente en: " + rutaPython );
			             bos.close();
					}
		            resultu = cargaPdfHelper.execSUA(userSession, document.getRfc(), document.getContrato(), tipo,  prefixDocumento, fechaCarga, nombreArchivo );
				break;
			 	case SISTEMA_UNICO_AUTODETERMINACION_BIM:  //SUA bimestral-Cedula de determinacion COP, aportaciones y amortizaciones
			 		log.info(">>>>>>>>>>>>>>>>>>>> SISTEMA_UNICO_AUTODETERMINACION_BIM: " );
			 		rutaFile = rutaPython +  "SUA";
					for (DocumentoTrabajadorTO to : documentos) {
				 		file = rutaPython + "SUA" + slash + 
				 				prefixDocumento  + "#" +  to.getNomArchivo();
				 		 FileOutputStream fos = new FileOutputStream(file);			 		 
				 		 BufferedOutputStream bos = new BufferedOutputStream(fos);
				 		 bos.write(to.getData());
			             log.error("Archivo creado exitosamente en: " + rutaPython );
			             bos.close();
					}
		            resultu = cargaPdfHelper.execSUA(userSession, document.getRfc(), document.getContrato(), tipo,  prefixDocumento, fechaCarga, nombreArchivo );
				break;				
				
			 	case AVISO_REPSE:
			 		 rutaFile = rutaPython + "Aviso_Repse"; 
					for (DocumentoTrabajadorTO to : documentos) {
				 		 file = rutaPython + "Aviso_Repse" + slash + 
				 				prefixDocumento  + "#" +  to.getNomArchivo();
				 		 FileOutputStream fos = new FileOutputStream(file);			 		 
				 		 BufferedOutputStream bos = new BufferedOutputStream(fos);
				 		 bos.write(to.getData());
			             log.error("Archivo creado exitosamente en: " + rutaPython );	
			             bos.close();
					}
		            resultu = cargaPdfHelper.execAvisoRepse(userSession, document.getRfc(), document.getContrato(), tipo,  prefixDocumento, fechaCarga, nombreArchivo );
		             
				break;
			 	case DEC_IVA_CONTRATISTA_COMPLEMENTARIO:
			 		log.info(">>>>>>>>>>>>>>>>>>>> DEC_IVA_CONTRATISTA_COMPLEMENTARIO: " );
			 		 rutaFile = rutaPython + "Declaracion_provisional_complementaria";
					for (DocumentoTrabajadorTO to : documentos) {
				 		 file = rutaPython + "Declaracion_provisional_complementaria" + slash + 
				 				prefixDocumento  + "#" +  to.getNomArchivo();
				 		 FileOutputStream fos = new FileOutputStream(file);			 		 
				 		 BufferedOutputStream bos = new BufferedOutputStream(fos);
				 		 bos.write(to.getData());
			             log.error("Archivo creado exitosamente en: " + rutaPython );	
			             bos.close();
					}
		            resultu = cargaPdfHelper.execDeclaracionProvicionalContratistaComplementario(userSession, document.getRfc(), document.getContrato(), tipo,  prefixDocumento, fechaCarga, nombreArchivo );
		             
				break;
			 	case AVISO_ICSOE:
			 		log.error(">>>>>>>>>>>>>>>>>>>> AVISO_ICSOE: " );
			 		 rutaFile = rutaPython + "Aviso_ICSOE";
			 		log.info(">>>>>>>>>>>>>>>>>>>> rutaPython AVISO_ICSOE: {} ",rutaFile );
					for (DocumentoTrabajadorTO to : documentos) {						
						 file = rutaPython + "Aviso_ICSOE" + slash + 
				 				prefixDocumento  + "#" +  to.getNomArchivo();
						 //log.info(">>>>>>  Data AVISO_ICSOE:\n{}", converterJsonAsString(to));
						 try {
				 		 FileOutputStream fos = new FileOutputStream(file);			 		 
				 		 BufferedOutputStream bos = new BufferedOutputStream(fos);
				 		 bos.write(to.getData());
			             log.error("Archivo creado exitosamente en: " + rutaPython );	
			             bos.close();
						 }  catch(Exception e) {
							 log.error("Error al escribir el archivo AVISO_ICSOE: {}", e.getMessage());
							 throw e; // Rethrow para que el manejo de errores en el nivel superior capture esta excepción
						 }
					}
		            resultu = cargaPdfHelper.execAvisoIcsoe(userSession, document.getRfc(), document.getContrato(), tipo,  prefixDocumento, fechaCarga, nombreArchivo );
		             
		             
				break;
			 	case AVISO_SISUB:
			 		log.error(">>>>>>>>>>>>>>>>>>>> AVISO_SISUB: " );
			 		 rutaFile = rutaPython + "Aviso_SISUB";
					for (DocumentoTrabajadorTO to : documentos) {
				 		 file = rutaPython + "Aviso_SISUB" + slash + 
				 				prefixDocumento  + "#" +  to.getNomArchivo();
				 		 try {
				 		 FileOutputStream fos = new FileOutputStream(file);			 		 
					 		 BufferedOutputStream bos = new BufferedOutputStream(fos);
					 		 bos.write(to.getData());
				             log.error("Archivo creado exitosamente en: " + rutaPython );
				             bos.close();
				 		}  catch(Exception e) {
							 log.error("Error al escribir el archivo AVISO_SISUB: {}", e.getMessage());
							 throw e; // Rethrow para que el manejo de errores en el nivel superior capture esta excepción
						 }
					}
		            resultu = cargaPdfHelper.execAvisoSisub(userSession, document.getRfc(), document.getContrato(), tipo,  prefixDocumento, fechaCarga, nombreArchivo );
		             
				break;
			 	default:
				break;
			}
			 
			  log.info(">>>>>>> listaDatos: {}", resultu.getColumns().size());
			  response = new ResponseEntity<>(resultu, HttpStatus.OK);		    
				
			} catch (IOException ex) { // | ConverterExcepcion
				log.error("cargaPdf BrioException:", ex.getMessage());
				response = new ResponseEntity<>(resultu, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		cargaPdfHelper.deleteFile(rutaFile);
		log.trace("<<<<<< RESPONSE Registrar parametro ### {}{}registrar pdfs \n{}", contextPath, ROOT_URL, response);
		return response;
	}

	
	/**
	 * Servicios para la la consulta y carga de trabjadores.
	 * 
	 * @return el response entity.
	 */
	@Operation(summary = "Consulta", description = "Obtiene registros de trabajadores.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK - Successful operation.", content = {
			@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(description = "Reporte trabajadores", title = "Reporte trabajadores.", implementation = TrabajadoresTO.class))) }),
			@ApiResponse(responseCode = "204", description = "No Content - The server successfully processed the request, and is not returning any content.", content = {
					@Content(schema = @Schema(implementation = String.class)) }),
			@ApiResponse(responseCode = "400", ref = "BadRequestError"),
			@ApiResponse(responseCode = "401", ref = "UnauthorizedError"),
			@ApiResponse(responseCode = "403", ref = "ForbiddenError"),
			@ApiResponse(responseCode = "404", ref = "NotFoundError"),
			@ApiResponse(responseCode = "405", ref = "MethodNotAllowedError"),
			@ApiResponse(responseCode = "408", ref = "RequestTimeoutError"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error - Processing error.", content = {
					@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = String.class)) }) })
	
	@Parameter(name = "datos", description = "Filtro Trabajadores.", required = true, schema = @Schema(title = "FiltroAdaptacionesTO.",description = "Filtro Trabajadores.", implementation = FiltroAdaptacionesTO.class))
	@SneakyThrows(BrioBPMException.class)

	@PostMapping(value = "getTrabajadorByFilter", produces = MediaType.APPLICATION_JSON_VALUE, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> getTrabajadorByFilter(@RequestBody FiltroAdaptacionesTO datos) {
		ResponseEntity<?> response = null;
		DatosAutenticacionTO userSession = getUserSessionBpm();
		log.trace(">>>>>> REQUEST getTrabajadorByFilter");
		List<TrabajadoresTO> list = new ArrayList<TrabajadoresTO>();
		
		String contrato = datos.getContrato();
		String[] info = contrato.split("\\|"); //proceso|instancia|contrato
		log.trace(">>>>>> REQUEST parametro contrato:,{}",contrato);

		if (datos.getTipo().equals("A")) {
			list = trabajadoresHelper.getTrabajadorActual(userSession, datos.getRfc(), info[2], info[1], info[0]);
		} else {
			list = trabajadoresHelper.getTrabajadorHistorico(userSession, datos.getRfc(), info[2], datos.getFecha(), info[0]);
		}
		
		if (list.isEmpty()) {
			response = new ResponseEntity<>(messagesService.getMessage(userSession, "ADAPTACION_TRABAJADORES",
					"ADAPTACION_TRABAJADORES_CONSULTAR_VACIA", ""), HttpStatus.NO_CONTENT); 
		} else {
			response = new ResponseEntity<>(list, HttpStatus.OK);
		}
		log.trace("<<<<<< RESPONSE Obtener Procesos x versiones ### {}{}/versiones \n{}", contextPath, ROOT_URL,
				response);
		return response;
	}
	
	@Operation(operationId = "validarArchivo", summary = "validar.", description = "Validar Trabajadores del Excel.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK - Successful operation.", content = {
			@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(description = "Lista de Trabajadores", title = "Lista.", implementation = CargaGridTO.class)) }),
			@ApiResponse(responseCode = "400", ref = "BadRequestError"),
			@ApiResponse(responseCode = "401", ref = "UnauthorizedError"),
			@ApiResponse(responseCode = "403", ref = "ForbiddenError"),
			@ApiResponse(responseCode = "404", ref = "NotFoundError"),
			@ApiResponse(responseCode = "405", ref = "MethodNotAllowedError"),
			@ApiResponse(responseCode = "408", ref = "RequestTimeoutError"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error - Processing error.", content = {
					@Content(mediaType = "", schema = @Schema(implementation = RetMsg.class)) }) })

	@SneakyThrows(BrioBPMException.class)
	@Parameter(name = "document", description = "Datos del Documento.", required = true, 
	schema = @Schema(implementation = DocumentoTrabajadorTO.class))
	@PostMapping(value = "cargaExcelTrabajadores", produces = MediaType.APPLICATION_JSON_VALUE, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> cargaExcelTrabajadores(@RequestBody DocumentoTrabajadorTO document, HttpServletRequest request) {
		log.info(">>>>>>> Entro al controller <<<<<<");

		ResponseEntity<?> response;
		DatosAutenticacionTO userSession = getUserSessionBpm();
		CargaGridTO listaDatos = null;
		
		int numberOfSheet = 1;	
		try {
			 log.error(">>>>>>>>>>>>>>>>>>>> document.getData(): " + document.getData().length);		
			 InputStream targetStream = new ByteArrayInputStream(document.getData());
			
			 log.error(">>>>>>>>>>>>>>>>>>>> entre en servicio upload<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
			 listaDatos = trabajadoresHelper.upload(userSession, targetStream, numberOfSheet, document.getContrato(), document.getRfc());

			  log.info(">>>>>>> listaDatos: {}", listaDatos.getGrid().size());
			  response = new ResponseEntity<>(listaDatos, HttpStatus.OK);		    
				
			} catch (IOException | ConverterExcepcion ex) { // | ConverterExcepcion
				log.error("BrioException:", ex.getStackTrace());
				//response = new ResponseEntity<>(listaDatos, HttpStatus.INTERNAL_SERVER_ERROR);
				response = new ResponseEntity<>(messagesService.getMessage(userSession, "ADAPTACION_TRABAJADORES",
						"ADAPTACION_TRABAJADORES_VALIDA_CARGA_ERROR", ""), HttpStatus.NO_CONTENT); 
			}
		 
		log.trace("<<<<<< RESPONSE Registrar parametro ### {}{}/registrar \n{}", contextPath, ROOT_URL, response);
		return response;
	}
	
	/**
	 * Realiza la actualización de trabajadores.
	 * 
	 * @return el response entity.
	 */
	@Operation(summary = "Actualizar", description = "Actualiza los trabajadores.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK - Successful operation.", content = {
			@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(description = "Resultado de la Carga", title = "Carga.", implementation = RetMsg.class)) }),
			@ApiResponse(responseCode = "204", description = "No Content - The server successfully processed the request, and is not returning any content.", content = {
					@Content(schema = @Schema(implementation = String.class)) }),
			@ApiResponse(responseCode = "400", ref = "BadRequestError"),
			@ApiResponse(responseCode = "401", ref = "UnauthorizedError"),
			@ApiResponse(responseCode = "403", ref = "ForbiddenError"),
			@ApiResponse(responseCode = "404", ref = "NotFoundError"),
			@ApiResponse(responseCode = "405", ref = "MethodNotAllowedError"),
			@ApiResponse(responseCode = "408", ref = "RequestTimeoutError"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error - Processing error.", content = {
					@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = String.class)) }) })
	
	@Parameter(name = "datos", description = "Trabajadores.", required = true, schema = @Schema(title = "CargaGridTO.",description = "Trabajadores para actualizar.", implementation = CargaGridTO.class))
	@SneakyThrows(BrioBPMException.class)

	@PostMapping(value = "actualizaTrabajadores", produces = MediaType.APPLICATION_JSON_VALUE, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> actualizaTrabajadores(@RequestBody CargaGridTO datos) {
		ResponseEntity<?> response = null;
		DatosAutenticacionTO userSession = getUserSessionBpm();
		log.trace(">>>>>> REQUEST actualizaTrabajadores");
		RetMsg result = null;

		String estatus = trabajadoresHelper.actualizaTrabajadores(userSession, datos);
		if (estatus.equals("OK")) {
			result = RetMsg.builder()
					.status("OK")
					.message(messagesService.getMessage(userSession, "ADAPTACION_TRABAJADORES",
							"ADAPTACION_TRABAJADORES_CARGA_EXITO", ""))
					.build();	
		} else {
			result = RetMsg.builder()
					.status("ERROR")
					.message(messagesService.getMessage(userSession, "ADAPTACION_TRABAJADORES",
							"ADAPTACION_TRABAJADORES_CARGA_ERROR", ""))
					.build();	
		}
		
		response = new ResponseEntity<>(result, HttpStatus.OK);
		
		log.trace("<<<<<< RESPONSE Obtener Procesos x versiones ### {}{}/versiones \n{}", contextPath, ROOT_URL,
				response);
		return response;
	}
	
	// ajuste de nuevos filtro y pantalla
	@Operation(summary = "Consulta", description = "Obtiene los contratos del RFC del proceso.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK - Successful operation.", content = {
			@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(description = "Lista de contartos", title = "Lista de contartos.", implementation = ComboBoxTO.class))) }),
			@ApiResponse(responseCode = "204", description = "No Content - The server successfully processed the request, and is not returning any content.", content = {
					@Content(schema = @Schema(implementation = String.class)) }),
			@ApiResponse(responseCode = "400", ref = "BadRequestError"),
			@ApiResponse(responseCode = "401", ref = "UnauthorizedError"),
			@ApiResponse(responseCode = "403", ref = "ForbiddenError"),
			@ApiResponse(responseCode = "404", ref = "NotFoundError"),
			@ApiResponse(responseCode = "405", ref = "MethodNotAllowedError"),
			@ApiResponse(responseCode = "408", ref = "RequestTimeoutError"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error - Processing error.", content = {
					@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = String.class)) }) })
	
	@Parameter(name = "datos", description = "Filtro Trabajadores.", required = true, schema = @Schema(title = "ConsultaPdfTO.",description = "Filtro Proceso.", implementation = ConsultaPdfTO.class))
	@SneakyThrows(BrioBPMException.class)

	@PostMapping(value = "getContratoProceso", produces = MediaType.APPLICATION_JSON_VALUE, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> getContratoProceso(@RequestBody ConsultaPdfTO datos) { //ConsultaPdfTO
		ResponseEntity<?> response = null;
		DatosAutenticacionTO userSession = getUserSessionBpm();
		log.trace(">>>>>> REQUEST getContratoProceso");
		List<ComboBoxProcesosTO> list = new ArrayList<ComboBoxProcesosTO>();	

		list = crProgamacionProcesoService.getContrato(userSession, datos.getActividad());
		
		if (list.isEmpty()) {
			response = new ResponseEntity<>(messagesService.getMessage(userSession, "CR_TRABAJADOR_HISTORICO",
					"MANTENIMIENTO_FRCHAS_CONSULTAR_ERROR", ""), HttpStatus.NO_CONTENT); // CREAR EN BASE
		} else {
			response = new ResponseEntity<>(list, HttpStatus.OK);
		}
		log.trace("<<<<<< RESPONSE Obtener Procesos x versiones ### {}{}/versiones \n{}", contextPath, ROOT_URL,
				response);
		return response;
	}
	
	@Operation(summary = "Consulta Procesos periodicos", description = "Obtiene los documentos.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK - Successful operation.", content = {
			@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(description = "Lista de documentos", title = "Lista de documentos.", implementation = Integer.class))) }),
			@ApiResponse(responseCode = "204", description = "No Content - The server successfully processed the request, and is not returning any content.", content = {
					@Content(schema = @Schema(implementation = String.class)) }),
			@ApiResponse(responseCode = "400", ref = "BadRequestError"),
			@ApiResponse(responseCode = "401", ref = "UnauthorizedError"),
			@ApiResponse(responseCode = "403", ref = "ForbiddenError"),
			@ApiResponse(responseCode = "404", ref = "NotFoundError"),
			@ApiResponse(responseCode = "405", ref = "MethodNotAllowedError"),
			@ApiResponse(responseCode = "408", ref = "RequestTimeoutError"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error - Processing error.", content = {
					@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = String.class)) }) })
	
	@SneakyThrows(BrioBPMException.class)
	@Parameter(name = "datos", description = "Datos del Documento.", required = true, schema = @Schema(implementation = ConsultaPdfTO.class))
	@PostMapping(value = "getProcesosPeriodicosByRfcContrato", produces = MediaType.APPLICATION_JSON_VALUE, consumes = {
			MediaType.APPLICATION_JSON_VALUE })

	public ResponseEntity<?> getProcesosPeriodicosByRfcContrato(@RequestBody ConsultaPdfTO datos) {
		ResponseEntity<?> response = null;
		DatosAutenticacionTO userSession = getUserSessionBpm();
		log.trace(">>>>>> REQUEST getSecuencuaDocumentos");
		List<ComboBoxTO> list = new ArrayList<ComboBoxTO>();
		list = crProcesoPeriodicoService.getProcesosPeriodicosByRfcContrato(userSession, datos);
        log.trace(">>>>>> REQUEST getRfcProcesoPeriodicos list.size(): " + list.size());        
		if (list.size() > 0) {
			response = new ResponseEntity<>(list, HttpStatus.OK);
		} else {
			list = new ArrayList<ComboBoxTO>();
			response = new ResponseEntity<>(list, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		log.trace("<<<<<< RESPONSE Obtener Procesos periodicos ### {}{}/versiones \n{}", contextPath, ROOT_URL,
				response);
		return response;
	}
	
	
	@Operation(operationId = "consultaPDF", summary = "consulta.", description = "Consulta los PDF.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK - Successful operation.", content = {
			@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(description = "Lista de Trabajadores", title = "Lista.", implementation = PdfGridTO.class)) }),
			@ApiResponse(responseCode = "400", ref = "BadRequestError"),
			@ApiResponse(responseCode = "401", ref = "UnauthorizedError"),
			@ApiResponse(responseCode = "403", ref = "ForbiddenError"),
			@ApiResponse(responseCode = "404", ref = "NotFoundError"),
			@ApiResponse(responseCode = "405", ref = "MethodNotAllowedError"),
			@ApiResponse(responseCode = "408", ref = "RequestTimeoutError"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error - Processing error.", content = {
					@Content(mediaType = "", schema = @Schema(implementation = RetMsg.class)) }) })

	@SneakyThrows(BrioBPMException.class)
	@Parameter(name = "datos", description = "Datos del Documento.", required = true, schema = @Schema(implementation = ConsultaPdfTO.class))
	@PostMapping(value = "consultaPdf", produces = MediaType.APPLICATION_JSON_VALUE, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> consultaPdf(@RequestBody ConsultaPdfTO datos) {
        log.info(">>>>>>> Entro al controller consultaPdf <<<<<<");

        ResponseEntity<?> response = null;
        DatosAutenticacionTO userSession = getUserSessionBpm();

        PdfGridTO result = crProgamacionProcesoService.getTabla(userSession, datos);
        
        log.info(">>>>>>> listaDatos: {}", result.getColumns().size());
        response = new ResponseEntity<>(result, HttpStatus.OK);

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
 	@Parameter(name = "datos", description = "Filtros del Documento.", required = true, schema = @Schema(implementation = ConsultaPdfTO.class))
 	@PostMapping(value = "downloadProcesoPeriodicos", produces = MediaType.APPLICATION_JSON_VALUE, consumes = {MediaType.APPLICATION_JSON_VALUE})
 	@ResponseStatus(value=HttpStatus.OK)
 	@ResponseBody
 	public void downloadProcesoPeriodicos(@RequestBody ConsultaPdfTO datos, HttpServletRequest request, HttpServletResponse response) {
 		try {    
 		    
 			DatosAutenticacionTO userSession = getUserSessionBpm();
 			log.info(">>>>>> REQUEST Descargar downloadProcesoPeriodicos ### {}{} \nSession Data:\n{} \nRequest Data:\n{}",
 				contextPath, ROOT_URL, converterJsonAsString(userSession), converterJsonAsString(datos));
 			
 			DAORet<CrPdfFiles, RetMsg> metaData = consultaPDFHelper.leeDocumentoBinario(userSession, datos);
 			
 			if (metaData.getMeta().getStatus().toUpperCase().equalsIgnoreCase("OK")) {

 				CrPdfFiles attachment = metaData.getContent();
				response.setContentType("application/pdf");
				response.setHeader("Content-Disposition", "attachment; filename=\"" + attachment.getNombreDocumento() + "\""); 				
			    try {
			    	
			        // Convierte el byte array a Blob para facilitar el manejo del contenido binario
			    	Blob archivoBinarioBlob = new SerialBlob(attachment.getArchivoBinario());

	            	log.debug("\t-----ssssssssssssssssssss {}", (int) archivoBinarioBlob.length());
	            	byte[] data = archivoBinarioBlob.getBytes(1, (int) archivoBinarioBlob.length());
			        FileCopyUtils.copy(data, response.getOutputStream());

			        
	            } catch (ArrayIndexOutOfBoundsException | SQLException exGral) {
	            	 throw new BrioBPMException("Error al convertir byte[] a Blob", exGral);
	            } 
				
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
 	
 	
	@Operation(operationId = "estatuProcesoPeriodico", summary = "estatus.", description = "consulta estatus de proceso.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK - Successful operation.", content = {
			@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(description = "estatus", title = "obtener.", implementation = ConsultaPdfTO.class)) }),
			@ApiResponse(responseCode = "400", ref = "BadRequestError"),
			@ApiResponse(responseCode = "401", ref = "UnauthorizedError"),
			@ApiResponse(responseCode = "403", ref = "ForbiddenError"),
			@ApiResponse(responseCode = "404", ref = "NotFoundError"),
			@ApiResponse(responseCode = "405", ref = "MethodNotAllowedError"),
			@ApiResponse(responseCode = "408", ref = "RequestTimeoutError"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error - Processing error.", content = {
					@Content(mediaType = "", schema = @Schema(implementation = RetMsg.class)) }) })

	@SneakyThrows(BrioBPMException.class)
	@Parameter(name = "datos", description = "Datos del Documento.", required = true, schema = @Schema(implementation = ConsultaPdfTO.class))
	@PostMapping(value = "estatuProcesoPeriodico", produces = MediaType.APPLICATION_JSON_VALUE, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> estatuProcesoPeriodico(@RequestBody ConsultaPdfTO datos) {
        log.error(">>>>>>> Entro al controller consultaEstatusProcesoPeriodico <<<<<<");

        ResponseEntity<?> response = null;
        DatosAutenticacionTO userSession = getUserSessionBpm();

        String result = crProgamacionProcesoService.getEstatusProcesoPeriodico(userSession, datos);
        datos.setActividad(result);
        
        log.error(">>>>>>> estatus: {}", result);
        response = new ResponseEntity<>(datos, HttpStatus.OK);
        log.error(">>>>>>> estatus: {}", response.toString());
        return response;
    }
	
	@Operation(operationId = "eliminaPdf", summary = "validar.", description = "Validar Trabajadores del Excel.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK - Successful operation.", content = {
			@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(description = "Lista de Trabajadores", title = "Lista.", implementation = CargaGridTO.class)) }),
			@ApiResponse(responseCode = "400", ref = "BadRequestError"),
			@ApiResponse(responseCode = "401", ref = "UnauthorizedError"),
			@ApiResponse(responseCode = "403", ref = "ForbiddenError"),
			@ApiResponse(responseCode = "404", ref = "NotFoundError"),
			@ApiResponse(responseCode = "405", ref = "MethodNotAllowedError"),
			@ApiResponse(responseCode = "408", ref = "RequestTimeoutError"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error - Processing error.", content = {
					@Content(mediaType = "", schema = @Schema(implementation = RetMsg.class)) }) })

	@SneakyThrows(BrioBPMException.class)
	@Parameter(name = "data", description = "Datos del Documento.", required = true, 
	schema = @Schema(implementation = ListaDocumentosTrabajadorTO.class))
	@PostMapping(value = "eliminaPdf", produces = MediaType.APPLICATION_JSON_VALUE, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> eliminaPdf(@RequestBody ListaDocumentosTrabajadorTO data, HttpServletRequest request) {
		log.info(">>>>>>> Entro al controller eliminaPdfs <<<<<<");

		ResponseEntity<?> response = null;

		DatosAutenticacionTO userSession = getUserSessionBpm();
		// String tipo = data.getLista().get(0).getCveProcesoPeriodico();
		// 
		boolean resultado = false;
		try {		
			 log.info(">>>>>>>>>>>>>>>>>>>> data.getLista().size(): " );		
			 
			 if (data.getLista().size() == 0) {
				 log.info(">>>>>>>>>>>>>>>>>>>> Lista vacia " );
				 response = new ResponseEntity<>("Lista vacia", HttpStatus.NO_CONTENT); 
				 
			 } else {
				 log.info(">>>>>>>>>>>>>>>>>>>>va a entrar eliminaCarga " );
				 //log.info(">>>>>>>>>>>>>>>>>>>>userSession {} ", userSession.toString()); );
				 //log.info(">>>>>>>>>>>>>>>>>>>>data {} ", data.toString() );
				 resultado = cargaPdfHelper.eliminaCarga(userSession,  data);
				 if (!resultado) {
					 log.info(">>>>>>>>>>>>>>>>>>>>No se pudo eliminar la carga " );
					 response = new ResponseEntity<>("No se pudo eliminar la carga",  HttpStatus.NOT_MODIFIED); 
				 } else {
					 log.info(">>>>>>>>>>>>>>>>>>>>Carga eliminada correctamentea " );
					 response = new ResponseEntity<>("Carga eliminada correctamente ", HttpStatus.OK); 
				 }
			 }		
				
			} catch (Exception ex) { // | ConverterExcepcion
				log.info("eliminaPdf BrioException:", ex.getMessage());
				response = new ResponseEntity<>("No se pudo eliminar la carga",  HttpStatus.NOT_MODIFIED);
			}
		log.info(">>>>>>>>>>>>>>>>>>>>termina proceso, {} ", response.toString() );
	    return response;
	}
}


