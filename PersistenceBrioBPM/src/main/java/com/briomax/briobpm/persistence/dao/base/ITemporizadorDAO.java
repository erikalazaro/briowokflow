/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.persistence.dao.base;

import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;

/**
 * El objetivo de la Interface ITemporizadorDAO.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Oct 12, 2020 7:28:49 PM Modificaciones:
 * @since JDK 1.8
 */
public interface ITemporizadorDAO {

	/**
	 * Actividades.
	 * @return el ret msg.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	public RetMsg actividades() throws BrioBPMException;

	/**
	 * Vencimiento documentos.
	 * @return el ret msg.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	public RetMsg vencimientoDocumentos() throws BrioBPMException;

	/**
	 * Procesa mensajes.
	 * @return el ret msg.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	public RetMsg procesaMensajes() throws BrioBPMException;


}
