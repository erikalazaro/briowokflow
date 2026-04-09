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
import com.briomax.briobpm.persistence.entity.StValorInicialVariable;
import com.briomax.briobpm.persistence.entity.StValorInicialVariablePK;
import com.briomax.briobpm.persistence.repository.IStValorInicialVariableRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Class IStValorInicialVariableRepository.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Feb 09, 2024 Modificaciones:
 * @since JDK 1.8
 */
@Slf4j
public class StValorInicialVariableTest extends AbstractDataTest {

	/** El atributo o variable repository. */
	@Autowired
	private IStValorInicialVariableRepository repository;

	/**
	 * StValorInicialVariable all test.
	 * 
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void stValorInicialVariableAllTest() throws BrioBPMException {
		List<StValorInicialVariable> list = repository.findAll();
		log.info("COUNT: {}", list.size());
		if (list.size() == 0) {
			fail("No existen folio Proces.");
		}
	}
	
	/**
	 * StValorInicialVariable ID test.
	 * 
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void stValorInicialVariableByIdTest() throws BrioBPMException {
		StValorInicialVariablePK id = StValorInicialVariablePK.builder()
				.cveEntidad("BRIOMAX")
				.cveProceso("REBATES AND REWARDS EJECUCIÓN")
				.version(new BigDecimal(1.0))
				.cveVariable("VPRO_CONSIDERACIONES_PARTICIPANTE")
				.ocurrencia(1)
				.secuenciaValor(1)
				.build();
		Optional<StValorInicialVariable> result = repository.findById(id );

		if (!result.isPresent()) {
			log.info("No existen un St Seccion Proceso.");
			fail("No existen un St Seccion Proceso.");
		}
	}

}
