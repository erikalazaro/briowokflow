package com.briomax.briobpm.persistence.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * El objetivo de la Class CrPagoBancario.java es ...
 * @author Sara Ventura
 * @version 1.0 Fecha de creacion September 04, 2024 12:08:29 PM Modificaciones:
 * @since JDK 1.8
 */
@Entity
@Table(name = "CR_REGISTRO_OBRA")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CrRegistroObra implements IEntity {

	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;
	
	/** El atributo o variable id. */
	@EmbeddedId
	private CrRegistroObraPK id;
	
	/** El atributo o variable. */	
	@Column(name = "NOMBRE_DOCUMENTO")
	private String nombreDocumento;	
	
	/** El atributo o variable. */	
	@Column(name = "RAZON_SOCIAL")
	private String razonSocial;

	/** El atributo o variable. */
	@Column(name = "REGISTRO_PATRONAL")
	private String registroPatronal;

	/** El atributo o variable. */	
	@Column(name = "NUMERO_REPSE")
	private String numeroRepse;
	
	/** El atributo o variable. */	
	@Column(name = "REGISTRO_OBRA")
	private String registroObra;
	
	/** El atributo o variable. */	
	@Column(name = "MONTO_OBRA")
	private String montoObra;
	
	/** El atributo o variable. */	
	@Column(name = "UBICACION_OBRA")
	private String ubicacionObra;	

	/** El atributo o variable. */	
	@Column(name = "FECHA_REGISTRO")
	private Date fechaRegistro;
	
	/** El atributo o variable. */	
	@Column(name = "SITUACION_CARGA")
	private String situacionCarga;
}