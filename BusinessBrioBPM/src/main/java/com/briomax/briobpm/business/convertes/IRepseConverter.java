package com.briomax.briobpm.business.convertes;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Function;

import com.briomax.briobpm.persistence.entity.CrConsultaRepse;
import com.briomax.briobpm.persistence.entity.CrConsultaRepsePK;
import com.briomax.briobpm.transferobjects.in.CrConsultaRepseTO;

public interface IRepseConverter {

	/** Converter Entity to DTO. */
	Function<CrConsultaRepse, CrConsultaRepseTO> converterEntityToDTO = (entity) -> {
		CrConsultaRepseTO to = null;
		if (entity != null) {
			to = CrConsultaRepseTO.builder()
					.cveEntidad(entity.getId().getCveEntidad())
					.cveProceso(entity.getId().getCveProceso())
					.rfcContratista(entity.getId().getRfcContratista())
					.numContrato(entity.getId().getNumContrato())
					.resultadoConsulta(entity.getResultadoConsulta())
					.razonSocial(entity.getRazonSocial())
					.folio(entity.getFolio())
					.entidadMunicipio(entity.getEntidadMunicipio())
					.avisoFechaRegistro(entity.getAvisoFechaRegistro())
					.imagenConsulta(entity.getImagenConsulta())
					.descripcionServicio(entity.getDescripcionServicio())
					.boton("boton")
					.build();

			String fechaFormateada;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			
			if(entity.getId().getFechaConsulta() != null) {
				fechaFormateada = sdf.format(entity.getId().getFechaConsulta());
				to.setFechaConsulta(fechaFormateada);
			} 
		}
		return to;
	};

	/** Converter DTO to Entity. */
	Function<CrConsultaRepseTO, CrConsultaRepse> converterDtoEntity = (to) -> {
	    CrConsultaRepse entity = null;
	    CrConsultaRepsePK id = CrConsultaRepsePK.builder()
	            .cveEntidad(to.getCveEntidad())
	            .cveProceso(to.getCveProceso())
	            .rfcContratista(to.getRfcContratista())
	            .numContrato(to.getNumContrato())
	            .build();

	    if (to != null) {
	        // Formateador para convertir de String a Date
	        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	        // Parsear la fecha sin try-catch, lanzará ParseException si falla
	        Date fechaConsulta = null;
			try {
				fechaConsulta = sdf.parse(to.getFechaConsulta());
			} catch (ParseException e) {
				e.printStackTrace();
			}

	        entity = CrConsultaRepse.builder()
	                .id(id)
	                .resultadoConsulta(to.getResultadoConsulta())
	                .razonSocial(to.getRazonSocial())
	                .folio(to.getFolio())
	                .entidadMunicipio(to.getEntidadMunicipio())
	                .avisoFechaRegistro(to.getAvisoFechaRegistro())
	                .imagenConsulta(to.getImagenConsulta())
	                .descripcionServicio(to.getDescripcionServicio())
	                .build();

	        // Asignar la fecha convertida a la entidad
	        entity.getId().setFechaConsulta(fechaConsulta);
	    }

	    return entity;
	};

	
}

