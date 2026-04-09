/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.persistence.repository;

import com.briomax.briobpm.persistence.entity.Moneda;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * El objetivo de la Interface IMonedaRepository.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Jun 14, 2021 12:31:05 PM Modificaciones:
 * @since JDK 1.8
 */
@Repository
public interface IMonedaRepository extends JpaRepository<Moneda, String> {

}
