package com.briomax.briobpm.business.helpers.base;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.briomax.briobpm.common.core.DAORet;
import com.briomax.briobpm.common.core.RetMsg;
import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.transferobjects.app.GraficaTO;
import com.briomax.briobpm.transferobjects.app.Seccion;
import com.briomax.briobpm.transferobjects.in.DatoDashboardTO;
import com.briomax.briobpm.transferobjects.in.DatosAutenticacionTO;
import com.briomax.briobpm.transferobjects.in.EstatusVariablesTO;
import com.briomax.briobpm.transferobjects.in.InNodoProcesoTO;
import com.briomax.briobpm.transferobjects.in.SeccionDashboardTO;

public interface IDashboardHelper {
	
	//FN_LEE_CATEGORIA_ACTIVIDAD
	/**
	 * Lee la categoría de actividad según el estado de la actividad, fecha de evaluación, 
	 * y otros parámetros relacionados con la actividad.
	 * 
	 * @param estadoActividad      El estado actual de la actividad (por ejemplo, "EN TIEMPO", "RETRASADA").
	 * @param session              Objeto de sesión que contiene datos relacionados con el nodo de proceso.
	 * @param fechaEvaluacion      La fecha a evaluar para determinar la categoría de la actividad.
	 * @param categoriaConsultar   La categoría que se desea consultar, como "NUEVA", "ATENDIDA", etc.
	 * @return                    El identificador de la categoría de la actividad.
	 * @throws BrioBPMException    En caso de error al leer la dirección de correo electrónico.
	 */
	public Integer fnLeeCategoriaActividad(String estadoActividad, InNodoProcesoTO session, Date fechaEvaluacion, 
			String categoriaConsultar) throws BrioBPMException;
	
	/**
	 * Obtiene una lista de dashboards de acuerdo con las credenciales de autenticación y 
	 * las ubicaciones de destino y lista.
	 * 
	 * @param session      El objeto de autenticación que contiene los datos de la sesión del usuario.
	 * @param destino      El destino de la lista de dashboard a obtener.
	 * @param ubicacionLista   La ubicación de la lista de dashboard a consultar.
	 * @return DAORet       Un objeto que contiene el resultado de la operación (HashMap) y un mensaje de retorno.
	 */
	DAORet<HashMap<String, String>, RetMsg> leeListaDashboard(DatosAutenticacionTO session, String destino,
			String ubicacionLista);
	
	/**
	 * Obtiene las secciones de un dashboard según el identificador del dashboard y la sesión de autenticación.
	 * 
	 * @param session          El objeto de autenticación con los datos de la sesión.
	 * @param cveDashboard     El código del dashboard a consultar.
	 * @param seccionDashboard La lista de secciones donde se almacenará la información obtenida.
	 * @return                 Un mensaje que contiene el resultado de la operación.
	 */
	RetMsg leeSeccionesDashboard(DatosAutenticacionTO session, String cveDashboard,
			List<SeccionDashboardTO> seccionDashboard, Boolean isSeccion, int cveSeccion);
	
	/**
	 * Obtiene la fecha del primer día del mes correspondiente al mes y año especificados.
	 * 
	 * @param session      El objeto de autenticación que contiene los datos de la sesión.
	 * @param mes          El mes para el cual se desea obtener el primer día.
	 * @param anio         El año para el cual se desea obtener el primer día.
	 * @return             Un objeto DAORet que contiene la fecha obtenida y un mensaje de retorno.
	 * @throws ParseException Si ocurre un error al procesar la fecha.
	 */
	DAORet<Date, RetMsg> obtenFechaPrimerDiaMes(DatosAutenticacionTO session, Integer mes, Integer anio)
			throws ParseException;
	
