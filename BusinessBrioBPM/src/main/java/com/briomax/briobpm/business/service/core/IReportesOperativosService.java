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
import com.briomax.briobpm.transferobjects.ReporteActividadesTO;
import com.briomax.briobpm.transferobjects.ReporteProcesosTO;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;

/**
 * El objetivo de la Interface IReportesService.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Oct 6, 2020 6:07:15 PM Modificaciones:
 * @since JDK 1.8
 */
public interface IReportesOperativosService {

	/**
	 * Obtener reporte proceso.
	 * @param session el session.
	 * @return el DAO ret.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	DAORet<List<ReporteProcesosTO>, RetMsg> obtenerReporteProcesos(DatosAutenticacionTO session)
		throws BrioBPMException;

	/**
	 * Obtener reporte actividad.
	 * @param session el session.
	 * @return el DAO ret.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	DAORet<List<ReporteActividadesTO>, RetMsg> obtenerReporteActividades(DatosAutenticacionTO session)
			throws BrioBPMException;

}
