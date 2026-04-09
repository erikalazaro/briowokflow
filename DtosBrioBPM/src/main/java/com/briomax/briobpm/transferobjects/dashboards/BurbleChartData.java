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

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * El objetivo de la Class BurbleChartData.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion May 12, 2021 1:50:15 PM Modificaciones:
 * @since JDK 1.8
 */
@Schema(title = "BurbleChartData.", description = "BurbleChartData.")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BurbleChartData implements Serializable {

	/** Serial Version UID. */
	private static final long serialVersionUID = 655526333685487812L;

	/** El atributo o variable x. */
	@Schema(name = "x", description = "X.", example = "0", implementation = BigDecimal.class)
	private BigDecimal x;

	/** El atributo o variable y. */
	@Schema(name = "y", description = "Y.", example = "0", implementation = BigDecimal.class)
	private BigDecimal y;

	/** El atributo o variable r. */
	@Schema(name = "r", description = "R.", example = "0", implementation = BigDecimal.class)
	private BigDecimal r;

}
