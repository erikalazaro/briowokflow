package com.briomax.briobpm.persistence.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.briomax.briobpm.persistence.entity.VariableResultado;
import com.briomax.briobpm.persistence.entity.VariableResultadoPK;

public interface IVariableResultadoRepository extends JpaRepository<VariableResultado, VariableResultadoPK>{
	
	@Query("SELECT vr FROM VariableResultado vr " +
	           "WHERE vr.id.cveEntidad = :cveEntidad AND " +
	           "vr.id.cveProceso = :cveProceso AND " +
	           "vr.id.version = :version AND " +
	           "vr.id.cveRegla = :cveRegla")
	    List<VariableResultado> findByParameters(
	        @Param("cveEntidad") String cveEntidad,
	        @Param("cveProceso") String cveProceso,
	        @Param("version") BigDecimal version,
	        @Param("cveRegla") String cveRegla
	    );

}
