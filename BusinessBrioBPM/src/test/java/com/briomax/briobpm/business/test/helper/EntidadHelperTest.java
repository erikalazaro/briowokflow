/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */
package com.briomax.briobpm.business.test.helper;

import java.util.List;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import com.briomax.briobpm.business.helpers.base.IEntidadHelper;
import com.briomax.briobpm.business.test.base.AbstractCoreTest;
import com.briomax.briobpm.common.core.DAORet;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.transferobjects.ComboBoxTO;
import com.briomax.briobpm.transferobjects.LocalidadTO;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Class EntidadHelperTest.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion May 15, 2020 11:59:05 AM Modificaciones:
 * @since JDK 1.8
 */
@Slf4j
public class EntidadHelperTest extends AbstractCoreTest {

	/** El atributo o variable entidad helper. */
	@Autowired
	private IEntidadHelper entidadHelper;

	/**
	 * Obtener el valor de entidades test.
	 * @return el entidades test.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void getEntidadesTest() throws BrioBPMException {
		DatosAutenticacionTO session = getDatosAutenticacionTO();
		DAORet<List<ComboBoxTO>, RetMsg> res = entidadHelper.getEntidades(session);
		log.info("{}", res.getMeta());
		assertEquals("OK", res.getMeta().getStatus().toUpperCase());
		for (ComboBoxTO item : res.getContent()) {
			log.info("{}", item);
		}
	}

	/**
	 * Obtener el valor de datos localidad test.
	 * @return el datos localidad test.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void getDatosLocalidadTest() throws BrioBPMException {
		DAORet<LocalidadTO, RetMsg> res = entidadHelper.getDatosLocalidad("Francisco.Rodriguez", "BRIOMAX",
			"BRIOMAX-CENTRAL", "ES-MX");
		log.info("{}", res);
		printerJson(res.getContent());
	}

	/**
	 * Obtener el valor de localidades test.
	 * @return el localidades test.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void getLocalidadesTest() throws BrioBPMException {
		List<ComboBoxTO> list = entidadHelper.getLocalidades("BRIOMAX");
		printerJson(list);
		for (ComboBoxTO item : list) {
			log.info("{}", item);
		}
	}

	/**
	 * Obtener el valor de fecha hora test.
	 * @return el fecha hora test.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void getFechaHoraTest() throws BrioBPMException {
		DatosAutenticacionTO session = getDatosAutenticacionTO();
		DAORet<String, RetMsg> res = entidadHelper.getHora(session);
		log.info("{}", res);
		printerJson(res.getContent());
		// -7
		session.setCveLocalidad("BRIOMAX-LOS CABOS");
		session.setCveUsuario("Erika.Vazquez");
		res = entidadHelper.getHora(session);
		log.info("{}", res);
		printerJson(res.getContent());
		// +3
		session.setCveLocalidad("BRIOMAX-QATAR");
		session.setCveUsuario("Kala.Mohamed");
		res = entidadHelper.getHora(session);
		log.info("{}", res);
		printerJson(res.getContent());
	}

}
