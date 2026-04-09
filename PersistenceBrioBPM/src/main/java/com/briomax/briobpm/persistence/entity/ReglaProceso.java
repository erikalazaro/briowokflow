package com.briomax.briobpm.persistence.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "REGLA_PROCESO")
@Data
@Builder
public class ReglaProceso implements IEntity {
	
	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private ReglaProcesoPK id;
	

	/** El atributo o variable cve Proceso*/
	@Column(name = "DESCRIPCION", nullable = false, length = 100)
	private String descripcion;
	
	/** El atributo o variable version*/
	@Column(name = "TIPO_EXPRESION", nullable = false, length = 20)
	private String tipoExpresion;
	
	/** El atributo o variable cve Regla*/
	@Column(name = "EXPRESION", nullable = false, length = 1500)
	private String expresion;
	
}
