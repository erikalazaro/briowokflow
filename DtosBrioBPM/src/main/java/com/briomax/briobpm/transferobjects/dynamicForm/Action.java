/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados. Este software contiene informacion
 * confidencial propiedad de Briomax Consulting por lo cual no puede ser reproducido, distribuido o alterado sin el
 * consentimiento previo de Briomax Consulting.
 */

package com.briomax.briobpm.transferobjects.dynamicForm;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * El objetivo de la Class Action.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion May 15, 2020 2:18:52 PM Modificaciones:
 * @since JDK 1.8
 */
@Schema(description = "Action", title = "Acciones.")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Action implements Serializable {

	/** Serial Version UID. */
	private static final long serialVersionUID = 1434283142213596600L;

	/** El atributo o variable action. */
	@Schema(name = "action", description = "Accion a Ejecutar,", example = "EJECUTAR_ACTIVIDAD")
	private String action;

	/** El atributo o variable label. */
	@Schema(name = "label", description = "Etiqueta a mostrar.", example = "Ejecutar Actividad")
	private String label;

}
