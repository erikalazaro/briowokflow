/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.business.helpers.base;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.transferobjects.NodoInicioProcesoTO;
import com.briomax.briobpm.transferobjects.NodoInicioTO;
import com.briomax.briobpm.transferobjects.SaveSectionTO;
import com.briomax.briobpm.transferobjects.in.ActividadTO;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;
import com.briomax.briobpm.transferobjects.in.EstatusCreaInstanciaTO;
import com.briomax.briobpm.transferobjects.in.EstatusFolioTO;
import com.briomax.briobpm.transferobjects.in.EstatusTO;
import com.briomax.briobpm.transferobjects.in.ProcesoInicialTO;

/**
 * El objetivo de la Interface IProcesoHelper.java es ...
 * 
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Ene 31, 2024 4:11:43 PM Modificaciones:
 * @since JDK 1.8
 */
public interface IProcesoHelper {

	// SP_CREA_FOLIO_IN_PROCESO
	/**
	 * Crea un folio para el proceso.
	 * 
	 * @param session    Datos de autenticación del usuario.
	 * @param cveProceso Clave del proceso.
	 * @param version    Versión del proceso.
	 * @return EstatusFolioTO con el resultado de la creación del folio y estado del
	 *         proceso.
	 * @throws BrioBPMException En caso de error durante la creación del folio.
	 */
	EstatusFolioTO creaFolio(DatosAutenticacionTO session, String cveProceso, BigDecimal version)
			throws BrioBPMException;

	// SP_VAL_DATOS_ST_PROCESO
	/**
	 * Obtiene el estatus de la validación de datos para el proceso.
	 * 
	 * @param session      Datos de autenticación del usuario.
	 * @param claveProceso Clave del proceso.
	 * @param version      Versión del proceso.
	 * @return EstatusTO con el resultado de la validación de datos y estado del
	 *         proceso.
	 * @throws BrioBPMException En caso de error durante la validación de datos.
	 */
	EstatusTO getValidaDatos(DatosAutenticacionTO session, String claveProceso, BigDecimal version)
			throws BrioBPMException;

	// SP_INS_IN_PROCESO
	/**
	 * Inserta un proceso inicial.
	 * 
	 * @param session        Datos de autenticación del usuario.
	 * @param procesoInicial Datos del proceso inicial a insertar.
	 * @return EstatusTO con el resultado de la inserción del proceso y estado del
	 *         proceso.
	 * @throws BrioBPMException En caso de error durante la inserción del proceso.
	 */
	EstatusTO setInsInProceso(DatosAutenticacionTO session, ProcesoInicialTO procesoInicial) throws BrioBPMException;

	/**
	 * Crea variables asociadas a una instancia de proceso.
	 * 
	 * @param session        Datos de autenticación del usuario.
	 * @param claveInstancia Clave de la instancia de proceso.
	 * @param claveProceso   Clave del proceso.
	 * @param version        Versión del proceso.
	 * @param condicionInicializacio Parametro de  Condicion Inicializacio
	 * @return EstatusTO con el resultado de la creación de variables y estado del
	 *         proceso.
	 * @throws BrioBPMException En caso de error durante la creación de variables.
	 */
	EstatusTO setCreaVariables(DatosAutenticacionTO session, String claveInstancia, String claveProceso,
			BigDecimal version, String condicionInicializacio) throws BrioBPMException;
	
	/**
	 * Crea variables asociadas a una instancia de proceso.
	 * 
	 * @param session        Datos de autenticación del usuario.
	 * @param claveInstancia Clave de la instancia de proceso.
	 * @param claveProceso   Clave del proceso.
	 * @param version        Versión del proceso.
	 * @param condicionInicializacio Parametro de  Condicion Inicializacio
	 * @return EstatusTO con el resultado de la creación de variables y estado del
	 *         proceso.
	 * @throws BrioBPMException En caso de error durante la creación de variables.
	 */
	EstatusTO setCreaVariablesServicio(DatosAutenticacionTO session, String claveInstancia, String claveProceso,
			BigDecimal version, String condicionInicializacio) throws BrioBPMException;

	// SP_LEE_NODO_INICIO_PROCESO
	/**
	 * Lee el nodo de inicio de un proceso.
	 * 
	 * @param session                Datos de autenticación del usuario.
	 * @param nodoProceso            Información del nodo de inicio de proceso.
	 * @param origen                 Origen del proceso.
	 * @param valoresReferenciaEnvio Valores de referencia para el envío.
	 * @return NodoInicioTO con la información del nodo de inicio leído.
	 * @throws BrioBPMException En caso de error durante la lectura del nodo de
	 *                          inicio.
	 */
	NodoInicioTO leeNodoInicioProceso(DatosAutenticacionTO session, NodoInicioProcesoTO nodoProceso, String origen,
			String valoresReferenciaEnvio) throws BrioBPMException, ParseException;

	// SP_CREA_INSTANCIA_PROCESO revisar return
	/**
	 * Crea una instancia de proceso.
	 * 
	 * @param session                Datos de autenticación del usuario.
	 * @param instanciaNodo          Información de la instancia de proceso a crear.
	 * @param valoresReferenciaEnvio Valores de referencia para el envío.
	 * @param folioMensajeEnvio      Folio del mensaje de envío asociado.
	 * @return EstatusCreaInstanciaTO con el resultado de la creación de la
	 *         instancia y estado del proceso.
	 * @throws BrioBPMException En caso de error durante la creación de la instancia
	 *                          de proceso.
	 * @throws ParseException   En caso de error al parsear fechas.
	 */
	EstatusCreaInstanciaTO creaInstancia(DatosAutenticacionTO session, ProcesoInicialTO instanciaNodo,
			String valoresReferenciaEnvio, Integer folioMensajeEnvio) throws BrioBPMException, ParseException;
	
	
	
	public RetMsg cargaListaEnTabla(DatosAutenticacionTO session, NodoInicioProcesoTO nodoProceso, String cadenaEntrada, 
			String separador, List<String> listaValor)throws BrioBPMException, ParseException;
	
	// SP_PROCESA_MENSAJES 
	public RetMsg procesaMensajes(DatosAutenticacionTO session)throws BrioBPMException, ParseException;
	
	/**
	 * Crea variables asociadas a una instancia de proceso.
	 * 
	 * @param session        Datos de autenticación del usuario.
	 * @param claveInstancia Clave de la instancia de proceso.
	 * @param claveProceso   Clave del proceso.
	 * @param version        Versión del proceso.
	 * @param condicionInicializacio Parametro de  Condicion Inicializacio
	 * @return EstatusTO con el resultado de la creación de variables y estado del
	 *         proceso.
	 * @throws BrioBPMException En caso de error durante la creación de variables.
	 */
	SaveSectionTO setCreaValoresReferenciaDesdeValores(
	        DatosAutenticacionTO session,
	        ActividadTO actividad,	        
	        String valoresReferencia) throws BrioBPMException;

}
