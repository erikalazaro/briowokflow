/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados. Este software contiene informacion
 * confidencial propiedad de Briomax Consulting por lo cual no puede ser reproducido, distribuido o alterado sin el
 * consentimiento previo de Briomax Consulting.
 */

package com.briomax.briobpm.business.service.test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.briomax.briobpm.business.service.core.IAppMovilService;
import com.briomax.briobpm.business.service.core.IFormularioDinamicoService;
import com.briomax.briobpm.business.test.base.AbstractCoreTest;
import com.briomax.briobpm.common.core.DAORet;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.transferobjects.DataOccurrenceTO;
import com.briomax.briobpm.transferobjects.DataSectionTO;
import com.briomax.briobpm.transferobjects.SaveSectionTO;
import com.briomax.briobpm.transferobjects.SectionVariablesTO;
import com.briomax.briobpm.transferobjects.dynamicForm.ColumnSection;
import com.briomax.briobpm.transferobjects.dynamicForm.FormularioDinamico;
import com.briomax.briobpm.transferobjects.dynamicForm.Section;
import com.briomax.briobpm.transferobjects.in.ActividadTO;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;
import com.briomax.briobpm.transferobjects.in.NodoTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Class FormDinVigDocsServiceTest.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Nov 9, 2020 7:53:59 PM Modificaciones:
 * @since JDK 1.8
 */
@Slf4j
public class GenerarJsonGuardarFormularioDinamicoTest extends AbstractCoreTest {

	/** El atributo o variable service. */
	@Autowired
	private IFormularioDinamicoService service;
	
	/** El atributo o variable app movil service. */
	@Autowired
	private IAppMovilService appMovilService;

	/**
	 * Obtener formulario dinamico test.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void generarJsonSaveFormularioDinamicoTest() throws BrioBPMException {
		DatosAutenticacionTO session = getDatosAutenticacionTO();
		//session.setCveUsuario("Francisco.Rodriguez");
		session.setCveUsuario("AIT.Usuario.Gerente");
		printerJson(session);
		ActividadTO actividad = geActividad();
		//actividad.setCveRol("COMERCIAL");
		actividad.setCveRol("ABOGADO");
		actividad.setCveProceso("G2 ACF");
		actividad.setCveNodo("ACTIVIDAD-USUARIO-TEMPORIZADOR");
		//actividad.setIdNodo(1);
		actividad.setIdNodo(1);
		actividad.setSecNodo(1);
		actividad.setCveInstancia("202012-000001");
		//actividad.setCveAreaTrabajo("AREA_SIN_SLA");
		actividad.setCveAreaTrabajo("AREA_CON_SLA");
		printerJson(actividad);
		DAORet<FormularioDinamico, RetMsg> metaData = null;
		try {
			metaData = service.obtenerFormularioDinamico(session, actividad);
		} catch (BrioBPMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FormularioDinamico formularioDinamico = metaData.getContent();
		for (Section section : formularioDinamico.getSections()) {
			log.info("{}", section);
		}
		printerJson(metaData.getContent());
		
		log.info("JSON GUARDAR ACTIVIDAD \n" +
			"======================================================================================================\n" +
			"======================================================================================================\n");
		SaveSectionTO dataSections = SaveSectionTO.builder()
				.activity(actividad)
				.build();

		List<DataSectionTO> data = new ArrayList<DataSectionTO>();
		for (Section section : formularioDinamico.getSections()) {
			String type = "";
			if (section.getSequential() != null) {
				type = "SEQUENTIAL";
			}
			if (section.getGrid() != null) {
				type = "GRID";
			}
			DataSectionTO seccion = DataSectionTO.builder()
					.content(section.getProperties().getContenido())
					.cveSection(section.getProperties().getCveSeccion())
					.type(type)
					.build();
			switch (type) {
				case "SEQUENTIAL":
					builderSequential(seccion, section);
					break;
				case "GRID":
					builderGrid(seccion, section);
					break;
				default:
					break;
			}
			data.add(seccion);
		}
		dataSections.setData(data);
		try {
			String json = mapper.writeValueAsString(dataSections);
			log.warn("JSON PARA GUARDAR ACTIVIDAD: {}", json);
			System.err.println(json);
		}
		catch (JsonProcessingException exJson) {
			log.error(exJson.getMessage());
		}
		printerJson(dataSections);
	}

	/**
	 * Builder sequential.
	 * @param seccion el seccion.
	 * @param section el section.
	 */
	private void builderSequential(DataSectionTO seccion, Section section) {
		List<DataOccurrenceTO> dataOccurrence = new ArrayList<DataOccurrenceTO>();
		List<SectionVariablesTO> sectionVariablesSave = new ArrayList<>();
		DataOccurrenceTO data = DataOccurrenceTO.builder().ocurrencia(section.getSequential().getOcurrencia()).build();
		for (ColumnSection item : section.getSequential().getColumns()) {
			if (item.getProperties().getEnvioGrabar().equalsIgnoreCase("SI")) {
				List<String> values = new ArrayList<>();
				try {
					values.add(item.getControl().getOptions().get(0).get(0));
				}
				catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
					// Valores Option
					values.add(null);
					log.error(e.getMessage());
				}
				sectionVariablesSave.add(SectionVariablesTO.builder()
					.cveVariable(item.getProperties().getCveVariable())
					.values(values)
					.build());
				data.setSectionVariables(sectionVariablesSave);
			}
		}
		dataOccurrence.add(data);
		seccion.setDataOccurrence(dataOccurrence);
	}

	/**
	 * Builder grid.
	 * @param seccion el seccion.
	 * @param section el section.
	 */
	private void builderGrid(DataSectionTO seccion, Section section) {
		/*List<SectionVariablesTOTest> sectionVariables = new ArrayList<>();
		int index = 0;
		for (ColumnGrid item : section.getGrid().getConfig().getColumns()) {
			SectionVariablesTOTest column =
					SectionVariablesTOTest.builder()
					.cveVariable(item.getProperties().getCveVariable())
					.envioGrabar(item.getProperties().getEnvioGrabar())
					.index(index)
					.build();
			sectionVariables.add(column);
			index++;
		}
		List<DataOccurrenceTO> dataOccurrence = new ArrayList<DataOccurrenceTO>();
		List<SectionVariablesTO> sectionVariablesSave = new ArrayList<>();
		for (Row row : section.getGrid().getTail()) {
			log.info("{}", row);
			DataOccurrenceTO data = DataOccurrenceTO.builder().ocurrencia(row.getOcurrencia()).build();
			sectionVariablesSave = new ArrayList<>();
			for (SectionVariablesTOTest itemTest : sectionVariables) {
				if (itemTest.getEnvioGrabar().equalsIgnoreCase("SI")) {
					List<String> values = new ArrayList<>();
					try {
						for (List<String> valores : row.getCells().get(itemTest.getIndex())) {
							try {
								values.add(valores.get(0));
							}
							catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
								// Valores Celda
								//values.add(null);
								log.error(e.getMessage());
							}
						}
					}
					catch (IndexOutOfBoundsException | NullPointerException e) {
						// Valores Columna
						//values.add(null);
						log.error(e.getMessage());
					}
					sectionVariablesSave.add(SectionVariablesTO.builder()
						.cveVariable(itemTest.getCveVariable())
						.values(values)
						.build());
				}
			}
			// Ocurrencia
			if (!sectionVariablesSave.isEmpty()) {
				data.setSectionVariables(sectionVariablesSave);
				dataOccurrence.add(data);
			}
		}
		seccion.setDataOccurrence(dataOccurrence);*/
	}

	/**
	 * Test guardar actividad.
	 * @throws JsonProcessingException 
	 * @throws JsonMappingException 
	 * @throws ParseException 
	 * @throws BrioBPMException 
	 */
