package com.briomax.briobpm.transferobjects.repse;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

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
public class GrupoFechaIntervalosTO  implements Serializable{

	/** Serial Version UID.*/
	private static final long serialVersionUID = 1L;
		
	/** El atributo o variable cada n periodos. */
	@Schema(name = "ordenFecha", description = "secuencia fechas.", example = "1, 2, 3")
	private int ordenFecha;
	
	/** El atributo o variable hasta. */
	@Schema(name = "fecha", description = "Clave de hasta.", example = "2024-12-31", implementation = Date.class)
	private Date fecha;
	
	/** El atributo o variable desde. */
	@Schema(name = "identificadorFecha", description = "Identificador de Fecha.", example = "2")
	private String identificadorFecha;

}
