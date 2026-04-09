package com.briomax.briobpm.persistence.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.briomax.briobpm.persistence.entity.StSeccionNodo;
import com.briomax.briobpm.persistence.entity.StSeccionNodoPK;

/**
 * El objetivo de la Interface IStNodoProcesoRepository.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Abr 09, 2024 Modificaciones:
 * @since JDK 1.8
 */
@Repository
public interface IStSeccionNodoRepository extends JpaRepository<StSeccionNodo, StSeccionNodoPK>{
	   
	@Query(value = "SELECT 1 " +
	    		"FROM ST_SECCION_NODO			STSN " +
	            "WHERE  STSN.CVE_ENTIDAD = :cveEntidad"+ 
	    		"		AND STSN.CVE_PROCESO = :cveProceso " +
	            "		AND STSN.VERSION = :version " +
	            "		AND STSN.CVE_NODO = :cveNodo " +
	            "		AND STSN.ID_NODO = :idNodo ", nativeQuery = true)
	    	Integer validaSecciones(
	    		 @Param("cveEntidad") String cveEntidad,
	             @Param("cveProceso") String cveProceso,
	             @Param("version") BigDecimal version,
	             @Param("cveNodo") String cveNodo,
	             @Param("idNodo") Integer idNodo);
	
	@Query(value = "SELECT " +
    		"	STSN.ORDEN_PRESENTACION		AS ORDEN, " +
    		"	STSN.CVE_SECCION				AS CVE_SECCION, " +
    		"	STSN.ETIQUETA					AS ETIQUETA_SECCION, " +
    		"	STSP.TIPO_PRESENTACION			AS TIPO_PRESENTACION, " +
    		"	STSN.BOTON_CREAR_RENGLON		AS BOTON_CREAR_RENGLON, " +
    		"	STSN.ETIQUETA_CREAR_RENGLON	AS ETIQUETA_CREAR_RENGLON, " +
    		"	STSN.BOTON_ELIMINAR_RENGLON	AS BOTON_ELIMINAR_RENGLON, " +
    		"	STSN.ETIQUETA_ELIMINAR_RENGLON	AS ETIQUETA_ELIMINAR_RENGLON, " +
    		"	STSP.CONTENIDO					AS CONTENIDO, " +
    		"	STSN.REGISTROS_SELECCIONABLES " +
    		"FROM ST_SECCION_NODO	STSN, " +
    		"	ST_SECCION_PROCESO	STSP " +
    		"WHERE " +
    		"	STSN.CVE_ENTIDAD = :cveEntidad AND " +
    		"	STSN.CVE_PROCESO = :cveProceso AND " +
    		"	STSN.VERSION = :version AND " +
    		"	STSN.CVE_NODO = :cveNodo AND " +
    		"	STSN.ID_NODO = :idNodo AND " +
    		"	STSP.USO_SECCION = :usoSeccion AND " +
    		"	STSP.CVE_ENTIDAD = STSN.CVE_ENTIDAD AND " +
    		"	STSP.CVE_PROCESO = STSN.CVE_PROCESO AND " +
    		"	STSP.VERSION = STSN.VERSION AND " +
    		"	STSP.CVE_SECCION = STSN.CVE_SECCION  " +
    		"ORDER BY STSN.ORDEN_PRESENTACION", nativeQuery = true)
		List<Object> recuperaSeccionesNodo(
		@Param("cveEntidad") String cveEntidad,
		@Param("cveProceso") String cveProceso,
		@Param("version") BigDecimal version,
		@Param("cveNodo") String cveNodo,
		@Param("idNodo") Integer idNodo,
		@Param("usoSeccion") String usoSeccion);

}
