package com.briomax.briobpm.persistence.repository;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.briomax.briobpm.persistence.entity.StVariableProceso;
import com.briomax.briobpm.persistence.entity.StVariableProcesoPK;

/**
 * El objetivo de la Interface StValorInicialVariable.java es ...
 * @author Javier Zamora
 * @version 1.0 Fecha de creacion Dic 11, 2023 Modificaciones:
 * @since JDK 1.8
 */
@Repository
public interface IStVariableProcesoRepository extends JpaRepository<StVariableProceso, StVariableProcesoPK> {

	@Query(value = "SELECT * " +
	           "FROM ST_VARIABLE_PROCESO STVP " +
	           "WHERE STVP.CVE_PROCESO = :cveProceso " +
	           "AND STVP.VERSION = :version " +
	           "AND STVP.CVE_VARIABLE = :cveVariable ", nativeQuery = true)
	Optional<StVariableProceso> obtenerStVariableP(@Param("cveProceso") String cveProceso,
	                                               @Param("version") BigDecimal version,
	                                               @Param("cveVariable") String cveVariable);

	@Query(value = "SELECT 1 " +
            "FROM ST_VARIABLE_PROCESO STVP " +
            "WHERE STVP.CVE_ENTIDAD = :cveEntidad " +
            "  AND STVP.CVE_PROCESO = :cveProceso " +
            "  AND STVP.VERSION = :version " +
            "  AND STVP.CVE_VARIABLE = :cveVariable " +
            "  AND STVP.TIPO_CONTROL IN ('LISTBOX', 'RADIOBUTTON', 'CHECKBOX')", nativeQuery = true)
	Integer findRecord(@Param("cveEntidad") String cveEntidad,
                @Param("cveProceso") String cveProceso,
                @Param("version") BigDecimal version,
                @Param("cveVariable") String cveVariable);
	
	 @Query("SELECT v.decimales " +
	           "FROM StVariableProceso v " +
	           "WHERE v.id.cveEntidad = :cveEntidad " +
	           "AND v.id.cveProceso = :cveProceso " +
	           "AND v.id.version = :version " +
	           "AND v.id.cveVariable = :cveVariable")
	    Integer obtenerDecimales(@Param("cveEntidad") String cveEntidad,
	                             @Param("cveProceso") String cveProceso,
	                             @Param("version") BigDecimal version,
	                             @Param("cveVariable") String cveVariable);
	
}
