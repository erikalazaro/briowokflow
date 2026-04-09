/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */
package com.briomax.briobpm.transferobjects.dashboards;

import java.io.Serializable;
import java.util.List;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * El objetivo de la Class LineChart.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion May 12, 2021 1:53:40 PM Modificaciones:
 * @since JDK 1.8
 */
@Schema(title = "LineChart.", description = "LineChart.", allOf = IGraph.class)
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LineChart implements IGraph, Serializable {

	/** Serial Version UID. */
	private static final long serialVersionUID = 9184022507884611214L;

	/** El atributo o variable tipo. */
	@Schema(name = "tipo", description = "Tipo de Grafica.", example = "", implementation = String.class)
	private TypeChartEnum tipo;

	/** El atributo o variable order. */
	@Schema(name = "order", description = "Orden de presentación.", example = "1", implementation = Integer.class)
	private int order;

	/** El atributo o variable labels. */
	@ArraySchema(schema = @Schema(name = "labels", description = "Labels.", implementation = String.class))
	private String[] labels;

	/** El atributo o variable datasets. */
	@ArraySchema(schema = @Schema(name = "datasets", description = "Datasets.", implementation = LineDataset.class))
	private List<LineDataset> datasets;

}
