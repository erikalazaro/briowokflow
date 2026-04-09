package com.briomax.briobpm.business.convertes;


import java.text.SimpleDateFormat;
import java.util.function.Function;

import com.briomax.briobpm.persistence.entity.CrAvisoICSOE;
import com.briomax.briobpm.transferobjects.catalogs.CrAvisoICSOETO;

public interface ICrAvisoICSOEConverter {

	/** Converter Entity to DTO. */
	Function<CrAvisoICSOE, CrAvisoICSOETO> converterEntityToDTO = (entity) -> {
		CrAvisoICSOETO to = null;
		if (entity != null) {
			to = CrAvisoICSOETO.builder()
					.cveEntidad(entity.getId().getCveEntidad())
					.cveProceso(entity.getId().getCveProceso())					
					.rfcContratista(entity.getId().getRfcContratista())
					.numContrato(entity.getId().getNumContrato())			
					.nombreDocumento(entity.getNombreDocumento())
					.rfc(entity.getRfc())
					.año(entity.getAño())
					.cuatrimestre(entity.getCuatrimestre())
					.tipoInformativa(entity.getTipoInformativa())
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

