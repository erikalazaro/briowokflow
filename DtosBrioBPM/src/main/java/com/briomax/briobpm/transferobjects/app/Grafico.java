/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.transferobjects.app;

import java.io.Serializable;
import java.util.List;

import com.briomax.briobpm.transferobjects.dashboards.BarDataset;
import com.briomax.briobpm.transferobjects.dashboards.TypeChartEnum;

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
@Schema(title = "Chart.", description = "Chart.")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Grafico implements Serializable {

	/** Serial Version UID. */
	private static final long serialVersionUID = 5753517089240052500L;

	/** El atributo o variable order. */
	@Schema(name = "id", description = "Orden de presentación de la Seccion.", example = "1",
			implementation = Integer.class)
	private String id;
	
	/** El atributo o variable tipo. */
	@Schema(name = "tipo", description = "Tipo de Grafica.", example = "", implementation = String.class)
	private String tipo;

	/** El atributo o variable labels. */
	@ArraySchema(schema = @Schema(name = "labels", description = "Labels.", implementation = String.class))
	private String[] labels;

	/** El atributo o variable datasets. */
	@ArraySchema(schema = @Schema(name = "datasets", description = "Datasets.", implementation = BarDataset.class))
	private List<Datos> data;

}
