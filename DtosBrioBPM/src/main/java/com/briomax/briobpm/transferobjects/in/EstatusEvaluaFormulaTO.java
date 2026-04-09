package com.briomax.briobpm.transferobjects.in;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

/**
 * El objetivo de la Class EstatusEvaluaFormulaTO.java es ...
 * @author Alexis Zamora.
 * @version 1.0 Fecha de creacion Abr 16 2024 Modificaciones:
 * @since JDK 1.8
 */
@Schema(description = "EstatusEvaluaFormulaTO", title = "EstatusEvaluaFormulaTO")
@Data
@Builder
public class EstatusEvaluaFormulaTO implements Serializable {

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
	
	@Schema(name = "valorAlfanumerico", description = "valor Alfanumerico", example = "", implementation = String.class)
	private String valorAlfanumerico;
	
	@Schema(name = "valorEntero", description = "Valor Entero", example = "1")
	private Integer valorEntero;
	
	@Schema(name = "valorDecimal", description = "Decimal", example = "1.0")
	private BigDecimal valorDecimal;
	
	@Schema(name = "valorFecha", description = "Valor Fecha", example = "12/12/2023 14:24")
	private Date valorFecha;

}
