/*
 * Copyright (c) 2020 Briomax Consulting S.A. de C.V.; Todos los derechos reservados.
 *
 * Este software contiene informacion confidencial propiedad de Briomax Consulting
 * por lo cual no puede ser reproducido, distribuido o alterado sin el consentimiento
 * previo de Briomax Consulting.
 */

package com.briomax.briobpm.persistence.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.briomax.briobpm.persistence.entity.Usuario;
import com.briomax.briobpm.persistence.entity.UsuarioPK;

/**
 * El objetivo de la Interface IUsuarioRepository.java es ...
 * @author Rigoberto Olvera
 * @version 1.0 Fecha de creacion Jul 27, 2020 1:21:43 PM Modificaciones:
 * @since JDK 1.8
 */
@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, UsuarioPK> {
	
	@Query(value = "SELECT U.* " +
            " FROM	USUARIO			U, " +
			"	LOCALIDAD_ENTIDAD	LE, " +
            "	ENTIDAD			E " +
            " WHERE U.CVE_USUARIO = :cveUsuario AND " +
            "      U.PASSWORD = :chPassword AND " +
            "      LE.CVE_ENTIDAD = U.CVE_ENTIDAD		AND " +
            "      LE.CVE_LOCALIDAD = U.CVE_LOCALIDAD	AND " +
            "      E.CVE_ENTIDAD = LE.CVE_ENTIDAD ", nativeQuery = true)
    List<Usuario> listUsuario(
            @Param("cveUsuario") String cveUsuario,
            @Param("chPassword") String chPassword
    );
	
	
	@Query(value = "SELECT COUNT(1) " +
            " FROM	USUARIO			U, " +
			"	LOCALIDAD_ENTIDAD	LE, " +
            "	ENTIDAD			E " +
            " WHERE U.CVE_ENTIDAD = :cveEntidad AND " +
            "      U.CVE_USUARIO = :cveUsuario AND " +
            "      U.PASSWORD = :chPassword AND " +
            "      U.CVE_LOCALIDAD = :cveLocalidad AND " +
            "      LE.CVE_ENTIDAD = U.CVE_ENTIDAD		AND " +
            "      LE.CVE_LOCALIDAD = U.CVE_LOCALIDAD	AND " +
            "      E.CVE_ENTIDAD = LE.CVE_ENTIDAD ", nativeQuery = true)
    Integer usrExiste(
            @Param("cveEntidad") String cveEntidad,
            @Param("cveUsuario") String cveUsuario,
            @Param("chPassword") String chPassword,
            @Param("cveLocalidad") String cveLocalidad
    );
	

	@Query(value = "SELECT	p.* " +
            " FROM	USUARIO			p, " +
            " 	LOCALIDAD_ENTIDAD	LE, " +
            " 	ENTIDAD			E, " +
            " 	IDIOMA			I, " +
            " 	MONEDA			M " +
            " WHERE	p.CVE_ENTIDAD = :cveEntidad		AND " +
            " 	p.CVE_USUARIO = :cveUsuario		    AND " +
            " 	p.PASSWORD = :chPassword			AND " +
            " 	p.CVE_LOCALIDAD = :cveLocalidad	    AND " +
            " 	LE.CVE_ENTIDAD = p.CVE_ENTIDAD		AND " +
            " 	LE.CVE_LOCALIDAD = p.CVE_LOCALIDAD	AND " +
            " 	E.CVE_ENTIDAD = LE.CVE_ENTIDAD		AND " +
            " 	M.CVE_MONEDA = LE.CVE_MONEDA		AND " +
            " 	I.CVE_IDIOMA = LE.CVE_IDIOMA ", nativeQuery = true)
	List<Usuario> getUrser(
                    @Param("cveEntidad") String cveEntidad,
                    @Param("cveUsuario") String cveUsuario,
                    @Param("chPassword") String chPassword,
                    @Param("cveLocalidad") String cveLocalidad
            );


	@Modifying
    @Transactional
    @Query(value = "INSERT INTO USUARIO " +
                   " (CVE_ENTIDAD, CVE_LOCALIDAD, CVE_USUARIO, " +
                   " PASSWORD, FECHA_PASSWORD, NOMBRE, TIPO, " +
                   " SITUACION, CORREO_ELECTRONICO, CVE_ENTIDAD_LOCALIDAD) " +
                   " VALUES " +
                   " ( :cveEntidad, :cveLocalidad, :cveUsuario, " + 
                   " :chPassword, :fechaPassword, :nombre, :tipo, " +
                   " :situacion, :correo, :cveEntidad)  ", nativeQuery = true)
    void insertFecha(
            @Param("cveEntidad") String cveEntidad,
            @Param("cveLocalidad") String cveLocalidad,
            @Param("cveUsuario") String cveUsuario,
            @Param("chPassword") String chPassword,
            @Param("fechaPassword") Date fechaPassword,
            @Param("nombre") String nombre,
            @Param("tipo") String tipo,
            @Param("situacion") String situacion,
            @Param("correo") String correo);


	@Modifying
    @Transactional
    @Query(value = "UPDATE USUARIO SET PASSWORD = :chPassword " +
                   " WHERE CVE_ENTIDAD = :cveEntidad " +
                   " AND CVE_LOCALIDAD = :cveLocalidad " + 
                   " AND CVE_USUARIO =:cveUsuario ", nativeQuery = true)
    void actualizaPassword(
            @Param("cveEntidad") String cveEntidad,
            @Param("cveLocalidad") String cveLocalidad,
            @Param("cveUsuario") String cveUsuario,
            @Param("chPassword") String chPassword);
	
	@Modifying
    @Transactional
    @Query(value = "UPDATE USUARIO SET SITUACION = :situacion " +
                   " WHERE CVE_ENTIDAD = :cveEntidad " +
                   " AND CVE_LOCALIDAD = :cveLocalidad " + 
                   " AND CVE_USUARIO =:cveUsuario ", nativeQuery = true)
    void actualizaSituacion(
            @Param("cveEntidad") String cveEntidad,
            @Param("cveLocalidad") String cveLocalidad,
            @Param("cveUsuario") String cveUsuario,
            @Param("situacion") String situacion);
	
	@Modifying
    @Transactional
    @Query(value = "UPDATE USUARIO SET SITUACION = :situacion " +
    			   " FECHA_PASSWORD = :fechaPassword,  " +
    			   " NOMBRE = :nombre , TIPO = :tipo , " +
    			   " SITUACION = :situacion, CORREO_ELECTRONICO = :correo " +
    			   " FECHA_ULTIMO_CAMBIO = :fechaCambio, CVE_ENTIDAD_ULTIMO_CAMBIO = :cveEntCambio " +
    			   " CVE_USUARIO_ULTIMO_CAMBIO = :cveUsuCambio " +
                   " WHERE CVE_ENTIDAD = :cveEntidad " +
                   " AND CVE_LOCALIDAD = :cveLocalidad " + 
                   " AND CVE_USUARIO =:cveUsuario ", nativeQuery = true)
    void actualizaInfomación(
            @Param("cveEntidad") String cveEntidad,
            @Param("cveLocalidad") String cveLocalidad,
            @Param("cveUsuario") String cveUsuario,
            @Param("fechaPassword") Date fechaPassword,
            @Param("nombre") String nombre,
            @Param("tipo") String tipo,
            @Param("situacion") String situacion,
            @Param("correo") String correo,
            @Param("cveEntCambio") String cveEntCambio,
            @Param("fechaCambio") Date fechaCambio,
            @Param("cveUsuCambio") String cveUsuCambio);
	
	@Modifying
    @Transactional
    @Query(value = "INSERT INTO USUARIO " +
                   " (CVE_ENTIDAD, CVE_LOCALIDAD, CVE_USUARIO, " +
                   " PASSWORD, NOMBRE, TIPO, " +
                   " SITUACION, CORREO_ELECTRONICO, CVE_ENTIDAD_LOCALIDAD) " +
                   " VALUES " +
                   " ( :cveEntidad, :cveLocalidad, :cveUsuario, " + 
                   " :chPassword, :nombre, :tipo, " +
                   " :situacion, :correo, :cveEntidad)  ", nativeQuery = true)
    void insertSinFecha(
            @Param("cveEntidad") String cveEntidad,
            @Param("cveLocalidad") String cveLocalidad,
            @Param("cveUsuario") String cveUsuario,
            @Param("chPassword") String chPassword,
            @Param("nombre") String nombre,
            @Param("tipo") String tipo,
            @Param("situacion") String situacion,
            @Param("correo") String correo);
	
	
	@Query(value = "SELECT U.* " +
            " FROM	USUARIO			U, " +
			"	LOCALIDAD_ENTIDAD	LE, " +
            "	ENTIDAD			E " +
            " WHERE U.CVE_USUARIO = :cveUsuario AND " +
            "      U.CORREO_ELECTRONICO LIKE :correo AND " +
            "      LE.CVE_ENTIDAD = U.CVE_ENTIDAD		AND " +
            "      LE.CVE_LOCALIDAD = U.CVE_LOCALIDAD	AND " +
            "      E.CVE_ENTIDAD = LE.CVE_ENTIDAD ", nativeQuery = true)
    List<Usuario> usuarioReset(
            @Param("cveUsuario") String cveUsuario,
            @Param("correo") String correo );
	

	@Modifying
    @Transactional
    @Query(value = "UPDATE USUARIO SET PASSWORD = :chPassword " +
                   " WHERE CORREO_ELECTRONICO like :correo " +
                   " AND CVE_USUARIO =:cveUsuario ", nativeQuery = true)
    void actualizaPasswordCorreo(
            @Param("correo") String correo,
            @Param("cveUsuario") String cveUsuario,
            @Param("chPassword") String chPassword);
	
	
	@Query(value = "SELECT u.CORREO_ELECTRONICO " + 
			"FROM USUARIO u, CR_ACCESO_USUARIO cau " + 
			"WHERE cau.CVE_ENTIDAD = u.CVE_ENTIDAD " + 
			"	and cau.CVE_USUARIO  = u.CVE_USUARIO " + 
			"	and cau.RFC = :rfc " + 
			"	and cau.TIPO_ACCESO_CR = :acceso " +
			"	and cau.CVE_ENTIDAD = :cveEntidad ", nativeQuery = true)
    List<String> buscaCorreos(
            @Param("rfc") String rfc,
            @Param("cveEntidad") String cveEntidad,
            @Param("acceso") String acceso );
}
