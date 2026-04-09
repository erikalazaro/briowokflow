package com.briomax.briobpm.transferobjects.in;

import java.io.Serializable;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * El objetivo de la Interface PeriodoActualCumplimientoTO.java es ...
 * 
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Oct 31 2024, 2024 6:05:58 PM Modificaciones:
 * @since JDK 1.8
 */
@Schema(description = "PeriodoActualCumplimientoTO", title = "PeriodoActualCumplimientoTO")
@Data
@Builder
public class PeriodoActualCumplimientoTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Schema(name = "labels", description = "Etiquetas para graficas.", example = "['Total de contratos por contratista', 'Contratos completados']")
	private List<String> labels;

	@Schema(name = "type", description = "Tipo de grafica.", example = "pie", implementation = String.class)
	private String type;

	@Schema(name = "data", description = "Corresponde al porcentaje de cada elemnto en la grafica.", example = "[65, 28]")
	private List<Integer> data;

	@Schema(name = "valoresTexto", description = "valores Texto.", example = "Documentos con plazo de entrega superior a 1 mes", implementation = String.class)
	private String valoresTexto;
}
