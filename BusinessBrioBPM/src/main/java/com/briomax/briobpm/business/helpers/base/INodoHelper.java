package com.briomax.briobpm.business.helpers.base;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.briomax.briobpm.common.core.DAORet;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.persistence.jdto.Documento;
import com.briomax.briobpm.transferobjects.SaveSectionTO;
import com.briomax.briobpm.transferobjects.VariableCadenaTO;
import com.briomax.briobpm.transferobjects.catalogs.BitacoraNodo;
import com.briomax.briobpm.transferobjects.in.ActividadTO;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;
import com.briomax.briobpm.transferobjects.in.DocumentoNodoTO;
import com.briomax.briobpm.transferobjects.in.DocumentoTO;
import com.briomax.briobpm.transferobjects.in.ElementoCadenaTO;
import com.briomax.briobpm.transferobjects.in.EstatusCompuertaTO;
import com.briomax.briobpm.transferobjects.in.EstatusCondicionTO;
import com.briomax.briobpm.transferobjects.in.EstatusConfiguracionEnvioTO;
import com.briomax.briobpm.transferobjects.in.EstatusTO;
import com.briomax.briobpm.transferobjects.in.EstatusVariableCadenaTO;
import com.briomax.briobpm.transferobjects.in.EstatusVariablesTO;
import com.briomax.briobpm.transferobjects.in.NodoProcesoTO;
import com.briomax.briobpm.transferobjects.in.NodoTO;
import com.briomax.briobpm.transferobjects.in.SituacionTareaTO;
import com.briomax.briobpm.transferobjects.in.StSeccionNodoTO;
import com.briomax.briobpm.transferobjects.in.TareaNodoTO;
import com.briomax.briobpm.transferobjects.in.ValorVariableTO;

/**
 * El objetivo de la Interface INodoHelper.java es ...
 * 
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Nov 15, 2023 6:05:58 PM Modificaciones:
 * @since JDK 1.8
 */
public interface INodoHelper {

	// SP_LEE_VARIABLES_CADENA
	/**
	 * Lee variables de tipo cadena.
	 * 
	 * @param cadenaEntrada      Cadena de entrada para la búsqueda de variables.
	 * @param variableCadenaList Lista de variables de tipo cadena.
	 * @return EstatusVariableCadenaTO con el resultado de la lectura de las
	 *         variables.
	 * @throws BrioBPMException En caso de error durante la lectura de las
	 *                          variables.
	 */
	public EstatusVariableCadenaTO leeVariablesCadena(String cadenaEntrada, List<VariableCadenaTO> variableCadenaList)
			throws BrioBPMException;

	// SP_CREA_FOLIO_IN_NODO
	/**
	 * Crea un folio para un nodo específico.
	 * 
	 * @param session Datos de autenticación del usuario.
	 * @param nodo    Nodo para el cual se creará el folio.
	 * @return EstatusVariablesTO con el resultado de la creación del folio.
	 * @throws BrioBPMException En caso de error durante la creación del folio.
	 */
	public EstatusVariablesTO creaFolio(DatosAutenticacionTO session, NodoTO nodo) throws BrioBPMException;

	// SP_INS_IN_NODO
	/**
	 * Inserta un nodo en un proceso.
	 * 
	 * @param session     Datos de autenticación del usuario.
	 * @param nodo        Nodo que se insertará en el proceso.
	 * @param nodoProceso Nodo del proceso.
	 * @param usuarioAsignado usuario al que asignara la actividad
	 * @return EstatusTO con el resultado de la inserción del nodo en el proceso.
	 * @throws BrioBPMException En caso de error durante la inserción del nodo.
	 */
	public EstatusTO insertaInNodo(DatosAutenticacionTO session, NodoTO nodo, NodoProcesoTO nodoProceso, List<String> usuarioAsignado)
			throws BrioBPMException;

	// SP_LEE_CONFIGURACION_ENVIO
	public EstatusConfiguracionEnvioTO leeConfiguracionEnvio(DatosAutenticacionTO session, NodoTO configEnvioNodo)
			throws BrioBPMException;

