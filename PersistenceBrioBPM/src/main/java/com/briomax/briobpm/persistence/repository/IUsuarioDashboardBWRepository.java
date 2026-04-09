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

import com.briomax.briobpm.persistence.entity.UsuarioDashboardBW;
import com.briomax.briobpm.persistence.entity.UsuarioDashboardBWPK;

/**
 * El objetivo de la Interface IUsuarioDashboardBWRepository.java.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Oct 31, 2024 Modificaciones:
 * @since JDK 1.8
 */
@Repository
public interface IUsuarioDashboardBWRepository extends JpaRepository<UsuarioDashboardBW, UsuarioDashboardBWPK> {

	@Query("SELECT ud.id.cveDashboard FROM UsuarioDashboardBW ud WHERE ud.id.cveEntidad = :cveEntidad AND ud.id.cveUsuario = :cveUsuario")
	List<String> encuentraCveDashboards(
	    @Param("cveEntidad") String cveEntidad,
	    @Param("cveUsuario") String cveUsuario
	);

}