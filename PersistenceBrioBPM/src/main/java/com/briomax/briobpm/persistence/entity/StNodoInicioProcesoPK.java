package com.briomax.briobpm.persistence.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * El objetivo de la Class StNodoProcesoPK.java es ...
 * @author Erika Vazquez
 * @version 1.0 Fecha de creacion Nov 11, 2023 
 * @since JDK 1.8
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class StNodoInicioProcesoPK implements IPrimaryKey {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** El atributo o variable cve entidad. */
	@Column(name = "CVE_ENTIDAD", nullable = false, length = 40)
	private String cveEntidad;

	/** El atributo o variable cve proceso. */
	@Column(name = "CVE_PROCESO", nullable = false, length = 40)
	private String cveProceso;

	/** El atributo o variable version. */
	@Column(name = "VERSION", nullable = false, length = 5, precision = 5, scale = 2)
	private BigDecimal version;

	/** El atributo o variable cve nodo. */
	@Column(name = "CVE_NODO", nullable = false, length = 40)
	private String cveNodo;

	/** El atributo o variable id nodo. */
	@Column(name = "ID_NODO", nullable = false, length = 5, precision = 5, scale = 0)
	private Integer idNodo;

}
