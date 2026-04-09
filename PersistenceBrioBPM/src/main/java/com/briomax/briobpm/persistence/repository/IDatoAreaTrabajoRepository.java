package com.briomax.briobpm.persistence.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.briomax.briobpm.persistence.entity.DatoAreaTrabajo;
import com.briomax.briobpm.persistence.entity.DatoAreaTrabajoPK;

/**
 * El objetivo de la interfaz IDatoAreaTrabajoRepository.java es proporcionar métodos 
 * para acceder y manipular datos relacionados con los procesos de nodo en la base de datos.
 *
 * @autor Alexis Zamora
 * @version 1.0
 * @fecha Mar 07, 2024 12:30:03 PM
 * @since JDK 1.8
 */
@Repository
public interface IDatoAreaTrabajoRepository extends JpaRepository<DatoAreaTrabajo, DatoAreaTrabajoPK> {

	@Query(value = "SELECT " +
			"DAT.ORDEN_DATO, " +
			"DATVS.CVE_VARIABLE, " +

			"INNP.CVE_INSTANCIA, " +
			"INNP.SECUENCIA_NODO, " +
			"INNP.ESTADO " +

		"FROM DATO_AREA_TRABAJO DAT, " +
			"DATO_AT_VARIABLE_SISTEMA DATVS, " +
			"IN_NODO_PROCESO INNP, " +
			"ST_NODO_PROCESO STNP " +

		"WHERE " +
			"DAT.CVE_ENTIDAD = :cveEntidad AND " +
			"DAT.CVE_PROCESO = :cveProceso AND " +
			"DAT.VERSION = :version AND " +
			"DAT.CVE_AREA_TRABAJO = :cveAreaTrabajo AND " +
			"DAT.ORIGEN_DATO = :origenDatoVS AND " +
			
			"DATVS.CVE_ENTIDAD = DAT.CVE_ENTIDAD AND " +
			"DATVS.CVE_PROCESO = DAT.CVE_PROCESO AND " +
			"DATVS.VERSION = DAT.VERSION AND " +
			"DATVS.CVE_AREA_TRABAJO = DAT.CVE_AREA_TRABAJO AND " +
			"DATVS.SECUENCIA_DATO = DAT.SECUENCIA_DATO AND " +
			

			"INNP.CVE_ENTIDAD = DAT.CVE_ENTIDAD AND " +
			"INNP.CVE_PROCESO = DAT.CVE_PROCESO AND " +
			"INNP.VERSION = DAT.VERSION AND " +
			"INNP.CVE_NODO = :cveNodo AND " +
			"INNP.ID_NODO = :idNodo AND " +

			"STNP.CVE_ENTIDAD = DAT.CVE_ENTIDAD  AND " +
			"STNP.CVE_PROCESO = DAT.CVE_PROCESO AND " +
			"STNP.VERSION = DAT.VERSION AND " +
			"STNP.CVE_NODO = INNP.CVE_NODO AND " +
			"STNP.ID_NODO = INNP.ID_NODO AND " +

	        " ( (STNP.TIPO_ACCESO = 'ROL' AND " +
			"		:cveUsuario IN 	( " + 
			"			SELECT	UR.CVE_USUARIO " +  
			"				FROM	USUARIO_ROL			UR, " + 
			"						ST_ROL_NODO			STRN " + 
			"				WHERE	STRN.CVE_ENTIDAD = INNP.CVE_ENTIDAD	AND " + 
			"					STRN.CVE_PROCESO = INNP.CVE_PROCESO		AND " + 
			"					STRN.VERSION = INNP.VERSION				AND " + 
			"					STRN.CVE_NODO = INNP.CVE_NODO		AND " + 
			"					STRN.ID_NODO = INNP.ID_NODO			AND " + 
			"					UR.CVE_ENTIDAD = STRN.CVE_ENTIDAD	AND " + 
			"					UR.CVE_PROCESO = STRN.CVE_PROCESO	AND " + 
			"					UR.VERSION = STRN.VERSION			AND " + 
			"					UR.CVE_ROL = STRN.CVE_ROL " + 
			"		)  ) " + 
	        " OR ( STNP.TIPO_ACCESO = 'ROL_USUARIO' AND INNP.USUARIO_CREADOR = :cveUsuario ) " +
			" OR :cveUsuario IN " +
			" 		( SELECT CVE_USUARIO FROM IN_NODO_PROCESO_USUARIO " + 
			"					WHERE CVE_ENTIDAD = INNP.CVE_ENTIDAD	AND " + 
			"					CVE_PROCESO = INNP.CVE_PROCESO			AND " + 
			"					VERSION = INNP.VERSION 					AND " + 
			"					CVE_INSTANCIA = INNP.CVE_INSTANCIA 		AND " + 
			"					CVE_NODO = INNP.CVE_NODO				AND " + 
			"					ID_NODO = INNP.ID_NODO					AND " + 
			"					SECUENCIA_NODO = INNP.SECUENCIA_NODO  " + 
			"		) ) " +			
		"ORDER BY DAT.ORDEN_DATO", nativeQuery = true)
	List<Object> obtenerDatosATSistema(@Param("cveEntidad") String cveEntidad,
                                       @Param("cveProceso") String cveProceso,
                                       @Param("version") BigDecimal version,
                                       @Param("cveAreaTrabajo") String cveAreaTrabajo,
                                       @Param("origenDatoVS") String origenDatoVS,
                                       @Param("cveNodo") String cveNodo,
                                       @Param("idNodo") Integer idNodo,
                                       @Param("cveUsuario") String cveUsuario);

