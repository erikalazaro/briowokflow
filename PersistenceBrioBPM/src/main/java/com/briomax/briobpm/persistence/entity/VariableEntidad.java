package com.briomax.briobpm.persistence.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * El objetivo de la Class UsuarioRol.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Dic 08, 2023 6:18:40 PM Modificaciones:
 * @since JDK 1.8
 */
@Entity
@Table(name = "VARIABLE_ENTIDAD")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false, exclude = {})
public class VariableEntidad  implements IEntity {

	/** Serial Version UID. */
	private static final long serialVersionUID = 1;
	
	/** El atributo o variable id. */
	@EmbeddedId
	private VariableEntidadPK id;

    /** El atributo o variable tipo. */
    @Column(name = "TIPO", nullable = false, length = 12)
    private String tipo;

    /** El atributo o variable longitud. */
    @Column(name = "LONGITUD", nullable = false, precision = 4, scale = 0)
    private Integer longitud;

    /** El atributo o variable enteros. */
    @Column(name = "ENTEROS",  precision = 2, scale = 0)
    private Integer enteros;

    /** El atributo o variable decimales. */
    @Column(name = "DECIMALES",  precision = 2, scale = 0)
    private Integer decimales;

    /** El atributo o variable formato fecha. */
    @Column(name = "FORMATO_FECHA",  length = 20)
    private String formatoFecha;

    /** El atributo o variable etiqueta. */
    @Column(name = "ETIQUETA", nullable = false, length = 80)
    private String etiqueta;

    /** El atributo o variable origen variable. */
    @Column(name = "ORIGEN_VARIABLE", nullable = false, length = 16)
    private String origenVariable;

    /** El atributo o variable valor alfanumérico. */
    @Column(name = "VALOR_ALFANUMERICO",  length = 1500)
    private String valorAlfanumerico;

    /** El atributo o variable valor entero. */
    @Column(name = "VALOR_ENTERO",  precision = 30, scale = 0)
    private Integer valorEntero;

    /** El atributo o variable valor decimal. */
    @Column(name = "VALOR_DECIMAL",  precision = 30, scale = 6)
    private BigDecimal valorDecimal;

    /** El atributo o variable valor fecha. */
    @Column(name = "VALOR_FECHA", nullable = true)
    private Date valorFecha;

    /** El atributo o variable fórmula. */
    @Column(name = "FORMULA",  length = 500)
    private String formula;

    /** El atributo o variable función. */
    @Column(name = "FUNCION",  length = 30)
    private String funcion;

    /** El atributo o variable situación. */
    @Column(name = "SITUACION", nullable = false, length = 12)
    private String situacion;

    /** El atributo o variable tipo control. */
    @Column(name = "TIPO_CONTROL",  length = 12)
    private String tipoControl;

    /** El atributo o variable tipo lista. */
    @Column(name = "TIPO_LISTA",  length = 12)
    private String tipoLista;

    /** El atributo o variable valor lista. */
    @Column(name = "VALOR_LISTA",  length = 40)
    private String valorLista;

    /** El atributo o variable descripción lista. */
    @Column(name = "DESCRIPCION_LISTA",  length = 40)
    private String descripcionLista;

    /** El atributo o variable tabla lista. */
    @Column(name = "TABLA_LISTA",  length = 200)
    private String tablaLista;

    /** El atributo o variable where lista. */
    @Column(name = "WHERE_LISTA",  length = 200)
    private String whereLista;

    /** El atributo o variable etiqueta moneda. */
    @Column(name = "ETIQUETA_MONEDA",  length = 80)
    private String etiquetaMoneda;
    
	/** El atributo o variable entidad. */
	@ManyToOne
	@JoinColumn(name = "CVE_ENTIDAD", referencedColumnName = "CVE_ENTIDAD", insertable = false, updatable = false)
	private Entidad entidad;
}
