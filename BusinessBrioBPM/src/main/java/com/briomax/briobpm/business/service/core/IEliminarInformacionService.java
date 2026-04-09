package com.briomax.briobpm.business.service.core;

import java.util.List;

import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.persistence.entity.ParametroGeneral;

/**
 * El objetivo de la Interface IEjecucionActividadService.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Mar 5, 2020 1:02:36 PM Modificaciones:
 * @since JDK 1.8
 */
public interface IEliminarInformacionService {
	
	public RetMsg eliminarProcesosPeriodicos() ;
	
	public RetMsg eliminarProcesoByCve(List<String> procesos) ;
	
	public List<String> getParametroGeneralRepository(String clave);

}
