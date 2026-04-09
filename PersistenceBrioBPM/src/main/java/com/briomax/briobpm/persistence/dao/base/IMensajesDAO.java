/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.persistence.dao.base;

import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;

/**
 * El objetivo de la Interface IMensajesDAO.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion May 19, 2020 11:22:43 AM Modificaciones:
 * @since JDK 1.8
 */
public interface IMensajesDAO {

	/**
	 * Obtener el valor de message.
	 * @param cveUsuario el cve usuario.
	 * @param cveEntidad el cve entidad.
	 * @param cveLocalidad el cve localidad.
	 * @param cveIdioma el cve idioma.
	 * @param idProceso el id proceso.
	 * @param codMensaje el cod mensaje.
	 * @param varValor el var valor.
	 * @return el message.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	public RetMsg getMessage(String cveUsuario, String cveEntidad, String cveLocalidad, String cveIdioma,
		String idProceso, String codMensaje, String varValor) throws BrioBPMException;
	
}
