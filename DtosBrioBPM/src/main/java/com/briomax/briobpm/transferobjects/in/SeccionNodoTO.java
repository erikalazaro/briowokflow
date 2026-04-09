package com.briomax.briobpm.transferobjects.in;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * El objetivo de la Interface SeccionNodoTO.java es ...
 * 
 * @autor Alexis Zamora
 * @version 1.0 Fecha de creación Jun 13, 2024 3:30:00 PM Modificaciones:
 * @since JDK 1.8
 */
@Schema(description = "SeccionNodoTO", title = "SeccionNodoTO")
@Data
@Builder
public class SeccionNodoTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(name = "orden", description = "Orden de la sección.", example = "1", implementation = Integer.class)
    private Integer orden;

    @Schema(name = "cveSeccion", description = "Clave de la sección.", example = "CVE_SECCION", implementation = String.class)
    private String cveSeccion;

    @Schema(name = "etiquetaSeccion", description = "Etiqueta de la sección.", example = "ETIQUETA_SECCION", implementation = String.class)
    private String etiquetaSeccion;

    @Schema(name = "tipoPresentacion", description = "Tipo de presentación.", example = "TIPO_PRESENTACION", implementation = String.class)
    private String tipoPresentacion;

    @Schema(name = "botonCrearRenglon", description = "Botón para crear renglón.", example = "SI", implementation = String.class)
    private String botonCrearRenglon;

    @Schema(name = "etiquetaCrearRenglon", description = "Etiqueta para crear renglón.", example = "CREAR", implementation = String.class)
    private String etiquetaCrearRenglon;

    @Schema(name = "botonEliminarRenglon", description = "Botón para eliminar renglón.", example = "SI", implementation = String.class)
    private String botonEliminarRenglon;

    @Schema(name = "etiquetaEliminarRenglon", description = "Etiqueta para eliminar renglón.", example = "ELIMINAR", implementation = String.class)
    private String etiquetaEliminarRenglon;

    @Schema(name = "contenido", description = "Contenido de la sección.", example = "CONTENIDO", implementation = String.class)
    private String contenido;
 
}
