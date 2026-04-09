/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */
package com.briomax.briobpm.data.test.dao;

import java.util.List;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import com.briomax.briobpm.common.core.DAORet;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.data.test.base.AbstractDataTest;
import com.briomax.briobpm.persistence.dao.base.IDashboardOperativoDAO;
import com.briomax.briobpm.persistence.entity.namedquery.LeeInfDashboardHistorico;
import com.briomax.briobpm.persistence.entity.namedquery.LeeInfDashboardKpi;
import com.briomax.briobpm.persistence.entity.namedquery.LeeInfDashboardPie;
import com.briomax.briobpm.persistence.entity.namedquery.LeeInfDashboardTabla;
import com.briomax.briobpm.persistence.entity.namedquery.LeeSecSubsecDashboard;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Class ReportesDAOTest.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Oct 6, 2020 5:35:49 PM Modificaciones:
 * @since JDK 1.8
 */
@Slf4j
public class DashboardOperativoDAOTest extends AbstractDataTest {

	/** La Constante DASHBOARD_OPERATIVO. */
	private static final String DASHBOARD_OPERATIVO = "DASHBOARD_OPERATIVO";

	/** La Constante USUARIO. */
	private static final String USUARIO = "Miguel.Garcia.Saavedra";
	
	/** El atributo o variable Dashboard DAO. */
	@Autowired
	private IDashboardOperativoDAO dashboardDAO;

	@Test
	public void leeSecSubsecDashboardTest() throws BrioBPMException {
		log.info("{} {} {} {}", getUser(), getEntidad(), getLocalidad(), getIdioma());
		DAORet<List<LeeSecSubsecDashboard>, RetMsg> ret =
				dashboardDAO.leeSecSubsecDashboard(USUARIO, getEntidad(), getLocalidad(), getIdioma(), 
					DASHBOARD_OPERATIVO, 1, 1);
		imprimir(ret);
		assertEquals("OK", ret.getMeta().getStatus().toUpperCase());
	}

	@Test
	public void leeInfDashboardPieTest() throws BrioBPMException {
		log.info("{} {} {} {}", getUser(), getEntidad(), getLocalidad(), getIdioma());
		DAORet<List<LeeInfDashboardPie>, RetMsg> ret =
			dashboardDAO.leeInfDashboardPie(USUARIO, getEntidad(), getLocalidad(), getIdioma(), null, null, null,
				null, "01/01/2019", "31/12/2021", DASHBOARD_OPERATIVO, 1, 1);
		imprimir(ret);
		assertEquals("OK", ret.getMeta().getStatus().toUpperCase());
	}
	
	@Test
	public void leeInfDashboardKpiTest() throws BrioBPMException {
		log.info("{} {} {} {}", getUser(), getEntidad(), getLocalidad(), getIdioma());
		DAORet<List<LeeInfDashboardKpi>, RetMsg> ret =
			dashboardDAO.leeInfDashboardKpi(USUARIO, getEntidad(), getLocalidad(), getIdioma(), null, null, null,
				null, "01/01/2019", "31/12/2021", DASHBOARD_OPERATIVO, 1, 2);
		imprimir(ret);
		assertEquals("OK", ret.getMeta().getStatus().toUpperCase());
	}

	@Test
	public void leeInfDashboardTablaTest() throws BrioBPMException {
		log.info("{} {} {} {}", getUser(), getEntidad(), getLocalidad(), getIdioma());
		DAORet<List<LeeInfDashboardTabla>, RetMsg> ret =
			dashboardDAO.leeInfDashboardTabla(USUARIO, getEntidad(), getLocalidad(), getIdioma(), null, null, null,
				null, "01/01/2019", "31/12/2021", DASHBOARD_OPERATIVO, 1, 3, null, null);
		imprimir(ret);
		assertEquals("OK", ret.getMeta().getStatus().toUpperCase());
	}

	@Test
	public void leeInfDashboardHistoricoTest() throws BrioBPMException {
		log.info("{} {} {} {}", getUser(), getEntidad(), getLocalidad(), getIdioma());
		DAORet<List<LeeInfDashboardHistorico>, RetMsg> ret =
			dashboardDAO.leeInfDashboardHistorico(USUARIO, getEntidad(), getLocalidad(), getIdioma(), null, null, null,
				null, "01/01/2019", "31/12/2021", DASHBOARD_OPERATIVO, 1, 3);
		imprimir(ret);
		assertEquals("OK", ret.getMeta().getStatus().toUpperCase());
	}
}