	// SP_LEE_CONFIGURACION_RECEPCION
	/**
	 * Lee la configuración de envío para un nodo específico.
	 * 
	 * @param session         Datos de autenticación del usuario.
	 * @param configEnvioNodo Nodo para el cual se desea leer la configuración de
	 *                        envío.
	 * @return EstatusConfiguracionEnvioTO con el resultado de la lectura de la
	 *         configuración de envío.
	 * @throws BrioBPMException En caso de error durante la lectura de la
	 *                          configuración de envío.
	 */
	public EstatusVariablesTO leeConfiguracionRecepcion(DatosAutenticacionTO session, NodoTO nodo)
			throws BrioBPMException;

	// SP_CREA_FOLIO_MENSAJE.
	/**
	 * Crea un folio mensaje para un nodo específico.
	 * 
	 * @param session Datos de autenticación del usuario.
	 * @param nodo    Nodo para el cual se creará el folio mensaje.
	 * @return EstatusVariablesTO con el resultado de la creación del folio mensaje.
	 * @throws BrioBPMException En caso de error durante la creación del folio
	 *                          mensaje.
	 */
	public EstatusVariablesTO creaFolioMensaje(DatosAutenticacionTO session, NodoTO nodo) throws BrioBPMException;

	// SP_CREA_VARIABLES_ENVIO
	/**
	 * Crea variables de envío para un nodo específico.
	 * 
	 * @param session Datos de autenticación del usuario.
	 * @param nodo    Nodo para el cual se crearán las variables de envío.
	 * @return EstatusTO con el resultado de la creación de las variables de envío.
	 * @throws BrioBPMException En caso de error durante la creación de las
	 *                          variables de envío.
	 * @throws ParseException   En caso de error al analizar la entrada.
	 */
	public EstatusTO creaVariablesEnvio(DatosAutenticacionTO session, NodoTO nodo)
			throws BrioBPMException, ParseException;

	// SP_LEE_VALOR_VPRO
	/**
	 * Lee el valor de una variable VPRO (Variable de Proceso) para un nodo
	 * específico.
	 * 
	 * @param session            Datos de autenticación del usuario.
	 * @param nodo               Nodo para el cual se desea leer el valor de la
	 *                           variable VPRO.
	 * @param agregacion         Tipo de agregación a aplicar.
	 * @param secuenciaDocumento Secuencia del documento.
	 * @return EstatusVariablesTO con el resultado de la lectura del valor de la
	 *         variable VPRO.
	 * @throws BrioBPMException En caso de error durante la lectura del valor de la
	 *                          variable VPRO.
	 */

	public EstatusVariablesTO leerValorVpro(DatosAutenticacionTO session, NodoTO nodo, String agregacion,
			Integer secuenciaDocumento) throws BrioBPMException;

	// SP_LEE_VALOR_VLOC
	/**
	 * Lee el valor de una variable VLOC (Variable Local) para un proceso
	 * específico.
	 * 
	 * @param session     Datos de autenticación del usuario.
	 * @param cveProceso  Clave del proceso.
	 * @param version     Versión del proceso.
	 * @param cveVariable Clave de la variable.
	 * @return EstatusVariablesTO con el resultado de la lectura del valor de la
	 *         variable VLOC.
	 * @throws BrioBPMException En caso de error durante la lectura del valor de la
	 *                          variable VLOC.
	 */
	public EstatusVariablesTO leerValorVeloc(DatosAutenticacionTO session, String cveProceso, BigDecimal version,
			String cveVariable) throws BrioBPMException;

	// SP_LEE_VALOR_VENT
	/**
	 * Lee el valor de una variable VENT (Variable Entorno) para un proceso
	 * específico.
	 * 
	 * @param session     Datos de autenticación del usuario.
	 * @param cveProceso  Clave del proceso.
	 * @param version     Versión del proceso.
	 * @param cveVariable Clave de la variable.
	 * @return EstatusVariablesTO con el resultado de la lectura del valor de la
	 *         variable VENT.
	 * @throws BrioBPMException En caso de error durante la lectura del valor de la
	 *                          variable VENT.
	 */
	public EstatusVariablesTO leerValorVent(DatosAutenticacionTO session, String cveProceso, BigDecimal version,
			String cveVariable) throws BrioBPMException;

