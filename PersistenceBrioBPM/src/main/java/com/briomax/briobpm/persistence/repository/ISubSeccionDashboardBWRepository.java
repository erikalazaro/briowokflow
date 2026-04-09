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

import com.briomax.briobpm.persistence.entity.SubSeccionDashboardBW;
import com.briomax.briobpm.persistence.entity.SubSeccionDashboardBWPK;

/**
 * El objetivo de la Interface ISeccionDashboardBWRepository.java.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Oct 31, 2024 Modificaciones:
 * @since JDK 1.8
 */
@Repository
public interface ISubSeccionDashboardBWRepository extends JpaRepository<SubSeccionDashboardBW, SubSeccionDashboardBWPK> {

	  @Query("SELECT s FROM SubSeccionDashboardBW s WHERE s.id.cveEntidad = :cveEntidad AND s.id.cveDashboard = :cveDashboard AND s.id.secuenciaSeccion = :secuenciaSeccion")
	    List<SubSeccionDashboardBW> encuentraSubSecuencias(@Param("cveEntidad") String cveEntidad, 
	                                         @Param("cveDashboard") String cveDashboard, 
	                                         @Param("secuenciaSeccion") Integer secuenciaSeccion);

	  @Query("SELECT s FROM SubSeccionDashboardBW s " +
		       "WHERE s.id.cveEntidad = :cveEntidad " +
		       "AND s.id.cveDashboard = :cveDashboard " +
		       "AND s.id.secuenciaSeccion = :secuenciaSeccion " +
		       "ORDER BY s.ordenSubSeccion")
		List<SubSeccionDashboardBW> encuentraSubSecuenciasConOrden(@Param("cveEntidad") String cveEntidad, 
		                                                           @Param("cveDashboard") String cveDashboard, 
		                                                           @Param("secuenciaSeccion") Integer secuenciaSeccion);
}
