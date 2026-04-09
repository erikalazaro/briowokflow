package com.briomax.briobpm.transferobjects.in;

import java.io.Serializable;

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
@Schema(description = "EstatusAtributosMonedaTO", title = "EstatusAtributosMonedaTO")
@Data
@Builder
public class EstatusAtributosMonedaTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NonNull
    @Schema(description = "Tipo de excepción 'OK', 'ERROR', 'AVISO'", example = "OK", implementation = String.class)
    private String tipoExcepcion;
    
    @NonNull
    @Schema(description = "Mensaje", example = "Mensaje de error", implementation = String.class)
    private String mensaje;

    @Schema(description = "Clave Dato Moneda", example = "", implementation = String.class)
    private String cveDatoMoneda;
    
    @Schema(description = "Clave Moneda", example = "", implementation = String.class)
    private String cveMoneda;
    
    @Schema(description = "Tipo Clave Moneda", example = "", implementation = String.class)
    private String tipoCveMoneda;
    
    @Schema(description = "Tipo Control Moneda", example = "", implementation = String.class)
    private String tipoControlMoneda;
    
    @Schema(description = "Lonngitud Moneda", example = "", implementation = Integer.class)
    private Integer longitudMoneda;
    
    @Schema(description = "Etiqueta Moneda", example = "", implementation = String.class)
    private String etiquetaMoneda;
}
