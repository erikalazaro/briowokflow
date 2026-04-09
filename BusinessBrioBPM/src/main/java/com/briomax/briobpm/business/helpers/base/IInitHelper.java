/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */
package com.briomax.briobpm.business.helpers.base;

import java.util.List;

import com.briomax.briobpm.common.core.DAORet;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.transferobjects.ComboBoxTO;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;

/**
 * El objetivo de la Interface IInitHelper.java es ...
 * @author Rigoberto Olvera C.
 * @version 1.0 Fecha de creacion 30/01/2020 12:22:32 AM Modificaciones:
 * @since JDK 1.8
 */
public interface IInitHelper {

	/**
	 * Obtener el valor de idiomas.
	 * @param session el session.
	 * @return el idiomas.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	public DAORet<List<ComboBoxTO>, RetMsg> getIdiomas(DatosAutenticacionTO session) throws BrioBPMException;

	/**
	 * Obtener el valor de monedas.
	 * @return el monedas.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	public DAORet<List<ComboBoxTO>, RetMsg> getMonedas() throws BrioBPMException;

}
