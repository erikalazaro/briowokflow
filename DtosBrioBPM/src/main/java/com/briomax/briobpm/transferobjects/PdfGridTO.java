/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.transferobjects;

import java.util.List;

import com.briomax.briobpm.transferobjects.core.ITransferObject;
import com.briomax.briobpm.transferobjects.dynamicForm.ColumnGrid;
import com.briomax.briobpm.transferobjects.dynamicForm.Properties;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/**
 * El objetivo de la Class PdfGridTO.java es ...
 * @author Sara Ventura
 * @version 1.0 Fecha de creacion Mar 10, 2020 12:22:52 PM Modificaciones:
 * @since JDK 1.8
 */
@Schema(title = "PdfGridTO", description = "Tabla de información de PDF")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PdfGridTO implements ITransferObject {

	/** Serial Version UID. */
	private static final long serialVersionUID = 8648753675829842698L;

	/** El atributo o variable cells. */
	@ArraySchema(schema = @Schema(name = "cells", description = "Datos del Grid.", implementation = String.class))
	private List<Object> cells;

	/** El atributo o variable columns. */
	@ArraySchema(schema = @Schema(name = "columns", description = "Columnas del Grid.",
			implementation = ColumnGrid.class))
	private List<Properties> columns;
}
