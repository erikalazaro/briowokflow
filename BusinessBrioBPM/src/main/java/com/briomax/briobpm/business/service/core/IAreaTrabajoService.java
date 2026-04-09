/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.business.service.core;

import java.math.BigDecimal;
import java.util.List;

import com.briomax.briobpm.common.core.DAORet;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.persistence.entity.AreaTrabajo;
import com.briomax.briobpm.persistence.entity.DatoAreaTrabajo;
import com.briomax.briobpm.persistence.entity.InNodoProceso;
import com.briomax.briobpm.persistence.entity.StNodoProceso;
import com.briomax.briobpm.persistence.entity.TarjetaAreaTrabajo;
import com.briomax.briobpm.transferobjects.BitacoraTO;
import com.briomax.briobpm.transferobjects.MenuAreaTrabajoTO;
import com.briomax.briobpm.transferobjects.dynamicForm.DataGrid;
import com.briomax.briobpm.transferobjects.in.ActividadTO;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;
import com.briomax.briobpm.transferobjects.in.EstatusCreaInstanciaTO;
import com.briomax.briobpm.transferobjects.in.ProcesoSeguroTO;
import com.briomax.briobpm.transferobjects.in.ProcesoTO;

/**
 * El objetivo de la Interface IAreaTrabajoService.java es ...
 * 
 * @author RIGOBERTO OLVERA
 * @version 1.0 Fecha de creacion 29/01/2020 12:31:30 PM Modificaciones:
 * @since JDK 1.8
 */
public interface IAreaTrabajoService {

	/**
	 * Obtener menu area trabajo.
	 * 
	 * @param session Datos autenticacion.
	 * @return el DAO ret.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	DAORet<List<MenuAreaTrabajoTO>, RetMsg> obtenerMenuAreaTrabajo(DatosAutenticacionTO session)
			throws BrioBPMException;
	
	/**
	 * Obtener menu dashboard.
	 * 
	 * @param datosAutenticacion el datos autenticacion.
	 * @return el DAO ret.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	DAORet<List<MenuAreaTrabajoTO>, RetMsg> obtenerMenuDashboard(DatosAutenticacionTO datosAutenticacion)
			throws BrioBPMException;

	/**
	 * Obtener el valor de area trabajo.
	 * 
	 * @param session      Datos autenticacion.
	 * @param datosProceso el datos proceso.
	 * @return el area trabajo.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	DAORet<DataGrid, RetMsg> getAreaTrabajo(DatosAutenticacionTO session, ProcesoTO datosProceso)
			throws BrioBPMException;

	/**
	 * Inicio proceso.
	 * 
	 * @param session      el session.
	 * @param datosProceso el datos proceso.
	 * @return el DAO ret.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	DAORet<ActividadTO, RetMsg> inicioProceso(DatosAutenticacionTO session, ProcesoTO datosProceso)
			throws BrioBPMException;

	/**
	 * Lee bitacora nodo.
	 * 
	 * @param session   el session.
	 * @param actividad el actividad.
	 * @return el DAO ret.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	DAORet<List<BitacoraTO>, RetMsg> leeBitacoraNodo(DatosAutenticacionTO session, ActividadTO actividad)
			throws BrioBPMException;

	/**
	 * Autenticar usuario.
	 * 
	 * @param datosAutenticacion los datos de autenticacion del usuario.
	 * @return el DAO ret con los datos del usuario autenticado.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	DAORet<DatosAutenticacionTO, RetMsg> autenticarUsuario(DatosAutenticacionTO datosAutenticacion)
			throws BrioBPMException;

	/**
	 * Autenticar usuario.
	 * 
	 * @param datosAutenticacion los datos de autenticacion del usuario.
	 * @return el DAO ret con los datos del usuario autenticado.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	DAORet<DatosAutenticacionTO, RetMsg> autenticarUsuarioPass(String usuario, String password)
			throws BrioBPMException;
	
	/**
	 * Inicio proceso seguro con autenticación incluida.
	 * 
	 * @param datosProcesoSeguro los datos del proceso con autenticación.
	 * @return el DAO ret con la actividad creada.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	DAORet<ActividadTO, RetMsg> inicioProcesoSeguro(DatosAutenticacionTO usuarioAutenticado ,ProcesoSeguroTO datosProcesoSeguro)
			throws BrioBPMException;

	List<StNodoProceso> getStNodosProceso(String cveEntidad, String cveProceso, BigDecimal version)
			throws BrioBPMException;

	List<AreaTrabajo> getAreasTrabajo(String cveEntidad, String cveProceso, BigDecimal version) throws BrioBPMException;

	List<TarjetaAreaTrabajo> getTarjetasAreaTrabajo(String cveEntidad, String cveProceso, BigDecimal version,
			String cveAreaTrabajo) throws BrioBPMException;

	List<InNodoProceso> getInNodosProceso(String cveEntidad, String cveProceso, BigDecimal version,
			String cveNodo, BigDecimal idNodo) throws BrioBPMException;

	List<DatoAreaTrabajo> getDatosAreaTrabajo(String cveEntidad, String cveProceso, BigDecimal version,
			String cveAreaTarjeta) throws BrioBPMException;
	
	/**
	 * Proceso para gestional el inicio del crea proceso
	 * @param session
	 * @param cveProceso
	 * @param cveNodo
	 * @param idNodo
	 * @param secNodo
	 * @param version
	 * @return
	 * @throws BrioBPMException
	 */
	EstatusCreaInstanciaTO inicioProcesoApp(DatosAutenticacionTO session, String cveProceso, String cveNodo,
			Integer idNodo, Integer secNodo, String version)
			throws BrioBPMException;
	
}