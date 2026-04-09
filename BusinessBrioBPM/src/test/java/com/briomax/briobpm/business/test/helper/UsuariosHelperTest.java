/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */
package com.briomax.briobpm.business.test.helper;

import org.junit.Test;

import com.briomax.briobpm.business.helpers.base.IUsuariosHelper;
import com.briomax.briobpm.business.test.base.AbstractCoreTest;
import com.briomax.briobpm.common.core.DAORet;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.transferobjects.DatosUsuarioTO;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;


/**
 * El objetivo de la Class UsuariosHelperTest.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion May 15, 2020 11:57:26 AM Modificaciones:
 * @since JDK 1.8
 */
@Slf4j
public class UsuariosHelperTest extends AbstractCoreTest {

	/** El atributo o variable usarios helper. */
	@Autowired
	private IUsuariosHelper usariosHelper;

	/**
	 * Valido usuario test.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void validoUsuarioTest() throws BrioBPMException {
		DAORet<DatosUsuarioTO, RetMsg> meta = usariosHelper.authenticate(entidad, localidad, USUARIO,
			"dd4741a3ccc633f404f25435392fc5893138a669c743783c151cba27c0f6fd5f", idioma);
		log.info("Meta: {}", meta.getMeta());
		log.info("Content: {}", meta.getContent());
	}

	@Test
	public void validoUsuarioTestAPP() throws BrioBPMException {
		DAORet<DatosUsuarioTO, RetMsg> meta = usariosHelper.authenticate("BRIOMAX", "BRIOMAX-CENTRAL", "AIT.Marco.Juarez",
			"3efc3b05dbd0963af796964d99659b35cd3d5dcd4f05fe38107c12dab0e5b889", "ES-MX");
		log.info("Meta: {}", meta.getMeta());
		log.info("Content: {}", meta.getContent());
	}

}
