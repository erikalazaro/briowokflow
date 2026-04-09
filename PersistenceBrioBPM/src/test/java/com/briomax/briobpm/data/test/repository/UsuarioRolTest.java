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
import com.briomax.briobpm.persistence.entity.UsuarioRol;
import com.briomax.briobpm.persistence.entity.UsuarioRolPK;
import com.briomax.briobpm.persistence.repository.IUsuarioRolRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Class UsuarioRolTest.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Jul 27, 2020 1:05:37 PM Modificaciones:
 * @since JDK 1.8
 */
@Slf4j
public class UsuarioRolTest extends AbstractDataTest {

	/** El atributo o variable repository. */
	@Autowired
	private IUsuarioRolRepository repository;

	/**
	 * UsuarioRol all test.
	 * 
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void usuarioRolAllTest() throws BrioBPMException {
	    List<UsuarioRol> list = repository.findAll();
	    log.info("COUNT: {}", list.size());

	    if (list.size() == 0) {
	        fail("No existen relaciones Usuario-Rol.");
	    }
	}

	/**
	 * UsuarioRol ID test.
	 * 
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void usuarioRolIdTest() throws BrioBPMException {
	    UsuarioRolPK id = UsuarioRolPK.builder()
	            .cveEntidad("BRIOMAX")
	            .cveUsuario("Admin.Opera")
	            .cveProceso("DUMMY")
	            .version(BigDecimal.ONE)
	            .cveRol("ROL_ADMIN_OPERA")
	            .build();
	    log.info("----->id: " + id.toString());
	    
	    Optional<UsuarioRol> usuarioRolOptional = repository.findById(id);
	    
	    if (!usuarioRolOptional.isPresent()) {
	        fail("No existe la relación Usuario-Rol con el ID proporcionado.");
	    }
	}

}
