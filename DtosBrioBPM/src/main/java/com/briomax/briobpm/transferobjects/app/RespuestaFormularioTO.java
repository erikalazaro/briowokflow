/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.transferobjects.app;

import java.io.Serializable;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * El objetivo de la Class RespuestaFormularioTO.java es ...
 * @author Alexis Zamoraw
 * @version 1.0 Fecha de creacion 24/01/2025 Modificaciones:
 * @since JDK 1.8
 */
@Schema(title = "RespuestaFormularioTO.", description = "Respuesta Formulario.")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RespuestaFormularioTO implements Serializable {

	/** Serial Version UID. */
	private static final long serialVersionUID = 5753517089240052500L;

	/** El atributo o variable secccionVariable. */
	String secccionVariable;
	
	/** El atributo o variable numeroDato. */
	Integer numeroDato;
	
	/** El atributo o variable tipoControl. */
	String tipoControl;
	
	/** El atributo o variable value. */
	String value;

}
