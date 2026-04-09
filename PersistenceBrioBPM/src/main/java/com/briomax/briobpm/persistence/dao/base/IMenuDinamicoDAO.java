package com.briomax.briobpm.persistence.dao.base;

import java.math.BigDecimal;
import java.util.List;

import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.persistence.entity.namedquery.ActividadProceso;
import com.briomax.briobpm.persistence.entity.namedquery.LeeMenuEstatico;
import com.briomax.briobpm.persistence.entity.namedquery.MenuPrincipal;
import com.briomax.briobpm.persistence.entity.namedquery.MenuProcesos;

/**
 * El objetivo de la interface IVwDatoActividadDAO.java es proporcionar métodos
 * para acceder y manipular datos relacionados con la vista VW_DATO_ACTIVIDAD en
 * la base de datos.
 *
 * @author Sara ventura
 * @ver 1.0
 * @fecha 13/02/2024
 * @since JDK 1.8
 */
public interface IMenuDinamicoDAO {


	/**
	 * Obtiene las opciones del menu dinamico
	 * @param cveEntidad
	 * @param cveUsuario
	 * @param cveidioma
	 * @return
	 * @throws BrioBPMException
	 */
	public List<MenuPrincipal> obtenerMenu(String cveEntidad, String cveUsuario, String cveidioma) throws BrioBPMException;

	/**
	 * Obtiene las opciones del menu dinamico
	 * @param cveEntidad
	 * @param cveUsuario
	 * @param cveidioma
	 * @param temporizador 
	 * @return
	 * @throws BrioBPMException
	 */
	public List<MenuPrincipal> obtenerMenuDos(String cveEntidad, String cveUsuario, String cveidioma, String temporizador) throws BrioBPMException;
	
	/**
	 * Obtiene las opciones del menu dashboard
	 * @param cveEntidad
	 * @param cveUsuario
	 * @return
	 * @throws BrioBPMException
	 */
	public List<LeeMenuEstatico> obtenerMenuDashbord(String cveEntidad, String cveUsuario) throws BrioBPMException;
	
	/**
	 * Obtene las opciones del menu de porcesos
	 * @param cveEntidad
	 * @param cveUsuario
	 * @return
	 * @throws BrioBPMException
	 */
	public List<MenuProcesos> obtenerMenuProceso(String cveEntidad, String cveUsuario) throws BrioBPMException;
	

	/**
	 * Obtner las actividades de un proceso.
	 * @param cveEntidad
	 * @param cveProceso
	 * @param version
	 * @param cveUsuario
	 * @return
	 * @throws BrioBPMException
	 */
	public List<ActividadProceso> obtenerActividaesByProceso(String cveEntidad, String cveProceso, BigDecimal version, String cveUsuario) throws BrioBPMException;
}
