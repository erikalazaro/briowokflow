/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */
package com.briomax.briobpm.persistence.entity.namedquery;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.briomax.briobpm.persistence.entity.IEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * El objetivo de la Class MenuAreaTrabajo.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion May 18, 2020 8:50:44 AM Modificaciones:
 * @since JDK 1.8
 */
@Entity 
@Getter
@Setter
@ToString
public class DatoTrabajador implements IEntity {

	/** Serial Version UID. */
	private static final long serialVersionUID = -1892868004251494539L;


	@EmbeddedId
	private DatoTrabajadorPK id;

	@Column(name = "VALOR_ALFANUMERICO")
	private String valorAlfamerico;
	
	/** El atributo o variable nombre Trabajador. */	
	//@Column(name = "NOMBRE_TRABAJADOR")
	//private String nombreTrabajador;
	

	


	/** El atributo o variable salario Base. */
	//@Column(name = "SALARIO_BASE_COTIZACION")
	//private String salarioBase;
	
	/** El atributo o variable seguro Social. */
	//@Column(name = "NUMERO_SEGURO_SOCIAL")
	//private String seguroSocial;

	/** El atributo o variable fecha Inicial. */
	//@Column(name = "FECHA_INICIAL_CONTRATO")
	//private Date fechaInicial;

	/** El atributo o variable fecha Final. */
	//@Column(name = "FECHA_FINAL_CONTRATO")
	//private Date fechaFinal;
	

}
