package com.briomax.briobpm.transferobjects;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
@Schema(title = "VariableResultadoTO.", description = "Variable Resultado TO DTO.")
@Data
@Builder
public class VariableResultadoTO implements Serializable {
	
	/** serial Version UID. */
	private static final long serialVersionUID = 1L;

	/** Clave de la variable cve Entidad. */
	@Schema(name = "CVE_ENTIDAD", description = "", example = "", implementation = String.class)
	private String cveEntidad;

	/** Tipo de la variable cve Proceso. */
	@Schema(name = "CVE_PROCESO", description = "", example = "", implementation = String.class)
	private String cveProceso;

	/** El atributo o variable version*/
	@Schema(name = "VERSION", nullable = false, description = "", example = "", implementation = String.class)
	private BigDecimal version;

	/** Clave regla. */
	@Schema(name = "CVE_REGLA", description = "", example = "", implementation = String.class)
	private String cveRegla;

}
