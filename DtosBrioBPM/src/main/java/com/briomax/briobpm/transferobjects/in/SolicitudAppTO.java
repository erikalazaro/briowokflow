/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */
package com.briomax.briobpm.transferobjects.in;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * El objetivo de la Class SolicitudAppTO.java es ...
 * @author Sara Ventura
 * @version 1.0 Fecha de creacion 21/02/2024 12:29:01 PM Modificaciones:
 * @since JDK 1.8
 */
@Schema(title = "SolicitudAppTO.", description = "Datos del Proceso.")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SolicitudAppTO implements Serializable {

	/** serial Version UID. */
	private static final long serialVersionUID = -564984068778186706L;

	/** El atributo o variable cve rol. */
	@Schema(name = "username", description = "Clave de usuario.", example = "Admin.Opera", implementation = String.class)
	private String username;

	/** El atributo o variable cve proceso. */
	@Schema(name = "cveEntidad", description = "Clave de entidad.", example = "BRIOMAX", implementation = String.class)
	private String cveEntidad;

	/** El atributo o variable version. */
	@Schema(name = "cveLocalidad", description = "Clave de la localidad", example = "Briomax-Mexico", implementation = String.class)
	private String cveLocalidad;

	/** El atributo o variable cve nodo. */
	@Schema(name = "cveIdioma", description = "Clave del Idioma.", example = "ES_MX", implementation = String.class)
	private String cveIdioma;

}


