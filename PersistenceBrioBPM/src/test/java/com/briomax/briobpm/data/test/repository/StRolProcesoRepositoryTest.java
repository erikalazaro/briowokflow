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
import com.briomax.briobpm.persistence.entity.StRolProceso;
import com.briomax.briobpm.persistence.entity.StRolProcesoPK;
import com.briomax.briobpm.persistence.repository.IStRolProcesoRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Class ProcesoTest.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Dec 18, 2020 3:13:43 PM Modificaciones:
 * @since JDK 1.8
 */
@Slf4j
public class StRolProcesoRepositoryTest extends AbstractDataTest {

	/** El atributo o variable repository. */
	@Autowired
	private IStRolProcesoRepository repository;
	
	/**
	 * StRolProceso all test.
	 * 
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void stRolProcesoAllTest() throws BrioBPMException {
	    List<StRolProceso> list = repository.findAll();
	    log.info("COUNT: {}", list.size());

	    if (list.size() == 0) {
	        fail("No existen roles de proceso.");
	    }
	}

	/**
	 * StRolProceso ID test.
	 * 
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void stRolProcesoId() throws BrioBPMException {
	    StRolProcesoPK id = StRolProcesoPK.builder()
	            .cveEntidad("BRIOMAX")
	            .cveProceso("DUMMY")
	            .version(new BigDecimal(1.0))
	            .cveRol("ROL DUMMY 1")
	            .build();
	    log.info("----->id: " + id.toString());
	    
	    Optional<StRolProceso> rolOptional = repository.findById(id);
	    
	    if (!rolOptional.isPresent()) {
	        fail("No existe el rol de proceso con el ID proporcionado.");
	    }
	}
}
