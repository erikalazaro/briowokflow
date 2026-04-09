/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados. Este software contiene informacion
 * confidencial propiedad de Briomax Consulting por lo cual no puede ser reproducido, distribuido o alterado sin el
 * consentimiento previo de Briomax Consulting.
 */

package com.briomax.briobpm.persistence.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.ParameterMode;

import com.briomax.briobpm.common.core.DAORet;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.persistence.dao.base.AbstractBaseDAO;
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

import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Class EjecucionActividadRepository.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion 26/01/2020 06:05:12 PM Modificaciones:
 * @since JDK 1.8
 */
@Repository("ejecucionActividadRepository")
@Slf4j
public class EjecucionActividadRepository extends AbstractBaseDAO implements IEjecucionActividadRepository {

	/**
	 * Crear una nueva instancia del objeto ejecucion actividad repository.
	 */
	public EjecucionActividadRepository() {
	}

	/**
	 * {@inheritDoc}
	 * @see com.briomax.briobpm.persistence.dao.base.IEjecucionActividadRepository#leeSeccionesNodo(java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.math.BigDecimal, java.lang.String,
	 *      java.lang.Integer, java.lang.String)
	 */
	@Override
	public DAORet<List<LeeSeccionesNodo>, RetMsg> leeSeccionesNodo(String cveUsuario, String cveEntidad,
		String cveLocalidad, String cveIdioma, String cveProceso, BigDecimal version, String cveNodo, Integer idNodo,
		String banGenTabTmp) throws BrioBPMException {
		log.debug("\t\t <DATABASE> EXEC SP_LEE_SECCIONES_NODO '{}', '{}', '{}', '{}', '{}', {}, '{}', {}, '{}', " +
			"@CH_TIPO_EXCEPCION, @CH_MENSAJE ", cveUsuario, cveEntidad, cveLocalidad, cveIdioma, cveProceso, version,
			cveNodo, idNodo, banGenTabTmp);
		return executeNamedStored("obtenerSeccionesNodo",
			new String[] {"CH_CVE_USUARIO", "CH_CVE_ENTIDAD", "CH_CVE_LOCALIDAD", "CH_CVE_IDIOMA", "CH_CVE_PROCESO",
				"C_VERSION", "CH_CVE_NODO", "I_ID_NODO", "CH_GENERA_TABLA_TEMPORAL"},
			new Object[] {cveUsuario, cveEntidad, cveLocalidad, cveIdioma, cveProceso, version, cveNodo, idNodo,
				banGenTabTmp},
			RetMsg.class);
	}

	/**
	 * {@inheritDoc}
	 * @see com.briomax.briobpm.persistence.dao.base.IEjecucionActividadRepository#leeColumnasSeccionOcu(java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.math.BigDecimal,
	 *      java.lang.String, java.lang.Integer, java.lang.String)
	 */
	@Override
	public DAORet<List<LeeColumnasSeccionOcu>, RetMsg> leeColumnasSeccionOcu(String cveUsuario, String cveEntidad,
		String cveLocalidad, String cveIdioma, String cveRol, String cveProceso, BigDecimal version, String cveNodo,
		Integer idNodo, String cveSeccion) throws BrioBPMException {
		log.debug("\t\t <DATABASE> EXEC SP_LEE_COLUMNAS_SECCION_OCU '{}', '{}', '{}', '{}', '{}', '{}', {}, '{}', {}," +
			" '{}', @CH_TIPO_EXCEPCION, @CH_MENSAJE", cveUsuario, cveEntidad, cveLocalidad, cveIdioma, cveRol,
			cveProceso, version, cveNodo, idNodo, cveSeccion);
		return executeNamedStored("obtenerColumnasSeccionOcu",
			new String[] {"CH_CVE_USUARIO", "CH_CVE_ENTIDAD", "CH_CVE_LOCALIDAD", "CH_CVE_IDIOMA", "CH_ROL",
				"CH_CVE_PROCESO", "C_VERSION", "CH_CVE_NODO", "I_ID_NODO", "CH_CVE_SECCION"},
			new Object[] {cveUsuario, cveEntidad, cveLocalidad, cveIdioma, cveRol, cveProceso, version, cveNodo, idNodo,
				cveSeccion},
			RetMsg.class);
	}

