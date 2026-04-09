package com.briomax.briobpm.transferobjects.repse;

import java.io.Serializable;
import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Contiene datos para filtrar en la consulta de documentos pdf ", title = "ConsultaPdfTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConsultaPdfTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Schema(name = "actividad", description = "Tipor de pantalla", example = "CONSULTA O CARGA", implementation = String.class)
	private String actividad;

	@Schema(name = "cveProceso", description = "Clave del proceso", example = "ALTA_CONTRATANTE", implementation = String.class)
	private String cveProceso;
	
	/** Clave de la instancia. */
	@Schema(name = "rfc", description = "RFC.", example = "BRIO278973", implementation = String.class)
	private String rfc;
	
	/** Clave de la instancia. */
	@Schema(name = "contrato", description = "Numero de contrato.", example = "0102033", implementation = String.class)
	private String contrato;
	
	/** Clave de la instancia. */
	@Schema(name = "fechaCarga", description = "Fcha de Carga.", example = "10/06/2025", implementation = String.class)
	private String fechaCarga;

	/** Clave de la instancia. */
	@Schema(name = "nombreDocumento", description = "Nombre Documento.", example = "tempral.pdf", implementation = String.class)
	private String nombreDocumento;
}
