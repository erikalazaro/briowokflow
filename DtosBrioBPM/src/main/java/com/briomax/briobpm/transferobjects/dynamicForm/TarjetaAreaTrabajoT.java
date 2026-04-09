package com.briomax.briobpm.transferobjects.dynamicForm;

import java.io.Serializable;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Schema(title = "TarjetaAreaTrabajoT", description = "TarjetaAreaTrabajoT")
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TarjetaAreaTrabajoT implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** El atributo o variable secuencia_tarjeta. */
	@Schema(name = "secuencia_tarjeta", description = "Muestra el valor de la columna SECUENCIA_TARJETA de la tabla TARJETA_AREA_TRABAJO",
			example = "secuencia_tarjeta", implementation = Integer.class)
	private Integer secuencia_tarjeta;
	
	/** El atributo o variable titulo_tarjeta. */
	@Schema(name = "titulo_tarjeta", description = "Muestra el valor de la columna TITULO_TARJETA de la tabla TARJETA_AREA_TRABAJO",
			example = "titulo_tarjeta", implementation = String.class)
	private String titulo_tarjeta;
	
	/** El atributo o variable buttons. */
	@Schema(name = "dato_area_trabajo", description = "Dato Area Trabajo.")
	private List<DatoAreaTrabajoT> dato_area_trabajo;

}
