/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.transferobjects;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Blob;

import com.briomax.briobpm.transferobjects.app.DatoGuardar;
import com.briomax.briobpm.transferobjects.app.DatoGuardar.DatoGuardarBuilder;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/**
 * El objetivo de la Class DocumentoTO.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Mar 9, 2020 11:35:41 AM Modificaciones:
 * @since JDK 1.8
 */
@Schema(title = "DocMultipleTO.", description = "Documento de una Actividad.")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocMultipleTO  implements Serializable {

	/** Serial Version UID. */
	private static final long serialVersionUID = 2199023171347854729L;

	/** El atributo o variable sec documento. */
	@Schema(name = "secDocumento", description = "Secuencia del Documento", example = "1",
			implementation = Integer.class)
	private Integer secDocumento;
	
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
	private byte[] contenido;

}
