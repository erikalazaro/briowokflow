/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.briomax.briobpm.persistence.entity.DatoDashboardBW;
import com.briomax.briobpm.persistence.entity.DatoDashboardBWPK;

/**
 * El objetivo de la Interface IDatoDashboardBWRepository.java.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Oct 31, 2024 Modificaciones:
 * @since JDK 1.8
 */
@Repository
public interface IDatoDashboardBWRepository extends JpaRepository<DatoDashboardBW, DatoDashboardBWPK> {

	@Query("SELECT d " +
		       "FROM DatoDashboardBW d " +
		       "WHERE d.id.cveEntidad = :cveEntidad " +
		       "AND d.id.cveDashboard = :cveDashboard " +
		       "AND d.id.secuenciaSeccion = :secuenciaSeccion " +
		       "AND d.id.secuenciaSubSeccion = :secuenciaSubSeccion " +
		       "AND d.id.secuenciaSubSubSeccion = :secuenciaSubSubSeccion " +
		       "AND d.id.numeroSerie = :numeroSerie " +
		       "ORDER BY d.id.numeroSerie, d.ordenDato")
		List<DatoDashboardBW> encuentraDatosDashboard(
		        @Param("cveEntidad") String cveEntidad, 
		        @Param("cveDashboard") String cveDashboard, 
		        @Param("secuenciaSeccion") Integer secuenciaSeccion, 
		        @Param("secuenciaSubSeccion") Integer secuenciaSubSeccion, 
		        @Param("secuenciaSubSubSeccion") Integer secuenciaSubSubSeccion,
		        @Param("numeroSerie") Integer numeroSerie
		);


}
