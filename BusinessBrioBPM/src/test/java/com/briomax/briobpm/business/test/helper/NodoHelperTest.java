package com.briomax.briobpm.business.test.helper;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.briomax.briobpm.business.helpers.NodoHelper;
import com.briomax.briobpm.business.service.EjecucionActividadService;
import com.briomax.briobpm.business.test.base.AbstractCoreTest;
import com.briomax.briobpm.common.core.Constants;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.persistence.dao.base.IEjecucionActividadRepository;
import com.briomax.briobpm.persistence.jdto.Documento;
import com.briomax.briobpm.transferobjects.DataOccurrenceTO;
import com.briomax.briobpm.transferobjects.DataSectionTO;
import com.briomax.briobpm.transferobjects.SaveSectionTO;
import com.briomax.briobpm.transferobjects.SectionVariablesTO;
import com.briomax.briobpm.transferobjects.in.ActividadTO;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;
import com.briomax.briobpm.transferobjects.in.ElementoCadenaTO;
import com.briomax.briobpm.transferobjects.in.EstatusCompuertaTO;
import com.briomax.briobpm.transferobjects.in.EstatusCondicionTO;
import com.briomax.briobpm.transferobjects.in.EstatusConfiguracionEnvioTO;
import com.briomax.briobpm.transferobjects.in.EstatusTO;
import com.briomax.briobpm.transferobjects.in.EstatusVariablesTO;
import com.briomax.briobpm.transferobjects.in.NodoTO;
import com.briomax.briobpm.transferobjects.in.StSeccionNodoTO;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Class NodoHelperTest.java es ...
 * 
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Nov 21, 2023 11:50:00 AM Modificaciones:
 * @since JDK 1.8
 */
@Slf4j
public class NodoHelperTest extends AbstractCoreTest {

	@Autowired
	private NodoHelper nodoHelper;
	
	@Autowired
	private EjecucionActividadService actividadService;
	
	/** El atributo o variable ejecucion actividad repository. */
	@Autowired
	private IEjecucionActividadRepository ejecucionActividadRepository;

	
	// SP_CREA_FOLIO_IN_NODO:
	@Ignore
	@Test
	public void spCreaFolioEnNodoTest() throws BrioBPMException {
		log.info("INICIA TEST SP_CREA_FOLIO_IN_NODO");
		// Ejecución del método a probar
		NodoTO nodo = NodoTO.builder()
				.idProceso("CREA_NODO")
				.cveProceso("DUMMY")
				.version(new BigDecimal(1.0))
				.cveInstancia("202205-000001")
				.cveNodo("EVENTO-INICIO")
				.idNodo(1)
				.secuenciaNodo(1)
				.build();
		EstatusVariablesTO estatus = nodoHelper.creaFolio(
				getDatosAutenticacionTO(), nodo);
		
		log.info("Mensaje: {}", estatus.getMensaje());
		log.info("------> TipoExcepcion: {}", estatus.getTipoExcepcion());
		if( estatus.getTipoExcepcion().equals(Constants.ERROR)) {
			log.error("FALLO EL STORED SP_CREA_FOLIO_IN_NODO");
		}
	}
	
	// SP_LEE_CONFIGURACION_ENVIO
	@Ignore
	@Test
	public void spLeeConfiguracionEnvioTest() throws BrioBPMException {
		log.info("INICIA TEST SP_LEE_CONFIGURACION_ENVIO");

		NodoTO nodo = NodoTO.builder()
				.idProceso("CREA_NODO")
				.cveProceso("VENTAS")
				.version(new BigDecimal(1.0))
				.cveInstancia("202205-000001")
				.cveNodo("EVENTO-INTERMEDIO-ENVIO")
				.idNodo(1)
				.secuenciaNodo(1)
				.build();
		EstatusConfiguracionEnvioTO result = nodoHelper.leeConfiguracionEnvio(
				getDatosAutenticacionTO(), nodo);
	
		log.info("Mensaje: {}", result.getMensaje());
		log.info("TipoExcepcion: {}", result.getTipoExcepcion());
		if( result.getTipoExcepcion().equals(Constants.ERROR)) {
			log.error("FALLO EL STORED SP_LEE_CONFIGURACION_ENVIO");
		}
	}
		
	// SP_LEE_CONFIGURACION_RECEPCION
	@Ignore
	@Test
	public void spLeeConfiguracionRecepcionTest() throws BrioBPMException {
		log.info("INICIA TEST SP_LEE_CONFIGURACION_RECEPCION");

		NodoTO nodo = NodoTO.builder()
				.idProceso("CREA_NODO")
				.cveProceso("IMPLEMENTACION")
				.version(new BigDecimal(1.0))
				.cveInstancia("202205-000001")
				.cveNodo("EVENTO-INICIO-MENSAJE")
				.idNodo(1)
				.secuenciaNodo(1)
				.build();
		EstatusVariablesTO estatus = nodoHelper.leeConfiguracionRecepcion(
				getDatosAutenticacionTO(), nodo);
	
		log.info("Mensaje: {}", estatus.getMensaje());
		log.info("spLeeConfiguracionRecepcionTest -- TipoExcepcion: {}", estatus.getTipoExcepcion());
		if( estatus.getTipoExcepcion().equals(Constants.ERROR)) {
			log.error("FALLO EL STORED SP_LEE_CONFIGURACION_RECEPCION");
		}
	}
	
	// SP_CREA_FOLIO_MENSAJE
	@Ignore
	@Test
	public void spCreaFolioMensaje() throws BrioBPMException {
		log.info("INICIA TEST SP_CREA_FOLIO_MENSAJE");
		NodoTO nodo = NodoTO.builder()
				.idProceso("CREA_NODO")
				.cveProceso("DUMMY")
				.version(new BigDecimal(1.0))
				.cveInstancia("202205-000002")
				.cveNodo("ACTIVIDAD-USUARIO")
				.idNodo(1)
				.secuenciaNodo(1)
				.build();
		EstatusVariablesTO estatus = nodoHelper.creaFolioMensaje(
				getDatosAutenticacionTO(), nodo);
		
		log.info("Mensaje: {}", estatus.getMensaje());
		log.info("TipoExcepcion: {}", estatus.getTipoExcepcion());
		if( estatus.getTipoExcepcion().equals(Constants.ERROR)) {
			log.error("FALLO EL STORED SP_CREA_FOLIO_MENSAJE");
		}
	}
	
