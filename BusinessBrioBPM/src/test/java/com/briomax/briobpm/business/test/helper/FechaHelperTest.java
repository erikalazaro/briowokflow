package com.briomax.briobpm.business.test.helper;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.briomax.briobpm.business.helpers.FechaHelper;
import com.briomax.briobpm.business.test.base.AbstractCoreTest;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.transferobjects.CalendarioLocalidadTO;
import com.briomax.briobpm.transferobjects.in.CalculoFechaLimiteTO;
import com.briomax.briobpm.transferobjects.in.CalculoFechaMasPlazoTO;
import com.briomax.briobpm.transferobjects.in.EstatusFechaTO;
import com.briomax.briobpm.transferobjects.in.EstatusTO;
import com.briomax.briobpm.transferobjects.repse.IntervaloFechaTO;
import com.briomax.briobpm.transferobjects.repse.OrdenFechaIntervalosTO;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Class FechaHelperTest.java es ...
 * 
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Ene 17, 2024 11:50:00 AM Modificaciones:
 * @since JDK 1.8
 */
@Slf4j
public class FechaHelperTest extends AbstractCoreTest {

	@Autowired
	private FechaHelper fechaHelper;
	
	@Test
	public void leeIntervaloFechasRepseTest() throws BrioBPMException, ParseException {
		log.info("INICIA TEST SP_LEE_INTERVALO_FECHAS");
		// Ejecución del método a probar
        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.NOVEMBER, 01); // OJO: los meses empiezan en 0 (enero = 0)

        Date desde = calendar.getTime();
        
        calendar.set(2026, Calendar.MARCH, 8);
        Date hasta = calendar.getTime();
        
		IntervaloFechaTO intervaloFechas = IntervaloFechaTO.builder()
				.cveProceso("SUA_MENSUAL")
				.version(new BigDecimal(1))
				.cadaNperiodos(0)
				.periodoTiempo("MES")
				.detallePeriodo("U")
				.diasDeLaSemana(null)
				.diasDelMes("8")
				.tipoDia("EXACTO")
				.desde(desde)//new Date()
				.hasta(hasta)
				.build(); 
		
		List<OrdenFechaIntervalosTO> result = fechaHelper.leeIntervaloFechaRepse("BRIOMAX", intervaloFechas, "NO");
		for (OrdenFechaIntervalosTO ordenFechaIntervalosTO : result) {
			log.info(">>>>>>>>>>>>>>>>>>>>>>result ULTIMO NO aplica inicio:{} {}",  converterJsonAsString(ordenFechaIntervalosTO));
		}	
		
		List<OrdenFechaIntervalosTO> resultfechas = fechaHelper.generaFechasIntervaloRepse(intervaloFechas, result);
		
		List<Date> fechaRepeticion =  fechaHelper.ajustaFechasIntervalo("BRIOMAX", "BRIOMAX-CENTRAL", "ES-MX", "ACUSE_ICSOE_IMSS", 
				new BigDecimal(1), "EXACTO", resultfechas);	
		
		for (OrdenFechaIntervalosTO ordenFechaIntervalosTO : resultfechas) {
			log.info(">>>>>>>>>>>>>>>>>>>>>>result  2 meses:{} {}", ordenFechaIntervalosTO.toString());
		}
		
		for (Date to : fechaRepeticion) {
			log.info(">>>>>>>>>>>>>>>>>>>>>>result  2 meses:{} {}", converterJsonAsString(to));
		}
		
		
		intervaloFechas.setCadaNperiodos(0);
		intervaloFechas.setDetallePeriodo("P");
		List<OrdenFechaIntervalosTO> result2 = fechaHelper.leeIntervaloFechaRepse("BRIOMAX", intervaloFechas, "NO");
		for (OrdenFechaIntervalosTO ordenFechaIntervalosTO : result2) {
			log.info(">>>>>>>>>>>>>>>>>>>>>>result PENULTIMO NO aplica inicio:{} {}", ordenFechaIntervalosTO.toString());
		}
		
