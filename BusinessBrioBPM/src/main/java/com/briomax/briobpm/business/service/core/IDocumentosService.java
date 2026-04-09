/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados. Este software contiene informacion
 * confidencial propiedad de Briomax Consulting por lo cual no puede ser reproducido, distribuido o alterado sin el
 * consentimiento previo de Briomax Consulting.
 */

package com.briomax.briobpm.business.service.core;

import java.util.List;

import com.briomax.briobpm.common.core.DAORet;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;
import com.briomax.briobpm.transferobjects.in.DocumentoTO;

/**
 * El objetivo de la Interface IDocumentosService.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Mar 9, 2020 11:39:08 AM Modificaciones:
 * @since JDK 1.8
 */
public interface IDocumentosService {


	/**
	 * Borra documento binario nodo.
	 * @param session el session.
	 * @param document el document.
	 * @return el ret msg.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	public RetMsg borraDocumentoBinarioNodo(DatosAutenticacionTO session, DocumentoTO document) throws BrioBPMException;

	/**
	 * Guarda un documento binario en un nodo.
	 * @param session la sesión de autenticación.
	 * @param document la lista de documentos a guardar.
	 * @return un objeto RetMsg que contiene el resultado de la operación.
	 * @throws BrioBPMException si ocurre un error durante la operación.
	 */
	public RetMsg guardaDocumentoBinarioNodo(DatosAutenticacionTO session, List<DocumentoTO> document)
	    throws BrioBPMException;

	/**
	 * Guarda un documento binario en múltiples nodos.
	 * @param session la sesión de autenticación.
	 * @param document el documento a guardar.
	 * @return un objeto RetMsg que contiene el resultado de la operación.
	 * @throws BrioBPMException si ocurre un error durante la operación.
	 */
	RetMsg guardaDocumentoBinarioNodoMultiple(DatosAutenticacionTO session, DocumentoTO document)
	        throws BrioBPMException;

	/**
	 * Crea un nuevo renglón para la entidad especificada.
	 * @param cveEntidad la clave de la entidad.
	 * @param documento el documento asociado al nuevo renglón.
	 * @return un objeto RetMsg que contiene el resultado de la operación.
	 */
	RetMsg creaNuevoRenglon(String cveEntidad, DocumentoTO documento);
	
	RetMsg eliminarRenglon(String cveEntidad, DocumentoTO documentoTO);

	/**
	 * Lee nombre documento.
	 * @param session el session.
	 * @param document el document.
	 * @return el DAO ret.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	List<String> leeNombreDocumentos(DatosAutenticacionTO session, DocumentoTO document);
	
	/**
	 * Lee documento binario nodo.
	 * @param session el session.
	 * @param document el document.
	 * @return el DAO ret.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	DAORet<DocumentoTO, RetMsg> leeDocumentoBinarioNodo(DatosAutenticacionTO session, DocumentoTO document)
			throws BrioBPMException;
}
