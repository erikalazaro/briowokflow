package com.briomax.briobpm.persistence.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.briomax.briobpm.persistence.entity.DestinarioCorreo;
import com.briomax.briobpm.persistence.entity.DestinarioCorreoPK;

/**
 * El objetivo de la Interface IDestinarioCorreoRepository.java es ...
 * @author Alexis Zamora
 * @version 1.0 Fecha de creacion Ene 08, 2024 12:31:05 PM Modificaciones:
 * @since JDK 1.8
 */
@Repository
public interface IDestinarioCorreoRepository extends JpaRepository<DestinarioCorreo, DestinarioCorreoPK>{

	@Query(value = "SELECT * " +
            "FROM (" +
            "    SELECT CVE_ENTIDAD,"  +
            " 			CVE_PROCESO, " + 
            " 			VERSION, " + 
            " 			CVE_EVENTO_CORREO, " + 
            " 			SECUENCIA_CORREO, " + 
            " 			GRUPO_CORREO, " + 
            " 			SECUENCIA_DESTINATARIO, " + 
            "			TIPO_DESTINATARIO, " + 
            "			USO_CVE_USUARIO_CREADOR, " + 
            "			CVE_ROL," + 
            " 			CVE_USUARIO " +
            "    FROM DESTINATARIO_CORREO " +
            "    WHERE  CVE_ENTIDAD =:cveEntidad AND " + 
            "			CVE_PROCESO =:cveProceso AND "  + 
            "			VERSION =:version AND "  + 
            "			CVE_EVENTO_CORREO =:cveEventoCorreo AND " + 
            "			SECUENCIA_CORREO =:secuenciaCorreo " +
            "    UNION ALL " +
            "    SELECT " + 
            "			'' CVE_ENTIDAD,"  +
            " 			'' CVE_PROCESO, " + 
            " 			'' VERSION, " + 
            " 			'' CVE_EVENTO_CORREO, " + 
            " 			'' SECUENCIA_CORREO, " +
            "			'GRUPO_UNO' GRUPO_CORREO," + 
            " 			1 SECUENCIA_DESTINATARIO, " + 
            "			:tipoDestinatarioPara TIPO_DESTINATARIO, " + 
            "			NULL USO_CVE_USUARIO_CREADOR, "  + 
            "			:cveRolDestinatario CVE_ROL, " + 
            "			:cveUsuarioDestinatario CVE_USUARIO" +
            ") D " +
            "WHERE  D.CVE_ROL IS NOT NULL OR " + 
            "		D.CVE_USUARIO IS NOT NULL " +
            "ORDER BY D.GRUPO_CORREO, D.SECUENCIA_DESTINATARIO", nativeQuery = true)
	List<DestinarioCorreo> obtenerDestinatariosCorreoBk(@Param("cveEntidad") String cveEntidad,
                                       @Param("cveProceso") String cveProceso,
                                       @Param("version") BigDecimal version,
                                       @Param("cveEventoCorreo") String cveEventoCorreo,
                                       @Param("secuenciaCorreo") Integer secuenciaCorreo,
                                       @Param("tipoDestinatarioPara") String tipoDestinatarioPara,
                                       @Param("cveRolDestinatario") String cveRolDestinatario,
                                       @Param("cveUsuarioDestinatario") String cveUsuarioDestinatario);
	

	
	@Query(value =  "SELECT p " +
            "    FROM DestinarioCorreo p " +
            "    WHERE p.id.cveEntidad =:cveEntidad " +
            "		AND p.id.cveProceso =:cveProceso " + 
            "		AND p.id.version =:version " +
            "		AND p.id.cveEventoCorreo =:cveEventoCorreo " + 
            "		AND p.id.secuenciaCorreo =:secuenciaCorreo " +            
            "    	AND (p.cveRol IS NOT NULL OR p.cveUsuario IS NOT NULL) " +
            "	ORDER BY p.id.grupoCorreo, p.id.secuenciaDestinatario ")
	List<DestinarioCorreo> obtenerDestinatariosCorreo_bk(@Param("cveEntidad") String cveEntidad,
                                       @Param("cveProceso") String cveProceso,
                                       @Param("version") BigDecimal version,
                                       @Param("cveEventoCorreo") String cveEventoCorreo,
                                       @Param("secuenciaCorreo") Integer secuenciaCorreo);
	
	
	@Query(value = "SELECT	D.GRUPO_CORREO, " + 
			"				D.SECUENCIA_DESTINATARIO, " + 
			"				D.TIPO_DESTINATARIO, " + 
			"				D.USO_CVE_USUARIO_CREADOR, " + 
			"				D.CVE_ROL, " + 
			"				D.CVE_USUARIO " + 
			"		FROM " + 
			"		( " + 
			"			SELECT	GRUPO_CORREO, " + 
			"					SECUENCIA_DESTINATARIO, " + 
			"					TIPO_DESTINATARIO, " + 
			"					USO_CVE_USUARIO_CREADOR, " + 
			"					CVE_ROL, " + 
			"					CVE_USUARIO " + 
 
			"				FROM	DESTINATARIO_CORREO " + 

			"				WHERE	CVE_ENTIDAD = :cveEntidad	AND " + 
			"					CVE_PROCESO = :cveProceso		AND " + 
			"					VERSION = :version				AND " + 
			"					CVE_EVENTO_CORREO = :cveEventoCorreo		AND " + 
			"					SECUENCIA_CORREO = :secuenciaCorreo " + 
			"			UNION ALL " + 

			"			SELECT	'GRUPO_UNO'				AS	GRUPO_CORREO, " + 
			"					1						AS	SECUENCIA_DESTINATARIO, " + 
			"					:tipoDestinatarioPara	AS	TIPO_DESTINATARIO, " + 
			"					'SI'					AS	USO_CVE_USUARIO_CREADOR, " + 
			"					:cveRolDestinatario		AS	CVE_ROL, " + 
			"					:cveUsuarioDestinatario	AS	CVE_USUARIO " + 
			"			)	D " + 

			"		WHERE	D.CVE_ROL IS NOT NULL		OR " + 
			"			D.CVE_USUARIO IS NOT NULL " + 

			"		ORDER BY " + 
			"				D.GRUPO_CORREO, " + 
			"				D.SECUENCIA_DESTINATARIO", nativeQuery = true )
	List<Object[]> obtenerDestinatariosCorreo(@Param("cveEntidad") String cveEntidad,
                                       @Param("cveProceso") String cveProceso,
                                       @Param("version") BigDecimal version,
                                       @Param("cveEventoCorreo") String cveEventoCorreo,
                                       @Param("secuenciaCorreo") Integer secuenciaCorreo	,
                                       @Param("tipoDestinatarioPara") String tipoDestinatarioPara,
                                       @Param("cveRolDestinatario") String	cveRolDestinatario,
                                       @Param("cveUsuarioDestinatario") String cveUsuarioDestinatario);

}
