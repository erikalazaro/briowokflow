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
import com.briomax.briobpm.persistence.entity.StRolProceso;
import com.briomax.briobpm.persistence.entity.StRolProcesoPK;
import com.briomax.briobpm.transferobjects.catalogs.RolProcesoTO;

/**
 * El objetivo de la Interface IStRolProcesoConverter.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Jun 26, 2020 12:20:05 PM Modificaciones:
 * @since JDK 1.8
 */
public interface IStRolProcesoConverter {

	/** Converter Entity to DTO. */
	Function<StRolProceso, RolProcesoTO> converterEntityToDTO = (entity) -> {
		RolProcesoTO to = null;
		if (entity != null) {
			to = RolProcesoTO.builder()
					.cveEntidad(entity.getId().getCveEntidad())
					.cveProceso(entity.getId().getCveProceso())
					.version(String.valueOf(entity.getId().getVersion()))
					.cveRol(entity.getId().getCveRol())
					.descripcion(entity.getDescripcion())
					.situacion(entity.getSituacion())
					.fecUltCambio(entity.getFechaUltimoCambio())
					.build();
		}
		return to;
	};

	/** Converter DTO to Entity. */
	Function<RolProcesoTO, StRolProceso> converterDtoEntity = (to) -> {
		StRolProceso entity = null;
		if (to != null) {
			entity = StRolProceso.builder()
					.id(StRolProcesoPK.builder()
						.cveEntidad(to.getCveEntidad())
						.cveProceso(to.getCveProceso())
						.version(new BigDecimal(to.getVersion()))
						.cveRol(to.getCveRol())
						.build())
					.descripcion(to.getDescripcion())
					.situacion(to.getSituacion())
					.stProceso(StProceso.builder()
						.id(StProcesoPK.builder()
							.cveEntidad(to.getCveEntidad())
							.cveProceso(to.getCveProceso())
							.version(new BigDecimal(to.getVersion()))
							.build())
						.build())
					.build();
		}
		return entity;
	};

}
