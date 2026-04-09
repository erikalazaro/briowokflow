package com.briomax.briobpm.persistence.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * El objetivo de la Class StNodoSiguientePK.java es ...
 * 
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacionNov 24, 2020 12:07:17 PM Modificaciones:
 * @since JDK 1.8
 */
@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StNodoSiguientePK implements IPrimaryKey {
	
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
	private BigDecimal idNodo;
	
	/** El atributo o variable tipo Nodo Siguiente*/
	@Column(name = "TIPO_NODO_SIGUIENTE", nullable = false, length = 40)
	private String tipoNodoSiguiente;
	
	/** El atributo o variable secuencia*/
	@Column(name = "SECUENCIA", nullable = false, precision = 5, scale = 0)
	private Integer secuencia;
	
}
