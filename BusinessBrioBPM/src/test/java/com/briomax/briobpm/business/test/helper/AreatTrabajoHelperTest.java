package com.briomax.briobpm.business.test.helper;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.briomax.briobpm.business.helpers.base.IAreaTrabajoHelper;
import com.briomax.briobpm.business.test.base.AbstractCoreTest;
import com.briomax.briobpm.common.core.Constants;
import com.briomax.briobpm.common.core.DAORet;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.transferobjects.InformacionAreaTrabajoTO;
import com.briomax.briobpm.transferobjects.in.DatosAreaTrabajoTO;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;
import com.briomax.briobpm.transferobjects.in.EstatusAccionesTO;
import com.briomax.briobpm.transferobjects.in.EstatusAreaTrabajoTO;
import com.briomax.briobpm.transferobjects.in.EstatusAtributosMonedaTO;
import com.briomax.briobpm.transferobjects.in.NodoTO;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Class AreatTrabajoHelperTest.java es ...
 * 
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Mar 14, 2024 11:50:00 AM Modificaciones:
 * @since JDK 1.8
 */
@Slf4j
public class AreatTrabajoHelperTest  extends AbstractCoreTest {

	@Autowired
	private IAreaTrabajoHelper helper;

	// SP_LEE_INF_AREA_TRABAJO
	//@Ignore
	@Test
	public void spLeeInfATTest() throws BrioBPMException {
		log.info("INICIA TEST SP_LEE_INF_AREA_TRABAJO");
		DatosAutenticacionTO datosAutenticacion = DatosAutenticacionTO.builder()
				.cveEntidad(entidad)
				.cveLocalidad(localidad)
				.cveUsuario("Admin.Opera")
				.cveIdioma(idioma)
				.cveMoneda("USD")
				.build();
		
		// Ejecución del método a probar
		NodoTO nodo = NodoTO.builder()
				.rol("SOLICITANTE")
				.cveNodo("ACTIVIDAD-USUARIO")
				.version(BigDecimal.ONE)
				.cveProceso("ATEN_INCI_TICK")
				.idNodo(1)
				.build();
		DAORet<EstatusAreaTrabajoTO, RetMsg> estatus = helper.leeInfAreaTrabajo(
				datosAutenticacion, nodo, "CRE_FOL", "REGISTRO_DATOS _CONTRATANTE_CONTRATISTA");
		
		for (InformacionAreaTrabajoTO item : estatus.getContent().getInfAreaTrabajo()) {
			log.info(item.toString());
		} 
		
		log.info("Mensaje: {}", estatus.getMeta().getStatus());
		log.info("------> TipoExcepcion: {}", estatus.getMeta().getMessage());
		assertEquals(Constants.OK, estatus.getMeta().getStatus());
	}
	
	
	// SP_LEE_INF_AREA_TRABAJO_VS
	//@Ignore
	@Test
	public void spLeeInfATVSTest() throws BrioBPMException {
		log.info("INICIA TEST SP_LEE_INF_AREA_TRABAJO_VS");
		// Ejecución del método a probar
		NodoTO nodo = NodoTO.builder()
				.rol("ROL_ADMIN_OPERA")
				.cveNodo("ACTIVIDAD-USUARIO")
				.version(BigDecimal.ONE)
				.cveProceso("DUMMY")
				.idNodo(1)
				.build();
		List<DatosAreaTrabajoTO> datosAreaTrabajo = new ArrayList<>();
		EstatusAreaTrabajoTO estatus = helper.leeInfAreaTrabajoVS(
				getDatosAutenticacionTO(), nodo, datosAreaTrabajo, "AREA_UNICA", 1);
		
		log.info("Mensaje: {}", estatus.getMensaje());
		log.info("------> TipoExcepcion: {}", estatus.getTipoExcepcion());
		if( estatus.getTipoExcepcion().equals(Constants.ERROR)) {
			log.error("FALLO EL STORED SP_LEE_INF_AREA_TRABAJO_VS");
		}
	}
	
