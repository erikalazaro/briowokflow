/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.transferobjects;

import java.io.Serializable;
import java.util.List;

import com.briomax.briobpm.transferobjects.in.ActividadTO;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * El objetivo de la Class SaveSectionTO.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Mar 4, 2020 4:26:09 PM Modificaciones:
 * @since JDK 1.8
 */
@Schema(description = "SaveSectionTO", title = "Guardar Seccion del Formulario.")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaveSectionTO implements Serializable {

	/** Serial Version UID. */
	private static final long serialVersionUID = 8087198704135119766L;

	/** El atributo o variable activity. */
	@Schema(name = "activity", description = "Datos de la Actividad", implementation = ActividadTO.class)
	private ActividadTO activity;

	/** El atributo o variable data. */
	@ArraySchema(schema = @Schema(name = "data", description = "", implementation = DataSectionTO.class))
	private List<DataSectionTO> data;

}
