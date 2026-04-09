package com.briomax.briobpm.persistence.repository;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.briomax.briobpm.persistence.entity.AreaTrabajo;
import com.briomax.briobpm.persistence.entity.AreaTrabajoPK;

@Repository
public interface IAreaTrabajoRepository extends JpaRepository<AreaTrabajo, AreaTrabajoPK>{

	
	@Query(value = "SELECT * " +
    		"FROM AREA_TRABAJO		AT " +
            "WHERE AT.CVE_ENTIDAD = :cveEntidad " +
    		"   AND AT.CVE_PROCESO = :cveProceso " +
            "	AND AT.VERSION = :version " +
            "	AND AT.CVE_AREA_TRABAJO = :cveAreaTrabajo " +
            "	AND AT.SITUACION_AREA_TRABAJO = 'HABILITADO' "
    		, nativeQuery = true)
	Optional<AreaTrabajo> obtieneAreasTrabajo(
			 @Param("cveEntidad") String cveEntidad,
			 @Param("cveProceso") String cveProceso,
             @Param("version") BigDecimal version,
			 @Param("cveAreaTrabajo") String cveAreaTrabajo);
}
