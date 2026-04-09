package com.briomax.briobpm.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.briomax.briobpm.persistence.entity.StVariableDependiente;
import com.briomax.briobpm.persistence.entity.StVariableDependientePK;


@Repository
public interface IStVariableDependienteRepository extends JpaRepository<StVariableDependiente, StVariableDependientePK> {
	
	@Query(value = "SELECT " +
			"STVD.SECCION_SELECT, " +
			"STVD.SECCION_FROM, "   +
			"STVD.SECCION_WHERE, "  +
			"STVD.CVE_VARIABLE_DEPENDIENTE " +
            "FROM ST_VARIABLE_DEPENDIENTE STVD " +
            "   WHERE " +
            "		STVD.CVE_ENTIDAD = :cveEntidad			         AND " +
            "       STVD.CVE_PROCESO = :cveProceso               AND " + 
            "       STVD.CVE_VARIABLE = :cveVariable        		  " /*   AND " +
            
            "       STVD.VERSION = :version                          AND " +
            "       STVD.CVE_NODO = :cveNodo           				 AND " +
            "		STVD.ID_NODO = :idNodo							 AND " + 
            "       STVD.CVE_SECCION = :cveSeccion        			 AND " +
            "       STVD.CVE_VARIABLE = :cveVariable        		 AND " +
            "       STVD.CVE_SECCION_DEPENDIENTE = :cveSeccionDependiente "*/, nativeQuery = true)
	

	List<Object[]> obtieneVariableDependiente(
			@Param("cveEntidad") String cveEntidad,
			@Param("cveProceso") String cveProceso,
			@Param("cveVariable") String cveVariable
			/*,
			@Param("version") BigDecimal version, 
			@Param("cveNodo") String cveNodo,
			@Param("idNodo") Integer idNodo, 
			@Param("cveSeccion") String cveSeccion, 
			@Param("cveVariable") String cveVariable, 
			@Param("cveSeccionDependiente") String cveSeccionDependiente*/);
	
	/*@Query(value = "SELECT " +
		       "vd.seccionSelect, " +
		       "vd.seccionFrom, " +
		       "vd.seccionWhere, " +
		       "vd.cveVariableDependiente " +
		     "FROM StVariableDependiente vd " +
		     "WHERE vd.id.cveEntidad = :cveEntidad " +
		       "AND vd.id.cveProceso = :cveProceso " +
		       "AND vd.id.version = :version " +
		       "AND vd.id.cveNodo = :cveNodo " +
		       "AND vd.id.idNodo = :idNodo " +
		       "AND vd.id.cveSeccion = :cveSeccion " +
		       "AND vd.id.cveVariable = :cveVariable " +
		       "AND vd.id.cveSeccionDependiente = :cveSeccionDependiente")
		       
		List<Object[]> obtieneVariableDependiente(
		       @Param("cveEntidad") String cveEntidad,
		       @Param("cveProceso") String cveProceso,
		       @Param("version") BigDecimal version,
		       @Param("cveNodo") String cveNodo,
		       @Param("idNodo") Integer idNodo,
		       @Param("cveSeccion") String cveSeccion,
		       @Param("cveVariable") String cveVariable,
		       @Param("cveSeccionDependiente") String cveSeccionDependiente);*/

}
