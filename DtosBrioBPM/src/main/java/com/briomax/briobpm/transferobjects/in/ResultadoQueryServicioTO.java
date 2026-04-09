package com.briomax.briobpm.transferobjects.in;

import java.io.Serializable;
import java.util.List;

import com.briomax.briobpm.transferobjects.app.GraficaTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * El objetivo de la Interface VigenciaRepseTO.java es ...
 * 
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Oct 31 2024, 2024 6:05:58 PM Modificaciones:
 * @since JDK 1.8
 */
@Schema(description = "DatosContrato", title = "DatosContrato")
@Data
@Builder
public class ResultadoQueryServicioTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Schema(name = "rfc", description = "RFC.", example = "RFC", implementation = String.class)
	private String rfc;
	
	@Schema(name = "Nombre", description = "Nombre.", example = "Nombre", implementation = String.class)
	private String nombre;
	
	@Schema(name = "Numeros de Contrato", description = "Numeros de Contrato.", example = "0002") 
	private List<String> numerosContrato;
	
	@Schema(name = "Graficas", description = "Graficas.") 
	private GraficaTO graficas;
}
