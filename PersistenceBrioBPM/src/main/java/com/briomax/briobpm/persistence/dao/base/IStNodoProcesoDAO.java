package com.briomax.briobpm.persistence.dao.base;

import java.math.BigDecimal;
import java.util.List;

import com.briomax.briobpm.common.exception.BrioBPMException;

/**
 * El objetivo de la interface IVwDatoActividadDAO.java es proporcionar métodos
 * para acceder y manipular datos relacionados con la vista VW_DATO_ACTIVIDAD en
 * la base de datos.
 *
 * @author Alexis Zamora
 * @ver 1.0
 * @fecha Mar 14, 2023 4:12:01 PM
 * @since JDK 1.8
 */
public interface IStNodoProcesoDAO {

	public List<Object> obtenerIdNodos(BigDecimal idNodo, String cveEntidad, String cveProceso, BigDecimal version,
			String cveNodo, String cveNodoTemporizador) throws BrioBPMException;

}
