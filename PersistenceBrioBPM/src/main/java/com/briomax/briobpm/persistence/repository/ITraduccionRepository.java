/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.briomax.briobpm.persistence.entity.Traduccion;
import com.briomax.briobpm.persistence.entity.TraduccionPK;

/**
 * El objetivo de la Interface ITraduccionRepository.java es ...
 * @author Sara Ventura
 * @version 1.0 Fecha de creacion Nov 07, 2023 Modificaciones:
 * @since JDK 1.8
 */
@Repository
public interface ITraduccionRepository extends JpaRepository<Traduccion, TraduccionPK> {

	@Query(value = "SELECT PALABRA_TRADUCIDA " +
			"FROM TRADUCCION " +
			"WHERE CVE_IDIOMA  = :cveIdioma		        AND " + 
			"				PALABRA_ORIGINAL = :cveProceso	 " , nativeQuery = true)
	String traducirEtiqueta(@Param("cveIdioma") String cveIdioma,
			@Param("cveProceso") String palabraOrigina);

}
