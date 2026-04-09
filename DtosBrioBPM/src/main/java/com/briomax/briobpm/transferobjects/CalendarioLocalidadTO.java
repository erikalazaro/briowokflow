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
 * El objetivo de la Class CalendarioLocalidadTO.java es ...
 * @author Sara Ventura
 * @version 1.0 Fecha de creacion Dic 11, 2023 Modificaciones:
 * @since JDK 1.8
 */
@Schema(title = "CalendarioLocalidadTO", description = "Opciones para ComboBoxs")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CalendarioLocalidadTO implements ITransferObject {

	/** Serial Version UID. */
	private static final long serialVersionUID = 8648753675829842698L;
	
	/** El atributo o variable id. */
	@Schema(name = "diaSemana", description = "Id de la Opcion.", implementation = Integer.class)
	private Integer diaSemana;
	
	/** El atributo o variable Habil. */
	@Schema(name = "esHabil", description = "SI/NO", implementation = String.class)
	private String esHabil;

	/** El atributo o variable hora Inicio dia. */
	@Schema(name = "horaInicioDia", description = "dd/mm/yyyy", implementation = Date.class)
	private String horaInicioDia;
	
	/** El atributo o variable hora Fin dia. */
	@Schema(name = "horaFinDia", description = "dd/mm/yyyy", implementation = Date.class)
	private String horaFinDia;
}
