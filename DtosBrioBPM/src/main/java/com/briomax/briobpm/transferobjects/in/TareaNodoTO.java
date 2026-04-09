package com.briomax.briobpm.transferobjects.in;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(title = "TareaNodoTO.", description = "TareaNodoTO.")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TareaNodoTO implements Serializable {

	/** serial Version UID. */
	private static final long serialVersionUID = 1L;

	/** El atributo o variable secuencia de Tarea. */
	@Schema(name = "secuenciaTarea", implementation = Integer.class)
	private Integer secuenciaTarea;
	
	/** El atributo o variable requerida. */
	@Schema(name = "descripcionTarea", implementation = String.class)
	private String descripcionTarea;
	
	/** El atributo o variable requerida. */
	@Schema(name = "requerida", implementation = String.class)
	private String requerida;
	
	/** El atributo o variable requerida. */
	@Schema(name = "copletada", implementation = String.class)
	private String copletada;

}
