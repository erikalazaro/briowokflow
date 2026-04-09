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
 * El objetivo de la Class SectionButtons.java es ...
 * @author Sara Ventura
 * @version 1.0 Fecha de creacion Mar 4, 2020 4:28:39 PM Modificaciones:
 * @since JDK 1.8
 */
@Schema(description = "ButtonsRow", title = "Botones de la propiedad.")
@Builder
@Data
public class ButtonsRow implements Serializable {

	/** Serial Version UID. */
	private static final long serialVersionUID = 4151285865487940482L;

	/** El atributo o variable cve seccion. */
	@Schema(name = "orden", description = "Clave del orden", example = "1",
			implementation = Integer.class)
	private Integer orden;

	/** El atributo o variable etiqueta. */
	@Schema(name = "etiqueta", description = "Descripción del boton", example = "Solicitud de Soporte",
			implementation = String.class)
	private String etiqueta;

	/** El atributo o variable contenido. */
	@Schema(name = "urlAccion",
			description = "URL para ejecutar",
			example = "/datos/seccion", implementation = String.class)
	private String urlAccion;
	
	/** El atributo o variable contenido. */
	@Schema(name = "funcionAccion",
			description = "Funcion para ejecutar",
			example = "activaRepse", implementation = String.class)
	private String funcionAccion;

	/** El atributo o variable etiqueta. */
	@Schema(name = "atributos", description = "Atributos de Objeto", example = "contrato",
			implementation = String.class)
	private String atributos;
	
	/** El atributo o variable etiqueta. */
	@Schema(name = "variables", description = "Variables del sistea", example = "contra_trabajador",
			implementation = String.class)
	private String variables;
	
	
}
