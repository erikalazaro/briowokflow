/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.persistence.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.briomax.briobpm.persistence.entity.StNodoProceso;
import com.briomax.briobpm.persistence.entity.StNodoProcesoPK;

/**
 * El objetivo de la Interface IStNodoProcesoRepository.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Nov 25, 2023 Modificaciones:
 * @since JDK 1.8
 */
@Repository
public interface IStNodoProcesoRepository extends JpaRepository<StNodoProceso, StNodoProcesoPK> {

	   @Query(value = "SELECT * " +
	    		"FROM ST_NODO_PROCESO		STNP " +
	            "WHERE STNP.CVE_PROCESO = :cveProceso " +
	            "	AND STNP.VERSION = :version " +
	            "	AND STNP.CVE_NODO = :cveNodo " +
	            "	AND STNP.ID_NODO = :idNodo ", nativeQuery = true)
	    Optional<StNodoProceso> obtieneParametrosActividad(
	             @Param("cveProceso") String cveProceso,
	             @Param("version") BigDecimal version,
	             @Param("cveNodo") String cveNodo,
	             @Param("idNodo") Integer idNodo);

	@Query(value = "SELECT COUNT(1) " +
	    		"FROM ST_NODO_PROCESO		STNP " +
	            "WHERE STNP.CVE_PROCESO = :cveProceso " +
	            "	AND STNP.VERSION = :version " +
	            "	AND STNP.CVE_NODO = :cveNodo " +
	            "	AND STNP.ID_NODO = :idNodo ", nativeQuery = true)
	   int contar(
	             @Param("cveProceso") String cveProceso,
	             @Param("version") BigDecimal version,
	             @Param("cveNodo") String cveNodo,
	             @Param("idNodo") BigDecimal idNodo);
	   
	//Pregunrar 	        
	@Query(value = 
	        "	SELECT DISTINCT STNP.ID_NODO " +
	        "	FROM ST_NODO_PROCESO STNP, 	" +
	        "		IN_NODO_PROCESO INNP 	" +
	        " 		LEFT JOIN IN_NODO_PROCESO_USUARIO INPU " + 
	        "					 ON INNP.CVE_ENTIDAD = INPU.CVE_ENTIDAD " + 
	        "					 AND INNP.CVE_PROCESO = INPU.CVE_PROCESO " + 
	        "					 AND INNP.VERSION = INPU.VERSION " + 
	        "					 AND INNP.CVE_NODO  = INPU.CVE_NODO  " + 
	        "					 AND INNP.ID_NODO = INPU.ID_NODO " + 
	        "					 AND INPU.CVE_USUARIO = :cveUsuario " +
	        "	WHERE STNP.CVE_ENTIDAD = :cveEntidad 	" +
	        "		AND STNP.CVE_PROCESO = :cveProceso 	" +
	        "		AND STNP.VERSION = :version 		" +
	        "		AND STNP.CVE_NODO = :cveNodo 		" +
	        "		AND :cveNodo = :cveNodoTemporizador	" +

	        "		AND STNP.CVE_ENTIDAD = INNP.CVE_ENTIDAD     " + 
	        "   	AND STNP.CVE_PROCESO = INNP.CVE_PROCESO     " + 
	        "  		AND STNP.VERSION = INNP.VERSION             " + 
	        "   	AND STNP.CVE_NODO = INNP.CVE_NODO           " + 
	        "   	AND STNP.ID_NODO = INNP.ID_NODO             " + 
	        
	        
	        " AND ( STNP.TIPO_ACCESO = 'ROL'  "+ 
	        " OR " +
	        " (STNP.TIPO_ACCESO = 'ROL_USUARIO' AND INNP.USUARIO_CREADOR = :cveUsuario ) " +
			"	)  ", nativeQuery = true)
	List<Integer> obtenerIdNodos(@Param("cveEntidad") String cveEntidad,
	                             @Param("cveProceso") String cveProceso,
	                             @Param("version") BigDecimal version,
	                             @Param("cveNodo") String cveNodo,
	                             @Param("cveUsuario") String cveUsuario,
	                             @Param("cveNodoTemporizador") String cveNodoTemporizador);
	
