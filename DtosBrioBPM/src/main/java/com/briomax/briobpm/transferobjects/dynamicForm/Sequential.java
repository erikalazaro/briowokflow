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
import lombok.Builder;
import lombok.Data;

/**
 * El objetivo de la Class Sequential.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Mar 4, 2020 4:28:11 PM Modificaciones:
 * @since JDK 1.8
 */
@Schema(description = "Sequential", title = "Tipo de Seccion SECUENCIAL.")
@Data
@Builder
public class Sequential implements Serializable {

	/** Serial Version UID. */
	private static final long serialVersionUID = 4917214344971762230L;

	/** El atributo o variable ocurrencia. */
	@Schema(name = "ocurrencia", description = "Secuencia de la Ocurrencia.", example = "1", 
			implementation = Integer.class)
	private Integer ocurrencia;

	/** El atributo o variable nuevo. */
	@Schema(name = "nueva", description = "Bandera para identificar si es nueva la Ocurrencia.", example = "false",
			implementation = boolean.class)
	private boolean nueva;

	/** El atributo o variable columns. */
	@ArraySchema(schema = @Schema(name = "columns", description = "Columnas de la Seccion SECUENCIAL.",
			implementation = ColumnSection.class))
	private List<ColumnSection> columns;

}
