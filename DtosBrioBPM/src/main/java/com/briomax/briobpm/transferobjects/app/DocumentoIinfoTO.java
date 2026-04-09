/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.transferobjects.app;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/**
 * El objetivo de la Class DocumentoIinfoTO.java es ...
 * @author Sara Ventura
 * @version 1.0 Fecha de creacion Mar 9, 2020 11:35:41 AM Modificaciones:
 * @since JDK 1.8
 */
@Schema(title = "DocumentoIinfoTO.", description = "Documento de una Actividad.")
@Getter
@Setter
@NoArgsConstructor
public class DocumentoIinfoTO implements Serializable {

	/** Serial Version UID. */
	private static final long serialVersionUID = 2199023171347854729L;
	
	@Schema(name = "seccionVariable", description = "Seccio a la que pertenece.", example = "EVID_DESP-APP|1|2",
			implementation = String.class)
	private String seccionVariable;

	/** El atributo o variable sec documento. */
	@Schema(name = "secDocumento", description = "Secuencia del Documento", example = "1",
			implementation = Integer.class)
	private Integer secDocumento; // se llena conforme se va llenadno su arreglo	
	
	/** El atributo o variable nom archivo. */
	@Schema(name = "nomArchivo", description = "Nombre del Archivo.", example = "image.jpg",
			implementation = String.class)
	private String nomArchivo;

	/** El atributo o variable content type. */
	@Schema(name = "contentType", description = "Content Type " +
			"(image/jpeg, image/png, image/gif, image/svg+xml, application/pdf, " +
			"application/vnd.openxmlformats-officedoc, etc)",
		example = "image/jpeg", implementation = String.class)
	private String contentType;

	/** El atributo o variable data. */
	@ArraySchema(schema = @Schema(name = "data", description = "Bytes del Archivo.", implementation = byte.class))
	private byte[] data;


	/**
	 * 
	 * @param secDocumento
	 * @param nomArchivo
	 * @param contentType
	 */
	@Builder(builderMethodName = "builderDocumentoTO")
	public DocumentoIinfoTO(Integer secDocumento, String nomArchivo,
		String contentType) {
		this.secDocumento = secDocumento;
		this.nomArchivo = nomArchivo;
		this.contentType = contentType;
	}

}
