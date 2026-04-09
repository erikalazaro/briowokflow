/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.rest.controller;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * El objetivo de la Class ApiError.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Jan 27, 2021 2:25:26 PM Modificaciones:
 * @since JDK 1.8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiError implements Serializable {

	/** Serial Version UID. */
	private static final long serialVersionUID = 3709000357190300957L;

	/** El atributo o variable timestamp. */
	private Date timestamp;

	/** El atributo o variable status. */
	private int status;

	/** El atributo o variable tier. */
	private String tier;

	/** El atributo o variable path. */
	private String path;

	/** El atributo o variable error. */
	private String error;

	/** El atributo o variable message. */
	private String message;

}
