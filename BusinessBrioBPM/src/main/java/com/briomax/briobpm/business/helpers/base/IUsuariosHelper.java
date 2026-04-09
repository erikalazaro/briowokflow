/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.business.helpers.base;

import com.briomax.briobpm.common.core.DAORet;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.transferobjects.DatosUsuarioTO;

/**
 * El objetivo de la Interface IUsuariosHelper.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Feb 26, 2020 11:21:09 AM Modificaciones:
 * @since JDK 1.8
 */
public interface IUsuariosHelper {

	/**
	 * @param entidad el entidad.
	 * @param localidad el localidad.
	 * @param cveUsuario el cve usuario.
	 * @param password el password.
	 * @param cveIdioma el cve idioma.
	 * @return el DAO ret.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	public DAORet<DatosUsuarioTO, RetMsg> authenticate(String entidad, String localidad, String cveUsuario,
		String password, String cveIdioma) throws BrioBPMException;

}
