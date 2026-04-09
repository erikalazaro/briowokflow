/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.business.service.catalogs.test;

import java.math.BigDecimal;

import org.junit.Test;

import com.briomax.briobpm.business.service.catalogs.core.IMessagesService;
import com.briomax.briobpm.business.test.base.AbstractCoreTest;
import com.briomax.briobpm.common.core.Constants;
import com.briomax.briobpm.common.core.RetMsg;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Class MessagesServiceTest.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Jun 26, 2020 11:50:00 AM Modificaciones:
 * @since JDK 1.8
 */
@Slf4j
public class MessagesServiceTest extends AbstractCoreTest {

	/** El atributo o variable messages service. */
	@Autowired
	private IMessagesService messagesService;

	/**
	 * Crear una nueva instancia del objeto messages service test.
	 */
	public MessagesServiceTest() {
	}

	/**
	 * Obtener el valor de message test.
	 * @return el message test.
	 */
	@Test
	public void getMessageTest() {
		String message = messagesService.getMessage("MANTENIMIENTO_CATALOGO_CONSULTAR_ERROR", getIdioma());
		log.info("MENSAJE: {}", message);
	}

	/**
	 * Obtener el valor de message test.
	 * @return el message SP test.
	 */
	@Test
	public void getMessage1Test() {
		String message =
			messagesService.getMessage(getDatosAutenticacionTO(), "BACK_MANTENIMIENTO_CATALOGO", 
				"MANTENIMIENTO_CATALOGO_CONSULTAR_ERROR", "@CVE_CATALOGO@|ROL_PROCESO|@CVE_ROL@|CLIENTE");
		log.info("MENSAJE: {}", message);
	}
	
	
	/**
	 * Obtener el valor de message test.
	 * @return el message SP test.
	 */
	@Test
	public void getMessage2Test() {
		String message =
			messagesService.getMessage(getDatosAutenticacionTO(), "BACK_MANTENIMIENTO_CATALOGO", 
				"USUARIO_INCORRECTO", "");
		log.info("MENSAJE: {}", message);
	}
	
	/**
	 * Obtener el valor de message  test.
	 * @return el message SP test.
	 */
	@Test
	public void getMessage3Test() {
		String message =
			messagesService.getMessage(getDatosAutenticacionTO(), "BACK_MANTENIMIENTO_CATALOGO", 
				"VARIABLES_REQUERIDAS_SIN_VALOR", "@MENSAJE_VARIABLES@|PRUEBA DE DATOS");
		log.info("MENSAJE: {}", message);
	}
	
	/**
	 * Obtener el valor de message  test.
	 * @return el message SP test.
	 */
	@Test
	public void getMessage4Test() {
		String message =
			messagesService.getMessage(getDatosAutenticacionTO(), "BACK_MANTENIMIENTO_CATALOGO", 
				"VERSION_NO_NUMERICA", "@CVE_PROCESO@|PRUEBA GENERAL|@VERSION@|1.0");
		log.info("MENSAJE: {}", message);
	}
	
	
	/**
	 * Obtener el valor de message  test.
	 * @return el message SP test.
	 */
	@Test
	public void actualizaSItuacionCorreoTest() {
		RetMsg correos = messagesService.actualizaSItuacionCorreo(
				getDatosAutenticacionTO(),
				"INCIDENCIA-SMI",
				new BigDecimal(1),
				8,
				"CREADO");
		log.info("correos: {}", correos.toString());
	}
	
	
	@Test
	public void documentosIncompletosTest() {
		log.info("documentosIncompletosTest");
		String documentos = messagesService.getMessage(
				getDatosAutenticacionTO(),
				"VALIDA_DOCUMENTO_REQUERIDOS",
				Constants.DOCUMENTOS_REQUERIDOS_SIN_ANEXAR,
				"@CVE_PROCESO@|ALTA_CONTRATISTA_CONTRATANTE|@VERSION@|1|@CVE_INSTANCIA@|202503-000005|@CVE_NODO@|ACTIVIDAD-USUARIO|@ID_NODO@|3|@SECUENCIA_NODO@|1|@LISTA_DOCUMENTOS@|Comprobante de Domicilio Contratista, Constancia de Situación Físcal Contratista, Aviso de Registro REPSE");
		
		log.info("correos: {}", documentos.toString());
	}
}
