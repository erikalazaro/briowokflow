/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */


package com.briomax.briobpm.business.service.catalogs;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.briomax.briobpm.business.helpers.NodoHelper;
import com.briomax.briobpm.business.helpers.TraductorHelper;
import com.briomax.briobpm.business.helpers.base.IAreaTrabajoHelper;
import com.briomax.briobpm.business.service.catalogs.core.IActividadService;
import com.briomax.briobpm.business.service.catalogs.core.IMessagesService;
import com.briomax.briobpm.common.core.Constants;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.persistence.dao.base.IMenuDinamicoDAO;
import com.briomax.briobpm.persistence.entity.namedquery.ActividadProceso;
import com.briomax.briobpm.persistence.repository.IStNodoProcesoRepository;
import com.briomax.briobpm.transferobjects.app.ActividadAutorizarTO;
import com.briomax.briobpm.transferobjects.in.NodoTO;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Clase/Iterfarce/Enumeracion ProcesoService.java es ...
 *
 * @author Rigoberto Olvera
 * @since JDK 1.8
 * @version 1.0 Fecha de creacion Jun 26, 2020 12:29:22 PM
 * Modificaciones:
 */
@Service
@Transactional
@Slf4j
public class ActividadService implements IActividadService {


	/** El atributo o variable messages service. */
	@Autowired
	private IMessagesService messagesService;

	/** El atributo o variable menu Dinamico DAO. */
	@Autowired
	private IMenuDinamicoDAO menuDinamicoDAO;
	
	/** El atributo o variable Traductor Helper service. */
	@Autowired
	private TraductorHelper traductorHelper;
	
	/** El atributo o variable Nodo Helper. */
	@Autowired
	private NodoHelper nodoHelper;
	
	/** El atributo o variable Area Trabajo Helper. */
	@Autowired
	private IAreaTrabajoHelper areaTrabajoHelper;
	
	/** El atributo o variable st NodoProceso. */
	@Autowired
	private IStNodoProcesoRepository stNodoProcesoRepository;
	
	/**
	 * Crear una nueva instancia del objeto MttoStProceso.
	 */
	public ActividadService() {
	}


	/** 
	 * {@inheritDoc}
	 * @see com.briomax.briobpm.business.service.catalogs.core.IActividadService#getActividadByProcesos()
	 */
	@Override
	public List<ActividadAutorizarTO> getActividadByProcesos(String cveEntidad, String cveProceso, BigDecimal version, String cveUsuario, String cveIdioma) throws BrioBPMException {
		
		List<ActividadAutorizarTO> lista = new LinkedList<ActividadAutorizarTO>();
		List<ActividadProceso> listMenu = menuDinamicoDAO.obtenerActividaesByProceso(cveEntidad, cveProceso, version, cveUsuario);
		String fecha;
		String desPproceos;
		String desActividad;
		String estilo;
		
		for (ActividadProceso entity : listMenu) {

			//desPproceos = traductorHelper.getTraducce(cveEntidad, cveIdioma, entity.getDesProceso());
			//desActividad = traductorHelper.getTraducce(cveEntidad, cveIdioma, entity.getDescripcion());
			log.debug(">>>>>>>>>>>>>>>> Entre en for ActividadProceso: " + entity.getFechaLimite());
			fecha = "";
			if (entity.getFechaLimite() != null) {
				fecha = nodoHelper.formatFecha(entity.getFechaLimite(), "dd/MM/yyyy HH:mm:ss");
			} 
					
			estilo = areaTrabajoHelper.leeEstiloNivelServicio(entity.getFechaCreacion(), entity.getFechaLimite(),
					"REGISTRO", entity.getFechaCreacion());
			
			ActividadAutorizarTO to = ActividadAutorizarTO.builder()
					.cveProceso(entity.getCveProceso())
					.version(entity.getVersion())
					.cveInstancia(entity.getCveInstancia())
					.cveNodo(entity.getCveNodo())
					.idNodo(entity.getIdNodo())
					.secNodo(entity.getSecNodo())
					.desProceso(entity.getDesProceso())					
					.desActividades(entity.getDescripcion())
					.fecHoraVencimiento(fecha)
					.colorVencimiento(estilo)
					.build();
			
			lista.add(to);	
		}
		
		List<Object> nueInstancia = stNodoProcesoRepository.obtienerNuevaInstancia(cveEntidad, cveProceso,
				version, cveUsuario);
		estilo = Constants.GDCELLGREEN;
		if (nueInstancia.size() > 0) {
			Object[] rowDetail = (Object[]) nueInstancia.get(0);
			
			String desProceso = (String) Arrays.asList(rowDetail).get(3);
			String cveNodo = (String) Arrays.asList(rowDetail).get(4);
			BigDecimal idNodo = (BigDecimal) Arrays.asList(rowDetail).get(5);
			String desBoton = (String) Arrays.asList(rowDetail).get(6);
			String desNodo = (String) Arrays.asList(rowDetail).get(7);
			
			ActividadAutorizarTO to = ActividadAutorizarTO.builder()
					.cveProceso(cveProceso)
					.version(version)
					.cveInstancia("0")
					.cveNodo(cveNodo)
					.idNodo(idNodo.intValue())
					.secNodo(0)
					.desProceso(desProceso)					
					.desActividades(desNodo + " - " + desBoton)
					.fecHoraVencimiento("")
					.colorVencimiento(estilo)
					.build();
			
			lista.add(to);	
		}

		return lista;
	}
}
