/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.rest.test.base;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.briomax.briobpm.rest.app.BackBrioBPMApplication;
import com.briomax.briobpm.transferobjects.in.ActividadTO;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;
import com.briomax.briobpm.transferobjects.in.ProcesoSeguroTO;
import com.briomax.briobpm.transferobjects.in.ProcesoTO;
import com.briomax.briobpm.transferobjects.in.StVariableDependienteInTO;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Getter;
import lombok.Setter;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = BackBrioBPMApplication.class)
public abstract class AbstractRestTest {

	/** La Constante CVE_ENTIDAD. */
	protected static final String CVE_ENTIDAD = "BRIOMAX";

	/** La Constante CVE_LOCALIDAD. */
	protected static final String CVE_LOCALIDAD = "BRIOMAX-CENTRAL";

	/** La Constante CVE_USUARIO. */
	protected static final String CVE_USUARIO = "Francisco.Rodriguez";

	/** La Constante PASSWORD. */
	protected static final String PASSWORD = "Frodriguez";

	/** La Constante IDIOMA. */
	protected static final String IDIOMA = "ES-MX";

	/** La Constante MONEDA. */
	protected static final String MONEDA = "MXN";

	/** format de fecha. */
	protected static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd");

	/** La Constante ROOT_URL. */
	protected static final String ROOT_CONTEXT = "ServicesBrioBPM";
	
	/** La Constante REQUEST_LOG. */
	protected static final String REQUEST_LOG = "REQUEST ### {}";

	/** La Constante RESPONSE_LOG. */
	protected static final String RESPONSE_LOG = "RESPONSE ### Status: {} Content: {}";

	/** La Constante PARAMETERS_LOG. */
	protected static final String PARAMETERS_LOG = "\tParameters={}";
	
	/** The media type json. */
	protected static final MediaType MEDIA_TYPE_JSON = new MediaType(MediaType.APPLICATION_JSON.getType(),
		MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	/** Convertidor de json a mensaje http. */
	protected @Getter @Setter HttpMessageConverter<?> convertidorJsonAMensajeHttp;

	/** Objeto mock para lanzar pruebas hacia los controladores. */
	protected @Getter @Setter MockMvc mockMvc;

	/** Definición del contexto. */
	@Autowired
	protected WebApplicationContext contextoWeb;

	/** El atributo o variable mapper. */
	protected ObjectMapper mapper;

	/**
	 * Bloque inicializador de variables, crea el contexto para ejecutar la prueba.
	 * @throws Exception lanzada cuando ocurre una excepción
	 */
	@Before
	public void setup() throws Exception {
		mapper = new ObjectMapper();
		initMockMvc();
	}

	/**
	 * Inits the mock mvc.
	 */
	protected void initMockMvc() {
		this.setMockMvc(MockMvcBuilders.webAppContextSetup(contextoWeb)
			.build());
	}

	/**
	 * Estabelece convertidores de las peticiones Http a Json.
	 * @param converters Convertidores que se usarán para transformar las peticiones http
	 */
	@Autowired
	protected void setConverters(final HttpMessageConverter<?>[] converters) {
		this.convertidorJsonAMensajeHttp = Arrays.asList(converters).stream()
			.filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter).findAny().orElse(null);
		//assertNotNull("el convertidor de mensagjes JSON no debe ser nulo", this.convertidorJsonAMensajeHttp);
		assertThat(convertidorJsonAMensajeHttp).isNotNull();
	}

	/**
	 * Builder datos autenticacion TO.
	 * @return el datos autenticacion TO.
	 */
	protected DatosAutenticacionTO builderDatosAutenticacionTO() {
		DatosAutenticacionTO datosAutenticacion = DatosAutenticacionTO.builder()
				.cveEntidad(CVE_ENTIDAD)
				.cveLocalidad(CVE_LOCALIDAD)
				.cveUsuario(CVE_USUARIO)
				.cveIdioma(IDIOMA)
				.cveMoneda(MONEDA)
				.build();
		return datosAutenticacion;
	}

	/**
	 * Builder proceso TO.
	 * @return el proceso TO.
	 */
	protected ProcesoTO builderProcesoTO() {
		ProcesoTO datosProceso = new ProcesoTO();
		datosProceso.setCveProceso("INCIDENCIA-SMI");
		datosProceso.setVersion("1.00");
		datosProceso.setCveNodo("ACTIVIDAD-USUARIO");
		datosProceso.setIdNodo(1);
		datosProceso.setCveAreaTrabajo("SOLICITUD_SOPORTE");
		datosProceso.setCveRol("ADMINISTRADOR");
		return datosProceso;
	}

	/**
	 * Builder actividad.
	 * @return el actividad TO.
	 */
	protected ActividadTO builderActividad() {
		ActividadTO actividad = ActividadTO.builderActividadTO()
				.cveProceso("INCIDENCIA-SMI")
				.version("1.00")
				.cveNodo("ACTIVIDAD-USUARIO")
				.idNodo(1)
				.idNodo(9)
				.cveAreaTrabajo("")
				.cveInstancia("201807-000001")
				.secNodo(9)
				.cveRol("ADMINISTRADOR")
				.build();
		return actividad;
	}
	
	protected StVariableDependienteInTO builderNodoSiguienteTO() {
		StVariableDependienteInTO actividad = StVariableDependienteInTO.builderVD()
				.etiquetaLista(" COPL1234")
				.cveEntidad("BRIOMAX")
				.cveProceso("REGISTRO_DE_CONTRATO")
				.version(BigDecimal.ONE)
				.cveNodo("AIVIDAD-USUARIO")
				.idNodo(1)
				.cveSeccion("DATO_CONTRATISTA")
				.cveVariable("VPRO_01_RFC_CONTRATISTA")
				.cveSeccionDependiente("DATO_CONTRATISTA")
				.build();
		return actividad;
	}
	
	 protected ProcesoSeguroTO builderProcesoSeguroTO() {
	       ProcesoSeguroTO datosProcesoSeguro = new ProcesoSeguroTO();
	       datosProcesoSeguro.setCveUsuario("AIT.Usuario.Cliente");
	       datosProcesoSeguro.setValoresReferenciaEnvio("VPRO_01_CR|50TXB,VPRO_01_NOM_CLIE|Temporizador 1,VPRO_01_NOM_TIENDA| Chetewi MEX,VPRO_01_NUM_INCIDENTE|42323,VPRO_01_OBSER_A|prueba mensaje#CVE_INSTANCIA");

	       return datosProcesoSeguro;
	   }

}
