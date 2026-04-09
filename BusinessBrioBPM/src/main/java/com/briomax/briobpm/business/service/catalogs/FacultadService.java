/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */


package com.briomax.briobpm.business.service.catalogs;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.briomax.briobpm.business.convertes.IUsuariosConverter;
import com.briomax.briobpm.business.service.catalogs.core.IFacultadService;
import com.briomax.briobpm.business.service.catalogs.core.IMessagesService;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.persistence.entity.Facultad;
import com.briomax.briobpm.persistence.entity.Usuario;
import com.briomax.briobpm.persistence.repository.IFacultad;
import com.briomax.briobpm.transferobjects.ComboBoxTO;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Clase/Iterfarce/Enumeracion UsarioService.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Jul 27, 2020 2:08:36 PM Modificaciones:
 * @since JDK 1.8
 */
@Service
@Transactional
@Slf4j
public class FacultadService implements IFacultadService {

	/** La Constante ROL_PROCESO. */
	private static final String USUARIO = "USUARIO";

	/** La Constante DATA_ERROR. */
	private static final String DATA_ERROR = "###DATA-ERROR### : {} ";

	/** La Constante VACIO. */
	private static final String VACIO = "";

	/** El atributo o variable repository. */
	@Autowired
	private IFacultad repository;

	/** El atributo o variable messages service. */
	@Autowired
	private IMessagesService messagesService;


	/**
	 * Crear una nueva instancia del objeto usuario service.
	 */
	public FacultadService() {
	}

	/** 
	 * {@inheritDoc}
	 * @see com.briomax.briobpm.business.service.catalogs.core.IUsuarioService#getAll(com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO)
	 */
	@Override
	public List<ComboBoxTO> getCmbFacultad(DatosAutenticacionTO session) throws BrioBPMException {
		List<ComboBoxTO> listDto = new ArrayList<ComboBoxTO>();
		try {

			List<Facultad> listEntity = repository.findAll();
			if (listEntity.size() > 0) {
				for (Facultad ite : listEntity) {
					ComboBoxTO to  = ComboBoxTO.builder()
							.id(ite.getCveFacultad())
							.descripcion(ite.getDescripcion())
							.build();
					listDto.add(to);
				}
			}

		}
		catch (DataAccessException exData) {
			log.error(DATA_ERROR, exData.getMessage());
			throw new BrioBPMException(
				messagesService.getMessage(session, USUARIO, "MANTENIMIENTO_FACULTAD_CONSULTAR_ERROR", VACIO), exData);
		}
		return listDto;
	}


}
