package com.briomax.briobpm.transferobjects.dynamicForm;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Datos de la tabla ST_NODO_PROCESO.", title = "StNodoProceso")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StNodoProcesoT implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	/** El atributo o variable para las tarjetas. */
	@Schema(name = "tarjetas", description = "contiene datos para las tarjetas", implementation = Tarjetas.class)
	private List<TarjetaDato>  tarjeta;
	
	/** El atributo o variable cve_proceso. */
	@Schema(name = "cve_proceso", description = "Muestra el valor de la columna CVE_PROCESO de la tabla ST_NODO_PROCESO",
			example = "cve_proceso", implementation = String.class)
	private String cve_proceso;
	
	/** El atributo o variable verision. */
	@Schema(name = "verision", description = "Muestra el valor de la columna VERSION de la tabla ST_NODO_PROCESO",
			example = "verision", implementation = BigDecimal.class)
	private BigDecimal version;
	
	/** El atributo o variable descripcion. */
	@Schema(name = "descripcion", description = "Muestra el valor de la columna DESCRIPCION de la tabla ST_NODO_PROCESO",
			example = "descripcion", implementation = String.class)
	private String descripcion;
	
	/** El atributo o variable cve_nodo. */
	@Schema(name = "cve_nodo", description = "Muestra el valor de la columna CVE_NODO de la tabla ST_NODO_PROCESO",
			example = "cve_nodo", implementation = String.class)
	private String cve_nodo;
	
	/** El atributo o variable id_nodo. */
	@Schema(name = "id_nodo", description = "Muestra el valor de la columna ID_NODO de la tabla ST_NODO_PROCESO",
			example = "id_nodo", implementation = Integer.class)
	private Integer id_nodo;
	
	/** El atributo o variable cve_area_trabajo_tarjeta. */
	@Schema(name = "cve_area_trabajo_tarjeta", description = "Muestra el valor de la columna CVE_AREA_TRABAJO_TARJETA de la tabla ST_NODO_PROCESO",
			example = "cve_area_trabajo_tarjeta", implementation = String.class)
	private String cve_area_trabajo_tarjeta;

	private ActionCveInstancia accionesCveInstancia;
	
	
}
