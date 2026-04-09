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
import com.briomax.briobpm.persistence.dao.base.IEntidadDAO;
import com.briomax.briobpm.persistence.entity.namedquery.LeeEntidades;
import com.briomax.briobpm.persistence.entity.namedquery.Localidad;

import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Class EntidadDAO.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Jul 14, 2021 1:18:07 PM Modificaciones:
 * @since JDK 1.8
 */
@Repository("entidadDAO")
@Slf4j
public class EntidadDAO extends AbstractBaseDAO implements IEntidadDAO {

	/** 
	 * {@inheritDoc}
	 * @see com.briomax.briobpm.persistence.dao.base.IEntidadDAO#obtenerEntidades(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public DAORet<List<LeeEntidades>, RetMsg> obtenerEntidades(String cveUsuario, String cveEntidad, String cveLocalidad,
		String cveIdioma) throws BrioBPMException {
		log.debug("\t\t <DATABASE> EXEC SP_LEE_ENTIDADES '{}', '{}', '{}', '{}', @CH_TIPO_EXCEPCION, @CH_MENSAJE",
			cveUsuario, cveEntidad, cveLocalidad, cveIdioma);
		return executeNamedStored("obtenerEntidades",
			new String[] {"CH_CVE_USUARIO", "CH_CVE_ENTIDAD", "CH_CVE_LOCALIDAD", "CH_CVE_IDIOMA"},
			new String[] {cveUsuario, cveEntidad, cveLocalidad, cveIdioma}, RetMsg.class);
	}

	/** 
	 * {@inheritDoc}
	 * @see com.briomax.briobpm.persistence.dao.base.IEntidadDAO#leeDatosLocalidad(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public DAORet<Localidad, RetMsg> leeDatosLocalidad(String cveUsuario, String cveEntidad, String cveLocalidad,
		String cveIdioma) throws BrioBPMException {
		DAORet<List<Localidad>, RetMsg> list = executeNamedStored("leeDatosLocalidad",
			new String[] {"CH_CVE_USUARIO", "CH_CVE_ENTIDAD", "CH_CVE_LOCALIDAD", "CH_CVE_IDIOMA"},
			new String[] {cveUsuario, cveEntidad, cveLocalidad, cveIdioma}, RetMsg.class);
		if (list.getContent().isEmpty()) {
			return new DAORet<Localidad, RetMsg>(null, list.getMeta());
		}
		else {
			return new DAORet<Localidad, RetMsg>(list.getContent().get(0), list.getMeta());
		}
	}
}
