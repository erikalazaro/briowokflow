/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */
package com.briomax.briobpm.transferobjects.repse;

import java.util.Date;

import com.briomax.briobpm.transferobjects.core.ITransferObject;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * El objetivo de la Interface CrUsuarioTO.java es ...
 * @author Sara Ventura
 * @version 1.0 Fecha de creacion Nov 25, 2025 12:08:29 Modificaciones:
 * @since JDK 1.8
 */
@Schema(description = "CrUsuarioTO", title = "Usuarios Repse.")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CrUsuarioTO implements ITransferObject {

	/** Serial Version UID. */
	private static final long serialVersionUID = 1764541225391468655L;

	/** El atributo o variable cve entidad. */
	@Schema(name = "cveEntidad", description = "Clave de la Entidad.", example = "BRIOMAX",
			implementation = String.class)
	private String cveEntidad;
	
	/** El atributo o variable cve usuario. */
	@Schema(name = "cveUsuario", description = "Clave del Usuario.", example = "BrioBpm", implementation = String.class)
	private String cveUsuario;
	
	/** El atributo o variable password. */
	@Schema(name = "password", description = "Contraseña encriptada.",
			example = "3efc3b05dbd0963af796964d99659b35cd3d5dcd4f05fe38107c12dab0e5b889", implementation = String.class)
	private String password;

	/** El atributo o variable nombre. */
	@Schema(name = "nombre", description = "Nombre del Usuario", example = "Brio Bpm", implementation = String.class)
	private String nombre;

	/** El atributo o variable correo electronico. */
	@Schema(name = "email", description = "Correo Electronico.", example = "user@company.com",
			implementation = String.class)
	private String email;
	
	/** El atributo o variable rfc. */
	@Schema(name = "rfc", description = "Clave de rfc.", example = "SVM790711PC1",
			implementation = String.class)
	private String rfc;

	/** El atributo o variable cve tipo Acceso . */
	@Schema(name = "usuarioContratante", description = "Usuario Contratante.", example = "sventura|aramirez",
			implementation = String.class)
	private String usuarioContratante;
}
