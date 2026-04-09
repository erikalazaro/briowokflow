/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.persistence.dao.base;

import java.math.BigDecimal;
import java.util.List;

import com.briomax.briobpm.common.core.DAORet;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.persistence.entity.namedquery.LeeCorreosPorEnviar;

/**
 * El objetivo de la Interface IEmailsDAO.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Apr 13, 2020 10:23:19 AM Modificaciones:
 * @since JDK 1.8
 */
public interface IEmailsDAO {

	/**
	 * Obtener correos por enviar.
	 * @return el DAO ret.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	public DAORet<List<LeeCorreosPorEnviar>, RetMsg> obtenerCorreosPorEnviar() throws BrioBPMException;

	/**
	 * Actualiza situacion correo.
	 * @param cveUsuario el cve usuario.
	 * @param cveEntidad el cve entidad.
	 * @param cveLocalidad el cve localidad.
	 * @param cveIdioma el cve idioma.
	 * @param cveProceso el cve proceso.
	 * @param version el version.
	 * @param numCorreo el num correo.
	 * @param situacion el situacion.
	 * @return el ret msg.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	public RetMsg actualizaSituacionCorreo(String cveUsuario, String cveEntidad, String cveLocalidad, String cveIdioma,
		String cveProceso, BigDecimal version, Long numCorreo, String situacion) throws BrioBPMException;
}
