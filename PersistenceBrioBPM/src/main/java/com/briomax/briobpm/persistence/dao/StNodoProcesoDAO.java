package com.briomax.briobpm.persistence.dao;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.query.NativeQuery;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.persistence.dao.base.AbstractBaseDAO;
import com.briomax.briobpm.persistence.dao.base.IStNodoProcesoDAO;

/**
 * El objetivo de la clase StNodoProcesoDAO.java es proporcionar métodos 
 * para acceder y manipular datos relacionados con la vista ST_NODO_PROCESO en la base de datos.
 *
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Mar 14, 2024 Modificaciones:
 * @since JDK 1.8
 */
@Repository("StNodoProcesoDAO")
public class StNodoProcesoDAO extends AbstractBaseDAO implements IStNodoProcesoDAO{// revisar falta interfaz


	@Override
	public List<Object> obtenerIdNodos(BigDecimal idNodo, String cveEntidad, String cveProceso, BigDecimal version, String cveNodo,
			String cveNodoTemporizador) throws BrioBPMException {
		/** 
	     * Construccion de la consulta SQL dinamicamente utilizando el nombre
	     * de la columna pasada como parametro
	     */
	    String query = "SELECT " + idNodo +
	                    " WHERE :cveNodo <> :cveNodoTemporizador " +
	                    "UNION ALL " +
	                    "SELECT DISTINCT STNP.ID_NODO " +
	                    "FROM ST_NODO_PROCESO STNP " +
	                    "WHERE STNP.CVE_ENTIDAD = :cveEntidad " +
	                    "   AND STNP.CVE_PROCESO = :cveProceso " +
	                    "   AND STNP.VERSION = :version " +
	                    "   AND STNP.CVE_NODO = :cveNodoTemporizador " +
	                    "   AND :cveNodo = :cveNodoTemporizador";

	    /** 
	     * Creacion de la consulta nativa y obtencion del resultado
	     * El resultado se guarda como un objeto 'Object' porque puede ser  
	     * de diferentes tipos, esto dependera de la columna a la que se consulte
	     */

	    List<Object> result = entityManager.createNativeQuery(query)
	        .setParameter("cveEntidad", cveEntidad)
	        .setParameter("cveProceso", cveProceso)
	        .setParameter("version", version)
	        .setParameter("cveNodoTemporizador", cveNodoTemporizador)
	        .setParameter("cveNodo", cveNodo)
	        .unwrap(NativeQuery.class)
	        .addScalar("ID_NODO", StandardBasicTypes.BIG_DECIMAL)
	        .list();
	    
	    return result;
    }
}