	@Query(value = 
	        "SELECT " +
	            "DAT.ORDEN_DATO, " + 
	            "DATVP.CVE_VARIABLE, " +
	            "DATVP.AGREGACION, " + 
	            "INNP.CVE_INSTANCIA, " + 
	            "INNP.SECUENCIA_NODO, " +
	            "INNP.ESTADO, " +
	            "'GRID' AS TIPO_TARJETA " +
	        "FROM " +
	            "DATO_AREA_TRABAJO DAT, " + 
	            "DATO_AT_VARIABLE_PROCESO DATVP, " +
	            "IN_NODO_PROCESO INNP, " +
	            "ST_NODO_PROCESO STNP  " +
	        "WHERE " +
	            "DAT.CVE_ENTIDAD = :cveEntidad AND " +
	            "DAT.CVE_PROCESO = :cveProceso AND " +
	            "DAT.VERSION = :version AND " +
	            "DAT.CVE_AREA_TRABAJO = :cveAreaTrabajo AND " +
	            "DAT.ORIGEN_DATO = :origenDatoVP AND " +
	            
	            "DAT.CVE_ENTIDAD = DATVP.CVE_ENTIDAD AND " +
	            "DAT.CVE_PROCESO = DATVP.CVE_PROCESO AND " +
	            "DAT.VERSION = DATVP.VERSION AND " +
	            "DAT.CVE_AREA_TRABAJO = DATVP.CVE_AREA_TRABAJO AND " +
	            "DAT.SECUENCIA_DATO = DATVP.SECUENCIA_DATO AND " +
	            
	            "STNP.CVE_ENTIDAD = DAT.CVE_ENTIDAD  AND " +
	            "STNP.CVE_PROCESO = DAT.CVE_PROCESO AND " +
	            "STNP.VERSION = INNP.VERSION AND " +
	            "STNP.CVE_NODO = INNP.CVE_NODO AND " +
	            "STNP.ID_NODO = INNP.ID_NODO AND " +
	            
	            "INNP.CVE_ENTIDAD = DAT.CVE_ENTIDAD  AND " +
	            "INNP.CVE_PROCESO = DAT.CVE_PROCESO  AND " +
	            "INNP.VERSION = DAT.VERSION AND " +
	            "INNP.CVE_NODO = :cveNodo AND " +
	            "INNP.ID_NODO = :idNodo AND " +

		        " ( (STNP.TIPO_ACCESO = 'ROL' AND " +
				"		:cveUsuario IN 	( " + 
				"			SELECT	UR.CVE_USUARIO " +  
				"				FROM	USUARIO_ROL			UR, " + 
				"						ST_ROL_NODO			STRN " + 
				"				WHERE	STRN.CVE_ENTIDAD = INNP.CVE_ENTIDAD	AND " + 
				"					STRN.CVE_PROCESO = INNP.CVE_PROCESO		AND " + 
				"					STRN.VERSION = INNP.VERSION				AND " + 
				"					STRN.CVE_NODO = INNP.CVE_NODO		AND " + 
				"					STRN.ID_NODO = INNP.ID_NODO			AND " + 
				"					UR.CVE_ENTIDAD = STRN.CVE_ENTIDAD	AND " + 
				"					UR.CVE_PROCESO = STRN.CVE_PROCESO	AND " + 
				"					UR.VERSION = STRN.VERSION			AND " + 
				"					UR.CVE_ROL = STRN.CVE_ROL " + 
				"		)  ) " + 
		        " OR ( STNP.TIPO_ACCESO = 'ROL_USUARIO' AND INNP.USUARIO_CREADOR = :cveUsuario ) " +
				" OR :cveUsuario IN " +
				" 		( SELECT CVE_USUARIO FROM IN_NODO_PROCESO_USUARIO " + 
				"					WHERE CVE_ENTIDAD = INNP.CVE_ENTIDAD	AND " + 
				"					CVE_PROCESO = INNP.CVE_PROCESO			AND " + 
				"					VERSION = INNP.VERSION 				    AND " + 
				"					CVE_INSTANCIA = INNP.CVE_INSTANCIA 		AND " + 
				"					CVE_NODO = INNP.CVE_NODO				AND " + 
				"					ID_NODO = INNP.ID_NODO					AND " + 
				"					SECUENCIA_NODO = INNP.SECUENCIA_NODO  " + 
				"		) ) " +
	        "UNION ALL " +
	        "SELECT " +
	            "DAT.ORDEN_DATO, " +
	            "DATVP.CVE_VARIABLE, " +
	            "DATVP.AGREGACION, " +
	            "INNP.CVE_INSTANCIA, " +
	            "INNP.SECUENCIA_NODO, " +
	            "INNP.ESTADO, " +
	            "'TARJETA' AS TIPO_TARJETA " +
	        "FROM " +
	            "ST_NODO_PROCESO STNP, " +
	            "IN_NODO_PROCESO INNP, " +
	            "TARJETA_AREA_TRABAJO TAT, " +
	            "DATO_AREA_TRABAJO DAT " +
	            "LEFT OUTER JOIN DATO_AT_VARIABLE_PROCESO DATVP " +
	            "ON DAT.CVE_ENTIDAD = DATVP.CVE_ENTIDAD AND " +
	            "    DAT.CVE_PROCESO = DATVP.CVE_PROCESO AND " +
	            "    DAT.VERSION = DATVP.VERSION AND " +
	            "    DAT.CVE_AREA_TRABAJO = DATVP.CVE_AREA_TRABAJO AND " +
	            "    DAT.SECUENCIA_TARJETA = DATVP.SECUENCIA_TARJETA AND " +
	            "    DAT.SECUENCIA_DATO = DATVP.SECUENCIA_DATO " +
	        "WHERE " +
	            "STNP.CVE_ENTIDAD = :cveEntidad AND " +
	            "STNP.CVE_PROCESO = :cveProceso AND " +
	            "STNP.VERSION = :version AND " +
	            "STNP.CVE_NODO = :cveNodo AND " +
	            "STNP.ID_NODO = :idNodo AND " +
	            
	            "TAT.CVE_ENTIDAD = STNP.CVE_ENTIDAD AND " +
	            "TAT.CVE_PROCESO = STNP.CVE_PROCESO AND " +
	            "TAT.VERSION = STNP.VERSION AND " +
	            "TAT.CVE_AREA_TRABAJO = :cveAreaTrabajoTarjeta AND " +
	            
	            "DAT.CVE_ENTIDAD = STNP.CVE_ENTIDAD AND " +
	            "DAT.CVE_PROCESO = STNP.CVE_PROCESO AND " +
	            "DAT.VERSION = STNP.VERSION AND " +
	            "DAT.CVE_AREA_TRABAJO = STNP.CVE_AREA_TRABAJO_TARJETA AND " +
	            "DAT.SECUENCIA_TARJETA = TAT.SECUENCIA_TARJETA AND " +
	            "DAT.ORIGEN_DATO = :origenDatoVP  AND " +
	            
	            "INNP.CVE_ENTIDAD = STNP.CVE_ENTIDAD AND " +
	            "INNP.CVE_PROCESO = STNP.CVE_PROCESO AND " +
	            "INNP.VERSION = STNP.VERSION AND " +
	            "INNP.CVE_NODO = STNP.CVE_NODO AND " +
	            "INNP.ID_NODO = STNP.ID_NODO AND " +

		        " ( (STNP.TIPO_ACCESO = 'ROL' AND " +
				"		:cveUsuario IN 	( " + 
				"			SELECT	UR.CVE_USUARIO " +  
				"				FROM	USUARIO_ROL			UR, " + 
				"						ST_ROL_NODO			STRN " + 
				"				WHERE	STRN.CVE_ENTIDAD = INNP.CVE_ENTIDAD	AND " + 
				"					STRN.CVE_PROCESO = INNP.CVE_PROCESO		AND " + 
				"					STRN.VERSION = INNP.VERSION				AND " + 
				"					STRN.CVE_NODO = INNP.CVE_NODO		AND " + 
				"					STRN.ID_NODO = INNP.ID_NODO			AND " + 
				"					UR.CVE_ENTIDAD = STRN.CVE_ENTIDAD	AND " + 
				"					UR.CVE_PROCESO = STRN.CVE_PROCESO	AND " + 
				"					UR.VERSION = STRN.VERSION			AND " + 
				"					UR.CVE_ROL = STRN.CVE_ROL " + 
				"		)  ) " + 
		        " OR ( STNP.TIPO_ACCESO = 'ROL_USUARIO' AND INNP.USUARIO_CREADOR = :cveUsuario ) " +
				" OR :cveUsuario IN " +
				" 		( SELECT CVE_USUARIO FROM IN_NODO_PROCESO_USUARIO " + 
				"					WHERE CVE_ENTIDAD = INNP.CVE_ENTIDAD	AND " + 
				"					CVE_PROCESO = INNP.CVE_PROCESO			AND " + 
				"					VERSION = INNP.VERSION  				AND " + 
				"					CVE_INSTANCIA = INNP.CVE_INSTANCIA 		AND " + 
				"					CVE_NODO = INNP.CVE_NODO				AND " + 
				"					ID_NODO = INNP.ID_NODO					AND " + 
				"					SECUENCIA_NODO = INNP.SECUENCIA_NODO  " + 
				"		) ) ",  nativeQuery = true)
	List<Object[]> obtenerDatosATProceso(@Param("cveEntidad") String cveEntidad,
	                                   @Param("cveProceso") String cveProceso,
	                                   @Param("version") BigDecimal version,
	                                   @Param("cveAreaTrabajo") String cveAreaTrabajo,
	                                   @Param("origenDatoVP") String origenDatoVP,
	                                   @Param("cveNodo") String cveNodo,
	                                   @Param("idNodo") Integer idNodo,
	                                   @Param("cveAreaTrabajoTarjeta") String cveAreaTrabajoTarjeta,
	                                   @Param("cveUsuario") String cveUsuario);


