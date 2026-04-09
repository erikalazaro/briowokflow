
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
 * El objetivo de la Class StNodoSiguienteTest.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Feb 09, 2024 3:13:43 PM :
 * @since JDK 1.8
 */
@Entity
@Table(name = "TRADUCCION")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false, exclude = {"idioma"})
public class Traduccion implements IEntity {

	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;

	/** El atributo o variable id. */
	@EmbeddedId
	private TraduccionPK id;

	/** El atributo o variable palabra Traducida. */
	@Column(name = "PALABRA_TRADUCIDA", nullable = false, length = 500)
	private String palabraTraducida;

	@ManyToOne
	@JoinColumn(name = "CVE_IDIOMA", referencedColumnName = "CVE_IDIOMA", insertable = false, updatable = false)
	private Idioma idioma;

}
