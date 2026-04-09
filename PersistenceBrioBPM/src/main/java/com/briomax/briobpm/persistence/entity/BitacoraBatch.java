package com.briomax.briobpm.persistence.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * El objetivo de la Class Entidad.java es ...
 * @author Erika Vázquez
 * @version 1.0 Fecha de creacion Nov 03, 2023 12:08:29 PM Modificaciones:
 * @since JDK 1.8
 */
@Entity
@Table(name = "BITACORA_BATCH")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {})
@EqualsAndHashCode(callSuper = false, exclude = {})
public class BitacoraBatch implements IEntity {

	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private BitacoraBatchPK id;
	
	/** El atributo o variable cve Procreso Batch.*/
	@Column(name = "TIPO_MENSAJE", nullable = false, length = 20)
	private String tipoMensaje;
	
	/** El atributo o variable cve Procreso Batch.*/
	@Column(name = "MENSAJE_PRINCIPAL", nullable = false, length = 500)
	private String mensajePrincipal;
	
	/** El atributo o variable cve Procreso Batch.*/
	@Column(name = "MENSAJE_AUXILIAR",  length = 500)
	private String mensajeAuxiliar;
	
	/** El atributo o variable cve Procreso Batch.*/
	@Column(name = "SUGERENCIA",  length = 500)
	private String sugerencia;
	
	/** El atributo o variable cve Procreso Batch.*/
	@Column(name = "CVE_ENTIDAD",  length = 40)
	private String cveEntidad;
	
	/** El atributo o variable cve Procreso Batch.*/
	@Column(name = "CVE_PROCESO",  length = 40)
	private String cveProceso;
	
	/** El atributo o variable cve Procreso Batch.*/
	@Column(name = "VERSION", length = 5, precision = 5, scale = 2)
	private BigDecimal version;
	
	/** El atributo o CVE_INSTANCIA cve Procreso Batch.*/
	@Column(name = "CVE_INSTANCIA",  length = 40)
	private String cveInstancia;
	
	/** El atributo o variable cve Procreso Batch.*/
	@Column(name = "CVE_NODO",  length = 40)
	private String cveNodo;
	
	/** El atributo o CVE_INSTANCIA cve Procreso Batch.*/
	@Column(name = "ID_NODO",  precision = 5, scale = 0)
	private Integer idNodo;
	
	/** El atributo o variable cve Procreso Batch.*/
	@Column(name = "SECUENCIA_NODO",  precision = 5, scale = 0)
	private Integer secuenciaNodo;

}
