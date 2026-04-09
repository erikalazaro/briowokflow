/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */
package com.briomax.briobpm.business.service.test;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.briomax.briobpm.business.helpers.AreaTrabajoHelper;
import com.briomax.briobpm.business.service.core.IAreaTrabajoService;
import com.briomax.briobpm.business.test.base.AbstractCoreTest;
import com.briomax.briobpm.common.core.DAORet;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.transferobjects.BitacoraTO;
import com.briomax.briobpm.transferobjects.MenuAreaTrabajoTO;
import com.briomax.briobpm.transferobjects.dynamicForm.DataGrid;
import com.briomax.briobpm.transferobjects.in.ActividadTO;
import com.briomax.briobpm.transferobjects.in.ColumnasAreaTrabajoTO;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;
import com.briomax.briobpm.transferobjects.in.NodoTO;
import com.briomax.briobpm.transferobjects.in.ProcesoTO;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Class AreaTrabajoServiceTest.java es ...
 *
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Feb 14, 2020 12:20:13 PM
 * Modificaciones:
 * @since JDK 1.8
 */
@Slf4j
public class AreaTrabajoServiceTest extends AbstractCoreTest {

	/** El atributo o variable area trabajo service. */
	@Autowired
	private IAreaTrabajoService areaTrabajoService;
	
	@Autowired 
	private AreaTrabajoHelper areaTrabajoHelper;
	
