/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.persistence.entity;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * El objetivo de la clase ParametroGeneral.java es ... The persistent class for
 * the BF_PARAMETRO_GENERAL database table.
 * 
 * @author Sara Ventura
 * @version 1.0 Fecha de creacion Dic 04, 2023 Modificaciones:
 * @since JDK 1.8
 */
@Entity
@Table(name="PARAMETRO_GENERAL")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {})
@EqualsAndHashCode(callSuper = false, exclude = {})
public class ParametroGeneral implements IEntity {

	/** El atributo o variable serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** El atributo o variable cveParametro. */
	@Id
	@Column(name="CVE_PARAMETRO", unique=true, nullable=false, length=40)
	private String cveParametro;
	
	/** El atributo o variable descripcion. */
	@Column(name="DESCRIPCION", nullable=false, length=100)
	private String descripcion;

	/** El atributo o variable tipo. */
	@Column(name="TIPO", nullable=false, length=12)
	private String tipo;

	/** El atributo o variable valorAlfanumerico. */
	@Column(name="VALOR_ALFANUMERICO", length=1500)
	private String valorAlfanumerico;

	/** El atributo o variable valorEntero. */
	@Column(name="VALOR_ENTERO", precision=30)
	private Integer valorEntero;

	/** El atributo o variable valorDecimal. */
	@Column(name="VALOR_DECIMAL", precision=30, scale=6)
	private BigDecimal valorDecimal;

	/** El atributo o variable valorFecha. */
	@Column(name="VALOR_FECHA")
	private Date valorFecha;

}
