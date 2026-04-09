package com.briomax.briobpm.persistence.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BitacoraBatchPK implements IPrimaryKey {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** El atributo o variable cve Procreso Batch.*/
	@Column(name = "CVE_PROCESO_BATCH", nullable = false, length = 60)
	private String cveProcresoBatch;
	
	/** El atributo o variable cve Procreso Batch.*/
	@Column(name = "CVE_SUBPROCESO_BATCH", nullable = false, length = 60)
	private String cveSubProcresoBatch;
	
	/** El atributo o variable cve Procreso Batch.*/
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_MENSAJE")
	private Date fechaMensaje;
}
