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

import com.briomax.briobpm.common.core.DAORet;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.persistence.entity.namedquery.LeeMensajesReglas;
import com.briomax.briobpm.transferobjects.in.ActividadTO;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;

/**
 * El objetivo de la Interface IEmailsService.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Apr 13, 2020 10:29:55 AM Modificaciones:
 * @since JDK 1.8
 */
public interface IMessagesService {

	/**
	 * Obtener el valor de message.
	 * @param codigoMensaje el codigo mensaje.
	 * @param cveIdioma el cve idioma.
	 * @return el message.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	String getMessage(String codigoMensaje, String cveIdioma) throws BrioBPMException;

	/**
	 * Obtener el valor de message.
	 * @param session el session.
	 * @param claveProceso el id proceso.
	 * @param codigoMensaje el codigo mensaje.
	 * @param variablesValores el variables valores.
	 * @return el message.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	String getMessage(DatosAutenticacionTO session, String claveProceso, String codigoMensaje, String variablesValores)
		throws BrioBPMException;

	RetMsg actualizaSItuacionCorreo(DatosAutenticacionTO session, String cveProceso, BigDecimal version,
			Integer numeroCorreo, String situacion) throws BrioBPMException;
	
	
	DAORet<List<LeeMensajesReglas>, RetMsg> leeMensajesReglas (DatosAutenticacionTO session, ActividadTO actividad);
	

}
