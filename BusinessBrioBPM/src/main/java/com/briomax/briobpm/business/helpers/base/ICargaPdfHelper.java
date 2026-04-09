/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.business.helpers.base;

import java.util.Date;

import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.transferobjects.PdfGridTO;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;
import com.briomax.briobpm.transferobjects.repse.ListaDocumentosTrabajadorTO;

/**
 * El objetivo de la Interface ICargaPdfHelper.java es ...
 * @author Sara Ventura
 * @version 1.0 Fecha de creacion Feb 26, 2020 11:21:09 AM Modificaciones:
 * @since JDK 1.8
 */
public interface ICargaPdfHelper {



	/**
	 * Ejecucion Envio Recibos Nomina
	 * @param session
	 * @param rfc
	 * @param contrato
	 * @param proceso
	 * @return
	 * @throws BrioBPMException
	 */
	
	PdfGridTO execEnvioRecibosNomina(DatosAutenticacionTO session, String rfc, String contrato, String proceso, String nombreArchivo, Date fechaCarga, String archivoPython)
			throws BrioBPMException;

	PdfGridTO execDeclaracionProvicionalContratista(DatosAutenticacionTO session, String rfc, String contrato, String proceso, String nombreArchivo, Date fechaCarga, String archivoPython)
			throws BrioBPMException;

	PdfGridTO execPagoBancario(DatosAutenticacionTO session, String rfc, String contrato, String proceso, String nombreArchivo, Date fechaCarga, String archivoPython)
			throws BrioBPMException;
	
	PdfGridTO execRegistroObra(DatosAutenticacionTO session, String rfc, String contrato, String proceso, String nombreArchivo, Date fechaCarga, String archivoPython)
			throws BrioBPMException;
	
	PdfGridTO execComprobanteCuotaOP(DatosAutenticacionTO session, String rfc, String contrato, String proceso, String nombreArchivo, Date fechaCarga, String archivoPython)
			throws BrioBPMException;
	
	PdfGridTO execFormatoCuotaOP(DatosAutenticacionTO session, String rfc, String contrato, String proceso, String nombreArchivo, Date fechaCarga, String archivoPython)
			throws BrioBPMException;
	
	PdfGridTO execSUA(DatosAutenticacionTO session, String rfc, String contrato, String cveProceso, String nombreArchivo, Date fechaCarga, String archivoPython)
			throws BrioBPMException;
	
	PdfGridTO execAvisoRepse(DatosAutenticacionTO session, String rfc, String contrato, String cveProceso, String nombreArchivo, Date fechaCarga, String archivoPython)
			throws BrioBPMException;
	
	PdfGridTO execDeclaracionProvicionalContratistaComplementario(DatosAutenticacionTO session, String rfc, String contrato, String cveProceso, String nombreArchivo, Date fechaCarga, String archivoPython)
			throws BrioBPMException;
	
	PdfGridTO execAvisoIcsoe(DatosAutenticacionTO session, String rfc, String contrato, String cveProceso, String nombreArchivo, Date fechaCarga, String archivoPython)
			throws BrioBPMException;
	
	PdfGridTO execAvisoSisub(DatosAutenticacionTO session, String rfc, String contrato, String cveProceso, String nombreArchivo, Date fechaCarga, String archivoPython)
			throws BrioBPMException;
	
	void moveFile(String file, String path) throws BrioBPMException;
	
	void deleteFile(String file);
	
	boolean  pythonExecutor (String scriptPath, String nombreArchivo);

	boolean validaCopiaArchivo(Object cellValue);
	
	boolean otorgaPermisos(String file);
	
	public boolean eliminaCarga(DatosAutenticacionTO session, ListaDocumentosTrabajadorTO listaDocumentos)
			throws BrioBPMException;

}

