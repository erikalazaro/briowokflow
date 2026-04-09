/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.business.helpers.base;

import java.util.List;

import com.briomax.briobpm.common.core.DAORet;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.transferobjects.ComboBoxTO;
import com.briomax.briobpm.transferobjects.LocalidadTO;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;

/**
 * El objetivo de la Interface IEntidadHelper.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Feb 26, 2020 4:11:43 PM Modificaciones:
 * @since JDK 1.8
 */
public interface IEntidadHelper {

	/**
	 * Obtener el valor de entidades.
	 * @param session el session.
	 * @return el entidades.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	public DAORet<List<ComboBoxTO>, RetMsg> getEntidades(DatosAutenticacionTO session) throws BrioBPMException;

	/**
	 * Obtener el valor de datos localidad.
	 * @param cveUsuario el cve usuario.
	 * @param cveEntidad el cve entidad.
	 * @param cveLocalidad el cve localidad.
	 * @param cveIdioma el cve idioma.
	 * @return el datos localidad.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	DAORet<LocalidadTO, RetMsg> getDatosLocalidad(String cveUsuario, String cveEntidad, String cveLocalidad,
		String cveIdioma) throws BrioBPMException;

	/**
	 * Obtener el valor de localidades.
	 * @param cveEntidad el cve entidad.
	 * @return el localidades.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	public List<ComboBoxTO> getLocalidades(String cveEntidad) throws BrioBPMException;

	/**
	 * Obtener el valor de hora.
	 * @param userSession el user session.
	 * @return el hora.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	DAORet<String, RetMsg> getHora(DatosAutenticacionTO userSession) throws BrioBPMException;

}
