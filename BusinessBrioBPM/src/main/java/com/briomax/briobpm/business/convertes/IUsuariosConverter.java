/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.business.convertes;

import java.util.function.Function;

import com.briomax.briobpm.persistence.entity.Entidad;
import com.briomax.briobpm.persistence.entity.LocalidadEntidad;
import com.briomax.briobpm.persistence.entity.LocalidadEntidadPK;
import com.briomax.briobpm.persistence.entity.Usuario;
import com.briomax.briobpm.persistence.entity.UsuarioPK;
import com.briomax.briobpm.transferobjects.catalogs.UsuarioTO;

/**
 * El objetivo de la Interface IUsuariosConverter.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Jul 27, 2020 3:11:07 PM Modificaciones:
 * @since JDK 1.8
 */
public interface IUsuariosConverter {

	/** Converter Entity to DTO. */
	Function<Usuario, UsuarioTO> converterEntityToDTO = (entity) -> {
		UsuarioTO to = null;
		if (entity != null) {
			to = UsuarioTO.builder()
					.cveEntidad(entity.getId().getCveEntidad())
					.desEntidad(entity.getEntidad().getDescripcion())
					.cveUsuario(entity.getId().getCveUsuario())
					.password(entity.getPassword())
					.fecPassword(entity.getFechaPassword())
					.nombre(entity.getNombre())
					.tipo(entity.getTipo())
					.fotografia(entity.getFotografia())
					.cveLocalidad(entity.getLocalidadEntidad().getId().getCveLocalidad())
					.desLocalidad(entity.getLocalidadEntidad().getDescripcion())
					.situacion(entity.getSituacion())
					.email(entity.getCorreoElectronico())
					.build();
			
			if (entity.getListOfUsuarioFacultad().size() > 0) {
				to.setCveFacultad(entity.getListOfUsuarioFacultad().get(0).getId().getCveFacultad());
			}
		}
		return to;
	};

	/** Converter DTO to Entity. */
	Function<UsuarioTO, Usuario> converterDtoEntity = (to) -> {
		Usuario entity = null;
		if (to != null) {
			entity = Usuario.builder()
					.id(UsuarioPK.builder()
						.cveEntidad(to.getCveEntidad())
						.cveUsuario(to.getCveUsuario())
						.build())
					.password(to.getPassword())
					.fechaPassword(to.getFecPassword())
					.nombre(to.getNombre())
					.tipo(to.getTipo())
					.fotografia(to.getFotografia())
					.situacion(to.getSituacion())
					.correoElectronico(to.getEmail())
					.entidad(Entidad.builder()
						.cveEntidad(to.getCveEntidad())
						.build())
					.localidadEntidad(LocalidadEntidad.builder()
						.id(LocalidadEntidadPK.builder()
							.cveEntidad(to.getCveEntidad())
							.cveLocalidad(to.getCveLocalidad())
							.build())
						.build())
					.build();
		}
		return entity;
	};

}
