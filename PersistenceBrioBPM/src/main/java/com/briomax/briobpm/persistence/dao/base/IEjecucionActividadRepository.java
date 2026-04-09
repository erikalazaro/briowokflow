/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */
package com.briomax.briobpm.persistence.dao.base;

import java.math.BigDecimal;
import java.util.List;

import com.briomax.briobpm.common.core.DAORet;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.persistence.entity.namedquery.LeeColumnasSeccionOcu;
import com.briomax.briobpm.persistence.entity.namedquery.LeeEtiquetasCatalogo;
import com.briomax.briobpm.persistence.entity.namedquery.LeeEtiquetasTabla;
import com.briomax.briobpm.persistence.entity.namedquery.LeeInfSeccionOcu;
import com.briomax.briobpm.persistence.entity.namedquery.LeeMensajesReglas;
import com.briomax.briobpm.persistence.entity.namedquery.LeeReglasActividad;
import com.briomax.briobpm.persistence.entity.namedquery.LeeSeccionesNodo;
import com.briomax.briobpm.persistence.entity.namedquery.LeeTareasNodo;
import com.briomax.briobpm.persistence.entity.namedquery.LeeVariablesSeccion;

/**
 * El objetivo de la Interface IEjecucionActividadRepository.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion 26/01/2020 04:04:39 PM Modificaciones:
 * @since JDK 1.8
 */
public interface IEjecucionActividadRepository {

