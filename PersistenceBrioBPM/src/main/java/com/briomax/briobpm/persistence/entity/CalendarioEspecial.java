/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.persistence.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
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
@Table(name = "CALENDARIO_ESPECIAL")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {})
@EqualsAndHashCode(callSuper = false, exclude = {})
public class CalendarioEspecial implements IEntity {

	/** Serial Version UID. */
	private static final long serialVersionUID = 8952495981117645102L;

	/** El atributo o variable id. */
	@EmbeddedId
	private CalendarioEspecialPK id;

	/** El atributo o variable hora Inicio Dom. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "HORA_INICIO")
	private Date horaInicio;
	
	/** El atributo o variable hora Fin Dom. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "HORA_FIN")
	private Date horaFin;

	/** El atributo o variable hora Inicio Lun. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "HORA_INICIO_1")
	private Date horaInicioUno;
	
	/** El atributo o variable hora Fin Lun. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "HORA_FIN_1")
	private Date horaFinUno;
	
	/** El atributo o variable entidad. */
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name = "CVE_ENTIDAD", referencedColumnName = "CVE_ENTIDAD", insertable = false, updatable = false),
		@JoinColumn(name = "CVE_LOCALIDAD", referencedColumnName = "CVE_LOCALIDAD", insertable = false, updatable = false)
	})
	private CalendarioLocalidad calendarioLocalidad;

}
