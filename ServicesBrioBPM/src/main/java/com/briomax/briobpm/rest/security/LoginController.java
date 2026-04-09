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
import javax.servlet.http.HttpSession;

import com.briomax.briobpm.transferobjects.ValueType;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.WebAttributes;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

/**
 * The objetive the Class MainPageController.java is ...
 * 
 * @author Rigoberto Olvera (Briomax Consulting S.A. de C.V.).
 * @version 1.0 date creation 23/01/2019 12:45:46 PM
 * @since JDK 1.8
 */
@Tag(name = "Login", description = "Autenticación de Usuarios.")
@RestController
@Slf4j
public class LoginController {

	/**
	 * Main.
	 * @return the response entity
	 */
	@Operation(operationId = "main", summary = "Autenticación Exitosa", description = "Autenticación Exitosa",
			hidden = true)
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", ref = "OK"),
		@ApiResponse(responseCode = "400", ref = "BadRequestError"),
		@ApiResponse(responseCode = "401", ref = "UnauthorizedError"),
		@ApiResponse(responseCode = "403", ref = "ForbiddenError"),
		@ApiResponse(responseCode = "404", ref = "NotFoundError"),
		@ApiResponse(responseCode = "405", ref = "MethodNotAllowedError"),
		@ApiResponse(responseCode = "408", ref = "RequestTimeoutError"),
		@ApiResponse(responseCode = "500", ref = "InternalServerError")
	})
	@PostMapping(path = "/main", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ValueType> main(HttpServletRequest request) {
		log.debug("\t authok /main.html request " + request);
		ValueType vt = ValueType.builder().type("authok").value("/.html").build();
		return new ResponseEntity<ValueType>(vt, HttpStatus.OK);
	}

	/**
	 * Authfail.
	 * @return the response entity
	 */
	@Operation(operationId = "authfail", summary = "Error de Autenticación", description = "Error de Autenticación",
			hidden = true)
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", ref = "OK"),
		@ApiResponse(responseCode = "400", ref = "BadRequestError"),
		@ApiResponse(responseCode = "401", ref = "UnauthorizedError"),
		@ApiResponse(responseCode = "403", ref = "ForbiddenError"),
		@ApiResponse(responseCode = "404", ref = "NotFoundError"),
		@ApiResponse(responseCode = "405", ref = "MethodNotAllowedError"),
		@ApiResponse(responseCode = "408", ref = "RequestTimeoutError"),
		@ApiResponse(responseCode = "500", ref = "InternalServerError")
	})
	@PostMapping(path = "/authfail", produces = MediaType.APPLICATION_JSON_VALUE)
	//@GetMapping(path = "/authfail", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ValueType>
			authfail(@RequestAttribute(WebAttributes.AUTHENTICATION_EXCEPTION) AuthenticationException exception) {
		log.error("\t authfail {}", exception.getMessage());
		ValueType vt = ValueType.builder().type("authfail").value(exception.getMessage()).build();
		return new ResponseEntity<ValueType>(vt, HttpStatus.OK);
	}

	/**
	 * Logout.
	 * @return the response entity
	 */
	@Operation(operationId = "jslogout", summary = "Log Out", description = "Cerrar Sesión.")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", ref = "OK"),
		@ApiResponse(responseCode = "400", ref = "BadRequestError"),
		@ApiResponse(responseCode = "401", ref = "UnauthorizedError"),
		@ApiResponse(responseCode = "403", ref = "ForbiddenError"),
		@ApiResponse(responseCode = "404", ref = "NotFoundError"),
		@ApiResponse(responseCode = "405", ref = "MethodNotAllowedError"),
		@ApiResponse(responseCode = "408", ref = "RequestTimeoutError"),
		@ApiResponse(responseCode = "500", ref = "InternalServerError")
	})
	@PostMapping(path = "/jslogout", produces = MediaType.APPLICATION_JSON_VALUE)
	public void logout(HttpServletRequest request, HttpServletResponse response) {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession(false);
		SecurityContextHolder.clearContext();
		if (session != null) {
			session.invalidate();
		}
		for (Cookie cookie : attr.getRequest().getCookies()) {
			cookie.setMaxAge(0);
		}
		RedirectToLogin rdirect = new RedirectToLogin();
		rdirect.redirect(request, response);
	}

}
