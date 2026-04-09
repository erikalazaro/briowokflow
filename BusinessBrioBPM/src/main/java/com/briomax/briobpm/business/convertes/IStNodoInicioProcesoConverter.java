package com.briomax.briobpm.business.convertes;


import java.util.function.Function;
import com.briomax.briobpm.persistence.entity.StNodoInicioProceso;
import com.briomax.briobpm.persistence.entity.StNodoInicioProcesoPK;
import com.briomax.briobpm.transferobjects.NodoInicioProcesoTO;


public interface IStNodoInicioProcesoConverter {
	
	/** Converter Entity to DTO. */
	Function<StNodoInicioProceso, NodoInicioProcesoTO> converterEntityToDTO = (entity) -> {
		NodoInicioProcesoTO to = null;
		if (entity != null) {
			to = NodoInicioProcesoTO.builder()
					.cveEntidad(entity.getId().getCveEntidad())
					.cveProceso(entity.getId().getCveProceso())
					.version(entity.getId().getVersion())
					.cveNodo(entity.getId().getCveNodo())
					.idNodo(entity.getId().getIdNodo())
					.variablesReferenciaRec(entity.getVariablesReferenciaRec())
					.build();
		}
		return to;
	};

	/** Converter DTO to Entity. */
	Function<NodoInicioProcesoTO, StNodoInicioProceso> converterDtoToEntity = (to) -> {
		StNodoInicioProceso entity = null;
		if (to != null) {
			entity = StNodoInicioProceso.builder()
					.id(StNodoInicioProcesoPK.builder()
						.cveEntidad(to.getCveEntidad())
						.cveProceso(to.getCveProceso())
						.version(to.getVersion())
						.idNodo(to.getIdNodo())						
						.build())
					.variablesReferenciaRec(to.getVariablesReferenciaRec())
					.build();
		}
		return entity;
	};


}
