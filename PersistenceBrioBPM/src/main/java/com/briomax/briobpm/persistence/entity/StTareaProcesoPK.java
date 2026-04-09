package com.briomax.briobpm.persistence.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * El objetivo de la Class StProcesoPK.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Feb 06, 2024 12:07:17 PM Modificaciones:
 * @since JDK 1.8
 */
@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StTareaProcesoPK implements IPrimaryKey {

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
	
	/** El atributo o variable cve proceso. */
	@Column(name = "SECUENCIA_TAREA", nullable = false, precision = 5)
	private Integer secuenciaTarea;

}