	// SP_CREA_VARIABLES_ENVIO
	@Ignore
	@Test
	public void spCreaVariablesEnvioTest() throws BrioBPMException, ParseException {
		log.info("INICIA TEST SP_CREA_VARIABLES_ENVIO");
		
		NodoTO nodo = NodoTO.builder()
				.idProceso("CREA_NODO")
				.cveProceso("VENTAS")
				.version(new BigDecimal(1.0))
				.cveInstancia("202205-000001")
				.cveNodo("EVENTO-INTERMEDIO-ENVIO")
				.idNodo(1)
				.secuenciaNodo(1)
				.build();
		EstatusTO result = nodoHelper.creaVariablesEnvio(
				getDatosAutenticacionTO(), nodo);
	
		log.info("fnLeeDireccionCorreoTest -- TipoExcepcion: {}", result.toString());
		if( result.getTipoExcepcion().equals(Constants.ERROR)) {
			log.error("FALLO EL STORED SP_CREA_VARIABLES_ENVIO");
		}
	}
	
	// SP_LEE_VALOR_VPRO
	@Ignore
	@Test
	public void spLeerValorVproTest() throws BrioBPMException {
		log.info("INICIA TEST SP_LEE_VALOR_VPRO");
		
		NodoTO nodo = NodoTO.builder()
				.idProceso("CREA_NODO")
				.cveProceso("DUMMY")
				.version(new BigDecimal(1.0))
				.cveInstancia("202205-000001")
				.cveVariable("VPRO_ALFANUMERICO_CAJA_TEXTO")
				.cveNodo(null)
				.ocurrencia(1)
				.idNodo(1)
				.secuenciaNodo(1)
				.build();
		EstatusVariablesTO estatus = nodoHelper.leerValorVpro(
				getDatosAutenticacionTO(), nodo, "SUMA", 1);
		log.info("TipoExcepcion: {}", estatus.getTipoExcepcion());
		log.info("Mensaje: {}", estatus.getMensaje());
		if( estatus.getTipoExcepcion().equals(Constants.ERROR)) {
			log.error("FALLO EL STORED SP_LEE_VALOR_VPRO");
		}
	}
	
	// SP_LEE_VALOR_VLOC
	@Ignore
	@Test
	public void spLeerValorVlocTest() throws BrioBPMException {
		log.info("INICIA TEST SP_LEE_VALOR_VLOC");

		EstatusVariablesTO estatus = nodoHelper.leerValorVeloc(
				getDatosAutenticacionTO(), "DUMMY", 
				new BigDecimal(1.0), "VLOC_IVA"
				);
	
		log.info("TipoExcepcion: {}", estatus.getTipoExcepcion());
		log.info("Mensaje: {}", estatus.getMensaje());
		if( estatus.getTipoExcepcion().equals(Constants.ERROR)) {
			log.error("FALLO EL STORED SP_LEE_VALOR_VLOC");
		}
	}
	
	
	// SP_LEE_VALOR_VENT
	@Ignore
	@Test
	public void spLeerValorVentTest() throws BrioBPMException {
		log.info("INICIA TEST SP_LEE_VALOR_VENT");

		EstatusVariablesTO estatus = nodoHelper.leerValorVent(
				getDatosAutenticacionTO(),
				"DUMMY",
				new BigDecimal(1.0),
				"VENT_ENTERO_PRUEBA"
				);
	
		log.info("TipoExcepcion: {}", estatus.getTipoExcepcion());
		log.info("Mensaje: {}", estatus.getMensaje());
		if( estatus.getTipoExcepcion().equals(Constants.ERROR)) {
			log.error("FALLO EL STORED SP_LEE_VALOR_VENT");
		}
	}
	
	// SP_LEE_VALOR_VSIS
	@Test
	public void spLeerValorVsisTest() throws BrioBPMException {
		log.info("INICIA TEST SP_LEE_VALOR_VSIS");
		DatosAutenticacionTO datosAutenticacion = DatosAutenticacionTO.builder()
				.cveEntidad("BRIOMAX")
				.cveLocalidad("BRIOMAX-CENTRAL")
				.cveUsuario("Miguel.Garcia.Saavedra")
				.cveIdioma("ES-MX").build()	;
		
		EstatusVariablesTO estatus = nodoHelper.leerValorVsis(
				datosAutenticacion,
				"INCIDENCIA-SMI",
				new BigDecimal(1.0),
				"VSIS_FECHA_ACTUAL"
				);
		log.info("Mensaje: {}", estatus.getMensaje());
		log.info("TipoExcepcion: {}", estatus.getTipoExcepcion());
		if( estatus.getTipoExcepcion().equals(Constants.ERROR)) {
			log.error("FALLO EL STORED SP_LEE_VALOR_VSIS");
		}
	}
	
		
	// SP_LEE_VALOR_VPN
	@Ignore
	@Test
	public void spLeerValorVpnTest() throws BrioBPMException, ParseException {
		log.info("INICIA TEST SP_LEE_VALOR_VPN");
		NodoTO nodo = NodoTO.builder()
				.idProceso("CREA_NODO")
				.cveProceso("REBATES AND REWARDS EJECUCIÓN")
				.version(new BigDecimal(1.0))
				.cveInstancia("202111-000001")
				.cveNodo("ACTIVIDAD-USUARIO-TEMPORIZACION")
				.idNodo(1)
				.secuenciaNodo(1)
				.build();
		EstatusVariablesTO estatus = nodoHelper.leerValorVpn(
				getDatosAutenticacionTO(), nodo, "DA.ID_NODO_ORIGEN");
		log.info("Mensaje: {}", estatus.getMensaje());
		log.info("TipoExcepcion: {}", estatus.getTipoExcepcion());
		if( estatus.getTipoExcepcion().equals(Constants.ERROR)) {
			log.error("FALLO EL STORED SP_LEE_VALOR_VPN");
		}
		
		estatus = nodoHelper.leerValorVpn(
				getDatosAutenticacionTO(), nodo, "DA.FECHA_BLOQUEO");
		log.info("Mensaje: {}", estatus.getMensaje());
		log.info("TipoExcepcion: {}", estatus.getTipoExcepcion());
		if( estatus.getTipoExcepcion().equals(Constants.ERROR)) {
			log.error("FALLO EL STORED SP_LEE_VALOR_VPN");
		}
		
		estatus = nodoHelper.leerValorVpn(
				getDatosAutenticacionTO(), nodo, "DA.VERSION");
		log.info("Mensaje: {}", estatus.getMensaje());
		log.info("TipoExcepcion: {}", estatus.getTipoExcepcion());
		if( estatus.getTipoExcepcion().equals(Constants.ERROR)) {
			log.error("FALLO EL STORED SP_LEE_VALOR_VPN");
		}
		
		estatus = nodoHelper.leerValorVpn(
				getDatosAutenticacionTO(), nodo, "DA.USUARIO_CREADOR_PROCESO");
		log.info("Mensaje: {}", estatus.getMensaje());
		log.info("TipoExcepcion: {}", estatus.getTipoExcepcion());
		if( estatus.getTipoExcepcion().equals(Constants.ERROR)) {
			log.error("FALLO EL STORED SP_LEE_VALOR_VPN");
		}
		
		NodoTO nodo2 = NodoTO.builder()
				.idProceso("CREA_NODO")
				.cveProceso("REBATES AND REWARDS EJECUCIÓN")
				.version(new BigDecimal(1.0))
				.cveInstancia("202111-000001")
				.build();
		estatus = nodoHelper.leerValorVpn(
				getDatosAutenticacionTO(), nodo2, "DA.ID_NODO_ORIGEN");
		log.info("Mensaje: {}", estatus.getMensaje());
		log.info("TipoExcepcion: {}", estatus.getTipoExcepcion());
		if( estatus.getTipoExcepcion().equals(Constants.ERROR)) {
			log.error("FALLO EL STORED SP_LEE_VALOR_VPN");
		}
		
		estatus = nodoHelper.leerValorVpn(
				getDatosAutenticacionTO(), nodo2, "DA.FECHA_BLOQUEO");
		log.info("Mensaje: {}", estatus.getMensaje());
		log.info("TipoExcepcion: {}", estatus.getTipoExcepcion());
		if( estatus.getTipoExcepcion().equals(Constants.ERROR)) {
			log.error("FALLO EL STORED SP_LEE_VALOR_VPN");
		}
		
		estatus = nodoHelper.leerValorVpn(
				getDatosAutenticacionTO(), nodo2, "DA.VERSION");
		log.info("Mensaje: {}", estatus.getMensaje());
		log.info("TipoExcepcion: {}", estatus.getTipoExcepcion());
		if( estatus.getTipoExcepcion().equals(Constants.ERROR)) {
			log.error("FALLO EL STORED SP_LEE_VALOR_VPN");
		}
		
		estatus = nodoHelper.leerValorVpn(
				getDatosAutenticacionTO(), nodo2, "DA.USUARIO_CREADOR_PROCESO");
		log.info("Mensaje: {}", estatus.getMensaje());
		log.info("TipoExcepcion: {}", estatus.getTipoExcepcion());
		if( estatus.getTipoExcepcion().equals(Constants.ERROR)) {
			log.error("FALLO EL STORED SP_LEE_VALOR_VPN");
		}
	}
	
