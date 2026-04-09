package com.briomax.briobpm.transferobjects.in;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

/**
 * El objetivo de la Class VwDatoActividadTO.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion 19 Mar 2024 12:34:39 PM Modificaciones:
 * @since JDK 1.8
 */
@Schema(description = "EstatusAtributosDatoTO", title = "EstatusAtributosDatoTO")
@Data
@Builder
public class EstatusAtributosDatoTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NonNull
    @Schema(description = "Tipo de excepción 'OK', 'ERROR', 'AVISO'", example = "OK", implementation = String.class)
    private String tipoExcepcion;
    
    @NonNull
    @Schema(description = "Mensaje", example = "Mensaje de error", implementation = String.class)
    private String mensaje;
    
    @Schema(description = "Tipo de Dato", example = "", implementation = String.class)
    private String tipoDato;

    @Schema(description = "Longitud", example = "", implementation = Integer.class)
    private Integer longitud;

    @Schema(description = "Enteros", example = "", implementation = Integer.class)
    private Integer enteros;
    
    @Schema(description = "Decimales", example = "", implementation = BigDecimal.class)
    private Integer decimales;
    
    @Schema(description = "Formato Fecha", example = "", implementation = String.class)
    private String formatoFecha;

}
