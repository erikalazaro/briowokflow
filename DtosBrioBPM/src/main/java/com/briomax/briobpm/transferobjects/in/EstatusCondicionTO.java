package com.briomax.briobpm.transferobjects.in;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
/**
 * El objetivo de la Class EstatusCondicionTO.java es ...
 * @author Alexis Zamora.
 * @version 1.0 Fecha de creacion Jun 20 de 2024 Modificaciones:
 * @since JDK 1.8
 */
@Schema(description = "EstatusCondicionTO", title = "EstatusCondicionTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EstatusCondicionTO implements Serializable {

	/** serial Version UID. */
	private static final long serialVersionUID = 1L;

	/** The type. */
	@NonNull
	@Schema(name = "tipoExcepcion", description = "tipoExcepcion", example = "OK", implementation = String.class)
	private String tipoExcepcion;

	/** The value. */
	@NonNull
	@Schema(name = "mensaje", description = "mensaje", example = "mensaje", implementation = String.class)
	private String mensaje;
	
	/** El atributo o variable cve area trabajo. */
	@Schema(name = "resultado", description = "resultado", example = "",
			implementation = String.class)
	private String resultado;

}
