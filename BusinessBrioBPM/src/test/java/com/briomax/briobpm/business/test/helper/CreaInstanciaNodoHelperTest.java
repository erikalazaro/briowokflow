package com.briomax.briobpm.business.test.helper;

import java.math.BigDecimal;
import java.text.ParseException;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.briomax.briobpm.business.helpers.NodoHelper;
import com.briomax.briobpm.business.test.base.AbstractCoreTest;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.transferobjects.in.EstatusCompuertaTO;
import com.briomax.briobpm.transferobjects.in.EstatusConfiguracionEnvioTO;
import com.briomax.briobpm.transferobjects.in.EstatusTO;
import com.briomax.briobpm.transferobjects.in.EstatusVariablesTO;
import com.briomax.briobpm.transferobjects.in.NodoTO;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Class NodoHelperTest.java es ...
 * 
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Nov 21, 2023 11:50:00 AM Modificaciones:
 * @since JDK 1.8
 */
@Slf4j
public class CreaInstanciaNodoHelperTest extends AbstractCoreTest {

	@Autowired
	private NodoHelper nodoHelper;
	
		
	// SP_CREA_NODO
	@Test
	public void spCreaNodoCondicionTest() throws BrioBPMException, ParseException {
		log.info("INICIA TEST SP_CREA_NODO");
		
		NodoTO nodo = NodoTO.builder()
				.idProceso("CREA_NODO")
				.cveProceso("DUMMY")
				.version(new BigDecimal(1.0))
				.cveInstancia("202402-000003")
				.cveNodo("ACTIVIDAD-USUARIO")
				.idNodo(1)
				.secuenciaNodo(1)
				.cveRolDestinatario("ROL_ADMIN_OPERA")
				.cveVariable("COMENTARIO")
				//.folioMensajeEnvio(folioMensajeEnvio)
				.rol("ROL DUMMY 1")
				.tipoGeneracion("SIG")
				.tipoNodoSiguiente("NORMAL")
				//.idNodoInicio(idNodoInicio)
				.build();
		EstatusVariablesTO estatus = nodoHelper.creaNodo(
				getDatosAutenticacionTO(), nodo, "", 1, "", null, null);
	
		log.info("Mensaje: {}", estatus.getMensaje());
		log.info("TipoExcepcion: {}", estatus.getTipoExcepcion());
		if( estatus.getTipoExcepcion().equals("ERROR")) {
			log.error("FALLO EL STORED SP_CREA_NODO");
		}
	}

	public void spCreaNodoTipoGeneracionTest() throws BrioBPMException, ParseException {
		log.info("INICIA TEST SP_CREA_NODO");
		
		NodoTO nodo = NodoTO.builder()
				.idProceso("CREA_NODO")
				.cveProceso("DUMMY")
				.version(new BigDecimal(1.00))
				.cveInstancia("202205-000001")
				.cveNodo("ACTIVIDAD-USUARIO")
				.idNodo(1)
				.secuenciaNodo(1)
				.cveVariable("COMENTARIO")
				//.folioMensajeEnvio(folioMensajeEnvio)
				.rol("ROL DUMMY 1")
				.tipoGeneracion("ACT")
				//.tipoNodoSiguiente(tipoNodoSiguiente)
				//.idNodoInicio(idNodoInicio)
				.build();
		EstatusVariablesTO estatus = nodoHelper.creaNodo(
				getDatosAutenticacionTO(), nodo, "ACTIVIDAD-USUARIO", 1,
				"", "SI", 2);
	
		log.info("Mensaje: {}", estatus.getMensaje());
		log.info("TipoExcepcion: {}", estatus.getTipoExcepcion());
		if( estatus.getTipoExcepcion().equals("ERROR")) {
			log.error("FALLO EL STORED SP_CREA_NODO");
		}
	}

	
}