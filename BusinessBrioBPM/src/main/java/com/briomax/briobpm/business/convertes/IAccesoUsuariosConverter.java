/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.business.convertes;

import java.util.function.Function;

import com.briomax.briobpm.persistence.entity.CrAccesoUsuario;
import com.briomax.briobpm.persistence.entity.CrAccesoUsuarioPK;
import com.briomax.briobpm.transferobjects.repse.CrUsuarioTO;

/**
 * El objetivo de la Interface IAccesoUsuariosConverter.java es ...
 * @author Sara Ventura
 * @version 1.0 Fecha de creacion Nov 25, 2025 12:08:29 Modificaciones:
 * @since JDK 1.8
 */
public interface IAccesoUsuariosConverter {

	/** Converter Entity to DTO. */
	Function<CrAccesoUsuario, CrUsuarioTO> converterEntityToDTO = (entity) -> {
		CrUsuarioTO to = null;
		if (entity != null) {
			to = CrUsuarioTO.builder()
					.cveEntidad(entity.getId().getCveEntidad())
					.cveUsuario(entity.getId().getCveUsuario())
					.rfc(entity.getId().getRfc())
					.password(entity.getUsuario().getPassword())
					.nombre(entity.getUsuario().getNombre())
					.email(entity.getUsuario().getCorreoElectronico())
					.usuarioContratante(entity.getUsuarioContratante())
					.build();
			

		}
		return to;
	};

	/** Converter DTO to Entity. */
	Function<CrUsuarioTO, CrAccesoUsuario> converterDtoEntity = (to) -> {
		CrAccesoUsuario entity = null;
		if (to != null) {
			entity = CrAccesoUsuario.builder()
					.id(CrAccesoUsuarioPK.builder()
						.cveEntidad(to.getCveEntidad())
						.cveUsuario(to.getCveUsuario())
						.rfc(to.getRfc())
						.build())
					.usuarioContratante(to.getUsuarioContratante())
					.build();
		}
		return entity;
	};

}
