package com.briomax.briobpm.persistence.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.briomax.briobpm.persistence.entity.StAccionNodoProcesoPK;
import com.briomax.briobpm.persistence.entity.StAccionSeccionNodo;

@Repository
public interface IStAccionSeccionNodoRepository extends JpaRepository<StAccionSeccionNodo, StAccionNodoProcesoPK>{

	
	@Query(value = "SELECT * FROM ST_ACCION_SECCION_NODO " + 
			"	WHERE CVE_ENTIDAD = :cveEntidad " + 
			"	AND CVE_PROCESO = :cveProceso " + 
			"	AND VERSION = :version " + 
			"	AND CVE_NODO = :cveNodo " + 
			"	AND ID_NODO = :idNodo " + 
			"	AND CVE_SECCION = :cveSeccion " +
			"   ORDER BY ORDEN_ACCION " , nativeQuery = true)
	List<StAccionSeccionNodo> obtieneBotones( @Param("cveEntidad") String cveEntidad, @Param("cveProceso") String cveProceso, 
			@Param("version") BigDecimal version, @Param("cveNodo") String cveNodo, 
			@Param("idNodo") Integer idNodo, @Param("cveSeccion") String cveSeccion);

}
