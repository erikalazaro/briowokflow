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
 * @version 1.0 Fecha de creacion Jul 26, 2024 11:09:51 AM Modificaciones:
 * @since JDK 1.8
 */
@Schema(description = "DestinatarioTO", title = "DestinatarioTO.")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DestinatarioTO implements ITransferObject {

	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;
	
	/** La secuencia . */
	@Schema(name = "emails", description = "Lista correos.", example = "1")
	String[] emails;
   
	/** La tabla o tablas para obtener la descripcion de documento. */
	@Schema(name = "tipo", description = "lista TIPO.", example = "CC")
	String[] tipo;
}
