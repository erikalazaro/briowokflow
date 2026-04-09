package com.briomax.briobpm.persistence.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * El objetivo de la Class CrCedulaDetCuotasPK.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Sep 18, 2024 12:08:29 PM Modificaciones:
 * @since JDK 1.8
 */

@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CrCedulaDetCuotasPK implements IPrimaryKey {

	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;

	@Column(name = "CVE_ENTIDAD", nullable = false, length = 40)
    private String cveEntidad;

    @Column(name = "CVE_PROCESO", nullable = false, length = 40)
    private String cveProceso;

    @Column(name = "RFC_CONTRATISTA", nullable = false, length = 20)
    private String rfcContratista;

    @Column(name = "NUMERO_CONTRATO", nullable = false, length = 50)
    private String numeroContrato;

    @Column(name = "FECHA_CARGA", nullable = false)
    private Date fechaCarga;
    
    @Column(name = "TIPO_PERIODO", nullable = false, length = 5)
    private String tipoPeriodo;
    
	/** El atributo o variable orden Area Trabajo.*/
	@Column(name = "SECUENCIA_CEDULA", nullable = false, precision = 5, scale = 0)
	private Integer secuenciaCedula;
}

 