package com.briomax.briobpm.transferobjects.in;

import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

/**
 * El objetivo de la Class VwDatoActividadTO.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion 14 Mar 2024 12:34:39 PM Modificaciones:
 * @since JDK 1.8
 */
@Schema(description = "EstatusAccionesTO", title = "EstatusAccionesTO")
@Data
@Builder
public class EstatusAccionesTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "Indica si se habilita la acción 'TOMAR' (SI/NO)", example = "SI", implementation = String.class)
    private String habilitarTomar;

    @Schema(description = "Etiqueta de la acción 'TOMAR'", example = "Tomar", implementation = String.class)
    private String etiquetaTomar;

    @Schema(description = "Indica si se habilita la acción 'LIBERAR' (SI/NO)", example = "SI", implementation = String.class)
    private String habilitarLiberar;

    @Schema(description = "Etiqueta de la acción 'LIBERAR'", example = "Liberar", implementation = String.class)
    private String etiquetaLiberar;

    @Schema(description = "Indica si se habilita la acción 'EJECUTAR' (SI/NO)", example = "SI", implementation = String.class)
    private String habilitarEjecutar;

    @Schema(description = "Etiqueta de la acción 'EJECUTAR'", example = "Ejecutar", implementation = String.class)
    private String etiquetaEjecutar;

    @Schema(description = "Indica si se habilita la acción 'TERMINAR' (SI/NO)", example = "SI", implementation = String.class)
    private String habilitarTerminar;

    @Schema(description = "Etiqueta de la acción 'TERMINAR'", example = "Terminar", implementation = String.class)
    private String etiquetaTerminar;

    @Schema(description = "Indica si se habilita la acción 'CONSULTAR' (SI/NO)", example = "SI", implementation = String.class)
    private String habilitarConsultar;

    @Schema(description = "Etiqueta de la acción 'CONSULTAR'", example = "Consultar", implementation = String.class)
    private String etiquetaConsultar;

    @Schema(description = "Indica si se habilita la acción 'CANCELAR' (SI/NO)", example = "SI", implementation = String.class)
    private String habilitarCancelar;

    @Schema(description = "Etiqueta de la acción 'CANCELAR'", example = "Cancelar", implementation = String.class)
    private String etiquetaCancelar;

    @Schema(description = "Indica si se habilita la acción 'BITÁCORA' (SI/NO)", example = "SI", implementation = String.class)
    private String habilitarBitacora;

    @Schema(description = "Etiqueta de la acción 'BITÁCORA'", example = "Bitácora", implementation = String.class)
    private String etiquetaBitacora;

    @Schema(description = "Indica si se habilita la acción 'BITÁCORA' (SI/NO)", example = "SI", implementation = String.class)
    private String habilitarGuardar;

    @Schema(description = "Etiqueta de la acción 'BITÁCORA'", example = "Bitácora", implementation = String.class)
    private String etiquetaGuardar;
    
    @NonNull
    @Schema(description = "Tipo de excepción 'OK', 'ERROR', 'AVISO'", example = "OK", implementation = String.class)
    private String tipoExcepcion;

    @NonNull
    @Schema(description = "Mensaje", example = "Mensaje de error", implementation = String.class)
    private String mensaje;

}
