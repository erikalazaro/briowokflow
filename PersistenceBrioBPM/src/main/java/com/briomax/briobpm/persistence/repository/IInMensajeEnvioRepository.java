package com.briomax.briobpm.persistence.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.briomax.briobpm.persistence.entity.InMensajeEnvio;
import com.briomax.briobpm.persistence.entity.InMensajeEnvioPK;

/**
 * El objetivo de la Interface IInNodoProcesoRepository.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Dic 01, 2023 12:30:03 PM:
 * @since JDK 1.8
 */
@Repository
public interface IInMensajeEnvioRepository extends JpaRepository<InMensajeEnvio, InMensajeEnvioPK>{
	
	@Query(value = "SELECT i FROM InMensajeEnvio i WHERE i.folioMensajeEnvio = :folioMensajeEnvio")
	Optional<InMensajeEnvio> obtenerDatosProcesoOrigen(@Param("folioMensajeEnvio") Integer folioMensajeEnvio);
	    

	    @Query(value = "SELECT inme.id.cveEntidad, inme.id.cveProceso, inme.id.version, " +
	              "inme.id.cveInstancia, inme.id.cveNodo, inme.id.idNodo, inme.id.secuenciaNodo, inme.folioMensajeEnvio, " +
	              "inme.valoresReferenciaEnvio, stme.iniciarProcesoDestino, stme.cveEntidadDestino, " +
	              "stme.cveProcesoDestino, stme.versionDestino, stme.cveNodoDestino, stme.idNodoDestino " +
	              "FROM InMensajeEnvio inme inner join StMensajeEnvio stme " +
	              "on inme.id.cveEntidad = stme.id.cveEntidad " +
	              " AND inme.id.cveProceso = stme.id.cveProceso " +
	              " AND inme.id.version = stme.id.version " +
	              " AND inme.id.cveNodo = stme.id.cveNodo " +
	              " AND inme.id.idNodo = stme.id.idNodo " +
	              " where stme.ambitoEnvio = :ambitoWfBdActual and ( "
	              + " inme.situacionEnvio = :situacionPendiente OR "
	              + " inme.situacionEnvio = :situacionError ) " )
	    List<Object> obtenerMensajesEnvio(@Param("ambitoWfBdActual") String ambitoWfBdActual,
                @Param("situacionPendiente") String situacionPendiente,
                @Param("situacionError") String situacionError);
	    
	    @Modifying
		@Query(value = "UPDATE InMensajeEnvio i " +
	              "SET i.situacionEnvio = :situacionEnvio " +
	              "WHERE i.id.cveEntidad = :cveEntidad " +
	              "AND i.id.cveProceso = :cveProceso " +
	              "AND i.id.version = :version " +
	              "AND i.id.cveInstancia = :cveInstancia " +
	              "AND i.folioMensajeEnvio = :folioMensajeEnvio")
		
		void updateSituacion(@Param("situacionEnvio") String situacionEnvio,
				@Param("cveEntidad") String cveEntidad,
				@Param("cveProceso") String cveProceso,
				@Param("version") BigDecimal version,
				@Param("cveInstancia") String cveInstancia,
				@Param("folioMensajeEnvio") Integer folioMensajeEnvio);
		
		
	    @Query(value = "SELECT i.id.cveEntidad, i.id.cveProceso, i.id.version, i.id.cveInstancia, " +
	    		  " i.id.cveNodo, i.id.idNodo, i.id.secuenciaNodo " +
	              " FROM InMensajeRecepcion i " +
	              " WHERE i.situacionRecepcion = :situacionPendiente AND " +
	              " ( " +
	              "    ((i.valoresReferenciaRec IS NULL OR i.valoresReferenciaRec = ' ') AND " +
	              "    (:valoresReferenciaEnvio IS NULL OR :valoresReferenciaEnvio = ' ')) " +
	              "    OR " +
	              "    (:valoresReferenciaEnvio IS NOT NULL AND :valoresReferenciaEnvio <> ' ' AND " +
	              "    :valoresReferenciaRec IS NOT NULL AND valoresReferenciaRec <> ' ' AND :valoresReferenciaEnvio  <> ' ' AND" +
	              "    :valoresReferenciaEnvio = valoresReferenciaRec) " +
	              " )" )
	    List<InMensajeEnvioPK> obtenerMensajesRecepcion(
              @Param("valoresReferenciaEnvio") String valoresReferenciaEnvio,
              @Param("valoresReferenciaRec") String valoresReferenciaRec);

}

