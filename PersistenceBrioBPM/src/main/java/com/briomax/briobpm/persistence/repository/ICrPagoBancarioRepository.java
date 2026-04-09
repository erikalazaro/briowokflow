package com.briomax.briobpm.persistence.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.briomax.briobpm.persistence.entity.CrPagoBancario;
import com.briomax.briobpm.persistence.entity.CrPagoBancarioPK;

/**
 * El objetivo de la Interface ICrReciboNominaRepository.java es ...
 * @author 
 * @version 1.0 Fecha de creacion Sept 03, 2024 Modificaciones:
 * @since JDK 1.8
 */
@Repository
public interface ICrPagoBancarioRepository extends JpaRepository <CrPagoBancario, CrPagoBancarioPK>{

	 @Query(value = "SELECT * " + 
	 		"  FROM CR_PAGO_BANCARIO " + 
	 		"  WHERE CVE_ENTIDAD = :cveEntidad " + 
	 		"  AND	CVE_PROCESO = :cveProceso " + 
	 		"  AND	RFC_CONTRATISTA = :rfcContratista " + 
	 		"  AND	NUMERO_CONTRATO = :numContrato " + 
	 		"  AND FECHA_CARGA >= :fechaCarga" +
	 		"  AND FECHA_CARGA < :fechaFin" + 
	 		"  ORDER BY FECHA_CARGA DESC", nativeQuery = true)
	 List<CrPagoBancario> getCargaPagoBancario(@Param("cveEntidad") String cveEntidad,
			 @Param("cveProceso") String cveProceso,
			 @Param("rfcContratista") String rfcContratista,
			 @Param("numContrato") String numContrato,
			 @Param("fechaCarga") Date fechaCarga,
			 @Param("fechaFin") Date fechaFin);
	 
		@Modifying
	    @Transactional
	    @Query(value = "  DELETE FROM CR_PAGO_BANCARIO " + 
		 		"  WHERE CVE_ENTIDAD = :cveEntidad " + 
		 		"  AND	CVE_PROCESO = :cveProceso " + 
		 		"  AND	RFC_CONTRATISTA = :rfcContratista " + 
		 		"  AND	NUMERO_CONTRATO = :numContrato " + 
		 		"  AND FECHA_CARGA >= :fechaCarga" +
		 		"  AND FECHA_CARGA < :fechaCarga" + 
	            "  AND SITUACION_CARGA != 'CARGA EXITOSA' ", nativeQuery = true)
	    void deleteCrPagoBancarioRepository (@Param("cveEntidad") String cveEntidad,
				 @Param("cveProceso") String cveProceso,
				 @Param("rfcContratista") String rfcContratista,
				 @Param("numContrato") String numContrato,
				 @Param("fechaCarga") Date fechaCarga);	 

	 @Query(value = "SELECT * " + 
			    "  FROM CR_PAGO_BANCARIO " + 
			    "  WHERE CVE_ENTIDAD = :cveEntidad " + 
			    "  AND FECHA_CARGA BETWEEN :fechaInicio AND :fechaFin " + 
			    "  AND  SITUACION_CARGA = 'CARGA EXITOSA' " +
			    "  ORDER BY FECHA_CARGA DESC", nativeQuery = true)
			List<CrPagoBancario> getCargaPagoBancarioFiltrado(@Param("cveEntidad") String cveEntidad,
			                                                  @Param("fechaInicio") Date fechaInicio,
			                                                  @Param("fechaFin") Date fechaFin);
	 
	 @Query(value = "SELECT * " + 
			    "  FROM CR_PAGO_BANCARIO " + 
			    "  WHERE CVE_ENTIDAD = :cveEntidad " + 
			    "  AND	RFC_CONTRATISTA = :rfcContratista " + 
			    "  AND FECHA_CARGA BETWEEN :fechaInicio AND :fechaFin " + 
			    "  AND  SITUACION_CARGA = 'CARGA EXITOSA' " +
			    "  ORDER BY FECHA_CARGA DESC", nativeQuery = true)
			List<CrPagoBancario> getCargaPagoBancarioFiltrados(@Param("cveEntidad") String cveEntidad,
			                                                  @Param("fechaInicio") Date fechaInicio,
			                                                  @Param("fechaFin") Date fechaFin,
			                                                  @Param("rfcContratista") String rfcContratista);
	 
	 @Query(value = "SELECT DISTINCT RFC_CONTRATISTA " + 
		 		"  FROM CR_PAGO_BANCARIO " + 
		 		"  WHERE CVE_ENTIDAD = :cveEntidad " + 
		 		"  AND	CVE_PROCESO = :cveProceso " + 
		 		"  AND FECHA_CARGA BETWEEN :fechaInicio AND :fechaFin " + 
		 		"  AND  SITUACION_CARGA = 'CARGA EXITOSA' " +
		 		"  ORDER BY RFC_CONTRATISTA DESC", nativeQuery = true)
	 List<String> getRfc(@Param("cveEntidad") String cveEntidad, @Param("fechaInicio") Date fechaInicio,             
          	@Param("fechaFin") Date fechaFin, @Param("cveProceso") String cveProceso);
	 
	 
	 
}
