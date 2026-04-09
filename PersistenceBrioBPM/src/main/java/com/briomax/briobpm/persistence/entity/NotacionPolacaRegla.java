package com.briomax.briobpm.persistence.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * El objetivo de la Class NotacionPolacaReglaPK.java es ...
 * @author Pamela Rodriguez
 * @version 1.0 Fecha de creacion sep 10, 2024 14:19:29 PM Modificaciones:
 * @since JDK 11
 */
@Entity
@Table(name = "NOTACION_POLACA_REGLA")
@Data
@Builder
@EqualsAndHashCode(callSuper = false, exclude = {})
public class NotacionPolacaRegla implements IEntity {

	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private NotacionPolacaReglaPK id;
	
	/** El atributo o variable descripcion. */
	@Column(name = "NOTACION_POLACA", nullable = false, length = 4000)
	private String notacionPolaca;
	
	
}
