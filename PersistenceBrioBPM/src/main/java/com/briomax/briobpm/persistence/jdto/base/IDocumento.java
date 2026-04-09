/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */
package com.briomax.briobpm.persistence.jdto.base;

import java.sql.Blob;

import com.briomax.briobpm.common.core.IOutsParams;

/**
 * El objetivo de la Interface IDocumento.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Mar 9, 2020 11:14:11 AM Modificaciones:
 * @since JDK 1.8
 */
public interface IDocumento extends IOutsParams {

	/**
	 * Establecer el valor de contenido.
	 * @param contenido el nuevo contenido.
	 */
	public void setContenido(Blob contenido);

	/**
	 * Obtener el valor de contenido.
	 * @return el contenido.
	 */
	public Blob getContenido();

	/**
	 * Establecer el valor de nombre.
	 * @param nombre el nuevo nombre.
	 */
	public void setNombre(String nombre);

	/**
	 * Obtener el valor de nombre.
	 * @return el nombre.
	 */
	public String getNombre();

}
