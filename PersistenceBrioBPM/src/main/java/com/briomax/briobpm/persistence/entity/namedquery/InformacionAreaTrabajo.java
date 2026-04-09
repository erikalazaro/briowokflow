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
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;

import com.briomax.briobpm.persistence.entity.IEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * El objetivo de la Class InformacionAreaTrabajo.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion May 22, 2020 10:35:37 AM Modificaciones:
 * @since JDK 1.8
 */
/*@NamedStoredProcedureQueries({@NamedStoredProcedureQuery(name = "leeInformacionAreaTrabajo",
		procedureName = "SP_LEE_INF_AREA_TRABAJO",
		parameters = {@StoredProcedureParameter(name = "CH_CVE_USUARIO", type = String.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "CH_CVE_ENTIDAD", type = String.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "CH_CVE_LOCALIDAD", type = String.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "CH_CVE_IDIOMA", type = String.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "CH_ROL", type = String.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "CH_CVE_PROCESO", type = String.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "C_VERSION", type = BigDecimal.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "CH_CVE_NODO", type = String.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "I_ID_NODO", type = Integer.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "CH_CVE_AREA_TRABAJO", type = String.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "CH_TIPO_EXCEPCION", type = String.class, mode = ParameterMode.OUT),
			@StoredProcedureParameter(name = "CH_MENSAJE", type = String.class, mode = ParameterMode.OUT)},
		resultClasses = InformacionAreaTrabajo.class)})*/
@Entity
@Getter
@Setter
@ToString
public class InformacionAreaTrabajo implements IEntity {

	/** Serial Version UID. */
	private static final long serialVersionUID = -887436810747974173L;

	/** El atributo o variable id. */
	@Column(name = "CVE_INSTANCIA")
	@EmbeddedId
	private InformacionAreaTrabajoPK id;

	/** El atributo o variable situacion. */
	@Column(name = "SITUACION")
	private String situacion;

	@Column(name = "USUARIO_BLOQUEA")
	private String usuarioBloquea;

	/** El atributo o variable secuencia dato estilo. */
	@Column(name = "SECUENCIA_DATO_ESTILO")
	private String secuenciaDatoEstilo;

	/** El atributo o variable estilo. */
	@Column(name = "ESTILO")
	private String estilo;

	/** El atributo o variable etiqueta tomar. */
	@Column(name = "ETIQUETA_TOMAR")
	private String etiquetaTomar;

	/** El atributo o variable habilitar tomar. */
	@Column(name = "HABILITAR_TOMAR")
	private String habilitarTomar;

	/** El atributo o variable etiqueta liberar. */
	@Column(name = "ETIQUETA_LIBERAR")
	private String etiquetaLiberar;

	/** El atributo o variable habilitar liberar. */
	@Column(name = "HABILITAR_LIBERAR")
	private String habilitarLiberar;

	/** El atributo o variable etiqueta ejecutar. */
	@Column(name = "ETIQUETA_EJECUTAR")
	private String etiquetaEjecutar;

	/** El atributo o variable habilitar ejecutar. */
	@Column(name = "HABILITAR_EJECUTAR")
	private String habilitarEjecutar;

	/** El atributo o variable etiqueta terminar. */
	@Column(name = "ETIQUETA_TERMINAR")
	private String etiquetaTerminar;

	/** El atributo o variable habilitar terminar. */
	@Column(name = "HABILITAR_TERMINAR")
	private String habilitarTerminar;

	/** El atributo o variable etiqueta cancelar. */
	@Column(name = "ETIQUETA_CANCELAR")
	private String etiquetaCancelar;

	/** El atributo o variable habilitar cancelar. */
	@Column(name = "HABILITAR_CANCELAR")
	private String habilitarCancelar;

	/** El atributo o variable etiqueta consultar. */
	@Column(name = "ETIQUETA_CONSULTAR")
	private String etiquetaConsultar;

	/** El atributo o variable habilitar consultar. */
	@Column(name = "HABILITAR_CONSULTAR")
	private String habilitarConsultar;

	/** El atributo o variable etiqueta bitacora. */
	@Column(name = "ETIQUETA_BITACORA")
	private String etiquetaBitacora;

	/** El atributo o variable habilitar bitacora. */
	@Column(name = "HABILITAR_BITACORA")
	private String habilitarBitacora;

	/** El atributo o variable datos actividad. */
	@Column(name = "DATOS_ACTIVIDAD")
	private String datosActividad;

}
