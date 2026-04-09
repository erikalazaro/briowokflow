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
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import com.briomax.briobpm.business.convertes.IStRolProcesoConverter;
import com.briomax.briobpm.business.convertes.UsuarioRolConverter;
import com.briomax.briobpm.business.service.catalogs.core.IMessagesService;
import com.briomax.briobpm.business.service.catalogs.core.IRolProcesoService;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.persistence.dao.base.IUsuarioDAO;
import com.briomax.briobpm.persistence.entity.StRolProceso;
import com.briomax.briobpm.persistence.entity.StRolProcesoPK;
import com.briomax.briobpm.persistence.entity.UsuarioRolPK;
import com.briomax.briobpm.persistence.entity.namedquery.UsuarioRolProceso;
import com.briomax.briobpm.persistence.repository.IStRolProcesoRepository;
import com.briomax.briobpm.persistence.repository.IUsuarioRolRepository;
import com.briomax.briobpm.transferobjects.catalogs.UsuariosRol;
import com.briomax.briobpm.transferobjects.catalogs.RolProcesoTO;
import com.briomax.briobpm.transferobjects.catalogs.UsuarioRol;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Class RolProcesoService.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Jun 26, 2020 12:39:56 PM Modificaciones:
 * @since JDK 1.8
 */
@Service
@Transactional
@Slf4j
public class RolProcesoService implements IRolProcesoService {

	/** La Constante ROL_PROCESO. */
	private static final String ROL_PROCESO = "ROL_PROCESO";

	/** La Constante DATA_ERROR. */
	private static final String DATA_ERROR = "###DATA-ERROR### : {} ";

	/** La Constante VACIO. */
	private static final String VACIO = "";

	/** El atributo o variable repository. */
	@Autowired
	private IStRolProcesoRepository repository;

	/** El atributo o variable repository usario rol. */
	@Autowired
	private IUsuarioRolRepository repositoryUsarioRol;

	/** El atributo o variable usuario DAO. */
	@Autowired
	private IUsuarioDAO usuarioDAO;

	/** El atributo o variable messages service. */
	@Autowired
	private IMessagesService messagesService;

	/**
	 * Crear una nueva instancia del objeto mtto st rol proceso.
	 */
	public RolProcesoService() {
	}

	/** 
	 * {@inheritDoc}
	 * @see com.briomax.briobpm.business.service.catalogs.core.IRolProcesoService#getAll(com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO, java.lang.String, java.lang.String)
	 */
	@Override
	public List<RolProcesoTO> getAll(DatosAutenticacionTO session, String cveProceso, String version)
		throws BrioBPMException {
		List<RolProcesoTO> listDto = new ArrayList<RolProcesoTO>();
		try {
			StRolProceso stRolProceso = StRolProceso.builder()
					.id(StRolProcesoPK.builder()
						.cveEntidad(session.getCveEntidad())
						.cveProceso(cveProceso)
						.version(new BigDecimal(version))
						.build())
					.build();
			List<StRolProceso> listEntity = repository.findAll(Example.of(stRolProceso),
				Sort.by(Sort.Direction.ASC, "id.cveRol"));
			log.debug("Converte Entity (StRolProceso) -> Dto (StRolProcesoTO) ");
			listDto
			.addAll(listEntity.stream().map(IStRolProcesoConverter.converterEntityToDTO).collect(Collectors.toList()));
		}
		catch (DataAccessException exData) {
			log.error(DATA_ERROR, exData.getMessage());
			throw new BrioBPMException(messagesService.getMessage(session, ROL_PROCESO,
					"MANTENIMIENTO_ROLPROCESO_CONSULTAR_ERROR", VACIO), exData);
		}
		return listDto;
	}

	/** 
	 * {@inheritDoc}
	 * @see com.briomax.briobpm.business.service.catalogs.core.IRolProcesoService#insert(com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO, com.briomax.briobpm.transferobjects.catalogs.RolProcesoTO)
	 */
	@Override
	public RetMsg insert(DatosAutenticacionTO session, RolProcesoTO rolProcesoTO) throws BrioBPMException {
		return process(session, rolProcesoTO, "MANTENIMIENTO_ROLPROCESO_REGISTRAR_OK",
			"MANTENIMIENTO_ROLPROCESO_REGISTRAR_ERROR", VACIO, false);
	}

	/** 
	 * {@inheritDoc}
	 * @see com.briomax.briobpm.business.service.catalogs.core.IRolProcesoService#update(com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO, com.briomax.briobpm.transferobjects.catalogs.RolProcesoTO)
	 */
	@Override
	public RetMsg update(DatosAutenticacionTO session, RolProcesoTO rolProcesoTO) throws BrioBPMException {
		return process(session, rolProcesoTO, "MANTENIMIENTO_ROLPROCESO_MODIFICAR_OK",
			"MANTENIMIENTO_ROLPROCESO_MODIFICAR_ERROR", VACIO, true);
	}

