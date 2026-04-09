package com.briomax.briobpm.transferobjects.in;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * El objetivo de la Interface SituacionTareaTO.java es ...
 * 
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Jun 11, 2024 6:05:58 PM Modificaciones:
 * @since JDK 1.8
 */
@Schema(description = "SituacionTareaTO", title = "SituacionTareaTO")
@Data
@Builder
public class SituacionTareaTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Schema(name = "secuenciaTarea", description = "Secuencia de la Tare.", example = "1")
	private Integer secuenciaTarea;

	@Schema(name = "completada", description = "Completada.", implementation = String.class)
	private String completada;

}
