/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.business.service.core;

import javax.mail.MessagingException;

/**
 * El objetivo de la Interface IBPMMailSender.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Apr 13, 2020 10:39:37 AM Modificaciones:
 * @since JDK 1.8
 */
public interface IBPMMailSender {

	/**
	 * Send simple mail message.
	 * @param to el to.
	 * @param cc el cc.
	 * @param subject el subject.
	 * @param body el body.
	 * @return verdadero, si es exitoso.
	 */
	public boolean sendSimpleMailMessage(String[] to, String[] cc, String subject, String body);

	/**
	 * Send html mail message.
	 * @param to el to.
	 * @param cc el cc.
	 * @param subject el subject.
	 * @param body el body.
	 * @return verdadero, si es exitoso.
	 */
	public boolean sendHtmlMailMessage(String[] to, String[] cc, String subject, String body);

	/**
	 * 
	 * @param to
	 * @param cc
	 * @param subject
	 * @param body
	 * @param imagenBytes
	 * @return
	 */
	boolean sendHtmlMailMessage(String[] to, String[] cc, String subject, String body, byte[] imagenBytes);

	/**
	 * Enviar correo con imagen.
	 * @param to el to.
	 * @param subject el subject.
	 * @param bodyHtml el body html.
	 * @param pathRepse el path repse.
	 * @throws MessagingException la messaging exception.
	 */
	boolean enviarCorreoConImagen(String to, String subject, String bodyHtml, String pathRepse) 
			throws MessagingException;

}
