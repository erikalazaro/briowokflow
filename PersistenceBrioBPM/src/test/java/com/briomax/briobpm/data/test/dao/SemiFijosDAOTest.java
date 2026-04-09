/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */
package com.briomax.briobpm.data.test.dao;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.briomax.briobpm.common.core.DAORet;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.data.test.base.AbstractDataTest;
import com.briomax.briobpm.persistence.dao.base.ISemiFijosDAO;
import com.briomax.briobpm.persistence.entity.namedquery.LeeValoresColumna;
import com.briomax.briobpm.persistence.repository.IValorColumnaRepository;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Class SemiFijosDAOTest.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Jul 2, 2020 11:26:08 AM Modificaciones:
 * @since JDK 1.8
 */
@Slf4j
public class SemiFijosDAOTest extends AbstractDataTest {

	/** El atributo o variable dao. */
	@Autowired
	private ISemiFijosDAO dao;
	
	/** El atributo o variable dao. */
	
	@Autowired
	private IValorColumnaRepository valorColumnaRepository;

	/**
	 * Obtener el valor de obtener valores test.
	 * @return el obtener valores test.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	
	@Ignore
	public void getObtenerValoresTest() throws BrioBPMException {
		log.info("{} {} {} {}", getUser(), getEntidad(), getLocalidad(), getIdioma());
		final DAORet<List<LeeValoresColumna>, RetMsg> ret = dao.getValoresColumna(getUser(), getEntidad(),
			getLocalidad(), getIdioma(), "CATALOGOS", new BigDecimal("1.00"), "ST_ROL_PROCESO", "SITUACION");
		log.info("MENSAJE: {}", ret);
		for (LeeValoresColumna item : ret.getContent()) {
			log.info("DAO TEST {}", item);
		}
	}
	
	
	@Test
	public void getObtenerValoresColumnaTest() throws BrioBPMException {
		log.info("{} {} {} {}", getUser(), getEntidad(), getLocalidad(), getIdioma());
		final List<Object> ret = valorColumnaRepository.getValoresColumna( getEntidad(), getIdioma(),
				"ST_ROL_PROCESO", "SITUACION");
			
		log.info("MENSAJE: {}", ret);
		for (Object item : ret) {
			
			log.info("VALOR COLUMNA {}", item);
		}
	}

}
