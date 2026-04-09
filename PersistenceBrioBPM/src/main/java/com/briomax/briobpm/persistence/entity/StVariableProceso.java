package com.briomax.briobpm.persistence.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * El objetivo de la Class StProceso.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Nov 21, 2023 12:24:43 PM Modificaciones:
 * @since JDK 1.8
 */
@Entity
@Table(name = "ST_VARIABLE_PROCESO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"listOfInVariableProceso", "listOfStVariableSeccion", "listOfStValorInicialVariable", "listOfStValorVariable", "listOfInImagenProceso"})
@EqualsAndHashCode(callSuper = false, exclude = {"listOfInVariableProceso", "listOfStVariableSeccion", "listOfStValorInicialVariable", "listOfStValorVariable", "listOfInImagenProceso"})
public class StVariableProceso  implements IEntity {
	
	/** Serial Version UID. */
	private static final long serialVersionUID = 3008509815141573530L;
	
	/** El atributo o variable id. */
	@EmbeddedId
	private StVariableProcesoPK id;
	
	/** El atributo o variable tipo. */
	@Column(name = "TIPO", nullable = false, length = 12)
	private String tipo;

	/** El atributo o variable longitud. */
	@Column(name = "LONGITUD", nullable = false, precision = 4, scale = 0)
	private Integer longitud;

	/** El atributo o variable enteros. */
	@Column(name = "ENTEROS", precision = 2, scale = 0)
	private Integer enteros;

	/** El atributo o variable decimales. */
	@Column(name = "DECIMALES", precision = 2, scale = 0)
	private Integer decimales;

	/** El atributo o variable formatoFecha. */
	@Column(name = "FORMATO_FECHA", length = 20)
	private String formatoFecha;

	/** El atributo o variable etiqueta. */
	@Column(name = "ETIQUETA", nullable = false, length = 80)
	private String etiqueta;

	/** El atributo o variable tipoInteraccion. */
	@Column(name = "TIPO_INTERACCION", nullable = false, length = 10)
	private String tipoInteraccion;

	/** El atributo o variable tipoControl. */
	@Column(name = "TIPO_CONTROL", nullable = false, length = 12)
	private String tipoControl;

	/** El atributo o variable tieneEtiqueta. */
	@Column(name = "TIENE_ETIQUETA", length = 2)
	private String tieneEtiqueta;

	/** El atributo o variable funcion. */
	@Column(name = "FUNCION", length = 30)
	private String funcion;

	/** El atributo o variable situacion. */
	@Column(name = "SITUACION", nullable = false, length = 12)
	private String situacion;

	/** El atributo o variable tipoLista. */
	@Column(name = "TIPO_LISTA", length = 20)
	private String tipoLista;

	/** El atributo o variable valorLista. */
	@Column(name = "VALOR_LISTA", length = 40)
	private String valorLista;

	/** El atributo o variable descripcionLista. */
	@Column(name = "DESCRIPCION_LISTA", length = 40)
	private String descripcionLista;

	/** El atributo o variable tablaLista. */
	@Column(name = "TABLA_LISTA", length = 200)
	private String tablaLista;

	/** El atributo o variable whereLista. */
	@Column(name = "WHERE_LISTA", length = 200)
	private String whereLista;

	/** El atributo o variable formula. */
	@Column(name = "FORMULA", length = 2000)
	private String formula;

	/** El atributo o variable origenMoneda. */
	@Column(name = "ORIGEN_MONEDA", length = 20)
	private String origenMoneda;

	/** El atributo o variable etiquetaMoneda. */
	@Column(name = "ETIQUETA_MONEDA", length = 80)
	private String etiquetaMoneda;

	/** El atributo o variable cveMoneda. */
	@Column(name = "CVE_MONEDA", length = 3)
	private String cveMoneda;

	/** El atributo o variable cveMonedaVisible. */
	@Column(name = "CVE_MONEDA_VISIBLE", length = 2)
	private String cveMonedaVisible;
	
	/** El atributo o variable monedaProceso. */
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="CVE_ENTIDAD", referencedColumnName = "CVE_ENTIDAD", insertable = false, updatable = false),
		@JoinColumn(name="CVE_PROCESO", referencedColumnName = "CVE_PROCESO", insertable = false, updatable = false),
		@JoinColumn(name="VERSION", referencedColumnName = "VERSION", insertable = false, updatable = false),
		@JoinColumn(name="CVE_MONEDA", referencedColumnName = "CVE_MONEDA", insertable = false, updatable = false)
		})
	private MonedaProceso monedaProceso;

	/** El atributo o variable stProceso. */
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="CVE_ENTIDAD", referencedColumnName = "CVE_ENTIDAD", insertable = false, updatable = false),
		@JoinColumn(name="CVE_PROCESO", referencedColumnName = "CVE_PROCESO", insertable = false, updatable = false),
		@JoinColumn(name="VERSION", referencedColumnName = "VERSION", insertable = false, updatable = false)
		})
	private StProceso stProceso;
	
	/** Asociacion bidireccional a StNodoProceso. */
	@OneToMany(mappedBy = "stVariableProceso", targetEntity = InVariableProceso.class)
	private List<InVariableProceso> listOfInVariableProceso;
	
	/** Asociacion bidireccional a StNodoProceso. */
	@OneToMany(mappedBy = "stVariableProceso", targetEntity = StVariableSeccion.class)
	private List<StVariableSeccion> listOfStVariableSeccion;
	
	/** Asociacion bidireccional a StNodoProceso. */
	@OneToMany(mappedBy = "stVariableProceso", targetEntity = StValorInicialVariable.class)
	private List<StValorInicialVariable> listOfStValorInicialVariable;
	
	/** Asociacion bidireccional a StNodoProceso. */
	@OneToMany(mappedBy = "stVariableProceso", targetEntity = StValorVariable.class)
	private List<StValorVariable> listOfStValorVariable;
	
	/** Asociacion bidireccional a StNodoProceso. */
	@OneToMany(mappedBy = "stVariableProceso", targetEntity = InVariableProceso.class)
	private List<InImagenProceso> listOfInImagenProceso;
}
