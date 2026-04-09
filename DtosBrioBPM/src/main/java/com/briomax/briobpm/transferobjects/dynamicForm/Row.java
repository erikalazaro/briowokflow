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
 * El objetivo de la Class Row.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion May 15, 2020 2:24:07 PM Modificaciones:
 * @since JDK 1.8
 */
@Schema(description = "Row", title = "Fila del Grid.")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Row implements Serializable {

	/** Serial Version UID. */
	private static final long serialVersionUID = 2831494903057672759L;

	/** El atributo o variable ocurrencia. */
	@Schema(name = "ocurrencia", description = "Secuencia de la Ocurrencia.", example = "1",
			implementation = Integer.class)
	private Integer ocurrencia;
	
	/** El atributo o variable nuevo. */
	@Schema(name = "nueva", description = "Bandera para identificar si es nueva la Ocurrencia.", example = "false",
			implementation = boolean.class)
	private boolean nueva;

	/** El atributo o variable cells. */
	@ArraySchema(schema = @Schema(name = "cells", description = "Datos del Grid.", implementation = String.class))
	private List<List<List<String>>> cells;

	@ArraySchema(schema = @Schema(name = "acciones", description = "Acciones a mostrar en el Grid.",
			implementation = Action.class))
	/** El atributo o variable acciones. */
	private List<Action> acciones;

	@ArraySchema(schema = @Schema(name = "estilos", description = "Style css a aplicar al registro del Grid.",
			implementation = Style.class))
	/** El atributo o variable estilos. */
	private List<Style> estilos;
	
	/** Datos imagenes. */
	@Schema(name = "datos de imagen", description = "lista de imagen.", example = "")
	List<byte[]> imagenes;
	
	/** El valor de la tipoDato. */
	@Schema(name = "tipoDato", description = "Dato la para Imagen", example = "tipo de Dato")
	String tipoDato;
}
