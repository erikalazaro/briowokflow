/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.persistence.repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.briomax.briobpm.persistence.entity.InVariableProceso;
import com.briomax.briobpm.persistence.entity.InVariableProcesoPK;

/**
 * El objetivo de la Interface InVariableProcesoRepository.java es ...
 * @author Sara Ventura 
 * @version 1.0 Fecha de creacion Nov 10, 2023 Modificaciones:
 * @since JDK 1.8
 */
@Repository
public interface IInVariableProcesoRepository extends JpaRepository<InVariableProceso, InVariableProcesoPK> {

  
    @Query(value = "SELECT 1 FROM IN_VARIABLE_PROCESO INVP " +
            "WHERE INVP.CVE_ENTIDAD = :cveEntidadDestino " +
            "AND INVP.CVE_PROCESO = :cveProcesoDestino " +
            "AND INVP.VERSION = :versionDestino " +
            "AND INVP.CVE_INSTANCIA = :cveInstancia " +
            "AND INVP.CVE_VARIABLE = :cveVariableDestino", nativeQuery = true)
     Integer verificaVariable(
             @Param("cveEntidadDestino") String cveEntidad,
             @Param("cveProcesoDestino") String cveProceso,
             @Param("versionDestino") BigDecimal version,
             @Param("cveInstancia") String cveInstancia,
             @Param("cveVariableDestino") String cveVariable);
   

	@Query(value = "SELECT	* "
			+ "	FROM	IN_VARIABLE_PROCESO	INVP"
			+ "	WHERE   INVP.CVE_ENTIDAD = :cveEntidad AND  "
			+ "			INVP.CVE_PROCESO = :cveProceso AND  "
			+ "			INVP.VERSION = :version AND "
			+ "			INVP.CVE_INSTANCIA = :cveInstancia		AND"
			+ "			INVP.CVE_VARIABLE = :cveVariable		AND"
			+ "			INVP.OCURRENCIA = :ocurrencia ", nativeQuery = true)
	 List<InVariableProceso> obtenerVariableProceso( @Param("cveEntidad") String cveEntidad,
										@Param("cveProceso") String cveProceso,
										@Param("version") BigDecimal version,
										@Param("cveInstancia") String cveInstancia,
										@Param("cveVariable") String cveVariable,
										@Param("ocurrencia") Integer ocurrencia);
    
    
    // max valor Decimal
    @Query(value = "SELECT SUM(INVP.VALOR_DECIMAL) FROM IN_VARIABLE_PROCESO INVP " +
            "WHERE INVP.CVE_ENTIDAD = :cveEntidad " +
            "		AND INVP.CVE_PROCESO = :cveProceso " +
            "		AND INVP.VERSION = :version " +
            "		AND INVP.CVE_INSTANCIA = :cveInstancia " +
            "		AND INVP.CVE_VARIABLE = :cveVariable " +
            "		AND INVP.OCURRENCIA = :ocurrencia", nativeQuery = true)
    BigDecimal encuentraMaxDecimal(
            @Param("cveEntidad") String cveEntidad,
            @Param("cveProceso") String cveProceso,
            @Param("version") BigDecimal version,
            @Param("cveInstancia") String cveInstancia,
            @Param("cveVariable") String cveVariable,
            @Param("ocurrencia") Integer ocurrencia);
    
    //max valor entero
    @Query(value = "SELECT SUM(INVP.VALOR_ENTERO) FROM IN_VARIABLE_PROCESO INVP " +
            "WHERE INVP.CVE_ENTIDAD = :cveEntidad " +
            "AND INVP.CVE_PROCESO = :cveProceso " +
            "AND INVP.VERSION = :version " +
            "AND INVP.CVE_INSTANCIA = :cveInstancia " +
            "AND INVP.CVE_VARIABLE = :cveVariable " +
            "AND INVP.OCURRENCIA = :ocurrencia", nativeQuery = true)
	Integer encuentraMaxEntero(
	            @Param("cveEntidad") String cveEntidad,
	            @Param("cveProceso") String cveProceso,
	            @Param("version") BigDecimal version,
	            @Param("cveInstancia") String cveInstancia,
	            @Param("cveVariable") String cveVariable,
	            @Param("ocurrencia") Integer ocurrencia);
    
    //max valor fecha
    @Query(value = "SELECT MAX(INVP.VALOR_FECHA) FROM IN_VARIABLE_PROCESO INVP " +
            "WHERE INVP.CVE_ENTIDAD = :cveEntidad " +
            "AND INVP.CVE_PROCESO = :cveProceso " +
            "AND INVP.VERSION = :version " +
            "AND INVP.CVE_INSTANCIA = :cveInstancia " +
            "AND INVP.OCURRENCIA = :ocurrencia " +
            "AND INVP.CVE_VARIABLE = :cveVariable "  , nativeQuery = true)
	Date encuentraMaxFecha(
			@Param("cveEntidad") String cveEntidad,
			@Param("cveProceso") String cveProceso,
			@Param("version") BigDecimal version,
			@Param("cveInstancia") String cveInstancia,
			@Param("cveVariable") String cveVariable,
			@Param("ocurrencia") Integer ocurrencia);
    
    // suma valor Decimal
    @Query(value = "SELECT SUM(INVP.VALOR_DECIMAL) FROM IN_VARIABLE_PROCESO INVP " +
            "WHERE INVP.CVE_ENTIDAD = :cveEntidad " +
            "AND INVP.CVE_PROCESO = :cveProceso " +
            "AND INVP.VERSION = :version " +
            "AND INVP.CVE_INSTANCIA = :cveInstancia " +
            "AND INVP.CVE_VARIABLE = :cveVariable " +
            "AND INVP.OCURRENCIA = :ocurrencia", nativeQuery = true)
    BigDecimal encuentraSumaDecimal(
            @Param("cveEntidad") String cveEntidad,
            @Param("cveProceso") String cveProceso,
            @Param("version") BigDecimal version,
            @Param("cveInstancia") String cveInstancia,
            @Param("cveVariable") String cveVariable,
            @Param("ocurrencia") Integer ocurrencia);
    
    //suma valor entero
    @Query(value = "SELECT SUM(INVP.VALOR_ENTERO) FROM IN_VARIABLE_PROCESO INVP " +
            "WHERE INVP.CVE_ENTIDAD = :cveEntidad " +
            "AND INVP.CVE_PROCESO = :cveProceso " +
            "AND INVP.VERSION = :version " +
            "AND INVP.CVE_INSTANCIA = :cveInstancia " +
            "AND INVP.OCURRENCIA = :ocurrencia " +
            "AND INVP.CVE_VARIABLE = :cveVariable ", nativeQuery = true)
	Integer encuentraSumaEntero(
	            @Param("cveEntidad") String cveEntidad,
	            @Param("cveProceso") String cveProceso,
	            @Param("version") BigDecimal version,
	            @Param("cveInstancia") String cveInstancia,
	            @Param("cveVariable") String cveVariable,
	            @Param("ocurrencia") Integer ocurrencia);

    
    //min valor fecha
    @Query(value = "SELECT MIN(VALOR_FECHA) FROM IN_VARIABLE_PROCESO INVP " +
            "WHERE INVP.CVE_ENTIDAD = :cveEntidad " +
            "AND INVP.CVE_PROCESO = :cveProceso " +
            "AND INVP.VERSION = :version " +
            "AND INVP.CVE_INSTANCIA = :cveInstancia " +
            "AND INVP.OCURRENCIA = :ocurrencia " +
            "AND INVP.CVE_VARIABLE = :cveVariable " , nativeQuery = true)
	Date encuentraMinFecha(
			@Param("cveEntidad") String cveEntidad,
			@Param("cveProceso") String cveProceso,
			@Param("version") BigDecimal version,
			@Param("cveInstancia") String cveInstancia,
			@Param("cveVariable") String cveVariable,
			@Param("ocurrencia") Integer ocurrencia);

    // min valor Decimal
    @Query(value = "SELECT MIN(INVP.VALOR_DECIMAL) FROM IN_VARIABLE_PROCESO INVP " +
            "WHERE INVP.CVE_ENTIDAD = :cveEntidad " +
            "AND INVP.CVE_PROCESO = :cveProceso " +
            "AND INVP.VERSION = :version " +
            "AND INVP.CVE_INSTANCIA = :cveInstancia " +
            "AND INVP.OCURRENCIA = :ocurrencia " +
            "AND INVP.CVE_VARIABLE = :cveVariable ", nativeQuery = true)
    BigDecimal encuentraMinDecimal(
            @Param("cveEntidad") String cveEntidad,
            @Param("cveProceso") String cveProceso,
            @Param("version") BigDecimal version,
            @Param("cveInstancia") String cveInstancia,
            @Param("cveVariable") String cveVariable,
            @Param("ocurrencia") Integer ocurrencia);
    
    //min valor entero
    @Query(value = "SELECT MIN(INVP.VALOR_ENTERO) FROM IN_VARIABLE_PROCESO INVP " +
            "WHERE INVP.CVE_ENTIDAD = :cveEntidad " +
            "AND INVP.CVE_PROCESO = :cveProceso " +
            "AND INVP.VERSION = :version " +
            "AND INVP.CVE_INSTANCIA = :cveInstancia " +
            "AND INVP.OCURRENCIA = :ocurrencia " +
            "AND INVP.CVE_VARIABLE = :cveVariable " , nativeQuery = true)
	Integer encuentraMinEntero(
	            @Param("cveEntidad") String cveEntidad,
	            @Param("cveProceso") String cveProceso,
	            @Param("version") BigDecimal version,
	            @Param("cveInstancia") String cveInstancia,
	            @Param("cveVariable") String cveVariable,
	            @Param("ocurrencia") Integer ocurrencia);
    
    // promedio valor Decimal
    @Query(value = "SELECT SUM(INVP.VALOR_DECIMAL) FROM IN_VARIABLE_PROCESO INVP " +
            "WHERE INVP.CVE_ENTIDAD = :cveEntidad " +
            "AND INVP.CVE_PROCESO = :cveProceso " +
            "AND INVP.VERSION = :version " +
            "AND INVP.CVE_INSTANCIA = :cveInstancia " +
            "AND INVP.OCURRENCIA = :ocurrencia " +
            "AND INVP.CVE_VARIABLE = :cveVariable " , nativeQuery = true)
    BigDecimal encuentraPromedioDecimal(
            @Param("cveEntidad") String cveEntidad,
            @Param("cveProceso") String cveProceso,
            @Param("version") BigDecimal version,
            @Param("cveInstancia") String cveInstancia,
            @Param("cveVariable") String cveVariable,
            @Param("ocurrencia") Integer ocurrencia);
    
    //promedio valor entero
    @Query(value = "SELECT SUM(INVP.VALOR_ENTERO) " + 
    		"FROM IN_VARIABLE_PROCESO INVP " +
            "WHERE INVP.CVE_ENTIDAD = :cveEntidad " +
            "		AND INVP.CVE_PROCESO = :cveProceso " +
            "		AND INVP.VERSION = :version " +
            "		AND INVP.CVE_INSTANCIA = :cveInstancia " +
            "		AND INVP.OCURRENCIA = :ocurrencia " +
            "		AND INVP.CVE_VARIABLE = :cveVariable " , nativeQuery = true)
	Integer encuentraPromedioEntero(
	            @Param("cveEntidad") String cveEntidad,
	            @Param("cveProceso") String cveProceso,
	            @Param("version") BigDecimal version,
	            @Param("cveInstancia") String cveInstancia,
	            @Param("cveVariable") String cveVariable,
	            @Param("ocurrencia") Integer ocurrencia);

