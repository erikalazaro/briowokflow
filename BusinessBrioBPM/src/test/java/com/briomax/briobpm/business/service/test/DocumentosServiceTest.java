/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */
package com.briomax.briobpm.business.service.test;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.fail;

import com.briomax.briobpm.business.service.core.IDocumentosService;
import com.briomax.briobpm.business.test.base.AbstractCoreTest;
import com.briomax.briobpm.common.core.DAORet;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;
import com.briomax.briobpm.transferobjects.in.DocumentoTO;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;


/**
 * El objetivo de la Class DocumentosServiceTest.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Mar 11, 2020 5:33:51 PM Modificaciones:
 * @since JDK 1.8
 */
@Slf4j
public class DocumentosServiceTest extends AbstractCoreTest {

	/** El atributo o variable documentos service. */
	@Autowired
	private IDocumentosService documentosService;

	/**
	 * Lee documento binario nodo test.
	 * @throws BrioBPMException la brio BPM exception.
	 */
//	@Test
//	public void leeDocumentoBinarioNodoTest() throws BrioBPMException {
//		DatosAutenticacionTO session = getDatosAutenticacionTO();
//		session.setCveUsuario("Gabriela.Arias");
//		printerJson(session);
//		DocumentoTO document = getDocumentoTo();
//		document.setCveProceso("INCIDENCIA-SMI");
//		document.setVersion("1");
//		document.setCveInstancia("202112-000001");
//		document.setCveNodo("ACTIVIDAD-USUARIO");
//		document.setIdNodo(8);
//		document.setSecDocumento(1);
//		printerJson(document);
//		DAORet<DocumentoTO, RetMsg> metaData = documentosService.leeDocumentoBinarioNodo(session, document);
//		log.info("{}", metaData.getMeta());
//		log.info("{}", metaData.getContent());
//		if (metaData.getMeta().getStatus().equalsIgnoreCase("ERROR")) {
//			fail("Fail Leer Documento");
//		}
//	}

	/**
	 * Borra documento binario nodo test.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void borraDocumentoBinarioNodoTest() throws BrioBPMException {
		DatosAutenticacionTO session = getDatosAutenticacionTO();
		session.setCveUsuario("Gabriela.Arias");
		printerJson(session);
		DocumentoTO document = getDocumentoTo();
		document.setSecDocumento(1);
		printerJson(document);
		RetMsg metaData = documentosService.borraDocumentoBinarioNodo(session, document);
		log.info("{}", metaData);
//		if (metaData.getStatus().equalsIgnoreCase("ERROR")) {
//			fail("Fail Borrar Documento");
//		}
	}
	
	/**
	 * Guarda documento binario nodo test.
	 * @throws BrioBPMException la brio BPM exception.
	 * @throws IOException Indica que se ha producido una excepción de E/S.
	 * @throws URISyntaxException la URI syntax exception.
	 */
//	@Test
//	public void guardaDocumentoBinarioNodoTest() throws BrioBPMException, IOException, URISyntaxException {
//		DatosAutenticacionTO session = getDatosAutenticacionTO();
//		session.setCveUsuario("Francisco.Rodriguez");
//		printerJson(session);
//		DocumentoTO document = getDocumentoTo();
//		document.setCveInstancia("202005-000005");
//		document.setCveAreaTrabajo("AREA_UNICA");
//		document.setIdNodo(1);
//		document.setSecNodo(2);
//		document.setCveNodo("ACTIVIDAD-USUARIO");
//		document.setCveProceso("DUMMY");
//		document.setCveRol("ROL DUMMY 1");
//		document.setSecDocumento(1);
//		String fileName = "INE.jpg";
//		document.setContentType("image/jpg");
//		document.setNomArchivo(fileName);
//		document.setData(getContenido(fileName));
//		printerJson(document);
//		//List<DocumentoTO> documents = new ArrayList<DocumentoTO>();
//		//documents.add(document);
//		RetMsg metaData = documentosService.guardaDocumentoBinarioNodo(session, document);
//		log.info("{}", metaData);
////		if (metaData.getStatus().equalsIgnoreCase("ERROR")) {
////			fail("Fail Guardar Documento");
////		}
//	}
	
	/**
	 * Obtener el valor de contenido.
	 * @return el contenido.
	 * @throws URISyntaxException la URI syntax exception.
	 * @throws IOException Indica que se ha producido una excepción de E/S.
	 */
	private byte[] getContenido(String fileName) throws URISyntaxException, IOException {
		Path path = Paths.get(getClass().getClassLoader().getResource(".\\docs\\" + fileName).toURI());
		byte[] contenido = Files.readAllBytes(path);
		return contenido;
	}
	
	/**
	 * Documento binario nodo test.
	 * @throws BrioBPMException la brio BPM exception.
	 * @throws IOException Indica que se ha producido una excepción de E/S.
	 * @throws URISyntaxException la URI syntax exception.
	 */
//	@Test
//	public void documentoBinarioNodoTest() throws BrioBPMException, IOException, URISyntaxException {
//		DatosAutenticacionTO session = getDatosAutenticacionTO();
//		session.setCveUsuario("Usuario.Contratista");
//		printerJson(session);
//		DocumentoTO document = getDocumentoTo();
//		document.setSecDocumento(1);
//		printerJson(document);
//		//
//		//documentosService.borraDocumentoBinarioNodo(session, document);
//		//
//		document.setCveProceso("ALTA_CONTRATISTA_CONTRATANTE");
//		document.setVersion("1");
//		document.setCveNodo("ACTIVIDAD-USUARIO");
//		document.setIdNodo(3);
//		document.setCveInstancia("202408-000006");
//		document.setSecDocumento(1);
//		document.setSecNodo(1);
//		document.setContentType("application/pdf");
//		document.setNomArchivo("PREUBA.pdf");
//        //document.setData(getContenido("PREUBA.pdf"));
//        //List<DocumentoTO> documents = new ArrayList<DocumentoTO>();
//		//documents.add(document);
//		RetMsg metaDataSave = documentosService.guardaDocumentoBinarioNodo(session, document);
//		log.info("GUARDAR DOCUMENTO: {}", metaDataSave);
//		if (metaDataSave.getStatus().equalsIgnoreCase("ERROR")) {
//			fail("Fail Guardar Documento");
//		}
//		DAORet<DocumentoTO, RetMsg> metaDataRead = documentosService.leeDocumentoBinarioNodo(session, document);
//		log.info("LEER DOCUMENTO: {}", metaDataRead.getMeta());
//		log.info("{}", metaDataRead.getContent());
//		if (metaDataRead.getMeta().getStatus().equalsIgnoreCase("ERROR")) {
//			fail("Fail Leer Documento");
//		}
//		RetMsg metaDataBorrar = documentosService.borraDocumentoBinarioNodo(session, document);
//		log.info("BORRAR DOCUMENTO: {}", metaDataBorrar);
//		if (metaDataBorrar.getStatus().equalsIgnoreCase("ERROR")) {
//			fail("Fail Borrar Documento");
//		}
//	}
}
