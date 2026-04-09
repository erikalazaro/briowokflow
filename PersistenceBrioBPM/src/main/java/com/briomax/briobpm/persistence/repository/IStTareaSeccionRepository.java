package com.briomax.briobpm.persistence.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.briomax.briobpm.persistence.entity.StTareaSeccion;
import com.briomax.briobpm.persistence.entity.StTareaSeccionPK;

/**
 * El objetivo de la Interface InVariableProcesoRepository.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Nov 23, 2023 Modificaciones:
 * @since JDK 1.8
 */
@Repository
public interface IStTareaSeccionRepository extends JpaRepository<StTareaSeccion, StTareaSeccionPK> {

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
	            @Param("idNodo") Integer idNodo);
	
	
	@Query(value = "SELECT STTS.SECUENCIA_TAREA AS SECUENCIA_TAREA, " +
            "STTP.DESCRIPCION AS DESCRIPCION_TAREA, " +
            "STTS.REQUERIDA AS REQUERIDA, " +
            "INTP.COMPLETADA AS COMPLETADA " +
            "FROM ST_TAREA_SECCION STTS, " +
            "ST_TAREA_PROCESO STTP, " +
            "IN_TAREA_PROCESO INTP " +
            "WHERE STTS.CVE_ENTIDAD = :cveEntidad " +
            "AND STTS.CVE_PROCESO = :cveProceso " +
            "AND STTS.VERSION = :version " +
            "AND STTS.CVE_NODO = :cveNodo " +
            "AND STTS.ID_NODO = :idNodo " +
            "AND STTS.CVE_SECCION = :cveSeccion " +
            "AND STTP.CVE_ENTIDAD = STTS.CVE_ENTIDAD " +
            "AND STTP.CVE_PROCESO = STTS.CVE_PROCESO " +
            "AND STTP.VERSION = STTS.VERSION " +
            "AND STTP.SECUENCIA_TAREA = STTS.SECUENCIA_TAREA " +
            "AND INTP.CVE_ENTIDAD = STTS.CVE_ENTIDAD " +
            "AND INTP.CVE_PROCESO = STTS.CVE_PROCESO " +
            "AND INTP.VERSION = STTS.VERSION " +
            "AND INTP.CVE_INSTANCIA = :cveInstancia " +
            "AND INTP.SECUENCIA_TAREA = STTP.SECUENCIA_TAREA " +
            "ORDER BY STTS.ORDEN_PRESENTACION", nativeQuery = true)
	List<Object[]> obtenerDetallesTareas(@Param("cveEntidad") String cveEntidad,
	                            @Param("cveProceso") String cveProceso,
	                            @Param("version") BigDecimal version,
	                            @Param("cveNodo") String cveNodo,
	                            @Param("idNodo") Integer integer,
	                            @Param("cveSeccion") String cveSeccion,
	                            @Param("cveInstancia") String cveInstancia);

}
