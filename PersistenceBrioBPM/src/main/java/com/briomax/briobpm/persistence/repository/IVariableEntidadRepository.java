package com.briomax.briobpm.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.briomax.briobpm.persistence.entity.VariableEntidad;
import com.briomax.briobpm.persistence.entity.VariableEntidadPK;

/**
 * El objetivo de la Interface IVariableSistemaRepository.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Dic 08, 2023 6:20:29 PM Modificaciones:
 * @since JDK 1.8
 */
public interface IVariableEntidadRepository  extends JpaRepository<VariableEntidad, VariableEntidadPK> {

}
