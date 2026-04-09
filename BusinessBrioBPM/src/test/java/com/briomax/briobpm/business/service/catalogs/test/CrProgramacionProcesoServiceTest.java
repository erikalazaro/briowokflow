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

import com.briomax.briobpm.business.service.catalogs.core.ICrProcesoPeriodicoService;
import com.briomax.briobpm.business.service.catalogs.core.ICrProgramacionProcesoService;
import com.briomax.briobpm.business.test.base.AbstractCoreTest;
import com.briomax.briobpm.transferobjects.ComboBoxTO;
import com.briomax.briobpm.transferobjects.PdfGridTO;
import com.briomax.briobpm.transferobjects.catalogs.ProcesosTO;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;
import com.briomax.briobpm.transferobjects.repse.ComboBoxProcesosTO;
import com.briomax.briobpm.transferobjects.repse.ConsultaPdfTO;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Class CrProcesoPeriodicoServiceTest.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Noc 14, 2024 12:44:25 PM Modificaciones:
 * @since JDK 1.8
 */
@Slf4j
public class CrProgramacionProcesoServiceTest extends AbstractCoreTest {

	/** El atributo o variable service. */
	@Autowired
	private ICrProgramacionProcesoService service;

	/**
	 * Crear una nueva instancia del objeto mtto st proceso service test.
	 */
	public CrProgramacionProcesoServiceTest() {
	}

	/**
	 * Obtener el valor de all test.
	 * @return el all test.
	 */
	@Test
	public void getAllTest() {
		// sesion
		DatosAutenticacionTO datosAutenticacion = getDatosAutenticacionTO();
		datosAutenticacion.setCveUsuario("U.MYPO");
		datosAutenticacion.setCveEntidad("BRIOMAX");
		datosAutenticacion.setCveLocalidad("BRIOMAX-CENTRAL");
		datosAutenticacion.setCveIdioma("ES-MX");

		List<ComboBoxProcesosTO> list = service.getContrato(datosAutenticacion, "CARGA");
		for (ComboBoxProcesosTO comboBoxTO : list) {
			log.info("{}", comboBoxTO);
		}
		printerJson(list);
	}
	
	@Test
	public void getAllDescargaTest() {
		// sesion
		DatosAutenticacionTO datosAutenticacion = getDatosAutenticacionTO();
		datosAutenticacion.setCveUsuario("MIN220616APA");
		datosAutenticacion.setCveEntidad("BRIOMAX");
		datosAutenticacion.setCveLocalidad("BRIOMAX-CENTRAL");
		datosAutenticacion.setCveIdioma("ES-MX");

		List<ComboBoxProcesosTO> list = service.getContrato(datosAutenticacion, "CONSULTA");
		for (ComboBoxProcesosTO comboBoxTO : list) {
			log.info("{}", comboBoxTO);
		}
		printerJson(list);
	}

	@Test
	public void getGridCargaAllTest() {
		// sesion
		DatosAutenticacionTO datosAutenticacion = getDatosAutenticacionTO();
		datosAutenticacion.setCveUsuario("Usuario.Contratista");
		datosAutenticacion.setCveEntidad("BRIOMAX");
		datosAutenticacion.setCveLocalidad("BRIOMAX-CENTRAL");
		datosAutenticacion.setCveIdioma("ES-MX");

		ConsultaPdfTO datos = ConsultaPdfTO.builder()
				.actividad("CARGA")
				.cveProceso("")
				.rfc("MIN220616APA")
				.contrato("AHA-EDIF-CAMILA-009-MYPO-2024")
				.build();
		PdfGridTO info = service.getTabla(datosAutenticacion, datos );

		printerJson(info);
	}
	
	@Test
	public void getGridConsultaAllTest() {
		// sesion
		DatosAutenticacionTO datosAutenticacion = getDatosAutenticacionTO();
		datosAutenticacion.setCveUsuario("Usuario.Contratista");
		datosAutenticacion.setCveEntidad("BRIOMAX");
		datosAutenticacion.setCveLocalidad("BRIOMAX-CENTRAL");
		datosAutenticacion.setCveIdioma("ES-MX");

		ConsultaPdfTO datos = ConsultaPdfTO.builder()
				.actividad("CONSULTA")
				.cveProceso("")
				.rfc("MIN220616APA")
				.contrato("AHA-EDIF-CAMILA-009-MYPO-2024")
				.build();
		PdfGridTO info = service.getTabla(datosAutenticacion, datos );

		printerJson(info);
	}
}

