/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.transferobjects;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;


/**
 * El objetivo de la Class ValueType.java es ...
 * @author Rigoberto Olvera C.
 * @version 1.0 Fecha de creacion 29/01/2020 11:41:23 PM Modificaciones:
 * @since JDK 1.8
 */
@Schema(description = "ValueType", title = "ValueType")
@Data
@Builder
public class ValueType implements Serializable {

	/** serial Version UID. */
	private static final long serialVersionUID = 3593261886846685341L;

	/** The type. */
	@NonNull
	@Schema(name = "type", description = "Type", example = "String", implementation = String.class)
	private String type;

	/** The value. */
	@NonNull
	@Schema(name = "value", description = "Value", example = "Value", implementation = String.class)
	private Object value;

}
