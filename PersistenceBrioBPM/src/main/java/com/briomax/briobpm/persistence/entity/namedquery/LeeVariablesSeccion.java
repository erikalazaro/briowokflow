
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
 * El objetivo de la Class LeeVariablesSeccion.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion 24/01/2020 07:24:22 PM Modificaciones:
 * @since JDK 1.8
 */
/*
@NamedStoredProcedureQueries({
	@NamedStoredProcedureQuery(name = "obtenerVariablesSeccion",
		procedureName = "SP_LEE_VARIABLES_SECCION",
		parameters = {
			@StoredProcedureParameter(name = "CH_CVE_USUARIO", type = String.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "CH_CVE_ENTIDAD", type = String.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "CH_CVE_LOCALIDAD", type = String.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "CH_CVE_IDIOMA", type = String.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "CH_CVE_PROCESO", type = String.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "C_VERSION", type = BigDecimal.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "CH_CVE_INSTANCIA", type = String.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "CH_CVE_NODO", type = String.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "I_ID_NODO", type = Integer.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "I_SECUENCIA_NODO", type = Integer.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "CH_CVE_SECCION", type = String.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "CH_GENERA_TABLA_TEMPORAL", type = String.class, mode = ParameterMode.IN),
			@StoredProcedureParameter(name = "CH_TIPO_EXCEPCION", type = String.class, mode = ParameterMode.OUT),
			@StoredProcedureParameter(name = "CH_MENSAJE", type = String.class, mode = ParameterMode.OUT)},
		resultClasses = LeeVariablesSeccion.class
	)
})
*/
@Entity
@Getter
@Setter
@ToString
public class LeeVariablesSeccion implements IEntity {

	/**
	 * El atributo serialVersionUID.
	 */
	private static final long serialVersionUID = 1307790844345140303L;

	/** El atributo o variable orden. */
	@Column(name = "ORDEN")
	@Id
	private Integer orden;

	/** El atributo o variable cve variable. */
	@Column(name = "CVE_VARIABLE")
	private String cveVariable;

	/** El atributo o variable etiqueta variable. */
	@Column(name = "ETIQUETA_VARIABLE")
	private String etiquetaVariable;

	/** El atributo o variable tipo. */
	@Column(name = "TIPO")
	private String tipo;

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

	/** El atributo o variable requerido. */
	@Column(name = "REQUERIDO")
	private String requerido;

	/** El atributo o variable valor alfanuemrico. */
	@Column(name = "VALOR_ALFANUMERICO")
	private String valorAlfanuemrico;

	/** El atributo o variable valor entero. */
	@Column(name = "VALOR_ENTERO")
	private Long valorEntero;

	/** El atributo o variable valor decimal. */
	@Column(name = "VALOR_DECIMAL")
	private BigDecimal valorDecimal;

	/** El atributo o variable valor fecha. */
	@Column(name = "VALOR_FECHA")
	private String valorFecha;

	/** El atributo o variable tipo control. */
	@Column(name = "TIPO_CONTROL")
	private String tipoControl;

	/** El atributo o variable tiene etiqueta. */
	@Column(name = "TIENE_ETIQUETA")
	private String tieneEtiqueta;

	/** El atributo o variable solo consulta. */
	@Column(name = "SOLO_CONSULTA")
	private String soloConsulta;

}
