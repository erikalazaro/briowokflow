package com.briomax.briobpm.transferobjects.repse;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * El objetivo de la Class DatosAutenticacionTO.java es ...
 * @author ALFREDO ESPINOSA
 * @version 1.0 Fecha de creacion 24/07/2024 Modificaciones:
 * @since JDK 11.0.22
 */
@Schema(title = "OrdenFechaIntervalosTO", description = "Datos de SP_LEE_INTERVALO_FECHAS.")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrdenFechaIntervalosTO  implements Serializable{

	/** Serial Version UID.*/
	private static final long serialVersionUID = 1L;
		
	/** El atributo o variable cada n periodos. */
	@Schema(name = "secuencia", description = "secuencia Periodos.", example = "1, 2, 3", implementation = Integer.class)
	private Integer secuencia;
	
	/** El atributo o variable desde. */
	@Schema(name = "fechaInicial", description = "Clave de desde.", example = "2024-01-01", implementation = Date.class)
	private Date fechaInicial;
	
	/** El atributo o variable hasta. */
	@Schema(name = "fechaFinal", description = "Clave de hasta.", example = "2024-12-31", implementation = Date.class)
	private Date fechaFinal;
	
	private List<GrupoFechaIntervalosTO>  grupoFecha;

}
