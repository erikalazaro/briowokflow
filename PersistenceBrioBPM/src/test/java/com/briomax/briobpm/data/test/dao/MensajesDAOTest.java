/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */
package com.briomax.briobpm.data.test.dao;

import org.junit.Test;

import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.data.test.base.AbstractDataTest;
import com.briomax.briobpm.persistence.dao.base.IMensajesDAO;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Class MensajesDAOTest.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Apr 13, 2020 11:27:08 AM Modificaciones:
 * @since JDK 1.8
 */
@Slf4j
public class MensajesDAOTest extends AbstractDataTest {

	/** El atributo o variable emails DAO. */
	@Autowired
	private IMensajesDAO dao;

	/**
	 * Obtener el valor de message test.
	 * @return el message test.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void getMessageTest() throws BrioBPMException {
		log.info("{} {} {} {}", getUser(), getEntidad(), getLocalidad(), getIdioma());
		final RetMsg ret = dao.getMessage(getUser(), getEntidad(), getLocalidad(), getIdioma(),
			"BACK", "ACCION_INVALIDA_SOBRE_NODO", "");
		log.info("MENSAJE: {}", ret);
	}

}
