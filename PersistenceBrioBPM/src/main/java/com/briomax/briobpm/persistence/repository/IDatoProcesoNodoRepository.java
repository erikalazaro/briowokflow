package com.briomax.briobpm.persistence.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.briomax.briobpm.persistence.entity.DatoProcesoNodo;

/**
 * El objetivo de la interfaz IDatoProcesoRepository.java es proporcionar métodos 
 * para acceder y manipular datos relacionados con los procesos de nodo en la base de datos.
 *
 * @autor Alexis Zamora
 * @version 1.0
 * @fecha Dic 05, 2023 12:30:03 PM
 * @since JDK 1.8
 */
@Repository
public interface IDatoProcesoNodoRepository extends JpaRepository<DatoProcesoNodo, String> {

	
	@Query(value = "SELECT 1 "+
			"FROM DATO_PROCESO_NODO DPN " + 
			"WHERE DPN.CVE_DATO_PROCESO_NODO = :cveVariable", nativeQuery = true)
	Integer verificaDato(@Param("cveVariable") String cveVariable);
	
	@Query(value = "SELECT * "+
			"FROM DATO_PROCESO_NODO DPN " + 
			"WHERE DPN.CVE_DATO_PROCESO_NODO = :cveVariable", nativeQuery = true)
	Optional<DatoProcesoNodo> encuentraDato(@Param("cveVariable") String cveVariable);
	
	@Query(value =
		    "SELECT " +
		    "    DAT.ORDEN_DATO AS ORDEN_DATO, " +
		    "    DAT.ETIQUETA AS ETIQUETA, " +
		    "    DAT.ANCHO_COLUMNA AS ANCHO_COLUMNA, " +
		    "    DAT.TIPO_CONTROL AS TIPO_CONTROL, " +
		    "    DAT.ORIGEN_DATO AS ORIGEN_DATO, " +
		    "    DAT.VISIBLE AS VISIBLE, " +
		    "    DAT.COLOR AS COLOR, " +
		    "    DAT.FILTRO AS FILTRO, " +
		    "    DAT.TIPO_LISTA AS TIPO_LISTA, " +
		    "    DAT.MULTI_SELECCION AS MULTI_SELECCION, " +
		    "    DAT.OPCION_TODOS AS OPCION_TODOS, " +
		    "    DAT.ETIQUETA_TODOS AS ETIQUETA_TODOS, " +
		    "    DAT.CVE_LISTA_FILTRO AS CVE_LISTA_FILTRO, " +
		    "    DAT.COLUMNA_VALOR AS COLUMNA_VALOR, " +
		    "    DAT.COLUMNA_DESCRIPCION AS COLUMNA_DESCRIPCION, " +
		    "    DAT.TABLA_LISTA AS TABLA_LISTA, " +
		    "    DAT.WHERE_LISTA AS WHERE_LISTA, " +
		    "    DATPN.CVE_DATO_PROCESO_NODO AS CVE_DATO_PROCESO_NODO, " +
		    "    DATVP.CVE_PROCESO AS CVE_PROCESO, " +
		    "    DATVP.VERSION AS VERSION, " +
		    "    DATVP.CVE_VARIABLE AS CVE_VARIABLE_1, " +
		    "    DATVP.AGREGACION AS AGREGACION, " +
		    "    DATVE.CVE_ENTIDAD AS CVE_ENTIDAD_1, " +
		    "    DATVE.CVE_VARIABLE AS CVE_VARIABLE_2, " +
		    "    DATVL.CVE_ENTIDAD AS CVE_ENTIDAD_2, " +
		    "    DATVL.CVE_LOCALIDAD AS CVE_LOCALIDAD, " +
		    "    DATVL.CVE_VARIABLE AS CVE_VARIABLE_3, " +
		    "    DATVS.CVE_VARIABLE AS CVE_VARIABLE_4 " +
		    "FROM DATO_AREA_TRABAJO DAT " +
		    "LEFT OUTER JOIN DATO_AT_PROCESO_NODO DATPN ON DAT.CVE_ENTIDAD = DATPN.CVE_ENTIDAD " +
		    "                                             AND DAT.CVE_PROCESO = DATPN.CVE_PROCESO " +
		    "                                             AND DAT.VERSION = DATPN.VERSION " +
		    "                                             AND DAT.CVE_AREA_TRABAJO = DATPN.CVE_AREA_TRABAJO " +
		    "                                             AND DAT.SECUENCIA_DATO = DATPN.SECUENCIA_DATO " +
		    "LEFT OUTER JOIN DATO_AT_VARIABLE_PROCESO DATVP ON DAT.CVE_ENTIDAD = DATVP.CVE_ENTIDAD " +
		    "                                                 AND DAT.CVE_PROCESO = DATVP.CVE_PROCESO " +
		    "                                                 AND DAT.VERSION = DATVP.VERSION " +
		    "                                                 AND DAT.CVE_AREA_TRABAJO = DATVP.CVE_AREA_TRABAJO " +
		    "                                                 AND DAT.SECUENCIA_DATO = DATVP.SECUENCIA_DATO " +
		    "LEFT OUTER JOIN DATO_AT_VARIABLE_ENTIDAD DATVE ON DAT.CVE_ENTIDAD = DATVE.CVE_ENTIDAD " +
		    "                                                  AND DAT.CVE_PROCESO = DATVE.CVE_PROCESO " +
		    "                                                  AND DAT.VERSION = DATVE.VERSION " +
		    "                                                  AND DAT.CVE_AREA_TRABAJO = DATVE.CVE_AREA_TRABAJO " +
		    "                                                  AND DAT.SECUENCIA_DATO = DATVE.SECUENCIA_DATO " +
		    "LEFT OUTER JOIN DATO_AT_VARIABLE_LOCALIDAD DATVL ON DAT.CVE_ENTIDAD = DATVL.CVE_ENTIDAD " +
		    "                                                    AND DAT.CVE_PROCESO = DATVL.CVE_PROCESO " +
		    "                                                    AND DAT.VERSION = DATVL.VERSION " +
		    "                                                    AND DAT.CVE_AREA_TRABAJO = DATVL.CVE_AREA_TRABAJO " +
		    "                                                    AND DAT.SECUENCIA_DATO = DATVL.SECUENCIA_DATO " +
		    "LEFT OUTER JOIN DATO_AT_VARIABLE_SISTEMA DATVS ON DAT.CVE_ENTIDAD = DATVS.CVE_ENTIDAD " +
		    "                                                 AND DAT.CVE_PROCESO = DATVS.CVE_PROCESO " +
		    "                                                 AND DAT.VERSION = DATVS.VERSION " +
		    "                                                 AND DAT.CVE_AREA_TRABAJO = DATVS.CVE_AREA_TRABAJO " +
		    "                                                 AND DAT.SECUENCIA_DATO = DATVS.SECUENCIA_DATO " +
		    "WHERE DAT.CVE_ENTIDAD = :cveEntidad " +
		    "      AND DAT.CVE_PROCESO = :cveProceso " +
		    "      AND DAT.VERSION = :version " +
		    "      AND DAT.CVE_AREA_TRABAJO = :cveAreaTrabajo " +
		    "ORDER BY ORDEN_DATO",
		    nativeQuery = true)
		List<Object> obtenerDatos(@Param("cveEntidad") String cveEntidad,
		                            @Param("cveProceso") String cveProceso,
		                            @Param("version") BigDecimal version,
		                            @Param("cveAreaTrabajo") String cveAreaTrabajo);


}