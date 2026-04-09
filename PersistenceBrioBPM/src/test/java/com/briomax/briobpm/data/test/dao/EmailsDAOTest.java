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
import com.briomax.briobpm.persistence.dao.base.IEmailsDAO;
import com.briomax.briobpm.persistence.entity.namedquery.LeeCorreosPorEnviar;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Class EmailsDAOTest.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Apr 13, 2020 11:27:08 AM Modificaciones:
 * @since JDK 1.8
 */
@Slf4j
public class EmailsDAOTest extends AbstractDataTest {

	/** El atributo o variable emails DAO. */
	@Autowired
	private IEmailsDAO emailsDAO;

	/**
	 * Obtener correos por enviar test.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void obtenerCorreosPorEnviarTest() throws BrioBPMException {
		log.info("{} {} {} {}", getUser(), getEntidad(), getLocalidad(), getIdioma());
		final DAORet<List<LeeCorreosPorEnviar>, RetMsg> ret = emailsDAO.obtenerCorreosPorEnviar();
		imprimir(ret);
		assertEquals("OK", ret.getMeta().getStatus().toUpperCase());
	}

	/**
	 * Actualiza situacion correo test.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void actualizaSituacionCorreoTest() throws BrioBPMException {
		log.info("{} {} {} {}", getUser(), getEntidad(), getLocalidad(), getIdioma());
		final RetMsg ret = emailsDAO.actualizaSituacionCorreo(getUser(), getEntidad(), getLocalidad(), 
			getIdioma(), CVE_PROCESO, VERSION, new Long(1), "CREADO");
		log.info("{}", ret);
	}


}
