package com.briomax.briobpm.persistence.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotacionPolacaReglaPK implements IPrimaryKey {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** El atributo o variable description. */
	@Column(name = "CVE_ENTIDAD ", nullable = false,  length = 40)
	private String cveEntidad;

	/** El atributo o variable orden Area Trabajo.*/
	@Column(name = "CVE_PROCESO", nullable = false, length = 40)
	private String cveProceso;
	
	/** El atributo o variable version*/
	@Column(name = "VERSION", nullable = false, length = 5, precision = 5, scale = 2)
	private BigDecimal version;
	
	/** El atributo o variable Tipo Area Trabajo. */
	@Column(name = "CVE_REGLA", nullable = false, length = 40)
	private String cveRegla;
}
