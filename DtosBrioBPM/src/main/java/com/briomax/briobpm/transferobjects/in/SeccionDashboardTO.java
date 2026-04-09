package com.briomax.briobpm.transferobjects.in;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * El objetivo de la Interface SeccionDashboardTO.java es ...
 * 
 * @autor Alexis Zamora
 * @version 1.0 Fecha de creación Nov 14, 2024 3:30:00 PM Modificaciones:
 * @since JDK 1.8
 */
@Schema(description = "SeccionDashboardTO", title = "DTO que guarda los datos de seccion de dashboard")
@Data
@Builder
public class SeccionDashboardTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(name = "cveDashboard", description = "Clave del dashboard.", example = "CVE_DASHBOARD", implementation = String.class)
    private String cveDashboard;

    @Schema(name = "secuenciaSeccion", description = "Secuencia de la sección.", example = "1", implementation = Integer.class)
    private Integer secuenciaSeccion;

    @Schema(name = "ordenSeccion", description = "Orden de la sección.", example = "1", implementation = Integer.class)
    private Integer ordenSeccion;

    @Schema(name = "tituloSeccion", description = "Título de la sección.", example = "Cumplimiento Global", implementation = String.class)
    private String tituloSeccion;

    @Schema(name = "secuenciaSubSeccion", description = "Secuencia de la sub-sección.", example = "1", implementation = Integer.class)
    private Integer secuenciaSubSeccion;

    @Schema(name = "ordenSubSeccion", description = "Orden de la sub-sección.", example = "1", implementation = Integer.class)
    private Integer ordenSubSeccion;

    @Schema(name = "tituloSubSeccion", description = "Título de la sub-sección.", example = "Periodo Anterior", implementation = String.class)
    private String tituloSubSeccion;

    @Schema(name = "secuenciaSubSubSeccion", description = "Secuencia de la sub-sub-sección.", example = "1", implementation = Integer.class)
    private Integer secuenciaSubSubSeccion;

    @Schema(name = "ordenSubSubSeccion", description = "Orden de la sub-sub-sección.", example = "1", implementation = Integer.class)
    private Integer ordenSubSubSeccion;

    @Schema(name = "tituloSubSubSeccion", description = "Título de la sub-sub-sección.", example = "Cumplimiento de Contratistas", implementation = String.class)
    private String tituloSubSubSeccion;

    @Schema(name = "ordenSerie", description = "Orden de la serie.", example = "1", implementation = Integer.class)
    private Integer ordenSerie;
    
    @Schema(name = "numeroSerie", description = "Numero Serie.", example = "1", implementation = Integer.class)
    private Integer numeroSerie;

    @Schema(name = "tituloSerie", description = "Título de serie.", example = "Cumplimiento de Contratistas", implementation = String.class)
    private String tituloSerie;
    
    @Schema(name = "escala", description = "Escala.", example = "1", implementation = Integer.class)
    private Integer escala;
    
    @Schema(name = "tipoPresentacion", description = "Tipo Presentación.", example = "Cumplimiento de Contratistas", implementation = String.class)
    private String tipoPresentacion;
    
    @Schema(name = "etiquetaEjeX", description = "Etiqueta Eje X", implementation = String.class)
    private String etiquetaEjeX;
    
    @Schema(name = "etiquetaEjeY1", description = "Etiqueta Eje Y1", implementation = String.class)
    private String etiquetaEjeY1;
    
    @Schema(name = "etiquetaEjeY2", description = "Etiqueta Eje Y2", implementation = String.class)
    private String etiquetaEjeY2;
        
    @Schema(name = "tituloAcotaciones", description = "Titulo Acotaciones", implementation = String.class)
    private String tituloAcotaciones;
}
