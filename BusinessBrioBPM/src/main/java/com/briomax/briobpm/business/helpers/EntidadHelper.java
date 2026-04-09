/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.business.helpers;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.briomax.briobpm.business.convertes.IEntidadConverter;
import com.briomax.briobpm.business.helpers.base.IDatosLocalidadHelper;
import com.briomax.briobpm.business.helpers.base.IEntidadHelper;
import com.briomax.briobpm.common.core.DAORet;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.persistence.dao.base.IEntidadDAO;
import com.briomax.briobpm.persistence.entity.Entidad;
import com.briomax.briobpm.persistence.entity.LocalidadEntidad;
import com.briomax.briobpm.persistence.entity.LocalidadEntidadPK;
import com.briomax.briobpm.persistence.entity.namedquery.LeeEntidades;
import com.briomax.briobpm.persistence.entity.namedquery.Localidad;
import com.briomax.briobpm.persistence.repository.IEntidadRepository;
import com.briomax.briobpm.persistence.repository.ILocalidadRepository;
import com.briomax.briobpm.transferobjects.ComboBoxTO;
import com.briomax.briobpm.transferobjects.LocalidadTO;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Class EntidadHelper.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Feb 26, 2020 4:12:01 PM Modificaciones:
 * @since JDK 1.8
 */
@Slf4j
@Service
@Transactional
public class EntidadHelper implements IEntidadHelper {

	/** La Constante DATA_ERROR. */
	private static final String DATA_ERROR = "###DATA-ERROR### : {} ";

	/** El atributo o variable entidadesdao. */
	@Autowired
	private IEntidadDAO entidadesdao;

	/** El atributo o variable localidad repository. */
	@Autowired
	private ILocalidadRepository localidadRepository;
	
	@Autowired
	private IDatosLocalidadHelper iDatosLocalidadHelper;
	
	@Autowired
	private IEntidadRepository entidadRepository;

	/**
	 * Crear una nueva instancia del objeto entidad helper.
	 */
	public EntidadHelper() {
	}

	/** 
	 * {@inheritDoc}
	 * @see com.briomax.briobpm.business.helpers.base.IEntidadHelper#getEntidades(com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO)
	 */
	@Override
	public DAORet<List<ComboBoxTO>, RetMsg> getEntidades(DatosAutenticacionTO session) throws BrioBPMException {
		Sort sort = Sort.by(Sort.Direction.ASC, "descripcion");
		List<LeeEntidades> leeEntidades= new ArrayList<LeeEntidades>();
		List<Entidad> entidades = entidadRepository.findAll(sort);
		RetMsg msg = new RetMsg();
		msg.setStatus("FAIL" );
		msg.setMessage("NO_EXISTEN_ENTIDADES");
		/*for (Entidad ent:entidades) {
			LeeEntidades leeEntidad = new LeeEntidades();
			leeEntidad.setCveEntidad(ent.getCveEntidad());
			leeEntidad.setDescripcion(ent.getDescripcion());
			leeEntidades.add(leeEntidad);
			msg.setStatus("OK" );
			msg.setMessage("");
		}*/
		

		
		/*DAORet<List<LeeEntidades>, RetMsg> metaData = entidadesdao.obtenerEntidades(session.getCveUsuario(),
			session.getCveEntidad(), session.getCveLocalidad(), session.getCveIdioma());*/
		List<ComboBoxTO> list = new ArrayList<>();
		DAORet<List<ComboBoxTO>, RetMsg> response = null;
		
		entidades.forEach((entity) -> {
				list.add(ComboBoxTO.builder().id(entity.getCveEntidad()).descripcion(entity.getDescripcion()).build());
				msg.setStatus("OK" );
				msg.setMessage("");
			});
		
		response = new DAORet<List<ComboBoxTO>, RetMsg>(list, msg);
		return response;
	}

