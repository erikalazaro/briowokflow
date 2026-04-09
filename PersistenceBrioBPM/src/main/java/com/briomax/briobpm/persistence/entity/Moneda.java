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
 * El objetivo de la Class Moneda.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Jul 27, 2020 1:16:22 PM Modificaciones:
 * @since JDK 1.8
 */
@Entity
@Table(name = "MONEDA")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"listOfEntidad", "listOfLocalidadEntidad", "listOfMonedaProceso"})
@EqualsAndHashCode(callSuper = false, exclude = {"listOfEntidad", "listOfLocalidadEntidad", "listOfMonedaProceso"})
public class Moneda implements IEntity {

	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;

	/** El atributo o variable cve moneda. */
	@Id
	@Column(name = "CVE_MONEDA", nullable = false, length = 3)
	private String cveMoneda;

	/** El atributo o variable descripcion. */
	@Column(name = "DESCRIPCION", nullable = false, length = 100)
	private String descripcion;

	/** El atributo o variable list of entidad. */
	@OneToMany(mappedBy = "moneda", targetEntity = Entidad.class)
	private List<Entidad> listOfEntidad;

	/** El atributo o variable list of localidad entidad. */
	@OneToMany(mappedBy = "moneda", targetEntity = LocalidadEntidad.class)
	private List<LocalidadEntidad> listOfLocalidadEntidad;
	
	/** El atributo o variable list of localidad entidad. */
	@OneToMany(mappedBy = "moneda", targetEntity = MonedaProceso.class)
	private List<MonedaProceso> listOfMonedaProceso;
}
