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

import com.briomax.briobpm.persistence.entity.SubSubSeccionDashboardBW;
import com.briomax.briobpm.persistence.entity.SubSubSeccionDashboardBWPK;

/**
 * El objetivo de la Interface ISubSubSeccionDashboardBWRepository.java.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Oct 31, 2024 Modificaciones:
 * @since JDK 1.8
 */
@Repository
public interface ISubSubSeccionDashboardBWRepository extends JpaRepository<SubSubSeccionDashboardBW, SubSubSeccionDashboardBWPK> {

	 @Query("SELECT s FROM SubSubSeccionDashboardBW s WHERE s.id.cveEntidad = :cveEntidad AND s.id.cveDashboard = :cveDashboard AND s.id.secuenciaSeccion = :secuenciaSeccion AND s.id.secuenciaSubSeccion = :subSecuenciaSeccion")
	    List<SubSubSeccionDashboardBW> encuentraSubSecciones(@Param("cveEntidad") String cveEntidad, 
	                                                         @Param("cveDashboard") String cveDashboard, 
	                                                         @Param("secuenciaSeccion") Integer secuenciaSeccion, 
	                                                         @Param("subSecuenciaSeccion") Integer subSecuenciaSeccion);

	 @Query("SELECT s.titulo FROM SubSubSeccionDashboardBW s WHERE s.id.cveEntidad = :cveEntidad AND s.id.cveDashboard = :cveDashboard AND s.id.secuenciaSeccion = :secuenciaSeccion AND s.id.secuenciaSubSeccion = :subSecuenciaSeccion AND s.id.secuenciaSubSubSeccion = :subsubSecuenciaSeccion ORDER BY s.ordenSubSubSeccion")
	 List<String> encontrarEtiquetas(@Param("cveEntidad") String cveEntidad, 
	                                 @Param("cveDashboard") String cveDashboard, 
	                                 @Param("secuenciaSeccion") Integer secuenciaSeccion, 
	                                 @Param("subSecuenciaSeccion") Integer subSecuenciaSeccion,
	                                 @Param("subsubSecuenciaSeccion") Integer subsubSecuenciaSeccion);


	  @Query("SELECT s FROM SubSubSeccionDashboardBW s " +
	           "WHERE s.id.cveEntidad = :cveEntidad " +
	           "AND s.id.cveDashboard = :cveDashboard " +
	           "AND s.id.secuenciaSeccion = :secuenciaSeccion " +
	           "AND s.id.secuenciaSubSeccion = :secuenciaSubSeccion " +
	           "ORDER BY s.ordenSubSubSeccion")
	    List<SubSubSeccionDashboardBW> encuentraSubSubSecuenciasConOrden(
	            @Param("cveEntidad") String cveEntidad, 
	            @Param("cveDashboard") String cveDashboard, 
	            @Param("secuenciaSeccion") Integer secuenciaSeccion,
	            @Param("secuenciaSubSeccion") Integer secuenciaSubSeccion);

}
