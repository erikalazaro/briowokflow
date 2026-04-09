/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.business.service.catalogs.core;

import java.math.BigDecimal;
import java.util.List;

import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.transferobjects.app.ActividadAutorizarTO;

/**
 * El objetivo de la Interface IProcesoService.java es ...
 * 
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Jun 26, 2020 12:26:05 PM Modificaciones:
 * @since JDK 1.8
 */
public interface IActividadService {

	/**
	 * Obtiene las actividades de un usuario y un proceso
	 * @param cveEntidad
	 * @param cveProceso
	 * @param version
	 * @param cveUsuario
	 * @return
	 * @throws BrioBPMException
	 */
	List<ActividadAutorizarTO> getActividadByProcesos(String cveEntidad, String cveProceso, BigDecimal version, String cveUsuario, String cveIdioma) throws BrioBPMException;

}
