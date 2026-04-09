/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados. Este software contiene informacion
 * confidencial propiedad de Briomax Consulting por lo cual no puede ser reproducido, distribuido o alterado sin el
 * consentimiento previo de Briomax Consulting.
 */

package com.briomax.briobpm.business.service.catalogs.core;

import java.util.List;

import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.transferobjects.ComboBoxTO;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;

/**
 * El objetivo de la Interface IUsarioService.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Jul 27, 2020 2:07:00 PM Modificaciones:
 * @since JDK 1.8
 */
public interface IFacultadService {

	/**
	 * Obtener todas las facultades.
	 * @param session el session.
	 * @return el all.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	List<ComboBoxTO> getCmbFacultad(DatosAutenticacionTO session) throws BrioBPMException;

	
}
