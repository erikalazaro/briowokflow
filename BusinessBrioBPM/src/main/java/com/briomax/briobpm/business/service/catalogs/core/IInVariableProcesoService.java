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
import com.briomax.briobpm.transferobjects.TrabajadoresTO;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;


/**
 * El objetivo de la Interface IRolProcesoService.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Jun 26, 2020 12:26:05 PM Modificaciones:
 * @since JDK 1.8
 */
public interface IInVariableProcesoService {

	/**
	 * Obtiene los RFC
	 * @param session
	 * @return
	 * @throws BrioBPMException
	 */
	List<ComboBoxTO> getAllNombres(DatosAutenticacionTO session)
		throws BrioBPMException;

	/**
	 * 
	 * @param session
	 * @param rfc
	 * @return
	 * @throws BrioBPMException
	 */
	List<ComboBoxTO> getContratosByRfc(DatosAutenticacionTO session, String rfc) 
			throws BrioBPMException;

	/**
	 * 
	 * @param session
	 * @param cveInstancia
	 * @param cveProceso
	 * @param ocurrencia
	 * @return
	 * @throws BrioBPMException
	 */
	String eliminaTrabajador(DatosAutenticacionTO session, String cveInstancia, String cveProceso, Integer ocurrencia)
			throws BrioBPMException;

	/**
	 * 
	 * @param userSession
	 * @param to
	 * @return
	 * @throws BrioBPMException
	 */
	String ingresarTrabajador(DatosAutenticacionTO userSession, TrabajadoresTO to) throws BrioBPMException;

	/**
	 * obtiene el dator del contrato por la cve del proceso y en rfc seleccionado
	 * @param session
	 * @param rfc
	 * @param cveProceso
	 * @return
	 * @throws BrioBPMException
	 */
	List<ComboBoxTO> getContratosByRfcProceso(DatosAutenticacionTO session, String rfc, String cveProceso) 
			throws BrioBPMException;

	/**
	 * obtiene el dator del contrato por la cve del proceso y en rfc seleccionado
	 * @param session
	 * @param rfc
	 * @return
	 * @throws BrioBPMException
	 */
	List<ComboBoxTO> getContratosByRfcProcesos(DatosAutenticacionTO session, String rfc) throws BrioBPMException;

}
