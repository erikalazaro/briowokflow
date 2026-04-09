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
import com.briomax.briobpm.persistence.entity.CalendarioLocalidad;
import com.briomax.briobpm.persistence.entity.CalendarioLocalidadPK;
import com.briomax.briobpm.persistence.repository.ICalendarioLocalidadRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Class CalendarioLocalidadTest.java es ...
 * @author Sara Ventura
 * @version 1.0 Fecha de creacion Nov 28, 2023 Modificaciones:
 * @since JDK 1.8
 */
@Slf4j
public class CalendarioLocalidadTest extends AbstractDataTest {

	/** El atributo o variable repository. */
	@Autowired
	private ICalendarioLocalidadRepository repository;

	/**
	 * Calendario all test.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void calendarioAllTest() throws BrioBPMException {
		List<CalendarioLocalidad> list = repository.findAll();
		log.info("COUNT: {}", list.size());
		if (list.size() == 0) {
			fail("No existen Calendario Localidad.");
		}

	}

	@Test
	public void stProcesoId() throws BrioBPMException {

		CalendarioLocalidadPK id = CalendarioLocalidadPK.builder()
				.cveEntidad("BRIOMAX")
				.cveLocalidad("BRIOMAX-CENTRAL")
				.build();
		log.info("----->id: " + id.toString());
		
		Optional<CalendarioLocalidad> result = repository.findById(id);
		
		if (!result.isPresent()) {
			fail("No existen Calendario Especial.");
		}

	}
}
