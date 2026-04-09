package com.briomax.briobpm.transferobjects;

import java.io.Serializable;
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
@Schema(description = "NodoInicioTO", title = "Nodo Inicio TO.")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NodoInicioTO implements Serializable {

	/** serial Version UID. */
	private static final long serialVersionUID = 3593261886846685341L;
	
	/** El atributo o variable cve nodo. */
	private String cveNodo;

	/** El atributo o variable id nodo. */
	private Integer idNodo;
	
	/** El atributo o variable cve nodo. */
	private String tipoExcepcion;

	/** El atributo o variable cve nodo. */
	private String mensaje;
	

}
