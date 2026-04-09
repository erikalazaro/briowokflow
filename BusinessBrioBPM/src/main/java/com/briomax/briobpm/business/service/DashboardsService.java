/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */
package com.briomax.briobpm.business.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;

import com.briomax.briobpm.business.service.core.IDashboardsService;
import com.briomax.briobpm.common.core.DAORet;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.persistence.dao.base.IDashboardOperativoDAO;
import com.briomax.briobpm.persistence.entity.namedquery.LeeInfDashboardBurbujas;
import com.briomax.briobpm.persistence.entity.namedquery.LeeInfDashboardHistorico;
import com.briomax.briobpm.persistence.entity.namedquery.LeeInfDashboardKpi;
import com.briomax.briobpm.persistence.entity.namedquery.LeeInfDashboardPie;
import com.briomax.briobpm.persistence.entity.namedquery.LeeInfDashboardTabla;
import com.briomax.briobpm.persistence.entity.namedquery.LeeSecSubsecDashboard;
import com.briomax.briobpm.transferobjects.dashboards.BarChart;
import com.briomax.briobpm.transferobjects.dashboards.BarDataset;
import com.briomax.briobpm.transferobjects.dashboards.BurbleChart;
import com.briomax.briobpm.transferobjects.dashboards.BurbleChartData;
import com.briomax.briobpm.transferobjects.dashboards.BurbleChartDataset;
import com.briomax.briobpm.transferobjects.dashboards.Dashboard;
import com.briomax.briobpm.transferobjects.dashboards.IGraph;
import com.briomax.briobpm.transferobjects.dashboards.KPI;
import com.briomax.briobpm.transferobjects.dashboards.LineChart;
import com.briomax.briobpm.transferobjects.dashboards.LineDataset;
import com.briomax.briobpm.transferobjects.dashboards.LineDatasetData;
import com.briomax.briobpm.transferobjects.dashboards.OperativoGerenteIn;
import com.briomax.briobpm.transferobjects.dashboards.OperativoIn;
import com.briomax.briobpm.transferobjects.dashboards.PieChart;
import com.briomax.briobpm.transferobjects.dashboards.Section;
import com.briomax.briobpm.transferobjects.dashboards.TableChart;
import com.briomax.briobpm.transferobjects.dashboards.TypeChartEnum;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Class DashboardsService.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Jun 7, 2021 6:14:48 PM Modificaciones:
 * @since JDK 1.8
 */
@Service
@Transactional(readOnly = true)
@Slf4j
public class DashboardsService implements IDashboardsService {

	/** La Constante DASHBOARD_OPERATIVO. */
	private static final String DASHBOARD_OPERATIVO = "DASHBOARD_OPERATIVO";
	
	/** La Constante DASHBOARD_GERENTE. */
	private static final String DASHBOARD_GERENTE = "DASHBOARD_GERENTE";

	/** El atributo o variable Dashboard DAO. */
	@Autowired
	private IDashboardOperativoDAO dashboardDAO;

