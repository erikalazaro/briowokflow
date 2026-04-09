/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */
package com.briomax.briobpm.persistence.entity.namedquery;

import java.math.BigDecimal;
import java.util.Date;

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
public class ActividadProceso implements IEntity {

	/** Serial Version UID. */
	private static final long serialVersionUID = -1892868004251494539L;

	/** El atributo o variable ordenamiento. */
	@Column(name = "ORDENAMIENTO")
	@Id
	private Integer ordenamiento;

	/** El atributo o variable cve entidad. */
	@Column(name = "CVE_ENTIDAD")
	private String cveEntidad;
	
	/** El atributo o variable cve proceso. */
	@Column(name = "CVE_PROCESO")
	private String cveProceso;

	/** El atributo o variable version. */
	@Column(name = "VERSION")
	private BigDecimal version;
	
	/** El atributo o variable des proceso. */
	@Column(name = "DESPROCESO")
	private String desProceso;

	/** El atributo o variable cve instancia. */
	@Column(name = "CVE_INSTANCIA")
	private String cveInstancia;

	/** El atributo o variable cve Nodo. */
	@Column(name = "CVE_NODO")
	private String cveNodo;

	/** El atributo o variable id Nodo. */
	@Column(name = "ID_NODO")
	private Integer idNodo;

	/** El atributo o variable sec Nodo. */
	@Column(name = "SECUENCIA_NODO")
	private Integer secNodo;

	/** El atributo o variable descripcion. */
	@Column(name = "DESCRIPCION")
	private String descripcion;

	/** El atributo o variable fecha Limite. */
	@Column(name = "FECHA_LIMITE")
	private Date fechaLimite;
	
	/** El atributo o variable fecha Limite. */
	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;
	
	/** El atributo o variable fecha Limite. */
	@Column(name = "FECHA_ESTADO_ACTUAL")
	private Date fechaestadoActual;
}
