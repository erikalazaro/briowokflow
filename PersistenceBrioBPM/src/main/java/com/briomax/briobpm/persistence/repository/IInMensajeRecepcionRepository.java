package com.briomax.briobpm.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.briomax.briobpm.persistence.entity.InMensajeRecepcion;
import com.briomax.briobpm.persistence.entity.InMensajeRecepcionPK;

/**
 * El objetivo de la Interface IInMensajeRecepcionRepository.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Dic 11, 2024 12:30:03 PM:
 * @since JDK 1.8
 */
@Repository
public interface IInMensajeRecepcionRepository extends JpaRepository<InMensajeRecepcion, InMensajeRecepcionPK>{

}
