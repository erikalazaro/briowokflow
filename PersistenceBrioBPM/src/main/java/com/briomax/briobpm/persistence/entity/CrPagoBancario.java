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
 * @version 1.0 Fecha de creacion September 03, 2024 12:08:29 PM Modificaciones:
 * @since JDK 1.8
 */
@Entity
@Table(name = "CR_PAGO_BANCARIO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CrPagoBancario implements IEntity {

	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;
	
	/** El atributo o variable id. */
	@EmbeddedId
	private CrPagoBancarioPK id;
	
	/** El atributo o variable. */	
	@Column(name = "NOMBRE_DOCUMENTO")
	private String nombreDocumento;	
	
	/** El atributo o variable. */	
	@Column(name = "LINEA_CAPTURA")
	private String lineaCaptura;

	/** El atributo o variable. */
	@Column(name = "FECHA_PAGO")
	private Date fechaPago;

	/** El atributo o variable. */	
	@Column(name = "SITUACION_CARGA")
	private String situacionCarga;
}