/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */
package com.briomax.briobpm.rest.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Class BpmAuthenticationFilter.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Mar 25, 2020 4:57:17 PM Modificaciones:
 * @since JDK 1.8
 */
@Slf4j
public class BpmAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	/** La Constante GUION. */
	private static final String GUION = "-";

	/** La Constante SPRING_SECURITY_FORM_ENTIDAD_KEY. */
	private static final String SPRING_SECURITY_FORM_ENTIDAD_KEY = "cveEntidad";
	
	/** La Constante SPRING_SECURITY_FORM_LOCALIDAD_KEY. */
	private static final String SPRING_SECURITY_FORM_LOCALIDAD_KEY = "cveLocalidad";
	
	/** La Constante SPRING_SECURITY_FORM_IDIOMA_KEY. */
	private static final String SPRING_SECURITY_FORM_IDIOMA_KEY = "cveIdioma";

	/** 
	 * {@inheritDoc}
	 * @see org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter#attemptAuthentication(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
		throws AuthenticationException {

		if (!request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
		}

		log.debug("\tENTIDAD: {}", obtainEntity(request));
		log.debug("\tLOCALIDAD: {}", obtainLocality(request));
		log.debug("\tIDIOMA: {}", obtainLanguage(request));
		log.debug("\tUSUARIO: {}", obtainUsername(request));
		log.debug("\tPASSWORD: {}", obtainPassword(request));

		UsernamePasswordAuthenticationToken authRequest = getAuthRequest(request);
		setDetails(request, authRequest);
		Authentication authentication = this.getAuthenticationManager().authenticate(authRequest);
		log.trace("Filter: {}", authentication);
		return authentication;
	}

	/**
	 * Obtener el valor de auth request.
	 * @param request el request.
	 * @return el auth request.
	 */
	private UsernamePasswordAuthenticationToken getAuthRequest(HttpServletRequest request) {
		String username = obtainUsername(request);
		if (StringUtils.isBlank(username)) {
			username = GUION;
		}
		String fullUserName = String.format("%s|%s|%s|%s", obtainEntity(request), obtainLocality(request),
			obtainLanguage(request), username);
		log.debug("{}", fullUserName);
		String password = obtainPassword(request);
		if (StringUtils.isBlank(password)) {
			password = GUION;
		} 
		return new UsernamePasswordAuthenticationToken(fullUserName, password);
	}

	/**
	 * Obtain entidad.
	 * @param request el request.
	 * @return el string.
	 */
	protected String obtainEntity(HttpServletRequest request) {
		String entidad = request.getParameter(SPRING_SECURITY_FORM_ENTIDAD_KEY);
		if (StringUtils.isBlank(entidad)) {
			entidad = GUION;
		}
		return entidad;
	}

	/**
	 * Obtain localidad.
	 * @param request el request.
	 * @return el string.
	 */
	protected String obtainLocality(HttpServletRequest request) {
		String localidad = request.getParameter(SPRING_SECURITY_FORM_LOCALIDAD_KEY);
		if (StringUtils.isBlank(localidad)) {
			localidad = GUION;
		}
		return localidad;
	}

	/**
	 * Obtain idioma.
	 * @param request el request.
	 * @return el string.
	 */
	protected String obtainLanguage(HttpServletRequest request) {
		String idioma = request.getParameter(SPRING_SECURITY_FORM_IDIOMA_KEY);
		if (StringUtils.isBlank(idioma)) {
			idioma = GUION;
		}
		return idioma;
	}

}
