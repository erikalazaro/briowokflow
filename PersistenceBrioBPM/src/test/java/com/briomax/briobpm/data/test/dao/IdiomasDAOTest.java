/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */
package com.briomax.briobpm.data.test.dao;

import java.util.List;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import com.briomax.briobpm.common.core.DAORet;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.data.test.base.AbstractDataTest;
import com.briomax.briobpm.persistence.dao.base.IIdiomasDAO;
import com.briomax.briobpm.persistence.entity.namedquery.LeeIdiomas;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Class UsuarioDAOTest.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion 27/01/2020 05:08:38 PM Modificaciones:
 * @since JDK 1.8
 */
@Slf4j
public class IdiomasDAOTest extends AbstractDataTest {

	/** El atributo o variable idiomas DAO. */
	@Autowired
	private IIdiomasDAO idiomasDAO;

	/**
	 * Obtener idiomas test.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void obtenerIdiomasTest() throws BrioBPMException {
		log.info("{} {} {} {}", getUser(), getEntidad(), getLocalidad(), getIdioma());
		final DAORet<List<LeeIdiomas>, RetMsg> ret =
			idiomasDAO.obtenerIdiomas(getUser(), getEntidad(), getEntidad(), getIdioma());
		imprimir(ret);
		assertEquals("OK", ret.getMeta().getStatus().toUpperCase());
	}

}
