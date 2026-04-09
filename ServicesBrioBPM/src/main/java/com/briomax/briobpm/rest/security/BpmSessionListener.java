/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.rest.security;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.security.web.session.HttpSessionCreatedEvent;
import org.springframework.security.web.session.HttpSessionDestroyedEvent;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Class BpmSessionListener.java es ...
 * @author Jose Ramon Alfonso.
 * @version 1.0 Fecha de creacion Nov 18, 2020 11:20:15 AM Modificaciones:
 * @since JDK 1.8
 */
@Component
@Slf4j
public class BpmSessionListener {

	/**
	 * Crear una nueva instancia del objeto bpm session listener.
	 */
	public BpmSessionListener() {
	}

	/** El atributo o variable stout. */
	@Value("${server.servlet.session.timeout:14m}")
	String sessionTimeout;

	/**
	 * Se invoca cuando se crea session.
	 * @param event el event.
	 */
	@EventListener
	public void sessionCreated(HttpSessionCreatedEvent event) {
		HttpSession ses = event.getSession();
		int timeout = sessiontimeout2sec(sessionTimeout);
		ses.setMaxInactiveInterval(timeout);
		log.debug("SESION ABIERTA: ID: {}, CTIME: {}, DECLTOUT {}, MAXINT {} ", ses.getId(), fechahora(ses.getCreationTime()), sessionTimeout, ses.getMaxInactiveInterval());
	}

	/**
	 * Session destroyed.
	 * @param event el event.
	 */
	@EventListener
	public void sessionDestroyed(HttpSessionDestroyedEvent event) {
		HttpSession ses = event.getSession();
		log.debug("SESION CERRADA: ID: {}, CTIME: {}, LASTACC: {}, NOW: {}", ses.getId(), fechahora(ses.getCreationTime()), fechahora(ses.getLastAccessedTime()), fechahora((new Date()).getTime()) );
	}

	/**
	 * Fechahora.
	 * @param millis el millis.
	 * @return el string.
	 */
	private String fechahora(long millis) {
		Date d = new Date(millis);
		String pattern = "yyyy-MM-dd'T'HH:mm:ssZ";
		java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat(pattern);
		return simpleDateFormat.format (d);
	}

	/**
	 * Sessiontimeout 2 sec.
	 * @param toutstr el toutstr.
	 * @return el int.
	 */
	private int sessiontimeout2sec(String toutstr) {
		String regex = "(\\d+) *(M|m|S|s)?";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(toutstr.trim());
		if (matcher.matches()) {
			int time = Integer.parseInt(matcher.group(1));
			String suffix = matcher.group(2);
			if (suffix != null && suffix.equalsIgnoreCase("m")) {
				return time * 60;
			} else {
				return time;
			}
		} else {
			return 15 * 60;
		}
	}

}
