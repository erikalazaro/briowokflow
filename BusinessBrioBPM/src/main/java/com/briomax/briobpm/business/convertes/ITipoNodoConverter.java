/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.business.convertes;

import java.util.function.Function;

import com.briomax.briobpm.persistence.entity.TipoNodo;
import com.briomax.briobpm.transferobjects.catalogs.TipoNodoTO;

/**
 * El objetivo de la Interface ITipoNodoConverter.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Jun 26, 2020 11:57:38 AM Modificaciones:
 * @since JDK 1.8
 */
public interface ITipoNodoConverter {

	/** Converter Entity to DTO. */
	Function<TipoNodo, TipoNodoTO> converterEntityToDTO = (entity) -> {
		TipoNodoTO to = null;
		if (entity != null) {
			to = TipoNodoTO.builder()
					.cveNodo(entity.getCveNodo())
					.descripcion(entity.getDescripcion())
					.tipoNodo(entity.getTipoNodo())
					.situacion(entity.getSituacion())
					.build();
		}
		return to;
	};

	/** Converter DTO to Entity. */
	Function<TipoNodoTO, TipoNodo> converterDtoToEntity = (to) -> {
		TipoNodo entity = null;
		if (to != null) {
			entity = TipoNodo.builder()
					.cveNodo(to.getCveNodo())
					.descripcion(to.getDescripcion())
					.tipoNodo(to.getTipoNodo())
					.situacion(to.getSituacion())
					.build();
		}
		return entity;
	};

}
