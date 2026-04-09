/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.transferobjects;

import com.briomax.briobpm.transferobjects.core.ITransferObject;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/**
 * El objetivo de la Class ComboBoxTO.java es ...
 * @author Sara Ventura
 * @version 1.0 Fecha de creacion Mar 10, 2020 12:22:52 PM Modificaciones:
 * @since JDK 1.8
 */
@Schema(title = "TrabajadoresTO", description = "trabajadores Repse")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrabajadoresTO implements ITransferObject {

	/** Serial Version UID. */
	private static final long serialVersionUID = 8648753675829842698L;

	/** El atributo o variable . */
	@Schema(name = "instancia", description = "Instancia.", implementation = String.class)
	private String instancia;
	
	/** El atributo o variable . */
	@Schema(name = "ocurrencia", description = "Ocurrencia.", implementation = String.class)
	private Integer ocurrencia;

	/** El atributo o variable . */
	@Schema(name = "rfc", description = " del rfc.", implementation = String.class)
	private String rfc;
	
	/** El atributo o variable . */
	@Schema(name = "numContrato", description = "Numero de Contrato.", implementation = String.class)
	private String numContrato;
	
	/** El atributo o variable . */
	@Schema(name = "curp", description = " de la curp.", implementation = String.class)
	private String curp;
	
	/** El atributo o variable . */
	@Schema(name = "rfcTrabajador", description = " del rfc.", implementation = String.class)
	private String rfcTrabajador;
	
	/** El atributo o variable . */
	@Schema(name = "nombreTrabajador", description = "Nombre Trabajador.", implementation = String.class)
	private String nombreTrabajador;

	/** El atributo o variable . */
	@Schema(name = "salarioBase", description = "Salario Base.", implementation = String.class)
	private String salarioBase;
	
	/** El atributo o variable . */
	@Schema(name = "seguroSocial", description = "Seguro Social.", implementation = String.class)
	private String seguroSocial;

	/** El atributo o variable . */
	@Schema(name = "fechaInicial", description = "Fecha Inicial Contrato.", implementation = String.class)
	private String fechaInicial;

	/** El atributo o variable . */
	@Schema(name = "fechaFinal", description = "Fecha Final Contrato.", implementation = String.class)
	private String fechaFinal;

}
