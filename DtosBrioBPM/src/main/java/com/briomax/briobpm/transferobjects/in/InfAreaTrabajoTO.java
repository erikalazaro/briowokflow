package com.briomax.briobpm.transferobjects.in;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * El objetivo de la Class InfAreaTrabajoTO.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion 07 Mar 2024 12:34:39 PM Modificaciones:
 * @since JDK 1.8
 */
@Schema(title = "InfAreaTrabajoTO", description = "Datos de Información de Area de Trabajo.")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InfAreaTrabajoTO implements Serializable {

	/** serial Version UID. */
	private static final long serialVersionUID = 1L;

    /** Clave de la instancia. */
    @Schema(name = "cveInstancia", description = "Clave de la Instancia.", example = "12345", implementation = String.class)
    private String cveInstancia;

    /** Secuencia del nodo. */
    @Schema(name = "secuenciaNodo", description = "Secuencia del Nodo.", example = "1", implementation = Integer.class)
    private Integer secuenciaNodo;

    /** Situación. */
    @Schema(name = "situacion", description = "Situación.", example = "Activo", implementation = String.class)
    private String situacion;

    /** Clave del usuario que bloquea. */
    @Schema(name = "cveUsuarioBloquea", description = "Clave del Usuario que Bloquea.", example = "usuario1", implementation = String.class)
    private String cveUsuarioBloquea;

    /** Secuencia del dato de estilo. */
    @Schema(name = "secuenciaDatoEstilo", description = "Secuencia del Dato de Estilo.", example = "", implementation = String.class)
    private String secuenciaDatoEstilo;

    /** Estilo del dato. */
    @Schema(name = "estiloDato", description = "Estilo del Dato.", example = "Estilo1", implementation = String.class)
    private String estiloDato;

    /** Habilitar tomar. */
    @Schema(name = "habilitarTomar", description = "Habilitar Tomar.", example = "Si", implementation = String.class)
    private String habilitarTomar;

    /** Etiqueta tomar. */
    @Schema(name = "etiquetaTomar", description = "Etiqueta Tomar.", example = "Tomar", implementation = String.class)
    private String etiquetaTomar;

    /** Habilitar liberar. */
    @Schema(name = "habilitarLiberar", description = "Habilitar Liberar.", example = "No", implementation = String.class)
    private String habilitarLiberar;

    /** Etiqueta liberar. */
    @Schema(name = "etiquetaLiberar", description = "Etiqueta Liberar.", example = "Liberar", implementation = String.class)
    private String etiquetaLiberar;

    /** Habilitar ejecutar. */
    @Schema(name = "habilitarEjecutar", description = "Habilitar Ejecutar.", example = "Si", implementation = String.class)
    private String habilitarEjecutar;

    /** Etiqueta ejecutar. */
    @Schema(name = "etiquetaEjecutar", description = "Etiqueta Ejecutar.", example = "Ejecutar", implementation = String.class)
    private String etiquetaEjecutar;

    /** Habilitar terminar. */
    @Schema(name = "habilitarTerminar", description = "Habilitar Terminar.", example = "No", implementation = String.class)
    private String habilitarTerminar;

    /** Etiqueta terminar. */
    @Schema(name = "etiquetaTerminar", description = "Etiqueta Terminar.", example = "Terminar", implementation = String.class)
    private String etiquetaTerminar;

    /** Habilitar consultar. */
    @Schema(name = "habilitarConsultar", description = "Habilitar Consultar.", example = "Si", implementation = String.class)
    private String habilitarConsultar;

    /** Etiqueta consultar. */
    @Schema(name = "etiquetaConsultar", description = "Etiqueta Consultar.", example = "Consultar", implementation = String.class)
    private String etiquetaConsultar;

    /** Habilitar cancelar. */
    @Schema(name = "habilitarCancelar", description = "Habilitar Cancelar.", example = "No", implementation = String.class)
    private String habilitarCancelar;

    /** Etiqueta cancelar. */
    @Schema(name = "etiquetaCancelar", description = "Etiqueta Cancelar.", example = "Cancelar", implementation = String.class)
    private String etiquetaCancelar;

    /** Habilitar bitacora. */
    @Schema(name = "habilitarBitacora", description = "Habilitar Bitacora.", example = "Si", implementation = String.class)
    private String habilitarBitacora;

    /** Etiqueta bitacora. */
    @Schema(name = "etiquetaBitacora", description = "Etiqueta Bitacora.", example = "Bitacora", implementation = String.class)
    private String etiquetaBitacora;

    /** Datos de la actividad. */
    @Schema(name = "datosActividad", description = "Datos de la Actividad.", example = "Actividad", implementation = String.class)
    private String datosActividad;


}
