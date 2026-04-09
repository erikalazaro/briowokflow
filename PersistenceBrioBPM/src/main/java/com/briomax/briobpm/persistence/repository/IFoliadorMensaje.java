package com.briomax.briobpm.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.briomax.briobpm.persistence.entity.FoliadorMensaje;

/**
 * El objetivo de la Interface IFoliadorMensaje.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Dic 15, 2023 12:31:05 PM Modificaciones:
 * @since JDK 1.8
 */
@Repository
public interface IFoliadorMensaje extends JpaRepository<FoliadorMensaje, Integer>{

	// Consulta nativa para obtener el FOLIO_MENSAJE
    @Query(value = "SELECT FOLIO_MENSAJE FROM FOLIADOR_MENSAJE ORDER BY FOLIO_MENSAJE ASC ", nativeQuery = true)
    Optional<Integer> findFolioMensaje();
}
