/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */
package com.briomax.briobpm.transferobjects.catalogs;

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
 * El objetivo de la Class Proceso.java es ...
 * @author RIGOBERTO OLVERA
 * @version 1.0 Fecha de creacion 29/01/2020 12:29:01 PM Modificaciones:
 * @since JDK 1.8
 */
@Schema(title = "Proceso.", description = "Proceso.")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Proceso implements Serializable {

	/** Serial Version UID. */
	private static final long serialVersionUID = -416229056731699793L;

	/** El atributo o variable cve proceso. */
	@Schema(name = "cveProceso", description = "Clave del Proceso.", example = "DUMMY", implementation = String.class)
	private String cveProceso;

	/** El atributo o variable versiones. */
	@ArraySchema(schema = @Schema(name = "versiones", description = "Versiones del Proceso.",
			implementation = ProcesoVersion.class))
	private List<ProcesoVersion> versiones;

}
