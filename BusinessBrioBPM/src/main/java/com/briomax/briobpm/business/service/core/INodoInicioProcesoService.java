package com.briomax.briobpm.business.service.core;

import com.briomax.briobpm.transferobjects.InNodoProcesoTO;
import com.briomax.briobpm.transferobjects.NodoInicioTO;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;

public interface INodoInicioProcesoService {
	
	public NodoInicioTO leeNodoInicial(DatosAutenticacionTO session, InNodoProcesoTO notoProceso);

}
