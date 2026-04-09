/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados. Este software contiene informacion
 * confidencial propiedad de Briomax Consulting por lo cual no puede ser reproducido, distribuido o alterado sin el
 * consentimiento previo de Briomax Consulting.
 */

package com.briomax.briobpm.transferobjects.catalogs;

import com.briomax.briobpm.transferobjects.core.ITransferObject;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * El objetivo de la Class UsuarioRol.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Jun 14, 2021 7:34:54 PM Modificaciones:
 * @since JDK 1.8
 */

@Schema(description = "UsuarioRol", title = "Usuarios de Un Proceso-Rol.")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRol implements ITransferObject {

	/** Serial Version UID. */
	private static final long serialVersionUID = -7312592477087774425L;

	/** El atributo o variable seleccionado. */
	@Schema(name = "seleccionado", description = "Seleccionado.", example = "true", implementation = Boolean.class)
	private Boolean seleccionado;

	/** El atributo o variable cve usuario. */
	@Schema(name = "cveUsuario", description = "Clave del Usuario.", example = "", implementation = String.class)
	private String cveUsuario;

	/** El atributo o variable nom usuario. */
	@Schema(name = "nomUsuario", description = "Nombre del Usuario", example = "", implementation = String.class)
	private String nomUsuario;

	/** El atributo o variable des localidad. */
	@Schema(name = "desLocalidad", description = "Descripcion de la Localidad.", example = "Briomax Central.",
			implementation = String.class)
	private String desLocalidad;

}
