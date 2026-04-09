package com.briomax.briobpm.transferobjects.in;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * El objetivo de la Class DatoDashboardTO.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion 20 Nov 2024 12:34:39 PM Modificaciones:
 * @since JDK 1.8
 */
@Schema(title = "DatoDashboardTO", description = "Dato Dashboard.")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DatoDashboardTO implements Serializable {

	 /** serial Version UID. */
    @Schema(name = "serialVersionUID", description = "Identificador único para la serialización.", example = "1L", implementation = Long.class)
    private static final long serialVersionUID = 1L;

    @Schema(name = "consecutivo", description = "Número consecutivo único.", example = "1", implementation = Integer.class)
    private Integer consecutivo;

    @Schema(name = "secuenciaSeccion", description = "Secuencia única de la sección.", example = "101", implementation = Integer.class)
    private Integer secuenciaSeccion;

    @Schema(name = "tituloSeccion", description = "Título descriptivo de la sección.", example = "Sección Principal", implementation = String.class)
    private String tituloSeccion;

    @Schema(name = "secuenciaSubSeccion", description = "Secuencia única de la sub-sección.", example = "102", implementation = Integer.class)
    private Integer secuenciaSubSeccion;

    @Schema(name = "tituloSubSeccion", description = "Título descriptivo de la sub-sección.", example = "Sub-Sección A", implementation = String.class)
    private String tituloSubSeccion;

    @Schema(name = "secuenciaSubSubSeccion", description = "Secuencia única de la sub-sub-sección.", example = "103", implementation = Integer.class)
    private Integer secuenciaSubSubSeccion;

    @Schema(name = "tituloSubSubSeccion", description = "Título descriptivo de la sub-sub-sección.", example = "Sub-Sub-Sección B", implementation = String.class)
    private String tituloSubSubSeccion;

    @Schema(name = "numeroSerie", description = "Número de serie asociado.", example = "1001", implementation = Integer.class)
    private Integer numeroSerie;

    @Schema(name = "tituloSerie", description = "Título descriptivo del número de serie.", example = "Serie A", implementation = String.class)
    private String tituloSerie;

    @Schema(name = "datoEtiquetaSerie", description = "Etiqueta descriptiva asociada al número de serie.", example = "Etiqueta de la Serie", implementation = String.class)
    private String datoEtiquetaSerie;
    
    @Schema(name = "escala", description = "Escala.", example = "1", implementation = Integer.class)
    private Integer escala;
    
    @Schema(name = "factorConversionEscala2", description = "Factor de conversion Escala.", example = "1.000")
    private BigDecimal factorConversionEscala2;
    
    @Schema(name = "tipoPresentacion", description = "Tipo Presentacion Grafica.", example = "PIE", implementation = String.class)
    private String tipoPresentacion;
}
