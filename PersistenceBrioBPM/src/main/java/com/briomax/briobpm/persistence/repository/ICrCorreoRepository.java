package com.briomax.briobpm.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.briomax.briobpm.persistence.entity.CrCorreo;
import com.briomax.briobpm.persistence.entity.CrCorreoPK;

/**
 * El objetivo de la Interface ICrDestinatarioCorreoRepository.java es ...
 * @author 
 * @version 1.0 Fecha de creacion Jul 22, 2024 Modificaciones:
 * @since JDK 1.8
 */
@Repository
public interface ICrCorreoRepository extends JpaRepository <CrCorreo, CrCorreoPK>{

	 @Query(value = "SELECT * FROM CR_CORREO " +
             "WHERE CVE_ENTIDAD = :cveEntidad " +
             "AND CVE_LOCALIDAD = :cveLocalidad " +
             "AND CVE_IDIOMA = :cveIdioma ", nativeQuery = true)
	 List<CrCorreo> buscarCorreos(@Param("cveEntidad") String cveEntidad,
                                            @Param("cveLocalidad") String cveLocalidad,
                                            @Param("cveIdioma") String cveIdioma);

}
