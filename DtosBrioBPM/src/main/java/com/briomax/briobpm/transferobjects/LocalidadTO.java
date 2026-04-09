/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.transferobjects;

import com.briomax.briobpm.transferobjects.core.ITransferObject;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * El objetivo de la Class LocalidadTO.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Feb 26, 2020 3:45:56 PM Modificaciones:
 * @since JDK 1.8
 */
@Schema(description = "LocalidadTO", title = "Localidad.")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LocalidadTO implements ITransferObject {

	/** serial Version UID. */
	private static final long serialVersionUID = -3547033960512223344L;

	/** El atributo o variable clave. */
	@Schema(name = "clave", description = "Clave de la Localidad.", example = "BRIOMAX-CENTRAL",
			implementation = String.class)
	private String clave;

	/** El atributo o variable nombre. */
	@Schema(name = "nombre", description = "Nombre de la Localidad", example = "BRIOMAX-CENTRAL",
			implementation = String.class)
	private String nombre;

	/** El atributo o variable tipo. */
	@Schema(name = "tipo", description = "Tipo.", example = "FILIAL", implementation = String.class)
	private String tipo;

	/** El atributo o variable direccion. */
	@Schema(name = "direccion", description = "Direccion.", example = "", implementation = String.class)
	private String direccion;

	/** El atributo o variable telefono. */
	@Schema(name = "telefono", description = "Telefono.", example = "(52)(55) 56 87 05 14 101", implementation = String.class)
	private String telefono;

	/** El atributo o variable email. */
	@Schema(name = "email", description = "Correo Electronico.", example = "support@company.com",
			implementation = String.class)
	private String email;

	/** El atributo o variable moneda. */
	@Schema(name = "moneda", description = "Moneda.", example = "Pesos Mexicanos", implementation = String.class)
	private String moneda;

	/** El atributo o variable husohorario. */
	@Schema(name = "husohorario", description = "Huso Horario.", example = "UTC-5", implementation = String.class)
	private String husohorario;

	/** El atributo o variable fecha. */
	@Schema(name = "fecha", description = "Fecha.", example = "Viernes, 28/05/2021", implementation = String.class)
	private String fecha;

	/** El atributo o variable hora. */
	@Schema(name = "hora", description = "Hora.", example = "11:45", implementation = String.class)
	private String hora;

}
