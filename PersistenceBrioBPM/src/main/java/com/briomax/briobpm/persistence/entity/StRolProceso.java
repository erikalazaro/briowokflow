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
import javax.persistence.ManyToMany;
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
 * El objetivo de la Class StRolProceso.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Jun 25, 2020 12:23:06 PM Modificaciones:
 * @since JDK 1.8
 */
@Entity
@Table(name = "ST_ROL_PROCESO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"listOfUsuario"})
@EqualsAndHashCode(callSuper = false, exclude = {"listOfUsuario"})
public class StRolProceso implements IEntity {

	/** Serial Version UID. */
	private static final long serialVersionUID = -3646175169244003993L;

	/** El atributo o variable id. */
	@EmbeddedId
	private StRolProcesoPK id;

	/** El atributo o variable descripcion. */
	@Column(name = "DESCRIPCION", nullable = false, length = 100)
	private String descripcion;

	/** El atributo o variable situacion. */
	@Column(name = "SITUACION", nullable = false, length = 12)
	private String situacion;

	/** El atributo o variable fecha ultimo cambio. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_ULTIMO_CAMBIO")
	private Date fechaUltimoCambio;

	/** El atributo o variable cve usuario ultimo cambio. */
	@Column(name = "CVE_USUARIO_ULTIMO_CAMBIO", length = 30)
	private String cveUsuarioUltimoCambio;

	/** El atributo o variable situacion. */
	@Column(name = "ICONO", nullable = false, length = 250)
	private String icono;

	/** El atributo o variable st proceso. */
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name = "CVE_ENTIDAD", referencedColumnName = "CVE_ENTIDAD", insertable = false, updatable = false),
		@JoinColumn(name = "CVE_PROCESO", referencedColumnName = "CVE_PROCESO", insertable = false, updatable = false),
		@JoinColumn(name = "VERSION", referencedColumnName = "VERSION", insertable = false, updatable = false)})
	private StProceso stProceso;

	/** El atributo o variable list of usuario. */
	@ManyToMany(mappedBy = "listOfStRolProceso", targetEntity = Usuario.class)
	private List<Usuario> listOfUsuario;
}
