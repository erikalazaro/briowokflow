package com.briomax.briobpm.persistence.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * El objetivo de la Class StReglaValidacionProceso.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Feb 06, 2024 12:08:29 PM Modificaciones:
 * @since JDK 1.8
 */

@Entity
@Table(name = "MONEDA_PROCESO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MonedaProceso implements IEntity {
	
	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private MonedaProcesoPK id;
	
	/** El atributo o variable stProceso. */
	@ManyToOne
	@JoinColumn(name="CVE_MONEDA", referencedColumnName = "CVE_MONEDA", insertable = false, updatable = false)
	private Moneda moneda;

}
