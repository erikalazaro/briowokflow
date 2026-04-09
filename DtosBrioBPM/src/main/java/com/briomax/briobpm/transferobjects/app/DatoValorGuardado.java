/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.transferobjects.app;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * El objetivo de la Class Seccion.java es ...
 * 
 * @author Sara Venruta
 * @version 1.0 Fecha de creacion 21/02/2024 PM Modificaciones:
 * @since JDK 1.8
 */
@Schema(title = "DatoValor.", description = "Dato Valor.")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DatoValorGuardado implements Serializable {

	/** Serial Version UID. */
	private static final long serialVersionUID = -940698310781150899L;

	/** El atributo o variable title. */
	@Schema(name = "tipoControl", description = "indica su tipo de control del componente", example = "TB", implementation = String.class)
	private String tipoControl;

	/** El atributo o variable order. */
	@Schema(name = "seccionVariable", description = "indica la clave de seccion concatenada con la clave de la variable", example = "DATO_SITI-APP|VPRO_01_NOM_CLIE", implementation = Integer.class)
	private String seccionVariable;

	/** El atributo o variable numeroDato. */
	private Integer numeroDato;
	
	private String[] value;

}
