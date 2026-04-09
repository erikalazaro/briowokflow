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
import com.briomax.briobpm.persistence.dao.base.IDashboardOperativoDAO;
import com.briomax.briobpm.persistence.entity.namedquery.LeeInfDashboardBurbujas;
import com.briomax.briobpm.persistence.entity.namedquery.LeeInfDashboardHistorico;
import com.briomax.briobpm.persistence.entity.namedquery.LeeInfDashboardKpi;
import com.briomax.briobpm.persistence.entity.namedquery.LeeInfDashboardPie;
import com.briomax.briobpm.persistence.entity.namedquery.LeeInfDashboardTabla;
import com.briomax.briobpm.persistence.entity.namedquery.LeeSecSubsecDashboard;

import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Class DashboardOperativoDAO.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Jun 7, 2021 12:32:21 PM Modificaciones:
 * @since JDK 1.8
 */
@Repository("dashboardOperativoDAO")
@Slf4j
public class DashboardOperativoDAO extends AbstractBaseDAO implements IDashboardOperativoDAO {

	/** 
	 * {@inheritDoc}
	 * @see com.briomax.briobpm.persistence.dao.base.IDashboardOperativoDAO#leeSecSubsecDashboard(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public DAORet<List<LeeSecSubsecDashboard>, RetMsg> leeSecSubsecDashboard(String cveUsuario, String cveEntidad,
		String cveLocalidad, String cveIdioma, String tipoDashboard, Integer numSeccion, Integer numSubseccion)
		throws BrioBPMException {
		log.debug("\t\t <DATABASE> EXEC SP_LEE_SEC_SUBSEC_DASHBOARD '{}', '{}', '{}', '{}', '{}', {}, {}, " +
				"@CH_TIPO_EXCEPCION OUT, @CH_MENSAJE OUT ", cveUsuario, cveEntidad, cveLocalidad, cveIdioma, tipoDashboard,
				numSeccion, numSubseccion);
		entityManager.clear();
		return executeNamedStored("leeSecSubseccionDashboard",
			new String[] {"CH_CVE_USUARIO", "CH_CVE_ENTIDAD", "CH_CVE_LOCALIDAD", "CH_CVE_IDIOMA", "CH_TIPO_DASHBOARD",
				"I_NUMERO_SECCION", "I_NUMERO_SUBSECCION"},
			new Object[] {cveUsuario, cveEntidad, cveLocalidad, cveIdioma, tipoDashboard, numSeccion, numSubseccion},
			RetMsg.class);
	}

	/** 
	 * {@inheritDoc}
	 * @see com.briomax.briobpm.persistence.dao.base.IDashboardOperativoDAO#leeInfDashboardPie(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.math.BigDecimal, java.lang.String, java.lang.Integer, java.lang.String, java.lang.String, java.lang.String, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public DAORet<List<LeeInfDashboardPie>, RetMsg> leeInfDashboardPie(String cveUsuario, String cveEntidad,
		String cveLocalidad, String cveIdioma, String cveProceso, BigDecimal version, String cveNodo, Integer idNodo,
		String fechaInicial, String fechaFinal, String tipoDashboard, Integer numSeccion, Integer numSubseccion)
		throws BrioBPMException {
		log.debug("\t\t <DATABASE> EXEC SP_LEE_INF_DASHBOARD_PIE '{}', '{}', '{}', '{}', '{}', {}, '{}', '{}', '{}', " +
			" '{}', '{}', '{}', @CH_TIPO_EXCEPCION, @CH_MENSAJE ", cveUsuario, cveEntidad, cveLocalidad, cveIdioma, cveProceso,
			version, cveNodo, idNodo, fechaInicial, fechaFinal, tipoDashboard, numSeccion, numSubseccion);
		entityManager.clear();
		return executeNamedStored("leeInfDashboardPie",
			new String[] {"CH_CVE_USUARIO", "CH_CVE_ENTIDAD", "CH_CVE_LOCALIDAD", "CH_CVE_IDIOMA", "CH_CVE_PROCESO",
				"C_VERSION", "CH_CVE_NODO", "I_ID_NODO", "CH_FECHA_INICIAL", "CH_FECHA_FINAL", "CH_TIPO_DASHBOARD",
				"I_NUMERO_SECCION", "I_NUMERO_SUBSECCION"},
			new Object[] {cveUsuario, cveEntidad, cveLocalidad, cveIdioma, cveProceso, version, cveNodo, idNodo,
				fechaInicial, fechaFinal, tipoDashboard, numSeccion, numSubseccion},
			RetMsg.class);
	}

	/** 
	 * {@inheritDoc}
	 * @see com.briomax.briobpm.persistence.dao.base.IDashboardOperativoDAO#leeInfDashboardKpi(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.math.BigDecimal, java.lang.String, java.lang.Integer, java.lang.String, java.lang.String, java.lang.String, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public DAORet<List<LeeInfDashboardKpi>, RetMsg> leeInfDashboardKpi(String cveUsuario, String cveEntidad,
		String cveLocalidad, String cveIdioma, String cveProceso, BigDecimal version, String cveNodo, Integer idNodo,
		String fechaInicial, String fechaFinal, String tipoDashboard, Integer numSeccion, Integer numSubseccion)
		throws BrioBPMException {
		log.debug("\t\t <DATABASE> EXEC SP_LEE_INF_DASHBOARD_KPI '{}', '{}', '{}', '{}', '{}', {}, '{}', '{}', '{}', " +
				" '{}', '{}', '{}', @CH_TIPO_EXCEPCION, @CH_MENSAJE ", cveUsuario, cveEntidad, cveLocalidad, cveIdioma, cveProceso,
				version, cveNodo, idNodo, fechaInicial, fechaFinal, tipoDashboard, numSeccion, numSubseccion);
		entityManager.clear();
		return executeNamedStored("leeInfDashboardKpi",
				new String[] {"CH_CVE_USUARIO", "CH_CVE_ENTIDAD", "CH_CVE_LOCALIDAD", "CH_CVE_IDIOMA", "CH_CVE_PROCESO",
					"C_VERSION", "CH_CVE_NODO", "I_ID_NODO", "CH_FECHA_INICIAL", "CH_FECHA_FINAL", "CH_TIPO_DASHBOARD",
					"I_NUMERO_SECCION", "I_NUMERO_SUBSECCION"},
				new Object[] {cveUsuario, cveEntidad, cveLocalidad, cveIdioma, cveProceso, version, cveNodo, idNodo,
					fechaInicial, fechaFinal, tipoDashboard, numSeccion, numSubseccion},
				RetMsg.class);
	}

	/** 
	 * {@inheritDoc}
	 * @see com.briomax.briobpm.persistence.dao.base.IDashboardOperativoDAO#leeInfDashboardTabla(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.math.BigDecimal, java.lang.String, java.lang.Integer, java.lang.String, java.lang.String, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.Integer)
	 */
	@Override
	public DAORet<List<LeeInfDashboardTabla>, RetMsg> leeInfDashboardTabla(String cveUsuario, String cveEntidad,
		String cveLocalidad, String cveIdioma, String cveProceso, BigDecimal version, String cveNodo, Integer idNodo,
		String fechaInicial, String fechaFinal, String tipoDashboard, Integer numSeccion, Integer numSubseccion,
		String top, Integer numRegistros) throws BrioBPMException {
		log.debug("\t\t <DATABASE> EXEC SP_LEE_INF_DASHBOARD_TABLA '{}', '{}', '{}', '{}', '{}', {}, '{}', '{}', '{}', " +
				" '{}', '{}', '{}', '{}', '{}', @CH_TIPO_EXCEPCION, @CH_MENSAJE ", cveUsuario, cveEntidad, cveLocalidad, cveIdioma, cveProceso,
				version, cveNodo, idNodo, fechaInicial, fechaFinal, tipoDashboard, numSeccion, numSubseccion, top, numRegistros);
		entityManager.clear();
		return executeNamedStored("leeInfDashboardTabla",
				new String[] {"CH_CVE_USUARIO", "CH_CVE_ENTIDAD", "CH_CVE_LOCALIDAD", "CH_CVE_IDIOMA", "CH_CVE_PROCESO",
					"C_VERSION", "CH_CVE_NODO", "I_ID_NODO", "CH_FECHA_INICIAL", "CH_FECHA_FINAL", "CH_TIPO_DASHBOARD",
					"I_NUMERO_SECCION", "I_NUMERO_SUBSECCION", "CH_TOP_SOLICITADO", "I_N_TOP"},
				new Object[] {cveUsuario, cveEntidad, cveLocalidad, cveIdioma, cveProceso, version, cveNodo, idNodo,
					fechaInicial, fechaFinal, tipoDashboard, numSeccion, numSubseccion, top, numRegistros},
				RetMsg.class);
	}