	@Query(value = " SELECT	COUNT (1) " + 
			"				FROM	IN_NODO_PROCESO		INNP, 	" + 
			"						ST_NODO_PROCESO		STNP	" + 
	        
			"				WHERE	INNP.CVE_ENTIDAD = :cveEntidad		AND " + 
			"					INNP.CVE_PROCESO = :cveProceso			AND " + 
			"					INNP.VERSION = :version					AND " + 
			"					INNP.CVE_NODO = :cveNodo				AND " + 
			"					INNP.ID_NODO = :idNodo					AND " + 
			"					INNP.ESTADO = :sitNodo			AND " + 
			"					STNP.CVE_ENTIDAD = INNP.CVE_ENTIDAD			AND " + 
			"					STNP.CVE_PROCESO = INNP.CVE_PROCESO			AND " + 
			"					STNP.VERSION = INNP.VERSION					AND " + 
			"					STNP.CVE_NODO = INNP.CVE_NODO				AND " + 
			"					STNP.ID_NODO = INNP.ID_NODO					AND " + 
			"					(  ( STNP.TIPO_ACCESO = 'ROL' AND " +
			"						:cveUsuario IN " + 
			"							( " + 
			"								SELECT	UR.CVE_USUARIO " +  
			"									FROM	USUARIO_ROL			UR, " + 
			"											ST_ROL_NODO			STRN " + 
			"									WHERE	STRN.CVE_ENTIDAD = INNP.CVE_ENTIDAD	AND " + 
			"										STRN.CVE_PROCESO = INNP.CVE_PROCESO		AND " + 
			"										STRN.VERSION = INNP.VERSION				AND " + 
			"										STRN.CVE_NODO = INNP.CVE_NODO		AND " + 
			"										STRN.ID_NODO = INNP.ID_NODO			AND " + 
			"										UR.CVE_ENTIDAD = STRN.CVE_ENTIDAD	AND " + 
			"										UR.CVE_PROCESO = STRN.CVE_PROCESO	AND " + 
			"										UR.VERSION = STRN.VERSION			AND " + 
			"										UR.CVE_ROL = STRN.CVE_ROL " + 
			"							) " +			
			"					 ) OR	( " + 
			"							STNP.TIPO_ACCESO = 'ROL_USUARIO'	AND " + 
			"							INNP.USUARIO_CREADOR = :cveUsuario  " +
			"					)  OR :cveUsuario IN " +
			" 							( SELECT CVE_USUARIO FROM IN_NODO_PROCESO_USUARIO " + 
			"										WHERE CVE_ENTIDAD = INNP.CVE_ENTIDAD	AND " + 
			"										CVE_PROCESO = INNP.CVE_PROCESO			AND " + 
			"										VERSION = INNP.VERSION					AND " + 
			"										CVE_INSTANCIA = INNP.CVE_INSTANCIA 		AND " + 
			"										CVE_NODO = INNP.CVE_NODO				AND " + 
			"										ID_NODO = INNP.ID_NODO					AND " + 
			"										SECUENCIA_NODO = INNP.SECUENCIA_NODO  " + 
			"							) " +
			" ) ", nativeQuery = true)
	Integer actividadesNodo (@Param("cveEntidad") String cveEntidad,
			@Param("cveProceso") String cveProceso,
            @Param("version") BigDecimal version,
            @Param("cveNodo") String cveNodo,
            @Param("idNodo") Integer idNodo,
            @Param("sitNodo") String sitNodo,
            @Param("cveUsuario") String cveUsuario);

