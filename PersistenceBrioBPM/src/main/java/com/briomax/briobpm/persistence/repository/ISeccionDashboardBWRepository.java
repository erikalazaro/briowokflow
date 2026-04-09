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

import com.briomax.briobpm.persistence.entity.SeccionDashboardBW;
import com.briomax.briobpm.persistence.entity.SeccionDashboardBWPK;

/**
 * El objetivo de la Interface ISeccionDashboardBWRepository.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Oct 31, 2024 Modificaciones:
 * @since JDK 1.8
 */
@Repository
public interface ISeccionDashboardBWRepository extends JpaRepository<SeccionDashboardBW, SeccionDashboardBWPK> {

	@Query("SELECT s FROM SeccionDashboardBW s WHERE s.id.cveEntidad = :cveEntidad AND s.id.cveDashboard = :cveDashboard AND s.titulo = :titulo")
	List<SeccionDashboardBW> encuentraSecciones(@Param("cveEntidad") String cveEntidad,
	                                            @Param("cveDashboard") String cveDashboard,
	                                            @Param("titulo") String titulo);

	@Query(value = "SELECT " +
	        "    S.ORDEN_SECCION AS ORDEN_SECCION, " +
	        "    S.SECUENCIA_SECCION AS SECUENCIA_SECCION, " +
	        "    S.TITULO AS TITULO_SECCION, " +
	        "    SS.ORDEN_SUB_SECCION AS ORDEN_SUB_SECCION, " +
	        "    SS.SECUENCIA_SUB_SECCION AS SECUENCIA_SUB_SECCION, " +
	        "    SS.TITULO AS TITULO_SUB_SECCION, " +
	        "    SSS.ORDEN_SUB_SUB_SECCION AS ORDEN_SUB_SUB_SECCION, " +
	        "    SSS.SECUENCIA_SUB_SUB_SECCION AS SECUENCIA_SUB_SUB_SECCION, " +
	        "    SSS.TITULO AS TITULO_SUB_SUB_SECCION, " +
	        "    SSS.TIPO_PRESENTACION AS TIPO_PRESENTACION, " +
	        "    SSS.ETIQUETA_EJE_X AS ETIQUETA_EJE_X, " +
	        "    SSS.ETIQUETA_EJE_Y1 AS ETIQUETA_EJE_Y1, " +
	        "    SSS.ETIQUETA_EJE_Y2 AS ETIQUETA_EJE_Y2, " +
	        "    SSS.TITULO_ACOTACIONES AS TITULO_ACOTACIONES, " +
	        "    SD.ORDEN_SERIE AS ORDEN_SERIE, " +
	        "    SD.NUMERO_SERIE AS NUMERO_SERIE, " +
	        "    SD.TITULO AS TITULO_SERIE, " +
	        "    SD.ESCALA AS ESCALA " +
	        "FROM " +
	        "    SECCION_DASHBOARD_BW S, " +
	        "    SUB_SECCION_DASHBOARD_BW SS, " +
	        "    SUB_SUB_SECCION_DASHBOARD_BW SSS, " +
	        "    SERIE_DASHBOARD_BW SD " +
	        "WHERE " +
	        "    S.CVE_ENTIDAD = :cveEntidad " +
	        "    AND S.CVE_DASHBOARD = :cveDashboard " +
	        "    AND SS.CVE_ENTIDAD = S.CVE_ENTIDAD " +
	        "    AND SS.CVE_DASHBOARD = S.CVE_DASHBOARD " +
	        "    AND SS.SECUENCIA_SECCION = S.SECUENCIA_SECCION " +
	        "    AND SSS.CVE_ENTIDAD = SS.CVE_ENTIDAD " +
	        "    AND SSS.CVE_DASHBOARD = SS.CVE_DASHBOARD " +
	        "    AND SSS.SECUENCIA_SECCION = SS.SECUENCIA_SECCION " +
	        "    AND SSS.SECUENCIA_SUB_SECCION = SS.SECUENCIA_SUB_SECCION " +
	        "    AND SD.CVE_ENTIDAD = SSS.CVE_ENTIDAD " +
	        "    AND SD.CVE_DASHBOARD = SSS.CVE_DASHBOARD " +
	        "    AND SD.SECUENCIA_SECCION = SSS.SECUENCIA_SECCION " +
	        "    AND SD.SECUENCIA_SUB_SECCION = SSS.SECUENCIA_SUB_SECCION " +
	        "    AND SD.SECUENCIA_SUB_SUB_SECCION = SSS.SECUENCIA_SUB_SUB_SECCION " +
	        "ORDER BY " +
	        "    S.ORDEN_SECCION, " +
	        "    SS.ORDEN_SUB_SECCION, " +
	        "    SSS.ORDEN_SUB_SUB_SECCION, " +
	        "    SD.ORDEN_SERIE, " +
	        "    SD.NUMERO_SERIE", 
	        nativeQuery = true)
	List<Object[]> obtieneSecciones(@Param("cveEntidad") String cveEntidad, 
	                                @Param("cveDashboard") String cveDashboard);

