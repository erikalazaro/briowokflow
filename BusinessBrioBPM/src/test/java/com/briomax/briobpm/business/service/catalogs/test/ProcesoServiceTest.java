/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.business.service.catalogs.test;

import java.util.List;

import org.junit.Test;

import com.briomax.briobpm.business.service.catalogs.core.IProcesoService;
import com.briomax.briobpm.business.test.base.AbstractCoreTest;
import com.briomax.briobpm.transferobjects.catalogs.Proceso;
import com.briomax.briobpm.transferobjects.catalogs.ProcesosTO;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Class MttoStProcesoServiceTest.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Jun 26, 2020 12:44:25 PM Modificaciones:
 * @since JDK 1.8
 */
@Slf4j
public class ProcesoServiceTest extends AbstractCoreTest {

	/** El atributo o variable service. */
	@Autowired
	private IProcesoService service;

	/**
	 * Crear una nueva instancia del objeto mtto st proceso service test.
	 */
	public ProcesoServiceTest() {
	}

	/**
	 * Obtener el valor de all test.
	 * @return el all test.
	 */
	@Test
	public void getAllTest() {
		printerJson(getDatosAutenticacionTO());
		List<ProcesosTO> list = service.getAll(getDatosAutenticacionTO());
		for (ProcesosTO stProcesoTO : list) {
			log.info("{}", stProcesoTO);
		}
		printerJson(list);
	}

	/**
	 * Obtener el valor de procesos test.
	 * @return el procesos test.
	 */
	@Test
	public void getProcesosTest() {
		printerJson(getDatosAutenticacionTO());
		List<Proceso> list = service.getProcesos(getDatosAutenticacionTO());
		for (Proceso proceso : list) {
			log.info("{}", proceso);
		}
		printerJson(list);
	}

}
