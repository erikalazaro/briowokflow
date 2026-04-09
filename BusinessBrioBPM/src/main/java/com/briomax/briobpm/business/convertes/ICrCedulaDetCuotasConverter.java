package com.briomax.briobpm.business.convertes;


import java.text.SimpleDateFormat;
import java.util.function.Function;

import com.briomax.briobpm.persistence.entity.CrCedulaDetCuotas;
import com.briomax.briobpm.transferobjects.catalogs.CrCedulaDetCuotasTO;

public interface ICrCedulaDetCuotasConverter {

	/** Converter Entity to DTO. */
	Function<CrCedulaDetCuotas, CrCedulaDetCuotasTO> converterEntityToDTO = (entity) -> {
	    CrCedulaDetCuotasTO to = null;
	    if (entity != null) {
	        to = CrCedulaDetCuotasTO.builder()
	        		.cveEntidad(entity.getId().getCveEntidad())
	                .cveProceso(entity.getId().getCveProceso())
	                .rfcContratista(entity.getId().getRfcContratista())
	                .rfcContratista(entity.getId().getRfcContratista())
	                .numeroContrato(entity.getId().getNumeroContrato())
	                .nombreDocumento(entity.getNombreDocumento())
	                .razonSocial(entity.getRazonSocial())
	                .registroPatronal(entity.getRegistroPatronal())
	                .nssTrabajador(entity.getNssTrabajador())
	                .nombreTrabajador(entity.getNombreTrabajador())
	                .rfcCurpTrabajador(entity.getRfcCurpTrabajador())
	                .salarioBaseCotizacion(entity.getSalarioBaseCotizacion())
	                .periodo(entity.getPeriodo())
	                .situacionCarga(entity.getSituacionCarga())
	                .situacionTrabajador(entity.getEstatusRegistro())
	                .boton("boton")
	                .build();

	        SimpleDateFormat sdfDateTime = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	        if (entity.getId().getFechaCarga() != null) {
	            String fechaFormateada = sdfDateTime.format(entity.getId().getFechaCarga());
	            to.setFechaCarga(fechaFormateada);
	        }
	    }
	    return to;
	};

	
}



