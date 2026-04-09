package com.briomax.briobpm.transferobjects.in;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * El objetivo de la Interface StSeccionNodoTO.java es ...
 * 
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Abr 11, 2024 6:05:58 PM Modificaciones:
 * @since JDK 1.8
 */
@Schema(description = "StSeccionNodoTO", title = "StSeccionNodoTO")
@Data
@AllArgsConstructor
@Builder
public class StSeccionNodoTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Schema(name = "orden", description = ".", example = "", implementation = Integer.class)
	private Integer orden;
	
	@Schema(name = "cveSeccion", description = ".", example = "", implementation = String.class)
	private String cveSeccion;

	@Schema(name = "etiquetaSeccion", description = ".", example = "", implementation = String.class)
	private String etiquetaSeccion;
	
	@Schema(name = "tipoPresentacion", description = ".", example = "", implementation = String.class)
	private String tipoPresentacion;
	
	@Schema(name = "botonCrearRenglon", description = ".", example = "", implementation = String.class)
	private String botonCrearRenglon;
	
	@Schema(name = "etiquetaCrearRenglon", description = ".", example = "", implementation = String.class)
	private String etiquetaCrearRenglon;
	
	@Schema(name = "botonEliminarRenglon", description = ".", example = "", implementation = String.class)
	private String botonEliminarRenglon;
	
	@Schema(name = "etiquetaElimianarRenglon", description = ".", example = "", implementation = String.class)
	private String etiquetaEliminarRenglon;
	
	@Schema(name = "contenido", description = ".", example = "", implementation = String.class)
	private String contenido;
	
	@Schema(name = "registroSelecconables", description = ".", example = "", implementation = Integer.class)
	private BigDecimal registroSelecconables;
}
