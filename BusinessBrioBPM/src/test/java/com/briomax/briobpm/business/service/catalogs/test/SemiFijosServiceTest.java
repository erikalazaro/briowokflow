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

import com.briomax.briobpm.business.service.catalogs.core.ISemiFijosService;
import com.briomax.briobpm.business.test.base.AbstractCoreTest;
import com.briomax.briobpm.transferobjects.ComboBoxTO;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Class SemiFijosServiceTest.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Jul 2, 2020 5:12:44 PM Modificaciones:
 * @since JDK 1.8
 */
@Slf4j
public class SemiFijosServiceTest extends AbstractCoreTest {

	/** El atributo o variable semi fijos service. */
	@Autowired
	private ISemiFijosService semiFijosService;

	/**
	 * Crear una nueva instancia del objeto messages service test.
	 
	public SemiFijosServiceTest() {
	}*/

	/**
	 * Obtener el valor de cmb situacion rol proceso test.
	 * @return el cmb situacion rol proceso test.
	 */
	@Test
	public void getCmbSituacionRolProcesoTest() {
		List<ComboBoxTO> list = semiFijosService.getCmbSituacionRolProceso(getDatosAutenticacionTO());
		for (ComboBoxTO item : list) {
			log.info("getCmbSituacionRolProcesoTest {}", item);
		}
		printerJson(list);
	}

}
