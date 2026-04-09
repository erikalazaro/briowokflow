package com.briomax.briobpm.transferobjects;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.briomax.briobpm.transferobjects.SaveSectionTO.SaveSectionTOBuilder;
import com.briomax.briobpm.transferobjects.in.ActividadTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * El objetivo de la Class ProcesoTO.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Nov 27 12:29:01 PM :
 * @since JDK 1.8
 */
@Schema(description = "StCompuertaInicioTO", title = "St Compuerta Inicio DTO.")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StCompuertaInicioTO implements Serializable {
	
	/** El atributo o variable cve Entidad.*/
	@Schema(name = "CVE_ENTIDAD", description = "", example = "", implementation = String.class)
	private String cveEntidad;
	
	/** El atributo o variable cve Proceso*/
	@Schema(name = "CVE_PROCESO", description = "", example = "", implementation = String.class)
	private String cveProceso;
	
	/** El atributo o variable version*/
	@Schema(name = "VERSION", description = "", example = "", implementation = BigDecimal.class)
	private BigDecimal version;
	
	/** El atributo o variable cve Nodo*/
	@Schema(name = "CVE_NODO_INICIO", description = "", example = "", implementation = String.class)
	private String cveNodoInicio;
	
	/** El atributo o variable id Nodo*/
	@Schema(name = "ID_NODO_INICIO", description = "", example = "", implementation = String.class)
	private Integer idNodoInicio;
	
	/** El atributo o variable estado*/
	@Schema(name = "CVE_NODO_CIERRE", description = "", example = "", implementation = String.class)
	private String cveNodoCierre;

	/** El atributo o variable estado*/
	@Schema(name = "ID_NODO_CIERRE")
	private Integer idNodoCierre;

}
