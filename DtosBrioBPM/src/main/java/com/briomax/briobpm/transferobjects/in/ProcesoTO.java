/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */
package com.briomax.briobpm.transferobjects.in;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * El objetivo de la Class ProcesoTO.java es ...
 * @author RIGOBERTO OLVERA
 * @version 1.0 Fecha de creacion 29/01/2020 12:29:01 PM Modificaciones:
 * @since JDK 1.8
 */
@Schema(title = "ProcesoTO.", description = "Datos del Proceso.")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProcesoTO implements Serializable {

	/** serial Version UID. */
	private static final long serialVersionUID = -564984068778186706L;

	/** El atributo o variable cve rol. */
	@Schema(name = "cveRol", description = "Clave del Rol.", example = "ROL DUMMY 1", implementation = String.class)
	private String cveRol;

	/** El atributo o variable cve proceso. */
	@Schema(name = "cveProceso", description = "Clave del Proceso.", example = "DUMMY", implementation = String.class)
	private String cveProceso;

	/** El atributo o variable version. */
	@Schema(name = "version", description = "Versión del proceso", example = "1.00")
	private String version;

	/** El atributo o variable cve nodo. */
	@Schema(name = "cveNodo", description = "Clave del Nodo.", example = "ACTIVIDAD-USUARIO",
			implementation = String.class)
	private String cveNodo;

	/** El atributo o variable id nodo. */
	@Schema(name = "idNodo", description = "Identificador del Nodo", example = "1", implementation = Integer.class)
	private Integer idNodo;

	/** El atributo o variable cve area trabajo. */
	@Schema(name = "cveAreaTrabajo", description = "Clave del Area de Trabajo", example = "SOLICITUD_SOPORTE",
			implementation = String.class)
	private String cveAreaTrabajo;
	
	/** El atributo o variable cve area trabajo. */
	@Schema(name = "origen", description = "Clave del Area de Trabajo", example = "SOLICITUD_SOPORTE",
			implementation = String.class)
	private String origen;
	
	/** El atributo o variable cve area trabajo. */
	@Schema(name = "concepto", description = "Clave del Area de Trabajo", example = "SOLICITUD_SOPORTE",
			implementation = String.class)
	private String concepto;
	
	/** El atributo o variable cve area trabajo. */
	@Schema(name = "valoresReferenciaEnvio", description = "Clave del Area de Trabajo", example = "SOLICITUD_SOPORTE",
			implementation = String.class)
	private String valoresReferenciaEnvio;
	
	/** El atributo o variable cve area trabajo. */
	@Schema(name = "folioMensajeEnvio", description = "Clave del Area de Trabajo", example = "SOLICITUD_SOPORTE",
			implementation = String.class)
	private String folioMensajeEnvio;

}
