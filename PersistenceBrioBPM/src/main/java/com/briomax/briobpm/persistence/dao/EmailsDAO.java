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

import javax.persistence.ParameterMode;

import com.briomax.briobpm.common.core.DAORet;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.persistence.dao.base.AbstractBaseDAO;
import com.briomax.briobpm.persistence.dao.base.IEmailsDAO;
import com.briomax.briobpm.persistence.entity.namedquery.LeeCorreosPorEnviar;

import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Class EmailsDAO.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Apr 13, 2020 10:25:07 AM Modificaciones:
 * @since JDK 1.8
 */
@Repository("emailsDAO")
@Slf4j
public class EmailsDAO extends AbstractBaseDAO implements IEmailsDAO {

	/**
	 * {@inheritDoc}
	 * @see com.briomax.briobpm.persistence.dao.base.IEmailsDAO#obtenerCorreosPorEnviar()
	 */
	@Override
	public DAORet<List<LeeCorreosPorEnviar>, RetMsg> obtenerCorreosPorEnviar() throws BrioBPMException {
		log.debug("\t\t <DATABASE> EXEC SP_LEE_CORREOS_POR_ENVIAR @CH_TIPO_EXCEPCION, @CH_MENSAJE");
		return executeNamedStored("obtenerCorreosPorEnviar", new String[] { }, new String[] { }, RetMsg.class);
	}

	/** 
	 * {@inheritDoc}
	 * @see com.briomax.briobpm.persistence.dao.base.IEmailsDAO#actualizaSituacionCorreo(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.math.BigDecimal, java.lang.Long, java.lang.String)
	 */
	@Override
	public RetMsg actualizaSituacionCorreo(String cveUsuario, String cveEntidad, String cveLocalidad, String cveIdioma,
		String cveProceso, BigDecimal version, Long numCorreo, String situacion) throws BrioBPMException {
		log.debug("\t\t <DATABASE> EXEC SP_ACTUALIZA_SITUACION_CORREO '{}', '{}', '{}', '{}', '{}', {}, {}, '{}'," +
			" @CH_TIPO_EXCEPCION, @CH_MENSAJE", cveUsuario, cveEntidad, cveLocalidad, cveIdioma, cveProceso, version,
			numCorreo, situacion);
		return executeNoResulsetStored("SP_ACTUALIZA_SITUACION_CORREO",
			new String[] {"CH_USUARIO", "CH_CVE_ENTIDAD", "CH_CVE_LOCALIDAD", "CH_CVE_IDIOMA", "CH_CVE_PROCESO",
				"C_VERSION", "I_NUMERO_CORREO", "CH_SITUACION", "CH_TIPO_EXCEPCION", "CH_MENSAJE"},
			new Class[] {String.class, String.class, String.class, String.class, String.class, BigDecimal.class,
				Long.class, String.class, String.class, String.class},
			new ParameterMode[] {ParameterMode.IN, ParameterMode.IN, ParameterMode.IN, ParameterMode.IN,
				ParameterMode.IN, ParameterMode.IN, ParameterMode.IN, ParameterMode.IN, ParameterMode.OUT,
				ParameterMode.OUT},
			new Object[] {cveUsuario, cveEntidad, cveLocalidad, cveIdioma, cveProceso, version, numCorreo, situacion},
			RetMsg.class);
	}

}
