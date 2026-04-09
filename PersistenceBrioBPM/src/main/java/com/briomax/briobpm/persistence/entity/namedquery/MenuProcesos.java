/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */
package com.briomax.briobpm.persistence.entity.namedquery;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.briomax.briobpm.persistence.entity.IEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * El objetivo de la Class MenuAreaTrabajo.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion May 18, 2020 8:50:44 AM Modificaciones:
 * @since JDK 1.8
 */
@Entity 
@Getter
@Setter
@ToString
public class MenuProcesos implements IEntity {

	/** Serial Version UID. */
	private static final long serialVersionUID = -1892868004251494539L;

	/** El atributo o variable ordenamiento. */
	@Column(name = "ORDENAMIENTO")
	@Id
	private Integer ordenamiento;
	
	/** El atributo o variable cve proceso. */
	@Column(name = "CVE_PROCESO")
	private String cveProceso;

	/** El atributo o variable version. */
	@Column(name = "VERSION")
	private BigDecimal version;

	/** El atributo o variable des proceso. */
	@Column(name = "DESCRIPCION_PROCESO")
	private String desProceso;

}
