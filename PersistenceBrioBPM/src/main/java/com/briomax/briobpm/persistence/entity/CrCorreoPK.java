package com.briomax.briobpm.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * El objetivo de la Class CorreoProcesoPK.java es ...
 * @author Sara Ventura
 * @version 1.0 Fecha de creacion Dic 22, 2023 12:08:29 PM Modificaciones:
 * @since JDK 1.8
 */

@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CrCorreoPK implements IPrimaryKey {

	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;

	@Column(name = "CVE_ENTIDAD", nullable = false, length = 40)
    private String cveEntidad;

	@Column(name = "CVE_LOCALIDAD", nullable = false, length = 40)
	private String cveLocalidad;
	
	/** El atributo o variable cve idioma. */
	@Column(name = "CVE_IDIOMA", nullable = false, length = 40)
	private String cveIdioma;
	
    @Column(name = "SECUENCIA_CORREO", nullable = false, length = 5)
    private Integer secuenciaCorreo;
    

   }
