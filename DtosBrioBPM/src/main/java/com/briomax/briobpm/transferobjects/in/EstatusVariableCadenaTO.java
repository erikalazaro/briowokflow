package com.briomax.briobpm.transferobjects.in;

import java.io.Serializable;
import java.util.List;

import com.briomax.briobpm.transferobjects.VariableCadenaTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

/**
 * El objetivo de la Class EstatusTO.java es ...
 * @author Alexis Zamora.
 * @version 1.0 Fecha de creacion 19/12/2023 Modificaciones:
 * @since JDK 1.8
 */
@Schema(description = "EstatusVariableCadenaTO", title = "EstatusVariableCadenaTO")
@Data
@Builder
public class EstatusVariableCadenaTO implements Serializable {

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
	@Schema(name = "cveVariable", description = "cveVariable", example = "",
			implementation = String.class)
	private String cveVariable;
	

	 /** Lista de valores. */
	 @Schema(name = "VALORES_LISTA", description = "Lista de valores", example = "[\"valor1\", \"valor2\"]", implementation = List.class)
	 private List<VariableCadenaTO> valoresLista;
}