	/**
	 * {@inheritDoc}
	 * @see com.briomax.briobpm.persistence.dao.base.IEjecucionActividadRepository#leeEtiquetasCatalogo(java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.math.BigDecimal, java.lang.String,
	 *      java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.String)
	 */
	@Override
	public DAORet<List<LeeEtiquetasCatalogo>, RetMsg> leeEtiquetasCatalogo(String cveUsuario, String cveEntidad,
		String cveLocalidad, String cveIdioma, String cveProceso, BigDecimal version, String cveInstancia, String cveNodo,
		Integer idNodo, Integer idSecuenciaNodo, String cveDato) throws BrioBPMException {
		log.debug("\t\t <DATABASE> EXEC SP_LEE_ETIQUETAS_CATALOGO '{}', '{}', '{}', '{}', '{}', {}, '{}', '{}', {}," +
			" {}, '{}', @CH_TIPO_EXCEPCION, @CH_MENSAJE", cveUsuario, cveEntidad, cveLocalidad, cveIdioma,
			cveProceso, version, cveInstancia, cveNodo, idNodo, idSecuenciaNodo, cveDato);
		return executeNamedStored("obtenerEtiquetasCatalogo",
			new String[] {"CH_CVE_USUARIO", "CH_CVE_ENTIDAD", "CH_CVE_LOCALIDAD", "CH_CVE_IDIOMA", "CH_CVE_PROCESO",
				"C_VERSION", "CH_CVE_INSTANCIA", "CH_CVE_NODO", "I_ID_NODO", "I_SECUENCIA_NODO", "CH_CVE_DATO"},
			new Object[] {cveUsuario, cveEntidad, cveLocalidad, cveIdioma, cveProceso, version, cveInstancia, cveNodo,
				idNodo, idSecuenciaNodo, cveDato},
			RetMsg.class);
	}