	/**
	 * Process.
	 * @param session el session.
	 * @param rolProcesoTO el rol proceso TO.
	 * @param codOk el cod ok.
	 * @param codError el cod error.
	 * @param variablesValores el variables valores.
	 * @param update el update.
	 * @return el ret msg.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	private RetMsg process(DatosAutenticacionTO session, RolProcesoTO rolProcesoTO, String codOk, String codError,
		String variablesValores, boolean update) throws BrioBPMException {
		RetMsg response = RetMsg.builder().status("ERROR").build();
		try {
			rolProcesoTO.setCveEntidad(session.getCveEntidad());
			log.debug("{}", rolProcesoTO);
			StRolProceso entity = IStRolProcesoConverter.converterDtoEntity.apply(rolProcesoTO);
			entity.setCveUsuarioUltimoCambio(session.getCveUsuario());
			entity.setFechaUltimoCambio(new Date(System.currentTimeMillis()));
			log.debug("{}", entity);
			if (update) {
				validarRolNodo(session, rolProcesoTO, codError, variablesValores, entity);
			}
			else {
				existRolProceso(session, rolProcesoTO, codError, variablesValores, entity.getId());
			}
			repository.saveAndFlush(entity);
			response.setStatus("OK");
			response.setMessage(messagesService.getMessage(session, ROL_PROCESO, codOk, VACIO));
		}
		catch (DataAccessException exData) {
			log.error(DATA_ERROR, exData.getMessage());
			throw new BrioBPMException(messagesService.getMessage(session, ROL_PROCESO, codError, variablesValores),
				exData);
		}
		return response;
	}

	/**
	 * Exist rol proceso.
	 * @param session el session.
	 * @param rolProcesoTO el rol proceso TO.
	 * @param codOk el cod ok.
	 * @param codError el cod error.
	 * @param variablesValores el variables valores.
	 * @param id el id.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	private void existRolProceso(DatosAutenticacionTO session, RolProcesoTO rolProcesoTO, String codError,
		String variablesValores, StRolProcesoPK id) throws BrioBPMException {
		try {
			repository.getOne(id);
			log.error(DATA_ERROR + " {}", "Already Exists.", id);
			throw new BrioBPMException(messagesService.getMessage(session, ROL_PROCESO, codError, variablesValores),
				new EntityExistsException(id + " Already Exists."));
		}
		catch (EntityNotFoundException exEntNotFound) {
			log.error(DATA_ERROR, exEntNotFound.getMessage());
		}
	}

	/**
	 * Validar rol nodo.
	 * @param session el session.
	 * @param rolProcesoTO el rol proceso TO.
	 * @param codError el cod error.
	 * @param variablesValores el variables valores.
	 * @param entity el entity.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	private void validarRolNodo(DatosAutenticacionTO session, RolProcesoTO rolProcesoTO, String codError,
		String variablesValores, StRolProceso entity) throws BrioBPMException {
		if ("DESACTIVADO".equalsIgnoreCase(entity.getSituacion())) {
			// TODO: Validar en ST_ROL_NODO.
		}
	}

	/** 
	 * {@inheritDoc}
	 * @see com.briomax.briobpm.business.service.catalogs.core.IRolProcesoService#getUsuariosRolProcesos(com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public List<UsuarioRol> getUsuariosRolProcesos(DatosAutenticacionTO session, String cveProceso, String version,
		String cveRol) throws BrioBPMException {
		List<UsuarioRol> listDto = new ArrayList<>();
		try {
			List<UsuarioRolProceso> listEntity =
				usuarioDAO.getUsuariosRol(session.getCveEntidad(), cveProceso, new BigDecimal(version), cveRol);
			listDto
				.addAll(listEntity.stream().map(UsuarioRolConverter.converterEntityToDTO).collect(Collectors.toList()));
		}
		catch (DataAccessException exData) {
			log.error("###DATA-ERROR### : {}  ", exData.getMessage());
			throw new BrioBPMException(messagesService.getMessage(session, "USUARIO_ROL_PROCESO",
				"MANTENIMIENTO_ROLPROCESOUSUARIOS_CONSULTAR_ERROR", ""), exData);
		}
		return listDto;
	}

	/** 
	 * {@inheritDoc}
	 * @see com.briomax.briobpm.business.service.catalogs.core.IRolProcesoService#asignarUsariosRol(com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO, com.briomax.briobpm.transferobjects.catalogs.UsuariosRol)
	 */
	@Override
	public RetMsg asignarUsariosRol(DatosAutenticacionTO session, UsuariosRol to) throws BrioBPMException {
		to.setCveEntidad(session.getCveEntidad());
		if (!to.getCveUsuarios().isEmpty()) {
			List<com.briomax.briobpm.persistence.entity.UsuarioRol> listEntity = new ArrayList<>();
			for (String cveUser : to.getCveUsuarios()) {
				listEntity.add(com.briomax.briobpm.persistence.entity.UsuarioRol.builder()
					.cveEntidadRol(session.getCveEntidad())
					.id(UsuarioRolPK.builder()
						.cveEntidad(to.getCveEntidad())
						.cveUsuario(cveUser)
						.cveProceso(to.getCveProceso())
						.version(new BigDecimal(to.getVersion()))
						.cveRol(to.getCveRol())
						.build())
					.build());
			}
			com.briomax.briobpm.persistence.entity.UsuarioRol delete = listEntity.get(0);
			log.trace("{}", delete);
			Integer count = usuarioDAO.deleteUsuariosRol(delete.getId().getCveEntidad(), delete.getId().getCveProceso(),
				delete.getId().getVersion(), delete.getId().getCveRol());
			log.trace("REGISTROS ELIMINADOS: {}", count);
			repositoryUsarioRol.saveAll(listEntity);
			return new RetMsg("OK", "");
		}
		return new RetMsg("ERROR", "");
	}

}
