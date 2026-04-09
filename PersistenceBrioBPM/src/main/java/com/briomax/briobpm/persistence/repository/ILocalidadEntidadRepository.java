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

import com.briomax.briobpm.persistence.entity.LocalidadEntidad;
import com.briomax.briobpm.persistence.entity.LocalidadEntidadPK;

/**
 * El objetivo de la Interface ILocalidadEntidadRepository.java es ...
 * @author Sara Ventura
 * @version 1.0 Fecha de creacion Nov 27, 2023 Modificaciones:
 * @since JDK 1.8
 */
@Repository
public interface ILocalidadEntidadRepository extends JpaRepository<LocalidadEntidad, LocalidadEntidadPK> {
	@Query(value = "SELECT	* "	+
			 "FROM LOCALIDAD_ENTIDAD				  LE,		"	+
		 	 "	   MONEDA							   M		"	+
		 	 "WHERE "	+
		 	 "	LE.CVE_ENTIDAD = 		 	  :cveEntidad		"	+
		 	 "	AND	LE.CVE_LOCALIDAD = 		:cveLocalidad		"	+
		 	 "	AND  M.CVE_MONEDA	= 		LE.CVE_MONEDA		"	,
		 	 nativeQuery = true)
List<LocalidadEntidad> recuperaDatosEntidad(
@Param("cveEntidad") String cveEntidad,
@Param("cveLocalidad") String cveLocalidad);
	
	
	@Query(value = "SELECT LOC.* FROM  " + 
			"	LOCALIDAD_ENTIDAD LOC, " + 
			"	ENTIDAD ENT	 " + 
			" WHERE SITUACION = 'HABILITADO' " + 
			"   AND ENT.CVE_ENTIDAD = LOC.CVE_ENTIDAD " + 
			"   AND ENT.CVE_IDIOMA = LOC.CVE_IDIOMA " + 
			"   AND ENT.CVE_MONEDA = LOC.CVE_MONEDA ", nativeQuery = true)
	List<LocalidadEntidad> entidadesHabilitadas();
}


