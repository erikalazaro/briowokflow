package com.briomax.briobpm.transferobjects.in;

import java.util.List;

import com.briomax.briobpm.transferobjects.core.ITransferObject;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

/**
 * El objetivo de la Class EstatusInfSeccionVPTO.java es ...
 * @author Sara Ventura.
 * @version 1.0 Fecha de creacion 30 Abr 2024 Modificaciones:
 * @since JDK 1.8
 */
@Schema(description = "EstatusInfSeccionVPTO", title = "EstatusInfSeccionVPTO")
@Data
@Builder
public class EstatusInfSeccionVPTO implements ITransferObject {

	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;

	/** The type. */
	@NonNull
	@Schema(name = "tipoExcepcion", description = "tipoExcepcion", example = "OK", implementation = String.class)
	private String tipoExcepcion;

	/** The value. */
	@NonNull
	@Schema(name = "mensaje", description = "mensaje", example = "mensaje", implementation = String.class)
	private String mensaje;
	
	/** The value datos Seccion OCU. */
	@Schema(name = "datosSeccionOCU", description = "datos Seccion OCU")
	private List<DatosSeccionOCUTO> datosSeccionOCU;
	
	/** The value datos tipo. */
	@Schema(name = "tipo", description = "si es tipo grid o variable", example ="DOCUMENTO")
	private String tipo;
}
