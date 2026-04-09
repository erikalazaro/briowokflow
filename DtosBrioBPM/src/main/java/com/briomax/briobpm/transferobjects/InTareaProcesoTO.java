package com.briomax.briobpm.transferobjects;

import java.math.BigDecimal;
import java.util.Date;

import com.briomax.briobpm.transferobjects.InVariableProcesoTO.InVariableProcesoTOBuilder;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * El objetivo de la Class InVariableProcesoTO.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Nov 20 12:29:01 PM :
 * @since JDK 1.8
 */
@Schema(title = "InTareaProcesoTO.", description = "In Tarea Proceso DTO.")
@Data
@Builder
public class InTareaProcesoTO {

	/** El atributo o variable cve entidad. */
	@Schema(name = "CVE_ENTIDAD", description = "", example = "", implementation = String.class)
	private String cveEntidad;

	/** El atributo o variable cve proceso. */
	@Schema(name = "CVE_PROCESO", description = "", example = "", implementation = String.class)
	private String cveProceso;

	/** El atributo o variable version. */
	@Schema(name = "VERSION", nullable = false, description = "", example = "", implementation = BigDecimal.class)
	private BigDecimal version;

	/** El atributo o variable cve instancia. */
	@Schema(name = "CVE_INSTANCIA", description = "", example = "", implementation = String.class)
	private String cveInstancia;
	
	/** El atributo o variable secuencia valor. */
	@Schema(name = "SECUENCIA_TAREA", description = "", example = "", implementation = Integer.class)
	private Integer secuenciaTarea;
	
	/** El atributo o variable cve instancia. */
	@Schema(name = "COMPLETADA", description = "", example = "", implementation = String.class)
	private String completada;
}
