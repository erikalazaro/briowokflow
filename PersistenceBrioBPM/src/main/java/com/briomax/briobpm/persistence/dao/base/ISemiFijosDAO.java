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
import com.briomax.briobpm.persistence.entity.namedquery.LeeValoresColumna;

/**
 * El objetivo de la Interface ISemiFijoDAO.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Jul 2, 2020 9:50:01 AM Modificaciones:
 * @since JDK 1.8
 */
public interface ISemiFijosDAO {

	/**
	 * Obtener el valor de valores columna.
	 * @param cveUsuario el cve usuario.
	 * @param cveEntidad el cve entidad.
	 * @param cveLocalidad el cve localidad.
	 * @param cveIdioma el cve idioma.
	 * @param cveProceso el cve proceso.
	 * @param version el version.
	 * @param nombreTabla el nombre tabla.
	 * @param nombreColumna el nombre columna.
	 * @return el valores columna.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	public DAORet<List<LeeValoresColumna>, RetMsg> getValoresColumna(String cveUsuario, String cveEntidad,
		String cveLocalidad, String cveIdioma, String cveProceso, BigDecimal version, String nombreTabla,
		String nombreColumna) throws BrioBPMException;

}
