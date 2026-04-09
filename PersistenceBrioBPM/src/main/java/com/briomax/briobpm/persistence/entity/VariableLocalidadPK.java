package com.briomax.briobpm.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * El objetivo de la Class StProceso.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Dic 11, 2023 12:24:43 PM Modificaciones:
 * @since JDK 1.8
 */
@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VariableLocalidadPK implements IPrimaryKey {

	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "CVE_ENTIDAD", nullable = false, length = 40)
	private String cveEntidad;

	@Column(name = "CVE_LOCALIDAD", nullable = false, length = 40)
	private String cveLocalidad;

	@Column(name = "CVE_VARIABLE", nullable = false, length = 80)
	private String cveVariable;

}