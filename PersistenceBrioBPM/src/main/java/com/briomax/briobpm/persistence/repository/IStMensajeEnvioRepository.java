package com.briomax.briobpm.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.briomax.briobpm.persistence.entity.StMensajeEnvio;
import com.briomax.briobpm.persistence.entity.StMensajeEnvioPK;

/**
 * El objetivo de la Interface IStNodoProcesoRepository.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Dic 2023, 2023 Modificaciones:
 * @since JDK 1.8
 */
@Repository
public interface IStMensajeEnvioRepository extends JpaRepository<StMensajeEnvio, StMensajeEnvioPK>{

}
