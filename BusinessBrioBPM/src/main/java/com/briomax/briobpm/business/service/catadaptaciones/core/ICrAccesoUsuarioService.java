/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados. Este software contiene informacion
 * confidencial propiedad de Briomax Consulting por lo cual no puede ser reproducido, distribuido o alterado sin el
 * consentimiento previo de Briomax Consulting.
 */

package com.briomax.briobpm.business.service.catadaptaciones.core;

import java.util.List;

import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;
import com.briomax.briobpm.transferobjects.repse.CrUsuarioTO;

/**
 * El objetivo de la Interface ICrAccesoUsuarioService.java es ...
 * @author Sara Ventura
 * @version 1.0 Fecha de creacion Nov 25, 2025 12:08:29 Modificaciones:
 * @since JDK 1.8
 */
public interface ICrAccesoUsuarioService {

	/**
	 * Obtener el valor de all.
	 * @param session el session.
	 * @return el all.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	List<CrUsuarioTO> getAll(DatosAutenticacionTO session) throws BrioBPMException;

	/**
	 * Insert.
	 * @param session el session.
	 * @param usuario el usuario.
	 * @return el ret msg.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	RetMsg insert(DatosAutenticacionTO session, CrUsuarioTO usuario) throws BrioBPMException;

	/**
	 * Update.
	 * @param session el session.
	 * @param usuario el usuario.
	 * @return el ret msg.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	RetMsg update(DatosAutenticacionTO session, CrUsuarioTO usuario) throws BrioBPMException;
	
	
}
