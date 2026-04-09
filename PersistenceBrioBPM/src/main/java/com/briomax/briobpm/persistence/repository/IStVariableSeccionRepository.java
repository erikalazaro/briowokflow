package com.briomax.briobpm.persistence.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.briomax.briobpm.persistence.entity.StVariableSeccion;
import com.briomax.briobpm.persistence.entity.StVariableSeccionPK;

/**
 * El objetivo de la Interface InVariableProcesoRepository.java es ...
 * @author Alexis Zamoea 
 * @version 1.0 Fecha de creacion Nov 22, 2023 Modificaciones:
 * @since JDK 1.8
 */
public interface IStVariableSeccionRepository extends JpaRepository<StVariableSeccion, StVariableSeccionPK> {
	
	@Query(
		    value = "SELECT stvs.CVE_VARIABLE " +
		            "FROM ST_VARIABLE_SECCION stvs, ST_SECCION_PROCESO stsp, ST_VARIABLE_PROCESO stvp " +
		            "WHERE stvs.CVE_ENTIDAD = :cveEntidad " +
		            "AND stvs.CVE_PROCESO = :cveProceso " +
		            "AND stvs.VERSION = :version " +
		            "AND stvs.CVE_NODO = :cveNodo " +
		            "AND stvs.ID_NODO = :idNodo " +
		            "AND stsp.CVE_ENTIDAD = stvs.CVE_ENTIDAD " +
		            "AND stsp.CVE_PROCESO = stvs.CVE_PROCESO " +
		            "AND stsp.VERSION = stvs.VERSION " +
		            "AND stsp.CVE_SECCION = stvs.CVE_SECCION " +
		            "AND ((stsp.TIPO_PRESENTACION = 'GRID' AND stsp.CONTENIDO = 'DOCUMENTOS' " +
		            "AND :tipoSeccion = 'DOCUMENTOS' " +
		            "AND stvs.CVE_VARIABLE NOT IN :vpro) " +  
		            "OR (stsp.TIPO_PRESENTACION <> 'GRID')) " +
		            "AND stvp.CVE_ENTIDAD = stvs.CVE_ENTIDAD " +
		            "AND stvp.CVE_PROCESO = stvs.CVE_PROCESO " +
		            "AND stvp.VERSION = stvs.VERSION " +
		            "AND stvp.CVE_VARIABLE = stvs.CVE_VARIABLE " +
		            "ORDER BY stvs.NUMERO_RENGLON", nativeQuery = true)
		List<String> encuentraVariablesDetalles(
		        @Param("cveEntidad") String cveEntidad,
		        @Param("cveProceso") String cveProceso,
		        @Param("version") BigDecimal version,
		        @Param("cveNodo") String cveNodo,
		        @Param("idNodo") Integer idNodo,
		        @Param("tipoSeccion") String tipoSeccion,
		        @Param("vpro") List<String> vpro);
	
	@Query(value = "SELECT " +
			"VS.NUMERO_RENGLON AS LUGAR_DEL_DATO_EN_EL_GRID, " +
			"VP.CVE_VARIABLE, " +
			"VP.ETIQUETA, " +
			"VS.ANCHO_COLUMNA, " +
			"VP.TIPO, " +
			"VP.LONGITUD, " +
			"VP.ENTEROS, " +
			"VP.DECIMALES, " +
			"VP.FORMATO_FECHA, " +
			"VP.ORIGEN_MONEDA, " +
			"VP.CVE_MONEDA_VISIBLE, " +
			"VS.SOLO_CONSULTA AS TIPO_INTERACCION, " +
			"VS.ENVIO_GRABAR, " +
			"VS.VISIBLE, " +
			"VP.TIPO_CONTROL, " +
			"VP.FORMULA, " +
			"VP.FUNCION, " +
			"VP.TIPO_LISTA, " +
			"VP.VALOR_LISTA, " +
			"VP.DESCRIPCION_LISTA, " +
			"VP.TABLA_LISTA, " +
			"VP.WHERE_LISTA, " +
			"VS.NUMERO_COLUMNA " +
		" FROM ST_VARIABLE_SECCION VS, ST_VARIABLE_PROCESO VP " +
		" WHERE " +
			"VS.CVE_ENTIDAD = VP.CVE_ENTIDAD " +
			"AND VS.CVE_PROCESO = VP.CVE_PROCESO " +
			"AND VS.VERSION = VP.VERSION " +
			"AND VS.CVE_VARIABLE = VP.CVE_VARIABLE " +
			"AND VS.CVE_ENTIDAD = :cveEntidad " +
			"AND VS.CVE_PROCESO = :cveProceso " +
			"AND VS.VERSION = :version " +
			"AND VS.CVE_NODO = :cveNodo " +
			"AND VS.ID_NODO = :idNodo " +
			"AND VS.CVE_SECCION = :cveSeccion " +
		"ORDER BY VS.NUMERO_RENGLON", nativeQuery = true)
	List<Object[]> leeDatosSeccion(
	    @Param("cveEntidad") String cveEntidad,
	    @Param("cveProceso") String cveProceso,
	    @Param("version") BigDecimal version,
	    @Param("cveNodo") String cveNodo,
	    @Param("idNodo") Integer idNodo,
	    @Param("cveSeccion") String cveSeccion);

