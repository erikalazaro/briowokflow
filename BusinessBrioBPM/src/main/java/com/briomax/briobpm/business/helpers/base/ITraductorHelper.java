package com.briomax.briobpm.business.helpers.base;

import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;

/**
 * El objetivo de la Interface IInFolioNodoHelper.java es ...
 * 
 * @author Sara Ventura
 * @version 1.0 Fecha de creacion 15/03/2024
 * @since JDK 1.8
 */
public interface ITraductorHelper {

	// SP_CALCULA_FECHA_LIMITE
	/**
	 * Traduce el texto
	 * @param session
	 * @param texto
	 * @return
	 * @throws BrioBPMException
	 */
	String getTraducce(DatosAutenticacionTO session, String texto) throws BrioBPMException;

	/**
	 * Traduce el texto
	 * @param cveEntidad
	 * @param cveIdioma
	 * @param texto
	 * @return
	 * @throws BrioBPMException
	 */
	String getTraducce(String cveEntidad, String cveIdioma, String texto) throws BrioBPMException;
}
