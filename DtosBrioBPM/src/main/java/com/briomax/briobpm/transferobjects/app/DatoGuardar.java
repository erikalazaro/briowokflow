/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.transferobjects.app;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * El objetivo de la Class Seccion.java es ...
 * @author Sara Venruta
 * @version 1.0 Fecha de creacion 21/02/2024 PM Modificaciones:
 * @since JDK 1.8
 */
@Schema(title = "Dato Guardar.", description = "Dato Guardar Valor.")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DatoGuardar implements Serializable {

	/** Serial Version UID. */
	private static final long serialVersionUID = -940698310781150899L;
	
	/** El atributo o variable cve nodo. */
	@Schema(name = "seccionVariable", description = "Contiene la clave de seccion concatenada con la clave de variable.", example = "CONF_LLEGA_SITI-APP|VPRO_01_CONF_LLEG", implementation = String.class)
	private String seccionVariable;
	
	/** El atributo o variable tipo. */
	@Schema(name = "numeroDato", description = "Numero de Dato.", example = "1")
	private Integer numeroDato;

	@Schema(name = "tipoControl", description = "TB-TEXBOX, CB-COMBOBOX, TX-TEXTAREA", example = "TB", implementation = String.class)
	private String tipoControl;
	
	/** El atributo o variable title. */
	@Schema(name = "valor", description = "texto o idDato1|idDato2|idDato3", example = "", implementation = String.class)
	private Object valor;

}
