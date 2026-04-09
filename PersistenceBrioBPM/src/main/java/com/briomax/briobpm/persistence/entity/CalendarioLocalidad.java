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
 * El objetivo de la Class LocalidadEntidad.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Jul 27, 2020 1:29:22 PM Modificaciones:
 * @since JDK 1.8
 */
@Entity
@Table(name = "CALENDARIO_LOCALIDAD")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"listOfCalendarioEspecial", "listOfInhabilCalendario"})
@EqualsAndHashCode(callSuper = false, exclude = {"listOfCalendarioEspecial", "listOfInhabilCalendario"})
public class CalendarioLocalidad implements IEntity {

	/** Serial Version UID. */
	private static final long serialVersionUID = 8952495981117645102L;

	/** El atributo o variable id. */
	@EmbeddedId
	private CalendarioLocalidadPK id;

	/** El atributo o variable Habil Dom. */
	@Column(name = "HABIL_DOM", length = 100)
	private String habilDom;

	/** El atributo o variable hora Inicio Dom. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "HORA_INICIO_DOM")
	private Date horaInicioDom;
	
	/** El atributo o variable hora Fin Dom. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "HORA_FIN_DOM")
	private Date horaFinDom;

	/** El atributo o variable Habil Lun. */
	@Column(name = "HABIL_LUN", length = 100)
	private String habilLun;

	/** El atributo o variable hora Inicio Lun. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "HORA_INICIO_LUN")
	private Date horaInicioLun;
	
	/** El atributo o variable hora Fin Lun. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "HORA_FIN_LUN")
	private Date horaFinLun;

	/** El atributo o variable Habil Mar. */
	@Column(name = "HABIL_MAR", length = 100)
	private String habilMar;

	/** El atributo o variable hora Inicio Mar. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "HORA_INICIO_MAR")
	private Date horaInicioMar;
	
	/** El atributo o variable hora Fin Mar. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "HORA_FIN_MAR")
	private Date horaFinMar;

	/** El atributo o variable Habil Mie. */
	@Column(name = "HABIL_MIE", length = 100)
	private String habilMie;

	/** El atributo o variable hora Inicio Mie. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "HORA_INICIO_MIE")
	private Date horaInicioMie;
	
	/** El atributo o variable hora Fin Mie. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "HORA_FIN_MIE")
	private Date horaFinMie;
	
	/** El atributo o variable Habil Jue. */
	@Column(name = "HABIL_JUE", length = 100)
	private String habilJue;

	/** El atributo o variable hora Inicio Jue. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "HORA_INICIO_JUE")
	private Date horaInicioJue;
	
	/** El atributo o variable hora Fin Jue. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "HORA_FIN_JUE")
	private Date horaFinJue;
	
	/** El atributo o variable Habil Vie. */
	@Column(name = "HABIL_VIE", length = 100)
	private String habilVie;

	/** El atributo o variable hora Inicio Vie. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "HORA_INICIO_VIE")
	private Date horaInicioVie;
	
	/** El atributo o variable hora Fin Vie. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "HORA_FIN_VIE")
	private Date horaFinVie;
	
	/** El atributo o variable Habil Vie. */
	@Column(name = "HABIL_SAB", length = 100)
	private String habilSab;

	/** El atributo o variable hora Inicio Vie. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "HORA_INICIO_SAB")
	private Date horaInicioSab;
	
	/** El atributo o variable hora Fin Vie. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "HORA_FIN_SAB")
	private Date horaFinSab;
	
	/** El atributo o variable entidad. */
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name = "CVE_ENTIDAD", referencedColumnName = "CVE_ENTIDAD", insertable = false, updatable = false),
		@JoinColumn(name = "CVE_LOCALIDAD", referencedColumnName = "CVE_LOCALIDAD", insertable = false, updatable = false)
	})
	private LocalidadEntidad localidadEntidad;

	/** El atributo o variable list of Calendario Especial. */
	@OneToMany(mappedBy = "calendarioLocalidad", targetEntity = CalendarioEspecial.class)
	private List<CalendarioEspecial> listOfCalendarioEspecial;
	
	/** El atributo o variable list of Calendario Especial. */
	@OneToMany(mappedBy = "calendarioLocalidad", targetEntity = InhabilCalendario.class)
	private List<InhabilCalendario> listOfInhabilCalendario;

}
