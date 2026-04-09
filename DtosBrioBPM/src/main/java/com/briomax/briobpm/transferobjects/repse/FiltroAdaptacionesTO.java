package com.briomax.briobpm.transferobjects.repse;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Schema(description = "FiltroAdaptacionesTO", title = "ListaTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FiltroAdaptacionesTO implements Serializable {

	/** serial Version UID. */
	private static final long serialVersionUID = 1L;
	
	@Schema(name = "cveProceso", description = "Clave del proceso", example = "ALTA_CONTRATANTE", implementation = String.class)
	private String cveProceso;
	
	/** Clave de la instancia. */
	@Schema(name = "rfc", description = "RFC.", example = "BRIO278973", implementation = String.class)
	private String rfc;
	
	/** Clave de la instancia. */
	@Schema(name = "contrato", description = "Numero de contrato.", example = "0102033", implementation = String.class)
	private String contrato;

	/** Situación. */
	@Schema(name = "tipo", description = "Tipo de consulta.", example = "A", implementation = String.class)
	private String tipo;

	/** Situación. */
	@Schema(name = "fecha", description = "Fecha de consulta.", example = "dd/MM/yyyy", implementation = String.class)
	private String fecha;
}

