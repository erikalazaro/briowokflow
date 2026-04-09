package com.briomax.briobpm.business.convertes;


import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.function.Function;

import com.briomax.briobpm.persistence.entity.CrDeclaracionProvisional;
import com.briomax.briobpm.transferobjects.catalogs.CrDeclaracionProvisionalTO;

public interface ICrDeclaracionProvicionalConverter {

	/** Converter Entity to DTO. */
	Function<CrDeclaracionProvisional, CrDeclaracionProvisionalTO> converterEntityToDTO = (entity) -> {
		CrDeclaracionProvisionalTO to = null;
		if (entity != null) {
			to = CrDeclaracionProvisionalTO.builder()
					.cveEntidad(entity.getId().getCveEntidad())
					.cveProceso(entity.getId().getCveProceso())					
					.rfcContratista(entity.getId().getRfcContratista())
					.numContrato(entity.getId().getNumContrato())			
					.tipoDeclaracion(entity.getTipoDeclaracion())
					.secuenciaDeclaracion(entity.getId().getSecuenciaDeclaracion())					
					.nombreDocumento(entity.getNombreDocumento())
					.razonSocial(entity.getRazonSocial())
					.rfc(entity.getRfc())
					.tipoDeclaracion(entity.getTipoDeclaracion())
					.periodoDeclaracion(entity.getPeriodoDeclaracion())
					.ejercicio(entity.getEjercicio())
					.lineaCaptura(entity.getLineaCaptura())
					.conceptoPago(entity.getConceptoPago())
					.situacionCarga(entity.getSituacionCarga())
					.boton("boton")
					.build();
			String fechaFormateada;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

			fechaFormateada = sdf.format(entity.getId().getFechaCarga());
			to.setFechaCarga(fechaFormateada);
			
			if(entity.getFechaHoraPresentacion() != null) {
				fechaFormateada = sdf.format(entity.getFechaHoraPresentacion());
				to.setFechaHoraPresentacion(fechaFormateada);
			} 
			if(entity.getImporteApagar() != null) {			
				DecimalFormat formato = new DecimalFormat("#,###.##");
				String valorFormateado = formato.format(entity.getImporteApagar());
				to.setImporteApagar(valorFormateado);
			}
			
			
			
		}
		return to;
	};

	
}

