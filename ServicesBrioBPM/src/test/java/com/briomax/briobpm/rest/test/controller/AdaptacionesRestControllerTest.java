/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.rest.test.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Collections;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.ResultActions;

import com.briomax.briobpm.business.helpers.base.ICargaPdfHelper;
import com.briomax.briobpm.business.service.catalogs.core.IMessagesService;
import com.briomax.briobpm.rest.controller.AdaptacionesRestController;
import com.briomax.briobpm.rest.security.BpmUser;
import com.briomax.briobpm.rest.test.base.AbstractRestTest;
import com.briomax.briobpm.transferobjects.DatosGeneralesUsuarioTO;
import com.briomax.briobpm.transferobjects.DatosUsuarioTO;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;
import com.briomax.briobpm.transferobjects.repse.DocumentoTrabajadorTO;
import com.briomax.briobpm.transferobjects.repse.ListaDocumentosTrabajadorTO;

import lombok.extern.slf4j.Slf4j;

/**
 * Test para la clase AdaptacionesRestController
 * Prueba específicamente el método eliminaPdf
 * 
 * @author Test Generator
 * @version 1.0
 * @since JDK 1.8
 */
@Slf4j
public class AdaptacionesRestControllerTest extends AbstractRestTest {

    @MockBean
    private ICargaPdfHelper cargaPdfHelper;

    @MockBean
    private IMessagesService messagesService;

    @SpyBean
    private AdaptacionesRestController adaptacionesRestController;

    private DatosAutenticacionTO testUserSession;

    @Before
    public void setupTestUserSession() {
        // Configurar datos de autenticación con usuario U.MYPO y entidad BRIOMAX
        testUserSession = DatosAutenticacionTO.builder()
                .cveUsuario("U.MYPO")
                .cveEntidad("BRIOMAX")
                .cveLocalidad("BRIOMAX-CENTRAL")
                .cveIdioma("ES-MX")
                .cveMoneda("MXN")
                .build();

        // Crear datos del usuario para el contexto de seguridad
        DatosGeneralesUsuarioTO datosGenerales = new DatosGeneralesUsuarioTO();
        datosGenerales.setClave("U.MYPO");
        datosGenerales.setNombre("Usuario MYPO");

        DatosUsuarioTO datosUsuario = new DatosUsuarioTO();
        datosUsuario.setGenerales(datosGenerales);
        datosUsuario.setDatosAutenticacion(testUserSession);

        // Crear el usuario BPM correcto para el contexto de seguridad
        BpmUser bpmUser = BpmUser.builder()
                .username("U.MYPO")
                .password("password")
                .authorities(Collections.emptyList())
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .enabled(true)
                .userData(datosUsuario)
                .build();
        
        // Crear authentication personalizado que contenga el BpmUser en details
        Authentication auth = new UsernamePasswordAuthenticationToken("U.MYPO", "password", Collections.emptyList()) {
            @Override
            public Object getDetails() {
                return bpmUser;
            }
        };
        
        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        securityContext.setAuthentication(auth);
        SecurityContextHolder.setContext(securityContext);

        log.info("Configured test user session: {}", testUserSession);
        log.info("Configured security context with BpmUser: {}", bpmUser.getUsername());
    }