	// SP_LEE_VALOR_VDOC
	@Ignore
	@Test
	public void spLeerValorVdocTest() throws BrioBPMException {
		log.info("INICIA TEST SP_LEE_VALOR_VDOC");
		NodoTO nodo = NodoTO.builder()
				.idProceso("CREA_NODO")
				.cveProceso("DUMMY")
				.version(new BigDecimal(1.0))
				.cveInstancia("202205-000001")
				.cveNodo("ACTIVIDAD-USUARIO")
				.idNodo(1)
				.secuenciaNodo(1)
				.cveVariable("EVENTO-INTERMEDIO-ENVIO")
				.ocurrencia(1)
				.build();
		EstatusVariablesTO estatus = nodoHelper.leerValorVdoc(
				getDatosAutenticacionTO(), nodo);
	
		log.info("Mensaje: {}", estatus.getMensaje());
		log.info("TipoExcepcion: {}", estatus.getTipoExcepcion());
		if( estatus.getTipoExcepcion().equals(Constants.ERROR)) {
			log.error("FALLO EL STORED SP_LEE_VALOR_VDOC ");
		}
	}
	
	// SP_CREA_TAREAS_NODO.
	@Ignore
	@Test
	public void spCreaTareasTest() throws BrioBPMException {
		log.info("INICIA TEST SP_CREA_TAREAS_NODO");
		NodoTO nodo = NodoTO.builder()
				.idProceso("CREA_NODO")
				.cveProceso("DUMMY")
				.version(new BigDecimal(1.0))
				.cveInstancia("202205-000001")
				.cveNodo("ACTIVIDAD-USUARIO")
				.idNodo(1)
				.secuenciaNodo(1)
				.build();
		EstatusTO estatus = nodoHelper.creaTareas(
				getDatosAutenticacionTO(), nodo);
		
		log.info("Mensaje: {}", estatus.getMensaje());
		log.info("TipoExcepcion: {}", estatus.getTipoExcepcion());
		if( estatus.getTipoExcepcion().equals(Constants.ERROR)) {
			log.error("FALLO EL STORED SP_CREA_TAREAS_NODO");
		}
	}


	// SP_CREA_DOCUMENTOS_NODO.
	@Ignore
	@Test
	public void spCreaDocumentosTest() throws BrioBPMException {
		log.info("INICIA TEST SP_CREA_DOCUMENTOS_NODO");
		NodoTO nodo = NodoTO.builder()
				.idProceso("CREA_NODO")
				.cveProceso("DUMMY")
				.version(new BigDecimal(1.0))
				.cveInstancia("202205-000001")
				.cveNodo("ACTIVIDAD-USUARIO")
				.idNodo(1)
				.secuenciaNodo(1)
				.build();
		EstatusTO estatus = nodoHelper.creaDocumentos(
				getDatosAutenticacionTO(), nodo);
		
		log.info("Mensaje: {}", estatus.getMensaje());
		log.info("TipoExcepcion: {}", estatus.getTipoExcepcion());
		if( estatus.getTipoExcepcion().equals(Constants.ERROR)) {
			log.error("FALLO EL STORED SP_CREA_DOCUMENTOS_NODO");
		}
	}
	
	// SP_RECIBE_VARIABLES_ENVIO.  revisar TABLA IN_MENSAJE_ENVIO VACIA
	@Ignore
	@Test
	public void spReciveValoresEnvioTest() throws BrioBPMException {
		log.info("INICIA TEST SP_RECIBE_VARIABLES_ENVIO");

		EstatusTO estatus = nodoHelper.recibeValoresEnvio(
				getDatosAutenticacionTO(),
				"CREA_INSTANCIA_PROCESO",
				1,
				"BRIOMAX",
				"DUMMY",
				new BigDecimal(1.0),
				"202205-000001");
	
		log.info("Mensaje: {}", estatus.getMensaje());
		log.info("TipoExcepcion: {}", estatus.getTipoExcepcion());
		if( estatus.getTipoExcepcion().equals(Constants.ERROR)) {
			log.error("FALLO EL STORED SP_RECIBE_VARIABLES_ENVIO");
		}
	}
	
