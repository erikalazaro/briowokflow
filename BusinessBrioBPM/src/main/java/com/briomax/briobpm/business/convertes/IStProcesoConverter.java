/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.business.convertes;

import java.math.BigDecimal;
import java.util.function.Function;

import com.briomax.briobpm.persistence.entity.StProceso;
import com.briomax.briobpm.persistence.entity.StProcesoPK;
import com.briomax.briobpm.transferobjects.catalogs.ProcesosTO;

/**
 * El objetivo de la Interface IStProcesoConverter.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Jun 26, 2020 11:59:15 AM Modificaciones:
 * @since JDK 1.8
 */
public interface IStProcesoConverter {

	/** Converter Entity to DTO. */
	Function<StProceso, ProcesosTO> converterEntityToDTO = (entity) -> {
		ProcesosTO to = null;
		if (entity != null) {
			to = ProcesosTO.builder()
					.cveProceso(entity.getId().getCveProceso())
					.version(String.valueOf(entity.getId().getVersion()))
					.descripcion(entity.getDescripcion())
					.situacion(entity.getSituacion())
					.instanciasDia(entity.getInstanciasDia())
					.instanciasSemana(entity.getInstanciasSemana())
					.idNodoInicio(entity.getIdNodoInicio())
					.idNodoFin(entity.getIdNodoFin())
					.build();
			to.setTipoNodoInicio(ITipoNodoConverter.converterEntityToDTO.apply(entity.getCveNodoInicio()));
			to.setTipoNodoFin(ITipoNodoConverter.converterEntityToDTO.apply(entity.getCveNodoFin()));
		}
		return to;
	};

	/** Converter DTO to Entity. */
	Function<ProcesosTO, StProceso> converterDtoToEntity = (to) -> {
		StProceso entity = null;
		if (to != null) {
			entity = StProceso.builder()
					.id(StProcesoPK.builder()
						.cveProceso(to.getCveProceso())
						.version(new BigDecimal(to.getVersion()))
						.build())
					.descripcion(to.getDescripcion())
					.situacion(to.getSituacion())
					.instanciasDia(to.getInstanciasDia())
					.instanciasSemana(to.getInstanciasSemana())
					.idNodoInicio(to.getIdNodoInicio())
					.idNodoFin(to.getIdNodoFin())
					.build();
			entity.setCveNodoInicio(ITipoNodoConverter.converterDtoToEntity.apply(to.getTipoNodoInicio()));
			entity.setCveNodoFin(ITipoNodoConverter.converterDtoToEntity.apply(to.getTipoNodoFin()));
		}
		return entity;
	};

}
