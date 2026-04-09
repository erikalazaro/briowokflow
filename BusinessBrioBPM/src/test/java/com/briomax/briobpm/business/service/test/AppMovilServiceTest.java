package com.briomax.briobpm.business.service.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.briomax.briobpm.business.service.AppMovilService;
import com.briomax.briobpm.business.test.base.AbstractCoreTest;
import com.briomax.briobpm.transferobjects.app.ActividadGuardar;
import com.briomax.briobpm.transferobjects.app.RespuestaFormularioTO;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;
import com.briomax.briobpm.transferobjects.in.EstatusTO;
import com.briomax.briobpm.transferobjects.in.NodoTO;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Class AppMovilServiceTest.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Oct 22, 2024 12:14:51 PM Modificaciones:
 * @since JDK 1.8
 */
@Slf4j
public class AppMovilServiceTest extends AbstractCoreTest{

	@Autowired
	AppMovilService appService;
	
	@Test
	@Ignore
    public void generarBotonesTest() {

        // Configurar el objeto NodoTO
        NodoTO nodo = NodoTO.builder()
                .cveProceso("ATEN_INCI_TICK")
                .version(BigDecimal.ONE)
                .idNodo(2)
               .cveNodo("ACTIVIDAD-USUARIO")
                .build();

        // Log para indicar el inicio del test
        log.info("Iniciando test: generarBotonesTest");
        log.info("Parámetros: cveEntidad = 'BRIOMAX', cveUsuario = 'AIT.Usuario.Gerente', nodo = {}", nodo);

        // Llamar al método a probar
        String[] botones = appService.generaBotones("BRIOMAX", "AIT.Usuario.Gerente", nodo);

        // Log de resultado
        log.info("Resultado: botones generados = {}", (Object) botones); // Cast necesario para loggear array

        // Verificar que los botones no son nulos y tienen el tamaño esperado
        assertNotNull(botones, "El array de botones no debe ser nulo");
        assertTrue(botones.length > 0, "El array de botones debe tener al menos un elemento");
        
        // Log para indicar el fin del test
        log.info("Finalizó test: generarBotonesTest");
	}
	
	@Test
    public void registraRespuestaUsuarioTest() throws ParseException {

        // Configurar el objeto NodoTO
        NodoTO nodo = NodoTO.builder()
                .cveProceso("ATEN_INCI_TICK")
                .version(BigDecimal.ONE)
                .idNodo(2)
                .cveNodo("ACTIVIDAD-USUARIO")
                .usoSeccion("APP")
                .build();

        // Log para indicar el inicio del test
        log.info("Iniciando test: registraRespuestaUsuarioTest");
        log.info("Parámetros: cveEntidad = 'BRIOMAX', cveUsuario = 'AIT.Usuario.Cliente', nodo = {}", nodo);

        DatosAutenticacionTO user = getDatosAutenticacionTO();
        user.setCveUsuario("AIT.Usuario.Cliente");
        List<RespuestaFormularioTO> respuestasFormulario = new ArrayList<RespuestaFormularioTO>();
        
        RespuestaFormularioTO formulario = RespuestaFormularioTO.builder()
        		.numeroDato(3)
        		.build();
        
        respuestasFormulario.add(formulario);
        // Llamar al método a probar
        EstatusTO actividad = appService.registraRespuestaUsuarioN(user, nodo, respuestasFormulario);

        // Log de resultado
        log.info("Resultado: actividad = {}", actividad);
        
        // Log para indicar el fin del test
        log.info("Finalizó test");
	}
}
