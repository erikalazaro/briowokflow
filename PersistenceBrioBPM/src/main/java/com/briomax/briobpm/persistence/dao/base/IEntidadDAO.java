/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.persistence.dao.base;

import java.util.List;

import com.briomax.briobpm.common.core.DAORet;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.persistence.entity.namedquery.LeeEntidades;
import com.briomax.briobpm.persistence.entity.namedquery.Localidad;

/**
 * El objetivo de la Interface IEntidadDAO.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Feb 26, 2020 4:09:13 PM Modificaciones:
 * @since JDK 1.8
 */
public interface IEntidadDAO {

	/**
	 * Obtener entidades.
	 * @param cveUsuario el cve usuario.
	 * @param cveEntidad el cve entidad.
	 * @param cveLocalidad el cve localidad.
	 * @param cveIdioma el cve idioma.
	 * @return el DAO ret.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	public DAORet<List<LeeEntidades>, RetMsg> obtenerEntidades(String cveUsuario, String cveEntidad,
		String cveLocalidad, String cveIdioma) throws BrioBPMException;

	/**
	 * Lee datos localidad.
	 * @param cveUsuario el cve usuario.
	 * @param cveEntidad el cve entidad.
	 * @param cveLocalidad el cve localidad.
	 * @param cveIdioma el cve idioma.
	 * @return el DAO ret.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	public DAORet<Localidad, RetMsg> leeDatosLocalidad(String cveUsuario, String cveEntidad, String cveLocalidad,
		String cveIdioma) throws BrioBPMException;

}
