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
 * El objetivo de la Class Buttons.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Feb 25, 2020 4:26:49 PM Modificaciones:
 * @since JDK 1.8
 */
@Schema(description = "Buttons", title = "Botones de Acciones en el Grid.")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Buttons implements Serializable {

	/** Serial Version UID. */
	private static final long serialVersionUID = 9065538033678114645L;

	/** El atributo o variable add row. */
	@Schema(name = "addRow", description = "Agregar botón para la opción de agregar filas, subir documento",
			example = "ADD", implementation = String.class)
	private String addRow;

	/** El atributo o variable add description. */
	@Schema(name = "addDescription", description = "Descripcion para el botón agregar.", example = "Add",
			implementation = String.class)
	private String addDescription;

	/** El atributo o variable del row. */
	@Schema(name = "delRow", description = "Agregar botón para la opciónde eliminar filas, borrar documento",
			example = "DEL", implementation = String.class)
	private String delRow;

	/** El atributo o variable del description. */
	@Schema(name = "delDescription", description = "Descripción para el botón eliminar.", example = "Delete",
			implementation = String.class)
	private String delDescription;

}
