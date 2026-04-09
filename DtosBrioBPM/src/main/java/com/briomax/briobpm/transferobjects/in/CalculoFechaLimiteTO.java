/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */
package com.briomax.briobpm.transferobjects.in;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

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
@Schema(title = "CalculoFechaLimiteTO.", description = "Calculo Fecha Limite.")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CalculoFechaLimiteTO implements Serializable {

	/** serial Version UID. */
	private static final long serialVersionUID = -564984068778186706L;

	/** El atributo o variable cve rol. */
	@Schema(name = "cveEntidadBase", description = "ENTIDAD BASE.", example = "", implementation = String.class)
	private String cveEntidadBase;
	
	/** El atributo o variable cve rol. */
	@Schema(name = "cveProceso", description = "CLAVE DEL PROCESO.", example = "ROL DUMMY 1", implementation = String.class)
	private String cveProceso;

	/** El atributo o variable version. */
	@Schema(name = "version", description = "Versión del proceso", example = "1.00", implementation = BigDecimal.class)
	private BigDecimal version;

	/** El atributo o variable cve area trabajo. */
	@Schema(name = "cveInstancia", description = "CLAVE DE LA INSTANCIA", example = "SOLICITUD_SOPORTE",
			implementation = String.class)
	private String cveInstancia;
	
	/** El atributo o variable cve nodo. */
	@Schema(name = "cveNodo", description = "Clave del Nodo.", example = "ACTIVIDAD-USUARIO",
			implementation = String.class)
	private String cveNodo;

	/** El atributo o variable id nodo. */
	@Schema(name = "idNodo", description = "Identificador del Nodo", example = "1", implementation = Integer.class)
	private Integer idNodo;
	
	/** El atributo o variable fecha Creacion. */
	@Schema(name = "fechaCreacion", description = "FECHA DE CREACIÓN", example = "119/11/2023", implementation = Date.class)	
	private Date fechaCreacion;

	/** El atributo o variable fecha plazo Nivel Servicio. */
	@Schema(name = "plazoNivelServicio", description = "PLAZO NIVEL SERVICIO", implementation = Integer.class)
	private Integer plazoNivelServicio;
	
	/** El atributo o variable base Calculo Nivel Servicio. */
	@Schema(name = "baseCalculoNivelServicio", description = "BASE CALCULO NIVEL SERVICIO", implementation = String.class)
	private String baseCalculoNivelServicio;
	
	/** El atributo o variable base Hora Nivel Servicio. */
	@Schema(name = "baseHoraNivelServicio", description = "BASE HORARIO NIVEL SERVICIO", implementation = String.class)
	private String baseHoraNivelServicio;
	
	/** El atributo o variable base Clave Localida Destino. */
	@Schema(name = "cveLocalidaDestino", description = "CVE LOCALIDAD DESTINO", implementation = String.class)
	private String cveLocalidaDestino;
	
	/** El atributo o variable fecha Creacion. */
	@Schema(name = "fechaCreacionLocalidad", description = "FECHA DE CREACIÓN", example = "119/11/2023", implementation = Date.class)	
	private Date fechaCreacionLocalidad;
}
