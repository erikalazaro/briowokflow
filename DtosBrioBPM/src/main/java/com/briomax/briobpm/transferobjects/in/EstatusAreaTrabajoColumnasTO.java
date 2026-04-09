package com.briomax.briobpm.transferobjects.in;

import java.io.Serializable;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

/**
 * El objetivo de la Class EstatusAreaTrabajoColumnasTO.java es ...
 * @author Alexis Zamora.
 * @version 1.0 Fecha de creacion 04 Abr 2024 Modificaciones:
 * @since JDK 1.8
 */
@Schema(description = "EstatusAreaTrabajoColumnasTO", title = "EstatusAreaTrabajoColumnasTO")
@Data
@Builder
public class EstatusAreaTrabajoColumnasTO implements Serializable {

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
	
	/** The list datosAreaTrabajo. */
	@Schema(name = "columnasAreaTrabajo", description = "lista de columnas de Area de Trabajo", example = "mensaje", implementation = ColumnasAreaTrabajoTO.class)
	List <ColumnasAreaTrabajoTO> columnasAreaTrabajo;

}
