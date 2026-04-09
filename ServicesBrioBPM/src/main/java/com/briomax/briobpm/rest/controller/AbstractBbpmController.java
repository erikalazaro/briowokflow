/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */
package com.briomax.briobpm.rest.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.rest.security.BpmUser;
import com.briomax.briobpm.transferobjects.DatosAdicionalesUsuarioTO;
import com.briomax.briobpm.transferobjects.DatosGeneralesUsuarioTO;
import com.briomax.briobpm.transferobjects.DatosUsuarioTO;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Class AbstractBbpmController.java es ...
 * @author RIGOBERTO OLVERA
 * @version 1.0 Fecha de creacion 29/01/2020 12:36:36 PM Modificaciones:
 * @since JDK 1.8
 */
@Slf4j
public abstract class AbstractBbpmController {

	/** El atributo o variable CONTEXT PATH. */
	@Value("${server.servlet.context-path}")
	protected @Getter @Setter String contextPath;

	/** El atributo o variable mapper. */
	@Autowired
	protected ObjectMapper mapper;

	/**
	 * Obtener el valor de user session data.
	 * @return el user session data.
	 */
	protected DatosUsuarioTO getUserSessionData() {
		DatosUsuarioTO userData = null;
		BpmUser user = null;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			Object details = auth.getDetails();
			if (details != null) {
				if (details instanceof BpmUser) {
					user = (BpmUser) details;
					userData = user.getUserData();
				}
			}
		}
		log.debug("getUserSessionData >>>>>>>>>>>>>>userData {}, >>>>>>>>>>>user {} ", userData, user);
		return userData;
	}

	/**
	 * Modify session.
	 * @param key el key.
	 * @param value el value.
	 * @return verdadero, si es exitoso.
	 */
	protected boolean modifySession(String key, String value) {
		log.debug(">>>>>>>>>>>>>>>>>>>>>>modifySession {} {}", key, value);
		boolean successful = false;
		DatosUsuarioTO userData = null;
		BpmUser user = null;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			log.trace("{}", auth);
			Object details = auth.getDetails();
			if (details != null) {
				log.trace("{}", details);
				if (details instanceof BpmUser) {
					user = (BpmUser) details;
					userData = user.getUserData();
					log.debug("{}", userData);
					if(StringUtils.isNotBlank(key) && StringUtils.isNotBlank(value)) {
						switch (key) {
							case "IDIOMA":
								log.debug("IDIOMA ACTUAL: {}", userData.getDatosAutenticacion().getCveIdioma());
								userData.getDatosAutenticacion().setCveIdioma(value);
								successful = true;
								log.debug("IDIOMA MODIFICADO: {}", userData.getDatosAutenticacion().getCveIdioma());
								break;
							case "MONEDA":
								log.debug("MONEDA ACTUAL: {}", userData.getDatosAutenticacion().getCveMoneda());
								userData.getDatosAutenticacion().setCveMoneda(value);
								successful = true;
								log.debug("MONEDA MODIFICADA: {}", userData.getDatosAutenticacion().getCveMoneda());
								break;
							default:
								log.trace("Parametro no disponible en sesion.");
								break;
						}
					}
				}
			}
		}
		return successful;
	}

	/**
	 * Obtener el valor de user session bpm.
	 * @return el user session bpm.
	 */
	protected DatosAutenticacionTO getUserSessionBpm() {
		DatosAutenticacionTO dataSession = null;
		DatosUsuarioTO user = getUserSessionData();
		String cveUsuario =  user.getGenerales().getClave();
		if (user != null) {
			user.getDatosAutenticacion().setCveUsuario(cveUsuario);
			dataSession = user.getDatosAutenticacion();
		}
		log.trace(">>>> dataSession {}.", dataSession);
		return dataSession;
	}

	/**
	 * Obtener el valor de general user data.
	 * @return el general user data.
	 */
	protected DatosGeneralesUsuarioTO getGeneralUserData() {
		DatosGeneralesUsuarioTO general = null;
		DatosUsuarioTO user = getUserSessionData();
		if (user != null) {
			general = user.getGenerales();
		}
		return general;
	}

	/**
	 * Obtener el valor de additional user data.
	 * @return el additional user data.
	 */
	protected DatosAdicionalesUsuarioTO getAdditionalUserData() {
		DatosAdicionalesUsuarioTO additional = null;
		DatosUsuarioTO user = getUserSessionData();
		if (user != null) {
			additional = user.getAdicionales();
		}
		return additional;
	}

	/**
	 * Obtener el valor de authorities.
	 * @return el authorities.
	 */
	protected List<GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			authorities =  (ArrayList<GrantedAuthority>) authentication.getAuthorities();
		}
		return authorities;
	}

	/**
	 * Printer json.
	 * @param prefix el prefix.
	 * @param object el object.
	 */
	protected void printerJson(String prefix, Object object) {
		String json = converterJsonAsString(object);
		log.trace(" <<< {}\n{}", prefix, json);
	}

	/**
	 * Converter json as string.
	 * @param object el object.
	 * @return el string.
	 */
	protected String converterJsonAsString(Object object) {
		try {
			return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
		}
		catch (JsonProcessingException jsonEx) {
			log.error("{} <> {}", jsonEx.getMessage(), object);
			return null;
		}
	}

	/**
	 * Obtener el valor de code http status.
	 * @param meta el meta.
	 * @return el code http status.
	 */
	protected HttpStatus getCodeHttpStatus(RetMsg meta) {
		if (meta == null) {
			return HttpStatus.INTERNAL_SERVER_ERROR; 
		}
		HttpStatus code = null;
		String status = meta.getStatus() == null ? "" : meta.getStatus().toUpperCase();
		switch (status) {
			case "OK":
				code = HttpStatus.OK;
				break;
			case "AVISO":
				code = HttpStatus.CONTINUE;
				break;
			case "ERROR":
				code = HttpStatus.BAD_REQUEST;
				break;
			default:
				code = HttpStatus.INTERNAL_SERVER_ERROR;
				break;
		}
		return code;
	}

}
