package com.briomax.briobpm.persistence.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * El objetivo de la Class InMensajeRecepcion.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Dic 01, 2023 12:08:29 PM Modificaciones:
 * @since JDK 1.8
 */
@Entity
@Table(name = "IN_MENSAJE_RECEPCION")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InMensajeRecepcion implements IEntity {
	
	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;
	
	/** El atributo o variable Id*/
	@EmbeddedId
	private InMensajeRecepcionPK id;
	
	/** El atributo o variable valores Referencia Envio*/
	@Column(name = "VALORES_REFERENCIA_REC", length = 500)
	private String valoresReferenciaRec;
	
	/** El atributo o variable fecha Creacion*/
	@Column(name = "FECHA_CREACION", nullable = false, precision = 2, scale = 0)
	private Date fechaCreacion;
	
	/** El atributo o variable fecha Envio*/
	@Column(name = "FECHA_ENVIO")
	private Date fechaEnvio;
	
	/** El atributo o variable situacion Envio*/
	@Column(name = "SITUACION_RECEPCION", nullable = false, length = 20)
	private String situacionRecepcion;
	
	@ManyToOne
	@JoinColumns({
	    @JoinColumn(name="CVE_ENTIDAD", referencedColumnName = "CVE_ENTIDAD", insertable = false, updatable = false),
	    @JoinColumn(name="CVE_PROCESO", referencedColumnName = "CVE_PROCESO", insertable = false, updatable = false),
	    @JoinColumn(name="VERSION", referencedColumnName = "VERSION", insertable = false, updatable = false),
	    @JoinColumn(name="CVE_INSTANCIA", referencedColumnName = "CVE_INSTANCIA", insertable = false, updatable = false),
	    @JoinColumn(name="CVE_NODO", referencedColumnName = "CVE_NODO", insertable = false, updatable = false),
	    @JoinColumn(name="ID_NODO", referencedColumnName = "ID_NODO", insertable = false, updatable = false),
	    @JoinColumn(name="SECUENCIA_NODO", referencedColumnName = "SECUENCIA_NODO", insertable = false, updatable = false) 
	})
	private InNodoProceso inNodoProceso;

}
