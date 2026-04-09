package com.briomax.briobpm.persistence.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.briomax.briobpm.persistence.entity.TarjetaAreaTrabajo;
import com.briomax.briobpm.persistence.entity.TarjetaAreaTrabajoPK;

@Repository
public interface TarjetaAreaTrabajoTRepository extends JpaRepository<TarjetaAreaTrabajo, TarjetaAreaTrabajoPK>{

	 @Query(value = "SELECT * " +
	    		"FROM TARJETA_AREA_TRABAJO		TAT " +
	            "WHERE TAT.CVE_ENTIDAD = :cveEntidad " +
	            "	AND TAT.CVE_PROCESO = :cveProceso " +
	            "	AND TAT.VERSION = :version " +
	            "	AND TAT.CVE_AREA_TRABAJO = :cveAreaTrabajo " +
	            "ORDER BY TAT.SECUENCIA_TARJETA", nativeQuery = true)
	    List<TarjetaAreaTrabajo> obtieneTarjetasAreaTrabajo(
	    		@Param("cveEntidad") String cveEntidad,
	             @Param("cveProceso") String cveProceso,
	             @Param("version") BigDecimal version,
	             @Param("cveAreaTrabajo") String cveAreaTrabajo);
	           

}