		intervaloFechas.setCadaNperiodos(0);
		intervaloFechas.setDetallePeriodo("1");
		result2 = fechaHelper.leeIntervaloFechaRepse("BRIOMAX", intervaloFechas, "NO");
		for (OrdenFechaIntervalosTO ordenFechaIntervalosTO : result2) {
			log.info(">>>>>>>>>>>>>>>>>>>>>>result MES 1 y NO aplica inicio:{} {}", ordenFechaIntervalosTO.toString());
		}
		
		result2 = fechaHelper.leeIntervaloFechaRepse("BRIOMAX", intervaloFechas, "SI");
		for (OrdenFechaIntervalosTO ordenFechaIntervalosTO : result2) {
			log.info(">>>>>>>>>>>>>>>>>>>>>>result MES 1 Y SI  aplica inicio:{} {}", ordenFechaIntervalosTO.toString());
		}
		
		intervaloFechas.setCadaNperiodos(2);
		intervaloFechas.setDetallePeriodo("1");
		
		result2 = fechaHelper.leeIntervaloFechaRepse("BRIOMAX", intervaloFechas, "NO");
		for (OrdenFechaIntervalosTO ordenFechaIntervalosTO : result2) {
			log.info(">>>>>>>>>>>>>>>>>>>>>>result MES bimestre Y NO  aplica inicio:{} {}", ordenFechaIntervalosTO.toString());
		}

		
		intervaloFechas.setCadaNperiodos(3);
		intervaloFechas.setDetallePeriodo("1");
		
		result2 = fechaHelper.leeIntervaloFechaRepse("BRIOMAX", intervaloFechas, "NO");

		resultfechas = fechaHelper.generaFechasIntervaloRepse(intervaloFechas, result2);
		
		fechaRepeticion =  fechaHelper.ajustaFechasIntervalo("BRIOMAX", "BRIOMAX-CENTRAL", "ES-MX", "ACUSE_ICSOE_IMSS", 
				new BigDecimal(1), "EXACTO", resultfechas);	
		
		for (OrdenFechaIntervalosTO ordenFechaIntervalosTO : resultfechas) {
			log.info(">>>>>>>>>>>>>>>>>>>>>>result 3 mese:{} {}", ordenFechaIntervalosTO.toString());
		}
		
		intervaloFechas.setCadaNperiodos(4);
		intervaloFechas.setDetallePeriodo("1");
		
		result2 = fechaHelper.leeIntervaloFechaRepse("BRIOMAX", intervaloFechas, "NO");

		resultfechas = fechaHelper.generaFechasIntervaloRepse(intervaloFechas, result2);
		
		fechaRepeticion =  fechaHelper.ajustaFechasIntervalo("BRIOMAX", "BRIOMAX-CENTRAL", "ES-MX", "ACUSE_ICSOE_IMSS", 
				new BigDecimal(1), "EXACTO", resultfechas);	
		
		for (OrdenFechaIntervalosTO ordenFechaIntervalosTO : resultfechas) {
			log.info(">>>>>>>>>>>>>>>>>>>>>>result 4 mese:{} {}", ordenFechaIntervalosTO.toString());
		}
		
		intervaloFechas.setCadaNperiodos(6);
		intervaloFechas.setDetallePeriodo("1");
		
		result2 = fechaHelper.leeIntervaloFechaRepse("BRIOMAX", intervaloFechas, "NO");

		resultfechas = fechaHelper.generaFechasIntervaloRepse(intervaloFechas, result2);
		
		fechaRepeticion =  fechaHelper.ajustaFechasIntervalo("BRIOMAX", "BRIOMAX-CENTRAL", "ES-MX", "ACUSE_ICSOE_IMSS", 
				new BigDecimal(1), "EXACTO", resultfechas);	
		
