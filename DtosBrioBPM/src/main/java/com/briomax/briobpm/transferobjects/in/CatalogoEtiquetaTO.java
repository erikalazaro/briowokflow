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
 * El objetivo de la Class CatalogoEtiquetaTO.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Abr 11, 2024 11:09:51 AM Modificaciones:
 * @since JDK 1.8
 */
@Schema(description = "CatalogoEtiquetaTO", title = "CatalogoEtiquetaTO.")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CatalogoEtiquetaTO implements ITransferObject {

	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;
	
	/** El atributo o variable cve variable. */
	@Schema(name = "cveVariable", description = "Clave Variable.", example = "",
			implementation = String.class)
	private String cveVariable;

	/** El atributo o variable nombre Dato. */
	@Schema(name = "nombreDato", description = "Nombre de dato.", example = "",
			implementation = String.class)
	private String nombreDato;
	
	/** El atributo o variable nombre Dato. */
	@Schema(name = "valorAlfanumerico", description = "Valor Alfanumerico.", example = "",
			implementation = String.class)
	private String valorAlfanumerico;
	
	/** El atributo o variable nombre Dato. */
	@Schema(name = "etiqueta", description = "Etiqueta.", example = "",
			implementation = String.class)
	private String etiqueta;
	
	/** El atributo o variable nombre Dato. */
	@Schema(name = "ordenPresentacion", description = "Orden Presentacion.", example = "",
			implementation = String.class)
	private String ordenPresentacion;
}
