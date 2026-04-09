package com.briomax.briobpm.persistence.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.briomax.briobpm.persistence.entity.CrCedulaDetCuotas;
import com.briomax.briobpm.persistence.entity.CrCedulaDetCuotasPK;

/**
 * El objetivo de la Interface ICrCedulaDetCuotasRepository.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Septiembre 18, 2024 Modificaciones:
 * @since JDK 1.8
 */
@Repository
public interface ICrCedulaDetCuotasRepository extends JpaRepository<CrCedulaDetCuotas, CrCedulaDetCuotasPK> {

	 @Query(value = "SELECT * " + 
		 		"  FROM CR_CEDULA_DET_CUOTAS " + 
		 		"  WHERE CVE_ENTIDAD = :cveEntidad " + 
		 		"  AND	CVE_PROCESO = :cveProceso " + 
		 		"  AND	RFC_CONTRATISTA = :rfcContratista " + 
		 		"  AND	NUMERO_CONTRATO = :numContrato " + 
		 		"  AND FECHA_CARGA >= :fechaCarga" +
		 		"  AND FECHA_CARGA < :fechaFin" + 
		 		"  ORDER BY FECHA_CARGA DESC"	, nativeQuery = true)
		 List<CrCedulaDetCuotas> getCrCedulaDetCuotas(@Param("cveEntidad") String cveEntidad,
				 @Param("cveProceso") String cveProceso,
				 @Param("rfcContratista") String rfcContratista,
				 @Param("numContrato") String numContrato,
				 @Param("fechaCarga") Date fechaCarga,
				 @Param("fechaFin") Date fechaFin);

		@Modifying
	    @Transactional
	    @Query(value = "  DELETE FROM CR_CEDULA_DET_CUOTAS " + 
		 		"  WHERE CVE_ENTIDAD = :cveEntidad " + 
		 		"  AND	CVE_PROCESO = :cveProceso " + 
		 		"  AND	RFC_CONTRATISTA = :rfcContratista " + 
		 		"  AND	NUMERO_CONTRATO = :numContrato " + 
		 		"  AND FECHA_CARGA = :fechaCarga " +
	            "  AND SITUACION_CARGA != 'CARGA EXITOSA' ", nativeQuery = true)
	    void deleteCrCedulaDetCuotas(@Param("cveEntidad") String cveEntidad,
				 @Param("cveProceso") String cveProceso,
				 @Param("rfcContratista") String rfcContratista,
				 @Param("numContrato") String numContrato,
				 @Param("fechaCarga") Date fechaCarga);
	 
	 @Query(value = "SELECT * " + 
			    "  FROM CR_CEDULA_DET_CUOTAS " + 
			    "  WHERE CVE_ENTIDAD = :cveEntidad " + 
			    "  AND  CVE_PROCESO = CVE_PROCESO " +
			    "  AND	RFC_CONTRATISTA = RFC_CONTRATISTA " + 
		 		"  AND	NUMERO_CONTRATO = NUMERO_CONTRATO " + 
			    "  AND FECHA_CARGA BETWEEN :fechaInicio AND :fechaFin " + 
			    "  AND  SITUACION_CARGA = 'CARGA EXITOSA' " +
			    "  ORDER BY FECHA_CARGA DESC", nativeQuery = true)
			List<CrCedulaDetCuotas> getCrCedulaDetCuotasFiltrado(@Param("cveEntidad") String cveEntidad,
			                                                     @Param("fechaInicio") Date fechaInicio,
			                                                     @Param("fechaFin") Date fechaFin);

	 @Query(value = "SELECT * " + 
			    "  FROM CR_CEDULA_DET_CUOTAS " + 
			    "  WHERE CVE_ENTIDAD = :cveEntidad " + 
			    "  AND	RFC_CONTRATISTA = :rfcContratista " + 
			    "  AND FECHA_CARGA BETWEEN :fechaInicio AND :fechaFin " + 
			    "  AND  SITUACION_CARGA = 'CARGA EXITOSA' " +
			    "  ORDER BY FECHA_CARGA DESC", nativeQuery = true)
			List<CrCedulaDetCuotas> getCrCedulaDetCuotasFiltrados(@Param("cveEntidad") String cveEntidad,
			                                                     @Param("fechaInicio") Date fechaInicio,
			                                                     @Param("fechaFin") Date fechaFin,
			                                                     @Param("rfcContratista") String rfcContratista);
	 
