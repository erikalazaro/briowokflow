package com.briomax.briobpm.persistence.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * El objetivo de la Class CrReciboNominaPK.java es ...
 * @author Sara Ventura
 * @version 1.0 Fecha de creacion Dic 22, 2023 12:08:29 PM Modificaciones:
 * @since JDK 1.8
 */

@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CrReciboNominaPK implements IPrimaryKey {

	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;

	@Column(name = "CVE_ENTIDAD", nullable = false, length = 40)
    private String cveEntidad;

    @Column(name = "CVE_PROCESO", nullable = false, length = 40)
    private String cveProceso;
    
	/** El atributo o variable. */
	@Column(name = "RFC_CONTRATISTA", nullable = false, length = 20)
	private String rfcContratista;

	/** El atributo o variable. */
	@Column(name = "NUMERO_CONTRATO", nullable = false, length = 50)
	private String numContrato;
	
	/** El atributo o variable. */
	@Column(name = "FECHA_CARGA", nullable = false)
	private Date fechaCarga;

	/** El atributo o variable. */
	@Column(name = "RFC_TRABAJADOR", nullable = false, length = 20)
	private String rfcTrabajador;
	
	/** El atributo o variable. */
	@Column(name = "TIPO_NOMINA", nullable = false, length = 20)
	private String tipoNomina;
	
    @Column(name = "SECUENCIA_RECIBO", nullable = false, precision = 5, scale = 0)
    private Integer secuenciaRecibo;
}
