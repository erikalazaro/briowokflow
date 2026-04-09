package com.briomax.briobpm.business.helpers.base;

import java.text.ParseException;

import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.persistence.entity.CrConsultaRepsePK;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * El objetivo de la Interface ITemporizadorHelper.java es ...
 * 
 * @author Sara Ventura
 * @version 1.0 Fecha de creacion 30 de Septiembre del 2024
 * @since JDK 1.8
 */
public interface ITemporizadorHelper {

	// SP_TEMPORIZADOR_ACTIVIDADES
	RetMsg temporizadorActivades() throws BrioBPMException, ParseException;
	
	/**
	 * Método para depuración de documentos Compliance REPSE.
	 */
	void depurarDocumentos() throws BrioBPMException;
	
    /**
     * Consulta REPSE por razon social.
     */
    void consultaRepse() throws BrioBPMException;
    /**
     * Consulta REPSE por razon social via CURL.
     */
    public String consultaRepsePorCurl(String razonSocial, boolean headless);
    
    /**
     * Consulta REPSE por razon social via CURL.
     */
    public void parseAndSaveRepseJson(DatosAutenticacionTO session, CrConsultaRepsePK pk, JsonNode root, String cveNodo, String cveInstancia);
	
    
    public RetMsg activadesPendientes() throws BrioBPMException , ParseException;
}