	/** 
	 * {@inheritDoc}
	 * @see com.briomax.briobpm.business.service.core.IDashboardsService#operativo(com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO, com.briomax.briobpm.transferobjects.dashboards.OperativoIn)
	 */
	@Override
	public DAORet<Dashboard, RetMsg> operativo(DatosAutenticacionTO session, OperativoIn filtros)
		throws BrioBPMException {
		log.debug("\t-----DASHBOARD OPERATIVO {} {}", session, filtros);

		List<Section> sections = new ArrayList<>();
		List<IGraph> graphs = new ArrayList<>();
		Section section = null;
		// 1
		section = Section.builder().order(1).build();
		//Remove dashboards addPieChart(graphs, section, session, session.getCveEntidad(), filtros.getFecInicial(), filtros.getFecFinal(), DASHBOARD_OPERATIVO, 1, 1);
		//Remove dashboards addKPI(graphs, section, session, session.getCveEntidad(), filtros.getFecInicial(), filtros.getFecFinal(), DASHBOARD_OPERATIVO, 1, 2);
		//Remove dashboards addTable(graphs, section, session, session.getCveEntidad(), filtros.getFecInicial(), filtros.getFecFinal(), DASHBOARD_OPERATIVO, 1, 3,
		//Remove dashboards 	null, null, true);
		section.setGraphics(graphs);
		sections.add(section);
		// 2
		graphs = new ArrayList<>();
		section = Section.builder().order(2).build();
		//Remove dashboards addPieChart(graphs, section, session, session.getCveEntidad(), filtros.getFecInicial(), filtros.getFecFinal(), DASHBOARD_OPERATIVO, 2, 1);
		//Remove dashboards addTable(graphs, section, session, session.getCveEntidad(), filtros.getFecInicial(), filtros.getFecFinal(), DASHBOARD_OPERATIVO, 2, 2,
		//Remove dashboards 	null, null, false);
		//Remove dashboards addTable(graphs, section, session, session.getCveEntidad(), filtros.getFecInicial(), filtros.getFecFinal(), DASHBOARD_OPERATIVO, 2, 3,
		//Remove dashboards 	null, null, true);
		section.setGraphics(graphs);
		sections.add(section);
		// 3
		graphs = new ArrayList<>();
		section = Section.builder().order(3).build();
		//Remove dashboards addBarChart(graphs, section, session, session.getCveEntidad(), filtros.getFecInicial(), filtros.getFecFinal(), DASHBOARD_OPERATIVO, 3, 1);
		//Remove dashboards addLineChart(graphs, section, session, session.getCveEntidad(), filtros.getFecInicial(), filtros.getFecFinal(), DASHBOARD_OPERATIVO, 3, 2);
		section.setGraphics(graphs);
		sections.add(section);

		//Remove dashboards Dashboard dashboard = Dashboard.builder().sections(sections).build();
		RetMsg ret = new RetMsg("OK", "");

		DAORet<Dashboard, RetMsg> dashboardResponse = new DAORet<Dashboard, RetMsg>(null, ret); //Remove dashboards (dashboard, ret)
		
		log.trace("\t      {}", dashboardResponse);
		log.debug("\t----- {}", dashboardResponse.getMeta());
		return dashboardResponse;
	}