	/** 
	 * {@inheritDoc}
	 * @see com.briomax.briobpm.business.helpers.base.IEntidadHelper#getDatosLocalidad(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public DAORet<LocalidadTO, RetMsg> getDatosLocalidad(String cveUsuario, String cveEntidad, String cveLocalidad,
		String cveIdioma) throws BrioBPMException {		
		//DAORet<Localidad, RetMsg> ret = entidadesdao.leeDatosLocalidad(cveUsuario, cveEntidad, cveLocalidad, cveIdioma);
				DatosAutenticacionTO session = DatosAutenticacionTO.builder()
												.cveUsuario(cveUsuario)
												.cveEntidad(cveEntidad)
												.cveLocalidad(cveLocalidad)
												.cveIdioma(cveIdioma)
												.build();
				DAORet<Localidad, RetMsg> ret = iDatosLocalidadHelper.leeDatoLocalidad(session);
		LocalidadTO localidad = null;
		if (ret.getMeta().getStatus().toUpperCase().equalsIgnoreCase("OK")) {
			localidad = IEntidadConverter.converterLocalidad.apply(ret.getContent());
		}
		return new DAORet<LocalidadTO, RetMsg>(localidad, ret.getMeta());
	}

	/** 
	 * {@inheritDoc}
	 * @see com.briomax.briobpm.business.helpers.base.IEntidadHelper#getLocalidades(java.lang.String)
	 */
	@Override
	public List<ComboBoxTO> getLocalidades(String cveEntidad) throws BrioBPMException {
		List<ComboBoxTO> listDto = new ArrayList<ComboBoxTO>();
		try {
			LocalidadEntidad localidadEntidad = LocalidadEntidad.builder()
					.id(LocalidadEntidadPK.builder()
						.cveEntidad(cveEntidad)
						.build())
					.build();
			List<LocalidadEntidad> listEntity = localidadRepository.findAll(Example.of(localidadEntidad),
				Sort.by(Sort.Direction.ASC, "descripcion"));
			for (LocalidadEntidad entity : listEntity) {
				listDto.add(ComboBoxTO.builder()
					.id(entity.getId().getCveLocalidad())
					.descripcion(entity.getDescripcion())
					.build());
			}
		}
		catch (DataAccessException exData) {
			log.error(DATA_ERROR, exData.getMessage());
			throw new BrioBPMException(true, 90001, exData.getMessage(), exData);
		}
		return listDto;
	}

	/** 
	 * {@inheritDoc}
	 * @see com.briomax.briobpm.business.helpers.base.IEntidadHelper#getHora(com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO)
	 */
	@Override
	public DAORet<String, RetMsg> getHora(DatosAutenticacionTO userSession) throws BrioBPMException {
		DAORet<String, RetMsg> ret = null;
		DAORet<Localidad, RetMsg> loc = iDatosLocalidadHelper.leeDatoLocalidad(userSession);
		if("OK".equalsIgnoreCase(loc.getMeta().getStatus())) {
			//String formattedOffset = String.format("%s%02d:00", loc.getContent().getHusohorario()? "-" : "+", Math.abs(Integer.parseInt(loc.getContent().getHusohorario())));
			String formatHuso = String.format("%s%02d:00", loc.getContent().getHusohorario().startsWith("-") ? "-" : "+", Math.abs(Integer.parseInt(loc.getContent().getHusohorario())));
			//log.trace("########################-->     loc.getContent..getHusohorario() {}", String.format("%+03d:00", loc.getContent().getHusohorario()));			 
			ZoneId zoneId = ZoneId.of(formatHuso);
			//ZoneId zoneId = ZoneId.of("-05:00");
			ZonedDateTime zoneDateTime = ZonedDateTime.now(zoneId);
			String zoneDateTimeFormat = zoneDateTime.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
			log.debug("\t Husohorario:{} \t ZonedDateTime:{}", loc.getContent().getHusohorario(), zoneDateTimeFormat);
			ret = new DAORet<>(zoneDateTimeFormat, loc.getMeta());
		}
		else {
			ret = new DAORet<>(null, loc.getMeta());
		}
		return ret;
	}

}
