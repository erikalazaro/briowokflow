package com.briomax.briobpm.persistence.entity;

import java.math.BigDecimal;

import javax.persistence.Column;

public class ReglaBotonActividadPK implements IPrimaryKey {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** El atributo o variable description. */
	@Column(name = "CVE_ENTIDAD ", nullable = false,  length = 40)
	private String cveEntidad;

	/** El atributo o variable orden Area Trabajo.*/
	@Column(name = "CVE_PROCESO",nullable = false, length = 40 )
	private String cveProceso;
	
	/** El atributo o variable situacion Area Trabajo. */
	@Column(name = "VERSION", nullable = false, precision = 5, scale = 2 )
	private BigDecimal version;
	
	@Column(name = "CVE_NODO", nullable = false, length = 40)
	private String cveNodo;
	
	@Column(name = "ID_NODO", nullable = false, precision = 5, scale = 0)
	private Integer idNodo;
	
    @Column(name = "TIPO_BOTON", nullable = false, length = 20)	
	private String tipoBoton;
	
	/** El atributo o variable Tipo Area Trabajo. */
	@Column(name = "SECUENCIA_REGLA", nullable = false, precision = 38, scale = 0)
	private Integer secuenciaRegla;

}