	/**
	 * Menu area trabajo test.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	@Ignore
	public void menuAreaTrabajoTest() throws BrioBPMException {
		DatosAutenticacionTO datosAutenticacion = getDatosAutenticacionTO();
		datosAutenticacion.setCveUsuario("Admin.Opera");
		printerJson(datosAutenticacion);
		DAORet<List<MenuAreaTrabajoTO>, RetMsg> res =
			areaTrabajoService.obtenerMenuAreaTrabajo(datosAutenticacion);
		res.getContent().forEach((item) -> {
			log.info("{}", item);
		});
		printerJson(res.getContent());
	}

	/**
	 * Menus area trabajo test.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	@Ignore
	public void menusAreaTrabajoTest() throws BrioBPMException {
		DatosAutenticacionTO datosAutenticacion = getDatosAutenticacionTO();
		datosAutenticacion.setCveUsuario("Gabriela.Arias");
		printerJson(datosAutenticacion);
		DAORet<List<MenuAreaTrabajoTO>, RetMsg> res =
			areaTrabajoService.obtenerMenuAreaTrabajo(datosAutenticacion);
		res.getContent().forEach((item) -> {
			log.info("{}", item);
		});
		printerJson(res.getContent());
	}

	/**
	 * Menu dashboard test.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	@Ignore
	public void menuDashboardTest() throws BrioBPMException {
		DatosAutenticacionTO datosAutenticacion = getDatosAutenticacionTO();
		datosAutenticacion.setCveUsuario("Miguel.Garcia.Saavedra");
		printerJson(datosAutenticacion);
		DAORet<List<MenuAreaTrabajoTO>, RetMsg> res =
			areaTrabajoService.obtenerMenuDashboard(datosAutenticacion);
		res.getContent().forEach((item) -> {
			log.info("{}", item);
		});
		printerJson(res.getContent());
	}

	/**
	 * Grid test.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	//@Ignore
	public void gridTest() throws BrioBPMException {
		log.info("INICIA GRID TEST");
		ProcesoTO datosProceso = getProceso();
		datosProceso.setCveNodo("ACTIVIDAD-USUARIO");
		datosProceso.setCveAreaTrabajo("SOLICITUD_SOPORTE");
		datosProceso.setIdNodo(1);
		//
		//datosProceso.setCveRol("SOPORTE_N2");
		//datosProceso.setIdNodo(6);
		//datosProceso.setCveAreaTrabajo("CORRECCION_INCIDENCIA");
		DatosAutenticacionTO dataSession = getDatosAutenticacionTO();
		//dataSession.setCveUsuario("Jose.Ramon.Alfonso");
		dataSession.setCveUsuario("Admin.Opera");
		datosProceso.setCveRol("ROL_ADMIN_OPERA");
		datosProceso.setIdNodo(1);
		datosProceso.setCveProceso("G2 ACF");
		datosProceso.setCveAreaTrabajo("AREA_SIN_SLA");
		datosProceso.setCveNodo("ACTIVIDAD-USUARIO");
		//
		log.info("DATOS DE LA SESION");
		printerJson(dataSession);
		log.info("DATOS DEL PROCESO");
		printerJson(datosProceso);
		DAORet<DataGrid, RetMsg> res = areaTrabajoService.getAreaTrabajo(dataSession, datosProceso);
		log.info("{}", res.getMeta());
		log.info("{}", res.getContent());
		printerJson(res.getContent());
		
		
		
		dataSession = getDatosAutenticacionTO();
		dataSession.setCveUsuario("Admin.Opera");
		datosProceso.setCveRol("ROL_ADMIN_OPERA");
		datosProceso.setIdNodo(2);
		datosProceso.setCveProceso("G2 ACF");
		datosProceso.setCveAreaTrabajo("AREA_CON_SLA");
		datosProceso.setCveNodo("ACTIVIDAD-USUARIO-TEMPORIZACION");
		//
		log.info("DATOS DE LA SESION");
		printerJson(dataSession);
		log.info("DATOS DEL PROCESO");
		printerJson(datosProceso);
		res = areaTrabajoService.getAreaTrabajo(dataSession, datosProceso);
		log.info("{}", res.getMeta());
		log.info("{}", res.getContent());
		printerJson(res.getContent());
	}

	/**
	 * Inicio proceso test.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	//@Ignore
	public void inicioProcesoTest() throws BrioBPMException {
		DatosAutenticacionTO session = getDatosAutenticacionTO();
		session.setCveUsuario("Francisco.Rodriguez");
		printerJson(session);
		ProcesoTO datosProceso = getProceso();
		datosProceso.setCveRol("CLIENTE");
		datosProceso.setCveProceso("ATEN_INCI_TICK");
		datosProceso.setVersion("1");
		datosProceso.setCveNodo("ACTIVIDAD-USUARIO");
		datosProceso.setIdNodo(1);
		datosProceso.setCveAreaTrabajo("CRE_FOL");
		printerJson(datosProceso);
		DAORet<ActividadTO, RetMsg> res = areaTrabajoService.inicioProceso(getDatosAutenticacionTO(), datosProceso);
		log.info("----------> INICIO PROCESO");
		log.info("{}", res.getMeta());
		log.info("{}", res.getContent());
		printerJson(res.getContent());
		log.info("SE TERMINO ESTE TEST");
	}

	/**
	 * Lee bitacora nodo test.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	@Ignore
	public void leeBitacoraNodoTest() throws BrioBPMException {
		DatosAutenticacionTO session = getDatosAutenticacionTO();
		session.setCveUsuario("Francisco.Rodriguez");
		printerJson(session);
		ActividadTO activity = geActividad();
		activity.setCveProceso("G2 ACF");
		activity.setCveInstancia("202012-000003");
		activity.setIdNodo(1);
		activity.setSecNodo(1);
		printerJson(activity);
		DAORet<List<BitacoraTO>, RetMsg> res = areaTrabajoService.leeBitacoraNodo(session, activity);
		res.getContent().forEach((item) -> {
			log.info("{}", item);
		});
		printerJson(res.getContent());
	}
	
	@Test
	public void getAreaTrabajoTest() throws BrioBPMException {
		// sesion
		DatosAutenticacionTO datosAutenticacion = getDatosAutenticacionTO();
		datosAutenticacion.setCveUsuario("Admin.Opera");
		datosAutenticacion.setCveEntidad("BRIOMAX");
		datosAutenticacion.setCveMoneda("USD");
		printerJson(datosAutenticacion);
		
		// proceso
		ProcesoTO datosProceso = new ProcesoTO();
//		datosProceso.setCveRol("PARTICIPANTE");
//		datosProceso.setCveProceso("REBATES AND REWARDS EJECUCIÓN");
//		datosProceso.setVersion("1");
//		datosProceso.setCveNodo("ACTIVIDAD-USUARIO-TEMPORIZACION");
//		datosProceso.setIdNodo(1);
//		datosProceso.setCveAreaTrabajo("PARTICIPANTE");
		datosProceso.setCveRol("ROL_ADMIN_OPERA");
		datosProceso.setCveProceso("G2 ACF");
		datosProceso.setVersion("1");
		datosProceso.setCveNodo("ACTIVIDAD-USUARIO"); // no tenemos un evento usuario para la pantalla de 
		datosProceso.setIdNodo(1);
		datosProceso.setCveAreaTrabajo("AREA_SIN_SLA");
		
		DAORet <DataGrid, RetMsg> res = 
			areaTrabajoService.getAreaTrabajo(datosAutenticacion,datosProceso);
		
		
		printerJson(res.getContent());
		log.info("TERMINA getAreaTrabajo -1");
		
		
		
		datosProceso = new ProcesoTO();
		datosProceso.setCveRol("ROL_ADMIN_OPERA");
		datosProceso.setCveProceso("G2 ACF");
		datosProceso.setVersion("1");
		datosProceso.setCveNodo("ACTIVIDAD-USUARIO-TEMPORIZACION"); 
		datosProceso.setIdNodo(2);
		datosProceso.setCveAreaTrabajo("AREA_CON_SLA");
		
		res = 
			areaTrabajoService.getAreaTrabajo(datosAutenticacion,datosProceso);
		
		
		printerJson(res.getContent());
		log.info("TERMINA getAreaTrabajo -2");
		
	}
	
	@Test
	public void getAreaTrabajoTest2() throws BrioBPMException {
		// sesion
		DatosAutenticacionTO datosAutenticacion = getDatosAutenticacionTO();
		datosAutenticacion.setCveUsuario("Admin.Opera");
		datosAutenticacion.setCveEntidad("BRIOMAX");
		datosAutenticacion.setCveMoneda("USD");
		printerJson(datosAutenticacion);
		
		// proceso
		ProcesoTO datosProceso = new ProcesoTO();
		datosProceso = new ProcesoTO();
		datosProceso.setCveRol("ROL_ADMIN_OPERA");
		datosProceso.setCveProceso("G2 ACF");
		datosProceso.setVersion("1");
		datosProceso.setCveNodo("ACTIVIDAD-USUARIO-TEMPORIZACION"); 
		datosProceso.setIdNodo(2);
		datosProceso.setCveAreaTrabajo("AREA_CON_SLA");
		
		DAORet <DataGrid, RetMsg>  res = 
			areaTrabajoService.getAreaTrabajo(datosAutenticacion,datosProceso);
		
		
		printerJson(res.getContent());
		log.info("TERMINA getAreaTrabajo -2");
		
	}
	
	
	@Test
	public void leeColumnaAreaTrabajoTest() throws BrioBPMException {
		DatosAutenticacionTO datosAutenticacion = getDatosAutenticacionTO();
		datosAutenticacion.setCveUsuario("Admin.Opera");
		datosAutenticacion.setCveEntidad("BRIOMAX");
		printerJson(datosAutenticacion);
		NodoTO nodo = new NodoTO();
		nodo.setRol("ROL_ADMIN_OPERA");
		nodo.setCveProceso("G2 ACF");
		nodo.setVersion(BigDecimal.ONE);
		nodo.setCveNodo("ACTIVIDAD-USUARIO");
		nodo.setIdNodo(1);
		String cveAreaTrabajo = "AREA_SIN_SLA";
		
		DAORet<List<ColumnasAreaTrabajoTO>, RetMsg> res = 
				areaTrabajoHelper.leeColumnaAreaTrabajo(getDatosAutenticacionTO(), nodo, cveAreaTrabajo);
		printerJson(res.getContent());
		log.info("TERMINA leeColumnaAreaTrabajoTest - 1");
		
		nodo.setRol("ROL_ADMIN_OPERA");
		nodo.setCveProceso("G2 ACF");
		nodo.setVersion(BigDecimal.ONE);
		nodo.setCveNodo("ACTIVIDAD-USUARIO-TEMPORIZACION");
		nodo.setIdNodo(2);
		cveAreaTrabajo = "AREA_CON_SLA";
		
		 res = 
				areaTrabajoHelper.leeColumnaAreaTrabajo(getDatosAutenticacionTO(), nodo, cveAreaTrabajo);
		printerJson(res.getContent());
		log.info("TERMINA leeColumnaAreaTrabajoTest - 2");
		
	}
}
