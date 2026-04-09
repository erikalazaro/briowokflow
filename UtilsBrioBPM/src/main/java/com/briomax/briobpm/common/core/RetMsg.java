
package com.briomax.briobpm.common.core;

import java.util.HashMap;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema( description = "Mensaje de Retorno.", title = "Mensaje de Retorno.")
public class RetMsg implements IOutsParams {

	@Schema(name = "status", description = "Estatus.", example = "OK, ERROR, AVISO.")
	private String status;

	@Schema(name = "message", description = "Mensaje.", example = "Mensje de Procesamiento.")
	private String message;

	@Override
	public void setOutParams(HashMap<String, Object> params) {
		this.status = (String) params.get("CH_TIPO_EXCEPCION");
		this.message = (String) params.get("CH_MENSAJE");
	}

}
