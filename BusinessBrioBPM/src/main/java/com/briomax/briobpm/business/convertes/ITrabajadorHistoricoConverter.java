package com.briomax.briobpm.business.convertes;


import java.util.function.Function;

import com.briomax.briobpm.persistence.entity.CrTrabajadorHistorico;
import com.briomax.briobpm.transferobjects.TrabajadoresTO;

public interface ITrabajadorHistoricoConverter {

	/** Converter Entity to DTO. */
	Function<CrTrabajadorHistorico, TrabajadoresTO> converterEntityToDTO = (entity) -> {
		TrabajadoresTO to = null;
		if (entity != null) {
			to = TrabajadoresTO.builder()
					.rfc(entity.getId().getRfcContratista())
					.numContrato(entity.getId().getNumContrato())
					.curp(entity.getId().getCurp())
					.nombreTrabajador(entity.getNombreTrabajador())
					.seguroSocial(entity.getSeguroSocial())
					.rfcTrabajador(entity.getRfcTrabajador())
					//.salarioBase(entity.getSalarioBase())
					.fechaInicial("")
					.fechaFinal("")
					.build();
			/*String fechaFormateada;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

			if(entity.getFechaInicial() != null) {
				fechaFormateada = sdf.format(entity.getFechaInicial());
				to.setFechaInicial(fechaFormateada);
			} 
			
			if(entity.getFechaFinal() != null) {
				fechaFormateada = sdf.format(entity.getFechaFinal());
				to.setFechaFinal(fechaFormateada);
			} */
		}
		return to;
	};

	
}

