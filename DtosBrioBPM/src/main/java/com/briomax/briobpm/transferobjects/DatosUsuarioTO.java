/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.transferobjects;

import java.io.Serializable;

import com.briomax.briobpm.transferobjects.core.ITransferObject;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * El objetivo de la Class DatosUsuarioTO.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Feb 25, 2020 12:32:13 PM Modificaciones:
 * @since JDK 1.8
 */
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = {"datosAutenticacion"})
@Schema(title = "DatosUsuarioTO", description = "Datos del Usuario.")
public class DatosUsuarioTO implements ITransferObject, Serializable {

	/** serial Version UID. */
	private static final long serialVersionUID = -7847896486923486378L;

	/** El atributo o variable generales. */
	@Schema(name = "generales", description = "Datos Generales.", implementation = DatosGeneralesUsuarioTO.class)
	DatosGeneralesUsuarioTO generales;

	/** El atributo o variable adicionales. */
	@Schema(name = "adicionales", description = "Datos Adicionales", implementation = DatosAdicionalesUsuarioTO.class)
	DatosAdicionalesUsuarioTO adicionales;

	/** El atributo o variable datos autenticacion. */
	@JsonIgnore
	@Schema(name = "datosAutenticacion", description = "Datos de Autenticacion.",
			implementation = DatosAutenticacionTO.class)
	DatosAutenticacionTO datosAutenticacion;

}
