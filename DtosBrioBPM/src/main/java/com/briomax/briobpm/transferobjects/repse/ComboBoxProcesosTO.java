/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.transferobjects.repse;

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
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Mar 10, 2020 12:22:52 PM Modificaciones:
 * @since JDK 1.8
 */
@Schema(title = "ComboBoxTO", description = "Opciones para ComboBoxs")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ComboBoxProcesosTO implements ITransferObject {

	/** Serial Version UID. */
	private static final long serialVersionUID = 8648753675829842698L;
	
	/** El atributo o variable id. */
	@Schema(name = "id", description = "Id de la Opcion.", implementation = String.class)
	private String id;
	
	/** El atributo o variable descripcion. */
	@Schema(name = "descripcion", description = "Descripcion de la Opcion.", implementation = String.class)
	private String descripcion;
	
	@Schema(name = "situacionEjecucion", description = "Descripcion de la Opcion.", implementation = boolean.class)
	private boolean situacionEjecucion;
	
	@Schema(name = "rfc", description = "Descripcion del rfc.", implementation = String.class)
	private String rfc;
	
	@Schema(name = "razonSocial", description = "Descripcion de la razon Social.", implementation = String.class)
	private String razonSocial;
	
	@Schema(name = "personalidad", description = "Descripcion de la personalidad.", implementation = String.class)
	private String personalidad;
	
	@Schema(name = "objetoContrato", description = "Descripcion de la Opcion.", implementation = String.class)
	private String objetoContrato;
	
	@Schema(name = "periodoAl", description = "Descripcion de la periodoAl.", implementation = String.class)
	private String periodoAl;
	
	@Schema(name = "periodoDel", description = "Descripcion de la periodoDel.", implementation = String.class)
	private String periodoDel;
	
	@Schema(name = "nuevoPeriodo", description = "Descripcion de la nuevoPeriodo.", implementation = String.class)
	private String nuevoPeriodo;
	
	@Schema(name = "razon", description = "Descripcion de la razon.", implementation = String.class)
	private String razon;

	@Schema(name = "justificacion", description = "Descripcion de la justificacion.", implementation = String.class)
	private String justificacion;
}