	/**
	 * Lee secciones nodo.
	 * @param cveUsuario el cve usuario.
	 * @param cveEntidad el cve entidad.
	 * @param cveLocalidad el cve localidad.
	 * @param cveIdioma el cve idioma.
	 * @param cveProceso el cve proceso.
	 * @param version el version.
	 * @param cveNodo el cve nodo.
	 * @param idNodo el id nodo.
	 * @param banSalTabTmp el ban sal tab tmp.
	 * @return el DAO ret.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	public DAORet<List<LeeSeccionesNodo>, RetMsg> leeSeccionesNodo(String cveUsuario, String cveEntidad,
		String cveLocalidad, String cveIdioma, String cveProceso, BigDecimal version, String cveNodo,
		Integer idNodo, String banSalTabTmp) throws BrioBPMException;
	
	/**
	 * Lee columnas seccion ocu.
	 * @param cveUsuario el cve usuario.
	 * @param cveEntidad el cve entidad.
	 * @param cveLocalidad el cve localidad.
	 * @param cveIdioma el cve idioma.
	 * @param cveRol el cve rol.
	 * @param cveProceso el cve proceso.
	 * @param version el version.
	 * @param cveNodo el cve nodo.
	 * @param idNodo el id nodo.
	 * @param cveSeccion el cve seccion.
	 * @return el DAO ret.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	public DAORet<List<LeeColumnasSeccionOcu>, RetMsg> leeColumnasSeccionOcu(String cveUsuario, String cveEntidad,
		String cveLocalidad, String cveIdioma, String cveRol, String cveProceso, BigDecimal version, String cveNodo,
		Integer idNodo, String cveSeccion) throws BrioBPMException;

	/**
	 * Lee etiquetas catalogo.
	 * @param cveUsuario el cve usuario.
	 * @param cveEntidad el cve entidad.
	 * @param cveLocalidad el cve localidad.
	 * @param cveIdioma el cve idioma.
	 * @param cveProceso el cve proceso.
	 * @param version el version.
	 * @param cveInstancia el cve instancia.
	 * @param cveNodo el cve nodo.
	 * @param idNodo el id nodo.
	 * @param secuenciaNodo el secuencia nodo.
	 * @param cveDato el cve dato.
	 * @return el DAO ret.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	public DAORet<List<LeeEtiquetasCatalogo>, RetMsg> leeEtiquetasCatalogo(String cveUsuario, String cveEntidad,
		String cveLocalidad, String cveIdioma, String cveProceso, BigDecimal version, String cveInstancia, String cveNodo,
		Integer idNodo, Integer secuenciaNodo, String cveDato) throws BrioBPMException;

	/**
	 * Lee etiquetas tabla.
	 * @param cveUsuario el cve usuario.
	 * @param cveEntidad el cve entidad.
	 * @param cveLocalidad el cve localidad.
	 * @param cveIdioma el cve idioma.
	 * @param cveProceso el cve proceso.
	 * @param version el version.
	 * @param cveInstancia el cve instancia.
	 * @param cveNodo el cve nodo.
	 * @param idNodo el id nodo.
	 * @param idSecuenciaNodo el id secuencia nodo.
	 * @param cveDato el cve dato.
	 * @param valorLista el valor lista.
	 * @param descripcionLista el descripcion lista.
	 * @param tablaLista el tabla lista.
	 * @param whereLista el where lista.
	 * @return el DAO ret.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	public DAORet<List<LeeEtiquetasTabla>, RetMsg> leeEtiquetasTabla(String cveUsuario, String cveEntidad,
		String cveLocalidad, String cveIdioma, String cveProceso, BigDecimal version, String cveInstancia, String cveNodo,
		Integer idNodo, Integer idSecuenciaNodo, String cveDato, String valorLista, String descripcionLista,
		String tablaLista, String whereLista) throws BrioBPMException;

	/**
	 * Lee inf seccion ocu.
	 * @param cveUsuario el cve usuario.
	 * @param cveEntidad el cve entidad.
	 * @param cveLocalidad el cve localidad.
	 * @param cveIdioma el cve idioma.
	 * @param cveRol el cve rol.
	 * @param cveProceso el cve proceso.
	 * @param version el version.
	 * @param cveInstancia el cve instancia.
	 * @param cveNodo el cve nodo.
	 * @param idNodo el id nodo.
	 * @param cveSeccion el cve seccion.
	 * @return el DAO ret.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	public DAORet<List<LeeInfSeccionOcu>, RetMsg> leeInfSeccionOcu(String cveUsuario, String cveEntidad,
		String cveLocalidad, String cveIdioma, String cveRol, String cveProceso, BigDecimal version, String cveInstancia,
		String cveNodo, Integer idNodo, String cveSeccion) throws BrioBPMException;

	/**
	 * Lee variables seccion.
	 * @param cveUsuario el cve usuario.
	 * @param cveEntidad el cve entidad.
	 * @param cveLocalidad el cve localidad.
	 * @param cveIdioma el cve idioma.
	 * @param cveProceso el cve proceso.
	 * @param version el version.
	 * @param cveInstancia el cve instancia.
	 * @param cveNodo el cve nodo.
	 * @param idNodo el id nodo.
	 * @param secuenciaNodo el secuencia nodo.
	 * @param cveSeccion el cve seccion.
	 * @param banGenTabTmp el ban gen tab tmp.
	 * @return el DAO ret.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	public DAORet<List<LeeVariablesSeccion>, RetMsg> leeVariablesSeccion(String cveUsuario, String cveEntidad,
		String cveLocalidad, String cveIdioma, String cveProceso, BigDecimal version, String cveInstancia, String cveNodo,
		Integer idNodo, Integer secuenciaNodo, String cveSeccion, String banGenTabTmp) throws BrioBPMException;

	/**
	 * Guarda variables seccion.
	 * @param cveUsuario el cve usuario.
	 * @param cveEntidad el cve entidad.
	 * @param cveLocalidad el cve localidad.
	 * @param cveIdioma el cve idioma.
	 * @param cveRol el cve rol.
	 * @param cveProceso el cve proceso.
	 * @param version el version.
	 * @param cveInstancia el cve instancia.
	 * @param cveNodo el cve nodo.
	 * @param idNodo el id nodo.
	 * @param cveSeccion el cve seccion.
	 * @param numOcurrencia el num ocurrencia.
	 * @param priOcurrencia el pri ocurrencia.
	 * @param nueva el nueva.
	 * @param datosOcurrencia el datos ocurrencia.
	 * @return el ret msg.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	public RetMsg guardaVariablesSeccion(String cveUsuario, String cveEntidad, String cveLocalidad, String cveIdioma,
		String cveRol, String cveProceso, BigDecimal version, String cveInstancia, String cveNodo, Integer idNodo,
		String cveSeccion, Integer numOcurrencia, String priOcurrencia, String nueva, String datosOcurrencia)
		throws BrioBPMException;

	/**
	 * Termina actividad.
	 * @param cveUsuario el cve usuario.
	 * @param cveEntidad el cve entidad.
	 * @param cveLocalidad el cve localidad.
	 * @param cveIdioma el cve idioma.
	 * @param cveProceso el cve proceso.
	 * @param version el version.
	 * @param cveInstancia el cve instancia.
	 * @param cveNodo el cve nodo.
	 * @param idNodo el id nodo.
	 * @param secuenciaNodo el secuencia nodo.
	 * @return el ret msg.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	public RetMsg terminaActividad(String cveUsuario, String cveEntidad,
		String cveLocalidad, String cveIdioma, String cveProceso, BigDecimal version, String cveInstancia, String cveNodo,
		Integer idNodo, Integer secuenciaNodo) throws BrioBPMException;

	/**
	 * Lee tareas nodo.
	 * @param cveUsuario el cve usuario.
	 * @param cveEntidad el cve entidad.
	 * @param cveLocalidad el cve localidad.
	 * @param cveIdioma el cve idioma.
	 * @param cveProceso el cve proceso.
	 * @param version el version.
	 * @param cveInstancia el cve instancia.
	 * @param cveNodo el cve nodo.
	 * @param idNodo el id nodo.
	 * @param secuenciaNodo el secuencia nodo.
	 * @param cveSeccion el cve seccion.
	 * @param banGenTabTmp el ban gen tab tmp.
	 * @return el DAO ret.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	public DAORet<List<LeeTareasNodo>, RetMsg> leeTareasNodo(String cveUsuario, String cveEntidad, String cveLocalidad,
		String cveIdioma, String cveProceso, BigDecimal version, String cveInstancia, String cveNodo, Integer idNodo,
		Integer secuenciaNodo, String cveSeccion, String banGenTabTmp) throws BrioBPMException;

	/**
	 * Guarda tareas seccion.
	 * @param cveUsuario el cve usuario.
	 * @param cveEntidad el cve entidad.
	 * @param cveLocalidad el cve localidad.
	 * @param cveIdioma el cve idioma.
	 * @param cveProceso el cve proceso.
	 * @param version el version.
	 * @param cveInstancia el cve instancia.
	 * @param cveNodo el cve nodo.
	 * @param idNodo el id nodo.
	 * @param secuenciaNodo el secuencia nodo.
	 * @param secuenciaCompletada el Secuencia Completada.
	 * @return el ret msg.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	public RetMsg guardaTareasSeccion(String cveUsuario, String cveEntidad, String cveLocalidad, String cveIdioma,
		String cveProceso, BigDecimal version, String cveInstancia, String cveNodo, Integer idNodo, int secuenciaNodo,
		String secuenciaCompletada) throws BrioBPMException;

	/**
	 * Cambia situacion actividad.
	 * @param cveUsuario el cve usuario.
	 * @param cveEntidad el cve entidad.
	 * @param cveLocalidad el cve localidad.
	 * @param cveIdioma el cve idioma.
	 * @param cveProceso el cve proceso.
	 * @param version el version.
	 * @param cveInstancia el cve instancia.
	 * @param cveNodo el cve nodo.
	 * @param idNodo el id nodo.
	 * @param secuenciaNodo el secuencia nodo.
	 * @param accion el accion.
	 * @return el ret msg.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	public RetMsg cambiaSituacionActividad(String cveUsuario, String cveEntidad, String cveLocalidad, String cveIdioma,
		String cveProceso, BigDecimal version, String cveInstancia, String cveNodo, Integer idNodo,
		Integer secuenciaNodo, String accion) throws BrioBPMException;

	/**
	 * Lee mensajes reglas.
	 * @param cveUsuario el cve usuario.
	 * @param cveEntidad el cve entidad.
	 * @param cveLocalidad el cve localidad.
	 * @param cveIdioma el cve idioma.
	 * @param cveRol el cve rol.
	 * @param cveProceso el cve proceso.
	 * @param version el version.
	 * @param cveNodo el cve nodo.
	 * @param idNodo el ID nodo.
	 * @return el DAO ret.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	public DAORet<List<LeeMensajesReglas>, RetMsg> leeMensajesReglas(String cveUsuario, String cveEntidad,
		String cveLocalidad, String cveIdioma, String cveRol, String cveProceso, BigDecimal version, String cveNodo,
		Integer idNodo) throws BrioBPMException;

	/**
	 * Lee reglas actividad.
	 * @param cveUsuario el cve usuario.
	 * @param cveEntidad el cve entidad.
	 * @param cveLocalidad el cve localidad.
	 * @param cveIdioma el cve idioma.
	 * @param cveRol el cve rol.
	 * @param cveProceso el cve proceso.
	 * @param version el version.
	 * @param cveNodo el cve nodo.
	 * @param cveInstancia el cve instancia.
	 * @param idNodo el Id nodo.
	 * @param secNodo el sec nodo.
	 * @return el DAO ret.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	public DAORet<List<LeeReglasActividad>, RetMsg> leeReglasActividad(String cveUsuario, String cveEntidad,
		String cveLocalidad, String cveIdioma, String cveRol, String cveProceso, BigDecimal version,
		String cveInstancia, String cveNodo, Integer idNodo, Integer secNodo) throws BrioBPMException;

}