	/**
	 * {@inheritDoc}
	 * @see com.briomax.briobpm.persistence.dao.base.IEjecucionActividadRepository#leeEtiquetasTabla(java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.math.BigDecimal, java.lang.String,
	 *      java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public DAORet<List<LeeEtiquetasTabla>, RetMsg> leeEtiquetasTabla(String cveUsuario, String cveEntidad,
		String cveLocalidad, String cveIdioma, String cveProceso, BigDecimal version, String cveInstancia, String cveNodo,
		Integer idNodo, Integer idSecuenciaNodo, String cveDato, String valorLista, String descripcionLista,
		String tablaLista, String whereLista) throws BrioBPMException {
		log.debug("\t\t <DATABASE> EXEC SP_LEE_ETIQUETAS_TABLA '{}', '{}', '{}', '{}', '{}', {}, '{}', '{}', {}, {}," +
			" '{}', '{}', '{}', '{}', '{}', @CH_TIPO_EXCEPCION, @CH_MENSAJE", cveUsuario, cveEntidad, cveLocalidad,
			cveIdioma, cveProceso, version, cveInstancia, cveNodo, idNodo, idSecuenciaNodo, cveDato, valorLista,
			descripcionLista, tablaLista, whereLista);
		return executeNamedStored("obtenerEtiquetasTabla",
			new String[] {"CH_CVE_USUARIO", "CH_CVE_ENTIDAD", "CH_CVE_LOCALIDAD", "CH_CVE_IDIOMA", "CH_CVE_PROCESO",
				"C_VERSION", "CH_CVE_INSTANCIA", "CH_CVE_NODO", "I_ID_NODO", "I_SECUENCIA_NODO", "CH_CVE_DATO",
				"CH_VALOR_LISTA", "CH_DESCRIPCION_LISTA", "CH_TABLA_LISTA", "CH_WHERE_LISTA"},
			new Object[] {cveUsuario, cveEntidad, cveLocalidad, cveIdioma, cveProceso, version, cveInstancia, cveNodo,
				idNodo, idSecuenciaNodo, cveDato, valorLista, descripcionLista, tablaLista, whereLista},
			RetMsg.class);
	}

	/**
	 * {@inheritDoc}
	 * @see com.briomax.briobpm.persistence.dao.base.IEjecucionActividadRepository#leeInfSeccionOcu(java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.math.BigDecimal,
	 *      java.lang.String, java.lang.String, java.lang.Integer, java.lang.String)
	 */
	@Override
	public DAORet<List<LeeInfSeccionOcu>, RetMsg> leeInfSeccionOcu(String cveUsuario, String cveEntidad,
		String cveLocalidad, String cveIdioma, String cveRol, String cveProceso, BigDecimal version, String cveInstancia,
		String cveNodo, Integer idNodo, String cveSeccion) throws BrioBPMException {
		log.debug("\t\t <DATABASE> EXEC SP_LEE_INF_SECCION_OCU '{}', '{}', '{}', '{}', '{}', '{}', '{}', '{}'," +
			" '{}', '{}', '{}', @CH_TIPO_EXCEPCION, @CH_MENSAJE ", cveUsuario, cveEntidad, cveLocalidad, cveIdioma,
			cveRol, cveProceso, version, cveInstancia, cveNodo, idNodo, cveSeccion);
		return executeNamedStored("obtenerInfSeccionOcu",
			new String[] {"CH_CVE_USUARIO", "CH_CVE_ENTIDAD", "CH_CVE_LOCALIDAD", "CH_CVE_IDIOMA", "CH_ROL",
				"CH_CVE_PROCESO", "C_VERSION", "CH_CVE_INSTANCIA", "CH_CVE_NODO", "I_ID_NODO", "CH_CVE_SECCION"},
			new Object[] {cveUsuario, cveEntidad, cveLocalidad, cveIdioma, cveRol, cveProceso, version, cveInstancia,
				cveNodo, idNodo, cveSeccion},
			RetMsg.class);
	}

	/**
	 * {@inheritDoc}
	 * @see com.briomax.briobpm.persistence.dao.base.IEjecucionActividadRepository#leeVariablesSeccion(java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.math.BigDecimal, java.lang.String,
	 *      java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String)
	 */
	@Override
	public DAORet<List<LeeVariablesSeccion>, RetMsg> leeVariablesSeccion(String cveUsuario, String cveEntidad,
		String cveLocalidad, String cveIdioma, String cveProceso, BigDecimal version, String cveInstancia, String cveNodo,
		Integer idNodo, Integer secuenciaNodo, String cveSeccion, String banGenTabTmp) throws BrioBPMException {
		log.debug("\t\t <DATABASE> EXEC SP_LEE_VARIABLES_SECCION '{}', '{}', '{}', '{}', '{}', {}, '{}', '{}', {}, {}," +
			" '{}', '{}', @CH_TIPO_EXCEPCION, @CH_MENSAJE ", cveUsuario, cveEntidad, cveLocalidad, cveIdioma,
			cveProceso, version, cveInstancia, cveNodo, idNodo, secuenciaNodo, cveSeccion, banGenTabTmp);
		return executeNamedStored("obtenerVariablesSeccion",
			new String[] {"CH_CVE_USUARIO", "CH_CVE_ENTIDAD", "CH_CVE_LOCALIDAD", "CH_CVE_IDIOMA", "CH_CVE_PROCESO",
				"C_VERSION", "CH_CVE_INSTANCIA", "CH_CVE_NODO", "I_ID_NODO", "I_SECUENCIA_NODO", "CH_CVE_SECCION",
				"CH_GENERA_TABLA_TEMPORAL"},
			new Object[] {cveUsuario, cveEntidad, cveLocalidad, cveIdioma, cveProceso, version, cveInstancia, cveNodo,
				idNodo, secuenciaNodo, cveSeccion, banGenTabTmp},
			RetMsg.class);
	}

