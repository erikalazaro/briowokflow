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
 * El objetivo de la Class TipoNodo.java es ...
 * 
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Jun 25, 2020 12:23:51 PM Modificaciones:
 * @since JDK 1.8
 */
@Entity
@Table(name = "TIPO_NODO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = { "listOfStProcesoInicio", "listOfStProcesoFin", "listInProcesoInicio", "listInProcesoFin",
		"listOfStNodoProceso" })
@EqualsAndHashCode(callSuper = false, exclude = { "listOfStProcesoInicio", "listOfStProcesoFin", "listInProcesoInicio",
		"listInProcesoFin", "listOfStNodoProceso" })
public class TipoNodo implements IEntity {

	/** Serial Version UID. */
	private static final long serialVersionUID = 3358809641611954033L;

	/** El atributo o variable cve nodo. */
	@Id
	@Column(name = "CVE_NODO", nullable = false, length = 40)
	private String cveNodo;

	/** El atributo o variable tipo nodo. */
	@Column(name = "TIPO_NODO", nullable = false, length = 40)
	private String tipoNodo;

	/** El atributo o variable descripcion. */
	@Column(name = "DESCRIPCION", nullable = false, length = 100)
	private String descripcion;

	/** El atributo o variable situacion. */
	@Column(name = "SITUACION", nullable = false, length = 12)
	private String situacion;

	/** El atributo o variable list of st proceso inicio. */
	@OneToMany(mappedBy = "cveNodoInicio", targetEntity = StProceso.class)
	private List<StProceso> listOfStProcesoInicio;

	/** El atributo o variable list of st proceso fin. */
	@OneToMany(mappedBy = "cveNodoFin", targetEntity = StProceso.class)
	private List<StProceso> listOfStProcesoFin;

	/** El atributo o variable list of st proceso inicio. */
	@OneToMany(mappedBy = "cveNodoInicio", targetEntity = InProceso.class)
	private List<InProceso> listInProcesoInicio;

	/** El atributo o variable list of st proceso fin. */
	@OneToMany(mappedBy = "cveNodoFin", targetEntity = InProceso.class)
	private List<InProceso> listInProcesoFin;

	/** El atributo o variable list of st proceso fin. */
	@OneToMany(mappedBy = "tipoNodo", targetEntity = StNodoProceso.class)
	private List<StNodoProceso> listOfStNodoProceso;

}
