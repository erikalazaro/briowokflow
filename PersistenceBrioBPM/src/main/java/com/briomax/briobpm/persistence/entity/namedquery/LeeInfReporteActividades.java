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
 * El objetivo de la Class LeeInfReporteProcesos.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Oct 6, 2020 5:09:08 PM Modificaciones:
 * @since JDK 1.8
 */
/*
@NamedStoredProcedureQueries({@NamedStoredProcedureQuery(name = "spLeeInfReporteActividad",
		procedureName = "SP_LEE_INF_REPORTE_ACTIVIDADES",
		parameters = {@StoredProcedureParameter(name = "CH_CVE_USUARIO", type = String.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "CH_CVE_ENTIDAD", type = String.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "CH_CVE_LOCALIDAD", type = String.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "CH_CVE_IDIOMA", type = String.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "CH_TIPO_EXCEPCION", type = String.class, mode = ParameterMode.OUT),
			@StoredProcedureParameter(name = "CH_MENSAJE", type = String.class, mode = ParameterMode.OUT)},
		resultClasses = LeeInfReporteActividades.class)})
		*/
@Entity
@Getter
@Setter
@ToString
public class LeeInfReporteActividades implements IEntity {

	/** Serial Version UID. */
	private static final long serialVersionUID = 6893347206883367448L;

	/** El atributo o variable id. */
	@EmbeddedId
	private LeeInfReporteActividadesPK id;

	/** El atributo o variable rol actividad. */
	@Column(name = "ROL_ACTIVIDAD")
	private String rolActividad;

	/** El atributo o variable usuario actividad. */
	@Column(name = "USUARIO_ACTIVIDAD")
	private String usuarioActividad;

	/** El atributo o variable fecha asignacion. */
	@Column(name = "FECHA_ASIGNACION")
	private String fechaAsignacion;

	/** El atributo o variable hora terminacion. */
	@Column(name = "FECHA_HORA_TERMINACION")
	private String fechaHoraTerminacion;

	/** El atributo o variable duracion. */
	@Column(name = "DURACION")
	private String duracion;

	/** El atributo o variable fecha limite SLA. */
	@Column(name = "FECHA_LIMITE_SLA")
	private String fechaLimiteSLA;

	/** El atributo o variable variacion. */
	@Column(name = "VARIACION")
	private String variacion;

	/** El atributo o variable situacion. */
	@Column(name = "SITUACION")
	private String situacion;

}
