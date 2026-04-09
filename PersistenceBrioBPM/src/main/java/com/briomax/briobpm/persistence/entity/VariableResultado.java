package com.briomax.briobpm.persistence.entity;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * El objetivo de la Class StProceso.java es ...
 * @author Erika Vazquez
 * @version 1.0 Fecha de creacion Sep 10, 2024 12:24:43 PM Modificaciones:
 * @since JDK 11
 */
@Entity
@Table(name = "VARIABLE_RESULTADO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {})
@EqualsAndHashCode(callSuper = false, exclude = {})
public class VariableResultado implements IEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private VariableResultadoPK id;
	
	/** El atributo o variable APLICAR EN GUARDADO*/
	@Column(name = "VALOR_ALFANUMERICO", nullable = false, length = 1500)
	private String valorAlfanumerico;
	
	/** El atributo o variable APLICAR EN GUARDADO*/
	@Column(name = "VALOR_ENTERO", nullable = false, length = 30)
	private BigInteger valorEntero;
	
	/** El atributo o variable APLICAR EN GUARDADO*/
	@Column(name = "VALOR_DECIMAL", nullable = false,  length = 30, precision = 30, scale = 6)
	private BigDecimal valorDecimal;
	
	/** El atributo o variable APLICAR EN GUARDADO*/
	@Column(name = "VALOR_FECHA", nullable = false, length = 4)
	private Date valorFecha;

}
