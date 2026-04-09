/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.business.helpers.base;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.common.exception.ConverterExcepcion;
import com.briomax.briobpm.transferobjects.CargaGridTO;
import com.briomax.briobpm.transferobjects.ComboBoxTO;
import com.briomax.briobpm.transferobjects.TrabajadoresTO;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;

/**
 * El objetivo de la Interface ITrabajadoresHelper.java es ...
 * @author Sara Ventura
 * @version 1.0 Fecha de creacion Feb 26, 2020 11:21:09 AM Modificaciones:
 * @since JDK 1.8
 */
public interface ITrabajadoresHelper {

	/**
	 * Trabajadores Actuales
	 * @param session
	 * @param rfc
	 * @param contrato
	 * @param instancia
	 * @param cveProceso
	 * @return
	 * @throws BrioBPMException
	 */
	List<TrabajadoresTO> getTrabajadorActual(DatosAutenticacionTO session, String rfc, String contrato, String instancia, String cveProceso) throws BrioBPMException;
	

	/**
	 * Trabajadores Historico
	 * @param session
	 * @param rfc
	 * @param contrato
	 * @param fecha
	 * @param cveProces
	 * @return
	 * @throws BrioBPMException
	 */
	List<TrabajadoresTO> getTrabajadorHistorico(DatosAutenticacionTO session, String rfc, String contrato, String fecha, String cveProces) throws BrioBPMException;

	/**
	 * 
	 * @param userSession
	 * @param file
	 * @param numberOfSheet
	 * @param contrato
	 * @return
	 * @throws IOException
	 * @throws ConverterExcepcion
	 * @throws BrioBPMException
	 */
	CargaGridTO upload(DatosAutenticacionTO userSession, InputStream file, Integer numberOfSheet, String contratoOrigen, String rfcOrigen)
			throws IOException, ConverterExcepcion, BrioBPMException;

	/**
	 * 
	 * @param userSession
	 * @param datosExcel
	 * @return
	 */
	String actualizaTrabajadores(DatosAutenticacionTO userSession, CargaGridTO datosExcel);

	/**
	 * Lista de Horas del historico
	 * @param session
	 * @param rfc
	 * @param contrato
	 * @param  cveProceso
	 * @return
	 * @throws BrioBPMException
	 */
	List<ComboBoxTO> getDiasHistorico(DatosAutenticacionTO session, String rfc, String contrato, String cveProceso)
			throws BrioBPMException;

}
