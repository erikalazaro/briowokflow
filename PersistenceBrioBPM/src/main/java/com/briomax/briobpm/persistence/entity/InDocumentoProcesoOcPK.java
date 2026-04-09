package com.briomax.briobpm.persistence.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * El objetivo de la Class InDocumentoProcesoPK.java es ...
 * 
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Ago 02, 2024 12:08:29 PM Modificaciones:
 * @since JDK 1.8
 */

@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InDocumentoProcesoOcPK implements IPrimaryKey {

	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;

	/** El atributo o variable cve Entidad. */
	@Column(name = "CVE_ENTIDAD", nullable = false, length = 40)
	private String cveEntidad;

	/** El atributo o variable cve Proceso */
	@Column(name = "CVE_PROCESO", nullable = false, length = 40)
	private String cveProceso;

	/** El atributo o variable version */
	@Column(name = "VERSION", nullable = false, length = 5, precision = 5, scale = 2)
	private BigDecimal version;

	/** El atributo o variable cve Instancia */
	@Column(name = "CVE_INSTANCIA", nullable = false, length = 40)
	private String cveInstancia;

	/** El atributo o variable Ocurrencia */
	@Column(name = "OCURRENCIA", nullable = false, precision = 5, scale = 0)
	private Integer ocurrencia;

	/** El atributo o variable secuencia Documento */
	@Column(name = "SECUENCIA_DOCUMENTO", nullable = false, precision = 5, scale = 0)
	private Integer secuenciaDocumento;

	/** El atributo o variable secuencia Archivo */
	@Column(name = "SECUENCIA_ARCHIVO", nullable = false, precision = 5, scale = 0)
	private Integer secuenciaArchivo;
}