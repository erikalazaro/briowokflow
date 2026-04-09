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

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/**
 * El objetivo de la Class EntidadTO.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Aug 12, 2020 11:09:51 AM Modificaciones:
 * @since JDK 1.8
 */
@Schema(description = "EntidadTO", title = "Entidad.")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EntidadTO implements ITransferObject {

	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;

	/** El atributo o variable cve entidad. */
	@Schema(name = "cveEntidad", description = "Clave de la Entidad.", example = "BRIOMAX",
			implementation = String.class)
	private String cveEntidad;
	
	/** El atributo o variable descripcion. */
	@Schema(name = "descripcion", description = "Descripcion de la Entidad", example = "BRIOMAX",
			implementation = String.class)
	private String descripcion;
	
	/** El atributo o variable localidades. */
	@ArraySchema(schema = @Schema(name = "localidades", description = "Localidades de la Entidad.",
			implementation = LocalidadTO.class))
	List<LocalidadTO> localidades;

}
