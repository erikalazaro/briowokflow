package com.briomax.briobpm.business.convertes;


import java.text.SimpleDateFormat;
import java.util.function.Function;

import com.briomax.briobpm.persistence.entity.namedquery.DatoTrabajador;
import com.briomax.briobpm.transferobjects.TrabajadoresTO;
import com.briomax.briobpm.transferobjects.in.CrConsultaRepseTO;

public interface ITrabajadorConverter {

	/** Converter Entity to DTO. */
	Function<DatoTrabajador, TrabajadoresTO> converterEntityToDTO = (entity) -> {
		TrabajadoresTO to = null;
		if (entity != null) {
			to = TrabajadoresTO.builder()
					.rfc(entity.getId().getRfc())					
					.numContrato(entity.getId().getNumContrato())
					//.curp(entity.getId().getCurp())
					//.instancia(entity.getInstancia())
					//.ocurrencia(entity.getOcurrencia())
					//.nombreTrabajador(entity.getNombreTrabajador())
					//.seguroSocial(entity.getSeguroSocial())
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

