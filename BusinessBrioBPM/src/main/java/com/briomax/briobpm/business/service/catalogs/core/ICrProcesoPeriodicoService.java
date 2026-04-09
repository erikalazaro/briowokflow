package com.briomax.briobpm.business.service.catalogs.core;

import java.util.List;

import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.transferobjects.ComboBoxTO;
import com.briomax.briobpm.transferobjects.PdfGridTO;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;
import com.briomax.briobpm.transferobjects.repse.ConsultaPdfTO;

/**
 * El objetivo de la Interface ICrDefinicionPeriodicaService.java es ...
 * IRepseHelper
 * @author Sara Ventura
 * @version 1.0 Fecha de creacion May 09, 2024 4:11:43 PM Modificaciones:
 * @since JDK 1.8
 */
public interface ICrProcesoPeriodicoService{
	
	List<ComboBoxTO> getProcesosPeriodicos(DatosAutenticacionTO session) throws BrioBPMException;

	List<ComboBoxTO> getProcesosPeriodicosByRfcContrato(DatosAutenticacionTO session, ConsultaPdfTO filtro)
			throws BrioBPMException;
	
	PdfGridTO getTabProcesos(DatosAutenticacionTO session)throws BrioBPMException;
	
}
