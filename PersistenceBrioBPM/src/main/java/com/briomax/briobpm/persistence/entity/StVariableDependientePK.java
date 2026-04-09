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
public class StVariableDependientePK implements IPrimaryKey{
	

	private static final long serialVersionUID = 1L;
	
	/** El atributo o variable cve Entidad.*/
	@Column(name = "CVE_ENTIDAD", nullable = false, length = 40)
	private String cveEntidad;
	
	/** El atributo o variable cve Proceso.*/
	@Column(name = "CVE_PROCESO", nullable = false, length = 40)
	private String cveProceso;
	
	/** El atributo o variable version.*/
	@Column(name = "VERSION", nullable = false, length = 5, precision = 5, scale = 2)
	private BigDecimal version;
	
	/** El atributo o variable cve nodo.*/
	@Column(name = "CVE_NODO", nullable = false, length = 40)
	private String cveNodo;
	
	/** El atributo o variable id nodo.*/
	@Column(name = "ID_NODO", nullable = false, precision = 5, scale = 0)
	private Integer idNodo;
	
	/** El atributo o variable cve seccion.*/
	@Column(name = "CVE_SECCION", nullable = false, length = 40)
	private String cveSeccion;
	
	/** El atributo o variable cve variable.*/
	@Column(name = "CVE_VARIABLE", nullable = false, length = 80)
	private String cveVariable;
	
	/** El atributo o variable cve variable.*/
	@Column(name = "CVE_SECCION_DEPENDIENTE", nullable = false, length = 40)
	private String cveSeccionDependiente;

	/** El atributo o variable cve variable.*/
	@Column(name = "CVE_VARIABLE_DEPENDIENTE", nullable = false, length = 80)
	private String cveVariableDependiente;


}
