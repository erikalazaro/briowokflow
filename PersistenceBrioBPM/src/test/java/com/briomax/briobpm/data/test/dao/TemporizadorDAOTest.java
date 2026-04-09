/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.data.test.dao;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.data.test.base.AbstractDataTest;
import com.briomax.briobpm.persistence.dao.base.ITemporizadorDAO;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Class TemporizadorDAOTest.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Oct 12, 2020 7:33:44 PM Modificaciones:
 * @since JDK 1.8
 */
@Slf4j
public class TemporizadorDAOTest extends AbstractDataTest {

	/** El atributo o variable temporizador DAO. */
	@Autowired
	private ITemporizadorDAO temporizadorDAO;

	/**
	 * Actividades test.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void actividadesTest() throws BrioBPMException {
		RetMsg ret = temporizadorDAO.actividades();
		log.info("{}", ret);
		assertEquals("OK", ret.getStatus().toUpperCase());
	}

	/**
	 * Vencimiento documentos test.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void vencimientoDocumentosTest() throws BrioBPMException {
		//RetMsg resp = temporizadorDAO.vencimientoDocumentos();
		log.info("SP_VENCIMIENTO_DOCUMENTOS {}");
	}

	/**
	 * Procesa mensaje test.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void procesaMensajeTest() throws BrioBPMException {
		//RetMsg resp = temporizadorDAO.procesaMensajes();
		log.info("SP_PROCESA_MENSAJES {}");
	}

}
