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
import lombok.Builder;
import lombok.Data;

/**
 * El objetivo de la Class ProcesoTO.java es ...
 * @author RIGOBERTO OLVERA
 * @version 1.0 Fecha de creacion 29/01/2020 12:29:01 PM Modificaciones:
 * @since JDK 1.8
 */
@Schema(title = "ProcesoInicialTO.", description = "Datos del Proceso Inicial.")
@Data
@Builder
public class ProcesoInicialTO implements Serializable {

	/** serial Version UID. */
	private static final long serialVersionUID = -564984068778186706L;

	/** El atributo o variable cve proceso. */
	@Schema(name = "idProceso", description = "Id del Proceso.", example = "IDENTIFICADOR DEL PROCESO QUE EJECUTA AL PRESENTE", implementation = String.class)
	private String idProceso;
	
	/** El atributo o variable cve proceso. */
	@Schema(name = "cveProceso", description = "Clave del Proceso.", example = "CLAVE DEL PROCESO", implementation = String.class)
	private String cveProceso;
	
	/** El atributo o variable version. */
	@Schema(name = "version", description = "Versión del proceso", example = "1.00", implementation = Integer.class)
	private BigDecimal version;
	
	/** El atributo o variable cve instancia. */
	@Schema(name = "cveInstancia", description = "Clave de la instancia.", example = "CLAVE DE INSTANCIA", implementation = String.class)
	private String cveInstancia;	

	/** El atributo o variable concepto. */
	@Schema(name = "concepto", description = "Concepto.", example = "CONCEPTO DE LA INSTANCIA", implementation = String.class)
	private String concepto;
	
	/** El atributo o variable fec creacion. */
	@Schema(name = "fecCreacion", description = "FECHA CREACIÓN", implementation = Date.class)
	private Date fecCreacion;

	/** El atributo o variable origen. */
	@Schema(name = "origen", description = "origen.", example = "ORIGEN DEL PROCESO", implementation = String.class)
	private String origen;
	
	/** El atributo o variable cve rol. */
	@Schema(name = "cveRol", description = "Clave del Rol.", example = "ROL CREADOR", implementation = String.class)
	private String cveRol;

	/** El atributo o variable situacion. */
	@Schema(name = "situacion", description = "Situacion HABILITADO/DESHABILITADO", example = "HABILITADO",
			implementation = String.class)
	private String situacion;

	/** El atributo o variable cve nodo. */
	@Schema(name = "cveNodoInicial", description = "Clave del Nodo.", example = "CLAVE DEL NODO INICIAL",
			implementation = String.class)
	private String cveNodoInicial;

	/** El atributo o variable id nodo. */
	@Schema(name = "idNodoInicial", description = "Identificador del Nodo", example = "1", implementation = Integer.class)
	private Integer idNodoInicial;

	/** El atributo o variable cve nodo. */
	@Schema(name = "cveNodoFinal", description = "Clave del Nodo.", example = "CLAVE DEL NODO FINAL",
			implementation = String.class)
	private String cveNodoFinal;

	/** El atributo o variable id nodo. */
	@Schema(name = "idNodoFinal", description = "Identificador del Nodo", example = "1", implementation = Integer.class)
	private Integer idNodoFinal;

	/** El atributo o variable cve nodo. */
	@Schema(name = "cveNodo", description = "Clave del Nodo.", example = "CLAVE DEL NODO",
			implementation = String.class)
	private String cveNodo;
	
	/** El atributo o variable id nodo. */
	@Schema(name = "idNodo", description = "ID DEL NODO", example = "1", implementation = Integer.class)
	private Integer idNodo;
	
	/** El atributo o variable id nodo. */
	@Schema(name = "secuenciaNodo", description = "SECUENCIA DEL NODO", example = "1", implementation = Integer.class)
	private Integer secuenciaNodo;
}
