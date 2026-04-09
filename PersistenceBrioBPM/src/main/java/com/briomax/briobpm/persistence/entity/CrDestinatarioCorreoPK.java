package com.briomax.briobpm.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * El objetivo de la Class InDocumentoProcesoPK.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Nov 07, 2023 12:08:29 PM Modificaciones:
 * @since JDK 1.8
 */

@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CrDestinatarioCorreoPK implements IPrimaryKey {

	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;

	@Column(name = "CVE_ENTIDAD", nullable = false, length = 40)
    private String cveEntidad;

	@Column(name = "CVE_LOCALIDAD", nullable = false, length = 40)
	private String cveLocalidad;
	
	/** El atributo o variable cve idioma. */
	@Column(name = "CVE_IDIOMA", nullable = false, length = 40)
	private String cveIdioma;
	
    @Column(name = "CVE_PROCESO_PERIODICO", nullable = false, length = 40)
    private String cveProcesoPeriodico;
    
    @Column(name = "SECUENCIA_CORREO", nullable = false, precision = 5, scale = 0)
    private Integer secuenciaCorreo;
    
    @Column(name = "SECUENCIA_DESTINATARIO", nullable = false, precision = 5, scale = 0)
    private Integer secuenciaDestinatario;
    
}
