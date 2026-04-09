package com.briomax.briobpm.transferobjects.in;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
/**
 * El objetivo de la Class EstatusTO.java es ...
 * @author Alexis Zamora.
 * @version 1.0 Fecha de creacion Abr 10/2024 Modificaciones:
 * @since JDK 1.8
 */
@Schema(description = "ListaTO", title = "ListaTO")
@Data
@Builder
public class ListaTO implements Serializable {

	/** serial Version UID. */
	private static final long serialVersionUID = 1L;

	/** The type. */
	@Schema(name = "valorLista", description = "valor de la lista", example = "OK", implementation = String.class)
	private String valorLista;

	/** The type. */
	@Schema(name = "descripcionLista", description = "descripcion de la lista", example = "OK", implementation = String.class)
	private String descripcionLista;
	
	/** The type. */
	@Schema(name = "tablaLista", description = "tabla lista", example = "OK", implementation = String.class)
	private String tablaLista;
	
	/** The type. */
	@Schema(name = "whereLista", description = "where Lista", example = "OK", implementation = String.class)
	private String whereLista;
	
}
