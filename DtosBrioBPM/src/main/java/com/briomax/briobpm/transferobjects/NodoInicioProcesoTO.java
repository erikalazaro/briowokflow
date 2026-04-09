package com.briomax.briobpm.transferobjects;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * El objetivo de la Class TipoNodoTO.java es ...
 * @author Erika Vázquez
 * @version 1.0 Fecha de creacion Nov 25, 2020 7:12:51 PM Modificaciones:
 * @since JDK 1.8
 */
@Schema(description = "NodoInicioProcesoTO", title = "Nodo Inicio Proceso TO.")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NodoInicioProcesoTO {
	
	/** El atributo o variable cve Entidad. */
	private String cveEntidad;

	/** El atributo o variable cve proceso. */
	private String cveProceso;

	/** El atributo o variable version. */
	private BigDecimal version;

	/** El atributo o variable cve nodo. */
	private String cveNodo;

	/** El atributo o variable id nodo. */
	private Integer idNodo;
	
	/** El atributo o variable descripcion. */
	private String variablesReferenciaRec;

}
