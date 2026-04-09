package com.briomax.briobpm.transferobjects.in;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

/**
 * El objetivo de la Class ActividadTO.java es ...
 * @author RIGOBERTO OLVERA
 * @version 1.0 Fecha de creacion 29/01/2020 05:14:09 PM Modificaciones:
 * @since JDK 1.8
 */
@Schema(title = "ActividadTO.", description = "Actividad.")
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class ActividadTO extends ProcesoTO implements Serializable {

	/** serial Version UID. */
	private static final long serialVersionUID = -4185314786731596604L;

	/** El atributo o variable cve instancia. */
	@Schema(name = "cveInstancia", description = "Clave de la Instancia.", example = "202008-000001",
			implementation = String.class)
	private String cveInstancia;

	/** El atributo o variable sec nodo. */
	@Schema(name = "secNodo", description = "Secuencia del Nodo", example = "1", implementation = Integer.class)
	private Integer secNodo;
	
	@Schema(name = "usoSeccion", description = "Uso de la sección según sea web o movil.", example = "APP", implementation = String.class)
	private String usoSeccion;

	@Schema(name = "origen", description = "Origen de la actividad.", example = "USUARIO", implementation = String.class)
	private String origen;

	/**
	 * Crear una nueva instancia del objeto actividad TO.
	 * @param cveRol el cve rol.
	 * @param cveProceso el cve proceso.
	 * @param version el version.
	 * @param cveNodo el cve nodo.
	 * @param idNodo el id nodo.
	 * @param cveAreaTrabajo el cve area trabajo.
	 * @param cveInstancia el cve instancia.
	 * @param secNodo el sec nodo.
	 */
	@Builder(builderMethodName = "builderActividadTO")
	public ActividadTO(String cveRol, String cveProceso, String version, String cveNodo, Integer idNodo,
		String cveAreaTrabajo, String cveInstancia, Integer secNodo, String origen) {
		super(cveRol, cveProceso, version, cveNodo, idNodo, cveAreaTrabajo, cveInstancia, cveInstancia, cveInstancia, cveInstancia);
		this.cveInstancia = cveInstancia;
		this.secNodo = secNodo;
		this.origen = origen;
	}


}