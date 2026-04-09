package com.briomax.briobpm.transferobjects.in;

import java.util.List;

import com.briomax.briobpm.transferobjects.core.ITransferObject;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * El objetivo de la Class RsOcurrenciaTipoTO.java es ...
 * @author Alexis Zamora.
 * @version 1.0 Fecha de creacion 02 Sep 2024 Modificaciones:
 * @since JDK 1.8
 */
@Schema(description = "RsOcurrenciaTipoTO", title = "RsOcurrenciaTipoTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RsOcurrenciaTipoTO implements ITransferObject {

	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;

	/** The value datos RsOcurrencias. */
	@Schema(name = "RsOcurrencias", description = "lista de RsOcurrencia")
	private List<RsOcurrenciaTO> rsOcurrencias;
	
	/** The value datos tipo. */
	@Schema(name = "tipo", description = "si es tipo grid o variable", example ="DOCUMENTO")
	private String tipo;
}
