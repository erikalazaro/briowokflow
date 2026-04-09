package com.briomax.briobpm.business.helpers.base;

import java.util.List;


import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.persistence.entity.namedquery.LeeCorreosPorEnviar;


/**
 * El objetivo de la Interface INodoHelper.java es ...
 * 
 * @author Pamela Rodriguez
 * @version 1.0 Fecha de creacion julio 16, 2024 1:13:58 PM Modificaciones:
 * @since JDK 11
 */
public interface ICorreosHelper {
	// SP_LEE_CORREOS_POR_ENVIAR
	/**
	 * Recuperar los correos por enviar.
	 * 
	 * @param session Datos de autenticación del usuario..
	 * @return EstatusVariablesTO con el resultado de la creación del folio.
	 * @throws BrioBPMException En caso de error durante la creación del folio.
     */
	public List<LeeCorreosPorEnviar>  leeCorreos( ) throws BrioBPMException;
}
