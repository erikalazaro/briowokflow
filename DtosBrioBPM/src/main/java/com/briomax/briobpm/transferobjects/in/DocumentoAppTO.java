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
import java.util.List;

import com.briomax.briobpm.transferobjects.app.DocumentoIinfoTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * El objetivo de la Class DocumentoTrabajadorTO.java es ...
 * @author Sara Ventura
 * @version 1.0 Fecha de creacion Mar 9, 2020 11:35:41 AM Modificaciones:
 * @since JDK 1.8
 */
@Schema(title = "DocumentoTrabajadorTO.", description = "Documento de una Actividad.")
@Getter
@Setter
@NoArgsConstructor
public class DocumentoAppTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/** El atributo o variable des Proceso. */
	@Schema(name = "cveEntidad", description = "Clave entidad.", implementation = String.class)
	private String cveEntidad;	
	
	/** El atributo o variable des Proceso. */
	@Schema(name = "cveLocalidad", description = "Clave localidad.", implementation = String.class)
	private String cveLocalidad;	
	
	/** El atributo o variable des Proceso. */
	@Schema(name = "cveIdioma", description = "Clave idioma.", implementation = String.class)
	private String cveIdioma;	
	 
	/** El atributo o variable des Proceso. */
	@Schema(name = "username", description = "usuario.", implementation = String.class)
	private String username;	
	
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

	/** El atributo o variable cve rol. */
	@Schema(name = "Informació de todos los documentos", description = "documentos.", example = "Object")
	private List<DocumentoIinfoTO> documentos;

	
}