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

import com.briomax.briobpm.persistence.entity.StRolProceso;
import com.briomax.briobpm.persistence.entity.StRolProcesoPK;

/**
 * El objetivo de la Interface IStRolProcesoRepository.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Jun 26, 2020 1:07:37 PM Modificaciones:
 * @since JDK 1.8
 */
@Repository
public interface IStRolProcesoRepository extends JpaRepository<StRolProceso, StRolProcesoPK> {

	@Query(value = "SELECT * FROM ST_ROL_PROCESO p " + 
			" WHERE	p.CVE_ENTIDAD = :cveEntidad AND " +
			" p.CVE_ROL = :cveRol ", nativeQuery = true)
	List<StRolProceso> listaRpese( @Param("cveEntidad") String cveEntidad, 
			@Param("cveRol") String cveRol);
}
