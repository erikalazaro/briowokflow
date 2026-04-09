package com.briomax.briobpm.persistence.entity;

import java.io.Serializable;

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
 * El objetivo de la Class StMensajeRecepcion.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Dic 22, 2023 12:24:43 PM Modificaciones:
 * @since JDK 1.8
 */
@Entity
@Table(name = "ST_MENSAJE_RECEPCION")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StMensajeRecepcion implements Serializable {

	/** serial Version UID. */
	private static final long serialVersionUID = 1L;
	
	/** El atributo o variable id. */
	@EmbeddedId
	private StMensajeRecepcionPK id;
	
    @Column(name = "DESCRIPCION_RECEPCION", nullable = false, length = 100)
    private String descripcionRecepcion;

    @Column(name = "VARIABLES_REFERENCIA_REC", length = 500)
    private String variablesReferenciaRec;
    /** El atributo o variable areaTrabajo. */
	
    @ManyToOne
	@JoinColumns({
		@JoinColumn(name="CVE_ENTIDAD", referencedColumnName = "CVE_ENTIDAD", insertable = false, updatable = false),
		@JoinColumn(name="CVE_PROCESO", referencedColumnName = "CVE_PROCESO", insertable = false, updatable = false),
		@JoinColumn(name="VERSION", referencedColumnName = "VERSION", insertable = false, updatable = false),
		@JoinColumn(name="CVE_NODO", referencedColumnName = "CVE_NODO", insertable = false, updatable = false),
		@JoinColumn(name="ID_NODO", referencedColumnName = "ID_NODO", insertable = false, updatable = false)
	})
	private StNodoProceso stNodoProceso;
}
