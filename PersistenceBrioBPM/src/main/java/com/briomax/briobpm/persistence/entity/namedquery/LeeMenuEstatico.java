/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */
package com.briomax.briobpm.persistence.entity.namedquery;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;

import com.briomax.briobpm.persistence.entity.IEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * El objetivo de la Class LeeMenuEstatico.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Oct 6, 2020 3:58:35 PM Modificaciones:
 * @since JDK 1.8
 */
/*
@NamedStoredProcedureQueries({
	@NamedStoredProcedureQuery(
		name = "leeMenuEstatico",
		procedureName = "SP_LEE_MENU_ESTATICO",
		parameters = {
			@StoredProcedureParameter(name="CH_CVE_USUARIO", type=String.class, mode=ParameterMode.IN),
			@StoredProcedureParameter(name="CH_CVE_ENTIDAD", type=String.class, mode=ParameterMode.IN),
			@StoredProcedureParameter(name="CH_CVE_LOCALIDAD", type=String.class, mode=ParameterMode.IN),
			@StoredProcedureParameter(name="CH_CVE_IDIOMA", type=String.class, mode=ParameterMode.IN),
			@StoredProcedureParameter(name="CH_TIPO_EXCEPCION", type=String.class, mode=ParameterMode.OUT),
			@StoredProcedureParameter(name="CH_MENSAJE", type=String.class, mode=ParameterMode.OUT)
		},
		resultClasses= LeeMenuEstatico.class
	),
	@NamedStoredProcedureQuery(
		name = "leeMenuDashboard",
		procedureName = "SP_LEE_MENU_DASHBOARD",
		parameters = {
			@StoredProcedureParameter(name="CH_CVE_USUARIO", type=String.class, mode=ParameterMode.IN),
			@StoredProcedureParameter(name="CH_CVE_ENTIDAD", type=String.class, mode=ParameterMode.IN),
			@StoredProcedureParameter(name="CH_CVE_LOCALIDAD", type=String.class, mode=ParameterMode.IN),
			@StoredProcedureParameter(name="CH_CVE_IDIOMA", type=String.class, mode=ParameterMode.IN),
			@StoredProcedureParameter(name="CH_TIPO_EXCEPCION", type=String.class, mode=ParameterMode.OUT),
			@StoredProcedureParameter(name="CH_MENSAJE", type=String.class, mode=ParameterMode.OUT)
		},
		resultClasses= LeeMenuEstatico.class
	)
})*/
@Entity 
@Getter
@Setter
@ToString
public class LeeMenuEstatico implements IEntity {

	/** serial Version UID. */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private LeeMenuEstaticoPK id;

	/** El atributo o variable descripcion menu. */
	@Column(name="DESCRIPCION_MENU")
	private String descripcionMenu;

	/** El atributo o variable descripcion opcion. */
	@Column(name="DESCRIPCION_OPCION")
	private String descripcionOpcion;

	/** El atributo o variable parametros. */
	@Column(name="PARAMETROS")
	private String parametros;

}

