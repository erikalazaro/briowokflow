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
 * El objetivo de la Class CorreoProceso.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Jul 22, 2024 12:08:29 PM Modificaciones:
 * @since JDK 1.8
 */
@Entity
@Table(name = "NOTIFICACION")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Notificacion implements IEntity {

	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;
	@Id	
    @Column(name = "SECUENCIA_CORREO", nullable = false, precision = 5, scale = 0)
    private Integer secuenciaCorreo;

    @Column(name = "PARA", nullable = false, length = 200)
    private String para;

    @Column(name = "CCP", nullable = false, length = 200)
    private String ccp;

    @Column(name = "ASUNTO", nullable = false, length = 200)
    private String asunto;

    @Column(name = "CUERPO", nullable = false, length = 4000)
    private String cuerpo;
    
}