	// SP_REEMPLAZA_VARIABLES
	@Ignore
	@Test
	public void spReemplazaVariablesTest() throws BrioBPMException, ParseException {
		log.info("INICIA TEST SP_REEMPLAZA_VARIABLES");
		NodoTO nodo = NodoTO.builder()
				.cveProceso("ALTA_CONTRATISTA_CONTRATANTE")
				.version(new BigDecimal(1.0))
				.cveEventoCorreo("ASIGNACION_ACTIVIDAD")
				.cveUsuarioDestinatario(null)
				.cveRolDestinatario(null)
				.cveNodo("ACTIVIDAD-USUARIO")
				.idNodo(3)
				.ocurrencia(1)
				.cveInstancia("202407-000001")
				.secuenciaNodo(1)
				
				.build();
		EstatusVariablesTO estatus = nodoHelper.reemplazaVariables(
				getDatosAutenticacionTO(), nodo, null, "<font face=\"Arial\" size=2>Estimado Usuario:<br><br>Le informamos que durante la ejecución del proceso <b>@DA.DESCRIPCION_PROCESO@</b>, <br>le ha sido asignada la siguiente actividad:<br><br><b>@DA.DESCRIPCION_ACTIVIDAD@</b><br><br>con fecha de asignación:<br><br><b>@DA.FECHA_HORA_CREACION_ACTIVIDAD@,</b><br><br>la cual tiene como fecha de vencimiento: <br><br><b>@DA.FECHA_HORA_LIMITE@.</b><br><br>Favor de ingresar a BRIO-Workflow para su atención.<br><br><A HREF=\"https://beta.briomax.com/bbpmdev/\">Ingreso a BRIO_Workflow</A><br><br>Atentamente<br><br><br><br><br></font><b>BRIO-Workflow</b>");
	
		log.info("Mensaje: {}", estatus.getMensaje());
		log.info("TipoExcepcion: {}", estatus.getTipoExcepcion());
		if( estatus.getTipoExcepcion().equals(Constants.ERROR)) {
			log.error("FALLO EL STORED SP_REEMPLAZA_VARIABLES");
		}
	}
	
	// SP_EVALUA_CONDICION
	@Ignore
	@Test
	public void spEvaluaCondicionTest() throws BrioBPMException {
		log.info("INICIA TEST SP_EVALUA_CONDICION");

		NodoTO nodo = NodoTO.builder()
				.idProceso("CREA_NODO")
				.cveProceso("ALTA_CONTRATISTA_CONTRATANTE")
				.version(new BigDecimal(1.0))
				.cveInstancia("202408-000006")
				.cveNodo("ACTIVIDAD-USUARIO")
				.idNodo(4)
				.secuenciaNodo(1)
				.build();
		EstatusCondicionTO estatus = nodoHelper.evaluarCondicion(
				getDatosAutenticacionTO(), nodo,
				"@VPRO_01_INFORMACION_CORRECTA_VERIFICACION_CONTRATANTE@ = 'SI'");
	
		log.info("Mensaje: {}", estatus.getMensaje());
		log.info("TipoExcepcion: {}", estatus.getTipoExcepcion());
		if( estatus.getTipoExcepcion().equals(Constants.ERROR)) {
			log.error("FALLO EL STORED SP_EVALUA_CONDICION");
		}
	}
	
	// SP_GENERA_EVENTO_BITACORA.
	@Ignore
	@Test
	public void spGeneraEventoBitacoraTest() throws BrioBPMException {
		log.info("INICIA TEST SP_GENERA_EVENTO_BITACORA");
		NodoTO nodo = NodoTO.builder()
				.idProceso("CREA_NODO")
				.cveProceso("DUMMY")
				.version(new BigDecimal(1.0))
				.cveInstancia("202111-000001")
				.cveNodo("ACTIVIDAD-USUARIO")
				.idNodo(1)
				.secuenciaNodo(1)
				.cveVariable("COMENTARIO")
				.build();
		log.info("---> CLAVE NODO: " + nodo.getCveNodo());
		EstatusTO estatus = nodoHelper.generaEventoBitacora(
				getDatosAutenticacionTO(), nodo, "Guardar");
		
		log.info("Mensaje: {}", estatus.getMensaje());
		log.info("TipoExcepcion: {}", estatus.getTipoExcepcion());
		if( estatus.getTipoExcepcion().equals(Constants.ERROR)) {
			log.error("FALLO EL STORED SP_GENERA_EVENTO_BITACORA");
		}
	}	
	
	// SP_VAL_DATOS_IN_NODO
	@Ignore
	@Test
	public void spValidaDatosInNodoTest() throws BrioBPMException {
		log.info("INICIA TEST SP_VAL_DATOS_IN_NODO");
		NodoTO nodo = NodoTO.builder()
				.idProceso("GENERA_EVENTO_BITACORA")
				.cveProceso("DUMMY")
				.version(new BigDecimal(1.0))
				.cveInstancia("202110-000001")
				.cveNodo("ACTIVIDAD-USUARIO")
				.idNodo(1)
				.secuenciaNodo(1)
				.build();
		EstatusTO estatus = nodoHelper.valDatosIn(
				getDatosAutenticacionTO(), nodo);
	
		log.info("Mensaje: {}", estatus.getMensaje());
		log.info("TipoExcepcion: {}", estatus.getTipoExcepcion());
		if( estatus.getTipoExcepcion().equals(Constants.ERROR)) {
			log.error("FALLO EL STORED SP_VAL_DATOS_IN_NODO");
		}
	}
	
	// SP_TERMINA_COMPUERTA_CIERRE revisar valores de entrada
	@Ignore
	@Test
	public void spTerminaCompuertaCierreTest() throws BrioBPMException {
		log.info("INICIA TEST SP_TERMINA_COMPUERTA_CIERRE");
		NodoTO nodo = NodoTO.builder()
				.idProceso("CREA_NODO")
				.cveProceso("G2 ACF")
				.version(new BigDecimal(1.0))
				.cveInstancia("202311-000001")
				.cveNodo("COMPUERTA-PARALELA-CIERRE")
				.idNodo(1)
				.secuenciaNodo(1)
				.rol("ROL DUMMY 1")
				.cveVariable("COMENTARIO")
				.build();
		EstatusCompuertaTO estatus = nodoHelper.terminaCompuertaCierre(
				getDatosAutenticacionTO(), nodo, "COMPUERTA-PARALELA-CIERRE", 1);
	
		log.info("Mensaje: {}", estatus.getMensaje());
		log.info("TipoExcepcion: {}", estatus.getTipoExcepcion());
		if( estatus.getTipoExcepcion().equals(Constants.ERROR)) {
			log.error("FALLO EL STORED SP_TERMINA_COMPUERTA_CIERRE");
		}
	}
	
	
	// SP_CREA_CORREO_PROCESO
	