	// SP_LEE_VALOR_VSIS
	/**
	 * Lee el valor de una variable VSIS (Variable Sistema) para un proceso
	 * específico.
	 * 
	 * @param session     Datos de autenticación del usuario.
	 * @param cveProceso  Clave del proceso.
	 * @param version     Versión del proceso.
	 * @param cveVariable Clave de la variable.
	 * @return EstatusVariablesTO con el resultado de la lectura del valor de la
	 *         variable VSIS.
	 * @throws BrioBPMException En caso de error durante la lectura del valor de la
	 *                          variable VSIS.
	 */
	public EstatusVariablesTO leerValorVsis(DatosAutenticacionTO session, String cveProceso, BigDecimal version,
			String cveVariable) throws BrioBPMException;

	// SP_LEE_VALOR_VPN revisar VIEW
	// revisar switch
	/**
	 * Lee el valor de una variable VPN (Variable de Proceso de Nodo) para un nodo
	 * específico.
	 * 
	 * @param session     Datos de autenticación del usuario.
	 * @param nodo        Nodo para el cual se desea leer el valor de la variable
	 *                    VPN.
	 * @param cveVariable Clave de la variable.
	 * @return EstatusVariablesTO con el resultado de la lectura del valor de la
	 *         variable VPN.
	 * @throws BrioBPMException En caso de error durante la lectura del valor de la
	 *                          variable VPN.
	 * @throws ParseException   En caso de error al analizar la entrada.
	 */
	public EstatusVariablesTO leerValorVpn(DatosAutenticacionTO session, NodoTO nodo, String cveVariable)
			throws BrioBPMException, ParseException;

	// SP_LEE_VALOR_VDOC
	/**
	 * Lee el valor de una variable VDOC (Variable Documento) para un nodo
	 * específico.
	 * 
	 * @param session Datos de autenticación del usuario.
	 * @param nodo    Nodo para el cual se desea leer el valor de la variable VDOC.
	 * @return EstatusVariablesTO con el resultado de la lectura del valor de la
	 *         variable VDOC.
	 * @throws BrioBPMException En caso de error durante la lectura del valor de la
	 *                          variable VDOC.
	 */
	public EstatusVariablesTO leerValorVdoc(DatosAutenticacionTO session, NodoTO nodo) throws BrioBPMException;

	// SP_CREA_TAREAS_NODO
	/**
	 * Crea tareas para un nodo específico.
	 * 
	 * @param session        Datos de autenticación del usuario.
	 * @param creaTareasNodo Nodo para el cual se crearán las tareas.
	 * @return EstatusTO con el resultado de la creación de las tareas.
	 * @throws BrioBPMException En caso de error durante la creación de las tareas.
	 */
	public EstatusTO creaTareas(DatosAutenticacionTO session, NodoTO creaTareasNodo) throws BrioBPMException;

	// SP_CREA_DOCUMENTOS_NODO
	/**
	 * Crea documentos para un nodo específico.
	 * 
	 * @param session Datos de autenticación del usuario.
	 * @param nodo    Nodo para el cual se crearán los documentos.
	 * @return EstatusTO con el resultado de la creación de los documentos.
	 * @throws BrioBPMException En caso de error durante la creación de los
	 *                          documentos.
	 */
	public EstatusTO creaDocumentos(DatosAutenticacionTO session, NodoTO nodo) throws BrioBPMException;

