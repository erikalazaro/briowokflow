package com.briomax.briobpm.transferobjects.in;

import java.io.Serializable;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * El objetivo de la Interface PeriodoAnteriorCumplimientoTO.java es ...
 * 
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Oct 31 2024, 2024 6:05:58 PM Modificaciones:
 * @since JDK 1.8
 */
@Schema(description = "PeriodoAnteriorCumplimientoTO", title = "PeriodoAnteriorCumplimientoTO")
@Data
@Builder
public class PeriodoAnteriorCumplimientoTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Schema(name = "labels", description = "Etiquetas para graficas.", example = "['Total de contratos por contratista', 'Contratos completados']") 
	private List<String> labels;
	
	@Schema(name = "type", description = "Tipo de grafica.", example = "pie", implementation = String.class)
	private String type;

	@Schema(name = "data", description = "Corresponde al porcentaje de cada elemnto en la grafica.", example = "[65, 28]") 
	private List<Integer> data;
}
