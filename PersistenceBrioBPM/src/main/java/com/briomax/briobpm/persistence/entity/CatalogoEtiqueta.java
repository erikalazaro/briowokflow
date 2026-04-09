package com.briomax.briobpm.persistence.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * El objetivo de la Class CatalogoEtiqueta.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Abr 10, 2024 12:08:29 PM Modificaciones:
 * @since JDK 1.8
 */
@Entity
@Table(name = "CATALOGO_ETIQUETA")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CatalogoEtiqueta implements IEntity {

	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;
	
	/** ID. */
	@EmbeddedId
	private CatalogoEtiquetaPK id;

	/** ETIQUETA. */
    @Column(name = "ETIQUETA", length = 100)
    private String etiqueta;

    /** ORDEN_PRESENTACION. */
    @Column(name = "ORDEN_PRESENTACION", precision = 5, scale = 0)
    private Integer ordenPresentacion;
}
