/*
numeroDato * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */
package com.briomax.briobpm.transferobjects.app;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * El objetivo de la Class ActividadGuardar.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion 26/03/2024
 * @since JDK 1.8
 */
@Schema(title = "ActividadGuardar.", description = "DTO de la actividad a guardar.")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActividadGuardar implements Serializable {

	/** serial Version UID. */
	private static final long serialVersionUID = -564984068778186706L;
	
	/** El atributo o variable estatus. */
	@Schema(name = "tipoExcepcion", description = "Estado de la operación.", example = "OK", required = true, implementation = String.class)
	private String tipoExcepcion;
	
	/** El atributo o variable mensaje. */
	@Schema(name = "mensaje", description = "Mensaje informativo del resultado de la operación.", example = "Operación realizada con éxito", required = true, implementation = String.class)
	private String mensaje;
	
	/** El atributo o variable username. */
	@Schema(name = "username", description = "Nombre de usuario.", example = "Admin.Opera", implementation = String.class)
	private String username;

	/** El atributo o variable cveEntidad. */
	@Schema(name = "cveEntidad", description = "Clave de la entidad.", example = "BRIOMAX", implementation = String.class)
	private String cveEntidad;

	/** El atributo o variable cveLocalidad. */
	@Schema(name = "cveLocalidad", description = "Clave de la localidad.", example = "BRIOMAX-CENTRAL", implementation = String.class)
	private String cveLocalidad;

	/** El atributo o variable cveIdioma. */
	@Schema(name = "cveIdioma", description = "Clave del idioma.", example = "ES-MX", implementation = String.class)
	private String cveIdioma;

	/** El atributo o variable cveProceso. */
	@Schema(name = "cveProceso", description = "Clave del proceso.", example = "INCIDENCIA-SMI", implementation = String.class)
	private String cveProceso;

	/** El atributo o variable version. */
	@Schema(name = "version", description = "Versión del proceso.", example = "1.0", implementation = BigDecimal.class)
	private BigDecimal version;

	/** El atributo o variable cveInstancia. */
	@Schema(name = "cveInstancia", description = "Clave de la instancia.", example = "NDF", implementation = String.class)
	private String cveInstancia;

	/** El atributo o variable cveNodo. */
	@Schema(name = "cveNodo", description = "Clave del nodo.", example = "ACTIVIDAD-USUARIO", implementation = String.class)
	private String cveNodo;

	/** El atributo o variable idNodo. */
	@Schema(name = "idNodo", description = "Identificador del nodo.", example = "1", implementation = Integer.class)
	private Integer idNodo;

	/** El atributo o variable secNodo. */
	@Schema(name = "secNodo", description = "Secuencia del nodo.", example = "1", implementation = Integer.class)
	private Integer secNodo;

	/** El atributo o variable cveBoton. */
	@Schema(name = "cveBoton", description = "Clave del botón.", example = "GUARDAR", implementation = String.class)
	private String cveBoton;
	
	private List<DatoGuardar> listaRespuestas;

}