    //SELECT entrada a variable proceso
    @Query(value = "SELECT  " + 
    		"	INVP.VALOR_ALFANUMERICO, " +
            "	INVP.VALOR_ENTERO, " +
            "	INVP.VALOR_DECIMAL, " +
            "	INVP.VALOR_FECHA, " +
            "	STVP.TIPO " +
            "FROM  IN_VARIABLE_PROCESO INVP, " + 
            "      ST_VARIABLE_PROCESO  STVP " +
            "WHERE INVP.CVE_ENTIDAD = :cveEntidad " +
            "      AND INVP.CVE_PROCESO = :cveProceso " +
            "      AND INVP.VERSION = :version " +
            "      AND INVP.CVE_INSTANCIA = :cveInstancia " +
            "      AND INVP.CVE_VARIABLE = :cveVariable " +
            "      AND STVP.CVE_ENTIDAD = INVP.CVE_ENTIDAD " +
            "      AND STVP.CVE_PROCESO = INVP.CVE_PROCESO " +
            "      AND STVP.VERSION = INVP.VERSION " +
            "      AND STVP.CVE_VARIABLE = INVP.CVE_VARIABLE", nativeQuery = true)
    Object findByIdCompleto(
             @Param("cveEntidad") String cveEntidad,
             @Param("cveProceso") String cveProceso,
             @Param("version") BigDecimal version,
             @Param("cveInstancia") String cveInstancia,
             @Param("cveVariable") String variable);


    @Query(value = "SELECT MAX(INVP.OCURRENCIA) " +
            "FROM IN_VARIABLE_PROCESO INVP, " +
            "     ST_VARIABLE_SECCION STVS " +
            "WHERE INVP.CVE_ENTIDAD = :cveEntidad " +
            "  AND INVP.CVE_PROCESO = :cveProceso " +
            "  AND INVP.VERSION = :version " +
            "  AND INVP.CVE_INSTANCIA = :cveInstancia " +
            "  AND STVS.CVE_ENTIDAD = INVP.CVE_ENTIDAD " +
            "  AND STVS.CVE_PROCESO = INVP.CVE_PROCESO " +
            "  AND STVS.VERSION = INVP.VERSION " +
            "  AND STVS.CVE_SECCION = :cveSeccion " +
            "  AND STVS.CVE_VARIABLE = INVP.CVE_VARIABLE ", nativeQuery = true)
    Integer findMaxOcurrencia(@Param("cveEntidad") String cveEntidad,
                        @Param("cveProceso") String cveProceso,
                        @Param("version") BigDecimal version,
                        @Param("cveInstancia") String cveInstancia,
                        @Param("cveSeccion") String cveSeccion);

    @Query(value = "SELECT 1 " +
            "FROM IN_VARIABLE_PROCESO INVP " +
            "WHERE INVP.CVE_ENTIDAD = :cveEntidad " +
            "  AND INVP.CVE_PROCESO = :cveProceso " +
            "  AND INVP.VERSION = :version " +
            "  AND INVP.CVE_INSTANCIA = :cveInstancia " +
            "  AND INVP.CVE_VARIABLE IN " +
            "      (SELECT STVS.CVE_VARIABLE " +
            "       FROM ST_VARIABLE_SECCION STVS " +
            "       WHERE STVS.CVE_ENTIDAD = INVP.CVE_ENTIDAD " +
            "         AND STVS.CVE_PROCESO = INVP.CVE_PROCESO " +
            "         AND STVS.VERSION = INVP.VERSION " +
            "         AND STVS.CVE_SECCION = :cveSeccion)", nativeQuery = true)
    Integer findRecord(@Param("cveEntidad") String cveEntidad,
                @Param("cveProceso") String cveProceso,
                @Param("version") BigDecimal version,
                @Param("cveInstancia") String cveInstancia,
                @Param("cveSeccion") String cveSeccion);


    @Query(value = "SELECT * " +
            "FROM IN_VARIABLE_PROCESO INVP " +
            "WHERE INVP.CVE_ENTIDAD = :cveEntidad " +
            "  AND INVP.CVE_PROCESO = :cveProceso " +
            "  AND INVP.VERSION = :version " +
            "  AND INVP.CVE_INSTANCIA = :cveInstancia " +
            "  AND INVP.CVE_VARIABLE IN " +
            "      (SELECT STVS.CVE_VARIABLE " +
            "       FROM ST_VARIABLE_SECCION STVS " +
            "       WHERE STVS.CVE_ENTIDAD = :cveEntidad " +
            "         AND STVS.CVE_PROCESO = :cveProceso " +
            "         AND STVS.VERSION = :version " +
            "         AND STVS.CVE_SECCION = :cveSeccion)", nativeQuery = true)
    List<InVariableProceso> findRecords(@Param("cveEntidad") String cveEntidad,
                                  @Param("cveProceso") String cveProceso,
                                  @Param("version") BigDecimal version,
                                  @Param("cveInstancia") String cveInstancia,
                                  @Param("cveSeccion") String cveSeccion);

    
    @Query(value = "SELECT * " +
            "FROM IN_VARIABLE_PROCESO " +
            "WHERE CVE_ENTIDAD = :cveEntidad " +
            "  AND CVE_PROCESO = :cveProceso " +
            "  AND VERSION = :version " +
            "  AND CVE_INSTANCIA = :cveInstancia " +
            "  AND OCURRENCIA = :ocurrencia " +
            "  AND CVE_VARIABLE = :cveVariable", nativeQuery = true)
    List<InVariableProceso> findVariablesProceso(@Param("cveEntidad") String cveEntidad,
                                  @Param("cveProceso") String cveProceso,
                                  @Param("version") BigDecimal version,
                                  @Param("cveInstancia") String cveInstancia,
                                  @Param("ocurrencia") Integer ocurrencia,
                                  @Param("cveVariable") String cveVariable);

    @Query(value = "SELECT A.ETIQUETA_VARIABLE FROM (" +
            "SELECT STVP.ETIQUETA AS ETIQUETA_VARIABLE, " +
    		" STVS.CVE_SECCION, " +
            " STVS.NUMERO_RENGLON as ORDEN_PRESENTACION " +
            "FROM IN_VARIABLE_PROCESO INVP, " +
            "  ST_VARIABLE_SECCION STVS, " +
            "  ST_VARIABLE_PROCESO STVP " +
            "WHERE INVP.CVE_ENTIDAD = :cveEntidad " +
            " AND INVP.VERSION = :version " +
            " AND INVP.CVE_INSTANCIA = :cveInstancia " +
            " AND INVP.CVE_PROCESO = :cveProceso " +
            " AND INVP.SECUENCIA_VALOR = ( " +
                "SELECT MIN(INVP1.SECUENCIA_VALOR) " +
                "FROM IN_VARIABLE_PROCESO INVP1 " +
                "WHERE INVP1.CVE_ENTIDAD = INVP.CVE_ENTIDAD " +
                "AND INVP1.CVE_PROCESO = INVP.CVE_PROCESO " +
                "AND INVP1.VERSION = INVP.VERSION " +
                "AND INVP1.CVE_INSTANCIA = INVP.CVE_INSTANCIA " +
                "AND INVP1.CVE_VARIABLE = INVP.CVE_VARIABLE) " +
            " AND STVS.CVE_ENTIDAD = INVP.CVE_ENTIDAD " +
            " AND STVS.CVE_PROCESO = INVP.CVE_PROCESO " +
            " AND STVS.VERSION = INVP.VERSION " +
            " AND STVS.CVE_NODO = :cveNodo " +
            " AND STVS.ID_NODO = :idNodo " +
            " AND STVS.CVE_SECCION in (:cveSeccion) " +
            " AND STVS.CVE_VARIABLE = INVP.CVE_VARIABLE " +
            " AND STVP.CVE_ENTIDAD = INVP.CVE_ENTIDAD " +
            " AND STVP.CVE_PROCESO = INVP.CVE_PROCESO " +
            " AND STVP.VERSION = INVP.VERSION " +
            " AND STVP.CVE_VARIABLE = INVP.CVE_VARIABLE " +
            " AND STVP.TIPO != 'IMAGEN' " +
            " AND STVS.REQUERIDA = 'REQUERIDA' " +
            " AND STVS.SOLO_CONSULTA = 'NO' " +
            " AND ((	STVP.TIPO = 'ALFANUMERICO'	AND " + 
            "			INVP.VALOR_ALFANUMERICO IS NULL OR INVP.VALOR_ALFANUMERICO = ' ' )	OR " + 
            "	 (	STVP.TIPO = 'ENTERO'	AND INVP.VALOR_ENTERO IS NULL )	OR " + 
            "	 (	STVP.TIPO = 'DECIMAL'	AND INVP.VALOR_DECIMAL IS NULL )	OR " + 
            "	 (	STVP.TIPO = 'FECHA'	AND INVP.VALOR_FECHA IS NULL ) ) " +
            
            "UNION ALL " + 
            
           " SELECT  " + 
		   "   STVP.ETIQUETA AS ETIQUETA_VARIABLE,  " + 
   		   "   STVS.CVE_SECCION, " +
           "   STVS.NUMERO_RENGLON AS ORDEN_PRESENTACION " +
		   "  FROM  IN_IMAGEN_PROCESO INVP,  " + 
		   "    ST_VARIABLE_PROCESO STVP, " + 
		   "    ST_VARIABLE_SECCION STVS " + 
		   "  WHERE INVP.CVE_ENTIDAD = :cveEntidad  " + 
		   "			AND INVP.CVE_PROCESO = :cveProceso " + 
		   "   AND INVP.VERSION = :version  " + 
		   "   AND INVP.CVE_INSTANCIA = :cveInstancia  " + 
		   "   AND INVP.SECUENCIA_VALOR = (  " + 
		   "       SELECT MIN(INVP1.SECUENCIA_VALOR)  " + 
		   "       FROM IN_IMAGEN_PROCESO INVP1  " + 
		   "       WHERE INVP1.CVE_ENTIDAD = INVP.CVE_ENTIDAD  " + 
		   "       AND INVP1.CVE_PROCESO = INVP.CVE_PROCESO  " + 
		   "       AND INVP1.VERSION = INVP.VERSION  " + 
		   "       AND INVP1.CVE_INSTANCIA = INVP.CVE_INSTANCIA  " + 
		   "       AND INVP1.CVE_VARIABLE = INVP.CVE_VARIABLE)  " + 
		   "   AND STVS.CVE_ENTIDAD = INVP.CVE_ENTIDAD  " + 
		   "   AND STVS.CVE_PROCESO = INVP.CVE_PROCESO  " + 
		   "   AND STVS.VERSION = INVP.VERSION  " + 
		   "   AND STVS.CVE_NODO = :cveNodo  " + 
		   "   AND STVS.ID_NODO = :idNodo " + 
		   "   AND STVS.CVE_SECCION in (:cveSeccion) " + 
		   "   AND STVS.CVE_VARIABLE = INVP.CVE_VARIABLE  " + 
		   "   AND STVP.CVE_ENTIDAD = INVP.CVE_ENTIDAD  " + 
		   "   AND STVP.CVE_PROCESO = INVP.CVE_PROCESO  " + 
		   "   AND STVP.VERSION = INVP.VERSION  " + 
		   "   AND STVP.CVE_VARIABLE = INVP.CVE_VARIABLE " + 
		   "   AND STVS.REQUERIDA = 'REQUERIDA' " +
		   "   AND STVS.SOLO_CONSULTA = 'NO' " +
		   "   AND STVP.TIPO = 'IMAGEN' " +
		   "   AND INVP.VALOR_IMAGEN = NULL )A ORDER BY A.CVE_SECCION, A.ORDEN_PRESENTACION ",  nativeQuery = true)
    List<String>  findVariableSeccionRecordsRequeridos(@Param("cveEntidad") String cveEntidad,
                                                @Param("cveProceso") String cveProceso,
                                                @Param("version") BigDecimal version,
                                                @Param("cveInstancia") String cveInstancia,
                                                @Param("cveNodo") String cveNodo,
                                                @Param("idNodo") Integer integer,
                                                @Param("cveSeccion") List<String> cveSeccion);
    


