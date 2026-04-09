package com.briomax.briobpm.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.briomax.briobpm.persistence.entity.InNodoProcesoUsuario;
import com.briomax.briobpm.persistence.entity.InNodoProcesoUsuarioPK;

/**
 * El objetivo de la Interface IInNodoProcesoUsuarioRepository.java es ...
 * @author Sara Ventura
 * @version 1.0 Fecha de creacion Jun 06, 2025
 * @since JDK 1.8
 */
@Repository
public interface IInNodoProcesoUsuarioRepository extends JpaRepository<InNodoProcesoUsuario, InNodoProcesoUsuarioPK> {

  }
