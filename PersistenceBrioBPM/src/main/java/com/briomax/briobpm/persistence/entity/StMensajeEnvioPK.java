package com.briomax.briobpm.persistence.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * El objetivo de la Class StMensajeEnvioPK.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Dic 22, 2023 12:08:29 PM Modificaciones:
 * @since JDK 1.8
 */

@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StMensajeEnvioPK implements IPrimaryKey {

	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;
	 
	@Column(name = "CVE_ENTIDAD", nullable = false, length = 40)
    private String cveEntidad;

    @Column(name = "CVE_PROCESO", nullable = false, length = 40)
    private String cveProceso;

    @Column(name = "VERSION", nullable = false, precision = 5, scale = 2)
    private BigDecimal version;

    @Column(name = "CVE_NODO", nullable = false, length = 40)
    private String cveNodo;

    @Column(name = "ID_NODO", nullable = false, precision = 5, scale = 0)
    private Integer idNodo;

}
