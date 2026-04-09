/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */
package com.briomax.briobpm.business.service.catalogs.core;

import java.util.List;

import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.transferobjects.ComboBoxTO;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;

/**
 * El objetivo de la Interface ISemiFijosService.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Jul 2, 2020 4:24:12 PM Modificaciones:
 * @since JDK 1.8
 */
public interface ISemiFijosService {

	/**
	 * Obtener el valor de cmb situacion rol proceso.
	 * @param session el session.
	 * @return el cmb situacion.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	List<ComboBoxTO> getCmbSituacionRolProceso(DatosAutenticacionTO session) throws BrioBPMException;

	/**
	 * Obtener el valor de cmb situacion usuario.
	 * @param session el session.
	 * @return el cmb situacion usuario.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	List<ComboBoxTO> getCmbSituacionUsuario(DatosAutenticacionTO session) throws BrioBPMException;

	/**
	 * Obtener el valor de cmb tipo usuario.
	 * @param session el session.
	 * @return el cmb tipo usuario.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	List<ComboBoxTO> getCmbTipoUsuario(DatosAutenticacionTO session) throws BrioBPMException;

}