	@Test
	public void spCreaCorreoProcesoTest() throws BrioBPMException, ParseException {
		log.info("INICIA TEST SP_CREA_CORREO_PROCESO");
		NodoTO nodo = NodoTO.builder()
				.cveProceso("FACT_SERV_CONT")
				.version(new BigDecimal(1.0))
				.cveEventoCorreo("REPSE_NO_VALIDO")
				.cveUsuarioDestinatario(null)
				.cveRolDestinatario(null)
				.cveNodo("ACTIVIDAD-USUARIO")
				.idNodo(2)
				.ocurrencia(1)
				.cveInstancia("202509-000001")
				.secuenciaNodo(1)
				.ocurrencia(1)
				
				.build();
		EstatusTO estatus = nodoHelper.creaCorreoProceso(getDatosAutenticacionTO(), nodo, null, "U.ALIANZA");
	
		log.info("Mensaje: {}", estatus.getMensaje());
		log.info("TipoExcepcion: {}", estatus.getTipoExcepcion());
		if( estatus.getTipoExcepcion().equals(Constants.ERROR)) {
			log.error("FALLO EL STORED SP_CREA_CORREO_PROCESO");
		}
	}
	
	// SP_CREA_VARIABLES_SECCION.
	@Ignore
	@Test
	public void spCreaVariablesSeccionTest() throws BrioBPMException {
		log.info("INICIA TEST SP_CREA_VARIABLES_SECCION");
		NodoTO nodo = NodoTO.builder()
				.idProceso("CREA_NODO")
				.cveProceso("DUMMY")
				.version(new BigDecimal(1.0))
				.cveInstancia("202205-000001")
				.cveNodo("ACTIVIDAD-USUARIO")
				.idNodo(1)
				.secuenciaNodo(1)
				.cveVariable("COMENTARIO")
				.build();
		EstatusTO estatus = nodoHelper.creaVariablesSeccion(
				getDatosAutenticacionTO(), nodo, "DOCUMENTOS", 1);
		log.info("Mensaje: {}", estatus.getMensaje());
		log.info("TipoExcepcion: {}", estatus.getTipoExcepcion());
		if( estatus.getTipoExcepcion().equals(Constants.ERROR)) {
			log.error("FALLO EL STORED SP_CREA_VARIABLES_SECCION");
		}
	}
	
	// SP_CREA_MENSAJE_NODO
	@Ignore
	@Test
	public void spCreaMensajeNodoTest() throws BrioBPMException, ParseException {
		log.info("INICIA TEST SP_CREA_MENSAJE_NODO");
		NodoTO nodo = NodoTO.builder()
				.idProceso("CREA_NODO")
				.cveProceso("DUMMY")
				.version(new BigDecimal(1.0))
				.cveInstancia("202205-000001")
				.cveNodo("ACTIVIDAD-USUARIO")
				.idNodo(1)
				.secuenciaNodo(1)
				.cveVariable("COMENTARIO")
				.build();
		EstatusTO estatus = nodoHelper.creaMensaje(getDatosAutenticacionTO(), nodo);
		
		log.info("Mensaje: {}", estatus.getMensaje());
		log.info("TipoExcepcion: {}", estatus.getTipoExcepcion());
		if( estatus.getTipoExcepcion().equals(Constants.ERROR)) {
			log.error("FALLO EL STORED SP_CREA_MENSAJE_NODO");
		}
	}
	// SP_CREA_INSTANCIA_NODO
	@Ignore
	@Test
	public void spCreaInstanciaTest() throws BrioBPMException, ParseException {
		log.info("INICIA TEST SP_CREA_INSTANCIA_NODO");
		NodoTO instanciaNodo = NodoTO.builder()
				.idProceso("CREA_NODO")
				.cveProceso("PEDIDO_DE_SALAS")
				.version(BigDecimal.ONE)
				//.cveInstancia("202207-000001")
				.cveNodo("ACTIVIDAD-USUARIO")
				.idNodo(3)
				.secuenciaNodo(1)
				.tipoGeneracion("SIG")
				.tipoNodoSiguiente("NORMAL")
				.cveNodoInicio("ACTIVIDAD-USUARIO")
				.idNodoInicio(3)
				.rol("CARPINTERO")
				.cveVariable("COMENTARIO")
				.folioMensajeEnvio(null)
				.build();
		EstatusTO estatus = nodoHelper.creaInstancia(getDatosAutenticacionTO(), instanciaNodo);
		
		log.info("Mensaje: {}", estatus.getMensaje());
		log.info("TipoExcepcion: {}", estatus.getTipoExcepcion());
		if( estatus.getTipoExcepcion().equals(Constants.ERROR)) {
			log.error("FALLO EL STORED SP_CREA_INSTANCIA_NODO");
		}
	}
		
	// SP_CREA_NODO
	@Ignore
	@Test
	public void spCreaNodoTest() throws BrioBPMException, ParseException {
		log.info("INICIA TEST SP_CREA_NODO");
		NodoTO nodo = NodoTO.builder()
				.idProceso("CREA_NODO")
				.cveProceso("DUMMY")
				.version(new BigDecimal(1.0))
				.cveInstancia("202205-000001")
				.cveNodo("ACTIVIDAD-USUARIO")
				.idNodo(1)
				.secuenciaNodo(1)
				.cveVariable("COMENTARIO")
				.build();
		EstatusVariablesTO estatus = nodoHelper.creaNodo(
				getDatosAutenticacionTO(), nodo, "", 1,
				"", "SI", 1);
	
		log.info("Mensaje: {}", estatus.getMensaje());
		log.info("TipoExcepcion: {}", estatus.getTipoExcepcion());
		if( estatus.getTipoExcepcion().equals(Constants.ERROR)) {
			log.error("FALLO EL STORED SP_CREA_NODO");
		}
	}

	// FN_LEE_DIRECCION_CORREO.
	@Ignore
	@Test
	public void fnLeeDireccionCorreoTest() throws BrioBPMException {
		log.info("INICIA TEST FN_LEE_DIRECCION_CORREO");

		String result = nodoHelper.fnLeeDireccionCorreo(
				"BRIOMAX",
				"DUMMY",
				new BigDecimal(1.0),
				"Francisco.Rodriguez",
				"");
	
		log.info("fnLeeDireccionCorreoTest -- TipoExcepcion: {}", result);
	}

