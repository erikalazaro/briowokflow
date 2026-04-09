/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */
package com.briomax.briobpm.data.test.repository;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.data.test.base.AbstractDataTest;
import com.briomax.briobpm.persistence.entity.CrPagoBancario;
import com.briomax.briobpm.persistence.entity.CrPagoBancarioPK;
import com.briomax.briobpm.persistence.repository.ICrPagoBancarioRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Class ICrPagoBancarioRepository.java es ...
 * @author Sara Ventura
 * @version 1.0 Fecha de creacion Sept 03, 2024 Modificaciones:
 * @since JDK 1.8
 */
@Slf4j
public class CrPagoBancarioRepositoryTest extends AbstractDataTest {

	/** El atributo o variable repository. */
	@Autowired
	private ICrPagoBancarioRepository repository;

	/**
	 * StProceso all test.
	 * 
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void crPagoBancarioAllTest() throws BrioBPMException {
	    List<CrPagoBancario> list = repository.findAll();
	    log.info("COUNT: {}", list.size());

	    if (list.size() == 0) {
	        log.info("No existen procesos.");
	    }
	}

	/**
	 * StProceso ID test.
	 * 
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void stProcesoId() throws BrioBPMException {
	    CrPagoBancarioPK id = CrPagoBancarioPK.builder()
	            .cveEntidad("BRIOMAX")
	            .cveProceso("DUMMY")
	            .build();
	    log.info("----->id: " + id.toString());
	    
	    Optional<CrPagoBancario> procesoOptional = repository.findById(id);
	    
	    if (!procesoOptional.isPresent()) {
	        log.info("No existe el proceso con el ID proporcionado.");
	    }
	}
}
