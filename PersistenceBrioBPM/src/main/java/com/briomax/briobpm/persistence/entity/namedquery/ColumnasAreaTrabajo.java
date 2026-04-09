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

import lombok.Data;

/**
 * El objetivo de la Class ColumnasAreaTrabajo.java es ...
 * @author Rigoberto Olvera C.
 * @version 1.0 Fecha de creacion Feb 12, 2020 2:21:13 PM Modificaciones:
 * @since JDK 1.8
 */
/*@NamedStoredProcedureQueries({
	@NamedStoredProcedureQuery(
		name = "leeColumnasAreaTrabajo",
		procedureName = "SP_LEE_COLUMNAS_AREA_TRABAJO",
		parameters = {
			@StoredProcedureParameter(name="CH_CVE_USUARIO", type=String.class, mode=ParameterMode.IN),
			@StoredProcedureParameter(name="CH_CVE_ENTIDAD", type=String.class, mode=ParameterMode.IN),
			@StoredProcedureParameter(name="CH_CVE_LOCALIDAD", type=String.class, mode=ParameterMode.IN),
			@StoredProcedureParameter(name="CH_CVE_IDIOMA", type=String.class, mode=ParameterMode.IN),
			@StoredProcedureParameter(name="CH_ROL", type=String.class, mode=ParameterMode.IN),
			@StoredProcedureParameter(name="CH_CVE_PROCESO", type=String.class, mode=ParameterMode.IN),
			@StoredProcedureParameter(name="C_VERSION", type=BigDecimal.class, mode=ParameterMode.IN),
			@StoredProcedureParameter(name="CH_CVE_NODO", type=String.class, mode=ParameterMode.IN),
			@StoredProcedureParameter(name="I_ID_NODO", type=Integer.class, mode=ParameterMode.IN),
			@StoredProcedureParameter(name="CH_CVE_AREA_TRABAJO", type=String.class, mode=ParameterMode.IN),
			@StoredProcedureParameter(name="CH_TIPO_EXCEPCION", type=String.class, mode=ParameterMode.OUT),
			@StoredProcedureParameter(name="CH_MENSAJE", type=String.class, mode=ParameterMode.OUT)
		},
		resultClasses= ColumnasAreaTrabajo.class
	)
})*/
@Entity 
@Data
public class ColumnasAreaTrabajo implements IEntity {

	/** serial Version UID. */
	private static final long serialVersionUID = 7528745768044266468L;

	/** El atributo o variable secuencia. */
	@Column(name = "SECUENCIA")
	@Id
	private Integer secuencia;

	/** El atributo o variable num columnas. */
	@Column(name = "NUM_COLUMNAS")
	private Integer numColumnas;

	/** El atributo o variable cve variable. */
	@Column(name = "CVE_VARIABLE")
	private String cveVariable;

	/** El atributo o variable etiqueta. */
	@Column(name = "ETIQUETA")
	private String etiqueta;

	/** El atributo o variable ancho columna. */
	@Column(name = "ANCHO_COLUMNA")
	private Integer anchoColumna;

	/** El atributo o variable tipo dato. */
	@Column(name = "TIPO_DATO")
	private String tipoDato;

	/** El atributo o variable longitud. */
	@Column(name = "LONGITUD")
	private Integer longitud;

	/** El atributo o variable enteros. */
	@Column(name = "ENTEROS")
	private Integer enteros;

	/** El atributo o variable decimales. */
	@Column(name = "DECIMALES")
	private Integer decimales;

	/** El atributo o variable formato fecha. */
	@Column(name = "FORMATO_FECHA")
	private String formatoFecha;

	/** El atributo o variable color dato. */
	@Column(name = "COLOR_DATO")
	private String colorDato;

	/** El atributo o variable filtro. */
	@Column(name = "FILTRO")
	private String filtro;

	/** El atributo o variable visible. */
	@Column(name = "VISIBLE")
	private String visible;

	/** El atributo o variable tipo control. */
	@Column(name = "TIPO_CONTROL")
	private String tipoControl;

	/** El atributo o variable tipo lista. */
	@Column(name = "TIPO_LISTA")
	private String tipoLista;

	/** El atributo o variable valor lista. */
	@Column(name = "VALOR_LISTA")
	private String valorLista;

	/** El atributo o variable tabla lista. */
	@Column(name = "TABLA_LISTA")
	private String tablaLista;

	/** El atributo o variable where lista. */
	@Column(name = "WHERE_LISTA")
	private String whereLista;

	/** El atributo o variable multiseleccion. */
	@Column(name = "MULTISELECCION")
	private String multiseleccion;

	/** El atributo o variable opcion todos. */
	@Column(name = "OPCION_TODOS")
	private String opcionTodos;

	/** El atributo o variable etiqueta todos. */
	@Column(name = "ETIQUETA_TODOS")
	private String etiquetaTodos;

}

