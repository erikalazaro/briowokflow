package com.briomax.briobpm.persistence.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * El objetivo de la Class ComposicionCorreoPK.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Ene 08, 2024 12:08:29 PM Modificaciones:
 * @since JDK 1.8
 */

@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ComposicionCorreoPK implements IPrimaryKey {

	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "CVE_ENTIDAD", nullable = false, length = 40)
    private String cveEntidad;

    @Column(name = "CVE_PROCESO", nullable = false, length = 40)
    private String cveProceso;

    @Column(name = "VERSION", nullable = false, length = 5, precision = 5, scale = 2)
    private BigDecimal version;
    
    @Column(name = "NUMERO_CORREO", nullable = false, precision = 6, scale = 0)
    private Integer numeroCorreo;
}
