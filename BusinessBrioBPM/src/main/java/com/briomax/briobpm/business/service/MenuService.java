/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.business.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.briomax.briobpm.business.helpers.TraductorHelper;
import com.briomax.briobpm.business.helpers.base.IEntidadHelper;
import com.briomax.briobpm.business.service.core.IMenuService;
import com.briomax.briobpm.common.core.DAORet;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.persistence.dao.base.IMenuDinamicoDAO;
import com.briomax.briobpm.persistence.entity.Funcion;
import com.briomax.briobpm.persistence.entity.namedquery.LeeMenuEstatico;
import com.briomax.briobpm.persistence.entity.namedquery.MenuPrincipal;
import com.briomax.briobpm.persistence.repository.IFuncion;
import com.briomax.briobpm.persistence.repository.IStNodoProcesoRepository;
import com.briomax.briobpm.transferobjects.MenuAreaTrabajoTO;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;

import lombok.extern.slf4j.Slf4j;


/**
 * El objetivo de la Class AreaTrabajoService.java es ...
 * @author RIGOBERTO OLVERA
 * @version 1.0 Fecha de creacion 29/01/2020 12:32:32 PM Modificaciones:
 * @since JDK 1.8
 */
@Service
@Transactional
@Slf4j
public class MenuService implements IMenuService {

	private static final String SALIDA = "SALIDA";

	/** El atributo o variable menu Dinamico DAO. */
	@Autowired
	private IMenuDinamicoDAO menuDinamicoDAO;
	
	/** El atributo o variable st NodoProceso. */
	@Autowired
	private IStNodoProcesoRepository stNodoProcesoRepository;

	/** El atributo o variable Traductor Helper service. */
	@Autowired
	private TraductorHelper traductorHelper;
	
	/** El atributo o variable Entidad Helper service. */
	@Autowired
	private IEntidadHelper entidadHelper;
	
	/** El atributo o variable Entidad Helper service. */
	@Autowired
	private IFuncion funcion;
	
	/**
	 * Crear una nueva instancia del objeto area trabajo service.
	 */
	public MenuService() {
		log.debug("Constructor Inicializando AreaTrabajoHelper ");
	}

