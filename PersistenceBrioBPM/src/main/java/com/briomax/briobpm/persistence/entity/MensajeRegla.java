package com.briomax.briobpm.persistence.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "MENSAJE_REGLA")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false, exclude = {})
public class MensajeRegla implements IEntity {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private MensajeReglaPK id;
	
	@Column(name = "NUMERO_MENSAJE", nullable = false,  precision = 5, scale = 0)
	private Integer numeroMensaje;
	
	@Column(name = "MENSAJE", nullable = false, length = 1500)
	private String mensaje;
  
	@ManyToOne
	@JoinColumn(name = "CVE_IDIOMA", referencedColumnName = "CVE_IDIOMA", insertable = false, updatable = false)
	private Idioma idioma;
}
