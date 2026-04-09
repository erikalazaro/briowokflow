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
import javax.persistence.JoinColumn;
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
 * El objetivo de la Class Entidad.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Jul 27, 2020 1:15:29 PM Modificaciones:
 * @since JDK 1.8
 */
@Entity
@Table(name = "ENTIDAD")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"listOfStProcesos", "listOfUsuario", "listOfVariableEntidad", "listOfDashboardBW"})
@EqualsAndHashCode(callSuper = false, exclude = {"listOfStProcesos", "listOfUsuario", "listOfVariableEntidad", "listOfDashboardBW"})
public class Entidad implements IEntity {

	/** Serial Version UID. */
	private static final long serialVersionUID = -7293193030911037952L;

	/** El atributo o variable cve entidad. */
	@Id
	@Column(name = "CVE_ENTIDAD", nullable = false, length = 40)
	private String cveEntidad;

	/** El atributo o variable descripcion. */
	@Column(name = "DESCRIPCION", nullable = false, length = 100)
	private String descripcion;

	/** El atributo o variable calle. */
	@Column(name = "CALLE", length = 200)
	private String calle;

	/** El atributo o variable numero exterior. */
	@Column(name = "NUMERO_EXTERIOR", length = 40)
	private String numeroExterior;

	/** El atributo o variable numero interior. */
	@Column(name = "NUMERO_INTERIOR", length = 40)
	private String numeroInterior;

	/** El atributo o variable colonia. */
	@Column(name = "COLONIA", length = 100)
	private String colonia;

	/** El atributo o variable delegacion municipio. */
	@Column(name = "DELEGACION_MUNICIPIO", length = 100)
	private String delegacionMunicipio;

	/** El atributo o variable codigo postal. */
	@Column(name = "CODIGO_POSTAL", length = 10)
	private String codigoPostal;

	/** El atributo o variable pais. */
	@Column(name = "PAIS", length = 40)
	private String pais;

	/** El atributo o variable telefono. */
	@Column(name = "TELEFONO", length = 40)
	private String telefono;

	/** El atributo o variable extension. */
	@Column(name = "EXTENSION", length = 20)
	private String extension;

	/** El atributo o variable correo electronico. */
	@Column(name = "CORREO_ELECTRONICO", length = 100)
	private String correoElectronico;

	/** El atributo o variable idioma. */
	@ManyToOne
	@JoinColumn(name = "CVE_IDIOMA", referencedColumnName = "CVE_IDIOMA")
	private Idioma idioma;

	/** El atributo o variable moneda. */
	@ManyToOne
	@JoinColumn(name = "CVE_MONEDA", referencedColumnName = "CVE_MONEDA")
	private Moneda moneda;

	/** El atributo o variable situacion. */
	@Column(name = "SITUACION", nullable = false, length = 12)
	private String situacion;

	/** El atributo o variable logotipo. */
	@Column(name = "LOGOTIPO", length = 100)
	private String logotipo;

	/** El atributo o variable list of st procesos. */
	@OneToMany(mappedBy="entidad", targetEntity = StProceso.class)
	private List<StProceso> listOfStProcesos;

	/** El atributo o variable list of usuario. */
	@OneToMany(mappedBy = "entidad", targetEntity = Usuario.class)
	private List<Usuario> listOfUsuario;
	
	/** El atributo o variable list of usuario. */
	@OneToMany(mappedBy = "entidad", targetEntity = VariableEntidad.class)
	private List<VariableEntidad> listOfVariableEntidad;
	
	/** El atributo o variable list of DashboardBW. */
	@OneToMany(mappedBy = "entidad", targetEntity = DashboardBW.class)
	private List<DashboardBW> listOfDashboardBW;
}
