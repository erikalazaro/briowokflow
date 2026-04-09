package com.briomax.briobpm.persistence.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * El objetivo de la Class InBitacoraNodo.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Dic 15, 2023 12:08:29 PM Modificaciones:
 * @since JDK 1.8
 */
@Entity
@Table(name = "FOLIO_MENSAJE_PROCESO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FolioMensajeProceso implements IEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name = "FOLIO_MENSAJE_ENVIO", nullable = false, precision = 9, scale = 0)
    private Integer folioMensajeEnvio;

    @Column(name = "CVE_ENTIDAD", nullable = false, length = 40)
    private String cveEntidad;

    @Column(name = "CVE_PROCESO", nullable = false, length = 40)
    private String cveProceso;

    @Column(name = "VERSION", nullable = false, precision = 5, scale = 2)
    private BigDecimal version;

    @Column(name = "CVE_INSTANCIA", nullable = false, length = 40)
    private String cveInstancia;

    @Column(name = "CVE_NODO", nullable = false, length = 40)
    private String cveNodo;

    @Column(name = "ID_NODO", nullable = false, precision = 5, scale = 0)
    private Integer idNodo;

    @Column(name = "SECUENCIA_NODO", nullable = false)
    private Integer secuenciaNodo;

    @Column(name = "FECHA_CREACION", nullable = false)
    private Date fechaCreacion;
    
    /** El atributo o variable entidad. */
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name = "CVE_ENTIDAD", referencedColumnName = "CVE_ENTIDAD", insertable = false, updatable = false),
		@JoinColumn(name = "CVE_PROCESO", referencedColumnName = "CVE_PROCESO", insertable = false, updatable = false),
		@JoinColumn(name = "VERSION", referencedColumnName = "VERSION", insertable = false, updatable = false),
		@JoinColumn(name = "CVE_INSTANCIA", referencedColumnName = "CVE_INSTANCIA", insertable = false, updatable = false),
		@JoinColumn(name = "CVE_NODO", referencedColumnName = "CVE_NODO", insertable = false, updatable = false),
		@JoinColumn(name = "ID_NODO", referencedColumnName = "ID_NODO", insertable = false, updatable = false),
		@JoinColumn(name = "SECUENCIA_NODO", referencedColumnName = "SECUENCIA_NODO", insertable = false, updatable = false)
	})
	private InNodoProceso inNodoProceso;
}
