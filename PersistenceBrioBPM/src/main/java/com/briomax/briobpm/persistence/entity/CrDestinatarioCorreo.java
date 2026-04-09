package com.briomax.briobpm.persistence.entity;

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
 * El objetivo de la Class CrDestinatarioCorreo.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Jul 22, 2024 12:08:29 PM Modificaciones:
 * @since JDK 1.8
 */
@Entity
@Table(name = "CR_DESTINATARIO_CORREO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CrDestinatarioCorreo implements IEntity {

	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;
	
	/** El atributo o variable id. */
	@EmbeddedId
	private CrDestinatarioCorreoPK id;

	@Column(name = "TIPO_DESTINATARIO", nullable = false, length = 20)
    private String tipoDestinatario;

	@Column(name = "CVE_USUARIO", length = 30)
	private String cveUsuario;
	
	/** El atributo o variable cve idioma. */
	@Column(name = "DIRECCION_CORREO", length = 400)
	private String direccionCorreo;

	/** El atributo o variable cve idioma. */
	@Column(name = "RFC_CONTRATISTA", length = 20)
	private String rfcContratista;
	
	/** El atributo o variable cve idioma. */
	@Column(name = "NUMERO_CONTRATO", length = 50)
	private String numeroContrato;
	
	/*@ManyToOne
	@JoinColumns({
	    @JoinColumn(name="CVE_ENTIDAD", referencedColumnName = "CVE_ENTIDAD", insertable = false, updatable = false),
	    @JoinColumn(name="CVE_LOCALIDAD", referencedColumnName = "CVE_LOCALIDAD", insertable = false, updatable = false),
	    @JoinColumn(name="CVE_IDIOMA", referencedColumnName = "CVE_IDIOMA", insertable = false, updatable = false),
	    @JoinColumn(name="CVE_PROCESO_PERIODICO", referencedColumnName = "CVE_PROCESO_PERIODICO", insertable = false, updatable = false),
	    @JoinColumn(name="SECUENCIA_CORREO", referencedColumnName = "SECUENCIA_CORREO", insertable = false, updatable = false),
	    @JoinColumn(name="RFC_CONTRATISTA", referencedColumnName = "RFC", insertable = false, updatable = false),
	    @JoinColumn(name="NUMERO_CONTRATO", referencedColumnName = "CONTRATO", insertable = false, updatable = false)
	    
	})
	private CrNotificacion crNotificacion;*/
	
}
