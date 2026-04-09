/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.rest.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.stereotype.Component;

/**
 * El objetivo de la Class BpmInvalidSessionStrategy.java es ...
 * @author Jose Ramon Alfonso.
 * @version 1.0 Fecha de creacion Nov 18, 2020 11:11:25 AM Modificaciones:
 * @since JDK 1.8
 */
@Component
public class BpmInvalidSessionStrategy implements InvalidSessionStrategy {

	/**
	 * Crear una nueva instancia del objeto bpm invalid session strategy.
	 */
	public BpmInvalidSessionStrategy() {
	}

	/** 
	 * {@inheritDoc}
	 * @see org.springframework.security.web.session.InvalidSessionStrategy#onInvalidSessionDetected(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void onInvalidSessionDetected(HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		RedirectToLogin rdirect = new RedirectToLogin();
		rdirect.redirect(request, response);
	}

}
