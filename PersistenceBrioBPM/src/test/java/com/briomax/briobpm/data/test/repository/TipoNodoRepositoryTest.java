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
import com.briomax.briobpm.persistence.entity.TipoNodo;
import com.briomax.briobpm.persistence.repository.ITipoNodoRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Class TipoNodoRepositoryTest.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Jun 25, 2020 12:32:21 PM Modificaciones:
 * @since JDK 1.8
 */
@Slf4j
public class TipoNodoRepositoryTest extends AbstractDataTest {

	/** El atributo o variable tipo nodo repository. */
	@Autowired
	private ITipoNodoRepository tipoNodoRepository;

	/**
	 * Find all test.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void findAllTest() throws BrioBPMException {
		List<TipoNodo> list = tipoNodoRepository.findAll();
		for (TipoNodo tipoNodo : list) {
			log.info("{}", tipoNodo);
		}
	}

	@Test
	public void tipoNodoIdTest() throws BrioBPMException {
		Optional<TipoNodo> entidad = tipoNodoRepository.findById("ACTIVIDAD-USUARIO");

		log.info("------> SE ENCONTRO ENTIDAD: " + entidad.toString());
		if (!entidad.isPresent()) {
			log.info("No existen TIPO NODO.");
			fail("No existen TIPO NODO.");
		}
	}

}