  //"      AND CPP.SITUACION_EJECUCION = 'PROGRAMADO' " +
    @Query(value = 
    		"SELECT DISTINCT T.RFC, T.NOMBRE, T.CONTRATO  FROM CR_PROGRAMACION_PROCESO CPP, ( " + 
    		"SELECT DISTINCT VPRFC.VALOR_ALFANUMERICO AS RFC,  " + 
    		"                VPNC.VALOR_ALFANUMERICO AS NOMBRE, " + 
    		"                VPC.VALOR_ALFANUMERICO AS CONTRATO " + 
    		"            FROM IN_VARIABLE_PROCESO VPRFC,  " + 
    		"                 IN_VARIABLE_PROCESO VPNC, " + 
    		"                 IN_VARIABLE_PROCESO VPC, " + 
    		"                 ST_NODO_PROCESO STNP   " + 
    		"            WHERE  VPRFC.CVE_ENTIDAD = :cveEntidad  " + 
    		"				AND VPRFC.CVE_PROCESO  in  ('REGISTRO_DE_CONTRATO', 'ALTA_DEL_CONTRATISTA', 'ALTA_CONTRATISTA_CON_CONTRATO') " + 
    		"				AND VPRFC.CVE_VARIABLE = 'VPRO_01_RFC_CONTRATISTA'  " + 
    		"				AND VPRFC.VALOR_ALFANUMERICO IS NOT NULl  " + 
    		"				AND VPNC.CVE_ENTIDAD = VPRFC.CVE_ENTIDAD  " + 
    		"				AND VPNC.CVE_PROCESO = VPRFC.CVE_PROCESO  " + 
    		"				AND VPNC.VERSION = VPRFC.VERSION  " + 
    		"				AND VPNC.CVE_INSTANCIA = VPRFC.CVE_INSTANCIA  " + 
    		"				AND VPNC.CVE_VARIABLE = 'VPRO_01_NOMBRE_RAZON_SOCIAL_CONTRATISTA'  " + 
    		"				AND VPC.VALOR_ALFANUMERICO IS NOT NULL   " + 
    		"				AND VPC.CVE_ENTIDAD = VPRFC.CVE_ENTIDAD  " + 
    		"				AND VPC.CVE_PROCESO = VPRFC.CVE_PROCESO  " + 
    		"				AND VPC.VERSION = VPRFC.VERSION  " + 
    		"				AND VPC.CVE_INSTANCIA = VPRFC.CVE_INSTANCIA  " + 
    		"				AND VPC.CVE_VARIABLE = 'VPRO_01_NUM_CONTRATO'  " + 
    		"				AND VPC.VALOR_ALFANUMERICO IS NOT NULL  " + 
    		"				AND STNP.CVE_ENTIDAD = VPRFC.CVE_ENTIDAD   " + 
    		"				AND STNP.CVE_PROCESO = VPRFC.CVE_PROCESO  " + 
    		"				AND STNP.VERSION = VPRFC.VERSION  " + 
    		"				AND STNP.CVE_NODO = 'ACTIVIDAD-USUARIO'  " + 
    		"	AND ( (STNP.TIPO_ACCESO = 'ROL' AND :cveUsuario IN (SELECT UR.CVE_USUARIO FROM USUARIO_ROL UR, ST_ROL_NODO STRN  " + 
    		"		WHERE STRN.CVE_ENTIDAD = STNP.CVE_ENTIDAD AND STRN.CVE_PROCESO = STNP.CVE_PROCESO  " + 
    		"			AND STRN.VERSION = STNP.VERSION AND STRN.CVE_NODO = STNP.CVE_NODO AND UR.CVE_ENTIDAD = STRN.CVE_ENTIDAD     " + 
    		"			AND UR.CVE_PROCESO = STRN.CVE_PROCESO AND UR.VERSION = STRN.VERSION AND  UR.CVE_ROL = STRN.CVE_ROL ) )  " + 
    		"	OR ( STNP.TIPO_ACCESO = 'ROL_USUARIO' AND :cveUsuario IN ( SELECT USUARIO_CREADOR FROM IN_NODO_PROCESO  " + 
    		"		WHERE CVE_ENTIDAD = STNP.CVE_ENTIDAD AND CVE_PROCESO = STNP.CVE_PROCESO AND VERSION = STNP.VERSION  " + 
    		"			AND CVE_NODO = STNP.CVE_NODO AND CVE_INSTANCIA =  VPRFC.CVE_INSTANCIA ) )  " + 
    		"	OR :cveUsuario IN ( SELECT CVE_USUARIO FROM IN_NODO_PROCESO_USUARIO  " + 
    		"		WHERE CVE_ENTIDAD = STNP.CVE_ENTIDAD AND CVE_PROCESO = STNP.CVE_PROCESO AND VERSION = STNP.VERSION  " + 
    		"		AND CVE_NODO = STNP.CVE_NODO AND CVE_INSTANCIA =  VPRFC.CVE_INSTANCIA )) " + 
    		") T " + 
    		" WHERE CPP.CVE_ENTIDAD = :cveEntidad  " + 
    		" AND T.RFC = CPP.RFC " + 
    		" AND T.CONTRATO = CPP.CONTRATO  " + 
    		" AND (MONTH(CPP.FECHA_PROGRAMACION) >= MONTH(:fecha) AND YEAR(CPP.FECHA_PROGRAMACION) >= YEAR(:fecha))" +
    	    " ORDER BY T.NOMBRE, T.CONTRATO ", nativeQuery = true) 
    List<Object[]> getAllRfcAndNombreByProcess(@Param("cveEntidad") String cveEntidad, @Param("fecha") Date fecha, @Param("cveUsuario") String cveUsuario);


    
    @Query(value = "SELECT      DISTINCT " + 
    		"            VPRFC.CVE_PROCESO               CVE_PROCESO, " + 
    		"            VPRFC.CVE_INSTANCIA             INSTANCIA, " + 
    		"            VPNC.VALOR_ALFANUMERICO         NUMERO_CONTRATO " + 
 
    		"    FROM    IN_VARIABLE_PROCESO     VPRFC, " + 
    		"            IN_VARIABLE_PROCESO     VPNC " + 

    		"    WHERE   VPRFC.CVE_ENTIDAD =:cveEntidad					AND " + 
    		"            VPRFC.CVE_PROCESO = 'REGISTRO_DE_CONTRATO'		AND " + 
    		"            VPRFC.VERSION = 1                     			AND " + 
    		"            VPRFC.CVE_VARIABLE = 'VPRO_01_RFC_CONTRATISTA'	AND " + 
    		"			VPRFC.VALOR_ALFANUMERICO =:nombre					AND " + 

    		"            VPNC.CVE_ENTIDAD = VPRFC.CVE_ENTIDAD  			AND " + 
    		"            VPNC.CVE_PROCESO = VPRFC.CVE_PROCESO   		AND " + 
    		"            VPNC.VERSION = VPRFC.VERSION              		AND " + 
    		"            VPNC.CVE_INSTANCIA = VPRFC.CVE_INSTANCIA  		AND " + 
    		"            VPNC.CVE_VARIABLE = 'VPRO_01_NUM_CONTRATO'  ", nativeQuery = true)
    List<Object> contratosByRfc(@Param("cveEntidad") String cveEntidad, 
    		@Param("nombre") String nombre);
    
