/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.transferobjects.in;

import java.io.Serializable;

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
@Schema(title = "DocumentoTO.", description = "Documento de una Actividad.")
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true, exclude = {"data"})
public class DocumentoTO extends ActividadTO implements Serializable {

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
	@ArraySchema(schema = @Schema(name = "data", description = "Bytes del Archivo.", implementation = byte.class))
	private byte[] data;

	private Integer ocurrencia;
	
	/**
	 * Crear una nueva instancia del objeto documento TO.
	 * @param cveRol el cve rol.
	 * @param cveProceso el cve proceso.
	 * @param version el version.
	 * @param cveNodo el cve nodo.
	 * @param idNodo el id nodo.
	 * @param cveAreaTrabajo el cve area trabajo.
	 * @param cveInstancia el cve instancia.
	 * @param secNodo el sec nodo.
	 * @param secDocumento el sec documento.
	 * @param nomArchivo el nom archivo.
	 * @param dataArchivo el data archivo.
	 * @param contentType el content type.
	 */
	@Builder(builderMethodName = "builderDocumentoTO")
	public DocumentoTO(String cveRol, String cveProceso, String version, String cveNodo, Integer idNodo,
		String cveAreaTrabajo, String cveInstancia, Integer secNodo, String origen, Integer secDocumento, String nomArchivo,
		String contentType) {
		super(cveRol, cveProceso, version, cveNodo, idNodo, cveAreaTrabajo, cveInstancia, secNodo, origen);
		this.secDocumento = secDocumento;
		this.nomArchivo = nomArchivo;
		this.contentType = contentType;
	}

}