	/** 
	 * {@inheritDoc}
	 * @see com.briomax.briobpm.business.service.core.IDashboardsService#operativoGerente(com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO, com.briomax.briobpm.transferobjects.dashboards.OperativoGerenteIn)
	 */
	@Override
	public DAORet<Dashboard, RetMsg> operativoGerente(DatosAutenticacionTO session, OperativoGerenteIn filtros)
		throws BrioBPMException {
		log.debug("\t-----DASHBOARD OPERATIVO GERENTE {} {}", session, filtros);

		List<Section> sections = new ArrayList<>();
		List<IGraph> graphs = new ArrayList<>();
		String cveEntidad = session.getCveEntidad();
		if (StringUtils.isNotBlank(filtros.getEntidad())) {
			cveEntidad = filtros.getEntidad();
		}
		// 1
		Section section = Section.builder().order(1).build();
		//Remove dashboards addPieChart(graphs, section, session, cveEntidad, filtros.getFecInicial(), filtros.getFecFinal(), DASHBOARD_GERENTE, 1, 1);
		//Remove dashboards addTable(graphs, section, session, cveEntidad, filtros.getFecInicial(), filtros.getFecFinal(), DASHBOARD_GERENTE, 1, 2,
		//Remove dashboards 	filtros.getTopSolicitado(), filtros.getNumRegistros(), true);
		//Remove dashboards addTable(graphs, section, session, cveEntidad, filtros.getFecInicial(), filtros.getFecFinal(), DASHBOARD_GERENTE, 1, 3,
		//Remove dashboards 	filtros.getTopSolicitado(), filtros.getNumRegistros(), true);
		section.setGraphics(graphs);
		sections.add(section);
		// 2
		graphs = new ArrayList<>();
		section = Section.builder().order(2).build();
		//Remove dashboards addPieChart(graphs, section, session, cveEntidad, filtros.getFecInicial(), filtros.getFecFinal(), DASHBOARD_GERENTE, 2, 1);
		//Remove dashboards addTable(graphs, section, session, cveEntidad, filtros.getFecInicial(), filtros.getFecFinal(), DASHBOARD_GERENTE, 2, 2,
		//Remove dashboards 	filtros.getTopSolicitado(), filtros.getNumRegistros(), false);
		//Remove dashboards addTable(graphs, section, session, cveEntidad, filtros.getFecInicial(), filtros.getFecFinal(), DASHBOARD_GERENTE, 2, 3,
		//Remove dashboards 	filtros.getTopSolicitado(), filtros.getNumRegistros(), true);
		section.setGraphics(graphs);
		sections.add(section);
		// 3
		graphs = new ArrayList<>();
		section = Section.builder().order(3).build();
		//Remove dashboards addBarChart(graphs, section, session, cveEntidad, filtros.getFecInicial(), filtros.getFecFinal(), DASHBOARD_GERENTE, 3, 1);
		//Remove dashboards addLineChart(graphs, section, session, cveEntidad, filtros.getFecInicial(), filtros.getFecFinal(), DASHBOARD_GERENTE, 3, 2);
		section.setGraphics(graphs);
		sections.add(section);
		// 4
		graphs = new ArrayList<>();
		section = Section.builder().order(4).build();
		//Remove dashboards addBurbleChart(graphs, section, session, cveEntidad, filtros.getFecInicial(), filtros.getFecFinal(), filtros.getSituacion(), DASHBOARD_GERENTE, 4, 1);
		//Remove dashboards addBurbleChart(graphs, section, session, cveEntidad, filtros.getFecInicial(), filtros.getFecFinal(), filtros.getSituacion(), DASHBOARD_GERENTE, 4, 2);
		section.setGraphics(graphs);
		sections.add(section);

		//Remove dashboards Dashboard dashboard = Dashboard.builder().sections(sections).build();
		RetMsg ret = new RetMsg("OK", "OK");

		DAORet<Dashboard, RetMsg> dashboardResponse = new DAORet<Dashboard, RetMsg>(null, ret);//Remove dashboards (dashboard, ret);

		log.trace("\t      {}", dashboardResponse);
		log.debug("\t----- {}", dashboardResponse.getMeta());
		return dashboardResponse;
	}

	/**
	 * Adds the pie chart.
	 * @param graphs el graphs.
	 * @param section el section.
	 * @param session el session.
	 * @param cveEntidad el cve entidad.
	 * @param fecInicial el fec inicial.
	 * @param fecFinal el fec final.
	 * @param tipoDashboard el tipo dashboard.
	 * @param numSeccion el num seccion.
	 * @param numSubseccion el num subseccion.
	 */
	private void addPieChart(List<IGraph> graphs, Section section, DatosAutenticacionTO session, String cveEntidad,
		String fecInicial, String fecFinal, String tipoDashboard, Integer numSeccion, Integer numSubseccion) {
		PieChart pieChart = null;
		DAORet<List<LeeSecSubsecDashboard>, RetMsg> metadata = null;
			dashboardDAO.leeSecSubsecDashboard(session.getCveUsuario(), cveEntidad,
				session.getCveLocalidad(), session.getCveIdioma(), tipoDashboard, numSeccion, numSubseccion);
		if ("OK".equalsIgnoreCase(metadata.getMeta().getStatus())) {
			List<String> labels = new ArrayList<>();
			metadata.getContent().forEach(row -> {
				log.trace("{}", row);
				labels.add(row.getEtiquetaSubseccion());
				section.setTitle(row.getTituloSeccion());
			});
			String[] dataArray = new String[] { };
			DAORet<List<LeeInfDashboardPie>, RetMsg> data = dashboardDAO.leeInfDashboardPie(session.getCveUsuario(),
				cveEntidad, session.getCveLocalidad(), session.getCveIdioma(), null, null, null, null,
				fecInicial, fecFinal, tipoDashboard, numSeccion, numSubseccion);
			if ("OK".equalsIgnoreCase(data.getMeta().getStatus())) {
				for (LeeInfDashboardPie row : data.getContent()) {
					log.trace("{}", row);
					if (row.getInformacion() != null) {
						dataArray = Arrays.stream(row.getInformacion().split("\\|")).map(String::trim).toArray(String[]::new);
					}
				}
			}
			pieChart = PieChart.builder()
					 .tipo(TypeChartEnum.PIE)
					 .order(numSubseccion)
					 .labels(labels.stream().map(l -> l).toArray(String[]::new))
					 .data(dataArray)
					 .build();
			graphs.add(pieChart);
		 }
	}

