/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */
package com.briomax.briobpm.data.test.repository;

import java.util.List;

import org.junit.Test;

import static org.junit.Assert.fail;

import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.data.test.base.AbstractDataTest;
import com.briomax.briobpm.persistence.entity.Moneda;
import com.briomax.briobpm.persistence.repository.IMonedaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Class MonedaRepositoryTest.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Jun 14, 2021 12:32:46 PM Modificaciones:
 * @since JDK 1.8
 */
@Slf4j
public class MonedaRepositoryTest extends AbstractDataTest {

	/** El atributo o variable repository. */
	@Autowired
	private IMonedaRepository repository;

	/**
	 * Idioma all test.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void idiomaAllTest() throws BrioBPMException {
		List<Moneda> list = repository.findAll(Sort.by("descripcion").ascending());
		log.info("COUNT: {}", list.size());
		if (list.size() == 0) {
			fail("No existen Monedas.");
		}
		for (Moneda idioma : list) {
			log.info("{}", idioma);
		}
	}

}
