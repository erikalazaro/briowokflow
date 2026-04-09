package com.briomax.briobpm.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * El objetivo de la Class Idioma.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Abr 29, 2024 9:18:05 AM Modificaciones:
 * @since JDK 1.8
 */
@Entity
@Table(name = "AP_MODULO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApModulo implements IEntity {/**

	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;

	/** El atributo o variable cve idioma. */
	@Id
	@Column(name = "ID_MODULO", nullable = false, length = 40)
	private String idModulo;

	/** El atributo o variable descripcion. */
	@Column(name = "DESCRIPCION_MODULO", length = 100)
	private String descripcionModulo;
}
