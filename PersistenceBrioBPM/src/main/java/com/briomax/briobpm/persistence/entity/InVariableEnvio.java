package com.briomax.briobpm.persistence.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
/**
 * El objetivo de la Class InTareaProceso.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Nov 22, 2023 12:08:29 PM Modificaciones:
 * @since JDK 1.8
 */
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name = "IN_VARIABLE_ENVIO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InVariableEnvio implements IEntity {

	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;
	
	/** El atributo o variable id. */
	@EmbeddedId
	private InVariableEnvioPK id;

	/** El atributo o variable cve variable destino. */
	@Column(name = "CVE_VARIABLE_DESTINO", nullable = false, length = 80)
	private String cveVariableDestino;

	/** El atributo o variable valor alfanumerico. */
	@Column(name = "VALOR_ALFANUMERICO", length = 1500)
	private String valorAlfanumerico;

	/** El atributo o variable valor entero. */
	@Column(name = "VALOR_ENTERO", precision = 30, scale = 0)
	private Integer valorEntero;

	/** El atributo o variable valor decimal. */
	@Column(name = "VALOR_DECIMAL", precision = 30, scale = 6)
	private BigDecimal valorDecimal;

	/** El atributo o variable valor fecha. */
	@Column(name = "VALOR_FECHA")
	private Date valorFecha;
	
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="CVE_ENTIDAD", referencedColumnName = "CVE_ENTIDAD", insertable = false, updatable = false),
		@JoinColumn(name="CVE_PROCESO", referencedColumnName = "CVE_PROCESO", insertable = false, updatable = false),
		@JoinColumn(name="VERSION", referencedColumnName = "VERSION", insertable = false, updatable = false),
		@JoinColumn(name="CVE_INSTANCIA", referencedColumnName = "CVE_INSTANCIA", insertable = false, updatable = false),
		@JoinColumn(name="CVE_NODO", referencedColumnName = "CVE_NODO", insertable = false, updatable = false),
		@JoinColumn(name="ID_NODO", referencedColumnName = "ID_NODO", insertable = false, updatable = false),
		@JoinColumn(name="SECUENCIA_NODO", referencedColumnName = "SECUENCIA_NODO", insertable = false, updatable = false) })
	private InMensajeEnvio inMensajeEnvio;
}
