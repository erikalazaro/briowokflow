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
 * El objetivo de la Class Idioma.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Jun 26, 2020 9:18:05 AM Modificaciones:
 * @since JDK 1.8
 */
@Entity
@Table(name = "IDIOMA")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"listOfEntidad", "listOfLocalidadEntidad", "listOfTraduccion", "listOfMensajeIdioma"})
@EqualsAndHashCode(callSuper = false, exclude = {"listOfEntidad", "listOfLocalidadEntidad", "listOfTraduccion", "listOfMensajeIdioma"})
public class Idioma implements IEntity {

	/** Serial Version UID. */
	private static final long serialVersionUID = -8198761391293242371L;

	/** El atributo o variable cve idioma. */
	@Id
	@Column(name = "CVE_IDIOMA", nullable = false, length = 40)
	private String cveIdioma;

	/** El atributo o variable descripcion. */
	@Column(name = "DESCRIPCION", nullable = false, length = 100)
	private String descripcion;

	/** El atributo o variable list of entidad. */
	@OneToMany(mappedBy = "idioma", targetEntity = Entidad.class)
	private List<Entidad> listOfEntidad;

	/** El atributo o variable list of localidad entidad. */
	@OneToMany(mappedBy = "idioma", targetEntity = LocalidadEntidad.class)
	private List<LocalidadEntidad> listOfLocalidadEntidad;

	/** El atributo o variable list of entidad. */
	@OneToMany(mappedBy = "idioma", targetEntity = Entidad.class)
	private List<Traduccion> listOfTraduccion;
	
	/** El atributo o variable list of entidad. */
	@OneToMany(mappedBy = "idioma", targetEntity = MensajeIdioma.class)
	private List<MensajeIdioma> listOfMensajeIdioma;
}
