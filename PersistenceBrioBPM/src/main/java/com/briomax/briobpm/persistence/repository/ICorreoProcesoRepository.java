package com.briomax.briobpm.persistence.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.briomax.briobpm.persistence.entity.CorreoProceso;
import com.briomax.briobpm.persistence.entity.CorreoProcesoPK;

/**
 * El objetivo de la Interface IStProcesoRepository.java es ...
 * 
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Dic 22, 2023 12:30:03 PM:
 * @since JDK 1.8
 */
@Repository
public interface ICorreoProcesoRepository extends JpaRepository<CorreoProceso, CorreoProcesoPK>{
	
	@Query(value = "SELECT * " +
            "FROM CORREO_PROCESO " +
            "WHERE CVE_ENTIDAD = :cveEntidad " +
            "  AND CVE_PROCESO = :cveProceso " +
            "  AND VERSION = :version " +
            "  AND CVE_EVENTO_CORREO = :cveEventoCorreo " +
            "  AND (CVE_NODO IS NULL OR (CVE_NODO IS NOT NULL AND CVE_NODO = :cveNodo )) " +
            "  AND (ID_NODO IS NULL OR (ID_NODO IS NOT NULL	AND ID_NODO = :idNodo)) " +
            "ORDER BY SECUENCIA_CORREO", nativeQuery = true)
	List<CorreoProceso> obtenerCorreos(
			@Param("cveEntidad") String cveEntidad,
            @Param("cveProceso") String cveProceso,
            @Param("version") BigDecimal version,
            @Param("cveEventoCorreo") String cveEventoCorreo,
            @Param("cveNodo") String cveNodo,
            @Param("idNodo") Integer idNodo);

}
