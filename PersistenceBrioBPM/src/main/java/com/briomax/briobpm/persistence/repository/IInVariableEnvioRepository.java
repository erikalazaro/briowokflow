package com.briomax.briobpm.persistence.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.briomax.briobpm.persistence.entity.InVariableEnvio;
import com.briomax.briobpm.persistence.entity.InVariableEnvioPK;

/**
 * El objetivo de la Interface InVariableProcesoRepository.java es ...
 * @author Alexis Zamoea 
 * @version 1.0 Fecha de creacion Nov 22, 2023 Modificaciones:
 * @since JDK 1.8
 */
public interface IInVariableEnvioRepository extends JpaRepository<InVariableEnvio, InVariableEnvioPK> {

	 /**
     * Obtiene los datos de origen para un folio de mensaje de envío específico.
     *
     * @param folioMensajeEnvio El folio del mensaje de envío.
     * @return Una instancia de Optional que contiene los datos de origen si se encuentran, o vacío si no.
     */
	  @Query(value = "SELECT * FROM IN_VARIABLE_ENVIO " +
	            "WHERE CVE_ENTIDAD = :cveEntidadOrigen " +
	            "AND CVE_PROCESO = :cveProcesoOrigen " +
	            "AND VERSION = :versionOrigen " +
	            "AND CVE_INSTANCIA = :cveInstanciaOrigen " +
	            "AND CVE_NODO = :cveNodoOrigen " +
	            "AND ID_NODO = :idNodoOrigen " +
	            "AND SECUENCIA_NODO = :secuenciaNodoOrigen", nativeQuery = true)
	    List<InVariableEnvio> obtenerDatosOrigen(
	            @Param("cveEntidadOrigen") String cveEntidadOrigen,
	            @Param("cveProcesoOrigen") String cveProcesoOrigen,
	            @Param("versionOrigen") BigDecimal version,
	            @Param("cveInstanciaOrigen") String cveInstancia,
	            @Param("cveNodoOrigen") String cveNodo,
	            @Param("idNodoOrigen") Integer idNodoOrigen,
	            @Param("secuenciaNodoOrigen") Integer secuenciaNodo);

}
