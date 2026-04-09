/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */
package com.briomax.briobpm.rest.security;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * El objetivo de la Class RedirectToLogin.java es ...
 * @author Jose Ramon Alfonso.
 * @version 1.0 Fecha de creacion Nov 18, 2020 11:09:23 AM Modificaciones:
 * @since JDK 1.8
 */
public class RedirectToLogin {

	/**
	 * Crear una nueva instancia del objeto redirect to login.
	 */
	public RedirectToLogin() {
	}

	/**
	 * Redirect.
	 * @param request el request.
	 * @param response el response.
	 */
	public void redirect(HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		Cookie jsess = null;
		if (cookies != null) {
			for (Cookie c : cookies) {
				if (c.getName().equals("JSESSIONID")) {
					jsess = c;
					break;
				}
			}
			if (jsess != null) {
				Cookie clone = (Cookie) jsess.clone();
				clone.setMaxAge(0);
				response.addCookie(clone);
			}
		}

		response.addHeader("redirectloc", "/");

	}

}
