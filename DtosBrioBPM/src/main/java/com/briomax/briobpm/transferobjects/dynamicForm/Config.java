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
 * El objetivo de la Class Config.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Mar 4, 2020 4:32:44 PM Modificaciones:
 * @since JDK 1.8
 */
@Schema(description = "Config", title = "Configuracion de las columnas del Grid.")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Config implements Serializable {

	/** Serial Version UID. */
	private static final long serialVersionUID = 3077315388776791817L;

	/** El atributo o variable columns. */
	@ArraySchema(schema = @Schema(name = "columns", description = "Columnas del Grid.",
			implementation = ColumnGrid.class))
	private List<ColumnGrid> columns;

	/** El atributo o variable buttons. */
	@Schema(name = "buttons", description = "Buttons.", implementation = Buttons.class)
	private Buttons buttons;

}
