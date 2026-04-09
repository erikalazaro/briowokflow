/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.briomax.briobpm.persistence.entity.QueryServicioBotonBW;
import com.briomax.briobpm.persistence.entity.QueryServicioBotonBWPK;

/**
 * El objetivo de la Interface IQueryServicioBotonBWRepository.java.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Oct 31, 2024 Modificaciones:
 * @since JDK 1.8
 */
@Repository
public interface IQueryServicioBotonBWRepository extends JpaRepository<QueryServicioBotonBW, QueryServicioBotonBWPK> {
}
