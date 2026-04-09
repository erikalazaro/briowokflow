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
@Table(name = "CR_AVISO_SISUB")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CrAvisoSISUB implements IEntity {

	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;
	
	/** El atributo o variable id. */
	@EmbeddedId
	private CrAvisoSISUBPK id;
	
	/** El atributo o variable. */	
	@Column(name = "NOMBRE_DOCUMENTO")
	private String nombreDocumento;	
	
	/** El atributo o variable. */	
	@Column(name = "FECHA_DOCUMENTO")
	private Date fechaDocumento;

	/** El atributo o variable. */
	@Column(name = "RFC")
	private String rfc;
	
	/** El atributo o variable. */
	@Column(name = "RAZON_SOCIAL")
	private String razonSocial;
	
	/** El atributo o variable. */
	@Column(name = "NUMERO_REPSE")
	private String numeroRepse;
	
	/** El atributo o variable. */
	@Column(name = "CUATRIMESTRE")
	private String cuatrimestre;

	/** El atributo o variable. */	
	@Column(name = "SITUACION_CARGA")
	private String situacionCarga;
}