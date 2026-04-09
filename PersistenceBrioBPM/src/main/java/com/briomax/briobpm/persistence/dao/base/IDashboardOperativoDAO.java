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
import com.briomax.briobpm.persistence.entity.namedquery.LeeInfDashboardBurbujas;
import com.briomax.briobpm.persistence.entity.namedquery.LeeInfDashboardHistorico;
import com.briomax.briobpm.persistence.entity.namedquery.LeeInfDashboardKpi;
import com.briomax.briobpm.persistence.entity.namedquery.LeeInfDashboardPie;
import com.briomax.briobpm.persistence.entity.namedquery.LeeInfDashboardTabla;
import com.briomax.briobpm.persistence.entity.namedquery.LeeSecSubsecDashboard;

/**
 * El objetivo de la Interface IDashboardOperativoDAO.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Jun 7, 2021 12:27:58 PM Modificaciones:
 * @since JDK 1.8
 */
public interface IDashboardOperativoDAO {
	
	/**
	 * Lee sec subsec dashboard.
	 * @param cveUsuario el cve usuario.
	 * @param cveEntidad el cve entidad.
	 * @param cveLocalidad el cve localidad.
	 * @param cveIdioma el cve idioma.
	 * @param tipoDashboard el tipo dashboard.
	 * @param numSeccion el num seccion.
	 * @param numSubseccion el num subseccion.
	 * @return el DAO ret.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	public DAORet<List<LeeSecSubsecDashboard>, RetMsg> leeSecSubsecDashboard(String cveUsuario, String cveEntidad,
		String cveLocalidad, String cveIdioma, String tipoDashboard, Integer numSeccion, Integer numSubseccion)
		throws BrioBPMException;

	/**
	 * Lee inf dashboard pie.
	 * @param cveUsuario el cve usuario.
	 * @param cveEntidad el cve entidad.
	 * @param cveLocalidad el cve localidad.
	 * @param cveIdioma el cve idioma.
	 * @param cveProceso el cve proceso.
	 * @param version el version.
	 * @param cveNodo el cve nodo.
	 * @param idNodo el id nodo.
	 * @param fechaInicial el fecha inicial.
	 * @param fechaFinal el fecha final.
	 * @param tipoDashboard el tipo dashboard.
	 * @param numSeccion el num seccion.
	 * @param numSubseccion el num subseccion.
	 * @return el DAO ret.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	public DAORet<List<LeeInfDashboardPie>, RetMsg> leeInfDashboardPie(String cveUsuario, String cveEntidad,
		String cveLocalidad, String cveIdioma, String cveProceso, BigDecimal version, String cveNodo, Integer idNodo,
		String fechaInicial, String fechaFinal, String tipoDashboard, Integer numSeccion, Integer numSubseccion)
		throws BrioBPMException;
	
	/**
	 * Lee inf dashboard kpi.
	 * @param cveUsuario el cve usuario.
	 * @param cveEntidad el cve entidad.
	 * @param cveLocalidad el cve localidad.
	 * @param cveIdioma el cve idioma.
	 * @param cveProceso el cve proceso.
	 * @param version el version.
	 * @param cveNodo el cve nodo.
	 * @param idNodo el id nodo.
	 * @param fechaInicial el fecha inicial.
	 * @param fechaFinal el fecha final.
	 * @param tipoDashboard el tipo dashboard.
	 * @param numSeccion el num seccion.
	 * @param numSubseccion el num subseccion.
	 * @return el DAO ret.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	public DAORet<List<LeeInfDashboardKpi>, RetMsg> leeInfDashboardKpi(String cveUsuario, String cveEntidad,
		String cveLocalidad, String cveIdioma, String cveProceso, BigDecimal version, String cveNodo, Integer idNodo,
		String fechaInicial, String fechaFinal, String tipoDashboard, Integer numSeccion, Integer numSubseccion)
		throws BrioBPMException;

	/**
	 * Lee inf dashboard tabla.
	 * @param cveUsuario el cve usuario.
	 * @param cveEntidad el cve entidad.
	 * @param cveLocalidad el cve localidad.
	 * @param cveIdioma el cve idioma.
	 * @param cveProceso el cve proceso.
	 * @param version el version.
	 * @param cveNodo el cve nodo.
	 * @param idNodo el id nodo.
	 * @param fechaInicial el fecha inicial.
	 * @param fechaFinal el fecha final.
	 * @param tipoDashboard el tipo dashboard.
	 * @param numSeccion el num seccion.
	 * @param numSubseccion el num subseccion.
	 * @param top el top.
	 * @param numRegistros el num registros.
	 * @return el DAO ret.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	public DAORet<List<LeeInfDashboardTabla>, RetMsg> leeInfDashboardTabla(String cveUsuario, String cveEntidad,
		String cveLocalidad, String cveIdioma, String cveProceso, BigDecimal version, String cveNodo, Integer idNodo,
		String fechaInicial, String fechaFinal, String tipoDashboard, Integer numSeccion, Integer numSubseccion,
		String top, Integer numRegistros) throws BrioBPMException;

	/**
	 * Lee inf dashboard historico.
	 * @param cveUsuario el cve usuario.
	 * @param cveEntidad el cve entidad.
	 * @param cveLocalidad el cve localidad.
	 * @param cveIdioma el cve idioma.
	 * @param cveProceso el cve proceso.
	 * @param version el version.
	 * @param cveNodo el cve nodo.
	 * @param idNodo el id nodo.
	 * @param fechaInicial el fecha inicial.
	 * @param fechaFinal el fecha final.
	 * @param tipoDashboard el tipo dashboard.
	 * @param numSeccion el num seccion.
	 * @param numSubseccion el num subseccion.
	 * @return el DAO ret.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	public DAORet<List<LeeInfDashboardHistorico>, RetMsg> leeInfDashboardHistorico(String cveUsuario, String cveEntidad,
		String cveLocalidad, String cveIdioma, String cveProceso, BigDecimal version, String cveNodo, Integer idNodo,
		String fechaInicial, String fechaFinal, String tipoDashboard, Integer numSeccion, Integer numSubseccion)
		throws BrioBPMException;

	/**
	 * Lee inf dashboard burbujas.
	 * @param cveUsuario el cve usuario.
	 * @param cveEntidad el cve entidad.
	 * @param cveLocalidad el cve localidad.
	 * @param cveIdioma el cve idioma.
	 * @param cveProceso el cve proceso.
	 * @param version el version.
	 * @param cveNodo el cve nodo.
	 * @param idNodo el id nodo.
	 * @param fechaInicial el fecha inicial.
	 * @param fechaFinal el fecha final.
	 * @param categoria el categoria.
	 * @param tipoDashboard el tipo dashboard.
	 * @param numSeccion el num seccion.
	 * @param numSubseccion el num subseccion.
	 * @return el DAO ret.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	public DAORet<List<LeeInfDashboardBurbujas>, RetMsg> leeInfDashboardBurbujas(String cveUsuario, String cveEntidad,
		String cveLocalidad, String cveIdioma, String cveProceso, BigDecimal version, String cveNodo, Integer idNodo,
		String fechaInicial, String fechaFinal, String categoria, String tipoDashboard, Integer numSeccion,
		Integer numSubseccion) throws BrioBPMException;

}
