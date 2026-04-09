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
import com.briomax.briobpm.persistence.entity.namedquery.LeeInfDashboardBurbujas;
import com.briomax.briobpm.persistence.entity.namedquery.LeeSecSubsecDashboard;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Class DashboardGerenteDAOTest.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Jun 25, 2021 12:31:25 PM Modificaciones:
 * @since JDK 1.8
 */
@Slf4j
public class DashboardGerenteDAOTest extends AbstractDataTest {

	/** La Constante DASHBOARD_GERENTE. */
	private static final String DASHBOARD_GERENTE = "DASHBOARD_GERENTE";

	/** La Constante USUARIO. */
	private static final String USUARIO = "Miguel.Garcia.Saavedra";
	
	/** El atributo o variable Dashboard DAO. */
	@Autowired
	private IDashboardOperativoDAO dashboardDAO;

	/**
	 * Lee sec subsec dashboard test.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void leeSecSubsecDashboardTest() throws BrioBPMException {
		log.info("{} {} {} {}", getUser(), getEntidad(), getLocalidad(), getIdioma());
		DAORet<List<LeeSecSubsecDashboard>, RetMsg> ret =
				dashboardDAO.leeSecSubsecDashboard(USUARIO, getEntidad(), getLocalidad(), getIdioma(), 
					DASHBOARD_GERENTE, 4, 1);
		imprimir(ret);
		assertEquals("OK", ret.getMeta().getStatus().toUpperCase());
	}

	/**
	 * Lee inf dashboard burbujas test.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void leeInfDashboardBurbujasTest() throws BrioBPMException {
		log.info("{} {} {} {}", getUser(), getEntidad(), getLocalidad(), getIdioma());
		DAORet<List<LeeInfDashboardBurbujas>, RetMsg> ret =
			dashboardDAO.leeInfDashboardBurbujas(USUARIO, getEntidad(), getLocalidad(), getIdioma(), null, null, null,
				null, "01/01/2019", "31/12/2021", "TODAS", DASHBOARD_GERENTE, 4, 1);
		imprimir(ret);
		assertEquals("OK", ret.getMeta().getStatus().toUpperCase());
	}
}
