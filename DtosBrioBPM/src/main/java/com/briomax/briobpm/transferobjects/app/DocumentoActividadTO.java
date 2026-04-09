/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.transferobjects.app;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(title = "DocumentoActividadTO.", description = "Documento de una Actividad.")
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true, exclude = {"data"})
public class DocumentoActividadTO implements Serializable {

	/** Serial Version UID. */
	private static final long serialVersionUID = 2199023171347854729L;

	/** El atributo o variable cve proceso. */
	@Schema(name = "cveProceso", description = "Clave del Proceso.", example = "DUMMY", implementation = String.class)
	private String cveProceso;

	/** El atributo o variable version. */
	@Schema(name = "version", description = "Versión del proceso", example = "1.00", implementation = BigDecimal.class)
	private BigDecimal version;
	
	/** El atributo o variable cve nodo. */
	@Schema(name = "cveInstancia", description = "Clave de la instacia.", example = "ACTIVIDAD-USUARIO",
			implementation = String.class)
	private String cveInstancia;

	/** El atributo o variable cve nodo. */
	@Schema(name = "cveNodo", description = "Clave del Nodo.", example = "ACTIVIDAD-USUARIO",
			implementation = String.class)
	private String cveNodo;

	/** El atributo o variable id nodo. */
	@Schema(name = "idNodo", description = "Identificador del Nodo", example = "1", implementation = Integer.class)
	private Integer idNodo;

	/** El atributo o variable actividades. */
	@Schema(name = "secNodo", description = "secuencia del nodo", example = "1", implementation = Integer.class)
	private Integer secNodo;
	
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

}
