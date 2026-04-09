/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */
package com.briomax.briobpm.transferobjects.app;

import java.io.Serializable;
import java.math.BigDecimal;

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
@Schema(title = "ActividadAutorizarTO.", description = "Actividad por Autorizar.")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActividadAutorizarTO implements Serializable {

	/** serial Version UID. */
	private static final long serialVersionUID = -564984068778186706L;
	
	/** El atributo o variable des Proceso. */
	@Schema(name = "desProceso", description = "Descripción de Proceso.", implementation = String.class)
	private String desProceso;	
	
	/** El atributo o variable cve proceso. */
	@Schema(name = "cveProceso", description = "Clave del Proceso.", example = "DUMMY", implementation = String.class)
	private String cveProceso;

	/** El atributo o variable version. */
	@Schema(name = "version", description = "Versión del proceso", example = "1.00", implementation = BigDecimal.class)
	private BigDecimal version;
	
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

	/** El atributo o variable des Actividades. */
	@Schema(name = "desActividades", description = "Descripción de Actividades.", implementation = String.class)
	private String desActividades;		
	
	/** El atributo o variable des Proceso. */
	@Schema(name = "fecHora", description = "Fecha y Hora de venciminiemto.", example = "dd/mm,/yyyy hh:mm:ss", implementation = String.class)
	private String fecHoraVencimiento;
	
	/** El atributo o variable des Proceso. */
	@Schema(name = "color", description = "Color de la actividad V, N o R.", implementation = String.class)
	private String colorVencimiento;
}
