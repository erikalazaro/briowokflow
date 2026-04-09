package com.briomax.briobpm.persistence.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.briomax.briobpm.persistence.entity.StNodoSiguiente;
import com.briomax.briobpm.persistence.entity.StNodoSiguientePK;

/**
 * El objetivo de la Interface IInDocumentoProcesoRepository.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Nov 27, 2023 Modificaciones:
 * @since JDK 1.8
 */
@Repository
public interface IStNodoSiguienteRepository extends JpaRepository<StNodoSiguiente, StNodoSiguientePK>{
	
	@Query(value = "SELECT COUNT(1) " +
            "FROM ST_NODO_SIGUIENTE STNS " +
			" LEFT JOIN IN_NODO_PROCESO INNP ON " +
			 "      INNP.CVE_ENTIDAD = STNS.CVE_ENTIDAD AND " +
	            "      INNP.CVE_PROCESO = STNS.CVE_PROCESO AND " +
	            "      INNP.VERSION = STNS.VERSION AND " +
	            "      INNP.CVE_INSTANCIA = :cveInstancia AND " +
	            "      INNP.CVE_NODO = STNS.CVE_NODO AND " +
	            "      INNP.CVE_NODO IN (:nodos) AND " +
	            "      INNP.ID_NODO = STNS.ID_NODO " +
            "WHERE STNS.CVE_ENTIDAD = :cveEntidad AND " +
            "      STNS.CVE_PROCESO = :cveProceso AND " +
            "      STNS.VERSION = :version AND " +
            "      STNS.CVE_NODO_SIGUIENTE = :cveNodoCompuerta AND " +
            "      STNS.ID_NODO_SIGUIENTE = :idNodoCompuerta  ", nativeQuery = true)
    Integer contarNodosPrevios(
            @Param("cveEntidad") String cveEntidad,
            @Param("cveProceso") String cveProceso,
            @Param("version") BigDecimal version,
            @Param("cveNodoCompuerta") String cveNodoCompuerta,
            @Param("idNodoCompuerta") Integer idNodoCompuerta,
            @Param("cveInstancia") String cveInstancia,
            @Param("nodos") List<String> nodos
    );

    @Query(value = "SELECT COUNT(1)  " +
            "FROM ST_NODO_SIGUIENTE STNS, IN_NODO_PROCESO INNP " +
            "WHERE STNS.CVE_ENTIDAD = :cveEntidad AND " +
            "      STNS.CVE_PROCESO = :cveProceso AND " +
            "      STNS.VERSION = :version AND " +
            "      STNS.CVE_NODO_SIGUIENTE = :cveNodoCompuerta AND " +
            "      STNS.ID_NODO_SIGUIENTE = :idNodoCompuerta AND " +
            "      INNP.CVE_ENTIDAD = STNS.CVE_ENTIDAD AND " +
            "      INNP.CVE_PROCESO = STNS.CVE_PROCESO AND " +
            "      INNP.VERSION = STNS.VERSION AND " +
            "      INNP.CVE_INSTANCIA = :cveInstancia AND " +
            "      INNP.CVE_NODO = STNS.CVE_NODO AND " +
            "      INNP.ID_NODO = STNS.ID_NODO AND " +
            "      INNP.CVE_NODO IN (:nodos) AND " +
            "      INNP.ESTADO = 'TERMINADA'", nativeQuery = true)
    Integer contarNodosTerminados(
		@Param("cveEntidad") String cveEntidad,
		@Param("cveProceso") String cveProceso,
		@Param("version") BigDecimal version,
		@Param("cveNodoCompuerta") String cveNodoCompuerta,
		@Param("idNodoCompuerta") Integer idNodoCompuerta,
		@Param("cveInstancia") String cveInstancia,
		@Param("nodos") List<String> nodos
		);
    
    @Query(value = "SELECT COUNT(1) " +
            "FROM ST_NODO_SIGUIENTE " +
            "WHERE CVE_ENTIDAD = :cveEntidad " +
            "  AND CVE_PROCESO = :cveProceso " +
            "  AND VERSION = :version " +
            "  AND CVE_NODO = :cveNodo " +
            "  AND ID_NODO = :idNodo " +
            "  AND TIPO_NODO_SIGUIENTE = :tipoNodoSiguiente", nativeQuery = true)
    int encontrarRegistroExistente(
	     @Param("cveEntidad") String cveEntidad,
	     @Param("cveProceso") String cveProceso,
	     @Param("version") BigDecimal version,
	     @Param("cveNodo") String cveNodo,
	     @Param("idNodo") Integer idNodo,
	     @Param("tipoNodoSiguiente") String tipoNodoSiguiente);
    
    @Query(value = "SELECT * " +
            "FROM ST_NODO_SIGUIENTE " +
            "WHERE CVE_ENTIDAD = :cveEntidad " +
            "  AND CVE_PROCESO = :cveProceso " +
            "  AND VERSION = :version " +
            "  AND CVE_NODO = :cveNodo " +
            "  AND ID_NODO = :idNodo " 
           + "  AND ((CVE_NODO = 'ACTIVIDAD-USUARIO-TEMPORIZACION' " + 
            "  AND TIPO_NODO_SIGUIENTE = :tipoNodoSiguiente) OR " +
            "  (CVE_NODO <> 'ACTIVIDAD-USUARIO-TEMPORIZACION' ))" 
            , nativeQuery = true)
    List<StNodoSiguiente> obtieneListaNodosSiguiente(
	     @Param("cveEntidad") String cveEntidad,
	     @Param("cveProceso") String cveProceso,
	     @Param("version") BigDecimal version,
	     @Param("cveNodo") String cveNodo,
	     @Param("idNodo") Integer idNodoProceso,
	     @Param("tipoNodoSiguiente") String tipoNodoSiguiente);
    
    @Query(value = "SELECT * " +
            "FROM ST_NODO_SIGUIENTE " +
            "WHERE CVE_ENTIDAD = :cveEntidad " +
            "  AND CVE_PROCESO = :cveProceso " +
            "  AND CVE_NODO = :cveNodo " +
            "  AND ID_NODO = :idNodo ", nativeQuery = true)
    List<StNodoSiguiente> obtieneProcesoNodosSiguiente( 
	     @Param("cveEntidad") String cveEntidad,
	     @Param("cveProceso") String cveProceso,
	     @Param("cveNodo") String cveNodo,
	     @Param("idNodo") Integer idNodoProceso);

}
