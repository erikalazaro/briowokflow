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

import com.briomax.briobpm.persistence.entity.ParametroGeneral;

/**
 * El objetivo de la Interface ICalendarioEspecialRepository.java es ...
 * @author Sara Ventura
 * @version 1.0 Fecha de creacion Dic 04, 2023 Modificaciones:
 * @since JDK 1.8
 */
@Repository
public interface IParametroGeneralRepository extends JpaRepository<ParametroGeneral, String> {
	
	@Query(value = "SELECT * FROM  PARAMETRO_GENERAL " + 
			" WHERE	CVE_PARAMETRO like :parametro ", nativeQuery = true)
	List<ParametroGeneral> documetosObligatorios(@Param("parametro") String parametro);
	

	@Query(value = "SELECT COUNT(1) FROM PARAMETRO_GENERAL " + 
			"	WHERE CVE_PARAMETRO LIKE 'DOC_OBLIGATORIO%' " + 
			"	AND VALOR_ALFANUMERICO LIKE :proceso ", nativeQuery = true)
	int documetosObligatoriosCorreo(@Param("proceso") String proceso);
	
}
