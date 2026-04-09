package com.briomax.briobpm.transferobjects.repse;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Schema(description = "FiltroValidaRepseTO", title = "TO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FiltroValidaRepseTO implements Serializable {

	/** serial Version UID. */
	private static final long serialVersionUID = 1L;

	@Schema(name = "razonSocial", description = "Razon Social", example = "Briomax Consulting", implementation = String.class)
	private String razonSocial;
	
	@Schema(name = "rfc", description = "rfc contratista", example = "BBA830831LJ2", implementation = String.class)
	private String rfc;
	
	@Schema(name = "contrato", description = "numero de contrato", example = "000101", implementation = String.class)
	private String contrato;
	
	@Schema(name = "cveProceso", description = "proceso", example = "REGISTRO_DE_CONTRATO", implementation = String.class)
	private String cveProceso;
	
	@Schema(name = "version", description = "version", example = "1.0", implementation = String.class)
	private String version;

	@Schema(name = "cveNodo", description = "clave nodo", example = "ACTIVIDAD_USUARIO", implementation = String.class)
	private String cveNodo;

	@Schema(name = "idNodo", description = "id nodo", example = "1", implementation = Integer.class)
	private Integer idNodo;
	
	@Schema(name = "cveRol", description = "clave del rol", example = "cveRol", implementation = String.class)
	private String cveRol;
	
	@Schema(name = "cveInstancia", description = "clave instancia", example = "cveInstancia", implementation = String.class)
	private String cveInstancia;
}
