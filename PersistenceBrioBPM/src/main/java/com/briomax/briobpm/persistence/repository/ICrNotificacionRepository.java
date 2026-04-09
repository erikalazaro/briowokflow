package com.briomax.briobpm.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.briomax.briobpm.persistence.entity.CrNotificacion;
import com.briomax.briobpm.persistence.entity.CrNotificacionPK;

/**
 * El objetivo de la Interface ICrNotificacionRepository.java es ...
 * @author 
 * @version 1.0 Fecha de creacion Jul 22, 2024 Modificaciones:
 * @since JDK 1.8
 */
@Repository
public interface ICrNotificacionRepository extends JpaRepository<CrNotificacion, CrNotificacionPK>{

	    @Query(value = "SELECT * FROM CR_NOTIFICACION " +
	                   "WHERE CVE_ENTIDAD = :cveEntidad " +
	                   "AND CVE_LOCALIDAD = :cveLocalidad " +
	                   "AND CVE_IDIOMA = :cveIdioma " +
	                   "AND CVE_PROCESO_PERIODICO = :cveProcesoPeriodico " +
	                   "AND SECUENCIA_CORREO = :secuencia " +
	                   "AND RFC = :rfc " +
	                   "AND CONTRATO = :contrato", 
	           nativeQuery = true)
	    List<CrNotificacion> encuentraNotificaciones(
	            @Param("cveEntidad") String cveEntidad,
	            @Param("cveLocalidad") String cveLocalidad,
	            @Param("cveIdioma") String cveIdioma,
	            @Param("cveProcesoPeriodico") String cveProcesoPeriodico,
	            @Param("secuencia") Integer secuencia,
	            @Param("rfc") String rfc,
	            @Param("contrato") String contrato);

}