	// FN_TRUNCA_DECIMALES.
	@Test
	public void truncarDecimalesTest() throws BrioBPMException {
		log.info("INICIA TEST FN_TRUNCA_DECIMALES");

		String result = nodoHelper.truncarDecimales(
				new BigDecimal(1111.055555),
				null);
	
		log.info("truncarDecimalesTest  -- TipoExcepcion: {}", result);
	}
	
		
	@Test
	public void spLeerValorVpnTestFECHA() throws BrioBPMException, ParseException {
		log.info("INICIA TEST SP_LEE_VALOR_VPN");
		NodoTO nodo = NodoTO.builder()
				.idProceso("CREA_NODO")
				.cveProceso("DUMMY")
				.version(new BigDecimal(1.0))
				.cveInstancia("202404-000012")
				.cveNodo("ACTIVIDAD-USUARIO")
				.idNodo(1)
				.secuenciaNodo(1)
				.build();
		EstatusVariablesTO estatus = nodoHelper.leerValorVpn(
				getDatosAutenticacionTO(), nodo, "DA.FECHA_HORA_LIMITE");
		log.info("Mensaje: {}", estatus.getMensaje());
		log.info("TipoExcepcion: {}", estatus.getTipoExcepcion());
		if( estatus.getTipoExcepcion().equals(Constants.ERROR)) {
			log.error("FALLO EL STORED SP_LEE_VALOR_VPN");
		}
	}

	// SP_GUARDA_TAREAS_NODO
	@Test
	public void spGuardaTareasNodo() throws BrioBPMException {
		log.info("INICIA TEST SP_LEE_VALOR_VPN");
		NodoTO nodo = NodoTO.builder()
				.idProceso("CREA_NODO")
				.cveProceso("INCIDENCIA-SMI")
				.version(new BigDecimal(1.0))
				.cveInstancia("201807-000001")
				.cveNodo("ACTIVIDAD-USUARIO")
				.idNodo(1)
				.secuenciaNodo(2)
				.build();
		RetMsg estatus = nodoHelper.guardaTareasNodo(
				getDatosAutenticacionTO(),
				nodo,
				"1|SI|2|SI");
		
		log.info("Mensaje: {}", estatus.getMessage());
		log.info("TipoExcepcion: {}", estatus.getStatus());
		if( estatus.getStatus().equals(Constants.ERROR)) {
			log.error("FALLO EL STORED SP_GUARDA_TAREAS_NODO");
		}
	}
	
	
	// SP_GUARDA_VARIABLES_SECCION
	@Test
	public void spGuardaVariablesSeccion() throws BrioBPMException, ParseException {
	 log.info("INICIA TEST SP_GUARDA_VARIABLES_SECCION");
	 	DatosAutenticacionTO session = getDatosAutenticacionTO();
	 	session.setCveUsuario("Gabriela.Arias");
	    NodoTO nodo = NodoTO.builder()
	            .idProceso("CREA_NODO")
	            .cveProceso("INCIDENCIA-SMI")
	            .version(new BigDecimal(1.0))
	            .cveInstancia("201807-000001")
	            .cveNodo("ACTIVIDAD-USUARIO")
	            .idNodo(1)
	            .ocurrencia(1)
	            .secuenciaNodo(1)
	            .build();
	    RetMsg estatus = nodoHelper.guardaVariablesSeccion(
	    		session,
	            nodo,
	            "DATOS_GENERALES_SOLICITUD",
	            "SI",
	            "NO", // REVISAR
	            "VPRO_DESCRIPCION_SOLICITUD|1|1|Error al ingresar al Dashboard Principal|VPRO_SEVERIDAD_SOLICITUD|1|1|ALTA|VPRO_USUARIO_CONTACTO|1|1|Gabriela Arias S.|VPRO_TELEFONO_USUARIO|1|1|5543495578|VPRO_HORARIO_CONTACTO|1|1|De 9:00 a 14:00 Hrs."
	    );


	    log.info("Mensaje: {}", estatus.getMessage());
	    log.info("TipoExcepcion: {}", estatus.getStatus());
	    if (estatus.getStatus().equals(Constants.ERROR)) {
	        log.error("FALLO EL STORED SP_GUARDA_TAREAS_NODO");
	    }
	}
	
	@Test
	public void spGuardaVariablesSeccion2() throws BrioBPMException, ParseException {
	 log.info("INICIA TEST SP_GUARDA_VARIABLES_SECCION");
	 	DatosAutenticacionTO session = getDatosAutenticacionTO();
	 	session.setCveUsuario("AIT.Usuario.Cliente");
	    NodoTO nodo = NodoTO.builder()
	    		.rol("ROL_ADMIN_OPERA")
	            .idProceso("CREA_NODO")
	            .cveProceso("ATEN_INCI_TICK")
	            .version(new BigDecimal(1.0))
	            .cveInstancia("202407-000034")
	            .cveNodo("ACTIVIDAD-USUARIO")
	            .idNodo(1)
	            .ocurrencia(1)
	            .secuenciaNodo(1)
	            .build();
	    RetMsg estatus = nodoHelper.guardaVariablesSeccion(
	    		session,
	            nodo,
	            "CREA_FOLI",
	            "NO",
	            "NO", // REVISAR
	            "VPRO_01_NOM_TIENDA|1|1|nueva prueba|VPRO_01_CR|1|1|CR PRUEBA|VPRO_01_NUM_INCIDENTE|1|1|12|VPRO_01_NOM_CLIE|1|1|CLIENTE PRUEBA|VPRO_01_TIPO_FOLIO_A|1|1|ASIGNACION|VPRO_01_OBSER_A|1|1|HAY OBSEDRVACIONES NUEVAS"
	    );


	    log.info("Mensaje: {}", estatus.getMessage());
	    log.info("TipoExcepcion: {}", estatus.getStatus());
	    if (estatus.getStatus().equals(Constants.ERROR)) {
	        log.error("FALLO EL STORED SP_GUARDA_TAREAS_NODO");
	    }
	}
	