    @Query(value = "SELECT      DISTINCT " + 
    		"            VPRFC.CVE_PROCESO               CVE_PROCESO, " + 
    		"            VPRFC.CVE_INSTANCIA             INSTANCIA, " + 
    		"            VPNC.VALOR_ALFANUMERICO         NUMERO_CONTRATO " + 
 
    		"    FROM    IN_VARIABLE_PROCESO     VPRFC, " + 
    		"            IN_VARIABLE_PROCESO     VPNC " + 

    		"    WHERE   VPRFC.CVE_ENTIDAD =:cveEntidad					AND " + 
    		"            VPRFC.CVE_PROCESO in ('REGISTRO_DE_CONTRATO', 'ALTA_CONTRATISTA_CON_CONTRATO')		AND " + 
    		"            VPRFC.VERSION = 1                     			AND " + 
    		"            VPRFC.CVE_VARIABLE = 'VPRO_01_NUM_CONTRATO' 	AND " + 
    		"			 VPRFC.VALOR_ALFANUMERICO =:nombre					AND " + 
    		"            VPNC.CVE_ENTIDAD = VPRFC.CVE_ENTIDAD  			AND " + 
    		"            VPNC.CVE_PROCESO = VPRFC.CVE_PROCESO   		AND " + 
    		"            VPNC.VERSION = VPRFC.VERSION              		AND " + 
    		"            VPNC.CVE_INSTANCIA = VPRFC.CVE_INSTANCIA  		AND " + 
    		"            VPNC.CVE_VARIABLE = 'VPRO_01_RFC_CONTRATISTA'  ", nativeQuery = true)
    List<Object> RfcByContrato(@Param("cveEntidad") String cveEntidad, 
    		@Param("nombre") String nombre);
    
    
    @Query(value = "SELECT      DISTINCT " + 
    		"            VPRFC.CVE_PROCESO               CVE_PROCESO, " + 
    		"            VPRFC.CVE_INSTANCIA             INSTANCIA, " + 
    		"            VPNC.VALOR_ALFANUMERICO         NUMERO_CONTRATO " + 
 
    		"    FROM    IN_VARIABLE_PROCESO     VPRFC, " + 
    		"            IN_VARIABLE_PROCESO     VPNC " + 

    		"    WHERE   VPRFC.CVE_ENTIDAD =:cveEntidad					AND " + 
    		"            VPRFC.CVE_PROCESO =:cveProceso					AND " + 
    		"            VPRFC.VERSION = 1                     			AND " + 
    		"            VPRFC.CVE_VARIABLE = 'VPRO_01_RFC_CONTRATISTA'	AND " + 
    		"			VPRFC.VALOR_ALFANUMERICO =:nombre					AND " + 

    		"            VPNC.CVE_ENTIDAD = VPRFC.CVE_ENTIDAD  			AND " + 
    		"            VPNC.CVE_PROCESO = VPRFC.CVE_PROCESO   		AND " + 
    		"            VPNC.VERSION = VPRFC.VERSION              		AND " + 
    		"            VPNC.CVE_INSTANCIA = VPRFC.CVE_INSTANCIA  		AND " + 
    		"            VPNC.CVE_VARIABLE = 'VPRO_01_NUM_CONTRATO'  ", nativeQuery = true)
    List<Object> contratosByRfcProceso(@Param("cveEntidad") String cveEntidad, 
    		@Param("nombre") String nombre, 
    		@Param("cveProceso") String cveProceso);
    
    

    
    //            "      AND CPP.SITUACION_EJECUCION = 'PROGRAMADO' " +
    /*@Query(value = "SELECT * FROM ( " +
            "    SELECT DISTINCT " +
            "        VPRFC.CVE_PROCESO AS CVE_PROCESO, " +
            "        VPRFC.CVE_INSTANCIA AS INSTANCIA, " +
            "        VPNC.VALOR_ALFANUMERICO AS NUMERO_CONTRATO " +
            "    FROM IN_VARIABLE_PROCESO VPRFC, " +
            "         IN_VARIABLE_PROCESO VPNC " +
            "    WHERE VPRFC.CVE_ENTIDAD = :cveEntidad " +
            "      AND VPRFC.CVE_PROCESO = 'REGISTRO_DE_CONTRATO' " +
            "      AND VPRFC.VERSION = 1 " +
            "      AND VPRFC.CVE_VARIABLE = 'VPRO_01_NOMBRE_RAZON_SOCIAL_CONTRATISTA' " +
            "      AND VPRFC.VALOR_ALFANUMERICO = :nombre " +
            "      AND VPNC.CVE_ENTIDAD = VPRFC.CVE_ENTIDAD " +
            "      AND VPNC.CVE_PROCESO = VPRFC.CVE_PROCESO " +
            "      AND VPNC.VERSION = VPRFC.VERSION " +
            "      AND VPNC.CVE_INSTANCIA = VPRFC.CVE_INSTANCIA " +
            "      AND VPNC.CVE_VARIABLE = 'VPRO_01_NUM_CONTRATO' " +
            ") T " +
            "WHERE T.NUMERO_CONTRATO IN ( " +
            "    SELECT DISTINCT CPP.CONTRATO " +
            "    FROM CR_PROGRAMACION_PROCESO CPP " +
            "    WHERE CPP.CVE_ENTIDAD = :cveEntidad " +
            "      AND CPP.CONTRATO IS NOT NULL " +
            "	   AND (MONTH(CPP.FECHA_PROGRAMACION) = MONTH(:fecha) AND YEAR(CPP.FECHA_PROGRAMACION) = YEAR(:fecha)) " +
            ")", nativeQuery = true)
    List<Object[]> findContratosByEntidadAndNombre(@Param("cveEntidad") String cveEntidad, @Param("nombre") String nombre, @Param("fecha") Date fecha);
*/
    
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM IN_VARIABLE_PROCESO " +
    		 " WHERE CVE_ENTIDAD =:cveEntidad " +
    		 " AND CVE_PROCESO =:cveProceso AND " +
    		 " VERSION = 1 AND " +
    		 " CVE_INSTANCIA =:cveInstancia AND " + 
    		 " OCURRENCIA =:ocurrencia AND " +
    		 " CVE_VARIABLE in ('VPRO_01_NOMBRE_TRABAJADOR_TE','VPRO_01_CURP_TE','VPRO_01_SALARIO_BASE_COTIZACION_TE'," +
    		 " 'VPRO_01_NSS_TE','VPRO_01_FECHA_INICIO_DEL_TE','VPRO_01_FECHA_INICIO_AL_TE') ", nativeQuery = true)
    void deleteTrabajadorProceso(@Param("cveEntidad") String cveEntidad, 
    		@Param("cveProceso") String cveProceso,
    		@Param("cveInstancia") String cveInstancia,
			@Param("ocurrencia") Integer ocurrencia);
    
    
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM IN_VARIABLE_PROCESO " +
    		" WHERE CVE_ENTIDAD =:cveEntidad AND " +
    		" CVE_PROCESO =:cveProceso AND " +
    		" VERSION = 1 AND " +
    		" CVE_INSTANCIA =:cveInstancia AND" +
    		" CVE_VARIABLE in ('VPRO_01_NOMBRE_TRABAJADOR_TE','VPRO_01_CURP_TE', 'VPRO_01_NSS_TE','VPRO_01_RFC_CTA_TRAB_ESP') ", nativeQuery = true)
    void deleteTrabajadoresProceso(@Param("cveEntidad") String cveEntidad, 
    		@Param("cveProceso") String cveProceso,
    		@Param("cveInstancia") String cveInstancia);
    
    @Query(value = "SELECT    max(VPRFC.OCURRENCIA) " + 
 
    		"    FROM    IN_VARIABLE_PROCESO     VPRFC, " + 
    		"            IN_VARIABLE_PROCESO     VPNC " + 

    		"    WHERE   VPRFC.CVE_ENTIDAD =:cveEntidad					AND " + 
    		"            VPRFC.CVE_PROCESO = 'REGISTRO_DE_CONTRATO'		AND " + 
    		"            VPRFC.VERSION = 1                     			AND " + 
    		"            VPRFC.CVE_VARIABLE = 'VPRO_01_RFC_CONTRATISTA'	AND " + 
    		"			 VPRFC.VALOR_ALFANUMERICO =:cveRfc				AND " + 

    		"            VPNC.CVE_ENTIDAD = VPRFC.CVE_ENTIDAD  			AND " + 
    		"            VPNC.CVE_PROCESO = VPRFC.CVE_PROCESO   		AND " + 
    		"            VPNC.VERSION = VPRFC.VERSION              		AND " + 
    		"            VPNC.CVE_INSTANCIA = VPRFC.CVE_INSTANCIA  		AND " + 
    		"            VPNC.CVE_VARIABLE = 'VPRO_01_NUM_CONTRATO'  	AND " +
    		" 			 VPNC.VALOR_ALFANUMERICO =:contrato ", nativeQuery = true)
    Integer maxOcurrenciaByContrato(@Param("cveEntidad") String cveEntidad, @Param("cveRfc") String cveRfc, @Param("contrato") String contrato);



	  @Query(value = "SELECT * " + 
	    		"FROM IN_VARIABLE_PROCESO INVP " +
	            "WHERE INVP.CVE_ENTIDAD = :cveEntidad " +
	            "		AND INVP.CVE_PROCESO = :cveProceso " +
	            "		AND INVP.VERSION = :version " +
	            "		AND INVP.CVE_INSTANCIA = :cveInstancia " +
	            "		AND INVP.OCURRENCIA = :ocurrencia " +
	            "		AND INVP.CVE_VARIABLE = :cveVariable " +
	            "		AND INVP.SECUENCIA_VALOR = :secuenciaValor ", nativeQuery = true)
	  Optional<InVariableProceso> encontrarInVarProceso(
		            @Param("cveEntidad") String cveEntidad,
		            @Param("cveProceso") String cveProceso,
		            @Param("version") BigDecimal version,
		            @Param("cveInstancia") String cveInstancia,
		            @Param("cveVariable") String cveVariable,
		            @Param("ocurrencia") Integer ocurrencia,
		            @Param("secuenciaValor") Integer secuenciaValor);

	  
	  @Query(value = "SELECT * " + 
	    		"FROM IN_VARIABLE_PROCESO INVP " +
	            "WHERE INVP.CVE_ENTIDAD = :cveEntidad " +
	            "		AND INVP.CVE_PROCESO = :cveProceso " +
	            "		AND INVP.VERSION = :version " +
	            "		AND INVP.CVE_INSTANCIA = :cveInstancia " +
	            "		AND INVP.CVE_VARIABLE = :cveVariable ", nativeQuery = true)
	  InVariableProceso encontraInVarProceso(
		            @Param("cveEntidad") String cveEntidad,
		            @Param("cveProceso") String cveProceso,
		            @Param("version") BigDecimal version,
		            @Param("cveInstancia") String cveInstancia,
		            @Param("cveVariable") String cveVariable);
	  

	  @Query(value = "SELECT * " +
				"FROM IN_VARIABLE_PROCESO INVP " 
			    + "WHERE INVP.CVE_ENTIDAD = :cveEntidad "
				+ "		AND INVP.CVE_PROCESO = :cveProceso " 
				+ "		AND INVP.VERSION = :version "
				+ "		AND INVP.CVE_VARIABLE = :cveVariable ", nativeQuery = true)
	List<InVariableProceso> encuentraLista(
			@Param("cveEntidad") String cveEntidad,
			@Param("cveProceso") String cveProceso,
			@Param("version") BigDecimal version,
			@Param("cveVariable") String cveVariable);
	  
	  
  
	  
	  @Query(value = "SELECT * FROM ( " +
	  		  " SELECT * " + 
			  " FROM IN_VARIABLE_PROCESO IVP " + 
			  " WHERE IVP.CVE_ENTIDAD = :cveEntidad  " + 
			  " AND IVP.CVE_PROCESO = 'REGISTRO_DE_CONTRATO'  " + 
			  " AND IVP.VERSION = 1  " + 
			  " AND IVP.CVE_VARIABLE IN ('VPRO_01_RFC_CONTRATISTA', 'VPRO_01_NUM_CONTRATO', " +
			  "        'VPRO_01_PERIODO_EJECUCION_DEL_CONTRATISTA', 'VPRO_01_PERIODO_EJECUCION_AL_CONTRATISTA') " + 
			  " AND IVP.CVE_INSTANCIA IN ( " + 
			  "     SELECT DISTINCT A.CVE_INSTANCIA " + 
			  "     FROM IN_VARIABLE_PROCESO A, IN_VARIABLE_PROCESO B " + 
			  "     WHERE A.CVE_ENTIDAD = IVP.CVE_ENTIDAD  " + 
			  "       AND A.CVE_PROCESO = 'REGISTRO_DE_CONTRATO'  " + 
			  "       AND A.VERSION = 1  " + 
			  "       AND A.CVE_VARIABLE = 'VPRO_01_CONTRATO_CORRECTO_VER' " + 
			  "       AND A.VALOR_ALFANUMERICO = 'SI' " + 
			 
			  "       AND B.CVE_ENTIDAD = A.CVE_ENTIDAD  " +			  
			  "       AND B.CVE_PROCESO = A.CVE_PROCESO  " +
			  "       AND B.VERSION = A.VERSION  " + 
			  "       AND B.CVE_INSTANCIA = A.CVE_INSTANCIA  " + 
			  "       AND B.CVE_VARIABLE = 'VPRO_01_CLASE_CONTRATO_CONTRATISTA' " + 
			  "       AND B.VALOR_ALFANUMERICO = :claseContrato " + 
			  "  ) UNION " +
	  		  " SELECT * " + 
			  " FROM IN_VARIABLE_PROCESO IVP " + 
			  " WHERE IVP.CVE_ENTIDAD = :cveEntidad  " + 
			  " AND IVP.CVE_PROCESO = 'ALTA_CONTRATISTA_CON_CONTRATO'  " + 
			  " AND IVP.VERSION = 1  " + 
			  " AND IVP.CVE_VARIABLE IN ('VPRO_01_RFC_CONTRATISTA', 'VPRO_01_NUM_CONTRATO', " +
			  "        'VPRO_01_PERIODO_EJECUCION_DEL_CONTRATISTA', 'VPRO_01_PERIODO_EJECUCION_AL_CONTRATISTA') " +
			  " AND IVP.CVE_INSTANCIA IN ( " + 
			  "     SELECT DISTINCT A.CVE_INSTANCIA " + 
			  "     FROM IN_VARIABLE_PROCESO A " + 
			  "     WHERE A.CVE_ENTIDAD = IVP.CVE_ENTIDAD  " + 
			  "       AND A.CVE_PROCESO = 'ALTA_CONTRATISTA_CON_CONTRATO'  " + 
			  "       AND A.VERSION = 1  " + 
			  "       AND A.CVE_VARIABLE = 'VPRO_01_CLASE_CONTRATO_CONTRATISTA' " + 
			  "       AND A.VALOR_ALFANUMERICO = :claseContrato  ) " + 			  
			  ") A ORDER BY A.CVE_ENTIDAD, A.CVE_PROCESO, A.CVE_INSTANCIA, A.CVE_VARIABLE ", nativeQuery = true)
		 List<InVariableProceso> obtenerContratosValidos(@Param("cveEntidad") String cveEntidad, @Param("claseContrato")  String claseContrato);
	

		@Query(value = "SELECT	* "
				+ "	FROM	IN_VARIABLE_PROCESO	INVP"
				+ "	WHERE   INVP.CVE_ENTIDAD = :cveEntidad AND  "
				+ "			INVP.CVE_PROCESO = :cveProceso AND  "
				+ "			INVP.VERSION = :version AND "
				+ "			INVP.CVE_INSTANCIA = :cveInstancia		AND"
				+ "			INVP.CVE_VARIABLE = :cveVariable		", nativeQuery = true)
		 List<InVariableProceso> obtenerVariableProcesoSinOcurrencia( @Param("cveEntidad") String cveEntidad,
											@Param("cveProceso") String cveProceso,
											@Param("version") BigDecimal version,
											@Param("cveInstancia") String cveInstancia,
											@Param("cveVariable") String cveVariable);
	    



	    @Query(value = " SELECT DISTINCT VPRFC.VALOR_ALFANUMERICO RFC, VPNC.VALOR_ALFANUMERICO NOMBRE " + 
	    		"	    						    FROM    IN_VARIABLE_PROCESO     VPRFC,  " + 
	    		"	    						            IN_VARIABLE_PROCESO     VPNC, 	" + 
	    		"											ST_NODO_PROCESO			STNP    " + 
	    		"	    						    WHERE   VPRFC.CVE_ENTIDAD = :cveEntidad AND	" + 
	    		"	    						            VPRFC.CVE_PROCESO  in ('REGISTRO_DE_CONTRATO','ALTA_CONTRATISTA_CON_CONTRATO') AND  " + 
	    		"	    						            VPRFC.CVE_VARIABLE = 'VPRO_01_RFC_CONTRATISTA'  AND    " + 
	    		"	    						            VPRFC.VALOR_ALFANUMERICO IS NOT NULL			AND    " + 
	    		
	    		"	    						            VPNC.CVE_ENTIDAD = VPRFC.CVE_ENTIDAD            AND    " + 
	    		"	    						            VPNC.CVE_PROCESO = VPRFC.CVE_PROCESO            AND    " + 
	    		"	    						            VPNC.VERSION = VPRFC.VERSION                    AND    " + 
	    		"	    						            VPNC.CVE_INSTANCIA = VPRFC.CVE_INSTANCIA        AND    " + 
	    		"	    						            VPNC.CVE_VARIABLE = 'VPRO_01_NOMBRE_RAZON_SOCIAL_CONTRATISTA' AND " + 
	    		"	    		                            VPNC.VALOR_ALFANUMERICO IS NOT NULL  AND" + 
	    		
	    		"											STNP.CVE_ENTIDAD = VPRFC.CVE_ENTIDAD  AND " + 
	    		"											STNP.CVE_PROCESO = VPRFC.CVE_PROCESO AND " + 
	    		"											STNP.VERSION = VPRFC.VERSION AND " + 
	    		"											STNP.CVE_NODO = 'ACTIVIDAD-USUARIO' AND " + 
	    		"											( (STNP.TIPO_ACCESO = 'ROL' AND " + 
	    		"													:cveUsuario IN (  " + 
	    		"														SELECT	UR.CVE_USUARIO   " + 
	    		"															FROM	USUARIO_ROL			UR,  " + 
	    		"																	ST_ROL_NODO			STRN  " + 
	    		"															WHERE	STRN.CVE_ENTIDAD = STNP.CVE_ENTIDAD AND  " + 
	    		"																STRN.CVE_PROCESO = STNP.CVE_PROCESO		AND  " + 
	    		"																STRN.VERSION = STNP.VERSION				AND  " + 
	    		"																STRN.CVE_NODO = STNP.CVE_NODO			AND" + 
	    		"																UR.CVE_ENTIDAD = STRN.CVE_ENTIDAD	AND  " + 
	    		"																UR.CVE_PROCESO = STRN.CVE_PROCESO	AND  " + 
	    		"																UR.VERSION = STRN.VERSION			AND  " + 
	    		"																UR.CVE_ROL = STRN.CVE_ROL  " + 
	    		"													)  )  " + 
	    		"											 OR ( STNP.TIPO_ACCESO = 'ROL_USUARIO' AND :cveUsuario IN (" + 
	    		"													SELECT USUARIO_CREADOR FROM IN_NODO_PROCESO" + 
	    		"													WHERE CVE_ENTIDAD = STNP.CVE_ENTIDAD	AND  " + 
	    		"																CVE_PROCESO = STNP.CVE_PROCESO	AND " + 
	    		"																VERSION = STNP.VERSION			AND " + 
	    		"																CVE_NODO = STNP.CVE_NODO		AND	" + 
	    		"																CVE_INSTANCIA =  VPRFC.CVE_INSTANCIA " +
	    		"											 ) ) " + 
	    		"											 OR :cveUsuario IN " + 
	    		"				 									( SELECT CVE_USUARIO FROM IN_NODO_PROCESO_USUARIO  " + 
	    		"																WHERE CVE_ENTIDAD = STNP.CVE_ENTIDAD	AND " + 
	    		"																CVE_PROCESO = STNP.CVE_PROCESO			AND " + 
	    		"																VERSION = STNP.VERSION					AND " + 
	    		"																CVE_NODO = STNP.CVE_NODO				AND " + 
	    		"																CVE_INSTANCIA =  VPRFC.CVE_INSTANCIA	" +
	    		"													) ) " + 
	    		"	    			    		ORDER BY   VPNC.VALOR_ALFANUMERICO   ", 
	    nativeQuery = true)
	    List<Object[]> getRfcAndNombreByProces(@Param("cveEntidad") String cveEntidad, @Param("cveUsuario") String cveUsuario);
	    
	    
	    
	    @Query(value = " 	SELECT DISTINCT VPRFC.CVE_PROCESO,   " + 
	    		"	    		            VPRFC.CVE_INSTANCIA,   " + 
	    		"	    		            VPNC.VALOR_ALFANUMERICO AS NUMERO_CONTRATO   " + 

	    		"	    		    FROM    IN_VARIABLE_PROCESO     VPRFC,   " + 
	    		"	    		            IN_VARIABLE_PROCESO     VPNC, " + 
	    		"							ST_NODO_PROCESO     STNP " + 

	    		"	    		    WHERE   VPRFC.CVE_ENTIDAD = :cveEntidad					AND   " + 
	    		"	    		            VPRFC.CVE_PROCESO IN ('REGISTRO_DE_CONTRATO','ALTA_CONTRATISTA_CON_CONTRATO')  AND   " + 
	    		"	    		            VPRFC.VERSION = 1                     			AND   " + 
	    		"	    		            VPRFC.CVE_VARIABLE = 'VPRO_01_RFC_CONTRATISTA'	AND   " + 
	    		"	    					VPRFC.VALOR_ALFANUMERICO = :rfc         		AND   " + 
 
	    		"	    		            VPNC.CVE_ENTIDAD = VPRFC.CVE_ENTIDAD  			AND   " + 
	    		"	    		            VPNC.CVE_PROCESO = VPRFC.CVE_PROCESO   		    AND   " + 
	    		"	    		            VPNC.VERSION = VPRFC.VERSION              		AND   " + 
	    		"	    		            VPNC.CVE_INSTANCIA = VPRFC.CVE_INSTANCIA  		AND   " + 
	    		"	    		            VPNC.CVE_VARIABLE = 'VPRO_01_NUM_CONTRATO'      AND   " + 
	    		"							STNP.CVE_ENTIDAD = VPRFC.CVE_ENTIDAD            AND   " + 
	    		"							STNP.CVE_PROCESO = VPRFC.CVE_PROCESO            AND   " + 
	    		"							STNP.VERSION = VPRFC.VERSION                    AND   " + 
	    		"							STNP.CVE_NODO = 'ACTIVIDAD-USUARIO'             AND   " + 
	    		"							( (STNP.TIPO_ACCESO = 'ROL' AND             " + 
	    		"								:cveUsuario IN ( SELECT UR.CVE_USUARIO                                                                                                         " + 
	    		"									FROM USUARIO_ROL UR,                                                                             " + 
	    		"										ST_ROL_NODO STRN                                                                                    " + 
	    		"									WHERE STRN.CVE_ENTIDAD = STNP.CVE_ENTIDAD AND                                                                         " + 
	    		"								STRN.CVE_PROCESO = STNP.CVE_PROCESO AND                                                                     " + 
	    		"								STRN.VERSION = STNP.VERSION         AND                                                             " + 
	    		"								STRN.CVE_NODO = STNP.CVE_NODO       AND                                                                     " + 
	    		"								UR.CVE_ENTIDAD = STRN.CVE_ENTIDAD   AND                                                                             " + 
	    		"								UR.CVE_PROCESO = STRN.CVE_PROCESO   AND                                                                             " + 
	    		"								UR.VERSION = STRN.VERSION           AND                                                                     " + 
	    		"								UR.CVE_ROL = STRN.CVE_ROL ) ) " + 
	    		"							OR ( STNP.TIPO_ACCESO = 'ROL_USUARIO' AND :cveUsuario IN ( " + 
	    		"								SELECT USUARIO_CREADOR FROM IN_NODO_PROCESO  " + 
	    		"								WHERE CVE_ENTIDAD = STNP.CVE_ENTIDAD AND  " + 
	    		"								CVE_PROCESO = STNP.CVE_PROCESO       AND      " + 
	    		"								VERSION = STNP.VERSION               AND  " + 
	    		"								CVE_NODO = STNP.CVE_NODO             AND           " + 
	    		"								CVE_INSTANCIA =  VPRFC.CVE_INSTANCIA  ) )                   " + 
	    		"							OR  :cveUsuario IN ( SELECT CVE_USUARIO  " + 
	    		"								FROM IN_NODO_PROCESO_USUARIO   " + 
	    		"								WHERE CVE_ENTIDAD = STNP.CVE_ENTIDAD AND        " + 
	    		"								CVE_PROCESO = STNP.CVE_PROCESO       AND   " + 
	    		"								VERSION = STNP.VERSION               AND   " + 
	    		"								CVE_NODO = STNP.CVE_NODO             AND  " + 
	    		"								CVE_INSTANCIA =  VPRFC.CVE_INSTANCIA                          " + 
	    		"								) )   ", nativeQuery = true)
	    List<Object> contratosByRfcProcesos(@Param("cveEntidad") String cveEntidad, 
	    		@Param("rfc") String rfc, @Param("cveUsuario") String cveUsuario);
	    

	    @Query(value = " SELECT RZ.CVE_VARIABLE, RZ.VALOR_ALFANUMERICO, RZ.VALOR_FECHA  " + 
	    		" FROM IN_VARIABLE_PROCESO INP, IN_VARIABLE_PROCESO IVC, IN_VARIABLE_PROCESO RZ  " + 
	    		" WHERE INP.CVE_ENTIDAD = :cveEntidad  " + 
	    		"	AND INP.CVE_PROCESO IN ('REGISTRO_DE_CONTRATO','ALTA_CONTRATISTA_CON_CONTRATO')  " + 
	    		"	AND INP.VALOR_ALFANUMERICO = :rfc  " + 

	    		"	AND IVC.CVE_ENTIDAD = INP.CVE_ENTIDAD  " + 
	    		"	AND IVC.CVE_INSTANCIA = INP.CVE_INSTANCIA  " + 
	    		"	AND IVC.CVE_PROCESO = INP.CVE_PROCESO  " + 
	    		"	AND IVC.VERSION = INP.VERSION  " + 
	    		"	AND IVC.VALOR_ALFANUMERICO = :contrato  " + 

	    		"	AND RZ.CVE_ENTIDAD = INP.CVE_ENTIDAD  " + 
	    		"	AND RZ.CVE_INSTANCIA = INP.CVE_INSTANCIA  " + 
	    		"	AND RZ.CVE_PROCESO = INP.CVE_PROCESO  " + 
	    		"	AND RZ.VERSION = INP.VERSION  " + 
	    		"	AND RZ.CVE_VARIABLE IN ('VPRO_01_NOMBRE_RAZON_SOCIAL_CONTRATISTA','VPRO_01_PERSONALIDAD_CONTRATISTA',   " + 
	    		"		'VPRO_01_OBJETO_CONTRATISTA','VPRO_01_PERIODO_EJECUCION_AL_CONTRATISTA','VPRO_01_PERIODO_EJECUCION_DEL_CONTRATISTA')  " + 
	    		" UNION  " + 
	    		" SELECT RZ.CVE_VARIABLE, RZ.VALOR_ALFANUMERICO, RZ.VALOR_FECHA  " + 
	    		" FROM IN_VARIABLE_PROCESO INP, IN_VARIABLE_PROCESO IVC, IN_VARIABLE_PROCESO RZ  " + 
	    		" WHERE INP.CVE_ENTIDAD = :cveEntidad  " + 
	    		"	AND INP.CVE_PROCESO IN ('CONT_SERV_ESPE_CTE','CONT_SERV_ESPE_CTE_CC')  " + 
	    		"	AND INP.VALOR_ALFANUMERICO = :rfc  " + 

	    		"	AND IVC.CVE_ENTIDAD = INP.CVE_ENTIDAD  " + 
	    		"	AND IVC.CVE_INSTANCIA = INP.CVE_INSTANCIA  " + 
	    		"	AND IVC.CVE_PROCESO = INP.CVE_PROCESO  " + 
	    		"	AND IVC.VERSION = INP.VERSION  " + 
	    		"	AND IVC.VALOR_ALFANUMERICO = :contrato  " + 
	    		
	    		"	AND RZ.CVE_ENTIDAD = INP.CVE_ENTIDAD  " + 
	    		"	AND RZ.CVE_INSTANCIA = INP.CVE_INSTANCIA  " + 
	    		"	AND RZ.CVE_PROCESO = INP.CVE_PROCESO  " + 
	    		"	AND RZ.VERSION = INP.VERSION  " + 
	    		"	AND RZ.CVE_VARIABLE IN ('VPRO_01_NVO_PER_AL','VPRO_01_RAZON', 'VPRO_01_JUSTIFICACION')  ", 
	    nativeQuery = true)
	    List<Object[]> getObtienInfoProcesos(@Param("cveEntidad") String cveEntidad, @Param("contrato") String contrato, @Param("rfc") String rfc);
	    
	    @Query(value ="SELECT DISTINCT VPRFC.*    " + 
	    		"	    FROM    IN_VARIABLE_PROCESO     VPRFC,    " + 
	    		"	    	IN_VARIABLE_PROCESO     VPNC,  " + 
	    		"	    	ST_NODO_PROCESO     STNP  " + 
	    		"	    WHERE   VPRFC.CVE_ENTIDAD = :cveEntidad					AND    " + 
	    		"	    VPRFC.CVE_PROCESO IN ('REGISTRO_DE_CONTRATO','ALTA_CONTRATISTA_CON_CONTRATO')  AND    " + 
	    		"	    VPRFC.VERSION = 1                     			AND    " + 
	    		"	    VPRFC.CVE_VARIABLE = 'VPRO_01_RFC_CONTRATISTA'	AND    " + 
	    		"	    VPRFC.VALOR_ALFANUMERICO = :rfc       			AND    " + 
	    		"	    VPRFC.OCURRENCIA = 1						    AND    " +
	    		
	    		"	    VPNC.CVE_ENTIDAD = VPRFC.CVE_ENTIDAD  			AND    " + 
	    		"	    VPNC.CVE_PROCESO = VPRFC.CVE_PROCESO   		    AND    " + 
	    		"	    VPNC.VERSION = VPRFC.VERSION              		AND    " + 
	    		"	    VPNC.OCURRENCIA = VPRFC.OCURRENCIA         		AND    " +
	    		"	    VPNC.CVE_INSTANCIA = VPRFC.CVE_INSTANCIA  		AND    " + 
	    		"	    VPNC.CVE_VARIABLE = 'VPRO_01_NUM_CONTRATO'      AND    " + 
	    		"	    VPNC.VALOR_ALFANUMERICO = :contrato ", nativeQuery = true)
	    InVariableProceso obternerDatosProceso(@Param("cveEntidad") String cveEntidad, @Param("contrato") String contrato, @Param("rfc") String rfc);

	   @Query(value ="SELECT iv.* FROM IN_VARIABLE_PROCESO iv  " + 
	    		"	    		WHERE iv.CVE_ENTIDAD = :cveEntidad AND iv.CVE_PROCESO = 'CONT_SERV_ESPE_CTE_CC'   " + 
	    		"	    		AND iv.CVE_VARIABLE = 'VPRO_01_NVO_PER_AL' " +
	    		"				AND iv.VALOR_FECHA IS NOT NULL " + 
	    		"	    		AND iv.CVE_INSTANCIA IN (SELECT MAX(ivp.CVE_INSTANCIA) FROM IN_VARIABLE_PROCESO ivp, IN_VARIABLE_PROCESO ivr     " + 
	    		"	    		WHERE ivp.CVE_ENTIDAD = :cveEntidad " + 
	    		"				AND ivp.CVE_ENTIDAD = ivr.CVE_ENTIDAD" + 
	    		"				AND ivp.CVE_PROCESO = ivr.CVE_PROCESO" + 
	    		"				AND ivp.CVE_PROCESO = 'CONT_SERV_ESPE_CTE_CC'  " + 
	    		"               AND ivp.VERSION = ivr.VERSION " +
	    		"				AND ivp.CVE_INSTANCIA = ivr.CVE_INSTANCIA" + 
	    		"               AND ivp.OCURRENCIA = ivr.OCURRENCIA " +
	    		"	    		AND ivp.CVE_VARIABLE = 'VPRO_01_NUM_CONTRATO' " + 
	    		"               AND ivp.SECUENCIA_VALOR = ivr.SECUENCIA_VALOR" +
	    		"				AND ivp.VALOR_ALFANUMERICO = :contrato " + 
	    		"				AND ivr.CVE_VARIABLE = 'VPRO_01_RAZON' " + 
	    		"				AND ivr.VALOR_ALFANUMERICO = :razon)  " + 
	    		"	    		UNION ALL " + 
	    		"	    		SELECT iv.* FROM IN_VARIABLE_PROCESO iv  " + 
	    		"	    		WHERE iv.CVE_ENTIDAD = :cveEntidad AND iv.CVE_PROCESO = 'CONT_SERV_ESPE_CTE'  " + 
	    		"	    		AND iv.CVE_VARIABLE = 'VPRO_01_NVO_PER_AL' " +
	    		"				AND iv.VALOR_FECHA IS NOT NULL " + 
	    		"	    		AND iv.CVE_INSTANCIA IN (SELECT MAX(ivp.CVE_INSTANCIA) FROM IN_VARIABLE_PROCESO ivp, IN_VARIABLE_PROCESO ivr     " + 
	    		"	    		WHERE ivp.CVE_ENTIDAD = :cveEntidad " +
	    		"               AND ivp.CVE_ENTIDAD = ivr.CVE_ENTIDAD" +
	    		"               AND ivp.CVE_PROCESO = ivr.CVE_PROCESO" +
	    		"				AND ivp.CVE_PROCESO = 'CONT_SERV_ESPE_CTE'  " + 
	            "               AND ivp.VERSION = ivr.VERSION " +
	    		"				AND ivr.CVE_INSTANCIA = ivp.CVE_INSTANCIA" + 
	    		"               AND ivp.OCURRENCIA = ivr.OCURRENCIA " +
	    		"	    		AND ivp.CVE_VARIABLE = 'VPRO_01_NUM_CONTRATO' " + 
	    		"               AND ivp.SECUENCIA_VALOR = ivr.SECUENCIA_VALOR" +
	    		"				AND ivp.VALOR_ALFANUMERICO = :contrato " + 
	    		"				AND ivr.CVE_VARIABLE = 'VPRO_01_RAZON' " + 
	    		"				AND ivr.VALOR_ALFANUMERICO = :razon ) ", nativeQuery = true)
	    List<InVariableProceso> obternNueFecProceso(@Param("cveEntidad") String cveEntidad,
	    											@Param("contrato") String contrato,
	    											@Param("razon") String razon);
	    
	    
	    @Query(value ="SELECT iv.* FROM IN_VARIABLE_PROCESO iv  " + 
	    		"	    		WHERE iv.CVE_ENTIDAD = :cveEntidad AND iv.CVE_PROCESO = 'CONT_SERV_ESPE_CTE_CC'   " + 
	    		"	    		AND ((iv.CVE_VARIABLE = 'VPRO_01_NVO_PER_AL' AND iv.VALOR_FECHA IS NOT NULL ) or " + 
	    		"				(iv.CVE_VARIABLE = 'VPRO_01_RAZON' AND iv.VALOR_ALFANUMERICO IS NOT NULL ))" + 
	    		"	    		AND iv.CVE_INSTANCIA IN (SELECT MAX(ivp.CVE_INSTANCIA) FROM IN_VARIABLE_PROCESO ivp, IN_VARIABLE_PROCESO ivr     " + 
	    		"	    		WHERE ivp.CVE_ENTIDAD = :cveEntidad " + 
	    		"               AND ivp.CVE_ENTIDAD = ivr.CVE_ENTIDAD" +
	    		"				AND ivp.CVE_PROCESO = ivr.CVE_PROCESO" +
	    		"				AND ivp.CVE_PROCESO = 'CONT_SERV_ESPE_CTE_CC'  " + 
	    		"               AND ivp.VERSION = ivr.VERSION " +
	    		"				AND ivr.CVE_INSTANCIA = ivp.CVE_INSTANCIA" +
	    		"               AND ivp.OCURRENCIA = ivr.OCURRENCIA " +
	    		"	    		AND ivp.CVE_VARIABLE = 'VPRO_01_NUM_CONTRATO' " +
	    		"               AND ivp.SECUENCIA_VALOR = ivr.SECUENCIA_VALOR" +
	    		"				AND ivp.VALOR_ALFANUMERICO = :contrato " + 
	    		"				AND ivr.CVE_VARIABLE = 'VPRO_01_RAZON' " + 
	    		"				AND ivr.VALOR_ALFANUMERICO != 'POSTERGACION')  " + 
	    		"	    		UNION ALL " + 
	    		"	    		SELECT iv.* FROM IN_VARIABLE_PROCESO iv  " + 
	    		"	    		WHERE iv.CVE_ENTIDAD = :cveEntidad AND iv.CVE_PROCESO = 'CONT_SERV_ESPE_CTE'  " + 
	    		"	    		AND ((iv.CVE_VARIABLE = 'VPRO_01_NVO_PER_AL' AND iv.VALOR_FECHA IS NOT NULL ) or " + 
	    		"				(iv.CVE_VARIABLE = 'VPRO_01_RAZON' AND iv.VALOR_ALFANUMERICO IS NOT NULL ))" + 
	    		"	    		AND iv.CVE_INSTANCIA IN (SELECT MAX(ivp.CVE_INSTANCIA) FROM IN_VARIABLE_PROCESO ivp, IN_VARIABLE_PROCESO ivr     " + 
	    		"	    		WHERE ivp.CVE_ENTIDAD = :cveEntidad " + 
	    		"               AND ivp.CVE_ENTIDAD = ivr.CVE_ENTIDAD" +
	    		"               AND ivp.CVE_PROCESO = ivr.CVE_PROCESO" +
	    		"				AND ivp.CVE_PROCESO = 'CONT_SERV_ESPE_CTE'  " + 
	            "               AND ivp.VERSION = ivr.VERSION " +
	    		"				AND ivr.CVE_INSTANCIA = ivp.CVE_INSTANCIA" +
	    		"               AND ivp.OCURRENCIA = ivr.OCURRENCIA " +
	    		"	    		AND ivp.CVE_VARIABLE = 'VPRO_01_NUM_CONTRATO' " +
	    		"               AND ivp.SECUENCIA_VALOR = ivr.SECUENCIA_VALOR" +
	    		"				AND ivp.VALOR_ALFANUMERICO = :contrato " + 
	    		"				AND ivr.CVE_VARIABLE = 'VPRO_01_RAZON' " + 
	    		"				AND ivr.VALOR_ALFANUMERICO != 'POSTERGACION' ) ", nativeQuery = true)
	    List<InVariableProceso> obternCalcelaProceso(@Param("cveEntidad") String cveEntidad, @Param("contrato") String contrato);
	    
	    // max valor Decimal
	    @Query(value = "SELECT INVP.VALOR_ALFANUMERICO FROM IN_VARIABLE_PROCESO INVP " +
	            "WHERE INVP.CVE_ENTIDAD = :cveEntidad " +
	            "		AND INVP.CVE_PROCESO = :cveProceso " +
	            "		AND INVP.VERSION = :version " +
	            "		AND INVP.CVE_INSTANCIA = :cveInstancia " +
	            "		AND INVP.CVE_VARIABLE = :cveVariable " +
	            " order by SECUENCIA_VALOR", nativeQuery = true)
	   List<String> encuentraValorAlfanumerico(
	            @Param("cveEntidad") String cveEntidad,
	            @Param("cveProceso") String cveProceso,
	            @Param("version") BigDecimal version,
	            @Param("cveInstancia") String cveInstancia,
	            @Param("cveVariable") String cveVariable);
	    
    @Query(value = " SELECT DISTINCT  "
    		+ "    vprfc.CVE_ENTIDAD AS CVE_ENTIDAD,  "
    		+ "    vprfc.CVE_PROCESO AS CVE_PROCESO,  "
    		+ "    vprfc.CVE_INSTANCIA AS INSTANCIA,  "
    		+ "    vprfc.VALOR_ALFANUMERICO AS RFC_CONTRATISTA,  "
    		+ "    vpnc.VALOR_ALFANUMERICO AS NUMERO_CONTRATO,  "
    		+ "    vprs.VALOR_ALFANUMERICO AS RAZON_SOCIAL_CONTRATISTA,  "
    		+ "    inpu.USUARIO_CREADOR AS CVE_USUARIO  "
    		+ " FROM IN_VARIABLE_PROCESO vprfc  "
    		+ " INNER JOIN IN_NODO_PROCESO inpu "
    		+ " ON inpu.CVE_ENTIDAD = vprfc.CVE_ENTIDAD	AND  "  
	    	+ "		inpu.CVE_PROCESO = vprfc.CVE_PROCESO			AND  "  
	    	+ "		inpu.VERSION = vprfc.VERSION					AND " 
	    	+ "		inpu.CVE_INSTANCIA =  vprfc.CVE_INSTANCIA AND "
	    	+ "		inpu.CVE_NODO_ORIGEN = 'EVENTO-INICIO' "
    		+ " INNER JOIN IN_VARIABLE_PROCESO vpnc  "
    		+ "    ON vpnc.CVE_ENTIDAD = vprfc.CVE_ENTIDAD  "
    		+ "   AND vpnc.CVE_PROCESO = vprfc.CVE_PROCESO  "
    		+ "   AND vpnc.VERSION = vprfc.VERSION  "
    		+ "   AND vpnc.CVE_INSTANCIA = vprfc.CVE_INSTANCIA  "
    		+ "   AND vpnc.CVE_VARIABLE = 'VPRO_01_NUM_CONTRATO'  "
    		+ " INNER JOIN IN_VARIABLE_PROCESO vprs  "
    		+ "    ON vprs.CVE_ENTIDAD = vprfc.CVE_ENTIDAD  "
    		+ "   AND vprs.CVE_PROCESO = vprfc.CVE_PROCESO  "
    		+ "   AND vprs.VERSION = vprfc.VERSION  "
    		+ "   AND vprs.CVE_INSTANCIA = vprfc.CVE_INSTANCIA  "
    		+ "   AND vprs.CVE_VARIABLE = 'VPRO_01_NOMBRE_RAZON_SOCIAL_CONTRATISTA'  "
    		+ " INNER JOIN IN_VARIABLE_PROCESO vpfi  "
    		+ "    ON vpfi.CVE_ENTIDAD = vprfc.CVE_ENTIDAD  "
    		+ "   AND vpfi.CVE_PROCESO = vprfc.CVE_PROCESO  "
    		+ "   AND vpfi.VERSION = vprfc.VERSION  "
    		+ "   AND vpfi.CVE_INSTANCIA = vprfc.CVE_INSTANCIA  "
    		+ "   AND vpfi.CVE_VARIABLE = 'VPRO_01_PERIODO_EJECUCION_DEL_CONTRATISTA'  "
    		+ " INNER JOIN IN_VARIABLE_PROCESO vpff  "
    		+ "    ON vpff.CVE_ENTIDAD = vprfc.CVE_ENTIDAD  "
    		+ "   AND vpff.CVE_PROCESO = vprfc.CVE_PROCESO  "
    		+ "   AND vpff.VERSION = vprfc.VERSION  "
    		+ "   AND vpff.CVE_INSTANCIA = vprfc.CVE_INSTANCIA  "
    		+ "   AND vpff.CVE_VARIABLE = 'VPRO_01_PERIODO_EJECUCION_AL_CONTRATISTA'  "
    		+ " WHERE vprfc.CVE_PROCESO IN ('ALTA_CONTRATISTA_CON_CONTRATO', 'REGISTRO_DE_CONTRATO')  "
    		+ "  AND vprfc.CVE_VARIABLE = 'VPRO_01_RFC_CONTRATISTA'  "
    		+ "  AND vprfc.VALOR_ALFANUMERICO IS NOT NULL  "
    		+ "  AND vpnc.VALOR_ALFANUMERICO IS NOT NULL  "
    		+ "  AND vprs.VALOR_ALFANUMERICO IS NOT NULL  "    		
    		+ "  AND CURRENT_DATE BETWEEN vpfi.VALOR_FECHA AND vpff.VALOR_FECHA  "
    		+ " ORDER BY vprfc.VALOR_ALFANUMERICO, vpnc.VALOR_ALFANUMERICO " , nativeQuery = true)
    List<Object[]> findRepseConsultaVariablesRaw();
    
    @Query(value = "SELECT COUNT(1)  FROM IN_VARIABLE_PROCESO ivp, IN_VARIABLE_PROCESO ivp2,  " + 
    		"	IN_VARIABLE_PROCESO ivp3, IN_VARIABLE_PROCESO ivp4, IN_VARIABLE_PROCESO ivp5, IN_VARIABLE_PROCESO ivp6   " + 
    		"WHERE ivp.CVE_ENTIDAD = :cveEntidad  " + 
    		"AND ivp.CVE_PROCESO = 'ELIMINADO_DE_DOCUMENTOS' " + 
    		"AND ivp.CVE_VARIABLE  = 'VPRO_01_PERIODO'  " + 
    		"AND ivp.VALOR_FECHA = :periodo " + 
    		"AND ivp2.CVE_ENTIDAD = ivp.CVE_ENTIDAD  " + 
    		"AND ivp2.CVE_PROCESO = ivp.CVE_PROCESO " + 
    		"AND ivp2.VERSION = ivp.VERSION " + 
    		"AND ivp2.OCURRENCIA  = ivp.OCURRENCIA  " + 
    		"AND ivp2.CVE_INSTANCIA = ivp.CVE_INSTANCIA " + 
    		"AND ivp2.CVE_VARIABLE  = 'VPRO_01_PROCESO'  " + 
    		"AND ivp2.VALOR_ALFANUMERICO = :docProceso " + 
    		"AND ivp3.CVE_ENTIDAD = ivp.CVE_ENTIDAD  " + 
    		"AND ivp3.CVE_PROCESO = ivp.CVE_PROCESO " + 
    		"AND ivp3.VERSION = ivp.VERSION " + 
    		"AND ivp3.OCURRENCIA  = ivp.OCURRENCIA  " + 
    		"AND ivp3.CVE_INSTANCIA = ivp.CVE_INSTANCIA " + 
    		"AND ivp3.CVE_VARIABLE  = 'VPRO_01_APROBACION'  " + 
    		"AND ivp3.VALOR_ALFANUMERICO = 'SI' " + 
    		"AND ivp4.CVE_ENTIDAD = ivp.CVE_ENTIDAD  " + 
    		"AND ivp4.CVE_PROCESO = ivp.CVE_PROCESO " + 
    		"AND ivp4.VERSION = ivp.VERSION " + 
    		"AND ivp4.OCURRENCIA  = ivp.OCURRENCIA  " + 
    		"AND ivp4.CVE_INSTANCIA = ivp.CVE_INSTANCIA " + 
    		"AND ivp4.CVE_VARIABLE  = 'VPRO_01_RFC'  " + 
    		"AND ivp4.VALOR_ALFANUMERICO = :rfc " + 
    		"AND ivp5.CVE_ENTIDAD = ivp.CVE_ENTIDAD  " + 
    		"AND ivp5.CVE_PROCESO = ivp.CVE_PROCESO " + 
    		"AND ivp5.VERSION = ivp.VERSION " + 
    		"AND ivp5.OCURRENCIA  = ivp.OCURRENCIA  " + 
    		"AND ivp5.CVE_INSTANCIA = ivp.CVE_INSTANCIA " + 
    		"AND ivp5.CVE_VARIABLE  = 'VPRO_01_NUM_CONTRATO' " +
    		"AND ivp5.VALOR_ALFANUMERICO = :contrato " + 
    		"AND ivp6.CVE_ENTIDAD = ivp.CVE_ENTIDAD " + 
    		"AND ivp6.CVE_PROCESO = ivp.CVE_PROCESO " + 
    		"AND ivp6.VERSION = ivp.VERSION " + 
    		"AND ivp6.OCURRENCIA  = ivp.OCURRENCIA " + 
    		"AND ivp6.CVE_INSTANCIA = ivp.CVE_INSTANCIA " + 
    		"AND ivp6.CVE_VARIABLE  = 'VPRO_01_CONF_DE_ELIMINADO' " + 
    		"AND ivp6.VALOR_ALFANUMERICO IS NULL", nativeQuery = true)
   int encuentraValorAlfanumericoEliminaDoc(
            @Param("cveEntidad") String cveEntidad,
            @Param("periodo") Date periodo,
            @Param("docProceso") String docProceso,
            @Param("rfc") String rfc,
            @Param("contrato") String contrato);
    
    @Query(value = "SELECT COUNT(1) " +
            "FROM IN_DOCUMENTO_PROCESO_OC DOC " +
            "INNER JOIN ST_DOCUMENTO_PROCESO STDOC " +
            "  ON DOC.CVE_ENTIDAD = STDOC.CVE_ENTIDAD " +
            " AND DOC.CVE_PROCESO = STDOC.CVE_PROCESO " +
            " AND DOC.VERSION = STDOC.VERSION " +
            " AND DOC.SECUENCIA_DOCUMENTO = STDOC.SECUENCIA_DOCUMENTO " +
            "LEFT JOIN PARAMETRO_GENERAL PG " +
            "  ON PG.DESCRIPCION = STDOC.DESCRIPCION " +
            " AND PG.CVE_PARAMETRO LIKE 'DOC_OBLIGATORIO_%' " +
            " AND PG.CVE_PARAMETRO LIKE '%OBRA%' " +
            "WHERE DOC.CVE_ENTIDAD = :cveEntidad " +
            "  AND DOC.CVE_PROCESO = :cveProceso " +
            "  AND DOC.CVE_INSTANCIA = :cveInstancia " +
            "  AND SUBSTRING_INDEX(PG.VALOR_ALFANUMERICO, '|', 1) = :cveProcesoPeriodico", nativeQuery = true)
    int cuentaDocsCargados(String cveEntidad, String cveProceso, String cveInstancia, String cveProcesoPeriodico);


    @Query(value = 
    	    " SELECT DISTINCT UPPER(SUBSTRING_INDEX(NOMBRE_ARCHIVO, '.', -1)) AS EXTENSION " +
    	    " FROM IN_DOCUMENTO_PROCESO_OC DOC " +
    	    " JOIN ( " +
    	    "    SELECT DISTINCT " +
    	    "         SUBSTRING_INDEX(PG.VALOR_ALFANUMERICO, '|', 1) AS CVE_PROCESO_PERIODICO, " +
    	    "        DOC.DESCRIPCION, " +
    	    "        DOC.CVE_ENTIDAD, " +
    	    "        DOC.CVE_PROCESO, " +
    	    "        DOC.VERSION, " +
    	    "        DOC.SECUENCIA_DOCUMENTO " +
    	    "    FROM ST_DOCUMENTO_PROCESO DOC " +
    	    "    INNER JOIN PARAMETRO_GENERAL PG " +
    	    "      ON PG.DESCRIPCION = DOC.DESCRIPCION " +
    	    "     AND PG.CVE_PARAMETRO LIKE 'DOC_OBLIGATORIO_%' " +
    	    "     AND PG.CVE_PARAMETRO LIKE '%OBRA%' " +
    	    "    WHERE DOC.CVE_ENTIDAD = :cveEntidad " +
    	    "      AND DOC.CVE_PROCESO =  'FACT_SERV_CONT'  " +
    	    " ) PP " +
    	    "  ON DOC.CVE_ENTIDAD = PP.CVE_ENTIDAD " +
    	    " AND DOC.CVE_PROCESO = PP.CVE_PROCESO " +
    	    " AND DOC.VERSION = PP.VERSION " +
    	    " AND DOC.SECUENCIA_DOCUMENTO = PP.SECUENCIA_DOCUMENTO " +
    	    " WHERE DOC.CVE_ENTIDAD = :cveEntidad " +
    	    "  AND DOC.CVE_PROCESO = 'FACT_SERV_CONT'  " +
    	    "  AND PP.CVE_PROCESO_PERIODICO = :cveProcesoPeriodico " +
    	    "  AND NOMBRE_ARCHIVO IS NOT NULL  " +
    	    " AND EXISTS ( " +
    	    "    SELECT 1 " +
    	    "    FROM IN_VARIABLE_PROCESO VP " +
    	    "    WHERE VP.CVE_ENTIDAD = DOC.CVE_ENTIDAD " +
    	    "      AND VP.CVE_PROCESO  IN ('ALTA_CONTRATISTA_CON_CONTRATO', 'REGISTRO_DE_CONTRATO') " +
    	    "      AND VP.CVE_VARIABLE = 'VPRO_01_NUM_CONTRATO' " +
    	    "      AND VP.VALOR_ALFANUMERICO = :numeroContrato " +
    	    " ) " +
    	    " AND EXISTS ( " +
    	    "    SELECT 1 " +
    	    "    FROM IN_VARIABLE_PROCESO VPRFC " +
    	    "    WHERE VPRFC.CVE_ENTIDAD = DOC.CVE_ENTIDAD " +
    	    "      AND VPRFC.CVE_PROCESO  IN ('ALTA_CONTRATISTA_CON_CONTRATO', 'REGISTRO_DE_CONTRATO') " +
    	    "      AND VPRFC.CVE_VARIABLE = 'VPRO_01_RFC_CONTRATISTA'" +
    	    "      AND VPRFC.VALOR_ALFANUMERICO = :rfc " +
    	    " )", nativeQuery = true)
	List<String> getExtensionesFacturas (@Param("cveEntidad") String cveEntidad, @Param("cveProcesoPeriodico") String cveProcesoPeriodico,             
		    	@Param("rfc") String rfc, @Param("numeroContrato") String numeroContrato);
    
    
    @Query(value = "SELECT DOC.CVE_INSTANCIA, DOC.CVE_ENTIDAD, PP.CVE_PROCESO_PERIODICO, " +
            "DOC.SECUENCIA_ARCHIVO AS SECUENCIA_CARGA, " +
            "SUBSTRING_INDEX(DOC.NOMBRE_ARCHIVO, '.', -1) AS TIPO_ARCHIVO, " +
            "DOC.NOMBRE_ARCHIVO AS NOMBRE_DOCUMENTO " +
            "FROM IN_DOCUMENTO_PROCESO_OC DOC " +
            "JOIN ( " +
            "   SELECT DISTINCT " +
            "       SUBSTRING_INDEX(PG.VALOR_ALFANUMERICO, '|', 1) AS CVE_PROCESO_PERIODICO, " +
            "       DOC.DESCRIPCION, DOC.CVE_ENTIDAD, DOC.CVE_PROCESO, DOC.VERSION, DOC.SECUENCIA_DOCUMENTO " +
            "   FROM ST_DOCUMENTO_PROCESO DOC " +
            "   INNER JOIN PARAMETRO_GENERAL PG " +
            "       ON PG.DESCRIPCION = DOC.DESCRIPCION " +
            "      AND PG.CVE_PARAMETRO LIKE 'DOC_OBLIGATORIO_%' " +
            "      AND PG.CVE_PARAMETRO LIKE '%OBRA%' " +
            "   WHERE DOC.CVE_ENTIDAD = :cveEntidad " +
            "     AND DOC.CVE_PROCESO = 'FACT_SERV_CONT' " +

            ") PP " +
            " ON DOC.CVE_ENTIDAD = PP.CVE_ENTIDAD " +
            "AND DOC.CVE_PROCESO = PP.CVE_PROCESO " +
            "AND DOC.VERSION = PP.VERSION " +
            "AND DOC.SECUENCIA_DOCUMENTO = PP.SECUENCIA_DOCUMENTO " +
            "WHERE DOC.CVE_ENTIDAD = :cveEntidad " +
            " AND DOC.CVE_PROCESO = 'FACT_SERV_CONT' " +
            " AND DOC.NOMBRE_ARCHIVO IS NOT NULL " +
            "     AND PP.CVE_PROCESO_PERIODICO = :cveProcesoPeriodico " +
            "     AND NOMBRE_ARCHIVO IS NOT NULL" +
            " AND EXISTS ( " +
            "   SELECT 1 FROM IN_VARIABLE_PROCESO VP " +
            "   WHERE VP.CVE_ENTIDAD = DOC.CVE_ENTIDAD " +
            "     AND VP.CVE_PROCESO = 'FACT_SERV_CONT' " +
            "     AND VP.CVE_INSTANCIA = DOC.CVE_INSTANCIA " +
            "     AND VP.CVE_VARIABLE = 'VPRO_01_NUM_CONTRATO' " +
            "     AND VP.VALOR_ALFANUMERICO = :numContrato " +
            " ) " +
            " AND EXISTS ( " +
            "   SELECT 1 FROM IN_VARIABLE_PROCESO VPRFC " +
            "   WHERE VPRFC.CVE_ENTIDAD = DOC.CVE_ENTIDAD " +
            
            "     AND VPRFC.CVE_PROCESO IN ('ALTA_CONTRATISTA_CON_CONTRATO', 'REGISTRO_DE_CONTRATO') " +
            "     AND VPRFC.CVE_VARIABLE = 'VPRO_01_RFC_CONTRATISTA' " +
            "     AND VPRFC.VALOR_ALFANUMERICO = :rfc " +
            " )",
            nativeQuery = true)
    List<Object[]> getDocumentoByContratoRFC(
            @Param("cveEntidad") String cveEntidad,
            @Param("numContrato") String numContrato,
            @Param("rfc") String rfc,
            @Param("cveProcesoPeriodico") String cveProcesoPeriodico);
    
    
    @Query(value = "SELECT DOC.CVE_INSTANCIA, DOC.CVE_ENTIDAD, PP.CVE_PROCESO_PERIODICO, " +
            "DOC.SECUENCIA_ARCHIVO AS SECUENCIA_CARGA, " +
            "SUBSTRING_INDEX(DOC.NOMBRE_ARCHIVO, '.', -1) AS TIPO_ARCHIVO, " +
            "DOC.NOMBRE_ARCHIVO AS NOMBRE_DOCUMENTO, DOC.ARCHIVO_BINARIO " +
            "FROM IN_DOCUMENTO_PROCESO_OC DOC " +
            "JOIN ( " +
            "   SELECT DISTINCT " +
            "       SUBSTRING_INDEX(PG.VALOR_ALFANUMERICO, '|', 1) AS CVE_PROCESO_PERIODICO, " +
            "       DOC.DESCRIPCION, DOC.CVE_ENTIDAD, DOC.CVE_PROCESO, DOC.VERSION, DOC.SECUENCIA_DOCUMENTO " +
            "   FROM ST_DOCUMENTO_PROCESO DOC " +
            "   INNER JOIN PARAMETRO_GENERAL PG " +
            "       ON PG.DESCRIPCION = DOC.DESCRIPCION " +
            "      AND PG.CVE_PARAMETRO LIKE 'DOC_OBLIGATORIO_%' "  +
            "       AND PG.CVE_PARAMETRO LIKE '%OBRA%' "  +
            "    WHERE DOC.CVE_ENTIDAD = :cveEntidad "  +
            "      AND DOC.CVE_PROCESO = 'FACT_SERV_CONT' "  +

            " ) PP "  +
            "  ON DOC.CVE_ENTIDAD = PP.CVE_ENTIDAD "  +
            " AND DOC.CVE_PROCESO = PP.CVE_PROCESO "  +
            " AND DOC.VERSION = PP.VERSION "  +
            " AND DOC.SECUENCIA_DOCUMENTO = PP.SECUENCIA_DOCUMENTO "  +
            " WHERE DOC.CVE_ENTIDAD = :cveEntidad "  +
            "  AND DOC.CVE_PROCESO = 'FACT_SERV_CONT' "  +
            "  AND DOC.NOMBRE_ARCHIVO IS NOT NULL "  +
            "  AND PP.CVE_PROCESO_PERIODICO = :cveProcesoPeriodico "  +
            "  AND DOC.NOMBRE_ARCHIVO = :nombreArchivo "  +
            "  AND EXISTS ( "  +
            "    SELECT 1 FROM IN_VARIABLE_PROCESO VP "  +
            "    WHERE VP.CVE_ENTIDAD = DOC.CVE_ENTIDAD "  +
            "      AND VP.CVE_PROCESO = 'FACT_SERV_CONT' "  +
            "      AND VP.CVE_INSTANCIA = DOC.CVE_INSTANCIA "  +
            "      AND VP.CVE_VARIABLE = 'VPRO_01_NUM_CONTRATO' "  +
            "      AND VP.VALOR_ALFANUMERICO = :numContrato "  +
            "  ) " +
            "  AND EXISTS ( " +
            "   SELECT 1 FROM IN_VARIABLE_PROCESO VPRFC " +
            "   WHERE VPRFC.CVE_ENTIDAD = DOC.CVE_ENTIDAD " +            
            "     AND VPRFC.CVE_PROCESO IN ('ALTA_CONTRATISTA_CON_CONTRATO', 'REGISTRO_DE_CONTRATO') " +
            "     AND VPRFC.CVE_VARIABLE = 'VPRO_01_RFC_CONTRATISTA' " +
            "     AND VPRFC.VALOR_ALFANUMERICO = :rfc " +
            "  ) ",
            nativeQuery = true)
    List<Object[]>  getDocumentoByNombreContratoRFC(
            @Param("cveEntidad") String cveEntidad,
            @Param("numContrato") String numContrato,
            @Param("rfc") String rfc,
            @Param("cveProcesoPeriodico") String cveProcesoPeriodico,
            @Param("nombreArchivo") String nombreArchivo);

}
