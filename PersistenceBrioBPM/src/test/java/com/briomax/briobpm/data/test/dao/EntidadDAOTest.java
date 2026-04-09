package com.briomax.briobpm.data.test.dao;

import java.util.List;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import com.briomax.briobpm.common.core.DAORet;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.data.test.base.AbstractDataTest;
import com.briomax.briobpm.persistence.dao.base.IEntidadDAO;
import com.briomax.briobpm.persistence.entity.namedquery.LeeEntidades;
import com.briomax.briobpm.persistence.entity.namedquery.Localidad;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;


/**
 * El objetivo de la Class EntidadDAOTest.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion 27/01/2020 05:07:54 PM Modificaciones:
 * @since JDK 1.8
 */
@Slf4j
public class EntidadDAOTest extends AbstractDataTest {

	/** El atributo o variable entidad DAO. */
	@Autowired
	private IEntidadDAO entidadDAO;

	@Test
	public void obtenerEntidadesTest() throws BrioBPMException {
		final DAORet<List<LeeEntidades>, RetMsg> ret =
			entidadDAO.obtenerEntidades(getUser(), getEntidad(), getLocalidad(), getIdioma());
		log.info("{}", ret.getMeta());
		assertEquals("OK", ret.getMeta().getStatus().toUpperCase());
		for (LeeEntidades entidad : ret.getContent()) {
			log.info("{}", entidad);
		}
	}

	/**
	 * Lee datos localidad test.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void leeDatosLocalidadTest() throws BrioBPMException {
		final DAORet<Localidad, RetMsg> ret =
			entidadDAO.leeDatosLocalidad(getUser(), getEntidad(), getLocalidad(), getIdioma());
		log.info("{}", ret);
		assertEquals("OK", ret.getMeta().getStatus().toUpperCase());
	}

}
