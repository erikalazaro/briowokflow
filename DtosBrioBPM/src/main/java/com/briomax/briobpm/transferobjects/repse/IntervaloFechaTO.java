package com.briomax.briobpm.transferobjects.repse;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Schema(title = "IntervaloFechaTO", description = "Datos de SP_LEE_INTERVALO_FECHAS.")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IntervaloFechaTO  implements Serializable{

	/** Serial Version UID.*/
	private static final long serialVersionUID = 1L;
	
	/** El atributo o variable cve proceso. */
	@Schema(name = "cveProceso", description = "Clave del Proceso.", example = "DUMMY", implementation = String.class)
	private String cveProceso;
	
	/** El atributo o variable cve version. */
	@Schema(name = "version", description = "Clave de la Version.", example = "1.0", implementation = BigDecimal.class)
	private BigDecimal version;
	
	/** El atributo o variable cada n periodos. */
	@Schema(name = "cadaNperiodos", description = "Clave de Cada n Periodos.", example = "1, 2, 3", implementation = Integer.class)
	private Integer cadaNperiodos;
	
	/** El atributo o variable periodo tiempo. */
	@Schema(name = "periodoTiempo", description = "Clave de Periodo Tiempo.", example = "DIA, SEMANA, MES", implementation = String.class)
	private String periodoTiempo;

	/** El atributo o variable detalle Periodo. */
	@Schema(name = "detallePeriodo", description = "1-PRIMER, 2-SEGUNDO, 3-TERCER, 4-CUARTO, ETC., P-PENULTIMO, U-ULTIMO.", example = "U", implementation = String.class)	
	private String detallePeriodo;

	/** El atributo o variable dias del Mes. */
	@Schema(name = "diasDelMes", description = "dias de la mes", example = "1,2,3 SÓLO APLICA PARA PERIODO = 'MES'", implementation = String.class)	
	private String diasDelMes;

	/** El atributo o variable dias del Mes. */
	@Schema(name = "diasDeLaSemana", description = "dias de la semana", example = "L,M,W,J,V,S,D SÓLO APLICA PARA PERIODO = 'SEMANA'", implementation = String.class)	
	private String diasDeLaSemana;
	
	/** El atributo o variable dias del Mes. */
	@Schema(name = "tipoDia", description = "tipo dia", example = "1", implementation = String.class)	
	private String tipoDia;
	
	/** El atributo o variable desde. */
	@Schema(name = "desde", description = "Clave de desde.", example = "2024-01-01", implementation = Date.class)
	private Date desde;
	
	/** El atributo o variable hasta. */
	@Schema(name = "hasta", description = "Clave de hasta.", example = "2024-12-31", implementation = Date.class)
	private Date hasta;
	
	

}
