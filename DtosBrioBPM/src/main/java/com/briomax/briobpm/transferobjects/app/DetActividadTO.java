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
 * El objetivo de la Class GraficaTO.java es ...
 * @author Sara Ventura
 * @version 1.0 Fecha de creacion 21/02/2024 12:29:01 PM Modificaciones:
 * @since JDK 1.8
 */
@Schema(title = "GraficaTO.", description = "Datos de las graficas.")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DetActividadTO implements Serializable {

	/** serial Version UID. */
	private static final long serialVersionUID = -564984068778186706L;

	/** El atributo o variable mensaje usuario. */
	@Schema(name = "msjUsuario", description = "msjUsuario.", example = "", implementation = String.class)
	private String msjUsuario;
	
	/** El atributo o variable contenido. */
	@Schema(name = "contenido", description = "contenido.", example = "Object")
	private List<Contenido> contenido;

	/** El atributo o variable numero de botones. */
	@ArraySchema(schema = @Schema(name = "numBotones", description = "Numero de botones.", implementation = int.class))
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
	
	/** El atributo o variable observacion. */
	@Schema(name = "observacion", description = "Etiqueta boton.", example = "", implementation = String.class)
	private String observacion;
}
