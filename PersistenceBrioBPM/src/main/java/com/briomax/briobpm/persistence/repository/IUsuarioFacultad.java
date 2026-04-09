package com.briomax.briobpm.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.briomax.briobpm.persistence.entity.UsuarioFacultad;
import com.briomax.briobpm.persistence.entity.UsuarioFacultadPK;

/**
 * El objetivo de la Interface IUsuraioFacultad.java es ...
 * @author Sara Ventura
 * @version 1.0 Fecha de creacion Nov 22, 2024 Modificaciones:
 * @since JDK 1.8
 */
@Repository
public interface IUsuarioFacultad extends JpaRepository<UsuarioFacultad, UsuarioFacultadPK>{


}
