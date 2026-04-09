/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.transferobjects;

import java.util.Date;

import com.briomax.briobpm.transferobjects.core.ITransferObject;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/**
 * El objetivo de la Class CargaTrabajadoresTO.java es ...
 * @author Sara Ventura
 * @version 1.0 Fecha de creacion Mar 10, 2020 12:22:52 PM Modificaciones:
 * @since JDK 1.8
 */
@Schema(title = "CargaTrabajadoresTO", description = "trabajadores Repse")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CargaTrabajadoresTO implements ITransferObject {

	/** Serial Version UID. */
	private static final long serialVersionUID = 8648753675829842698L;
	
	/** El atributo o variable . */
	@Schema(name = "rfc", description = " del rfc.", implementation = String.class)
	private String rfc;
	
	/** El atributo o variable . */
	@Schema(name = "contrato", description = "Numero de Contrato.", implementation = String.class)
	private String contrato;
	
	/** El atributo o variable . */
	@Schema(name = "curp", description = " de la curp.", implementation = String.class)
	private String curp;
	
	/** El atributo o variable . */
	@Schema(name = "nombreTrabajador", description = "Nombre Trabajador.", implementation = String.class)
	private String nombreTrabajador;
	
	@Schema(name = "rfcTrabajador", description = " del rfc.", implementation = String.class)
	private String rfcTrabajador;

	/** El atributo o variable . */
	@Schema(name = "salarioBaseContrato", description = "Salario Base.", implementation = String.class)
	private double salarioBase;
	
	/** El atributo o variable . */
	@Schema(name = "salarioBase", description = "Salario Base.", implementation = String.class)
	private String salario;
	
	/** El atributo o variable . */
	@Schema(name = "seguroSocial", description = "Seguro Social.", implementation = String.class)
	private String seguroSocial;

	/** El atributo o variable . */
	@Schema(name = "fechaIni", description = "Fecha Inicial Contrato.", implementation = String.class)
	private Date fechaIni;

	/** El atributo o variable . */
	@Schema(name = "fechaFin", description = "Fecha Final Contrato.", implementation = String.class)
	private Date fechaFin;
	
	/** El atributo o variable . */
	@Schema(name = "fechaInicial", description = "Fecha Inicial Contrato.", implementation = String.class)
	private String fechaInicial;

	/** El atributo o variable . */
	@Schema(name = "fechaFinal", description = "Fecha Final Contrato.", implementation = String.class)
	private String fechaFinal;

	/** El atributo o variable . */
	@Schema(name = "desError", description = "Descripción del error.", implementation = String.class)
	private String desError;
}
