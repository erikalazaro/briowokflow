/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.business.helpers;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import com.briomax.briobpm.business.helpers.base.IIdiomaHelper;
import com.briomax.briobpm.business.helpers.base.IInitHelper;
import com.briomax.briobpm.common.core.DAORet;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.persistence.dao.base.IIdiomasDAO;
import com.briomax.briobpm.persistence.entity.Moneda;
import com.briomax.briobpm.persistence.entity.namedquery.LeeIdiomas;
import com.briomax.briobpm.persistence.repository.IMonedaRepository;
import com.briomax.briobpm.transferobjects.ComboBoxTO;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * El objetivo de la Class InitHelper.java es ...
 * @author Rigoberto Olvera C.
 * @version 1.0 Fecha de creacion 30/01/2020 12:19:46 AM Modificaciones:
 * @since JDK 1.8
 */
@Service
@Transactional
public class InitHelper implements IInitHelper {

	/** El atributo o variable idiomas DAO. */
	@Autowired
	private IIdiomasDAO idiomasDAO;

	/** El atributo o variable moneda repository. */
	@Autowired
	private IMonedaRepository monedaRepository;
	
	@Autowired
	private IIdiomaHelper idiomaHelper;

	/** Crear una nueva instancia del objeto inits the helper. */
	public InitHelper() {
	}

	/** 
	 * {@inheritDoc}
	 * @see com.briomax.briobpm.business.helpers.base.IInitHelper#getIdiomas(com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO)
	 */
	@Override
	public DAORet<List<ComboBoxTO>, RetMsg> getIdiomas(DatosAutenticacionTO session) throws BrioBPMException {
		DAORet<List<LeeIdiomas>, RetMsg> metaData=idiomaHelper.leeIdioma(session);
		//		DAORet<List<LeeIdiomas>, RetMsg> metaData = idiomasDAO.obtenerIdiomas(session.getCveUsuario(),
//			session.getCveEntidad(), session.getCveLocalidad(), session.getCveIdioma());
		List<ComboBoxTO> list = new ArrayList<ComboBoxTO>();
		DAORet<List<ComboBoxTO>, RetMsg> response = null;
		if (metaData.getMeta().getStatus().equalsIgnoreCase("OK")) {
			metaData.getContent().forEach((entity) -> {
				list.add(ComboBoxTO.builder().id(entity.getCveIdioma()).descripcion(entity.getDescripcion()).build());
			});
		}
		response = new DAORet<List<ComboBoxTO>, RetMsg>(list, metaData.getMeta());
		return response;
	}

	/** 
	 * {@inheritDoc}
	 * @see com.briomax.briobpm.business.helpers.base.IInitHelper#getMonedas()
	 */
	@Override
	public DAORet<List<ComboBoxTO>, RetMsg> getMonedas() throws BrioBPMException {
		List<Moneda> data = monedaRepository.findAll(Sort.by("descripcion").ascending());
		List<ComboBoxTO> list = new ArrayList<>();
		data.forEach(entity -> 
			list.add(ComboBoxTO.builder().id(entity.getCveMoneda()).descripcion(entity.getDescripcion()).build()));
		return new DAORet<>(list, new RetMsg("OK", ""));
	}

}
