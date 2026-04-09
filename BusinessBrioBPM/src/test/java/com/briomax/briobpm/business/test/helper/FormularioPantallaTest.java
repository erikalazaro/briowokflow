package com.briomax.briobpm.business.test.helper;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.briomax.briobpm.business.helpers.FormularioPantalla;
import com.briomax.briobpm.business.helpers.NodoHelper;
import com.briomax.briobpm.business.test.base.AbstractCoreTest;
import com.briomax.briobpm.common.core.Constants;
import com.briomax.briobpm.common.core.DAORet;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.persistence.dao.EjecucionActividadRepository;
import com.briomax.briobpm.persistence.dao.base.IEjecucionActividadRepository;
import com.briomax.briobpm.persistence.entity.namedquery.LeeEtiquetasTabla;
import com.briomax.briobpm.persistence.entity.namedquery.LeeInfSeccionOcu;
import com.briomax.briobpm.persistence.entity.namedquery.LeeSeccionesNodo;
import com.briomax.briobpm.transferobjects.in.CatalogoEtiquetaTO;
import com.briomax.briobpm.transferobjects.in.ColumasGridEjecucionTO;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;
import com.briomax.briobpm.transferobjects.in.DatosSeccionOCUTO;
import com.briomax.briobpm.transferobjects.in.DocumentoNodoTO;
import com.briomax.briobpm.transferobjects.in.EstatusEvaluaFormulaTO;
import com.briomax.briobpm.transferobjects.in.EstatusInfSeccionVPTO;
import com.briomax.briobpm.transferobjects.in.ListaTO;
import com.briomax.briobpm.transferobjects.in.NodoTO;
import com.briomax.briobpm.transferobjects.in.RsOcurrenciaTO;
import com.briomax.briobpm.transferobjects.in.StSeccionNodoTO;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Class FormularioPantallaTest.java es ...
 * 
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Abr 16, 2024 11:50:00 AM Modificaciones:
 * @since JDK 1.8
 */
@Slf4j
public class FormularioPantallaTest extends AbstractCoreTest {

	@Autowired
	private FormularioPantalla formularioHelper;
	
	@Autowired
	private  EjecucionActividadRepository dao;
	
	/** El atributo o variable Nodo Helper. */
	@Autowired
	private NodoHelper nodoHelper;

	// SP_EVALUA_FORMULA
	@Test
	//@Ignore
	public void spEvaluaFormula() throws BrioBPMException {
		log.info("INICIA TEST SP_EVALUA_FORMULA");
		// Ejecución del método a probar
		NodoTO nodo = NodoTO.builder()
				.idProceso("CREA_NODO")
				.cveProceso("DUMMY")
				.version(new BigDecimal(1.0))
				.cveInstancia("202205-000001")
				.cveNodo("EVENTO-INICIO")
				.idNodo(1)
				.secuenciaNodo(1)
				.ocurrencia(1)
				.build();
		EstatusEvaluaFormulaTO estatus = 
				formularioHelper.evaluaFormula(getDatosAutenticacionTO(),
						nodo,
						Constants.DECIMAL,
						"@VERSION@");
		
		log.info("Mensaje: {}", estatus.getMensaje());
		log.info("------> TipoExcepcion: {}", estatus.getTipoExcepcion());
		if( estatus.getTipoExcepcion().equals(Constants.ERROR)) {
			log.error("FALLO EL STORED SP_EVALUA_FORMULA");
		}
	}
	
	
	// SP_LEE_SECCIONES_NODO
	@Test
	//@Ignore
	public void spLeeSeccionesNodo() throws BrioBPMException {
		log.info("-----> INICIA TEST SP_LEE_SECCIONES_NODO");
		// Ejecución del método a probar
		NodoTO nodo = NodoTO.builder()
				.idProceso("CREA_NODO")
				.cveProceso("ALTA_CONTRATISTA_CONTRATANTE")
				.version(new BigDecimal(1.0))
				//.cveInstancia("202205-000001")
				.cveNodo("ACTIVIDAD-USUARIO")
				.idNodo(3)
				.secuenciaNodo(1)
				.ocurrencia(1)
				.build();
		
		List<StSeccionNodoTO> seccionNodo = new ArrayList<StSeccionNodoTO>();
		DAORet<List<StSeccionNodoTO>, RetMsg> estatus = 
				nodoHelper.leeSeccionesNodo(getDatosAutenticacionTO(),
						nodo,
						Constants.NO, seccionNodo);
		
	    // Log del contenido devuelto
	    log.info("-----> Contenido SP_LEE_SECCIONES_NODO DESACOPLADO: {}", estatus.getContent());
	    
	    // Verificar el tipo de excepción
	    log.info("-----> TipoExcepcion: {}", estatus.getMeta().getStatus());
	    log.info("-----> Mensaje: {}", estatus.getMeta().getMessage());
	    if (Constants.ERROR.equals(estatus.getMeta().getStatus())) {
	    	log.info("---> ERROR EN SP_LEE_SECCIONES_NODO");
	    }
	}
	
