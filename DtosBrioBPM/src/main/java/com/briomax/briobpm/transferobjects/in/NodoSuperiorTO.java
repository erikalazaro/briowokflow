package com.briomax.briobpm.transferobjects.in;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * El objetivo de la Class EstatusTO.java es ...
 * @author Alexis Zamora.
 * @version 1.0 Fecha de creacion 11/01/2024 Modificaciones:
 * @since JDK 1.8
 */
@Schema(description = "NodoSuperiorTO", title = "NodoSuperiorTO")
@Data
@Builder
public class NodoSuperiorTO implements Serializable {

	/** serial Version UID. */
	private static final long serialVersionUID = 1L;

	/** The type. */
	@Schema(name = "consecutivo", description = "consecutivo", example = "", implementation = BigDecimal.class)
	private Integer consecutivo;

	/** The value. */
	@Schema(name = "cveNodo", description = "cveNodo", example = "", implementation = String.class)
	private String cveNodo;
	
	/** The type. */
	@Schema(name = "idNodo", description = "idNodo", example = "1", implementation = Integer.class)
	private Integer idNodo;

}
