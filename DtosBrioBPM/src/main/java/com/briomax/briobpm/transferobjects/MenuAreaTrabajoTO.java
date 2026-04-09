/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.transferobjects;

import com.briomax.briobpm.transferobjects.core.ITransferObject;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * El objetivo de la Class MenuAreaTrabajoTO.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion May 18, 2020 8:52:30 AM Modificaciones:
 * @since JDK 1.8
 */
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuAreaTrabajoTO implements ITransferObject {

	/** Serial Version UID. */
	private static final long serialVersionUID = 6971967974128181185L;

	/** El atributo o variable ordenamiento. */
	@Schema(name = "ordenamiento", description = "Ordenamiento", example = "1", implementation = Integer.class)
	private Integer ordenamiento;

	/** El atributo o variable tipo. */
	@Schema(name = "tipo", description = "Tipo", example = "DINAMICO", implementation = String.class)
	private String tipo;

	/** El atributo o variable cve proceso. */
	@Schema(name = "cveProceso", description = "Clave del Proceso.", example = "DUMMY", implementation = String.class)
	private String cveProceso;

	/** El atributo o variable version. */
	@Schema(name = "version", description = "Version del Proceso.", example = "1.00")
	private String version;

	/** El atributo o variable des proceso. */
	@Schema(name = "desProceso", description = "Descripcion del Proceso.", example = "Seguimiento de Incidencias")
	private String desProceso;

	/** El atributo o variable cve nodo. */
	@Schema(name = "cveNodo", description = "Clave del Nodo.", example = "ACTIVIDAD-USUARIO",
			implementation = String.class)
	private String cveNodo;

	/** El atributo o variable id nodo. */
	@Schema(name = "idNodo", description = "Identificador del Nodo", example = "1", implementation = Integer.class)
	private Integer idNodo;

	/** El atributo o variable des nodo. */
	@Schema(name = "desNodo", description = "Descripción del Nodo.", example = "Solicitud de soporte",
			implementation = String.class)
	private String desNodo;

	/** El atributo o variable ini proceso. */
	@Schema(name = "iniProceso", description = "Iniciar Proceso.", example = "NO", implementation = String.class)
	private String iniProceso;

	/** El atributo o variable eti boton. */
	@Schema(name = "etiBoton", description = "Etiqueta del Boton.", example = "Iniciar Actividad",
			implementation = String.class)
	private String etiBoton;

	/** El atributo o variable cve area trabajo. */
	@Schema(name = "cveAreaTrabajo", description = "Clave del Area de Trabajo", example = "SOLICITUD_SOPORTE",
			implementation = String.class)
	private String cveAreaTrabajo;

	/** El atributo o variable cve rol. */
	@Schema(name = "cveRol", description = "Clave del Rol.", example = "ROL DUMMY 1", implementation = String.class)
	private String cveRol;

	/** El atributo o variable eje automatica. */
	@Schema(name = "ejeAutomatica", description = "Ejecucion Automatica", example = "NO", implementation = String.class)
	private String ejeAutomatica;

	/** El atributo o variable eti bot eje automatica. */
	@Schema(name = "etiBotEjeAutomatica", description = "Etiqueta del Boton Ejecucion Automatica", example = "Ejecutar",
			implementation = String.class)
	private String etiBotEjeAutomatica;

	/** El atributo o variable url opcion. */
	@Schema(name = "idOpcion", description = "ID de la Opciion.", example = "CONSULTA_POR_PROCESO",
			implementation = String.class)
	private String idOpcion;

	/** El atributo o variable url opcion. */
	@Schema(name = "parametros", description = "Parametros en formato JSON.", example = "{}",
			implementation = String.class)
	private String parametros;
	
	/** Icono del proceso. */
	@Schema(name = "icono", description = "Icono del Proceso.", format = "byte", type = "string", example = "imagen.jpg")
	private byte[] icono;

}
