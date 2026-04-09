package com.briomax.briobpm.business.helpers.base;

import java.text.ParseException;
import java.util.List;

import com.briomax.briobpm.common.core.DAORet;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.persistence.entity.namedquery.LeeReglasActividad;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;
import com.briomax.briobpm.transferobjects.in.NodoTO;


public interface IReglaActividadHelper  {
	
	//SP_LEE_REGLAS_ACTIVIDAD
	public DAORet<List<LeeReglasActividad>, RetMsg>  leeReglasActividad(DatosAutenticacionTO session, NodoTO nodo) 
			throws BrioBPMException, ParseException ;

}
