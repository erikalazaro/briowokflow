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
import com.briomax.briobpm.persistence.entity.StTareaSeccion;
import com.briomax.briobpm.persistence.entity.StTareaSeccionPK;
import com.briomax.briobpm.persistence.repository.IStTareaSeccionRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Class IStTareaSeccionRepository.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Feb 09, 2024 Modificaciones:
 * @since JDK 1.8
 */
@Slf4j
public class StTareaSeccionTest extends AbstractDataTest {

	/** El atributo o variable repository. */
	@Autowired
	private IStTareaSeccionRepository repository;

	/**
	 * StTareaSeccion ID test.
	 * 
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void stTareaSeccionAllTest() throws BrioBPMException {
		List<StTareaSeccion> list = repository.findAll();
		log.info("COUNT: {}", list.size());
		if (list.size() == 0) {
			fail("No existen folio Proces.");
		}

	}
	
	/**
	 * StTareaSeccion ID test.
	 * 
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void stTareaSeccionByIdTest() throws BrioBPMException {
		StTareaSeccionPK id = StTareaSeccionPK.builder()
				.cveEntidad("BRIOMAX")
				.cveProceso("DUMMY")
				.version(new BigDecimal(1.0))
				.cveNodo("ACTIVIDAD-USUARIO")
				.idNodo(new BigDecimal(1.0))
				.cveSeccion("TAREAS DUMMY")
				.secuenciaTarea(1)
				.build();
		Optional<StTareaSeccion> result = repository.findById(id );

		if (!result.isPresent()) {
			log.info("No existen un St Seccion Proceso.");
			fail("No existen un St Seccion Proceso.");
		}
	}

}
