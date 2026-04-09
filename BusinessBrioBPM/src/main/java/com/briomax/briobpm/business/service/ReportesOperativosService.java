/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.business.service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import com.briomax.briobpm.business.convertes.IReportesOperativosConverter;
import com.briomax.briobpm.business.service.core.IReportesOperativosService;
import com.briomax.briobpm.common.core.DAORet;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.persistence.dao.base.IReportesOperativosDAO;
import com.briomax.briobpm.persistence.entity.namedquery.LeeInfReporteActividades;
import com.briomax.briobpm.persistence.entity.namedquery.LeeInfReporteProcesos;
import com.briomax.briobpm.transferobjects.ReporteActividadesTO;
import com.briomax.briobpm.transferobjects.ReporteProcesosTO;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;


/**
 * El objetivo de la Class ReportesService.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Oct 6, 2020 6:05:58 PM Modificaciones:
 * @since JDK 1.8
 */
@Service
@Transactional(readOnly = true)
@Slf4j
public class ReportesOperativosService implements IReportesOperativosService {

	/** El atributo o variable reportes DAO. */
	@Autowired
	private IReportesOperativosDAO reportesDAO;

	/** 
	 * {@inheritDoc}
	 * @see com.briomax.briobpm.business.service.core.IReportesOperativosService#obtenerReporteProcesos(com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO)
	 */
	@Override
	public DAORet<List<ReporteProcesosTO>, RetMsg> obtenerReporteProcesos(DatosAutenticacionTO session)
		throws BrioBPMException {
		log.info("\t-----REPORTE OPERATIVO X PROCESOS SERVICE {} ", session);
		List<ReporteProcesosTO> report = new LinkedList<ReporteProcesosTO>();
		DAORet<List<LeeInfReporteProcesos>, RetMsg> reporteProcesos = reportesDAO.obtenerReporteProcesos(
			session.getCveUsuario(), session.getCveEntidad(), session.getCveLocalidad(), session.getCveIdioma());
		log.trace("\t\tRESPONSE DATA PROCESOS: {}", reporteProcesos);
		if (reporteProcesos.getMeta().getStatus().equalsIgnoreCase("OK")) {
			log.debug("[{}] Converte Entity (LeeInfReporteProcesos) -> Dto (ReporteProcesosTO) ",
				reporteProcesos.getContent().size());
			report.addAll(reporteProcesos.getContent().stream().map(IReportesOperativosConverter.converterReporteProceso)
				.collect(Collectors.toList()));
		}
		log.trace("\t      {}", report);
		log.debug("\t----- {}", reporteProcesos.getMeta());
		return new DAORet<List<ReporteProcesosTO>, RetMsg>(report, reporteProcesos.getMeta());
	}

	/** 
	 * {@inheritDoc}
	 * @see com.briomax.briobpm.business.service.core.IReportesOperativosService#obtenerReporteActividades(com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO)
	 */
	@Override
	public DAORet<List<ReporteActividadesTO>, RetMsg> obtenerReporteActividades(DatosAutenticacionTO session)
		throws BrioBPMException {
		log.info("\t-----REPORTE OPERATIVO X ACTIVIDADES SERVICE {} ", session);
		List<ReporteActividadesTO> report = new LinkedList<ReporteActividadesTO>();
		DAORet<List<LeeInfReporteActividades>, RetMsg> reporteActividades = reportesDAO.obtenerReporteActividades(
			session.getCveUsuario(), session.getCveEntidad(), session.getCveLocalidad(), session.getCveIdioma());
		log.trace("\t\tRESPONSE DATA ACTIVIDADES: {}", reporteActividades);
		if (reporteActividades.getMeta().getStatus().equalsIgnoreCase("OK")) {
			log.debug("[{}] Converte Entity (LeeInfReporteActividad) -> Dto (ReporteActividadTO) ",
				reporteActividades.getContent().size());
			report.addAll(reporteActividades.getContent().stream().map(IReportesOperativosConverter.converterReporteActividad)
				.collect(Collectors.toList()));
		}
		log.trace("\t      {}", report);
		log.debug("\t----- {}", reporteActividades.getMeta());
		return new DAORet<List<ReporteActividadesTO>, RetMsg>(report, reporteActividades.getMeta());
	}

}
