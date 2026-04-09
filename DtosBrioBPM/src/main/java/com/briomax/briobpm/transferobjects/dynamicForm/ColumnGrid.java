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
 * El objetivo de la Class ColumnGrid.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Mar 4, 2020 4:33:38 PM Modificaciones:
 * @since JDK 1.8
 */
@Schema(description = "ColumnGrid", title = "Columna del Grid.")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ColumnGrid implements Serializable {

	/** Serial Version UID. */
	private static final long serialVersionUID = 6792477366305191186L;

	/** El atributo o variable secuencia. */
	@Schema(name = "secuencia", description = "Orden de presentación de la columna.", example = "1",
			implementation = Integer.class)
	private Integer secuencia;

	/** El atributo o variable properties. */
	@Schema(name = "properties", description = "Propiedades de la Columna.", implementation = Properties.class)
	private Properties properties;

	/** El atributo o variable format. */
	@Schema(name = "format", description = "Formatos de la Columna.", implementation = Format.class)
	private Format format;

	/** El atributo o variable control. */
	@Schema(name = "control", description = "Control grafico de la Columna.", implementation = Control.class)
	private Control control;

	/** El atributo o variable filter. */
	@Schema(name = "filter", description = "Filtro de la Columna.", implementation = Filter.class)
	private Filter filter;

}
