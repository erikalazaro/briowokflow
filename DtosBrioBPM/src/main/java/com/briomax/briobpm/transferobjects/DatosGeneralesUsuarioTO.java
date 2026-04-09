/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */
package com.briomax.briobpm.transferobjects;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * El objetivo de la Class DatosGeneralesUsuarioTO.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Feb 25, 2020 3:02:03 PM Modificaciones:
 * @since JDK 1.8
 */
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(title = "DatosGeneralesUsuarioTO", description = "Datos Generales del Usuario.")
public class DatosGeneralesUsuarioTO implements Serializable {

	/** serial Version UID. */
	private static final long serialVersionUID = 6435219964446280721L;

	/** El atributo o variable clave. */
	@Schema(name = "clave", description = "Clave del Usuario.", example = "BrioBpm", implementation = String.class)
	private String clave;

	/** El atributo o variable nombre. */
	@Schema(name = "nombre", description = "Nombre del Usuario.", example = "Brio Bpm", implementation = String.class)
	private String nombre;

	/** El atributo o variable cve entidad. */
	@Schema(name = "cveEntidad", description = "Clave de la Entidad.", example = "BRIOMAX", implementation = String.class)
	private String cveEntidad;

	/** El atributo o variable des entidad. */
	@Schema(name = "desEntidad", description = "Descripon de la Entidad.", implementation = String.class)
	private String desEntidad;

	/** El atributo o variable cve localidad. */
	@Schema(name = "cveLocalidad", description = "Clave de la Localidad.", example = "BRIOMAX-CENTRAL",
			implementation = String.class)
	private String cveLocalidad;

	/** El atributo o variable des localidad. */
	@Schema(name = "desLocalidad", description = "Descripcion de la Localidad.", implementation = String.class)
	private String desLocalidad;

	/** El atributo o variable cve moneda. */
	@Schema(name = "cveMoneda", description = "Clave de la Moneda.", example = "MXN", implementation = String.class)
	private String cveMoneda;

	/** El atributo o variable des moneda. */
	@Schema(name = "desMoneda", description = "Descripcion de la Moneda.", example = "Pesos Mexicanos",
			implementation = String.class)
	private String desMoneda;

	/** El atributo o variable cve idioma. */
	@Schema(name = "cveIdioma", description = "Clave del Idioma.", example = "ES-MX", implementation = String.class)
	private String cveIdioma;

	/** El atributo o variable des idioma. */
	@Schema(name = "desIdioma", description = "Descripcion del Idioma.", example = "Español México",
			implementation = String.class)
	private String desIdioma;

	/** El atributo o variable cve huso horario. */
	@Schema(name = "cveHusoHorario", description = "Clave del Huso horario.", implementation = String.class)
	private String cveHusoHorario;

	/** El atributo o variable des huso horario. */
	@Schema(name = "desHusoHorario", description = "Descripcion Huso horiario.", implementation = String.class)
	private String desHusoHorario;

	/** El atributo o variable tipo. */
	@Schema(name = "tipo", description = "tipo.", example = "INTERNO", implementation = String.class)
	private String tipo;

	/** El atributo o variable situacion. */
	@Schema(name = "situacion", description = "Situación del Usuario.", example = "HABILITADO",
			implementation = String.class)
	private String situacion;

	/** El atributo o variable fotografia. */
	@Schema(name = "fotografia", description = "Nombre de la imagen de la Fotografia.", example = "user.jpg",
			implementation = String.class)
	private String fotografia;

}
