package com.briomax.briobpm.transferobjects.in;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.briomax.briobpm.transferobjects.InformacionAreaTrabajoTO;
import com.briomax.briobpm.transferobjects.in.EstatusAccionesTO.EstatusAccionesTOBuilder;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * El objetivo de la Class EstatusAreaTrabajoTO.java es ...
 * @author Alexis Zamora.
 * @version 1.0 Fecha de creacion 07 Mar 2024 Modificaciones:
 * @since JDK 1.8
 */
@Schema(description = "EstatusTO", title = "EstatusTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EstatusAreaTrabajoTO implements Serializable {

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
	@Schema(name = "datosAreaTrabajo", description = "lista de datos de Area de Trabajo", example = "mensaje", implementation = DatosAreaTrabajoTO.class)
	List <DatosAreaTrabajoTO> datosAreaTrabajo;
	
	/** The list infAreaTrabajo. */
	@Schema(name = "infAreaTrabajo", description = "lista de Informacion Area de Trabajo", example = "mensaje", implementation = InformacionAreaTrabajoTO.class)
	List <InformacionAreaTrabajoTO> infAreaTrabajo;
	
	/** The list mapDatos. */
	@Schema(name = "mapDatos", description = "HashMap de lista de datos area trabajo", example = "")
	Map<String, List<DatosAreaTrabajoTO>> mapDatos;

}
