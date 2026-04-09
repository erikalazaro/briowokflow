/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.persistence.repository;

import com.briomax.briobpm.persistence.entity.MensajeIdioma;
import com.briomax.briobpm.persistence.entity.MensajeIdiomaPK;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * El objetivo de la Interface IMensajeIdiomaRepository.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Jun 26, 2020 9:21:00 AM Modificaciones:
 * @since JDK 1.8
 */
@Repository
public interface IMensajeIdiomaRepository extends JpaRepository<MensajeIdioma, MensajeIdiomaPK> {

}
