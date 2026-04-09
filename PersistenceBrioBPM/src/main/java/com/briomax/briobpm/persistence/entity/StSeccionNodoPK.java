package com.briomax.briobpm.persistence.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * El objetivo de la Class StSeccionNodoPK.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Nov 24, 2023 12:08:29 PM Modificaciones:
 * @since JDK 1.8
 */

@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StSeccionNodoPK implements IPrimaryKey {

	/** Serial Version UID. */
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
	
	/** El atributo o variable cve Nodo*/
	@Column(name = "CVE_NODO", nullable = false, length = 40)
	private String cveNodo;
	
	/** El atributo o variable id Nodo*/
	@Column(name = "ID_NODO", nullable = false, precision = 5, scale = 0)
	private Integer idNodo;
	
	/** El atributo o variable cveSeccion. */
	@Column(name = "CVE_SECCION", nullable = false, length = 40)
	private String cveSeccion;
	
}
