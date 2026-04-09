package com.briomax.briobpm.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * El objetivo de la Class CorreoProceso.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Febrero 06, 2024 12:08:29 PM Modificaciones:
 * @since JDK 1.8
 */
@Entity
@Table(name = "EVENTO_CORREO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {})
@EqualsAndHashCode(callSuper = false, exclude = {})
public class EventoCorreo implements IEntity {
	
	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name = "CVE_EVENTO_CORREO", nullable = false, length = 40)
    private String cveEventoCorreo;

    @Column(name = "DESCRIPCION", nullable = false, length = 100)
    private String secuenciaCorreo;

}
