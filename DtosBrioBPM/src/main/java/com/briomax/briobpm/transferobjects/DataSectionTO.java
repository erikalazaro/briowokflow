/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.transferobjects;

import java.io.Serializable;
import java.util.List;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * El objetivo de la Class DataSectionTO.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Mar 4, 2020 4:25:33 PM Modificaciones:
 * @since JDK 1.8
 */
@Schema(description = "DataSectionTO", title = "Datos de la Seccion del Formulario.")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DataSectionTO implements Serializable {

	/** Serial Version UID. */
	private static final long serialVersionUID = -1021940706565087218L;

	/** El atributo o variable cve section. */
	@Schema(name = "cveSection", description = "Clave de la Seccion", example = "DATOS_GENERALES_SOLICITUD",
			implementation = String.class)
	private String cveSection;

	/** El atributo o variable type. */
	@Schema(name = "type", description = "Tipo de Presentacion SECUENCIAL/GRID", example = "SECUENCIAL",
			implementation = String.class)
	private String type;

	/** El atributo o variable content. */
	@Schema(name = "content", description = "Tipo de Contenido VARIABLES, TAREAS o DOCUMENTOS.", example = "VARIABLES",
			implementation = String.class)
	private String content;

	/** El atributo o variable data occurrence. */
	@ArraySchema(schema = @Schema(name = "dataOccurrence",
			description = "OCURRENCIAS (para la presentación SECUENCIAL solo es una)",
			implementation = DataOccurrenceTO.class))
	private List<DataOccurrenceTO> dataOccurrence;

}
