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
 * El objetivo de la Class LineDataset.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion May 12, 2021 1:54:36 PM Modificaciones:
 * @since JDK 1.8
 */
@Schema(title = "LineDataset.", description = "LineDataset.")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LineDataset implements Serializable {

	/** Serial Version UID. */
	private static final long serialVersionUID = 7036016886785082676L;

	/** El atributo o variable data. */
	@ArraySchema(schema = @Schema(name = "data", description = "Data.", implementation = LineDatasetData.class))
	private List<LineDatasetData> data;

}
