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
 * El objetivo de la Class FormularioDinamico.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Mar 4, 2020 4:30:11 PM Modificaciones:
 * @since JDK 1.8
 */
@Schema(description = "Formulario Dinamico", title = "Formulario Dinamico")
@Data
@Builder
public class FormularioDinamico implements Serializable {

	/** Serial Version UID. */
	private static final long serialVersionUID = 555825467467595604L;

	/** El atributo o variable sections. */
	@ArraySchema(schema = @Schema(name = "sections", description = "Secciones del Formulario.",
			implementation = Section.class))
	private List<Section> sections;

	/** El atributo o variable notacion polaca. */
	@Schema(name = "behavior", description = "Comportamiento.", implementation = Hehavior.class)
	private Hehavior behavior ;

	/** El atributo o variable etiqueta bitacora. */
	private String etiquetaGuardar;

	/** El atributo o variable habilitar bitacora. */
	private String habilitarGuardar;
	
	/** El atributo o variable habilitar ejecutar. */
	private String habilitarTerminar;

	/** El atributo o variable etiqueta terminar. */
	private String etiquetaTerminar;
}
