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
 * El objetivo de la Class BurbleChartDataset.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion May 12, 2021 1:47:54 PM Modificaciones:
 * @since JDK 1.8
 */
@Schema(title = "BurbleChartDataset.", description = "BurbleChartDataset.")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BurbleChartDataset implements Serializable {

	/** Serial Version UID. */
	private static final long serialVersionUID = -6390673274318336387L;

	/** El atributo o variable label. */
	@Schema(name = "label", description = "Label.", example = "", implementation = String.class)
	private String label;

	/** El atributo o variable xlabel. */
	@Schema(name = "xlabel", description = "Label X.", example = "", implementation = String.class)
	private String xlabel;

	/** El atributo o variable ylabel. */
	@Schema(name = "ylabel", description = "Label Y.", example = "", implementation = String.class)
	private String ylabel;

	/** El atributo o variable rlabel. */
	@Schema(name = "rlabel", description = "Label R.", example = "", implementation = String.class)
	private String rlabel;

	/** El atributo o variable background color. */
	@Schema(name = "backgroundColor", description = "Background Color.", example = "", implementation = String.class)
	private String backgroundColor;

	/** El atributo o variable border color. */
	@Schema(name = "borderColor", description = "Border Color.", example = "", implementation = String.class)
	private String borderColor;

	/** El atributo o variable hover background color. */
	@Schema(name = "hoverBackgroundColor", description = "Hover Background Color.", example = "",
			implementation = String.class)
	private String hoverBackgroundColor;

	/** El atributo o variable hover border color. */
	@Schema(name = "hoverBorderColor", description = "Hover Border Color.", example = "", implementation = String.class)
	private String hoverBorderColor;

	/** El atributo o variable data. */
	@ArraySchema(schema = @Schema(name = "data", description = "Data.", implementation = BurbleChartData.class))
	private List<BurbleChartData> data;

}
