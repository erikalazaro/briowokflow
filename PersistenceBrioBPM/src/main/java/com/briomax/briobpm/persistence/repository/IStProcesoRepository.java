/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.persistence.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.briomax.briobpm.persistence.entity.StProceso;
import com.briomax.briobpm.persistence.entity.StProcesoPK;

/**
 * El objetivo de la Interface IStProcesoRepository.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Jun 25, 2020 12:30:03 PM Modificaciones:
 * @since JDK 1.8
 */
@Repository
public interface IStProcesoRepository extends JpaRepository<StProceso, StProcesoPK> {

	/**
	 * Find distinct procesos by entidad.
	 * @param cveEntidad el cve entidad.
	 * @return el procesos by entidad.
	 */
	@Query("SELECT DISTINCT p.id.cveProceso FROM StProceso p where p.id.cveEntidad =:cveEntidad AND p.situacion='HABILITADO' ")
	public List<String> findDistinctProcesosByEntidad(@Param("cveEntidad") String cveEntidad);
	
	   @Query(value = "SELECT * " +
		       "FROM ST_PROCESO " +
		        "WHERE CVE_ENTIDAD = :cveEntidad AND " +
		        "CVE_PROCESO = :cveProceso AND " +
		        "VERSION = :version AND " +
		        "SITUACION = :situacionHabilitado", nativeQuery = true)
	   Optional<StProceso> obtenerDatos(
		        @Param("cveEntidad") String cveEntidad,
		        @Param("cveProceso") String cveProceso,
		        @Param("version") BigDecimal version,
		        @Param("situacionHabilitado") String situacionHabilitado);


}
