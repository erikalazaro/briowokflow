/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.persistence.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * El objetivo de la Class Usuario.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Jul 27, 2020 1:20:38 PM Modificaciones:
 * @since JDK 1.8
 */
@Entity
@Table(name = "USUARIO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"listOfStRolProceso", "listOfUsuarioFacultad", "listOfUsuarioDashboardBW"})
@EqualsAndHashCode(callSuper = false, exclude = {"listOfStRolProceso", "listOfUsuarioFacultad", "listOfUsuarioDashboardBW"})
public class Usuario implements IEntity {

	/** Serial Version UID. */
	private static final long serialVersionUID = -4183674351860382471L;

	/** El atributo o variable id. */
	@EmbeddedId
	private UsuarioPK id;

	/** El atributo o variable password. */
	@Column(name = "PASSWORD", nullable = false, length = 100)
	private String password;

	/** El atributo o variable fecha password. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_PASSWORD", nullable = false)
	private Date fechaPassword;

	/** El atributo o variable nombre. */
	@Column(name = "NOMBRE", length = 120)
	private String nombre;

	/** El atributo o variable tipo. */
	@Column(name = "TIPO", nullable = false, length = 10)
	private String tipo;

	/** El atributo o variable fotografia. */
	@Column(name = "FOTOGRAFIA", nullable = false, length = 200)
	private String fotografia;

	/** El atributo o variable localidad entidad. */
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name = "CVE_ENTIDAD_LOCALIDAD", referencedColumnName = "CVE_ENTIDAD", insertable = true, updatable = true),
		@JoinColumn(name = "CVE_LOCALIDAD", referencedColumnName = "CVE_LOCALIDAD", insertable = true, updatable = true)})
	private LocalidadEntidad localidadEntidad;

	/** El atributo o variable situacion. */
	@Column(name = "SITUACION", nullable = false, length = 12)
	private String situacion;

	/** El atributo o variable correo electronico. */
	@Column(name = "CORREO_ELECTRONICO", length = 150)
	private String correoElectronico;

	/** El atributo o variable fecha ultimo cambio. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_ULTIMO_CAMBIO")
	private Date fechaUltimoCambio;

	/** El atributo o variable cve entidad ultimo cambio. */
	@Column(name = "CVE_ENTIDAD_ULTIMO_CAMBIO", length = 40)
	private String cveEntidadUltimoCambio;

	/** El atributo o variable cve usuario ultimo cambio. */
	@Column(name = "CVE_USUARIO_ULTIMO_CAMBIO", length = 30)
	private String cveUsuarioUltimoCambio;

	/** El atributo o variable entidad. */
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name = "CVE_ENTIDAD", referencedColumnName = "CVE_ENTIDAD", insertable = false, updatable = false)
		})
	private Entidad entidad;

	/** El atributo o variable list of st rol proceso. */
	@ManyToMany(targetEntity = StRolProceso.class)
	@JoinTable(name = "USUARIO_ROL",
			joinColumns = {
				@JoinColumn(name = "CVE_ENTIDAD", referencedColumnName = "CVE_ENTIDAD"),
				@JoinColumn(name = "CVE_USUARIO", referencedColumnName = "CVE_USUARIO")},
			inverseJoinColumns = {
				@JoinColumn(name = "CVE_ENTIDAD_ROL", referencedColumnName = "CVE_ENTIDAD"),
				@JoinColumn(name = "CVE_PROCESO", referencedColumnName = "CVE_PROCESO"),
				@JoinColumn(name = "VERSION", referencedColumnName = "VERSION"),
				@JoinColumn(name = "CVE_ROL", referencedColumnName = "CVE_ROL")})
	private List<StRolProceso> listOfStRolProceso;
	
	/** El atributo o variable list of usuario. */
	@OneToMany(mappedBy = "usuario", targetEntity = UsuarioFacultad.class)
	private List<UsuarioFacultad> listOfUsuarioFacultad;
	
	/** El atributo o variable list of usuario. */
	@OneToMany(mappedBy = "usuario", targetEntity = UsuarioDashboardBW.class)
	private List<UsuarioDashboardBW> listOfUsuarioDashboardBW;
	
}
