package com.briomax.briobpm.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * El objetivo de la Class CorreoProcesoPK.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Jul 22, 2023 12:08:29 PM Modificaciones:
 * @since JDK 1.8
 */

@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CrNotificacionPK implements IPrimaryKey {

	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;

	@Column(name = "CVE_ENTIDAD", nullable = false, length = 40)
    private String cveEntidad;

    @Column(name = "CVE_LOCALIDAD", nullable = false, length = 40)
    private String cveLocalidad;

    @Column(name = "CVE_IDIOMA", nullable = false, length = 40)
    private String cveIdioma;

    @Column(name = "CVE_PROCESO_PERIODICO", nullable = false, length = 40)
    private String cveProcesoPeriodico;

    @Column(name = "SECUENCIA_CORREO", nullable = false, precision = 5, scale = 0)
    private Integer secuenciaCorreo;
    
    @Column(name = "EJECUTOR", nullable = false, length = 12)
    private String ejecutor;

    @Column(name = "RFC", nullable = false, length = 20)
    private String rfc;

    @Column(name = "CONTRATO", nullable = false, length = 20)
    private String contrato;


}
