/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.persistence.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * El objetivo de la Class UsuarioRol.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Jul 27, 2020 6:18:40 PM Modificaciones:
 * @since JDK 1.8
 */
@Entity
@Table(name = "USUARIO_ROL")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRol implements IEntity {

	/** Serial Version UID. */
	private static final long serialVersionUID = -1792310143972546289L;

	/** El atributo o variable id. */
	@EmbeddedId
	private UsuarioRolPK id;

	/** El atributo o variable cve entidad rol. */
	@Column(name = "CVE_ENTIDAD_ROL")
	private String cveEntidadRol;

}
