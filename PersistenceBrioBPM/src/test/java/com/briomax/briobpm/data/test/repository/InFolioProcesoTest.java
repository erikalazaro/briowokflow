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
import com.briomax.briobpm.persistence.entity.InFolioProceso;
import com.briomax.briobpm.persistence.entity.InFolioProcesoPK;
import com.briomax.briobpm.persistence.repository.IInFolioProcesoRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Class IInFolioProcesoRepository.java es ...
 * @author Sara Ventura
 * @version 1.0 Fecha de creacion Nov 28, 2023 Modificaciones:
 * @since JDK 1.8
 */
@Slf4j
public class InFolioProcesoTest extends AbstractDataTest {

	/** El atributo o variable repository. */
	@Autowired
	private IInFolioProcesoRepository repository;

	/**
	 * Calendario all test.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void inFolioProcesoAllTest() throws BrioBPMException {
		List<InFolioProceso> list = repository.findAll();
		log.info("COUNT: {}", list.size());
		if (list.size() == 0) {
			fail("No existen folio Proces.");
		}

	}

	@Test
	public void inFolioProcesoByIdTest() throws BrioBPMException {
		InFolioProcesoPK id = InFolioProcesoPK.builder()
				.cveEntidad("BRIOMAX")
				.cveProceso("DUMMY")
				.version(new BigDecimal(1.0))
				.mes(10)
				.anio(2021)
				.build();
		Optional<InFolioProceso> result = repository.findById(id );

		if (!result.isPresent()) {
			log.info("No existen in FOLIO PROCESO.");
			fail("No existen in FOLIO PROCESO.");
		}
	}
}