	@Query(value = "SELECT " +
	        "DAT.ORDEN_DATO, " +
	        "DATVE.CVE_VARIABLE, " +

	        "INNP.CVE_INSTANCIA, " +
	        "INNP.SECUENCIA_NODO, " +
	        "INNP.ESTADO, " +
	        " ROW_NUMBER() OVER (ORDER BY DAT.ORDEN_DATO, DATVE.CVE_VARIABLE) AS RNUM " +
	        "FROM DATO_AREA_TRABAJO DAT, " +
	        "DATO_AT_VARIABLE_ENTIDAD DATVE, " +
	        "IN_NODO_PROCESO INNP, " +
	        "ST_NODO_PROCESO STNP " +

	        "WHERE " +
	        "DAT.CVE_ENTIDAD = :cveEntidad AND " +
	        "DAT.CVE_PROCESO = :cveProceso AND " +
	        "DAT.VERSION = :version AND " +
	        "DAT.CVE_AREA_TRABAJO = :cveAreaTrabajo AND " +
	        "DAT.ORIGEN_DATO = :origenDatoVE AND " +
	        
	        "DATVE.CVE_ENTIDAD = DAT.CVE_ENTIDAD AND " +
	        "DATVE.CVE_PROCESO = DAT.CVE_PROCESO AND " +
	        "DATVE.VERSION = DAT.VERSION AND " +
	        "DATVE.CVE_AREA_TRABAJO = DAT.CVE_AREA_TRABAJO AND " +
	        "DATVE.SECUENCIA_DATO = DAT.SECUENCIA_DATO AND " +
	        

	        "INNP.CVE_ENTIDAD = DAT.CVE_ENTIDAD AND " +
	        "INNP.CVE_PROCESO = DAT.CVE_PROCESO AND " +
	        "INNP.VERSION = DAT.VERSION AND " +
	        "INNP.CVE_NODO = :cveNodo AND " +
	        "INNP.ID_NODO = :idNodo AND " +

		        " ( (STNP.TIPO_ACCESO = 'ROL' AND " +
				"		:cveUsuario IN 	( " + 
				"			SELECT	UR.CVE_USUARIO " +  
				"				FROM	USUARIO_ROL			UR, " + 
				"						ST_ROL_NODO			STRN " + 
				"				WHERE	STRN.CVE_ENTIDAD = INNP.CVE_ENTIDAD	AND " + 
				"					STRN.CVE_PROCESO = INNP.CVE_PROCESO		AND " + 
				"					STRN.VERSION = INNP.VERSION				AND " + 
				"					STRN.CVE_NODO = INNP.CVE_NODO		AND " + 
				"					STRN.ID_NODO = INNP.ID_NODO			AND " + 
				"					UR.CVE_ENTIDAD = STRN.CVE_ENTIDAD	AND " + 
				"					UR.CVE_PROCESO = STRN.CVE_PROCESO	AND " + 
				"					UR.VERSION = STRN.VERSION			AND " + 
				"					UR.CVE_ROL = STRN.CVE_ROL " + 
				"		)  ) " + 
		        " OR ( STNP.TIPO_ACCESO = 'ROL_USUARIO' AND INNP.USUARIO_CREADOR = :cveUsuario ) " +
				" OR :cveUsuario IN " +
				" 		( SELECT CVE_USUARIO FROM IN_NODO_PROCESO_USUARIO " + 
				"					WHERE CVE_ENTIDAD = INNP.CVE_ENTIDAD	AND " + 
				"					CVE_PROCESO = INNP.CVE_PROCESO			AND " + 
				"					VERSION = INNP.VERSION 					AND " + 
				"					CVE_INSTANCIA = INNP.CVE_INSTANCIA 		AND " + 
				"					CVE_NODO = INNP.CVE_NODO				AND " + 
				"					ID_NODO = INNP.ID_NODO					AND " + 
				"					SECUENCIA_NODO = INNP.SECUENCIA_NODO  " + 
				"		) ) " +

	        "ORDER BY DAT.ORDEN_DATO", nativeQuery = true)
	List<Object> obtenerDatosASEntidad(@Param("cveEntidad") String cveEntidad,
                                        @Param("cveProceso") String cveProceso,
                                        @Param("version") BigDecimal version,
                                        @Param("cveAreaTrabajo") String cveAreaTrabajo,
                                        @Param("origenDatoVE") String origenDatoVE,
                                        @Param("cveNodo") String cveNodo,
                                        @Param("idNodo") Integer idNodo,
                                        @Param("cveUsuario") String cveUsuario);

