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
 * El objetivo de la Class FoliadorMensaje.java es ...
 * @authorAlexis Zamora
 * @version 1.0 Fecha de creacion Dic 14, 2023 12:24:43 PM Modificaciones:
 * @since JDK 1.8
 */
@Entity
@Table(name = "FOLIADOR_MENSAJE")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FoliadorMensaje implements IEntity {

	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;
	
	/** El atributo o variable descripcion. */
	@Id
	@Column(name = "FOLIO_MENSAJE", nullable = false, precision = 9, scale = 0)
	private Integer folioMensaje;

}