	// SP_RECIBE_VARIABLES_ENVIO
	/**
	 * Recibe variables de envío para un proceso específico.
	 * 
	 * @param session             Datos de autenticación del usuario.
	 * @param idProceso           Identificador del proceso.
	 * @param folioMensajeEnvio   Folio del mensaje de envío.
	 * @param cveEntidadDestino   Clave de la entidad destino.
	 * @param cveProcesoDestino   Clave del proceso destino.
	 * @param versionDestino      Versión del proceso destino.
	 * @param cveInstanciaDestino Clave de la instancia destino.
	 * @return EstatusTO con el resultado de la recepción de las variables de envío.
	 * @throws BrioBPMException En caso de error durante la recepción de las
	 *                          variables de envío.
	 */
	public EstatusTO recibeValoresEnvio(DatosAutenticacionTO session, String idProceso, Integer folioMensajeEnvio,
			String cveEntidadDestino, String cveProcesoDestino, BigDecimal versionDestino, String cveInstanciaDestino)
			throws BrioBPMException;

	// SP_REEMPLAZA_VARIABLES revisar agregacion
	/**
	 * Reemplaza variables para un nodo específico.
	 * 
	 * @param session            Datos de autenticación del usuario.
	 * @param nodo               Nodo para el cual se realizará el reemplazo de
	 *                           variables.
	 * @param secuenciaDocumento Secuencia del documento.
	 * @param cadenaEntrada      Cadena de entrada para el reemplazo de variables.
	 * @return EstatusVariablesTO con el resultado del reemplazo de variables.
	 * @throws BrioBPMException En caso de error durante el reemplazo de variables.
	 * @throws ParseException   En caso de error al analizar la entrada.
	 */
	public EstatusVariablesTO reemplazaVariables(DatosAutenticacionTO session, NodoTO nodo, Integer secuenciaDocumento,
			String cadenaEntrada) throws BrioBPMException, ParseException;

	// SP_EVALUA_CONDICION.
	/**
	 * Evalúa una condición para un nodo específico.
	 * 
	 * @param session   Datos de autenticación del usuario.
	 * @param nodo      Nodo para el cual se evaluará la condición.
	 * @param condicion Condición a evaluar.
	 * @return EstatusVariablesTO con el resultado de la evaluación de la condición.
	 * @throws BrioBPMException En caso de error durante la evaluación de la
	 *                          condición.
	 */
	public EstatusCondicionTO evaluarCondicion(DatosAutenticacionTO session, NodoTO nodo, String condicion)
			throws BrioBPMException;

	// SP_GENERA_EVENTO_BITACORA
	/**
	 * Genera un evento en la bitácora para un nodo específico.
	 * 
	 * @param session Datos de autenticación del usuario.
	 * @param nodo    Nodo para el cual se generará el evento en la bitácora.
	 * @param accion  Acción a registrar en la bitácora.
	 * @return EstatusTO con el resultado de la generación del evento en la
	 *         bitácora.
	 * @throws BrioBPMException En caso de error durante la generación del evento en
	 *                          la bitácora.
	 */
	public EstatusTO generaEventoBitacora(DatosAutenticacionTO session, NodoTO nodo, String accion)
			throws BrioBPMException;

	// SP_VAL_DATOS_IN_NODO.
	/**
	 * Valida los datos de entrada para un nodo específico.
	 * 
	 * @param session   Datos de autenticación del usuario.
	 * @param datosNodo Nodo para el cual se validarán los datos de entrada.
	 * @return EstatusTO con el resultado de la validación de los datos de entrada.
	 * @throws BrioBPMException En caso de error durante la validación de los datos
	 *                          de entrada.
	 */
	public EstatusTO valDatosIn(DatosAutenticacionTO session, NodoTO datosNodo) throws BrioBPMException;

	// SP_TERMINA_COMPUERTA_CIERRE.
	/**
	 * Termina la compuerta de cierre para un nodo específico.
	 * 
	 * @param session          Datos de autenticación del usuario.
	 * @param nodo             Nodo para el cual se terminará la compuerta de
	 *                         cierre.
	 * @param cveNodoCompuerta Clave del nodo compuerta.
	 * @param idNodoCompuerta  ID del nodo compuerta.
	 * @return EstatusCompuertaTO con el resultado de terminar la compuerta de
	 *         cierre.
	 * @throws BrioBPMException En caso de error durante la terminación de la
	 *                          compuerta de cierre.
	 */
	public EstatusCompuertaTO terminaCompuertaCierre(DatosAutenticacionTO session, NodoTO nodo, String cveNodoCompuerta,
			Integer idNodoCompuerta) throws BrioBPMException;

