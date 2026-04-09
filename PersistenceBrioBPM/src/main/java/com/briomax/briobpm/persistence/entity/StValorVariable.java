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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * El objetivo de la Class StValorVariable.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Abr 10, 2024 12:24:43 PM Modificaciones:
 * @since JDK 1.8
 */
@Entity
@Table(name = "ST_VALOR_VARIABLE")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {})
@EqualsAndHashCode(callSuper = false, exclude = {})
public class StValorVariable implements IEntity {/**

	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;

	/** El atributo o variable id. */
	@EmbeddedId
	private StValorVariablePK id;
	
	/** El atributo o variable origen. */
	@Column(name = "ETIQUETA_LISTA", length = 80)
	private String etiquetaLista;
	
	/** El atributo o variable origen. */
	@Column(name = "VALOR_ALFANUMERICO", length = 1500)
	private String valorAlfanumerico;
	
	/** El atributo o variable entero. */
	@Column(name = "VALOR_ENTERO", length = 30, precision = 30, scale = 0)
	private Integer valorEntero;
	
	/** El atributo o variable decimal. */
	@Column(name = "VALOR_DECIMAL", length = 30, precision = 30, scale = 6)
	private BigDecimal valorDecimal;
	
	/** El atributo o variable fecha. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "VALOR_FECHA")
	private Date valorFecha;
	
	/** El atributo o variable StVariableProceso. */
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="CVE_ENTIDAD", referencedColumnName = "CVE_ENTIDAD", insertable = false, updatable = false),
		@JoinColumn(name="CVE_PROCESO", referencedColumnName = "CVE_PROCESO", insertable = false, updatable = false),
		@JoinColumn(name="VERSION", referencedColumnName = "VERSION", insertable = false, updatable = false),
		@JoinColumn(name="CVE_VARIABLE", referencedColumnName = "CVE_VARIABLE", insertable = false, updatable = false)
		})
	private StVariableProceso stVariableProceso;
}
