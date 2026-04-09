package com.briomax.briobpm.transferobjects.in;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
/**
 * El objetivo de la Class EstatusCompuertaTO.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion 29/01/2024 Modificaciones:
 * @since JDK 1.8
 */
@Schema(description = "EstatusCompuertaTO", title = "EstatusCompuertaTO")
@Data
@Builder
public class EstatusCompuertaTO implements Serializable {

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
	
	/** The value. */
	@Schema(name = "cveInstancia", description = "estado compuerta", example = "", implementation = String.class)
	private String estadoCompuerta;

}