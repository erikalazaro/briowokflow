/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */
package com.briomax.briobpm.transferobjects.catalogs;

import com.briomax.briobpm.transferobjects.core.ITransferObject;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * El objetivo de la Class UsuarioTO.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Jul 27, 2020 2:05:44 PM Modificaciones:
 * @since JDK 1.8
 */
@Schema(description = "UsuarioTO", title = "Usuarios de una Entidad - Localidad.")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResetTO implements ITransferObject {

	/** Serial Version UID. */
	private static final long serialVersionUID = 1764541225391468655L;

	/** El atributo o variable cve usuario. */
	@Schema(name = "cveUsuario", description = "Clave del Usuario.", example = "BrioBpm", implementation = String.class)
	private String cveUsuario;
	
	/** El atributo o variable password. */
	@Schema(name = "password", description = "Contraseña encriptada.",
			example = "3efc3b05dbd0963af796964d99659b35cd3d5dcd4f05fe38107c12dab0e5b889", implementation = String.class)
	private String password;

	/** El atributo o variable cve localidad. */
	@Schema(name = "token", description = "Token creado", example = "3efc3b05dbd0963af796964d99659b35cd",
			implementation = String.class)
	private String token;
	
	/** El atributo o variable correo electronico. */
	@Schema(name = "email", description = "Correo Electronico.", example = "user@company.com",
			implementation = String.class)
	private String email;

}