	@Test
	@Ignore
	public void testLeeSeccionesNodo() throws BrioBPMException {
	    log.info("-----> INICIA TEST LEE_SECCIONES_NODO");
	    
	    // Datos de entrada
	    String cveUsuario = "usuario_prueba";
	    String cveEntidad = "BRIOMAX";
	    String cveLocalidad = "MX";
	    String cveIdioma = "ES";
	    String cveProceso = "G2 ACF";
	    BigDecimal version = new BigDecimal(1.0);
	    String cveNodo = "ACTIVIDAD-USUARIO";
	    Integer idNodo = 1;
	    String banGenTabTmp = Constants.NO;
	    
	    // Ejecución del método a probar
	    DAORet<List<LeeSeccionesNodo>, RetMsg> estatus = dao.leeSeccionesNodo(
	        cveUsuario,
	        cveEntidad,
	        cveLocalidad,
	        cveIdioma,
	        cveProceso,
	        version,
	        cveNodo,
	        idNodo,
	        banGenTabTmp
	    );

	    // Log del contenido devuelto
	    log.info("-----> Contenido SP_LEE_SECCIONES_NODO ORIGINAL: {}", estatus.getContent());
	    
	    // Verificar el tipo de excepción
	    log.info("-----> TipoExcepcion: {}", estatus.getMeta().getStatus());
	    log.info("-----> Mensaje: {}", estatus.getMeta().getMessage());
	    if (Constants.ERROR.equals(estatus.getMeta().getStatus())) {
	        log.info("---> ERROR EN LEE_SECCIONES_NODO");
	    }
	}

	
	
	// SP_LEE_COLUMNAS_SECCION_OCU
	@Test
	//@Ignore
	public void spLeeColumnasSeccionOCU() throws BrioBPMException {
		log.info("-----> INICIA TEST SP_LEE_COLUMNAS_SECCION_OCU");
		// Ejecución del método a probar
		NodoTO nodo = NodoTO.builder()
				.idProceso("CREA_NODO")
				.cveProceso("G2 ACF")
				.version(new BigDecimal(1.0))
				.cveInstancia("202205-000001")
				.cveNodo("ACTIVIDAD-USUARIO")
				.idNodo(1)
				.secuenciaNodo(1)
				.ocurrencia(1)
				.build();
		DAORet<List<ColumasGridEjecucionTO>, RetMsg> estatus = 
				formularioHelper.leeColumnasSeccionOCU(getDatosAutenticacionTO(),
						nodo,
						"VALIDA_EXITENCIA_PROSPECTO");
		
	    // Log del contenido devuelto
	    log.info("-----> Contenido: {}", estatus.getContent());
	    
	    // Verificar el tipo de excepción
	    log.info("-----> TipoExcepcion: {}", estatus.getMeta().getStatus());
	    log.info("-----> Mensaje: {}", estatus.getMeta().getMessage());
	    if (Constants.ERROR.equals(estatus.getMeta().getStatus())) {
	    	log.info("---> ERROR EN SP_LEE_COLUMNAS_SECCION_OCU");
	    }
	}
	
	
	// SP_LEE_ETIQUETAS_CATALOGO
	@Test
	//@Ignore
	public void spLeeEtiquetasCatalogo() throws BrioBPMException {
	log.info("-----> INICIA TEST SP_LEE_COLUMNAS_SECCION_OCU");
		// Ejecución del método a probar
		NodoTO nodo = NodoTO.builder()
				.idProceso("CREA_NODO")
				.cveProceso("ALTA_CONTRATISTA_CONTRATANTE")
				.version(new BigDecimal(1.0))
				.cveInstancia("202405-000003")
				.cveNodo("ACTIVIDAD-USUARIO")
				.idNodo(3)
				.secuenciaNodo(1)
				.ocurrencia(1)
				.build();
		DAORet<List<CatalogoEtiquetaTO>, RetMsg> estatus = 
				formularioHelper.leeEtiquetasCatalogo(getDatosAutenticacionTO(),
						nodo,
						"VALIDA_EXITENCIA_PROSPECTO");
		
	    // Log del contenido devuelto
	    log.info("-----> Contenido: {}", estatus.getContent());
	    
	    // Verificar el tipo de excepción
	    log.info("-----> TipoExcepcion: {}", estatus.getMeta().getStatus());
	    log.info("-----> Mensaje: {}", estatus.getMeta().getMessage());
	    if (Constants.ERROR.equals(estatus.getMeta().getStatus())) {
	    	log.info("---> ERROR EN SP_LEE_COLUMNAS_SECCION_OCU");
	    }
	}
	
