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
import org.springframework.beans.factory.annotation.Autowired;

import com.briomax.briobpm.business.service.catalogs.core.ICrProgramacionProcesoService;
import com.briomax.briobpm.business.test.base.AbstractCoreTest;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;
import com.briomax.briobpm.transferobjects.repse.ComboBoxProcesosTO;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Class CrProcesoPeriodicoServiceTest.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Noc 14, 2024 12:44:25 PM Modificaciones:
 * @since JDK 1.8
 */
@Slf4j
public class CrProcesoPeriodicoServiceTest extends AbstractCoreTest {

	/** El atributo o variable service. */
	@Autowired
	private ICrProgramacionProcesoService service;

	/**
	 * Crear una nueva instancia del objeto mtto st proceso service test.
	 */
	public CrProcesoPeriodicoServiceTest() {
	}

	/**
	 * Obtener el valor de all test.
	 * @return el all test.
	 */
	@Test
	public void getAllTest() {
		// sesion
		DatosAutenticacionTO datosAutenticacion = getDatosAutenticacionTO();
		datosAutenticacion.setCveUsuario("Usuario.Contratista");
		datosAutenticacion.setCveEntidad("BRIOMAX");
		datosAutenticacion.setCveLocalidad("BRIOMAX-CENTRAL");
		datosAutenticacion.setCveIdioma("ES-MX");

		List<ComboBoxProcesosTO> list = service.getContrato(datosAutenticacion, "CARGA");
		for (ComboBoxProcesosTO comboBoxTO : list) {
			log.info("{}", comboBoxTO);
		}
		printerJson(list);
	}


}
