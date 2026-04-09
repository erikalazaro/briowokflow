package com.briomax.briobpm.persistence.entity;

import java.math.BigDecimal;

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
public class TarjetaAreaTrabajoPK implements IPrimaryKey {
    private static final long serialVersionUID = 1L;

    @Column(name = "CVE_ENTIDAD", nullable = false, length = 40)
    private String cveEntidad;

    @Column(name = "CVE_PROCESO", nullable = false, length = 40)
    private String cveProceso;

    @Column(name = "VERSION", nullable = false, length = 5)
    private BigDecimal version;

    @Column(name = "CVE_AREA_TRABAJO", nullable = false, length = 40)
    private String cveAreaTrabajo;

    @Column(name = "SECUENCIA_TARJETA", nullable = false, length = 5)
    private int secuenciaTarjeta;
}
