/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.transferobjects;

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
 * El objetivo de la Class SectionVariablesTO.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Mar 4, 2020 4:26:33 PM Modificaciones:
 * @since JDK 1.8
 */
@Schema(description = "SectionVariablesTO", title = "Lista de Variables de un Ocurrencia de una Seccion del Formulario.")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SectionVariablesTO implements Serializable {

	/** Serial Version UID. */
	private static final long serialVersionUID = 6035594771371002960L;

	/** El atributo o variable cve variable. */
	@Schema(name = "cveVariable", description = "Clave de la Variable o campo", example = "VPRO_DESCRIPCION_SOLICITUD",
			implementation = String.class)
	private String cveVariable;

	/** El atributo o variable values. */
	@Schema(name = "values", description = "Valores del campo o varibale variable",
			example = "No se muestran datos en el dashboard", implementation = String.class)
	private List<String> values;

}
