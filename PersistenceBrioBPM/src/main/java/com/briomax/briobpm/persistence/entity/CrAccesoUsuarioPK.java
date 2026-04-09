package com.briomax.briobpm.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * El objetivo de la Class CrAccesoUsuarioPK.java es ...
 * @author Sara Ventura
 * @version 1.0 Fecha de creacion Nov 25, 2025 12:08:29 PM Modificaciones:
 * @since JDK 1.8
 */

@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CrAccesoUsuarioPK implements IPrimaryKey {

	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;
	
	/** El atributo o variable cve entidad. */
	@Column(name = "CVE_ENTIDAD", nullable = false, length = 40)
	private String cveEntidad;

	/** El atributo o variable cve usuario. */
	@Column(name = "CVE_USUARIO", nullable = false, length = 30)
	private String cveUsuario;
    	
	/** El atributo o variable RFC Contratista. */
	@Column(name = "RFC", nullable = false, length = 20)
	private String rfc;
	
	/** El atributo o variable. */
	@Column(name = "TIPO_ACCESO_CR", nullable = false, length = 20)
	private String tipoAccesoCr;


}
