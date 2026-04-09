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
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;

/**
 * El objetivo de la Class BpmSessionExpirationStrategy.java es ...
 * @author Jose Ramon Alfonso.
 * @version 1.0 Fecha de creacion Nov 18, 2020 11:09:42 AM Modificaciones:
 * @since JDK 1.8
 */
@Component
public class BpmSessionExpirationStrategy implements SessionInformationExpiredStrategy {

	/**
	 * Crear una nueva instancia del objeto bpm session expiration strategy.
	 */
	public BpmSessionExpirationStrategy() {
	}

	/** 
	 * {@inheritDoc}
	 * @see org.springframework.security.web.session.SessionInformationExpiredStrategy#onExpiredSessionDetected(org.springframework.security.web.session.SessionInformationExpiredEvent)
	 */
	@Override
	public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
		RedirectToLogin rdirect = new RedirectToLogin();
		//event.getResponse().setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
		event.getResponse().setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		rdirect.redirect(event.getRequest(), event.getResponse());
	}

}
