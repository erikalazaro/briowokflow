/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.persistence.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.briomax.briobpm.persistence.entity.UsuarioRol;
import com.briomax.briobpm.persistence.entity.UsuarioRolPK;

/**
 * El objetivo de la Interface IUsuarioRolRepository.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Jul 27, 2020 6:20:29 PM Modificaciones:
 * @since JDK 1.8
 */
@Repository
public interface IUsuarioRolRepository extends JpaRepository<UsuarioRol, UsuarioRolPK> {

	@Query(value = "SELECT U.CORREO_ELECTRONICO " +
            "FROM USUARIO_ROL UR, USUARIO U " +
            "WHERE UR.CVE_ENTIDAD = :cveEntidad		        AND " + 
            "				UR.CVE_PROCESO = :cveProceso	AND " + 
            "				UR.VERSION = :version			    AND " + 
            "				UR.CVE_ROL = :cveRol			AND "  + 
            "				U.CVE_ENTIDAD = UR.CVE_ENTIDAD		AND " + 
            "				U.CVE_USUARIO = UR.CVE_USUARIO ", nativeQuery = true)
List<String> obtieneDireccionesCorreoRol(@Param("cveEntidad") String cveEntidad,
                                       @Param("cveProceso") String cveProceso,
                                       @Param("version") BigDecimal version,
                                       @Param("cveRol") String cveEventoCorreo);

	@Query(value = "SELECT UR.CVE_ROL " +
            "FROM USUARIO_ROL UR " +
            "WHERE UR.CVE_ENTIDAD = :cveEntidad		        AND " + 
            "				UR.CVE_USUARIO = :cveUsuario    AND" +
            "				UR.CVE_PROCESO = :cveProceso	AND " + 
            "				UR.VERSION = :version ", nativeQuery = true)
List<String> buscarRoles(@Param("cveEntidad") String cveEntidad,
									   @Param("cveUsuario") String cveUsuario,
                                       @Param("cveProceso") String cveProceso,
                                       @Param("version") BigDecimal version);

	@Query(value = "SELECT * " +
            "FROM USUARIO_ROL UR " +
            "WHERE UR.CVE_ENTIDAD = :cveEntidad		        AND " + 
            "				UR.CVE_USUARIO = :cveUsuario  " + 
            " ORDER BY UR.CVE_ROL ", nativeQuery = true)
List<UsuarioRol> buscarUsuario(@Param("cveEntidad") String cveEntidad,
									   @Param("cveUsuario") String cveUsuario);
}
