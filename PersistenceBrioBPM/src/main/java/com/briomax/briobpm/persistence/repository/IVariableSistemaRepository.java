package com.briomax.briobpm.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.briomax.briobpm.persistence.entity.VariableSistema;

/**
 * El objetivo de la Interface IVariableSistemaRepository.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Dic 06, 2023 6:20:29 PM Modificaciones:
 * @since JDK 1.8
 */
@Repository
public interface IVariableSistemaRepository extends JpaRepository<VariableSistema, String> {

}
