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
 * El objetivo de la Class Properties.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Mar 4, 2020 4:29:42 PM Modificaciones:
 * @since JDK 1.8
 */
@Schema(description = "Properties", title = "Propiedades de la columna.")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Properties implements Serializable {

	/** Serial Version UID. */
	private static final long serialVersionUID = -4843612455373288217L;

	/** El atributo o variable cve variable. */
	@Schema(name = "cveVariable", description = "Clave de la variable", example = "SITUACION",
			implementation = String.class)
	private String cveVariable;

	/** El atributo o variable etiqueta. */
	@Schema(name = "etiqueta", description = "Titulo de la columna.", example = "SITUACION",
			implementation = String.class)
	private String etiqueta;

	/** El atributo o variable width. */
	@Schema(name = "width", description = "Ancho de la columna.", example = "25")
	private Integer width;

	/** El atributo o variable tipo dato. */
	@Schema(name = "tipoDato", description = "Tipo de dato ALFANUMERICO, ENTERO, DECIMAL, FECHA, Etc.",
			example = "ALFANUMERICO", implementation = String.class)
	private String tipoDato;

	/** El atributo o variable visible. */
	@Schema(name = "visible", description = "Es visible SI/NO", example = "SI", implementation = String.class)
	private String visible;

	/** El atributo o variable send save. */
	@Schema(name = "envioGrabar", description = "Si el dato de la columna se envía a guardar SI/NO", example = "SI",
			implementation = String.class)
	private String envioGrabar;
	
	/** El atributo o variable width. */
	@Schema(name = "totalColumnas", description = "Total de numero de columnas.", example = "25")
	private Integer totalColumnas;
	
	/** El atributo o variable width. */
	@Schema(name = "numeroColumna", description = "Numero de columna.", example = "25")
	private Integer numeroColumna;
	
	/** El atributo o variable width. */
	@Schema(name = "renglon", description = "Renglón en el que se debe colocar.", example = "25")
	private Integer renglon;

}
