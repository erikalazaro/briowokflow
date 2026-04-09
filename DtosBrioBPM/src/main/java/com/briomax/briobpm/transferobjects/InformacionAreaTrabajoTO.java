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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * El objetivo de la Class InformacionAreaTrabajo.java es ...
 * @author SVM
 * @version 1.0 Fecha de creacion May 22, 2020 10:35:37 AM Modificaciones:
 * @since JDK 1.8
 */

@Schema(title = "InformacionAreaTrabajoTO.", description = "In Nodo Proceso.")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InformacionAreaTrabajoTO implements Serializable {

	/** Serial Version UID. */
	private static final long serialVersionUID = -887436810747974173L;

	private String cveInstancia;

	private Integer secuenciaNodo;

	/** El atributo o variable situacion. */
	private String situacion;

	private String usuarioBloquea;

	/** El atributo o variable secuencia dato estilo. */
	private String secuenciaDatoEstilo;

	/** El atributo o variable estilo. */
	private String estilo;

	/** El atributo o variable etiqueta tomar. */
	private String etiquetaTomar;

	/** El atributo o variable habilitar tomar. */
	private String habilitarTomar;

	/** El atributo o variable etiqueta liberar. */
	private String etiquetaLiberar;

	/** El atributo o variable habilitar liberar. */
	private String habilitarLiberar;

	/** El atributo o variable etiqueta ejecutar. */
	private String etiquetaEjecutar;

	/** El atributo o variable habilitar ejecutar. */
	private String habilitarEjecutar;

	/** El atributo o variable etiqueta terminar. */
	private String etiquetaTerminar;

	/** El atributo o variable habilitar terminar. */
	private String habilitarTerminar;

	/** El atributo o variable etiqueta cancelar. */
	private String etiquetaCancelar;

	/** El atributo o variable habilitar cancelar. */
	private String habilitarCancelar;

	/** El atributo o variable etiqueta consultar. */
	private String etiquetaConsultar;

	/** El atributo o variable habilitar consultar. */
	private String habilitarConsultar;

	/** El atributo o variable etiqueta bitacora. */
	private String etiquetaBitacora;

	/** El atributo o variable habilitar bitacora. */
	private String habilitarBitacora;

	/** El atributo o variable etiqueta bitacora. */
	private String etiquetaGuardar;

	/** El atributo o variable habilitar bitacora. */
	private String habilitarGuardar;
	
	/** El atributo o variable datos actividad. */
	private String datosActividad;

}
