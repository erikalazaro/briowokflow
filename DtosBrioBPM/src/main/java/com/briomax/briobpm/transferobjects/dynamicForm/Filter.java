/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.transferobjects.dynamicForm;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * El objetivo de la Class Filter.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Mar 4, 2020 4:31:17 PM Modificaciones:
 * @since JDK 1.8
 */
@Schema(description = "Filter", title = "Filtro de la Columna.")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Filter implements Serializable {

	/** Serial Version UID. */
	private static final long serialVersionUID = -295478188679324747L;

	/** El atributo o variable filtro. */
	@Schema(name = "filtro", description = "Filtro por columna SI/NO", example = "NO", implementation = String.class)
	private String filtro;

	/** El atributo o variable multi seleccion. */
	@Schema(name = "multiSeleccion", description = "Selección multiple", example = "NO", implementation = String.class)
	private String multiSeleccion;

	/** El atributo o variable opcion todos. */
	@Schema(name = "opcionTodos", description = "Mostrar la opción TODOS SI/NO", example = "NO",
			implementation = String.class)
	private String opcionTodos;

	/** El atributo o variable etiqueta todos. */
	@Schema(name = "etiquetaTodos", description = "Etiqueta a mostrar para la opción TODOS", example = "TODOS",
			implementation = String.class)
	private String etiquetaTodos;

}
