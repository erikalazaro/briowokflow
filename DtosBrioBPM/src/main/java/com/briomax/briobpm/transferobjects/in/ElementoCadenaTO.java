package com.briomax.briobpm.transferobjects.in;

import com.briomax.briobpm.transferobjects.core.ITransferObject;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * El objetivo de la Class DocumentoNodoTO.java es ...
 * 
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Jun 10, 2024 11:09:51 AM Modificaciones:
 * @since JDK 1.8 
 */
@Schema(description = "ElementoCadenaTO", title = "ElementoCadenaTO.")
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ElementoCadenaTO implements ITransferObject {

	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;

	/** La tabla o tablas para obtener encontrado. */
	@Schema(name = "encontro", description = "Indica si el elemento se encontró.", example = "SI")
	String encontro;

	/** La tabla o tablas para obtener la posicionFinal. */
	@Schema(name = "posicionFinal", description = "Posición final del elemento de la cadena.", example = "")
	Integer posicionFinal;

	/** La tabla o tablas para obtener elemento. */
	@Schema(name = "elemento", description = "Elemento Resultante.", example = "")
	String elemento;

}
