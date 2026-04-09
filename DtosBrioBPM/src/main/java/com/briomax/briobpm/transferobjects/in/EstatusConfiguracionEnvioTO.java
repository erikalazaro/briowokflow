package com.briomax.briobpm.transferobjects.in;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

/**
 * El objetivo de la Class EstatusTO.java es ...
 * @author Alexis Zamora.
 * @version 1.0 Fecha de creacion Dic 22 del 2023 Modificaciones:
 * @since JDK 1.8
 */
@Schema(description = "EstatusConfiguracionEnvioTO", title = "EstatusConfiguracionEnvioTO")
@Data
@Builder
public class EstatusConfiguracionEnvioTO implements Serializable {

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
	
	@Schema(name = "ambitoEnvio", description = "Ambito de envio", example = "", implementation = String.class)
	private String ambitoEnvio;

	@Schema(name = "iniciarProcesoDestino", description = "Iniciar proceso destino", example = "", implementation = String.class)
	private String iniciarProcesoDestino;

	@Schema(name = "cveEntidadDestino", description = "Entidad destino", example = "", implementation = String.class)
	private String cveEntidadDestino;

	@Schema(name = "cveProcesoDestino", description = "Proceso destino", example = "", implementation = String.class)
	private String cveProcesoDestino;

	@Schema(name = "versionDestino", description = "Version destino", example = "", implementation = BigDecimal.class)
	private BigDecimal versionDestino;

	@Schema(name = "cveNodoDestino", description = "Nodo destino", example = "", implementation = String.class)
	private String cveNodoDestino;

	@Schema(name = "idNodoDestino", description = "ID Nodo destino", example = "", implementation = BigDecimal.class)
	private BigDecimal idNodoDestino;

	@Schema(name = "variablesReferenciaEnv", description = "Variables referencia envío", example = "", implementation = String.class)
	private String variablesReferenciaEnv;


}
