package com.briomax.briobpm.transferobjects.in;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

/**
 * El objetivo de la Class EstatusFolio.java es ...
 * @author Sara Ventura.
 * @version 1.0 Fecha de creacion 26/01/2024 Modificaciones:
 * @since JDK 1.8
 */
@Schema(description = "EstatusFolioTO", title = "EstatusFolioTO")
@Data
@Builder
public class EstatusFolioTO implements Serializable {

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
	@Schema(name = "cveInstancia", description = "clave instancia", example = "202205-000001", implementation = String.class)
	private String cveInstancia;

}
