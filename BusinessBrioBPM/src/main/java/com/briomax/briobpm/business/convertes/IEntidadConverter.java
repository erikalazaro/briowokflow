/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.business.convertes;

import java.util.function.Function;

import com.briomax.briobpm.persistence.entity.LocalidadEntidad;
import com.briomax.briobpm.persistence.entity.namedquery.Localidad;
import com.briomax.briobpm.transferobjects.LocalidadTO;

public interface IEntidadConverter {

	Function<Localidad, LocalidadTO> converterLocalidad = (entity) -> {
		LocalidadTO to = null;
		if (entity != null) {
			to = LocalidadTO.builder()
					.clave(entity.getClave())
					.tipo(entity.getTipo())
					.nombre(entity.getNombre())
					.direccion(entity.getDireccion())
					.telefono(entity.getTelefono())
					.email(entity.getEmail())
					.moneda(entity.getMoneda())
					.husohorario(entity.getHusohorario())
					.fecha(entity.getFecha())
					.hora(entity.getHora())
					.build();
		}
		return to;
	};

	/** El atributo o variable converter localidad entidad. */
	Function<LocalidadEntidad, LocalidadTO> converterLocalidadEntidad = (entity) -> {
		LocalidadTO to = null;
		if (entity != null) {
			to = LocalidadTO.builder()
					.clave(entity.getId().getCveLocalidad())
					.tipo(entity.getTipo())
					.nombre(entity.getDescripcion())
					.direccion(entity.getDelegacionMunicipio())
					.telefono(entity.getTelefono())
					.email(entity.getCorreoElectronico())
					.moneda(entity.getMoneda().getCveMoneda())
					.husohorario(entity.getHusoHorario().getCveHusoHorario())
					.build();
		}
		return to;
	};
}
