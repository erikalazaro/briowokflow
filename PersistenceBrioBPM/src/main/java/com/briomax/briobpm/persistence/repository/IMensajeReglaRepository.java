package com.briomax.briobpm.persistence.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.briomax.briobpm.persistence.entity.MensajeRegla;
import com.briomax.briobpm.persistence.entity.MensajeReglaPK;

@Repository
public interface IMensajeReglaRepository extends JpaRepository<MensajeRegla, MensajeReglaPK> {

	@Query(
		    value = "SELECT DISTINCT MR.NUMERO_MENSAJE, " +
		            "                MR.CVE_IDIOMA, " +
		            "                MR.MENSAJE " +
		            "FROM   REGLA_VARIABLE_SECCION RVS, " +
		            "       MENSAJE_REGLA MR " +
		            "WHERE  RVS.CVE_ENTIDAD = :cveEntidad " +
		            "AND    RVS.CVE_PROCESO = :cveProceso " +
		            "AND    RVS.VERSION = :version " +
		            "AND    RVS.CVE_NODO = :cveNodo " +
		            "AND    RVS.ID_NODO = :idNodo " +
		            "AND    MR.CVE_ENTIDAD = RVS.CVE_ENTIDAD " +
		            "AND    MR.CVE_PROCESO = RVS.CVE_PROCESO " +
		            "AND    MR.VERSION = RVS.VERSION " +
		            "AND    MR.CVE_REGLA = RVS.CVE_REGLA " +
		            "AND    MR.CVE_IDIOMA = :cveIdioma " +
		            "ORDER BY MR.NUMERO_MENSAJE",
		    nativeQuery = true)
		List<Object[]> obtenerMensajesRegla(
		    @Param("cveEntidad") String cveEntidad,
		    @Param("cveProceso") String cveProceso,
		    @Param("version") BigDecimal version,
		    @Param("cveNodo") String cveNodo,
		    @Param("idNodo") Integer idNodo,
		    @Param("cveIdioma") String cveIdioma);	
		
		@Query("SELECT mr FROM MensajeRegla mr " +
		           "WHERE mr.id.cveEntidad = :cveEntidad AND " +
		           "mr.id.cveProceso = :cveProceso AND " +
		           "mr.id.version = :version AND " +
		           "mr.id.cveRegla = :cveRegla AND " +
		           "mr.id.cveIdioma = :cveIdioma")
		    List<MensajeRegla> findByParameters(
		        @Param("cveEntidad") String cveEntidad,
		        @Param("cveProceso") String cveProceso,
		        @Param("version") BigDecimal version,
		        @Param("cveRegla") String cveRegla,
		        @Param("cveIdioma") String cveIdioma
		    );
		
		/*@Query("SELECT mr FROM MensajeRegla mr " +
				           "WHERE mr.id.cveEntidad = :cveEntidad AND " +
				           "mr.id.cveProceso = :cveProceso AND " +
				           "mr.id.version = :version AND " +
				           "mr.id.cveRegla = :cveRegla AND " +
				           "mr.id.cveIdioma = :cveIdioma AND " +
				           "mr.id.tipoMensaje = :tipoMensaje ")
		MensajeRegla obtenerMensajesReglaTipo(
			    @Param("cveEntidad") String cveEntidad,
			    @Param("cveProceso") String cveProceso,
			    @Param("version") BigDecimal version,
			    @Param("cveNodo") String cveNodo,
			    @Param("idNodo") Integer idNodo,
			    @Param("cveIdioma") String cveIdioma,
			    @Param("tipoMensaje") String tipoMensaje);	*/
}
