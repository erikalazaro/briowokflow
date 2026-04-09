/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.business.service.core;

import java.util.List;

import com.briomax.briobpm.common.core.DAORet;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.transferobjects.MenuAreaTrabajoTO;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;

/**
 * El objetivo de la Interface IAreaTrabajoService.java es ...
 * @author RIGOBERTO OLVERA
 * @version 1.0 Fecha de creacion 29/01/2020 12:31:30 PM Modificaciones:
 * @since JDK 1.8
 */
public interface IMenuService {

	/**
	 * Obtener menu area trabajo.
	 * @param session Datos autenticacion.
	 * @return el DAO ret.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	DAORet<List<MenuAreaTrabajoTO>, RetMsg> obtenerMenuAreaTrabajo(DatosAutenticacionTO session)
		throws BrioBPMException;

	/**
	 * Obtener menu dashboard.
	 * @param datosAutenticacion el datos autenticacion.
	 * @return el DAO ret.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	DAORet<List<MenuAreaTrabajoTO>, RetMsg> obtenerMenuDashboard(DatosAutenticacionTO datosAutenticacion)
		throws BrioBPMException;
	
	

}
