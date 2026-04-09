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
import com.briomax.briobpm.transferobjects.app.ProcesoTO;
import com.briomax.briobpm.transferobjects.catalogs.Proceso;
import com.briomax.briobpm.transferobjects.catalogs.ProcesosTO;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;


/**
 * El objetivo de la Interface IProcesoService.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Jun 26, 2020 12:26:05 PM Modificaciones:
 * @since JDK 1.8
 */
public interface IProcesoService {

	/**
	 * Obtener el valor de procesos.
	 * @param session el session.
	 * @return el procesos.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	List<Proceso> getProcesos(DatosAutenticacionTO session) throws BrioBPMException;

	/**
	 * Get All.
	 * @param session el session.
	 * @return el all.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	List<ProcesosTO> getAll(DatosAutenticacionTO session) throws BrioBPMException;
	
	/**
	 * Obtener los procegsos que tiene un usuario para la app
	 * @param cveEntidad
	 * @param cveUsuario
	 * @param cveIdioma
	 * @param cveLocalidad
	 * @return
	 * @throws BrioBPMException
	 */
	List<ProcesoTO> getProcesosByUser(String cveEntidad, String cveUsuario, String cveIdioma, String cveLocalidad) throws BrioBPMException;

}