	// SP_CREA_CORREO_PROCESO
	/**
	 * Crea un correo para un proceso específico.
	 * 
	 * @param session            Datos de autenticación del usuario.
	 * @param nodo               Nodo para el cual se creará el correo.
	 * @param secuenciaDocumento Secuencia del documento.
	 * @param cveUsuarioCreador  Clave del usuario creador del correo.
	 * @return EstatusTO con el resultado de la creación del correo.
	 * @throws BrioBPMException En caso de error durante la creación del correo.
	 * @throws ParseException   En caso de error al analizar la entrada.
	 */
	public EstatusTO creaCorreoProceso(DatosAutenticacionTO session, NodoTO nodo, Integer secuenciaDocumento,
			String cveUsuarioCreador) throws BrioBPMException, ParseException;

	// FN_LEE_DIRECCION_CORREO
	/**
	 * Lee la dirección de correo electrónico.
	 * 
	 * @param cveEntidad Clave de la entidad.
	 * @param cveProceso Clave del proceso.
	 * @param version    Versión del proceso.
	 * @param cveUsuario Clave del usuario.
	 * @param cveRol     Clave del rol.
	 * @return Dirección de correo electrónico.
	 * @throws BrioBPMException En caso de error al leer la dirección de correo
	 *                          electrónico.
	 */
	public String fnLeeDireccionCorreo(String cveEntidad, String cveProceso, BigDecimal version, String cveUsuario,
			String cveRol) throws BrioBPMException;

	// FN_TRUNCA_DECIMALES
	/**
	 * Trunca un valor decimal al número de decimales especificado.
	 * 
	 * @param valorDecimal    Valor decimal a truncar.
	 * @param numeroDecimales Número de decimales a mantener.
	 * @return Valor decimal truncado como cadena.
	 * @throws BrioBPMException En caso de error durante el truncamiento de
	 *                          decimales.
	 */
	public String truncarDecimales(BigDecimal valorDecimal, Integer numeroDecimales) throws BrioBPMException;

	// FN_FORMATEA_FECHA
	/**
	 * Formatea una fecha según el formato especificado.
	 * 
	 * @param fecha   Fecha a formatear.
	 * @param formato Formato de la fecha.
	 * @return Fecha formateada como cadena.
	 * @throws BrioBPMException En caso de error durante el formateo de la fecha.
	 */
	public String formatFecha(Date fecha, String formato) throws BrioBPMException;

	// SP_CREA_VARIABLES_SECCION revisar codigo muerto
	/**
	 * Crea variables para una sección específica.
	 * 
	 * @param session            Datos de autenticación del usuario.
	 * @param nodo               Nodo para el cual se crearán las variables de
	 *                           sección.
	 * @param tipoSeccion        Tipo de sección.
	 * @param secuenciaDocumento Secuencia del documento.
	 * @return EstatusTO con el resultado de la creación de las variables de
	 *         sección.
	 * @throws BrioBPMException En caso de error durante la creación de las
	 *                          variables de sección.
	 */
	public EstatusTO creaVariablesSeccion(DatosAutenticacionTO session, NodoTO nodo, String tipoSeccion,
			Integer secuenciaDocumento) throws BrioBPMException;

	// SP_CREA_MENSAJE_NODO
	/**
	 * Crea un mensaje para un nodo específico.
	 * 
	 * @param session Datos de autenticación del usuario.
	 * @param nodo    Nodo para el cual se creará el mensaje.
	 * @return EstatusTO con el resultado de la creación del mensaje.
	 * @throws BrioBPMException En caso de error durante la creación del mensaje.
	 * @throws ParseException   En caso de error al analizar la entrada.
	 */
	// revisar estructura IF
	public EstatusTO creaMensaje(DatosAutenticacionTO session, NodoTO nodo) throws BrioBPMException, ParseException;