	 @Query(value = "SELECT DISTINCT RFC_CONTRATISTA " + 
		 		"  FROM CR_CEDULA_DET_CUOTAS " + 
		 		"  WHERE CVE_ENTIDAD = :cveEntidad " + 
		 		"  AND	CVE_PROCESO = :cveProceso " + 
		 		"  AND FECHA_CARGA BETWEEN :fechaInicio AND :fechaFin " + 
		 		"  AND  SITUACION_CARGA = 'CARGA EXITOSA' " +
		 		"  ORDER BY RFC_CONTRATISTA DESC", nativeQuery = true)
	 List<String> getRfc(@Param("cveEntidad") String cveEntidad, @Param("fechaInicio") Date fechaInicio,             
 	@Param("fechaFin") Date fechaFin, @Param("cveProceso") String cveProceso);


	 @Query(value = "SELECT a.rfcCurpTrabajador " + 
		 		"  FROM CrCedulaDetCuotas a " + 
		 		"  WHERE a.id.cveEntidad = :cveEntidad " + 
		 		"  AND	a.id.cveProceso = :cveProceso " + 
		 		"  AND situacionCarga = 'CARGA EXITOSA' " +
		 		"  AND nssTrabajador = :nss " +
		 		"  AND nombreTrabajador = :nombre " +
		 		"  AND (rfcCurpTrabajador = :curpTrabajador OR rfcCurpTrabajador = :rfcTrabajador) " + 
		 		"  AND a.id.fechaCarga = (SELECT MAX (c.id.fechaCarga) FROM CrPdfFiles c " +
		 		" 			WHERE c.id.cveEntidad =:cveEntidad  AND " + 
		 		"				c.id.cveProcesoPeriodico = :cveProceso AND " + 
		 		"				c.id.rfc = :rfcContratista AND c.id.numeroContrato = :numeroContrato ) ")
	String getCurpTrabajador(@Param("cveEntidad") String cveEntidad, @Param("cveProceso") String cveProceso,
			@Param("rfcContratista") String rfcContratista, @Param("numeroContrato") String numeroContrato,
			@Param("nss") String nss, @Param("nombre") String nombre,
			@Param("curpTrabajador") String curpTrabajador, @Param("rfcTrabajador") String rfcTrabajador);
	 
	 @Query(value = "SELECT COUNT (a.rfcCurpTrabajador) " + 
		 		"  FROM CrCedulaDetCuotas a " + 
		 		"  WHERE a.id.cveEntidad = :cveEntidad " + 
		 		"  AND	a.id.cveProceso = :cveProceso " + 
		 		"  AND	a.id.rfcContratista = :rfcContratista " + 
		 		"  AND	a.id.numeroContrato = :numeroContrato " +
		 		"  AND  situacionCarga = 'CARGA EXITOSA' " +
		 		"  AND a.id.fechaCarga = (SELECT MAX (c.id.fechaCarga) FROM CrPdfFiles c " +
		 		" 		WHERE c.id.cveEntidad =:cveEntidad  AND " + 
		 		"			c.id.cveProcesoPeriodico = :cveProceso AND " + 
		 		"			c.id.rfc = :rfcContratista AND c.id.numeroContrato = :numeroContrato ) ")
	Integer getNumTrabajadores(@Param("cveEntidad") String cveEntidad, @Param("cveProceso") String cveProceso,
			@Param("rfcContratista") String rfcContratista,              
			@Param("numeroContrato") String numeroContrato);
	 @Query(value = "SELECT * " + 
		 		"  FROM CR_CEDULA_DET_CUOTAS " + 
		 		"  WHERE CVE_ENTIDAD = :cveEntidad " + 
		 		"  AND	CVE_PROCESO = :cveProceso " + 
		 		"  AND	RFC_CONTRATISTA = :rfcContratista " + 
		 		"  AND	NUMERO_CONTRATO = :numContrato " + 
		 		"  AND FECHA_CARGA >= :fechaCarga " +
		 		"  AND FECHA_CARGA < :fechaFin " + 
		 		"  AND TIPO_PERIODO < :periodo " + 
		 		"  ORDER BY FECHA_CARGA DESC"	, nativeQuery = true)
	List<CrCedulaDetCuotas> getCrCedulaDetCuotasByPeriodo(@Param("cveEntidad") String cveEntidad,
				 @Param("cveProceso") String cveProceso,
				 @Param("rfcContratista") String rfcContratista,
				 @Param("numContrato") String numContrato,
				 @Param("fechaCarga") Date fechaCarga,
				 @Param("fechaFin") Date fechaFin,
				 @Param("periodo") String periodo);
}

