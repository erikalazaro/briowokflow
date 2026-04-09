package com.briomax.briobpm.business.service.catadaptaciones.core;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.transferobjects.ComboBoxTO;
import com.briomax.briobpm.transferobjects.in.CrConsultaRepseTO;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;
import com.briomax.briobpm.transferobjects.repse.ConsultaPdfTO;
import com.briomax.briobpm.transferobjects.repse.FiltroValidaRepseTO;

/**
 * El objetivo de la Interface ICrDefinicionPeriodicaService.java es ...
 * IRepseHelper
 * @author Sara Ventura
 * @version 1.0 Fecha de creacion May 09, 2024 4:11:43 PM Modificaciones:
 * @since JDK 1.8
 */
public interface ICrConsultaRepseService{
	
	List<ComboBoxTO> getRfcProcesosPeriodicos(DatosAutenticacionTO session, ConsultaPdfTO filtro) throws BrioBPMException;
	
	List<CrConsultaRepseTO> consultarRegistros(DatosAutenticacionTO session, String razonSocial,
			String resultadoColumna, Date fecha) throws BrioBPMException;

    // Método para consultar registros por razon social
    List<CrConsultaRepseTO> consultaTodosRegistros();
    
    RetMsg correEjecutablePython (String data) throws BrioBPMException, IOException, InterruptedException;

    /**
     * 
     * Valida en line el proceso de repse
     * @param session
     * @param razonSocial
     * @return
     * @throws BrioBPMException
     * @throws IOException
     * @throws InterruptedException
     */
	RetMsg validaRepseLinea(DatosAutenticacionTO session, FiltroValidaRepseTO datos)
			throws BrioBPMException, IOException, InterruptedException;
    
}
