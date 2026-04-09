/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */
package com.briomax.briobpm.business.service.core;

import java.text.ParseException;

import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.transferobjects.SaveSectionTO;
import com.briomax.briobpm.transferobjects.in.ActividadTO;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;

/**
 * El objetivo de la Interface IEjecucionActividadService.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Mar 5, 2020 1:02:36 PM Modificaciones:
 * @since JDK 1.8
 */
public interface IEjecucionActividadService {

	/**
	 * Guardar informacion actividad.
	 * @param session el session.
	 * @param dataSections el data sections.
	 * @return el ret msg.
	 * @throws BrioBPMException la brio BPM exception.
	 * @throws ParseException 
	 */
	RetMsg guardarActividad(DatosAutenticacionTO session, SaveSectionTO dataSections)
		throws BrioBPMException, ParseException;

	/**
	 * Terminar actividad.
	 * @param session el session.
	 * @param dataSections el data sections.
	 * @return el ret msg.
	 * @throws BrioBPMException la brio BPM exception.
	 * @throws ParseException 
	 */
	RetMsg guardarTerminarActividad(DatosAutenticacionTO session, SaveSectionTO dataSections) throws BrioBPMException, ParseException;

	/**
	 * Obtener actividad.
	 * @param session el session.
	 * @param activity el activity.
	 * @return el ret msg.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	RetMsg obtenerActividad(DatosAutenticacionTO session, ActividadTO activity) throws BrioBPMException;

	/**
	 * Liberar actividad.
	 * @param session el session.
	 * @param activity el activity.
	 * @return el ret msg.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	RetMsg liberarActividad(DatosAutenticacionTO session, ActividadTO activity) throws BrioBPMException;

	/**
	 * Cancelar actividad.
	 * @param session el session.
	 * @param activity el activity.
	 * @return el ret msg.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	RetMsg cancelarActividad(DatosAutenticacionTO session, ActividadTO activity) throws BrioBPMException;

	/**
	 * Terminar actividad.
	 * @param session el session.
	 * @param activity el activity.
	 * @return el ret msg.
	 * @throws BrioBPMException la brio BPM exception.
	 * @throws ParseException 
	 */
	RetMsg terminarActividad(DatosAutenticacionTO session, ActividadTO activity) throws BrioBPMException, ParseException;

}
