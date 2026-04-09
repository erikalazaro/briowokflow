/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.persistence.dao;

import org.springframework.stereotype.Repository;

import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.persistence.dao.base.AbstractBaseDAO;
import com.briomax.briobpm.persistence.dao.base.ITemporizadorDAO;

import lombok.extern.slf4j.Slf4j;

/**
 * El objetivo de la Class TemporizadorDAO.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Oct 12, 2020 7:31:44 PM Modificaciones:
 * @since JDK 1.8
 */
@Repository("temporizadorDAO")
@Slf4j
public class TemporizadorDAO extends AbstractBaseDAO implements ITemporizadorDAO {

	/** 
	 * {@inheritDoc}
	 * @see com.briomax.briobpm.persistence.dao.base.ITemporizadorDAO#actividades()
	 */
	@Override
	public RetMsg actividades() throws BrioBPMException {
		RetMsg mensaje = null;
		log.debug("\t\t <DATABASE> EXEC SP_TEMPORIZADOR_ACTIVIDADES @CH_TIPO_EXCEPCION, @CH_MENSAJE");
		
		/*return executeNoResulsetStored("SP_TEMPORIZADOR_ACTIVIDADES",
			new String[] {"CH_TIPO_EXCEPCION", "CH_MENSAJE"},
			new Class[] { String.class, String.class},
			new ParameterMode[] {ParameterMode.OUT, ParameterMode.OUT},
			new Object[] {},
			RetMsg.class);*/
		return null;
	}

	/** 
	 * {@inheritDoc}
	 * @see com.briomax.briobpm.persistence.dao.base.ITemporizadorDAO#vencimientoDocumentos()
	 */
	@Override
	public RetMsg vencimientoDocumentos() throws BrioBPMException {
		log.trace("\t\t <DATABASE> EXEC SP_VENCIMIENTO_DOCUMENTOS @CH_TIPO_EXCEPCION, @CH_MENSAJE");
		/*return executeNoResulsetStored("SP_VENCIMIENTO_DOCUMENTOS",
			new String[] {"CH_TIPO_EXCEPCION", "CH_MENSAJE"},
			new Class[] {String.class, String.class},
			new ParameterMode[] {ParameterMode.OUT, ParameterMode.OUT},
			new Object[] { },
			RetMsg.class);*/
		return null;
	}

	/** 
	 * {@inheritDoc}
	 * @see com.briomax.briobpm.persistence.dao.base.ITemporizadorDAO#procesaMensajes()
	 */
	@Override
	public RetMsg procesaMensajes() throws BrioBPMException {
		log.trace("\t\t <DATABASE> EXEC SP_PROCESA_MENSAJES @CH_TIPO_EXCEPCION, @CH_MENSAJE");
		/*return executeNoResulsetStored("SP_PROCESA_MENSAJES",
			new String[] {"CH_TIPO_EXCEPCION", "CH_MENSAJE"},
			new Class[] {String.class, String.class},
			new ParameterMode[] {ParameterMode.OUT, ParameterMode.OUT},
			new Object[] { },
			RetMsg.class);*/
		return null;
	}

}
