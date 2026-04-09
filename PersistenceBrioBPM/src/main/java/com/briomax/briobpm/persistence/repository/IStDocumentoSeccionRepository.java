package com.briomax.briobpm.persistence.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.briomax.briobpm.persistence.entity.StDocumentoSeccion;
import com.briomax.briobpm.persistence.entity.StDocumentoSeccionPK;

/**
 * El objetivo de la Interface IInDocumentoProcesoRepository.java es ...
 * @author Sara Ventura 
 * @version 1.0 Fecha de creacion Nov 24, 2023 Modificaciones:
 * @since JDK 1.8
 */
@Repository
public interface IStDocumentoSeccionRepository extends JpaRepository<StDocumentoSeccion, StDocumentoSeccionPK> {

	@Query(value = "SELECT stdp.SECUENCIA_DOCUMENTO " +
		       "FROM ST_DOCUMENTO_SECCION stds, ST_DOCUMENTO_PROCESO stdp " +
		       "WHERE stds.CVE_ENTIDAD = :cveEntidad " +
		       "AND stds.CVE_PROCESO = :cveProceso " +
		       "AND stds.VERSION = :version " +
		       "AND stds.CVE_NODO = :cveNodo " +
		       "AND stds.ID_NODO = :idNodo " +
		       "AND stdp.CVE_ENTIDAD = stds.CVE_ENTIDAD " +
		       "AND stdp.CVE_PROCESO = stds.CVE_PROCESO " +
		       "AND stdp.VERSION = stds.VERSION " +
		       "AND stdp.SECUENCIA_DOCUMENTO = stds.SECUENCIA_DOCUMENTO " +
		       "ORDER BY stds.CVE_SECCION, stds.ORDEN_PRESENTACION", nativeQuery = true)
	List<Integer> encuentraSecuenciaDocumento(
		        @Param("cveEntidad") String cveEntidad,
		        @Param("cveProceso") String cveProceso,
		        @Param("version") BigDecimal version,
		        @Param("cveNodo") String cveNodo,
		        @Param("idNodo") Integer idNodo);
	
	@Query(
		    value = "SELECT " +
		            " 	STDP.SECUENCIA_DOCUMENTO AS SECUENCIA, " +
		            " 	STDP.DESCRIPCION AS DESCRIPCION, " +
		            " 	STDS.REQUERIDA AS REQUERIDO, " +
		            " 	( SELECT NOMBRE_ARCHIVO " +
		            "     FROM IN_DOCUMENTO_PROCESO " +
		            "     WHERE  CVE_ENTIDAD = :cveEntidad " +
		            "            AND CVE_PROCESO = :cveProceso " +
		            "            AND VERSION = :version " +
		            "            AND CVE_INSTANCIA = :cveInstancia " +
		            "            AND SECUENCIA_DOCUMENTO = STDP.SECUENCIA_DOCUMENTO " +
		            "    ) AS NOMBRE_ARCHIVO " +
		            "FROM " +
		            " 	ST_DOCUMENTO_SECCION STDS, " +
		            " 	ST_DOCUMENTO_PROCESO STDP " +
		            "WHERE " +
		            " 	STDS.CVE_ENTIDAD = :cveEntidad " +
		            "	 AND STDS.CVE_PROCESO = :cveProceso " +
		            "	 AND STDS.VERSION = :version " +
		            "	 AND STDS.CVE_NODO = :cveNodo " +
		            " 	 AND STDS.ID_NODO = :idNodo " +
		            "	 AND STDS.CVE_SECCION = :cveSeccion " +
		            " 	 AND STDP.CVE_ENTIDAD = STDS.CVE_ENTIDAD " +
		            " 	 AND STDP.CVE_PROCESO = STDS.CVE_PROCESO " +
		            " 	 AND STDP.VERSION = STDS.VERSION " +
		            "	 AND STDP.SECUENCIA_DOCUMENTO = STDS.SECUENCIA_DOCUMENTO " +
		            "ORDER BY STDS.ORDEN_PRESENTACION",
		    nativeQuery = true)
		List<Object> obtenerDocumentos(
		    @Param("cveEntidad") String cveEntidad,
		    @Param("cveProceso") String cveProceso,
		    @Param("version") BigDecimal version,
			@Param("cveInstancia") String cveInstancia,
		    @Param("cveNodo") String cveNodo,
		    @Param("idNodo") Integer idNodo,
		    @Param("cveSeccion") String cveSeccion);
	
	
	@Query(
			   value = "SELECT " +
					   " 	STDP.SECUENCIA_DOCUMENTO AS SECUENCIA, " +
					   " 	STDP.DESCRIPCION AS DESCRIPCION, " +
					   " 	STDS.REQUERIDA AS REQUERIDO " +
					   "FROM " +
					   " 	ST_DOCUMENTO_SECCION STDS, " +
					   " 	ST_DOCUMENTO_PROCESO STDP " +
					   "WHERE " +
					   " 	STDS.CVE_ENTIDAD = :cveEntidad " +
					   "	 AND STDS.CVE_PROCESO = :cveProceso " +
					   "	 AND STDS.VERSION = :version " +
					   "	 AND STDS.CVE_NODO = :cveNodo " +
					   " 	 AND STDS.ID_NODO = :idNodo " +
					   "	 AND STDS.CVE_SECCION = :cveSeccion " +
					   " 	 AND STDP.CVE_ENTIDAD = STDS.CVE_ENTIDAD " +
					   " 	 AND STDP.CVE_PROCESO = STDS.CVE_PROCESO " +
					   " 	 AND STDP.VERSION = STDS.VERSION " +
					   "	 AND STDP.SECUENCIA_DOCUMENTO = STDS.SECUENCIA_DOCUMENTO " +
					   "ORDER BY STDS.ORDEN_PRESENTACION",
					   nativeQuery = true)
	   List<Object> obtenerDocumentosMultiple(
			   @Param("cveEntidad") String cveEntidad,
			   @Param("cveProceso") String cveProceso,
			   @Param("version") BigDecimal version,
			   @Param("cveNodo") String cveNodo,
			   @Param("idNodo") Integer idNodo,
			   @Param("cveSeccion") String cveSeccion);

	

