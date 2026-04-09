/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */
package com.briomax.briobpm.transferobjects.catalogs;

import java.util.Date;

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
public class UsuarioTO implements ITransferObject {

	/** Serial Version UID. */
	private static final long serialVersionUID = 1764541225391468655L;

	/** El atributo o variable cve entidad. */
	@Schema(name = "cveEntidad", description = "Clave de la Entidad.", example = "BRIOMAX",
			implementation = String.class)
	private String cveEntidad;
	
	/** El atributo o variable cve entidad. */
	@Schema(name = "desEntidad", description = "Nombre de la Entidad.", example = "BRIOMAX",
			implementation = String.class)
	private String desEntidad;

	/** El atributo o variable cve usuario. */
	@Schema(name = "cveUsuario", description = "Clave del Usuario.", example = "BrioBpm", implementation = String.class)
	private String cveUsuario;
	
	/** El atributo o variable password. */
	@Schema(name = "password", description = "Contraseña encriptada.",
			example = "3efc3b05dbd0963af796964d99659b35cd3d5dcd4f05fe38107c12dab0e5b889", implementation = String.class)
	private String password;

	/** El atributo o variable fec password. */
	@Schema(name = "fecPassword", description = "Fecha Password.", example = "2020-08-12 00:00:00",
			implementation = Date.class)
	private Date fecPassword;

	/** El atributo o variable nombre. */
	@Schema(name = "nombre", description = "Nombre del Usuario", example = "Brio Bpm", implementation = String.class)
	private String nombre;

	/** El atributo o variable tipo. */
	@Schema(name = "tipo", description = "Tipo de Usuario INTERNO/EXTERNO", example = "INTERNO",
			implementation = String.class)
	private String tipo;

	/** El atributo o variable fotografia. */
	@Schema(name = "fotografia", description = "Archivo de la fotografia del usuario.", example = "user.jpg",
			implementation = String.class)
	private String fotografia;

	/** El atributo o variable cve localidad. */
	@Schema(name = "cveLocalidad", description = "Clave de la Localidad.", example = "BRIOMAX-CENTRAL",
			implementation = String.class)
	private String cveLocalidad;

	/** El atributo o variable des localidad. */
	@Schema(name = "desLocalidad", description = "Descripcion de la Localidad.", example = "BRIOMAX-CENTRAL",
			implementation = String.class)
	private String desLocalidad;

	/** El atributo o variable situacion. */
	@Schema(name = "situacion", description = "Situacion HABILITADO/DESHABILITADO", example = "HABILITADO",
			implementation = String.class)
	private String situacion;

	/** El atributo o variable correo electronico. */
	@Schema(name = "email", description = "Correo Electronico.", example = "user@company.com",
			implementation = String.class)
	private String email;

	/** El atributo o variable correo electronico. */
	@Schema(name = "actualPassword", description = "Actual Password.", example = "texto",
			implementation = String.class)
	private String actualPassword;
	
	/** El atributo o variable cve Facultad. */
	@Schema(name = "cveFacultad", description = "Clave de la Facultad.", example = "GERENTE",
			implementation = String.class)
	private String cveFacultad;
		
}
