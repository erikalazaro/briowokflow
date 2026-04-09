/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.persistence.dao;

import java.math.BigDecimal;
import java.util.List;

import com.briomax.briobpm.common.core.DAORet;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.persistence.dao.base.AbstractBaseDAO;
import com.briomax.briobpm.persistence.dao.base.ISemiFijosDAO;
import com.briomax.briobpm.persistence.entity.namedquery.LeeValoresColumna;

import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Class SemiFijosDAO.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Jul 2, 2020 11:20:19 AM Modificaciones:
 * @since JDK 1.8
 */
@Repository("semiFijosDAO")
@Slf4j
public class SemiFijosDAO extends AbstractBaseDAO implements ISemiFijosDAO {

	/** 
	 * {@inheritDoc}
	 * @see com.briomax.briobpm.persistence.dao.base.ISemiFijosDAO#getValoresColumna(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public DAORet<List<LeeValoresColumna>, RetMsg> getValoresColumna(String cveUsuario, String cveEntidad,
		String cveLocalidad, String cveIdioma, String cveProceso, BigDecimal version, String nombreTabla,
		String nombreColumna) throws BrioBPMException {
		log.debug("\t\t <DATABASE> EXEC SP_LEE_VALORES_COLUMNA '{}', '{}', '{}', '{}', '{}', {}, '{}', '{}'," +
			" @CH_TIPO_EXCEPCION, @CH_MENSAJE", cveUsuario, cveEntidad, cveLocalidad, cveIdioma, cveProceso, version,
			nombreTabla, nombreColumna);
		return executeNamedStored("obtenerValoresColumna",
			new String[] {"CH_CVE_USUARIO", "CH_CVE_ENTIDAD", "CH_CVE_LOCALIDAD", "CH_CVE_IDIOMA", "CH_CVE_PROCESO",
				"C_VERSION", "CH_NOMBRE_TABLA", "CH_NOMBRE_COLUMNA"},
			new Object[] {cveUsuario, cveEntidad, cveLocalidad, cveIdioma, cveProceso, version, nombreTabla,
				nombreColumna},
			RetMsg.class);
	}

}