	/**
	 * Adds the KPI.
	 * @param graphs el graphs.
	 * @param section el section.
	 * @param session el session.
	 * @param cveEntidad el cve entidad.
	 * @param fecInicial el fec inicial.
	 * @param fecFinal el fec final.
	 * @param tipoDashboard el tipo dashboard.
	 * @param numSeccion el num seccion.
	 * @param numSubseccion el num subseccion.
	 */
	private void addKPI(List<IGraph> graphs, Section section, DatosAutenticacionTO session, String cveEntidad,
		String fecInicial, String fecFinal, String tipoDashboard, Integer numSeccion, Integer numSubseccion) {
		final KPI kpi = KPI.builder()
				.tipo(TypeChartEnum.KPI)
				.order(numSubseccion)
				.build();
		DAORet<List<LeeSecSubsecDashboard>, RetMsg> metadata =
					dashboardDAO.leeSecSubsecDashboard(session.getCveUsuario(), cveEntidad, session.getCveLocalidad(), 
						session.getCveIdioma(), 
						tipoDashboard, numSeccion, numSubseccion);
		if ("OK".equalsIgnoreCase(metadata.getMeta().getStatus())) {
			metadata.getContent().forEach(row -> {
				log.trace("{}", row);
				kpi.setTitle(row.getEtiquetaSubseccion());
				section.setTitle(row.getTituloSeccion());
			});
			DAORet<List<LeeInfDashboardKpi>, RetMsg> data = dashboardDAO.leeInfDashboardKpi(session.getCveUsuario(),
				cveEntidad, session.getCveLocalidad(), session.getCveIdioma(), null, null, null, null,
				fecInicial, fecFinal, tipoDashboard, numSeccion, numSubseccion);
			if ("OK".equalsIgnoreCase(data.getMeta().getStatus())) {
				data.getContent().stream().map(LeeInfDashboardKpi::getInformacion).forEach(kpi::setValue);
			}
			graphs.add(kpi);
		}
	}

	/**
	 * Adds the table.
	 * @param graphs el graphs.
	 * @param section el section.
	 * @param session el session.
	 * @param cveEntidad el cve entidad.
	 * @param fecInicial el fec inicial.
	 * @param fecFinal el fec final.
	 * @param tipoDashboard el tipo dashboard.
	 * @param numSeccion el num seccion.
	 * @param numSubseccion el num subseccion.
	 * @param top el top.
	 * @param numRegistros el num registros.
	 * @param json el json.
	 */
	private void addTable(List<IGraph> graphs, Section section, DatosAutenticacionTO session, String cveEntidad,
		String fecInicial, String fecFinal, String tipoDashboard, Integer numSeccion, Integer numSubseccion, String top,
		Integer numRegistros, boolean json) {
		DAORet<List<LeeSecSubsecDashboard>, RetMsg> metadata =
			dashboardDAO.leeSecSubsecDashboard(session.getCveUsuario(), cveEntidad,
				session.getCveLocalidad(), session.getCveIdioma(), tipoDashboard, numSeccion, numSubseccion);
		if ("OK".equalsIgnoreCase(metadata.getMeta().getStatus())) {
			List<String> labels = new ArrayList<>();
			List<String> fields = new ArrayList<>();
			List<String> types = new ArrayList<>();
			metadata.getContent().forEach(row -> {
				log.trace("{}", row);
				String text = row.getEtiquetaSubseccion() == null ? "" : row.getEtiquetaSubseccion();
				labels.add(text);
				fields.add(text);
				section.setTitle(row.getTituloSeccion());
				types.add(row.getTipoDatoEtiqueta());
			});
			log.trace("{}\t{}", labels, types);
			List<String[]> records = new ArrayList<>();
			DAORet<List<LeeInfDashboardTabla>, RetMsg> data = dashboardDAO.leeInfDashboardTabla(session.getCveUsuario(),
				cveEntidad, session.getCveLocalidad(), session.getCveIdioma(), null, null, null, null,
				fecInicial, fecFinal, tipoDashboard, numSeccion, numSubseccion, top, numRegistros);
			if ("OK".equalsIgnoreCase(data.getMeta().getStatus())) {
				data.getContent().forEach(row -> {
					log.trace("{}", row);
					if (row.getInformacion() != null) {
						records.add(Arrays.stream(row.getInformacion().split("\\|")).map(String::trim).toArray(String[]::new));
					}
				});
				TableChart table = TableChart.builder()
						.tipo(TypeChartEnum.TABLE)
						.order(numSubseccion)
						.labels(labels.stream().map(label -> label).toArray(String[]::new))
						.types(types.stream().map(type -> type).toArray(String[]::new))
						.records(generateDataJson(json, fields, records))
						.build();
				graphs.add(table);
			}
			
		}
	}

