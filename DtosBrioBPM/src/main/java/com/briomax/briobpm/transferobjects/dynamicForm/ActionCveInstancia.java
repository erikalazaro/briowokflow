package com.briomax.briobpm.transferobjects.dynamicForm;

import java.io.Serializable;
import java.util.List;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Contiene acciones y clave de instancia", title = "ActionCveInstancia")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActionCveInstancia implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ArraySchema(schema = @Schema(name = "acciones", description = "Acciones a mostrar en la tarjeta.",
			implementation = Action.class))
	/** El atributo o variable acciones. */
	private List<Action> acciones;	
	
	private String cveInstancia;
	
}