	@Test
	public void spGuardaVariablesSeccion3() throws BrioBPMException, ParseException {
	 log.info("INICIA TEST SP_GUARDA_VARIABLES_SECCION");
	 	DatosAutenticacionTO session = getDatosAutenticacionTO();
	 	session.setCveUsuario("Francisco.Rodriguez");
	    NodoTO nodo = NodoTO.builder()
	    		.rol("ROL_ADMIN_OPERA")
	            .idProceso("CREA_NODO")
	            .cveProceso("G2 ACF")
	            .version(new BigDecimal(1.0))
	            .cveInstancia("202311-000002")
	            .cveNodo("ACTIVIDAD-USUARIO")
	            .idNodo(3)
	            .ocurrencia(1)
	            .secuenciaNodo(1)
	            .build();
	    RetMsg estatus = ejecucionActividadRepository.guardaVariablesSeccion(
	    		"Francisco.Rodriguez", "BRIOMAX", "BRIOMAX-CENTRAL","ES-MX", "FACTURACION",
	    		"INCIDENCIA-SMI", BigDecimal.ONE, "201807-000001", "ACTIVIDAD-USUARIO", 9, "DATOS_GENERALES_SOLICITUD",
	    		null, "NO", "NO", "VPRO_DESCRIPCION_SOLICITUD|1|1|No se muestran cifras en el dashboard a nivel terrotorio|VPRO_SEVERIDAD_SOLICITUD|1|1|ALTA|VPRO_USUARIO_CONTACTO|1|1|Alonso Martínez|VPRO_TELEFONO_USUARIO|1|1|5544332211|VPRO_HORARIO_CONTACTO|1|1|9:00-18:00");


	    log.info("Mensaje: {}", estatus.getMessage());
	    log.info("TipoExcepcion: {}", estatus.getStatus());
	    if (estatus.getStatus().equals(Constants.ERROR)) {
	        log.error("FALLO EL STORED SP_GUARDA_TAREAS_NODO");
	    }
	}
	
	
	@Test
	public void guardarActividadTest() throws BrioBPMException, ParseException {
	 log.info("INICIA TEST SP_GUARDA_VARIABLES_SECCION");
	 	DatosAutenticacionTO session = getDatosAutenticacionTO();
	 	session.setCveUsuario("Admin.Opera");
	 	SaveSectionTO nodo = SaveSectionTO.builder()
	 			.activity(
	 	                ActividadTO.builderActividadTO()
                        .cveRol("ROL_ADMIN_OPERA")
                        .cveProceso("G2 ACF")
                        .version("1.00")
                        .cveNodo("ACTIVIDAD-USUARIO")
                        .idNodo(3)
                        .cveAreaTrabajo("AREA_CON_SLA")
                        .cveInstancia("202311-000002")
                        .secNodo(1)
                        .build()
	 	                
	 	            )
	            .data(Arrays.asList(
	                DataSectionTO.builder()
	                    .cveSection("DOCUMENTOS_DEL_PROSPECTO")
	                    .type("GRID")
	                    .content("DOCUMENTOS")
	                    .dataOccurrence(Arrays.asList(
	                        DataOccurrenceTO.builder().ocurrencia(1).nueva(false).sectionVariables(Arrays.asList()).build(),
	                        DataOccurrenceTO.builder().ocurrencia(2).nueva(false).sectionVariables(Arrays.asList()).build(),
	                        DataOccurrenceTO.builder().ocurrencia(3).nueva(false).sectionVariables(Arrays.asList()).build(),
	                        DataOccurrenceTO.builder().ocurrencia(4).nueva(false).sectionVariables(Arrays.asList()).build(),
	                        DataOccurrenceTO.builder().ocurrencia(5).nueva(false).sectionVariables(Arrays.asList()).build(),
	                        DataOccurrenceTO.builder().ocurrencia(6).nueva(false).sectionVariables(Arrays.asList()).build(),
	                        DataOccurrenceTO.builder().ocurrencia(7).nueva(false).sectionVariables(Arrays.asList()).build(),
	                        DataOccurrenceTO.builder().ocurrencia(8).nueva(false).sectionVariables(Arrays.asList()).build(),
	                        DataOccurrenceTO.builder().ocurrencia(9).nueva(false).sectionVariables(Arrays.asList()).build(),
	                        DataOccurrenceTO.builder().ocurrencia(10).nueva(false).sectionVariables(Arrays.asList()).build()
	                    ))
	                    .build(),
	                DataSectionTO.builder()
	                    .cveSection("DICTAMEN_LEGAL")
	                    .type("SECUENCIAL")
	                    .content("VARIABLES")
	                    .dataOccurrence(Arrays.asList(
	                        DataOccurrenceTO.builder()
	                            .ocurrencia(1)
	                            .nueva(false)
	                            .sectionVariables(Arrays.asList(
	                                SectionVariablesTO.builder()
	                                    .cveVariable("VPRO_02_RECOMENDACION_LEGAL")
	                                    .values(Arrays.asList("ACEPTAR"))
	                                    .build(),
	                                SectionVariablesTO.builder()
	                                    .cveVariable("VPRO_01_RESULTADO_DICTAMEN_LEGAL")
	                                    .values(Arrays.asList("HOLA MUNDO 12"))
	                                    .build()
	                            ))
	                            .build()
	                    ))
	                    .build(),
	                DataSectionTO.builder()
	                    .cveSection("DOCUMENTO_DICTAMEN_LEGAL")
	                    .type("GRID")
	                    .content("DOCUMENTOS")
	                    .dataOccurrence(Arrays.asList(
	                        DataOccurrenceTO.builder()
	                            .ocurrencia(11)
	                            .nueva(false)
	                            .sectionVariables(Arrays.asList(
	                                SectionVariablesTO.builder()
	                                    .cveVariable("VPRO_01_FECHA_VIGENCIA_DOCUMENTO")
	                                    .values(Arrays.asList(" ", " "))
	                                    .build()
	                            ))
	                            .build()
	                    ))
	                    .build()
	            ))
	            .build();
	    RetMsg estatus = actividadService.guardarActividad(session, nodo);


	    log.info("Mensaje: {}", estatus.getMessage());
	    log.info("TipoExcepcion: {}", estatus.getStatus());
	    if (estatus.getStatus().equals(Constants.ERROR)) {
	        log.error("FALLO EL STORED SP_GUARDA_TAREAS_NODO");
	    }
	}
	
	
	// SP_LEE_DOCUMENTO_BINARIO_NODO
	@Test
	public void spLeeDocumentoBinarioNodo() throws BrioBPMException, ParseException {
	 log.info("INICIA TEST SP_LEE_DOCUMENTO_BINARIO_NODO");
	 	DatosAutenticacionTO session = getDatosAutenticacionTO();
	 	session.setCveUsuario("Gabriela.Arias");
	    NodoTO nodo = NodoTO.builder()
	            .idProceso("CREA_NODO")
	            .cveProceso("INCIDENCIA-SMI")
	            .version(new BigDecimal(1.0))
	            .cveInstancia("202404-000004")
	            .cveNodo("ACTIVIDAD-USUARIO")
	            .idNodo(1)
	            .ocurrencia(1)
	            .secuenciaNodo(1)
	            .build();
	    Documento estatus = nodoHelper.leeDocumentoBinarioNodo(session, nodo, 1);

	    log.info("DOCUMENTO: " + estatus.getContenido());
	    log.info("Mensaje: {}", estatus.getMessage());
	    log.info("TipoExcepcion: {}", estatus.getStatus());
	    if (estatus.getStatus().equals(Constants.ERROR)) {
	        log.error("FALLO EL STORED SP_GUARDA_TAREAS_NODO");
	    }
	}
	
	
	// SP_TERMINA_ACTIVIDAD
	@Test
	public void spTerminaActividadTest() throws BrioBPMException, ParseException {
	 log.info("INICIA TEST SP_TERMINA_ACTIVIDAD");
	 	DatosAutenticacionTO session = getDatosAutenticacionTO();
	 	session.setCveUsuario("Usuario.Contratante");
	    NodoTO nodo = NodoTO.builder()
	            .idProceso(null)
	            .cveProceso("ALTA_CONTRATISTA_CONTRATANTE")
	            .version(new BigDecimal(1.0))
	            .cveInstancia("202409-000013")
	            .cveNodo("ACTIVIDAD-USUARIO")
	            .idNodo(4)
	            .ocurrencia(null)
	            .secuenciaNodo(1)
	            .build();
	    RetMsg estatus = nodoHelper.terminaActividad(session, nodo);

	    log.info("Mensaje: {}", estatus.getMessage());
	    log.info("TipoExcepcion: {}", estatus.getStatus());
	    if (estatus.getStatus().equals(Constants.ERROR)) {
	        log.error("FALLO EL STORED SP_GUARDA_TAREAS_NODO");
	    }
	}
	
