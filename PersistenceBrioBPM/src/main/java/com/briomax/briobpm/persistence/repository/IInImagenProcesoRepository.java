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

import com.briomax.briobpm.persistence.entity.InImagenProceso;
import com.briomax.briobpm.persistence.entity.InImagenProcesoPK;

/**
 * El objetivo de la Interface InVariableProcesoRepository.java es ...
 * @author Sara Ventura 
 * @version 1.0 Fecha de creacion Nov 10, 2023 Modificaciones:
 * @since JDK 1.8
 */
@Repository
public interface IInImagenProcesoRepository extends JpaRepository<InImagenProceso, InImagenProcesoPK> {

  
}


