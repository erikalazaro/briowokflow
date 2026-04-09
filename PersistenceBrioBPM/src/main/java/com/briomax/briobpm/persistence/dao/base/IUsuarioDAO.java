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
import com.briomax.briobpm.persistence.entity.namedquery.DatosUsuario;
import com.briomax.briobpm.persistence.entity.namedquery.UsuarioRolProceso;

public interface IUsuarioDAO {

	// RetMsg validaUsuario(String usuario, String password) throws BrioBPMException;

	/**
	 * Valida usuario.
	 * @param usuario el usuario.
	 * @param cveEntidad el cve entidad.
	 * @param cveLocalidad el cve localidad.
	 * @param cveIdioma el cve idioma.
	 * @param password el password.
	 * @return el DAO ret.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	public DAORet<List<DatosUsuario>, RetMsg> validaUsuario(String usuario, String cveEntidad, String cveLocalidad,
		String cveIdioma, String password) throws BrioBPMException;

	/**
	 * Obtener el valor de usuarios rol.
	 * @param cveEntidad el cve entidad.
	 * @param cveProceso el cve proceso.
	 * @param version el version.
	 * @param cveRol el cve rol.
	 * @return el usuarios rol.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	List<UsuarioRolProceso> getUsuariosRol(String cveEntidad, String cveProceso, BigDecimal version, String cveRol)
		throws BrioBPMException;

	/**
	 * Delete usuarios rol.
	 * @param cveEntidad el cve entidad.
	 * @param cveProceso el cve proceso.
	 * @param version el version.
	 * @param cveRol el cve rol.
	 * @return el integer.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	Integer deleteUsuariosRol(String cveEntidad, String cveProceso, BigDecimal version, String cveRol)
		throws BrioBPMException;

}