	@Query(value = "SELECT " +
			"DAT.SECUENCIA_DATO, " +
			"DAT.ORDEN_DATO, " +
			"DATVL.CVE_VARIABLE, " +

			"INNP.CVE_INSTANCIA, " +
			"INNP.SECUENCIA_NODO, " +
			"INNP.ESTADO " +
			" ,ROW_NUMBER() OVER ( " +
			"		ORDER BY DAT.SECUENCIA_DATO, " +
			"			DAT.ORDEN_DATO " +
			"		) AS RNUM " +
		" FROM DATO_AREA_TRABAJO DAT, " +
			"DATO_AT_VARIABLE_LOCALIDAD DATVL, " +
			"IN_NODO_PROCESO INNP, " +
			"ST_NODO_PROCESO STNP " +

		" WHERE " +
			"DAT.CVE_ENTIDAD = :cveEntidad AND " +
			"DAT.CVE_PROCESO = :cveProceso AND " +
			"DAT.VERSION = :version AND " +
			"DAT.CVE_AREA_TRABAJO = :cveAreaTrabajo AND " +
			"DAT.ORIGEN_DATO = :origenDatoVL AND " +
			
			"DATVL.CVE_ENTIDAD = DAT.CVE_ENTIDAD AND " +
			"DATVL.CVE_PROCESO = DAT.CVE_PROCESO AND " +
			"DATVL.VERSION = DAT.VERSION AND " +
			"DATVL.CVE_AREA_TRABAJO = DAT.CVE_AREA_TRABAJO AND " +
			"DATVL.SECUENCIA_DATO = DAT.SECUENCIA_DATO AND " +
			
			"INNP.CVE_ENTIDAD = DAT.CVE_ENTIDAD AND " +
			"INNP.CVE_PROCESO =  DAT.CVE_PROCESO AND " +
			"INNP.VERSION = DAT.VERSION AND " +
			"INNP.CVE_NODO = :cveNodo AND " +
			"INNP.ID_NODO = :idNodo AND " +
			
			"STNP.CVE_ENTIDAD = DAT.CVE_ENTIDAD  AND " +
			"STNP.CVE_PROCESO = DAT.CVE_PROCESO AND  " +
			"STNP.VERSION = DAT.VERSION AND " +
			"STNP.CVE_NODO = INNP.CVE_NODO AND " +

		        " ( (STNP.TIPO_ACCESO = 'ROL' AND " +
				"		:cveUsuario IN 	( " + 
				"			SELECT	UR.CVE_USUARIO " +  
				"				FROM	USUARIO_ROL			UR, " + 
				"						ST_ROL_NODO			STRN " + 
				"				WHERE	STRN.CVE_ENTIDAD = INNP.CVE_ENTIDAD	AND " + 
				"					STRN.CVE_PROCESO = INNP.CVE_PROCESO		AND " + 
				"					STRN.VERSION = INNP.VERSION				AND " + 
				"					STRN.CVE_NODO = INNP.CVE_NODO		AND " + 
				"					STRN.ID_NODO = INNP.ID_NODO			AND " + 
				"					UR.CVE_ENTIDAD = STRN.CVE_ENTIDAD	AND " + 
				"					UR.CVE_PROCESO = STRN.CVE_PROCESO	AND " + 
				"					UR.VERSION = STRN.VERSION			AND " + 
				"					UR.CVE_ROL = STRN.CVE_ROL " + 
				"		)  ) " + 
		        " OR ( STNP.TIPO_ACCESO = 'ROL_USUARIO' AND INNP.USUARIO_CREADOR = :cveUsuario ) " +
				" OR :cveUsuario IN " +
				" 		( SELECT CVE_USUARIO FROM IN_NODO_PROCESO_USUARIO " + 
				"					WHERE CVE_ENTIDAD = INNP.CVE_ENTIDAD	AND " + 
				"					CVE_PROCESO = INNP.CVE_PROCESO			AND " + 
				"					VERSION = INNP.VERSION 					AND " + 
				"					CVE_INSTANCIA = INNP.CVE_INSTANCIA 		AND " + 
				"					CVE_NODO = INNP.CVE_NODO				AND " + 
				"					ID_NODO = INNP.ID_NODO					AND " + 
				"					SECUENCIA_NODO = INNP.SECUENCIA_NODO  " + 
				"		) ) " +

		"ORDER BY DAT.ORDEN_DATO", nativeQuery = true)
	List<Object> obtenerDatosASLocalidad(@Param("cveEntidad") String cveEntidad,
                                       @Param("cveProceso") String cveProceso,
                                       @Param("version") BigDecimal version,
                                       @Param("cveAreaTrabajo") String cveAreaTrabajo,
                                       @Param("origenDatoVL") String origenDatoVL,
                                       @Param("cveNodo") String cveNodo,
                                       @Param("idNodo") Integer idNodo,
                                       @Param("cveUsuario") String cveUsuario);


