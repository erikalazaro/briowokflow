package com.briomax.briobpm.persistence.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.briomax.briobpm.persistence.entity.InTareaProceso;
import com.briomax.briobpm.persistence.entity.InTareaProcesoPK;

/**
 * El objetivo de la Interface InVariableProcesoRepository.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Nov 20, 2023 Modificaciones:
 * @since JDK 1.8
 */
@Repository
public interface IInTareaProcesoRepository extends JpaRepository<InTareaProceso, InTareaProcesoPK> {
	   
	@Query(value = "SELECT DISTINCT ts.SECUENCIA_TAREA " +
	            "FROM ST_TAREA_SECCION ts " +
	            "WHERE ts.CVE_ENTIDAD = :cveEntidad " +
	            "AND ts.CVE_PROCESO = :cveProceso " +
	            "AND ts.VERSION = :version " +
	            "AND ts.CVE_NODO = :cveNodo " +
	            "AND ts.ID_NODO = :idNodo " +
	            "ORDER BY ts.SECUENCIA_TAREA", nativeQuery = true)
	    List<Integer> encuentraSecuenciaTarea(
	            @Param("cveEntidad") String cveEntidad,
	            @Param("cveProceso") String cveProceso,
	            @Param("version") BigDecimal version,
	            @Param("cveNodo") String cveNodo,
	            @Param("idNodo") BigDecimal idNodo);

	@Query(value = "SELECT 1 " +
		       "FROM IN_TAREA_PROCESO itp " +
		       "WHERE itp.CVE_ENTIDAD = :cveEntidad " +
		       "AND itp.CVE_PROCESO = :cveProceso " +
		       "AND itp.VERSION = :version " +
		       "AND itp.CVE_INSTANCIA = :cveInstancia " +
		       "AND itp.SECUENCIA_TAREA = :secuenciaTarea", nativeQuery = true)
		Integer existeInTareaProceso(
		        @Param("cveEntidad") String cveEntidad,
		        @Param("cveProceso") String cveProceso,
		        @Param("version") BigDecimal version,
		        @Param("cveInstancia") String cveInstancia,
		        @Param("secuenciaTarea") Integer secuenciaTarea);

}
