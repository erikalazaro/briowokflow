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
 * El objetivo de la Class DatosSeccionOCUTO.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Abr 30, 2024 11:09:51 AM Modificaciones:
 * @since JDK 1.8
 */
@Schema(description = "DatosSeccionOCUTO", title = "DatosSeccionOCUTO.")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DatosSeccionOCUTO implements ITransferObject {

	/** Serial Version UID. */
	private static final long serialVersionUID = 1L;
	
	/** La ocurrencia . */
	@Schema(name = "ocurrencia", description = "Ocurrencai.", example = "1")
	Integer ocurrencia;

	Integer secuenciaDocumento;
	
	/** El orden del dato. */
	@Schema(name = "orden_dato", description = "El orden del dato.", example = "1")
	Integer ordenDato;

	/** La clave del dato. */
	@Schema(name = "cve_dato", description = "La clave del dato.", example = "DATO_001")
	String cveDato;

	/** Numero Valores. */
	@Schema(name = "Numero valores", description = "Numero valores", example = "1")
	Integer numeroValores;
	
	/** La secuencia del valor. */
	@Schema(name = "secuencia_valor", description = "La secuencia del valor.", example = "1")
	Integer secuenciaValor;

	/** El valor base de la base de datos. */
	@Schema(name = "valor_base_datos", description = "El valor base de la base de datos.", example = "Valor base de datos")
	String valorBaseDatos;

	/** El valor de la pantalla. */
	@Schema(name = "valor_pantalla", description = "El valor de la pantalla.", example = "Valor de la pantalla")
	String valorPantalla;
	
	/** El valor base de la base de datos. */
	@Schema(name = "valor_base_datos", description = "El valor base de la base de datos.", example = "Valor base de datos")
	byte[]  valorBaseDatosImagen;

	/** El valor de la pantalla. */
	@Schema(name = "valor_pantalla", description = "El valor de la pantalla.", example = "Valor de la pantalla")
	byte[]  valorPantallaImagen;
	
	/** El valor de la tipoDato. */
	@Schema(name = "tipoDato", description = "Dato la para Imagen", example = "tipo de Dato")
	String tipoDato;
}