	@Query(value = "SELECT " +
            "DAT.SECUENCIA_DATO, " +
            "DAT.ORDEN_DATO, " +
            "DATPN.CVE_DATO_PROCESO_NODO, " +
            "'GRID' AS TIPO_TARJETA " +
			" ,ROW_NUMBER() OVER ( " +
			"		ORDER BY DAT.SECUENCIA_DATO, " +
			"			DAT.ORDEN_DATO " +
			"		) AS RNUM " +
        "FROM " +
            "DATO_AREA_TRABAJO DAT " +
            "LEFT OUTER JOIN DATO_AT_PROCESO_NODO DATPN " +
            "ON DAT.CVE_ENTIDAD = DATPN.CVE_ENTIDAD AND " +
            "    DAT.CVE_PROCESO = DATPN.CVE_PROCESO AND " +
            "    DAT.VERSION = DATPN.VERSION AND " +
            "    DAT.CVE_AREA_TRABAJO = DATPN.CVE_AREA_TRABAJO AND " +
            "    DAT.SECUENCIA_DATO = DATPN.SECUENCIA_DATO " +
        "WHERE " +
            "DAT.CVE_ENTIDAD = :cveEntidad AND " +
            "DAT.CVE_PROCESO = :cveProceso AND " +
            "DAT.VERSION = :version AND " +
            "DAT.CVE_AREA_TRABAJO = :cveAreaTrabajo AND " +
            "DAT.ORIGEN_DATO = :origenDatoDPN " +
        "UNION ALL " +
        "SELECT " +
            "DAT.SECUENCIA_DATO, " +
            "DAT.ORDEN_DATO, " +
            "DATPN.CVE_DATO_PROCESO_NODO, " +
            "'TARJETA' AS TIPO_TARJETA " +
            " ,ROW_NUMBER() OVER ( " +
			"		ORDER BY DAT.SECUENCIA_DATO, " +
			"			DAT.ORDEN_DATO " +
			"		) AS RNUM " +
        "FROM " +
            "ST_NODO_PROCESO STNP, " +
            "TARJETA_AREA_TRABAJO TAT, " +
            "DATO_AREA_TRABAJO DAT " +
            "LEFT OUTER JOIN DATO_AT_PROCESO_NODO DATPN " +
            "ON DAT.CVE_ENTIDAD = DATPN.CVE_ENTIDAD AND " +
            "    DAT.CVE_PROCESO = DATPN.CVE_PROCESO AND " +
            "    DAT.VERSION = DATPN.VERSION AND " +
            "    DAT.CVE_AREA_TRABAJO = DATPN.CVE_AREA_TRABAJO AND " +
            "    DAT.SECUENCIA_TARJETA = DATPN.SECUENCIA_TARJETA AND " +
            "    DAT.SECUENCIA_DATO = DATPN.SECUENCIA_DATO " +
        "WHERE " +
            "STNP.CVE_ENTIDAD = :cveEntidad AND " +
            "STNP.CVE_PROCESO = :cveProceso AND " +
            "STNP.VERSION = :version AND " +
            "STNP.CVE_NODO = :cveNodo AND " +
            "STNP.ID_NODO = :idNodo AND " +
            "STNP.CVE_AREA_TRABAJO = :cveAreaTrabajo AND " +
            "TAT.CVE_ENTIDAD = STNP.CVE_ENTIDAD AND " +
            "TAT.CVE_PROCESO = STNP.CVE_PROCESO AND " +
            "TAT.VERSION = STNP.VERSION AND " +
            "DAT.ORIGEN_DATO = :origenDatoDPN  AND " +
            "DAT.CVE_ENTIDAD = STNP.CVE_ENTIDAD AND " +
            "DAT.CVE_PROCESO = STNP.CVE_PROCESO AND " +
            "DAT.VERSION = STNP.VERSION AND " +
            "DAT.CVE_AREA_TRABAJO = STNP.CVE_AREA_TRABAJO_TARJETA AND " +
            "DAT.CVE_AREA_TRABAJO = TAT.CVE_AREA_TRABAJO AND " +
            "DAT.SECUENCIA_TARJETA = TAT.SECUENCIA_TARJETA",
        nativeQuery = true)
	List<Object> obtenerDatosProcesoNodo(@Param("cveEntidad") String cveEntidad,
	                                     @Param("cveProceso") String cveProceso,
	                                     @Param("version") BigDecimal version,
	                                     @Param("cveAreaTrabajo") String cveAreaTrabajo,
	                                     @Param("origenDatoDPN") String origenDatoDPN,
	                                     @Param("cveNodo") String cveNodo,
	                                     @Param("idNodo") Integer idNodo);

	
	@Query(value = "SELECT	DAT.SECUENCIA_DATO,	" + 
				"			DAT.ORDEN_DATO,		" + 
				"			DATPN.CVE_DATO_PROCESO_NODO	" + 				
				" ,ROW_NUMBER() OVER ( " +
				"		ORDER BY DAT.SECUENCIA_DATO, " +
				"			DAT.ORDEN_DATO " +
				"		) AS RNUM " +
				"		FROM	DATO_AREA_TRABAJO DAT	" + 
				"			LEFT OUTER JOIN				" + 
				"			DATO_AT_PROCESO_NODO DATPN ON	" + 
				"			DAT.CVE_AREA_TRABAJO = DATPN.CVE_AREA_TRABAJO AND	" + 
				"			DAT.SECUENCIA_DATO = DATPN.SECUENCIA_DATO			" + 
				"		WHERE	" + 
				"			DAT.CVE_AREA_TRABAJO =:cveAreaTrabajo	AND		" + 
				"			DAT.ORIGEN_DATO =:origenDatoDPN				" + 
				"		ORDER BY ORDEN_DATO", nativeQuery = true)
	List<Object> cursorDatosAtdpn(@Param("cveAreaTrabajo") String cveAreaTrabajo,
                                   @Param("origenDatoDPN") String origenDatoDPN);


	@Query(value = "SELECT * " +
    		"FROM DATO_AREA_TRABAJO		DAT " +
            "WHERE DAT.CVE_ENTIDAD = :cveEntidad " +
            "	AND DAT.CVE_PROCESO = :cveProceso " +
            "	AND DAT.VERSION = :version " +
            "	AND DAT.CVE_AREA_TRABAJO = :cveAreaTarjeta ", nativeQuery = true)
	List<DatoAreaTrabajo> obtieneDatosAT(
										@Param("cveEntidad") String cveEntidad,
								        @Param("cveProceso") String cveProceso,
								        @Param("version") BigDecimal version,
								        @Param("cveAreaTarjeta") String cveAreaTarjeta);

}
