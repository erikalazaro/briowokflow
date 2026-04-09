/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.persistence.dao;

import java.util.List;

import com.briomax.briobpm.common.core.DAORet;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.persistence.dao.base.AbstractBaseDAO;
import com.briomax.briobpm.persistence.dao.base.IIdiomasDAO;
import com.briomax.briobpm.persistence.entity.namedquery.LeeIdiomas;

import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Class IdiomasDAO.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Mar 10, 2020 12:07:17 PM Modificaciones:
 * @since JDK 1.8
 */
@Repository("idiomasDAO")
@Slf4j
public class IdiomasDAO extends AbstractBaseDAO implements IIdiomasDAO {

	/** 
	 * {@inheritDoc}
	 * @see com.briomax.briobpm.persistence.dao.base.IIdiomasDAO#obtenerIdiomas(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public DAORet<List<LeeIdiomas>, RetMsg> obtenerIdiomas(String cveUsuario, String cveEntidad, String cveLocalidad,
		String cveIdioma) throws BrioBPMException {
		log.debug("\t\t <DATABASE> EXEC SP_LEE_VALORES_COLUMNA '{}', '{}', '{}', '{}', @CH_TIPO_EXCEPCION, @CH_MENSAJE",
			cveUsuario, cveEntidad, cveLocalidad, cveIdioma);
		return executeNamedStored("obtenerIdiomas",
			new String[] {"CH_CVE_USUARIO", "CH_CVE_ENTIDAD", "CH_CVE_LOCALIDAD", "CH_CVE_IDIOMA"},
			new String[] {cveUsuario, cveEntidad, cveLocalidad, cveIdioma}, RetMsg.class);
	}

}
