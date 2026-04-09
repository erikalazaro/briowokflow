/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

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
 * El objetivo de la Class StProceso.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Jun 25, 2020 12:24:43 PM Modificaciones:
 * @since JDK 1.8
 */
@Entity
@Table(name = "IN_VARIABLE_PROCESO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {})
@EqualsAndHashCode(callSuper = false, exclude = {})
public class InVariableProceso implements IEntity {

	/** Serial Version UID. */
	private static final long serialVersionUID = 3008509815141573530L;

	/** El atributo o variable id. */
	@EmbeddedId
	private InVariableProcesoPK id;

	/** El atributo o variable origen. */
	@Column(name = "VALOR_ALFANUMERICO", length = 1500)
	private String valorAlfanumerico;
	
	/** El atributo o variable fecha. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "VALOR_FECHA")
	private Date valorFecha;
	
	/** El atributo o variable decimal. */
	@Column(name = "VALOR_DECIMAL", precision = 30, scale = 6)
	private BigDecimal valorDecimal;

	/** El atributo o variable entero. */
	@Column(name = "VALOR_ENTERO")
	private Integer valorEntero;

	/** El atributo o variable inProceso. */
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="CVE_ENTIDAD", referencedColumnName = "CVE_ENTIDAD", insertable = false, updatable = false),
		@JoinColumn(name="CVE_PROCESO", referencedColumnName = "CVE_PROCESO", insertable = false, updatable = false),
		@JoinColumn(name="VERSION", referencedColumnName = "VERSION", insertable = false, updatable = false),
		@JoinColumn(name="CVE_INSTANCIA", referencedColumnName = "CVE_INSTANCIA", insertable = false, updatable = false)
	})
	private InProceso inProceso;

	/** El atributo o variable stVariableProceso. */
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="CVE_ENTIDAD", referencedColumnName = "CVE_ENTIDAD", insertable = false, updatable = false),
		@JoinColumn(name="CVE_PROCESO", referencedColumnName = "CVE_PROCESO", insertable = false, updatable = false),
		@JoinColumn(name="VERSION", referencedColumnName = "VERSION", insertable = false, updatable = false),
		@JoinColumn(name="CVE_VARIABLE", referencedColumnName = "CVE_VARIABLE", insertable = false, updatable = false)
	})
	private StVariableProceso stVariableProceso;
}
