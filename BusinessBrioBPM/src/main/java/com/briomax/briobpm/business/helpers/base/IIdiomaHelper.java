package com.briomax.briobpm.business.helpers.base;

import java.util.List;

import com.briomax.briobpm.common.core.DAORet;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.persistence.entity.namedquery.LeeIdiomas;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;
import com.briomax.briobpm.transferobjects.in.EstatusTO;

/**
 * El objetivo de la Interface IIdiomaHelper.java es ...
 * 
 * @author Jose Corona
 * @version 1.0 Fecha de creacion Jul 05, 2024 2:23: PM Modificaciones:
 * @since JDK 11
 */

public interface IIdiomaHelper {
	// SP_LEE_IDIOMAS
	/**
	 * RECUPERA LA LISTA DE IDIOMAS DEL SISTEMA.
	 * 
	 * @param session Datos de autenticación del usuario.
	 * @return EstatusTO con el resultado de la creación del folio.
	 * @throws BrioBPMException En caso de error durante la creación del folio.
	 */
	public DAORet<List<LeeIdiomas>, RetMsg>  leeIdioma(DatosAutenticacionTO session) throws BrioBPMException;

	
	
}
