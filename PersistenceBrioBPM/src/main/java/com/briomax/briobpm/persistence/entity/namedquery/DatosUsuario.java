/*
 *
 * Copyright (c) 2017 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
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

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * El objetivo de la Class DatosUsuario.java es ...
 * @author Rigoberto Olvera C.
 * @version 1.0 Fecha de creacion 7/09/2017 10:39:55 PM Modificaciones:
 * @since JDK 1.8
 */
/*@NamedStoredProcedureQueries({
	@NamedStoredProcedureQuery(name = "validarUsuario",
		procedureName = "SP_VALIDA_USUARIO",
		parameters = {
			@StoredProcedureParameter(name="CH_CVE_USUARIO", type=String.class, mode=ParameterMode.IN),
			@StoredProcedureParameter(name="CH_CVE_ENTIDAD", type=String.class, mode=ParameterMode.IN),
			@StoredProcedureParameter(name="CH_CVE_LOCALIDAD", type=String.class, mode=ParameterMode.IN),
			@StoredProcedureParameter(name="CH_CVE_IDIOMA", type=String.class, mode=ParameterMode.IN),
			@StoredProcedureParameter(name="CH_PASSWORD", type=String.class, mode=ParameterMode.IN),
			@StoredProcedureParameter(name="CH_TIPO_EXCEPCION", type=String.class, mode=ParameterMode.OUT),
			@StoredProcedureParameter(name="CH_MENSAJE", type=String.class, mode=ParameterMode.OUT)
		},
		resultClasses= DatosUsuario.class
	)
})*/
@Entity  
@Getter
@Setter
@ToString
public class DatosUsuario {

	/** El atributo o variable nombre. */
	@Id
	@Column(name = "NOMBRE")
	private String nombre;
	
	/** El atributo o variable cve entidad. */
	@Column(name = "CVE_ENTIDAD")
	private String cveEntidad;
	
	/** El atributo o variable des entidad. */
	@Column(name = "DES_ENTIDAD")
	private String desEntidad;
	
	/** El atributo o variable cve localidad. */
	@Column(name = "CVE_LOCALIDAD")
	private String cveLocalidad;
	
	/** El atributo o variable des localidad. */
	@Column(name = "DES_LOCALIDAD")
	private String desLocalidad;

	/** El atributo o variable cve moneda. */
	@Column(name = "CVE_MONEDA")
	private String cveMoneda;

	/** El atributo o variable des moneda. */
	@Column(name = "DES_MONEDA")
	private String desMoneda;

	/** El atributo o variable cve idioma. */
	@Column(name = "CVE_IDIOMA")
	private String cveIdioma;

	/** El atributo o variable des idioma. */
	@Column(name = "DES_IDIOMA")
	private String desIdioma;

	/** El atributo o variable cve huso horario. */
	@Column(name = "CVE_HUSO_HORARIO")
	private String cveHusoHorario;

	/** El atributo o variable fotografia. */
	@Column(name = "FOTOGRAFIA")
	private String fotografia;

}
