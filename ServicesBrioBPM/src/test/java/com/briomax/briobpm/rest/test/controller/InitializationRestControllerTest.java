
package com.briomax.briobpm.rest.test.controller;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.briomax.briobpm.rest.controller.InitializationRestController;
import com.briomax.briobpm.rest.test.base.AbstractRestTest;
import com.briomax.briobpm.transferobjects.EntidadTO;
import com.briomax.briobpm.transferobjects.MenuAreaTrabajoTO;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;
import com.fasterxml.jackson.core.type.TypeReference;

import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InitializationRestControllerTest extends AbstractRestTest {

	@Test
	public void datosinicioTest() throws Exception {
		String url = InitializationRestController.ROOT_URL + "datosinicio";
		log.info(REQUEST_LOG, ROOT_CONTEXT + url);
		ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(url)
			.contentType(MEDIA_TYPE_JSON));
		MockHttpServletResponse response = resultActions.andReturn().getResponse();
		resultActions.andExpect(status().isOk());
		log.info(RESPONSE_LOG, response.getStatus(), response.getContentAsString());
	}

	
	@Test
	public void etiquetasTest() throws Exception {
		String url = InitializationRestController.ROOT_URL + "etiquetas";
		log.info(REQUEST_LOG, ROOT_CONTEXT + url);
		ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(url)
			.param("idioma", "EN-EU")
			.contentType(MEDIA_TYPE_JSON));
		MockHttpServletResponse response = resultActions.andReturn().getResponse();
		log.info(RESPONSE_LOG, response.getStatus(), response.getContentAsString());
		resultActions.andExpect(status().isOk());
	}

	@Test
	public void menudinamicoTest() throws Exception {
		String url = InitializationRestController.ROOT_URL + "menudinamico";
		log.info(REQUEST_LOG, ROOT_CONTEXT + url);
		DatosAutenticacionTO datosAutenticacion = builderDatosAutenticacionTO();
		String menudinamicoJson = mapper.writeValueAsString(datosAutenticacion);
		log.info(PARAMETERS_LOG, menudinamicoJson);
		ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post(url)
			.contentType(MEDIA_TYPE_JSON)
			.content(menudinamicoJson));
		MockHttpServletResponse response = resultActions.andReturn().getResponse();
		resultActions.andExpect(status().isOk());
		log.info(RESPONSE_LOG, response.getStatus(), response.getContentAsString());
		List<MenuAreaTrabajoTO> opciones =
			Arrays.asList(mapper.readValue(response.getContentAsString(), MenuAreaTrabajoTO[].class));
		for (MenuAreaTrabajoTO opcion : opciones) {
			log.info("\t{}", opcion);
		}
	}

	@Test
	public void datosEntidadTest() throws Exception {
		String url = InitializationRestController.ROOT_URL + "datosentidad";
		log.info(REQUEST_LOG, ROOT_CONTEXT + url);
		ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(url)
			.contentType(MEDIA_TYPE_JSON));
		MockHttpServletResponse response = resultActions.andReturn().getResponse();
		resultActions.andExpect(status().isOk());
		log.info(RESPONSE_LOG, response.getStatus(), response.getContentAsString());
	}

	@Test
	public void datosLocalidadTest() throws Exception {
		String url = InitializationRestController.ROOT_URL + "datoslocalidad";
		log.info(REQUEST_LOG, ROOT_CONTEXT + url);
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("cveUsuario", CVE_USUARIO);
		params.add("cveEntidad", CVE_ENTIDAD);
		params.add("cveLocalidad", CVE_LOCALIDAD);
		params.add("cveIdioma", IDIOMA);
		log.info(PARAMETERS_LOG, params);
		ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post(url)
			.params(params)
//			.param("cveUsuario", CVE_USUARIO)
//			.param("cveEntidad", CVE_ENTIDAD)
//			.param("cveLocalidad", CVE_LOCALIDAD)
//			.param("cveIdioma", IDIOMA)
			.contentType(MEDIA_TYPE_JSON)
			);
		MockHttpServletResponse response = resultActions.andReturn().getResponse();
		resultActions.andExpect(status().isOk());
		log.info(RESPONSE_LOG, response.getStatus(), response.getContentAsString());
	}

	@Test
	public void getEntidadesTest() throws Exception {
		String url = InitializationRestController.ROOT_URL + "getentidades";
		log.info(REQUEST_LOG, ROOT_CONTEXT + url);
		ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(url));
		MockHttpServletResponse response = resultActions.andReturn().getResponse();
		resultActions.andExpect(status().isOk());
		log.info(RESPONSE_LOG, response.getStatus(), response.getContentAsString());
		List<EntidadTO> entidades =
			mapper.readValue(response.getContentAsString(), new TypeReference<List<EntidadTO>>() {});
		entidades.forEach(entidad -> log.info("\t{}", entidad));
	}

}
