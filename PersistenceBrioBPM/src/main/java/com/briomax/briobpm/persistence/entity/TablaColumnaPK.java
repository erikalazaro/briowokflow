package com.briomax.briobpm.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * El objetivo de la Class TablaColumnaPK.java es ...
 * @author Erika Vázquez
 * @version 1.0 Fecha de creacion Jun 25, 2020 12:07:17 PM Modificaciones:
 * @since JDK 1.8
 */
@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TablaColumnaPK implements IPrimaryKey {
	@Column(name = "NOMBRE_TABLA", length = 30, nullable = false)
	private String nombreTabla;
	
	@Column(name = "NOMBRE_COLUMNA", length = 30, nullable = false)
    private String nombreColumna;
    
}
