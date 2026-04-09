/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.persistence.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.briomax.briobpm.persistence.entity.StValorInicialVariable;
import com.briomax.briobpm.persistence.entity.StValorInicialVariablePK;

/**
 * El objetivo de la Interface StValorInicialVariable.java es ...
 * @author Sara Ventura 
 * @version 1.0 Fecha de creacion Nov 10, 2023 Modificaciones:
 * @since JDK 1.8
 */
@Repository
public interface IStValorInicialVariableRepository extends JpaRepository<StValorInicialVariable, StValorInicialVariablePK> {
	
	/**
	 * 
	 * @param cveEntidad
	 * @param cveProceso
	 * @param version
	 * @return
	 */
	@Query(value = "SELECT * FROM ST_VALOR_INICIAL_VARIABLE SVIV,   " + 
			"			ST_VARIABLE_PROCESO STVP " + 
			"		WHERE SVIV.CVE_ENTIDAD = STVP.CVE_ENTIDAD " + 
			"		 AND SVIV.CVE_PROCESO = STVP.CVE_PROCESO " + 
			"		 AND SVIV.VERSION = STVP.VERSION " + 
			"		 AND SVIV.CVE_VARIABLE = STVP.CVE_VARIABLE " + 
			"		 AND STVP.TIPO != 'IMAGEN' " + 
			"		 AND SVIV.CVE_ENTIDAD = :cveEntidad   " + 
			"        AND SVIV.CVE_PROCESO = :cveProceso  " + 
			"        AND SVIV.VERSION = :version " +
			"        AND SVIV.NIVEL_INICIALIZACION = 'PROCESO' ", nativeQuery = true)
	public List<StValorInicialVariable> findValorInicialVariableSIByParam(@Param("cveEntidad") String cveEntidad, 
                                                                 @Param("cveProceso") String cveProceso, 
                                                                 @Param("version") BigDecimal version);

	/**
	 * 
	 * @param cveEntidad
	 * @param cveProceso
	 * @param version
	 * @return
	 */
	@Query(value = "SELECT * FROM ST_VALOR_INICIAL_VARIABLE SVIV,   " + 
			"			ST_VARIABLE_PROCESO STVP " + 
			"		WHERE SVIV.CVE_ENTIDAD = STVP.CVE_ENTIDAD " + 
			"		 AND SVIV.CVE_PROCESO = STVP.CVE_PROCESO " + 
			"		 AND SVIV.VERSION = STVP.VERSION " + 
			"		 AND SVIV.CVE_VARIABLE = STVP.CVE_VARIABLE " + 
			"		 AND STVP.TIPO = 'IMAGEN' " + 
			"		 AND SVIV.CVE_ENTIDAD = :cveEntidad   " + 
			"        AND SVIV.CVE_PROCESO = :cveProceso  " + 
			"        AND SVIV.VERSION = :version " +
			"        AND SVIV.NIVEL_INICIALIZACION = 'PROCESO' ", nativeQuery = true)
	public List<StValorInicialVariable> findValorInicialVariableCIByParam(@Param("cveEntidad") String cveEntidad, 
                                                                 @Param("cveProceso") String cveProceso, 
                                                                 @Param("version") BigDecimal version);
	
	/**
	 * 
	 * @param cveEntidad
	 * @param cveProceso
	 * @param version
	 * @param referencia
	 * @return
	 */
	@Query(value = "SELECT * FROM ST_VALOR_INICIAL_VARIABLE SVIV,   " + 
			"			ST_VARIABLE_PROCESO STVP " + 
			"		WHERE SVIV.CVE_ENTIDAD = STVP.CVE_ENTIDAD " + 
			"		 AND SVIV.CVE_PROCESO = STVP.CVE_PROCESO " + 
			"		 AND SVIV.VERSION = STVP.VERSION " + 
			"		 AND SVIV.CVE_VARIABLE = STVP.CVE_VARIABLE " + 
			"		 AND STVP.TIPO != 'IMAGEN' " + 
			"		 AND SVIV.CVE_ENTIDAD = :cveEntidad   " + 
			"        AND SVIV.CVE_PROCESO = :cveProceso  " + 
			"        AND SVIV.VERSION = :version " +
			"        AND SVIV.NIVEL_INICIALIZACION = 'NODO' " +
			"		 AND (SVIV.REFERENCIA_1 = :referencia OR SVIV.REFERENCIA_2 = :referencia) " +
			"		 AND SVIV.CVE_VARIABLE NOT IN ( SELECT CVE_VARIABLE FROM IN_VARIABLE_PROCESO " +
			" 			WHERE CVE_ENTIDAD = :cveEntidad AND CVE_PROCESO = :cveProceso " +
			"			AND VERSION = :version AND CVE_INSTANCIA = :instancia )	", nativeQuery = true)
	public List<StValorInicialVariable> findValIniVariableSIByParam(@Param("cveEntidad") String cveEntidad, 
                                                                 @Param("cveProceso") String cveProceso, 
                                                                 @Param("version") BigDecimal version,
                                                                 @Param("referencia") String referencia,
                                                                 @Param("instancia") String instancia);

	/**
	 * 
	 * @param cveEntidad
	 * @param cveProceso
	 * @param version
	 * @param referencia
	 * @return
	 */
	@Query(value = "SELECT * FROM ST_VALOR_INICIAL_VARIABLE SVIV,   " + 
			"			ST_VARIABLE_PROCESO STVP " + 
			"		WHERE SVIV.CVE_ENTIDAD = STVP.CVE_ENTIDAD " + 
			"		 AND SVIV.CVE_PROCESO = STVP.CVE_PROCESO " + 
			"		 AND SVIV.VERSION = STVP.VERSION " + 
			"		 AND SVIV.CVE_VARIABLE = STVP.CVE_VARIABLE " + 
			"		 AND STVP.TIPO = 'IMAGEN' " + 
			"		 AND SVIV.CVE_ENTIDAD = :cveEntidad   " + 
			"        AND SVIV.CVE_PROCESO = :cveProceso  " + 
			"        AND SVIV.VERSION = :version " +
			"        AND SVIV.NIVEL_INICIALIZACION = 'NODO' " +
			"		 AND (SVIV.REFERENCIA_1 = :referencia OR SVIV.REFERENCIA_2 = :referencia) " +
			"		 AND SVIV.CVE_VARIABLE NOT IN ( SELECT CVE_VARIABLE FROM IN_VARIABLE_PROCESO " +
			" 			WHERE CVE_ENTIDAD = :cveEntidad AND CVE_PROCESO = :cveProceso " +
			"			AND VERSION = :version AND CVE_INSTANCIA = :instancia )	", nativeQuery = true) 
	public List<StValorInicialVariable> findValIniVariableCIByParam(@Param("cveEntidad") String cveEntidad, 
                                                                 @Param("cveProceso") String cveProceso, 
                                                                 @Param("version") BigDecimal version,
                                                                 @Param("referencia") String referencia,
                                                                 @Param("instancia") String instancia);
}
