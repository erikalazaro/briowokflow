package com.briomax.briobpm.transferobjects.in;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StInMensajeTO implements Serializable{
	/** El atributo o variable cve proceso. */
	@Schema(name = "cveEntidad", description = "Clave del Proceso.", example = "DUMMY", implementation = String.class)
	private String cveEntidad;
	
	/** El atributo o variable cve proceso. */
	@Schema(name = "cveProceso", description = "Clave del Proceso.", example = "DUMMY", implementation = String.class)
	private String cveProceso;

	/** El atributo o variable version. */
	@Schema(name = "version", description = "Versión del proceso", example = "1.00")
	private String version;
	
	/** El atributo o variable version. */
	@Schema(name = "cveInstancia", description = "Versión del proceso", example = "1.00")
	private String cveInstancia;

	/** El atributo o variable cve nodo. */
	@Schema(name = "cveNodo", description = "Clave del Nodo.", example = "ACTIVIDAD-USUARIO",
			implementation = String.class)
	private String cveNodo;

	/** El atributo o variable id nodo. */
	@Schema(name = "idNodo", description = "Identificador del Nodo", example = "1", implementation = Integer.class)
	private Integer idNodo;
	
	/** El atributo o variable id nodo. */
	@Schema(name = "secNodo", description = "Secuencia del Nodo", example = "1", implementation = Integer.class)
	private Integer secNodo;
	
	/** El atributo o variable id nodo. */
	@Schema(name = "folioMensaje", description = "Folio del mensaje", example = "1", implementation = Integer.class)
	private Integer folioMensaje;
	
	/** El atributo o variable cve nodo. */
	@Schema(name = "valoresReferenciaEnvio", description = "Valores de referencia.", example = "ACTIVIDAD-USUARIO",
			implementation = String.class)
	private String valoresReferenciaEnvio;

	/** El atributo o variable cve nodo. */
	@Schema(name = "inicioProcesoDestino", description = "Valores de referencia.", example = "ACTIVIDAD-USUARIO",
			implementation = String.class)
	private String inicioProcesoDestino;
	
	/** El atributo o variable cve nodo. */
	@Schema(name = "cveEntidadDestino", description = "Valores de referencia.", example = "ACTIVIDAD-USUARIO",
			implementation = String.class)
	private String cveEntidadDestino;
	
	/** El atributo o variable cve nodo. */
	@Schema(name = "cveProcesodDestino", description = "Valores de referencia.", example = "ACTIVIDAD-USUARIO",
			implementation = String.class)
	private String cveProcesodDestino;
	
	/** El atributo o variable version. */
	@Schema(name = "versionDestino", description = "Versión del proceso", example = "1.00")
	private BigDecimal versionDestino;
	
	@Schema(name = "cveNodoDestino", description = "Versión del proceso", example = "1.00")
	private String cveNodoDestino;

	@Schema(name = "idNodoDestino", description = "Versión del proceso", example = "1.00")
	private BigDecimal idNodoDestino;


}
