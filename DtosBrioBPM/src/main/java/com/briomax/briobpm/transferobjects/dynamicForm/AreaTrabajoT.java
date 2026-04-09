package com.briomax.briobpm.transferobjects.dynamicForm;

import java.io.Serializable;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Area de Trabajo TO.", title = "AreaTrabajoT")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AreaTrabajoT implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	/** El atributo o variable buttons. */
	@Schema(name = "tarjetas_Areas_Trabajo", description = "Areas Trabajo.")
	private List<TarjetaAreaTrabajoT> tarjetas_Areas_Trabajo;

}
