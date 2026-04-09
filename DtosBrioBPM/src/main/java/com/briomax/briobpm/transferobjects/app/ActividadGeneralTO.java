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
 * El objetivo de la Class ActividadAutorizarTO.java es ...
 * @author Sara Ventura
 * @version 1.0 Fecha de creacion 26/03/2024
 * @since JDK 1.8
 */
@Schema(title = "ActividadGeneralTO.", description = "Actividad en General.")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActividadGeneralTO implements Serializable {

	/** serial Version UID. */
	private static final long serialVersionUID = -564984068778186706L;

	/** El atributo o variable etiqueta. */
	@Schema(name = "etiqueta1", description = "Etiqueta 1.", example = "DUMMY", implementation = String.class)
	private String etiqueta1;

	/** El atributo o variable dato. */
	@Schema(name = "dato1", description = "Dato 1", implementation = String.class)
	private String dato1;
	
	/** El atributo o variable alineacion. */
	@Schema(name = "alineacion1", description = "I-Izquierda, D-Derecha, C-Centro,", implementation = String.class)
	private String alineacion1;	
	
	/** El atributo o variable etiqueta. */
	@Schema(name = "etiqueta2", description = "Etiqueta.", example = "DUMMY", implementation = String.class)
	private String etiqueta2;

	/** El atributo o variable dato. */
	@Schema(name = "dato2", description = "Dato", implementation = String.class)
	private String dato2;
	
	/** El atributo o variable alineacion. */
	@Schema(name = "alineacion2", description = "I-Izquierda, D-Derecha, C-Centro,", implementation = String.class)
	private String alineacion2;	
	
	/** El atributo o variable etiqueta. */
	@Schema(name = "etiqueta3", description = "Etiqueta.", example = "DUMMY", implementation = String.class)
	private String etiqueta3;

	/** El atributo o variable dato. */
	@Schema(name = "dato3", description = "Dato", implementation = String.class)
	private String dato3;
	
	/** El atributo o variable alineacion. */
	@Schema(name = "alineacion3", description = "I-Izquierda, D-Derecha, C-Centro,", implementation = String.class)
	private String alineacion3;		

	/** El atributo o variable etiqueta. */
	@Schema(name = "etiqueta4", description = "Etiqueta.", example = "DUMMY", implementation = String.class)
	private String etiqueta4;

	/** El atributo o variable dato. */
	@Schema(name = "dato4", description = "Dato", implementation = String.class)
	private String dato4;
	
	/** El atributo o variable alineacion. */
	@Schema(name = "alineacion4", description = "I-Izquierda, D-Derecha, C-Centro,", implementation = String.class)
	private String alineacion4;	
	
	/** El atributo o variable etiqueta. */
	@Schema(name = "etiqueta5", description = "Etiqueta 1.", example = "DUMMY", implementation = String.class)
	private String etiqueta5;

	/** El atributo o variable dato. */
	@Schema(name = "dato5", description = "Dato 1", implementation = String.class)
	private String dato5;
	
	/** El atributo o variable alineacion. */
	@Schema(name = "alineacion5", description = "I-Izquierda, D-Derecha, C-Centro,", implementation = String.class)
	private String alineacion5;	
	
	/** El atributo o variable etiqueta. */
	@Schema(name = "etiqueta6", description = "Etiqueta.", example = "DUMMY", implementation = String.class)
	private String etiqueta6;

	/** El atributo o variable dato. */
	@Schema(name = "dato6", description = "Dato", implementation = String.class)
	private String dato6;
	
	/** El atributo o variable alineacion. */
	@Schema(name = "alineacion6", description = "I-Izquierda, D-Derecha, C-Centro,", implementation = String.class)
	private String alineacion6;	
	
	/** El atributo o variable etiqueta. */
	@Schema(name = "etiqueta7", description = "Etiqueta.", example = "DUMMY", implementation = String.class)
	private String etiqueta7;

	/** El atributo o variable dato. */
	@Schema(name = "dato7", description = "Dato", implementation = String.class)
	private String dato7;
	
	/** El atributo o variable alineacion. */
	@Schema(name = "alineacion7", description = "I-Izquierda, D-Derecha, C-Centro,", implementation = String.class)
	private String alineacion7;		

	/** El atributo o variable etiqueta. */
	@Schema(name = "etiqueta8", description = "Etiqueta.", example = "DUMMY", implementation = String.class)
	private String etiqueta8;

	/** El atributo o variable dato. */
	@Schema(name = "dato8", description = "Dato", implementation = String.class)
	private String dato8;
	
	/** El atributo o variable alineacion. */
	@Schema(name = "alineacion8", description = "I-Izquierda, D-Derecha, C-Centro,", implementation = String.class)
	private String alineacion8;	
	
	/** El atributo o variable msjUsurio. */
	@Schema(name = "msjUsurio", description = "mensaje para el usuario", implementation = String.class)
	private String msjUsurio;
	
	/** El atributo o variable dato. */
	@Schema(name = "noBoton", description = "numero de botones",  implementation = Integer.class)
	private Integer noBoton;
	
	/** El atributo o variable msjUsurio. */
	@Schema(name = "boton1", description = "Etiqueta Botón", implementation = String.class)
	private String boton1;
	
	/** El atributo o variable msjUsurio. */
	@Schema(name = "boton2", description = "Etiqueta Botón", implementation = String.class)
	private String boton2;
	
	/** El atributo o variable msjUsurio. */
	@Schema(name = "boton3", description = "Etiqueta Botón", implementation = String.class)
	private String boton3;
	
	/** El atributo o variable msjUsurio. */
	@Schema(name = "observacion", description = "valor SI, NO", implementation = String.class)
	private String observacion;
}
