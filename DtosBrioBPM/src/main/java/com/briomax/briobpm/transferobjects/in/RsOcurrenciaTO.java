package com.briomax.briobpm.transferobjects.in;

import java.util.List;

import com.briomax.briobpm.transferobjects.core.ITransferObject;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * El objetivo de la Class RsOcurrenciaTO.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Abr 30, 2024 11:09:51 AM Modificaciones:
 * @since JDK 1.8
 */
@Schema(description = "RsOcurrenciaTO", title = "RsOcurrenciaTO.")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RsOcurrenciaTO implements ITransferObject {

	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;
	

	/** Clave Seccion. */
	@Schema(name = "Clave Seccion", description = "Clave Seccion.", example = "Nombre")
	String cveSeccion;	
	
	/** La ocurrencia . */
	@Schema(name = "ocurrencia", description = "Ocurrencai.", example = "1")
	Integer ocurrencia;

	/** Datos Ocurrencai. */
	@Schema(name = "datos Ocurrencia", description = "Datos Ocurrencia.", example = "")
	String datosOcurrencia;
	
	Integer secuenciaDocumento;
	
	/** Datos imagenes. */
	@Schema(name = "datos de imagen", description = "lista de imagen.", example = "")
	List<byte[]> imagenes;
	
	/** El valor de la tipoDato. */
	@Schema(name = "tipoDato", description = "Dato la para Imagen", example = "tipo de Dato")
	String tipoDato;
	
}
