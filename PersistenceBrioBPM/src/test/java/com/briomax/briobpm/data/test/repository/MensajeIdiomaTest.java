/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */
package com.briomax.briobpm.data.test.repository;

import static org.junit.Assert.fail;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.data.test.base.AbstractDataTest;
import com.briomax.briobpm.persistence.entity.MensajeIdioma;
import com.briomax.briobpm.persistence.entity.MensajeIdiomaPK;
import com.briomax.briobpm.persistence.repository.IMensajeIdiomaRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Class MensajeIdiomaTest.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Jun 25, 2020 12:32:21 PM Modificaciones:
 * @since JDK 1.8
 */
@Slf4j
public class MensajeIdiomaTest extends AbstractDataTest {

	/** El atributo o variable mensaje idioma repository. */
	@Autowired
	private IMensajeIdiomaRepository mensajeIdiomaRepository;

	/**
	 * Mesajes idioma test.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void mesajesIdiomaTest() throws BrioBPMException {
		List<MensajeIdioma> list = mensajeIdiomaRepository.findAll();
		log.info("COUNT: {}", list.size());
		for (MensajeIdioma mensajeIdioma : list) {
			log.info("{}", mensajeIdioma);
		}
	}

	/**
	 * MensajeIdioma ID test.
	 * 
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void mesajeIdiomaTest() throws BrioBPMException {
		MensajeIdiomaPK id = MensajeIdiomaPK.builder()
				.codigoMensaje("ACCION_INVALIDA_SOBRE_NODO")
				.cveIdioma(LOCALE_SPANISH)
				.build();
		Optional<MensajeIdioma> message = mensajeIdiomaRepository.findById(id);

		if (!message.isPresent()) {
			fail("No existen Mensaje Idioma.");
		}
	}

}
