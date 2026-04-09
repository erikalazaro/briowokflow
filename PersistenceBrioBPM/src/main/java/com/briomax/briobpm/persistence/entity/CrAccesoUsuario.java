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
 * El objetivo de la Class CrAccesoUsuario.java es ...
 * @author Sara Ventura
 * @version 1.0 Fecha de creacion Nov 25, 2025 12:08:29 PM Modificaciones:
 * @since JDK 1.8
 */
@Entity
@Table(name = "CR_ACCESO_USUARIO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CrAccesoUsuario implements IEntity {

	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;
	
	/** El atributo o variable id. */
	@EmbeddedId
	private CrAccesoUsuarioPK id;
	
	/** El atributo o variable Situacion Carga. */
	@Column(name = "USUARIO_CONTRATANTE", nullable = false, length = 500)
	private String usuarioContratante;
	
	
	/** El atributo o variable entidad. */
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name = "CVE_ENTIDAD", referencedColumnName = "CVE_ENTIDAD", insertable = false, updatable = false),
		@JoinColumn(name = "CVE_USUARIO", referencedColumnName = "CVE_USUARIO", insertable = false, updatable = false)
		})
	private Usuario usuario;
}