	/** 
	 * {@inheritDoc}
	 * @see com.briomax.briobpm.persistence.dao.base.IEjecucionActividadRepository#guardaVariablesSeccion(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.math.BigDecimal, java.lang.String, java.lang.String, java.lang.Integer, java.lang.String, java.lang.Integer, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public RetMsg guardaVariablesSeccion(String cveUsuario, String cveEntidad, String cveLocalidad, String cveIdioma,
		String cveRol, String cveProceso, BigDecimal version, String cveInstancia, String cveNodo, Integer idNodo,
		String cveSeccion, Integer numOcurrencia, String priOcurrencia, String nueva, String datosOcurrencia)
		throws BrioBPMException {
		log.debug("\t\t <DATABASE> EXEC SP_GUARDA_VARIABLES_SECCION '{}', '{}', '{}', '{}', '{}', '{}', {}, '{}', " +
			"'{}', {}, '{}', {}, '{}', '{}', @CH_TIPO_EXCEPCION, @CH_MENSAJE", cveUsuario, cveEntidad,
			cveLocalidad, cveIdioma, cveRol, cveProceso, version, cveInstancia, cveNodo, idNodo, cveSeccion,
			numOcurrencia, priOcurrencia, nueva, datosOcurrencia);
		return executeNoResulsetStored("SP_GUARDA_VARIABLES_SECCION",
			new String[] {"CH_CVE_USUARIO", "CH_CVE_ENTIDAD", "CH_CVE_LOCALIDAD", "CH_CVE_IDIOMA", "CH_ROL",
				"CH_CVE_PROCESO", "C_VERSION", "CH_CVE_INSTANCIA", "CH_CVE_NODO", "I_ID_NODO", "CH_CVE_SECCION",
				"I_OCURRENCIA", "CH_PRIMERA_OCURRENCIA", "CH_NUEVA_OCURRENCIA", "CH_DATOS_OCURRENCIA",
				"CH_TIPO_EXCEPCION", "CH_MENSAJE"},
			new Class[] {String.class, String.class, String.class, String.class, String.class, String.class,
				BigDecimal.class, String.class, String.class, Integer.class, String.class, Integer.class, String.class,
				String.class, String.class, String.class, String.class},
			new ParameterMode[] {ParameterMode.IN, ParameterMode.IN, ParameterMode.IN, ParameterMode.IN,
				ParameterMode.IN, ParameterMode.IN, ParameterMode.IN, ParameterMode.IN, ParameterMode.IN,
				ParameterMode.IN, ParameterMode.IN, ParameterMode.IN, ParameterMode.IN, ParameterMode.IN,
				ParameterMode.IN, ParameterMode.OUT, ParameterMode.OUT},
			new Object[] {cveUsuario, cveEntidad, cveLocalidad, cveIdioma, cveRol, cveProceso, version, cveInstancia,
				cveNodo, idNodo, cveSeccion, numOcurrencia, priOcurrencia, nueva, datosOcurrencia},
			RetMsg.class);
	}

	/**
	 * {@inheritDoc}
	 * @see com.briomax.briobpm.persistence.dao.base.IEjecucionActividadRepository#terminaActividad(java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.math.BigDecimal, java.lang.String,
	 *      java.lang.String, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public RetMsg terminaActividad(String cveUsuario, String cveEntidad, String cveLocalidad, String cveIdioma,
		String cveProceso, BigDecimal version, String cveInstancia, String cveNodo, Integer idNodo, Integer secuenciaNodo)
		throws BrioBPMException {
		log.debug("\t\t <DATABASE> EXEC SP_TERMINA_ACTIVIDAD '{}', '{}', '{}', '{}', '{}', {}, '{}', '{}', {}, {}," +
			" @CH_TIPO_EXCEPCION, @CH_MENSAJE ", cveUsuario, cveEntidad, cveLocalidad, cveIdioma, cveProceso, version,
			cveInstancia, cveNodo, idNodo, secuenciaNodo);
		return executeNoResulsetStored("SP_TERMINA_ACTIVIDAD",
			new String[] {"CH_CVE_USUARIO", "CH_CVE_ENTIDAD", "CH_CVE_LOCALIDAD", "CH_CVE_IDIOMA", "CH_CVE_PROCESO",
				"C_VERSION", "CH_CVE_INSTANCIA", "CH_CVE_NODO", "I_ID_NODO", "I_SECUENCIA_NODO", "CH_TIPO_EXCEPCION",
				"CH_MENSAJE"},
			new Class[] {String.class, String.class, String.class, String.class, String.class, BigDecimal.class,
				String.class, String.class, Integer.class, Integer.class, String.class, String.class},
			new ParameterMode[] {ParameterMode.IN, ParameterMode.IN, ParameterMode.IN, ParameterMode.IN,
				ParameterMode.IN, ParameterMode.IN, ParameterMode.IN, ParameterMode.IN, ParameterMode.IN,
				ParameterMode.IN, ParameterMode.OUT, ParameterMode.OUT},
			new Object[] {cveUsuario, cveEntidad, cveLocalidad, cveIdioma, cveProceso, version, cveInstancia, cveNodo,
				idNodo, secuenciaNodo},
			RetMsg.class);
	}

	/** 
	 * {@inheritDoc}
	 * @see com.briomax.briobpm.persistence.dao.base.IEjecucionActividadRepository#leeTareasNodo(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.math.BigDecimal, java.lang.String, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String)
	 */
	@Override
	public DAORet<List<LeeTareasNodo>, RetMsg> leeTareasNodo(String cveUsuario, String cveEntidad, String cveLocalidad,
		String cveIdioma, String cveProceso, BigDecimal version, String cveInstancia, String cveNodo, Integer idNodo,
		Integer secuenciaNodo, String cveSeccion, String banGenTabTmp) throws BrioBPMException {
		log.debug("\t\t <DATABASE> EXEC SP_LEE_TAREAS_NODO '{}', '{}', '{}', '{}', '{}', {}, '{}', '{}', {}, {}," +
			" '{}', @CH_TIPO_EXCEPCION, @CH_MENSAJE ", cveUsuario, cveEntidad, cveLocalidad, cveIdioma, cveProceso,
			version, cveInstancia, cveNodo, idNodo, secuenciaNodo, cveSeccion, banGenTabTmp);
		return executeNamedStored("obtenerTareasNodo",
			new String[] {"CH_CVE_USUARIO", "CH_CVE_ENTIDAD", "CH_CVE_LOCALIDAD", "CH_CVE_IDIOMA", "CH_CVE_PROCESO",
				"C_VERSION", "CH_CVE_INSTANCIA", "CH_CVE_NODO", "I_ID_NODO", "I_SECUENCIA_NODO", "CH_CVE_SECCION",
				"CH_GENERA_TABLA_TEMPORAL"},
			new Object[] {cveUsuario, cveEntidad, cveLocalidad, cveIdioma, cveProceso, version, cveInstancia, cveNodo,
				idNodo, secuenciaNodo, cveSeccion, banGenTabTmp},
			RetMsg.class);
	}

