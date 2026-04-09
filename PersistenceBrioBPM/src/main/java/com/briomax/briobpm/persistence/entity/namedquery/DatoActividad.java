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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;

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
public class DatoActividad implements IEntity {

	/** Serial Version UID. */
	private static final long serialVersionUID = -1892868004251494539L;

	@EmbeddedId
	private DatoActividadPK id;

	/** El atributo o variable des proceso. */
	@Column(name = "VALOR_BASE")
	private String valorBase;

	/** El atributo o variable cve nodo. */
	@Column(name = "VALOR_BASE_DATOS")
	private String valorBaseDatos;

	/** El atributo o variable id nodo. */
	@Column(name = "VALOR_PANTALLA")
	private String valorPantalla;

	/** El atributo o variable id nodo. */
	@Column(name = "ESTADO_ACTIVIDAD")
	private String estadoActividad;
//	
//	@Column(name = "TIPO_TARJETA")
//	private String tipoTarjeta;
}
