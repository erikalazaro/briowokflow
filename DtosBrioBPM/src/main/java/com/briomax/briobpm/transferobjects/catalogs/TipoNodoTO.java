/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */
package com.briomax.briobpm.transferobjects.catalogs;

import com.briomax.briobpm.transferobjects.core.ITransferObject;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * El objetivo de la Class TipoNodoTO.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Jun 25, 2020 7:12:51 PM Modificaciones:
 * @since JDK 1.8
 */
@Schema(description = "TipoNodoTO", title = "Tipo de Nodo.")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TipoNodoTO implements ITransferObject {

	/** Serial Version UID. */
	private static final long serialVersionUID = 1337874081445292508L;

	/** El atributo o variable cve nodo. */
	@Schema(name = "cveNodo", description = "Clave del Nodo.", example = "ACTIVIDAD-USUARIO",
			implementation = String.class)
	private String cveNodo;

	/** El atributo o variable tipo nodo. */
	@Schema(name = "tipoNodo", description = "Tipo del Nodo.", example = "ACTIVIDAD-USUARIO",
			implementation = String.class)
	private String tipoNodo;

	/** El atributo o variable descripcion. */
	@Schema(name = "descripcion", description = "Descripcion.", example = "Actividad de usuario",
			implementation = String.class)
	private String descripcion;

	/** El atributo o variable situacion. */
	@Schema(name = "situacion", description = "Situacion HABILITADO/DESHABILITADO", example = "HABILITADO",
			implementation = String.class)
	private String situacion;

}
