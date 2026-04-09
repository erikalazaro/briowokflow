/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.transferobjects.app;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * El objetivo de la Class Chart.java es ...
 * @author Sara Ventura
 * @version 1.0 Fecha de creacion 21/02/2024 Modificaciones:
 * @since JDK 1.8
 */
@Schema(title = "Datos.", description = "Datos.")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Datos implements Serializable {

	/** Serial Version UID. */
	private static final long serialVersionUID = 5753517089240052500L;

	/** El atributo o variable tipo. */
	@Schema(name = "id", description = "Clave.", example = "", implementation = String.class)
	private String id;

	/** El atributo o variable labels. */
	@ArraySchema(schema = @Schema(name = "x", description = "Valor de x.", implementation = String.class))
	private String x;

	/** El atributo o variable datasets. */
	@ArraySchema(schema = @Schema(name = "y", description = "Valor de y.", implementation = String.class))
	private int y;

}
