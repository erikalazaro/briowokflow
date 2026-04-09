/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */
package com.briomax.briobpm.business.helpers.base;

import com.briomax.briobpm.common.exception.BrioBPMException;

/**
 * El objetivo de la Interface ICriptography.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Jul 27, 2020 5:48:06 PM Modificaciones:
 * @since JDK 1.8
 */
public interface ICriptography {

	/**
	 * Crypt text.
	 * @param text el text.
	 * @return el string.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	public String cryptText(String text) throws BrioBPMException;

	/**
	 * Hash text.
	 * @param text el text.
	 * @return el string.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	public String hashText(String text) throws BrioBPMException;

}
