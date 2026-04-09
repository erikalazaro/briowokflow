package com.briomax.briobpm.business.helpers.base;

import java.util.List;

import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;
import com.briomax.briobpm.transferobjects.in.StVariableDependienteInTO;

public interface IStVariableDependienteHelper {
	
	public List<List<String>> obtenerVariable(DatosAutenticacionTO userSession, StVariableDependienteInTO in) throws BrioBPMException;

}
