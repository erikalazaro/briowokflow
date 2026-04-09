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

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * El objetivo de la Class Chart.java es ...
 * 
 * @author Sara Ventura
 * @version 1.0 Fecha de creacion 28/05/2024 Modificaciones:
 * @since JDK 1.8
 */
@Schema(title = "DatoActividad.", description = "Datos de las Actividades.")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DetDatoActividad implements Serializable {

	/** Serial Version UID. */
	private static final long serialVersionUID = 5753517089240052500L;

	/** El atributo o variable labels. */
	@Schema(name = "seccionVariable", description = "Concatenacion de la clave de seccion y la clave de variable", example = "DATO_SITI-APP|VPRO_01_NOM_TIENDA", implementation = String.class)
	private String seccionVariable;

	/** El atributo o variable tipo. */
	@Schema(name = "numeroDato", description = "Numero de Dato.", example = "01", implementation = String.class)
	private int numeroDato;

	/** El atributo o variable labels. */
	@Schema(name = "tipoDato", description = "A-Alfanumerico, E-Entero, D-Decimal, F-Fecha, C-Documento.", example = "A", implementation = String.class)
	private String tipoDato;

	/** El atributo o variable labels. */
	@Schema(name = "alineacion", description = "I-Izquierda, C-Centro, D-Derecha.", example = "I", implementation = String.class)
	private String alineacion;

	/** El atributo o variable labels. */
	@Schema(name = "interaccion", description = "E-Entrada, S-Salida, ES-Entrada Salida", example = "E", implementation = String.class)
	private String interaccion;

	/** El atributo o variable labels. */
	@Schema(name = "requerido", description = "R-Dato requerido, O-Dato opcional, N-No aplica, cuando el dato es de S-Salida.", example = "R", implementation = String.class)
	private String requerido;

	/** El atributo o variable labels. */
	@Schema(name = "tipoControl", description = "TB-Textbox, CB-Combobox, LB-Listbox, RB-RadioButton, CX-Checkbox, CA-Calendario", example = "TB", implementation = String.class)
	private String tipoControl;

	/** El atributo o variable labels. */
	@Schema(name = "etiqueta", description = "Etiqueta.", example = "etiqueta texto", implementation = String.class)
	private String etiqueta;
	
	//private byte[] documentoValor;

	/** El atributo o variable labels. */
	@ArraySchema(schema = @Schema(name = "listaValores", description = "Lista de valores"))
	private List<DatoValor> listaValores;

}