	/** 
	 * {@inheritDoc}
	 * @see com.briomax.briobpm.persistence.dao.base.IEjecucionActividadRepository#guardaTareasSeccion(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.math.BigDecimal, java.lang.String, java.lang.String, java.lang.Integer, int, java.lang.String)
	 */
	@Override
	public RetMsg guardaTareasSeccion(String cveUsuario, String cveEntidad, String cveLocalidad, String cveIdioma,
		String cveProceso, BigDecimal version, String cveInstancia, String cveNodo, Integer idNodo, int secuenciaNodo,
		String secuenciaCompletada) throws BrioBPMException {
		log.debug("\t\t <DATABASE> EXEC SP_GUARDA_TAREAS_NODO '{}', '{}', '{}', '{}', '{}', {}, '{}', '{}', {}, {}," +
			" '{}', @CH_TIPO_EXCEPCION, @CH_MENSAJE ", cveUsuario, cveEntidad, cveLocalidad, cveIdioma, cveProceso,
			version, cveInstancia, cveNodo, idNodo, secuenciaNodo, secuenciaCompletada);
		return executeNoResulsetStored("SP_GUARDA_TAREAS_NODO",
			new String[] {"CH_CVE_USUARIO", "CH_CVE_ENTIDAD", "CH_CVE_LOCALIDAD", "CH_CVE_IDIOMA", "CH_CVE_PROCESO",
				"C_VERSION", "CH_CVE_INSTANCIA", "CH_CVE_NODO", "I_ID_NODO", "I_SECUENCIA_NODO",
				"CH_SECUENCIA_COMPLETADA", "CH_TIPO_EXCEPCION", "CH_MENSAJE"},
			new Class[] {String.class, String.class, String.class, String.class, String.class, BigDecimal.class,
				String.class, String.class, Integer.class, Integer.class, String.class, String.class,
				String.class},
			new ParameterMode[] {ParameterMode.IN, ParameterMode.IN, ParameterMode.IN, ParameterMode.IN,
				ParameterMode.IN, ParameterMode.IN, ParameterMode.IN, ParameterMode.IN, ParameterMode.IN,
				ParameterMode.IN, ParameterMode.IN, ParameterMode.OUT, ParameterMode.OUT},
			new Object[] {cveUsuario, cveEntidad, cveLocalidad, cveIdioma, cveProceso, version, cveInstancia, cveNodo,
				idNodo, secuenciaNodo, secuenciaCompletada},
			RetMsg.class);
	}

