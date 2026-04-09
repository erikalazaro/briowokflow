
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

/**
 * El objetivo de la Class LocalidadEntidad.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Feb 06, 2024 1:29:22 PM Modificaciones:
 * @since JDK 1.8
 */
@Entity
@Table(name = "MENSAJE_IDIOMA")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false, exclude = {"idioma"})
public class MensajeIdioma implements IEntity {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private MensajeIdiomaPK id;

	@Column(name = "MENSAJE", nullable = false, length = 1500)
	private String mensaje;

	@ManyToOne
	@JoinColumn(name = "CVE_IDIOMA", referencedColumnName = "CVE_IDIOMA", insertable = false, updatable = false)
	private Idioma idioma;

}
