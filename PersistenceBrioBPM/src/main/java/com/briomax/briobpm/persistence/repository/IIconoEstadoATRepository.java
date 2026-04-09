package com.briomax.briobpm.persistence.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.briomax.briobpm.persistence.entity.IconoEstadoAT;
import com.briomax.briobpm.persistence.entity.IconoEstadoATPK;

/**
 * El objetivo de la interfaz IICAToAreaTrabajoRepository.java es proporcionar métodos 
 * para acceder y manipular ICATos relacionados con los iconos en la base de ICATos.
 *
 * @autor Alexis Zamora
 * @version 1.0
 * @fecha Mar 07, 2024 12:30:03 PM
 * @since JDK 1.8
 */
@Repository
public interface IIconoEstadoATRepository extends JpaRepository<IconoEstadoAT, IconoEstadoATPK> {

	@Query(value = "SELECT * " +
	        "FROM ICONO_ESTADO_AT ICAT " +
	        "WHERE ICAT.CVE_ENTIDAD = :cveEntidad " +
	        "    AND ICAT.CVE_PROCESO = :cveProceso " +
	        "    AND ICAT.VERSION = :version " +
	        "    AND ICAT.CVE_AREA_TRABAJO = :cveAreaTrabajo " +
	        "    AND ICAT.SECUENCIA_TARJETA = :secuenciaTarjeta", nativeQuery = true)
	List<IconoEstadoAT> obtieneIconos(
	                                    @Param("cveEntidad") String cveEntidad,
	                                    @Param("cveProceso") String cveProceso,
	                                    @Param("version") BigDecimal version,
	                                    @Param("cveAreaTrabajo") String cveAreaTrabajo,
	                                    @Param("secuenciaTarjeta") int secuenciaTarjeta);

}
