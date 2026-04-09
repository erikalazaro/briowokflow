package com.briomax.briobpm.persistence.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * El objetivo de la Class VwDatoActividadPK.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Mar 11, 2024 12:24:43 PM Modificaciones:
 * @since JDK 1.8
 */
@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VwDatoActividadPK implements IPrimaryKey {
	
	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;
	
	/** La clave de entidad. */
	@Column(name = "CVE_ENTIDAD")
	private String cveEntidad;

	/** La clave de proceso. */
	@Column(name = "CVE_PROCESO")
	private String cveProceso;

	/** La versión. */
	@Column(name = "VERSION")
	private BigDecimal version;

	/** La clave de instancia. */
	@Column(name = "CVE_INSTANCIA")
	private String cveInstancia;

	/** La clave de nodo. */
	@Column(name = "CVE_NODO")
	private String cveNodo;

	/** El ID de nodo. */
	@Column(name = "ID_NODO")
	private Integer idNodo;

	/** La secuencia de nodo. */
	@Column(name = "SECUENCIA_NODO")
	private Integer secuenciaNodo;
}
