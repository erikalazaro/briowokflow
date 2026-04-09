/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.transferobjects.dashboards;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * El objetivo de la Enum TypeChartEnum.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion May 12, 2021 1:19:04 PM Modificaciones:
 * @since JDK 1.8
 */
@Schema(title = "TypeChartEnum.", description = "TypeChartEnum.")
@AllArgsConstructor
public enum TypeChartEnum {

	/** El atributo o variable bar. */
	BAR("BAR"),
	
	/** El atributo o variable burble. */
	BURBLE("BURBLE"),
	
	/** El atributo o variable kpi. */
	KPI("KPI"),
	
	/** El atributo o variable line. */
	LINE("LINE"),
	
	/** El atributo o variable pie. */
	PIE("PIE"),
	
	/** El atributo o variable table. */
	TABLE("TABLE"),
	
	/** El atributo o variable none. */
	NONE("");

	/** El atributo o variable value. */
	@Schema(name = "value", description = "Value.", example = "BAR", implementation = String.class)
	private @Getter String value;

	/**
	 * Obtener el valor de type chart.
	 * @param typeChart el type chart.
	 * @return el type chart.
	 */
	public static TypeChartEnum getTypeChart(final String typeChart) {
		if (typeChart != null) {
			for (TypeChartEnum function : TypeChartEnum.values()) {
				if (typeChart.toUpperCase().equals(function.getValue())) {
					return function;
				}
			}
		}
		return TypeChartEnum.NONE;
	}

}
