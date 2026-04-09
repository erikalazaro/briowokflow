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
import com.briomax.briobpm.persistence.dao.base.IAreaTrabajoDAO;
import com.briomax.briobpm.persistence.entity.namedquery.ColumnasAreaTrabajo;
import com.briomax.briobpm.persistence.entity.namedquery.InformacionAreaTrabajo;
import com.briomax.briobpm.persistence.entity.namedquery.LeeBitacoraNodo;
import com.briomax.briobpm.persistence.entity.namedquery.LeeMenuEstatico;
import com.briomax.briobpm.persistence.entity.namedquery.MenuAreaTrabajo;
import com.briomax.briobpm.persistence.jdto.InicioProceso;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;


/**
 * El objetivo de la Class AreaTrabajoDAOTest.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion 27/01/2020 05:06:46 PM Modificaciones:
 * @since JDK 1.8
 */
@Slf4j
public class AreaTrabajoDAOTest extends AbstractDataTest {

	/** El atributo o variable menu DAO. */
	@Autowired
	private IAreaTrabajoDAO areaTrabajoDAO;

	/**
	 * Lee menu estatico test.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void leeMenuEstaticoTest() throws BrioBPMException {
		final DAORet<List<LeeMenuEstatico>, RetMsg> ret =
			areaTrabajoDAO.leeMenuEstatico(getUser(), getEntidad(), getLocalidad(), getIdioma());
		imprimir(ret);
		assertEquals("OK", ret.getMeta().getStatus().toUpperCase());
	}

	/**
	 * Lee menu area trabajo test.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void leeMenuAreaTrabajoTest() throws BrioBPMException {
		final DAORet<List<MenuAreaTrabajo>, RetMsg> ret =
			areaTrabajoDAO.leeMenuAreaTrabajo(getUser(), getEntidad(), getLocalidad(), getIdioma());
		imprimir(ret);
		assertEquals("OK", ret.getMeta().getStatus().toUpperCase());
	}

	/**
	 * Lee menu area trabajo rol cliente test.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void leeMenuAreaTrabajoRolClienteTest() throws BrioBPMException {
		final DAORet<List<MenuAreaTrabajo>, RetMsg> ret =
			areaTrabajoDAO.leeMenuAreaTrabajo("Gabriela.Arias", getEntidad(), getLocalidad(), LOCALE_SPANISH);
		imprimir(ret);
		assertEquals("OK", ret.getMeta().getStatus().toUpperCase());
	}

	/**
	 * Lee columnas area trabajo test.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void leeColumnasAreaTrabajoTest() throws BrioBPMException {
		/*final DAORet<List<ColumnasAreaTrabajo>, RetMsg> ret = areaTrabajoDAO.leeColumnasAreaTrabajo(getUser(), getEntidad(),
			getLocalidad(), getIdioma(), CVE_ROL, CVE_PROCESO, VERSION, CVE_NODO, ID_NODO, CVE_AREA_TRABAJO);
		imprimir(ret);
		assertEquals("OK", ret.getMeta().getStatus().toUpperCase());*/
	}

	/**
	 * Lee informacion area trabajo test.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void leeInformacionAreaTrabajoTest() throws BrioBPMException {
		/*final DAORet<List<InformacionAreaTrabajo>, RetMsg> ret =
			areaTrabajoDAO.leeInformacionAreaTrabajo(getUser(), getEntidad(), getLocalidad(), getIdioma(), CVE_ROL,
				CVE_PROCESO, VERSION, CVE_NODO, ID_NODO, CVE_AREA_TRABAJO);
		imprimir(ret);
		assertEquals("OK", ret.getMeta().getStatus().toUpperCase());*/
	}

	/**
	 * Crea instancia proceso test.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	/*@Test
	public void creaInstanciaProcesoTest() throws BrioBPMException {
		InicioProceso resp = areaTrabajoDAO.creaInstanciaProceso(getUser(), getEntidad(), getLocalidad(),
			LOCALE_SPANISH, CVE_ROL, CVE_PROCESO, VERSION, "NUEVA INCIDENCIA", "USUARIO");
		log.info("SP_CREA_INSTANCIA_PROCESO {}", resp);
	}*/

	/**
	 * Lee bitacora nodo test.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	/*@Test
	public void leeBitacoraNodoTest() throws BrioBPMException {
		final DAORet<List<LeeBitacoraNodo>, RetMsg> ret = areaTrabajoDAO.leeBitacoraNodo(getUser(), getEntidad(),
			getLocalidad(), getIdioma(), "G2 ACF", VERSION, "202012-000003", CVE_NODO, 1, 1);
		imprimir(ret);
		assertEquals("OK", ret.getMeta().getStatus().toUpperCase());
	}*/

	/**
	 * Lee menu dashboard test.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	/*@Test
	public void leeMenuDashboardTest() throws BrioBPMException {
		final DAORet<List<LeeMenuEstatico>, RetMsg> ret =
			areaTrabajoDAO.leeMenuDashboard("Miguel.Garcia.Saavedra", getEntidad(), getLocalidad(), getIdioma());
		imprimir(ret);
		assertEquals("OK", ret.getMeta().getStatus().toUpperCase());
	}*/

}