	@Query(value = "  SELECT COUNT (1) " + 
			"				FROM	IN_NODO_PROCESO		INNP, " + 
			"						ST_NODO_PROCESO		STNP " + 
	        " 				LEFT JOIN IN_NODO_PROCESO_USUARIO INPU " + 
	        "					 ON INNP.CVE_ENTIDAD = INPU.CVE_ENTIDAD " + 
	        "					 AND INNP.CVE_PROCESO = INPU.CVE_PROCESO " + 
	        "					 AND INNP.VERSION = INPU.VERSION " + 
	        "					 AND INNP.CVE_NODO  = INPU.CVE_NODO  " + 
	        "					 AND INNP.ID_NODO = INPU.ID_NODO " + 
	        "					 AND INPU.CVE_USUARIO = :cveUsuario " +
	        "				WHERE	INNP.CVE_ENTIDAD = :cveEntidad		AND " + 
			"					INNP.CVE_PROCESO = :cveProceso			AND " + 
			"					INNP.VERSION = :version					AND " + 
			"					INNP.CVE_NODO = :cveNodo				AND " + 
			"					INNP.ESTADO = :sitNodo			AND " + 
			"					STNP.CVE_ENTIDAD = INNP.CVE_ENTIDAD			AND " + 
			"					STNP.CVE_PROCESO = INNP.CVE_PROCESO			AND " + 
			"					STNP.VERSION = INNP.VERSION					AND " + 
			"					STNP.CVE_NODO = INNP.CVE_NODO				AND " + 
			"					STNP.ID_NODO = INNP.ID_NODO					AND	" + 
			"					(	STNP.TIPO_ACCESO = 'ROL' 	OR " + 
			"						( " + 
			"							STNP.TIPO_ACCESO = 'ROL_USUARIO'	AND " + 
			"							INNP.USUARIO_CREADOR = :cveUsuario  " +
			"						)  " + 
			
			"					) ", nativeQuery = true)
	Integer actividadesTemporizador (@Param("cveEntidad") String cveEntidad,
			@Param("cveProceso") String cveProceso,
            @Param("version") BigDecimal version,
            @Param("cveNodo") String cveNodo,
            @Param("sitNodo") String sitNodo,
            @Param("cveUsuario") String cveUsuario);

