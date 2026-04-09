package com.briomax.briobpm.transferobjects.dynamicForm;

import java.io.Serializable;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data/*lombok*/
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TarjetaDato implements Serializable {

	/** Serial Version UID. */
	private static final long serialVersionUID = -2720263948175388532L;

	/** El atributo o variable para las tarjetas. */
	@Schema(name = "datosPorTarjeta", description = "contiene datos para las tarjetas", implementation = DatoAreaTrabajoT.class)
	private List<DatoAreaTrabajoT>  datosPorTarjeta;
	
	private List<IconoAtTO> iconosEstado;
	
	/** El atributo o variable cve_proceso. */
	@Schema(name = "titulo_tarjeta", description = "Muestra el valor de la columna CVE_PROCESO de la tabla ST_NODO_PROCESO",
			example = "Participante", implementation = String.class)
	private String titulo_tarjeta;

}
