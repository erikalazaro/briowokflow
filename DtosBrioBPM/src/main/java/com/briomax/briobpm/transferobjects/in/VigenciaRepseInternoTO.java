package com.briomax.briobpm.transferobjects.in;

import java.io.Serializable;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * El objetivo de la Interface VigenciaRepseInternoTO.java es ...
 * 
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Oct 31 2024, 2024 6:05:58 PM Modificaciones:
 * @since JDK 1.8
 */
@Schema(description = "VigenciaRepseInternoTO", title = "VigenciaRepseInternoTO")
@Data
@Builder
public class VigenciaRepseInternoTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Schema(name = "dataInterna", description = "Corresponde al porcentaje de cada elemnto en la grafica.", example = "[65]") 
	private List<Integer> dataInterna;
	
	@Schema(name = "label", description = "Etiquetas para graficas.", example = "Contratistas que no cumplen el registro") 
	private String label;
	
}
