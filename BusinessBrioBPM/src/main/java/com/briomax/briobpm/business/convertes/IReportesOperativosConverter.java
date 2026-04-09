/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */
package com.briomax.briobpm.business.convertes;

import java.util.function.Function;

import com.briomax.briobpm.persistence.entity.namedquery.LeeInfReporteActividades;
import com.briomax.briobpm.persistence.entity.namedquery.LeeInfReporteProcesos;
import com.briomax.briobpm.transferobjects.ReporteActividadesTO;
import com.briomax.briobpm.transferobjects.ReporteProcesosTO;

/**
 * El objetivo de la Interface IReportesConverter.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Oct 6, 2020 6:48:43 PM Modificaciones:
 * @since JDK 1.8
 */
public interface IReportesOperativosConverter {

	Function<LeeInfReporteProcesos, ReporteProcesosTO> converterReporteProceso = (entity) -> {
		ReporteProcesosTO to = null;
		if (entity != null) {
			to = ReporteProcesosTO.builder()
					.idProceso(entity.getId().getIdProceso())
					.nomProceso(entity.getId().getNombreProceso())
					.instancia(entity.getId().getInstancia())
					.fecHorInicio(entity.getFechaHoraInicio())
					.fecHorTermino(entity.getFechaHoraTermino())
					.situacion(entity.getSituacion())
					.build();
		}
		return to;
	};

	Function<LeeInfReporteActividades, ReporteActividadesTO> converterReporteActividad = (entity) -> {
		ReporteActividadesTO to = null;
		if (entity != null) {
			to = ReporteActividadesTO.builder()
					.idProceso(entity.getId().getIdProceso())
					.nomProceso(entity.getId().getNombreProceso())
					.instancia(entity.getId().getInstancia())
					.nomActividad(entity.getId().getNombreActividad())
					.rolActividad(entity.getRolActividad())
					.usuActividad(entity.getUsuarioActividad())
					.fecAsignacion(entity.getFechaAsignacion())
					.fecHorTerminacion(entity.getFechaHoraTerminacion())
					.duracion(entity.getDuracion())
					.fecLimSLA(entity.getFechaLimiteSLA())
					.variacion(entity.getVariacion())
					.situacion(entity.getSituacion())
					.build();
		}
		return to;
	};

}
