package com.briomax.briobpm.transferobjects.in;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * El objetivo de la Class EstatusTO.java es ...
 * @author Alexis Zamora.
 * @version 1.0 Fecha de creacion 12/12/2023 Modificaciones:
 * @since JDK 1.8
 */
@Schema(description = "EstatusVariablesTO", title = "EstatusVariablesTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EstatusVariablesTO implements Serializable {

	/** serial Version UID. */
	private static final long serialVersionUID = 3593261886846685341L;

	/** The type. */
	@NonNull
	@Schema(name = "tipoExcepcion", description = "tipoExcepcion", example = "OK", implementation = String.class)
	private String tipoExcepcion;

	/** The value. */
	@NonNull
	@Schema(name = "mensaje", description = "mensaje", example = "mensaje", implementation = String.class)
	private String mensaje;
	
	/** El atributo o variable cve area trabajo. */
	@Schema(name = "tipo", description = "tipo", example = "",
			implementation = String.class)
	private String tipo;
	
	/** El atributo o variable cve area trabajo. */
	@Schema(name = "decimales", description = "decimales", example = "",
			implementation = BigDecimal.class)
	private Integer decimales;
	
	/** El atributo o variable cve area trabajo. */
	@Schema(name = "formatoFecha", description = "formato Fecha", example = "",
			implementation = String.class)
	private String formatoFecha;
	
	/** El atributo o variable cve area trabajo. */
	@Schema(name = "valorAlfanumerico", description = "valor Alfanumerico", example = "",
			implementation = String.class)
	private String valorAlfanumerico;
	
	/** El atributo o variable cve area trabajo. */
	@Schema(name = "valorEntero", description = "valor Entero", example = "1",
			implementation = Integer.class)
	private Integer valorEntero;
	
	/** El atributo o variable cve area trabajo. */
	@Schema(name = "valorDecimal", description = "valor decimal", example = "1.0",
			implementation = String.class)
	private BigDecimal valorDecimal;
	
	/** El atributo o variable cve area trabajo. */
	@Schema(name = "valorFecha", description = "Valor fecha", example = "",
			implementation = Date.class)
	private Date valorFecha;
	
	/** El atributo o variable cve area trabajo. */
	@Schema(name = "tipoDato", description = "tipoDato", example = "",
			implementation = String.class)
	private String tipoDato;
	
	/** El atributo o variablevsecuencia Nodo. */
	@Schema(name = "secuenciaNodo", description = "secuenciaNodo", example = "",
			implementation = String.class)
	private Integer secuenciaNodo;
	
	/** El atributo o variablevsecuencia Nodo. */
	@Schema(name = "folioMensaje", description = "folioMensaje", example = "",
			implementation = Integer.class)
	private Integer folioMensaje;
	
	/** El atributo o variablevsecuencia Nodo. */
	@Schema(name = "resulado", description = "resulado", example = "",
			implementation = String.class)
	private String resulado;
	
	/** El atributo o variablevsecuencia Nodo. */
	@Schema(name = "cadenaSalida", description = "cadenaSalida", example = "",
			implementation = String.class)
	private String cadenaSalida;
	
	/** El atributo o variablevsecuencia Nodo. */
	@Schema(name = "variablesReferenciaRec", description = "variablesReferenciaRec", example = "",
			implementation = String.class)
	private String variablesReferenciaRec;
	
	/** El atributo o variablevsecuencia Nodo. */
	@Schema(name = "valoresReferenciaRec", description = "valoresReferenciaRec", example = "",
			implementation = String.class)
	private String valoresReferenciaRec;

}