package com.briomax.briobpm.persistence.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.briomax.briobpm.persistence.entity.StSeccionProceso;
import com.briomax.briobpm.persistence.entity.StSeccionProcesoPK;

/**
 * El objetivo de la Interface IStSeccionProcesoRepository.java es ...
 * @author Sara Ventura
 * @version 1.0 Fecha de creacion Nov 23, 2023 Modificaciones:
 * @since JDK 1.8
 */
@Repository
public interface IStSeccionProcesoRepository extends JpaRepository<StSeccionProceso, StSeccionProcesoPK> {
	@Query(value = "SELECT CVE_SECCION FROM ST_SECCION_PROCESO WHERE CVE_ENTIDAD = :cveEntidad AND CVE_PROCESO = :cveProceso AND VERSION = :version AND CVE_SECCION = :cveSeccion", nativeQuery = true)
	String encontrar(
	    @Param("cveEntidad") String cveEntidad,
	    @Param("cveProceso") String cveProceso,
	    @Param("version") BigDecimal version,
	    @Param("cveSeccion") String cveSeccion
	);

}
