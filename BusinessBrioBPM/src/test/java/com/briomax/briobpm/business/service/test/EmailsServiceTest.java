/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.business.service.test;

import java.text.ParseException;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.briomax.briobpm.business.service.core.IEmailsService;
import com.briomax.briobpm.business.test.base.AbstractCoreTest;
import com.briomax.briobpm.common.core.DAORet;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.persistence.entity.namedquery.LeeCorreosPorEnviar;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Class EmailsServiceTest.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Apr 13, 2020 10:45:59 AM Modificaciones:
 * @since JDK 1.8
 */
@Slf4j
public class EmailsServiceTest extends AbstractCoreTest {

	/** El atributo o variable mail service. */
	@Autowired
	private IEmailsService mailService;
	
	/**
	 * Crear una nueva instancia del objeto emails service test.
	 */
	public EmailsServiceTest() {
	}

	/**
	 * Send simple mail message test.
	 */
	//@Test
	@Ignore
	public void sendSimpleMailMessageTest() {
		DatosAutenticacionTO info = getDatosAutenticacionTO();
		mailService.sendEmails(info.getCveUsuario(), info.getCveEntidad(), info.getCveLocalidad(), info.getCveIdioma());	
	}
	/**
	 * Send simple mail message test.
	 */
	//@Test
	@Ignore
	public void leeCorreosPorEnviarTest() {
		DAORet<List<LeeCorreosPorEnviar>, RetMsg>rest = mailService.leeCorreosEnviar();
	    List<LeeCorreosPorEnviar> correosEnviar = rest.getContent();
	    for (LeeCorreosPorEnviar correos : correosEnviar ) {
			log.info("TEST CORREOS POR ENVIAR {}", correos.getConCopiaPara());
		}
	}
	
	/**
	 * Send simple mail message test.
	 * @throws ParseException 
	 * @throws BrioBPMException 
	 */
	//@Test
	@Ignore
	public void ejecutarCorreosProcesoTest() throws BrioBPMException, ParseException {
		//envio de correos de procesos periodicos
		mailService.notifiacionUsuarios("BRIOMAX", "BRIOMAX-CENTRAL", "ES-MX");
	}
	
	/**
	 * Send simple mail message test.
	 * @throws ParseException 
	 * @throws BrioBPMException 
	 */
	@Test
	public void ejecutarCorreosProcesoRepseTest() throws BrioBPMException, ParseException {
		//envio de correos de procesos periodicos
		mailService.notifiacionUsuariosRepse("BRIOMAX", "BRIOMAX-CENTRAL", "ES-MX");
	}

}
