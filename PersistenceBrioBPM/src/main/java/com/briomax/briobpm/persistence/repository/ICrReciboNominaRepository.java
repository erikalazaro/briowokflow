package com.briomax.briobpm.persistence.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.briomax.briobpm.persistence.entity.CrReciboNomina;
import com.briomax.briobpm.persistence.entity.CrReciboNominaPK;


/**
 * El objetivo de la Interface ICrReciboNominaRepository.java es ...
 * @author 
 * @version 1.0 Fecha de creacion Jul 22, 2024 Modificaciones:
 * @since JDK 1.8
 */
@Repository
public interface ICrReciboNominaRepository extends JpaRepository <CrReciboNomina, CrReciboNominaPK>{

	 @Query(value = "SELECT * " + 
		 		"  FROM CR_RECIBO_NOMINA " + 
		 		"  WHERE CVE_ENTIDAD = :cveEntidad " + 
		 		"  AND	CVE_PROCESO = :cveProceso " + 
		 		"  AND	RFC_CONTRATISTA = :rfcContratista " + 
		 		"  AND	NUMERO_CONTRATO = :numContrato " + 
		 		"  AND FECHA_CARGA >= :fechaCarga" +
		 		"  AND FECHA_CARGA < :fechaFin" + 
		 		"  ORDER BY FECHA_CARGA DESC", nativeQuery = true)
	 List<CrReciboNomina> getCargaReciboNimina(@Param("cveEntidad") String cveEntidad,
			 @Param("cveProceso") String cveProceso,
			 @Param("rfcContratista") String rfcContratista,
			 @Param("numContrato") String numContrato,
			 @Param("fechaCarga") Date fechaCarga,
			 @Param("fechaFin") Date fechaFin);
	 
		@Modifying
	    @Transactional
	    @Query(value = "  DELETE FROM  CR_RECIBO_NOMINA " + 
		 		"  WHERE CVE_ENTIDAD = :cveEntidad " + 
		 		"  AND	CVE_PROCESO = :cveProceso " + 
		 		"  AND	RFC_CONTRATISTA = :rfcContratista " + 
		 		"  AND	NUMERO_CONTRATO = :numContrato " + 
		 		"  AND FECHA_CARGA >= :fechaCarga" +
		 		"  AND FECHA_CARGA < :fechaCarga" + 
	            "  AND SITUACION_CARGA != 'CARGA EXITOSA' ", nativeQuery = true)
	    void deleteCrReciboNomina(@Param("cveEntidad") String cveEntidad,
				 @Param("cveProceso") String cveProceso,
				 @Param("rfcContratista") String rfcContratista,
				 @Param("numContrato") String numContrato,
				 @Param("fechaCarga") Date fechaCarga);

	 @Query(value = "SELECT * " + 
			    "  FROM CR_RECIBO_NOMINA " + 
			    "  WHERE CVE_ENTIDAD = :cveEntidad " + 
			    "  AND  FECHA_CARGA BETWEEN :fechaInicio AND :fechaFin " + 
			    "  AND  SITUACION_CARGA = 'CARGA EXITOSA' " +
			    "  ORDER BY FECHA_CARGA DESC", nativeQuery = true)
	List<CrReciboNomina> getCargaReciboNominaFiltrado(@Param("cveEntidad") String cveEntidad,
			                                                  @Param("fechaInicio") Date fechaInicio,
			                                                  @Param("fechaFin") Date fechaFin);
	 
	 @Query(value = "SELECT * " + 
			    "  FROM CR_RECIBO_NOMINA " + 
			    "  WHERE CVE_ENTIDAD = :cveEntidad " + 
			    "  AND	RFC_CONTRATISTA = :rfc " + 
			    "  AND  FECHA_CARGA BETWEEN :fechaInicio AND :fechaFin " + 
			    "  AND  SITUACION_CARGA = 'CARGA EXITOSA' " +
			    "  ORDER BY FECHA_CARGA DESC", nativeQuery = true)
	List<CrReciboNomina> getCargaReciboNominaFiltrados(@Param("cveEntidad") String cveEntidad,
			                                                  @Param("fechaInicio") Date fechaInicio,
			                                                  @Param("fechaFin") Date fechaFin,
			                                                  @Param("rfc") String rfc);
	 
	 @Query(value = "SELECT DISTINCT RFC_PATRON " + 
		 		"  FROM CR_RECIBO_NOMINA " + 
		 		"  WHERE CVE_ENTIDAD = :cveEntidad " + 
		 		"  AND	CVE_PROCESO = :cveProceso " + 
		 		"  AND FECHA_CARGA BETWEEN :fechaInicio AND :fechaFin " + 
		 		"  AND  SITUACION_CARGA = 'CARGA EXITOSA' " +
		 		"  ORDER BY RFC_PATRON DESC", nativeQuery = true)
	 List<String> getRfc(@Param("cveEntidad") String cveEntidad, @Param("fechaInicio") Date fechaInicio,             
             	@Param("fechaFin") Date fechaFin, @Param("cveProceso") String cveProceso);
	 
	 @Modifying
	    @Transactional
	    @Query(value = "  DELETE FROM  CR_RECIBO_NOMINA " + 
		 		"  WHERE CVE_ENTIDAD = :cveEntidad " + 
		 		"  AND	CVE_PROCESO = :cveProceso " + 
		 		"  AND	RFC_CONTRATISTA = :rfcContratista " + 
		 		"  AND	NUMERO_CONTRATO = :numContrato " + 
		 		"  AND FECHA_CARGA = :fechaCarga" +
	            "  AND SITUACION_CARGA = 'CARGA EXITOSA' ", nativeQuery = true)
	    void deleteReciboNomina(@Param("cveEntidad") String cveEntidad,
				 @Param("cveProceso") String cveProceso,
				 @Param("rfcContratista") String rfcContratista,
				 @Param("numContrato") String numContrato,
				 @Param("fechaCarga") Date fechaCarga);

}
