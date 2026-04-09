/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */
package com.briomax.briobpm.data.test.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import com.briomax.briobpm.common.core.DAORet;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.data.test.base.AbstractDataTest;
import com.briomax.briobpm.persistence.dao.base.IEjecucionActividadRepository;
import com.briomax.briobpm.persistence.entity.namedquery.LeeColumnasSeccionOcu;
import com.briomax.briobpm.persistence.entity.namedquery.LeeEtiquetasCatalogo;
import com.briomax.briobpm.persistence.entity.namedquery.LeeEtiquetasTabla;
import com.briomax.briobpm.persistence.entity.namedquery.LeeInfSeccionOcu;
import com.briomax.briobpm.persistence.entity.namedquery.LeeMensajesReglas;
import com.briomax.briobpm.persistence.entity.namedquery.LeeReglasActividad;
import com.briomax.briobpm.persistence.entity.namedquery.LeeSeccionesNodo;
import com.briomax.briobpm.persistence.entity.namedquery.LeeTareasNodo;
import com.briomax.briobpm.persistence.entity.namedquery.LeeVariablesSeccion;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Class EjecucionActividadRepositoryTest.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion 26/01/2020 02:42:11 PM Modificaciones:
 * @since JDK 1.8
 */
@Slf4j
public class EjecucionActividadRepositoryTest extends AbstractDataTest {

	/** El atributo o variable ejecucion actividad repository. */
	@Autowired
	private IEjecucionActividadRepository ejecucionActividadRepository;

