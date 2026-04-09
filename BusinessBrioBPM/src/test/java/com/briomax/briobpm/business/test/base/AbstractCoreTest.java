/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */
package com.briomax.briobpm.business.test.base;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.runner.RunWith;

import static org.junit.Assert.fail;

import com.briomax.briobpm.business.test.config.CoreTestInitializer;
import com.briomax.briobpm.common.core.DAORet;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.transferobjects.in.ActividadTO;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;
import com.briomax.briobpm.transferobjects.in.DocumentoTO;
import com.briomax.briobpm.transferobjects.in.ProcesoTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;


/**
 * El objetivo de la Class AbstractCoreTest.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Jul 31, 2020 9:57:09 AM Modificaciones:
 * @since JDK 1.8
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CoreTestInitializer.class)
@Slf4j
@Transactional
public abstract class AbstractCoreTest {

	/** La Constante USUARIO. */
	protected static final String USUARIO = "Francisco.Rodriguez";

	/** La Constante MONEDA. */
	protected static final String MONEDA = "MXN";

	/** El atributo o variable entidad. */
	@Value("${brio.bpm.entidad}")
	public @Getter String entidad;

	/** El atributo o variable localidad. */
	@Value("${brio.bpm.localidad}")
	public @Getter String localidad;

	/** El atributo o variable idioma. */
	@Value("${brio.bpm.idioma}")
	public @Getter String idioma;

	/** El atributo o variable cve proceso. */
	@Value("${brio.bpm.test.proceso}")
	public @Getter String cveProceso;

	/** El atributo o variable version. */
	@Value("${brio.bpm.test.version}")
	public @Getter String version;
	
	/** El atributo o variable mapper. */
	final protected ObjectMapper mapper = new ObjectMapper();

	/**
	 * Printer json.
	 * @param object el object.
	 */
	protected void printerJson(Object object) {
		try {
			String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
			log.info("\n{}", json);
		}
		catch (JsonProcessingException e) {
			fail("Parse Json");
		}
	}

	/**
	 * Obtener el valor de datos autenticacion TO.
	 * @return el datos autenticacion TO.
	 */
	protected DatosAutenticacionTO getDatosAutenticacionTO() {
		DatosAutenticacionTO datosAutenticacion = DatosAutenticacionTO.builder()
				.cveEntidad(entidad)
				.cveLocalidad(localidad)
				.cveUsuario(USUARIO)
				.cveIdioma(idioma)
				.cveMoneda(MONEDA)
				.build();
		return datosAutenticacion;
	}
	
	/**
	 * Obtener el valor de proceso.
	 * @return el proceso.
	 */
	protected ProcesoTO getProceso() {
		ProcesoTO datosProceso = ProcesoTO.builder()
				.cveProceso("INCIDENCIA-SMI")
				.version("1.00")
				.cveNodo("ACTIVIDAD-USUARIO")
				.idNodo(1)
				.cveAreaTrabajo("")
				.cveRol("ADMINISTRADOR")
				.build();
		return datosProceso;
	}

	/**
	 * Ge actividad.
	 * @return el actividad TO.
	 */
	protected ActividadTO geActividad() {
		ActividadTO actividad = ActividadTO.builderActividadTO()
				.cveProceso("INCIDENCIA-SMI")
				.version("1.00")
				.cveInstancia("201807-000001")
				.cveNodo("ACTIVIDAD-USUARIO")
				.idNodo(9)
				.secNodo(9)
				.cveAreaTrabajo("")
				.cveRol("ADMINISTRADOR")
				.build();
		return actividad;
	}

	/**
	 * Obtener el valor de documento to.
	 * @return el documento to.
	 */
	protected DocumentoTO getDocumentoTo() {
		DocumentoTO document = DocumentoTO.builderDocumentoTO()
				.cveProceso("INCIDENCIA-SMI")
				.version("1.00")
				.cveInstancia("201807-000001")
				.cveNodo("ACTIVIDAD-USUARIO")
				.idNodo(1)
				.secNodo(2)
				.cveAreaTrabajo("")
				.secDocumento(0)
				.cveRol("ADMINISTRADOR")
				.build();
		return document;
	}

	/**
	 * Imprimir.
	 * @param <T> el tipo generico.
	 * @param ret el ret.
	 */
	public <T> void imprimir(final DAORet<List<T>, RetMsg> ret) {
		ret.getContent().forEach((row) -> {
			log.info("{}", row);
		});
	}
	
	/**
	 * Converter json as string.
	 * @param object el object.
	 * @return el string.
	 */
	protected String converterJsonAsString(Object object) {
		try {
			return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
		}
		catch (JsonProcessingException jsonEx) {
			log.error("{} <> {}", jsonEx.getMessage(), object);
			return null;
		}
	}
}
