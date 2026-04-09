package com.briomax.briobpm.transferobjects.in;

import java.math.BigDecimal;
import java.util.Date;

import com.briomax.briobpm.transferobjects.core.ITransferObject;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * El objetivo de la Class ValorVariableTO.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Abr 10, 2024 11:09:51 AM Modificaciones:
 * @since JDK 1.8
 */
@Schema(description = "ValorVariableTO", title = "ValorVariableTO.")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StValorVariableTO implements ITransferObject {

	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;

	/** El atributo o variable cve entidad. */
	@Schema(name = "cveEntidad", description = "Clave de la Entidad.", example = "BRIOMAX",
			implementation = String.class)
	private String cveEntidad;
	
	/** El atributo o variable cve proceso. */
	@Schema(name = "cveProceso", description = "Clave Proceso.", example = "DUMMY",
			implementation = String.class)
	private String cveProceso;
	
	/** El atributo o variable version. */
	@Schema(name = "version", description = "Version.", example = "1.0",
			implementation = BigDecimal.class)
	private BigDecimal version;
	
	/** El atributo o variable cve variable. */
	@Schema(name = "cveVariable", description = "Clave Variable.", example = "",
			implementation = String.class)
	private String cveVariable;
	
	/** El atributo o variable cve secuencia. */
	@Schema(name = "secuencia", description = "Etiqueta.", example = "1",
			implementation = Integer.class)
	private Integer secuencia;
	
	/** El atributo o variable etiqueta Lista. */
	@Schema(name = "etiquetaLista", description = "Etiqueta Lista.", example = "",
			implementation = String.class)
	private String etiquetaLista;
	
	/** El atributo o variable valor Alfanumerico. */
	@Schema(name = "valorAlfanumerico", description = "Valor Alfanumerico.", example = "",
			implementation = String.class)
	private String valorAlfanumerico;
	
	/** El atributo o variable valor Entero. */
	@Schema(name = "valorEntero", description = "Valor Entero.", example = "",
			implementation = Integer.class)
	private Integer valorEntero;
	
	/** El atributo o variable valor Decimal. */
	@Schema(name = "valorDecimal", description = "Valor Decimal.", example = "",
			implementation = BigDecimal.class)
	private BigDecimal valorDecimal;
	
	/** El atributo o variable valor Fecha. */
	@Schema(name = "valorFecha", description = "Valor Fecha.", example = "",
			implementation = Date.class)
	private Date valorFecha;
}