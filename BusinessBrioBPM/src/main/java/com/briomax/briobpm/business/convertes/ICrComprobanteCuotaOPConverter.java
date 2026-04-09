package com.briomax.briobpm.business.convertes;


import java.text.SimpleDateFormat;
import java.util.function.Function;

import com.briomax.briobpm.persistence.entity.CrComprobanteCuotasOP;
import com.briomax.briobpm.transferobjects.catalogs.CrComprobanteCuotaOPTO;

public interface ICrComprobanteCuotaOPConverter {

	/** Converter Entity to DTO. */
	Function<CrComprobanteCuotasOP, CrComprobanteCuotaOPTO> converterEntityToDTO = (entity) -> {
		CrComprobanteCuotaOPTO to = null;
		if (entity != null) {
			to = CrComprobanteCuotaOPTO.builder()
					.cveEntidad(entity.getId().getCveEntidad())
					.cveProceso(entity.getId().getCveProceso())					
					.rfcContratista(entity.getId().getRfcContratista())
					.numContrato(entity.getId().getNumContrato())
					.nombreDocumento(entity.getNombreDocumento())
					.razonSocial(entity.getRazonSocial())
					.registroPatronal(entity.getRegistroPatronal())
					.periodoPagoinfonavit(entity.getPeriodoPagoinfonavit())
					.periodoPagoimss(entity.getPeriodoPagoimss())
					.situacionCarga(entity.getSituacionCarga())
					.boton("boton")
					.build();
			String fechaFormateada;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

			fechaFormateada = sdf.format(entity.getId().getFechaCarga());
			to.setFechaCarga(fechaFormateada);
			
			SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
			
			if(entity.getFechaPago() != null) {
				fechaFormateada = sdf2.format(entity.getFechaPago());
				to.setFechaPago(fechaFormateada);
			} 
			
			
		}
		return to;
	};

	
}

