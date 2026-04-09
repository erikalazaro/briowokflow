/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados. Este software contiene informacion
 * confidencial propiedad de Briomax Consulting por lo cual no puede ser reproducido, distribuido o alterado sin el
 * consentimiento previo de Briomax Consulting.
 */

package com.briomax.briobpm.transferobjects.catalogs;

import java.util.List;

import com.briomax.briobpm.transferobjects.core.ITransferObject;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * El objetivo de la Class IdUsuarioRol.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Jun 14, 2021 1:34:34 PM Modificaciones:
 * @since JDK 1.8
 */
@Schema(description = "Ids Usuarios Rol-Proceso", title = "Id de Usuario-Rol.")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuariosRol implements ITransferObject {

	/** Serial Version UID. */
	private static final long serialVersionUID = -3397260012315050431L;

	/** El atributo o variable cve entidad. */
	@JsonIgnore
	@Schema(name = "cveEntidad", description = "Clave de la Emtidad.", example = "BRIOMAX",
			implementation = String.class)
	private String cveEntidad;

	/** El atributo o variable cve proceso. */
	@Schema(name = "cveProceso", description = "Clave del Proceso.", example = "DUMMY", implementation = String.class)
	private String cveProceso;

	/** El atributo o variable version. */
	@Schema(name = "version", description = "Version del Proceso.", example = "1.00", implementation = String.class)
	private String version;

	/** El atributo o variable cve rol. */
	@Schema(name = "cveRol", description = "Clave del Rol.", example = "ROL DUMMY 1", implementation = String.class)
	private String cveRol;

	/** El atributo o variable cve usuarios. */
	@ArraySchema(schema = @Schema(name = "cveUsuarios", description = "Claves de Uusarios.", implementation = String.class))
	private List<String> cveUsuarios;

}
