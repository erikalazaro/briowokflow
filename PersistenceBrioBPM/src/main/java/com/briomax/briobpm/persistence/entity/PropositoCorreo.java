package com.briomax.briobpm.persistence.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;


/**
 * El objetivo de la Class CorreoProceso.java es ...
 * @author Sara Ventura
 * @version 1.0 Fecha de creacion Dic 22, 2023 12:08:29 PM Modificaciones:
 * @since JDK 1.8
 */
@Entity
@Table(name = "PROPOSITO_CORREO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {})
@EqualsAndHashCode(callSuper = false, exclude = {})
public class PropositoCorreo implements IEntity {

	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PropositoCorreoPK id;

    @Column(name = "DESCRIPCION", nullable = false, length = 100)
    private String descripcion;

    @Column(name = "ASUNTO", nullable = false, length = 200)
    private String asunto;

    @Column(name = "CUERPO_CORREO", nullable = false, length = 4000)
    private String cuerpoCorreo;

    
}
