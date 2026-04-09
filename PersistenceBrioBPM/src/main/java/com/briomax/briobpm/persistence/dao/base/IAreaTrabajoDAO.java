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
import com.briomax.briobpm.persistence.entity.namedquery.ColumnasAreaTrabajo;
import com.briomax.briobpm.persistence.entity.namedquery.InformacionAreaTrabajo;
import com.briomax.briobpm.persistence.entity.namedquery.LeeBitacoraNodo;
import com.briomax.briobpm.persistence.entity.namedquery.LeeMenuEstatico;
import com.briomax.briobpm.persistence.entity.namedquery.MenuAreaTrabajo;
import com.briomax.briobpm.persistence.jdto.InicioProceso;

/**
 * El objetivo de la Interface IAreaTrabajoDAO.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Mar 9, 2020 3:21:55 PM Modificaciones:
 * @since JDK 1.8
 */
public interface IAreaTrabajoDAO {

	/**
	 * Lee menu estatico.
	 * @param cveUsuario el cve usuario.
	 * @param cveEntidad el cve entidad.
	 * @param cveLocalidad el cve localidad.
	 * @param cveIdioma el cve idioma.
	 * @return el DAO ret.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	DAORet<List<LeeMenuEstatico>, RetMsg> leeMenuEstatico(String cveUsuario, String cveEntidad, String cveLocalidad,
		String cveIdioma) throws BrioBPMException;

	/**
	 * Lee menu area trabajo.
	 * @param cveUsuario el cve usuario.
	 * @param cveEntidad el cve entidad.
	 * @param cveLocalidad el cve localidad.
	 * @param cveIdioma el cve idioma.
	 * @return el DAO ret.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	DAORet<List<MenuAreaTrabajo>, RetMsg> leeMenuAreaTrabajo(String cveUsuario, String cveEntidad, String cveLocalidad,
		String cveIdioma) throws BrioBPMException;

	/**
	 * Lee columnas area trabajo.
	 * @param cveUsuario el cve usuario.
	 * @param cveEntidad el cve entidad.
	 * @param cveLocalidad el cve localidad.
	 * @param cveIdioma el cve idioma.
	 * @param rol el rol.
	 * @param cveProceso el cve proceso.
	 * @param version el version.
	 * @param cveNodo el cve nodo.
	 * @param idNodo el id nodo.
	 * @param cveAreaTrabajo el cve area trabajo.
	 * @return el DAO ret.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	/*DAORet<List<ColumnasAreaTrabajo>, RetMsg> leeColumnasAreaTrabajo(String cveUsuario, String cveEntidad,
		String cveLocalidad, String cveIdioma, String rol, String cveProceso, BigDecimal version, String cveNodo,
		Integer idNodo, String cveAreaTrabajo) throws BrioBPMException;*/

	/**
	 * Lee informacion area trabajo.
	 * @param cveUsuario el cve usuario.
	 * @param cveEntidad el cve entidad.
	 * @param cveLocalidad el cve localidad.
	 * @param cveIdioma el cve idioma.
	 * @param rol el rol.
	 * @param cveProceso el cve proceso.
	 * @param version el version.
	 * @param cveNodo el cve nodo.
	 * @param idNodo el id nodo.
	 * @param cveAreaTrabajo el cve area trabajo.
	 * @return el DAO ret.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	/*DAORet<List<InformacionAreaTrabajo>, RetMsg> leeInformacionAreaTrabajo(String cveUsuario, String cveEntidad,
		String cveLocalidad, String cveIdioma, String rol, String cveProceso, BigDecimal version, String cveNodo,
		Integer idNodo, String cveAreaTrabajo) throws BrioBPMException;*/

	/**
	 * Crea instancia proceso.
	 * @param cveUsuario el cve usuario.
	 * @param cveEntidad el cve entidad.
	 * @param cveLocalidad el cve localidad.
	 * @param cveIdioma el cve idioma.
	 * @param rol el rol.
	 * @param cveProceso el cve proceso.
	 * @param version el version.
	 * @param concepto el concepto.
	 * @param origen el ori cre instancia.
	 * @return el inicio proceso.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	/*public InicioProceso creaInstanciaProceso(String cveUsuario, String cveEntidad, String cveLocalidad,
		String cveIdioma, String rol, String cveProceso, BigDecimal version, String concepto, String origen)
		throws BrioBPMException;*/

	/**
	 * Lee bitacora nodo.
	 * @param cveUsuario el cve usuario.
	 * @param cveEntidad el cve entidad.
	 * @param cveLocalidad el cve localidad.
	 * @param cveIdioma el cve idioma.
	 * @param cveProceso el cve proceso.
	 * @param version el version.
	 * @param cveInstancia el cve instancia.
	 * @param cveNodo el cve nodo.
	 * @param idNodo el id nodo.
	 * @param secNodo el sec nodo.
	 * @return el DAO ret.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	/*DAORet<List<LeeBitacoraNodo>, RetMsg> leeBitacoraNodo(String cveUsuario, String cveEntidad,
		String cveLocalidad, String cveIdioma, String cveProceso, BigDecimal version, String cveInstancia, String cveNodo,
		Integer idNodo, Integer secNodo) throws BrioBPMException;*/

	/**
	 * Lee menu dashboard.
	 * @param cveUsuario el cve usuario.
	 * @param cveEntidad el cve entidad.
	 * @param cveLocalidad el cve localidad.
	 * @param cveIdioma el cve idioma.
	 * @return el DAO ret.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	DAORet<List<LeeMenuEstatico>, RetMsg> leeMenuDashboard(String cveUsuario, String cveEntidad, String cveLocalidad,
		String cveIdioma) throws BrioBPMException;

	
	/**
	 * Ajecuta el query para obtener el usuario asignado
	 * @param query
	 * @return
	 * @throws BrioBPMException
	 */
	List<String> obtenerUsuarioAsignado(String query) throws BrioBPMException;

	/**
	 * Ajecuta el query para obtener un dato simple
	 * @param query
	 * @return
	 * @throws BrioBPMException
	 */
	String ejecutaQuerySimple(String query) throws BrioBPMException;
	
	/**
	 * Ejecuta un query y regresa un string
	 * @param query
	 * @return
	 * @throws BrioBPMException
	 */
	String ejecutaQuery(String query) throws BrioBPMException;

}
