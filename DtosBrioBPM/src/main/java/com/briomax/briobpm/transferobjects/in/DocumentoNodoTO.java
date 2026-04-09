package com.briomax.briobpm.transferobjects.in;

import com.briomax.briobpm.transferobjects.core.ITransferObject;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * El objetivo de la Class DocumentoNodoTO.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Abr 15, 2024 11:09:51 AM Modificaciones:
 * @since JDK 1.8
 */
@Schema(description = "DocumentoNodoTO", title = "DocumentoNodoTO.")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentoNodoTO implements ITransferObject {

	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;
	
	/** La secuencia . */
	@Schema(name = "secuencia", description = "La secuencia de la columna.", example = "1")
	Integer secuencia;

	/** La tabla o tablas para obtener la descripcion de documento. */
	@Schema(name = "descripcionDocumento", description = "Descripcion del Documento.", example = "")
	String descripcionDocumento;

	/** La clave de la variable requerido. */
	@Schema(name = "requerido", description = "Requerido.", example = "")
	String requerido;

	/** La etiqueta del nombre del Archivo. */
	@Schema(name = "nombreArchivo", description = "Nombre del Archivo.", example = "Nombre")
	String nombreArchivo;


}
