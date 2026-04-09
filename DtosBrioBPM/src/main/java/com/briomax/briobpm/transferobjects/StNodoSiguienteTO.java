package com.briomax.briobpm.transferobjects;

import java.io.Serializable;
import java.math.BigDecimal;

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
@Schema(description = "StNodoSiguiente", title = "St Nodo Siguiente DTO.")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StNodoSiguienteTO implements Serializable {
	
	/** El atributo o variable cve Entidad.*/
	@Schema(name = "CVE_ENTIDAD", description = "", example = "", implementation = String.class)
	private String cveEntidad;
	
	/** El atributo o variable cve Proceso*/
	@Schema(name = "CVE_PROCESO", description = "", example = "", implementation = String.class)
	private String cveProceso;
	
	/** El atributo o variable version*/
	@Schema(name = "VERSION", nullable = false, description = "", example = "", implementation = String.class)
	private BigDecimal version;

	/** El atributo o variable cve Nodo*/
	@Schema(name = "CVE_NODO", description = "", example = "", implementation = String.class)
	private String cveNodo;
	
	/** El atributo o variable cve Nodo*/
	@Schema(name = "ID_NODO")
	private Integer idNodo;
	
	/** El atributo o variable cve Nodo*/
	@Schema(name = "TIPO_NODO_SIGUIENTE", description = "", example = "", implementation = String.class)
	private String tipoNodoSiguiente;
	
	/** El atributo o variable cve Nodo*/
	@Schema(name = "SECUENCIA")
	private Integer secuencia;
	
	/** El atributo o variable cve Instancia*/
	@Schema(name = "CVE_NODO_SIGUIENTE",description = "", example = "", implementation = String.class)
	private String cveNodoSiguiente;

	/** El atributo o variable id Nodo Siguiente*/
	@Schema(name = "ID_NODO_SIGUIENTE")
	private Integer idNodoSiguiente;
	
	/** El atributo o variable condicion*/
	@Schema(name = "CONDICION", description = "", example = "", implementation = String.class)
	private String condicion;
}
