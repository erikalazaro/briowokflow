package com.briomax.briobpm.business.test.helper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.briomax.briobpm.business.helpers.base.IDashboardHelper;
import com.briomax.briobpm.business.helpers.base.IDashboardRepseHelper;
import com.briomax.briobpm.business.test.base.AbstractCoreTest;
import com.briomax.briobpm.common.core.Constants;
import com.briomax.briobpm.common.core.DAORet;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.transferobjects.app.GraficaTO;
import com.briomax.briobpm.transferobjects.app.Seccion;
import com.briomax.briobpm.transferobjects.in.DatoDashboardTO;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;
import com.briomax.briobpm.transferobjects.in.DatosContratista;
import com.briomax.briobpm.transferobjects.in.DatosContrato;
import com.briomax.briobpm.transferobjects.in.EstatusVariablesTO;
import com.briomax.briobpm.transferobjects.in.InNodoProcesoTO;
import com.briomax.briobpm.transferobjects.in.SeccionDashboardTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DashboardHelperTest extends AbstractCoreTest{
	
	@Autowired
	private IDashboardHelper helper;
	
	@Autowired
	private IDashboardRepseHelper helperRepse;
	
	// FN_LEE_CATEGORIA_ACTIVIDAD
	@Ignore
	@Test
	public void leeCategoriaActividadTest() throws BrioBPMException {
		log.info("INICIA TEST FN_LEE_CATEGORIA_ACTIVIDAD");
		
		// Crear una instancia de Calendar para la fecha actual
        Calendar calendar = Calendar.getInstance();

        // Obtener la fecha actual
        Date fechaActual = calendar.getTime();

        // Crear una fecha límite (por ejemplo, 30 días después de la fecha actual)
        calendar.add(Calendar.DAY_OF_MONTH, 30);
        Date fechaLimite = calendar.getTime();

        // Crear una fecha de evaluación (por ejemplo, 15 días después de la fecha actual)
        calendar.setTime(fechaActual);
        calendar.add(Calendar.DAY_OF_MONTH, 15);
        Date fechaEvaluacion = calendar.getTime();
        
        
		// Ejecución del método a probar
		InNodoProcesoTO datos2 = InNodoProcesoTO.builder()
												.fechaCreacion(new Date())
												.fechaLimite(fechaLimite)
												.fechaEstadoActual(fechaActual)
												.build();
		
		String categoriaConsultar = "PREVIA";
		String estadoActividad = "PREVIA";
	

		
		Integer estatus = helper.fnLeeCategoriaActividad(estadoActividad, datos2, fechaEvaluacion, categoriaConsultar);
		log.info("Estatus de TEST FN_LEE_CATEGORIA_ACTIVIDAD", estatus);
		
		categoriaConsultar = "NUEVA";			
		fechaEvaluacion = new Date();

		estatus = helper.fnLeeCategoriaActividad(estadoActividad, datos2, fechaEvaluacion, categoriaConsultar);
		log.info("Estatus de TEST FN_LEE_CATEGORIA_ACTIVIDAD", estatus);
		
		
		estatus = helper.fnLeeCategoriaActividad("CANCELADA", datos2, fechaEvaluacion, "CANCELADA");
		log.info("Estatus de TEST FN_LEE_CATEGORIA_ACTIVIDAD", estatus);
		
		datos2.setFechaLimite(null);
		estatus = helper.fnLeeCategoriaActividad("EN_TIEMPO", datos2, fechaEvaluacion, "EN_TIEMPO");
		log.info("Estatus de TEST FN_LEE_CATEGORIA_ACTIVIDAD", estatus);
		
		datos2.setFechaLimite(fechaLimite);
		estatus = helper.fnLeeCategoriaActividad("REGISTRO", datos2, fechaEvaluacion, "EN_TIEMPO");
		log.info("Estatus de TEST FN_LEE_CATEGORIA_ACTIVIDAD", estatus);
		
		datos2.setFechaLimite(fechaLimite);
		fechaActual = fechaEvaluacion;
		estatus = helper.fnLeeCategoriaActividad("TERMINADA", datos2, fechaEvaluacion, "EN_TIEMPO");
		log.info("Estatus de TEST FN_LEE_CATEGORIA_ACTIVIDAD", estatus);
	}
	
	
	// SP_LEE_SECCIONES_DASHBOARD
	@Test
	public void leeSeccionesDashboardTest() throws BrioBPMException {
		log.info("INICIA SP_LEE_SECCIONES_DASHBOARD");
		
		List<SeccionDashboardTO> seccionDashboard = new ArrayList<SeccionDashboardTO>();
		List<String> datos = new ArrayList<String>();
		RetMsg estatus = helper.leeSeccionesDashboard(getDatosAutenticacionTO(), "MONITOR-COMPLIANCE-REPSE", seccionDashboard, true, 1);
		log.info("LISTA DE SECCIONES: {}  {} ",  seccionDashboard.size(), seccionDashboard.toString());
		log.info("Estatus de TEST SP_LEE_SECCIONES_DASHBOARD: {} ",  estatus.getStatus());
	}
	
	// SP_OBTEN_FECHA_PRIMER_DIA_MES
	@Test
	public void obtenFechaPrimerDiaMesTest() throws BrioBPMException, ParseException {
		log.info("INICIA SP_LEE_SECCIONES_DASHBOARD");
		
		DAORet<Date, RetMsg>  estatus = helper.obtenFechaPrimerDiaMes(getDatosAutenticacionTO(), 2, 2024);
		log.info("Estatus de TEST SP_OBTEN_FECHA_PRIMER_DIA_MES: {} {}",  estatus.getContent(), estatus.getMeta());
	}
	
	
	// SP_OBTEN_FECHA_ULTIMO_DIA_MES
	@Test
	public void obtenFechaUltimoDiaMesTest() throws BrioBPMException, ParseException {
		log.info("INICIA SP_LEE_SECCIONES_DASHBOARD");
		
		DAORet<Date, RetMsg>  estatus = helper.obtenFechaUltimoDiaMes(getDatosAutenticacionTO(), 2, 2024);
		log.info("Estatus de TEST SP_OBTEN_FECHA_ULTIMO_DIA_MES: {} {}",  estatus.getContent(), estatus.getMeta());
	}
	
	
	// SP_LEE_VALOR_VARIABLE_ESPECIAL
	@Test
	public void leeValorVariableEspecialTest() throws BrioBPMException, ParseException {
		log.info("INICIA SP_LEE_VALOR_VARIABLE_ESPECIAL");
		String cveVariable = "FECHA_ACTUAL";
		EstatusVariablesTO  estatus = helper.leeValorVariableEspecial(getDatosAutenticacionTO(), cveVariable);
		log.info("---->>> FECHA: {} : {}", cveVariable ,estatus.getValorFecha());
		
		cveVariable = "DESCRIPCION_PROCESO";
		estatus = helper.leeValorVariableEspecial(getDatosAutenticacionTO(), cveVariable);
		log.info("---->>> DESCRIPCION_PROCESO: {} : {}", cveVariable ,estatus);

//		cveVariable = "FECHA_INI_MES_ACTUAL";
//		estatus = helper.leeValorVariableEspecial(getDatosAutenticacionTO(), cveVariable);
//		log.info("---->>> FECHA: {} : {}", cveVariable ,estatus.getValorFecha());
//		
//		cveVariable = "FECHA_FIN_MES_ACTUAL";
//		estatus = helper.leeValorVariableEspecial(getDatosAutenticacionTO(), cveVariable);
//		log.info("---->>> FECHA: {} : {}", cveVariable ,estatus.getValorFecha());
//		
//		cveVariable = "FECHA_INI_MES_ANTERIOR";
//		estatus = helper.leeValorVariableEspecial(getDatosAutenticacionTO(), cveVariable);
//		log.info("---->>> FECHA: {} : {}", cveVariable ,estatus.getValorFecha());
//		
//		cveVariable = "FECHA_FIN_MES_ANTERIOR";
//		estatus = helper.leeValorVariableEspecial(getDatosAutenticacionTO(), cveVariable);
//		log.info("---->>> FECHA: {} : {}", cveVariable ,estatus.getValorFecha());
//		
//		cveVariable = "FECHA_INI_ANIO_ACTUAL";
//		estatus = helper.leeValorVariableEspecial(getDatosAutenticacionTO(), cveVariable);
//		log.info("---->>> FECHA: {} : {}", cveVariable ,estatus.getValorFecha());
//
//		cveVariable = "FECHA_FIN_ANIO_ACTUAL";
//		estatus = helper.leeValorVariableEspecial(getDatosAutenticacionTO(), cveVariable);
//		log.info("---->>> FECHA: {} : {}", cveVariable ,estatus.getValorFecha());
//		
//		cveVariable = "FECHA_INI_ANIO_ANTERIOR";
//		estatus = helper.leeValorVariableEspecial(getDatosAutenticacionTO(), cveVariable);
//		log.info("---->>> FECHA: {} : {}", cveVariable ,estatus.getValorFecha());
//		
//		cveVariable = "FECHA_FIN_ANIO_ANTERIOR";
//		estatus = helper.leeValorVariableEspecial(getDatosAutenticacionTO(), cveVariable);
//		log.info("---->>> FECHA: {} : {}", cveVariable ,estatus.getValorFecha());
//		
//		cveVariable = "FEC_FIN_MES_FEC_ACT_MENOS_10_MESES";
//		estatus = helper.leeValorVariableEspecial(getDatosAutenticacionTO(), cveVariable);
//		log.info("---->>> FECHA: {} : {}", cveVariable ,estatus.getValorFecha());
//		
//		cveVariable = "FEC_INI_MES_FEC_ACT_MENOS_1_MESES";
//		estatus = helper.leeValorVariableEspecial(getDatosAutenticacionTO(), cveVariable);
//		log.info("---->>> FECHA: {} : {}", cveVariable ,estatus.getValorFecha());
//		
//		cveVariable = "FECHA_ACTUAL_MENOS_5_SEMANAS";
//		estatus = helper.leeValorVariableEspecial(getDatosAutenticacionTO(), cveVariable);
//		log.info("---->>> FECHA: {} : {}", cveVariable ,estatus.getValorFecha());
//		
//		cveVariable = "FECHA_ACTUAL_MENOS_12_MESES";
//		estatus = helper.leeValorVariableEspecial(getDatosAutenticacionTO(), cveVariable);
//		log.info("---->>> FECHA: {} : {}", cveVariable ,estatus.getValorFecha());
//		
//		cveVariable = "FEC_FIN_SEM_FEC_ACT_MENOS_5_IL";
//		estatus = helper.leeValorVariableEspecial(getDatosAutenticacionTO(), cveVariable);
//		log.info("---->>> FECHA: {} : {}", cveVariable ,estatus.getValorFecha());
//		
//		cveVariable = "FEC_FIN_SEM_FEC_ACT_MENOS_10_ID";
//		estatus = helper.leeValorVariableEspecial(getDatosAutenticacionTO(), cveVariable);
//		log.info("---->>> FECHA: {} : {}", cveVariable ,estatus.getValorFecha());
//		
//		cveVariable = "FEC_INI_SEM_FEC_ACT_MENOS_5_IL";
//		estatus = helper.leeValorVariableEspecial(getDatosAutenticacionTO(), cveVariable);
//		log.info("---->>> FECHA: {} : {}", cveVariable ,estatus.getValorFecha());
//		
//		cveVariable = "FEC_INI_SEM_FEC_ACT_MENOS_10_ID";
//		estatus = helper.leeValorVariableEspecial(getDatosAutenticacionTO(), cveVariable);
//		log.info("---->>> FECHA: {} : {}", cveVariable ,estatus.getValorFecha());
	}
	
	
	
	// SP_LEE_INFORMACION_DASHBOARD
	@Test
	public void leeInformacionDashboardTest() throws BrioBPMException, ParseException {
	    log.info("INICIA TEST: SP_LEE_INFORMACION_DASHBOARD");

	    // Datos de entrada
	    String cveDashboard = "MONITOR-COMPLIANCE-REPSE";
	    String destino = "WEB";

	    List<String> datos = new ArrayList<>();
	    
	    // Ejecución del método a probar
	    DAORet<List<DatoDashboardTO>, RetMsg> resultado = helper.leeInformacionDashboard(getDatosAutenticacionTO(), destino, cveDashboard, datos, true, 1);

	    // Validación de resultados
	    assertNotNull(resultado, "El resultado no debe ser nulo");
	    assertNotNull(resultado.getMeta(), "El objeto RetMsg no debe ser nulo");
	    assertEquals(Constants.OK, resultado.getMeta().getStatus(), "El estatus debe ser OK");
	    assertTrue(resultado.getContent() != null && !resultado.getContent().isEmpty(), "La lista de DatoDashboardTO no debe estar vacía");

	    // Logs adicionales
	    log.info("Estatus de TEST SP_LEE_INFORMACION_DASHBOARD: {} {}", resultado.getMeta().getStatus(), resultado.getMeta().getMessage());
	    log.info("Cantidad de datos obtenidos: {}", resultado.getContent().size());
	    
	    int d = 1;
	    for(DatoDashboardTO dato : resultado.getContent()) {
	    	log.info("DATOS {}: {}", d, dato.toString());
	    	d++;
	    }
	}
	
	
	
	
	@Test
	public void generaGraficoTest() throws ParseException {
	    log.info("INICIA TEST: generaGrafico");

	    // Datos de entrada
	    String destino = "WEB";
	    String cveDashboard = "MONITOR-COMPLIANCE-REPSE";

	    // Mock de DatosAutenticacionTO
	    DatosAutenticacionTO session = getDatosAutenticacionTO();
	    List<String> datos = new ArrayList<>();

	    // Mock del método leeInformacionDashboard (si usas un framework como Mockito)
	    DAORet<List<DatoDashboardTO>, RetMsg> mockInformacionDashboard = helper.leeInformacionDashboard(session, destino, cveDashboard,datos, true, 1); 

	    // Validaciones iniciales
	    assertNotNull(mockInformacionDashboard, "El resultado de leeInformacionDashboard no debe ser nulo");
	    assertTrue(mockInformacionDashboard.getContent() != null && !mockInformacionDashboard.getContent().isEmpty(), 
	            "La lista de DatoDashboardTO no debe estar vacía");

	    // Ejecución del método generaGrafico
	    DAORet<GraficaTO, RetMsg> resultadoGrafico = helper.generaGrafico(session, destino, cveDashboard, datos, true, 1);

	    // Validación de resultados
	    assertNotNull(resultadoGrafico, "El resultado de generaGrafico no debe ser nulo");
	    assertNotNull(resultadoGrafico.getMeta(), "El objeto RetMsg no debe ser nulo");
	    assertEquals(Constants.OK, resultadoGrafico.getMeta().getStatus(), "El estatus debe ser OK");
	    assertNotNull(resultadoGrafico.getContent(), "El objeto GraficaTO no debe ser nulo");
	    assertTrue(resultadoGrafico.getContent().getSections() != null && !resultadoGrafico.getContent().getSections().isEmpty(),
	            "La lista de secciones en GraficaTO no debe estar vacía");

	    // Logs adicionales
	    log.info("Estatus de TEST generaGrafico: {} {}", resultadoGrafico.getMeta().getStatus(), resultadoGrafico.getMeta().getMessage());
	    log.info("Cantidad de secciones generadas: {}", resultadoGrafico.getContent().getSections().size());

	    int index = 1;
	    for (Seccion seccion : resultadoGrafico.getContent().getSections()) {
	        log.info("SECCIÓN {}: {}", index, seccion.toString());
	        index++;
	    }
	}

	@Test
	public void obtieneGraficoPorRFCyNUMTest() throws ParseException {
	    log.info("INICIA TEST: obtieneGraficoPorRFCyNUM");

        // Datos de entrada
	    
	    GraficaTO graficaTO = helperRepse.obtieneGraficoPorRFCyNUM(getDatosAutenticacionTO(), "WEB",
	    		"MONITOR-COMPLIANCE-REPSE", "MIN220616APA", "AHA-EDIF-CAMILA-009-MYPO-2024", true, 3);
	    
	    int index = 1;
		for (Seccion seccion : graficaTO.getSections()) {
			log.info("SECCIÓN {}: {}",index ,seccion.toString());
			index++;
		}
	}
	
	
	@Test
	public void obtieneContratosPorRFCyNUMTest() throws ParseException {
        log.info("INICIA TEST: obtieneContratosPorRFCyNUM");
        
        List<DatosContrato> datosContrato = helperRepse.obtieneContratosPorRFCyNUM(getDatosAutenticacionTO(), "WEB", 
        		"MONITOR-COMPLIANCE-REPSE", true, 3);
        
        log.info("Tamaño de la lista: {}", datosContrato.size());
        
		for (DatosContrato dato : datosContrato) {
			log.info("DATOS: RFC: {} | NOMBRE: {}", dato.getRfc(), dato.getNombre());
			
			int index = 1;
			for (String numeroContrato : dato.getNumerosContrato()) {
				log.info("*-*-*-*-*-*-*-*- NUMERO DE CONTRATO {}: {}", index, numeroContrato );
				index++;
			}
			
			int index2 = 1;
			log.info("-------------Graficas del primer contrato----------------");
			for (Seccion seccion : dato.getGraficas().getSections()) {
				log.info(" * SECCIÓN {}: {}", index2 ,seccion.toString());
				index2++;
			}
			
		}
	}

	@Test
	public void obtieneContratosPorRFCTest() throws ParseException {
		log.info("INICIA TEST: obtieneContratosPorRFC");
        
        List<DatosContratista> datosContrato = helperRepse.obtieneContratosPorRFC(getDatosAutenticacionTO(), "WEB", 
                "MONITOR-COMPLIANCE-REPSE", true, 2);
        
        log.info("Tamaño de la lista: {}", datosContrato.size());
        
        for (DatosContratista dato : datosContrato) {
			log.info("DATOS: RAZON SOCIAL: {}" , dato.getRazonSocial());

			int index = 1;
			for (Seccion seccion : dato.getSections()) {
				log.info(" * SECCION {}: {}", index, seccion.toString());
				index++;
			}
        }
    }	

	
	@Test
	public void monitorAPP() throws ParseException {
		
        String destino = "APP";
        String cveDashboard = "MONITOR-COMPLIANCE-REPSE";
        
		DatosAutenticacionTO userSession = DatosAutenticacionTO.builder()
				.cveEntidad("BRIOMAX")
				.cveLocalidad("BRIOMAX-CENTRAL")
				.cveIdioma("ES-MX")
				.cveUsuario("AIT.Usuario.FieldService")
				.build();
		
        // Crear objeto GraficoTO
        log.info("Generando grafico para dashboard {} de destino {}", cveDashboard, destino);
        DAORet<GraficaTO, RetMsg> dashboard = helper.generaGrafico(userSession, destino, cveDashboard, null, true, 1);  
		
        log.info("GRAPHICS: {}", dashboard.getContent().toString());
        GraficaTO grafico = dashboard.getContent();
		if (grafico != null) {
			log.info("MODFIYING GRAPHICS");
	        int id = 1;
	        for (Seccion seccion : grafico.getSections()) {
	        	helper.procesarSeccion(seccion, id++);
	        }
		}
		
		log.info("GRAPHICS MODIFIED");
		log.info("GRAPHICS: {}", grafico.toString());
	}
}
