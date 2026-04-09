package com.briomax.briobpm.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.briomax.briobpm.persistence.entity.InFolioNodo;
import com.briomax.briobpm.persistence.entity.InFolioNodoPK;

/**
 * El objetivo de la Interface IInFolioNodoRepository.java es ...
 * 
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Nov 15, 2023 12:30:03 PM:
 * @since JDK 1.8
 */
@Repository
public interface IInFolioNodoRepository extends JpaRepository<InFolioNodo, InFolioNodoPK> {

	
}