	/** 
	 * {@inheritDoc}
	 * @see com.briomax.briobpm.persistence.dao.base.IEjecucionActividadRepository#cambiaSituacionActividad(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.math.BigDecimal, java.lang.String, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.String)
	 */
	@Override
	public RetMsg cambiaSituacionActividad(String cveUsuario, String cveEntidad, String cveLocalidad, String cveIdioma,
		String cveProceso, BigDecimal version, String cveInstancia, String cveNodo, Integer idNodo,
		Integer secuenciaNodo, String accion) throws BrioBPMException {
		log.debug("\t\t <DATABASE> EXEC SP_CAMBIA_SITUACION_NODO '{}', '{}', '{}', '{}', '{}', {}, '{}', '{}', {}, " +
			"{}, '{}', @CH_TIPO_EXCEPCION, @CH_MENSAJE ", cveUsuario, cveEntidad, cveLocalidad, cveIdioma,
			cveProceso, version, cveInstancia, cveNodo, idNodo, secuenciaNodo, accion);
		return executeNoResulsetStored("SP_CAMBIA_SITUACION_NODO",
			new String[] {"CH_CVE_USUARIO", "CH_CVE_ENTIDAD", "CH_CVE_LOCALIDAD", "CH_CVE_IDIOMA", "CH_CVE_PROCESO",
				"C_VERSION", "CH_CVE_INSTANCIA", "CH_CVE_NODO", "I_ID_NODO", "I_SECUENCIA_NODO", "CH_ACCION",
				"CH_TIPO_EXCEPCION", "CH_MENSAJE"},
			new Class[] {String.class, String.class, String.class, String.class, String.class, BigDecimal.class,
				String.class, String.class, Integer.class, Integer.class, String.class, String.class, String.class},
			new ParameterMode[] {ParameterMode.IN, ParameterMode.IN, ParameterMode.IN, ParameterMode.IN,
				ParameterMode.IN, ParameterMode.IN, ParameterMode.IN, ParameterMode.IN, ParameterMode.IN,
				ParameterMode.IN, ParameterMode.IN, ParameterMode.OUT, ParameterMode.OUT},
			new Object[] {cveUsuario, cveEntidad, cveLocalidad, cveIdioma, cveProceso, version, cveInstancia, cveNodo,
				idNodo, secuenciaNodo, accion},
			RetMsg.class);
	}

