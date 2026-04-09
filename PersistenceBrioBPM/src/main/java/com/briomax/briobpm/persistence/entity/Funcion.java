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
 * @author Sara Ventura
 * @version 1.0 Fecha de creacion 16 mar. 2022 Modificaciones:
 * @since JDK 1.8
 */
@Entity
@Table(name="FUNCION")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Funcion implements IEntity {

	/** El atributo o variable serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** El atributo o variable id. */
	@Id
	@Column(name = "CVE_FUNCION", nullable = false, length = 40)
	private String cveFacultad;
	
	/** El atributo o variable descripcion. */
	@Column(name="DESCRIPCION", nullable=false, length=100)
	private String descripcion;
	
	/** El atributo o variable descripcion Corta. */
	@Column(name="DESCRIPCION_CORTA", nullable=false, length=20)
	private String descripcionCorta;
	
	/** El atributo o variable tipo Funcion. */
	@Column(name="TIPO_FUNCION", nullable=false, length=20)
	private String tipoFuncion;
	
	/** El atributo o variable descripcion Tipo Funcion. */
	@Column(name="DES_TIPO_FUNCION", nullable=false, length=100)
	private String desTipoFuncion;
	
	/** El atributo o variable Funcion. */
	@Column(name="FUNCION", nullable=false, length=100)
	private String funcion;
	
	/** El atributo o variable situacion. */
	@Column(name="SITUACION", nullable=false, length=12)
	private String situacion;
}