	// SP_LEE_INF_AREA_TRABAJO_VL
	//@Ignore
	@Test
	public void spLeeInfATVLTest() throws BrioBPMException {
		log.info("INICIA TEST SP_LEE_INF_AREA_TRABAJO_VL");
		// Ejecución del método a probar
		NodoTO nodo = NodoTO.builder()
				.rol("ROL_ADMIN_OPERA")
				.cveNodo("ACTIVIDAD-USUARIO")
				.version(BigDecimal.ONE)
				.cveProceso("DUMMY")
				.idNodo(1)
				.build();
		List<DatosAreaTrabajoTO> datosAreaTrabajo = new ArrayList<>();
		EstatusAreaTrabajoTO estatus = helper.leeInfAreaTrabajoVL(
				getDatosAutenticacionTO(), nodo, datosAreaTrabajo, "AREA_UNICA",1);
		
		log.info("Mensaje: {}", estatus.getMensaje());
		log.info("------> TipoExcepcion: {}", estatus.getTipoExcepcion());
		if( estatus.getTipoExcepcion().equals(Constants.ERROR)) {
			log.error("FALLO EL STORED SP_LEE_INF_AREA_TRABAJO_VL");
		}
	}
		
	// SP_LEE_INF_AREA_TRABAJO_VE
	//@Ignore
	@Test
	public void spLeeInfATVETest() throws BrioBPMException {
		log.info("INICIA TEST SP_CREA_FOLIO_IN_NODO");
		// Ejecución del método a probar
		NodoTO nodo = NodoTO.builder()
				.rol("ROL_ADMIN_OPERA")
				.cveNodo("ACTIVIDAD-USUARIO")
				.version(BigDecimal.ONE)
				.cveProceso("DUMMY")
				.idNodo(1)
				.build();
		List<DatosAreaTrabajoTO> datosAreaTrabajo = new ArrayList<>();
		EstatusAreaTrabajoTO estatus = helper.leeInfAreaTrabajoVE(
				getDatosAutenticacionTO(), nodo, datosAreaTrabajo, "AREA_UNICA", 1);
		
		log.info("Mensaje: {}", estatus.getMensaje());
		log.info("------> TipoExcepcion: {}", estatus.getTipoExcepcion());
		if( estatus.getTipoExcepcion().equals(Constants.ERROR)) {
			log.error("FALLO EL STORED SP_LEE_INF_AREA_TRABAJO_VE");
		}
	}
	
	// SP_LEE_INF_AREA_TRABAJO_VP
	//@Ignore
	@Test
	public void spLeeInfATVTPest() throws BrioBPMException {
		log.info("INICIA TEST SP_LEE_INF_AREA_TRABAJO_VP");
		// Ejecución del método a probar
		NodoTO nodo = NodoTO.builder()
				.rol("ROL_ADMIN_OPERA")
				.cveNodo("ACTIVIDAD-USUARIO")
				.version(BigDecimal.ONE)
				.cveProceso("DUMMY")
				.idNodo(1)
				.build();
		List<DatosAreaTrabajoTO> datosAreaTrabajo = new ArrayList<>();
		EstatusAreaTrabajoTO estatus = helper.leeInfAreaTrabajoVP(
				getDatosAutenticacionTO(), nodo, datosAreaTrabajo, "AREA_UNICA", 1, "REGISTRO_DATOS _CONTRATANTE_CONTRATISTA");
		
		log.info("Mensaje: {}", estatus.getMensaje());
		log.info("------> TipoExcepcion: {}", estatus.getTipoExcepcion());
		if( estatus.getTipoExcepcion().equals(Constants.ERROR)) {
			log.error("FALLO EL STORED SP_LEE_INF_AREA_TRABAJO_VP");
		}
	}
	
	
	// FN_LEE_ESTILO_NIVEL_SERVICIO
	//@Ignore
	@Test
	public void fnLeeEstiloNivelServicioPest() throws BrioBPMException {
		log.info("INICIA TEST FN_LEE_ESTILO_NIVEL_SERVICIO");

		 // Obtener la fecha actual
        Calendar calendar = Calendar.getInstance();
        Date fechaEstadoActual = calendar.getTime();

        // Restar un día a la fecha actual
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        Date fechaCreacionActividad = calendar.getTime();
        
        // sumar un día a la fecha actual
        calendar.add(Calendar.DAY_OF_MONTH, + 3);
        Date fechaLimite = calendar.getTime();
        
		String estatus = helper.leeEstiloNivelServicio( fechaCreacionActividad,
				fechaLimite, "TERMINADA", fechaEstadoActual);
		
		log.info("Mensaje: {}", estatus);
	}
	
