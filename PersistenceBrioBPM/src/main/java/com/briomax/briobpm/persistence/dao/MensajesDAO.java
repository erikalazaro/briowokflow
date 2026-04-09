/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.persistence.dao;

import javax.persistence.ParameterMode;

import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.persistence.dao.base.AbstractBaseDAO;
import com.briomax.briobpm.persistence.dao.base.IMensajesDAO;

import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Class MensajesDAO.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion May 19, 2020 11:27:19 AM Modificaciones:
 * @since JDK 1.8
 */
@Repository("mensajesDAO")
@Slf4j
public class MensajesDAO extends AbstractBaseDAO implements IMensajesDAO {

	/** 
	 * {@inheritDoc}
	 * @see com.briomax.briobpm.persistence.dao.base.IMensajesDAO#getMessage(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public RetMsg getMessage(String cveUsuario, String cveEntidad, String cveLocalidad, String cveIdioma,
		String idProceso, String codMensaje, String varValor) throws BrioBPMException {
		log.debug("\t\t <DATABASE> EXEC SP_OBTEN_MENSAJE_CODIGO '{}', '{}', '{}', '{}', '{}', '{}', '{}', " +
			" @CH_TIPO_EXCEPCION, @CH_MENSAJE", cveUsuario, cveEntidad, cveLocalidad, cveIdioma, idProceso, codMensaje,
			varValor);
		return executeNoResulsetStored("SP_OBTEN_MENSAJE_CODIGO",
			new String[] {"CH_CVE_USUARIO", "CH_CVE_ENTIDAD", "CH_CVE_LOCALIDAD", "CH_CVE_IDIOMA", "CH_ID_PROCESO",
				"CH_CODIGO_MENSAJE", "CH_VARIABLE_VALOR", "CH_TIPO_EXCEPCION", "CH_MENSAJE"},
			new Class[] {String.class, String.class, String.class, String.class, String.class, String.class,
				String.class, String.class, String.class},
			new ParameterMode[] {ParameterMode.IN, ParameterMode.IN, ParameterMode.IN, ParameterMode.IN,
				ParameterMode.IN, ParameterMode.IN, ParameterMode.IN, ParameterMode.OUT, ParameterMode.OUT},
			new Object[] {cveUsuario, cveEntidad, cveLocalidad, cveIdioma, idProceso, codMensaje, varValor},
			RetMsg.class);
	}

}
