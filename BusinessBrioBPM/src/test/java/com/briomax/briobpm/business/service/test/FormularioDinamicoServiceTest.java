/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados. Este software contiene informacion
 * confidencial propiedad de Briomax Consulting por lo cual no puede ser reproducido, distribuido o alterado sin el
 * consentimiento previo de Briomax Consulting.
 */

package com.briomax.briobpm.business.service.test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.briomax.briobpm.business.service.FormularioDinamicoService;
import com.briomax.briobpm.business.service.catalogs.core.IMessagesService;
import com.briomax.briobpm.business.service.core.IFormularioDinamicoService;
import com.briomax.briobpm.business.test.base.AbstractCoreTest;
import com.briomax.briobpm.common.core.DAORet;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.persistence.entity.namedquery.LeeMensajesReglas;
import com.briomax.briobpm.transferobjects.dynamicForm.FormularioDinamico;
import com.briomax.briobpm.transferobjects.dynamicForm.Hehavior;
import com.briomax.briobpm.transferobjects.dynamicForm.Section;
import com.briomax.briobpm.transferobjects.in.ActividadTO;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;
import com.briomax.briobpm.transferobjects.in.StSeccionNodoTO;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Class EjecucionActividadServiceTest.java es ...
 * @author Rigoberto Olvera C.
 * @version 1.0 Fecha de creacion 31/01/2020 11:08:58 AM Modificaciones:
 * @since JDK 1.8
 */
@Slf4j
public class FormularioDinamicoServiceTest extends AbstractCoreTest {

	/** El atributo o variable service. */
	@Autowired
	private IFormularioDinamicoService service;
	
	@Autowired
	private IMessagesService message;
	
	@Autowired
	private FormularioDinamicoService formularioDinamicoService;

	/**
	 * Obtener formulario dinamico test.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void obtenerFormularioDinamicoTest() throws BrioBPMException {
		DatosAutenticacionTO session = getDatosAutenticacionTO();
		session.setCveUsuario("Francisco.Rodriguez");
		printerJson(session);
		ActividadTO actividad = geActividad();
		actividad.setCveProceso("DUMMY");
		actividad.setCveNodo("ACTIVIDAD-USUARIO");
		actividad.setCveRol("ROL DUMMY 1");
		actividad.setIdNodo(1);
		actividad.setSecNodo(2);
		actividad.setCveInstancia("202005-000008");
		actividad.setCveInstancia("202006-000003");
		actividad.setCveAreaTrabajo("AREA_UNICA");
		printerJson(actividad);
		DAORet<FormularioDinamico, RetMsg> metaData = null;
		try {
			metaData = service.obtenerFormularioDinamico(session, actividad);
		} catch (BrioBPMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FormularioDinamico formularioDinamico = metaData.getContent();
		for (Section section : formularioDinamico.getSections()) {
			log.info("{}", section);
		}
		printerJson(metaData.getContent());
	}

	/**
	 * Obtener formulario dinamico tareas test.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void obtenerFormularioDinamicoTareasTest() throws BrioBPMException {
		DatosAutenticacionTO session = getDatosAutenticacionTO();
		printerJson(session);
		ActividadTO actividad = geActividad();
		actividad.setIdNodo(8);
		actividad.setSecNodo(2);
		printerJson(actividad);
		DAORet<FormularioDinamico, RetMsg> metaData = null;
		try {
			metaData = service.obtenerFormularioDinamico(session, actividad);
		} catch (BrioBPMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FormularioDinamico formularioDinamico = metaData.getContent();
		for (Section section : formularioDinamico.getSections()) {
			log.info("{}", section);
		}
		List<String> messages = new ArrayList<String>();
		formularioDinamico.setBehavior(Hehavior.builder()
				.specification("")
				.messages(messages)
				.build()
			);
		printerJson(formularioDinamico);
	}

	/**
	 * Obtener formulario dinamico documentos test.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void obtenerFormularioDinamicoDocumentosTest() throws BrioBPMException {
		ActividadTO actividad = geActividad();
		DatosAutenticacionTO session = getDatosAutenticacionTO();
		session.setCveUsuario("Gabriela.Arias");
		printerJson(session);
		actividad.setCveInstancia("202006-000001");
		actividad.setIdNodo(1);
		actividad.setSecNodo(2);
		printerJson(actividad);
		DAORet<FormularioDinamico, RetMsg> metaData = null;
		try {
			metaData = service.obtenerFormularioDinamico(session, actividad);
		} catch (BrioBPMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FormularioDinamico formularioDinamico = metaData.getContent();
		for (Section section : formularioDinamico.getSections()) {
			log.info("{}", section);
		}
		printerJson(metaData.getContent());
	}

	/**
	 * Obtener reglas actividad test.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void obtenerReglasActividadTest() throws BrioBPMException {
		ActividadTO actividad = geActividad();
		DatosAutenticacionTO session = getDatosAutenticacionTO();
		session.setCveUsuario("Miguel.Garcia.Saavedra");
		printerJson(session);
		actividad.setCveProceso("INCIDENCIA-SMI");
		actividad.setCveInstancia("202106-000002");
		actividad.setCveRol("GERENTE-CUENTA");
		actividad.setIdNodo(2);
		actividad.setSecNodo(1);
		printerJson(actividad);
		DAORet<FormularioDinamico, RetMsg> metaData = null;
		try {
			metaData = service.obtenerFormularioDinamico(session, actividad);
		} catch (BrioBPMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FormularioDinamico formularioDinamico = metaData.getContent();
		for (Section section : formularioDinamico.getSections()) {
			log.info("{}", section);
		}
		printerJson(metaData.getContent());
	}
   
	@Test
	public void mensajesReglasTest() throws BrioBPMException {
		ActividadTO actividad = geActividad();
		DatosAutenticacionTO session = getDatosAutenticacionTO();
		session.setCveUsuario("Miguel.Garcia.Saavedra");
		printerJson(session);
		actividad.setCveProceso("INCIDENCIA-SMI");
		actividad.setCveInstancia("202106-000002");
		actividad.setCveRol("GERENTE-CUENTA");
		actividad.setIdNodo(2);
		actividad.setSecNodo(1);
		actividad.setCveNodo("ACTIVIDAD-USUARIO");
		printerJson(actividad);
		DAORet<List<LeeMensajesReglas>, RetMsg> metaData =
		 message.leeMensajesReglas(session, actividad);
		List<LeeMensajesReglas> lista = metaData.getContent();
		for (LeeMensajesReglas section : lista) {
			log.info("{}", section);
		}
		printerJson(metaData.getContent());	
	}
	
	@Test
	void builderSectionGridTest() {
		log.info("INICIA builderSectionGridTest TEST");
		
		
		DatosAutenticacionTO session = getDatosAutenticacionTO();
		ActividadTO actividad = geActividad();
		
		StSeccionNodoTO seccion = StSeccionNodoTO.builder()
				.cveSeccion("GRID")
				.build();
		
		
		Section grid = null;
		try {
			grid = formularioDinamicoService.builderSectionGrid(session, actividad, seccion);
		} catch (BrioBPMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		log.info("grid: {}", grid.toString());
	}
	    
}