	@Query(value = "SELECT DISTINCT stds.CVE_SECCION " +
            "FROM ST_DOCUMENTO_SECCION stds, ST_DOCUMENTO_PROCESO stdp " +
            "WHERE stds.CVE_ENTIDAD = :cveEntidad " +
            "AND stds.CVE_PROCESO = :cveProceso " +
            "AND stds.VERSION = :version " +
           // "AND stds.SECUENCIA_DOCUMENTO = :secuenciaDocumento " +
            //"AND stds.ID_NODO = :idNodo " +
            "AND stdp.CVE_ENTIDAD = stds.CVE_ENTIDAD " +
            "AND stdp.CVE_PROCESO = stds.CVE_PROCESO " +
            "AND stdp.VERSION = stds.VERSION " +
            "AND stdp.SECUENCIA_DOCUMENTO = stds.SECUENCIA_DOCUMENTO " +
            "ORDER BY stds.CVE_SECCION", nativeQuery = true)
String encuentraSeccion(
         @Param("cveEntidad") String cveEntidad,
         @Param("cveProceso") String cveProceso,
         @Param("version") BigDecimal version);

	
	   @Query(value = "SELECT MAX(ORDEN_PRESENTACION) " +
               "FROM ST_DOCUMENTO_SECCION " +
               "WHERE CVE_ENTIDAD = :cveEntidad " +
               "AND CVE_PROCESO = :cveProceso " +
               "AND VERSION = :version " +
               "AND CVE_NODO = :cveNodo " +
               "AND ID_NODO = :idNodo " +
               "AND CVE_SECCION = :cveSeccion", nativeQuery = true)
	Integer findMaxOrdenPresentacion(
	        @Param("cveEntidad") String cveEntidad,
	        @Param("cveProceso") String cveProceso,
	        @Param("version") BigDecimal version,
	        @Param("cveNodo") String cveNodo,
	        @Param("idNodo") Integer idNodo,
	        @Param("cveSeccion") String cveSeccion
			);
	   
	   /**VENCIMIENTO DE DOCUMUENTOS*/
		@Query(value = "SELECT STRN.CVE_ROL " +
				"FROM ST_DOCUMENTO_SECCION STDS, ST_ROL_NODO STRN " +
				"WHERE STDS.CVE_ENTIDAD = :cveEntidad AND " +
				"      STDS.CVE_PROCESO = :cveProceso AND " +
				"      STDS.VERSION = :version AND " +
				"      STDS.SECUENCIA_DOCUMENTO = :secuenciaDocumento AND " +
				"      STDS.SOLO_CONSULTA = :soloConsulta AND " +
				"      STRN.CVE_ENTIDAD = STDS.CVE_ENTIDAD AND " +
				"      STRN.CVE_PROCESO = STDS.CVE_PROCESO AND " +
				"      STRN.VERSION = STDS.VERSION AND " +
				"      STRN.CVE_NODO = STDS.CVE_NODO AND " +
				"      STRN.ID_NODO = STDS.ID_NODO", nativeQuery = true)									  
		List<String> findRoles(@Param("cveEntidad") String cveEntidad,
				@Param("cveProceso") String cveProceso,
				@Param("version") BigDecimal version,
				@Param("secuenciaDocumento") Integer secuenciaDocumento,											 
				@Param("soloConsulta") String soloConsulta);
		
		
		@Query(
			    value = "SELECT NOMBRE_ARCHIVO " +
			            "FROM IN_DOCUMENTO_PROCESO_OC " +
			            "WHERE CVE_ENTIDAD = :cveEntidad " +
			            "  AND CVE_PROCESO = :cveProceso " +
			            "  AND VERSION = :version " +
			            "  AND CVE_INSTANCIA = :cveInstancia " +
			            "  AND SECUENCIA_DOCUMENTO = :secuenciaDocumento",
			    nativeQuery = true
			)
			List<String> obtenerNombresArchivo(
			    @Param("cveEntidad") String cveEntidad,
			    @Param("cveProceso") String cveProceso,
			    @Param("version") BigDecimal version,
			    @Param("cveInstancia") String cveInstancia,
			    @Param("secuenciaDocumento") Integer secuenciaDocumento
			);

		@Query(value = "SELECT * " +
	               "FROM ST_DOCUMENTO_SECCION " +
	               "WHERE CVE_ENTIDAD = :cveEntidad " +
	               "AND CVE_PROCESO = :cveProceso " +
	               "AND VERSION = :version " +
	               "AND CVE_NODO = :cveNodo " +
	               "AND ID_NODO = :idNodo " +
	               "AND CVE_SECCION = :cveSeccion", nativeQuery = true)
		List<StDocumentoSeccion> encuentraStDocumentoSeccion(
		        @Param("cveEntidad") String cveEntidad,
		        @Param("cveProceso") String cveProceso,
		        @Param("version") BigDecimal version,
		        @Param("cveNodo") String cveNodo,
		        @Param("idNodo") Integer idNodo,
		        @Param("cveSeccion") String cveSeccion
				);

}