	@Query(value = " SELECT	COUNT (1) " + 
			"			FROM	IN_NODO_PROCESO		INNP, " + 
			"					ST_NODO_PROCESO		STNP " + 
			"			WHERE	INNP.CVE_ENTIDAD = :cveEntidad		AND " + 
			"				INNP.CVE_PROCESO = :cveProceso			AND " + 
			"				INNP.VERSION = :version					AND " + 
			"				INNP.ESTADO = :sitNodo			AND " + 
			"				STNP.CVE_ENTIDAD = INNP.CVE_ENTIDAD			AND " + 
			"				STNP.CVE_PROCESO = INNP.CVE_PROCESO			AND " + 
			"				STNP.VERSION = INNP.VERSION					AND " + 
			"				STNP.CVE_NODO = INNP.CVE_NODO				AND " + 
			"				STNP.ID_NODO = INNP.ID_NODO					AND " +  
			"				( " + 
			"					( " + 
			"						STNP.TIPO_ACCESO = 'ROL_USUARIO'	AND " + 
			"						INNP.USUARIO_CREADOR = :cveUsuario " + 
			"					)	OR  ( " + 
			"						STNP.TIPO_ACCESO = 'ROL'			AND " + 
			"						:cveUsuario IN " + 
			"							( " + 
			"								SELECT	UR.CVE_USUARIO " +  
			"									FROM	USUARIO_ROL			UR, " + 
			"											ST_ROL_NODO			STRN " + 
			"									WHERE	STRN.CVE_ENTIDAD = INNP.CVE_ENTIDAD	AND " + 
			"										STRN.CVE_PROCESO = INNP.CVE_PROCESO		AND " + 
			"										STRN.VERSION = INNP.VERSION				AND " + 
			"										STRN.CVE_NODO = INNP.CVE_NODO		AND " + 
			"										STRN.ID_NODO = INNP.ID_NODO			AND " + 
			"										UR.CVE_ENTIDAD = STRN.CVE_ENTIDAD	AND " + 
			"										UR.CVE_PROCESO = STRN.CVE_PROCESO	AND " + 
			"										UR.VERSION = STRN.VERSION			AND " + 
			"										UR.CVE_ROL = STRN.CVE_ROL " + 
			"							) " + 
			"					) OR :cveUsuario IN " +
			" 							( SELECT CVE_USUARIO FROM IN_NODO_PROCESO_USUARIO " + 
			"										WHERE CVE_ENTIDAD = INNP.CVE_ENTIDAD	AND " + 
			"										CVE_PROCESO = INNP.CVE_PROCESO			AND " + 
			"										VERSION = INNP.VERSION					AND " + 
			"										CVE_INSTANCIA = INNP.CVE_INSTANCIA 		AND " + 
			"										CVE_NODO = INNP.CVE_NODO				AND " + 
			"										ID_NODO = INNP.ID_NODO					AND " + 
			"										SECUENCIA_NODO = INNP.SECUENCIA_NODO  " + 
			"									) " +
			"				)  ", nativeQuery = true)
	Integer actividadesSinNodo (@Param("cveEntidad") String cveEntidad,
			@Param("cveProceso") String cveProceso,
            @Param("version") BigDecimal version,
            @Param("sitNodo") String sitNodo,
            @Param("cveUsuario") String cveUsuario);
	
	
	@Query(value = "SELECT * " +
    		"FROM ST_NODO_PROCESO		STNP " +
            "WHERE STNP.CVE_ENTIDAD = :cveEntidad " +
            "	AND STNP.CVE_PROCESO = :cveProceso " +
            "	AND STNP.VERSION = :version ", nativeQuery = true)
	List <StNodoProceso> obtieneStNodosProcesos(
			 @Param("cveEntidad") String cveEntidad,
             @Param("cveProceso") String cveProceso,
             @Param("version") BigDecimal version);
	
	
	@Query(value = "SELECT " + 
	        "			 STNP.CVE_PROCESO, " + 
			"            STNP.VERSION, " + 
			"            STNP.DESCRIPCION, " + 
			"            STNP.CVE_AREA_TRABAJO_TARJETA, " + 
			"            TAT.TITULO_TARJETA, " + 
			"            TAT.SECUENCIA_TARJETA, " + 
			"            DAT.SECUENCIA_DATO, " + 
			"            DAT.ORIGEN_DATO, " + 
			"            DAT.ETIQUETA, " + 
			"            DATPN.CVE_DATO_PROCESO_NODO, " + 
			"       	 DATVP.CVE_VARIABLE, " +
            "       	 DAT.VISIBLE " +
			"FROM        ST_NODO_PROCESO             STNP, " + 
			"            TARJETA_AREA_TRABAJO        TAT, " + 
			"            DATO_AREA_TRABAJO           DAT " + 
			"                LEFT OUTER JOIN     DATO_AT_PROCESO_NODO        DATPN " + 
			"                    ON  DAT.CVE_ENTIDAD = DATPN.CVE_ENTIDAD     AND " + 
			"                        DAT.CVE_PROCESO = DATPN.CVE_PROCESO     AND " + 
			"                        DAT.VERSION = DATPN.VERSION             AND " + 
			"                        DAT.CVE_AREA_TRABAJO = DATPN.CVE_AREA_TRABAJO   AND " + 
			"                        DAT.SECUENCIA_TARJETA = DATPN.SECUENCIA_TARJETA AND " + 
			"                        DAT.SECUENCIA_DATO = DATPN.SECUENCIA_DATO  " + 
			"                LEFT OUTER JOIN     DATO_AT_VARIABLE_PROCESO    DATVP " + 
			"                    ON  DAT.CVE_ENTIDAD = DATVP.CVE_ENTIDAD     AND " + 
			"                        DAT.CVE_PROCESO = DATVP.CVE_PROCESO     AND " + 
			"                        DAT.VERSION = DATVP.VERSION             AND " + 
			"                        DAT.CVE_AREA_TRABAJO = DATVP.CVE_AREA_TRABAJO   AND " + 
			"                        DAT.SECUENCIA_TARJETA = DATVP.SECUENCIA_TARJETA AND " + 
			"                        DAT.SECUENCIA_DATO = DATVP.SECUENCIA_DATO " + 
			"WHERE       STNP.CVE_ENTIDAD = :cveEntidad           AND " + 
			"            STNP.CVE_PROCESO = :cveProceso     AND " + 
			"            STNP.VERSION = :version                      AND " + 
			"            STNP.CVE_NODO = :cveNodo     AND " + 
			"            STNP.ID_NODO = :idNodo                        AND " + 
			"            TAT.CVE_ENTIDAD = STNP.CVE_ENTIDAD      AND " + 
			"            TAT.CVE_PROCESO = STNP.CVE_PROCESO      AND " + 
			"            TAT.VERSION = STNP.VERSION              AND " + 
			"            TAT.CVE_AREA_TRABAJO = :cveAreaTrabajo               AND " + 
			"            DAT.CVE_ENTIDAD = STNP.CVE_ENTIDAD      AND " + 
			"            DAT.CVE_PROCESO = STNP.CVE_PROCESO      AND " + 
			"            DAT.VERSION = STNP.VERSION              AND " + 
			"            DAT.CVE_AREA_TRABAJO = STNP.CVE_AREA_TRABAJO_TARJETA    AND " + 
			"            DAT.SECUENCIA_TARJETA = TAT.SECUENCIA_TARJETA " +
			"ORDER BY  TAT.SECUENCIA_TARJETA, DAT.SECUENCIA_DATO", nativeQuery = true)
	List<Object[]> obtieneDatosTarjetaCompuesta(
	 @Param("cveEntidad") String cveEntidad,
	 @Param("cveProceso") String cveProceso,
	 @Param("version") BigDecimal version,
	 @Param("cveNodo") String cveNodo,
	 @Param("idNodo") Integer idNodo,
	 @Param("cveAreaTrabajo") String cveAreaTrabajo);