	/** 
	 * {@inheritDoc}
	 * @see com.briomax.briobpm.persistence.dao.base.IEjecucionActividadRepository#leeMensajesReglas(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.math.BigDecimal, java.lang.String, java.lang.Integer)
	 */
	@Override
	public DAORet<List<LeeMensajesReglas>, RetMsg> leeMensajesReglas(String cveUsuario, String cveEntidad,
		String cveLocalidad, String cveIdioma, String cveRol, String cveProceso, BigDecimal version, String cveNodo,
		Integer secuenciaNodo) throws BrioBPMException {
		log.debug("\t\t <DATABASE> EXEC SP_LEE_MENSAJES_REGLAS '{}', '{}', '{}', '{}', '{}', {}, '{}', '{}', {}, {}," +
				" '{}', '{}', @CH_TIPO_EXCEPCION, @CH_MENSAJE ", cveUsuario, cveEntidad, cveLocalidad, cveIdioma, cveRol,
				cveProceso, version, cveNodo, secuenciaNodo);
		entityManager.clear();
		return executeNamedStored("leeMensajesReglas",
			new String[] {"CH_CVE_USUARIO", "CH_CVE_ENTIDAD", "CH_CVE_LOCALIDAD", "CH_CVE_IDIOMA", "CH_ROL",
				"CH_CVE_PROCESO", "C_VERSION", "CH_CVE_NODO", "I_ID_NODO"},
			new Object[] {cveUsuario, cveEntidad, cveLocalidad, cveIdioma, cveRol, cveProceso, version, cveNodo,
				secuenciaNodo},
				RetMsg.class);

	}

	/** 
	 * {@inheritDoc}
	 * @see com.briomax.briobpm.persistence.dao.base.IEjecucionActividadRepository#leeReglasActividad(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.math.BigDecimal, java.lang.String, java.lang.Integer)
	 */
	@Override
	public DAORet<List<LeeReglasActividad>, RetMsg> leeReglasActividad(String cveUsuario, String cveEntidad,
		String cveLocalidad, String cveIdioma, String cveRol, String cveProceso, BigDecimal version,
		String cveInstancia, String cveNodo, Integer idNodo, Integer secNodo) throws BrioBPMException {
		log.debug("\t\t <DATABASE> EXEC SP_LEE_REGLAS_ACTIVIDAD '{}', '{}', '{}', '{}', '{}', '{}', {}, '{}', '{}'," +
			" {}, {}, @CH_TIPO_EXCEPCION OUT, @CH_MENSAJE OUT ", cveUsuario, cveEntidad, cveLocalidad,
			cveIdioma, cveRol, cveProceso, version, cveInstancia, cveNodo, idNodo, secNodo);
		entityManager.clear();
		return executeNamedStored("leeReglasActividad",
			new String[] {"CH_CVE_USUARIO", "CH_CVE_ENTIDAD", "CH_CVE_LOCALIDAD", "CH_CVE_IDIOMA", "CH_ROL",
				"CH_CVE_PROCESO", "C_VERSION", "CH_CVE_INSTANCIA", "CH_CVE_NODO", "I_ID_NODO", "I_SECUENCIA_NODO"},
			new Object[] {cveUsuario, cveEntidad, cveLocalidad, cveIdioma, cveRol, cveProceso, version, cveInstancia,
				cveNodo, idNodo, secNodo},
				RetMsg.class);
	}

}
