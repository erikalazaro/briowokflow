package com.briomax.briobpm.persistence.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.briomax.briobpm.persistence.entity.CrFormatoCuotasOP;
import com.briomax.briobpm.persistence.entity.CrFormatoCuotasOPPK;

@Repository
public interface ICrFormatoCuotaOPRepository extends JpaRepository< CrFormatoCuotasOP, CrFormatoCuotasOPPK>{

	
	 @Query(value = "SELECT * " + 
		 		"  FROM CR_FORMATO_PAGO_CUOTAS_OP " + 
		 		"  WHERE CVE_ENTIDAD = :cveEntidad " + 
		 		"  AND	CVE_PROCESO = :cveProceso " + 
		 		"  AND	RFC_CONTRATISTA = :rfcContratista " + 
		 		"  AND	NUMERO_CONTRATO = :numContrato " + 
		 		"  AND FECHA_CARGA >= :fechaCarga" +
		 		"  AND FECHA_CARGA < :fechaFin" + 
		 		"  ORDER BY FECHA_CARGA DESC"	, nativeQuery = true)
		 List<CrFormatoCuotasOP> getCargaFormatoCuotasOP(@Param("cveEntidad") String cveEntidad,
				 @Param("cveProceso") String cveProceso,
				 @Param("rfcContratista") String rfcContratista,
				 @Param("numContrato") String numContrato,
				 @Param("fechaCarga") Date fechaCarga,
				 @Param("fechaFin") Date fechaFin);

		@Modifying
	    @Transactional
	    @Query(value = "  DELETE FROM CR_FORMATO_PAGO_CUOTAS_OP " + 
		 		"  WHERE CVE_ENTIDAD = :cveEntidad " + 
		 		"  AND	CVE_PROCESO = :cveProceso " + 
		 		"  AND	RFC_CONTRATISTA = :rfcContratista " + 
		 		"  AND	NUMERO_CONTRATO = :numContrato " + 
		 		"  AND FECHA_CARGA >= :fechaCarga" +
		 		"  AND FECHA_CARGA < :fechaCarga" + 
	            "  AND SITUACION_CARGA != 'CARGA EXITOSA' ", nativeQuery = true)
	    void deleteCrFormatoCuotaOP(@Param("cveEntidad") String cveEntidad,
				 @Param("cveProceso") String cveProceso,
				 @Param("rfcContratista") String rfcContratista,
				 @Param("numContrato") String numContrato,
				 @Param("fechaCarga") Date fechaCarga);
	 
	 @Query("SELECT c FROM CrFormatoCuotasOP c " +
		       "WHERE c.id.cveEntidad = :cveEntidad " +
		       "AND c.id.fechaCarga BETWEEN :fechaInicio AND :fechaFin " +
		       " AND c.situacionCarga = 'CARGA EXITOSA' " +
		       "ORDER BY c.fechaPago DESC")
		List<CrFormatoCuotasOP> getCargaFormatoCuotasOPFiltrado(@Param("cveEntidad") String cveEntidad,
		                                                        @Param("fechaInicio") Date fechaInicio,
		                                                        @Param("fechaFin") Date fechaFin);
	 
	 @Query("SELECT c FROM CrFormatoCuotasOP c " +
		       " WHERE c.id.cveEntidad = :cveEntidad " +
		       "	AND c.id.fechaCarga BETWEEN :fechaInicio AND :fechaFin " +
		       "	AND	c.id.rfcContratista = :rfcContratista " + 
		       "	AND c.situacionCarga = 'CARGA EXITOSA' " +
		       " ORDER BY c.fechaPago DESC")
		List<CrFormatoCuotasOP> getCargaFormatoCuotasOPFiltrados(@Param("cveEntidad") String cveEntidad,
		                                                        @Param("fechaInicio") Date fechaInicio,
		                                                        @Param("fechaFin") Date fechaFin,
		                                                        @Param("rfcContratista") String rfcContratista);
	 
	 @Query(value = "SELECT DISTINCT RFC_CONTRATISTA " + 
		 		"  FROM CR_FORMATO_PAGO_CUOTAS_OP " + 
		 		"  WHERE CVE_ENTIDAD = :cveEntidad " + 
		 		"  AND	CVE_PROCESO = :cveProceso " + 
		 		"  AND FECHA_CARGA BETWEEN :fechaInicio AND :fechaFin " + 
		 		"  AND  SITUACION_CARGA = 'CARGA EXITOSA' " +
		 		"  ORDER BY RFC_CONTRATISTA DESC", nativeQuery = true)
	 List<String> getRfc(@Param("cveEntidad") String cveEntidad, @Param("fechaInicio") Date fechaInicio,             
    	@Param("fechaFin") Date fechaFin, @Param("cveProceso") String cveProceso);


}