	/**
	 * Generate data json.
	 * @param json el json.
	 * @param fields el fields.
	 * @param records el records.
	 * @return el string.
	 */
	private String generateDataJson(boolean json, List<String> fields, List<String[]> records) {
		JSONArray recordList = new JSONArray();
		if (json) {
			StringBuilder jsonStr = new StringBuilder("[");
			int r = 0;
			for (String[] record : records) {
				jsonStr.append("{");
				int index = 0;
				for (String column : record) {
					jsonStr.append("\"").append(fields.get(index)).append("\":\"").append(column).append("\"");
					index++;
					if (index < record.length) {
						jsonStr.append(",");
					}
				}
				jsonStr.append("}");
				r++;
				if (r < records.size()) {
					jsonStr.append(",");
				}
			}
			jsonStr.append("]");
			return jsonStr.toString();
		} else {
			JSONArray row = null;
			for (String[] record : records) {
				row = new JSONArray();
				for (int index = 0; index < record.length; index++) {
					row.put(record[index]);
				}
				recordList.put(row);
			}
		}
		System.err.println(recordList.toString());
		return recordList.toString();
	}

	/**
	 * Adds the bar chart.
	 * @param graphs el graphs.
	 * @param section el section.
	 * @param session el session.
	 * @param cveEntidad el cve entidad.
	 * @param fecInicial el fec inicial.
	 * @param fecFinal el fec final.
	 * @param tipoDashboard el tipo dashboard.
	 * @param numSeccion el num seccion.
	 * @param numSubseccion el num subseccion.
	 */
	private void addBarChart(List<IGraph> graphs, Section section, DatosAutenticacionTO session, String cveEntidad,
		String fecInicial, String fecFinal, String tipoDashboard, Integer numSeccion, Integer numSubseccion) {
		BarChart barChart = null;
		DAORet<List<LeeSecSubsecDashboard>, RetMsg> metadata =
			dashboardDAO.leeSecSubsecDashboard(session.getCveUsuario(), cveEntidad,
				session.getCveLocalidad(), session.getCveIdioma(), tipoDashboard, numSeccion, numSubseccion);
		List<String> tipPresentacion = new ArrayList<>();
		List<String> escala = new ArrayList<>();
		if ("OK".equalsIgnoreCase(metadata.getMeta().getStatus())) {
			List<String> labels = new ArrayList<>();
			metadata.getContent().forEach(row -> {
				log.trace("{}", row);
				tipPresentacion.add(row.getTipoRepresentacion());
				escala.add(row.getEscala());
				section.setTitle(row.getTituloSeccion());
			});
			DAORet<List<LeeInfDashboardHistorico>, RetMsg> data = dashboardDAO.leeInfDashboardHistorico(
				session.getCveUsuario(), cveEntidad, session.getCveLocalidad(), session.getCveIdioma(),
				null, null, null, null, fecInicial, fecFinal, tipoDashboard, numSeccion, numSubseccion);
			List<List<String>> records = new ArrayList<>();
			data.getContent().forEach(row -> {
				log.trace("{}", row);
				records.add(Arrays.stream(row.getInformacion().split("\\|")).map(String::trim).collect(Collectors.toList()));
			});
			List<BarDataset> datasets = new ArrayList<>();
			int index = 0;
			for (List<String> list : records) {
				if (index == 0) {
					list.remove(0);
					list.remove(0);
					labels = list;
				}
				else {
					String etiqueta = list.get(0);
					String tipo = list.get(1);
					if (tipo != null && tipo.equalsIgnoreCase("NULL")) {
						tipo = null;
					}
					list.remove(0);
					list.remove(0);
					datasets.add(BarDataset.builder()
						.label(etiqueta)
						.tipo(tipo)
						.type(tipPresentacion.get(index - 1))
						.yAxisID(escala.get(index - 1))
						.data(list.stream()
							.map(numberString -> {
							try {
								return new BigDecimal(numberString);
							}
							catch (NumberFormatException exNumForm) {
								return BigDecimal.ZERO;
							}
						}).toArray(BigDecimal[]::new))
						.build());
				}
				index++;
			}
			barChart = BarChart.builder()
					 .tipo(TypeChartEnum.BAR)
					 .order(numSubseccion)
					 .labels(labels.stream().map(label -> label).toArray(String[]::new))
					 .datasets(datasets)
					 .build();
			graphs.add(barChart);
		 }
	}

