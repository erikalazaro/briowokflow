package com.briomax.briobpm.persistence.repository;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.briomax.briobpm.persistence.entity.StCompuertaInicio;
import com.briomax.briobpm.persistence.entity.StCompuertaInicioPK;

/**
 * El objetivo de la Interface InVariableProcesoRepository.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Nov 27, 2023 Modificaciones:
 * @since JDK 1.8
 */
@Repository
public interface IStCompuertaInicioRepository extends JpaRepository<StCompuertaInicio, StCompuertaInicioPK> {
    
	@Query("SELECT COUNT(1) FROM StCompuertaInicio stci " +
		       "WHERE stci.id.cveEntidad = :cveEntidad " +
		       "AND stci.id.cveProceso = :cveProceso " +
		       "AND stci.id.version = :version " +
		       "AND stci.cveNodoCierre = :cveNodoCierre " +
		       "AND stci.idNodoCierre = :idNodoCierre")
	Integer existeStCompuertaInicio(
             @Param("cveEntidad") String cveEntidad,
             @Param("cveProceso") String cveProceso,
             @Param("version") BigDecimal version,
             @Param("cveNodoCierre") String cveNodoCierre,
             @Param("idNodoCierre") Integer idNodoCompuerta);
	
	
	 @Query("SELECT stci " +
	           "FROM StCompuertaInicio stci " +
	           "WHERE stci.id.cveEntidad = :cveEntidad " +
	           "AND stci.id.cveProceso = :cveProceso " +
	           "AND stci.id.version = :version " +
	           "AND stci.cveNodoCierre = :cveNodoCompuerta " +
	           "AND stci.idNodoCierre = :idNodoCompuerta")
    Optional<StCompuertaInicio> identificaCompuertaInicio(
             @Param("cveEntidad") String cveEntidad,
             @Param("cveProceso") String cveProceso,
             @Param("version") BigDecimal version,
             @Param("cveNodoCompuerta") String cveNodoCompuerta,
             @Param("idNodoCompuerta") Integer idNodoCompuerta);
    
	 @Query("SELECT COUNT(1) " +
		       "FROM StCompuertaInicio stci " +
		       "WHERE stci.id.cveEntidad = :cveEntidad " +
		       "  AND stci.id.cveProceso = :cveProceso " +
		       "  AND stci.id.version = :version " +
		       "  AND stci.id.cveNodoInicio = :cveNodoCrear " +
		       "  AND stci.id.idNodoInicio = :idNodoCrear")
	Integer encontrarRegistroExistente(
	     @Param("cveEntidad") String cveEntidad,
	     @Param("cveProceso") String cveProceso,
	     @Param("version") BigDecimal version,
	     @Param("cveNodoCrear") String cveNodoCrear,
	     @Param("idNodoCrear") Integer idNodoCrear);

	
}