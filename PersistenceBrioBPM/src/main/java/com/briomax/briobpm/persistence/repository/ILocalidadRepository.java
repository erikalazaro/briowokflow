/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.persistence.repository;

import com.briomax.briobpm.persistence.entity.LocalidadEntidad;
import com.briomax.briobpm.persistence.entity.LocalidadEntidadPK;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * El objetivo de la Interface ILocalidadRepository.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Jul 29, 2020 6:35:45 PM Modificaciones:
 * @since JDK 1.8
 */
@Repository
public interface ILocalidadRepository extends JpaRepository<LocalidadEntidad, LocalidadEntidadPK> {
}
