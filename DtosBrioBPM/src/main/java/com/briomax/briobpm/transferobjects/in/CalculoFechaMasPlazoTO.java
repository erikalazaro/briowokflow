package com.briomax.briobpm.transferobjects.in;

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
 * El objetivo de la Class ProcesoTO.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion 19/01/2024 12:29:01 PM Modificaciones:
 * @since JDK 1.8
 */
@Schema(title = "calculoFechaMasPlazoTO.", description = "calculo de Fecha Mas Plazo.")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CalculoFechaMasPlazoTO implements Serializable {

	/** serial Version UID. */
	private static final long serialVersionUID = -564984068778186706L;

	/** El atributo o variable cve rol. */
	@Schema(name = "cveProceso", description = "CLAVE DEL PROCESO.", example = "ROL DUMMY 1", implementation = String.class)
	private String cveProceso;

	/** El atributo o variable version. */
	@Schema(name = "version", description = "Versión del proceso", example = "1.00", implementation = BigDecimal.class)
	private BigDecimal version;

	/** El atributo o variable cve area trabajo. */
	@Schema(name = "cveInstancia", description = "CLAVE DE LA INSTANCIA", example = "SOLICITUD_SOPORTE",
			implementation = String.class)
	private String cveInstancia;
	
	/** El atributo o variable cve nodo. */
	@Schema(name = "cveNodo", description = "Clave del Nodo.", example = "ACTIVIDAD-USUARIO",
			implementation = String.class)
	private String cveNodo;

	/** El atributo o variable id nodo. */
	@Schema(name = "idNodo", description = "Identificador del Nodo", example = "1", implementation = Integer.class)
	private Integer idNodo;
	
	/** El atributo o variable cve nodo. */
	@Schema(name = "cveEntidadBase", description = "Clave de entidad base.", example = "",
			implementation = String.class)
	private String cveEntidadBase;
	
	/** El atributo o variable cve nodo. */
	@Schema(name = "cveLocalidadBase", description = "Clave localidad Base.", example = "",
			implementation = String.class)
	private String cveLocalidadBase;

	/** El atributo o variable id nodo. */
	@Schema(name = "fechaBase", description = "Fecha Base", example = "", implementation = Date.class)
	private Date fechaBase;
	
	/** El atributo o variable cve nodo. */
	@Schema(name = "plazo", description = "Plazo.", example = "",
			implementation = Integer.class)
	private Integer plazo;

	/** El atributo o variable id nodo. */
	@Schema(name = "baseCalculo", description = "Base Calculo", example = "", implementation = String.class)
	private String baseCalculo;

}
