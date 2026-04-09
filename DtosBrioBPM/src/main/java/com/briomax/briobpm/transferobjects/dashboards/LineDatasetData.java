/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.transferobjects.dashboards;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * El objetivo de la Class LineDatasetData.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion May 12, 2021 1:55:51 PM Modificaciones:
 * @since JDK 1.8
 */
@Schema(title = "LineDatasetData.", description = "LineDatasetData.")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LineDatasetData implements Serializable {

	/** Serial Version UID. */
	private static final long serialVersionUID = -3163059246853557961L;

	/** El atributo o variable label. */
	@Schema(name = "label", description = "Label.", example = "", implementation = String.class)
	private String label;

	/** El atributo o variable data. */
	@ArraySchema(schema = @Schema(name = "data", description = "Data.", implementation = Integer.class))
	private  BigDecimal[] data;

	/** El atributo o variable tipo. */
	@Schema(name = "tipo", description = "Tipo.", example = "", implementation = String.class)
	private String tipo;

	/** El atributo o variable type. */
	@Schema(name = "type", description = "Type Chart.", example = "", implementation = String.class)
	private String type;

	/** El atributo o variable y axis ID. */
	@Schema(name = "yAxisID", description = "yAxisID.", example = "y-axis-1", implementation = String.class)
	private String yAxisID;

}
