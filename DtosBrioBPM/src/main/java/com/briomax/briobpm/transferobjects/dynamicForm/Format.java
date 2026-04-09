/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.transferobjects.dynamicForm;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * El objetivo de la Class Format.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Mar 4, 2020 4:30:35 PM Modificaciones:
 * @since JDK 1.8
 */
@Schema(description = "Format", title = "Formato de la columna.")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Format implements Serializable {
	
	/** Serial Version UID. */
	private static final long serialVersionUID = -8083779754260117895L;

	/** El atributo o variable longitud. */
	@Schema(name = "longitud", description = "Longitud del texto", example = "5", implementation = Integer.class)
	private Integer longitud;

	/** El atributo o variable enteros. */
	@Schema(name = "enteros", description = "Número de entero para los digitos.", example = "3",
			implementation = Integer.class)
	private Integer enteros;

	/** El atributo o variable decimales. */
	@Schema(name = "decimales", description = "Número de decimales para los digitos", example = "2",
			implementation = Integer.class)
	private Integer decimales;

	/** El atributo o variable formato fecha. */
	@Schema(name = "formatoFecha", description = "Formato para los tipos de datos FECHA/HORA", example = "YYYY-DD-MM",
			implementation = String.class)
	private String formatoFecha;

	/** El atributo o variable color dato. */
	@Schema(name = "colorDato", description = "Color del Dato SI/NO", example = "NO", implementation = String.class)
	private String colorDato;

}
