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
 * El objetivo de la Class LeeInfDashboardKpi.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Jun 7, 2021 6:54:38 PM Modificaciones:
 * @since JDK 1.8
 */
/*@NamedStoredProcedureQueries({
	@NamedStoredProcedureQuery(name = "leeInfDashboardKpi",
		procedureName = "SP_LEE_INF_DASHBOARD_KPI",
		parameters = {
			@StoredProcedureParameter(name = "CH_CVE_USUARIO", type = String.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "CH_CVE_ENTIDAD", type = String.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "CH_CVE_LOCALIDAD", type = String.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "CH_CVE_IDIOMA", type = String.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "CH_CVE_PROCESO", type = String.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "C_VERSION", type = BigDecimal.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "CH_CVE_NODO", type = String.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "I_ID_NODO", type = Integer.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "CH_FECHA_INICIAL", type = String.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "CH_FECHA_FINAL", type = String.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "CH_TIPO_DASHBOARD", type = String.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "I_NUMERO_SECCION", type = Integer.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "I_NUMERO_SUBSECCION", type = Integer.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "CH_TIPO_EXCEPCION", type = String.class, mode = ParameterMode.OUT),
			@StoredProcedureParameter(name = "CH_MENSAJE", type = String.class, mode = ParameterMode.OUT)},
		resultClasses = LeeInfDashboardKpi.class
	)
})*/
@Entity
@Getter
@Setter
@ToString
public class LeeInfDashboardKpi implements IEntity {

	/** Serial Version UID. */
	private static final long serialVersionUID = 7523607646830021483L;

	/** El atributo o variable consecutivo. */
	@Id
	@Column(name = "CONSECUTIVO")
	private Integer consecutivo;

	/** El atributo o variable informacion. */
	@Column(name = "INFORMACION")
	private String informacion;
}
