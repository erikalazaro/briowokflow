package com.briomax.briobpm.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.briomax.briobpm.persistence.entity.StMensajeRecepcion;
import com.briomax.briobpm.persistence.entity.StMensajeRecepcionPK;
/**
 * El objetivo de la Interface IStMensajeRecepcionRepository.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Dic 22, 2023 Modificaciones:
 * @since JDK 1.8
 */
@Repository
public interface IStMensajeRecepcionRepository extends JpaRepository<StMensajeRecepcion, StMensajeRecepcionPK> {

}
