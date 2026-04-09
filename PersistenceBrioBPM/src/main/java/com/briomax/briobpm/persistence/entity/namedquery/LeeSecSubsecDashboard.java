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

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * El objetivo de la Class LeeSecSubsecDashboard.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Jun 7, 2021 12:58:46 PM Modificaciones:
 * @since JDK 1.8
 */
/*
@NamedStoredProcedureQueries({
	@NamedStoredProcedureQuery(name = "leeSecSubseccionDashboard",
		procedureName = "SP_LEE_SEC_SUBSEC_DASHBOARD",
		parameters = {
			@StoredProcedureParameter(name = "CH_CVE_USUARIO", type = String.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "CH_CVE_ENTIDAD", type = String.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "CH_CVE_LOCALIDAD", type = String.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "CH_CVE_IDIOMA", type = String.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "CH_TIPO_DASHBOARD", type = String.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "I_NUMERO_SECCION", type = Integer.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "I_NUMERO_SUBSECCION", type = Integer.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "CH_TIPO_EXCEPCION", type = String.class, mode = ParameterMode.OUT),
			@StoredProcedureParameter(name = "CH_MENSAJE", type = String.class, mode = ParameterMode.OUT)},
		resultClasses = LeeSecSubsecDashboard.class
	)
})*/
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LeeSecSubsecDashboard implements IEntity {

	/** Serial Version UID. */
	private static final long serialVersionUID = -5431683109907427689L;

	/** El atributo o variable consecutivo. */
	@Id
	@Column(name = "CONSECUTIVO")
	private Integer consecutivo;

	/** El atributo o variable titulo dashboard. */
	@Column(name = "TITULO_DASHBOARD")
	private String tituloDashboard;

	/** El atributo o variable titulo seccion. */
	@Column(name = "TITULO_SECCION")
	private String tituloSeccion;

	/** El atributo o variable tipo representacion. */
	@Column(name = "TIPO_REPRESENTACION")
	private String tipoRepresentacion;

	/** El atributo o variable escala. */
	@Column(name = "ESCALA")
	private String escala;

	/** El atributo o variable nivel informacion. */
	@Column(name = "NIVEL_INFORMACION")
	private String nivelInformacion;

	/** El atributo o variable orden etiqueta. */
	@Column(name = "ORDEN_ETIQUETA")
	private Integer ordenEtiqueta;

	/** El atributo o variable etiqueta subseccion. */
	@Column(name = "ETIQUETA_SUBSECCION")
	private String etiquetaSubseccion;

	/** El atributo o variable tipo dato etiqueta. */
	@Column(name = "TIPO_DATO_ETIQUETA")
	private String tipoDatoEtiqueta;

}
