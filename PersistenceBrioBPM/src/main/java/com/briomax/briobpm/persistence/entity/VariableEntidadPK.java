package com.briomax.briobpm.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * El objetivo de la Class VariableEntidadPK.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Dic 07, 2023 6:17:47 PM Modificaciones:
 * @since JDK 1.8
 */
@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VariableEntidadPK implements IPrimaryKey {

	/** Serial Version UID. */
	private static final long serialVersionUID = 1;
	
	  /** El atributo o variable cve entidad. */
    @Column(name = "CVE_ENTIDAD", nullable = false, length = 40)
    private String cveEntidad;

    /** El atributo o variable cve variable. */
    @Column(name = "CVE_VARIABLE", nullable = false, length = 80)
    private String cveVariable;


}