	// SP_CREA_INSTANCIA_NODO
	/**
	 * Crea una instancia para un nodo específico.
	 * 
	 * @param session       Datos de autenticación del usuario.
	 * @param instanciaNodo Nodo para el cual se creará la instancia.
	 * @return EstatusTO con el resultado de la creación de la instancia.
	 * @throws BrioBPMException En caso de error durante la creación de la
	 *                          instancia.
	 * @throws ParseException   En caso de error al analizar la entrada.
	 */
	public EstatusTO creaInstancia(DatosAutenticacionTO session, NodoTO instanciaNodo)
			throws BrioBPMException, ParseException;

	// SP_CREA_NODO
	/**
	 * Crea un nodo para un proceso específico.
	 * 
	 * @param session            Datos de autenticación del usuario.
	 * @param nodo               Nodo para el cual se creará el nodo.
	 * @param cveNodoCrear       Clave del nodo a crear.
	 * @param idNodoCrear        ID del nodo a crear.
	 * @param condicionNodoCrear Condición para crear el nodo.
	 * @param aplicarTerminacion Indicador de si se aplicará terminación.
	 * @param folioMensajeEnvio  Folio del mensaje de envío.
	 * @return EstatusVariablesTO con el resultado de la creación del nodo.
	 * @throws BrioBPMException En caso de error durante la creación del nodo.
	 * @throws ParseException   En caso de error al analizar la entrada.
	 */
	public EstatusVariablesTO creaNodo(DatosAutenticacionTO session, NodoTO nodo, String cveNodoCrear,
			Integer idNodoCrear, String condicionNodoCrear, String aplicarTerminacion, Integer folioMensajeEnvio)
			throws BrioBPMException, ParseException;

	// SP_LEE_BITACORA_NODO
	/**
	 * Recupera la bitacora de un nodo / Actividad.
	 * 
	 * @param session   Datos de autenticación del usuario.
	 * @param actividad ActividadTO
	 * @return LeeBitacoraNodo con el resultado de la creación del nodo.
	 * @throws BrioBPMException En caso de error durante la creación del nodo.
	 */
	public BitacoraNodo leeBitacoraNodo(DatosAutenticacionTO session, ActividadTO actividad) throws BrioBPMException;

	/**
	 * Obtener la traducción de la etiqueta.
	 * 
	 * @param userSession el user session.
	 * @param cve         entidad
	 * @param cve         Idioma
	 * @param etiqueta
	 * @return el hora.
	 * @throws BrioBPMException la brio BPM exception.
	 */
	String traducirEtiqueta(DatosAutenticacionTO session, String etiqueta) throws BrioBPMException;

	// SP_LEE_SECCIONES_NODO
	public DAORet<List<StSeccionNodoTO>, RetMsg> leeSeccionesNodo(DatosAutenticacionTO session, NodoTO nodo,
			String generaTablaTemporal, List<StSeccionNodoTO> seccionNodo) throws BrioBPMException;

	// SP_LEE_DOCUMENTOS_NODO
	public DAORet<List<DocumentoNodoTO>, RetMsg> leeDocumentosNodo(DatosAutenticacionTO session, NodoTO nodo,
			String cveSeccion, String generaTablaTemporal, List<DocumentoNodoTO> documentoNodo) throws BrioBPMException;

	// SP_LEE_ELEMENTO_CADENA
	public ElementoCadenaTO leeElementoCadena(String cadena, Integer longitudCadena, Integer posicionBusqueda,
			String separador) throws BrioBPMException;

	// SP_EXTRAE_DATOS_OCURRENCIA
	public EstatusTO extraeDatosOcurrencia(DatosAutenticacionTO session, NodoTO nodo, String datosOcurrencia,
			List<ValorVariableTO> valorVariable) throws BrioBPMException, ParseException;

	// SP_GUARDA_VARIABLES_SECCION
	public RetMsg guardaVariablesSeccion(DatosAutenticacionTO session, NodoTO nodo, String cveSeccion,
			String primeraOcurrencia, String nuevaOcurrencia, String datosOcurrencia)
			throws BrioBPMException, ParseException;

