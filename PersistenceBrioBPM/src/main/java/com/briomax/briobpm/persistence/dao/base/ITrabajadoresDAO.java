package com.briomax.briobpm.persistence.dao.base;

import java.util.List;

import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.persistence.entity.namedquery.DatoTrabajador;

/**
 * El objetivo de la interface ITrabajadoresDAO.java es proporcionar métodos
 * para acceder y manipular datos relacionados con la vista ITrabajadoresDAO en
 * la base de datos.
 *
 * @author Sara Ventura
 * @ver 1.0
 * @fecha Feb 14, 2023 4:12:01 PM
 * @since JDK 1.8
 */
public interface ITrabajadoresDAO {

	/**
	 *  Lista para obtener Trabajadores Activos
	 * @param entidad
	 * @param Rfc
	 * @param contrato
	 * @param instancia
	 * @param cveProces
	 * @return
	 * @throws BrioBPMException
	 */
	List<DatoTrabajador> obtenerTrabajadoresActivos(String entidad, String Rfc, String contrato, String instancia, String cveProces) throws BrioBPMException;
	
	List<DatoTrabajador> obtenerTrabajadoresActivosBk(String entidad, String Rfc, String contrato, String instancia) throws BrioBPMException;
}
