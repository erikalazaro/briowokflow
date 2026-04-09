/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */
package com.briomax.briobpm.business.service.core;

import java.text.ParseException;

import com.briomax.briobpm.common.core.DAORet;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.transferobjects.dynamicForm.FormularioDinamico;
import com.briomax.briobpm.transferobjects.in.ActividadTO;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;
import com.briomax.briobpm.transferobjects.in.NodoTO;

/**
 * El objetivo de la Interface IEjecucionActividadService.java es ...
 * @author Rigoberto Olvera C.
 * @version 1.0 Fecha de creacion 29/01/2020 12:22:15 PM Modificaciones:
 * @since JDK 1.8
 */
public interface IFormularioDinamicoService {

	/**
	 * Obtener formulario dinamico.
	 * @param session el session.
	 * @param actividad el actividad.
	 * @return el Formulario Dinamico.
	 * @throws BrioBPMException la brio BPM exception.
	 * @throws ParseException 
	 */
	public DAORet<FormularioDinamico, RetMsg> obtenerFormularioDinamico(DatosAutenticacionTO session,
		ActividadTO actividad) throws BrioBPMException, ParseException;
	
	
	public DAORet<FormularioDinamico, RetMsg> obtenerFormularioDinamicoMovil(DatosAutenticacionTO session,
			NodoTO actividad) throws BrioBPMException, ParseException;
}
