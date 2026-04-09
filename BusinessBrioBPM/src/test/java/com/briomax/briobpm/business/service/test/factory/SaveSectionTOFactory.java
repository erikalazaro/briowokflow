/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */
package com.briomax.briobpm.business.service.test.factory;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.briomax.briobpm.transferobjects.DataOccurrenceTO;
import com.briomax.briobpm.transferobjects.DataSectionTO;
import com.briomax.briobpm.transferobjects.SaveSectionTO;
import com.briomax.briobpm.transferobjects.SectionVariablesTO;
import com.briomax.briobpm.transferobjects.in.ActividadTO;

/**
 * El objetivo de la Class EjecucionActividadServiceTest.java es Una fábrica para crear objetos SaveSectionTO.
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Mar 5, 2020 2:16:28 PM Modificaciones:
 * @since JDK 1.8
 */
public class SaveSectionTOFactory {

	/**
	 * Builder save section TO.
	 * @return el save section TO.
	 */
	public static SaveSectionTO builderSaveSectionTO() {
		List<DataSectionTO> data = new LinkedList<DataSectionTO>();
		List<DataOccurrenceTO> dataOccurrence = null;
		DataOccurrenceTO dataSection = null;
		List<SectionVariablesTO> sectionVariables;
		SectionVariablesTO sectionVariable;
		List<String> values;
		DataSectionTO ds = null;
		// _/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\
		dataOccurrence = new LinkedList<DataOccurrenceTO>();
		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		dataSection = DataOccurrenceTO.builder()
				.build();
		sectionVariables = new ArrayList<SectionVariablesTO>();
		// ###############
		sectionVariable = SectionVariablesTO.builder()
				.cveVariable("VPRO_DESCRIPCION_SOLICITUD")
				.build();
		values = new ArrayList<String>();
		values.add("No se muestran cifras en el dashboard a nivel terrotorio");
		sectionVariable.setValues(values);
		sectionVariables.add(sectionVariable);
		dataSection.setSectionVariables(sectionVariables);
		// ###############
		sectionVariable = SectionVariablesTO.builder()
				.cveVariable("VPRO_SEVERIDAD_SOLICITUD")
				.build();
		values = new ArrayList<String>();
		values.add("ALTA");
		sectionVariable.setValues(values);
		sectionVariables.add(sectionVariable);
		dataSection.setSectionVariables(sectionVariables);
		// ###############
		sectionVariable = SectionVariablesTO.builder()
				.cveVariable("VPRO_USUARIO_CONTACTO")
				.build();
		values = new ArrayList<String>();
		values.add("Alonso Martínez");
		sectionVariable.setValues(values);
		sectionVariables.add(sectionVariable);
		dataSection.setSectionVariables(sectionVariables);
		// ###############
		sectionVariable = SectionVariablesTO.builder()
				.cveVariable("VPRO_TELEFONO_USUARIO")
				.build();
		values = new ArrayList<String>();
		values.add("5544332211");
		sectionVariable.setValues(values);
		sectionVariables.add(sectionVariable);
		dataSection.setSectionVariables(sectionVariables);
		// ###############
		sectionVariable = SectionVariablesTO.builder()
				.cveVariable("VPRO_HORARIO_CONTACTO")
				.build();
		values = new ArrayList<String>();
		values.add("9:00-18:00");
		sectionVariable.setValues(values);
		sectionVariables.add(sectionVariable);
		dataSection.setSectionVariables(sectionVariables);
		// ###############
		dataOccurrence.add(dataSection);
		// <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		ds = DataSectionTO.builder()
				.cveSection("DATOS_GENERALES_SOLICITUD")
				.type("SECUENCIAL")
				.content("VARIABLES")
				.dataOccurrence(dataOccurrence)
				.build();
		data.add(ds);
		// -\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/
		// _/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\
		dataOccurrence = new LinkedList<DataOccurrenceTO>();
		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		dataSection = DataOccurrenceTO.builder()
				.build();
		sectionVariables = new ArrayList<SectionVariablesTO>();
		// ###############
		sectionVariable = SectionVariablesTO.builder()
				.cveVariable("VPRO_MODULO")
				.build();
		values = new ArrayList<String>();
		values.add(null);
		//sectionVariable.setValues(values);
		sectionVariable.setValues(null);
		sectionVariables.add(sectionVariable);
		dataSection.setSectionVariables(sectionVariables);
		// ###############
		sectionVariable = SectionVariablesTO.builder()
				.cveVariable("VPRO_PANTALLA_REPORTE_PROCESO")
				.build();
		values = new ArrayList<String>();
		values.add(null);
		//sectionVariable.setValues(values);
		sectionVariable.setValues(null);
		sectionVariables.add(sectionVariable);
		dataSection.setSectionVariables(sectionVariables);
		// ###############
		dataOccurrence.add(dataSection);
		// <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		ds = DataSectionTO.builder()
				.cveSection("DATOS_MODULO")
				.type("SECUENCIAL")
				.content("VARIABLES")
				.dataOccurrence(dataOccurrence)
				.build();
		data.add(ds);
		// -\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/
		// _/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\
		dataOccurrence = new LinkedList<DataOccurrenceTO>();
		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		dataSection = DataOccurrenceTO.builder()
				.build();
		sectionVariables = new ArrayList<SectionVariablesTO>();
		// ###############
		sectionVariable = SectionVariablesTO.builder()
				.cveVariable("VPRO_FECHA_INICIO_CONTRATO")
				.build();
		values = new ArrayList<String>();
		values.add(" ");
		sectionVariable.setValues(values);
		sectionVariables.add(sectionVariable);
		dataSection.setSectionVariables(sectionVariables);
		// ###############
		sectionVariable = SectionVariablesTO.builder()
				.cveVariable("VPRO_FECHA_FIN_CONTRATO")
				.build();
		values = new ArrayList<String>();
		values.add(" ");
		sectionVariable.setValues(values);
		sectionVariables.add(sectionVariable);
		dataSection.setSectionVariables(sectionVariables);
		// ###############
		sectionVariable = SectionVariablesTO.builder()
				.cveVariable("VPRO_CONTRATO_VIGENTE")
				.build();
		values = new ArrayList<String>();
		values.add(" ");
		sectionVariable.setValues(values);
		sectionVariables.add(sectionVariable);
		dataSection.setSectionVariables(sectionVariables);
		// ###############
		dataOccurrence.add(dataSection);
		// <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		ds = DataSectionTO.builder()
				.cveSection("INFORMACION_CONTRATO")
				.type("SECUENCIAL")
				.content("VARIABLES")
				.dataOccurrence(dataOccurrence)
				.build();
		data.add(ds);
		// -\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/
		// _/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\
		dataOccurrence = new LinkedList<DataOccurrenceTO>();
		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		dataSection = DataOccurrenceTO.builder()
				.build();
		sectionVariables = new ArrayList<SectionVariablesTO>();
		// ###############
		sectionVariable = SectionVariablesTO.builder()
				.cveVariable("VPRO_RESUMEN_SOPORTE_REALIZADO")
				.build();
		values = new ArrayList<String>();
		values.add(null);
		//sectionVariable.setValues(values);
		sectionVariable.setValues(null);
		sectionVariables.add(sectionVariable);
		dataSection.setSectionVariables(sectionVariables);
		// ###############
		sectionVariable = SectionVariablesTO.builder()
				.cveVariable("VPRO_OBSERVACIONES_SOPORTE")
				.build();
		values = new ArrayList<String>();
		values.add(null);
		//sectionVariable.setValues(values);
		sectionVariable.setValues(null);
		sectionVariables.add(sectionVariable);
		dataSection.setSectionVariables(sectionVariables);
		// ###############
		dataOccurrence.add(dataSection);
		// <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		ds = DataSectionTO.builder()
				.cveSection("EJECUCION_SOPORTE")
				.type("SECUENCIAL")
				.content("VARIABLES")
				.dataOccurrence(dataOccurrence)
				.build();
		data.add(ds);
		// -\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/
		// _/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\
		dataOccurrence = new LinkedList<DataOccurrenceTO>();
		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		dataSection = DataOccurrenceTO.builder()
				.build();
		sectionVariables = new ArrayList<SectionVariablesTO>();
		// ###############
		sectionVariable = SectionVariablesTO.builder()
				.cveVariable("VPRO_DESCRIPCION_VISTO_BUENO_SOPORTE")
				.build();
		values = new ArrayList<String>();
		values.add(null);
		//sectionVariable.setValues(values);
		sectionVariable.setValues(null);
		sectionVariables.add(sectionVariable);
		dataSection.setSectionVariables(sectionVariables);
		// ###############
		sectionVariable = SectionVariablesTO.builder()
				.cveVariable("VPRO_VISTO_BUENO_SOPORTE")
				.build();
		values = new ArrayList<String>();
		values.add(null);
		//sectionVariable.setValues(values);
		sectionVariable.setValues(null);
		sectionVariables.add(sectionVariable);
		dataSection.setSectionVariables(sectionVariables);
		// ###############
		dataOccurrence.add(dataSection);
		// <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		ds = DataSectionTO.builder()
				.cveSection("VISTO_BUENO_SOPORTE")
				.type("SECUENCIAL")
				.content("VARIABLES")
				.dataOccurrence(dataOccurrence)
				.build();
		data.add(ds);
		// -\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/
		// _/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\
		dataOccurrence = new LinkedList<DataOccurrenceTO>();
		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		dataSection = DataOccurrenceTO.builder()
				.build();
		sectionVariables = new ArrayList<SectionVariablesTO>();
		// ###############
		sectionVariable = SectionVariablesTO.builder()
				.cveVariable("VPRO_CONCEPTO_SOPORTE")
				.build();
		values = new ArrayList<String>();
		values.add("Instalación de SW V2.5");
		sectionVariable.setValues(values);
		sectionVariables.add(sectionVariable);
		dataSection.setSectionVariables(sectionVariables);
		// ###############
		sectionVariable = SectionVariablesTO.builder()
				.cveVariable("VPRO_HORAS_INVERTIDAS_SOPORTE")
				.build();
		values = new ArrayList<String>();
		values.add("3.0000");
		sectionVariable.setValues(values);
		sectionVariables.add(sectionVariable);
		dataSection.setSectionVariables(sectionVariables);
		// ###############
		sectionVariable = SectionVariablesTO.builder()
				.cveVariable("VPRO_TARIFA_SOPORTE")
				.build();
		values = new ArrayList<String>();
		values.add("400.0000");
		sectionVariable.setValues(values);
		sectionVariables.add(sectionVariable);
		dataSection.setSectionVariables(sectionVariables);
		// ###############
		sectionVariable = SectionVariablesTO.builder()
				.cveVariable("VPRO_IMPORTE_SOPORTE")
				.build();
		values = new ArrayList<String>();
		values.add("1200.0000");
		sectionVariable.setValues(values);
		sectionVariables.add(sectionVariable);
		dataSection.setSectionVariables(sectionVariables);
		// ###############
		sectionVariable = SectionVariablesTO.builder()
				.cveVariable("VPRO_IMPORTE_IVA_SOPORTE")
				.build();
		values = new ArrayList<String>();
		values.add("192.0000");
		sectionVariable.setValues(values);
		sectionVariables.add(sectionVariable);
		dataSection.setSectionVariables(sectionVariables);
		// ###############
		sectionVariable = SectionVariablesTO.builder()
				.cveVariable("VPRO_IMPORTE_SOPORTE_MAS_IVA")
				.build();
		values = new ArrayList<String>();
		values.add("1392.0000");
		sectionVariable.setValues(values);
		sectionVariables.add(sectionVariable);
		//
		dataSection.setSectionVariables(sectionVariables);
		// ###############
		dataOccurrence.add(dataSection);
		// <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		dataSection = DataOccurrenceTO.builder()
				.build();
		sectionVariables = new ArrayList<SectionVariablesTO>();
		// ###############
		sectionVariable = SectionVariablesTO.builder()
				.cveVariable("VPRO_CONCEPTO_SOPORTE")
				.build();
		values = new ArrayList<String>();
		values.add("Configuración de SW");
		sectionVariable.setValues(values);
		sectionVariables.add(sectionVariable);
		dataSection.setSectionVariables(sectionVariables);
		// ###############
		sectionVariable = SectionVariablesTO.builder()
				.cveVariable("VPRO_HORAS_INVERTIDAS_SOPORTE")
				.build();
		values = new ArrayList<String>();
		values.add("1.0000");
		sectionVariable.setValues(values);
		sectionVariables.add(sectionVariable);
		dataSection.setSectionVariables(sectionVariables);
		// ###############
		sectionVariable = SectionVariablesTO.builder()
				.cveVariable("VPRO_TARIFA_SOPORTE")
				.build();
		values = new ArrayList<String>();
		values.add("400.0000");
		sectionVariable.setValues(values);
		sectionVariables.add(sectionVariable);
		dataSection.setSectionVariables(sectionVariables);
		// ###############
		sectionVariable = SectionVariablesTO.builder()
				.cveVariable("VPRO_IMPORTE_SOPORTE")
				.build();
		values = new ArrayList<String>();
		values.add("400.0000");
		sectionVariable.setValues(values);
		sectionVariables.add(sectionVariable);
		dataSection.setSectionVariables(sectionVariables);
		// ###############
		sectionVariable = SectionVariablesTO.builder()
				.cveVariable("VPRO_IMPORTE_IVA_SOPORTE")
				.build();
		values = new ArrayList<String>();
		values.add("64.0000");
		sectionVariable.setValues(values);
		sectionVariables.add(sectionVariable);
		dataSection.setSectionVariables(sectionVariables);
		// ###############
		sectionVariable = SectionVariablesTO.builder()
				.cveVariable("VPRO_IMPORTE_SOPORTE_MAS_IVA")
				.build();
		values = new ArrayList<String>();
		values.add("464.0000");
		sectionVariable.setValues(values);
		sectionVariables.add(sectionVariable);
		//
		dataSection.setSectionVariables(sectionVariables);
		// ###############
		dataOccurrence.add(dataSection);
		// <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		ds = DataSectionTO.builder()
				.cveSection("INFORMACION_FACTURACION_SOPORTE")
				.type("GRID")
				.content("VARIABLES")
				.dataOccurrence(dataOccurrence)
				.build();
		data.add(ds);
		// -\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/
		// _/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\
		dataOccurrence = new LinkedList<DataOccurrenceTO>();
		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		dataSection = DataOccurrenceTO.builder()
				.build();
		sectionVariables = new ArrayList<SectionVariablesTO>();
		// ###############
		sectionVariable = SectionVariablesTO.builder()
				.cveVariable("VPRO_IMPORTE_TOTAL_SOPORTE")
				.build();
		values = new ArrayList<String>();
		values.add(null);
		//sectionVariable.setValues(values);
		sectionVariable.setValues(null);
		sectionVariables.add(sectionVariable);
		dataSection.setSectionVariables(sectionVariables);
		// ###############
		sectionVariable = SectionVariablesTO.builder()
				.cveVariable("VPRO_IMPORTE_TOTAL_IVA_SOPORTE")
				.build();
		values = new ArrayList<String>();
		values.add(null);
		//sectionVariable.setValues(values);
		sectionVariable.setValues(null);
		sectionVariables.add(sectionVariable);
		dataSection.setSectionVariables(sectionVariables);
		// ###############
		sectionVariable = SectionVariablesTO.builder()
				.cveVariable("VPRO_IMPORTE_TOTAL_SOPORTE_MAS_IVA")
				.build();
		values = new ArrayList<String>();
		values.add(null);
		//sectionVariable.setValues(values);
		sectionVariable.setValues(null);
		sectionVariables.add(sectionVariable);
		dataSection.setSectionVariables(sectionVariables);
		// ###############
		dataOccurrence.add(dataSection);
		// <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		ds = DataSectionTO.builder()
				.cveSection("MONTO_TOTAL_FACTURACION_SOPORTE")
				.type("SECUENCIAL")
				.content("VARIABLES")
				.dataOccurrence(dataOccurrence)
				.build();
		data.add(ds);
		// -\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/
		// _/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\
		dataOccurrence = new LinkedList<DataOccurrenceTO>();
		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		dataSection = DataOccurrenceTO.builder()
				.build();
		sectionVariables = new ArrayList<SectionVariablesTO>();
		// ###############
		sectionVariable = SectionVariablesTO.builder()
				.cveVariable("VPRO_01_SECUENCIA_TAREA")
				.build();
		values = new ArrayList<String>();
		values.add("1");
		sectionVariable.setValues(values);
		sectionVariables.add(sectionVariable);
		dataSection.setSectionVariables(sectionVariables);
		// ###############
		sectionVariable = SectionVariablesTO.builder()
				.cveVariable("VPRO_01_TAREA_COMPLETADA")
				.build();
		values = new ArrayList<String>();
		values.add("NO");
		sectionVariable.setValues(values);
		sectionVariables.add(sectionVariable);
		dataSection.setSectionVariables(sectionVariables);
		// ###############
		dataOccurrence.add(dataSection);
		// <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		dataSection = DataOccurrenceTO.builder()
				.build();
		sectionVariables = new ArrayList<SectionVariablesTO>();
		// ###############
		sectionVariable = SectionVariablesTO.builder()
				.cveVariable("VPRO_01_SECUENCIA_TAREA")
				.build();
		values = new ArrayList<String>();
		values.add("2");
		sectionVariable.setValues(values);
		sectionVariables.add(sectionVariable);
		dataSection.setSectionVariables(sectionVariables);
		// ###############
		sectionVariable = SectionVariablesTO.builder()
				.cveVariable("VPRO_01_TAREA_COMPLETADA")
				.build();
		values = new ArrayList<String>();
		values.add("SI");
		sectionVariable.setValues(values);
		sectionVariables.add(sectionVariable);
		dataSection.setSectionVariables(sectionVariables);
		// ###############
		dataOccurrence.add(dataSection);
		// <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		dataSection = DataOccurrenceTO.builder()
				.build();
		sectionVariables = new ArrayList<SectionVariablesTO>();
		// ###############
		sectionVariable = SectionVariablesTO.builder()
				.cveVariable("VPRO_01_SECUENCIA_TAREA")
				.build();
		values = new ArrayList<String>();
		values.add("3");
		sectionVariable.setValues(values);
		sectionVariables.add(sectionVariable);
		dataSection.setSectionVariables(sectionVariables);		
		// ###############
		sectionVariable = SectionVariablesTO.builder()
				.cveVariable("VPRO_01_TAREA_COMPLETADA")
				.build();
		values = new ArrayList<String>();
		values.add("NO");
		sectionVariable.setValues(values);
		sectionVariables.add(sectionVariable);
		dataSection.setSectionVariables(sectionVariables);
		// ###############
		dataOccurrence.add(dataSection);
		// <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		dataSection = DataOccurrenceTO.builder()
				.build();
		sectionVariables = new ArrayList<SectionVariablesTO>();
		// ###############
		sectionVariable = SectionVariablesTO.builder()
				.cveVariable("VPRO_01_SECUENCIA_TAREA")
				.build();
		values = new ArrayList<String>();
		values.add("4");
		sectionVariable.setValues(values);
		sectionVariables.add(sectionVariable);
		dataSection.setSectionVariables(sectionVariables);
		// ###############
		sectionVariable = SectionVariablesTO.builder()
				.cveVariable("VPRO_01_TAREA_COMPLETADA")
				.build();
		values = new ArrayList<String>();
		values.add("SI");
		sectionVariable.setValues(values);
		sectionVariables.add(sectionVariable);
		dataSection.setSectionVariables(sectionVariables);
		// ###############
		dataOccurrence.add(dataSection);
		// <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		ds = DataSectionTO.builder()
				.cveSection("VERIFICACION_CLIENTE")
				.type("GRID")
				.content("TAREAS")
				.dataOccurrence(dataOccurrence)
				.build();
		data.add(ds);
		// -\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/
		// &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
		SaveSectionTO dataSections = SaveSectionTO.builder()
				.activity(ActividadTO.builderActividadTO()
					.cveRol("FACTURACION")
					.cveProceso("INCIDENCIA-SMI")
					.version("1.00")
					.cveInstancia("201807-000001")
					.cveNodo("ACTIVIDAD-USUARIO")
					.idNodo(9)
					.secNodo(2)
					.cveAreaTrabajo("")
					.build())
				.data(data)
				.build();
		// &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
		return dataSections;
	}

