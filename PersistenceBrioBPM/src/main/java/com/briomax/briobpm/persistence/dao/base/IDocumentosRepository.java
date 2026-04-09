/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */
package com.briomax.briobpm.persistence.dao.base;

/**
 * El objetivo de la Interface IEjecucionActividadRepository.java es ...
 * 
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion 26/01/2020 04:04:39 PM Modificaciones:
 * @since JDK 1.8
 */
public interface IDocumentosRepository {
	/*
		*//**
			 * Lee documentos nodo.
			 * 
			 * @param cveUsuario    el cve usuario.
			 * @param cveEntidad    el cve entidad.
			 * @param cveLocalidad  el cve localidad.
			 * @param cveIdioma     el cve idioma.
			 * @param cveProceso    el cve proceso.
			 * @param version       el version.
			 * @param cveInstancia  el cve instancia.
			 * @param cveNodo       el cve nodo.
			 * @param idNodo        el id nodo.
			 * @param secuenciaNodo el secuencia nodo.
			 * @param cveSeccion    el cve seccion.
			 * @param banGenTabTmp  el ban gen tab tmp.
			 * @return el DAO ret.
			 * @throws BrioBPMException la brio BPM exception.
			 */
	/*
	 * public DAORet<List<LeeDocumentosNodo>, RetMsg> leeDocumentosNodo(String
	 * cveUsuario, String cveEntidad, String cveLocalidad, String cveIdioma, String
	 * cveProceso, BigDecimal version, String cveInstancia, String cveNodo, Integer
	 * idNodo, Integer secuenciaNodo, String cveSeccion, String banGenTabTmp) throws
	 * BrioBPMException;
	 * 
	 *//**
		 * Lee documento binario nodo.
		 * 
		 * @param cveUsuario   el cve usuario.
		 * @param cveEntidad   el cve entidad.
		 * @param cveLocalidad el cve localidad.
		 * @param cveIdioma    el cve idioma.
		 * @param cveProceso   el cve proceso.
		 * @param version      el version.
		 * @param cveInstancia el cve instancia.
		 * @param cveNodo      el cve nodo.
		 * @param idNodo       el id nodo.
		 * @param secNodo      el sec nodo.
		 * @param secDoc       el id doc.
		 * @return el documento.
		 * @throws BrioBPMException la brio BPM exception.
		 */
	/*
	 * public Documento leeDocumentoBinarioNodo(String cveUsuario, String
	 * cveEntidad, String cveLocalidad, String cveIdioma, String cveProceso,
	 * BigDecimal version, String cveInstancia, String cveNodo, Integer idNodo,
	 * Integer secNodo, Integer secDoc) throws BrioBPMException;
	 * 
	 *//**
		 * Borra documento binario nodo.
		 * 
		 * @param cveUsuario   el cve usuario.
		 * @param cveEntidad   el cve entidad.
		 * @param cveLocalidad el cve localidad.
		 * @param cveIdioma    el cve idioma.
		 * @param cveProceso   el cve proceso.
		 * @param version      el version.
		 * @param cveInstancia el cve instancia.
		 * @param cveNodo      el cve nodo.
		 * @param idNodo       el id nodo.
		 * @param secNodo      el sec nodo.
		 * @param secDoc       el id doc.
		 * @return el ret msg.
		 * @throws BrioBPMException la brio BPM exception.
		 */
	/*
	 * public RetMsg borraDocumentoBinarioNodo(String cveUsuario, String cveEntidad,
	 * String cveLocalidad, String cveIdioma, String cveProceso, BigDecimal version,
	 * String cveInstancia, String cveNodo, Integer idNodo, Integer secNodo, Integer
	 * secDoc) throws BrioBPMException;
	 * 
	 *//**
		 * Guarda documento binario nodo.
		 * 
		 * @param cveUsuario   el cve usuario.
		 * @param cveEntidad   el cve entidad.
		 * @param cveLocalidad el cve localidad.
		 * @param cveIdioma    el cve idioma.
		 * @param cveProceso   el cve proceso.
		 * @param version      el version.
		 * @param cveInstancia el cve instancia.
		 * @param cveNodo      el cve nodo.
		 * @param idNodo       el id nodo.
		 * @param secNodo      el sec nodo.
		 * @param secDoc       el id doc.
		 * @param nomArchivo   el nom archivo.
		 * @param arcBinario   el arc binario.
		 * @param contenType   el conten type.
		 * @return el ret msg.
		 * @throws BrioBPMException la brio BPM exception.
		 *//*
			 * public RetMsg guardaDocumentoBinarioNodo(String cveUsuario, String
			 * cveEntidad, String cveLocalidad, String cveIdioma, String cveProceso,
			 * BigDecimal version, String cveInstancia, String cveNodo, Integer idNodo,
			 * Integer secNodo, Integer secDoc, String nomArchivo, byte[] arcBinario, String
			 * contenType) throws BrioBPMException;
			 */

}
