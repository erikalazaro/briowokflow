/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.business.helpers;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briomax.briobpm.business.convertes.IUsuarioConverter;
import com.briomax.briobpm.business.helpers.base.IUsuariosHelper;
import com.briomax.briobpm.business.service.catalogs.core.IMessagesService;
import com.briomax.briobpm.common.core.Constants;
import com.briomax.briobpm.common.core.DAORet;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.persistence.dao.base.IUsuarioDAO;
import com.briomax.briobpm.persistence.entity.Entidad;
import com.briomax.briobpm.persistence.entity.HusoHorario;
import com.briomax.briobpm.persistence.entity.Idioma;
import com.briomax.briobpm.persistence.entity.LocalidadEntidad;
import com.briomax.briobpm.persistence.entity.Moneda;
import com.briomax.briobpm.persistence.entity.Usuario;
import com.briomax.briobpm.persistence.entity.UsuarioPK;
import com.briomax.briobpm.persistence.entity.namedquery.DatosUsuario;
import com.briomax.briobpm.persistence.repository.IUsuarioRepository;
import com.briomax.briobpm.transferobjects.DatosAdicionalesUsuarioTO;
import com.briomax.briobpm.transferobjects.DatosGeneralesUsuarioTO;
import com.briomax.briobpm.transferobjects.DatosUsuarioTO;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Class UsuariosHelper.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Feb 25, 2020 6:59:06 PM Modificaciones:
 * @since JDK 1.8
 */
@Service
@Transactional
@Slf4j
public class UsuariosHelper implements IUsuariosHelper {

	/** El atributo o variable usuario dao. */
	@Autowired
	private IUsuarioDAO usuarioDao;
	
	/** El atributo o variable messages service. */
	@Autowired
	private IMessagesService messagesService;

	/** El atributo o variable usuario dao. */
	@Autowired
	private IUsuarioRepository usuarioRepository;
	
	/** Crear una nueva instancia del objeto usuarios helper. */
	public UsuariosHelper() {
	}

	/** 
	 * {@inheritDoc}
	 * @see com.briomax.briobpm.business.helpers.base.IUsuariosHelper#authenticate(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional
	public DAORet<DatosUsuarioTO, RetMsg> authenticate(String cveEntidad, String cveLocalidad, String cveUsuario,
		String password, String cveIdioma) throws BrioBPMException {
		log.info("\tValidar Usuario >>> Entidad:{} , Localidad:{} , Usuario:{} , Idioma:{}", cveEntidad, cveLocalidad,
			cveUsuario, cveIdioma);
		DatosUsuarioTO user = null;
		int count =  usuarioRepository.usrExiste(cveEntidad, cveUsuario, password, cveLocalidad);
		RetMsg smg = RetMsg.builder().status(Constants.OK)
				.message("")
				.build();
		
		if (count > 0) {
			
			List<Usuario> result = usuarioRepository.getUrser(cveEntidad, cveUsuario, password, cveLocalidad);
			
			if (result.size() > 0) {
				Usuario toUsuario = result.get(0);
				Entidad enti = toUsuario.getEntidad();
				LocalidadEntidad locEnt = toUsuario.getLocalidadEntidad();
				Moneda moneda = locEnt.getMoneda();
				Idioma idioma = locEnt.getIdioma();
				HusoHorario husoHorario = locEnt.getHusoHorario();
						
				DatosGeneralesUsuarioTO generales = DatosGeneralesUsuarioTO.builder()
						.clave(cveUsuario)
						.cveEntidad(enti.getCveEntidad())
						.desEntidad(enti.getDescripcion())
						.cveLocalidad(locEnt.getId().getCveLocalidad())
						.desLocalidad(locEnt.getDescripcion())
						.cveIdioma(idioma.getCveIdioma())
						.desIdioma(idioma.getDescripcion())
						.cveMoneda(moneda.getCveMoneda())
						.desMoneda(moneda.getDescripcion())
						.nombre(toUsuario.getNombre())
						.cveHusoHorario(husoHorario.getCveHusoHorario())
						.fotografia(toUsuario.getFotografia())
						.build();
				user = DatosUsuarioTO.builder()
						.generales(generales)
						.adicionales(DatosAdicionalesUsuarioTO.builder().clave(cveUsuario).build())
						.datosAutenticacion(DatosAutenticacionTO.builder()
							.cveEntidad(cveEntidad)
							.cveLocalidad(cveLocalidad)
							.cveIdioma(cveIdioma)
							.cveMoneda(moneda.getCveMoneda())
							.build())
						.build();				
			} else {
				String mensaje = messagesService.getMessage("USUARIO_INCORRECTO", cveIdioma);
				smg = RetMsg.builder().status(Constants.ERROR)
						.message(mensaje)
						.build();
			}

			
		} else {
			
			//Si tiene otra entidada
			List<Usuario> result = usuarioRepository.listUsuario(cveUsuario, password);
			
			if (result.size() > 0) {
				Usuario toUsuario = result.get(0);
				Entidad enti = toUsuario.getEntidad();
				LocalidadEntidad locEnt = toUsuario.getLocalidadEntidad();
				Moneda moneda = locEnt.getMoneda();
				Idioma idioma = locEnt.getIdioma();
				HusoHorario husoHorario = locEnt.getHusoHorario();
						
				DatosGeneralesUsuarioTO generales = DatosGeneralesUsuarioTO.builder()
						.clave(cveUsuario)
						.cveEntidad(enti.getCveEntidad())
						.desEntidad(enti.getDescripcion())
						.cveLocalidad(locEnt.getId().getCveLocalidad())
						.desLocalidad(locEnt.getDescripcion())
						.cveIdioma(idioma.getCveIdioma())
						.desIdioma(idioma.getDescripcion())
						.cveMoneda(moneda.getCveMoneda())
						.desMoneda(moneda.getDescripcion())
						.nombre(toUsuario.getNombre())
						.cveHusoHorario(husoHorario.getCveHusoHorario())
						.fotografia(toUsuario.getFotografia())
						.build();
				user = DatosUsuarioTO.builder()
						.generales(generales)
						.adicionales(DatosAdicionalesUsuarioTO.builder().clave(cveUsuario).build())
						.datosAutenticacion(DatosAutenticacionTO.builder()
							.cveEntidad(enti.getCveEntidad())
							.cveLocalidad(locEnt.getId().getCveLocalidad())
							.cveIdioma(idioma.getCveIdioma())
							.cveMoneda(moneda.getCveMoneda())
							.build())
						.build();				
				
			} else {
				String mensaje = messagesService.getMessage("USUARIO_INCORRECTO", cveIdioma);
				smg = RetMsg.builder().status(Constants.ERROR)
						.message(mensaje)
						.build();
			}
		}
		
		log.debug("Usuario: {}", user);
		return new DAORet<DatosUsuarioTO, RetMsg>(user, smg);
	}

}
