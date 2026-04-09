package com.briomax.briobpm.persistence.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.briomax.briobpm.persistence.entity.ReglaVariableSeccion;
import com.briomax.briobpm.persistence.entity.ReglaVariableSeccionPK;
/**
 * El objetivo de la clase IReglaVariableSeccionRepository.java es obtener las reglas asociadas a una actividad.
 *
 * @author Erika Vazquez
 * @ver 1.0
 * @fecha Sep 10, 2024 4:12:01 PM
 * @since JDK 11
 */
public interface IReglaVariableSeccionRepository extends JpaRepository<ReglaVariableSeccion, ReglaVariableSeccionPK> {
	@Query(value =" SELECT rp.id.cveRegla, "  +
		       "        rp.tipoExpresion, "  +
		       "        stvs.id.cveVariable, "  +
		       "        rvs.aplicarEnCaptura, "  +
		       "        rvs.aplicarEnGuardado, "  +
		       "        rvs.apicarEnTerminacion, "  +
		       "        npr.notacionPolaca, "  +
		       "        stsn.id.cveEntidad, "  +
		       "        stsn.id.cveProceso, "  +
		       "        stsn.id.version, "  +
		       "        stsn.id.cveNodo, "  +
		       "        stsn.id.idNodo "  +
		       " FROM StSeccionNodo stsn, "  +
		       "      StVariableSeccion stvs, "  +
		       "      ReglaVariableSeccion rvs, "  +
		       "      ReglaProceso rp, "  +
		       "      NotacionPolacaRegla npr "  +
		       " WHERE stsn.id.cveEntidad = :cveEntidad "  +
		       "   AND stsn.id.cveProceso = :cveProceso "  +
		       "   AND stsn.id.version = :version "  +
		       "   AND stsn.id.cveNodo = :cveNodo "  +
		       "   AND stsn.id.idNodo = :idNodo "  +
		       "   AND stvs.id.cveEntidad = stsn.id.cveEntidad "  +
		       "   AND stvs.id.cveProceso = stsn.id.cveProceso "  +
		       "   AND stvs.id.version = stsn.id.version "  +
		       "   AND stvs.id.cveNodo = stsn.id.cveNodo "  +
		       "   AND stvs.id.idNodo = stsn.id.idNodo "  +
		       "   AND stvs.id.cveSeccion = stsn.id.cveSeccion "  +
		       "   AND rvs.id.cveEntidad = stvs.id.cveEntidad "  +
		       "   AND rvs.id.cveProceso = stvs.id.cveProceso "  +
		       "   AND rvs.id.version = stvs.id.version "  +
		       "   AND rvs.id.cveNodo = stvs.id.cveNodo "  +
		       "   AND rvs.id.idNodo = stvs.id.idNodo "  +
		       "   AND rvs.id.cveSeccion = stvs.id.cveSeccion "  +
		       "   AND rvs.id.cveVariable = stvs.id.cveVariable "  +
		       "   AND rp.id.cveEntidad = rvs.id.cveEntidad "  +
		       "   AND rp.id.cveProceso = rvs.id.cveProceso "  +
		       "   AND rp.id.version = rvs.id.version "  +
		       "   AND rp.id.cveRegla = rvs.id.cveRegla "  +
		       "   AND npr.id.cveEntidad = rp.id.cveEntidad "  +
		       "   AND npr.id.cveProceso = rp.id.cveProceso "  +
		       "   AND npr.id.version = rp.id.version "  +
		       "   AND npr.id.cveRegla = rp.id.cveRegla "  +
		       " ORDER BY stsn.ordenPresentacion, "  +
		       "          stvs.numeroRenglon, "  +
		       "          stvs.id.cveVariable, "  +
		       "          rvs.id.ordenAplicacion" )
	List<Object> especificacionReglas(
			@Param("cveEntidad" ) String cveEntidad,
			@Param("cveProceso" ) String cveProceso,
			@Param("version" ) BigDecimal version,
			@Param("cveNodo" ) String cveNodo,
			@Param("idNodo" ) Integer idNodo);
}