		for (OrdenFechaIntervalosTO ordenFechaIntervalosTO : resultfechas) {
			log.info(">>>>>>>>>>>>>>>>>>>>>>result 4 mese:{} {}", ordenFechaIntervalosTO.toString());
		}
	}

	
	
	@Test
	public void leeIntervaloFechasCuatroTest() throws BrioBPMException, ParseException {
		log.info("INICIA TEST SP_LEE_INTERVALO_FECHAS");
		// Ejecución del método a probar
        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.DECEMBER, 12); // OJO: los meses empiezan en 0 (enero = 0)

        Date desde = calendar.getTime();
        
        calendar.set(2025, Calendar.MARCH, 8);
        Date hasta = calendar.getTime();        
		IntervaloFechaTO intervaloFechas = IntervaloFechaTO.builder()
				.cveProceso("ACUSE_ICSOE_IMSS")
				.version(new BigDecimal(1))
				.cadaNperiodos(2)
				.periodoTiempo("MES")
				.detallePeriodo("1")
				.diasDeLaSemana(null)
				.diasDelMes("18")
				.tipoDia("EXACTO")
				.desde(desde)//new Date()
				.hasta(hasta)
				.build(); 
				
		
		List<OrdenFechaIntervalosTO> result = fechaHelper.leeIntervaloFecha("BRIOMAX", intervaloFechas );
		List<OrdenFechaIntervalosTO> resultfechas = fechaHelper.generaFechasIntervalo (intervaloFechas, result);
		List<Date> fechaRepeticion =  fechaHelper.ajustaFechasIntervalo("BRIOMAX", "BRIOMAX-CENTRAL", "ES-MX", "ACUSE_ICSOE_IMSS", 
				new BigDecimal(1), "EXACTO", resultfechas);		

		for (OrdenFechaIntervalosTO ordenFechaIntervalosTO : result) {
			log.info(">>>>>>>>>>>>>>>>>>>>>>result:{} {}", ordenFechaIntervalosTO.toString());
		}
		
		for (OrdenFechaIntervalosTO date : resultfechas) {
			log.info(">>>>>>>>>>>>>>>>>>>>>>result fecha:{}", date.toString());
		}
		
		for (Date date : fechaRepeticion) {
			log.info("xxxxxxxxxxxxxxxxxxxxxxxxxxresult fechaRepeticion:{}", date.toString());
		}
		
	}

	@Test
	public void leeIntervaloFechasUltimoTest() throws BrioBPMException, ParseException {
		log.info("INICIA TEST SP_LEE_INTERVALO_FECHAS");
		// Ejecución del método a probar
        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.DECEMBER, 31); // OJO: los meses empiezan en 0 (enero = 0)

        Date hasta = calendar.getTime();
        
		IntervaloFechaTO intervaloFechas = IntervaloFechaTO.builder()
				.cveProceso("ACUSE_ICSOE_IMSS")
				.version(new BigDecimal(1))
				.cadaNperiodos(4)
				.periodoTiempo("MES")
				.detallePeriodo("U")
				.diasDeLaSemana(null)
				.diasDelMes("17")
				.tipoDia("EXACTO")
				.desde(new Date())
				.hasta(hasta)
				.build();
				
		
		List<OrdenFechaIntervalosTO> result = fechaHelper.leeIntervaloFecha("BRIOMAX", intervaloFechas );
		
		List<OrdenFechaIntervalosTO> resultfechas = fechaHelper.generaFechasIntervalo (intervaloFechas, result);
		
		List<Date> fechaRepeticion =  fechaHelper.ajustaFechasIntervalo("BRIOMAX", "BRIOMAX-CENTRAL", "ES-MX", "ACUSE_ICSOE_IMSS", 
				new BigDecimal(1), "EXACTO", resultfechas);	

		//log.info("Mensaje: {}", result.getMessage());
		//log.info("TipoExcepcion: {}", result.getStatus());
		log.info(">>>>>>>>>>>>>>>>>>>>>>result:{} {}", result.size(), result.toString());
		for (Date date : fechaRepeticion) {
			log.info(">>>>>>>>>>>>>>>>>>>>>>result fecha:{}", date.toString());
		}
		
	}

	
	@Test
	public void leeIntervaloFechasPenultimoTest() throws BrioBPMException, ParseException {
		log.info("INICIA TEST SP_LEE_INTERVALO_FECHAS");
		// Ejecución del método a probar
        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.DECEMBER, 31); // OJO: los meses empiezan en 0 (enero = 0)

        Date hasta = calendar.getTime();
        
		IntervaloFechaTO intervaloFechas = IntervaloFechaTO.builder()
				.cveProceso("ACUSE_ICSOE_IMSS")
				.version(new BigDecimal(1))
				.cadaNperiodos(4)
				.periodoTiempo("MES")
				.detallePeriodo("P")
				.diasDeLaSemana(null)
				.diasDelMes("17")
				.tipoDia("EXACTO")
				.desde(new Date())
				.hasta(hasta)
				.build();
				
		
		List<OrdenFechaIntervalosTO> result = fechaHelper.leeIntervaloFecha("BRIOMAX", intervaloFechas );
		
		List<OrdenFechaIntervalosTO> resultfechas = fechaHelper.generaFechasIntervalo (intervaloFechas, result);
		
		List<Date> fechaRepeticion =  fechaHelper.ajustaFechasIntervalo("BRIOMAX", "BRIOMAX-CENTRAL", "ES-MX", "ACUSE_ICSOE_IMSS", 
				new BigDecimal(1), "EXACTO", resultfechas);	

		//log.info("Mensaje: {}", result.getMessage());
		//log.info("TipoExcepcion: {}", result.getStatus());
		log.info(">>>>>>>>>>>>>>>>>>>>>>result:{} {}", result.size(), result.toString());
		for (Date date : fechaRepeticion) {
			log.info(">>>>>>>>>>>>>>>>>>>>>>result fecha:{}", date.toString());
		}
		
	}

	
	
	// SP_CALCULA_FECHA_LIMITE
	//@Ignore
	@Test
	public void calculaFechaLimiteTest() throws BrioBPMException, ParseException {
		log.info("INICIA TEST SP_CALCULA_FECHA_LIMITE");
		// Ejecución del método a probar

		CalculoFechaLimiteTO datosFecha1 = CalculoFechaLimiteTO.builder()
				.cveProceso("G2 ACF")
				.version(new BigDecimal(1))
				.cveInstancia("202110-000001")
				.cveNodo("ACTIVIDAD-USUARIO")
				.idNodo(3)
				.cveEntidadBase("BRIOMAX")
				.cveLocalidaDestino("BRIOMAX-CENTRAL")
				.fechaCreacion(new Date())
				.build();
			
		EstatusFechaTO estatus = fechaHelper.calculaFechaLimite(
				getDatosAutenticacionTO(), datosFecha1);
		log.info("Mensaje: {}", estatus.getMensaje());
		log.info("TipoExcepcion: {}", estatus.getTipoExcepcion());
		if( estatus.getTipoExcepcion().equals("ERROR")) {
			log.error("FALLO EL STORED SP_CALCULA_FECHA_LIMITE");
		}
	}
	
	// SP_CALCULA_HORA_LOCALIDAD
	//@Ignore
	@Test
	public void calculaHoraLocalidadTest() throws BrioBPMException, ParseException {
		log.info("INICIA TEST SP_CALCULA_HORA_LOCALIDAD");
		// Ejecución del método a probar
		EstatusFechaTO estatus = fechaHelper.calculaHoraLocalidad(
				getDatosAutenticacionTO(), new Date());
		log.info("Mensaje: {}", estatus.getMensaje());
		log.info("TipoExcepcion: {}", estatus.getTipoExcepcion());
		if( estatus.getTipoExcepcion().equals("ERROR")) {
			log.error("FALLO EL STORED SP_CALCULA_HORA_LOCALIDAD");
		}
	}
	
	// SP_CARGA_CALENDARIO_LOCALIDAD
	//@Ignore
	@Test
	public void cargaCalendarioLocalidadTest() throws BrioBPMException, ParseException {
		log.info("INICIA TEST SP_CARGA_CALENDARIO_LOCALIDAD");
		// Ejecución del método a probar

		CalculoFechaLimiteTO datosFecha = CalculoFechaLimiteTO.builder()
				.cveEntidadBase("BRIOMAX")
				.cveLocalidaDestino("BRIOMAX-CENTRAL")
				.cveProceso("DUMMY")
				.version(new BigDecimal(1))
				.build();
		List<CalendarioLocalidadTO> calendarioLocalidad = new ArrayList<CalendarioLocalidadTO>();
			
		EstatusTO estatus = fechaHelper.cargaCalendarioLocalidad(
				getDatosAutenticacionTO(), datosFecha, calendarioLocalidad);
		
				log.info("Mensaje: {}", estatus.getMensaje());
				log.info("TipoExcepcion: {}", estatus.getTipoExcepcion());
				if( estatus.getTipoExcepcion().equals("ERROR")) {
					log.error("FALLO EL STORED SP_CARGA_CALENDARIO_LOCALIDAD");
				}
	}
	
	// SP_CALCULA_FECHA_MAS_PLAZO_HMS
	//@Ignore
	@Test
	public void calculaFechaMasPlazoHMSTest() throws BrioBPMException, ParseException {
		log.info("INICIA TEST SP_CALCULA_FECHA_MAS_PLAZO_HMS");
		// Ejecución del método a probar

		CalculoFechaLimiteTO datosFecha1 = CalculoFechaLimiteTO.builder()
				.cveEntidadBase("BRIOMAX")
				.cveLocalidaDestino("BRIOMAX-CENTRAL")
				.cveProceso("DUMMY")
				.version(new BigDecimal(1))
				.build();
		List<CalendarioLocalidadTO> calendarioLocalidad = new ArrayList<CalendarioLocalidadTO>();

		EstatusTO result1 = fechaHelper.cargaCalendarioLocalidad(
				getDatosAutenticacionTO(), datosFecha1, calendarioLocalidad);

	    Date fechaHoraBase = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2025-03-22 7:50:00");

		CalculoFechaMasPlazoTO datosFecha = CalculoFechaMasPlazoTO.builder()
				.cveEntidadBase("BRIOMAX")
				.cveLocalidadBase("BRIOMAX-CENTRAL")
				.fechaBase(fechaHoraBase)
				.plazo(2)
				.baseCalculo("HORAS")
				.build();
			
		EstatusFechaTO result = fechaHelper.calculaFechaMasPlazoHMS(
				getDatosAutenticacionTO(), datosFecha, calendarioLocalidad);
		
		log.info("------> TEST TERMINADO cargaCalendarioLocalidad: " + result1.toString());
		log.info("------> TEST TERMINADO calculaFechaMasPlazoDSM: " + result.toString());
		if( result.getTipoExcepcion().equals("ERROR") 
				|| result1.getTipoExcepcion().equals("ERROR")) {
			
			log.error("FALLO EL STORED SP_CALCULA_FECHA_MAS_PLAZO_HMS");
		}
	}
		
	// SP_CALCULA_FECHA_MAS_PLAZO_DSM
	//@Ignore
	@Test
	public void calculaFechaMasPlazoDSMTest() throws BrioBPMException, ParseException {
		log.info("INICIA TEST SP_CALCULA_FECHA_MAS_PLAZO_DSM");
		// Ejecución del método a probar

		CalculoFechaLimiteTO datosFecha1 = CalculoFechaLimiteTO.builder()
				.cveEntidadBase("BRIOMAX")
				.cveLocalidaDestino("BRIOMAX-CENTRAL")
				.cveProceso("DUMMY")
				.version(new BigDecimal(1))
				.build();
		List<CalendarioLocalidadTO> calendarioLocalidad = new ArrayList<CalendarioLocalidadTO>();

			
		EstatusTO result1 = fechaHelper.cargaCalendarioLocalidad(
				getDatosAutenticacionTO(), datosFecha1, calendarioLocalidad);

		CalculoFechaMasPlazoTO datosFecha = CalculoFechaMasPlazoTO.builder()
				.fechaBase(new Date())
				.plazo(1)
				.baseCalculo("DIAS")
				.build();
			
		EstatusFechaTO result = fechaHelper.calculaFechaMasPlazoDSM(
				getDatosAutenticacionTO(), datosFecha, calendarioLocalidad);
		
		log.info("------> TEST TERMINADO cargaCalendarioLocalidad:" + result1.toString());
		log.info("------> TEST TERMINADO calculaFechaMasPlazoDSM:" + result.toString());
		if( result.getTipoExcepcion().equals("ERROR") 
				|| result1.getTipoExcepcion().equals("ERROR")) {
			
			log.error("FALLO EL STORED SP_CALCULA_FECHA_MAS_PLAZO_DSM");
		}
	}
	
	// SP_VALIDA_FECHA_HABIL
	//@Ignore
	@Test
	public void validaFechaHabilTest() throws BrioBPMException, ParseException {
		log.info("INICIA TEST");
		
		List<CalendarioLocalidadTO> calendarioLocalidad = new ArrayList<CalendarioLocalidadTO>();

		// Ejecución del método a probar
		String fecha = fechaHelper.validaFechaHabil(
				"BRIOMAX", "BRIOMAX-CENTRAL", new Date(), calendarioLocalidad);
		
		log.info("------> fecha: " + fecha);
		log.info("------> TEST TERMINADO");
	}
	
	
	// SP_CALCULA_FECHA_MAS_PLAZO
	@Test
	//@Ignore
	public void calculaFechaMasPlazoTest() throws BrioBPMException, ParseException {
		log.info("INICIA TEST SP_CALCULA_FECHA_MAS_PLAZO");
		// Ejecución del método a probar

		CalculoFechaMasPlazoTO datosFecha = CalculoFechaMasPlazoTO.builder()
				.cveProceso("DUMMY")
				.version(new BigDecimal(1))
				.cveInstancia("202110-000001")
				.cveNodo("EVENTO-INICIO")
				.idNodo(1)
				.cveEntidadBase("BRIOMAX")
				.cveLocalidadBase("BRIOMAX-CENTRAL")
				.fechaBase(new Date())
				.plazo(1)
				.baseCalculo("")
				.build();
			
		EstatusFechaTO estatus = fechaHelper.calculaFechaMasPlazo(
				getDatosAutenticacionTO(), datosFecha);
		
		log.info("Mensaje: {}", estatus.getMensaje());
		log.info("TipoExcepcion: {}", estatus.getTipoExcepcion());
		if( estatus.getTipoExcepcion().equals("ERROR")) {
			log.error("FALLO EL STORED SP_CALCULA_FECHA_MAS_PLAZO");
		}
	}

	// SP_CALCULA_FECHA_TEMPORIZADOR
	//@Ignore
	@Test
	public void calculaFechaTemporizadorTest() throws BrioBPMException, ParseException {
		log.info("INICIA TEST SP_CALCULA_FECHA_TEMPORIZADOR");
		// Ejecución del método a probar

		CalculoFechaLimiteTO datosFecha1 = CalculoFechaLimiteTO.builder()
				.cveProceso("G2 ACF")
				.version(new BigDecimal(1))
				.cveInstancia("202110-000001")
				.cveNodo("ACTIVIDAD-USUARIO")
				.idNodo(3)
				.cveEntidadBase("BRIOMAX")
				.cveLocalidaDestino("BRIOMAX-CENTRAL")
				.fechaCreacion(new Date())
				.build();
			
		EstatusFechaTO result = fechaHelper.calculaFechaTemporizador(
				getDatosAutenticacionTO(), datosFecha1);
		
		log.info("Mensaje: {}", result.getMensaje());
		log.info("TipoExcepcion: {}", result.getTipoExcepcion());
		if( result.getTipoExcepcion().equals("ERROR")) {
			log.error("FALLO EL STORED SP_CALCULA_FECHA_TEMPORIZADOR");
		}
	}
	
	// SP_LEE_INTERVALO_FECHAS
		@Test
		public void leeIntervaloFechasTest() throws BrioBPMException, ParseException {
			log.info("INICIA TEST SP_LEE_INTERVALO_FECHAS");
			// Ejecución del método a probar
	        Calendar calendar = Calendar.getInstance();
	        calendar.set(2025, Calendar.DECEMBER, 31); // OJO: los meses empiezan en 0 (enero = 0)

	        Date hasta = calendar.getTime();
	        
			IntervaloFechaTO intervaloFechas = IntervaloFechaTO.builder()
					.cveProceso("COMPROBANTE_CUOTAS_OBRERO_PATRONALES")
					.version(new BigDecimal(1))
					.cadaNperiodos(1)
					.periodoTiempo("MES")
					.detallePeriodo(null)
					.diasDeLaSemana(null)
					.diasDelMes("17")
					.tipoDia("EXACTO")
					.desde(new Date())
					.hasta(hasta)
					.build();
					
			
			List<OrdenFechaIntervalosTO> result = fechaHelper.leeIntervaloFecha("BRIOMAX", intervaloFechas );
			
			List<OrdenFechaIntervalosTO> resultfechas = fechaHelper.generaFechasIntervalo (intervaloFechas, result);
			
			//log.info("Mensaje: {}", result.getMessage());
			//log.info("TipoExcepcion: {}", result.getStatus());
			log.info("result:{} {}", result.size(), result.toString());
			
		}
		
		
	@Test
	public void obtenerFechaHoraActualTest() throws BrioBPMException, ParseException {
		log.info("INICIA TEST obtenerFechaHoraActualTest");
		
		log.info("------> FECHA ACTUAL : " + new Date());
		
		// Ejecución del método a probar
		entidad = "BRIOMAX";
		localidad = "BRIOMAX-CENTRAL";
		Date result = fechaHelper.obtenerFechaCreacion(entidad, localidad);
		log.info("Fecha y Hora Actual - 1 : {}", result);
		
		localidad = "BRIOMAX-LOS CABOS";
		result = fechaHelper.obtenerFechaCreacion(entidad, localidad);
		log.info("Fecha y Hora Actual - 2 : {}", result);
		
		localidad = "BRIOMAX-QATAR";
		result = fechaHelper.obtenerFechaCreacion(entidad, localidad);
		log.info("Fecha y Hora Actual - 3 : {}", result);
		
		localidad = "BRIOMAX-QUINTANA-ROO";
		result = fechaHelper.obtenerFechaCreacion(entidad, localidad);
		log.info("Fecha y Hora Actual - 4 : {}", result);
		
		localidad = "BRIOMAX-SAN ANGEL";
		result = fechaHelper.obtenerFechaCreacion(entidad, localidad);
		log.info("Fecha y Hora Actual - 5 : {}", result);
		
		entidad = "MSD";
		localidad = "MSD-CENTRAL";
		result = fechaHelper.obtenerFechaCreacion(entidad, localidad);
		log.info("Fecha y Hora Actual - 6 : {}", result);
		
		localidad = "MSD-XOCHIMILCO";
		result = fechaHelper.obtenerFechaCreacion(entidad, localidad);
		log.info("Fecha y Hora Actual - 7 : {}", result);
		
		log.info("------> TEST TERMINADO");
	}
	
}
