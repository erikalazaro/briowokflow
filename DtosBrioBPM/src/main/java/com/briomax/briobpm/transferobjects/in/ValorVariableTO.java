package com.briomax.briobpm.transferobjects.in;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * El objetivo de la Class ValorVariableTO.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Jun 10, 2024 11:35:41 AM Modificaciones:
 * @since JDK 1.8
 */
@Schema(title = "ValorVariableTO.", description = "ValorVariableTO.")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ValorVariableTO implements Serializable {

	/** Serial Version UID. */
	private static final long serialVersionUID = 1l;
	
	   /** 
     * @schema secuencia
     * @description Secuencia del documento
     * @example 1
     */
    @Schema(name = "secuencia", description = "Secuencia del documento", example = "1")
    private Integer secuencia;

    /** 
     * @schema cveVariable
     * @description Clave de la variable
     * @example "variable1"
     */
    @Schema(name = "cveVariable", description = "Clave de la variable", example = "variable1")
    private String cveVariable;

    /** 
     * @schema tipo
     * @description Tipo de la variable
     * @example "string"
     */
    @Schema(name = "tipo", description = "Tipo de la variable", example = "string")
    private String tipo;

    /** 
     * @schema longitud
     * @description Longitud de la variable
     * @example 10
     */
    @Schema(name = "longitud", description = "Longitud de la variable", example = "10")
    private Integer longitud;

    /** 
     * @schema enteros
     * @description Número de enteros en la variable decimal
     * @example 5
     */
    @Schema(name = "enteros", description = "Número de enteros en la variable decimal", example = "5")
    private Integer enteros;

    /** 
     * @schema decimales
     * @description Número de decimales en la variable decimal
     * @example 2
     */
    @Schema(name = "decimales", description = "Número de decimales en la variable decimal", example = "2")
    private Integer decimales;

    /** 
     * @schema formatoFecha
     * @description Formato de fecha de la variable
     * @example "dd/MM/yyyy"
     */
    @Schema(name = "formatoFecha", description = "Formato de fecha de la variable", example = "dd/MM/yyyy")
    private String formatoFecha;

    /** 
     * @schema valorAlfanumerico
     * @description Valor alfanumérico de la variable
     * @example "abc123"
     */
    @Schema(name = "valorAlfanumerico", description = "Valor alfanumérico de la variable", example = "abc123")
    private String valorAlfanumerico;

    /** 
     * @schema valorEntero
     * @description Valor entero de la variable
     * @example 123
     */
    @Schema(name = "valorEntero", description = "Valor entero de la variable", example = "123")
    private Integer valorEntero;
    
    /** 
     * @schema valorDecimal
     * @description Valor decimal de la variable
     * @example 123.45
     */
    @Schema(name = "valorDecimal", description = "Valor decimal de la variable", example = "123.45")
    private BigDecimal valorDecimal;

    /** 
     * @schema valorFecha
     * @description Valor de fecha de la variable
     * @example "2024-06-15"
     */
    @Schema(name = "valorFecha", description = "Valor de fecha de la variable", example = "2024-06-15")
    private Date valorFecha;
}
