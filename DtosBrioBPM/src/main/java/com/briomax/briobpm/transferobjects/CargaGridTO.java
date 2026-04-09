/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.transferobjects;

import java.util.List;

import com.briomax.briobpm.transferobjects.core.ITransferObject;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/**
 * El objetivo de la Class CargaGridTO.java es ...
 * @author Sara Ventura
 * @version 1.0 Fecha de creacion Mar 10, 2020 12:22:52 PM Modificaciones:
 * @since JDK 1.8
 */
@Schema(title = "CargaGridTO", description = "trabajadores Repse")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CargaGridTO implements ITransferObject {

	/** Serial Version UID. */
	private static final long serialVersionUID = 8648753675829842698L;

	/** El atributo o variable . */
	@Schema(name = "estatus", description = "Estatus de la carga.", implementation = String.class)
	private String estatus;
	
	/** El atributo o variable . */
	@Schema(name = "descripcion", description = "Descripcion de la carga.", implementation = String.class)
	private String descripcion;

	/** El atributo o variable . */
	@Schema(name = "rfc", description = "Clave rfc .", implementation = String.class)
	private String rfc;
	
	/** El atributo o variable . */
	@Schema(name = "contrato", description = "Clave contrato .", implementation = String.class)
	private String contrato;
	
	/** El atributo o variable . */
	@Schema(name = "proceso", description = "Clave Proceso .", implementation = String.class)
	private String proceso;
	
	/** El atributo o variable . */
	@Schema(name = "instancia", description = "Clave Instancia.", implementation = String.class)
	private String instancia;

	/** El atributo o variable . */
	@Schema(name = "grid", description = "Lista de trabajadores.", implementation = String.class)
	private List<CargaTrabajadoresTO> grid;
}