	/** 
	 * {@inheritDoc}
	 * @see com.briomax.briobpm.persistence.dao.base.IDashboardOperativoDAO#leeInfDashboardHistorico(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.math.BigDecimal, java.lang.String, java.lang.Integer, java.lang.String, java.lang.String, java.lang.String, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public DAORet<List<LeeInfDashboardHistorico>, RetMsg> leeInfDashboardHistorico(String cveUsuario, String cveEntidad,
		String cveLocalidad, String cveIdioma, String cveProceso, BigDecimal version, String cveNodo, Integer idNodo,
		String fechaInicial, String fechaFinal, String tipoDashboard, Integer numSeccion, Integer numSubseccion)
		throws BrioBPMException {
		log.debug("\t\t <DATABASE> EXEC SP_LEE_INF_DASHBOARD_HISTORICO '{}', '{}', '{}', '{}', '{}', {}, '{}', '{}', '{}', " +
				" '{}', '{}', '{}', @CH_TIPO_EXCEPCION OUT, @CH_MENSAJE OUT ",
				cveUsuario, cveEntidad, cveLocalidad, cveIdioma, cveProceso, version, cveNodo, idNodo, fechaInicial,
				fechaFinal, tipoDashboard, numSeccion, numSubseccion);
		entityManager.clear();
		return executeNamedStored("leeInfDashboardHistorico",
				new String[] {"CH_CVE_USUARIO", "CH_CVE_ENTIDAD", "CH_CVE_LOCALIDAD", "CH_CVE_IDIOMA", "CH_CVE_PROCESO",
					"C_VERSION", "CH_CVE_NODO", "I_ID_NODO", "CH_FECHA_INICIAL", "CH_FECHA_FINAL", "CH_TIPO_DASHBOARD",
					"I_NUMERO_SECCION", "I_NUMERO_SUBSECCION"},
				new Object[] {cveUsuario, cveEntidad, cveLocalidad, cveIdioma, cveProceso, version, cveNodo, idNodo,
					fechaInicial, fechaFinal, tipoDashboard, numSeccion, numSubseccion},
				RetMsg.class);
	}

	/** 
	 * {@inheritDoc}
	 * @see com.briomax.briobpm.persistence.dao.base.IDashboardOperativoDAO#leeInfDashboardBurbujas(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.math.BigDecimal, java.lang.String, java.lang.Integer, java.lang.String, java.lang.String, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.String)
	 */
	@Override
	public DAORet<List<LeeInfDashboardBurbujas>, RetMsg> leeInfDashboardBurbujas(String cveUsuario, String cveEntidad,
		String cveLocalidad, String cveIdioma, String cveProceso, BigDecimal version, String cveNodo, Integer idNodo,
		String fechaInicial, String fechaFinal, String categoria, String tipoDashboard, Integer numSeccion,
		Integer numSubseccion) throws BrioBPMException {
		log.debug("\t\t <DATABASE> EXEC SP_LEE_INF_DASHBOARD_BURBUJAS '{}', '{}', '{}', '{}', '{}', {}, '{}', '{}', '{}', " +
				" '{}', '{}', '{}', '{}', @CH_TIPO_EXCEPCION OUT, @CH_MENSAJE OUT ",
				cveUsuario, cveEntidad, cveLocalidad, cveIdioma, cveProceso, version, cveNodo, idNodo, fechaInicial,
				fechaFinal, tipoDashboard, numSeccion, numSubseccion, categoria);
		entityManager.clear();
		return executeNamedStored("leeInfDashboardBurbujas",
				new String[] {"CH_CVE_USUARIO", "CH_CVE_ENTIDAD", "CH_CVE_LOCALIDAD", "CH_CVE_IDIOMA", "CH_CVE_PROCESO",
					"C_VERSION", "CH_CVE_NODO", "I_ID_NODO", "CH_FECHA_INICIAL", "CH_FECHA_FINAL", "CH_TIPO_DASHBOARD",
					"I_NUMERO_SECCION", "I_NUMERO_SUBSECCION", "CH_FILTRO_CATEGORIA"},
				new Object[] {cveUsuario, cveEntidad, cveLocalidad, cveIdioma, cveProceso, version, cveNodo, idNodo,
					fechaInicial, fechaFinal, tipoDashboard, numSeccion, numSubseccion, categoria},
				RetMsg.class);
	}

}
