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
 * El objetivo de la Class VwDatoActividadTO.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion 14 Mar 2024 12:34:39 PM Modificaciones:
 * @since JDK 1.8
 */
@Schema(title = "VwDatoActividadTO", description = "Vista Dato de Area de TO.")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VwDatoActividadTO implements Serializable {

	/** serial Version UID. */
	private static final long serialVersionUID = 1L;
	
    /** Clave de la entidad. */
    @Schema(name = "cveEntidad", description = "Clave de la Entidad.", example = "12345", implementation = String.class)
    private String cveEntidad;

    /** Clave Instancia. */
    @Schema(name = "cveInstancia", description = "Clave nInstancia.", example = "", implementation = String.class)
    private String cveInstancia;
    
    /** Clave Instancia. */
    @Schema(name = "secuenciaNodo", description = "Secuencia Nodo.", example = "", implementation = Integer.class)
    private Integer secuenciaNodo;

    /** Orden del dato. */
    @Schema(name = "ordenDato", description = "Orden del Dato.", example = "1", implementation = Integer.class)
    private Integer ordenDato;
    
    /** Estado Actividad. */
    @Schema(name = "estadoActividad", description = "Estado Actividad.", example = "", implementation = String.class)
    private String estadoActividad;
    
    /** Estado Actividad. */
    @Schema(name = "valorPantalla", description = "Valor Pantalla.", example = "", implementation = String.class)
    private String valorPantalla;
    
    /** Estado Actividad. */
    @Schema(name = "valorBaseDatos", description = "Valor Base Datos.", example = "", implementation = String.class)
    private String valorBaseDatos;
}
