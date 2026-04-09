package com.briomax.briobpm.transferobjects.in;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
ColumnasAreaTrabajoTO * 
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Mar 21, 2024 6:05:58 PM Modificaciones:
 * @since JDK 1.8
 */
@Schema(description = "ColumnasAreaTrabajoTO", title = "ColumnasAreaTrabajoTO")
@Data
@Builder
public class ColumnasAreaTrabajoTO  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Schema(name = "numColumnas", description = "Numero de Columnas.", example = "1", implementation = Integer.class)
	private Integer numColumnas;
	
	@Schema(name = "secuencia", description = "Secuencia.", example = "1", implementation = Integer.class)
	private Integer secuencia;

	@Schema(name = "cveVariable", description = "Clave del Dato.", example = "CLAVE_DEL_DATO", implementation = String.class)
	private String cveVariable;

	@Schema(name = "visible", description = "Visible.", example = "SI", implementation = String.class)
	private String visible;

	@Schema(name = "etiqueta", description = "Etiqueta.", example = "ETIQUETA", implementation = String.class)
	private String etiqueta;

	@Schema(name = "anchoColumna", description = "Ancho de la Columna.", example = "1", implementation = BigDecimal.class)
	private Integer anchoColumna;

	@Schema(name = "tipoDato", description = "Tipo de Dato.", example = "TIPO_DE_DATO", implementation = String.class)
	private String tipoDato;

	@Schema(name = "colorDato", description = "Color del Dato.", example = "SI", implementation = String.class)
	private String colorDato;

	@Schema(name = "longitud", description = "Longitud.", example = "10", implementation = Integer.class)
	private Integer longitud;

	@Schema(name = "enteros", description = "Número de Enteros.", example = "2", implementation = Integer.class)
	private Integer enteros;

	@Schema(name = "decimales", description = "Número de Decimales.", example = "2", implementation = Integer.class)
	private Integer decimales;

	@Schema(name = "formatoFecha", description = "Formato de Fecha.", example = "YYYY-MM-DD", implementation = String.class)
	private String formatoFecha;

	@Schema(name = "tipoControl", description = "Tipo de Control.", example = "TEXTBOX", implementation = String.class)
	private String tipoControl;

	@Schema(name = "filtro", description = "Filtro.", example = "SI", implementation = String.class)
	private String filtro;

	@Schema(name = "tipoLista", description = "Tipo de Lista.", example = "CATALOGO_ETIQUETA", implementation = String.class)
	private String tipoLista;

	@Schema(name = "valorLista", description = "Valor de la Lista.", example = "VALOR_DE_LA_LISTA", implementation = String.class)
	private String valorLista;

	@Schema(name = "descripcionLista", description = "Descripción de la Lista.", example = "DESCRIPCION_DE_LA_LISTA", implementation = String.class)
	private String descripcionLista;

	@Schema(name = "tablaLista", description = "Tabla de la Lista.", example = "TABLA_DE_LA_LISTA", implementation = String.class)
	private String tablaLista;

	@Schema(name = "whereLista", description = "WHERE de la Lista.", example = "WHERE_DE_LA_LISTA", implementation = String.class)
	private String whereLista;

	@Schema(name = "multiseleccion", description = "Multiselección.", example = "SI", implementation = String.class)
	private String multiseleccion;

	@Schema(name = "opcionTodos", description = "Opción Todos.", example = "SI", implementation = String.class)
	private String opcionTodos;

	@Schema(name = "etiquetaTodos", description = "Etiqueta para Todos.", example = "ETIQUETA_PARA_TODOS", implementation = String.class)
	private String etiquetaTodos;

	@Schema(name = "cveListaFiltro", description = "Clave de la Lista Filtro.", example = "CLAVE_DE_LA_LISTA_FILTRO", implementation = String.class)
	private String cveListaFiltro;

}