	// SP_LEE_ELEMENTO_CADENA_TEST
	@Test
	public void spLeeElementoCadena() throws BrioBPMException, ParseException {
	 log.info("INICIA TEST SP_LEE_ELEMENTO_CADENA_TEST");

	    ElementoCadenaTO estatus = nodoHelper.leeElementoCadena("1|SI|2|SI|3|SI|4|SI|5|SI|6|SI|7|SI|8|SI|9|SI|10|SI",
	    		50, 2, "|");

	    log.info("ENCONTRO: {}", estatus.getEncontro());
	    log.info("POSICION FINAL: {}", estatus.getPosicionFinal());
	    log.info("ELEMNTO: {}", estatus.getElemento());
	
	}
	
	// SP_EXTRAE_DATOS_OCURRENCIA
//	@Test
//	public void spExtraeDatosOcurrencia() throws BrioBPMException, ParseException {
//	 log.info("INICIA TEST SP_LEE_ELEMENTO_CADENA_TEST");
//	 DatosAutenticacionTO session = getDatosAutenticacionTO();
//	 session.setCveUsuario("AIT.Usuario.Cliente");
//	 NodoTO nodo = NodoTO.builder()
//			 .cveProceso("ATEN_INCI_TICK")
//	         .version(new BigDecimal(1.0))
//			 .build();
//	    ElementoCadenaTO estatus = nodoHelper.extraeDatosOcurrencia(session, nodo, datosOcurrencia, valorVariableList);
//
//	    log.info("ENCONTRO: {}", estatus.getEncontro());
//	    log.info("POSICION FINAL: {}", estatus.getPosicionFinal());
//	    log.info("ELEMNTO: {}", estatus.getElemento());
//	
//	}
	
	
	// SP_CAMBIA_SITUACION_NODO
		@Test
		public void spCambiaSituacionNodoTest() throws BrioBPMException {
		 log.info("INICIA TEST SP_CAMBIA_SITUACION_NODO");
		 DatosAutenticacionTO session = getDatosAutenticacionTO();
		 	session.setCveUsuario("Usuario.Contratante");
		 // Usamos el builder para crear una instancia de ActividadTO
		 	ActividadTO actividad = new ActividadTO();
		 	actividad.setCveInstancia("202409-000009");
		 	actividad.setCveProceso("ALTA_CONTRATISTA_CONTRATANTE");
		 	actividad.setVersion("1.0");
		 	actividad.setCveNodo("ACTIVIDAD-USUARIO");
		 	actividad.setSecNodo(1);
		 	actividad.setIdNodo(1);;
		 	
		 	RetMsg estatus = nodoHelper.cambiaSituacionNodo(session, actividad , "OBTENER");
		    		

		    log.info("MENSAJE: {}", estatus.getMessage());
		    log.info("ESTATUS: {}", estatus.getStatus());
		
		}
		
		
	//SP_VALIDA_DOCUMENTO_REQUERIDOS
	@Test
	public void spValidaDocumentoRequeridos() {
		log.info("INICIA TEST SP_VALIDA_DOCUMENTO_REQUERIDOS");
        DatosAutenticacionTO session = getDatosAutenticacionTO();
        session.setCveUsuario("Usuario.Contratista");
        NodoTO nodo = NodoTO.builder()
                .cveProceso("ALTA_CONTRATISTA_CONTRATANTE")
                .version(new BigDecimal(1.0))
                .cveInstancia("202503-000005")
                .cveNodo("ACTIVIDAD-USUARIO")
                .idNodo(3)
                .ocurrencia(1)
                .secuenciaNodo(1)
                .build();
        
        List<StSeccionNodoTO> seccionNodo = new ArrayList<StSeccionNodoTO>();
        seccionNodo.add(new StSeccionNodoTO(1, "DAT_CONTRATISTA", "Datos del Contratista", "SECUENCIAL", null, null, null, null, "VARIABLES", BigDecimal.ZERO));
        seccionNodo.add(new StSeccionNodoTO(2, "DOM_CONTRATISTA", "Domicilio Fiscal", "SECUENCIAL", null, null, null, null, "VARIABLES",  BigDecimal.ZERO));
        seccionNodo.add(new StSeccionNodoTO(3, "DOC_CONTRATISTA", "Documentación", "GRID", null, null, null, null, "DOCUMENTOS",  BigDecimal.ZERO));
        seccionNodo.add(new StSeccionNodoTO(4, "OTROS_DOCUMENTOS", "Otros Documentos de Utilidad", "GRID", null, null, null, null, "DOCUMENTOS",  BigDecimal.ZERO));
        seccionNodo.add(new StSeccionNodoTO(5, "CON_CONTRATISTA", "Contacto del Contratista", "GRID", "SI", "Nuevo Contacto", "SI", "Eliminar Contacto", "VARIABLES",  BigDecimal.ZERO));

        EstatusTO estatus = nodoHelper.validaDocumentoRequetidos(session, nodo, seccionNodo);
        
        log.info("Mensaje: {}", estatus.getMensaje());
        log.info("TipoExcepcion: {}", estatus.getTipoExcepcion());
        

	}
	

}