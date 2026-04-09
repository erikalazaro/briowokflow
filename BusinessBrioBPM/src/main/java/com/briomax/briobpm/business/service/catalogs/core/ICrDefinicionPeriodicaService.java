package com.briomax.briobpm.business.service.catalogs.core;

import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.transferobjects.PdfGridTO;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;
import com.briomax.briobpm.transferobjects.repse.CorreoTO;
import com.briomax.briobpm.transferobjects.repse.GuardaCorreoTO;

/**
 * El objetivo de la Interface ICrDefinicionPeriodicaService.java es ...
 * IRepseHelper
 * @author Sara Ventura
 * @version 1.0 Fecha de creacion May 09, 2024 4:11:43 PM Modificaciones:
 * @since JDK 1.8
 */
public interface ICrDefinicionPeriodicaService{

	PdfGridTO getTabDetProcesos(DatosAutenticacionTO session, CorreoTO cveProceso)throws BrioBPMException;
	
	RetMsg guardaCorreo(DatosAutenticacionTO session, GuardaCorreoTO correo) throws BrioBPMException;
}
