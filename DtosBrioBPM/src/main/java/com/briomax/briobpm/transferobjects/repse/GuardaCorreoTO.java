/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */
package com.briomax.briobpm.transferobjects.repse;

import com.briomax.briobpm.transferobjects.core.ITransferObject;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * El objetivo de la Interface GuardaCorreoTO.java es ...
 * @author Sara Ventura
 * @version 1.0 Fecha de creacion Nov 25, 2025 12:08:29 Modificaciones:
 * @since JDK 1.8
 */
@Schema(description = "GuardaCorreoTO", title = "Guarda Correo.")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GuardaCorreoTO implements ITransferObject {

	/** Serial Version UID. */
	private static final long serialVersionUID = 1764541225391468655L;

	/** El atributo o variable Proceso Periodico. */
	@Schema(name = "cveProcesoPeriodico", description = "Clave del proceso.", example = "BRIOMAX",
			implementation = String.class)
	private String cveProcesoPeriodico;
	
	/** El atributo o variable periocidad. */
	@Schema(name = "periocidad", description = "Periocidad.", example = "04_MES", implementation = String.class)
	private String periocidad;
	
	/** El atributo o variable aplica Inicio. */
	@Schema(name = "aplicaInicio", description = "Aplica Inicio.", example = "SI",
			implementation = String.class)
	private String aplicaInicio;
	
	/** El atributo o variable dia. */
	@Schema(name = "dia", description = "Dia para Nottficacion.", example = "1", implementation = Integer.class)
	private Integer dia;
	
	/** El atributo o variable notificacion Continua. */
	@Schema(name = "notContinua", description = "Notificacion continua.", example = "SI", implementation = String.class)
	private String notContinua;
	
	/** El atributo o variable cve usuario. */
	@Schema(name = "direccionCorreo", description = "Coreos.", example = "svm@briomax.com", implementation = String.class)
	private String direccionCorreo;
}
