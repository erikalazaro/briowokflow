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

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * El objetivo de la Class Seccion.java es ...
 * @author Sara Venruta
 * @version 1.0 Fecha de creacion 21/02/2024 PM Modificaciones:
 * @since JDK 1.8
 */
@Schema(title = "Seccion.", description = "Seccion.")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Seccion implements Serializable {

	/** Serial Version UID. */
	private static final long serialVersionUID = -940698310781150899L;

	/** El atributo o variable title. */
	@Schema(name = "title", description = "Titulo Seccion del Dashboard.", example = "Cumplimiento Global", implementation = String.class)
	private String title;
	
	/** El atributo o variable title. */
	@Schema(name = "subTitle", description = "Subtiulo Seccion del Dashboard.", example = "Périodo anterior de Cumplimiento", implementation = String.class)
	private String subTitle;

	/** El atributo o variable order. */
	@Schema(name = "order", description = "Orden de presentación de la Seccion.", example = "1",
			implementation = Integer.class)
	private int order;

	/** El atributo o variable title. */
	@Schema(name = "tieneEscala", description = "SI y NO.", example = "", implementation = String.class)
	private String tieneEscala;
	
	/** El atributo o variable labels. */
	@ArraySchema(schema = @Schema(name = "scale", description = "scale.", implementation = int.class))
	private int[] rightScale;
	
	/** El atributo o variable labels. */
	@ArraySchema(schema = @Schema(name = "scale", description = "scale.", implementation = int.class))
	private int[] leftScale;

	/** El atributo o variable graphics. */
	@ArraySchema(schema = @Schema(name = "graphics", description = "Graficas del Dashboard " +
			"(BarChart, BurbleChart, KPI, LineChart, PieChart, TableChart)."))
	private List<Grafico> graphics;

}
