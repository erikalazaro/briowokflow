/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.persistence.entity;

import java.util.List;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * El objetivo de la clase Facultad.java es ... The persistent class for the
 * USUARIO_FACULTAD database table.
 * 
 * @author Sara Ventura
 * @version 1.0 Fecha de creacion 16 mar. 2022 Modificaciones:
 * @since JDK 1.8
 */
@Entity
@Table(name = "USUARIO_FACULTAD")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {})
@EqualsAndHashCode(callSuper = false, exclude = {})
public class UsuarioFacultad implements IEntity {

	/** El atributo o variable serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** El atributo o variable id. */
	@EmbeddedId
	private UsuarioFacultadPK id;
	
	/** El atributo o variable usuario facultad. */
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name = "CVE_ENTIDAD", referencedColumnName = "CVE_ENTIDAD", insertable = false, updatable = false),
		@JoinColumn(name = "CVE_USUARIO", referencedColumnName = "CVE_USUARIO", insertable = false, updatable = false)})
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name = "CVE_FACULTAD", referencedColumnName = "CVE_FACULTAD", insertable = false, updatable = false)
		})
	private Facultad facultad;

}
