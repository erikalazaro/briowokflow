package com.briomax.briobpm.persistence.repository;

import java.math.BigDecimal;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.briomax.briobpm.persistence.entity.InDocumentoProceso;
import com.briomax.briobpm.persistence.entity.InDocumentoProcesoPK;

/**
 * El objetivo de la Interface IInDocumentoProcesoRepository.java es ...
 * @author Sara Ventura 
 * @version 1.0 Fecha de creacion Nov 20, 2023 Modificaciones:
 * @since JDK 1.8
 */
@Repository
public interface IInDocumentoProcesoRepository extends JpaRepository<InDocumentoProceso, InDocumentoProcesoPK> {

	@Query(value = "SELECT COUNT(1) " +
		       "FROM IN_DOCUMENTO_PROCESO ITP " +
		       "WHERE ITP.CVE_ENTIDAD = :cveEntidad " +
		       "AND ITP.CVE_PROCESO = :cveProceso " +
		       "AND ITP.VERSION = :version " +
		       "AND ITP.CVE_INSTANCIA = :cveInstancia " +
		       "AND ITP.SECUENCIA_DOCUMENTO = :secuenciaDocumento", nativeQuery = true)
		Integer existeInDocumentoProceso(
		        @Param("cveEntidad") String cveEntidad,
		        @Param("cveProceso") String cveProceso,
		        @Param("version") BigDecimal version,
		        @Param("cveInstancia") String cveInstancia,
		        @Param("secuenciaDocumento") Integer secuenciaDocumento);

	
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM InDocumentoProceso  p where p.id.cveEntidad =:cveEntidad AND p.id.cveProceso =:cveProceso AND " +
    		" p.id.version=:version AND p.id.cveInstancia=:cveInstancia " )
    void deleteDocumentoProceso(@Param("cveEntidad") String cveEntidad, 
    		@Param("cveProceso") String cveProceso,
			@Param("version") BigDecimal version, 
			@Param("cveInstancia") String cveInstancia);

    
    /**VENCIMIENTO DE DOCUMENTOS*/

    @Query(value = "SELECT INDP.CVE_ENTIDAD, " +
            "INDP.CVE_PROCESO, " +
            "INDP.VERSION, " +
            "INDP.CVE_INSTANCIA, " +
            "INDP.SECUENCIA_DOCUMENTO, " +
            "STDP.DESCRIPCION, " +
            "INDP.NOMBRE_ARCHIVO, " +
            "INVP.VALOR_FECHA " +
            "FROM IN_PROCESO INP, " +
            "IN_DOCUMENTO_PROCESO INDP, " +
            "IN_VARIABLE_PROCESO INVP, " +
            "ST_DOCUMENTO_PROCESO STDP " +
            "WHERE INP.SITUACION = :situacionRegistrado " +
            "AND INDP.CVE_ENTIDAD = INP.CVE_ENTIDAD " +
            "AND INDP.CVE_PROCESO = INP.CVE_PROCESO " +
            "AND INDP.VERSION = INP.VERSION " +
            "AND INDP.CVE_INSTANCIA = INP.CVE_INSTANCIA " +
            "AND INVP.CVE_ENTIDAD = INP.CVE_ENTIDAD " +
            "AND INVP.CVE_PROCESO = INP.CVE_PROCESO " +
            "AND INVP.VERSION = INP.VERSION " +
            "AND INVP.CVE_INSTANCIA = INP.CVE_INSTANCIA " +
            "AND INVP.OCURRENCIA = INDP.SECUENCIA_DOCUMENTO " +
            "AND INVP.CVE_VARIABLE = :cveVariableFechaVencimiento " +
            "AND INVP.SECUENCIA_VALOR = 1 " +
            "AND INVP.VALOR_FECHA IS NOT NULL " +
            "AND INVP.VALOR_FECHA >= :fechaActual  " +
            "AND STDP.CVE_ENTIDAD = INDP.CVE_ENTIDAD " +
            "AND STDP.CVE_PROCESO = INDP.CVE_PROCESO " +
            "AND STDP.VERSION = INDP.VERSION " +
            "AND STDP.SECUENCIA_DOCUMENTO = INDP.SECUENCIA_DOCUMENTO " ,
    nativeQuery = true)
     List<Object[]> obtenerDocumentos(@Param("situacionRegistrado") String situacionRegistrado,
                        @Param("cveVariableFechaVencimiento") String cveVariableFechaVencimiento,
                        @Param("fechaActual") java.sql.Timestamp fechaActual);

}