	//SP_LEE_INF_SECCION_VP - CARGA UNA TABLA TEMPORAL CON LOS DATOS DE LAS VARIABLES DE PROCESO PARA UNA SECCIÓN DE TIPO GRID
	// le quite el case para y le agregue 2 columnas más para hacer el case en java.
	@Query(value = "SELECT " + 
	        " 	INVP.OCURRENCIA			AS OCURRENCIA, " +
	        " 	STVS.NUMERO_RENGLON	AS ORDEN_PRESENTACION, " +
	        " 	STVS.CVE_VARIABLE			AS CVE_VARIABLE, " +
	        " 	STVP.TIPO					AS TIPO, " +
	        " 	INVP.SECUENCIA_VALOR		AS SECUENCIA_VALOR, " +
	        " 	INVP.VALOR_ALFANUMERICO	AS VALOR_ALFANUMERICO, " +
	        " 	' ' 						AS VPRO_01_ARCHIVO_DOC, " +
	        " 	' ' 						AS VPRO_01_REQUERIDO_DOC, " +
	        " 	INVP.VALOR_ENTERO			AS VALOR_ENTERO, " +
	        " 	INVP.VALOR_DECIMAL		AS VALOR_DECIMAL, " +
	        " 	INVP.VALOR_FECHA			AS VALOR_FECHA, " +
	        " 	STVP.FORMULA				AS FORMULA " +
	        " FROM ST_VARIABLE_SECCION STVS " +
	        " LEFT OUTER JOIN IN_VARIABLE_PROCESO INVP " +
	        " ON " +
	        "	INVP.CVE_ENTIDAD = STVS.CVE_ENTIDAD		AND " +
	        " 	INVP.CVE_PROCESO = STVS.CVE_PROCESO		AND " +
	        " 	INVP.VERSION = STVS.VERSION	 			AND " +
	        " 	INVP.CVE_INSTANCIA = :cveInstancia		AND " +
	        " 	INVP.CVE_VARIABLE = STVS.CVE_VARIABLE, " +
	        " ST_VARIABLE_PROCESO STVP,  " +
	        " ST_SECCION_PROCESO STSP " +
	        " WHERE " +
	        " 	STVS.CVE_ENTIDAD = :cveEntidad " +
	        " 	AND STVS.CVE_PROCESO = :cveProceso " +
	        " 	AND STVS.VERSION = :version " +
	        " 	AND STVS.CVE_NODO = :cveNodo " +
	        " 	AND STVS.ID_NODO = :idNodo " +
	        " 	AND STVS.CVE_SECCION = :cveSeccion " +
	        " 	AND STVS.CVE_VARIABLE NOT IN (:vpro01SecDoc, :vpro01DescDoc, :vpro01ArchivoDoc, :vpro01RequeridoDoc) " +
	        " 	AND STVP.CVE_ENTIDAD = STVS.CVE_ENTIDAD  " + 
	        " 	AND STVP.CVE_PROCESO = STVS.CVE_PROCESO  " +
	        " 	AND STVP.VERSION = STVS.VERSION		  " +
	        " 	AND STVP.CVE_VARIABLE = STVS.CVE_VARIABLE  " +				
	        " 	AND STSP.CVE_ENTIDAD = STVS.CVE_ENTIDAD  " +
	        " 	AND STSP.CVE_PROCESO = STVS.CVE_PROCESO  " +
	        " 	AND STSP.VERSION = STVS.VERSION		  " +
	        " 	AND STSP.CVE_SECCION = STVS.CVE_SECCION " +
	    " UNION ALL " +
	        "SELECT	 " +
	        " 	STDS.SECUENCIA_DOCUMENTO		AS OCURRENCIA, " +
	        " 	STVS.ORDEN_PRESENTACION			AS ORDEN_PRESENTACION, " +
	        " 	STVS.CVE_VARIABLE				AS CVE_VARIABLE, " +
	        " 	STVP.TIPO						AS TIPO, " +
	        " 	1								AS SECUENCIA_VALOR," +
	        " 	STDP.DESCRIPCION 				AS VALOR_ALFANUMERICO, " +
	        " 	INDP.NOMBRE_ARCHIVO 			AS VPRO_01_ARCHIVO_DOC, " +
	        " 	STDS.REQUERIDA 					AS VPRO_01_REQUERIDO_DOC, " +
	        " 	STDS.SECUENCIA_DOCUMENTO 		AS VALOR_ENTERO, " +
	        " 	NULL 							AS VALOR_DECIMAL, "  +
	        " 	NULL 							AS VALOR_FECHA, "  +
	        " 	NULL 							AS FORMULA " +
	        " FROM	ST_DOCUMENTO_SECCION				STDS" +
	        " LEFT OUTER JOIN" +
	        " 	IN_DOCUMENTO_PROCESO_OC		INDP ON" +
	        " 	INDP.CVE_ENTIDAD = STDS.CVE_ENTIDAD			AND" +
	        " 	INDP.CVE_PROCESO = STDS.CVE_PROCESO			AND" +
	        " 	INDP.VERSION = STDS.VERSION					AND" +
	        " 	INDP.CVE_INSTANCIA = :cveInstancia			AND" +
	        " 	INDP.SECUENCIA_DOCUMENTO = STDS.SECUENCIA_DOCUMENTO, " +
	        " 	ST_DOCUMENTO_PROCESO			STDP, " +
	        " 	ST_VARIABLE_SECCION				STVS, " +
	        " 	ST_VARIABLE_PROCESO				STVP " +
	        " WHERE " +
	        " 	STDS.CVE_ENTIDAD = :cveEntidad				AND" +
	        " 	STDS.CVE_PROCESO = :cveProceso				AND" +
	        " 	STDS.VERSION = :version 					AND" +
	        " 	STDS.CVE_NODO = :cveNodo					AND" +
	        " 	STDS.ID_NODO = :idNodo 						AND" +
	        " 	STDS.CVE_SECCION = :cveSeccion 				AND" +
	        " 	STDP.CVE_ENTIDAD = STDS.CVE_ENTIDAD			AND" +
	        " 	STDP.CVE_PROCESO = STDS.CVE_PROCESO			AND" +
	        " 	STDP.VERSION = STDS.VERSION					AND" +
	        " 	STDP.SECUENCIA_DOCUMENTO = STDS.SECUENCIA_DOCUMENTO	AND" +
	        " 	STVS.CVE_ENTIDAD = STDS.CVE_ENTIDAD			AND" +
	        " 	STVS.CVE_PROCESO = STDS.CVE_PROCESO			AND" +
	        " 	STVS.VERSION = STDS.VERSION					AND" +
	        " 	STVS.CVE_NODO = STDS.CVE_NODO				AND" +
	        " 	STVS.ID_NODO = STDS.ID_NODO					AND" +
	        " 	STVS.CVE_SECCION = STDS.CVE_SECCION			AND" +
	        " 	STVS.CVE_VARIABLE IN" +
	        " 	(" +
	        " 		:vpro01SecDoc, " +
	        " 		:vpro01DescDoc, " +
	        " 		:vpro01ArchivoDoc, " +
	        " 		:vpro01RequeridoDoc " +
	        " 	)											AND" +
	        " 	STVP.CVE_ENTIDAD = STVS.CVE_ENTIDAD			AND" +
	        " 	STVP.CVE_PROCESO = STVS.CVE_PROCESO			AND" +
	        " 	STVP.VERSION = STVS.VERSION					AND" +
	        " 	STVP.CVE_VARIABLE = STVS.CVE_VARIABLE " +
	        " ORDER BY" +
	        " 	1, 2, 3, 5", nativeQuery = true)
	List<Object> cargaVariablesProceso(
	    @Param("cveEntidad") String cveEntidad,
	    @Param("cveProceso") String cveProceso,
	    @Param("version") BigDecimal version,
	    @Param("cveInstancia") String cveInstancia,
	    @Param("cveNodo") String cveNodo,
	    @Param("idNodo") Integer idNodo,
	    @Param("cveSeccion") String cveSeccion,
	    @Param("vpro01SecDoc") String vpro01SecDoc,
	    @Param("vpro01DescDoc") String vpro01DescDoc,
	    @Param("vpro01ArchivoDoc") String vpro01ArchivoDoc,
	    @Param("vpro01RequeridoDoc") String vpro01RequeridoDoc);

//////////////////NUEVO CODIGO PRUEBA
	@Query(value = 
		    "SELECT INVP.OCURRENCIA AS OCURRENCIA, " +
		    "       0 AS SECUENCIA_DOCUMENTO, " +
		    "       STVS.NUMERO_RENGLON AS ORDEN_PRESENTACION, " +
		    "       STVS.CVE_VARIABLE AS CVE_VARIABLE, " +
		    "       STVP.TIPO AS TIPO, " +
		    "       INVP.SECUENCIA_VALOR AS SECUENCIA_VALOR, " +
		    "       INVP.VALOR_ALFANUMERICO AS VALOR_ALFANUMERICO, " +
		    "       ' ' AS VPRO_01_ARCHIVO_DOC, " +
		    "       ' ' AS VPRO_01_REQUERIDO_DOC, " +
		    "       INVP.VALOR_ENTERO AS VALOR_ENTERO, " +
		    "       INVP.VALOR_DECIMAL AS VALOR_DECIMAL, " +
		    "       INVP.VALOR_FECHA AS VALOR_FECHA, " +
		    "       STVP.FORMULA AS FORMULA " +
		    "FROM ST_VARIABLE_SECCION STVS " +
		    "LEFT OUTER JOIN IN_VARIABLE_PROCESO INVP " +
		    "     ON INVP.CVE_ENTIDAD = STVS.CVE_ENTIDAD " +
		    "    AND INVP.CVE_PROCESO = STVS.CVE_PROCESO " +
		    "    AND INVP.VERSION = STVS.VERSION " +
		    "    AND INVP.CVE_INSTANCIA = :cveInstancia " +
		    "    AND INVP.CVE_VARIABLE = STVS.CVE_VARIABLE, " +
		    "     ST_VARIABLE_PROCESO STVP, " +
		    "     ST_SECCION_PROCESO STSP " +
		    "WHERE STVS.CVE_ENTIDAD = :cveEntidad " +
		    "  AND STVS.CVE_PROCESO = :cveProceso " +
		    "  AND STVS.VERSION = :version " +
		    "  AND STVS.CVE_NODO = :cveNodo " +
		    "  AND STVS.ID_NODO = :idNodo " +
		    "  AND STVS.CVE_SECCION = :cveSeccion " +
		    "  AND STVS.CVE_VARIABLE NOT IN ('VPRO_01_SECUENCIA_DOCUMENTO', 'VPRO_01_DESCRIPCION_DOCUMENTO', 'VPRO_01_ARCHIVO_DOCUMENTO', 'VPRO_01_REQUERIDO_DOCUMENTO') " +
		    "  AND STVP.CVE_ENTIDAD = STVS.CVE_ENTIDAD " +
		    "  AND STVP.CVE_PROCESO = STVS.CVE_PROCESO " +
		    "  AND STVP.VERSION = STVS.VERSION " +
		    "  AND STVP.CVE_VARIABLE = STVS.CVE_VARIABLE " +
		    "  AND STSP.CVE_ENTIDAD = STVS.CVE_ENTIDAD " +
		    "  AND STSP.CVE_PROCESO = STVS.CVE_PROCESO " +
		    "  AND STSP.VERSION = STVS.VERSION " +
		    "  AND STSP.CVE_SECCION = STVS.CVE_SECCION " +
		    "UNION ALL " +
		    "SELECT INDP.OCURRENCIA AS OCURRENCIA, " +
		    "       STDS.SECUENCIA_DOCUMENTO AS SECUENCIA_DOCUMENTO, " +
		    "       STVS.NUMERO_RENGLON AS ORDEN_PRESENTACION, " +
		    "       STVS.CVE_VARIABLE AS CVE_VARIABLE, " +
		    "       STVP.TIPO AS TIPO, " +
		    "       1 AS SECUENCIA_VALOR, " +
		    "       STDP.DESCRIPCION AS VALOR_ALFANUMERICO, " +
		    "       INDP.NOMBRE_ARCHIVO AS VPRO_01_ARCHIVO_DOC, " +
		    "       STDS.REQUERIDA AS VPRO_01_REQUERIDO_DOC, " +
		    "       STDS.SECUENCIA_DOCUMENTO AS VALOR_ENTERO, " +
		    "       NULL AS VALOR_DECIMAL, " +
		    "       NULL AS VALOR_FECHA, " +
		    "       NULL AS FORMULA " +
		    "FROM ST_DOCUMENTO_SECCION STDS " +
		    "LEFT OUTER JOIN IN_DOCUMENTO_PROCESO_OC INDP " +
		    "     ON INDP.CVE_ENTIDAD = STDS.CVE_ENTIDAD " +
		    "    AND INDP.CVE_PROCESO = STDS.CVE_PROCESO " +
		    "    AND INDP.VERSION = STDS.VERSION " +
		    "    AND INDP.CVE_INSTANCIA = :cveInstancia " +
		    "    AND INDP.SECUENCIA_DOCUMENTO = STDS.SECUENCIA_DOCUMENTO " +
		    "    AND INDP.SECUENCIA_ARCHIVO = 1, " +
		    "     ST_DOCUMENTO_PROCESO STDP, " +
		    "     ST_VARIABLE_SECCION STVS, " +
		    "     ST_VARIABLE_PROCESO STVP " +
		    "WHERE STDS.CVE_ENTIDAD = :cveEntidad " +
		    "  AND STDS.CVE_PROCESO = :cveProceso " +
		    "  AND STDS.VERSION = :version " +
		    "  AND STDS.CVE_NODO = :cveNodo " +
		    "  AND STDS.ID_NODO = :idNodo " +
		    "  AND STDS.CVE_SECCION = :cveSeccion " +
		    "  AND STDP.CVE_ENTIDAD = STDS.CVE_ENTIDAD " +
		    "  AND STDP.CVE_PROCESO = STDS.CVE_PROCESO " +
		    "  AND STDP.VERSION = STDS.VERSION " +
		    "  AND STDP.SECUENCIA_DOCUMENTO = STDS.SECUENCIA_DOCUMENTO " +
		    "  AND STVS.CVE_ENTIDAD = STDS.CVE_ENTIDAD " +
		    "  AND STVS.CVE_PROCESO = STDS.CVE_PROCESO " +
		    "  AND STVS.VERSION = STDS.VERSION " +
		    "  AND STVS.CVE_NODO = STDS.CVE_NODO " +
		    "  AND STVS.ID_NODO = STDS.ID_NODO " +
		    "  AND STVS.CVE_SECCION = STDS.CVE_SECCION " +
		    "  AND STVS.CVE_VARIABLE IN ('VPRO_01_SECUENCIA_DOCUMENTO', 'VPRO_01_DESCRIPCION_DOCUMENTO', 'VPRO_01_ARCHIVO_DOCUMENTO', 'VPRO_01_REQUERIDO_DOCUMENTO') " +
		    "  AND STVP.CVE_ENTIDAD = STVS.CVE_ENTIDAD " +
		    "  AND STVP.CVE_PROCESO = STVS.CVE_PROCESO " +
		    "  AND STVP.VERSION = STVS.VERSION " +
		    "  AND STVP.CVE_VARIABLE = STVS.CVE_VARIABLE " +
		    "ORDER BY SECUENCIA_DOCUMENTO, OCURRENCIA ASC, ORDEN_PRESENTACION ASC, TIPO ASC",
		    nativeQuery = true)
		List<Object> cargaVariablesProcesoMultiple(
		    @Param("cveEntidad") String cveEntidad,
		    @Param("cveProceso") String cveProceso,
		    @Param("version") BigDecimal version,
		    @Param("cveInstancia") String cveInstancia,
		    @Param("cveNodo") String cveNodo,
		    @Param("idNodo") Integer idNodo,
		    @Param("cveSeccion") String cveSeccion
		);
	
	
	@Query(value = " SELECT a.OCURRENCIA, " + 
			"	a.SECUENCIA_DOCUMENTO, " + 
			"	a.ORDEN_PRESENTACION, " + 
			"	a.CVE_VARIABLE, " + 
			"	a.TIPO, " + 
			"	a.SECUENCIA_VALOR, " + 
			"	a.VALOR_ALFANUMERICO, " + 
			"	a.VPRO_01_ARCHIVO_DOC, " + 
			"	a.VPRO_01_REQUERIDO_DOC, " + 
			"	a.VALOR_ENTERO, " + 
			"	a.VALOR_DECIMAL, " + 
			"	a.VALOR_FECHA, " + 
			"	a.FORMULA, " + 
			"	a.VALOR_IMAGEN " + 
			"FROM ( " +
		    "SELECT INVP.OCURRENCIA AS OCURRENCIA, " +
		    "       0 AS SECUENCIA_DOCUMENTO, " +
		    "       STVS.NUMERO_RENGLON AS ORDEN_PRESENTACION, " +
		    "       STVS.CVE_VARIABLE AS CVE_VARIABLE, " +
		    "       STVP.TIPO AS TIPO, " +
		    "       INVP.SECUENCIA_VALOR AS SECUENCIA_VALOR, " +
		    "       INVP.VALOR_ALFANUMERICO AS VALOR_ALFANUMERICO, " +
		    "       ' ' AS VPRO_01_ARCHIVO_DOC, " +
		    "       ' ' AS VPRO_01_REQUERIDO_DOC, " +
		    "       INVP.VALOR_ENTERO AS VALOR_ENTERO, " +
		    "       INVP.VALOR_DECIMAL AS VALOR_DECIMAL, " +
		    "       INVP.VALOR_FECHA AS VALOR_FECHA, " +
		    "       STVP.FORMULA AS FORMULA, " +
		    "       NULL AS VALOR_IMAGEN " +
		    "FROM ST_VARIABLE_SECCION STVS " +
		    "LEFT OUTER JOIN IN_VARIABLE_PROCESO INVP " +
		    "     ON INVP.CVE_ENTIDAD = STVS.CVE_ENTIDAD " +
		    "    AND INVP.CVE_PROCESO = STVS.CVE_PROCESO " +
		    "    AND INVP.VERSION = STVS.VERSION " +
		    "    AND INVP.CVE_INSTANCIA = :cveInstancia " +
		    "    AND INVP.CVE_VARIABLE = STVS.CVE_VARIABLE, " +
		    "     ST_VARIABLE_PROCESO STVP, " +
		    "     ST_SECCION_PROCESO STSP " +
		    "WHERE STVS.CVE_ENTIDAD = :cveEntidad " +
		    "  AND STVS.CVE_PROCESO = :cveProceso " +
		    "  AND STVS.VERSION = :version " +
		    "  AND STVS.CVE_NODO = :cveNodo " +
		    "  AND STVS.ID_NODO = :idNodo " +
		    "  AND STVS.CVE_SECCION = :cveSeccion " +
		    "  AND STVS.CVE_VARIABLE NOT IN ('VPRO_01_SECUENCIA_DOCUMENTO', 'VPRO_01_DESCRIPCION_DOCUMENTO', 'VPRO_01_ARCHIVO_DOCUMENTO', 'VPRO_01_REQUERIDO_DOCUMENTO') " +
		    "  AND STVP.CVE_ENTIDAD = STVS.CVE_ENTIDAD " +
		    "  AND STVP.CVE_PROCESO = STVS.CVE_PROCESO " +
		    "  AND STVP.VERSION = STVS.VERSION " +
		    "  AND STVP.CVE_VARIABLE = STVS.CVE_VARIABLE " +
		    "  AND STSP.CVE_ENTIDAD = STVS.CVE_ENTIDAD " +
		    "  AND STSP.CVE_PROCESO = STVS.CVE_PROCESO " +
		    "  AND STSP.VERSION = STVS.VERSION " +
		    "  AND STSP.CVE_SECCION = STVS.CVE_SECCION " +
		    "  AND STVP.TIPO != 'IMAGEN' " +
		    "UNION ALL " +
		    " SELECT INVP.OCURRENCIA AS OCURRENCIA,  " + 
		    "       0 AS SECUENCIA_DOCUMENTO,  " + 
		    "       STVS.NUMERO_RENGLON AS ORDEN_PRESENTACION,  " + 
		    "       STVS.CVE_VARIABLE AS CVE_VARIABLE,  " + 
		    "       STVP.TIPO AS TIPO,  " + 
		    "       INVP.SECUENCIA_VALOR AS SECUENCIA_VALOR,  " + 
		    "       NULL AS VALOR_ALFANUMERICO,  " + 
		    "       ' ' AS VPRO_01_ARCHIVO_DOC,  " + 
		    "       ' ' AS VPRO_01_REQUERIDO_DOC,  " + 
		    "       NULL AS VALOR_ENTERO,  " + 
		    "       NULL AS VALOR_DECIMAL,  " + 
		    "       NULL AS VALOR_FECHA,  " + 
		    "       STVP.FORMULA AS FORMULA,  " + 
		    "       INVP.VALOR_IMAGEN AS VALOR_IMAGEN  " + 
		    "		    FROM ST_VARIABLE_SECCION STVS  " + 
		    "		    LEFT OUTER JOIN IN_IMAGEN_PROCESO INVP  " + 
		    "		         ON INVP.CVE_ENTIDAD = STVS.CVE_ENTIDAD  " + 
		    "		        AND INVP.CVE_PROCESO = STVS.CVE_PROCESO  " + 
		    "		        AND INVP.VERSION = STVS.VERSION  " + 
		    "		        AND INVP.CVE_INSTANCIA = :cveInstancia  " + 
		    "		        AND INVP.CVE_VARIABLE = STVS.CVE_VARIABLE,  " + 
		    "		     ST_VARIABLE_PROCESO STVP,  " + 
		    "		     ST_SECCION_PROCESO STSP  " + 
		    "		    WHERE STVS.CVE_ENTIDAD = :cveEntidad  " + 
		    "		      AND STVS.CVE_PROCESO = :cveProceso  " + 
		    "		      AND STVS.VERSION = :version " + 
		    "		      AND STVS.CVE_NODO = :cveNodo " + 
		    "		      AND STVS.ID_NODO = :idNodo  " + 
		    "		      AND STVS.CVE_SECCION = :cveSeccion  " + 
		    "		      AND STVS.CVE_VARIABLE NOT IN ('VPRO_01_SECUENCIA_DOCUMENTO', 'VPRO_01_DESCRIPCION_DOCUMENTO', 'VPRO_01_ARCHIVO_DOCUMENTO', 'VPRO_01_REQUERIDO_DOCUMENTO')  " + 
		    "		      AND STVP.CVE_ENTIDAD = STVS.CVE_ENTIDAD  " + 
		    "		      AND STVP.CVE_PROCESO = STVS.CVE_PROCESO  " + 
		    "		      AND STVP.VERSION = STVS.VERSION  " + 
		    "		      AND STVP.CVE_VARIABLE = STVS.CVE_VARIABLE  " + 
		    "		      AND STSP.CVE_ENTIDAD = STVS.CVE_ENTIDAD  " + 
		    "		      AND STSP.CVE_PROCESO = STVS.CVE_PROCESO  " + 
		    "		      AND STSP.VERSION = STVS.VERSION  " + 
		    "		      AND STSP.CVE_SECCION = STVS.CVE_SECCION  " + 
		    "			  AND STVP.TIPO = 'IMAGEN' ) a " +
		    " ORDER BY a.SECUENCIA_DOCUMENTO, a.OCURRENCIA ASC, a.ORDEN_PRESENTACION ASC, a.TIPO ASC ",
		    nativeQuery = true)
		List<Object> cargaVariablesProcesoSinDocumentos(
		    @Param("cveEntidad") String cveEntidad,
		    @Param("cveProceso") String cveProceso,
		    @Param("version") BigDecimal version,
		    @Param("cveInstancia") String cveInstancia,
		    @Param("cveNodo") String cveNodo,
		    @Param("idNodo") Integer idNodo,
		    @Param("cveSeccion") String cveSeccion
		);

	
	@Query(value = 
	         "SELECT INDP.id.ocurrencia AS ocurrencia, " +
	         "  INDP.id.secuenciaDocumento AS secuenciaDocumento, " +
	         "  STVS.numeroRenglon AS ordenPresentacion, " +
	         "  STVS.id.cveVariable AS cveVariable, " +
	         "  STVP.tipo AS tipo, " +
	         "  1 AS secuenciaValor, " +
	         "  COALESCE(STDP.descripcion, 'Adjunta Evidencias') AS valorAlfanumerico, " +
	         "  INDP.nombreArchivo AS vpro01ArchivoDoc, " +
	         "  COALESCE(STDS.requerida, 'OPCIONAL') AS vpro01RequeridoDoc, " +
	         "  INDP.id.secuenciaDocumento AS valorEntero, " +
	         "  '' AS valorDecimal, " +
	         "  '' AS valorFecha, " +
	         "  '' AS formula, " +
	         "  '' AS valorImagen " +
	         " FROM InDocumentoProcesoOc INDP " +
	         "     LEFT JOIN StDocumentoSeccion STDS ON STDS.id.cveEntidad = INDP.id.cveEntidad " +
	         "     AND STDS.id.cveProceso = INDP.id.cveProceso " +
	         "     AND STDS.id.version = INDP.id.version " +
	         "     AND STDS.id.secuenciaDocumento = INDP.id.secuenciaDocumento " +
	         "     AND STDS.id.cveNodo = :cveNodo " +
	         "     AND STDS.id.idNodo = :idNodo " +
	         "     AND STDS.id.cveSeccion = :cveSeccion, " +
	         " StDocumentoProceso STDP, " +
	         " StVariableSeccion STVS, " +
	         " StVariableProceso STVP " +
	         " WHERE INDP.id.cveEntidad = :cveEntidad " +
	         " AND INDP.id.cveProceso = :cveProceso " +
	         " AND INDP.id.version = :version " +
	         " AND INDP.id.cveInstancia = :cveInstancia " +
	         " AND INDP.id.secuenciaArchivo = 1 " +
	         " AND STDP.id.cveEntidad = INDP.id.cveEntidad " +
	         " AND STDP.id.cveProceso = INDP.id.cveProceso " +
	         " AND STDP.id.version = INDP.id.version " +
	         " AND STDP.id.secuenciaDocumento = STDS.id.secuenciaDocumento " +
	         " AND STVS.id.cveEntidad = INDP.id.cveEntidad " +
	         " AND STVS.id.cveProceso = INDP.id.cveProceso " +
	         " AND STVS.id.version = INDP.id.version " +
	         " AND STVS.id.cveNodo = :cveNodo " +
	         " AND STVS.id.idNodo = :idNodo " +
	         " AND STVS.id.cveSeccion = :cveSeccion " +
	         " AND STVS.id.cveVariable IN ('VPRO_01_SECUENCIA_DOCUMENTO', 'VPRO_01_DESCRIPCION_DOCUMENTO', 'VPRO_01_ARCHIVO_DOCUMENTO', 'VPRO_01_REQUERIDO_DOCUMENTO') " +
	         " AND STVP.id.cveEntidad = STVS.id.cveEntidad " +
	         " AND STVP.id.cveProceso = STVS.id.cveProceso " +
	         " AND STVP.id.version = STVS.id.version " +
	         " AND STVP.id.cveVariable = STVS.id.cveVariable " +
	         " ORDER BY INDP.id.secuenciaDocumento, INDP.id.ocurrencia ASC, STVS.numeroRenglon ASC, STVP.tipo ASC"
	    )
		List<Object> cargaDocumentosProceso(
		    @Param("cveEntidad") String cveEntidad,
		    @Param("cveProceso") String cveProceso,
		    @Param("version") BigDecimal version,
		    @Param("cveInstancia") String cveInstancia,
		    @Param("cveNodo") String cveNodo,
		    @Param("idNodo") Integer idNodo,
		    @Param("cveSeccion") String cveSeccion
		);
	
