package com.briomax.briobpm.transferobjects.in;

import java.io.Serializable;
import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Schema(description = "FiltroRepseTO", title = "ListaTO")
@Data
@Builder
public class FiltroRepseTO implements Serializable {

	/** serial Version UID. */
	private static final long serialVersionUID = 1L;

	/** Clave de la instancia. */
	@Schema(name = "razonSocial", description = "Clave de la Instancia.", example = "12345", implementation = String.class)
	private String razonSocial;

	/** Situación. */
	@Schema(name = "resultadoColumna", description = "Situación.", example = "Activo", implementation = String.class)
	private String resultadoColumna;

	/** Secuencia del nodo. */
	@Schema(name = "secuenciaNodo", description = "Secuencia del Nodo.", example = "1")
	private Date fecha;
}
