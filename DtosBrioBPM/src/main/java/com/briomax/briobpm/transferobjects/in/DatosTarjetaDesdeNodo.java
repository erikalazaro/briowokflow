package com.briomax.briobpm.transferobjects.in;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * El objetivo de la Class DatosTarjetaDesdeNodo.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion 12 Mar 2024 12:34:39 PM Modificaciones:
 * @since JDK 1.8
 */
@Schema(title = "DatosTarjetaDesdeNodo", description = "DatosTarjetaDesdeNodo.")
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DatosTarjetaDesdeNodo implements Serializable {

	/** serial Version UID. */
	private static final long serialVersionUID = 1L;

	private String cveProceso;
    private BigDecimal version;
    private String descripcion;
    private String cveAreaTrabajoTarjeta;
    private String tituloTarjeta;
    private Integer secuenciaTarjeta;
    private Integer secuenciaDato;
    private String origenDato;
    private String etiqueta;
    private String cveDatoProcesoNodo;
    private String cveVariable;
    private String visible;
}
