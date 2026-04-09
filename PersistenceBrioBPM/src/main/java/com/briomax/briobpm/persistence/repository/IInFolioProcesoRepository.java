package com.briomax.briobpm.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.briomax.briobpm.persistence.entity.InFolioProceso;
import com.briomax.briobpm.persistence.entity.InFolioProcesoPK;

/**
 * El objetivo de la Interface IStProcesoRepository.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Nov 07, 2023 12:30:03 PM:
 * @since JDK 1.8
 */
@Repository
public interface IInFolioProcesoRepository extends JpaRepository<InFolioProceso, InFolioProcesoPK> {

	/**
	 * Find distinct procesos by obtenerFolio.
	 * @param obtenerFolio el cve entidad.
	 * @return el procesos by entidad.
	 */
	Optional<InFolioProceso> findById(InFolioProcesoPK id);
}