	// SP_EXTRAE_TAREAS_CADENA
	public EstatusTO extraeTareasCadena(DatosAutenticacionTO session, NodoTO nodo, String secuenciaCompletada,
			List<SituacionTareaTO> situacionTarea) throws BrioBPMException;

	// SP_GUARDA_TAREAS_NODO
	public RetMsg guardaTareasNodo(DatosAutenticacionTO session, NodoTO nodo, String secuenciaCompletada)
			throws BrioBPMException;

	// SP_GUARDA_DOCUMENTO_BINARIO_NODO
	public RetMsg guardaDocumentoBinarioNodo(DatosAutenticacionTO session, DocumentoTO documento)
			throws BrioBPMException;

	// SP_LEE_VARIABLES_SECCION
	//public EstatusTO leeVariablesSeccion(DatosAutenticacionTO session, NodoTO nodo, String cveSeccion,
	//		String generaTablaTemporal, List<VariableSeccionTO> variableSeccion) throws BrioBPMException;

	// SP_VALIDA_VARIABLES_REQUERIDAS
//	public EstatusTO validaVariablesRequeridas(DatosAutenticacionTO session, NodoTO nodo, List<StSeccionNodoTO> seccionNodo)
//			throws BrioBPMException;
	// SP_TERMINA_ACTIVIDAD
	public RetMsg terminaActividad(DatosAutenticacionTO session, NodoTO nodo) throws BrioBPMException, ParseException;

	// SP_LEE_TAREAS_NODO
	public DAORet<List<TareaNodoTO>, EstatusTO> leeTareasNodo(DatosAutenticacionTO session, NodoTO nodo, String cveSeccion,
			String generaTablaTemporal, List<TareaNodoTO> tareaNodo) throws BrioBPMException;

	// SP_LEE_DOCUMENTO_BINARIO_NODO
	public Documento leeDocumentoBinarioNodo(DatosAutenticacionTO session, NodoTO nodo, Integer secuenciaDocumento)
			throws BrioBPMException;

	// SP_BORRA_DOCUMENTO_BINARIO_NODO
	public RetMsg borraDocumentoBinarioNodo(DatosAutenticacionTO session, NodoTO nodo, Integer secuenciaDocumento)
			throws BrioBPMException;

	// SP_VALIDA_TAREAS_REQUERIDAS
	public EstatusTO validaTareasRequerida(DatosAutenticacionTO session, NodoTO nodo, List<StSeccionNodoTO> seccionNodo)
			throws BrioBPMException;

	// SP_VALIDA_DOCUMENTOS_REQUERIDOS
	public EstatusTO validaDocumentoRequetidos(DatosAutenticacionTO session, NodoTO nodo, List<StSeccionNodoTO> seccionNodo)
			throws BrioBPMException;

	List<Documento> leeDocumentoBinarioNodoMultiple(DatosAutenticacionTO session, NodoTO nodo, Integer secuenciaDocumento)
			throws BrioBPMException;

	RetMsg guardaDocumentoBinarioNodoMultiple(DatosAutenticacionTO session, List<DocumentoTO> documento)
			throws BrioBPMException;

	EstatusTO creaDocumentosMultiples(DatosAutenticacionTO session, NodoTO nodo) throws BrioBPMException;


	// SP_LEE_VALOR_VARIABLE
	EstatusVariablesTO leeValorVariable(DatosAutenticacionTO session, NodoTO nodo, String cveVariable,
			Integer secuenciaDocumento) throws BrioBPMException, ParseException;
	
	// SP_CAMBIA_SITUACION_NODO
	RetMsg cambiaSituacionNodo(DatosAutenticacionTO session, ActividadTO actividad, String accion) throws BrioBPMException;

	/**
	 * Ajuste para aplicar reglas al ejecutar los proceso
	 * @param session
	 * @param nodo
	 * @param tipoBoton
	 * @return
	 */
	RetMsg evaluaReglasProcesoTerminar(DatosAutenticacionTO session, NodoTO nodo, String tipoBoton);

	void consultarNodoRepse(DatosAutenticacionTO session, NodoTO nodo, SaveSectionTO dataSections) throws BrioBPMException;
	
}
