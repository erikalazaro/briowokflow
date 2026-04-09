package com.briomax.briobpm.transferobjects.in;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * El objetivo de la Class VariableSeccionTO.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Jun 11, 2024 10:20:00 AM Modificaciones:
 * @since JDK 1.8
 */
@Schema(title = "VariableSeccionTO", description = "Variable de una Sección.")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VariableSeccionTO implements Serializable {

    /** Serial Version UID. */
    private static final long serialVersionUID = 1L;

    /** El atributo o variable orden. */
    @Schema(name = "orden", description = "Orden de la Variable", example = "1", implementation = Integer.class)
    private Integer orden;

    /** El atributo o variable cveVariable. */
    @Schema(name = "cveVariable", description = "Clave de la Variable", example = "VAR001", implementation = String.class)
    private String cveVariable;

    /** El atributo o variable etiquetaVariable. */
    @Schema(name = "etiquetaVariable", description = "Etiqueta de la Variable", example = "Etiqueta", implementation = String.class)
    private String etiquetaVariable;

    /** El atributo o variable tipo. */
    @Schema(name = "tipo", description = "Tipo de la Variable", example = "CHAR", implementation = String.class)
    private String tipo;

    /** El atributo o variable longitud. */
    @Schema(name = "longitud", description = "Longitud de la Variable", example = "255")
    private Integer longitud;

    /** El atributo o variable enteros. */
    @Schema(name = "enteros", description = "Cantidad de Enteros", example = "10", implementation = Integer.class)
    private Integer enteros;

    /** El atributo o variable decimales. */
    @Schema(name = "decimales", description = "Cantidad de Decimales", example = "2", implementation = Integer.class)
    private Integer decimales;

    /** El atributo o variable formatoFecha. */
    @Schema(name = "formatoFecha", description = "Formato de la Fecha", example = "yyyy-MM-dd", implementation = String.class)
    private String formatoFecha;

    /** El atributo o variable requerida. */
    @Schema(name = "requerida", description = "Indicador de si es Requerida", example = "SI", implementation = String.class)
    private String requerida;

    /** El atributo o variable valorAlfanumerico. */
    @Schema(name = "valorAlfanumerico", description = "Valor Alfanumérico", example = "Valor", implementation = String.class)
    private String valorAlfanumerico;

    /** El atributo o variable valorEntero. */
    @Schema(name = "valorEntero", description = "Valor Entero", example = "100")
    private Integer valorEntero;

    /** El atributo o variable valorDecimal. */
    @Schema(name = "valorDecimal", description = "Valor Decimal", example = "100.50", implementation = BigDecimal.class)
    private BigDecimal valorDecimal;

    /** El atributo o variable valorFecha. */
    @Schema(name = "valorFecha", description = "Valor Fecha", example = "2024-06-11")
    private Date valorFecha;

    /** El atributo o variable tipoControl. */
    @Schema(name = "tipoControl", description = "Tipo de Control", example = "TEXTBOX", implementation = String.class)
    private String tipoControl;

    /** El atributo o variable tieneEtiqueta. */
    @Schema(name = "tieneEtiqueta", description = "Indicador de si Tiene Etiqueta", example = "SI", implementation = String.class)
    private String tieneEtiqueta;

    /** El atributo o variable soloConsulta. */
    @Schema(name = "soloConsulta", description = "Indicador de si es Solo para Consulta", example = "SI", implementation = String.class)
    private String soloConsulta;

    /** El atributo o variable valorImagen. */
    @Schema(name = "soloConsulta", description = "Indicador de si es Solo para Consulta", example = "SI")
    private byte[] valorImagen;
}
