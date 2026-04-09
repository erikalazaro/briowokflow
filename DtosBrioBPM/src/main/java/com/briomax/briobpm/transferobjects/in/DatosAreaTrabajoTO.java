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
 * El objetivo de la Class DatosAreaTrabajoTO.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion 07 Mar 2024 12:34:39 PM Modificaciones:
 * @since JDK 1.8
 */
@Schema(title = "DatosAreaTrabajoTO", description = "Datos de Area de Trabajo.")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DatosAreaTrabajoTO implements Serializable {

	/** serial Version UID. */
	private static final long serialVersionUID = 1L;

	  /** Clave de la instancia. */
    @Schema(name = "cveInstancia", description = "Clave de la Instancia.", example = "12345", implementation = String.class)
    private String cveInstancia;

    /** Secuencia del nodo. */
    @Schema(name = "secuenciaNodo", description = "Secuencia del Nodo.", example = "1", implementation = Integer.class)
    private Integer secuenciaNodo;

    /** Secuencia del dato. */
    @Schema(name = "secuenciaDato", description = "Secuencia del Dato.", example = "1", implementation = Integer.class)
    private Integer secuenciaDato;

    /** Orden del dato. */
    @Schema(name = "ordenDato", description = "Orden del Dato.", example = "1", implementation = Integer.class)
    private Integer ordenDato;

    /** Clave del dato. */
    @Schema(name = "cveDato", description = "Clave del Dato.", example = "dato123", implementation = String.class)
    private String cveDato;

    /** Situación del dato. */
    @Schema(name = "situacion", description = "Situación del Dato.", example = "Activo", implementation = String.class)
    private String situacion;

    /** Valor en la base de datos. */
    @Schema(name = "valorBaseDatos", description = "Valor en la Base de Datos.", example = "12345", implementation = String.class)
    private String valorBaseDatos;

    /** Valor en pantalla. */
    @Schema(name = "valorPantalla", description = "Valor en Pantalla.", example = "Valor", implementation = String.class)
    private String valorPantalla;
    
    /** Usuario Bloquea. */
    @Schema(name = "usuarioBloquea", description = "Ubuario Bloquea.", example = "Valor", implementation = String.class)
    private String usuarioBloquea;
    
    /** Usuario Bloquea. */
    @Schema(name = "datosActividad", description = "datos Actividad.", example = "Valor", implementation = String.class)
    private String datosActividad;
    
    /** Usuario Bloquea. */
    @Schema(name = "fechaHoraLimite", description = "fecha Hora Limite.", example = "true", implementation = Boolean.class)
    private boolean fechaHoraLimite; 
    
    private int ordenDatoLimite;
    
    private String tipoTarjeta;
    
    private String cveDatoProcesoNodo;
}
