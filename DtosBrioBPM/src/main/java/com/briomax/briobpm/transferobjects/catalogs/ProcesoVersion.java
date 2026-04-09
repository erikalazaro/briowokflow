/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */
package com.briomax.briobpm.transferobjects.catalogs;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * El objetivo de la Class ProcesoVersion.java es ...
 * @author RIGOBERTO OLVERA
 * @version 1.0 Fecha de creacion 29/01/2020 12:29:01 PM Modificaciones:
 * @since JDK 1.8
 */
@Schema(title = "Proceso Version.", description = "Versiones del Proceso.")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProcesoVersion implements Serializable {

	/** serial Version UID. */
	private static final long serialVersionUID = -3559565879853496273L;

	/** El atributo o variable version. */
	@Schema(name = "version", description = "Version del Proceso.", example = "1.00", implementation = String.class)
	private String version;

	/** El atributo o variable descripcion. */
	@Schema(name = "descripcion", description = "Descripcion del Proceso.", example = "Proceso Dummy",
			implementation = String.class)
	private String descripcion;

}
