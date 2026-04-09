/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */
package com.briomax.briobpm.data.test.repository;

import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.data.test.base.AbstractDataTest;
import com.briomax.briobpm.persistence.entity.StSeccionProceso;
import com.briomax.briobpm.persistence.entity.StSeccionProcesoPK;
import com.briomax.briobpm.persistence.repository.IStSeccionProcesoRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Class IStSeccionProcesoRepository.java es ...
 * @author Sara Ventura
 * @version 1.0 Fecha de creacion Nov 28, 2023 Modificaciones:
 * @since JDK 1.8
 */
@Slf4j
public class stSeccionProcesoTest extends AbstractDataTest {

	/** El atributo o variable repository. */
	@Autowired
	private IStSeccionProcesoRepository repository;

	/**
	 * Calendario all test.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void stProcesoAllTest() throws BrioBPMException {
		List<StSeccionProceso> list = repository.findAll();
		log.info("COUNT: {}", list.size());
		if (list.size() == 0) {
			fail("No existen Proces.");
		}

	}
	
	@Test
	public void stSeccionProcesoByIdTest() throws BrioBPMException {
		StSeccionProcesoPK id = StSeccionProcesoPK.builder()
				.version(new BigDecimal(1.0))
				.cveProceso("DUMMY")
				.cveEntidad("BRIOMAX")
				.cveSeccion("DOCUMENTOS")
				.build();
		Optional<StSeccionProceso> result = repository.findById(id );

		if (!result.isPresent()) {
			log.info("No existen un St Seccion Proceso.");
			fail("No existen un St Seccion Proceso.");
		}
	}

}
