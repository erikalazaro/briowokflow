package com.briomax.briobpm.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * El objetivo de la Class CatalogoEtiquetaPK.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Abr 10, 2024 12:08:29 PM Modificaciones:
 * @since JDK 1.8
 */

@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CatalogoEtiquetaPK implements IPrimaryKey {

	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "NOMBRE_DATO", nullable = false, length = 60)
    private String nombreDato;

    @Column(name = "VALOR_ALFANUMERICO", nullable = false, length = 100)
    private String valorAlfanumerico;

}
