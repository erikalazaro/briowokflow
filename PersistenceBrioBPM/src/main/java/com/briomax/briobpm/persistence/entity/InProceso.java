/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.persistence.entity;

import java.math.BigDecimal;
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
 * El objetivo de la Class StProceso.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Feb 06, 2024 12:24:43 PM Modificaciones:
 * @since JDK 1.8
 */
@Entity
@Table(name = "IN_PROCESO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"listOfInVariableProceso", "listOfInNodoProceso", "listOfInImageneProceso"})
@EqualsAndHashCode(callSuper = false, exclude = {"listOfInVariableProceso", "listOfInNodoProceso", "stRolProceso", "listOfInImageneProceso"})
public class InProceso implements IEntity {

	/** Serial Version UID. */
	private static final long serialVersionUID = 3008509815141573530L;

	/** El atributo o variable id. */
	@EmbeddedId
	private InProcesoPK id;

	/** El atributo o variable descripcion. */
	@Column(name = "CONCEPTO", nullable = false, length = 300)
	private String concepto;

	/** El atributo o variable fec expiracion. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;

	/** El atributo o variable origen. */
	@Column(name = "ORIGEN", nullable = false, length = 60)
	private String origen;

	/** El atributo o variable rol Creador. */
	@Column(name = "ROL_CREADOR", nullable = false, length = 40)
	private String rolCreador;
	
	/** El atributo o variable usuario Creador. */
	@Column(name = "USUARIO_CREADOR", nullable = false, length = 30)
	private String usuarioCreador;
	
	/** El atributo o variable situacion. */
	@Column(name = "SITUACION", nullable = false, length = 12)
	private String situacion;

	/** El atributo o variable entidad Creador. */
	@Column(name = "CVE_ENTIDAD_CREADORA", nullable = false, length = 40)
	private String cveEntidadCreadora;
	
	/** El atributo o variable localidad Creador. */
	@Column(name = "CVE_LOCALIDAD_CREADORA", nullable = false, length = 40)
	private String cveLocalidaCreadora;

	/** El atributo o variable id nodo inicio. */
	@Column(name = "ID_NODO_INICIO", length = 5, precision = 5, scale = 0)
	private Integer idNodoInicio;

	// "cveNodoInicio" (column "CVE_NODO_INICIO") is not defined by itself because used as FK in a link
	/** El atributo o variable id nodo fin. */
	@Column(name = "ID_NODO_FIN", length = 5, precision = 5, scale = 0)
	private Integer idNodoFin;
	
	/** El atributo o variable stVariableProceso. */
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="CVE_ENTIDAD", referencedColumnName = "CVE_ENTIDAD", insertable = false, updatable = false),
		@JoinColumn(name="CVE_PROCESO", referencedColumnName = "CVE_PROCESO", insertable = false, updatable = false),
		@JoinColumn(name="VERSION", referencedColumnName = "VERSION", insertable = false, updatable = false)	})
	private StProceso stProceso;

	// "cveNodoFin" (column "CVE_NODO_FIN") is not defined by itself because used as FK in a link
	/** El atributo o variable cve nodo inicio. */
	@ManyToOne
	@JoinColumn(name = "CVE_NODO_INICIO", referencedColumnName = "CVE_NODO")
	private TipoNodo cveNodoInicio;

	/** El atributo o variable cve nodo fin. */
	@ManyToOne
	@JoinColumn(name = "CVE_NODO_FIN", referencedColumnName = "CVE_NODO")
	private TipoNodo cveNodoFin;

	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="CVE_PROCESO", referencedColumnName = "CVE_PROCESO", insertable = false, updatable = false),
		@JoinColumn(name="CVE_ENTIDAD", referencedColumnName = "CVE_ENTIDAD", insertable = false, updatable = false),
		@JoinColumn(name="VERSION", referencedColumnName = "VERSION", insertable = false, updatable = false),
		@JoinColumn(name="ROL_CREADOR", referencedColumnName = "CVE_ROL", insertable = false, updatable = false) })
	private StRolProceso stRolProceso;

	/** Asociacion bidireccional a InVariableProceso. */
	@OneToMany(mappedBy = "inProceso", targetEntity = InVariableProceso.class)
	private List<InVariableProceso> listOfInVariableProceso;
	
	/** Asociacion bidireccional a InVariableProceso. */
	@OneToMany(mappedBy = "inProceso", targetEntity = InNodoProceso.class)
	private List<InNodoProceso> listOfInNodoProceso;
	
	/** Asociacion bidireccional a InVariableProceso. */
	@OneToMany(mappedBy = "inProceso", targetEntity = InVariableProceso.class)
	private List<InImagenProceso> listOfInImageneProceso;
}
