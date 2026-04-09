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
import com.briomax.briobpm.persistence.entity.namedquery.LeeInfReporteActividades;
import com.briomax.briobpm.persistence.entity.namedquery.LeeInfReporteProcesos;

/**
 * El objetivo de la Interface IReportesOperativosDAO.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Oct 6, 2020 7:02:00 PM Modificaciones:
 * @since JDK 1.8
 */
public interface IReportesOperativosDAO {

	/**
	 * Obtener reporte procesos.
	 * @param cveUsuario el cve usuario.
	 * @param cveEntidad el cve entidad.
	 * @param cveLocalidad el cve localidad.
	 * @param cveIdioma el cve idioma.
	 * @return el DAO ret.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	public DAORet<List<LeeInfReporteProcesos>, RetMsg> obtenerReporteProcesos(String cveUsuario, String cveEntidad,
		String cveLocalidad, String cveIdioma) throws BrioBPMException;

	/**
	 * Obtener reporte actividades.
	 * @param cveUsuario el cve usuario.
	 * @param cveEntidad el cve entidad.
	 * @param cveLocalidad el cve localidad.
	 * @param cveIdioma el cve idioma.
	 * @return el DAO ret.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	public DAORet<List<LeeInfReporteActividades>, RetMsg> obtenerReporteActividades(String cveUsuario, String cveEntidad,
		String cveLocalidad, String cveIdioma) throws BrioBPMException;
	
}
