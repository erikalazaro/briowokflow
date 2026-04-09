package com.briomax.briobpm.transferobjects;

import java.math.BigDecimal;
import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * El objetivo de la Class InVariableProcesoTO.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Nov 17 12:29:01 PM :
 * @since JDK 1.8
 */
@Schema(title = "InVariableProcesoTO.", description = "In Variable Proceso TO.")
@Data
@Builder
public class InVariableProcesoTO {

	/** El atributo o variable cve entidad. */
	@Schema(name = "CVE_ENTIDAD", description = "", example = "", implementation = String.class)
	private String cveEntidad;
	
	/** El atributo o variable cve entidad. */
	@Schema(name = "RFC", description = "", example = "", implementation = String.class)
	private String rfc;

	/** El atributo o variable cve proceso. */
	@Schema(name = "CVE_PROCESO", description = "", example = "", implementation = String.class)
	private String cveProceso;

	/** El atributo o variable version. */
	@Schema(name = "VERSION", nullable = false, description = "", example = "", implementation = BigDecimal.class)
	private BigDecimal version;

	/** El atributo o variable cve instancia. */
	@Schema(name = "CVE_INSTANCIA", description = "", example = "", implementation = String.class)
	private String cveInstancia;

	/** El atributo o variable ocurrencia. */
	@Schema(name = "OCURRENCIA", description = "", example = "", implementation = Integer.class)
	private Integer ocurrencia;

	/** El atributo o variable cve variable. */
	@Schema(name = "CVE_VARIABLE", description = "", example = "", implementation = String.class)
	private String cveVariable;

	/** El atributo o variable secuencia valor. */
	@Schema(name = "SECUENCIA_VALOR", description = "", example = "", implementation = Integer.class)
	private Integer secuenciaValor;
	
	/** El atributo o variable valor alfanumerico. */
	@Schema(name = "VALOR_ALFANUMERICO", description = "", example = "", implementation = String.class)
	private String valorAlfanumerico;

	/** El atributo o variable valor entero. */
	@Schema(name = "VALOR_ENTERO", description = "", example = "", implementation = Integer.class)
	private Integer valorEntero;

	/** El atributo o variable valor decimal. */
	@Schema(name = "VALOR_DECIMAL", description = "", example = "", implementation = BigDecimal.class)
	private BigDecimal valorDecimal;

	/** El atributo o variable valor fecha. */
	@Schema(name = "VALOR_FECHA", description = "", example = "", implementation = Date.class)
	private Date valorFecha;
	
	/** El atributo o variable valor fecha. */
	@Schema(name = "DEL", description = "", example = "", implementation = Date.class)
	private Date del;
	
	/** El atributo o variable valor fecha. */
	@Schema(name = "HASTA", description = "", example = "", implementation = Date.class)
	private Date hasta;
	
}
