package com.briomax.briobpm.persistence.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.briomax.briobpm.persistence.entity.InDocumentoProcesoOc;
import com.briomax.briobpm.persistence.entity.InDocumentoProcesoOcPK;

@Repository
public interface IInDocumentoProcesoOcRepository extends JpaRepository<InDocumentoProcesoOc, InDocumentoProcesoOcPK>{

		@Query(value = "SELECT COUNT(1) " +
		       "FROM IN_DOCUMENTO_PROCESO_OC ITP " +
		       "WHERE ITP.CVE_ENTIDAD = :cveEntidad " +
		       "AND ITP.CVE_PROCESO = :cveProceso " +
		       "AND ITP.VERSION = :version " +
		       "AND ITP.CVE_INSTANCIA = :cveInstancia " +
		       "AND ITP.OCURRENCIA = :ocurrencia " +
		       "AND ITP.SECUENCIA_DOCUMENTO = :secuenciaDocumento", nativeQuery = true)
		Integer existeInDocumentoProcesoOc(
		        @Param("cveEntidad") String cveEntidad,
		        @Param("cveProceso") String cveProceso,
		        @Param("version") BigDecimal version,
		        @Param("cveInstancia") String cveInstancia,
		        @Param("secuenciaDocumento") Integer secuenciaDocumento,
		        @Param("ocurrencia") Integer ocurrencia );

		@Query(value = "SELECT MAX(ITP.OCURRENCIA) " +
	               "FROM IN_DOCUMENTO_PROCESO_OC ITP " +
	               "WHERE ITP.CVE_ENTIDAD = :cveEntidad " +
	               "AND ITP.CVE_PROCESO = :cveProceso " +
	               "AND ITP.VERSION = :version " +
	               "AND ITP.CVE_INSTANCIA = :cveInstancia ", nativeQuery = true) // AGREGAR SECUENCIA DOC
	Integer encuentraMaxOcurrencia(
	    @Param("cveEntidad") String cveEntidad,
	    @Param("cveProceso") String cveProceso,
	    @Param("version") BigDecimal version,
	    @Param("cveInstancia") String cveInstancia);

		@Modifying
	    @Transactional
	    @Query(value = "DELETE FROM IN_DOCUMENTO_PROCESO_OC " +
	                   "WHERE CVE_ENTIDAD = :cveEntidad " +
	                   "AND CVE_PROCESO = :cveProceso " +
	                   "AND VERSION = :version " +
	                   "AND CVE_INSTANCIA = :cveInstancia " +
	                   "AND OCURRENCIA = :ocurrencia", nativeQuery = true)
	    void deleteDocumentoProceso(
	            @Param("cveEntidad") String cveEntidad,
	            @Param("cveProceso") String cveProceso,
	            @Param("version") BigDecimal version,
	            @Param("cveInstancia") String cveInstancia,
	            @Param("ocurrencia") Integer ocurrencia);
		
		@Modifying
	    @Transactional
	    @Query(value = "DELETE FROM IN_DOCUMENTO_PROCESO_OC " +
	                   "WHERE CVE_ENTIDAD = :cveEntidad " +
	                   "AND CVE_PROCESO = :cveProceso " +
	                   "AND VERSION = :version " +
	                   "AND CVE_INSTANCIA = :cveInstancia " +
	                   "AND OCURRENCIA = :ocurrencia " +
	                   "AND SECUENCIA_DOCUMENTO = :secDocumento " +
	                   "AND SECUENCIA_ARCHIVO > 1", nativeQuery = true)
	    void deleteMulDocumentoProceso(
	            @Param("cveEntidad") String cveEntidad,
	            @Param("cveProceso") String cveProceso,
	            @Param("version") BigDecimal version,
	            @Param("cveInstancia") String cveInstancia,
	            @Param("secDocumento") Integer secDocumento,
	            @Param("ocurrencia") Integer ocurrencia);
		 
