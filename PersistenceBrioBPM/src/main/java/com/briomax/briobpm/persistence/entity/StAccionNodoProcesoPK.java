package com.briomax.briobpm.persistence.entity;

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * El objetivo de la Class StNodoProcesoPK.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Nov 07, 2023 12:08:29 PM Modificaciones:
 * @since JDK 1.8
 */

@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StAccionNodoProcesoPK implements IPrimaryKey {

	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;

	/** El atributo o variable cve Entidad.*/
	@Column(name = "CVE_ENTIDAD", nullable = false, length = 40)
	private String cveEntidad;
	
	/** El atributo o variable cve Proceso*/
	@Column(name = "CVE_PROCESO", nullable = false, length = 40)
	private String cveProceso;
	
	/** El atributo o variable version*/
	@Column(name = "VERSION", nullable = false, length = 5, precision = 5, scale = 2)
	private BigDecimal version;
	
	/** El atributo o variable cve Nodo*/
	@Column(name = "CVE_NODO", nullable = false, length = 40)
	private String cveNodo;
	
	/** El atributo o variable id Nodo*/
	@Column(name = "ID_NODO", nullable = false, precision = 5, scale = 0)
	private Integer idNodo;
		
	/** El atributo o variable cve Proceso*/
	@Column(name = "CVE_SECCION", nullable = false, length = 40)
	private String cveSeccion;
	
	/** El atributo o variable id Nodo*/
	@Column(name = "SECUENCIA_ACCION", nullable = false, precision = 2, scale = 0)
	private Integer secuenciaAccion;
}