	@Query(value = 
	 "SELECT stvs.id.cveVariable FROM StVariableSeccion stvs " +
     "WHERE stvs.id.cveEntidad = :cveEntidad " +
     "AND stvs.id.cveProceso = :cveProceso " +
     "AND stvs.id.version = :version " +
     "AND stvs.id.cveNodo = :cveNodo " +
     "AND stvs.id.idNodo = :idNodo")
     List<String> getVariables(
 		    @Param("cveEntidad") String cveEntidad,
 		    @Param("cveProceso") String cveProceso,
 		    @Param("version") BigDecimal version,
 		    @Param("cveNodo") String cveNodo,
 		    @Param("idNodo") BigDecimal idNodo
 		);

	
	@Query(value = 
	        "SELECT CVE_VARIABLE FROM ST_VARIABLE_SECCION " +
	        "WHERE CVE_ENTIDAD = :cveEntidad " +
	        "AND CVE_PROCESO = :cveProceso " +
	        "AND VERSION = :version " +
	        "AND CVE_NODO = :cveNodo " +
	        "AND CVE_SECCION = :seccion " +
	        "AND ID_NODO = :idNodo " +
	        "ORDER BY NUMERO_RENGLON", 
	        nativeQuery = true)
	List<String> getVariablesSeccion(
	        @Param("cveEntidad") String cveEntidad,
	        @Param("cveProceso") String cveProceso,
	        @Param("version") BigDecimal version,
	        @Param("cveNodo") String cveNodo,
	        @Param("idNodo") Integer idNodo,
	        @Param("seccion") String seccion
	    );


	
	@Query(value = " SELECT a.OCURRENCIA, " + 
			"	a.SECUENCIA_DOCUMENTO, " + 
			"	a.ORDEN_PRESENTACION, " + 
			"	a.CVE_VARIABLE, " + 
			"	a.TIPO, " + 
			"	a.SECUENCIA_VALOR, " + 
			"	a.VALOR_ALFANUMERICO, " + 
			"	a.VPRO_01_ARCHIVO_DOC, " + 
			"	a.VPRO_01_REQUERIDO_DOC, " + 
			"	a.VALOR_ENTERO, " + 
			"	a.VALOR_DECIMAL, " + 
			"	a.VALOR_FECHA, " + 
			"	a.FORMULA, " + 
			"	a.VALOR_IMAGEN " + 
			"FROM ( " +
		    "SELECT INVP.OCURRENCIA AS OCURRENCIA, " +
		    "       0 AS SECUENCIA_DOCUMENTO, " +
		    "       STVS.NUMERO_RENGLON AS ORDEN_PRESENTACION, " +
		    "       STVS.CVE_VARIABLE AS CVE_VARIABLE, " +
		    "       STVP.TIPO AS TIPO, " +
		    "       INVP.SECUENCIA_VALOR AS SECUENCIA_VALOR, " +
		    "       INVP.VALOR_ALFANUMERICO AS VALOR_ALFANUMERICO, " +
		    "       ' ' AS VPRO_01_ARCHIVO_DOC, " +
		    "       ' ' AS VPRO_01_REQUERIDO_DOC, " +
		    "       INVP.VALOR_ENTERO AS VALOR_ENTERO, " +
		    "       INVP.VALOR_DECIMAL AS VALOR_DECIMAL, " +
		    "       INVP.VALOR_FECHA AS VALOR_FECHA, " +
		    "       STVP.FORMULA AS FORMULA, " +
		    "       NULL AS VALOR_IMAGEN " +
		    "FROM ST_VARIABLE_SECCION STVS " +
		    "LEFT OUTER JOIN IN_VARIABLE_PROCESO INVP " +
		    "     ON INVP.CVE_ENTIDAD = STVS.CVE_ENTIDAD " +
		    "    AND INVP.CVE_PROCESO = STVS.CVE_PROCESO " +
		    "    AND INVP.VERSION = STVS.VERSION " +
		    "    AND INVP.CVE_INSTANCIA = :cveInstancia " +
		    "    AND INVP.CVE_VARIABLE = STVS.CVE_VARIABLE " +
		    "	 AND INVP.OCURRENCIA IN ( Select OCURRENCIA from ST_VALOR_INICIAL_VARIABLE " + 
		    "					WHERE CVE_ENTIDAD = INVP.CVE_ENTIDAD  " + 
		    "							and CVE_PROCESO = INVP.CVE_PROCESO " + 
		    "							and VERSION = INVP.VERSION " + 
		    "							and CVE_VARIABLE = INVP.CVE_VARIABLE " + 
		    "							and ( REFERENCIA_1 = :referencia OR REFERENCIA_2 = :referencia )), " +
		    "     ST_VARIABLE_PROCESO STVP, " +
		    "     ST_SECCION_PROCESO STSP " +
		    "WHERE STVS.CVE_ENTIDAD = :cveEntidad " +
		    "  AND STVS.CVE_PROCESO = :cveProceso " +
		    "  AND STVS.VERSION = :version " +
		    "  AND STVS.CVE_NODO = :cveNodo " +
		    "  AND STVS.ID_NODO = :idNodo " +
		    "  AND STVS.CVE_SECCION = :cveSeccion " +
		    "  AND STVS.CVE_VARIABLE NOT IN ('VPRO_01_SECUENCIA_DOCUMENTO', 'VPRO_01_DESCRIPCION_DOCUMENTO', 'VPRO_01_ARCHIVO_DOCUMENTO', 'VPRO_01_REQUERIDO_DOCUMENTO') " +
		    "  AND STVP.CVE_ENTIDAD = STVS.CVE_ENTIDAD " +
		    "  AND STVP.CVE_PROCESO = STVS.CVE_PROCESO " +
		    "  AND STVP.VERSION = STVS.VERSION " +
		    "  AND STVP.CVE_VARIABLE = STVS.CVE_VARIABLE " +
		    "  AND STSP.CVE_ENTIDAD = STVS.CVE_ENTIDAD " +
		    "  AND STSP.CVE_PROCESO = STVS.CVE_PROCESO " +
		    "  AND STSP.VERSION = STVS.VERSION " +
		    "  AND STSP.CVE_SECCION = STVS.CVE_SECCION " +
		    "  AND STVP.TIPO != 'IMAGEN' " +
		    "  AND STVS.CVE_VARIABLE IN ( SELECT DISTINCT CVE_VARIABLE FROM ST_VALOR_INICIAL_VARIABLE  " + 
		    "		    					WHERE CVE_ENTIDAD = STVS.CVE_ENTIDAD   " + 
		    "		    							and CVE_PROCESO = STVS.CVE_PROCESO  " + 
		    "		    							and VERSION = STVS.VERSION  " + 
		    "		    							and CVE_VARIABLE = STVS.CVE_VARIABLE  " + 
		    "		    							and ( REFERENCIA_1 = :referencia OR REFERENCIA_2 = :referencia ))" +
		    "  UNION ALL  " +
		    " SELECT INVP.OCURRENCIA AS OCURRENCIA,  " + 
		    "       0 AS SECUENCIA_DOCUMENTO,  " + 
		    "       STVS.NUMERO_RENGLON AS ORDEN_PRESENTACION,  " + 
		    "       STVS.CVE_VARIABLE AS CVE_VARIABLE,  " + 
		    "       STVP.TIPO AS TIPO,  " + 
		    "       INVP.SECUENCIA_VALOR AS SECUENCIA_VALOR,  " + 
		    "       NULL AS VALOR_ALFANUMERICO,  " + 
		    "       ' ' AS VPRO_01_ARCHIVO_DOC,  " + 
		    "       ' ' AS VPRO_01_REQUERIDO_DOC,  " + 
		    "       NULL AS VALOR_ENTERO,  " + 
		    "       NULL AS VALOR_DECIMAL,  " + 
		    "       NULL AS VALOR_FECHA,  " + 
		    "       STVP.FORMULA AS FORMULA,  " + 
		    "       INVP.VALOR_IMAGEN AS VALOR_IMAGEN  " + 
		    "		    FROM ST_VARIABLE_SECCION STVS  " + 
		    "		    LEFT OUTER JOIN IN_IMAGEN_PROCESO INVP  " + 
		    "		         ON INVP.CVE_ENTIDAD = STVS.CVE_ENTIDAD  " + 
		    "		        AND INVP.CVE_PROCESO = STVS.CVE_PROCESO  " + 
		    "		        AND INVP.VERSION = STVS.VERSION  " + 
		    "		        AND INVP.CVE_INSTANCIA = :cveInstancia  " + 
		    "		        AND INVP.CVE_VARIABLE = STVS.CVE_VARIABLE  " + 
		    "	 			AND INVP.OCURRENCIA IN ( Select OCURRENCIA from ST_VALOR_INICIAL_VARIABLE " + 
		    "					WHERE CVE_ENTIDAD = INVP.CVE_ENTIDAD  " + 
		    "							and CVE_PROCESO = INVP.CVE_PROCESO " + 
		    "							and VERSION = INVP.VERSION " + 
		    "							and CVE_VARIABLE = INVP.CVE_VARIABLE " + 
		    "							and ( REFERENCIA_1 = :referencia OR REFERENCIA_2 = :referencia )), " +
		    "		     ST_VARIABLE_PROCESO STVP,  " + 
		    "		     ST_SECCION_PROCESO STSP  " + 
		    "		    WHERE STVS.CVE_ENTIDAD = :cveEntidad  " + 
		    "		      AND STVS.CVE_PROCESO = :cveProceso  " + 
		    "		      AND STVS.VERSION = :version " + 
		    "		      AND STVS.CVE_NODO = :cveNodo " + 
		    "		      AND STVS.ID_NODO = :idNodo  " + 
		    "		      AND STVS.CVE_SECCION = :cveSeccion  " + 
		    "		      AND STVS.CVE_VARIABLE NOT IN ('VPRO_01_SECUENCIA_DOCUMENTO', 'VPRO_01_DESCRIPCION_DOCUMENTO', 'VPRO_01_ARCHIVO_DOCUMENTO', 'VPRO_01_REQUERIDO_DOCUMENTO')  " + 
		    "		      AND STVP.CVE_ENTIDAD = STVS.CVE_ENTIDAD  " + 
		    "		      AND STVP.CVE_PROCESO = STVS.CVE_PROCESO  " + 
		    "		      AND STVP.VERSION = STVS.VERSION  " + 
		    "		      AND STVP.CVE_VARIABLE = STVS.CVE_VARIABLE  " + 
		    "		      AND STSP.CVE_ENTIDAD = STVS.CVE_ENTIDAD  " + 
		    "		      AND STSP.CVE_PROCESO = STVS.CVE_PROCESO  " + 
		    "		      AND STSP.VERSION = STVS.VERSION  " + 
		    "		      AND STSP.CVE_SECCION = STVS.CVE_SECCION  " + 
		    "			  AND STVP.TIPO = 'IMAGEN' " +
		    "  			  AND STVS.CVE_VARIABLE IN ( SELECT DISTINCT CVE_VARIABLE FROM ST_VALOR_INICIAL_VARIABLE  " + 
		    "		    					WHERE CVE_ENTIDAD = STVS.CVE_ENTIDAD   " + 
		    "		    							and CVE_PROCESO = STVS.CVE_PROCESO  " + 
		    "		    							and VERSION = STVS.VERSION  " + 
		    "		    							and CVE_VARIABLE = STVS.CVE_VARIABLE  " + 
		    "		    							and ( REFERENCIA_1 = :referencia OR REFERENCIA_2 = :referencia ))" +
		    " ) a ORDER BY a.SECUENCIA_DOCUMENTO, a.OCURRENCIA ASC, a.ORDEN_PRESENTACION ASC, a.TIPO ASC",
		    nativeQuery = true)
		List<Object> cargaVariablesProcesoReferencia(
		    @Param("cveEntidad") String cveEntidad,
		    @Param("cveProceso") String cveProceso,
		    @Param("version") BigDecimal version,
		    @Param("cveInstancia") String cveInstancia,
		    @Param("cveNodo") String cveNodo,
		    @Param("idNodo") Integer idNodo,
		    @Param("cveSeccion") String cveSeccion,
		    @Param("referencia") String referencia
		);
	
	
	@Query(value = " SELECT a.OCURRENCIA, " + 
			"	a.SECUENCIA_DOCUMENTO, " + 
			"	a.ORDEN_PRESENTACION, " + 
			"	a.CVE_VARIABLE, " + 
			"	a.TIPO, " + 
			"	a.SECUENCIA_VALOR, " + 
			"	a.VALOR_ALFANUMERICO, " + 
			"	a.VPRO_01_ARCHIVO_DOC, " + 
			"	a.VPRO_01_REQUERIDO_DOC, " + 
			"	a.VALOR_ENTERO, " + 
			"	a.VALOR_DECIMAL, " + 
			"	a.VALOR_FECHA, " + 
			"	a.FORMULA, " + 
			"	a.VALOR_IMAGEN " + 
			"FROM ( " +
		    "SELECT INVP.OCURRENCIA AS OCURRENCIA, " +
		    "       0 AS SECUENCIA_DOCUMENTO, " +
		    "       STVS.NUMERO_RENGLON AS ORDEN_PRESENTACION, " +
		    "       STVS.CVE_VARIABLE AS CVE_VARIABLE, " +
		    "       STVP.TIPO AS TIPO, " +
		    "       INVP.SECUENCIA_VALOR AS SECUENCIA_VALOR, " +
		    "       INVP.VALOR_ALFANUMERICO AS VALOR_ALFANUMERICO, " +
		    "       ' ' AS VPRO_01_ARCHIVO_DOC, " +
		    "       ' ' AS VPRO_01_REQUERIDO_DOC, " +
		    "       INVP.VALOR_ENTERO AS VALOR_ENTERO, " +
		    "       INVP.VALOR_DECIMAL AS VALOR_DECIMAL, " +
		    "       INVP.VALOR_FECHA AS VALOR_FECHA, " +
		    "       STVP.FORMULA AS FORMULA, " +
		    "       NULL AS VALOR_IMAGEN " +
		    "FROM ST_VARIABLE_SECCION STVS " +
		    "LEFT OUTER JOIN IN_VARIABLE_PROCESO INVP " +
		    "     ON INVP.CVE_ENTIDAD = STVS.CVE_ENTIDAD " +
		    "    AND INVP.CVE_PROCESO = STVS.CVE_PROCESO " +
		    "    AND INVP.VERSION = STVS.VERSION " +
		    "    AND INVP.CVE_INSTANCIA = :cveInstancia " +
		    "    AND INVP.CVE_VARIABLE = STVS.CVE_VARIABLE, " +
		    "     ST_VARIABLE_PROCESO STVP, " +
		    "     ST_SECCION_PROCESO STSP " +
		    "WHERE STVS.CVE_ENTIDAD = :cveEntidad " +
		    "  AND STVS.CVE_PROCESO = :cveProceso " +
		    "  AND STVS.VERSION = :version " +
		    "  AND STVS.CVE_NODO = :cveNodo " +
		    "  AND STVS.ID_NODO = :idNodo " +
		    "  AND STVS.CVE_SECCION = :cveSeccion " +
		    "  AND STVS.CVE_VARIABLE NOT IN ('VPRO_01_SECUENCIA_DOCUMENTO', 'VPRO_01_DESCRIPCION_DOCUMENTO', 'VPRO_01_ARCHIVO_DOCUMENTO', 'VPRO_01_REQUERIDO_DOCUMENTO') " +
		    "  AND STVP.CVE_ENTIDAD = STVS.CVE_ENTIDAD " +
		    "  AND STVP.CVE_PROCESO = STVS.CVE_PROCESO " +
		    "  AND STVP.VERSION = STVS.VERSION " +
		    "  AND STVP.CVE_VARIABLE = STVS.CVE_VARIABLE " +
		    "  AND STSP.CVE_ENTIDAD = STVS.CVE_ENTIDAD " +
		    "  AND STSP.CVE_PROCESO = STVS.CVE_PROCESO " +
		    "  AND STSP.VERSION = STVS.VERSION " +
		    "  AND STSP.CVE_SECCION = STVS.CVE_SECCION " +
		    "  AND STVP.TIPO != 'IMAGEN' " +
		    "  AND STVS.CVE_VARIABLE NOT IN ( SELECT DISTINCT CVE_VARIABLE FROM ST_VALOR_INICIAL_VARIABLE  " + 
		    "		    					WHERE CVE_ENTIDAD = STVS.CVE_ENTIDAD   " + 
		    "		    							AND CVE_PROCESO = STVS.CVE_PROCESO  " + 
		    "		    							AND VERSION = STVS.VERSION  " + 
		    "		    							AND CVE_VARIABLE = STVS.CVE_VARIABLE  " + 
		    "		    							AND ( REFERENCIA_1 = :referencia OR REFERENCIA_2 = :referencia ))" +		    
		    "  UNION ALL  " +
		    " SELECT INVP.OCURRENCIA AS OCURRENCIA,  " + 
		    "       0 AS SECUENCIA_DOCUMENTO,  " + 
		    "       STVS.NUMERO_RENGLON AS ORDEN_PRESENTACION,  " + 
		    "       STVS.CVE_VARIABLE AS CVE_VARIABLE,  " + 
		    "       STVP.TIPO AS TIPO,  " + 
		    "       INVP.SECUENCIA_VALOR AS SECUENCIA_VALOR,  " + 
		    "       NULL AS VALOR_ALFANUMERICO,  " + 
		    "       ' ' AS VPRO_01_ARCHIVO_DOC,  " + 
		    "       ' ' AS VPRO_01_REQUERIDO_DOC,  " + 
		    "       NULL AS VALOR_ENTERO,  " + 
		    "       NULL AS VALOR_DECIMAL,  " + 
		    "       NULL AS VALOR_FECHA,  " + 
		    "       STVP.FORMULA AS FORMULA,  " + 
		    "       INVP.VALOR_IMAGEN AS VALOR_IMAGEN  " + 
		    "		    FROM ST_VARIABLE_SECCION STVS  " + 
		    "		    LEFT OUTER JOIN IN_IMAGEN_PROCESO INVP  " + 
		    "		         ON INVP.CVE_ENTIDAD = STVS.CVE_ENTIDAD  " + 
		    "		        AND INVP.CVE_PROCESO = STVS.CVE_PROCESO  " + 
		    "		        AND INVP.VERSION = STVS.VERSION  " + 
		    "		        AND INVP.CVE_INSTANCIA = :cveInstancia  " + 
		    "		        AND INVP.CVE_VARIABLE = STVS.CVE_VARIABLE,  " + 
		    "		     ST_VARIABLE_PROCESO STVP,  " + 
		    "		     ST_SECCION_PROCESO STSP  " + 
		    "		    WHERE STVS.CVE_ENTIDAD = :cveEntidad  " + 
		    "		      AND STVS.CVE_PROCESO = :cveProceso  " + 
		    "		      AND STVS.VERSION = :version " + 
		    "		      AND STVS.CVE_NODO = :cveNodo " + 
		    "		      AND STVS.ID_NODO = :idNodo  " + 
		    "		      AND STVS.CVE_SECCION = :cveSeccion  " + 
		    "		      AND STVS.CVE_VARIABLE NOT IN ('VPRO_01_SECUENCIA_DOCUMENTO', 'VPRO_01_DESCRIPCION_DOCUMENTO', 'VPRO_01_ARCHIVO_DOCUMENTO', 'VPRO_01_REQUERIDO_DOCUMENTO')  " + 
		    "		      AND STVP.CVE_ENTIDAD = STVS.CVE_ENTIDAD  " + 
		    "		      AND STVP.CVE_PROCESO = STVS.CVE_PROCESO  " + 
		    "		      AND STVP.VERSION = STVS.VERSION  " + 
		    "		      AND STVP.CVE_VARIABLE = STVS.CVE_VARIABLE  " + 
		    "		      AND STSP.CVE_ENTIDAD = STVS.CVE_ENTIDAD  " + 
		    "		      AND STSP.CVE_PROCESO = STVS.CVE_PROCESO  " + 
		    "		      AND STSP.VERSION = STVS.VERSION  " + 
		    "		      AND STSP.CVE_SECCION = STVS.CVE_SECCION  " + 
		    "			  AND STVP.TIPO = 'IMAGEN'  " +
		    "  			  AND STVS.CVE_VARIABLE NOT IN ( SELECT DISTINCT CVE_VARIABLE FROM ST_VALOR_INICIAL_VARIABLE  " + 
		    "		    					WHERE CVE_ENTIDAD = STVS.CVE_ENTIDAD   " + 
		    "		    							AND CVE_PROCESO = STVS.CVE_PROCESO  " + 
		    "		    							AND VERSION = STVS.VERSION  " + 
		    "		    							AND CVE_VARIABLE = STVS.CVE_VARIABLE  " + 
		    "		    							AND ( REFERENCIA_1 = :referencia OR REFERENCIA_2 = :referencia ))" +
		    " ) a ORDER BY a.SECUENCIA_DOCUMENTO, a.OCURRENCIA ASC, a.ORDEN_PRESENTACION ASC, a.TIPO ASC ",
		    nativeQuery = true)
		List<Object> cargaVariablesProcesoSinReferencia(
		    @Param("cveEntidad") String cveEntidad,
		    @Param("cveProceso") String cveProceso,
		    @Param("version") BigDecimal version,
		    @Param("cveInstancia") String cveInstancia,
		    @Param("cveNodo") String cveNodo,
		    @Param("idNodo") Integer idNodo,
		    @Param("cveSeccion") String cveSeccion,
		    @Param("referencia") String referencia
		);
}