		 @Modifying
		    @Transactional
		    @Query(value = "DELETE FROM IN_DOCUMENTO_PROCESO_OC " +
		                   "WHERE CVE_ENTIDAD = :cveEntidad " +
		                   "AND CVE_INSTANCIA = :cveInstancia " +
		                   "AND SECUENCIA_DOCUMENTO = :secuenciaDocumento " +
		                   "AND CVE_PROCESO = :cveProceso " +
		                   "AND VERSION = :version", nativeQuery = true)
		    void borraDocumentosMultiples(
		        @Param("cveEntidad") String cveEntidad,
		        @Param("cveInstancia") String cveInstancia,
		        @Param("cveProceso") String cveProceso,
		        @Param("version") BigDecimal version,
			     @Param("secuenciaDocumento") Integer secuenciaDocumento);


	    @Query(value = "SELECT MAX(OCURRENCIA) " +
                "FROM IN_DOCUMENTO_PROCESO_OC ITP " +
                "WHERE ITP.CVE_ENTIDAD = :cveEntidad " +
                "AND ITP.CVE_PROCESO = :cveProceso " +
                "AND ITP.VERSION = :version " +
                "AND SECUENCIA_DOCUMENTO = :secuenciaDocumento " +
                "AND ITP.CVE_INSTANCIA = :cveInstancia "  , nativeQuery = true)
	    Integer comprobarEliminado(
	     @Param("cveEntidad") String cveEntidad,
	     @Param("cveInstancia") String cveInstancia,
	     @Param("cveProceso") String cveProceso,
	     @Param("version") BigDecimal version,
	     @Param("secuenciaDocumento") Integer secuenciaDocumento);
	    		
	 // Método para encontrar la secuencia máxima de archivo
	    @Query(value = "SELECT * " +
	                   "FROM IN_DOCUMENTO_PROCESO_OC ITP " +
	                   "WHERE ITP.CVE_ENTIDAD = :cveEntidad " +
	                   "AND ITP.CVE_PROCESO = :cveProceso " +
	                   "AND ITP.VERSION = :version " +
	                   "AND ITP.CVE_INSTANCIA = :cveInstancia " +
	                   "AND ITP.SECUENCIA_DOCUMENTO = :secuenciaDocumento ", nativeQuery = true)
		List<InDocumentoProcesoOc> encuentraDocumentos(
	        @Param("cveEntidad") String cveEntidad,
	        @Param("cveProceso") String cveProceso,
	        @Param("version") BigDecimal version,
	        @Param("cveInstancia") String cveInstancia,
	        @Param("secuenciaDocumento") Integer secuenciaDocumento);

	    
		// Método para encontrar la secuencia máxima de archivo
	    @Query(value = "SELECT MAX(ITP.OCURRENCIA) " +
	                   "FROM IN_DOCUMENTO_PROCESO_OC ITP " +
	                   "WHERE ITP.CVE_ENTIDAD = :cveEntidad " +
	                   "AND ITP.CVE_PROCESO = :cveProceso " +
	                   "AND ITP.VERSION = :version " +
	                   "AND ITP.CVE_INSTANCIA = :cveInstancia " +
	                   "AND ITP.SECUENCIA_DOCUMENTO = :secuenciaDocumento", nativeQuery = true)
	    Integer encuentraMaxOcurrenciaNueva(
	        @Param("cveEntidad") String cveEntidad,
	        @Param("cveProceso") String cveProceso,
	        @Param("version") BigDecimal version,
	        @Param("cveInstancia") String cveInstancia,
	        @Param("secuenciaDocumento") Integer secuenciaDocumento);
	    
	    
		@Modifying
	    @Transactional
	    @Query(value = "DELETE FROM IN_DOCUMENTO_PROCESO_OC " +
	                   "WHERE CVE_ENTIDAD = :cveEntidad " +
	                   "AND CVE_PROCESO = :cveProceso " +
	                   "AND VERSION = :version " +
	                   "AND CVE_INSTANCIA = :cveInstancia " +
	                   "AND OCURRENCIA = :ocurrencia " +
	                   "AND SECUENCIA_DOCUMENTO = :secDocumento " +
	                   "AND OCURRENCIA <> 1", nativeQuery = true)
	    void deleteRenglonDocumentoProceso(
	            @Param("cveEntidad") String cveEntidad,
	            @Param("cveProceso") String cveProceso,
	            @Param("version") BigDecimal version,
	            @Param("cveInstancia") String cveInstancia,
	            @Param("secDocumento") Integer secDocumento,
	            @Param("ocurrencia") Integer ocurrencia);
		
		
		 // Método para encontrar la secuencia máxima de archivo
	    @Query(value = "SELECT * " +
	                   "FROM IN_DOCUMENTO_PROCESO_OC ITP " +
	                   "WHERE ITP.CVE_ENTIDAD = :cveEntidad " +
	                   "AND ITP.CVE_PROCESO = :cveProceso " +
	                   "AND ITP.VERSION = :version " +
	                   "AND ITP.CVE_INSTANCIA = :cveInstancia " +
	                   "AND ITP.SECUENCIA_DOCUMENTO = :secuenciaDocumento " + 
	                   "AND ITP.NOMBRE_ARCHIVO = :nombreDocumento " + 
	                   "AND ITP.OCURRENCIA = :ocurrencia " +
	                   "AND ITP.ARCHIVO_BINARIO  IS NOT NULL  ", nativeQuery = true)
	    List<InDocumentoProcesoOc> getDocumento(
	        @Param("cveEntidad") String cveEntidad,
	        @Param("cveProceso") String cveProceso,
	        @Param("version") BigDecimal version,
	        @Param("cveInstancia") String cveInstancia,
	        @Param("secuenciaDocumento") Integer secuenciaDocumento,
	        @Param("nombreDocumento") String nombreDocumento,
	        @Param("ocurrencia") Integer ocurrencia);
	    
