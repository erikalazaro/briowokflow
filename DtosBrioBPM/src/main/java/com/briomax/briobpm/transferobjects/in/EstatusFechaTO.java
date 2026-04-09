/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.transferobjects.in;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.briomax.briobpm.transferobjects.CalendarioLocalidadTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;


/**
 * El objetivo de la Class EstatusTO.java es ...
 * @author Sara Ventura.
 * @version 1.0 Fecha de creacion 08/11/2023 Modificaciones:
 * @since JDK 1.8
 */
@Schema(description = "EstatusFechaTO", title = "EstatusFechaTO")
@Data
@Builder
public class EstatusFechaTO implements Serializable {

	/** serial Version UID. */
	private static final long serialVersionUID = 3593261886846685341L;

	/** The type. */
	@NonNull
	@Schema(name = "tipoExcepcion", description = "tipoExcepcion", example = "OK", implementation = String.class)
	private String tipoExcepcion;

	/** The value. */
	@NonNull
	@Schema(name = "mensaje", description = "mensaje", example = "mensaje", implementation = String.class)
	private String mensaje;

	@Schema(name = "fechaLimite", description = "Fecha Limite", example = "12/12/2023 14:24", implementation = Date.class)
	private Date fechaLimite;
	
	@Schema(name = "listDias", description = "List Dias", example = "1 domingo")
	private List<CalendarioLocalidadTO> listDias;

}
