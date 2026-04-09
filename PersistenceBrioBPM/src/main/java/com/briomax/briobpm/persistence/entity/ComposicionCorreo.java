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
 * El objetivo de la Class InFolioNodo.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Dic 08, 2024 12:08:29 PM Modificaciones:
 * @since JDK 1.8
 */
@Entity
@Table(name = "COMPOSICION_CORREO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ComposicionCorreo implements IEntity {

	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private ComposicionCorreoPK id;

    @Column(name = "FECHA_COMPOSICION", nullable = false)
    private Date fechaComposicion;

    @Column(name = "REFERENCIA", nullable = false, length = 60)
    private String referencia;

    @Column(name = "SITUACION_CORREO", nullable = false, length = 20)
    private String situacionCorreo;

    @Column(name = "FECHA_ENVIO")
    private Date fechaEnvio;

    @Column(name = "PARA", nullable = false, length = 1000)
    private String para;

    @Column(name = "CON_COPIA_PARA", length = 1000)
    private String conCopiaPara;

    @Column(name = "ASUNTO", nullable = false, length = 300)
    private String asunto;

    @Column(name = "MENSAJE", nullable = false, length = 4000)
    private String mensaje;
    
	/** El atributo o variable cve Nodo*/
	@Column(name = "CVE_NODO", nullable = false, length = 40)
	private String cveNodo;
	
	/** El atributo o variable id Nodo*/
	@Column(name = "ID_NODO", nullable = false, precision = 5, scale = 0)
	private Integer idNodo;
	
	/** Secuencia del nodo */
	@Column(name = "SECUENCIA_NODO", nullable = false)
	private Integer secuenciaNodo;
	
	
    
	/** El atributo o variable entidad. */
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name = "CVE_ENTIDAD", referencedColumnName = "CVE_ENTIDAD", insertable = false, updatable = false),
		@JoinColumn(name = "CVE_PROCESO", referencedColumnName = "CVE_PROCESO", insertable = false, updatable = false),
		@JoinColumn(name = "VERSION", referencedColumnName = "VERSION", insertable = false, updatable = false)
	})
	private StProceso stProceso;
	
	/** Relación con ST_NODO_PROCESO */
	@ManyToOne
	@JoinColumns({
	    @JoinColumn(name = "CVE_ENTIDAD", referencedColumnName = "CVE_ENTIDAD", insertable = false, updatable = false),
	    @JoinColumn(name = "CVE_PROCESO", referencedColumnName = "CVE_PROCESO", insertable = false, updatable = false),
	    @JoinColumn(name = "VERSION", referencedColumnName = "VERSION", insertable = false, updatable = false),
	    @JoinColumn(name = "CVE_NODO", referencedColumnName = "CVE_NODO", insertable = false, updatable = false),
	    @JoinColumn(name = "ID_NODO", referencedColumnName = "ID_NODO", insertable = false, updatable = false)
	})
	private StNodoProceso stNodoProceso;
}
