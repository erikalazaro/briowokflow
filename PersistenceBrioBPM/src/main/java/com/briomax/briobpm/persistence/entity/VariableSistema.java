package com.briomax.briobpm.persistence.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
/**
 * El objetivo de la Class VariableSistema.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Dic 06, 2023 12:08:29 PM Modificaciones:
 * @since JDK 1.8
 */
@Entity
@Table(name = "VARIABLE_SISTEMA")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"listOfDatoAtVariableSistema"})
@EqualsAndHashCode(callSuper = false, exclude = {"listOfDatoAtVariableSistema"})
public class VariableSistema implements IEntity{

	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;

	/** El atributo o variable cve Variable. */
	@Id
	@Column(name = "CVE_VARIABLE", nullable = false, length = 80)
	private String cveVariable;
	
	/** El atributo o variable tipo.*/
	@Column(name = "TIPO", nullable = false, length = 12)
	private String tipo;
	
	/** El atributo o variable longitud.*/
	@Column(name = "LONGITUD", nullable = false, precision = 4, scale = 0)
	private Integer longitud;
	
	/** El atributo o variable enteros.*/
	@Column(name = "ENTEROS", nullable = false, precision = 2, scale = 0)
	private Integer enteros;
	
	/** El atributo o variable decimales.*/
	@Column(name = "DECIMALES", nullable = false, precision = 2, scale = 0)
	private Integer decimales;
	
	/** El atributo o variable formato Fecha.*/
	@Column(name = "FORMATO_FECHA", nullable = false, length = 20)
	private String formatoFecha;
	
	/** El atributo o variable etiqueta. */
	@Column(name = "ETIQUETA", nullable = false, length = 80)
	private String etiqueta;

	/** El atributo o variable origenVariable. */
	@Column(name = "ORIGEN_VARIABLE", nullable = false, length = 16)
	private String origenVariable;

	/** El atributo o variable tipoControl. */
	@Column(name = "TIPO_CONTROL", nullable = false, length = 12)
	private String tipoControl;

	/** El atributo o variable tieneEtiqueta. */
	@Column(name = "TIENE_ETIQUETA", nullable = false, length = 2)
	private String tieneEtiqueta;

	/** El atributo o variable valorAlfanumerico. */
	@Column(name = "VALOR_ALFANUMERICO", length = 1500)
	private String valorAlfanumerico;

	/** El atributo o variable valorEntero. */
	@Column(name = "VALOR_ENTERO", precision = 30, scale = 0)
	private Integer valorEntero;

	/** El atributo o variable valorDecimal. */
	@Column(name = "VALOR_DECIMAL", precision = 30, scale = 6)
	private BigDecimal valorDecimal;

	/** El atributo o variable valorFecha. */
	@Column(name = "VALOR_FECHA")
	private Date valorFecha;

	/** El atributo o variable situacion. */
	@Column(name = "SITUACION", nullable = false, length = 12)
	private String situacion;

	/** El atributo o variable tipoLista. */
	@Column(name = "TIPO_LISTA", length = 12)
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

	/** El atributo o variable funcionVariable. */
	@Column(name = "FUNCION_VARIABLE", length = 300)
	private String funcionVariable;

	/** El atributo o variable truncarHora. */
	@Column(name = "TRUNCAR_HORA", length = 4)
	private String truncarHora;

	/** El atributo o variable truncarFecha. */
	@Column(name = "TRUNCAR_FECHA", length = 4)
	private String truncarFecha;

	/** El atributo o variable etiquetaMoneda. */
	@Column(name = "ETIQUETA_MONEDA", length = 80)
	private String etiquetaMoneda;

	/** El atributo o variable list of Folio Nodo. */
	@OneToMany(mappedBy = "variableSistema", targetEntity = DatoAtVariableSistema.class)
	private List<DatoAtVariableSistema> listOfDatoAtVariableSistema;

}
