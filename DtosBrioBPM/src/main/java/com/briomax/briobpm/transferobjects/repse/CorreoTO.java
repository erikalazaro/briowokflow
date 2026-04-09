/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */
package com.briomax.briobpm.transferobjects.repse;

import java.util.Date;

import com.briomax.briobpm.transferobjects.core.ITransferObject;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * El objetivo de la Interface CrUsuarioTO.java es ...
 * @author Sara Ventura
 * @version 1.0 Fecha de creacion Nov 25, 2025 12:08:29 Modificaciones:
 * @since JDK 1.8
 */
@Schema(description = "CrUsuarioTO", title = "Usuarios Repse.")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CorreoTO implements ITransferObject {

	/** Serial Version UID. */
	private static final long serialVersionUID = 1764541225391468655L;

	/** El atributo o variable cve entidad. */
	@Schema(name = "cveProceso", description = "Clave del proceso.", example = "BRIOMAX",
			implementation = String.class)
	private String cveProceso;
	
	/** El atributo o variable cve usuario. */
	@Schema(name = "cvePeriodicidad", description = "Clave del Periodo.", example = "BrioBpm", implementation = String.class)
	private String cvePeriodicidad;
	
}
