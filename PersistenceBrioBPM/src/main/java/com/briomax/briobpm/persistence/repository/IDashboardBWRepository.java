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

import com.briomax.briobpm.persistence.entity.DashboardBW;
import com.briomax.briobpm.persistence.entity.DashboardBWPK;

/**
 * El objetivo de la Interface IDashboardBWRepository.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Oct 31, 2024 Modificaciones:
 * @since JDK 1.8
 */
@Repository
public interface IDashboardBWRepository extends JpaRepository<DashboardBW, DashboardBWPK> {
	
	@Query(value = 
	        "SELECT D.CVE_DASHBOARD, " +
	        "       D.TITULO " +
	        "FROM DASHBOARD_BW D, USUARIO_DASHBOARD_BW UD " +
	        "WHERE D.CVE_ENTIDAD = :cveEntidad " +
	        "  AND D.DASHBOARD_HABILITADO = :habilitado " +
	        "  AND UD.CVE_ENTIDAD = D.CVE_ENTIDAD " +
	        "  AND UD.CVE_DASHBOARD = D.CVE_DASHBOARD " +
	        "  AND UD.CVE_USUARIO = :cveUsuario " +
	        "  AND ( " +
	        "      (:destino = :destinoWeb AND ( " +
	        "          (:ubicacionLista = :ubicacionInicio AND D.DESTINO_WEB IN (:ubicacionInicio, :ubicacionInicioMenu)) " +
	        "          OR " +
	        "          (:ubicacionLista = :ubicacionMenu AND D.DESTINO_WEB IN (:ubicacionMenu, :ubicacionInicioMenu)) " +
	        "      )) OR " +
	        "     (:destino = :destinoApp AND ( " +
	        "          (:ubicacionLista = :ubicacionInicio AND D.DESTINO_APP IN (:ubicacionInicio, :ubicacionInicioMenu)) " +
	        "          OR " +
	        "          (:ubicacionLista = :ubicacionMenu AND D.DESTINO_APP IN (:ubicacionMenu, :ubicacionInicioMenu)) " +
	        "      )) " +
	        "  ) " , nativeQuery = true)
	List<Object[]> encuentraDashboards(
	        @Param("cveEntidad") String cveEntidad,
	        @Param("habilitado") String habilitado,
	        @Param("cveUsuario") String cveUsuario,
	        @Param("destino") String destino,
	        @Param("destinoWeb") String destinoWeb,
	        @Param("destinoApp") String destinoApp,
	        @Param("ubicacionLista") String ubicacionLista,
	        @Param("ubicacionInicio") String ubicacionInicio,
	        @Param("ubicacionMenu") String ubicacionMenu,
	        @Param("ubicacionInicioMenu") String ubicacionInicioMenu
	);
	
}
