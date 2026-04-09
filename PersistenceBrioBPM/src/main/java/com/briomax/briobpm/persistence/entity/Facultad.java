/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.persistence.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
 * BF_FACULTAD database table.
 * 
 * @author Marco A. Morales
 * @version 1.0 Fecha de creacion 16 mar. 2022 Modificaciones:
 * @since JDK 1.8
 */
@Entity
@Table(name="FACULTAD")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"listOfUsuarioFacultad"})
@EqualsAndHashCode(callSuper = false, exclude = {"listOfUsuarioFacultad"})

public class Facultad implements IEntity {

	/** El atributo o variable serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** El atributo o variable id. */
	@Id
	@Column(name = "CVE_FACULTAD", nullable = false, length = 40)
	private String cveFacultad;
	
	/** El atributo o variable descripcion. */
	@Column(name="DESCRIPCION", nullable=false, length=100)
	private String descripcion;
	
	/** El atributo o variable list of usuario. */
	@OneToMany(mappedBy = "facultad", targetEntity = UsuarioFacultad.class)
	private List<UsuarioFacultad> listOfUsuarioFacultad;

}
