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
import com.briomax.briobpm.persistence.entity.InTareaProceso;
import com.briomax.briobpm.persistence.entity.InTareaProcesoPK;
import com.briomax.briobpm.persistence.repository.IInTareaProcesoRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Class IInTareaProcesoRepository.java es ...
 * @author Sara Ventura
 * @version 1.0 Fecha de creacion Nov 28, 2023 Modificaciones:
 * @since JDK 1.8
 */
@Slf4j
public class InTareaProcesoTest extends AbstractDataTest {

	/** El atributo o variable repository. */
	@Autowired
	private IInTareaProcesoRepository repository;

	/**
	 * InTareaProceso all test.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void inTareaProcesoAllTest() throws BrioBPMException {
		List<InTareaProceso> list = repository.findAll();
		log.info("COUNT: {}", list.size());
		if (list.size() == 0) {
			fail("No existen folio Proces.");
		}
	}

	/**
	 * InTareaProceso ID test.
	 * 
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void inTareaProcesoByIdTest() throws BrioBPMException {
		InTareaProcesoPK id = InTareaProcesoPK.builder()
				.cveEntidad("BRIOMAX")
				.cveProceso("DUMMY")
				.version(new BigDecimal(1.0))				
				.cveInstancia("202110-000001")
				.secuenciaTarea(1)
				.build();
		Optional<InTareaProceso> result = repository.findById(id );

		if (!result.isPresent()) {
			fail("No existen in Tarea Proceso.");
		}
	}
}
