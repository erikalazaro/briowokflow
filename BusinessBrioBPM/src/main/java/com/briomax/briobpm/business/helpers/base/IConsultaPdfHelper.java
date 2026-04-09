/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.business.helpers.base;

import java.util.Date;
import java.util.List;

import com.briomax.briobpm.common.core.DAORet;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.persistence.entity.CrPdfFiles;
import com.briomax.briobpm.transferobjects.PdfGridTO;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;
import com.briomax.briobpm.transferobjects.repse.ConsultaPdfTO;

/**
 * El objetivo de la Class IConsultaPdfHelper.java es ...
 * 
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Nov 12, 2024 6:59:06 PM Modificaciones:
 * @since JDK 1.8
 */
public interface IConsultaPdfHelper {

	PdfGridTO execEnvioRecibosNomina(DatosAutenticacionTO session, Date fechaLimiteInferior, Date fechaLimiteSuperior, String rfc)
	        throws BrioBPMException;

	PdfGridTO execDeclaracionProvicionalContratista(DatosAutenticacionTO session, Date fechaLimiteInferior, Date fechaLimiteSuperior, String rfc)
	        throws BrioBPMException;

	PdfGridTO execRegistroObra(DatosAutenticacionTO session, Date fechaLimiteInferior, Date fechaLimiteSuperior, String rfc)
	        throws BrioBPMException;

	PdfGridTO execComprobanteCuotaOP(DatosAutenticacionTO session, Date fechaLimiteInferior, Date fechaLimiteSuperior, String rfc)
	        throws BrioBPMException;

	PdfGridTO execFormatoCuotaOP(DatosAutenticacionTO session, Date fechaLimiteInferior, Date fechaLimiteSuperior, String rfc)
			throws BrioBPMException;
	
	PdfGridTO execSUA(DatosAutenticacionTO session, Date fechaLimiteInferior, Date fechaLimiteSuperior, String rfc)
	        throws BrioBPMException;

	PdfGridTO execAvisoRepse(DatosAutenticacionTO session, Date fechaLimiteInferior, Date fechaLimiteSuperior, String rfc)
	        throws BrioBPMException;

	PdfGridTO execDeclaracionProvicionalContratistaComplementario(DatosAutenticacionTO session, Date fechaLimiteInferior, Date fechaLimiteSuperior, String rfc)
	        throws BrioBPMException;

	PdfGridTO execPagoBancario(DatosAutenticacionTO session, Date fechaLimiteInferior, Date fechaLimiteSuperior, String rfc)
	        throws BrioBPMException;

	PdfGridTO execAvisoSisub(DatosAutenticacionTO userSession, Date fechaInicio, Date fechaFin, String rfc);

	PdfGridTO execAvisoIcsoe(DatosAutenticacionTO userSession, Date fechaInicio, Date fechaFin, String rfc);
	
	List<Date> obtieneRangoFechas(Date fechaLimiteInferior, Date fechaLimiteSuperior);
	
	DAORet<CrPdfFiles, RetMsg> leeDocumentoBinario(DatosAutenticacionTO session, ConsultaPdfTO datos) throws BrioBPMException;
	
}

