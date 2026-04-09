/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.rest.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.Date;

import javax.persistence.PersistenceException;

import org.hibernate.HibernateException;
import org.hibernate.exception.ConstraintViolationException;

import com.briomax.briobpm.common.exception.BrioBPMException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Class GlobalExceptionHandler.java es ...
 * @author Rigoberto Olvera.
 * @version 1.0 Fecha de creacion Jan 13, 2021 5:48:43 PM Modificaciones:
 * @since JDK 1.8
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	/** La Constante DATABASE. */
	private static final String DATABASE = "DATABASE";

	/**
	 * Handle.
	 * @param ex el ex.
	 * @param request el request.
	 * @return el response entity.
	 */
	@ExceptionHandler(BrioBPMException.class)
	public ResponseEntity<ApiError> handle(BrioBPMException ex, WebRequest request) {
		String tier = null;
		String accion = null;
		if (ex.isDataAccess()) {
			tier = DATABASE;
			accion = ex.getMensaje() + "\nContactar al Administrador de Bases de Datos.";
		}
		else {
			tier = "BACK";
			accion = ex.getMessage() + "\nContactar al Administrador del Back.";
		}
		return responseEntity(request, getError(ex), accion, tier);
	}

	/**
	 * Response entity.
	 * @param request el request.
	 * @param error el error.
	 * @param message el message.
	 * @param tier el tier.
	 * @return el response entity.
	 */
	private ResponseEntity<ApiError> responseEntity(WebRequest request, String error, String message, String tier) {
		ApiError ae = getApiError(request, error, message, tier);
		ResponseEntity<ApiError> response = new ResponseEntity<>(ae, HttpStatus.INTERNAL_SERVER_ERROR);
		log.debug("<<<<<< {}: {} ", ae.getPath(), response);
		return response;
	}

	/**
	 * Obtener el valor de error.
	 * @param ex el ex.
	 * @return el error.
	 */
	private String getError(Exception ex) {
		String error = ex.getMessage();
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		ex.printStackTrace(pw);
		String[] tracE = sw.toString().split("\n");
		for (String s : tracE) {
			if (s.startsWith("Caused by: com.microsoft.sqlserver.jdbc.SQLServerException:")) {
				String[] causa = s.split(":");
				error = causa[2];
			}
		}
		log.error(error, ex);
		return error;
	}

	/**
	 * Handle.
	 * @param ex el ex.
	 * @param request el request.
	 * @return el response entity.
	 */
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ApiError> handle(ConstraintViolationException ex, WebRequest request) {
		return responseEntity(request, ex.getSQLException().getMessage(), ex.getMessage(), DATABASE);
	}

	/**
	 * Handle.
	 * @param ex el ex.
	 * @param request el request.
	 * @return el response entity.
	 */
	@ExceptionHandler(PersistenceException.class)
	public ResponseEntity<ApiError> handle(PersistenceException ex, WebRequest request) {
		return responseEntity(request, getError(ex), ex.getMessage(), DATABASE);
	}

	/**
	 * Handle.
	 * @param ex el ex.
	 * @param request el request.
	 * @return el response entity.
	 */
	@ExceptionHandler(HibernateException.class)
	public ResponseEntity<ApiError> handle(HibernateException ex, WebRequest request) {
		return responseEntity(request, getError(ex), ex.getMessage(), DATABASE);
	}

	/**
	 * Handle.
	 * @param ex el ex.
	 * @param request el request.
	 * @return el response entity.
	 */
	@ExceptionHandler(SQLException.class)
	public ResponseEntity<ApiError> handle(SQLException ex, WebRequest request) {
		return responseEntity(request, getError(ex), ex.getMessage(), DATABASE);
	}

	/**
	 * Obtener el valor de path.
	 * @param request el request.
	 * @return el path.
	 */
	private String getPath(WebRequest request) {
		ServletWebRequest servletWebRequest = (ServletWebRequest) request;
		return servletWebRequest.getRequest().getContextPath() + servletWebRequest.getRequest().getServletPath();
	}

	/**
	 * Obtener el valor de api error database.
	 * @param request el request.
	 * @param error el error.
	 * @param message el message.
	 * @param tier el tier.
	 * @return el api error database.
	 */
	private ApiError getApiError(WebRequest request, String error, String message, String tier) {
		return ApiError.builder()
				.timestamp(new Date())
				.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.error(error)
				.message(message)
				.path(getPath(request))
				.tier(tier)
				.build();
	}

}
