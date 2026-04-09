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
 * El objetivo de la Class Hehavior.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion May 20, 2021 7:59:48 PM Modificaciones:
 * @since JDK 1.8
 */
@Schema(description = "Hehavior.", title = "Hehavior.")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Hehavior implements Serializable {

	/** Serial Version UID. */
	private static final long serialVersionUID = -3097105511914338464L;

	/** El atributo o variable specification. */
	@Schema(name = "specification", description = "Especificacion.", example = "", implementation = String.class)
	private String specification;

	/** El atributo o variable messages. */
	@ArraySchema(schema = @Schema(name = "messages", description = "Mensajes de la Especificacion.",
			example = "['{campo1} 1 inf', '{campo2} 2 war', '{campo3} 3 err']",
			implementation = String.class))
	private List<String> messages;

}
