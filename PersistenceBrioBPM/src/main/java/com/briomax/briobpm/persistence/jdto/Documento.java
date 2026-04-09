/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.persistence.jdto;

import java.io.Serializable;
import java.sql.Blob;
import java.util.HashMap;

import com.briomax.briobpm.persistence.jdto.base.IDocumento;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * El objetivo de la Class Documento.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Mar 9, 2020 11:13:35 AM Modificaciones:
 * @since JDK 1.8
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Documento implements IDocumento, Serializable {

	/** Serial Version UID. */
	private static final long serialVersionUID = -701775080756174347L;

	/** El atributo o variable status. */
	private String status;

	/** El atributo o variable message. */
	private String message;

	/** El atributo o variable nombre. */
	private String nombre;

	/** El atributo o variable content type. */
	private String contentType;

	/** El atributo o variable contenido. */
	private Blob contenido;

	private byte[] data;
	
	/** 
	 * {@inheritDoc}
	 * @see com.briomax.briobpm.common.core.IOutsParams#setOutParams(java.util.HashMap)
	 */
	@Override
	public void setOutParams(HashMap<String, Object> params) {
		this.status = (String) params.get("CH_TIPO_EXCEPCION");
		this.message = (String) params.get("CH_MENSAJE");
		this.nombre = (String) params.get("CH_NOMBRE_ARCHIVO");
		this.contentType = (String) params.get("CH_CONTENT_TYPE");
		this.contenido = (Blob) params.get("VB_ARCHIVO_BINARIO");
	}

}
