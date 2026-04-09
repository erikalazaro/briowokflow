package com.briomax.briobpm.transferobjects.dynamicForm;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Grafico", title = "grafico")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Grafico implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** El atributo o variable action. */
	@Schema(name = "TIPO_AREA", description = "TIPO DE AREA", example = "TARJETA O GRID")
	 String tipoArea;

}
