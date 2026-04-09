package com.briomax.briobpm.persistence.entity;

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
public class DatoDashboardBWPK implements IPrimaryKey {

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
	
	/** El atributo o variable numeroSerie.*/
	@Column(name = "NUMERO_SERIE", nullable = false, length = 3)
	private Integer numeroSerie;
	
	/** El atributo o variable secuenciaDato.*/
	@Column(name = "SECUENCIA_DATO", nullable = false, length = 3)
	private Integer secuenciaDato;
}
