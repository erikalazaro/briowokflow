package com.briomax.briobpm.persistence.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * El objetivo de la Class DatoAtVariableSistemaPK.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Mar 07, 2024 12:08:29 PM Modificaciones:
 * @since JDK 1.8
 */
@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DatoAtVariableSistemaPK implements IPrimaryKey {

	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "CVE_ENTIDAD", nullable = false, length = 40)
    private String cveEntidad;

    @Column(name = "CVE_PROCESO", nullable = false, length = 40)
    private String cveProceso;

    @Column(name = "VERSION", nullable = false, precision = 5, scale = 2)
    private BigDecimal version;
    
    @Column(name = "CVE_AREA_TRABAJO", nullable = false, length = 40)
    private String cveAreaTrabajo;
    
    @Column(name = "SECUENCIA_TARJETA", nullable = false, precision = 2, scale = 0)
    private Integer secuenciaTarjeta;
    
    @Column(name = "SECUENCIA_DATO", nullable = false, precision = 2, scale = 0)
    private Integer secuenciaDato;
    
    @Column(name = "CVE_VARIABLE", nullable = false, length = 80)
    private String cveVariable;

}