	/**
	 * Adds the line chart.
	 * @param graphs el graphs.
	 * @param section el section.
	 * @param session el session.
	 * @param cveEntidad el cve entidad.
	 * @param fecInicial el fec inicial.
	 * @param fecFinal el fec final.
	 * @param tipoDashboard el tipo dashboard.
	 * @param numSeccion el num seccion.
	 * @param numSubseccion el num subseccion.
	 */
	private void addLineChart(List<IGraph> graphs, Section section, DatosAutenticacionTO session, String cveEntidad,
		String fecInicial, String fecFinal, String tipoDashboard, Integer numSeccion, Integer numSubseccion) {
		LineChart lineChart = null;
		DAORet<List<LeeSecSubsecDashboard>, RetMsg> metadata =
			dashboardDAO.leeSecSubsecDashboard(session.getCveUsuario(), cveEntidad,
				session.getCveLocalidad(), session.getCveIdioma(), tipoDashboard, numSeccion, numSubseccion);
		List<String> tipPresentacion = new ArrayList<>();
		List<String> escala = new ArrayList<>();
		 if ("OK".equalsIgnoreCase(metadata.getMeta().getStatus())) {
			 List<String> labels = new ArrayList<>(); 
			metadata.getContent().forEach(row -> {
				log.trace("{}", row);
				tipPresentacion.add(row.getTipoRepresentacion());
				escala.add(row.getEscala());
				section.setTitle(row.getTituloSeccion());
			});
			DAORet<List<LeeInfDashboardHistorico>, RetMsg> data = dashboardDAO.leeInfDashboardHistorico(
				session.getCveUsuario(), cveEntidad, session.getCveLocalidad(), session.getCveIdioma(),
				null, null, null, null, fecInicial, fecFinal, tipoDashboard, numSeccion, numSubseccion);
			List<List<String>> records = new ArrayList<>();
			data.getContent().forEach(row -> {
				log.trace("{}", row);
				records.add(Arrays.stream(row.getInformacion().split("\\|")).map(String::trim).collect(Collectors.toList()));
			});
			List<LineDatasetData> datasetData = new ArrayList<>();
			int index = 0;
			for (List<String> list : records) {
				if (index == 0) {
					list.remove(0);
					list.remove(0);
					labels = list;
				}
				else {
					String etiqueta = list.get(0);
					String tipo = list.get(1);
					if (tipo != null && tipo.equalsIgnoreCase("NULL")) {
						tipo = null;
					}
					list.remove(0);
					list.remove(0);
					datasetData.add(LineDatasetData.builder()
						.label(etiqueta)
						.tipo(tipo)
						.type(tipPresentacion.get(index - 1))
						.yAxisID(escala.get(index - 1))
						.data(list.stream()
							.map(numberString -> {
							try {
								return new BigDecimal(numberString);
							}
							catch (NumberFormatException exNumForm) {
								return BigDecimal.ZERO;
							}
						}).toArray(BigDecimal[]::new))
						.build());
				}
				index++;
			}
			List<LineDataset> datasets = new ArrayList<>();
			datasets.add(LineDataset.builder().data(datasetData).build());
			lineChart = LineChart.builder()
					 .tipo(TypeChartEnum.LINE)
					 .order(numSubseccion)
					 .labels(labels.stream().map(label -> label).toArray(String[]::new))
					 .datasets(datasets)
					 .build();
			graphs.add(lineChart);
		 }
	}

