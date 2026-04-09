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

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * El objetivo de la Class SectionButtons.java es ...
 * @author Sara Ventura
 * @version 1.0 Fecha de creacion Mar 4, 2020 4:28:39 PM Modificaciones:
 * @since JDK 1.8
 */
@Schema(description = "SectionButtons", title = "Botones de la Seccion.")
@Builder
@Data
public class SectionButtons implements Serializable {

	/** Serial Version UID. */
	private static final long serialVersionUID = 4151285865487940482L;

	/** El atributo o variable cve seccion. */
	@Schema(name = "numButtons", description = "Numero de botones", example = "1",
			implementation = Integer.class)
	private Integer numButtons;

	/** El atributo o variable etiqueta. */
	@Schema(name = "tail", description = "Lista de boton")
	private List<ButtonsRow> tail;
	
}
