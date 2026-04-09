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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
 * El objetivo de la Class LocalidadEntidad.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Jul 27, 2020 1:29:22 PM Modificaciones:
 * @since JDK 1.8
 */
@Entity
@Table(name = "LOCALIDAD_ENTIDAD")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"listOfUsuario","listOfStNodoProcesoServicio","listOfStNodoProcesoEspera","listOfCalendarioLocalidad", "listOfInhabilCalendario"})
@EqualsAndHashCode(callSuper = false, exclude = {"listOfUsuario","listOfStNodoProcesoServicio","listOfStNodoProcesoEspera","listOfCalendarioLocalidad", "listOfInhabilCalendario"})
public class LocalidadEntidad implements IEntity {

	/** Serial Version UID. */
	private static final long serialVersionUID = 8952495981117645102L;

	/** El atributo o variable id. */
	@EmbeddedId
	private LocalidadEntidadPK id;

	/** El atributo o variable descripcion. */
	@Column(name = "DESCRIPCION", nullable = false, length = 100)
	private String descripcion;

	/** El atributo o variable tipo. */
	@Column(name = "TIPO", nullable = false, length = 12)
	private String tipo;

	/** El atributo o variable huso horario. */
	@ManyToOne
	@JoinColumn(name = "CVE_HUSO_HORARIO", referencedColumnName = "CVE_HUSO_HORARIO")
	private HusoHorario husoHorario;

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
	@Column(name = "PAIS", nullable = false, length = 40)
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

	/** El atributo o variable moneda. */
	@ManyToOne
	@JoinColumn(name = "CVE_MONEDA", referencedColumnName = "CVE_MONEDA")
	private Moneda moneda;

	/** El atributo o variable idioma. */
	@ManyToOne
	@JoinColumn(name = "CVE_IDIOMA", referencedColumnName = "CVE_IDIOMA")
	private Idioma idioma;

	/** El atributo o variable entidad. */
	@ManyToOne
	@JoinColumn(name = "CVE_ENTIDAD", referencedColumnName = "CVE_ENTIDAD", insertable = false, updatable = false)
	private Entidad entidad;

	/** El atributo o variable list of usuario. */
	@OneToMany(mappedBy = "localidadEntidad", targetEntity = Usuario.class)
	private List<Usuario> listOfUsuario;
	
	/** El atributo o variable list of StNodoProceso. */
	@OneToMany(mappedBy = "localidadEntidadServicio", targetEntity = StNodoProceso.class)
	private List<StNodoProceso> listOfStNodoProcesoServicio;
	
	/** El atributo o variable list of StNodoProceso. */
	@OneToMany(mappedBy = "localidadEntidadEspera", targetEntity = StNodoProceso.class)
	private List<StNodoProceso> listOfStNodoProcesoEspera;

	/** El atributo o variable list of CalendarioLocalidad. */
	@OneToMany(mappedBy = "localidadEntidad", targetEntity = CalendarioLocalidad.class)
	private List<CalendarioLocalidad> listOfCalendarioLocalidad;
	
	/** El atributo o variable list of InhabilCalendario. */
	@OneToMany(mappedBy = "localidadEntidad", targetEntity = CalendarioLocalidad.class)
	private List<InhabilCalendario> listOfInhabilCalendario;
	
	/** El atributo o variable list of InhabilCalendario. */
	@OneToMany(mappedBy = "localidadEntidad", targetEntity = CrProcesoPeriodico.class)
	private List<CrProcesoPeriodico> listOfCrProcesoPeriodico;

}
