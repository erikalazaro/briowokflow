package com.briomax.briobpm.persistence.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
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
 * @version 1.0 Fecha de creacion Dic 11, 2023 12:24:43 PM Modificaciones:
 * @since JDK 1.8
 */
@Entity
@Table(name = "VARIABLE_LOCALIDAD")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {})
@EqualsAndHashCode(callSuper = false, exclude = {})
public class VariableLocalidad implements IEntity {

	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;
	
	/** El atributo o variable id. */
	@EmbeddedId
	private VariableLocalidadPK id;
	
	@Column(name = "TIPO", nullable = false, length = 12)
	private String tipo;

	@Column(name = "LONGITUD", nullable = false, precision = 4, scale = 0)
	private Integer longitud;

	@Column(name = "ENTEROS", precision = 2, scale = 0)
	private Integer enteros;

	@Column(name = "DECIMALES", precision = 2, scale = 0)
	private Integer decimales;

	@Column(name = "FORMATO_FECHA", length = 20)
	private String formatoFecha;

	@Column(name = "ETIQUETA", nullable = false, length = 80)
	private String etiqueta;

	@Column(name = "ORIGEN_VARIABLE", nullable = false, length = 16)
	private String origenVariable;

	@Column(name = "TIPO_CONTROL", nullable = false, length = 12)
	private String tipoControl;

	@Column(name = "TIENE_ETIQUETA", nullable = false, length = 2)
	private String tieneEtiqueta;

	@Column(name = "VALOR_ALFANUMERICO", length = 1500)
	private String valorAlfanumerico;

	@Column(name = "VALOR_ENTERO", precision = 30, scale = 0)
	private Integer valorEntero;

	@Column(name = "VALOR_DECIMAL", precision = 30, scale = 6)
	private BigDecimal valorDecimal;

	@Column(name = "VALOR_FECHA")
	private Date valorFecha;

	@Column(name = "SITUACION", nullable = false, length = 12)
	private String situacion;

	@Column(name = "TIPO_LISTA", length = 12)
	private String tipoLista;

	@Column(name = "VALOR_LISTA", length = 40)
	private String valorLista;

	@Column(name = "DESCRIPCION_LISTA", length = 40)
	private String descripcionLista;

	@Column(name = "TABLA_LISTA", length = 200)
	private String tablaLista;

	@Column(name = "WHERE_LISTA", length = 200)
	private String whereLista;

	@Column(name = "ETIQUETA_MONEDA", length = 80)
	private String etiquetaMoneda;

	/** El atributo o variable entidad. */
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name = "CVE_ENTIDAD", referencedColumnName = "CVE_ENTIDAD", insertable = false, updatable = false),
		@JoinColumn(name = "CVE_LOCALIDAD", referencedColumnName = "CVE_LOCALIDAD", insertable = false, updatable = false)
		})
	private LocalidadEntidad localidadEntidad;
}
