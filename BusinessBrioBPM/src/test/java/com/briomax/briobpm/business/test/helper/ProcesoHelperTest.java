/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */
package com.briomax.briobpm.business.test.helper;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.briomax.briobpm.business.helpers.base.IProcesoHelper;
import com.briomax.briobpm.business.test.base.AbstractCoreTest;
import com.briomax.briobpm.common.core.Constants;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.transferobjects.NodoInicioProcesoTO;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;
import com.briomax.briobpm.transferobjects.in.EstatusCreaInstanciaTO;
import com.briomax.briobpm.transferobjects.in.EstatusFolioTO;
import com.briomax.briobpm.transferobjects.in.EstatusTO;
import com.briomax.briobpm.transferobjects.in.ProcesoInicialTO;

import lombok.extern.slf4j.Slf4j;


/**
 * El objetivo de la Class UsuariosHelperTest.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion May 15, 2020 11:57:26 AM Modificaciones:
 * @since JDK 1.8
 */
@Slf4j
public class ProcesoHelperTest extends AbstractCoreTest {

	/** El atributo o variable usarios helper. */
	@Autowired
	private IProcesoHelper procesoHelper;
	
	// SP_CREA_FOLIO_IN_NODO
	@Test
	@Ignore
	public void testCreaFolioEnProceso() throws BrioBPMException {
		log.info("INICIA TEST");
		// Ejecución del método a probar
		EstatusFolioTO estatus = procesoHelper.creaFolio(
				getDatosAutenticacionTO(), "INCIDENCIA-SMI", BigDecimal.ONE );
		
		log.info("Mensaje: {}", estatus.getMensaje());
		log.info("TipoExcepcion: {}", estatus.getTipoExcepcion());
		if( estatus.getTipoExcepcion().equals(Constants.ERROR)) {
			log.error("FALLO EL STORED");
		}
	}

