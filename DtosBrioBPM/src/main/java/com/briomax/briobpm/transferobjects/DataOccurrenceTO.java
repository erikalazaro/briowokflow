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

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * El objetivo de la Class DataOccurrenceTO.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Mar 4, 2020 4:25:05 PM Modificaciones:
 * @since JDK 1.8
 */
@Schema(description = "DataOccurrenceTO", title = "Ocurrencia de una Seccion del Formulario.")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DataOccurrenceTO implements Serializable {

	/** Serial Version UID. */
	private static final long serialVersionUID = 8698270079401334957L;

	/** El atributo o variable ocurrencia. */
	@Schema(name = "ocurrencia", description = "Secuencia de la Ocurrencia.", example = "1",
			implementation = Integer.class)
	private Integer ocurrencia;

	/** El atributo o variable nuevo. */
	@Schema(name = "nueva", description = "Bandera para identificar si es nueva la Ocurrencia.", example = "false",
			implementation = boolean.class)
	private boolean nueva;

	/** El atributo o variable section variables. */
	@ArraySchema(schema = @Schema(name = "sectionVariables", description = "Lista de Variables.",
			implementation = SectionVariablesTO.class))
	private List<SectionVariablesTO> sectionVariables;

}
