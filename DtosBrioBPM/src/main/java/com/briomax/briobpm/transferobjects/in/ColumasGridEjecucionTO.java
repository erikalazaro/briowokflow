package com.briomax.briobpm.transferobjects.in;

import java.math.BigDecimal;

import com.briomax.briobpm.transferobjects.core.ITransferObject;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * El objetivo de la Class ColumasGridEjecucionTO.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Abr 12, 2024 11:09:51 AM Modificaciones:
 * @since JDK 1.8
 */
@Schema(description = "ColumasGridEjecucionTO", title = "ColumasGridEjecucionTO.")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ColumasGridEjecucionTO implements ITransferObject {

	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;
	
	/** La tabla o tablas para obtener la clave seccion. */
	@Schema(name = "cveSeccion", description = "Clave Seccion.", example = "")
	String cveSeccion;
	
	/** La cláusula WHERE para obtener numero columnas. */
	@Schema(name = "numColumnas", description = "Numero Columnas.", example = "")
	Integer numColumnas;
	
	/** La secuencia de la columna. */
	@Schema(name = "secuencia", description = "La secuencia de la columna.", example = "1")
	Integer secuencia;

	/** La clave de la variable. */
	@Schema(name = "cveVariable", description = "Clave de la variable.", example = "VAR001")
	String cveVariable;

	/** La etiqueta de la columna. */
	@Schema(name = "etiqueta", description = "Etiqueta de la columna.", example = "Nombre")
	String etiqueta;

	/** El ancho de la columna. */
	@Schema(name = "anchoColumna", description = "Ancho de la columna.", example = "80")
	Integer anchoColumna;

	/** El tipo de dato de la columna. */
	@Schema(name = "tipoDato", description = "Tipo de dato de la columna.", example = "ENTERO")
	String tipoDato;

	/** La longitud del dato. */
	@Schema(name = "longitud", description = "Longitud del dato.", example = "80")
	Integer longitud;

	/** La cantidad de enteros. */
	@Schema(name = "enteros", description = "Cantidad de enteros.", example = "10")
	BigDecimal enteros;

	/** La cantidad de decimales. */
	@Schema(name = "decimales", description = "Cantidad de decimales.", example = "2")
	BigDecimal decimales;

	/** El formato de fecha. */
	@Schema(name = "formatoFecha", description = "Formato de fecha.", example = "yyyy-MM-dd")
	String formatoFecha;

	/** El tipo de interacción (ENTRADA o SALIDA). */
	@Schema(name = "tipoInteraccion", description = "Tipo de interacción (ENTRADA o SALIDA).", example = "ENTRADA")
	String tipoInteraccion;

	/** Indica si se envía a grabar a base de datos desde el formulario (SI o NO). */
	@Schema(name = "envioGrabar", description = "Indica si se envía a grabar a base de datos desde el formulario (SI o NO).", example = "SI")
	String envioGrabar;

	/** Indica si el dato es visible o no (SI o NO). */
	@Schema(name = "visible", description = "Indica si el dato es visible o no (SI o NO).", example = "SI")
	String visible;

	/** El tipo de control (TEXTBOX, TEXTAREA, COMBOBOX, etc.). */
	@Schema(name = "tipoControl", description = "Tipo de control (TEXTBOX, TEXTAREA, COMBOBOX, etc.).", example = "TEXTBOX")
	String tipoControl;

	/** La fórmula asociada. */
	@Schema(name = "formula", description = "Fórmula asociada.", example = "SUMA(A1:A10)")
	String formula;

	/** La función asociada. */
	@Schema(name = "funcion", description = "Función asociada.", example = "SUM")
	String funcion;

	/** El tipo de lista (TABLA u otro). */
	@Schema(name = "tipoLista", description = "Tipo de lista (TABLA u otro).", example = "TABLA")
	String tipoLista;

	/** El valor de la lista (la columna que se toma como valor). */
	@Schema(name = "valorLista", description = "Valor de la lista (la columna que se toma como valor).", example = "ID")
	String valorLista;

	/** La descripción de la lista (la columna que se visualiza por el usuario). */
	@Schema(name = "descripcionLista", description = "Descripción de la lista (la columna que se visualiza por el usuario).", example = "Nombre")
	String descripcionLista;

	/** La tabla o tablas para obtener la lista. */
	@Schema(name = "tablaLista", description = "Tabla o tablas para obtener la lista.", example = "VW_TABLA")
	String tablaLista;

	/** La cláusula WHERE para obtener la lista. */
	@Schema(name = "whereLista", description = "Cláusula WHERE para obtener la lista.", example = "activo = true")
	String whereLista;
	
	/** La longitud del dato. */
	@Schema(name = "numeroColumna", description = "Numero de Columna en donde va el dato", example = "80")
	Integer numeroColumna;
	
	/** La longitud del dato. */
	@Schema(name = "renglon", description = "Numero de renglon en donde va el dato", example = "80")
	Integer renglon;
}
