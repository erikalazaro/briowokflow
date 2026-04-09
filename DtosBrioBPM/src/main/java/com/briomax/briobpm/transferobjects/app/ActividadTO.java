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
 * El objetivo de la Class ActividadTO.java es ...
 * @author Sara Ventura
 * @version 1.0 Fecha de creacion 28/05/2024
 * @since JDK 1.8
 */
@Schema(title = "ActividadTO.", description = "Detalled de la Actividad de un proceso.")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActividadTO implements Serializable {

	/** serial Version UID. */
	private static final long serialVersionUID = -564984068778186706L;

	/** El atributo o variable etiqueta. */
	@Schema(name = "desActividad", description = "Etiqueta 1.", example = "DUMMY", implementation = String.class)
	private String desActividad;

	/** El atributo o variable graphics. */
	@ArraySchema(schema = @Schema(name = "datos", description = "Lista de los datos que tiene una actividad"))
	private List<DetDatoActividad> datos;
	
	/** El atributo o variable numero de botones. */
	@Schema(name = "numBotones", description = "Numero de botones.", example = "1", implementation = String.class)
	private int numBotones;

	/** El atributo o variable etiqueta boton. */
	@Schema(name = "btnEtiquetaUno", description = "Etiqueta boton.", example = "", implementation = String.class)
	private String btnEtiquetaUno;
	
	/** El atributo o variable etiqueta boton. */
	@Schema(name = "btnEtiquetaDos", description = "Etiqueta boton.", example = "", implementation = String.class)
	private String btnEtiquetaDos;
	
	/** El atributo o variable etiqueta boton. */
	@Schema(name = "btnEtiquetaTres", description = "Etiqueta boton.", example = "", implementation = String.class)
	private String btnEtiquetaTres;
}