	/**
	 * Obtiene la fecha del último día del mes correspondiente al mes y año especificados.
	 * 
	 * @param session      El objeto de autenticación que contiene los datos de la sesión.
	 * @param mes          El mes para el cual se desea obtener el último día.
	 * @param anio         El año para el cual se desea obtener el último día.
	 * @return             Un objeto DAORet que contiene la fecha obtenida y un mensaje de retorno.
	 * @throws ParseException Si ocurre un error al procesar la fecha.
	 */
	DAORet<Date, RetMsg> obtenFechaUltimoDiaMes(DatosAutenticacionTO session, Integer mes, Integer anio)
			throws ParseException;
	
	/**
	 * Lee el valor de una variable especial en el sistema.
	 * 
	 * @param session    El objeto de autenticación que contiene los datos de la sesión.
	 * @param cveVariable El código de la variable que se desea consultar.
	 * @return           Un objeto EstatusVariablesTO que contiene el valor de la variable especial.
	 * @throws ParseException Si ocurre un error al procesar la variable.
	 */
	EstatusVariablesTO leeValorVariableEspecial(DatosAutenticacionTO session, String cveVariable) throws ParseException;
	
	/**
	 * Reemplaza las variables dentro de una cadena de entrada de acuerdo con el proceso especificado.
	 * 
	 * @param session      El objeto de autenticación que contiene los datos de la sesión.
	 * @param idProceso    El identificador del proceso que se está ejecutando.
	 * @param cadenaEntrada La cadena que contiene las variables a reemplazar.
	 * @return             Un objeto DAORet que contiene la cadena con las variables reemplazadas y un mensaje de retorno.
	 * @throws ParseException Si ocurre un error al procesar las variables.
	 */
	DAORet<String, RetMsg> reemplazaVariablesBW(DatosAutenticacionTO session, String idProceso, String cadenaEntrada)
			throws ParseException;
	
	/**
	 * Ejecuta una consulta en el dashboard con los parámetros proporcionados.
	 * 
	 * @param session                El objeto de autenticación que contiene los datos de la sesión.
	 * @param cveDashboard           El código del dashboard donde se ejecutará la consulta.
	 * @param servicioQuery          El servicio de consulta que se debe ejecutar.
	 * @param factorConversionEsacala2 El factor de conversión que se aplicará.
	 * @param resultadoQueryServicio Un HashMap que almacenará los resultados de la consulta.
	 * @return                       Un mensaje con el resultado de la ejecución de la consulta.
	 * @throws ParseException        Si ocurre un error al ejecutar la consulta.
	 */
	DAORet<String, RetMsg> ejecutaQueryDashboard(DatosAutenticacionTO session, String cveDashboard, String servicioQuery,
			BigDecimal factorConversionEsacala2, List<String> datos) throws ParseException;

	/**
	 * Consulta y devuelve la información del dashboard según el destino y el código del dashboard especificados.
	 *
	 * @param session      El objeto de autenticación que contiene los datos de la sesión del usuario.
	 * @param destino      El identificador del destino del dashboard que se desea consultar.
	 * @param cveDashboard El código único del dashboard que se desea consultar.
	 * @return             Un objeto {@code DAORet} que contiene una lista de {@code DatoDashboardTO} con los resultados 
	 *                     del dashboard y un mensaje de retorno con información adicional.
	 * @throws ParseException Si ocurre un error al procesar o analizar los datos del dashboard.
	 */
//	DAORet<List<DatoDashboardTO>, RetMsg> leeInformacionDashboard(DatosAutenticacionTO session, String destino, String cveDashboard)
//	        throws ParseException;

//	DAORet<GraficaTO, RetMsg> generaGrafico(DatosAutenticacionTO session, String destino, String cveDashboard, String rfc, List<String> datos)
//			throws ParseException;
	

	DAORet<List<DatoDashboardTO>, RetMsg> leeInformacionDashboard(DatosAutenticacionTO session, String destino,
			String cveDashboard, List<String> datos, Boolean isSeccion, int cveSeccion) throws ParseException;

	DAORet<GraficaTO, RetMsg> generaGrafico(DatosAutenticacionTO session, String destino, String cveDashboard,
			List<String> datos, Boolean isSeccion, int cveSeccion) throws ParseException;

	public void procesarSeccion(Seccion seccion, int i);

}
