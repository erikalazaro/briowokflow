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
import com.briomax.briobpm.persistence.entity.StVariableSeccion;
import com.briomax.briobpm.persistence.entity.StVariableSeccionPK;
import com.briomax.briobpm.persistence.repository.IStVariableSeccionRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Class StVariablelSeccionTest.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Feb 09, 2024 3:13:43 PM :
 * @since JDK 1.8
 */
@Slf4j
public class StVariablelSeccionTest extends AbstractDataTest {

	/** El atributo o variable repository. */
	@Autowired
	private IStVariableSeccionRepository repository;

	/**
	 * StVariableSeccion all test.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void stVariableSeccionAllTest() throws BrioBPMException {
		List<StVariableSeccion> list = repository.findAll();
		log.info("COUNT: {}", list.size());
		if (list.size() == 0) {
			fail("No existen folio Proces.");
		}
	}

	/**
	 * StVariableSeccion ID test.
	 * 
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void stVariableSeccionIdTest() throws BrioBPMException {
		StVariableSeccionPK id = StVariableSeccionPK.builder()
				.cveEntidad("BRIOMAX")
				.cveProceso("DUMMY")
				.version(new BigDecimal(1.0))
				.cveNodo("ACTIVIDAD-USUARIO")
				.idNodo(new BigDecimal(1.0))
				.cveSeccion("DOCUMENTOS")
				.cveVariable("VPRO_01_ARCHIVO_DOCUMENTO")
				.build();
		Optional<StVariableSeccion> result = repository.findById(id );

		if (!result.isPresent()) {
			log.info("No existen un St Variable Envio.");
			fail("No existen un St Variable Envio.");
		}
	}
}