	// SP_LEE_DOCUMENTOS_NODO
	@Test
	@Ignore
	public void spLeeDocumentosNodo() throws BrioBPMException {
	log.info("-----> INICIA TEST SP_LEE_DOCUMENTOS_NODO");
		// Ejecución del método a probar
		NodoTO nodo = NodoTO.builder()
				.idProceso("CREA_NODO")
				.cveProceso("G2 ACF")
				.version(new BigDecimal(1.0))
				.cveInstancia("202311-000001")
				.cveNodo("ACTIVIDAD-USUARIO-TEMPORIZACION")
				.idNodo(7)
				.secuenciaNodo(11)
				.ocurrencia(1)
				.build();
		List<DocumentoNodoTO> documentoNodo = new ArrayList<DocumentoNodoTO>();
		DAORet<List<DocumentoNodoTO>, RetMsg> estatus = 
				nodoHelper.leeDocumentosNodo(getDatosAutenticacionTO(),
						nodo,
						"DOCUMENTO_DICTAMEN_FISCAL", "SI", documentoNodo);
		
	    // Log del contenido devuelto
	    log.info("-----> Contenido: {}", estatus.getContent());
	    
	    // Verificar el tipo de excepción
	    log.info("-----> TipoExcepcion: {}", estatus.getMeta().getStatus());
	    log.info("-----> Mensaje: {}", estatus.getMeta().getMessage());
	    if (Constants.ERROR.equals(estatus.getMeta().getStatus())) {
	    	log.info("---> ERROR EN SP_LEE_DOCUMENTOS_NODO");
	    }
	}
	
