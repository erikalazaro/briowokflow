package com.briomax.briobpm.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.briomax.briobpm.persistence.entity.CrAccesoUsuario;
import com.briomax.briobpm.persistence.entity.CrAccesoUsuarioPK;

/**
 * El objetivo de la Interface ICrAccesoUsuarioRepository.java es ...
 * @author Sara ventura
 * @version 1.0 Fecha de creacion Nov 25, 2025 12:08:29 Modificaciones:
 * @since JDK 1.8
 */
@Repository
public interface ICrAccesoUsuarioRepository extends JpaRepository<CrAccesoUsuario, CrAccesoUsuarioPK>{


	 
	@Query(value = "SELECT * FROM CR_ACCESO_USUARIO " + 
			" WHERE	CVE_ENTIDAD = :cveEntidad AND " +
			" USUARIO_CONTRATANTE like :usuario ", nativeQuery = true)
	List<CrAccesoUsuario> obtenContratista( @Param("cveEntidad") String cveEntidad, 
			@Param("usuario") String usuario);
}
