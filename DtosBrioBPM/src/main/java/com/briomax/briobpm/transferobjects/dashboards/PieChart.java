/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.transferobjects.dashboards;

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
 * El objetivo de la Class PieChart.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion May 12, 2021 1:58:23 PM Modificaciones:
 * @since JDK 1.8
 */
@Schema(title = "PieChart.", description = "PieChart.", allOf = IGraph.class)
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PieChart implements IGraph, Serializable {

	/** Serial Version UID. */
	private static final long serialVersionUID = 6455584687824240609L;

	/** El atributo o variable tipo. */
	@Schema(name = "tipo", description = "Tipo de Grafica.", example = "", implementation = String.class)
	private TypeChartEnum tipo;

	/** El atributo o variable order. */
	@Schema(name = "order", description = "Orden de presentación.", example = "1", implementation = Integer.class)
	private int order;

	/** El atributo o variable labels. */
	@ArraySchema(schema = @Schema(name = "labels", description = "Labels.", implementation = String.class))
	private String[] labels;

	/** El atributo o variable data. */
	@ArraySchema(schema = @Schema(name = "data", description = "Data.", implementation = String.class))
	private String[] data;

}
