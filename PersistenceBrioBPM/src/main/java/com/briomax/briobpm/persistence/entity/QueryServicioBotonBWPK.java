package com.briomax.briobpm.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * El objetivo de la Class QueryServicioBotonBWPK.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Oct 31, 2024 1:05:37 PM Modificaciones:
 * @since JDK 1.8
 */
@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QueryServicioBotonBWPK implements IPrimaryKey {

	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;

	/** El atributo o variable cve Entidad.*/
	@Column(name = "CVE_ENTIDAD", nullable = false, length = 40)
	private String cveEntidad;
	
	/** El atributo o variable clave Dashboard.*/
	@Column(name = "CVE_DASHBOARD", nullable = false, length = 60)
	private String cveDashboard;
	
	/** El atributo o variable clave Dashboard.*/
	@Column(name = "SECUENCIA_SECCION", nullable = false, length = 3)
	private Integer secuenciaSeccion;
	
	/** El atributo o variable clave Dashboard.*/
	@Column(name = "SECUENCIA_SUB_SECCION", nullable = false, length = 3)
	private Integer secuenciaSubSeccion;
	
	/** El atributo o variable clave Dashboard.*/
	@Column(name = "SECUENCIA_SUB_SUB_SECCION", nullable = false, length = 3)
	private Integer secuenciaSubSubSeccion;
	
	/** El atributo o variable secuenciaDato.*/
	@Column(name = "SECUENCIA_DATO", nullable = false, length = 3)
	private Integer secuenciaDato;
	
	/** El atributo o variable nivelQueryServicio.*/
	@Column(name = "NIVEL_QUERY_SERVICIO", nullable = false, length = 20)
	private String nivelQueryServicio;
		
	/** El atributo o variable secuenciaQueryServicio.*/
	@Column(name = "SECUENCIA_QUERY_SERVICIO", nullable = false, length = 3)
	private Integer secuenciaQueryServicio;
}
