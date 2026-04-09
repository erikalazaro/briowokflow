package com.briomax.briobpm.persistence.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.briomax.briobpm.persistence.entity.ReglaBotonActividad;
import com.briomax.briobpm.persistence.entity.ReglaBotonActividadPK;
/**
 * El objetivo de la clase IReglaVariableSeccionRepository.java es obtener las reglas asociadas a una actividad.
 *
 * @author Alexis Zamora
 * @ver 1.0
 * @fecha Sep 10, 2024 4:12:01 PM
 * @since JDK 11
 */
public interface IReglaBotonActividadRepository extends JpaRepository<ReglaBotonActividad, ReglaBotonActividadPK> {

	// consulta para traer una lista de reglas de proceso terminar
	
	@Query("SELECT r FROM ReglaBotonActividad r "
			+ " WHERE r.id.cveEntidad = :cveEntidad AND r.id.cveProceso = :cveProceso "
			+ " 	AND r.id.version = :version AND  r.id.cveNodo = :cveNodo "
			+ "		AND r.id.idNodo = :idNodo AND r.origenFormulario = :origen AND  r.id.tipoBoton = :tipoBoton ORDER BY r.ordenAplicacion " )
	List<ReglaBotonActividad> encuentraReglasTerminar(
			@Param("cveEntidad") String cveEntidad,
			@Param("cveProceso") String cveProceso,
			@Param("version") BigDecimal version,
			@Param("cveNodo") String cveNodo,
			@Param("idNodo") Integer idNodo,
			@Param("origen") String origen,
			@Param("tipoBoton") String tipoBoton);

}
