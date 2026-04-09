package com.briomax.briobpm.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.briomax.briobpm.persistence.entity.CrPeriodicidad;

@Repository
public interface ICrPeriodicidadRepository extends JpaRepository<CrPeriodicidad, String>{


}
