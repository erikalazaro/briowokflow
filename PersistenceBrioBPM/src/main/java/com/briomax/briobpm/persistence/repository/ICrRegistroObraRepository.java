package com.briomax.briobpm.persistence.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.briomax.briobpm.persistence.entity.CrRegistroObra;
import com.briomax.briobpm.persistence.entity.CrRegistroObraPK;

@Repository
public interface ICrRegistroObraRepository extends JpaRepository<CrRegistroObra, CrRegistroObraPK>{

	
	 @Query(value = "SELECT * " + 
		 		"  FROM CR_REGISTRO_OBRA " + 
		 		"  WHERE CVE_ENTIDAD = :cveEntidad " + 
		 		"  AND	CVE_PROCESO = :cveProceso " + 
		 		"  AND	RFC_CONTRATISTA = :rfcContratista " + 
		 		"  AND	NUMERO_CONTRATO = :numContrato " + 
		 		"  AND FECHA_CARGA >= :fechaCarga" +
		 		"  AND FECHA_CARGA < :fechaFin" + 
		 		"  ORDER BY FECHA_CARGA DESC"	, nativeQuery = true)
		 List<CrRegistroObra> getCargaRegistroObra(@Param("cveEntidad") String cveEntidad,
				 @Param("cveProceso") String cveProceso,
				 @Param("rfcContratista") String rfcContratista,
				 @Param("numContrato") String numContrato,
				 @Param("fechaCarga") Date fechaCarga,
				 @Param("fechaFin") Date fechaFin);
	 
		@Modifying
	    @Transactional
	    @Query(value = "  DELETE FROM CR_REGISTRO_OBRA " + 
		 		"  WHERE CVE_ENTIDAD = :cveEntidad " + 
		 		"  AND	CVE_PROCESO = :cveProceso " + 
		 		"  AND	RFC_CONTRATISTA = :rfcContratista " + 
		 		"  AND	NUMERO_CONTRATO = :numContrato " + 
		 		"  AND DATE(FECHA_CARGA) = DATE(:fechaCarga)" +
	            "  AND SITUACION_CARGA != 'CARGA EXITOSA' ", nativeQuery = true)
	    void deleteCrRegistroObra(@Param("cveEntidad") String cveEntidad,
				 @Param("cveProceso") String cveProceso,
				 @Param("rfcContratista") String rfcContratista,
				 @Param("numContrato") String numContrato,
				 @Param("fechaCarga") Date fechaCarga);

	 @Query(value = "SELECT * " + 
			    "  FROM CR_REGISTRO_OBRA " + 
			    "  WHERE CVE_ENTIDAD = :cveEntidad " + 
			    "  AND FECHA_CARGA BETWEEN :fechaInicio AND :fechaFin " + 
			    "  AND  SITUACION_CARGA = 'CARGA EXITOSA' " +
			    "  ORDER BY FECHA_CARGA DESC", nativeQuery = true)
			List<CrRegistroObra> getCargaRegistroObraFiltrado(@Param("cveEntidad") String cveEntidad,
			                                                  @Param("fechaInicio") Date fechaInicio,
			                                                  @Param("fechaFin") Date fechaFin);

	 @Query(value = "SELECT * " + 
			    "  FROM CR_REGISTRO_OBRA " + 
			    "  WHERE CVE_ENTIDAD = :cveEntidad " + 
			    "  AND	RFC_CONTRATISTA = :rfc " + 
			    "  AND FECHA_CARGA BETWEEN :fechaInicio AND :fechaFin " + 
			    "  AND  SITUACION_CARGA = 'CARGA EXITOSA' " +
			    "  ORDER BY FECHA_CARGA DESC", nativeQuery = true)
			List<CrRegistroObra> getCargaRegistroObraFiltrados(@Param("cveEntidad") String cveEntidad,
			                                                  @Param("fechaInicio") Date fechaInicio,
			                                                  @Param("fechaFin") Date fechaFin,
			                                                  @Param("rfc") String rfc);

	 @Query(value = "SELECT DISTINCT RFC_CONTRATISTA " + 
		 		"  FROM CR_REGISTRO_OBRA " + 
		 		"  WHERE CVE_ENTIDAD = :cveEntidad " + 
		 		"  AND	CVE_PROCESO = :cveProceso " + 
		 		"  AND FECHA_CARGA BETWEEN :fechaInicio AND :fechaFin " + 
		 		"  AND  SITUACION_CARGA = 'CARGA EXITOSA' " +
		 		"  ORDER BY RFC_CONTRATISTA DESC", nativeQuery = true)
	 List<String> getRfc(@Param("cveEntidad") String cveEntidad, @Param("fechaInicio") Date fechaInicio,             
          	@Param("fechaFin") Date fechaFin, @Param("cveProceso") String cveProceso);
}