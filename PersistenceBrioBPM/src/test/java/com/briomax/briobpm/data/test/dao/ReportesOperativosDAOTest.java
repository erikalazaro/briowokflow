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
import com.briomax.briobpm.persistence.dao.base.IReportesOperativosDAO;
import com.briomax.briobpm.persistence.entity.namedquery.LeeInfReporteActividades;
import com.briomax.briobpm.persistence.entity.namedquery.LeeInfReporteProcesos;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Class ReportesDAOTest.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Oct 6, 2020 5:35:49 PM Modificaciones:
 * @since JDK 1.8
 */
@Slf4j
public class ReportesOperativosDAOTest extends AbstractDataTest {

	/** El atributo o variable reportes DAO. */
	@Autowired
	private IReportesOperativosDAO reportesDAO;

	/**
	 * Obtener reporte proceso test.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void obtenerReporteProcesoTest() throws BrioBPMException {
		log.info("{} {} {} {}", getUser(), getEntidad(), getLocalidad(), getIdioma());
		final DAORet<List<LeeInfReporteProcesos>, RetMsg> ret =
			reportesDAO.obtenerReporteProcesos(getUser(), getEntidad(), getLocalidad(), getIdioma());
		imprimir(ret);
		assertEquals("OK", ret.getMeta().getStatus().toUpperCase());
	}

	/**
	 * Obtener reporte actividad test.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void obtenerReporteActividadTest() throws BrioBPMException {
		log.info("{} {} {} {}", getUser(), getEntidad(), getLocalidad(), getIdioma());
		final DAORet<List<LeeInfReporteActividades>, RetMsg> ret =
			reportesDAO.obtenerReporteActividades(getUser(), getEntidad(), getLocalidad(), getIdioma());
		imprimir(ret);
		assertEquals("OK", ret.getMeta().getStatus().toUpperCase());
	}

}
