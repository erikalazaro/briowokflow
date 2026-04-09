/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.transferobjects.dynamicForm;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * El objetivo de la Class Section.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Mar 4, 2020 4:29:11 PM Modificaciones:
 * @since JDK 1.8
 */
@Schema(description = "Section", title = "Seccion del Formulario.")
@Data
@Builder
public class Section implements Serializable {

	/** Serial Version UID. */
	private static final long serialVersionUID = 1376195689415573986L;

	/** El atributo o variable orden. */
	@Schema(name = "orden", description = "Orden de presentación de la Seccion", example = "1",
			implementation = Integer.class)
	private Integer orden;

	/** El atributo o variable properties. */
	@Schema(name = "properties", description = "Propiedades de la Seccion.", implementation = SectionProperties.class)
	private SectionProperties properties;

	/** El atributo o variable sequential. */
	@Schema(name = "sequential", description = "Tipo de Presentación SECUENCIAL", implementation = Sequential.class)
	private Sequential sequential;

	/** El atributo o variable grid. */
	@Schema(name = "grid", description = "Tipo de Presentación GRID", implementation = DataGrid.class)
	private DataGrid grid;

	/** El atributo o variable grid. */
	@Schema(name = "button", description = "Seccion de botones", implementation = SectionButtons.class)
	private SectionButtons button;
	
	private BigDecimal registroSelecconables;

}
