package com.briomax.briobpm.persistence.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * El objetivo de la Class DestinarioCorreoPK.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Ene 08, 2024 12:08:29 PM Modificaciones:
 * @since JDK 1.8
 */

@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DestinarioCorreoPK implements IPrimaryKey {

	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;
	
    @Column(name = "CVE_ENTIDAD", nullable = false, length = 40)
    private String cveEntidad;

    @Column(name = "CVE_PROCESO", nullable = false, length = 40)
    private String cveProceso;

    @Column(name = "VERSION", nullable = false, precision = 5, scale = 2)
    private BigDecimal version;

    @Column(name = "CVE_EVENTO_CORREO", nullable = false, length = 40)
    private String cveEventoCorreo;

    @Column(name = "SECUENCIA_CORREO", nullable = false, length = 5)
    private Integer secuenciaCorreo;

    @Column(name = "GRUPO_CORREO", nullable = false, length = 20)
    private String grupoCorreo;

    @Column(name = "SECUENCIA_DESTINATARIO", nullable = false, length = 5)
    private Integer secuenciaDestinatario;
}
