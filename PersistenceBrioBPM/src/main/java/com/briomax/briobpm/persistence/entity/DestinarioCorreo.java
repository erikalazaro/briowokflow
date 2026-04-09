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
 * El objetivo de la Class DestinarioCorreo.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Ene 08, 2024 12:08:29 PM Modificaciones:
 * @since JDK 1.8
 */
@Entity
@Table(name = "DESTINATARIO_CORREO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DestinarioCorreo implements IEntity {
	
	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;
	
	/** El atributo o variable id*/
	@EmbeddedId
	private DestinarioCorreoPK id;

    @Column(name = "TIPO_DESTINATARIO", nullable = false, length = 20)
    private String tipoDestinatario;

    @Column(name = "USO_CVE_USUARIO_CREADOR", length = 4)
    private String usoCveUsuarioCreador;

    @Column(name = "CVE_ROL", length = 40)
    private String cveRol;

    @Column(name = "CVE_USUARIO", length = 30)
    private String cveUsuario;
    
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="CVE_ENTIDAD", referencedColumnName = "CVE_ENTIDAD", insertable = false, updatable = false),
		@JoinColumn(name="CVE_PROCESO", referencedColumnName = "CVE_PROCESO", insertable = false, updatable = false),
		@JoinColumn(name="VERSION", referencedColumnName = "VERSION", insertable = false, updatable = false),
		@JoinColumn(name="CVE_EVENTO_CORREO", referencedColumnName = "CVE_EVENTO_CORREO", insertable = false, updatable = false),
		@JoinColumn(name="SECUENCIA_CORREO", referencedColumnName = "SECUENCIA_CORREO", insertable = false, updatable = false)
		})
	private CorreoProceso correoProceso;
}
