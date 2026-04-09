package com.briomax.briobpm.transferobjects.in;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 * El objetivo de la Class EstatusCreaInstanciaTO.java es ...
 * @author Alexis Zamora.
 * @version 1.0 Fecha de creacion 26/01/2024 Modificaciones:
 * @since JDK 1.8
 */
@Schema(description = "EstatusCreaInstanciaTO", title = "EstatusCreaInstanciaTO")
@Data
@Builder
@Getter
@Setter
public class EstatusCreaInstanciaTO  implements Serializable {

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
	@Schema(name = "cveInstancia", description = "mensaje", example = "", implementation = String.class)
	private String cveInstancia;
	
	/** The value. */
	@Schema(name = "cveNodoActividad", description = "mensaje", example = "", implementation = String.class)
	private String cveNodoActividad;
	
	/** The value. */
	@Schema(name = "idNodoActividad", description = "mensaje", example = "1", implementation = Integer.class)
	private Integer idNodoActividad;
	
	/** The value. */
	@Schema(name = "secuenciaNodoActividad", description = "mensaje", example = "", implementation = Integer.class)
	private Integer secuenciaNodoActividad;
}
