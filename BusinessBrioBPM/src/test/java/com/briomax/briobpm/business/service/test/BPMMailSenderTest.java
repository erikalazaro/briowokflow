/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.business.service.test;

import org.junit.Before;
import org.junit.Test;

import com.briomax.briobpm.business.service.core.IBPMMailSender;
import com.briomax.briobpm.business.test.base.AbstractCoreTest;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * El objetivo de la Class BPMMailSenderTest.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Apr 11, 2020 12:14:51 PM Modificaciones:
 * @since JDK 1.8
 */
public class BPMMailSenderTest extends AbstractCoreTest {

	/** El atributo o variable to. */
	private String[] to;
	
	/** El atributo o variable cc. */
	private String[] cc;

	/** El atributo o variable bpm mail sender. */
	@Autowired
	private IBPMMailSender bpmMailSender;
	
	/**
	 * Before.
	 */
	@Before
	public void before() {
		to = new String[] {"Erika Vázquez <frodriguez@briomax.com>",
							"Gabriela Arias <frodriguez@briomax.com>",
							"Roberto Quintana <frodriguez@briomax.com>",
							"Miguel García Saavedra <frodriguez@briomax.com>"
						};
		cc = new String[] {"José Ramón A. <jalfonso@briomax.com>", "Rigoberto Olvera <rolvera@briomax.com>"};
	}
	
	/**
	 * Send simple mail message test.
	 */
	@Test
	public void sendSimpleMailMessageTest() {
		bpmMailSender.sendSimpleMailMessage(to, cc, "TEST TEXT ### Le ha sido asignada una actividad del proceso: Incidencias SMI-Solicitud de soporte	Estimado Usuario:",
			"Le informamos que durante la ejecución del proceso Incidencias SMI, le ha sido asignada la siguiente actividad:\r\n" + 
			"Solicitud de soporte\r\n" + 
			"con fecha de asignación:\r\n" + 
			"10 Apr 2020 21:26:13,\r\n" + 
			"la cual tiene como fecha de vencimiento: \r\n" + 
			"NA.\r\n" + 
			"\r\n" + 
			"Favor de Ingresar a BRIO-Workflow para su atención.\r\n" + 
			"\r\n" + 
			"Atentamente\r\n" + 
			"\r\n" + 
			"\r\n" + 
			"BRIO-Workflow");
	}

	/**
	 * Send html mail message test.
	 */
	@Test
	public void sendHtmlMailMessageTest() {
		bpmMailSender.sendHtmlMailMessage(to, cc,
			"TEST HTML ### Le ha sido asignada una actividad del proceso: Incidencias SMI-Solicitud de soporte	Estimado Usuario:",
			"<font face=\"Arial\" size=2>Estimado Usuario:<br><br>Le informamos que durante la ejecución del proceso <b>Incidencias SMI</b>, <br>le ha sido asignada la siguiente actividad:<br><br><b>Solicitud de soporte</b><br><br>con fecha de asignación:<br><br><b>11 Apr 2020 14:10:43,</b><br><br>la cual tiene como fecha de vencimiento: <br><br><b>NA.</b><br><br>Favor de ingresar a BRIO-Workflow para su atención.<br><br>Atentamente<br><br><br><br><br></font><b>BRIO-Workflow</b>");
	}

}
