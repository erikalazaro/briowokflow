/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.business.service.core;

import com.briomax.briobpm.common.core.DAORet;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.transferobjects.dashboards.Dashboard;
import com.briomax.briobpm.transferobjects.dashboards.OperativoGerenteIn;
import com.briomax.briobpm.transferobjects.dashboards.OperativoIn;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;

/**
 * El objetivo de la Interface IDashboardsService.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion May 12, 2021 4:18:42 PM Modificaciones:
 * @since JDK 1.8
 */
public interface IDashboardsService {

	/**
	 * Operativo.
	 * @param session el session.
	 * @param filtros el filtros.
	 * @return el DAO ret.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	DAORet<Dashboard, RetMsg> operativo(DatosAutenticacionTO session, OperativoIn filtros) throws BrioBPMException;

	/**
	 * Operativo gerente.
	 * @param session el session.
	 * @param filtros el filtros.
	 * @return el DAO ret.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	DAORet<Dashboard, RetMsg> operativoGerente(DatosAutenticacionTO session, OperativoGerenteIn filtros)
		throws BrioBPMException;

}