	/**
	 * Builder save section dummy.
	 * @return el save section TO.
	 */
	public static SaveSectionTO builderSaveSectionDummy() {
		List<DataSectionTO> data = new LinkedList<DataSectionTO>();
		List<DataOccurrenceTO> dataOccurrence = null;
		DataOccurrenceTO dataSection = null;
		List<SectionVariablesTO> sectionVariables;
		SectionVariablesTO sectionVariable;
		List<String> values;
		DataSectionTO ds = null;
		// _/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\
		dataOccurrence = new LinkedList<DataOccurrenceTO>();
		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		dataSection = DataOccurrenceTO.builder()
				.build();
		sectionVariables = new ArrayList<SectionVariablesTO>();
		// ###############
		sectionVariable = SectionVariablesTO.builder()
				.cveVariable("VPRO_ALFANUMERICO_CAJA_TEXTO")
				.build();
		values = new ArrayList<String>();
		values.add("CAJA DE TEXTO");
		sectionVariable.setValues(values);
		sectionVariables.add(sectionVariable);
		dataSection.setSectionVariables(sectionVariables);
		// ###############
		sectionVariable = SectionVariablesTO.builder()
				.cveVariable("VPRO_ALFANUMERICO_TEXTAREA")
				.build();
		values = new ArrayList<String>();
		values.add("CAJA DE TEXTO CON SCROLL");
		sectionVariable.setValues(values);
		sectionVariables.add(sectionVariable);
		dataSection.setSectionVariables(sectionVariables);
		// ###############
		sectionVariable = SectionVariablesTO.builder()
				.cveVariable("VPRO_ALFANUMERICO_COMBOBOX")
				.build();
		values = new ArrayList<String>();
		values.add("RECURSOS HUMANOS");
		sectionVariable.setValues(values);
		sectionVariables.add(sectionVariable);
		dataSection.setSectionVariables(sectionVariables);
		// ###############
		sectionVariable = SectionVariablesTO.builder()
				.cveVariable("VPRO_ALFANUMERICO_LISTBOX")
				.build();
		values = new ArrayList<String>();
		values.add("OPCIÓN 1");
		values.add("OPCIÓN 3");
		values.add("OPCIÓN 5");
		sectionVariable.setValues(values);
		sectionVariables.add(sectionVariable);
		dataSection.setSectionVariables(sectionVariables);
		// ###############
		sectionVariable = SectionVariablesTO.builder()
				.cveVariable("VPRO_ALFANUMERICO_CHECKBOX")
				.build();
		values = new ArrayList<String>();
		values.add("ROJO");
		values.add("AZUL");
		sectionVariable.setValues(values);
		sectionVariables.add(sectionVariable);
		dataSection.setSectionVariables(sectionVariables);
		// ###############
		sectionVariable = SectionVariablesTO.builder()
				.cveVariable("VPRO_ALFANUMERICO_RADIOBUTTON")
				.build();
		values = new ArrayList<String>();
		values.add("ZONA CENTRO");
		sectionVariable.setValues(values);
		sectionVariables.add(sectionVariable);
		dataSection.setSectionVariables(sectionVariables);
		// ###############
		dataOccurrence.add(dataSection);
		// <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		ds = DataSectionTO.builder()
				.cveSection("SECUENCIAL_ALFANUMERICOS_CON_VARIANTES")
				.type("SECUENCIAL")
				.content("VARIABLES")
				.dataOccurrence(dataOccurrence)
				.build();
		data.add(ds);
		// -\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/
		// _/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\
		dataOccurrence = new LinkedList<DataOccurrenceTO>();
		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		dataSection = DataOccurrenceTO.builder()
				.build();
		sectionVariables = new ArrayList<SectionVariablesTO>();
		// ###############
		sectionVariable = SectionVariablesTO.builder()
				.cveVariable("VPRO_ENTERO_CAJA_TEXTO")
				.build();
		values = new ArrayList<String>();
		values.add("99999");
		sectionVariable.setValues(values);
		sectionVariables.add(sectionVariable);
		dataSection.setSectionVariables(sectionVariables);
		// ###############
		sectionVariable = SectionVariablesTO.builder()
				.cveVariable("VPRO_ENTERO_COMBOBOX")
				.build();
		values = new ArrayList<String>();
		values.add("5");
		sectionVariable.setValues(values);
		sectionVariables.add(sectionVariable);
		dataSection.setSectionVariables(sectionVariables);
		// ###############
		sectionVariable = SectionVariablesTO.builder()
				.cveVariable("VPRO_ENTERO_LISTBOX")
				.build();
		values = new ArrayList<String>();
		values.add("50");
		values.add("75");
		sectionVariable.setValues(values);
		sectionVariables.add(sectionVariable);
		dataSection.setSectionVariables(sectionVariables);
		// ###############
		sectionVariable = SectionVariablesTO.builder()
				.cveVariable("VPRO_ENTERO_CHECKBOX")
				.build();
		values = new ArrayList<String>();
		values.add("3");
		sectionVariable.setValues(values);
		sectionVariables.add(sectionVariable);
		dataSection.setSectionVariables(sectionVariables);
		// ###############
		sectionVariable = SectionVariablesTO.builder()
				.cveVariable("VPRO_ENTERO_RADIOBUTTON")
				.build();
		values = new ArrayList<String>();
		values.add("200");
		sectionVariable.setValues(values);
		sectionVariables.add(sectionVariable);
		dataSection.setSectionVariables(sectionVariables);
		// ###############
		dataOccurrence.add(dataSection);
		// <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		ds = DataSectionTO.builder()
				.cveSection("SECUENCIAL_ENTEROS_CON_VARIANTES")
				.type("SECUENCIAL")
				.content("VARIABLES")
				.dataOccurrence(dataOccurrence)
				.build();
		data.add(ds);
		// -\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/
		// _/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\
		dataOccurrence = new LinkedList<DataOccurrenceTO>();
		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		dataSection = DataOccurrenceTO.builder()
				.build();
		sectionVariables = new ArrayList<SectionVariablesTO>();
		// ###############
		sectionVariable = SectionVariablesTO.builder()
				.cveVariable("VPRO_DECIMAL_CAJA_TEXTO")
				.build();
		values = new ArrayList<String>();
		values.add("999.99");
		sectionVariable.setValues(values);
		sectionVariables.add(sectionVariable);
		dataSection.setSectionVariables(sectionVariables);
		// ###############
		sectionVariable = SectionVariablesTO.builder()
				.cveVariable("VPRO_FECHA_CAJA_TEXTO")
				.build();
		values = new ArrayList<String>();
		values.add("07/07/2020");
		sectionVariable.setValues(values);
		sectionVariables.add(sectionVariable);
		dataSection.setSectionVariables(sectionVariables);
		// ###############
		dataOccurrence.add(dataSection);
		// <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		ds = DataSectionTO.builder()
				.cveSection("SECUENCIAL_DECIMAL_Y_FECHA")
				.type("SECUENCIAL")
				.content("VARIABLES")
				.dataOccurrence(dataOccurrence)
				.build();
		data.add(ds);
		// -\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/
		// _/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\
		dataOccurrence = new LinkedList<DataOccurrenceTO>();
		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		dataSection = DataOccurrenceTO.builder()
				.build();
		sectionVariables = new ArrayList<SectionVariablesTO>();
		// ###############
		sectionVariable = SectionVariablesTO.builder()
				.cveVariable("VPRO_GRID1_NUMERO_CONTROL")
				.build();
		values = new ArrayList<String>();
		values.add("1");
		sectionVariable.setValues(values);
		sectionVariables.add(sectionVariable);
		dataSection.setSectionVariables(sectionVariables);
		// ###############
		sectionVariable = SectionVariablesTO.builder()
				.cveVariable("VPRO_GRID1_DESCRIPCION")
				.build();
		values = new ArrayList<String>();
		values.add("Descripcion 1");
		sectionVariable.setValues(values);
		sectionVariables.add(sectionVariable);
		dataSection.setSectionVariables(sectionVariables);
		// ###############
		sectionVariable = SectionVariablesTO.builder()
				.cveVariable("VPRO_GRID1_ENTERO_CAJA_TEXTO")
				.build();
		values = new ArrayList<String>();
		values.add("111");
		sectionVariable.setValues(values);
		sectionVariables.add(sectionVariable);
		dataSection.setSectionVariables(sectionVariables);
		// ###############
		sectionVariable = SectionVariablesTO.builder()
				.cveVariable("VPRO_GRID1_FECHA_CAJA_TEXTO")
				.build();
		values = new ArrayList<String>();
		values.add("07/07/2020");
		sectionVariable.setValues(values);
		sectionVariables.add(sectionVariable);
		dataSection.setSectionVariables(sectionVariables);
		// ###############
		sectionVariable = SectionVariablesTO.builder()
				.cveVariable("VPRO_GRID1_DECIMAL_CAJA_TEXTO")
				.build();
		values = new ArrayList<String>();
		values.add("999.00");
		sectionVariable.setValues(values);
		sectionVariables.add(sectionVariable);
		dataSection.setSectionVariables(sectionVariables);
		// ###############
		sectionVariable = SectionVariablesTO.builder()
				.cveVariable("VPRO_GRID1_ALFANUMERICO_RADIOBUTTON")
				.build();
		values = new ArrayList<String>();
		values.add("NO");
		sectionVariable.setValues(values);
		sectionVariables.add(sectionVariable);
		//
		dataSection.setSectionVariables(sectionVariables);
		// ###############
		dataOccurrence.add(dataSection);
		// <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		dataSection = DataOccurrenceTO.builder()
				.build();
		sectionVariables = new ArrayList<SectionVariablesTO>();
		// ###############
		sectionVariable = SectionVariablesTO.builder()
				.cveVariable("VPRO_GRID1_NUMERO_CONTROL")
				.build();
		values = new ArrayList<String>();
		values.add("2");
		sectionVariable.setValues(values);
		sectionVariables.add(sectionVariable);
		dataSection.setSectionVariables(sectionVariables);
		// ###############
		sectionVariable = SectionVariablesTO.builder()
				.cveVariable("VPRO_GRID1_DESCRIPCION")
				.build();
		values = new ArrayList<String>();
		values.add("Descripcion 2");
		sectionVariable.setValues(values);
		sectionVariables.add(sectionVariable);
		dataSection.setSectionVariables(sectionVariables);
		// ###############
		sectionVariable = SectionVariablesTO.builder()
				.cveVariable("VPRO_GRID1_ENTERO_CAJA_TEXTO")
				.build();
		values = new ArrayList<String>();
		values.add("222");
		sectionVariable.setValues(values);
		sectionVariables.add(sectionVariable);
		dataSection.setSectionVariables(sectionVariables);
		// ###############
		sectionVariable = SectionVariablesTO.builder()
				.cveVariable("VPRO_GRID1_FECHA_CAJA_TEXTO")
				.build();
		values = new ArrayList<String>();
		values.add("08/07/2020");
		sectionVariable.setValues(values);
		sectionVariables.add(sectionVariable);
		dataSection.setSectionVariables(sectionVariables);
		// ###############
		sectionVariable = SectionVariablesTO.builder()
				.cveVariable("VPRO_GRID1_DECIMAL_CAJA_TEXTO")
				.build();
		values = new ArrayList<String>();
		values.add("123.45");
		sectionVariable.setValues(values);
		sectionVariables.add(sectionVariable);
		dataSection.setSectionVariables(sectionVariables);
		// ###############
		sectionVariable = SectionVariablesTO.builder()
				.cveVariable("VPRO_GRID1_ALFANUMERICO_RADIOBUTTON")
				.build();
		values = new ArrayList<String>();
		values.add("SI");
		sectionVariable.setValues(values);
		sectionVariables.add(sectionVariable);
		//
		dataSection.setSectionVariables(sectionVariables);
		// ###############
		dataOccurrence.add(dataSection);
		// <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		ds = DataSectionTO.builder()
				.cveSection("G1_E_C-A_C-E_C-F-CA-D_CL-A_R")
				.type("GRID")
				.content("VARIABLES")
				.dataOccurrence(dataOccurrence)
				.build();
		data.add(ds);
		// -\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/
		// _/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\
		dataOccurrence = new LinkedList<DataOccurrenceTO>();
		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		dataSection = DataOccurrenceTO.builder()
				.build();
		sectionVariables = new ArrayList<SectionVariablesTO>();
		// ###############
		sectionVariable = SectionVariablesTO.builder()
				.cveVariable("VPRO_GRID2_NUMERO_CONTROL")
				.build();
		values = new ArrayList<String>();
		values.add("101");
		sectionVariable.setValues(values);
		sectionVariables.add(sectionVariable);
		dataSection.setSectionVariables(sectionVariables);
		// ###############
		sectionVariable = SectionVariablesTO.builder()
				.cveVariable("VPRO_GRID2_ALFANUMERICO_RADIOBUTTON")
				.build();
		values = new ArrayList<String>();
		values.add("NEGRO");
		sectionVariable.setValues(values);
		sectionVariables.add(sectionVariable);
		dataSection.setSectionVariables(sectionVariables);
		// ###############
		sectionVariable = SectionVariablesTO.builder()
				.cveVariable("VPRO_GRID2_ALFANUMERICO_CHECKBOX")
				.build();
		values = new ArrayList<String>();
		values.add("FRUTAS");
		values.add("CARNE");
		sectionVariable.setValues(values);
		sectionVariables.add(sectionVariable);
		dataSection.setSectionVariables(sectionVariables);
		// ###############
		sectionVariable = SectionVariablesTO.builder()
				.cveVariable("VPRO_GRID2_PAIS_NACIMIENTO_COMBOBOX")
				.build();
		values = new ArrayList<String>();
		values.add("MEXICO");
		sectionVariable.setValues(values);
		sectionVariables.add(sectionVariable);
		dataSection.setSectionVariables(sectionVariables);
		// ###############
		sectionVariable = SectionVariablesTO.builder()
				.cveVariable("VPRO_GRID2_DEPORTES_PRACTICADOS_LISTBOX")
				.build();
		values = new ArrayList<String>();
		values.add("FUTBOL");
		values.add("Raquetbol");
		values.add("Beisbol");
		sectionVariable.setValues(values);
		sectionVariables.add(sectionVariable);
		dataSection.setSectionVariables(sectionVariables);
		// ###############
		sectionVariable = SectionVariablesTO.builder()
				.cveVariable("VPRO_GRID2_ENTERO_COMBOBOX")
				.build();
		values = new ArrayList<String>();
		values.add("1000");
		sectionVariable.setValues(values);
		sectionVariables.add(sectionVariable);
		dataSection.setSectionVariables(sectionVariables);
		// ###############
		sectionVariable = SectionVariablesTO.builder()
				.cveVariable("VPRO_GRID2_ENTERO_LISTBOX")
				.build();
		values = new ArrayList<String>();
		values.add("2");
		sectionVariable.setValues(values);
		sectionVariables.add(sectionVariable);
		dataSection.setSectionVariables(sectionVariables);
		// ###############
				sectionVariable = SectionVariablesTO.builder()
				.cveVariable("VPRO_GRID2_DECIMAL_CAJA_TEXTO")
				.build();
		values = new ArrayList<String>();
		values.add("987.65");
		sectionVariable.setValues(values);
		sectionVariables.add(sectionVariable);
		dataSection.setSectionVariables(sectionVariables);
		// ###############
		dataOccurrence.add(dataSection);
		// <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		dataSection = DataOccurrenceTO.builder()
				.build();
		sectionVariables = new ArrayList<SectionVariablesTO>();
		// ###############
		sectionVariable = SectionVariablesTO.builder()
				.cveVariable("VPRO_GRID2_NUMERO_CONTROL")
				.build();
		values = new ArrayList<String>();
		values.add("202");
		sectionVariable.setValues(values);
		sectionVariables.add(sectionVariable);
		dataSection.setSectionVariables(sectionVariables);
		// ###############
		sectionVariable = SectionVariablesTO.builder()
				.cveVariable("VPRO_GRID2_ALFANUMERICO_RADIOBUTTON")
				.build();
		values = new ArrayList<String>();
		values.add("BLANCO");
		sectionVariable.setValues(values);
		sectionVariables.add(sectionVariable);
		dataSection.setSectionVariables(sectionVariables);
		// ###############
		sectionVariable = SectionVariablesTO.builder()
				.cveVariable("VPRO_GRID2_ALFANUMERICO_CHECKBOX")
				.build();
		values = new ArrayList<String>();
		values.add("VERDURAS");
		values.add("HUEVO");
		sectionVariable.setValues(values);
		sectionVariables.add(sectionVariable);
		dataSection.setSectionVariables(sectionVariables);
		// ###############
		sectionVariable = SectionVariablesTO.builder()
				.cveVariable("VPRO_GRID2_PAIS_NACIMIENTO_COMBOBOX")
				.build();
		values = new ArrayList<String>();
		values.add("ESTADOS UNIDOS");
		sectionVariable.setValues(values);
		sectionVariables.add(sectionVariable);
		dataSection.setSectionVariables(sectionVariables);
		// ###############
		sectionVariable = SectionVariablesTO.builder()
				.cveVariable("VPRO_GRID2_DEPORTES_PRACTICADOS_LISTBOX")
				.build();
		values = new ArrayList<String>();
		values.add("Basquetbol");
		values.add("Natación");
		sectionVariable.setValues(values);
		sectionVariables.add(sectionVariable);
		dataSection.setSectionVariables(sectionVariables);
		// ###############
		sectionVariable = SectionVariablesTO.builder()
				.cveVariable("VPRO_GRID2_ENTERO_COMBOBOX")
				.build();
		values = new ArrayList<String>();
		values.add("3000");
		sectionVariable.setValues(values);
		sectionVariables.add(sectionVariable);
		dataSection.setSectionVariables(sectionVariables);
		// ###############
		sectionVariable = SectionVariablesTO.builder()
				.cveVariable("VPRO_GRID2_ENTERO_LISTBOX")
				.build();
		values = new ArrayList<String>();
		values.add("3");
		sectionVariable.setValues(values);
		sectionVariables.add(sectionVariable);
		dataSection.setSectionVariables(sectionVariables);
		// ###############
				sectionVariable = SectionVariablesTO.builder()
				.cveVariable("VPRO_GRID2_DECIMAL_CAJA_TEXTO")
				.build();
		values = new ArrayList<String>();
		values.add("432.10");
		sectionVariable.setValues(values);
		sectionVariables.add(sectionVariable);
		dataSection.setSectionVariables(sectionVariables);
		// ###############
		dataOccurrence.add(dataSection);
		// <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		dataSection = DataOccurrenceTO.builder()
				.build();
		sectionVariables = new ArrayList<SectionVariablesTO>();
		// ###############
		sectionVariable = SectionVariablesTO.builder()
				.cveVariable("VPRO_GRID2_NUMERO_CONTROL")
				.build();
		values = new ArrayList<String>();
		values.add("303");
		sectionVariable.setValues(values);
		sectionVariables.add(sectionVariable);
		dataSection.setSectionVariables(sectionVariables);
		// ###############
		sectionVariable = SectionVariablesTO.builder()
				.cveVariable("VPRO_GRID2_ALFANUMERICO_RADIOBUTTON")
				.build();
		values = new ArrayList<String>();
		values.add("NEGRO");
		sectionVariable.setValues(values);
		sectionVariables.add(sectionVariable);
		dataSection.setSectionVariables(sectionVariables);
		// ###############
		sectionVariable = SectionVariablesTO.builder()
				.cveVariable("VPRO_GRID2_ALFANUMERICO_CHECKBOX")
				.build();
		values = new ArrayList<String>();
		values.add("FRUTAS");
		values.add("VERDURAS");
		values.add("CARNE");
		values.add("HUEVO");
		sectionVariable.setValues(values);
		sectionVariables.add(sectionVariable);
		dataSection.setSectionVariables(sectionVariables);
		// ###############
		sectionVariable = SectionVariablesTO.builder()
				.cveVariable("VPRO_GRID2_PAIS_NACIMIENTO_COMBOBOX")
				.build();
		values = new ArrayList<String>();
		values.add("OTRO");
		sectionVariable.setValues(values);
		sectionVariables.add(sectionVariable);
		dataSection.setSectionVariables(sectionVariables);
		// ###############
		sectionVariable = SectionVariablesTO.builder()
				.cveVariable("VPRO_GRID2_DEPORTES_PRACTICADOS_LISTBOX")
				.build();
		values = new ArrayList<String>();
		values.add("FUTBOL");
		values.add("Basquetbol");
		values.add("Raquetbol");
		values.add("Natación");
		values.add("Beisbol");
		sectionVariable.setValues(values);
		sectionVariables.add(sectionVariable);
		dataSection.setSectionVariables(sectionVariables);
		// ###############
		sectionVariable = SectionVariablesTO.builder()
				.cveVariable("VPRO_GRID2_ENTERO_COMBOBOX")
				.build();
		values = new ArrayList<String>();
		values.add("500");
		sectionVariable.setValues(values);
		sectionVariables.add(sectionVariable);
		dataSection.setSectionVariables(sectionVariables);
		// ###############
		sectionVariable = SectionVariablesTO.builder()
				.cveVariable("VPRO_GRID2_ENTERO_LISTBOX")
				.build();
		values = new ArrayList<String>();
		values.add("1");
		sectionVariable.setValues(values);
		sectionVariables.add(sectionVariable);
		dataSection.setSectionVariables(sectionVariables);
		// ###############
				sectionVariable = SectionVariablesTO.builder()
				.cveVariable("VPRO_GRID2_DECIMAL_CAJA_TEXTO")
				.build();
		values = new ArrayList<String>();
		values.add("567.89");
		sectionVariable.setValues(values);
		sectionVariables.add(sectionVariable);
		dataSection.setSectionVariables(sectionVariables);
		// ###############
		dataOccurrence.add(dataSection);
		// <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		ds = DataSectionTO.builder()
				.cveSection("G2_E_C-A_R-A_CH-A_CB-A_L-E_CB-E_L-D_C")
				.type("GRID")
				.content("VARIABLES")
				.dataOccurrence(dataOccurrence)
				.build();
		data.add(ds);
		// -\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/
		// _/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\
		dataOccurrence = new LinkedList<DataOccurrenceTO>();
		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		dataSection = DataOccurrenceTO.builder()
				.build();
		sectionVariables = new ArrayList<SectionVariablesTO>();
		// ###############
		sectionVariable = SectionVariablesTO.builder()
				.cveVariable("VPRO_01_SECUENCIA_TAREA")
				.build();
		values = new ArrayList<String>();
		values.add("1");
		sectionVariable.setValues(values);
		sectionVariables.add(sectionVariable);
		dataSection.setSectionVariables(sectionVariables);
		// ###############
		sectionVariable = SectionVariablesTO.builder()
				.cveVariable("VPRO_01_TAREA_COMPLETADA")
				.build();
		values = new ArrayList<String>();
		values.add("NO");
		sectionVariable.setValues(values);
		sectionVariables.add(sectionVariable);
		dataSection.setSectionVariables(sectionVariables);
		// ###############
		dataOccurrence.add(dataSection);
		// <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		dataSection = DataOccurrenceTO.builder()
				.build();
		sectionVariables = new ArrayList<SectionVariablesTO>();
		// ###############
		sectionVariable = SectionVariablesTO.builder()
				.cveVariable("VPRO_01_SECUENCIA_TAREA")
				.build();
		values = new ArrayList<String>();
		values.add("2");
		sectionVariable.setValues(values);
		sectionVariables.add(sectionVariable);
		dataSection.setSectionVariables(sectionVariables);
		// ###############
		sectionVariable = SectionVariablesTO.builder()
				.cveVariable("VPRO_01_TAREA_COMPLETADA")
				.build();
		values = new ArrayList<String>();
		values.add("SI");
		sectionVariable.setValues(values);
		sectionVariables.add(sectionVariable);
		dataSection.setSectionVariables(sectionVariables);
		// ###############
		dataOccurrence.add(dataSection);
		// <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		dataSection = DataOccurrenceTO.builder()
				.build();
		sectionVariables = new ArrayList<SectionVariablesTO>();
		// ###############
		sectionVariable = SectionVariablesTO.builder()
				.cveVariable("VPRO_01_SECUENCIA_TAREA")
				.build();
		values = new ArrayList<String>();
		values.add("3");
		sectionVariable.setValues(values);
		sectionVariables.add(sectionVariable);
		dataSection.setSectionVariables(sectionVariables);		
		// ###############
		sectionVariable = SectionVariablesTO.builder()
				.cveVariable("VPRO_01_TAREA_COMPLETADA")
				.build();
		values = new ArrayList<String>();
		values.add("NO");
		sectionVariable.setValues(values);
		sectionVariables.add(sectionVariable);
		dataSection.setSectionVariables(sectionVariables);
		// ###############
		dataOccurrence.add(dataSection);
		// <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		dataSection = DataOccurrenceTO.builder()
				.build();
		sectionVariables = new ArrayList<SectionVariablesTO>();
		// ###############
		sectionVariable = SectionVariablesTO.builder()
				.cveVariable("VPRO_01_SECUENCIA_TAREA")
				.build();
		values = new ArrayList<String>();
		values.add("4");
		sectionVariable.setValues(values);
		sectionVariables.add(sectionVariable);
		dataSection.setSectionVariables(sectionVariables);
		// ###############
		sectionVariable = SectionVariablesTO.builder()
				.cveVariable("VPRO_01_TAREA_COMPLETADA")
				.build();
		values = new ArrayList<String>();
		values.add("SI");
		sectionVariable.setValues(values);
		sectionVariables.add(sectionVariable);
		dataSection.setSectionVariables(sectionVariables);
		// ###############
		dataOccurrence.add(dataSection);
		// <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		ds = DataSectionTO.builder()
				.cveSection("TAREAS DUMMY")
				.type("GRID")
				.content("TAREAS")
				.dataOccurrence(dataOccurrence)
				.build();
		data.add(ds);
		// -\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/
		// _/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\
		dataOccurrence = new LinkedList<DataOccurrenceTO>();
		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		dataSection = DataOccurrenceTO.builder()
				.build();
		sectionVariables = new ArrayList<SectionVariablesTO>();
		// ###############
		sectionVariable = SectionVariablesTO.builder()
				.cveVariable("VPRO_01_SECUENCIA_TAREA")
				.build();
		values = new ArrayList<String>();
		values.add("5");
		sectionVariable.setValues(values);
		sectionVariables.add(sectionVariable);
		dataSection.setSectionVariables(sectionVariables);
		// ###############
		sectionVariable = SectionVariablesTO.builder()
				.cveVariable("VPRO_01_TAREA_COMPLETADA")
				.build();
		values = new ArrayList<String>();
		values.add("SI");
		sectionVariable.setValues(values);
		sectionVariables.add(sectionVariable);
		dataSection.setSectionVariables(sectionVariables);
		// ###############
		dataOccurrence.add(dataSection);
		// <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		dataSection = DataOccurrenceTO.builder()
				.build();
		sectionVariables = new ArrayList<SectionVariablesTO>();
		// ###############
		sectionVariable = SectionVariablesTO.builder()
				.cveVariable("VPRO_01_SECUENCIA_TAREA")
				.build();
		values = new ArrayList<String>();
		values.add("6");
		sectionVariable.setValues(values);
		sectionVariables.add(sectionVariable);
		dataSection.setSectionVariables(sectionVariables);
		// ###############
		sectionVariable = SectionVariablesTO.builder()
				.cveVariable("VPRO_01_TAREA_COMPLETADA")
				.build();
		values = new ArrayList<String>();
		values.add("NO");
		sectionVariable.setValues(values);
		sectionVariables.add(sectionVariable);
		dataSection.setSectionVariables(sectionVariables);
		// ###############
		dataOccurrence.add(dataSection);
		// <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		dataSection = DataOccurrenceTO.builder()
				.build();
		sectionVariables = new ArrayList<SectionVariablesTO>();
		// ###############
		sectionVariable = SectionVariablesTO.builder()
				.cveVariable("VPRO_01_SECUENCIA_TAREA")
				.build();
		values = new ArrayList<String>();
		values.add("7");
		sectionVariable.setValues(values);
		sectionVariables.add(sectionVariable);
		dataSection.setSectionVariables(sectionVariables);		
		// ###############
		sectionVariable = SectionVariablesTO.builder()
				.cveVariable("VPRO_01_TAREA_COMPLETADA")
				.build();
		values = new ArrayList<String>();
		values.add("SI");
		sectionVariable.setValues(values);
		sectionVariables.add(sectionVariable);
		dataSection.setSectionVariables(sectionVariables);
		// ###############
		dataOccurrence.add(dataSection);
		// <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		ds = DataSectionTO.builder()
				.cveSection("TAREAS_DUMMY_2")
				.type("GRID")
				.content("TAREAS")
				.dataOccurrence(dataOccurrence)
				.build();
		data.add(ds);
		// -\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/-\_/
		// &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
		SaveSectionTO dataSections = SaveSectionTO.builder()
				.activity(ActividadTO.builderActividadTO()
					.cveRol("ROL DUMMY 1")
					.cveProceso("DUMMY")
					.version("1.00")
					.cveInstancia("202006-000003")
					.cveNodo("ACTIVIDAD-USUARIO")
					.idNodo(1)
					.secNodo(2)
					.cveAreaTrabajo("AREA_UNICA")
					.build())
				.data(data)
				.build();
		// &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
		return dataSections;
	}
}
