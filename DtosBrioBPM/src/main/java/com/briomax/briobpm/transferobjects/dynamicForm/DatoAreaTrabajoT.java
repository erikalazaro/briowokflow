package com.briomax.briobpm.transferobjects.dynamicForm;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Datos de la tabla DATO_AREA_TRABAJO.", title = "DatoAreaTrabajo")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DatoAreaTrabajoT implements Serializable {

    private static final long serialVersionUID = 1L;
    

    /** El atributo o variable secuenciaDato. */
    @Schema(name = "secuenciaDato", description = "Muestra el valor de la columna SECUENCIA_DATO de la tabla DATO_AREA_TRABAJO",
            example = "secuencia_dato", implementation = Integer.class)
    private Integer secuenciaDato;

    /** El atributo o variable origenDato. */
    @Schema(name = "origenDato", description = "Muestra el valor de la columna ORIGEN_DATO de la tabla DATO_AREA_TRABAJO",
            example = "origen_dato", implementation = String.class)
    private String origenDato;

    /** El atributo o variable etiqueta. */
    @Schema(name = "etiqueta", description = "Muestra el valor de la columna ETIQUETA de la tabla DATO_AREA_TRABAJO",
            example = "etiqueta", implementation = String.class)
    private String etiqueta;

    /** El atributo o variable cveDatoProcesoNodo. */
    @Schema(name = "cveDatoProcesoNodo", description = "Muestra la calve del dato del proceso de la instancia",
            example = "DA.FECHA_CREACION_PROCESO", implementation = String.class)
    private String cveDatoProcesoNodo;
    
    /** El atributo o variable visible. */
    @Schema(name = "cveVariable", description = "Muestra la clave Variable de DATO_AT_VARIABLE_PROCESO",
            example = "VPRO_01_FIEL_SERV_ASIG", implementation = String.class)
    private String cveVariable;
    
    
    /** El atributo o variable visible. */
    @Schema(name = "visible", description = "Muestra el valor de la columna VISIBLE de la tabla DATO_AREA_TRABAJO",
    		example = "visible", implementation = String.class)
    private String visible;
    
    private String valorPantalla;
    
    private String tituloTarjeta;
}

