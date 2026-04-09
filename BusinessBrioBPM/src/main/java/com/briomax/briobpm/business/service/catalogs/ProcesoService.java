/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */


package com.briomax.briobpm.business.service.catalogs;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.briomax.briobpm.business.convertes.IStProcesoConverter;
import com.briomax.briobpm.business.helpers.TraductorHelper;
import com.briomax.briobpm.business.service.catalogs.core.IMessagesService;
import com.briomax.briobpm.business.service.catalogs.core.IProcesoService;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.persistence.dao.base.IMenuDinamicoDAO;
import com.briomax.briobpm.persistence.entity.StProceso;
import com.briomax.briobpm.persistence.entity.StProcesoPK;
import com.briomax.briobpm.persistence.entity.namedquery.MenuProcesos;
import com.briomax.briobpm.persistence.repository.IStNodoProcesoRepository;
import com.briomax.briobpm.persistence.repository.IStProcesoRepository;
import com.briomax.briobpm.transferobjects.app.ProcesoTO;
import com.briomax.briobpm.transferobjects.catalogs.Proceso;
import com.briomax.briobpm.transferobjects.catalogs.ProcesoVersion;
import com.briomax.briobpm.transferobjects.catalogs.ProcesosTO;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;

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
public class ProcesoService implements IProcesoService {

	/** El atributo o variable repository. */
	@Autowired
	private IStProcesoRepository repository;

	/** El atributo o variable messages service. */
	@Autowired
	private IMessagesService messagesService;

	/** El atributo o variable menu Dinamico DAO. */
	@Autowired
	private IMenuDinamicoDAO menuDinamicoDAO;
	
	/** El atributo o variable st NodoProceso. */
	@Autowired
	private IStNodoProcesoRepository stNodoProcesoRepository;
	
	/** El atributo o variable Traductor Helper service. */
	@Autowired
	private TraductorHelper traductorHelper;
	
	/**
	 * Crear una nueva instancia del objeto MttoStProceso.
	 */
	public ProcesoService() {
	}

	/** 
	 * {@inheritDoc}
	 * @see com.briomax.briobpm.business.service.catalogs.core.IProcesoService#getAll(com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO)
	 */
	@Override
	public List<ProcesosTO> getAll(DatosAutenticacionTO session) throws BrioBPMException {
		List<ProcesosTO> listDto = new ArrayList<ProcesosTO>();
		try {
			log.debug("Get Entitys StProceso ");
			StProceso stProceso = StProceso.builder()
					.id(StProcesoPK.builder()
						.cveEntidad(session.getCveEntidad())
						.build())
					.build();
			List<StProceso> listEntity =
				repository.findAll(Example.of(stProceso), Sort.by("id.cveProceso").ascending().and(Sort.by("id.version").ascending()));
			log.debug("Converte Entity (StProceso) -> Dto (StProcesoTO) ");
			listDto
				.addAll(listEntity.stream().map(IStProcesoConverter.converterEntityToDTO).collect(Collectors.toList()));
		}
		catch (DataAccessException exData) {
			log.error("###DATA-ERROR### : {} ", exData.getMessage());
			throw new BrioBPMException(messagesService.getMessage(session, "PROCESO",
				"MANTENIMIENTO_PROCESO_CONSULTAR_ERROR", ""), exData);
		}
		return listDto;
	}

	/** 
	 * {@inheritDoc}
	 * @see com.briomax.briobpm.business.service.catalogs.core.IProcesoService#getProcesos(com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO)
	 */
	@Override
	public List<Proceso> getProcesos(DatosAutenticacionTO session) throws BrioBPMException {
		List<Proceso> process = new ArrayList<>();
		List<String> procesos = repository.findDistinctProcesosByEntidad(session.getCveEntidad());
		procesos.forEach(cveProceso -> {
			log.info("\t{}", cveProceso);
			List<ProcesoVersion> versiones = new ArrayList<>();
			List<StProceso> listEntity =
					repository.findAll(Example.of(StProceso.builder()
						.id(StProcesoPK.builder()
							.cveEntidad(session.getCveEntidad())
							.cveProceso(cveProceso)
							.build())
						.situacion("HABILITADO")
						.build()), Sort.by("id.version").ascending());
			listEntity.forEach(proceso -> {
				log.info("\t\t{}", proceso);
				versiones.add(ProcesoVersion.builder().version(String.valueOf(proceso.getId().getVersion()))
					.descripcion(proceso.getDescripcion()).build());
			});
			process.add(Proceso.builder().cveProceso(cveProceso).versiones(versiones).build());
		});
		return process;
	}

	/** 
	 * {@inheritDoc}
	 * @see com.briomax.briobpm.business.service.catalogs.core.IProcesoService#getProcesosByUser()
	 */
	@Override
	public List<ProcesoTO> getProcesosByUser(String cveEntidad, String cveUsuario, String cveIdioma, String cveLocalidad ) throws BrioBPMException {
		
		List<ProcesoTO> lista = new LinkedList<ProcesoTO>();
		List<MenuProcesos> listMenu = menuDinamicoDAO.obtenerMenuProceso(cveEntidad, cveUsuario);
		Integer noProcesos;
		String desPproceos;
		
		for (MenuProcesos entity : listMenu) {

			log.debug("actividadesSinNodo DENTRO DE getProcesosByUser");
			noProcesos = stNodoProcesoRepository.actividadesSinNodo(cveEntidad, entity.getCveProceso(),
					(BigDecimal) entity.getVersion(), "REGISTRO", cveUsuario);
			desPproceos = traductorHelper.getTraducce(cveEntidad, cveIdioma, entity.getDesProceso());
			
			List<Object> nueInstancia = stNodoProcesoRepository.obtienerNuevaInstancia(cveEntidad, entity.getCveProceso(),
					(BigDecimal) entity.getVersion(), cveUsuario);
			
			if (noProcesos > 0 || nueInstancia.size() > 0) {
				ProcesoTO to = ProcesoTO.builder()
						.cveProceso(entity.getCveProceso())
						.version(entity.getVersion())
						.desProceso(desPproceos) // revisar cambio con david  a cveProceso 
						.actividad(noProcesos)
						.build();
				lista.add(to);				
			}
			
		}
		

		return lista;
	}
}
