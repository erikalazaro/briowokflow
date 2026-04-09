package com.briomax.briobpm.transferobjects.dynamicForm;

import java.io.Serializable;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "datos que deben contener las tarjetas", title = "Tarjetas")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Tarjetas implements Serializable{
	
	private static final long serialVersionUID = 1L;

	/** El atributo o variable buttons. */
	@Schema(name = "st_nodo_proceso", description = "Valores de la tabal de ST_NODO_PROCESO", implementation = StNodoProcesoT.class)
	private List<StNodoProcesoT> st_nodos_proceso;
}
