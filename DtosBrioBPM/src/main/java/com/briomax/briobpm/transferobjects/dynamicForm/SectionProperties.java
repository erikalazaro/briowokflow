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
 * El objetivo de la Class SectionProperties.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Mar 4, 2020 4:28:39 PM Modificaciones:
 * @since JDK 1.8
 */
@Schema(description = "SectionProperties", title = "Porpiedades de la Seccion.")
@Builder
@Data
public class SectionProperties implements Serializable {

	/** Serial Version UID. */
	private static final long serialVersionUID = 4151285865487940482L;

	/** El atributo o variable cve seccion. */
	@Schema(name = "cveSeccion", description = "Clave de la Seccion", example = "DATOS_GENERALES_SOLICITUD",
			implementation = String.class)
	private String cveSeccion;

	/** El atributo o variable etiqueta. */
	@Schema(name = "etiqueta", description = "Descripción de la Seccion", example = "Solicitud de Soporte",
			implementation = String.class)
	private String etiqueta;

	/** El atributo o variable contenido. */
	@Schema(name = "contenido",
			description = "Tipo de Contenido VARIABLES, TAREAS o DOCUMENTOS, " +
				"para la presentación secuencial solo aplica el tipo de contenido VARIABLES",
			example = "SECUENCIAL", implementation = String.class)
	private String contenido;

}