	// SP_VAL_DATOS_ST_PROCESO
	/**
	 * Valido datos test.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	@Ignore
	public void validoDatosTest() throws BrioBPMException {
		DatosAutenticacionTO session = getDatosAutenticacionTO();
		session.setCveUsuario("Francisco.Rodriguez");
		printerJson(session);
		BigDecimal d = new BigDecimal(1.00);
		EstatusTO estatus = procesoHelper.getValidaDatos(session, "INCIDENCIA-SMI", d);
		log.info("Mensaje: {}", estatus.getMensaje());
		log.info("TipoExcepcion: {}", estatus.getTipoExcepcion());
		if( estatus.getTipoExcepcion().equals(Constants.ERROR)) {
			log.error("FALLO EL STORED");
		}
	}
	@Test
	@Ignore
	public void validoDatosTest2() throws BrioBPMException {
		DatosAutenticacionTO session = getDatosAutenticacionTO();
		session.setCveUsuario("Admin.Opera");
		printerJson(session);
		BigDecimal d = new BigDecimal(1.00);
		EstatusTO estatus = procesoHelper.getValidaDatos(session, "PROCESO-INEXISTENTE", d);
		log.info("Mensaje: {}", estatus.getMensaje());
		log.info("TipoExcepcion: {}", estatus.getTipoExcepcion());
		if( estatus.getTipoExcepcion().equals(Constants.ERROR)) {
			log.error("FALLO EL STORED");
		}
	}	
	@Test
	@Ignore
	public void validoDatosTest3() throws BrioBPMException {
		DatosAutenticacionTO session = getDatosAutenticacionTO();
		session.setCveUsuario("Admin.Opera");
		session.setCveIdioma("EN-US");
		printerJson(session);
		BigDecimal d = new BigDecimal(1.00);
		EstatusTO estatus = procesoHelper.getValidaDatos(session, "PROCESO-INEXISTENTE", d);
		log.info("Mensaje: {}", estatus.getMensaje());
		log.info("TipoExcepcion: {}", estatus.getTipoExcepcion());
		if( estatus.getTipoExcepcion().equals(Constants.ERROR)) {
			log.error("FALLO EL STORED");
		}
	}	
	// SP_INS_IN_PROCESO
	/**
	 * Valido datos test.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	@Ignore
	public void InsetInProcesoTest() throws BrioBPMException {
		DatosAutenticacionTO session = getDatosAutenticacionTO();
		session.setCveUsuario("Francisco.Rodriguez");
		printerJson(session);
		@SuppressWarnings("deprecation")
		Date date1 = new Date(116, 11, 18);
		
		BigDecimal d = new BigDecimal(1.00);
		Integer d2 = 1;
	    
		ProcesoInicialTO procesoInicial = ProcesoInicialTO.builder()
				.idProceso("CREA_INSTANCIA_PROCESO")
				.cveProceso("DUMMY")
				.version(d)
				.cveInstancia("202111-000666")
				.concepto("NUEVA INCIDENCIA PRUEBA")
				.fecCreacion(date1)
				.origen ("USUARIO")
				.cveRol ("ROL_ADMIN_OPERA")
				.situacion ("REGISTRADO")
				.cveNodoInicial ("EVENTO-INICIO")
				.idNodoInicial (d2)
				.cveNodoFinal ("EVENTO-FIN")
				.idNodoFinal (d2)
				.build();
		
		EstatusTO estatus = procesoHelper.setInsInProceso(session, procesoInicial);
		log.info("Mensaje: {}", estatus.getMensaje());
		log.info("TipoExcepcion: {}", estatus.getTipoExcepcion());
		if( estatus.getTipoExcepcion().equals(Constants.ERROR)) {
			log.error("FALLO EL STORED SP_INS_IN_PROCESO");
		}
	}
	
	//
	
	// SP_LEE_NODO_INICIO_PROCESO
	
	// SP_CREA_INSTANCIA_PROCESO
	@Test
	@Ignore
	public void creaInstanciaProcesoTest() throws BrioBPMException, ParseException {
		DatosAutenticacionTO session = getDatosAutenticacionTO();
		session.setCveUsuario("AIT.Usuario.Cliente");
		printerJson(session);
		@SuppressWarnings("deprecation")
		Date date1 = new Date(2016, 11, 18);
		
		BigDecimal d = new BigDecimal(1.00);
	    
		ProcesoInicialTO procesoInicial = ProcesoInicialTO.builder()
				.idProceso("CREA_INSTANCIA_PROCESO")
				.cveProceso("ATEN_INCI_TICK")
				.version(d)
				.concepto("NUEVA INCIDENCIA")
				.origen ("USUARIO")
				.cveRol ("CLIENTE")
				.cveNodo("ACTIVIDAD-USUARIO")
				.idNodo(1)
				.build();
		
		EstatusCreaInstanciaTO estatus = procesoHelper.creaInstancia(session, procesoInicial, null , null);
		
		log.info("Mensaje: {}", estatus.getMensaje());
		log.info("TipoExcepcion: {}", estatus.getTipoExcepcion());
		if( estatus.getTipoExcepcion().equals(Constants.ERROR)) {
			log.error("FALLO EL STORED SP_CREA_INSTANCIA_PROCESO");
			
			procesoInicial.setCveProceso("G2 ACF");
			procesoInicial.setCveRol("COMERCIAL");
			estatus = 
					procesoHelper.creaInstancia(session, procesoInicial, null , null);
			
			log.info("Mensaje: {}", estatus.getMensaje());
			log.info("TipoExcepcion: {}", estatus.getTipoExcepcion());
			if( estatus.getTipoExcepcion().equals(Constants.ERROR)) {
				log.error("FALLO EL STORED SP_CREA_INSTANCIA_PROCESO");
			}
			
			procesoInicial.setCveProceso("INCIDENCIA-SMI");
			procesoInicial.setCveRol("CLIENTE");
			estatus = 
					procesoHelper.creaInstancia(session, procesoInicial, null , null);
			
			log.info("Mensaje: {}", estatus.getMensaje());
			log.info("TipoExcepcion: {}", estatus.getTipoExcepcion());
			if( estatus.getTipoExcepcion().equals(Constants.ERROR)) {
				log.error("FALLO EL STORED SP_CREA_INSTANCIA_PROCESO");
			}
		}
	}
	
	
	// SP_CREA_INSTANCIA_PROCESO
		@Test
		@Ignore
		public void creaInstanciaProcesoSMITest() throws BrioBPMException, ParseException {
			DatosAutenticacionTO session = getDatosAutenticacionTO();
			session.setCveUsuario("Admin.Opera");
			printerJson(session);
		    
			ProcesoInicialTO procesoInicial = ProcesoInicialTO.builder()
					.idProceso("CREA_INSTANCIA_PROCESO")
					.cveProceso("INCIDENCIA-SMI")
					.version(new BigDecimal(1.00))
					.cveNodo("ACTIVIDAD-USUARIO")
					.concepto("NUEVA INCIDENCIA")
					.origen ("USUARIO")
					.cveRol ("ROL_ADMIN_OPERA")
					.build();
			
			EstatusCreaInstanciaTO estatus = 
					procesoHelper.creaInstancia(session, procesoInicial, null , null);
			
			log.info("Mensaje: {}", estatus.getMensaje());
			log.info("TipoExcepcion: {}", estatus.getTipoExcepcion());
			if( estatus.getTipoExcepcion().equals(Constants.ERROR)) {
				log.error("FALLO EL STORED SP_CREA_INSTANCIA_PROCESO");
				
				procesoInicial.setCveProceso("G2 ACF");
				procesoInicial.setCveRol("COMERCIAL");
				estatus = 
						procesoHelper.creaInstancia(session, procesoInicial, null , null);
				
				log.info("Mensaje: {}", estatus.getMensaje());
				log.info("TipoExcepcion: {}", estatus.getTipoExcepcion());
				if( estatus.getTipoExcepcion().equals(Constants.ERROR)) {
					log.error("FALLO EL STORED SP_CREA_INSTANCIA_PROCESO");
				}
				
				procesoInicial.setCveProceso("INCIDENCIA-SMI");
				procesoInicial.setCveRol("CLIENTE");
				estatus = 
						procesoHelper.creaInstancia(session, procesoInicial, null , null);
				
				log.info("Mensaje: {}", estatus.getMensaje());
				log.info("TipoExcepcion: {}", estatus.getTipoExcepcion());
				if( estatus.getTipoExcepcion().equals(Constants.ERROR)) {
					log.error("FALLO EL STORED SP_CREA_INSTANCIA_PROCESO");
				}
			}
		}
		// SP_CARGA_LISTA_EN_TABLA
		@Test
		@Ignore
		public void cargaListaEnTablaTest() throws BrioBPMException, ParseException {
			log.info("INICIA TEST SP_CARGA_LISTA_EN_TABLA");
			// Ejecución del método a probar
			
			NodoInicioProcesoTO datos = NodoInicioProcesoTO.builder()
					.cveProceso("REGISTRO_DE_CONTRATO")
					.version(new BigDecimal(1))
					.build();
			
			String cadenaEntrada = "VPRO_01_NOM_TIENDA|1|1|";
			String separador = "|";
		
					
			List<String> listaValor = new ArrayList<String>();
			
			RetMsg result = procesoHelper.cargaListaEnTabla(
					getDatosAutenticacionTO(),datos,cadenaEntrada, separador, listaValor);
			
			log.info("Mensaje: {}", result.getMessage());
			log.info("TipoExcepcion: {}", result.getStatus());
			log.info(":{} {}", listaValor.size(), listaValor.toString());
			
			if( result.getStatus().equals("ERROR")) {
				log.error("FALLO SP_LEE_INTERVALO_FECHAS");
			}else {
				log.error("OK");
			}
		}
		
		// SP_CARGA_LISTA_EN_TABLA
		@Test
		public void procesaMensajeTest() throws BrioBPMException, ParseException {
			log.info("INICIA TEST PROCESA MENSAJES");
			// Ejecución del método a probar
			
			RetMsg result = procesoHelper.procesaMensajes(getDatosAutenticacionTO());
			
			if( result.getStatus().equals("ERROR")) {
				log.error("FALLO SP_LEE_INTERVALO_FECHAS");
			}else {
				log.error("OK");
			}
		}
}
