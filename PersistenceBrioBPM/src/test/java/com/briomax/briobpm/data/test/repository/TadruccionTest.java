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
import com.briomax.briobpm.persistence.entity.Traduccion;
import com.briomax.briobpm.persistence.entity.TraduccionPK;
import com.briomax.briobpm.persistence.repository.ITraduccionRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Class TadruccionTest.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Feb 09, 2024 3:13:43 PM :
 * @since JDK 1.8
 */
@Slf4j
public class TadruccionTest extends AbstractDataTest {

	/** El atributo o variable repository. */
	@Autowired
	private ITraduccionRepository repository;

	/**
	 * Traduccion all test.
	 * 
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void traduccionAllTest() throws BrioBPMException {
	    List<Traduccion> list = repository.findAll();
	    log.info("COUNT: {}", list.size());

	    if (list.size() == 0) {
	        fail("No existen traducciones.");
	    }
	}

	/**
	 * Traduccion ID test.
	 * 
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void traduccionIdTest() throws BrioBPMException {
	    TraduccionPK id = TraduccionPK.builder()
	            .palabraOriginal("¿Resultado de pre comité es aceptado?")
	            .cveIdioma("EN-US")
	            .build();
	    log.info("----->id: " + id.toString());
	    
	    Optional<Traduccion> traduccionOptional = repository.findById(id);
	    
	    if (!traduccionOptional.isPresent()) {
	        fail("No existe la traducción con el ID proporcionado.");
	    }
	}


}
