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
 * El objetivo de la Class Section.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion May 12, 2021 1:39:59 PM Modificaciones:
 * @since JDK 1.8
 */
@Schema(title = "Section.", description = "Section.")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Section implements Serializable {

	/** Serial Version UID. */
	private static final long serialVersionUID = -940698310781150899L;

	/** El atributo o variable title. */
	@Schema(name = "title", description = "Titulo Seccion del Dashboard.", example = "", implementation = String.class)
	private String title;

	/** El atributo o variable order. */
	@Schema(name = "order", description = "Orden de presentación de la Seccion en el Dashboard.", example = "1",
			implementation = Integer.class)
	private int order;

	/** El atributo o variable graphics. */
//	@ArraySchema(schema = @Schema(name = "graphics", description = "Graficas del Dashboard " +
//		"(BarChart, BurbleChart, KPI, LineChart, PieChart, TableChart).", implementation = IGraph.class,
//		oneOf = {BarChart.class, BurbleChart.class, KPI.class, LineChart.class, PieChart.class, TableChart.class},
//		discriminatorProperty = "tipo", discriminatorMapping = {
//			@DiscriminatorMapping( value = "BAR", schema = BarChart.class ),
//			@DiscriminatorMapping( value = "BURBLE", schema = BurbleChart.class ),
//			@DiscriminatorMapping( value = "KPI", schema = KPI.class ),
//			@DiscriminatorMapping( value = "LINE", schema = LineChart.class ),
//			@DiscriminatorMapping( value = "PIE", schema = PieChart.class ),
//			@DiscriminatorMapping( value = "TABLE", schema = TableChart.class )
//		}))
	@ArraySchema(schema = @Schema(name = "graphics", description = "Graficas del Dashboard " +
			"(BarChart, BurbleChart, KPI, LineChart, PieChart, TableChart).", implementation = IGraph.class))
	private List<IGraph> graphics;

}
