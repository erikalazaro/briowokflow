package com.briomax.briobpm.persistence.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * El objetivo de la Class CrPdfFilesPK.java es ...
 * @author Erika Vazquez
 * @version 1.0 Fecha de creacion Enero 31, 2025 12:08:29 PM Modificaciones:
 * @since JDK 1.8
 */

@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CrPdfFilesPK implements IPrimaryKey {
	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;

	@Column(name = "CVE_ENTIDAD", nullable = false, length = 40)
    private String cveEntidad;
	
    @Column(name = "CVE_PROCESO_PERIODICO", nullable = false, length = 40)
    private String cveProcesoPeriodico;
    
	/** El atributo o variable. */
	@Column(name = "FECHA_CARGA", nullable = false)
	private Date fechaCarga;
	
	/** El atributo o variable secuencia Definicion. */
	@Column(name = "SECUENCIA_CARGA", nullable = false,  precision = 5, scale = 0)
	private Integer secuenciaCarga;
	
    @Column(name = "NUMERO_CONTRATO", nullable = false, length = 50)
    private String numeroContrato;  
    
    @Column(name = "RFC", nullable = false, length = 50)
    private String rfc; 
    
    @Column(name = "TIPO_ARCHIVO", nullable = false, length = 5)
    private String tipoArchivo; 
    

}
