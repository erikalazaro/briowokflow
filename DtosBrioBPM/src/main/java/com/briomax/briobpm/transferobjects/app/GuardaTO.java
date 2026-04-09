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
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * El objetivo de la Class Seccion.java es ...
 * @author Sara Venruta
 * @version 1.0 Fecha de creacion 21/02/2024 PM Modificaciones:
 * @since JDK 1.8
 */
@Schema(title = "Guarda.", description = "Guarda Valor.")
@Getter
@Setter
@Builder
public class GuardaTO implements Serializable {

	/** Serial Version UID. */
	private static final long serialVersionUID = -940698310781150899L;

	/** El atributo o variable cve Idioma. */
	@Schema(name = "cveIdioma", description = "Idioma.", example = "ES-MX", implementation = String.class)
	private String cveIdioma;

	/** El atributo o variable cve Entidad. */
	@Schema(name = "cveEntidad", description = "entidad.", example = "BRIOMAX", implementation = String.class)
	private String cveEntidad;

	/** El atributo o variable cve Localidad. */
	@Schema(name = "cveLocalidad", description = "localidad.", example = "BRIOMAX-CENTRAL", implementation = String.class)
	private String cveLocalidad;
	
	/** El atributo o variable username. */
	@Schema(name = "username", description = "localidad.", example = "DUMMY", implementation = String.class)
	private String username;

	
	/** El atributo o variable cve proceso. */
	@Schema(name = "cveProceso", description = "Clave del Proceso.", example = "DUMMY", implementation = String.class)
	private String cveProceso;

	/** El atributo o variable version. */
	@Schema(name = "version", description = "Versión del proceso", example = "1.00", implementation = String.class)
	private String version;
	
	/** El atributo o variable cve nodo. */
	@Schema(name = "cveInstancia", description = "Clave de la instacia.", example = "ACTIVIDAD-USUARIO",
			implementation = String.class)
	private String cveInstancia;

	/** El atributo o variable cve nodo. */
	@Schema(name = "cveNodo", description = "Clave del Nodo.", example = "ACTIVIDAD-USUARIO",
			implementation = String.class)
	private String cveNodo;

	/** El atributo o variable id nodo. */
	@Schema(name = "idNodo", description = "Identificador del Nodo", example = "1", implementation = Integer.class)
	private Integer idNodo;

	/** El atributo o variable actividades. */
	@Schema(name = "secNodo", description = "secuencia del nodo", example = "1", implementation = Integer.class)
	private Integer secNodo;
	
	/** El atributo o variable id nodo. */
	@Schema(name = "seleccionaAccion", description = "Boton ejecutado", example = "TERMINAR", implementation = String.class)
	private String cveBoton;
	
	/** El atributo o variable labels. */
	private List<Object> listaRespuestas;

}