//	@Test
//	//@Rollback(value = false)
//	public void testGuardarActividad() throws JsonMappingException, JsonProcessingException, BrioBPMException, ParseException {
//	    DatosAutenticacionTO session = getDatosAutenticacionTO();
//	    session.setCveUsuario("AIT.Usuario.Cliente");
//	    printerJson(session);
//
//	    // JSON STRING con los datos requeridos
//	    String jsonString = "{"
//	            + "\"activity\": {"
//	            + "    \"cveRol\": \"CLIENTE\","
//	            + "    \"cveProceso\": \"ATEN_INCI_TICK\","
//	            + "    \"version\": \"1.00\","
//	            + "    \"cveNodo\": \"ACTIVIDAD-USUARIO\","
//	            + "    \"idNodo\": 1,"
//	            + "    \"cveAreaTrabajo\": \"CRE_FOL\","
//	            + "    \"cveInstancia\": \"202407-000033\","
//	            + "    \"secNodo\": 0"
//	            + "},"
//	            + "\"data\": [{"
//	            + "    \"cveSection\": \"CREA_FOLI\","
//	            + "    \"type\": \"SECUENCIAL\","
//	            + "    \"content\": \"VARIABLES\","
//	            + "    \"dataOccurrence\": [{"
//	            + "        \"ocurrencia\": 1,"
//	            + "        \"nueva\": false,"
//	            + "        \"sectionVariables\": [{"
//	            + "            \"cveVariable\": \"VPRO_01_NOM_TIENDA\","
//	            + "            \"values\": [\"nueva tienda\"]"
//	            + "        }, {"
//	            + "            \"cveVariable\": \"VPRO_01_CR\","
//	            + "            \"values\": [\"cr tienda\"]"
//	            + "        }, {"
//	            + "            \"cveVariable\": \"VPRO_01_NUM_INCIDENTE\","
//	            + "            \"values\": [\"2332\"]"
//	            + "        }, {"
//	            + "            \"cveVariable\": \"VPRO_01_NOM_CLIE\","
//	            + "            \"values\": [\"oxxo\"]"
//	            + "        }, {"
//	            + "            \"cveVariable\": \"VPRO_01_TIPO_FOLIO_A\","
//	            + "            \"values\": [\"AVOPV_INHIBICION_DE_EQUIPO_CAJA_3\"]"
//	            + "        }, {"
//	            + "            \"cveVariable\": \"VPRO_01_OBSER_A\","
//	            + "            \"values\": [\"aqui hay info\"]"
//	            + "        }]"
//	            + "    }]"
//	            + "}]"
//	            + "}";
//
//	    // Convertir JSON a objeto SaveSectionTO
//	    SaveSectionTO data = mapper.readValue(jsonString, SaveSectionTO.class);
//	    printerJson(data);
//	    
//	    // inicia test
//	    RetMsg response = ejecucionActividadService.guardarActividad(session, data);
//	    log.info(" GUARDAR ACTIVIDAD: {}", response);
//	    printerJson(response);
//	    if (response.getStatus().equalsIgnoreCase("ERROR")) {
//	        fail("Fail Guardar Actividad");
//	    }
//	}

	
	@Test
	public void testObtenerFormularioDinamicoMovil() throws BrioBPMException {
		DatosAutenticacionTO session = getDatosAutenticacionTO();
		session.setCveUsuario("AIT.Usuario.FieldService");
		printerJson(session);
		NodoTO actividad = new NodoTO();
		actividad.setCveProceso("ATEN_INCI_TICK");
		actividad.setCveInstancia("202502-000001");
		actividad.setCveNodo("ACTIVIDAD-USUARIO");
		actividad.setIdNodo(4);
		actividad.setUsoSeccion("APP");
		actividad.setVersion(BigDecimal.valueOf(1.00));
				
		printerJson(actividad);
		com.briomax.briobpm.transferobjects.app.ActividadTO metaData = null;
		try {
			metaData = appMovilService.obtenerFormularioDinamicoMovil(session, actividad);
		} catch (BrioBPMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		log.info("----META DATA : {} ", metaData);
		;
		printerJson(metaData);
	}
	
	@Test
	public void testObtenerFormularioDinamicoMoviFieldService() throws BrioBPMException {
		DatosAutenticacionTO session = getDatosAutenticacionTO();
		session.setCveUsuario("AIT.Usuario.FieldService");
		printerJson(session);
		NodoTO actividad = new NodoTO();
		actividad.setCveProceso("ATEN_INCI_TICK");
		actividad.setCveInstancia("202503-000010");
		actividad.setCveNodo("ACTIVIDAD-USUARIO");
		actividad.setIdNodo(4);
		actividad.setUsoSeccion("APP");
		actividad.setVersion(BigDecimal.valueOf(1.00));
				
		printerJson(actividad);
		com.briomax.briobpm.transferobjects.app.ActividadTO metaData = null;
		try {
			metaData = appMovilService.obtenerFormularioDinamicoMovil(session, actividad);
		} catch (BrioBPMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		log.info("----META DATA : {} ", metaData);
		;
		printerJson(metaData);
	}
	
	@Test
	public void testObtenerFormularioDinamicoMoviCliente() throws BrioBPMException {
		DatosAutenticacionTO session = getDatosAutenticacionTO();
		session.setCveUsuario("AIT.Usuario.Cliente");
		printerJson(session);
		NodoTO actividad = new NodoTO();
		actividad.setCveProceso("ATEN_INCI_TICK");
		actividad.setCveInstancia("202502-000008");
		actividad.setCveNodo("ACTIVIDAD-USUARIO");
		actividad.setIdNodo(1);
		actividad.setUsoSeccion("APP");
		actividad.setVersion(BigDecimal.valueOf(1.00));
				
		printerJson(actividad);
		com.briomax.briobpm.transferobjects.app.ActividadTO metaData = null;
		try {
			metaData = appMovilService.obtenerFormularioDinamicoMovil(session, actividad);
		} catch (BrioBPMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		log.info("----META DATA CLIENTE : {} ", metaData);
		;
		printerJson(metaData);
	}
	
	@Test
	public void testObtenerFormularioDinamicoMoviGerente() throws BrioBPMException {
		DatosAutenticacionTO session = getDatosAutenticacionTO();
		session.setCveUsuario("AIT.Usuario.Gerente");
		printerJson(session);
		NodoTO actividad = new NodoTO();
		actividad.setCveProceso("ATEN_INCI_TICK");
		actividad.setCveInstancia("202503-000002");
		actividad.setCveNodo("ACTIVIDAD-USUARIO-TEMPORIZACION");
		actividad.setIdNodo(2);
		actividad.setUsoSeccion("APP");
		actividad.setVersion(BigDecimal.valueOf(1.00));
				
		printerJson(actividad);
		com.briomax.briobpm.transferobjects.app.ActividadTO metaData = null;
		try {
			metaData = appMovilService.obtenerFormularioDinamicoMovil(session, actividad);
		} catch (BrioBPMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		log.info("----META DATA GERENTE : {} ", metaData);
		;
		printerJson(metaData);
	}
	
	
	@Test
	public void testGuardarFomularioDinamicoMovil() {
		
	}
}
