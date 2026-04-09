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
 * El objetivo de la Class DatosAreaTrabajoTO.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion 13 Mar 2024 12:34:39 PM Modificaciones:
 * @since JDK 1.8
 */
@Schema(title = "DatoAreaTrabajoTO", description = "Dato de Area de Trabajo.")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DatoAreaTrabajoTO implements Serializable {

	/** serial Version UID. */
	private static final long serialVersionUID = 1L;
	
    /** Clave de la entidad. */
    @Schema(name = "cveEntidad", description = "Clave de la Entidad.", example = "12345", implementation = String.class)
    private String cveEntidad;

    /** Clave del proceso. */
    @Schema(name = "cveProceso", description = "Clave del Proceso.", example = "12345", implementation = String.class)
    private String cveProceso;

    /** Versión. */
    @Schema(name = "version", description = "Versión.", example = "1.0", implementation = BigDecimal.class)
    private BigDecimal version;

    /** Clave de área de trabajo. */
    @Schema(name = "cveAreaTrabajo", description = "Clave de Área de Trabajo.", example = "12345", implementation = String.class)
    private String cveAreaTrabajo;

    /** Secuencia del dato. */
    @Schema(name = "secuenciaDato", description = "Secuencia del Dato.", example = "1", implementation = Integer.class)
    private Integer secuenciaDato;

    /** Orden del dato. */
    @Schema(name = "ordenDato", description = "Orden del Dato.", example = "1", implementation = Integer.class)
    private Integer ordenDato;

    /** Origen del dato. */
    @Schema(name = "origenDato", description = "Origen del Dato.", example = "Origen", implementation = String.class)
    private String origenDato;

    /** Etiqueta. */
    @Schema(name = "etiqueta", description = "Etiqueta.", example = "Etiqueta", implementation = String.class)
    private String etiqueta;

    /** Visible. */
    @Schema(name = "visible", description = "Visible.", example = "Si", implementation = String.class)
    private String visible;

    /** Color. */
    @Schema(name = "color", description = "Color.", example = "Rojo", implementation = String.class)
    private String color;

    /** Filtro. */
    @Schema(name = "filtro", description = "Filtro.", example = "Filtro", implementation = String.class)
    private String filtro;

    /** Tipo de control. */
    @Schema(name = "tipoControl", description = "Tipo de Control.", example = "Control", implementation = String.class)
    private String tipoControl;

    /** Tipo de lista. */
    @Schema(name = "tipoLista", description = "Tipo de Lista.", example = "Lista", implementation = String.class)
    private String tipoLista;

    /** Clave de lista de filtro. */
    @Schema(name = "cveListaFiltro", description = "Clave de Lista de Filtro.", example = "12345", implementation = String.class)
    private String cveListaFiltro;

    /** Columna de valor. */
    @Schema(name = "columnaValor", description = "Columna de Valor.", example = "Valor", implementation = String.class)
    private String columnaValor;

    /** Columna de descripción. */
    @Schema(name = "columnaDescripcion", description = "Columna de Descripción.", example = "Descripción", implementation = String.class)
    private String columnaDescripcion;

    /** Tabla de lista. */
    @Schema(name = "tablaLista", description = "Tabla de Lista.", example = "Tabla", implementation = String.class)
    private String tablaLista;

    /** Where de lista. */
    @Schema(name = "whereLista", description = "Where de Lista.", example = "Where", implementation = String.class)
    private String whereLista;

    /** Selección múltiple. */
    @Schema(name = "multiSeleccion", description = "Selección Múltiple.", example = "Sí", implementation = String.class)
    private String multiSeleccion;

    /** Opción de todos. */
    @Schema(name = "opcionTodos", description = "Opción de Todos.", example = "Sí", implementation = String.class)
    private String opcionTodos;

    /** Etiqueta de todos. */
    @Schema(name = "etiquetaTodos", description = "Etiqueta de Todos.", example = "Todos", implementation = String.class)
    private String etiquetaTodos;

    /** Ancho de columna. */
    @Schema(name = "anchoColumna", description = "Ancho de Columna.", example = "100", implementation = BigDecimal.class)
    private BigDecimal anchoColumna;

    /** Clave variable.. */
    @Schema(name = "cveVariable", description = "Clave variable.", example = "", implementation = String.class)
    private String cveVariable;

    /** Agregacion. */
    @Schema(name = "agregacion", description = "Agregacion.", example = "", implementation = String.class)
    private String agregacion;

    /** Clave Instancia. */
    @Schema(name = "cveInstancia", description = "Clave Instancia.", example = "", implementation = String.class)
    private String cveInstancia;

    /** Secuencia Nodo. */
    @Schema(name = "secuenciaNodo", description = "Secuencia Nodo.", example = "", implementation = Integer.class)
    private Integer secuenciaNodo;

    /** Estado. */
    @Schema(name = "estado", description = "Estado.", example = "", implementation = String.class)
    private String estado;
    
    /** Clave Dato Proceso Nodo. */
    @Schema(name = "cveDatoProcesoNodo", description = "Clave Dato Proceso Nodo.", example = "", implementation = String.class)
    private String cveDatoProcesoNodo;
    
    /** Clave Entidad Localidad. */
    @Schema(name = "cveEntidadLocalidad", description = "Clave Entidad Localidad.", example = "", implementation = String.class)
    private String cveEntidadLocalidad;

    /** Clave Localidad. */
    @Schema(name = "cveLocalidad", description = "Clave Localidad.", example = "", implementation = String.class)
    private String cveLocalidad;
    
    /** Clave Variable Localidad. */
    @Schema(name = "cveVariableLocalidad", description = "Clave Variable Localidad.", example = "", implementation = String.class)
    private String cveVariableLocalidad;
    
    /** Clave Variable Entidad. */
    @Schema(name = "cveVariableEntidad", description = "Clave Variable Entidad.", example = "", implementation = String.class)
    private String cveVariableEntidad;
    
    /** Clave Variable Sistema. */
    @Schema(name = "cveVariableSistema", description = "Clave Variable Sistema.", example = "", implementation = String.class)
    private String cveVariableSistema;
    
    private String tipoTarjeta;
}
