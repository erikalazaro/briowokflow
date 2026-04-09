/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.persistence.entity.namedquery;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * El objetivo de la Class UsuarioRolProceso.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Jun 14, 2021 6:35:03 PM Modificaciones:
 * @since JDK 1.8
 */
@Entity
@Table(name = "USUARIO_ROL_PROCESO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRolProceso implements Serializable {

	/** Serial Version UID. */
	private static final long serialVersionUID = -2590926156549115828L;

	/** El atributo o variable cve usuario. */
	@Id
	@Column(name = "CVE_USUARIO", nullable = false, length = 30)
	private String cveUsuario;

	/** El atributo o variable seleccionado. */
	@Column(name = "SELECCIONADO", nullable = false)
	private Integer seleccionado;

	/** El atributo o variable nom usuario. */
	@Column(name = "NOMBRE")
	private String nomUsuario;

	/** El atributo o variable des localidad. */
	@Column(name = "DESCRIPCION")
	private String desLocalidad;

}