	// SP_LEE_ACCIONES_ACTIVIDAD

	//@Ignore
	@Test
	public void spLeeAATTest() throws BrioBPMException {
		log.info("INICIA TEST SP_LEE_INF_AREA_TRABAJO");
		
		DatosAutenticacionTO user = getDatosAutenticacionTO();
		user.setCveUsuario("Usuario.Contratista");
		
		// Ejecución del método a probar
		NodoTO nodo = NodoTO.builder()
				.rol("ROL_ADMIN_OPERA")
				.cveInstancia("202410-000001")
				.cveNodo("ACTIVIDAD-USUARIO")
				.cveProceso("ENVIO_RECIBOS_NOMINA_CONTRATISTA")
				.idNodo(2)
				.secuenciaNodo(1)
				.version(BigDecimal.ONE)
				.build();
		EstatusAccionesTO estatus = helper.leeAccionesActividad(user, nodo);
		// Para ver los valores de configuración de acciones obtenidos desde el objeto estatus
		log.info("Valores de configuración de acciones:");

		// ETIQUETAS
		log.info("ETIQUETA_TOMAR: {}", estatus.getEtiquetaTomar() != null ? estatus.getEtiquetaTomar() : "Nulo");
		log.info("ETIQUETA_LIBERAR: {}", estatus.getEtiquetaLiberar() != null ? estatus.getEtiquetaLiberar() : "Nulo");
		log.info("ETIQUETA_EJECUTAR: {}", estatus.getEtiquetaEjecutar() != null ? estatus.getEtiquetaEjecutar() : "Nulo");
		log.info("ETIQUETA_TERMINAR: {}", estatus.getEtiquetaTerminar() != null ? estatus.getEtiquetaTerminar() : "Nulo");
		log.info("ETIQUETA_CONSULTAR: {}", estatus.getEtiquetaConsultar() != null ? estatus.getEtiquetaConsultar() : "Nulo");
		log.info("ETIQUETA_CANCELAR: {}", estatus.getEtiquetaCancelar() != null ? estatus.getEtiquetaCancelar() : "Nulo");
		log.info("ETIQUETA_BITACORA: {}", estatus.getEtiquetaBitacora() != null ? estatus.getEtiquetaBitacora() : "Nulo");

		// HABILITAR
		log.info("HABILITAR_TOMAR: {}", estatus.getHabilitarTomar() != null ? estatus.getHabilitarTomar() : "Nulo");
		log.info("HABILITAR_LIBERAR: {}", estatus.getHabilitarLiberar() != null ? estatus.getHabilitarLiberar() : "Nulo");
		log.info("HABILITAR_EJECUTAR: {}", estatus.getHabilitarEjecutar() != null ? estatus.getHabilitarEjecutar() : "Nulo");
		log.info("HABILITAR_TERMINAR: {}", estatus.getHabilitarTerminar() != null ? estatus.getHabilitarTerminar() : "Nulo");
		log.info("HABILITAR_CONSULTAR: {}", estatus.getHabilitarConsultar() != null ? estatus.getHabilitarConsultar() : "Nulo");
		log.info("HABILITAR_CANCELAR: {}", estatus.getHabilitarCancelar() != null ? estatus.getHabilitarCancelar() : "Nulo");
		log.info("HABILITAR_BITACORA: {}", estatus.getHabilitarBitacora() != null ? estatus.getHabilitarBitacora() : "Nulo");

		
		log.info("Mensaje: {}", estatus.getMensaje());
		log.info("------> TipoExcepcion: {}", estatus.getTipoExcepcion());
		if( estatus.getTipoExcepcion().equals(Constants.ERROR)) {
			log.error("FALLO EL STORED SP_LEE_INF_AREA_TRABAJO");
		}
	}
	
	
	// SP_LEE_ATRIBUTOS_MONEDA
	@Test
	public void spLeeAtributosMoneda() throws BrioBPMException {
		log.info("INICIA TEST SP_LEE_ATRIBUTOS_MONEDA");
		// Ejecución del método a probar
		NodoTO nodo = NodoTO.builder()
				.rol("ROL_ADMIN_OPERA")
				.cveInstancia("202110-000001")
				.cveNodo("ACTIVIDAD-USUARIO")
				.cveProceso("DUMMY")
				.idNodo(1)
				.secuenciaNodo(1)
				.version(BigDecimal.ONE)
				.build();
		EstatusAtributosMonedaTO estatus = helper.leeAtributosMoneda(
				getDatosAutenticacionTO(),
				nodo,
				"VPRO_SEC_MONEDA_ENTIDAD",
				"VARIABLE_PROCESO");
		
		log.info("Mensaje: {}", estatus.getMensaje());
		log.info("------> TipoExcepcion: {}", estatus.getTipoExcepcion());
		if( estatus.getTipoExcepcion().equals(Constants.ERROR)) {
			log.error("FALLO EL STORED SP_LEE_ATRIBUTOS_MONEDA");
		}
	}
	