	/**
	 * Lee secciones area trabajo test.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void leeSeccionesTest() throws BrioBPMException {
		final DAORet<List<LeeSeccionesNodo>, RetMsg> ret = ejecucionActividadRepository.leeSeccionesNodo(getUser(),
			getEntidad(), getLocalidad(), getIdioma(), CVE_PROCESO, VERSION, CVE_NODO, ID_NODO, "NO");
		imprimir(ret);
		assertEquals("OK", ret.getMeta().getStatus().toUpperCase());
	}

	/**
	 * Lee columnas seccion ocu test.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void leeColumnasSeccionOcuTest() throws BrioBPMException {
		final DAORet<List<LeeColumnasSeccionOcu>, RetMsg> ret =
			ejecucionActividadRepository.leeColumnasSeccionOcu(getUser(), getEntidad(), getLocalidad(), getIdioma(),
				CVE_ROL, CVE_PROCESO, VERSION, CVE_NODO, 9, "INFORMACION_FACTURACION_SOPORTE");
		imprimir(ret);
		assertEquals("OK", ret.getMeta().getStatus().toUpperCase());
	}

	/**
	 * Lee etiquetas catalogo test.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void leeEtiquetasCatalogoTest() throws BrioBPMException {
		final DAORet<List<LeeEtiquetasCatalogo>, RetMsg> ret =
			ejecucionActividadRepository.leeEtiquetasCatalogo(getUser(), getEntidad(), getLocalidad(), getIdioma(),
				CVE_PROCESO, VERSION, "201807-000001", CVE_NODO, ID_NODO, 1, "DIA_DE_LA_SEMANA");
		imprimir(ret);
		assertEquals("OK", ret.getMeta().getStatus().toUpperCase());
	}
	
	/**
	 * Lee etiquetas tabla test.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void leeEtiquetasTablaTest() throws BrioBPMException {
		final DAORet<List<LeeEtiquetasTabla>, RetMsg> ret = ejecucionActividadRepository.leeEtiquetasTabla(getUser(),
			getEntidad(), getLocalidad(), LOCALE_SPANISH, CVE_PROCESO, VERSION, "201807-000001", CVE_NODO, ID_NODO, 1,
			"VPRO_MODULO", "ID_MODULO", "DESCRIPCION_MODULO", "AP_MODULO", "");
		imprimir(ret);
		assertEquals("OK", ret.getMeta().getStatus().toUpperCase());
	}

	/**
	 * Lee inf seccion ocu test.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void leeInfSeccionOcuTest() throws BrioBPMException {
		final DAORet<List<LeeInfSeccionOcu>, RetMsg> ret =
			ejecucionActividadRepository.leeInfSeccionOcu(getUser(), getEntidad(), getLocalidad(), getIdioma(),
				CVE_ROL, CVE_PROCESO, VERSION, "201807-000001", CVE_NODO, 9, "INFORMACION_FACTURACION_SOPORTE");
		imprimir(ret);
		assertEquals("OK", ret.getMeta().getStatus().toUpperCase());
	}

	/**
	 * Lee variables seccion test.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void leeVariablesSeccionTest() throws BrioBPMException {
		final DAORet<List<LeeVariablesSeccion>, RetMsg> ret =
			ejecucionActividadRepository.leeVariablesSeccion(getUser(), getEntidad(), getLocalidad(), getIdioma(),
				CVE_PROCESO, VERSION, "201807-000001", CVE_NODO, 1, 2, "DATOS_GENERALES_SOLICITUD", "NO");
		imprimir(ret);
		assertEquals("OK", ret.getMeta().getStatus().toUpperCase());
	}

	/**
	 * Guarda variables seccion test.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	@Ignore
	public void guardaVariablesSeccionTest() throws BrioBPMException {
		RetMsg response = ejecucionActividadRepository.guardaVariablesSeccion(getUser(), getEntidad(), getLocalidad(),
			LOCALE_SPANISH, "FACTURACION", CVE_PROCESO, VERSION, "202005-000002", "ACTIVIDAD-USUARIO", 1,
			"INFORMACION_FACTURACION_INCIDENCIA", 1, "SI", "NO",
			"VPRO_CONCEPTO_CORRECCION|1|1|Nuevo A Primer Concepto|VPRO_HORAS_INVERTIDAS_CORRECCION|1|1|8|VPRO_IMPORTE_CORRECCION|1|1||VPRO_IMPORTE_CORRECCION_MAS_IVA|1|1||VPRO_IMPORTE_IVA_CORRECCION|1|1||VPRO_TARIFA_CORRECCION|1|1|");
		log.info("SP_GUARDA_VARIABLES_SECCION: {} ", response);
		assertEquals("OK", response.getStatus().toUpperCase());
	}

	/**
	 * Termina actividad test.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void terminaActividadTest() throws BrioBPMException {
		RetMsg response = ejecucionActividadRepository.terminaActividad(getUser(), getEntidad(), getLocalidad(),
			LOCALE_SPANISH, CVE_PROCESO, VERSION, "201807-000001", "ACTIVIDAD-USUARIO", 1, 2);
		log.info("SP_TERMINA_ACTIVIDAD: {} ", response);
		//assertEquals("OK", response.getStatus().toUpperCase());
	}

	/**
	 * Lee tareas nodo test.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void leeTareasNodoTest() throws BrioBPMException {
		final DAORet<List<LeeTareasNodo>, RetMsg> response =
			ejecucionActividadRepository.leeTareasNodo(getUser(), getEntidad(), getLocalidad(), LOCALE_SPANISH,
				CVE_PROCESO, VERSION, "201807-000001", "ACTIVIDAD-USUARIO", 1, 2, "VERIFICACION_CLIENTE", "NO");
		log.info("SP_LEE_TAREAS_NODO:");
		imprimir(response);
	}
	
	/**
	 * Lee tareas nodo dummy test.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void leeTareasNodoDummyTest() throws BrioBPMException {
		final DAORet<List<LeeTareasNodo>, RetMsg> response =
			ejecucionActividadRepository.leeTareasNodo(getUser(), getEntidad(), getLocalidad(), LOCALE_SPANISH,
				"DUMMY", VERSION, "202005-000008", "ACTIVIDAD-USUARIO", 1, 2, "TAREAS DUMMY", "NO");
		log.info("SP_LEE_TAREAS_NODO_NEW:");
		imprimir(response);
	}

	/**
	 * Guarda tareas seccion test.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	@Ignore
	public void guardaTareasSeccionTest() throws BrioBPMException {
		RetMsg response = ejecucionActividadRepository.guardaTareasSeccion("Gabriela.Arias", getEntidad(), getLocalidad(),
			LOCALE_SPANISH, CVE_PROCESO, VERSION, "201807-000001", "ACTIVIDAD-USUARIO", 8, 2, "1|SI|2|NO|3|SI|2|NO");
		log.info("SP_GUARDA_VARIABLES_SECCION: {} ", response);
		assertEquals("OK", response.getStatus().toUpperCase());
	}

	/**
	 * Cambia situacion actividad test.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void cambiaSituacionActividadTest() throws BrioBPMException {
		log.info("\t\t SP_CAMBIA_SITUACION_NODO ");
		RetMsg response = ejecucionActividadRepository.cambiaSituacionActividad(getUser(), getEntidad(), getLocalidad(),
			LOCALE_SPANISH, CVE_PROCESO, VERSION, "201807-000001", "ACTIVIDAD-USUARIO", 1, 2, "OBTENER");
		log.info("SP_CAMBIA_SITUACION_NODO 'OBTENER': {} ", response);
		response = ejecucionActividadRepository.cambiaSituacionActividad(getUser(), getEntidad(), getLocalidad(),
			LOCALE_SPANISH, CVE_PROCESO, VERSION, "201807-000001", "ACTIVIDAD-USUARIO", 1, 2, "CEDER");
		log.info("SP_CAMBIA_SITUACION_NODO 'CEDER': {} ", response);
		response = ejecucionActividadRepository.cambiaSituacionActividad(getUser(), getEntidad(), getLocalidad(),
			LOCALE_SPANISH, CVE_PROCESO, VERSION, "201807-000001", "ACTIVIDAD-USUARIO", 1, 2, "CANCELAR");
		log.info("SP_CAMBIA_SITUACION_NODO 'CANCELAR': {} ", response);
		//assertEquals("OK", response.getStatus().toUpperCase());
	}

	/**
	 * Lee mensajes reglas test.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void leeMensajesReglasTest() throws BrioBPMException {
		final DAORet<List<LeeMensajesReglas>, RetMsg> ret = ejecucionActividadRepository.leeMensajesReglas("Miguel.Garcia.Saavedra",
			getEntidad(), getLocalidad(), getIdioma(), "GERENTE-CUENTA", CVE_PROCESO, VERSION, CVE_NODO, 2);
		imprimir(ret);
		assertEquals("OK", ret.getMeta().getStatus().toUpperCase());
	}

	/**
	 * Lee reglas actividad test.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	@Test
	public void leeReglasActividadTest() throws BrioBPMException {
		final DAORet<List<LeeReglasActividad>, RetMsg> ret = ejecucionActividadRepository.leeReglasActividad(
			"Miguel.Garcia.Saavedra", getEntidad(), getLocalidad(), getIdioma(), "GERENTE-CUENTA", CVE_PROCESO, VERSION,
			"202106-000002", CVE_NODO, 2, 1);
		imprimir(ret);
		assertEquals("OK", ret.getMeta().getStatus().toUpperCase());
	}

}
