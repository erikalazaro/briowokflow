package com.briomax.briobpm.business.convertes;


import java.text.SimpleDateFormat;
import java.util.function.Function;

import com.briomax.briobpm.persistence.entity.CrPagoBancario;
import com.briomax.briobpm.transferobjects.catalogs.CrPagoBancarioTO;

public interface ICrPagoBancarioConverter {

	/** Converter Entity to DTO. */
	Function<CrPagoBancario, CrPagoBancarioTO> converterEntityToDTO = (entity) -> {
		CrPagoBancarioTO to = null;
		if (entity != null) {
			to = CrPagoBancarioTO.builder()
					.cveEntidad(entity.getId().getCveEntidad())
					.cveProceso(entity.getId().getCveProceso())					
					.rfcContratista(entity.getId().getRfcContratista())
					.numContrato(entity.getId().getNumContrato())			
					.nombreDocumento(entity.getNombreDocumento())
					.lineaCaptura(entity.getLineaCaptura())
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
			
		}return to;
		
	};
		
	
}

