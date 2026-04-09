package com.briomax.briobpm.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.briomax.briobpm.persistence.entity.VariableLocalidad;
import com.briomax.briobpm.persistence.entity.VariableLocalidadPK;

/**
 * El objetivo de la Interface IVariableSistemaRepository.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Dic 11, 2023 6:20:29 PM Modificaciones:
 * @since JDK 1.8
 */
public interface IVariableLocalidadRepository extends JpaRepository<VariableLocalidad, VariableLocalidadPK> {

}
