/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */
package com.briomax.briobpm.data.test.repository;

import static org.junit.Assert.fail;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.data.test.base.AbstractDataTest;
import com.briomax.briobpm.persistence.entity.LocalidadEntidad;
import com.briomax.briobpm.persistence.entity.LocalidadEntidadPK;
import com.briomax.briobpm.persistence.repository.ILocalidadEntidadRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Class ILocalidadEntidadRepository.java es ...
 * @author Alexs Zamora 
 * @version 1.0 Fecha de creacion Nov 28, 2023 Modificaciones:
 * @since JDK 1.8
 */
@Slf4j
public class LocalidadEntidadTest extends AbstractDataTest {

	/** El atributo o variable repository. */
	@Autowired
	private ILocalidadEntidadRepository repository;

	/**
	 * Calendario all test.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void localidadEntidadAllTest() throws BrioBPMException {
		List<LocalidadEntidad> list = repository.findAll();
		log.info("COUNT: {}", list.size());
		if (list.size() == 0) {
			fail("No existen LocalidadEntidades.");
		}

	}
	
	/**
	 * LocalidadEntidad ID test.
	 * 
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void localidadEntidadId() throws BrioBPMException {
	    LocalidadEntidadPK id = LocalidadEntidadPK.builder()
	            .cveEntidad("BRIOMAX")
	            .cveLocalidad("BRIOMAX-CENTRAL")
	            .build();
	    log.info("----->id: " + id.toString());
	    
	    Optional<LocalidadEntidad> localidadOptional = repository.findById(id);
	    
	    if (!localidadOptional.isPresent()) {
	        fail("No existe la localidad de entidad con el ID proporcionado.");
	    }
	}

}
