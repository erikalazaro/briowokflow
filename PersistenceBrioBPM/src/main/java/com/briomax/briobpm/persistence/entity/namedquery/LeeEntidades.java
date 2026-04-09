/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.persistence.entity.namedquery;

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
 * El objetivo de la Class LeeIdiomas.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Jul 14, 2021 1:14:26 PM Modificaciones:
 * @since JDK 1.8
 */
/*@NamedStoredProcedureQueries({
	@NamedStoredProcedureQuery(name = "obtenerEntidades",
		procedureName = "SP_LEE_ENTIDADES",
		parameters = {
			@StoredProcedureParameter(name = "CH_CVE_USUARIO", type = String.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "CH_CVE_ENTIDAD", type = String.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "CH_CVE_LOCALIDAD", type = String.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "CH_CVE_IDIOMA", type = String.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "CH_TIPO_EXCEPCION", type = String.class, mode = ParameterMode.OUT),
			@StoredProcedureParameter(name = "CH_MENSAJE", type = String.class, mode = ParameterMode.OUT)},
		resultClasses = LeeEntidades.class
	)
})*/
@Entity
@Getter
@Setter
@ToString
public class LeeEntidades implements IEntity {

	/** Serial Version UID. */
	private static final long serialVersionUID = 7769799199895423193L;

	/** El atributo o variable cve entidad. */
	@Column(name = "CVE_ENTIDAD")
	@Id
	private String cveEntidad;

	/** El atributo o variable descripcion. */
	@Column(name = "DESCRIPCION")
	private String descripcion;

}
