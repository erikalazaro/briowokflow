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

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * El objetivo de la Class LeeValoresColumna.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Jul 1, 2020 7:05:43 PM Modificaciones:
 * @since JDK 1.8
 */

/*@NamedStoredProcedureQueries({
	@NamedStoredProcedureQuery(name = "obtenerValoresColumna",
		procedureName = "SP_LEE_VALORES_COLUMNA",
		parameters = {
			@StoredProcedureParameter(name = "CH_CVE_USUARIO", type = String.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "CH_CVE_ENTIDAD", type = String.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "CH_CVE_LOCALIDAD", type = String.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "CH_CVE_IDIOMA", type = String.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "CH_CVE_PROCESO", type = String.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "C_VERSION", type = BigDecimal.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "CH_NOMBRE_TABLA", type = String.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "CH_NOMBRE_COLUMNA", type = String.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "CH_TIPO_EXCEPCION", type = String.class, mode = ParameterMode.OUT),
			@StoredProcedureParameter(name = "CH_MENSAJE", type = String.class, mode = ParameterMode.OUT)},
		resultClasses = LeeValoresColumna.class
	)
})*/
@Entity
@Getter
@Setter
@ToString
@Builder
public class LeeValoresColumna implements IEntity {

	/** Serial Version UID. */
	private static final long serialVersionUID = 1665226700446505155L;

	@Column(name = "SECUENCIA_VALOR")
	@Id
	private BigDecimal secuenciaValor;

	/** El atributo o variable ordenamiento secuencia. */
	@Column(name = "ORDENAMIENTO_SECUENCIA")
	private String ordenamientoSecuencia;

	/** El atributo o variable valor base datos. */
	@Column(name = "VALOR_BASE_DATOS")
	private String valorBaseDatos;

	/** El atributo o variable Valor pantalla. */
	@Column(name = "VALOR_PANTALLA")
	private String ValorPantalla;

}
