/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */
package com.briomax.briobpm.business.service.test;

import org.junit.Test;

import static org.junit.Assert.fail;

import java.text.ParseException;

import com.briomax.briobpm.business.service.core.IEjecucionActividadService;
import com.briomax.briobpm.business.service.test.factory.SaveSectionTOFactory;
import com.briomax.briobpm.business.test.base.AbstractCoreTest;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.transferobjects.SaveSectionTO;
import com.briomax.briobpm.transferobjects.in.ActividadTO;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Class EjecucionActividadServiceTest.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Mar 5, 2020 2:16:28 PM Modificaciones:
 * @since JDK 1.8
 */
@Slf4j
public class EjecucionActividadServiceTest extends AbstractCoreTest {

	/** El atributo o variable ejecucion actividad service. */
	@Autowired
	private IEjecucionActividadService ejecucionActividadService;

	/**
	 * Guardar actividad test.
	 * @throws BrioBPMException la brio BPM exception.
	 * @throws ParseException 
	 */
	@Test
	public void guardarActividadTest() throws BrioBPMException, ParseException {
		DatosAutenticacionTO session = getDatosAutenticacionTO();
		printerJson(session);
		SaveSectionTO data = SaveSectionTOFactory.builderSaveSectionTO();
		data = SaveSectionTOFactory.builderSaveSectionDummy();
		printerJson(data);
		
		RetMsg response = ejecucionActividadService.guardarActividad(session, data);
		log.info(" GUARDAR ACTIVIDAD: {}", response);
		printerJson(response);
		if (response.getStatus().equalsIgnoreCase("ERROR")) {
			fail("Fail Guradar Actividad");
		}
	}

	/**
	 * Guardar & Terminar actividad test.
	 * @throws BrioBPMException la brio BPM exception.
	 * @throws ParseException 
	 */
	@Test
	public void guardarTerminarActividadTest() throws BrioBPMException, ParseException {
		DatosAutenticacionTO session = getDatosAutenticacionTO();
		session.setCveUsuario("Admin.Opera");
		printerJson(session);
		SaveSectionTO data = SaveSectionTOFactory.builderSaveSectionTO();
		printerJson(data);
		RetMsg response = ejecucionActividadService.guardarTerminarActividad(session, data);
		log.info(" GUARDAR & TERMINAR ACTIVIDAD: {}", response);
		printerJson(response);
		if (response.getStatus().equalsIgnoreCase("ERROR")) {
			fail("Fail Guardar & Terminar Actividad");
		}
	}

	/**
	 * Terminar actividad test.
	 * @throws BrioBPMException la brio BPM exception.
	 * @throws ParseException 
	 */
	@Test
	public void TerminarActividadTest() throws BrioBPMException, ParseException {
		DatosAutenticacionTO session = getDatosAutenticacionTO();
		printerJson(session);
		ActividadTO activity = geActividad();
		activity.setIdNodo(1);
		activity.setSecNodo(1);
		printerJson(activity);
		RetMsg response = ejecucionActividadService.terminarActividad(session, activity);
		log.info(" TERMINAR ACTIVIDAD: {}", response);
		printerJson(response);
		if (response.getStatus().equalsIgnoreCase("ERROR")) {
			//fail("Fail Terminar Actividad");
		}
	}

	/**
	 * Obtener actividad test.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void obtenerActividadTest() throws BrioBPMException {
		DatosAutenticacionTO session = getDatosAutenticacionTO();
		printerJson(session);
		ActividadTO activity = geActividad();
		activity.setIdNodo(1);
		activity.setSecNodo(2);
		printerJson(activity);
		RetMsg response = ejecucionActividadService.obtenerActividad(session, activity);
		log.info(" OBTENER ACTIVIDAD: {}", response);
		printerJson(response);
		if (response.getStatus().equalsIgnoreCase("ERROR")) {
			fail("Fail Obtener Actividad");
		}
	}

	/**
	 * Ceder actividad test.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void cederActividadTest() throws BrioBPMException {
		DatosAutenticacionTO session = getDatosAutenticacionTO();
		printerJson(session);
		ActividadTO activity = geActividad();
		activity.setIdNodo(1);
		activity.setSecNodo(2);
		printerJson(activity);
		RetMsg response = ejecucionActividadService.liberarActividad(session, activity);
		log.info(" CEDER ACTIVIDAD: {}", response);
		printerJson(response);
		if (response.getStatus().equalsIgnoreCase("ERROR")) {
			fail("Fail Ceder Actividad");
		}
	}

	/**
	 * Cancelar actividad test.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void cancelarActividadTest() throws BrioBPMException {
		DatosAutenticacionTO session = getDatosAutenticacionTO();
		printerJson(session);
		ActividadTO activity = geActividad();
		activity.setIdNodo(1);
		activity.setSecNodo(2);
		printerJson(activity);
		RetMsg response = ejecucionActividadService.liberarActividad(session, activity);
		log.info(" CANCELAR ACTIVIDAD: {}", response);
		printerJson(response);
		if (response.getStatus().equalsIgnoreCase("ERROR")) {
			fail("Fail Cancelar Actividad");
		}
	}

}
