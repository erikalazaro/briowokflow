package com.briomax.briobpm.persistence.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.briomax.briobpm.persistence.entity.CrTrabajadorHistorico;
import com.briomax.briobpm.persistence.entity.CrTrabajadorHistoricoPK;

@Repository
public interface ICrTrabajadoresHistoricoRepository extends JpaRepository<CrTrabajadorHistorico, CrTrabajadorHistoricoPK>{

	
	@Query(value = "SELECT * " +
    		"FROM CR_TRABAJADOR_HISTORICO  " +
            "WHERE  CVE_ENTIDAD = :cveEntidad " +
            "   AND CVE_PROCESO = :cveProceso " +
    		"   AND RFC_CONTRATISTA = :rfc " +
            "	AND NUMERO_CONTRATO = :contrato " + 
    		"   AND FORMAT(FECHA_CARGA, 'dd/MM/yyyy') = :fecha", nativeQuery = true)
	List<CrTrabajadorHistorico> obtieneTrabajo( @Param("cveEntidad") String cveEntidad,
			@Param("cveProceso") String cveProceso, @Param("rfc") String rfc,
			@Param("contrato") String contrato, @Param("fecha") String fecha);

	//FORMAT(c.id.fechaCarga, 'dd/MM/yyyy')
	
	
	@Query(value = "SELECT DISTINCT FECHA_CARGA " +
    		"FROM CR_TRABAJADOR_HISTORICO  " +
            "WHERE  CVE_ENTIDAD = :cveEntidad " +
            "   AND CVE_PROCESO = :cveProceso " +
    		"   AND RFC_CONTRATISTA = :rfc " +
            "	AND NUMERO_CONTRATO = :contrato", nativeQuery = true)
	List<Date> obtieneFechas( @Param("cveEntidad") String cveEntidad,  @Param("cveProceso") String cveProceso,
			@Param("rfc") String rfc, @Param("contrato") String contrato);

	//CONVER(date, FECHA_CARGA)
}
