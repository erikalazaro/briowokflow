/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */
package com.briomax.briobpm.persistence.jdto.base;

import com.briomax.briobpm.common.core.IOutsParams;

/**
 * El objetivo de la Interface IInicioProceso.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Mar 9, 2020 3:25:44 PM Modificaciones:
 * @since JDK 1.8
 */
public interface IInicioProceso extends IOutsParams {

	/**
	 * Establecer el valor de cve instancia.
	 * @param cveInstancia el nuevo cve instancia.
	 */
	public void setCveInstancia(String cveInstancia);

	/**
	 * Obtener el valor de cve instancia.
	 * @return el cve instancia.
	 */
	public String getCveInstancia();

}
