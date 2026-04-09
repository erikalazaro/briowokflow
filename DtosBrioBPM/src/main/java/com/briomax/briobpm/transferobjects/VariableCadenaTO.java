package com.briomax.briobpm.transferobjects;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import com.briomax.briobpm.transferobjects.StCompuertaInicioTO.StCompuertaInicioTOBuilder;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * El objetivo de la Class VariableCadenaTO.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Dic 21 12:29:01 PM :
 * @since JDK 1.8
 */
@Schema(description = "VariableCadenaTO", title = "Variable Cadena DTO.")
@ToString
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VariableCadenaTO implements Serializable {
	
	/** serial Version UID. */
	private static final long serialVersionUID = 1L;

	/** Clave de la variable. */
	@Schema(name = "CVE_VARIABLE", description = "", example = "", implementation = String.class)
	private String cveVariable;

	/** Tipo de la variable. */
	@Schema(name = "TIPO", description = "", example = "", implementation = String.class)
	private String tipo;

	/** Número de decimales. */
	@Schema(name = "DECIMALES", nullable = false, description = "", example = "", implementation = BigDecimal.class)
	private Integer decimales;

	/** Formato de la fecha. */
	@Schema(name = "FORMATO_FECHA", description = "", example = "", implementation = String.class)
	private String formatoFecha;

	/** Valor alfanumérico de la variable. */
	@Schema(name = "VALOR_ALFANUMERICO", description = "", example = "", implementation = String.class)
	private String valorAlfanumerico;

	/** Valor entero de la variable. */
	@Schema(name = "VALOR_ENTERO", description = "", example = "", implementation = Integer.class)
	private Integer valorEntero;

	/** Valor decimal de la variable. */
	@Schema(name = "VALOR_DECIMAL", nullable = false, description = "", example = "", implementation = BigDecimal.class)
	private BigDecimal valorDecimal;

	/** Valor de fecha de la variable. */
	@Schema(name = "VALOR_FECHA", description = "", example = "", implementation = Date.class)
	private Date valorFecha;

}
