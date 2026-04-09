/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */
package com.briomax.briobpm.data.test.repository;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.data.test.base.AbstractDataTest;
import com.briomax.briobpm.persistence.entity.Usuario;
import com.briomax.briobpm.persistence.entity.UsuarioPK;
import com.briomax.briobpm.persistence.repository.IUsuarioRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Class UsuarioRepositoryTest.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Feb 09, 2024 3:13:43 PM :
 * @since JDK 1.8
 */
@Slf4j
public class UsuarioRepositoryTest extends AbstractDataTest {

	/** El atributo o variable usuario repository. */
	@Autowired
	private IUsuarioRepository repository;

	/**
	 * Mesajes idioma test.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void findAllTest() throws BrioBPMException {
		List<Usuario> list = repository.findAll();
		log.info("COUNT: {}", list.size());
		if (list.size() == 0) {
	        fail("No existen usuarios.");
	    }
	}
	
	/**
	 * Usuario ID test.
	 * 
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void usuarioIdTest() throws BrioBPMException {
	    UsuarioPK id = UsuarioPK.builder()
	            .cveEntidad("BRIOMAX")
	            .cveUsuario("Francisco.Rodriguez")
	            .build();
	    log.info("----->id: " + id.toString());
	    
	    Optional<Usuario> result = repository.findById(id);
	    
	    if (!result.isPresent()) {
	        fail("No existe el usuario con el ID proporcionado.");
	    }
	}

}
