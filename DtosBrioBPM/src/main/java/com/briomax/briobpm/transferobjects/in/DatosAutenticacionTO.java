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
 * El objetivo de la Class DatosAutenticacionTO.java es ...
 * @author RIGOBERTO OLVERA
 * @version 1.0 Fecha de creacion 29/01/2020 12:34:39 PM Modificaciones:
 * @since JDK 1.8
 */
@Schema(title = "DatosAutenticacionTO", description = "Datos de Autenticacion del Usuario.")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DatosAutenticacionTO implements Serializable {

	/** serial Version UID. */
	private static final long serialVersionUID = 8337855687969507535L;

	/** El atributo o variable cve usuario. */
	@Schema(name = "cveUsuario", description = "Clave del Usuario.", example = "BrioBpm", implementation = String.class)
	private String cveUsuario;

	/** El atributo o variable cve entidad. */
	@Schema(name = "cveEntidad", description = "Clave de la Entidad.", example = "BRIOMAX",
			implementation = String.class)
	private String cveEntidad;

	/** El atributo o variable cve localidad. */
	@Schema(name = "cveLocalidad", description = "Clave de la Localidad.", example = "BRIOMAX-CENTRAL",
			implementation = String.class)
	private String cveLocalidad;

	/** El atributo o variable cve idioma. */
	@Schema(name = "cveIdioma", description = "Clave del Idioma.", example = "ES-MX", implementation = String.class)
	private String cveIdioma;

	/** El atributo o variable cve moneda. */
	@Schema(name = "cveMoneda", description = "Clave de la Moneda.", example = "MXN", implementation = String.class)
	private String cveMoneda;

}