	@Query(value = "SELECT " +
            "    S.* " + 
            "FROM " +
            "    SECCION_DASHBOARD_BW S " +
            "WHERE " +
            "    S.CVE_ENTIDAD = :cveEntidad " +
            "    AND S.CVE_DASHBOARD = :cveDashboard " +
            "ORDER BY " +
            "    S.ORDEN_SECCION", nativeQuery = true)
	List<SeccionDashboardBW> getSecciones(@Param("cveEntidad") String cveEntidad, 
	                                      @Param("cveDashboard") String cveDashboard);

	
	@Query(value = "SELECT " +
	        "    S.ORDEN_SECCION AS ORDEN_SECCION, " +
	        "    S.SECUENCIA_SECCION AS SECUENCIA_SECCION, " +
	        "    S.TITULO AS TITULO_SECCION, " +
	        "    SS.ORDEN_SUB_SECCION AS ORDEN_SUB_SECCION, " +
	        "    SS.SECUENCIA_SUB_SECCION AS SECUENCIA_SUB_SECCION, " +
	        "    SS.TITULO AS TITULO_SUB_SECCION, " +
	        "    SSS.ORDEN_SUB_SUB_SECCION AS ORDEN_SUB_SUB_SECCION, " +
	        "    SSS.SECUENCIA_SUB_SUB_SECCION AS SECUENCIA_SUB_SUB_SECCION, " +
	        "    SSS.TITULO AS TITULO_SUB_SUB_SECCION, " +
	        "    SSS.TIPO_PRESENTACION AS TIPO_PRESENTACION, " +
	        "    SSS.ETIQUETA_EJE_X AS ETIQUETA_EJE_X, " +
	        "    SSS.ETIQUETA_EJE_Y1 AS ETIQUETA_EJE_Y1, " +
	        "    SSS.ETIQUETA_EJE_Y2 AS ETIQUETA_EJE_Y2, " +
	        "    SSS.TITULO_ACOTACIONES AS TITULO_ACOTACIONES, " +
	        "    SD.ORDEN_SERIE AS ORDEN_SERIE, " +
	        "    SD.NUMERO_SERIE AS NUMERO_SERIE, " +
	        "    SD.TITULO AS TITULO_SERIE, " +
	        "    SD.ESCALA AS ESCALA " +
	        "FROM " +
	        "    SECCION_DASHBOARD_BW S, " +
	        "    SUB_SECCION_DASHBOARD_BW SS, " +
	        "    SUB_SUB_SECCION_DASHBOARD_BW SSS, " +
	        "    SERIE_DASHBOARD_BW SD " +
	        "WHERE " +
	        "    S.CVE_ENTIDAD = :cveEntidad " +
	        "    AND S.CVE_DASHBOARD = :cveDashboard " +
	        "    AND S.SECUENCIA_SECCION = :secuenciaSeccion " +
	        "    AND SS.CVE_ENTIDAD = S.CVE_ENTIDAD " +
	        "    AND SS.CVE_DASHBOARD = S.CVE_DASHBOARD " +
	        "    AND SS.SECUENCIA_SECCION = S.SECUENCIA_SECCION " +
	        "    AND SSS.CVE_ENTIDAD = SS.CVE_ENTIDAD " +
	        "    AND SSS.CVE_DASHBOARD = SS.CVE_DASHBOARD " +
	        "    AND SSS.SECUENCIA_SECCION = SS.SECUENCIA_SECCION " +
	        "    AND SSS.SECUENCIA_SUB_SECCION = SS.SECUENCIA_SUB_SECCION " +
	        "    AND SD.CVE_ENTIDAD = SSS.CVE_ENTIDAD " +
	        "    AND SD.CVE_DASHBOARD = SSS.CVE_DASHBOARD " +
	        "    AND SD.SECUENCIA_SECCION = SSS.SECUENCIA_SECCION " +
	        "    AND SD.SECUENCIA_SUB_SECCION = SSS.SECUENCIA_SUB_SECCION " +
	        "    AND SD.SECUENCIA_SUB_SUB_SECCION = SSS.SECUENCIA_SUB_SUB_SECCION " +
	        "ORDER BY " +
	        "    S.ORDEN_SECCION, " +
	        "    SS.ORDEN_SUB_SECCION, " +
	        "    SSS.ORDEN_SUB_SUB_SECCION, " +
	        "    SD.ORDEN_SERIE, " +
	        "    SD.NUMERO_SERIE", 
	        nativeQuery = true)
	List<Object[]> obtieneDatosPorSeccion(@Param("cveEntidad") String cveEntidad, 
	                                @Param("cveDashboard") String cveDashboard,
	                                @Param("secuenciaSeccion") int secuenciaSeccion);

}