	// SP_LEE_ETIQUETAS_TABLA
	@Test
	//@Ignore
	public void spLeeEtiquetasTablaTest() throws BrioBPMException {
		log.info("INICIA TEST SP_LEE_ETIQUETAS_TABLA");
		// Ejecución del método a probar
		NodoTO nodo = NodoTO.builder()
				.idProceso("CREA_NODO")
				.cveProceso("INCIDENCIA-SMI")
				.version(new BigDecimal(1.0))
				.cveInstancia("202111-000001")
				.cveNodo("ACTIVIDAD-USUARIO")
				.idNodo(1)
				.secuenciaNodo(1)
				.build();
		ListaTO listaTO = ListaTO.builder()
				.valorLista("ID_MODULO")
				.descripcionLista("DESCRIPCION_MODULO")
				.tablaLista("AP_MODULO")
				.whereLista(null)
				.build();
		DAORet<List<LeeEtiquetasTabla>, RetMsg> estatus = null;
		try {
			estatus = formularioHelper.leeEtiquetasTabla(
					getDatosAutenticacionTO(),
					nodo,
					"VPRO_MODULO",
					listaTO);
		} catch (BrioBPMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		   // Log del contenido devuelto
	    log.info("-----> Contenido: {}", estatus.getContent());
	    
	    // Verificar el tipo de excepción
	    log.info("-----> TipoExcepcion: {}", estatus.getMeta().getStatus());
	    log.info("-----> Mensaje: {}", estatus.getMeta().getMessage());
	    if (Constants.ERROR.equals(estatus.getMeta().getStatus())) {
	    	log.info("---> ERROR EN SP_LEE_ETIQUETAS_TABLA");
	    }
	}
	
	
	// SP_LEE_INF_SECCION_OCU
	@Test
	public void spLeeInfSeccionOCU() throws BrioBPMException {
		log.info("INICIA TEST SP_LEE_INF_SECCION_OCU");
		// Ejecución del método a probar
		NodoTO nodo = NodoTO.builder()
				.cveProceso("ALTA_CONTRATISTA_CONTRATANTE")
				.version(new BigDecimal(1.0))
				.cveInstancia("202408-000003")
				.cveNodo("ACTIVIDAD-USUARIO")
				.idNodo(3)
				.rol("CONTRATISTA_OPERADOR")
				.build();
		DatosAutenticacionTO session = getDatosAutenticacionTO();
	    session.setCveUsuario("Usuario.Contratista");
		DAORet<List<RsOcurrenciaTO>, RetMsg> 
	    estatus = formularioHelper.leeInfSeccionOcu(
	    				session,
						nodo,
						"OTROS_DOCUMENTOS");
		
	    // Log del contenido devuelto
	    log.info("-----> Datos 1: {}", estatus.getContent());
	    
	    // Verificar el tipo de excepción
	    log.info("-----> TipoExcepcion: {}", estatus.getMeta().getStatus());
	    log.info("-----> Mensaje: {}", estatus.getMeta().getMessage());
	    if (Constants.ERROR.equals(estatus.getMeta().getStatus())) {
	    	log.info("---> ERROR EN SP_LEE_INF_SECCION_OCU");
	    }
	    
	}
	
	@Test
	//@Ignore
	public void spLeeInfSeccionOCUContratistaTest() throws BrioBPMException {
	    log.info("INICIA TEST SP_LEE_INF_SECCION_OCU_CONTRATISTA");
	    
	    // Ejecución del método a probar
	    NodoTO nodo = NodoTO.builder()
	            .idProceso(null)
	            .cveProceso("REGISTRO_DE_CONTRATO")
	            .version(new BigDecimal(1.00))
	            .cveInstancia("202503-000006")
	            .cveNodo("ACTIVIDAD-USUARIO")
	            .idNodo(1)
	            .secuenciaNodo(1)
	            .rol("CONTRATANTE_OPERADOR")
	            .build();
	    
	    DatosAutenticacionTO session = DatosAutenticacionTO.builder()
	            .cveUsuario("Usuario.Contratante")
	            .cveEntidad("BRIOMAX")
	            .cveLocalidad("BRIOMAX-CENTRAL")
	            .cveIdioma("es-MX")
	            .cveMoneda("USD")
	            .build();
	    
	    DAORet<List<RsOcurrenciaTO>, RetMsg> estatus = formularioHelper.leeInfSeccionOcu(
	            session,
	            nodo,
	            "OBJ_CONTRATO");
	    
	    // Log del contenido devuelto
	    log.info("-----> Datos 1: {}", estatus.getContent());
	    
	    // Verificar el tipo de excepción
	    log.info("-----> TipoExcepcion: {}", estatus.getMeta().getStatus());
	    log.info("-----> Mensaje: {}", estatus.getMeta().getMessage());
	    if (Constants.ERROR.equals(estatus.getMeta().getStatus())) {
	        log.info("---> ERROR EN SP_LEE_INF_SECCION_OCU_CONTRATISTA");
	    }
	}

	
	
	/** El atributo o variable repository. */
	@Autowired
	private IEjecucionActividadRepository repository;
	
	// SP_LEE_INF_SECCION_OCU2
	@Test
	@Ignore
	public void spLeeInfSeccionOCU2() throws BrioBPMException {
		
		  // Execute the method to be tested
        DAORet<List<LeeInfSeccionOcu>, RetMsg> infSeccionEntity = repository.leeInfSeccionOcu(
                "Admin.Opera",        // cveUsuario
                "BRIOMAX",            // cveEntidad
                "BRIOMAX-CENTRAL",    // cveLocalidad
                "es-MX",              // cveIdioma
                "ROL_ADMIN_OPERA",    // cveRol
                "DUMMY",     // cveProceso
                new BigDecimal("1"),  // version
                "202402-000008",      // cveInstancia
                "ACTIVIDAD-USUARIO",  // cveNodo
                1,                    // idNodo
                "G2_E_C-A_R-A_CH-A_CB-A_L-E_CB-E_L-D_C"
        );
		
			log.debug("\t\t ===== # FIN getInfoSeccion 2 \t =====");
	    // Verificar el tipo de excepción
		log.info("-----> Datos 2: {}", infSeccionEntity.getContent());
	    log.info("-----> TipoExcepcion: {}", infSeccionEntity.getMeta().getStatus());
	    log.info("-----> Mensaje: {}", infSeccionEntity.getMeta().getMessage());
	    if (Constants.ERROR.equals(infSeccionEntity.getMeta().getStatus())) {
	    	log.info("---> ERROR EN SP_LEE_INF_SECCION_OCU2");
	    }
	    
	}
	
	@Test
	@Ignore
	public void spLeeInfSeccionOCUContratista2() throws BrioBPMException {
	    log.info("INICIA TEST SP_LEE_INF_SECCION_OCU_CONTRATISTA_2");

	    // Ejecución del método a probar
	    DAORet<List<LeeInfSeccionOcu>, RetMsg> infSeccionEntity = repository.leeInfSeccionOcu(
	    		"Usuario.Contratista",    // cveUsuario
	            "BRIOMAX",                // cveEntidad
	            "BRIOMAX-CENTRAL",        // cveLocalidad
	            "es-MX",                  // cveIdioma
	            "CONTRATISTA_OPERADOR",   // cveRol
	            "ALTA_CONTRATISTA_CONTRATANTE", // cveProceso
	            new BigDecimal("1.00"),   // version
	            "202405-000007",          // cveInstancia
	            "ACTIVIDAD-USUARIO",      // cveNodo
	            3,                        // idNodo
	            "CON_CONTRATISTA"         // cveSeccion
	    );

	    log.debug("\t\t ===== # FIN getInfoSeccion 2 \t =====");
	    // Verificar el tipo de excepción
	    log.info("-----> Datos ultimos 2: {}", infSeccionEntity.getContent());
	    log.info("-----> TipoExcepcion: {}", infSeccionEntity.getMeta().getStatus());
	    log.info("-----> Mensaje: {}", infSeccionEntity.getMeta().getMessage());
	    if (Constants.ERROR.equals(infSeccionEntity.getMeta().getStatus())) {
	        log.info("---> ERROR EN SP_LEE_INF_SECCION_OCU_CONTRATISTA_2");
	    }
	}

	
	
	// SP_LEE_INF_SECCION_VP
	@Test
	//@Ignore
    public void testLeeInfSeccionVP() throws BrioBPMException {
        log.info("INICIA TEST SP_LEE_INF_SECCION_VP");
        
        // Mocking session data
        DatosAutenticacionTO session = DatosAutenticacionTO.builder()
                .cveUsuario("Admin.Opera")
                .cveEntidad("BRIOMAX")
                .cveLocalidad("BRIOMAX-CENTRAL")
                .cveIdioma("es-MX")
                .cveMoneda("USD")
                .build();

        // Mocking node data
        NodoTO nodo = NodoTO.builder()
                .cveProceso("DUMMY")
                .version(new BigDecimal(1.00))
                .cveInstancia("202402-000008")
                .cveNodo("ACTIVIDAD-USUARIO")
                .idNodo(1)
                .rol("ROL_ADMIN_OPERA")
                .build();

        // Creating a list for DatosSeccionOCUTO
        List<DatosSeccionOCUTO> lista = new ArrayList<>();

        // Executing the method to be tested
        EstatusInfSeccionVPTO estatus = formularioHelper.leeInfSeccionVP(
                session,
                nodo,
                "G2_E_C-A_R-A_CH-A_CB-A_L-E_CB-E_L-D_C",
                lista
        );
        
        // Log the returned content
		log.info("-----> Datos 1: {} {}", lista.size(), lista.toString());

        log.info("-----> TipoExcepcion: {}", estatus.getTipoExcepcion());
        log.info("-----> Mensaje: {}", estatus.getMensaje());

        // Verify the type of exception
        assertEquals(Constants.OK, estatus.getTipoExcepcion());
        assertEquals("", estatus.getMensaje());
    }
	    
}
