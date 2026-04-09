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
public class ReglaVariableSeccionPK implements IPrimaryKey {
	
	/**
	 * 
	 */
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
	
	/** El atributo o variable cve Regla*/
	@Column(name = "CVE_REGLA", nullable = false, length = 40)
	private String cveRegla;
	
	/** El atributo o variable CVE NODO.*/
	@Column(name = "CVE_NODO", nullable = false, length = 40)
	private String cveNodo;
	
	/** El atributo o variable ID NODO*/
	@Column(name = "ID_NODO", nullable = false, length = 5, precision = 5, scale = 0)
	private Integer idNodo;
	
	/** El atributo o variable CVE SECCION*/
	@Column(name = "CVE_SECCION", nullable = false, length = 40)
	private String cveSeccion;
	
	/** El atributo o variable CVE VARIABLE*/
	@Column(name = "CVE_VARIABLE", nullable = false, length = 80)
	private String cveVariable;
	/** Serial Version UID. */
	
	/** El atributo o variable ORDEN APLICACION*/
	@Column(name = "ORDEN_APLICACION", nullable = false, length = 2, precision = 2, scale = 0 )
	private BigDecimal ordenAplicacion;
	

}
