/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.business.convertes;

import java.util.function.Function;

import com.briomax.briobpm.persistence.entity.namedquery.UsuarioRolProceso;
import com.briomax.briobpm.transferobjects.catalogs.UsuarioRol;

/**
 * El objetivo de la Interface UsuarioRolConverter.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Jun 14, 2021 7:37:22 PM Modificaciones:
 * @since JDK 1.8
 */
public interface UsuarioRolConverter {

	/** Converter Entity to DTO. */
	Function<UsuarioRolProceso, UsuarioRol> converterEntityToDTO = (entity) -> {
		UsuarioRol to = null;
		if (entity != null) {
			to = UsuarioRol.builder()
					.seleccionado(1 == entity.getSeleccionado())
					.cveUsuario(entity.getCveUsuario())
					.nomUsuario(entity.getNomUsuario())
					.desLocalidad(entity.getDesLocalidad())
					.build();
		}
		return to;
	};

}
