/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.transferobjects.repse;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/**
 * El objetivo de la Class DocumentoTrabajadorTO.java es ...
 * @author Sara Ventura
 * @version 1.0 Fecha de creacion Mar 9, 2020 11:35:41 AM Modificaciones:
 * @since JDK 1.8
 */
@Schema(title = "DocumentoTrabajadorTO.", description = "Documento de una Actividad.")
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true, exclude = {"data"})
public class DocumentoTrabajadorTO implements Serializable {

	private static final long serialVersionUID = 1L;

	/** El atributo o variable nom archivo. */
	@Schema(name = "rfc", description = "RFC del contratista.", example = "CKYH2344",
			implementation = String.class)
	private String rfc;
	
	/** El atributo o variable nom archivo. */
	@Schema(name = "contrato", description = "Numero de contrato.", example = "0004",
			implementation = String.class)
	private String contrato;
	
	/** El atributo o variable nom archivo. */
	@Schema(name = "cveProcesoPeriodico", description = "Clave del proceso.", example = "ACUSE_ICSOE_IMSS",
			implementation = String.class)
	private String cveProcesoPeriodico;
	
	/** El atributo o variable nom archivo. */
	@Schema(name = "fechaCarga", description = "Fecha de la Carga.", example = "20/02/2025",
			implementation = String.class)
	private String fechaCarga;
	
	/** El atributo o variable nom archivo. */
	@Schema(name = "nomArchivo", description = "Nombre del Archivo.", example = "image.jpg",
			implementation = String.class)
	private String nomArchivo;
	
	/** El atributo o variable secuencia archivo. */
	@Schema(name = "secuencia", description = "Secuencia del Archivo.", example = "1",
			implementation = String.class)
	private int secuencia;
	
	/** El atributo o variable nom archivo. */
	@Schema(name = "tipoPeriodo", description = "Tipo del periodo.", example = "Bimestral",
			implementation = String.class)
	private String tipoPeriodo;

	/** El atributo o variable content type. */
	@Schema(name = "contentType", description = "Content Type " +
			"(image/jpeg, image/png, image/gif, image/svg+xml, application/pdf, " +
			"application/vnd.openxmlformats-officedoc, etc)",
		example = "image/jpeg", implementation = String.class)
	private String contentType;

	/** El atributo o variable data. */
	@ArraySchema(schema = @Schema(name = "data", description = "Bytes del Archivo.", implementation = byte.class))
	private byte[] data;
}