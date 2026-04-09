/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */
package com.briomax.briobpm.persistence.entity.namedquery;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.briomax.briobpm.persistence.entity.IEntity;

import lombok.Builder;
import lombok.Data;
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
public class DatoActividadDate implements IEntity {

	/** Serial Version UID. */
	private static final long serialVersionUID = -1892868004251494539L;


	@EmbeddedId
	private DatoActividadPK id;

	/** El atributo o variable des proceso. */	
	@Column(name = "VALOR_BASE")
	private String valorBase;

	/** El atributo o variable id nodo. */
	@Column(name = "ESTADO_ACTIVIDAD")
	private String estadoActividad;
	
	/** El atributo o variable cve nodo. */
	//@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "VALOR_BASE_DATOS")
	private Date valorBaseDatos;

	/** El atributo o variable id nodo. */
	//@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "VALOR_PANTALLA")
	private Date valorPantalla;


	

}