	/** 
	 * {@inheritDoc}
	 * @see com.briomax.briobpm.business.service.core.IAreaTrabajoService#obtenerMenuAreaTrabajo(com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO)
	 */
	@Override
	public DAORet<List<MenuAreaTrabajoTO>, RetMsg> obtenerMenuAreaTrabajo(DatosAutenticacionTO datosAutenticacion) throws BrioBPMException {
		List<MenuAreaTrabajoTO> lista = new LinkedList<MenuAreaTrabajoTO>();
		log.info("\t----- Obtener Menu Dinamico para {}.", datosAutenticacion.getCveUsuario());
		Integer count = 1;
		Integer noProcesos, noNodo;
		String desPproceos, desNode;
		MenuAreaTrabajoTO to = null;
		
		List<MenuPrincipal> listMenu = menuDinamicoDAO.obtenerMenu(datosAutenticacion.getCveEntidad().toUpperCase(), datosAutenticacion.getCveUsuario(), 
				datosAutenticacion.getCveIdioma().toUpperCase());
		
		//log.info("-----> listMenu: " + listMenu.size());
		for (MenuPrincipal entity : listMenu) {
			//log.info("\t----- Obtener listaMenu {}.", entity);
			noNodo = stNodoProcesoRepository.actividadesNodo(datosAutenticacion.getCveEntidad(), entity.getCveProceso(),
					entity.getVersion(), entity.getCveNodo(), entity.getIdNodo(), 
					"REGISTRO", datosAutenticacion.getCveUsuario());
			log.debug("------------> NUMERO NODO: " + noNodo);
			//desNode = traductorHelper.getTraducce(datosAutenticacion, entity.getDesNodo()) + " (" + noNodo + ")";
			log.debug("actividadesSinNodo DENTRO DE obtenerMenuAreaTrabajo 1");
			noProcesos = stNodoProcesoRepository.actividadesSinNodo(datosAutenticacion.getCveEntidad(), entity.getCveProceso(),
					entity.getVersion(), "REGISTRO", datosAutenticacion.getCveUsuario());
			//desPproceos = traductorHelper.getTraducce(datosAutenticacion, entity.getDesProceso()) + " (" + noProcesos + ")";
			
			to = MenuAreaTrabajoTO.builder()
					.tipo("DINAMICO")
					.ordenamiento(entity.getOrdenamiento())
					.cveProceso(entity.getCveProceso())
					.version(String.valueOf(entity.getVersion()))
					.desProceso(entity.getDesProceso()+ " (" + noProcesos + ")")
					.cveNodo(entity.getCveNodo())
					.idNodo(entity.getIdNodo())
					.desNodo(entity.getDesNodo()+ " (" + noNodo + ")")
					.iniProceso(entity.getIniProceso())
					.etiBoton(entity.getEtiquetaBoton() == null? " ": entity.getEtiquetaBoton())
					.cveRol(entity.getCveRol())
					.cveAreaTrabajo(entity.getCveAreaTrabajo())
					.ejeAutomatica(entity.getEjecucionAutomatica())
					.etiBotEjeAutomatica(entity.getEtiquetaBotonEjecucionAutomatica()== null? " ":entity.getEtiquetaBotonEjecucionAutomatica())
					.icono(entity.getIconoProceso())
					.build();
			lista.add(to);
			count++;
		}
		
		listMenu  = new LinkedList<MenuPrincipal>();
		listMenu = menuDinamicoDAO.obtenerMenuDos(datosAutenticacion.getCveEntidad(), datosAutenticacion.getCveUsuario(), 
				datosAutenticacion.getCveIdioma(), "TEMPORIZADOR");
		//log.info("-----> listMenu: " + listMenu.size());

		for (MenuPrincipal entity : listMenu) {
			
			noNodo = stNodoProcesoRepository.actividadesNodo(datosAutenticacion.getCveEntidad(), entity.getCveProceso(),
					entity.getVersion(), entity.getCveNodo(), entity.getIdNodo(), 
					"REGISTRO", datosAutenticacion.getCveUsuario());
			//desNode = traductorHelper.getTraducce(datosAutenticacion, entity.getDesNodo()) + " (" + noNodo + ")";
			log.debug("actividadesSinNodo DENTRO DE obtenerMenuAreaTrabajo 2");
			noProcesos = stNodoProcesoRepository.actividadesSinNodo(datosAutenticacion.getCveEntidad(), entity.getCveProceso(),
					entity.getVersion(), "REGISTRO", datosAutenticacion.getCveUsuario());
			//desPproceos = traductorHelper.getTraducce(datosAutenticacion, entity.getDesProceso()) + " (" + noProcesos + ")";
			
			to = MenuAreaTrabajoTO.builder()
					.tipo("DINAMICO")
					.ordenamiento(entity.getOrdenamiento())
					.cveProceso(entity.getCveProceso())
					.version(String.valueOf(entity.getVersion()))
					.desProceso(entity.getDesProceso()+ " (" + noProcesos + ")")
					.cveNodo(entity.getCveNodo())
					.idNodo(entity.getIdNodo())
					.desNodo(entity.getDesNodo()+ " (" + noNodo + ")")
					.iniProceso(entity.getIniProceso())
					.etiBoton(entity.getEtiquetaBoton() == null? " ": entity.getEtiquetaBoton())
					.cveRol(entity.getCveRol())
					.cveAreaTrabajo(entity.getCveAreaTrabajo())
					.ejeAutomatica(entity.getEjecucionAutomatica())
					.etiBotEjeAutomatica(entity.getEtiquetaBotonEjecucionAutomatica()== null? " ":entity.getEtiquetaBotonEjecucionAutomatica())
					.icono(entity.getIconoProceso())
					.build();
			lista.add(to);
			count++;
		}
		log.debug(">>>>>>> listMenu {}", lista.size());
		
		List<Funcion> listas = funcion.obtenFuncionUsuario(datosAutenticacion.getCveEntidad(), datosAutenticacion.getCveUsuario());

		for (Funcion funcion : listas) {
			to = MenuAreaTrabajoTO.builder().tipo("ESTATICO").ordenamiento(count).cveProceso(funcion.getTipoFuncion())
					.desProceso(funcion.getDesTipoFuncion()).cveNodo(funcion.getCveFacultad()).desNodo(funcion.getDescripcion())
					.idOpcion(funcion.getFuncion()).parametros("").build();
			lista.add(to);
			count++;
		}
				
/*
		to = MenuAreaTrabajoTO.builder().tipo("ESTATICO").ordenamiento(count).cveProceso("REPORTES")
				.desProceso("Reportes Operativos").cveNodo("CONSULTA_POR_ACTIVIDAD").desNodo("Consulta por Actividad")
				.idOpcion("reporteactividad").parametros("").build();
		lista.add(to);
		
		count++;
		to = MenuAreaTrabajoTO.builder().tipo("ESTATICO").ordenamiento(count).cveProceso("CONSULTA")
				.desProceso("Adaptaciones").cveNodo("CONSULTA_REPSE").desNodo("Consulta REPSE")
				.idOpcion("reportrepse").parametros("").build();
		lista.add(to);
		count++;
		
		to = MenuAreaTrabajoTO.builder().tipo("ESTATICO").ordenamiento(count).cveProceso("CONSULTA")
				.desProceso("Adaptaciones").cveNodo("CONSULTA_TRABAJADOR").desNodo("Consulta Trabajadores")
				.idOpcion("consultrabajares").parametros("").build();
		lista.add(to);
		count++;
		
		to = MenuAreaTrabajoTO.builder().tipo("ESTATICO").ordenamiento(count).cveProceso("CONSULTA")
				.desProceso("Adaptaciones").cveNodo("CONSULTA_FTP").desNodo("Procesos Periódicos")
				.idOpcion("cargapdf").parametros("").build();
		lista.add(to);
		count++;
		
		to = MenuAreaTrabajoTO.builder().tipo("ESTATICO").ordenamiento(count).cveProceso("CONSULTA")
				.desProceso("Administración").cveNodo("USUARIO").desNodo("Usuario")
				.idOpcion("usuario").parametros("").build();
		lista.add(to);
		//assemblerMenu(lista);
		log.debug("\t      {}", lista);*/
		
		RetMsg msg = RetMsg.builder().status("OK").message("").build();
		return new DAORet<List<MenuAreaTrabajoTO>, RetMsg>(lista, msg);
	}

