/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.transferobjects.dynamicForm;

import java.io.Serializable;
import java.util.List;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * El objetivo de la Class Control.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Mar 4, 2020 4:32:18 PM Modificaciones:
 * @since JDK 1.8
 */
@Schema(description = "Control", title = "Control grafico de la columna.")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Control implements Serializable {

	/** Serial Version UID. */
	private static final long serialVersionUID = 4072791800958197983L;
	
	/** El atributo o variable tipo control. */
	@Schema(name = "tipoControl",
			description = "Tipo de Control TEXTBOX, TEXTAREA, COMBOBOX, LISTBOX, CHECKBOX, RADIOBUTTON, Etc.",
			example = "TEXTBOX", implementation = String.class)
	private String tipoControl;
	
	/** El atributo o variable tipo interaccion. */
	@Schema(name = "tipoInteraccion", description = "Interacción ENTRADA/SALIDA", example = "SALIDA", implementation = String.class)
	private String tipoInteraccion;
	
	/** El atributo o variable options. */
	@ArraySchema(schema = @Schema(name = "options", description = "Lista de valores para ComboBox", implementation = String.class))
	private List<List<String>> options;
}
