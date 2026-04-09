package com.briomax.briobpm.persistence.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.briomax.briobpm.persistence.entity.StValorVariable;
import com.briomax.briobpm.persistence.entity.StValorVariablePK;

/**
 * El objetivo de la Interface IStValorVariableRepository.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Abr 10, 2024 Modificaciones:
 * @since JDK 1.8
 */
@Repository
public interface IStValorVariableRepository extends JpaRepository<StValorVariable, StValorVariablePK> {

	@Query(value = "SELECT 1 " +
    		"FROM ST_VALOR_VARIABLE			VV " +
            "WHERE  VV.CVE_ENTIDAD = :cveEntidad"+ 
    		"		AND VV.CVE_PROCESO = :cveProceso " +
            "		AND VV.VERSION = :version " +
            "		AND VV.CVE_VARIABLE = :cveVariable ", nativeQuery = true)
    	Integer validaValorVariabless(
    		 @Param("cveEntidad") String cveEntidad,
             @Param("cveProceso") String cveProceso,
             @Param("version") BigDecimal version,
             @Param("cveVariable") String cveVariable);
	
	@Query(value = "SELECT " + 
	        "		:cveVariable AS CVE_DATO, " + 
	        "		VV.VALOR_ALFANUMERICO AS VALOR_ALFANUMERICO, " + 
	        "		VV.ETIQUETA_LISTA AS ETIQUETA " + 
	        "FROM ST_VALOR_VARIABLE VV " + 
	        "WHERE  VV.CVE_ENTIDAD = :cveEntidad " + 
	        "		AND VV.CVE_PROCESO = :cveProceso " +
	        "		AND VV.VERSION = :version " +
	        "		AND VV.CVE_VARIABLE = :cveVariable " + 
	        "ORDER BY VV.SECUENCIA", nativeQuery = true)
	List<Object> regresaValorVariables(
	         @Param("cveEntidad") String cveEntidad,
	         @Param("cveProceso") String cveProceso,
	         @Param("version") BigDecimal version,
	         @Param("cveVariable") String cveVariable);
	
	
	@Query(value = "SELECT * " +
    		"FROM ST_VALOR_VARIABLE			VV " +
            "WHERE  VV.CVE_ENTIDAD = :cveEntidad"+ 
    		"		AND VV.CVE_PROCESO = :cveProceso " +
            "		AND VV.VERSION = :version " +
            "		AND VV.CVE_VARIABLE = :cveVariable " +
            "		AND VV.ETIQUETA_LISTA = :valor ", nativeQuery = true)
	Optional<StValorVariable> encuentraValor(
			@Param("cveEntidad") String cveEntidad,
			@Param("cveProceso") String cveProceso,
			@Param("version") BigDecimal version,
			@Param("cveVariable") String cveVariable,
			@Param("valor") String valor);

	@Query(value = "SELECT * " +
    		"FROM ST_VALOR_VARIABLE			VV " +
            "WHERE  VV.CVE_ENTIDAD = :cveEntidad"+ 
    		"		AND VV.CVE_PROCESO = :cveProceso " +
            "		AND VV.VERSION = :version " +
            "		AND VV.CVE_VARIABLE = :cveVariable " +
            "		AND VV.VALOR_ALFANUMERICO = :valor ", nativeQuery = true)
	StValorVariable encuentraEtiqueta(
			@Param("cveEntidad") String cveEntidad,
			@Param("cveProceso") String cveProceso,
			@Param("version") BigDecimal version,
			@Param("cveVariable") String cveVariable,
			@Param("valor") String valor);
}