	    @Query(value = "SELECT ITP.NOMBRE_ARCHIVO " +
                "FROM IN_DOCUMENTO_PROCESO_OC ITP " +
                "WHERE ITP.CVE_ENTIDAD = :cveEntidad " +
                "AND ITP.CVE_PROCESO = :cveProceso " +
                "AND ITP.VERSION = :version " +
                "AND ITP.CVE_INSTANCIA = :cveInstancia " +
                "AND ITP.OCURRENCIA = :ocurrencia " +
                "AND ITP.SECUENCIA_DOCUMENTO = :secuenciaDocumento ", nativeQuery = true)
		List<String> encuentraNombreDocumentos(
	     @Param("cveEntidad") String cveEntidad,
	     @Param("cveProceso") String cveProceso,
	     @Param("version") BigDecimal version,
	     @Param("cveInstancia") String cveInstancia,
	     @Param("secuenciaDocumento") Integer secuenciaDocumento, 
	     @Param("ocurrencia") Integer ocurrencia);
	    
	    
	    @Query(value = "SELECT * " +
                "FROM IN_DOCUMENTO_PROCESO_OC ITP " +
                "WHERE ITP.CVE_ENTIDAD = :cveEntidad " +
                "AND ITP.CVE_PROCESO = :cveProceso " +
                "AND ITP.VERSION = :version " +
                "AND ITP.CVE_INSTANCIA = :cveInstancia " +
                "AND ITP.SECUENCIA_DOCUMENTO = :secuenciaDocumento ", nativeQuery = true)
		List<InDocumentoProcesoOc> encuentraDocumentosAPP(
	     @Param("cveEntidad") String cveEntidad,
	     @Param("cveProceso") String cveProceso,
	     @Param("version") BigDecimal version,
	     @Param("cveInstancia") String cveInstancia,
	     @Param("secuenciaDocumento") Integer secuenciaDocumento);
}