	/** 
	 * {@inheritDoc}
	 * @see com.briomax.briobpm.business.service.core.IAreaTrabajoService#obtenerMenuDashboard(com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO)
	 */
	@Override
	public DAORet<List<MenuAreaTrabajoTO>, RetMsg> obtenerMenuDashboard(DatosAutenticacionTO datosAutenticacion)
		throws BrioBPMException {
		List<MenuAreaTrabajoTO> lista = new LinkedList<MenuAreaTrabajoTO>();
		List<LeeMenuEstatico> listMenu = menuDinamicoDAO.obtenerMenuDashbord(datosAutenticacion.getCveEntidad(), datosAutenticacion.getCveUsuario());		
		log.debug(">>>>>>>>>>>>>>>> obtenerMenuDashboard ---- Obtener Menu Dashboard para {} ", datosAutenticacion.getCveUsuario());
		MenuAreaTrabajoTO to = null;
		for (LeeMenuEstatico entity : listMenu) {
			to = MenuAreaTrabajoTO.builder()
					.tipo("ESTATICO")
					.ordenamiento(entity.getId().getOrdenamiento())
					.cveProceso(entity.getId().getCveMenu())
					.desProceso(entity.getDescripcionMenu())
					.cveNodo(entity.getId().getCveOpcion())
					.desNodo(entity.getDescripcionOpcion())
					.idOpcion(entity.getId().getIdOpcion())
					.parametros(entity.getParametros())
					.build();
			lista.add(to);
		}

		log.trace("\t  obtenerMenuDashboard {}", lista);
		RetMsg msg = RetMsg.builder().status("OK").message("").build();
		return new DAORet<List<MenuAreaTrabajoTO>, RetMsg>(lista, msg);
	}


}
