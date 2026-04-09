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
@NamedStoredProcedureQueries({@NamedStoredProcedureQuery(name = "spLeeInfReporteProcesos",
		procedureName = "SP_LEE_INF_REPORTE_PROCESOS",
		parameters = {@StoredProcedureParameter(name = "CH_CVE_USUARIO", type = String.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "CH_CVE_ENTIDAD", type = String.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "CH_CVE_LOCALIDAD", type = String.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "CH_CVE_IDIOMA", type = String.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "CH_TIPO_EXCEPCION", type = String.class, mode = ParameterMode.OUT),
			@StoredProcedureParameter(name = "CH_MENSAJE", type = String.class, mode = ParameterMode.OUT)},
		resultClasses = LeeInfReporteProcesos.class)})
		*/
@Entity
@Getter
@Setter
@ToString
public class LeeInfReporteProcesos implements IEntity {

	/** Serial Version UID. */
	private static final long serialVersionUID = -478280915186075009L;

	/** El atributo o variable id. */
	@EmbeddedId
	private LeeInfReporteProcesosPK id;

	/** El atributo o variable fecha hora inicio. */
	@Column(name = "FECHA_HORA_INICIO")
	private String fechaHoraInicio;

	/** El atributo o variable fecha hora termino. */
	@Column(name = "FECHA_HORA_TERMINO")
	private String fechaHoraTermino;

	/** El atributo o variable situacion. */
	@Column(name = "SITUACION")
	private String situacion;

}