	/**
	 * Adds the burble chart.
	 * @param graphs el graphs.
	 * @param section el section.
	 * @param session el session.
	 * @param cveEntidad el cve entidad.
	 * @param fecInicial el fec inicial.
	 * @param fecFinal el fec final.
	 * @param categoria el categoria.
	 * @param tipoDashboard el tipo dashboard.
	 * @param numSeccion el num seccion.
	 * @param numSubseccion el num subseccion.
	 */
	private void addBurbleChart(List<IGraph> graphs, Section section, DatosAutenticacionTO session, String cveEntidad,
		String fecInicial, String fecFinal, String categoria, String tipoDashboard, Integer numSeccion,
		Integer numSubseccion) {
		BurbleChart burbleChart = null;
		DAORet<List<LeeSecSubsecDashboard>, RetMsg> metadata =
			dashboardDAO.leeSecSubsecDashboard(session.getCveUsuario(), cveEntidad,
				session.getCveLocalidad(), session.getCveIdioma(), tipoDashboard, numSeccion, numSubseccion);
		
		log.warn("{}", metadata);
		
		 if ("OK".equalsIgnoreCase(metadata.getMeta().getStatus())) {
			 List<String> labels = new ArrayList<>();
			 List<String> types = new ArrayList<>();
			metadata.getContent().forEach(row -> {
				log.trace("{}", row);
				labels.add(row.getEtiquetaSubseccion());
				section.setTitle(row.getTituloSeccion());
				types.add(row.getTipoDatoEtiqueta());
			});

			DAORet<List<LeeInfDashboardBurbujas>, RetMsg> dataGraph = dashboardDAO.leeInfDashboardBurbujas(
				session.getCveUsuario(), cveEntidad, session.getCveLocalidad(), session.getCveIdioma(),
				null, null, null, null, fecInicial, fecFinal, categoria, tipoDashboard, numSeccion, numSubseccion);
			List<List<String>> records = new ArrayList<>();
			dataGraph.getContent().forEach(row -> {
				log.trace("{}", row);
				records.add(Arrays.stream(row.getInformacion().split("\\|")).map(String::trim).collect(Collectors.toList()));
			});
			BurbleChartDataset dataset = null;
			List<BurbleChartDataset> datasets = new ArrayList<>();
			List<BurbleChartData> data = null;
			for (List<String> list : records) {
				System.err.println(list);
				data = new ArrayList<>();
				data.add(BurbleChartData.builder()
					.x(new BigDecimal(list.get(1)))
					.y(new BigDecimal(list.get(2)))
					.r(new BigDecimal(list.get(3)))
					.build());
				dataset = BurbleChartDataset.builder()
						.label(list.get(0))
						.xlabel(labels.get(0))
						.ylabel(labels.get(1))
						.rlabel(labels.get(2))
						.backgroundColor(list.get(4))
						.borderColor(list.get(5))
						.hoverBackgroundColor(list.get(6))
						.hoverBorderColor(list.get(6))
						.data(data)
						.build();
				datasets.add(dataset);
			}
			burbleChart = BurbleChart.builder()
					 .tipo(TypeChartEnum.BURBLE)
					 .order(numSubseccion)
					 .datasets(datasets)
					 .build();
			graphs.add(burbleChart);
		 }
	}

	
}
