/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */
package com.briomax.briobpm.transferobjects;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(title = "DatosAdicionalesUsuarioTO", description = "Datos Adicionales del Usuario.")
public class DatosAdicionalesUsuarioTO implements Serializable {

	/** serial Version UID. */
	private static final long serialVersionUID = 1L;

	/** El atributo o variable clave. */
	@Schema(name = "clave", description = "Clave del Usuario.", example = "BrioBpm", implementation = String.class)
	private String clave;

	/*
	private Set<EmailUsuario> emails;
	private Set<TelefonoUsuario> telefonos;
	private Set<FuncionUsuario> funciones;
	private Set<RolUsuario> roles;
	 */

}
