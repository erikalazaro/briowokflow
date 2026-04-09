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
import com.briomax.briobpm.persistence.dao.base.IUsuarioDAO;
import com.briomax.briobpm.persistence.entity.namedquery.DatosUsuario;
import com.briomax.briobpm.persistence.entity.namedquery.UsuarioRolProceso;

import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

@Repository("usuarioDAO")
@Slf4j
public class UsuarioDAO extends AbstractBaseDAO implements IUsuarioDAO {

	/*
	@Override
	public RetMsg validaUsuario(String usuario, String password) throws BrioBPMException {
		return executeNoResulsetStored("SP_VALIDA_USUARIO",
			new String[] {"CH_CVE_USUARIO", "CH_PASSWORD", "CH_TIPO_EXCEPCION", "CH_MENSAJE"},
			new Class[] {String.class, String.class, String.class, String.class},
			new ParameterMode[] {ParameterMode.IN, ParameterMode.IN, ParameterMode.OUT, ParameterMode.OUT},
			new Object[] {usuario, password}, RetMsg.class);
	}
	 */

	/** 
	 * {@inheritDoc}
	 * @see com.briomax.briobpm.persistence.dao.base.IUsuarioDAO#validaUsuario(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public DAORet<List<DatosUsuario>, RetMsg> validaUsuario(String usuario, String cveEntidad, String cveLocalidad,
		String cveIdioma, String password) throws BrioBPMException {
		log.debug("\t\t <DATABASE> EXEC SP_VALIDA_USUARIO '{}', '{}', '{}', '{}', '{}', @CH_TIPO_EXCEPCION, @CH_MENSAJE",
			usuario, cveEntidad, cveLocalidad, cveIdioma, password);
		return executeNamedStored("validarUsuario",
			new String[] {"CH_CVE_USUARIO", "CH_CVE_ENTIDAD", "CH_CVE_LOCALIDAD", "CH_CVE_IDIOMA", "CH_PASSWORD"},
			new String[] {usuario, cveEntidad, cveLocalidad, cveIdioma, password}, RetMsg.class);
	}

	@Override
	public List<UsuarioRolProceso> getUsuariosRol(String cveEntidad, String cveProceso, BigDecimal version,
		String cveRol) throws BrioBPMException {
		String query =  "SELECT " +
				"       CASE " +
				"           WHEN UR.CVE_USUARIO IS NULL THEN 0 " +
				"           WHEN UR.CVE_USUARIO IS NOT NULL THEN 1 " +
				"        END AS SELECCIONADO, " +
				"       U.CVE_USUARIO, " +
				"       U.NOMBRE, " +
				"       LE.DESCRIPCION " +
				"FROM   {h-schema}USUARIO U " +
				"            LEFT OUTER JOIN" +
				"            {h-schema}USUARIO_ROL  UR " +
				"            ON UR.CVE_ENTIDAD = U.CVE_ENTIDAD  AND " +
				"               UR.CVE_USUARIO = U.CVE_USUARIO  AND " +
				"               UR.CVE_PROCESO = :cveProceso    AND " +
				"               UR.VERSION = :version            AND " +
				"               UR.CVE_ROL = :cveRol , " +
				"       {h-schema}LOCALIDAD_ENTIDAD  LE " +
				"WHERE  U.CVE_ENTIDAD = :cveEntidad              AND " +
				"       LE.CVE_ENTIDAD = U.CVE_ENTIDAD  AND " +
				"       LE.CVE_LOCALIDAD = U.CVE_LOCALIDAD " +
				"ORDER BY " +
				"        U.NOMBRE ";
		return executeAndTransform(query, new String[] {"cveEntidad", "cveProceso", "version", "cveRol"},
			new Object[] {cveEntidad, cveProceso, version, cveRol}, UsuarioRolProceso.class);
	}

	/** 
	 * {@inheritDoc}
	 * @see com.briomax.briobpm.persistence.dao.base.IUsuarioDAO#deleteUsuariosRol(java.lang.String, java.lang.String, java.math.BigDecimal, java.lang.String)
	 */
	@Override
	public Integer deleteUsuariosRol(String cveEntidad, String cveProceso, BigDecimal version, String cveRol)
		throws BrioBPMException {
		String query = "DELETE FROM {h-schema}USUARIO_ROL WHERE CVE_ENTIDAD = :cveEntidad AND " +
				" CVE_PROCESO = :cveProceso AND VERSION = :version AND CVE_ROL = :cveRol ";
		return executeUpdate(query, 
			new String[] {"cveEntidad", "cveProceso", "version", "cveRol"},
			new Object[] {cveEntidad, cveProceso, version, cveRol});
	}

}
