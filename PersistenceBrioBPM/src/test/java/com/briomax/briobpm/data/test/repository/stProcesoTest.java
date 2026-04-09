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
import com.briomax.briobpm.persistence.entity.StProceso;
import com.briomax.briobpm.persistence.entity.StProcesoPK;
import com.briomax.briobpm.persistence.repository.IStProcesoRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Class IStProcesoRepository.java es ...
 * @author Sara Ventura
 * @version 1.0 Fecha de creacion Nov 28, 2023 Modificaciones:
 * @since JDK 1.8
 */
@Slf4j
public class stProcesoTest extends AbstractDataTest {

	/** El atributo o variable repository. */
	@Autowired
	private IStProcesoRepository repository;

	/**
	 * StProceso all test.
	 * 
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void stProcesoAllTest() throws BrioBPMException {
	    List<StProceso> list = repository.findAll();
	    log.info("COUNT: {}", list.size());

	    if (list.size() == 0) {
	        fail("No existen procesos.");
	    }
	}

	/**
	 * StProceso ID test.
	 * 
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void stProcesoId() throws BrioBPMException {
	    StProcesoPK id = StProcesoPK.builder()
	            .cveEntidad("BRIOMAX")
	            .cveProceso("DUMMY")
	            .version(new BigDecimal(1.0))
	            .build();
	    log.info("----->id: " + id.toString());
	    
	    Optional<StProceso> procesoOptional = repository.findById(id);
	    
	    if (!procesoOptional.isPresent()) {
	        fail("No existe el proceso con el ID proporcionado.");
	    }
	}
}
