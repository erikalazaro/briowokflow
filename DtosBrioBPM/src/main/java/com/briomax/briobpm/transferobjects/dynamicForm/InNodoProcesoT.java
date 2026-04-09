package com.briomax.briobpm.transferobjects.dynamicForm;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Schema(title = "InNodoProcesoT", description = "InNodoProcesoT")
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InNodoProcesoT implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** El atributo o variable secuencia_tarjeta. */
	@Schema(name = "cve_instancia", description = "Muestra el valor de la columna CVE_INSTANCIA de la tabla IN_NODO_PROCESO",
			example = "cve_instancia", implementation = String.class)
	private String cve_instancia;
	
	/** El atributo o variable secuencia_tarjeta. */
	@Schema(name = "cve_nodo", description = "Muestra el valor de la columna CVE_NODO de la tabla IN_NODO_PROCESO",
			example = "cve_nodo", implementation = String.class)
	private String cve_nodo;
	
	/** El atributo o variable secuencia_tarjeta. */
	@Schema(name = "id_nodo", description = "Muestra el valor de la columna ID_NODO de la tabla IN_NODO_PROCESO",
			example = "id_nodo", implementation = BigDecimal.class)
	private BigDecimal id_nodo;
	
	/** El atributo o variable secuencia_tarjeta. */
	@Schema(name = "secuencia_nodo", description = "Muestra el valor de la columna SECUENCIA_NODO de la tabla IN_NODO_PROCESO",
			example = "secuencia_nodo", implementation = Integer.class)
	private Integer secuencia_nodo;

}
