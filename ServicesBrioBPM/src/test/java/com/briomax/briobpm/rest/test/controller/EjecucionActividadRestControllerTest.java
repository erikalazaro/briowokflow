/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.rest.test.controller;

import org.junit.Ignore;
import org.junit.Test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.briomax.briobpm.rest.controller.EjecucionActividadRestController;
import com.briomax.briobpm.rest.test.base.AbstractRestTest;
import com.briomax.briobpm.transferobjects.in.ActividadTO;
import com.briomax.briobpm.transferobjects.in.StVariableDependienteInTO;

import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Class EjecucionActividadRestControllerTest.java es ...
 * @author Rigoberto Olvera C.
 * @version 1.0 Fecha de creacion 31/01/2020 01:35:57 PM Modificaciones:
 * @since JDK 1.8
 */
@Slf4j
public class EjecucionActividadRestControllerTest extends AbstractRestTest {

	/**
	 * Formulario dinamico test.
	 * @throws Exception la exception.
	 */
	@Test
	@Ignore
	public void formularioDinamicoTest() throws Exception {
		String url = EjecucionActividadRestController.ROOT_URL + "formulariodinamico";
		log.info(REQUEST_LOG, ROOT_CONTEXT + url);
		ActividadTO datosProceso = builderActividad();
		String datosProcesoJson = mapper.writeValueAsString(datosProceso);
		log.info(PARAMETERS_LOG, datosProcesoJson);
		ResultActions resultActions =
			mockMvc.perform(MockMvcRequestBuilders.post(url).contentType(MEDIA_TYPE_JSON).content(datosProcesoJson));
		MockHttpServletResponse response = resultActions.andReturn().getResponse();
		resultActions.andExpect(status().isOk());
		log.info(RESPONSE_LOG, response.getStatus(), response.getContentAsString());
	}
	
	@Test
	public void variableDependienteTest() throws Exception {
		String url = EjecucionActividadRestController.ROOT_URL + "variabledependiente";
		log.info(REQUEST_LOG, ROOT_CONTEXT + url);
		StVariableDependienteInTO datosProceso = builderNodoSiguienteTO();
		String datosProcesoJson = mapper.writeValueAsString(datosProceso);
		log.info(PARAMETERS_LOG, datosProcesoJson);
		ResultActions resultActions =
			mockMvc.perform(MockMvcRequestBuilders.post(url).contentType(MEDIA_TYPE_JSON).content(datosProcesoJson));
		MockHttpServletResponse response = resultActions.andReturn().getResponse();
		resultActions.andExpect(status().isOk());
		log.info(RESPONSE_LOG, response.getStatus(), response.getContentAsString());
	}


}
