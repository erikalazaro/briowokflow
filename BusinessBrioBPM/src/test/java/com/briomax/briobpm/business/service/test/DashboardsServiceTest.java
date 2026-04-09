/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados. Este software contiene informacion
 * confidencial propiedad de Briomax Consulting por lo cual no puede ser reproducido, distribuido o alterado sin el
 * consentimiento previo de Briomax Consulting.
 */

package com.briomax.briobpm.business.service.test;

import org.junit.Test;

import static org.junit.Assert.fail;

import com.briomax.briobpm.business.service.core.IDashboardsService;
import com.briomax.briobpm.business.test.base.AbstractCoreTest;
import com.briomax.briobpm.common.core.DAORet;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.transferobjects.dashboards.Dashboard;
import com.briomax.briobpm.transferobjects.dashboards.OperativoGerenteIn;
import com.briomax.briobpm.transferobjects.dashboards.OperativoIn;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Class EjecucionActividadServiceTest.java es ...
 * @author Rigoberto Olvera C.
 * @version 1.0 Fecha de creacion 31/01/2020 11:08:58 AM Modificaciones:
 * @since JDK 1.8
 */
@Slf4j
public class DashboardsServiceTest extends AbstractCoreTest {

	/** El atributo o variable dashboards service. */
	@Autowired
	private IDashboardsService dashboardsService;

	/**
	 * Obtener formulario dinamico test.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void operativoTest() throws BrioBPMException {
		DatosAutenticacionTO userSession = getDatosAutenticacionTO();
		userSession.setCveUsuario("Miguel.Garcia.Saavedra");
		log.info("========== \t DATOS DE LA SESION ========== ");
		printerJson(userSession);
		OperativoIn filtros = OperativoIn.builder()
				.fecInicial("01/01/2019")
				.fecFinal("31/12/2021")
				.build();
		log.info("FILTROS:  ");
		printerJson(filtros);

		DAORet<Dashboard, RetMsg> dashboard = dashboardsService.operativo(userSession, filtros);
		printerJson(dashboard);

		if (dashboard.getMeta().getStatus().equalsIgnoreCase("ERROR")) {
			fail("Fail Reporte Operativo");
		}
	}

	/**
	 * Operativo gerente test.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void operativoGerenteTest() throws BrioBPMException {
		DatosAutenticacionTO userSession = getDatosAutenticacionTO();
		userSession.setCveUsuario("Miguel.Garcia.Saavedra");
		log.info("========== \t DATOS DE LA SESION ==========");
		printerJson(userSession);
		OperativoGerenteIn filtros = OperativoGerenteIn.builderOperativoGerenteIn()
				.entidad("BRIOMAX")
				.fecInicial("01/01/2019")
				.fecFinal("31/12/2021")
				.topSolicitado("EFICIENTE")
				.numRegistros(5)
				.situacion("TODAS")
				.build();
		log.info("FILTROS:");
		printerJson(filtros);

		DAORet<Dashboard, RetMsg> dashboard = dashboardsService.operativoGerente(userSession, filtros);
		printerJson(dashboard);

		if (dashboard.getMeta().getStatus().equalsIgnoreCase("ERROR")) {
			fail("Fail Reporte Operativo Gerente");
		}
	}

}
