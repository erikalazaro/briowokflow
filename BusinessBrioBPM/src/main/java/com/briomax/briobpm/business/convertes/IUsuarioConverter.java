/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */
package com.briomax.briobpm.business.convertes;

import java.util.function.Function;

import com.briomax.briobpm.persistence.entity.namedquery.DatosUsuario;
import com.briomax.briobpm.transferobjects.DatosAdicionalesUsuarioTO;
import com.briomax.briobpm.transferobjects.DatosGeneralesUsuarioTO;
import com.briomax.briobpm.transferobjects.DatosUsuarioTO;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;

/**
 * El objetivo de la Interface IUsuarioConverter.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion May 15, 2020 11:55:24 AM Modificaciones:
 * @since JDK 1.8
 */
public interface IUsuarioConverter {
	
	Function<DatosUsuario, DatosUsuarioTO> converterValidaDatosUsuario = (entity) -> {
		DatosUsuarioTO to = null;
		if (entity != null) {
			DatosGeneralesUsuarioTO generales = DatosGeneralesUsuarioTO.builder()
					//.clave(entity.getId().getCveUsuario())
					.cveEntidad(entity.getCveEntidad())
					.desEntidad(entity.getDesEntidad())
					.cveLocalidad(entity.getCveLocalidad())
					.desLocalidad(entity.getDesLocalidad())
					.cveIdioma(entity.getCveIdioma())
					.desIdioma(entity.getDesIdioma())
					.cveMoneda(entity.getCveMoneda())
					.desMoneda(entity.getDesMoneda())
					.nombre(entity.getNombre())
					//.tipo(entity.getTipo())
					//.situacion(entity.getSituacion())
					.cveHusoHorario(entity.getCveHusoHorario())
					//.desHusoHorario(entity.getLocalidadEntidad().getHusoHorario().getDescripcion())
					.fotografia(entity.getFotografia())
					.build();
			to = DatosUsuarioTO.builder()
					.generales(generales)
					.adicionales(DatosAdicionalesUsuarioTO.builder().build())
					.datosAutenticacion(DatosAutenticacionTO.builder()
						//.cveUsuario(entity.getId().getCveUsuario())
						.cveEntidad(entity.getCveEntidad())
						.cveLocalidad(entity.getCveLocalidad())
						.cveIdioma(entity.getCveIdioma())
						.cveMoneda(entity.getCveMoneda())
						.build())
					.build();
		}
		return to;
	};
	
}
