package com.briomax.briobpm.persistence.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.briomax.briobpm.persistence.entity.StNodoInicioProceso;
import com.briomax.briobpm.persistence.entity.StNodoInicioProcesoPK;


public interface IStNodoInicioProcesoRepository extends JpaRepository<StNodoInicioProceso, StNodoInicioProcesoPK>{

	@Query(value = "SELECT * " +
            "FROM ST_NODO_INICIO_PROCESO " +
            "WHERE CVE_ENTIDAD = :cveEntidad " +
            " AND CVE_PROCESO = :cveProceso " +
            " AND VERSION = :version " +
            " AND CVE_NODO = :eventoInicio " , nativeQuery = true)
List<StNodoInicioProceso> listaNodosInicio(
     @Param("cveEntidad") String cveEntidad,
     @Param("cveProceso") String cveProceso,
     @Param("version") BigDecimal version,
     @Param("eventoInicio") String eventoInicio
);


}
