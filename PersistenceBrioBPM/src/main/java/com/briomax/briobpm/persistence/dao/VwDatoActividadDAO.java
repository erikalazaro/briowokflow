package com.briomax.briobpm.persistence.dao;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.briomax.briobpm.common.exception.BrioBPMException;
import com.briomax.briobpm.persistence.dao.base.AbstractBaseDAO;
import com.briomax.briobpm.persistence.dao.base.IVwDatoActividadDAO;
import com.briomax.briobpm.persistence.entity.namedquery.DatoActividad;
import com.briomax.briobpm.persistence.entity.namedquery.DatoActividadDate;

/**
 * El objetivo de la clase VwDatoActividadDAO.java es proporcionar métodos 
 * para acceder y manipular datos relacionados con la vista VW_DATO_ACTIVIDAD en la base de datos.
 *
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Feb 14, 2024 Modificaciones:
 * @since JDK 1.8
 */
@Repository("vwDatoActividadDAO")
public class VwDatoActividadDAO extends AbstractBaseDAO implements IVwDatoActividadDAO{ 
	
	/** 
	 * Inyeccion de EntityManager, es la interfaz principal a traves de la
	 * cual se realizan operaciones de base de datos en JPA
	 */
	@PersistenceContext 
    private EntityManager entityManager;
    
    /**
     * Metodo para obtener el valor maximo de una columna
     *  especifica con datos completos
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
	@Override
    public String obtenerMaxCompleto(String cveColumna, String cveEntidad, String cveProceso, BigDecimal version,
            String cveInstancia, String cveNodo, Integer idNodo, Integer secuenciaNodo) throws BrioBPMException {
        
    	/** 
    	 * Construccion de la consulta SQL dinamicamente utilizando el nombre
    	 * de la columna pasada como parametro
    	 */
		entityManager.clear();
        String query =  "SELECT MAX(" + cveColumna + ") " +
        				"FROM VW_DATO_ACTIVIDAD DA " +
				        "WHERE DA.CVE_ENTIDAD = :cveEntidad " +
				        "	AND DA.CVE_PROCESO = :cveProceso " +
				        "	AND DA.VERSION = :version " +
				        "	AND DA.CVE_INSTANCIA = :cveInstancia " +
				        "	AND DA.CVE_NODO = :cveNodo " +
				        "	AND DA.ID_NODO = :idNodo " +
				        "	AND DA.SECUENCIA_NODO = :secuenciaNodo";
        
        /** 
    	 * Creacion de la consulta nativa y obtencion del resultado
    	 * El resultado se guarda como un objeto 'Object' porque puede ser  
    	 * de diferentes tipos, esto dependera de la columna a la que se consulte
         */
        Object result = entityManager.createNativeQuery(query)
        .setParameter("cveEntidad", cveEntidad)
        .setParameter("cveProceso", cveProceso)
        .setParameter("version", version)
        .setParameter("cveInstancia", cveInstancia)
        .setParameter("cveNodo", cveNodo)
        .setParameter("idNodo", idNodo)
        .setParameter("secuenciaNodo", secuenciaNodo)
        .getSingleResult();
        
        /**
         *  Verificacion del tipo de dato del resultado y conversion segun corresponda
         */
        if (result instanceof Timestamp) { // Si es un Timestamp
            Timestamp timestamp = (Timestamp) result; // Cast a Timestamp
            SimpleDateFormat dateFormat =
            		new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"); // Formato deseado
            return dateFormat.format(timestamp); // Formatear y devolver como String
        
        } else if (result instanceof Number) { // Si es un numero (entero o decimal)
            return result.toString(); // Convertir a String y devolver
        
        } else if (result instanceof String) { // Si es una cadena de texto
            return (String) result; // Devolver tal cual
        }
        
        return null; // Si el tipo de dato no es reconocido, devolver null
    }

	@Override
	public String obtenerMax(String cveColumna, String cveEntidad, String cveProceso, BigDecimal version,
			String cveInstancia) throws BrioBPMException {
		/** 
    	 * Construccion de la consulta SQL dinamicamente utilizando el nombre
    	 * de la columna pasada como parametro
    	 */
		entityManager.clear();
        String query =  "SELECT MAX(" + cveColumna + ") " +
        				"FROM VW_DATO_ACTIVIDAD DA " +
				        "WHERE DA.CVE_ENTIDAD = :cveEntidad " +
				        "	AND DA.CVE_PROCESO = :cveProceso " +
				        "	AND DA.VERSION = :version " +
				        "	AND DA.CVE_INSTANCIA = :cveInstancia ";
        
        /** 
    	 * Creacion de la consulta nativa y obtencion del resultado
    	 * El resultado se guarda como un objeto 'Object' porque puede ser  
    	 * de diferentes tipos, esto dependera de la columna a la que se consulte
         */
        Object result = entityManager.createNativeQuery(query)
        .setParameter("cveEntidad", cveEntidad)
        .setParameter("cveProceso", cveProceso)
        .setParameter("version", version)
        .setParameter("cveInstancia", cveInstancia)
        .getSingleResult();
        
        /**
         *  Verificacion del tipo de dato del resultado y conversion segun corresponda
         */
        if (result instanceof Timestamp) { // Si es un Timestamp
            Timestamp timestamp = (Timestamp) result; // Cast a Timestamp
            SimpleDateFormat dateFormat =
            		new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"); // Formato deseado
            return dateFormat.format(timestamp); // Formatear y devolver como String
        
        } else if (result instanceof Number) { // Si es un numero (entero o decimal)
            return result.toString(); // Convertir a String y devolver
        
        } else if (result instanceof String) { // Si es una cadena de texto
            return (String) result; // Devolver tal cual
        }
        
        return null; // Si el tipo de dato no es reconocido, devolver null
    }
	
	
	@Override
    public Optional<Object> obtenerDatosCompuestos(String qrySelect) throws BrioBPMException {
		String query = qrySelect;
		entityManager.clear();
	    Object result = entityManager.createNativeQuery(query).getSingleResult();
	    if (result != null) {
	        return Optional.of(result);
	    } else {
	        return null;
	    }
	}
	
	@Override
    public List<DatoActividad> obtenerDatoActividad(String query) throws BrioBPMException {
		entityManager.clear(); // Limpia el contexto de persistencia para evitar resultados cacheados
		return  entityManager.createNativeQuery(query, DatoActividad.class).getResultList();
    }
	
	@Override
    public List<DatoActividadDate> obtenerDatoActividadDate(String query) throws BrioBPMException {
		entityManager.clear();
		//return (List<Object>) entityManager.createNativeQuery(query).getSingleResult();
		//entityManager.createNativeQuery(query, DatoActividadDate.class).getResultList();
		return entityManager.createNativeQuery(query, DatoActividadDate.class).getResultList();
    }
}