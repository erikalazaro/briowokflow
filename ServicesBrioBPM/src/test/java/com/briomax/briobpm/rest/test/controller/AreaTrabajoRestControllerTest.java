package com.briomax.briobpm.rest.test.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.briomax.briobpm.rest.controller.AreaTrabajoRestController;
import com.briomax.briobpm.rest.test.base.AbstractRestTest;
import com.briomax.briobpm.transferobjects.dynamicForm.DataGrid;
import com.briomax.briobpm.transferobjects.in.ProcesoSeguroTO;
import com.briomax.briobpm.transferobjects.in.ProcesoTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AreaTrabajoRestControllerTest extends AbstractRestTest {

	@Test
	public void gridTest() throws Exception {
		String url = AreaTrabajoRestController.ROOT_URL + "grid";
		log.info(REQUEST_LOG, ROOT_CONTEXT + url);
		ProcesoTO datosProceso = builderProcesoTO();
		String datosProcesoJson = mapper.writeValueAsString(datosProceso);
		log.info(PARAMETERS_LOG, datosProcesoJson);
		ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post(url)
			.contentType(MEDIA_TYPE_JSON)
			.content(datosProcesoJson)
			);
		MockHttpServletResponse response = resultActions.andReturn().getResponse();
		log.info(RESPONSE_LOG, response.getStatus(), response.getContentAsString());
		DataGrid area = mapper.readValue(response.getContentAsString(), DataGrid.class);
		log.info("\t --- AREA DE TRABAJO --- \n", area);
		resultActions.andExpect(status().isOk());
	}
	
	   @Test
	   public void inicioProcesoSeguroTest() throws Exception {
	       String url = AreaTrabajoRestController.ROOT_URL + "inicioprocesoseguro";
	       log.info(REQUEST_LOG, ROOT_CONTEXT + url);
	       ProcesoSeguroTO datosProcesoSeguro = builderProcesoSeguroTO();
	       String datosProcesoSeguroJson = mapper.writeValueAsString(datosProcesoSeguro);
	       log.info("\t --- INICIO PROCESO SEGURO REQUEST --- \n{}", datosProcesoSeguroJson);
	       log.info(PARAMETERS_LOG, datosProcesoSeguroJson);
	       
	       ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post(url)
	           .contentType(MEDIA_TYPE_JSON)
	           .content(datosProcesoSeguroJson)
	       );
	       
	       MockHttpServletResponse response = resultActions.andReturn().getResponse();
	       log.info(RESPONSE_LOG, response.getStatus(), response.getContentAsString());
	       
	       // Validar que la respuesta sea exitosa
	       resultActions.andExpect(status().isOk());
	       
	       // Validar que el contenido de la respuesta sea el esperado
	       String responseContent = response.getContentAsString();
	       log.info("\t --- INICIO PROCESO SEGURO RESPONSE --- \n{}", responseContent);
	       
	       // Verificar que la respuesta contiene el mensaje esperado
	       if (responseContent.contains("Entro a endpoint")) {
	           log.info("Test exitoso: El endpoint inicioProcesoSeguro respondió correctamente");
	       }
	       resultActions.andExpect(status().isOk());
	   }

	
	

}
