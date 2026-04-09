package com.briomax.briobpm.persistence.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.briomax.briobpm.persistence.entity.VwDatoActividad;
import com.briomax.briobpm.persistence.entity.VwDatoActividadPK;

/**
 * El objetivo de la Interface IVwDatoActividadRepository.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Mar 11, 2024 6:20:29 PM Modificaciones:
 * @since JDK 1.8
 */
public interface IVwDatoActividadRepository  extends JpaRepository<VwDatoActividad, VwDatoActividadPK> {

	@Query(value = "SELECT USUARIO_BLOQUEA " +
            "FROM VW_DATO_ACTIVIDAD DA " +
            "WHERE DA.CVE_ENTIDAD = :cveEntidad " +
            "  AND DA.CVE_PROCESO = :cveProceso " +
            "  AND DA.VERSION = :version " +
            "  AND DA.CVE_INSTANCIA = :cveInstancia " +
            "  AND DA.CVE_NODO = :cveNodo " +
            "  AND DA.ID_NODO = :idNodo " +
            "  AND DA.SECUENCIA_NODO = :secuenciaNodo", nativeQuery = true)
	String encontrarUsuarioBloquea(
			@Param("cveEntidad") String cveEntidad,
		    @Param("cveProceso") String cveProceso,
		    @Param("version") BigDecimal version,
		    @Param("cveInstancia") String cveInstancia,
		    @Param("cveNodo") String cveNodo,
		    @Param("idNodo") Integer idNodo,
		    @Param("secuenciaNodo") Integer secuenciaNodo);
}
