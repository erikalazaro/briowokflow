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
 * El objetivo de la Class ComboBoxTO.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Mar 10, 2020 12:22:52 PM Modificaciones:
 * @since JDK 1.8
 */
@Schema(title = "ComboBoxArrayTO", description = "Opciones para ComboBox con arreglo")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ComboBoxArrayTO implements ITransferObject {

	/** Serial Version UID. */
	private static final long serialVersionUID = 8648753675829842698L;
	
	/** El atributo o variable id. */
	@Schema(name = "id", description = "Id de la Opcion.", implementation = String.class)
	private String id;
	
	/** El atributo o variable descripcion. */
	@Schema(name = "descripcion", description = "Descripcion de la Opcion.", implementation = List.class)
	private List <String> descripcion;

}
