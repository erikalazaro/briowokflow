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
import com.briomax.briobpm.persistence.entity.StNodoProceso;
import com.briomax.briobpm.persistence.entity.StNodoProcesoPK;
import com.briomax.briobpm.persistence.repository.IStNodoProcesoRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Class StNodoProcesoTest.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Nov 28, 2023 Modificaciones:
 * @since JDK 1.8
 */
@Slf4j
public class StNodoProcesoTest extends AbstractDataTest {

	/** El atributo o variable repository. */
	@Autowired
	private IStNodoProcesoRepository repository;

	/**
	 * StNodoProceso all test.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void stNodoProcesoAllTest() throws BrioBPMException {
		List<StNodoProceso> list = repository.findAll();
		log.info("COUNT: {}", list.size());
		if (list.size() == 0) {
			fail("No existen folio Proces.");
		}

	}
	
	/**
	 * StNodoProceso ID test.
	 * 
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void stNodoProcesoIdTest() throws BrioBPMException {
		StNodoProcesoPK id = StNodoProcesoPK
				.builder()
				.cveEntidad("BRIOMAX")
				.cveProceso("DUMMY")
				.version(new BigDecimal(1.0))
				.cveNodo("ACTIVIDAD-USUARIO")
				.idNodo(1)
				.build();
		log.info("------>ID: " + id.toString());
		Optional<StNodoProceso> entidad = repository.findById(id);

		if (!entidad.isPresent()) {
			log.info("No existen St Nodo Proceso.");
			fail("No existen St Nodo Proceso.");
		}
	}

}
