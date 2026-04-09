/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.transferobjects.in;

import java.io.Serializable;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * El objetivo de la Class SituacionGlobalCumplimientoTO.java es ...
 * 
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Oct 31 2024 12:34:39 PM Modificaciones:
 * @since JDK 1.8
 */
@Schema(title = "SituacionGlobalCumplimientoTO", description = "DTO donde se almacenan los datos correspondientes a las graficas de lso Dashboards.")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SituacionGlobalCumplimientoTO implements Serializable {

	/** serial Version UID. */
	private static final long serialVersionUID = 8337855687969507535L;

	private List<PeriodoAnteriorCumplimientoTO> periodoAnteriorCumplimiento;

	private List<PeriodoActualCumplimientoTO> periodoActualCumplimiento;
}
