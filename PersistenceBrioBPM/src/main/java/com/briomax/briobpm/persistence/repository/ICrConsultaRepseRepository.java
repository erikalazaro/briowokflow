package com.briomax.briobpm.persistence.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.briomax.briobpm.persistence.entity.CrConsultaRepse;
import com.briomax.briobpm.persistence.entity.CrConsultaRepsePK;

/**
 * El objetivo de la Interface StValorInicialVariable.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion May 09, 2024 Modificaciones:
 * @since JDK 1.8
 */
@Repository
public interface ICrConsultaRepseRepository extends JpaRepository<CrConsultaRepse, CrConsultaRepsePK>{

 // Método de consulta personalizada que filtra por razon social, resultado de consulta y/o fecha
    @Query(value = "SELECT * FROM CR_CONSULTA_REPSE WHERE (:razonSocial IS NULL OR RAZON_SOCIAL = :razonSocial) " +
            "AND (:resultadoConsulta IS NULL OR RESULTADO_CONSULTA = :resultadoConsulta) " +
            "AND (:fecha IS NULL OR FORMAT(FECHA_CONSULTA, 'dd/MM/yyyy') = :fecha);", nativeQuery = true)
    List<CrConsultaRepse> findByAll(@Param("razonSocial") String razonSocial,
                                    @Param("resultadoConsulta") String resultadoConsulta,
                                    @Param("fecha") String fecha);

	 @Query(value = "SELECT * " + 
		 		"  FROM CR_CONSULTA_REPSE " + 
		 		"  WHERE CVE_ENTIDAD = :cveEntidad " + 
		 		"  AND	CVE_PROCESO = :cveProceso " + 
		 		"  AND	RFC_CONTRATISTA = :rfcContratista " + 
		 		"  AND	NUMERO_CONTRATO = :numContrato " + 
		 		"  AND FECHA_CARGA >= :fechaCarga" +
		 		"  AND FECHA_CARGA < :fechaFin" + 
		 		"  ORDER BY FECHA_CONSULTA DESC"	, nativeQuery = true)
		 Optional<CrConsultaRepse> getAvisoRepse(@Param("cveEntidad") String cveEntidad,
				 @Param("cveProceso") String cveProceso,
				 @Param("rfcContratista") String rfcContratista,
				 @Param("numContrato") String numContrato,
				 @Param("fechaCarga") Date fechaCarga,
				 @Param("fechaFin") Date fechaFin);
	 
		@Modifying
	    @Transactional
	    @Query(value = "  DELETE FROM CR_CONSULTA_REPSE " + 
		 		"  WHERE CVE_ENTIDAD = :cveEntidad " + 
		 		"  AND	CVE_PROCESO = :cveProceso " + 
		 		"  AND	RFC_CONTRATISTA = :rfcContratista " + 
		 		"  AND	NUMERO_CONTRATO = :numContrato " + 
		 		"  AND FECHA_CARGA >= :fechaCarga" +
		 		"  AND FECHA_CARGA < :fechaCarga" + 
	            "  AND SITUACION_CARGA != 'CARGA EXITOSA' ", nativeQuery = true)
	    void deleteCrConsultaRepse(@Param("cveEntidad") String cveEntidad,
				 @Param("cveProceso") String cveProceso,
				 @Param("rfcContratista") String rfcContratista,
				 @Param("numContrato") String numContrato,
				 @Param("fechaCarga") Date fechaCarga);
	 
	 @Query(value = "SELECT * " + 
			    "  FROM CR_CONSULTA_REPSE " + 
			    "  WHERE CVE_ENTIDAD = :cveEntidad " + 
			    "  AND FECHA_CONSULTA BETWEEN :fechaInicio AND :fechaFin " + 
			    "  AND  SITUACION_CARGA = 'CARGA EXITOSA' " +
			    "  ORDER BY FECHA_CONSULTA DESC", nativeQuery = true)
	 List<CrConsultaRepse> getAvisoRepseFiltrado(@Param("cveEntidad") String cveEntidad,
			                                                @Param("fechaInicio") Date fechaInicio,
			                                                @Param("fechaFin") Date fechaFin);

	 @Query(value = "SELECT * " + 
			    "  FROM CR_CONSULTA_REPSE " + 
			    "  WHERE CVE_ENTIDAD = :cveEntidad " + 
			    "  AND	RFC_CONTRATISTA = :rfcContratista " + 
			    "  AND FECHA_CONSULTA BETWEEN :fechaInicio AND :fechaFin " + 
			    "  AND  SITUACION_CARGA = 'CARGA EXITOSA' " +
			    "  ORDER BY FECHA_CONSULTA DESC", nativeQuery = true)
	 List<CrConsultaRepse> getAvisoRepseFiltrados(@Param("cveEntidad") String cveEntidad,
			                                                @Param("fechaInicio") Date fechaInicio,
			                                                @Param("fechaFin") Date fechaFin,
			                                                @Param("rfcContratista") String rfcContratista);
	 
	 @Query(value = "SELECT DISTINCT RFC_CONTRATISTA " + 
		 		"  FROM CR_CONSULTA_REPSE " + 
		 		"  WHERE CVE_ENTIDAD = :cveEntidad " + 
		 		"  AND	CVE_PROCESO = :cveProceso " + 
		 		"  AND FECHA_CONSULTA BETWEEN :fechaInicio AND :fechaFin " + 
		 		"  AND  SITUACION_CARGA = 'CARGA EXITOSA' " +
		 		"  ORDER BY RFC_CONTRATISTA DESC", nativeQuery = true)
	 List<String> getRfc(@Param("cveEntidad") String cveEntidad, @Param("fechaInicio") Date fechaInicio,             
	@Param("fechaFin") Date fechaFin, @Param("cveProceso") String cveProceso);

	 
	 @Query(value = "SELECT * " + 
			    "  FROM CR_CONSULTA_REPSE " + 
			    "  WHERE CVE_ENTIDAD = :cveEntidad " + 
			    "  AND	RAZON_SOCIAL = :razonSocial " + 
			    "  AND FECHA_CONSULTA = :fechaConsulta " + 
			    "  ORDER BY FECHA_CONSULTA DESC", nativeQuery = true)
	 CrConsultaRepse getAvisoRepseFiltro(@Param("cveEntidad") String cveEntidad,
			                                                @Param("fechaConsulta") Date fechaConsulta,
			                                                @Param("razonSocial") String razonSocial);
}
