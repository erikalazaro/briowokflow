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
import lombok.Builder;
import lombok.Data;

/**
 * El objetivo de la Class ColumnSection.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Mar 4, 2020 4:33:09 PM Modificaciones:
 * @since JDK 1.8
 */
@Schema(description = "ColumnSection", title = "Tipo de Presentación SECUENCIAL")
@Data
@Builder
public class ColumnSection implements Serializable {

	/** Serial Version UID. */
	private static final long serialVersionUID = 3694640098910231954L;

	/** El atributo o variable secuencia. */
	@Schema(name = "secuencia", description = "", example = "1", implementation = Integer.class)
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

}