    /**
     * Test para eliminaPdf con lista vacía
     * Verifica que retorne HttpStatus.NO_CONTENT cuando la lista está vacía
     */
    @Test
    public void testEliminaPdf_ListaVacia() throws Exception {
        String url = AdaptacionesRestController.ROOT_URL + "eliminaPdf";
        log.info("Testing eliminaPdf with empty list: {}", ROOT_CONTEXT + url);

        // Preparar datos de prueba con lista vacía
        ListaDocumentosTrabajadorTO data = new ListaDocumentosTrabajadorTO();
        data.setLista(Collections.emptyList());

        // Mock del mensaje de respuesta
        when(messagesService.getMessage(any(DatosAutenticacionTO.class), 
                eq("ADAPTACION_TRABAJADORES"), 
                eq("Lista vacia"), 
                eq("")))
                .thenReturn("Lista vacía");

        String requestJson = mapper.writeValueAsString(data);
        log.info("Request JSON: {}", requestJson);

        // Ejecutar la petición
        ResultActions resultActions = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson));

        // Verificar el resultado
        MockHttpServletResponse response = resultActions.andReturn().getResponse();
        resultActions.andExpect(status().isNoContent());
        
        log.info("Response status: {}, content: {}", response.getStatus(), response.getContentAsString());
    }

    /**
     * Test para eliminaPdf con documentos válidos - eliminación exitosa
     * Simula el escenario donde la eliminación es exitosa
     */
    @Test
    public void testEliminaPdf_EliminacionExitosa() throws Exception {
        String url = AdaptacionesRestController.ROOT_URL + "eliminaPdf";
        log.info("Testing eliminaPdf with successful deletion: {}", ROOT_CONTEXT + url);

        // Preparar datos de prueba basados en la información proporcionada
        DocumentoTrabajadorTO documento = createTestDocumento();
        ListaDocumentosTrabajadorTO data = new ListaDocumentosTrabajadorTO();
        data.setLista(Arrays.asList(documento));

        // Mock del helper para retornar éxito
        when(cargaPdfHelper.eliminaCarga(any(DatosAutenticacionTO.class), eq(data)))
                .thenReturn(true);

        // Mock del mensaje de éxito
        when(messagesService.getMessage(any(DatosAutenticacionTO.class), 
                eq("ADAPTACION_TRABAJADORES"), 
                eq("Carga eliminada correctamente"), 
                eq("")))
                .thenReturn("Carga eliminada correctamente");

        String requestJson = mapper.writeValueAsString(data);
        log.info("Request JSON: {}", requestJson);

        // Ejecutar la petición
        ResultActions resultActions = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson));

        // Verificar el resultado
        MockHttpServletResponse response = resultActions.andReturn().getResponse();
        resultActions.andExpect(status().isOk());
        
        log.info("Response status: {}, content: {}", response.getStatus(), response.getContentAsString());
    }

    /**
     * Test para eliminaPdf con documentos válidos - eliminación fallida
     * Simula el escenario donde la eliminación falla
     */
    @Test
    public void testEliminaPdf_EliminacionFallida() throws Exception {
        String url = AdaptacionesRestController.ROOT_URL + "eliminaPdf";
        log.info("Testing eliminaPdf with failed deletion: {}", ROOT_CONTEXT + url);

        // Preparar datos de prueba
        DocumentoTrabajadorTO documento = createTestDocumento();
        ListaDocumentosTrabajadorTO data = new ListaDocumentosTrabajadorTO();
        data.setLista(Arrays.asList(documento));

        // Mock del helper para retornar fallo
        when(cargaPdfHelper.eliminaCarga(any(DatosAutenticacionTO.class), eq(data)))
                .thenReturn(false);

        // Mock del mensaje de error
        when(messagesService.getMessage(any(DatosAutenticacionTO.class), 
                eq("ADAPTACION_TRABAJADORES"), 
                eq("No se pudo eliminar la carga"), 
                eq("")))
                .thenReturn("No se pudo eliminar la carga");

        String requestJson = mapper.writeValueAsString(data);
        log.info("Request JSON: {}", requestJson);

        // Ejecutar la petición
        ResultActions resultActions = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson));

        // Verificar el resultado
        MockHttpServletResponse response = resultActions.andReturn().getResponse();
        resultActions.andExpect(status().isNotModified());
        
        log.info("Response status: {}, content: {}", response.getStatus(), response.getContentAsString());
    }

    /**
     * Test para eliminaPdf con excepción
     * Simula el escenario donde ocurre una excepción durante el procesamiento
     */
    @Test
    public void testEliminaPdf_ConExcepcion() throws Exception {
        String url = AdaptacionesRestController.ROOT_URL + "eliminaPdf";
        log.info("Testing eliminaPdf with exception: {}", ROOT_CONTEXT + url);

        // Preparar datos de prueba
        DocumentoTrabajadorTO documento = createTestDocumento();
        ListaDocumentosTrabajadorTO data = new ListaDocumentosTrabajadorTO();
        data.setLista(Arrays.asList(documento));

        // Mock del helper para lanzar excepción
        when(cargaPdfHelper.eliminaCarga(any(DatosAutenticacionTO.class), eq(data)))
                .thenThrow(new RuntimeException("Error simulado"));

        // Mock del mensaje de error
        when(messagesService.getMessage(any(DatosAutenticacionTO.class), 
                eq("ADAPTACION_TRABAJADORES"), 
                eq("No se pudo eliminar la carga"), 
                eq("")))
                .thenReturn("No se pudo eliminar la carga");

        String requestJson = mapper.writeValueAsString(data);
        log.info("Request JSON: {}", requestJson);

        // Ejecutar la petición
        ResultActions resultActions = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson));

        // Verificar el resultado
        MockHttpServletResponse response = resultActions.andReturn().getResponse();
        resultActions.andExpect(status().isNotModified());
        
        log.info("Response status: {}, content: {}", response.getStatus(), response.getContentAsString());
    }

    /**
     * Test para eliminaPdf con múltiples documentos
     * Verifica el comportamiento con múltiples documentos en la lista
     */
    @Test
    public void testEliminaPdf_MultiplesDocumentos() throws Exception {
        String url = AdaptacionesRestController.ROOT_URL + "eliminaPdf";
        log.info("Testing eliminaPdf with multiple documents: {}", ROOT_CONTEXT + url);

        // Preparar datos de prueba con múltiples documentos
        DocumentoTrabajadorTO documento1 = createTestDocumento();
        DocumentoTrabajadorTO documento2 = createTestDocumento();
        documento2.setNomArchivo("2 Segundo documento.pdf");
        documento2.setSecuencia(2);

        ListaDocumentosTrabajadorTO data = new ListaDocumentosTrabajadorTO();
        data.setLista(Arrays.asList(documento1, documento2));

        // Mock del helper para retornar éxito
        when(cargaPdfHelper.eliminaCarga(any(DatosAutenticacionTO.class), eq(data)))
                .thenReturn(true);

        // Mock del mensaje de éxito
        when(messagesService.getMessage(any(DatosAutenticacionTO.class), 
                eq("ADAPTACION_TRABAJADORES"), 
                eq("Carga eliminada correctamente"), 
                eq("")))
                .thenReturn("Carga eliminada correctamente");

        String requestJson = mapper.writeValueAsString(data);
        log.info("Request JSON: {}", requestJson);

        // Ejecutar la petición
        ResultActions resultActions = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson));

        // Verificar el resultado
        MockHttpServletResponse response = resultActions.andReturn().getResponse();
        resultActions.andExpect(status().isOk());
        
        log.info("Response status: {}, content: {}", response.getStatus(), response.getContentAsString());
    }

    /**
     * Crea un objeto DocumentoTrabajadorTO de prueba basado en los datos proporcionados
     * Usa la información de CR_REGISTRO_OBRA como base
     */
    private DocumentoTrabajadorTO createTestDocumento() {
        DocumentoTrabajadorTO documento = new DocumentoTrabajadorTO();
        
        // Datos basados en el registro de ejemplo proporcionado
        documento.setRfc("MIN220616APA");
        documento.setContrato("AHA-EDIF-CAMILA-009-MYPO-2024");
        documento.setCveProcesoPeriodico("REGISTRO_SIROC");
        documento.setFechaCarga("05/01/2025");
        documento.setNomArchivo("1 Acuse de recibo registro de obra de construcción MYPO CASA 9 CAMILA HILLS.pdf");
        documento.setSecuencia(1);
        documento.setTipoPeriodo("Mensual");
        documento.setContentType("application/pdf");
        
        // Simular datos binarios del PDF
        byte[] pdfData = "Datos simulados del PDF".getBytes();
        documento.setData(pdfData);
        
        return documento;
    }
}
