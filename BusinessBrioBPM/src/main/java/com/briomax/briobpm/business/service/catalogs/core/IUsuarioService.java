/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados. Este software contiene informacion
 * confidencial propiedad de Briomax Consulting por lo cual no puede ser reproducido, distribuido o alterado sin el
 * consentimiento previo de Briomax Consulting.
 */

package com.briomax.briobpm.business.service.catalogs.core;

import java.util.List;

import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.transferobjects.catalogs.UsuarioResetTO;
import com.briomax.briobpm.transferobjects.catalogs.UsuarioTO;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;

/**
 * El objetivo de la Interface IUsarioService.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Jul 27, 2020 2:07:00 PM Modificaciones:
 * @since JDK 1.8
 */
public interface IUsuarioService {

	/**
	 * Obtener el valor de all.
	 * @param session el session.
	 * @return el all.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	List<UsuarioTO> getAll(DatosAutenticacionTO session) throws BrioBPMException;

	/**
	 * Insert.
	 * @param session el session.
	 * @param usuario el usuario.
	 * @return el ret msg.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	RetMsg insert(DatosAutenticacionTO session, UsuarioTO usuario) throws BrioBPMException;

	/**
	 * Update.
	 * @param session el session.
	 * @param usuario el usuario.
	 * @return el ret msg.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	RetMsg update(DatosAutenticacionTO session, UsuarioTO usuario) throws BrioBPMException;
	
	/**
	 * Cambio de contraseña
	 * @param session
	 * @param usuario
	 * @return
	 * @throws BrioBPMException
	 */
	RetMsg actualizaPassword(DatosAutenticacionTO session, UsuarioTO usuario) throws BrioBPMException;

	/**
	 * Cambio de situación
	 * @param session
	 * @param usuario
	 * @return
	 * @throws BrioBPMException
	 */
	RetMsg actualizaSituacion(DatosAutenticacionTO session, UsuarioTO usuario) throws BrioBPMException;
	
	/**
	 * Cambio de situación
	 * @param usuario
	 * @return
	 * @throws BrioBPMException
	 */
	RetMsg resetPassword(UsuarioResetTO usuario) throws BrioBPMException;
	
	/**
	 * Cambio de situación
	 * @param usuario
	 * @return
	 * @throws BrioBPMException
	 */
	RetMsg restauraPassword(UsuarioResetTO usuario) throws BrioBPMException;
}
