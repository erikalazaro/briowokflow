package com.briomax.briobpm.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.briomax.briobpm.persistence.entity.PropositoCorreo;
import com.briomax.briobpm.persistence.entity.PropositoCorreoPK;

@Repository
public interface IPropositoCorreoRepository extends JpaRepository<PropositoCorreo, PropositoCorreoPK>{
	
	@Query(value = "SELECT * " +
    		"FROM PROPOSITO_CORREO P " +
            "WHERE P.CVE_PROPOSITO = :cveProposito", nativeQuery = true)
	PropositoCorreo correoReset(
			 @Param("cveProposito") String cveProposito);
	
}
