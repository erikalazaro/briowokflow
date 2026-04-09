package com.briomax.briobpm.persistence.dao.base;

import java.math.BigDecimal;
import java.util.List;

import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.persistence.entity.namedquery.DatoActividad;
import com.briomax.briobpm.persistence.entity.namedquery.DatoActividadDate;

/**
 * El objetivo de la interface IVwDatoActividadDAO.java es proporcionar métodos
 * para acceder y manipular datos relacionados con la vista VW_DATO_ACTIVIDAD en
 * la base de datos.
 *
 * @author Alexis Zamora
 * @ver 1.0
 * @fecha Feb 14, 2023 4:12:01 PM
 * @since JDK 1.8
 */
public interface IVwDatoActividadDAO {

	/**
	 * Metodo para obtener el valor maximo de una columna especifica con datos
	 * completos
	 * 
	 * @param cveColumna
	 * @param cveEntidad
	 * @param cveProceso
	 * @param version
	 * @param cveInstancia
	 * @param cveNodo
	 * @param idNodo
	 * @param secuenciaNodo
	 * @return
	 * @throws BrioBPMException la brio BPM exception.
	 */
	public String obtenerMaxCompleto(String cveColumna, String cveEntidad, String cveProceso, BigDecimal version,
			String cveInstancia, String cveNodo, Integer idNodo, Integer secuenciaNodo) throws BrioBPMException;

	/**
	 * Metodo para obtener el valor maximo de una columna espesidica
	 * 
	 * @param cveColumna
	 * @param cveEntidad
	 * @param cveProceso
	 * @param version
	 * @param cveInstancia
	 * @return
	 * @throws BrioBPMException la brio BPM exception.
	 */
	public String obtenerMax(String cveColumna, String cveEntidad, String cveProceso, BigDecimal version,
			String cveInstancia) throws BrioBPMException;

	public Object obtenerDatosCompuestos(String qrySelect)throws BrioBPMException;
	
	/**
	 * Ejecuta query y lo convierte en una lista.
	 * @param query
	 * @return
	 * @throws BrioBPMException
	 */
	public List<DatoActividad> obtenerDatoActividad(String query) throws BrioBPMException;
	
	/**
	 * Ejecuta query y lo convierte en una lista con date.
	 * @param query
	 * @return
	 * @throws BrioBPMException
	 */
	 public List<DatoActividadDate> obtenerDatoActividadDate(String query) throws BrioBPMException;
}
