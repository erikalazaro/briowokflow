package com.briomax.briobpm.business.convertes;


import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.function.Function;

import com.briomax.briobpm.persistence.entity.CrReciboNomina;
import com.briomax.briobpm.transferobjects.catalogs.CrReciboNominaTO;

public interface ICrReciboNominaConverter {

	/** Converter Entity to DTO. */
	Function<CrReciboNomina, CrReciboNominaTO> converterEntityToDTO = (entity) -> {
		CrReciboNominaTO to = null;
		if (entity != null) {
			to = CrReciboNominaTO.builder()
					.cveEntidad(entity.getId().getCveEntidad())
					.cveProceso(entity.getId().getCveProceso())					
					.rfcContratista(entity.getId().getRfcContratista())
					.numContrato(entity.getId().getNumContrato())			
					.rfcTrabajador(entity.getId().getRfcTrabajador())
					.tipoNomina(entity.getId().getTipoNomina())
					.secuenciaRecibo(entity.getId().getSecuenciaRecibo())
					.nombreDocumento(entity.getNombreDocumento())
					.razonSocialPatron(entity.getRazonSocialPatron())
					.rfcPatron(entity.getRfcPatron())
					.registroPatronal(entity.getRegistroPatronal())
					.nombreTrabajdor(entity.getNombreTrabajdor())
					.curpTrabajdor(entity.getCurpTrabajdor())
					.nssTrabajdor(entity.getNssTrabajdor())
					.situacionCarga(entity.getSituacionCarga())
					.boton("boton")
					.build();
			String fechaFormateada;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy  HH:mm:ss");

			fechaFormateada = sdf.format(entity.getId().getFechaCarga());
			to.setFechaCarga(fechaFormateada);
			
			SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
			
			if(entity.getFechaInicioPeriodo() != null) {
				fechaFormateada = sdf2.format(entity.getFechaInicioPeriodo());
				to.setFechaInicioPeriodo(fechaFormateada);
			} 
			
			if(entity.getFechaFinalPeriodo() != null) {
				fechaFormateada = sdf2.format(entity.getFechaFinalPeriodo());
				to.setFechaFinalPeriodo(fechaFormateada);
			} 
			
			if(entity.getFechaPago() != null) {
				fechaFormateada = sdf2.format(entity.getFechaPago());
				to.setFechaPago(fechaFormateada);
			} 
			if(entity.getFechaTimbrado() != null) {
				fechaFormateada = sdf2.format(entity.getFechaTimbrado());
				to.setFechaTimbrado(fechaFormateada);
			} 
			
			DecimalFormat formato = new DecimalFormat("#,###.##");
			String valorFormateado = formato.format(entity.getSalarioBase() == null? 0:entity.getSalarioBase());
			
			to.setSalarioBase(valorFormateado);
			
		}
		return to;
	};

	
}

