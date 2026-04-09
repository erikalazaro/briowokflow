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
 * El objetivo de la Class TableChart.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion May 12, 2021 2:01:26 PM Modificaciones:
 * @since JDK 1.8
 */
@Schema(title = "TableChart.", description = "TableChart.", allOf = IGraph.class)
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TableChart implements IGraph, Serializable {

	/** Serial Version UID. */
	private static final long serialVersionUID = -2801041745825484209L;

	/** El atributo o variable tipo. */
	@Schema(name = "tipo", description = "Tipo de Grafica.", example = "", implementation = String.class)
	private TypeChartEnum tipo;

	/** El atributo o variable order. */
	@Schema(name = "order", description = "Orden de presentación.", example = "1", implementation = Integer.class)
	private int order;

	/** El atributo o variable labels. */
	@ArraySchema(schema = @Schema(name = "labels", description = "Labels.", implementation = String.class))
	private String[] labels;

	/** El atributo o variable records. */
	@Schema(name = "records", description = "Records.", example = "", implementation = String.class)
	private String records;

	/** El atributo o variable types. */
	@ArraySchema(schema = @Schema(name = "types", description = "Types.", implementation = String.class))
	private String[] types;

}
