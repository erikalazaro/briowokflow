package com.briomax.briobpm.persistence.repository;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.briomax.briobpm.persistence.entity.StVariableEnvio;
import com.briomax.briobpm.persistence.entity.StVariableEnvioPK;

/**
 * El objetivo de la Interface InVariableProcesoRepository.java es ...
 * @author Alexis Zamoea 
 * @version 1.0 Fecha de creacion Dic 11, 2023 Modificaciones:
 * @since JDK 1.8
 */
public interface IStVariableEnvioRepository extends JpaRepository<StVariableEnvio, StVariableEnvioPK>{
	
	@Query(
			value = "SELECT 1 " +
	  "FROM ST_VARIABLE_ENVIO STVE " +
	  "WHERE STVE.CVE_ENTIDAD = :cveEntidad " +
	  "AND STVE.CVE_PROCESO = :cveProceso " +
	  "AND STVE.VERSION = :version " +
	  "AND STVE.CVE_NODO = :cveNodo " +
	  "AND STVE.ID_NODO = :idNodo " , nativeQuery = true)
	Integer verificaExisteVariables(
			@Param("cveEntidad") String cveEntidad,
			@Param("cveProceso") String cveProceso,
			@Param("version") BigDecimal version,
			@Param("cveNodo") String cveNodo,
			@Param("idNodo") Integer idNodo);
	
	
	@Query(
			value = "SELECT * " +
	  "FROM ST_VARIABLE_ENVIO STVE " +
	  "WHERE STVE.CVE_ENTIDAD = :cveEntidad " +
	  "AND STVE.CVE_PROCESO = :cveProceso " +
	  "AND STVE.VERSION = :version " +
	  "AND STVE.CVE_NODO = :cveNodo " +
	  "AND STVE.ID_NODO = :idNodo " , nativeQuery = true)
	ArrayList<StVariableEnvio> variablesParaAplicarReemplazo(
			@Param("cveEntidad") String cveEntidad,
			@Param("cveProceso") String cveProceso,
			@Param("version") BigDecimal version,
			@Param("cveNodo") String cveNodo,
			@Param("idNodo") Integer idNodo);

}
