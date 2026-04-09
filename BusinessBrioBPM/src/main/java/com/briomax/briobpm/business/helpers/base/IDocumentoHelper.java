package com.briomax.briobpm.business.helpers.base;

import java.text.ParseException;

import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
/**
 * El objetivo de la Class FechaHelperTest.java es ...
 * 
 * @author Pamela Rodriguez
 * @version 1.0 Fecha de creacion Agos 13, 2024 11:50:00 AM Modificaciones:
 * @since JDK 11
 */
public interface IDocumentoHelper {
	//SP_VENCIMIENTO_DOCUMENTOS
	RetMsg vencimientoDocumentos()throws BrioBPMException, ParseException;
}
