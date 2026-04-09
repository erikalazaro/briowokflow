package com.briomax.briobpm.business.convertes;


import java.text.SimpleDateFormat;
import java.util.function.Function;

import com.briomax.briobpm.persistence.entity.CrRegistroObra;
import com.briomax.briobpm.transferobjects.catalogs.CrRegistroObraTO;

public interface ICrRegistroObraConverter {

	/** Converter Entity to DTO. */
	Function<CrRegistroObra, CrRegistroObraTO> converterEntityToDTO = (entity) -> {
		CrRegistroObraTO to = null;
		if (entity != null) {
			to = CrRegistroObraTO.builder()
					.cveEntidad(entity.getId().getCveEntidad())
					.cveProceso(entity.getId().getCveProceso())					
					.rfcContratista(entity.getId().getRfcContratista())
					.numContrato(entity.getId().getNumContrato())
					.nombreDocumento(entity.getNombreDocumento())
					.razonSocial(entity.getRazonSocial())
					.registroPatronal(entity.getRegistroPatronal())
					.numeroRepse(entity.getNumeroRepse())
					.registroObra(entity.getRegistroObra())
					.montoObra(entity.getMontoObra())
					.ubicacionObra(entity.getUbicacionObra())
					.situacionCarga(entity.getSituacionCarga())
					.boton("boton")
					.build();
			String fechaFormateada;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

			fechaFormateada = sdf.format(entity.getId().getFechaCarga());
			to.setFechaCarga(fechaFormateada);
			
			
			SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
			
			if(entity.getFechaRegistro() != null) {
				fechaFormateada = sdf2.format(entity.getFechaRegistro());
				to.setFechaRegistro(fechaFormateada);
			} 
			
			
		}
		return to;
	};

	
}