	// SP_LEE_ATRIBUTOS_MONEDA
		@Test
		public void TestSpLeeAtributosMoneda() throws BrioBPMException {
			log.info("INICIA TEST SP_LEE_ATRIBUTOS_MONEDA");
			// Ejecución del método a probar
			NodoTO nodo = NodoTO.builder()
					.rol("ROL_ADMIN_OPERA")
					.cveInstancia("202110-000001")
					.cveNodo("ACTIVIDAD-USUARIO")
					.cveProceso("DUMMY")
					.idNodo(1)
					.secuenciaNodo(1)
					.version(BigDecimal.ONE)
					.build();
			EstatusAtributosMonedaTO estatus = helper.leeAtributosMoneda(
					getDatosAutenticacionTO(),
					nodo,
					"VPRO_SEC_MONEDA_ENTIDAD",
					"VARIABLE_PROCESO");
			
			log.info("Mensaje: {}", estatus.getMensaje());
			log.info("------> TipoExcepcion: {}", estatus.getTipoExcepcion());
			if( estatus.getTipoExcepcion().equals(Constants.ERROR)) {
				log.error("FALLO EL STORED SP_LEE_ATRIBUTOS_MONEDA");
			}
		}
	
//	// SP_LEE_COLUMNAS_AREA_TRABAJO
//	@Test
//	public void spLeeCATTest() throws BrioBPMException {
//		log.info("INICIA TEST SP_LEE_COLUMNAS_AREA_TRABAJO");
//		// Ejecución del método a probar
//		NodoTO nodo = NodoTO.builder()
//				.rol("ROL_ADMIN_OPERA")
//				.cveProceso("G2 ACF")
//				.version(BigDecimal.ONE)
//				.cveNodo("ACTIVIDAD-USUARIO")
//				.idNodo(1)
//				.build();
//		DatosAutenticacionTO usuario = getDatosAutenticacionTO();
//		usuario.setCveUsuario("Admin.Opera");
//		EstatusAreaTrabajoColumnasTO estatus = helper.leeColumnaAreaTrabajo(
//				getDatosAutenticacionTO(), nodo, "AREA_SIN_SLA");
//		
//		log.info("Mensaje: {}", estatus.getMensaje());
//		log.info("------> TipoExcepcion: {}", estatus.getTipoExcepcion());
//		if( estatus.getTipoExcepcion().equals(Constants.ERROR)) {
//			log.error("FALLO EL STORED SP_LEE_COLUMNAS_AREA_TRABAJO");
//		}
//	}
}
