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

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.briomax.briobpm.persistence.entity.StBotonNodo;
import com.briomax.briobpm.persistence.entity.StBotonNodoPK;

/**
 * El objetivo de la Interface IStBotonNodoRepository.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Oct 22, 2024 12:31:05 PM Modificaciones:
 * @since JDK 1.8
 */
@Repository
public interface IStBotonNodoRepository extends JpaRepository<StBotonNodo, StBotonNodoPK> {

	@Query(
		    value = "SELECT DISTINCT STBN.ORDEN_BOTON, " +
		    		"       STBN.ACCION_BOTON, " +
		            "       STBN.ETIQUETA_BOTON, " +
		            "       STBN.REQUIERE_CONFIRMACION, " +
		            "       STBN.MENSAJE_CONFIRMACION, " +
		            "       STBN.ACCION_NO_CONFIRMACION, " +
		            "       STBN.MENSAJE_NO_CONFIRMACION " +
		            "FROM   ST_BOTON_NODO STBN, " +
		            "       ST_ROL_USUARIO_BOTON STRUB " +
		            "WHERE  STBN.CVE_ENTIDAD = :cveEntidad " +
		            "AND    STBN.CVE_PROCESO = :cveProceso " +
		            "AND    STBN.VERSION = :version " +
		            "AND    STBN.CVE_NODO = :cveNodo " +
		            "AND    STBN.ID_NODO = :idNodo " +
		            "AND    STRUB.CVE_ENTIDAD = STBN.CVE_ENTIDAD " +
		            "AND    STRUB.CVE_PROCESO = STBN.CVE_PROCESO " +
		            "AND    STRUB.VERSION = STBN.VERSION " +
		            "AND    STRUB.CVE_NODO = STBN.CVE_NODO " +
		            "AND    STRUB.ID_NODO = STBN.ID_NODO " +
		            "AND    STRUB.SECUENCIA_BOTON = STBN.SECUENCIA_BOTON " +
		            "AND    STRUB.CVE_ROL in ( " + 
		            "							SELECT DISTINCT STN.CVE_ROL " + 
		            "									FROM ST_ROL_NODO STN WHERE " + 
		            "										   STN.CVE_ENTIDAD = :cveEntidad " +
		            "									AND    STBN.CVE_PROCESO = :cveProceso " +
		            "									AND    STN.VERSION = :version " +
		            "									AND    STN.CVE_NODO = :cveNodo " +
		            "									AND    STN.ID_NODO = :idNodo )" +
		            "AND    (STRUB.CVE_USUARIO IS NULL OR (STRUB.CVE_USUARIO IS NOT NULL AND STRUB.CVE_USUARIO = :cveUsuario)) " +
		            "ORDER BY STBN.ORDEN_BOTON",
		    nativeQuery = true)
		List<Object[]> obtenerDatosBotones(
		    @Param("cveEntidad") String cveEntidad,
		    @Param("cveProceso") String cveProceso,
		    @Param("version") BigDecimal version,
		    @Param("cveNodo") String cveNodo,
		    @Param("idNodo") Integer idNodo,
		    @Param("cveUsuario") String cveUsuario);

		
}