	@Query(value = "SELECT  STNP.CVE_ENTIDAD, STNP.CVE_PROCESO, STNP.VERSION,  " + 
			"         STP.DESCRIPCION AS DESPROCESO, STNP.CVE_NODO,  " + 
			"         STNP.ID_NODO,  STNP.ETIQUETA_BOTON, STNP.DESCRIPCION  " +
			"       FROM    ST_NODO_PROCESO STNP, " + 
			"               ST_PROCESO      STP, " + 
			"               ST_ROL_NODO     STRN,   " + 
			"               USUARIO_ROL     UR   " + 
			"       WHERE   " + 
			"                    STNP.CVE_ENTIDAD = :cveEntidad             AND " + 
			"                    STNP.CVE_PROCESO = :cveProceso             AND " + 
			"                    STNP.VERSION = :version                    AND " + 
			"                    (   STNP.CVE_NODO = 'ACTIVIDAD-USUARIO'    OR   " + 
			"                           STNP.CVE_NODO = 'ACTIVIDAD-USUARIO-TEMPORIZACION'   " + 
			"                    )                                          AND " + 
			"                    STNP.BOTON_INICIO_PROCESO = 'SI'           AND " + 

			"                    STP.CVE_ENTIDAD = STNP.CVE_ENTIDAD         AND " + 
			"                    STP.CVE_PROCESO = STNP.CVE_PROCESO         AND " + 
			"                    STP.VERSION = STNP.VERSION                 AND " + 

			"                    STRN.CVE_ENTIDAD = STNP.CVE_ENTIDAD        AND   " + 
			"                    STRN.CVE_PROCESO = STNP.CVE_PROCESO        AND   " + 
			"                    STRN.VERSION = STNP.VERSION                AND   " + 
			"                    STRN.CVE_NODO = STNP.CVE_NODO              AND   " + 
			"                    STRN.ID_NODO = STNP.ID_NODO                AND   " + 

			"                    UR.CVE_ENTIDAD = STNP.CVE_ENTIDAD          AND " + 
			"                    UR.CVE_PROCESO = STNP.CVE_PROCESO          AND " + 
			"                    UR.VERSION = STNP.VERSION                  AND " + 
			"                    UR.CVE_USUARIO = :cveUsuario               AND " + 
			"                    UR.CVE_ROL = STRN.CVE_ROL " + 
			" ", nativeQuery = true)
	List<Object> obtienerNuevaInstancia(@Param("cveEntidad") String cveEntidad,
			@Param("cveProceso") String cveProceso,
            @Param("version") BigDecimal version,
            @Param("cveUsuario") String cveUsuario);

}
