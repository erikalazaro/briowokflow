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
 * El objetivo de la Class CrConsultaRepse.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion May 09, 2024 12:08:29 PM Modificaciones:
 * @since JDK 1.8
 */
@Entity
@Table(name = "CR_TRABAJADOR_HISTORICO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CrTrabajadorHistorico implements IEntity {

	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;
	
	/** El atributo o variable id. */
	@EmbeddedId
	private CrTrabajadorHistoricoPK id;
	
	/** El atributo o variable nombre Trabajador. */	
	@Column(name = "NOMBRE_TRABAJADOR")
	private String nombreTrabajador;
	
	/** El atributo o variable rfc Trabajador. */	
	@Column(name = "RFC_TRABAJADOR")
	private String rfcTrabajador;
	
	/** El atributo o variable salario Base. */
	//@Column(name = "SALARIO_BASE_COTIZACION")
	//private String salarioBase;
	
	/** El atributo o variable seguro Social. */
	@Column(name = "NUMERO_SEGURIDAD_SOCIAL")
	private String seguroSocial;

	/** El atributo o variable fecha Inicial. */
	//(name = "FECHA_INICIAL_CONTRATO")
	//private Date fechaInicial;

	/** El atributo o variable fecha Final. */
	//@Column(name = "FECHA_FINAL_CONTRATO")
	//private Date fechaFinal;
}