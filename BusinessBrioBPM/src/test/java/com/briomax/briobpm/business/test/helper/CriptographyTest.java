/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */
package com.briomax.briobpm.business.test.helper;

import org.junit.Test;

import com.briomax.briobpm.business.helpers.base.ICriptography;
import com.briomax.briobpm.business.test.base.AbstractCoreTest;
import com.briomax.briobpm.common.exception.BrioBPMException;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Class CriptographyTest.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Jul 27, 2020 5:54:37 PM Modificaciones:
 * @since JDK 1.8
 */
@Slf4j
public class CriptographyTest extends AbstractCoreTest {

	/** El atributo o variable criptography. */
	@Autowired
	private ICriptography criptography;

	/**
	 * Crypt password test.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void cryptPasswordTest() throws BrioBPMException {
		String text = "Frodriguez";
		String hash = criptography.cryptText(text);
		log.info("{}", hash);
		hash = criptography.cryptText(text);
		log.info("{}", hash);
	}

	/**
	 * Hash password test.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void hashPasswordTest() throws BrioBPMException {
		String text = "briobpm";
		String hash = criptography.hashText(text);
		log.info("{}", hash);
		hash = criptography.hashText(text);
		log.info("{}", hash);
	}

}
