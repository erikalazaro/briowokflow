package com.briomax.briobpm.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.briomax.briobpm.persistence.entity.CrDestinatarioCorreo;
import com.briomax.briobpm.persistence.entity.CrDestinatarioCorreoPK;

/**
 * El objetivo de la Interface ICrDestinatarioCorreoRepository.java es ...
 * @author 
 * @version 1.0 Fecha de creacion Jul 22, 2024 Modificaciones:
 * @since JDK 1.8
 */
@Repository
public interface ICrDestinatarioCorreoRepository extends JpaRepository <CrDestinatarioCorreo, CrDestinatarioCorreoPK>{

	 @Query(value = "SELECT * FROM CR_DESTINATARIO_CORREO " +
             "WHERE CVE_ENTIDAD = :cveEntidad " +
             "AND CVE_LOCALIDAD = :cveLocalidad " +
             "AND CVE_IDIOMA = :cveIdioma " +
             "AND CVE_PROCESO_PERIODICO = :cveProcesoPeriodico " +
             "AND SECUENCIA_CORREO = :secuenciaCorreo", nativeQuery = true)
	 List<CrDestinatarioCorreo> buscarDestinatario(@Param("cveEntidad") String cveEntidad,
                                            @Param("cveLocalidad") String cveLocalidad,
                                            @Param("cveIdioma") String cveIdioma,
                                            @Param("cveProcesoPeriodico") String cveProcesoPeriodico,
                                            @Param("secuenciaCorreo") Integer secuenciaCorreo);

	 @Query(value = "SELECT DISTINCT DIRECCION_CORREO FROM CR_DESTINATARIO_CORREO " +
             "WHERE CVE_ENTIDAD = :cveEntidad " +
             "AND CVE_LOCALIDAD = :cveLocalidad " +
             "AND CVE_IDIOMA = :cveIdioma " +
             "AND CVE_PROCESO_PERIODICO = :cveProcesoPeriodico " +
             "AND SECUENCIA_CORREO = :secuenciaCorreo " +
             "AND RFC_CONTRATISTA IN (SELECT DISTINCT cau.RFC FROM CR_ACCESO_USUARIO cau " + 
             "							WHERE cau.CVE_ENTIDAD = :cveEntidad  " + 
             "							AND cau.CVE_USUARIO = :cveUsuario) ", nativeQuery = true)
	 List<String> buscarDestProceso(@Param("cveEntidad") String cveEntidad,
                                            @Param("cveLocalidad") String cveLocalidad,
                                            @Param("cveIdioma") String cveIdioma,
                                            @Param("cveProcesoPeriodico") String cveProcesoPeriodico,
                                            @Param("secuenciaCorreo") Integer secuenciaCorreo,
                                            @Param("cveUsuario") String cveUsuario);
	 
	 
	 @Query(value = "SELECT * FROM CR_DESTINATARIO_CORREO " +
             " WHERE CVE_ENTIDAD = :cveEntidad " +
             " AND CVE_LOCALIDAD = :cveLocalidad " +
             " AND CVE_IDIOMA = :cveIdioma " +
             " AND CVE_PROCESO_PERIODICO = :cveProcesoPeriodico " +
             " AND SECUENCIA_CORREO = :secuenciaCorreo " +
             " AND RFC_CONTRATISTA = :rfc " +
             " AND NUMERO_CONTRATO = :contrato ", nativeQuery = true)
	 List<CrDestinatarioCorreo> buscarDestinatarioByContrato(@Param("cveEntidad") String cveEntidad,
                                            @Param("cveLocalidad") String cveLocalidad,
                                            @Param("cveIdioma") String cveIdioma,
                                            @Param("cveProcesoPeriodico") String cveProcesoPeriodico,
                                            @Param("secuenciaCorreo") Integer secuenciaCorreo,
                                            @Param("rfc") String rfc,
                                            @Param("contrato") String contrato);
}
