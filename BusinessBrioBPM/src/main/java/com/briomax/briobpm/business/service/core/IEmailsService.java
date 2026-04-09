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
import com.briomax.briobpm.persistence.entity.CrProgramacionProceso;
import com.briomax.briobpm.persistence.entity.namedquery.LeeCorreosPorEnviar;
import com.briomax.briobpm.transferobjects.in.DestinatarioTO;

/**
 * El objetivo de la Interface IEmailsService.java es ...
 * 
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Apr 13, 2020 10:29:55 AM Modificaciones:
 * @since JDK 1.8
 */
public interface IEmailsService {

	/**
	 * Send emails.
	 * 
	 * @param cveUsuario   el cve usuario.
	 * @param cveEntidad   el cve entidad.
	 * @param cveLocalidad el cve localidad.
	 * @param cveIdioma    el cve idioma.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	public void sendEmails(String cveUsuario, String cveEntidad, String cveLocalidad, String cveIdioma)
			throws BrioBPMException;

	public DAORet<List<LeeCorreosPorEnviar>, RetMsg> leeCorreosEnviar() throws BrioBPMException;

	/**
	 * Notificciones procesos normales
	 * @param cveEntidad
	 * @param cveLocalidad
	 * @param idioma
	 */
	void notifiacionUsuarios(String cveEntidad, String cveLocalidad, String idioma);
	
	/**
	 * Notificaciones porcesos REPSE
	 * @param cveEntidad
	 * @param cveLocalidad
	 * @param cveIdioma
	 */
	void notifiacionUsuariosRepse(String cveEntidad, String cveLocalidad, String cveIdioma);

	DestinatarioTO obtenerDestinatarios(String cveEntidad, String cveLocalidad, String cveIdioma,
			String cveProcesoPeriodico, Integer secuenciaCorreo);
	

	public void actualizarFechaUltimaNotificacion(CrProgramacionProceso proceso, Integer horas);

	public void notifiacionNewUsuarios();

}