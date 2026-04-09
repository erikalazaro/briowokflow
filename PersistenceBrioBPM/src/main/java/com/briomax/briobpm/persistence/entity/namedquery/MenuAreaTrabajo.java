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
/*
@NamedStoredProcedureQueries({
	@NamedStoredProcedureQuery(
		name = "leeMenuAreaTrabajo",
		procedureName = "SP_LEE_MENU_AREA_TRABAJO",
		parameters = {
			@StoredProcedureParameter(name="CH_CVE_USUARIO", type=String.class, mode=ParameterMode.IN),
			@StoredProcedureParameter(name="CH_CVE_ENTIDAD", type=String.class, mode=ParameterMode.IN),
			@StoredProcedureParameter(name="CH_CVE_LOCALIDAD", type=String.class, mode=ParameterMode.IN),
			@StoredProcedureParameter(name="CH_CVE_IDIOMA", type=String.class, mode=ParameterMode.IN),
			@StoredProcedureParameter(name="CH_TIPO_EXCEPCION", type=String.class, mode=ParameterMode.OUT),
			@StoredProcedureParameter(name="CH_MENSAJE", type=String.class, mode=ParameterMode.OUT)
		},
		resultClasses= MenuAreaTrabajo.class
	)
})*/
@Entity 
@Getter
@Setter
@ToString
public class MenuAreaTrabajo implements IEntity {

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

	/** El atributo o variable cve nodo. */
	@Column(name = "CVE_NODO")
	private String cveNodo;

	/** El atributo o variable id nodo. */
	@Column(name = "ID_NODO")
	private Integer idNodo;

	/** El atributo o variable des nodo. */
	@Column(name = "DESCRIPCION_NODO")
	private String desNodo;

	/** El atributo o variable cve area trabajo. */
	@Column(name = "CVE_AREA_TRABAJO")
	private String cveAreaTrabajo;

	/** El atributo o variable cve rol. */
	@Column(name = "CVE_ROL")
	private String cveRol;

	/** El atributo o variable ini proceso. */
	@Column(name = "INICIAR_PROCESO")
	private String iniProceso;

	/** El atributo o variable etiqueta boton. */
	@Column(name = "ETIQUETA_BOTON")
	private String etiquetaBoton;

	/** El atributo o variable ejecucion automatica. */
	@Column(name = "EJECUCION_AUTOMATICA")
	private String ejecucionAutomatica;

	/** El atributo o variable Etiqueta boton ejecucion automatica. */
	@Column(name = "ETIQUETA_BOTON_EJECUCION_AUTOMATICA")
	private String EtiquetaBotonEjecucionAutomatica;

}
