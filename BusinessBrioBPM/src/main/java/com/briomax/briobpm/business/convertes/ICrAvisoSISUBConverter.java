package com.briomax.briobpm.business.convertes;


import java.text.SimpleDateFormat;
import java.util.function.Function;

import com.briomax.briobpm.persistence.entity.CrAvisoSISUB;
import com.briomax.briobpm.transferobjects.catalogs.CrAvisoSISUBTO;

public interface ICrAvisoSISUBConverter {

	/** Converter Entity to DTO. */
	Function<CrAvisoSISUB, CrAvisoSISUBTO> converterEntityToDTO = (entity) -> {
		CrAvisoSISUBTO to = null;
		if (entity != null) {
			to = CrAvisoSISUBTO.builder()
					.cveEntidad(entity.getId().getCveEntidad())
					.cveProceso(entity.getId().getCveProceso())					
					.rfcContratista(entity.getId().getRfcContratista())
					.numContrato(entity.getId().getNumContrato())			
					.nombreDocumento(entity.getNombreDocumento())
					.rfc(entity.getRfc())
					.razonSocial(entity.getRazonSocial())
					.cuatrimestre(entity.getCuatrimestre())
					.numeroRepse(entity.getNumeroRepse())
					.situacionCarga(entity.getSituacionCarga())
					.boton("boton")
					.build();
			String fechaFormateada;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

			fechaFormateada = sdf.format(entity.getId().getFechaCarga());
			to.setFechaCarga(fechaFormateada);
			
			SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
			
			if(entity.getFechaDocumento() != null) {
				fechaFormateada = sdf2.format(entity.getFechaDocumento());
				to.setFechaDocumento(fechaFormateada);
			} 
			
		}return to;
		
	};
		
	
}

